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
				if(split[split.length-1].equals("/server")){	//�������� �� �޽����� ��� �濡 �Ѹ���
					ta.append(msg.substring(0,msg.length()-8)+"\n");
					ta.setCaretPosition(ta.getDocument().getLength());				
					for(Map.Entry<String, NewRoomFrame> entry : chatClient.getRoomInfoList().entrySet()){
						entry.getValue().getTa().append(msg.substring(0,msg.length()-8)+"\n");
						entry.getValue().getTa().setCaretPosition(entry.getValue().getTa().getDocument().getLength());
					}
					if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
						chatClient.getTrayIcon().displayMessage("Server", msg.substring(0,msg.length()-8), MessageType.NONE);
					}
				}else if(split[0].equals("/roomNum")){	//���ο� ���� �����ϸ� �����κ��� ���ȣ�� �޾ƿ�
					chatClient.getNewRoomFrame().setTitle("ä�ù� : "+chatClient.getNewRoomFrame().getRoomName()+" ( ä�ø� : "+chatClient.getId()+" ) - "+split[1]);
					chatClient.getRoomInfoList().put(split[1], chatClient.getNewRoomFrame());
					chatClient.getRoomInfoList().get(split[1]).setRoomNum(split[1]);
					for (int i = 0; i < chatClient.getModelAll().getRowCount(); i++) {
						chatClient.getRoomInfoList().get(split[1]).getModelAll().addRow(new String[]{chatClient.getModelAll().getValueAt(i, 0).toString()});
					}
					chatClient.modelRoomDataChanged();
				}else if(split[0].equals("/pwCreateNewRoom")){
					chatClient.pwCreateNewRoom();
					
				}else if(split[0].equals("/newRoom")){	//���ο� ���� ����� ���ǿ� �� ��� ����
					chatClient.getModelRoom().addRow(new String[]{split[split.length-1],msg.substring(split[0].length()+1,msg.lastIndexOf(split[split.length-1])-1),"NO"});
				}else if(split[0].equals("/newSecretRoom")){	//���ο� ���� ����� ���ǿ� �� ��� ����
					chatClient.getModelRoom().addRow(new String[]{split[split.length-1],msg.substring(split[0].length()+1,msg.lastIndexOf(split[split.length-1])-1),"YES"});
				}else if(split[0].equals("/removeRoom")){	//���� ������� ���� �� ��� ����
					for (int i = 0; i < chatClient.getModelRoom().getRowCount(); i++) {
						if(chatClient.getModelRoom().getValueAt(i, 0).equals(split[1])){
							chatClient.getModelRoom().removeRow(i);
							chatClient.removeBannedRoomList(split[1]);
							break;
						}
					}
				}else if(split[0].equals("/uin")){	//���ο� ���� �α���
					chatClient.getModelAll().addRow(new String[]{split[1]});
					for (Map.Entry<String, NewRoomFrame> entry: chatClient.getRoomInfoList().entrySet()) {
						entry.getValue().getModelAll().addRow(new String[]{split[1]});
					}

				}else if(split[0].equals("/uout")){	//�ٸ� ���� �α׾ƿ� ��
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
					if(chatClient.getBlockNameList().contains(split[1])){	//�ӼӸ� ������ ���̵� ���ܸ���Ʈ���� ����
						chatClient.getBlockNameList().remove(split[1]);
					}
				}else if(split[0].equals("/user")){	//�α����ϸ� ���� ���� ����Ʈ ����
					chatClient.getModelAll().addRow(new String[]{split[1]});
				}else if(split[0].equals("/roomlist")){	//�α����ϸ� ���� ��ȭ�� ����Ʈ�� ����
					chatClient.getModelRoom().addRow(new String[]{split[1],msg.substring(msg.indexOf(split[1])+split[1].length()+1,msg.lastIndexOf(split[split.length-1])-1),split[split.length-1]});
				}else if(split[0].equals("/roomDetail")){
					chatClient.createBeforeEnterRoomDetail(split.clone());	//�濡 �����ϱ� ���� �������� ������
				}else if(split[0].equals("/correctPassword")){				//��� ��ȭ���� ��й�ȣ�� ��ġ�ϴ� ���
					chatClient.correctPassword();
				}else if(split[0].equals("/inviteRoomDetail")){
					chatClient.createBeforeInviteRoomDetail(split.clone());
				}else if(split[0].equals("/inviteNo")){						//�̹� �ʴ뿡 ���ؼ� ������ �� ��� ������ �ʴ븦 �ؼ� �ʴ�޽����� ������ ���
					chatClient.inviteNo(split.clone(),msg);
				}else if(split[0].equals("/enterOk")){
					chatClient.enterRoom(msg.substring(split[0].length()+1,msg.lastIndexOf(split[split.length-2])-1),split[split.length-1]);
				}else if(split[0].equals("/wrongPassword")){				//��� ��ȭ���� ��й�ȣ�� Ʋ�� ���
					chatClient.wrongPassword();
				}else if(split[0].equals("/roomuser")){	//��ȭ�� ���� �� ���� ���� ����Ʈ ����
					chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().addRow(new String[]{split[1]});
				}else if(split[0].equals("/roomuserO")){//��ȭ�� ���� �� ���� ������ ����
					chatClient.getRoomInfoList().get(split[split.length-1]).getModelOwner().addRow(new String[]{split[1]});
				}else if(split[0].equals("/rin")){	//��ȭ�濡 ���ο� ���� ���� //�ش� ��ȭ�濡 ��Ŀ���� ���� ���� �˸�
					chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().addRow(new String[]{split[1]});
					chatClient.getRoomInfoList().get(split[split.length-1]).getTa().append(split[1]+"���� �����ϼ̽��ϴ�.\n");
					chatClient.getRoomInfoList().get(split[split.length-1]).getTa().setCaretPosition(chatClient.getRoomInfoList().get(split[split.length-1]).getTa().getDocument().getLength());
					if(chatClient.roomInfoList.get(split[split.length-1]).getRoomAlarm()){
						if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
							chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(), split[1]+"���� �����ϼ̽��ϴ�.", MessageType.NONE);
						}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
							boolean focused = false;
							for (Map.Entry<String, NewRoomFrame> entry:chatClient.getRoomInfoList().entrySet()) {
								if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
									focused = true;
									break;
								}
							}
							if(!focused){
								chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(), split[1]+"���� �����ϼ̽��ϴ�.", MessageType.NONE);
							}
						}
					}
				}else if(split[0].equals("/rout")){	//��ȭ�濡 ���� ���� ����
					for (int i = 0; i < chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().getRowCount(); i++) {
						if(chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().getValueAt(i, 0).equals(split[1])){
							chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().removeRow(i);
							break;
						}
					}
					chatClient.getRoomInfoList().get(split[split.length-1]).getTa().append(split[1]+"������ �����ϼ̽��ϴ�.\n");
					chatClient.getRoomInfoList().get(split[split.length-1]).getTa().setCaretPosition(chatClient.getRoomInfoList().get(split[split.length-1]).getTa().getDocument().getLength());
					if(chatClient.roomInfoList.get(split[split.length-1]).getRoomAlarm()){
						if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
							chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(), split[1]+"���� �����ϼ̽��ϴ�.", MessageType.NONE);
						}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
							boolean focused = false;
							for (Map.Entry<String, NewRoomFrame> entry:chatClient.getRoomInfoList().entrySet()) {
								if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
									focused = true;
									break;
								}
							}
							if(!focused){
								chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(), split[1]+"���� �����ϼ̽��ϴ�.", MessageType.NONE);
							}
						}
						
					}
				}else if(split[0].equals("/routOk")){	//�濡�� ������ �� ��ȭ�� ����Ʈ���� ����
					chatClient.getRoomInfoList().remove(split[1]);
					chatClient.modelRoomDataChanged();
				}else if(split[0].equals("/rchgOwner")){	//��ȭ�� �����̸� ����Ǿ����� ��ȭ�� ����ڿ��� �˸�
					chatClient.getRoomInfoList().get(split[split.length-1]).getTa().append("������ "+split[1]+"������ ����Ǿ����ϴ�.\n");
					chatClient.getRoomInfoList().get(split[split.length-1]).getTa().setCaretPosition(chatClient.getRoomInfoList().get(split[split.length-1]).getTa().getDocument().getLength());
					chatClient.getRoomInfoList().get(split[split.length-1]).getModelOwner().removeRow(0);
					chatClient.getRoomInfoList().get(split[split.length-1]).getModelOwner().addRow(new String[]{split[1]});
				}else if(split[0].equals("/routOwner")){	//������ ������ ������ ���ο� ������ ������ �ְ� ����	//������ ���� ������ ����� ������ ��
					//���ο� ���� ����
					pw.println("/newOwner "+chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().getValueAt(0, 0)+" /room "+split[split.length-1]);
					chatClient.getRoomInfoList().remove(split[split.length-1]);	
					chatClient.modelRoomDataChanged();
				}else if(split[0].equals("/banned")){		//����� ������� ����Ǿ����� �˸�
					chatClient.getRoomInfoList().get(split[3]).dispose();	//����� ä�ù� �ݱ�
					chatClient.getRoomInfoList().remove(split[3]);			
					chatClient.addBannedRoomList(split[3]);					//����� �� ����Ʈ�� ����
					chatClient.modelRoomDataChanged();				
					ta.append(split[3]+"�� ä�ù濡�� ����Ǽ̽��ϴ�.\n");				
					ta.setCaretPosition(ta.getDocument().getLength());				
					if(chatClient.isMsgAlarm()){
						if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null||KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
							chatClient.getTrayIcon().displayMessage("����", split[3]+"�� ä�ù濡�� ����Ǽ̽��ϴ�.", MessageType.NONE);
						}
					}
				}else if(split[0].equals("/ban")){	//����� ����� �ƴ� ����ڿ��Դ� ����� ����� �˸�		
					for (int i = 0; i < chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().getRowCount(); i++) {//�ش��ϴ� ����� ����
						if(chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().getValueAt(i, 0).equals(split[1])){
							chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().removeRow(i);
							break;
						}
					}
					chatClient.roomInfoList.get(split[split.length-1]).getTa().append(split[1]+"���� ä�ù濡�� ����Ǽ̽��ϴ�.\n");	
					chatClient.getRoomInfoList().get(split[split.length-1]).getTa().setCaretPosition(chatClient.getRoomInfoList().get(split[split.length-1]).getTa().getDocument().getLength());
					if(chatClient.roomInfoList.get(split[split.length-1]).getRoomAlarm()){
						if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
							chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(), split[1]+"���� ����Ǽ̽��ϴ�.", MessageType.NONE);
						}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
							boolean focused = false;
							for (Map.Entry<String, NewRoomFrame> entry:chatClient.getRoomInfoList().entrySet()) {
								if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
									focused = true;
									break;
								}
							}
							if(!focused){
								chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(), split[1]+"���� ����Ǽ̽��ϴ�.", MessageType.NONE);
							}
						}
					}
				}else if(split[0].equals("/roomBanned")){	//����� �濡 ������ �õ��ϴ� ��� ����� ������ �˸�
					chatClient.showAlreadyBannedRoom();
				}else if(split[0].equals("/invite")){
					chatClient.showInviteAccept(split);
				}else if(split[0].equals("/pwchgId")){
					chatClient.pwchgId();
				}else if(split[0].equals("/chgId")){
					//�ٸ� ��� ���̵� �����
					//blocklist ����
					//���̵� ����Ʈ ���� ����
					//���� ������ ���� ó���Ǿ���
					if(chatClient.getBlockNameList().contains(split[1])){
						chatClient.getBlockNameList().add(split[2]);
						chatClient.getBlockNameList().remove(split[1]);
					}
					boolean MyId = true;
					for (int i = 0; i < chatClient.getModelAll().getRowCount(); i++) {
						if(chatClient.getModelAll().getValueAt(i, 0).equals(split[1])){
							chatClient.getModelAll().setValueAt(split[2], i, 0);
							ta.append(split[1]+"���� ���̵� "+split[2]+"�� ����Ǿ����ϴ�.\n");
							ta.setCaretPosition(ta.getDocument().getLength());
							MyId = false;
							break;
						}
					}
					for(Map.Entry<String, NewRoomFrame> entry : chatClient.getRoomInfoList().entrySet()){	//��ȭ�� ���� ����Ʈ ���̵� ����
						for (int i = 0; i < entry.getValue().getModelAll().getRowCount(); i++) {
							if(entry.getValue().getModelAll().getValueAt(i, 0).equals(split[1])){
								entry.getValue().getModelAll().setValueAt(split[2], i, 0);
								entry.getValue().getTa().append(split[1]+"���� ���̵� "+split[2]+"�� ����Ǿ����ϴ�.\n");
								entry.getValue().getTa().setCaretPosition(entry.getValue().getTa().getDocument().getLength());
								break;
							}
						}
						for (int i = 0; i < entry.getValue().getModelRoom().getRowCount(); i++) {
							if(entry.getValue().getModelRoom().getValueAt(i, 0).equals(split[1])){
								entry.getValue().getModelRoom().setValueAt(split[2], i, 0);
								if(entry.getValue().getRoomAlarm()){
									if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null||!(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame)){
										chatClient.getTrayIcon().displayMessage("���̵� ����",split[1]+"���� ���̵� "+split[2]+"�� ����Ǿ����ϴ�.", MessageType.NONE);
									}
								}
								break;
							}
						}
					}
					if(MyId){
						for(Map.Entry<String, NewRoomFrame> entry : chatClient.getRoomInfoList().entrySet()){	//������ �� �ִ� ��ȭ�濡 Ÿ��Ʋ�� ǥ�õǴ� ���̵� ���� 
							entry.getValue().setTitle(entry.getValue().getTitle().substring(0,entry.getValue().getTitle().lastIndexOf(chatClient.getId()+" ) - "))+split[2]+" ) - "+entry.getKey());
						}
						chatClient.setId(split[2]);
						chatClient.setTitle(split[2]+"���� ä��â");	//���� Ÿ��Ʋ�� ���̵� ǥ�� ����
						chatClient.chgIdOk();
					}
				}else if(split[0].equals("/sendFile")){
					chatClient.receiveFileChk(split.clone());	//������ ���۹��� ������ Ȯ��
				}else if(split[0].equals("/receiveFile")){	//���� ���� ����
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
				}else if(split[0].equals("/chgRoomName")){		//��ȭ�� �̸��� ������ ��� ����ڵ鿡�� ��ȭ���̸��� ����Ǿ����� �˸�
					for (int i = 0; i < chatClient.getTableRoom().getRowCount(); i++) {
						if(chatClient.getTableRoom().getValueAt(i, 0).equals(split[split.length-1])){
							chatClient.getTableRoom().setValueAt(msg.substring(split[0].length()+1,msg.lastIndexOf(" /room")), i, 1);
							break;
						}
					}
					if(chatClient.getRoomInfoList().containsKey(split[split.length-1])){	//��ȭ���̸��� ����� ��ȭ�濡 �� �ִ� ��� Ÿ��Ʋ�� ����
						String title = chatClient.getRoomInfoList().get(split[split.length-1]).getTitle(); 
						chatClient.getRoomInfoList().get(split[split.length-1]).setTitle(
								title.substring(0,title.indexOf(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName()))
								+msg.substring(split[0].length()+1,msg.lastIndexOf(" /room"))+title.substring(title.indexOf(" ( ä�ø� : ")));
						chatClient.getRoomInfoList().get(split[split.length-1]).getTa().append("��ȭ���� �̸��� "+chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName()
								+"���� "+msg.substring(split[0].length()+1,msg.lastIndexOf(" /room"))+"���� ����Ǿ����ϴ�.\n");
						chatClient.getRoomInfoList().get(split[split.length-1]).getTa().setCaretPosition(chatClient.getRoomInfoList().get(split[split.length-1]).getTa().getDocument().getLength());
						chatClient.getRoomInfoList().get(split[split.length-1]).setRoomName(msg.substring(split[0].length()+1,msg.lastIndexOf(" /room")));
					}
				}else if(split[0].equals("/cancelSecretRoomOk")){
					chatClient.cancelSecretRoomOk(split[split.length-1]);
				}else if(split[0].equals("/cancelSecretRoom")){		//��ȭ�� �������� ���� ��й� �̾��µ� ��й� ������ ����ϴ� ���
					for (int i = 0; i < chatClient.getTableRoom().getRowCount(); i++) {
						if(chatClient.getTableRoom().getValueAt(i, 0).equals(split[split.length-1])){
							chatClient.getTableRoom().setValueAt("NO", i, 2);
							break;
						}
					}
				}else if(split[0].equals("/chgRoomPassWordOk")){
					chatClient.chgRoomPassWordOk(split[split.length-1]);
				}else if(split[0].equals("/setSecretRoom")){		//���� ��й��� �ƴϾ��� ���� ��й����� ������ ��� ��ȭ�� ����Ʈ�� ����
					for (int i = 0; i < chatClient.getTableRoom().getRowCount(); i++) {
						if(chatClient.getTableRoom().getValueAt(i, 0).equals(split[split.length-1])){
							chatClient.getTableRoom().setValueAt("YES", i, 2);
							break;
						}
					}
				}else if(split[split.length-1].equals("/room")){	//���� ä��
					if(split[0].equals("[�ӼӸ�]")){//�ӼӸ� ���� ���� Ȯ��
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
									chatClient.getTrayIcon().displayMessage("����",msg.substring(0,msg.indexOf("/room")-1), MessageType.NONE);
								}
							}
						}else{	//�ӼӸ� ���ܵ� ���
							if(split[split.length-3].equals("/room")){	//��ȭ�濡�� ���ǿ� �ִ� ������� �ӼӸ��ߴµ� ���ܵ� ��� ��ȭ������ �ٽ� �˸�
								pw.println("/blockWhisper "+split[1]+" /room "+split[split.length-2]);
							}else{
								pw.println("/blockWhisper "+split[1]);
							}
						}
					}else{	//�Ϲ� ��ȭ
						if(split[0].equals(chatClient.getId())){	//���� ���� �޽���
							ta.append(split[0]+"(��) "+msg.substring(msg.indexOf(split[1]),msg.length()-6)+"\n");
							ta.setCaretPosition(ta.getDocument().getLength());
						}else{
							ta.append(msg.substring(0,msg.length()-6)+"\n");
							ta.setCaretPosition(ta.getDocument().getLength());
						}
					}
				}else if(split[split.length-2].equals("/room")){	//�� ä��
					if(split[0].equals("[�ӼӸ�]")){
						boolean blocked = false;	//�ӼӸ� ���� ���� Ȯ��
						for (int i = 0; i < chatClient.getBlockNameList().size(); i++) {
							if(split[1].equals(chatClient.getBlockNameList().get(i))){
								blocked = true;
								break;
							}
						}
						if(!blocked){
							chatClient.getRoomInfoList().get(split[split.length-1]).getTa().append(msg.substring(0,msg.indexOf("/room")-1)+"\n");
							chatClient.getRoomInfoList().get(split[split.length-1]).getTa().setCaretPosition(chatClient.getRoomInfoList().get(split[split.length-1]).getTa().getDocument().getLength());
							// �ش� �濡 ��Ŀ���� ���� ��쿡�� �˸�
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
					}else{	//��ȭ�� �Ϲ� ��ȭ
						if(split[0].equals(chatClient.getId())){	//���� ���� �޽����� ��쿡�� (��) ��� ����
							chatClient.getRoomInfoList().get(split[split.length-1]).getTa().append(split[0]+"(��) "+msg.substring(msg.indexOf(split[1]),msg.indexOf("/room")-1)+"\n");
							chatClient.getRoomInfoList().get(split[split.length-1]).getTa().setCaretPosition(chatClient.getRoomInfoList().get(split[split.length-1]).getTa().getDocument().getLength());
							// �ش� �濡 ��Ŀ���� ���� ��쿡�� �˸�
							if(chatClient.roomInfoList.get(split[split.length-1]).getRoomAlarm()){
								if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
									chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(),split[0]+"(��) "+msg.substring(msg.indexOf(split[1]),msg.indexOf("/room")-1) , MessageType.NONE);
								}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
									boolean focused = false;
									for (Map.Entry<String, NewRoomFrame> entry:chatClient.getRoomInfoList().entrySet()) {
										if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
											focused = true;
											break;
										}
									}
									if(!focused){
										chatClient.getTrayIcon().displayMessage(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName(),split[0]+"(��) "+msg.substring(msg.indexOf(split[1]),msg.indexOf("/room")-1) , MessageType.NONE);
									}
								}
							}
						}else{
							chatClient.getRoomInfoList().get(split[split.length-1]).getTa().append(msg.substring(0,msg.indexOf("/room")-1)+"\n");
							chatClient.getRoomInfoList().get(split[split.length-1]).getTa().setCaretPosition(chatClient.getRoomInfoList().get(split[split.length-1]).getTa().getDocument().getLength());
							// �ش� �濡 ��Ŀ���� ���� ��쿡�� �˸�
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
			JOptionPane.showMessageDialog(null, "������ ����Ǿ����ϴ�.");
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

