package client;

import java.awt.KeyboardFocusManager;
import java.awt.TrayIcon.MessageType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class ChatClientThread extends Thread{
	Socket socket;
	JTextArea ta;
	DefaultTableModel model;
	BufferedReader br;
	PrintWriter pw;
	ChatClient chatClient;
	boolean isRunning = true;
	public ChatClientThread(String id,Socket socket, JTextArea ta,DefaultTableModel model,ChatClient chatClientPrac, PrintWriter pw, BufferedReader br) {
		this.socket = socket;
		this.ta = ta;
		this.model = model;
		this.chatClient =chatClientPrac;
		this.br = br;
		this.pw = pw;
	}
	@Override
	public void run() {
		String msg;
		String[] split = null;
			
		
		try {
			while ((msg = br.readLine())!=null) {
				split = msg.split(" ");
				if(split[split.length-1].equals("/server")){	//서버에서 온 메시지는 모든 방에 뿌리기
					ta.append(msg.substring(0,msg.length()-8)+"\n");
					ta.setCaretPosition(ta.getDocument().getLength());				
					for(Map.Entry<String, NewRoomFrame> entry : chatClient.getRoomInfoList().entrySet()){
						entry.getValue().getTa().append(msg.substring(0,msg.length()-8)+"\n");
						entry.getValue().getTa().setCaretPosition(entry.getValue().getTa().getDocument().getLength());
					}
					if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
						chatClient.getTrayIcon().displayMessage("Server", msg.substring(0,msg.length()-8), MessageType.NONE);
					}
				}else if(split[0].equals("/roomNum")){	//새로운 방을 생성하면 서버로부터 방번호를 받아옴
					chatClient.getNewRoomFrame().setTitle("채팅방 : "+chatClient.getNewRoomFrame().getRoomName()+" ( 채팅명 : "+chatClient.getId()+" ) - "+split[1]);
					chatClient.getRoomInfoList().put(split[1], chatClient.getNewRoomFrame());
					chatClient.getRoomInfoList().get(split[1]).setRoomNum(split[1]);
					for (int i = 0; i < chatClient.getModelAll().getRowCount(); i++) {
						chatClient.getRoomInfoList().get(split[1]).getModelAll().addRow(new String[]{chatClient.getModelAll().getValueAt(i, 0).toString()});
					}
					chatClient.modelRoomDataChanged();
				}else if(split[0].equals("/pwCreateNewRoom")){
					chatClient.pwCreateNewRoom();
					
				}else if(split[0].equals("/newRoom")){	//새로운 방이 생기면 대기실에 방 목록 변경
					chatClient.getModelRoom().addRow(new String[]{split[split.length-1],msg.substring(split[0].length()+1,msg.lastIndexOf(split[split.length-1])-1),"NO"});
				}else if(split[0].equals("/newSecretRoom")){	//새로운 방이 생기면 대기실에 방 목록 변경
					chatClient.getModelRoom().addRow(new String[]{split[split.length-1],msg.substring(split[0].length()+1,msg.lastIndexOf(split[split.length-1])-1),"YES"});
				}else if(split[0].equals("/removeRoom")){	//방이 사라지면 대기실 방 목록 변경
					for (int i = 0; i < chatClient.getModelRoom().getRowCount(); i++) {
						if(chatClient.getModelRoom().getValueAt(i, 0).equals(split[1])){
							chatClient.getModelRoom().removeRow(i);
							chatClient.removeBannedRoomList(split[1]);
							break;
						}
					}
				}else if(split[0].equals("/uin")){	//새로운 유저 로그인
					chatClient.getModelAll().addRow(new String[]{split[1]});
					for (Map.Entry<String, NewRoomFrame> entry: chatClient.getRoomInfoList().entrySet()) {
						entry.getValue().getModelAll().addRow(new String[]{split[1]});
					}

				}else if(split[0].equals("/uout")){	//다른 유저 로그아웃 시
					for (int i = 0; i < chatClient.getModelAll().getRowCount(); i++) {
						if(chatClient.getModelAll().getValueAt(i, 0).equals(split[1])){
							chatClient.getModelAll().removeRow(i);
							break;
						}
					}
					for (Map.Entry<String, NewRoomFrame> entry: chatClient.getRoomInfoList().entrySet()) {
						for (int i = 0; i < entry.getValue().getModelAll().getRowCount(); i++) {
							if(entry.getValue().getModelAll().getValueAt(i, 0).equals(split[1])){
								entry.getValue().getModelAll().removeRow(i);
							}
						}
					}
					if(chatClient.getBlockNameList().contains(split[1])){	//귓속말 차단한 아이디를 차단리스트에서 제외
						chatClient.getBlockNameList().remove(split[1]);
					}
				}else if(split[0].equals("/user")){	//로그인하면 기존 유저 리스트 저장
					chatClient.getModelAll().addRow(new String[]{split[1]});
				}else if(split[0].equals("/roomlist")){	//로그인하면 기존 대화방 리스트를 제공
					chatClient.getModelRoom().addRow(new String[]{split[1],msg.substring(msg.indexOf(split[1])+split[1].length()+1,msg.lastIndexOf(split[split.length-1])-1),split[split.length-1]});
				}else if(split[0].equals("/roomDetail")){
					chatClient.createBeforeEnterRoomDetail(split.clone());	//방에 입장하기 전에 방정보를 보여줌
				}else if(split[0].equals("/correctPassword")){				//비밀 대화방의 비밀번호가 일치하는 경우
					chatClient.correctPassword();
				}else if(split[0].equals("/inviteRoomDetail")){
					chatClient.createBeforeInviteRoomDetail(split.clone());
				}else if(split[0].equals("/inviteNo")){						//이미 초대에 응해서 입장을 한 경우 여러번 초대를 해서 초대메시지에 수락한 경우
					chatClient.inviteNo(split.clone(),msg);
				}else if(split[0].equals("/enterOk")){
					chatClient.enterRoom(msg.substring(split[0].length()+1,msg.lastIndexOf(split[split.length-2])-1),split[split.length-1]);
				}else if(split[0].equals("/wrongPassword")){				//비밀 대화방의 비밀번호를 틀린 경우
					chatClient.wrongPassword();
				}else if(split[0].equals("/roomuser")){	//대화방 입장 시 기존 유저 리스트 저장
					chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().addRow(new String[]{split[1]});
				}else if(split[0].equals("/roomuserO")){//대화방 입장 시 방장 정보를 저장
					chatClient.getRoomInfoList().get(split[split.length-1]).getModelOwner().addRow(new String[]{split[1]});
				}else if(split[0].equals("/rin")){	//대화방에 새로운 유저 입장 //해당 대화방에 포커스가 없을 때만 알림
					chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().addRow(new String[]{split[1]});
					chatClient.getRoomInfoList().get(split[split.length-1]).getTa().append(split[1]+"님이 입장하셨습니다.\n");
					chatClient.getRoomInfoList().get(split[split.length-1]).getTa().setCaretPosition(chatClient.getRoomInfoList().get(split[split.length-1]).getTa().getDocument().getLength());
					if(chatClient.roomInfoList.get(split[split.length-1]).getRoomAlarm()){
						if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
							chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(), split[1]+"님이 입장하셨습니다.", MessageType.NONE);
						}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
							boolean focused = false;
							for (Map.Entry<String, NewRoomFrame> entry:chatClient.getRoomInfoList().entrySet()) {
								if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
									focused = true;
									break;
								}
							}
							if(!focused){
								chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(), split[1]+"님이 입장하셨습니다.", MessageType.NONE);
							}
						}
					}
				}else if(split[0].equals("/rout")){	//대화방에 기존 유저 퇴장
					for (int i = 0; i < chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().getRowCount(); i++) {
						if(chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().getValueAt(i, 0).equals(split[1])){
							chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().removeRow(i);
							break;
						}
					}
					chatClient.getRoomInfoList().get(split[split.length-1]).getTa().append(split[1]+"님으로 퇴장하셨습니다.\n");
					chatClient.getRoomInfoList().get(split[split.length-1]).getTa().setCaretPosition(chatClient.getRoomInfoList().get(split[split.length-1]).getTa().getDocument().getLength());
					if(chatClient.roomInfoList.get(split[split.length-1]).getRoomAlarm()){
						if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
							chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(), split[1]+"님이 퇴장하셨습니다.", MessageType.NONE);
						}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
							boolean focused = false;
							for (Map.Entry<String, NewRoomFrame> entry:chatClient.getRoomInfoList().entrySet()) {
								if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
									focused = true;
									break;
								}
							}
							if(!focused){
								chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(), split[1]+"님이 퇴장하셨습니다.", MessageType.NONE);
							}
						}
						
					}
				}else if(split[0].equals("/routOk")){	//방에서 나가면 들어간 대화방 리스트에서 제거
					chatClient.getRoomInfoList().remove(split[1]);
					chatClient.modelRoomDataChanged();
				}else if(split[0].equals("/rchgOwner")){	//대화방 방장이름 변경되었음을 대화방 사용자에게 알림
					chatClient.getRoomInfoList().get(split[split.length-1]).getTa().append("방장이 "+split[1]+"님으로 변경되었습니다.\n");
					chatClient.getRoomInfoList().get(split[split.length-1]).getTa().setCaretPosition(chatClient.getRoomInfoList().get(split[split.length-1]).getTa().getDocument().getLength());
					chatClient.getRoomInfoList().get(split[split.length-1]).getModelOwner().removeRow(0);
					chatClient.getRoomInfoList().get(split[split.length-1]).getModelOwner().addRow(new String[]{split[1]});
				}else if(split[0].equals("/routOwner")){	//방장이 나갈때 방장이 새로운 방장을 선정해 주고 나감	//들어온지 가장 오래된 멤버가 방장이 됨
					//새로운 방장 선정
					pw.println("/newOwner "+chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().getValueAt(0, 0)+" /room "+split[split.length-1]);
					chatClient.getRoomInfoList().remove(split[split.length-1]);	
					chatClient.modelRoomDataChanged();
				}else if(split[0].equals("/banned")){		//강퇴된 사람에게 강퇴되었음을 알림
					chatClient.getRoomInfoList().get(split[3]).dispose();	//강퇴된 채팅방 닫기
					chatClient.getRoomInfoList().remove(split[3]);			
					chatClient.addBannedRoomList(split[3]);					//강퇴된 방 리스트에 저장
					chatClient.modelRoomDataChanged();				
					ta.append(split[3]+"번 채팅방에서 강퇴되셨습니다.\n");				
					ta.setCaretPosition(ta.getDocument().getLength());				
					if(chatClient.isMsgAlarm()){
						if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null||KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
							chatClient.getTrayIcon().displayMessage("대기실", split[3]+"번 채팅방에서 강퇴되셨습니다.", MessageType.NONE);
						}
					}
				}else if(split[0].equals("/ban")){	//강퇴된 사람이 아닌 사용자에게는 강퇴된 사람을 알림		
					for (int i = 0; i < chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().getRowCount(); i++) {//해당하는 사람을 제거
						if(chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().getValueAt(i, 0).equals(split[1])){
							chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().removeRow(i);
							break;
						}
					}
					chatClient.roomInfoList.get(split[split.length-1]).getTa().append(split[1]+"님이 채팅방에서 강퇴되셨습니다.\n");	
					chatClient.getRoomInfoList().get(split[split.length-1]).getTa().setCaretPosition(chatClient.getRoomInfoList().get(split[split.length-1]).getTa().getDocument().getLength());
					if(chatClient.roomInfoList.get(split[split.length-1]).getRoomAlarm()){
						if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
							chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(), split[1]+"님이 강퇴되셨습니다.", MessageType.NONE);
						}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
							boolean focused = false;
							for (Map.Entry<String, NewRoomFrame> entry:chatClient.getRoomInfoList().entrySet()) {
								if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
									focused = true;
									break;
								}
							}
							if(!focused){
								chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(), split[1]+"님이 강퇴되셨습니다.", MessageType.NONE);
							}
						}
					}
				}else if(split[0].equals("/roomBanned")){	//강퇴된 방에 입장을 시도하는 경우 강퇴된 방임을 알림
					chatClient.showAlreadyBannedRoom();
				}else if(split[0].equals("/invite")){
					chatClient.showInviteAccept(split);
				}else if(split[0].equals("/pwchgId")){
					chatClient.pwchgId();
				}else if(split[0].equals("/chgId")){
					//다른 사람 아이디 변경시
					//blocklist 변경
					//아이디 리스트 전부 변경
					//방장 변경은 따로 처리되었음
					if(chatClient.getBlockNameList().contains(split[1])){
						chatClient.getBlockNameList().add(split[2]);
						chatClient.getBlockNameList().remove(split[1]);
					}
					boolean MyId = true;
					for (int i = 0; i < chatClient.getModelAll().getRowCount(); i++) {
						if(chatClient.getModelAll().getValueAt(i, 0).equals(split[1])){
							chatClient.getModelAll().setValueAt(split[2], i, 0);
							ta.append(split[1]+"님의 아이디가 "+split[2]+"로 변경되었습니다.\n");
							ta.setCaretPosition(ta.getDocument().getLength());
							MyId = false;
							break;
						}
					}
					for(Map.Entry<String, NewRoomFrame> entry : chatClient.getRoomInfoList().entrySet()){	//대화방 내에 리스트 아이디 변경
						for (int i = 0; i < entry.getValue().getModelAll().getRowCount(); i++) {
							if(entry.getValue().getModelAll().getValueAt(i, 0).equals(split[1])){
								entry.getValue().getModelAll().setValueAt(split[2], i, 0);
								entry.getValue().getTa().append(split[1]+"님의 아이디가 "+split[2]+"로 변경되었습니다.\n");
								entry.getValue().getTa().setCaretPosition(entry.getValue().getTa().getDocument().getLength());
								break;
							}
						}
						for (int i = 0; i < entry.getValue().getModelRoom().getRowCount(); i++) {
							if(entry.getValue().getModelRoom().getValueAt(i, 0).equals(split[1])){
								entry.getValue().getModelRoom().setValueAt(split[2], i, 0);
								if(entry.getValue().getRoomAlarm()){
									if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null||!(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame)){
										chatClient.getTrayIcon().displayMessage("아이디 변경",split[1]+"님의 아이디가 "+split[2]+"로 변경되었습니다.", MessageType.NONE);
									}
								}
								break;
							}
						}
					}
					if(MyId){
						for(Map.Entry<String, NewRoomFrame> entry : chatClient.getRoomInfoList().entrySet()){	//본인이 들어가 있는 대화방에 타이틀에 표시되는 아이디 변경 
							entry.getValue().setTitle(entry.getValue().getTitle().substring(0,entry.getValue().getTitle().lastIndexOf(chatClient.getId()+" ) - "))+split[2]+" ) - "+entry.getKey());
						}
						chatClient.setId(split[2]);
						chatClient.setTitle(split[2]+"님의 채팅창");	//대기실 타이틀에 아이디 표시 변경
						chatClient.chgIdOk();
					}
				}else if(split[0].equals("/sendFile")){
					chatClient.receiveFileChk(split.clone());	//파일을 전송받을 것인지 확인
				}else if(split[0].equals("/receiveFile")){	//파일 전송 시작
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(chatClient.getId().equals(split[1])){
						new FileReceiveClient(chatClient, split.clone()).start();
					}else{
						new FileSendClient(chatClient, split.clone()).start();
					}
					
				}else if(split[0].equals("/pwChgRoomName")){
					chatClient.pwChgRoomName(split[split.length-1]);
				}else if(split[0].equals("/chgRoomNameOK")){
					chatClient.chgRoomNameOK(split[split.length-1]);
				}else if(split[0].equals("/chgRoomName")){		//대화방 이름을 변경한 경우 사용자들에게 대화방이름이 변경되었음을 알림
					for (int i = 0; i < chatClient.getTableRoom().getRowCount(); i++) {
						if(chatClient.getTableRoom().getValueAt(i, 0).equals(split[split.length-1])){
							chatClient.getTableRoom().setValueAt(msg.substring(split[0].length()+1,msg.lastIndexOf(" /room")), i, 1);
							break;
						}
					}
					if(chatClient.getRoomInfoList().containsKey(split[split.length-1])){	//대화방이름이 변경된 대화방에 들어가 있는 경우 타이틀을 변경
						String title = chatClient.getRoomInfoList().get(split[split.length-1]).getTitle(); 
						chatClient.getRoomInfoList().get(split[split.length-1]).setTitle(
								title.substring(0,title.indexOf(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName()))
								+msg.substring(split[0].length()+1,msg.lastIndexOf(" /room"))+title.substring(title.indexOf(" ( 채팅명 : ")));
						chatClient.getRoomInfoList().get(split[split.length-1]).getTa().append("대화방의 이름이 "+chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName()
								+"에서 "+msg.substring(split[0].length()+1,msg.lastIndexOf(" /room"))+"으로 변경되었습니다.\n");
						chatClient.getRoomInfoList().get(split[split.length-1]).getTa().setCaretPosition(chatClient.getRoomInfoList().get(split[split.length-1]).getTa().getDocument().getLength());
						chatClient.getRoomInfoList().get(split[split.length-1]).setRoomName(msg.substring(split[0].length()+1,msg.lastIndexOf(" /room")));
					}
				}else if(split[0].equals("/cancelSecretRoomOk")){
					chatClient.cancelSecretRoomOk(split[split.length-1]);
				}else if(split[0].equals("/cancelSecretRoom")){		//대화방 설정에서 원래 비밀방 이었는데 비밀방 설정을 취소하는 경우
					for (int i = 0; i < chatClient.getTableRoom().getRowCount(); i++) {
						if(chatClient.getTableRoom().getValueAt(i, 0).equals(split[split.length-1])){
							chatClient.getTableRoom().setValueAt("NO", i, 2);
							break;
						}
					}
				}else if(split[0].equals("/chgRoomPassWordOk")){
					chatClient.chgRoomPassWordOk(split[split.length-1]);
				}else if(split[0].equals("/setSecretRoom")){		//원래 비밀방이 아니었던 방을 비밀방으로 설정한 경우 대화방 리스트를 수정
					for (int i = 0; i < chatClient.getTableRoom().getRowCount(); i++) {
						if(chatClient.getTableRoom().getValueAt(i, 0).equals(split[split.length-1])){
							chatClient.getTableRoom().setValueAt("YES", i, 2);
							break;
						}
					}
				}else if(split[split.length-1].equals("/room")){	//대기실 채팅
					if(split[0].equals("[귓속말]")){//귓속말 차단 여부 확인
						boolean blocked = false;
						for (int i = 0; i < chatClient.getBlockNameList().size(); i++) {
							if(split[1].equals(chatClient.getBlockNameList().get(i))){
								blocked = true;
								break;
							}
						}
						if(!blocked){
							ta.append(msg.substring(0,msg.indexOf("/room")-1)+"\n");
							ta.setCaretPosition(ta.getDocument().getLength());
							if(chatClient.isMsgAlarm()){
								if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null||KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
									chatClient.getTrayIcon().displayMessage("대기실",msg.substring(0,msg.indexOf("/room")-1), MessageType.NONE);
								}
							}
						}else{	//귓속말 차단된 경우
							if(split[split.length-3].equals("/room")){	//대화방에서 대기실에 있는 사람에게 귓속말했는데 차단된 경우 대화방으로 다시 알림
								pw.println("/blockWhisper "+split[1]+" /room "+split[split.length-2]);
							}else{
								pw.println("/blockWhisper "+split[1]);
							}
						}
					}else{	//일반 대화
						if(split[0].equals(chatClient.getId())){	//내가 보낸 메시지
							ta.append(split[0]+"(나) "+msg.substring(msg.indexOf(split[1]),msg.length()-6)+"\n");
							ta.setCaretPosition(ta.getDocument().getLength());
						}else{
							ta.append(msg.substring(0,msg.length()-6)+"\n");
							ta.setCaretPosition(ta.getDocument().getLength());
						}
					}
				}else if(split[split.length-2].equals("/room")){	//방 채팅
					if(split[0].equals("[귓속말]")){
						boolean blocked = false;	//귓속말 차단 여부 확인
						for (int i = 0; i < chatClient.getBlockNameList().size(); i++) {
							if(split[1].equals(chatClient.getBlockNameList().get(i))){
								blocked = true;
								break;
							}
						}
						if(!blocked){
							chatClient.getRoomInfoList().get(split[split.length-1]).getTa().append(msg.substring(0,msg.indexOf("/room")-1)+"\n");
							chatClient.getRoomInfoList().get(split[split.length-1]).getTa().setCaretPosition(chatClient.getRoomInfoList().get(split[split.length-1]).getTa().getDocument().getLength());
							// 해당 방에 포커스가 없을 경우에만 알림
							if(chatClient.roomInfoList.get(split[split.length-1]).getRoomAlarm()){
								if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
									chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(),msg.substring(0,msg.indexOf("/room")-1) , MessageType.NONE);
								}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
									boolean focused = false;
									for (Map.Entry<String, NewRoomFrame> entry:chatClient.getRoomInfoList().entrySet()) {
										if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
											focused = true;
											break;
										}
									}
									if(!focused){
										chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(),msg.substring(0,msg.indexOf("/room")-1) , MessageType.NONE);
									}
								}
							}
						}else{
							pw.println("/blockWhisper "+split[1]+" /room "+split[split.length-1]);
						}
					}else{	//대화방 일반 대화
						if(split[0].equals(chatClient.getId())){	//내가 보낸 메시지인 경우에는 (나) 라고 붙임
							chatClient.getRoomInfoList().get(split[split.length-1]).getTa().append(split[0]+"(나) "+msg.substring(msg.indexOf(split[1]),msg.indexOf("/room")-1)+"\n");
							chatClient.getRoomInfoList().get(split[split.length-1]).getTa().setCaretPosition(chatClient.getRoomInfoList().get(split[split.length-1]).getTa().getDocument().getLength());
							// 해당 방에 포커스가 없을 경우에만 알림
							if(chatClient.roomInfoList.get(split[split.length-1]).getRoomAlarm()){
								if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
									chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(),split[0]+"(나) "+msg.substring(msg.indexOf(split[1]),msg.indexOf("/room")-1) , MessageType.NONE);
								}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
									boolean focused = false;
									for (Map.Entry<String, NewRoomFrame> entry:chatClient.getRoomInfoList().entrySet()) {
										if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
											focused = true;
											break;
										}
									}
									if(!focused){
										chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(),split[0]+"(나) "+msg.substring(msg.indexOf(split[1]),msg.indexOf("/room")-1) , MessageType.NONE);
									}
								}
							}
						}else{
							chatClient.getRoomInfoList().get(split[split.length-1]).getTa().append(msg.substring(0,msg.indexOf("/room")-1)+"\n");
							chatClient.getRoomInfoList().get(split[split.length-1]).getTa().setCaretPosition(chatClient.getRoomInfoList().get(split[split.length-1]).getTa().getDocument().getLength());
							// 해당 방에 포커스가 없을 경우에만 알림
							if(chatClient.roomInfoList.get(split[split.length-1]).getRoomAlarm()){
								if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
									chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(),msg.substring(0,msg.indexOf("/room")-1) , MessageType.NONE);
								}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
									boolean focused = false;
									for (Map.Entry<String, NewRoomFrame> entry:chatClient.getRoomInfoList().entrySet()) {
										if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
											focused = true;
											break;
										}
									}
									if(!focused){
										chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(),msg.substring(0,msg.indexOf("/room")-1) , MessageType.NONE);
									}
								}
							}
						}
					}
				}
			}
		} catch (SocketException e) {
			JOptionPane.showMessageDialog(null, "서버가 종료되었습니다.");
			System.exit(0);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(socket!=null){
					socket.close();
				}
				if(br!=null){
					br.close();
				}
				if(pw!=null){
					pw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(111);
		}
	}
}

