package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class ChatServerThread extends Thread{
	HashMap<String, PrintWriter> map;	//���� ������
	JTextArea ta;						//���� â
	DefaultTableModel modelAll;			//��� ������ ���̺� ��
	String id;							//����Ǿ� �ִ� ������� ���̵�
	
	BufferedReader br;
	PrintWriter pw;
	Socket clientSocket;				//���߿� ��������
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
			boolean prohibitWord = true;
			while(exist||prohibitWord){	//����ڰ� ����ϰ��� �ϴ� ���̵� �̹� ������� ���̵����� Ȯ��
				exist = false;
				prohibitWord = false;
				id = br.readLine();
				for (int i = 0; i < chatServer.modelAll.getRowCount(); i++) {
					if(chatServer.modelAll.getValueAt(i, 0).equals(id)){
						pw.println("/exist");	//�̹� ������� ���̵����� ����ڿ��� �˷���
						exist = true;
						break;
					}
				}
				for (Iterator iterator = chatServer.getProhibitWordList().iterator(); iterator.hasNext();) {
					if(id.indexOf((String)iterator.next())!=-1){
						pw.println("/prohibit");	//������ ���
						prohibitWord = true;
						break;
					}
				}
				
			}
			
			pw.println("/comein");
			ta.append(id+"���� �����ϼ̽��ϴ�."+"\n");
			ta.setCaretPosition(ta.getDocument().getLength());
			pw.println("ȯ���մϴ�. "+id+"�� /room");
			synchronized (map) {
				//map�� ���ϱ� ���� ���� ����鿡�� ������ �˸�
				for(Map.Entry<String, PrintWriter> entry: map.entrySet()){
					entry.getValue().println("/uin "+id);
					entry.getValue().println(id+"���� �����ϼ̽��ϴ�. /room");
				}
				//���� ������ ������� ���� ��� ����� �ѷ���
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
		}catch (SocketException e) {
			
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
					if(split[split.length-1].equals("/room")){	//���ǿ��� ���� �޽���
						if(split[0].equals("/createRoom")){		//���ο� �� ����
							boolean PW = false;
							for (Iterator iterator = chatServer.getProhibitWordList().iterator(); iterator.hasNext();) {
								if(msg.indexOf((String)iterator.next())!=-1){
									PW = true;
									break;
								}
							}
							
							if(PW){
								pw.println("/pwCreateNewRoom");
							}else{
								createNewRoom(split.clone(), msg);				
							}
						}else if(split[0].equals("/createSecretRoom")){
							boolean PW = false;
							for (Iterator iterator = chatServer.getProhibitWordList().iterator(); iterator.hasNext();) {
								if(msg.indexOf((String)iterator.next())!=-1){
									PW = true;
									break;
								}
							}
							
							if(PW){
								pw.println("/pwCreateNewRoom");
							}else{
								createSecretRoom(split.clone(),msg);
							}
						}else if(split[0].equals("/w")){
							boolean PW = false;
							for (Iterator iterator = chatServer.getProhibitWordList().iterator(); iterator.hasNext();) {
								if(msg.indexOf((String)iterator.next())!=-1){
									PW = true;
									break;
								}
							}
							if(PW){
								pw.println("�ٸ� ���� ����սô�!!! /room");
							}else{
								pw.println("[�ӼӸ�]("+split[1]+"���� ����) "+id+" : "+msg.substring(msg.indexOf(split[2])));
								map.get(split[1]).println("[�ӼӸ�] "+id+" : "+msg.substring(msg.indexOf(split[2])));
							}
						}else if(split[0].equals("/chgId")){	//���̵� ������ ���
							boolean PW = false;
							for (Iterator iterator = chatServer.getProhibitWordList().iterator(); iterator.hasNext();) {
								if(msg.indexOf((String)iterator.next())!=-1){
									PW = true;
									break;
								}
							}
							if(PW){
								pw.println("/pwchgId");
							}else{
								//����鿡�� �˸���
								//������ ��� ���� �̸��� ����
								broadcast(msg);	//���̵� ����� ��� ����ڿ��� �˸�
								map.put(split[2], map.get(split[1]));	//�������� �����ϴ� ��ü ����� �ʸ���Ʈ�� ���̵� ����
								map.remove(split[1]);					//����Ʈ�� ���ϰ� ����
								id = split[2];							
								for(Map.Entry<String, Room> entry : chatServer.getRoomMap().entrySet()){	
									if(entry.getValue().getBanned().contains(split[1])){		//��ȭ�� ���� ����Ʈ�� �ִ� ��� �̸� �ٲٱ�
										entry.getValue().getBanned().remove(split[1]);
										entry.getValue().getBanned().add(split[2]);
									}
									if(entry.getValue().getOwner().equals(split[1])){			//������ �̸��� ������ ��� �̸� �ٲٱ�
										entry.getValue().setOwner(split[2]);
										broadcastRoom("/rchgOwner "+split[2]+" /room "+entry.getKey(), entry.getKey());
									}
									if(entry.getValue().getParticipants().containsKey(split[1])){
										entry.getValue().getParticipants().put(split[2], entry.getValue().getParticipants().get(split[1]));
										entry.getValue().getParticipants().remove(split[1]);
									}
								}
								for (int i = 0; i < chatServer.getTableAll().getRowCount(); i++) {	//���� ����� ���̵� ���̺� ����
									if(chatServer.getTableAll().getValueAt(i, 0).equals(split[1])){
										chatServer.getTableAll().setValueAt(split[2], i, 0);
										break;
									}
								}
								ta.append(split[1]+"���� ���̵� "+split[2]+"�� ����Ǿ����ϴ�.\n");
								ta.setCaretPosition(ta.getDocument().getLength());
							}
						}else{
							boolean PW = false;
							for (Iterator iterator = chatServer.getProhibitWordList().iterator(); iterator.hasNext();) {
								if(msg.indexOf((String)iterator.next())!=-1){
									PW = true;
									break;
								}
							}
							if(PW){
								pw.println("�ٸ� ���� ����սô�!!! /room");
							}else{
								broadcast(id+" : "+msg.substring(2));	//���ǿ��� �޽��� ������
								ta.append(id+" : "+msg.substring(2)+"\n");
							}
						}
					}else if(split[0].equals("/close")){
						Vector<String> removeRoomNum = new Vector<>();
						//���ڱ� ���������� �濡���� �����ߵ�
						for(Map.Entry<String, Room> entry : chatServer.getRoomMap().entrySet()){
							if(entry.getValue().getParticipants().get(id)!=null){
								if(entry.getValue().getOwner().equals(id)&&entry.getValue().getParticipants().size()!=1){	//������ ������
									//���� ó���ϰ� �־ �ּ�ó��
//									//���� ���� �ѱ��
//									for(Map.Entry<String, PrintWriter> entry2 : entry.getValue().getParticipants().entrySet()){
//										if(!entry2.getKey().equals(id)){
//											entry.getValue().setOwner(entry2.getKey());
//											break;
//										}
//									}
//									broadcastRoom("/rchgOwner "+entry.getValue().getOwner()+" /room "+entry.getKey(),entry.getKey());
//									broadcastRoom("/rout "+id+" /room "+entry.getKey(),entry.getKey());
								}else if(entry.getValue().getParticipants().size()!=1){	//�Ϲ� ����� ������
									broadcastRoom("/rout "+id+" /room "+entry.getKey(),entry.getKey());
								}else{	//���� ȥ�� ���� �濡�� ������
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
							ta.append(id+"���� �����̽��ϴ�."+"\n");
							ta.setCaretPosition(ta.getDocument().getLength());
							for(Map.Entry<String, PrintWriter> entry:map.entrySet()){
								entry.getValue().println("/uout "+id);
								entry.getValue().println(id+"���� �����̽��ϴ�. /room");
							}
						}
						for (int i = 0; i < modelAll.getRowCount(); i++) {
							if(modelAll.getValueAt(i, 0).equals(id)){
								modelAll.removeRow(i);
								break;
							}
						}
						
					}else if(split[0].equals("/ban")){
						if(chatServer.getRoomMap().get(split[3]).getOwner().equals(split[1])){//������ ������ �����ų�� ���� ����
							pw.println("������ ������ �����ų �� �����ϴ�. /room "+split[3]);
						}else{	
							if(chatServer.getRoomMap().get(split[3]).getOwner().equals(id)){	//���常 ������ ��밡��
								for(Map.Entry<String, PrintWriter> entry : chatServer.getRoomMap().get(split[3]).getParticipants().entrySet()){
									if(entry.getKey().equals(split[1])){	//����� ������� ����Ǿ����� �˸�
										entry.getValue().println("/banned "+entry.getKey()+" /room "+split[3]);
									}else{									//����� ����� �ƴ� ����ڿ��Դ� ����� ����� �˸�
										entry.getValue().println(msg);
									}
								}
								chatServer.getRoomMap().get(split[3]).getParticipants().remove(split[1]);
								chatServer.getRoomMap().get(split[3]).getBanned().add(split[1]);
							}else{
								pw.println("���常 ���� ����� ����Ͻ� �� �ֽ��ϴ�. /room "+split[3]);
							}
							
						}
					}else if(split[0].equals("/exitRoom")){		//��ȭ�濡�� ������
						if(chatServer.getRoomMap().get(split[1]).getOwner().equals(id)&&chatServer.getRoomMap().get(split[1]).getParticipants().size()!=1){	//������ ������
							//���� ���� �ѱ��
							pw.println("/routOwner "+id+" /room "+split[1]);
						}else if(chatServer.getRoomMap().get(split[1]).getParticipants().size()!=1){	//�Ϲ� ����� ������
							chatServer.getRoomMap().get(split[1]).removeMem(id);
							broadcastRoom("/rout "+id+" /room "+split[1],split[1]);
							pw.println("/routOk "+split[1]);	//���� ���������� �ʿ��� �κ�
						}else{	//���� ȥ�� ���� �濡�� ������
							chatServer.getRoomMap().remove(split[1]);
							broadcast("/removeRoom "+split[1]);	//ȥ�� ���� �濡�� ������ ���� ������� ������
							pw.println("/routOk "+split[1]);	//���� ���������� �ʿ��� �κ�
						}
					}else if(split[0].equals("/beforeEnterRoom")){	//��ȭ�濡 ���� ���� ��ȭ�濡 ���� ������ �� �� �ֵ��� �ش� ������ ����ڿ��� ����
						if(chatServer.getRoomMap().get(split[1]).getBanned().contains(id)){	//�ش� ����ڰ� ���� ����Ʈ�� �ִ� ��� ����
							pw.println("/roomBanned");
						}else{
							String participants = "";
							for(Map.Entry<String, PrintWriter> entry : chatServer.getRoomMap().get(split[1]).getParticipants().entrySet()){
								participants += entry.getKey()+" ";
							}
							pw.println("/roomDetail "+split[1]	//���ȣ
									+" "+(chatServer.getRoomMap().get(split[1]).isSecretRoom()?"YES":"NO")	//��й�����
									+" "+chatServer.getRoomMap().get(split[1]).getOwner()					//���� ���̵�
									+" "+participants);														//������ ���̵�
						}
					}else if(split[0].equals("/enterRoom")){	//��ȭ�� �������� ��ȭ�� �����ϱ⸦ ������ ���
						if(chatServer.getRoomMap().get(split[1]).getParticipants().containsKey(id)){	//�ʴ�� �濡 �̹� �������ִ� ���(�ʴ븦 �������� �������� ��� ���µ� �� �� ���� �־)
							pw.println("/inviteNo "+chatServer.getRoomMap().get(split[1]).getName()+" /room "+split[1]);
						}else{
							pw.println("/enterOk "+chatServer.getRoomMap().get(split[1]).getName()+" /room "+split[1]);
							broadcastRoom("/rin "+id+" /room "+split[1],split[1]);
						}
					}else if(split[0].equals("/enterSecretRoom")){	//��й濡 �����ϱ��� ��� ��ȣ Ȯ�� ����
						if(split[2].equals(chatServer.getRoomMap().get(split[1]).getPassword())){
							pw.println("/correctPassword "+split[1]);
							pw.println("/enterOk "+chatServer.getRoomMap().get(split[1]).getName()+" /room "+split[1]);
							broadcastRoom("/rin "+id+" /room "+split[1],split[1]);
							
						}else{										//��й� ��й�ȣ�� Ʋ�� ���
							pw.println("/wrongPassword "+split[1]);
						}
					}else if(split[0].equals("/enterRoomEnd")){		//��ȭ�� ����� ��ȭ�� �������� �����ǰ� ���� ������ �����ֱ� ���ؼ� �ʿ��� ����
						informRoomUser(split[1]);
						informRoomOwner(split[1]);
						chatServer.getRoomMap().get(split[1]).addMem(id, pw);
					}else if(split[0].equals("/newOwner")){			//������ ��ȭ�濡�� ������ ���ο� ������ ������
						chatServer.getRoomMap().get(split[split.length-1]).setOwner(split[1]);
						chatServer.getRoomMap().get(split[split.length-1]).removeMem(id);
						broadcastRoom("/rchgOwner "+split[1]+" /room "+split[3],split[3]);	//��ȭ�� ����� ��ο��� ������ �ٲ����� �˸�
						broadcastRoom("/rout "+id+" /room "+split[3],split[3]);				//���� ������ ���� �������� �˸�
					}else if(split[0].equals("/ca")){				//������ ������ ������ ��										
						if(chatServer.getRoomMap().get(split[3]).getOwner().equals(id)){
							chatServer.getRoomMap().get(split[3]).setOwner(split[1]);
							broadcastRoom("/rchgOwner "+split[1]+" /room "+split[3],split[3]);
						}else{
							pw.println("���常 ���� ���� ����� ����Ͻ� �� �ֽ��ϴ�. /room "+split[3]);
						}
					}else if(split[0].equals("/invite")){	// �ʴ�
						if(chatServer.getRoomMap().get(split[3]).getParticipants().containsKey(split[1])){	//�̹� �濡 �ִ� ����� �ʴ��� ���
							pw.println("�̹� �濡 �����Ͻ� ������ �ʴ��� �� �����ϴ�. /room "+split[3]);
						}else{
							if(chatServer.getRoomMap().get(split[3]).getBanned().contains(split[1])){
								if(chatServer.getRoomMap().get(split[3]).getOwner().equals(id)){//���常 ����� ��� �ʴ� ����
									
									String participants = "";
									for(Map.Entry<String, PrintWriter> entry : chatServer.getRoomMap().get(split[3]).getParticipants().entrySet()){
										participants += entry.getKey()+" ";
									}
									map.get(split[1]).println("/inviteRoomDetail "+split[3]	//���ȣ
											+" "+id		//���� �ʴ��ߴ���
											+" "+chatServer.getRoomMap().get(split[3]).getOwner()	//���� ���̵�
											+" "+participants);										//������ ���̵�
								}else{
									pw.println("����� ������ ���常 �ʴ��Ͻ� �� �ֽ��ϴ�. /room "+split[3]);
								}
							}else{															//����� ����� �ƴ� ��� �׳� �ʴ�
								String participants = "";
								for(Map.Entry<String, PrintWriter> entry : chatServer.getRoomMap().get(split[3]).getParticipants().entrySet()){
									participants += entry.getKey()+" ";
								}
								map.get(split[1]).println("/inviteRoomDetail "+split[3]
										+" "+id		//���� �ʴ��ߴ���
										+" "+chatServer.getRoomMap().get(split[3]).getOwner()
										+" "+participants);
							}	
						}
						
					}else if(split[0].equals("/rejectInvite")){		//�ʴ븦 ������ ��� �ʴ��� ������� ���������� ����
						map.get(split[1]).println(id+"�Բ��� �ʴ븦 �����ϼ̽��ϴ�. /room "+split[3]);
					}else if(split[0].equals("/blockWhisper")){		//�ӼӸ� ����
						if(split.length==2){
							map.get(split[1]).println(id+"�Բ��� �ӼӸ��� �����߽��ϴ�. /room");	//���ǿ��� �ӼӸ� ���� ��� ���ܵǸ� ���ǿ� ���ܵ��� �˸�
						}else{
							map.get(split[1]).println(id+"�Բ��� �ӼӸ��� �����߽��ϴ�. /room "+split[3]);	//��ȭ�濡�� �ӼӸ� ���� ��� ���ܵǸ� ���ǿ� ���ܵ��� �˸�
						}
					}else if(split[0].equals("/sendFile")){	//���� ������ ������ ���
						if(split[1].equals("/all")){
							for(Map.Entry<String, PrintWriter> entry : chatServer.getRoomMap().get(split[4]).getParticipants().entrySet()){
								if(!entry.getKey().equals(id)){
									entry.getValue().println("/sendFile "+entry.getKey()+" "+split[2]+" "+id+" /room "+split[split.length-1]);
								}
							}
							
						}else{
							map.get(split[1]).println("/sendFile "+split[1]+" "+split[2]+" "+id+" /room "+split[split.length-1]);
						}
					}else if(split[0].equals("/receiveFile")){	//������ ���۹ޱ�� �� ���
						FileReceiveServer fileReceiveServer = new FileReceiveServer();	//���� ���۹��� ������ ��Ʈ�� ���
						new FileSendServer(fileReceiveServer).start();					//���� ���ۺ��� ������ ��Ʈ ���
						fileReceiveServer.start();
						map.get(split[3]).println(msg);
						map.get(split[1]).println(msg);
					}else if(split[0].equals("/chgRoomName")){
						boolean PW = false;
						for (Iterator iterator = chatServer.getProhibitWordList().iterator(); iterator.hasNext();) {
							if(msg.indexOf((String)iterator.next())!=-1){
								PW = true;
								break;
							}
						}
						if(PW){
							pw.println("/pwChgRoomName /room "+split[split.length-1]);
						}else{
							chatServer.getRoomMap()
							.get(split[split.length-1]).
							setName(msg.substring
									(split[0].length()+1,
									msg.lastIndexOf(" /room")));
							broadcast(msg);
							pw.println("/chgRoomNameOK /room "+split[split.length-1]);
						}
					}else if(split[0].equals("/cancelSecretRoom")){		//��ȭ�� �������� ���� ��й� �̾��µ� ��й� ������ ����ϴ� ���
						chatServer.getRoomMap().get(split[split.length-1]).setPassword(null);
						chatServer.getRoomMap().get(split[split.length-1]).setSecretRoom(false);
						pw.println("/cancelSecretRoomOk /room "+split[split.length-1]);
						broadcast(msg);
					}else if(split[0].equals("/chgRoomPassWord")){		//��ȭ�濡�� ��й�ȣ�� ���� ����
						if(chatServer.getRoomMap().get(split[split.length-1]).isSecretRoom()){	//���� ��й��̾��� ��� ��й�ȣ�� ����
							chatServer.getRoomMap().get(split[split.length-1]).setPassword(split[1]);
						}else{																	//���� ��й��� �ƴϾ��� ��� ��й����� �����ϰ� ��й�ȣ ����
							chatServer.getRoomMap().get(split[split.length-1]).setSecretRoom(true);
							chatServer.getRoomMap().get(split[split.length-1]).setPassword(split[1]);
							broadcast("/setSecretRoom "+split[split.length-1]);
						}
						pw.println("/chgRoomPassWordOk /room "+split[split.length-1]);
					}else if(split[split.length-2].equals("/room")){	
						boolean PW = false;
						for (Iterator iterator = chatServer.getProhibitWordList().iterator(); iterator.hasNext();) {
							if(msg.indexOf((String)iterator.next())!=-1){
								PW = true;
								break;
							}
						}
						if(PW){
							pw.println("�ٸ� ���� ����սô�!!! /room "+split[split.length-1]);
						}else{
							if(chatServer.getRoomMap().get(split[split.length-1]).getOwner().equals(id)){	//������ �ӼӸ��� ���
								if(split[0].equals("/w")){
									if(chatServer.getRoomMap().get(split[split.length-1]).getParticipants().containsKey(split[1])){//������ �ƴ� ������� �ӼӸ��� ���� ��쿡�� ���ǿ� �ش系���� ����
										map.get(split[1]).println("[�ӼӸ�] "+id+" (����) : "+msg.substring(msg.indexOf(split[2])));
										pw.println("[�ӼӸ�]("+split[1]+"���� ����) "+id+" (����) : "+msg.substring(msg.indexOf(split[2])));
									}else{	//��ȭ�濡�� ���Ƿ� �ӼӸ��� ������ ���
										map.get(split[1]).println("[�ӼӸ�] "+id+" : "+msg.substring(msg.indexOf(split[2]))+" /room");
										pw.println("[�ӼӸ�]("+split[1]+"���� ����) "+id+" : "+msg.substring(msg.indexOf(split[2])));
									}
								}else{	//�׳�  ���� ��ȭ
									broadcastRoom(id+" (����) : "+msg.substring(2),split[split.length-1]);
								}
							}else{	//������ �ƴ� ����� �ӼӸ� ���� ���
								if(split[0].equals("/w")){
									if(chatServer.getRoomMap().get(split[split.length-1]).getParticipants().containsKey(split[1])){
										map.get(split[1]).println("[�ӼӸ�] "+id+" : "+msg.substring(msg.indexOf(split[2])));
										pw.println("[�ӼӸ�]("+split[1]+"���� ����) "+id+" : "+msg.substring(msg.indexOf(split[2])));
									}else{	//��ȭ�濡�� ���Ƿ� �ӼӸ��� ������ ���
										map.get(split[1]).println("[�ӼӸ�] "+id+" : "+msg.substring(msg.indexOf(split[2]))+" /room");
										pw.println("[�ӼӸ�]("+split[1]+"���� ����) "+id+" : "+msg.substring(msg.indexOf(split[2])));
									}
								}else{	//�׳� ��ȭ
									broadcastRoom(id+" : "+msg.substring(2),split[split.length-1]);
								}
							}
							
						}
					}
				}
			} catch (IOException e) {
				Vector<String> removeRoomNum = new Vector<>();
				//���ڱ� ���������� �濡���� �����ߵ�
				for(Map.Entry<String, Room> entry : chatServer.getRoomMap().entrySet()){
					if(entry.getValue().getParticipants().get(id)!=null){
						if(entry.getValue().getOwner().equals(id)&&entry.getValue().getParticipants().size()!=1){	//������ ������
							//���� ���� �ѱ��
							for(Map.Entry<String, PrintWriter> entry2 : entry.getValue().getParticipants().entrySet()){
								if(!entry2.getKey().equals(id)){
									entry.getValue().setOwner(entry2.getKey());
									break;
								}
							}
							broadcastRoom("/rchgOwner "+entry.getValue().getOwner()+" /room "+entry.getKey(),entry.getKey());
							broadcastRoom("/rout "+id+" /room "+entry.getKey(),entry.getKey());
						}else if(entry.getValue().getParticipants().size()!=1){	//�Ϲ� ����� ������
							broadcastRoom("/rout "+id+" /room "+entry.getKey(),entry.getKey());
						}else{	//���� ȥ�� ���� �濡�� ������
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
					ta.append(id+"���� �����̽��ϴ�."+"\n");
					ta.setCaretPosition(ta.getDocument().getLength());
					for(Map.Entry<String, PrintWriter> entry:map.entrySet()){
						entry.getValue().println("/uout "+id);
						entry.getValue().println(id+"���� �����̽��ϴ�. /room");
					}
				}
				for (int i = 0; i < modelAll.getRowCount(); i++) {
					if(modelAll.getValueAt(i, 0).equals(id)){
						modelAll.removeRow(i);
						break;
					}
				}
			}finally {
				try {
					if(br!=null){
						br.close();
					}
					if(pw!=null){
						pw.close();
					}
					if(clientSocket!=null){
						clientSocket.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	//���ο� �� ����
	public void createNewRoom(String[] split,String msg){
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		String roomNum = chatServer.getRoomNum()+format1.format(Calendar.getInstance().getTime());	//��ȭ�� ��ȣ ����
		pw.println("/roomNum "+roomNum);	//����(�� ������)���� ���ȣ ����
		synchronized (map) {
			for(Map.Entry<String, PrintWriter> entry:map.entrySet()){
				entry.getValue().println("/newRoom "+msg.substring(split[0].length()+1,msg.lastIndexOf(" /room"))+" "+roomNum);	//���ο� ���� �����Ǿ����� �ٸ� ����鿡�� �˸�
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
