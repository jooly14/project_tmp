package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class ChatServerThread extends Thread{
	HashMap<String, PrintWriter> map;	//현재 접속자
	JTextArea ta;						//서버 창
	DefaultTableModel modelAll;			//모든 접속자 테이블 모델
	String id;							//연결되어 있는 사용자의 아이디
	
	BufferedReader br;
	PrintWriter pw;
	Socket clientSocket;				//나중에 닫으려고
	ChatServer chatServer;				
	public ChatServerThread(ChatServer chatServer,HashMap<String, PrintWriter> map,Socket clientSocket, JTextArea ta,DefaultTableModel modelAll) {
		this.chatServer = chatServer;
		this.clientSocket = clientSocket;
		
		this.map =map;
		this.ta = ta;
		this.modelAll =modelAll;
		try {
			pw = new PrintWriter(clientSocket.getOutputStream(),true);
			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean exist = true;
			while(exist){	//사용자가 사용하고자 하는 아이디가 이미 사용중이 아이디인지 확인
				exist = false;
				id = br.readLine();
				for (int i = 0; i < chatServer.modelAll.getRowCount(); i++) {
					if(chatServer.modelAll.getValueAt(i, 0).equals(id)){
						pw.println("/exist");	//이미 사용중인 아이디임을 사용자에게 알려줌
						exist = true;
						break;
					}
				}
			}
			
			pw.println("/comein");
			ta.append(id+"님이 입장하셨습니다."+"\n");
			ta.setCaretPosition(ta.getDocument().getLength());
			pw.println("환영합니다. "+id+"님 /room");
			synchronized (map) {
				//map에 더하기 전에 기존 멤버들에게 입장을 알림
				for(Map.Entry<String, PrintWriter> entry: map.entrySet()){
					entry.getValue().println("/uin "+id);
					entry.getValue().println(id+"님이 입장하셨습니다. /room");
				}
				//새로 입장한 멤버에게 기존 멤버 명단을 뿌려줌
				for(String s:map.keySet()){
					pw.println("/user " +s);
				}
				map.put(id, pw);
			}
			synchronized (chatServer.getRoomMap()) {
				for(Map.Entry<String, Room> entry: chatServer.getRoomMap().entrySet()){
					pw.println("/roomlist "+entry.getKey()+" "+entry.getValue().getName()+" "+(entry.getValue().isSecretRoom()?"YES":"NO"));
				}
			}
			modelAll.addRow(new String[]{id.toString()});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		String msg = null;
		String[] split = null;
			try {
				while((msg = br.readLine())!=null){
					ta.append(msg+"\n");
					ta.setCaretPosition(ta.getDocument().getLength());
					split = msg.split(" ");
					if(split[split.length-1].equals("/room")){	//대기실에서 보낸 메시지
						if(split[0].equals("/createRoom")){		//새로운 방 생성
							createNewRoom(split.clone(), msg);				
						}else if(split[0].equals("/createSecretRoom")){
							createSecretRoom(split.clone(),msg);
						}else if(split[0].equals("/w")){
							pw.println("[귓속말]("+split[1]+"에게 보냄) "+id+" : "+msg.substring(msg.indexOf(split[2])));
							map.get(split[1]).println("[귓속말] "+id+" : "+msg.substring(msg.indexOf(split[2])));
						}else if(split[0].equals("/chgId")){	//아이디를 변경한 경우
							//사람들에게 알리기
							//방장인 경우 방장 이름도 변경
							broadcast(msg);
							map.put(split[2], map.get(split[1]));
							map.remove(split[1]);
							id = split[2];
							for(Map.Entry<String, Room> entry : chatServer.getRoomMap().entrySet()){
								if(entry.getValue().getBanned().contains(split[1])){
									entry.getValue().getBanned().remove(split[1]);
									entry.getValue().getBanned().add(split[2]);
								}
								if(entry.getValue().getOwner().equals(split[1])){
									entry.getValue().setOwner(split[2]);
									broadcastRoom("/rchgOwner "+split[2]+" /room "+entry.getKey(), entry.getKey());
								}
								if(entry.getValue().getParticipants().containsKey(split[1])){
									entry.getValue().getParticipants().put(split[2], entry.getValue().getParticipants().get(split[1]));
									entry.getValue().getParticipants().remove(split[1]);
								}
							}
						}else{
							broadcast(id+" : "+msg.substring(2));	//대기실에서 메시지 보내기
							ta.append(id+" : "+msg.substring(2)+"\n");
						}
					}else if(split[0].equals("/ban")){
						if(chatServer.getRoomMap().get(split[3]).getOwner().equals(split[1])){
							pw.println("방장은 본인을 강퇴시킬 수 없습니다. /room "+split[3]);
						}else{	//방장이 방장을 강퇴시킬수 없게 설정
							if(chatServer.getRoomMap().get(split[3]).getOwner().equals(id)){
								for(Map.Entry<String, PrintWriter> entry : chatServer.getRoomMap().get(split[3]).getParticipants().entrySet()){
									if(entry.getKey().equals(split[1])){
										entry.getValue().println("/banned "+entry.getKey()+" /room "+split[3]);
									}else{
										entry.getValue().println(msg);
									}
								}
								chatServer.getRoomMap().get(split[3]).getParticipants().remove(split[1]);
								chatServer.getRoomMap().get(split[3]).getBanned().add(split[1]);
							}else{
								pw.println("방장만 강퇴 기능을 사용하실 수 있습니다. /room "+split[3]);
							}
							
						}
					}else if(split[0].equals("/exitRoom")){		//대화방에서 나갈때
						if(chatServer.getRoomMap().get(split[1]).getOwner().equals(id)&&chatServer.getRoomMap().get(split[1]).getParticipants().size()!=1){	//방장이 나갈때
							//방장 권한 넘기기
							pw.println("/routOwner "+id+" /room "+split[1]);
						}else if(chatServer.getRoomMap().get(split[1]).getParticipants().size()!=1){	//일반 멤버가 나갈때
							chatServer.getRoomMap().get(split[1]).removeMem(id);
							broadcastRoom("/rout "+id+" /room "+split[1],split[1]);
							pw.println("/routOk "+split[1]);	//진행 순서문제로 필요한 부분
						}else{	//방장 혼자 남은 방에서 나갈때
							chatServer.getRoomMap().remove(split[1]);
							broadcast("/removeRoom "+split[1]);
							pw.println("/routOk "+split[1]);	//진행 순서문제로 필요한 부분
						}
					}else if(split[0].equals("/beforeEnterRoom")){	//대화방에 들어가기 전에 대화방에 대한 정보를 볼 수 있도록 해당 정보를 사용자에게 제공
						if(chatServer.getRoomMap().get(split[1]).getBanned().contains(id)){	//해당 사용자가 강퇴 리스트에 있는 경우 거절
							pw.println("/roomBanned");
						}else{
							String participants = "";
							for(Map.Entry<String, PrintWriter> entry : chatServer.getRoomMap().get(split[1]).getParticipants().entrySet()){
								participants += entry.getKey()+" ";
							}
							pw.println("/roomDetail "+split[1]	//방번호
									+" "+(chatServer.getRoomMap().get(split[1]).isSecretRoom()?"YES":"NO")	//비밀방인지
									+" "+chatServer.getRoomMap().get(split[1]).getOwner()					//방장 아이디
									+" "+participants);														//참여자 아이디
						}
					}else if(split[0].equals("/enterRoom")){	//대화방 정보에서 대화방 입장하기를 선택한 경우
						if(chatServer.getRoomMap().get(split[1]).getParticipants().containsKey(id)){	//초대된 방에 이미 입장해있는 경우(초대를 여러명이 여러번한 경우 들어갔는데 또 들어갈 수도 있어서)
							pw.println("/inviteNo "+chatServer.getRoomMap().get(split[1]).getName()+" /room "+split[1]);
						}else{
							pw.println("/enterOk "+chatServer.getRoomMap().get(split[1]).getName()+" /room "+split[1]);
							broadcastRoom("/rin "+id+" /room "+split[1],split[1]);
						}
					}else if(split[0].equals("/enterSecretRoom")){	//비밀방에 입장하기전 비밀 번호 확인 절차
						if(split[2].equals(chatServer.getRoomMap().get(split[1]).getPassword())){
							pw.println("/correctPassword "+split[1]);
							pw.println("/enterOk "+chatServer.getRoomMap().get(split[1]).getName()+" /room "+split[1]);
							broadcastRoom("/rin "+id+" /room "+split[1],split[1]);
							
						}else{										//비밀방 비밀번호를 틀린 경우
							pw.println("/wrongPassword "+split[1]);
						}
					}else if(split[0].equals("/enterRoomEnd")){		//대화방 입장시 대화방 프레임이 생성되고 나서 정보를 보내주기 위해서 필요한 절차
						informRoomUser(split[1]);
						informRoomOwner(split[1]);
						chatServer.getRoomMap().get(split[1]).addMem(id, pw);
					}else if(split[0].equals("/newOwner")){
						chatServer.getRoomMap().get(split[split.length-1]).setOwner(split[1]);
						chatServer.getRoomMap().get(split[split.length-1]).removeMem(id);
						broadcastRoom("/rchgOwner "+split[1]+" /room "+split[3],split[3]);
						broadcastRoom("/rout "+id+" /room "+split[3],split[3]);
					}else if(split[0].equals("/ca")){
						
						if(chatServer.getRoomMap().get(split[3]).getOwner().equals(id)){
							chatServer.getRoomMap().get(split[3]).setOwner(split[1]);
							broadcastRoom("/rchgOwner "+split[1]+" /room "+split[3],split[3]);
						}else{
							pw.println("방장만 방장 변경 기능을 사용하실 수 있습니다. /room "+split[3]);
						}
					}else if(split[0].equals("/invite")){	// 초대
						if(chatServer.getRoomMap().get(split[3]).getParticipants().containsKey(split[1])){	//이미 방에 있는 사람을 초대한 경우
							pw.println("이미 방에 입장하신 유저는 초대할 수 없습니다. /room "+split[3]);
						}else{
							if(chatServer.getRoomMap().get(split[3]).getBanned().contains(split[1])){
								if(chatServer.getRoomMap().get(split[3]).getOwner().equals(id)){//방장만 강퇴된 사람 초대 가능
									
									String participants = "";
									for(Map.Entry<String, PrintWriter> entry : chatServer.getRoomMap().get(split[3]).getParticipants().entrySet()){
										participants += entry.getKey()+" ";
									}
									map.get(split[1]).println("/inviteRoomDetail "+split[3]	//방번호
											+" "+id		//누가 초대했는지
											+" "+chatServer.getRoomMap().get(split[3]).getOwner()	//방장 아이디
											+" "+participants);										//참여자 아이디
								}else{
									pw.println("강퇴된 유저는 방장만 초대하실 수 있습니다. /room "+split[3]);
								}
							}else{															//강퇴된 사람이 아닌 경우 그냥 초대
								String participants = "";
								for(Map.Entry<String, PrintWriter> entry : chatServer.getRoomMap().get(split[3]).getParticipants().entrySet()){
									participants += entry.getKey()+" ";
								}
								map.get(split[1]).println("/inviteRoomDetail "+split[3]
										+" "+id		//누가 초대했는지
										+" "+chatServer.getRoomMap().get(split[3]).getOwner()
										+" "+participants);
							}	
						}
						
					}else if(split[0].equals("/rejectInvite")){		//초대를 거절한 경우 초대한 사람에게 거절했음을 전달
						map.get(split[1]).println(id+"님께서 초대를 거절하셨습니다. /room "+split[3]);
					}else if(split[0].equals("/blockWhisper")){
						if(split.length==2){
							map.get(split[1]).println(id+"님께서 귓속말을 차단했습니다. /room");
						}else{
							map.get(split[1]).println(id+"님께서 귓속말을 차단했습니다. /room "+split[3]);
						}
					}else if(split[0].equals("/sendFile")){
						if(split[1].equals("/all")){
							for(Map.Entry<String, PrintWriter> entry : chatServer.getRoomMap().get(split[4]).getParticipants().entrySet()){
								if(!entry.getKey().equals(id)){
									entry.getValue().println("/sendFile "+entry.getKey()+" "+split[2]+" "+id+" /room "+split[split.length-1]);
								}
							}
							
						}else{
							map.get(split[1]).println("/sendFile "+split[1]+" "+split[2]+" "+id+" /room "+split[split.length-1]);
						}
					}else if(split[0].equals("/receiveFile")){
						FileReceiveServer fileReceiveServer = new FileReceiveServer();
						new FileSendServer(fileReceiveServer).start();
						fileReceiveServer.start();
						map.get(split[3]).println(msg);
						map.get(split[1]).println(msg);
					}else if(split[0].equals("/chgRoomName")){
						chatServer.getRoomMap().get(split[split.length-1]).setName(split[2]);
						broadcast(msg);
					}else if(split[0].equals("/cancelSecretRoom")){
						chatServer.getRoomMap().get(split[split.length-1]).setPassword(null);
						chatServer.getRoomMap().get(split[split.length-1]).setSecretRoom(false);
						broadcast(msg);
					}else if(split[0].equals("/chgRoomPassWord")){
						if(chatServer.getRoomMap().get(split[split.length-1]).isSecretRoom()){
							chatServer.getRoomMap().get(split[split.length-1]).setPassword(split[1]);
						}else{
							chatServer.getRoomMap().get(split[split.length-1]).setSecretRoom(true);
							chatServer.getRoomMap().get(split[split.length-1]).setPassword(split[1]);
							broadcast("/setSecretRoom "+split[split.length-1]);
						}
					}else if(split[split.length-2].equals("/room")){	
						if(chatServer.getRoomMap().get(split[split.length-1]).getOwner().equals(id)){	//방장이 귓속말한 경우
							if(split[0].equals("/w")){
								if(chatServer.getRoomMap().get(split[split.length-1]).getParticipants().containsKey(split[1])){//방멤버가 아닌 사람에게 귓속말을 했을 경우에는 대기실에 해당내용을 보냄
									map.get(split[1]).println("[귓속말] "+id+" (방장) : "+msg.substring(msg.indexOf(split[2])));
									pw.println("[귓속말]("+split[1]+"에게 보냄) "+id+" (방장) : "+msg.substring(msg.indexOf(split[2])));
								}else{	
									map.get(split[1]).println("[귓속말] "+id+" : "+msg.substring(msg.indexOf(split[2]))+" /room");
									pw.println("[귓속말]("+split[1]+"에게 보냄) "+id+" : "+msg.substring(msg.indexOf(split[2])));
								}
							}else{
								broadcastRoom(id+" (방장) : "+msg.substring(2),split[split.length-1]);
							}
						}else{	//방장이 아닌 사람이 귓속말 보낸 경우
							if(split[0].equals("/w")){
								if(chatServer.getRoomMap().get(split[split.length-1]).getParticipants().containsKey(split[1])){
									map.get(split[1]).println("[귓속말] "+id+" : "+msg.substring(msg.indexOf(split[2])));
									pw.println("[귓속말]("+split[1]+"에게 보냄) "+id+" : "+msg.substring(msg.indexOf(split[2])));
								}else{
									map.get(split[1]).println("[귓속말] "+id+" : "+msg.substring(msg.indexOf(split[2]))+" /room");
									pw.println("[귓속말]("+split[1]+"에게 보냄) "+id+" : "+msg.substring(msg.indexOf(split[2])));
								}
							}else{
								broadcastRoom(id+" : "+msg.substring(2),split[split.length-1]);
							}
						}
					}
				}
			} catch (IOException e) {
				Vector<String> removeRoomNum = new Vector<>();
				//갑자기 나가버릴때 방에서도 나가야됨
				for(Map.Entry<String, Room> entry : chatServer.getRoomMap().entrySet()){
					if(entry.getValue().getParticipants().get(id)!=null){
						if(entry.getValue().getOwner().equals(id)&&entry.getValue().getParticipants().size()!=1){	//방장이 나갈때
							//방장 권한 넘기기
							for(Map.Entry<String, PrintWriter> entry2 : entry.getValue().getParticipants().entrySet()){
								if(!entry2.getKey().equals(id)){
									entry.getValue().setOwner(entry2.getKey());
									break;
								}
							}
							broadcastRoom("/rchgOwner "+entry.getValue().getOwner()+" /room "+entry.getKey(),entry.getKey());
							broadcastRoom("/rout "+id+" /room "+entry.getKey(),entry.getKey());
						}else if(entry.getValue().getParticipants().size()!=1){	//일반 멤버가 나갈때
							broadcastRoom("/rout "+id+" /room "+entry.getKey(),entry.getKey());
						}else{	//방장 혼자 남은 방에서 나갈때
							removeRoomNum.add(entry.getKey());
							broadcast("/removeRoom "+entry.getKey());
						}
						entry.getValue().getParticipants().remove(id);
					}

				}
				for (int i = 0; i < removeRoomNum.size(); i++) {
					chatServer.getRoomMap().remove(removeRoomNum.get(i));
				}
				
				
				map.remove(id);
				synchronized (map) {
					ta.append(id+"님이 나가셨습니다."+"\n");
					ta.setCaretPosition(ta.getDocument().getLength());
					for(Map.Entry<String, PrintWriter> entry:map.entrySet()){
						entry.getValue().println("/uout "+id);
						entry.getValue().println(id+"님이 나가셨습니다. /room");
					}
				}
				for (int i = 0; i < modelAll.getRowCount(); i++) {
					if(modelAll.getValueAt(i, 0).equals(id)){
						modelAll.removeRow(i);
						break;
					}
				}
				
			}
	}
	public void broadcastRoom(String msg,String roomNum){
		for(Map.Entry<String, PrintWriter> entry:chatServer.getRoomMap().get(roomNum).getParticipants().entrySet()){
			entry.getValue().println(msg);
		}
	}
	public void informRoomUser(String roomNum){
		for(Map.Entry<String, PrintWriter> entry:chatServer.getRoomMap().get(roomNum).getParticipants().entrySet()){
			pw.println("/roomuser "+entry.getKey()+" /room "+roomNum);
		}
		
	}
	public void informRoomOwner(String roomNum){
		pw.println("/roomuserO "+chatServer.getRoomMap().get(roomNum).getOwner()+" /room "+roomNum);
	}
	public void broadcast(String msg){
		synchronized (map) {
			for(Map.Entry<String, PrintWriter> entry: map.entrySet()){
				entry.getValue().println(msg);
			}
		}
	}
	//새로운 방 생성
	public void createNewRoom(String[] split,String msg){
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		String roomNum = chatServer.getRoomNum()+format1.format(Calendar.getInstance().getTime());	//대화방 번호 생성
		pw.println("/roomNum "+roomNum);	//방장(방 생성자)에게 방번호 전달
		synchronized (map) {
			for(Map.Entry<String, PrintWriter> entry:map.entrySet()){
				entry.getValue().println("/newRoom "+msg.substring(split[0].length()+1,msg.lastIndexOf(" /room"))+" "+roomNum);	//새로운 방이 생성되었음을 다른 사람들에게 알림
			}
		}
		chatServer.createNewRoom(roomNum,id,msg.substring(split[0].length()+1,msg.lastIndexOf(" /room")));
	}
	public void createSecretRoom(String[] split,String msg){
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		String roomNum = chatServer.getRoomNum()+format1.format(Calendar.getInstance().getTime());
		pw.println("/roomNum "+roomNum);
		synchronized (map) {
			for(Map.Entry<String, PrintWriter> entry:map.entrySet()){
				entry.getValue().println("/newSecretRoom "+msg.substring(split[0].length()+1,msg.lastIndexOf(split[split.length-2])-1)+" "+roomNum);
			}
		}
		chatServer.createNewSecretRoom(roomNum,id,msg.substring(split[0].length()+1,msg.lastIndexOf(split[split.length-2])-1),split[split.length-2]);
	}
}
