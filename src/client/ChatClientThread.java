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
//		System.out.println(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow().getName());
				System.out.println(msg);
				split = msg.split(" ");
				if(split[split.length-1].equals("/server")){	//�������� �� �޽����� ��� �濡 �Ѹ���
					ta.append(msg.substring(0,msg.length()-8)+"\n");
					for(Map.Entry<String, NewRoomFrame> entry : chatClient.getRoomInfoList().entrySet()){
						entry.getValue().getTa().append(msg.substring(0,msg.length()-8)+"\n");
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
					chatClient.
					getRoomInfoList().
					get(split[split.length-1]).
					getModelRoom().
					addRow(new String[]{split[1]});
				}else if(split[0].equals("/roomuserO")){//��ȭ�� ���� �� ���� ������ ����
					chatClient.getRoomInfoList().get(split[split.length-1]).getModelOwner().addRow(new String[]{split[1]});
				}else if(split[0].equals("/rin")){	//��ȭ�濡 ���ο� ���� ���� //�ش� ��ȭ�濡 ��Ŀ���� ���� ���� �˸�
					chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().addRow(new String[]{split[1]});
					if(chatClient.roomInfoList.get(split[split.length-1]).getRoomAlarm()){
						if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
							chatClient.getTrayIcon().displayMessage(split[split.length-1], split[1]+"���� �����ϼ̽��ϴ�.", MessageType.NONE);
						}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
							boolean focused = false;
							for (Map.Entry<String, NewRoomFrame> entry:chatClient.getRoomInfoList().entrySet()) {
								if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
									focused = true;
									break;
								}
							}
							if(!focused){
								chatClient.getTrayIcon().displayMessage(split[split.length-1], split[1]+"���� �����ϼ̽��ϴ�.", MessageType.NONE);
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
					if(chatClient.roomInfoList.get(split[split.length-1]).getRoomAlarm()){
						if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
							chatClient.getTrayIcon().displayMessage(split[split.length-1], split[1]+"���� �����ϼ̽��ϴ�.", MessageType.NONE);
						}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
							boolean focused = false;
							for (Map.Entry<String, NewRoomFrame> entry:chatClient.getRoomInfoList().entrySet()) {
								if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
									focused = true;
									break;
								}
							}
							if(!focused){
								chatClient.getTrayIcon().displayMessage(split[split.length-1], split[1]+"���� �����ϼ̽��ϴ�.", MessageType.NONE);
							}
						}
						
					}
				}else if(split[0].equals("/routOk")){
					chatClient.getRoomInfoList().remove(split[1]);
					chatClient.modelRoomDataChanged();
				}else if(split[0].equals("/rchgOwner")){
					chatClient.getRoomInfoList().get(split[split.length-1]).getModelOwner().removeRow(0);
					chatClient.getRoomInfoList().get(split[split.length-1]).getModelOwner().addRow(new String[]{split[1]});
				}else if(split[0].equals("/routOwner")){	//������ ������ ������ ���ο� ������ ������ �ְ� ����	//������ ���� ������ ����� ������ ��
					//���ο� ���� ����
					pw.println("/newOwner "+chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().getValueAt(0, 0)+" /room "+split[split.length-1]);
					chatClient.getRoomInfoList().remove(split[split.length-1]);
					chatClient.modelRoomDataChanged();
				}else if(split[0].equals("/banned")){
					chatClient.getRoomInfoList().get(split[3]).dispose();
					chatClient.getRoomInfoList().remove(split[3]);
					chatClient.addBannedRoomList(split[3]);
					chatClient.modelRoomDataChanged();
					ta.append(split[3]+"�� ä�ù濡�� ����Ǽ̽��ϴ�.");
					if(chatClient.isMsgAlarm()){
						if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null||KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
							chatClient.getTrayIcon().displayMessage("����", split[3]+"�� ä�ù濡�� ����Ǽ̽��ϴ�.", MessageType.NONE);
						}
					}
				}else if(split[0].equals("/ban")){
					for (int i = 0; i < chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().getRowCount(); i++) {
						if(chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().getValueAt(i, 0).equals(split[1])){
							chatClient.getRoomInfoList().get(split[split.length-1]).getModelRoom().removeRow(i);
							break;
						}
					}
					if(chatClient.roomInfoList.get(split[split.length-1]).getRoomAlarm()){
						if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
							chatClient.getTrayIcon().displayMessage(split[split.length-1], split[1]+"���� ����Ǽ̽��ϴ�.", MessageType.NONE);
						}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
							boolean focused = false;
							for (Map.Entry<String, NewRoomFrame> entry:chatClient.getRoomInfoList().entrySet()) {
								if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
									focused = true;
									break;
								}
							}
							if(!focused){
								chatClient.getTrayIcon().displayMessage(split[split.length-1], split[1]+"���� ����Ǽ̽��ϴ�.", MessageType.NONE);
							}
						}
					}
				}else if(split[0].equals("/roomBanned")){	//����� �濡 ������ �õ��ϴ� ��� ����� ������ �˸�
					chatClient.showAlreadyBannedRoom();
				}else if(split[0].equals("/invite")){
					chatClient.showInviteAccept(split);
				}else if(split[0].equals("/chgId")){
					//�ٸ� ��� ���̵� �����
					//blocklist ����
					//���̵� ����Ʈ ���� ����
					//���� ������ ���� ó���Ǿ���
					if(chatClient.getBlockNameList().contains(split[1])){
						chatClient.getBlockNameList().add(split[2]);
						chatClient.getBlockNameList().remove(split[1]);
					}
					for (int i = 0; i < chatClient.getModelAll().getRowCount(); i++) {
						if(chatClient.getModelAll().getValueAt(i, 0).equals(split[1])){
							chatClient.getModelAll().setValueAt(split[2], i, 0);
							ta.append(split[1]+"���� ���̵� "+split[2]+"�� ����Ǿ����ϴ�.\n");
							if(chatClient.isMsgAlarm()){
								if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null||KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
									chatClient.getTrayIcon().displayMessage("���̵� ����",split[1]+"���� ���̵� "+split[2]+"�� ����Ǿ����ϴ�.", MessageType.NONE);
								}
							}
							break;
						}
					}
					for(Map.Entry<String, NewRoomFrame> entry : chatClient.getRoomInfoList().entrySet()){
						for (int i = 0; i < entry.getValue().getModelAll().getRowCount(); i++) {
							if(entry.getValue().getModelAll().getValueAt(i, 0).equals(split[1])){
								entry.getValue().getModelAll().setValueAt(split[2], i, 0);
								entry.getValue().getTa().append(split[1]+"���� ���̵� "+split[2]+"�� ����Ǿ����ϴ�.\n");
								break;
							}
						}
						for (int i = 0; i < entry.getValue().getModelRoom().getRowCount(); i++) {
							if(entry.getValue().getModelRoom().getValueAt(i, 0).equals(split[1])){
								entry.getValue().getModelRoom().setValueAt(split[2], i, 0);
								break;
							}
						}
					}
					
				}else if(split[0].equals("/sendFile")){
					chatClient.receiveFileChk(split.clone());
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
					
				}else if(split[0].equals("/chgRoomName")){
					for (int i = 0; i < chatClient.getTableRoom().getRowCount(); i++) {
						if(chatClient.getTableRoom().getValueAt(i, 0).equals(split[split.length-1])){
							chatClient.getTableRoom().setValueAt(split[2], i, 1);
							break;
						}
					}
					if(chatClient.getRoomInfoList().containsKey(split[split.length-1])){
						String title = chatClient.getRoomInfoList().get(split[split.length-1]).getTitle(); 
						chatClient.getRoomInfoList().get(split[split.length-1]).setTitle(
								title.substring(0,title.indexOf(chatClient.getRoomInfoList().get(split[split.length-1]).getRoomName()))
								+split[2]+title.substring(title.indexOf(" ( ä�ø� : ")));
						chatClient.getRoomInfoList().get(split[split.length-1]).setRoomName(split[2]);
					}
				}else if(split[0].equals("/cancelSecretRoom")){
					for (int i = 0; i < chatClient.getTableRoom().getRowCount(); i++) {
						if(chatClient.getTableRoom().getValueAt(i, 0).equals(split[split.length-1])){
							chatClient.getTableRoom().setValueAt("NO", i, 2);
							break;
						}
					}
				}else if(split[0].equals("/setSecretRoom")){
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
							if(chatClient.isMsgAlarm()){
								if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null||KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
									chatClient.getTrayIcon().displayMessage("����",msg.substring(0,msg.indexOf("/room")-1), MessageType.NONE);
								}
							}
						}else{
							if(split[split.length-3].equals("/room")){
								pw.println("/blockWhisper "+split[1]+" /room "+split[split.length-2]);
							}else{
								pw.println("/blockWhisper "+split[1]);
							}
						}
					}else{	//�Ϲ� ��ȭ
						if(split[0].equals(chatClient.getId())){	//���� ���� �޽���
							ta.append(split[0]+"(��) "+msg.substring(msg.indexOf(split[1]),msg.length()-6)+"\n");
						}else{
							ta.append(msg.substring(0,msg.length()-6)+"\n");
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
							// �ش� �濡 ��Ŀ���� ���� ��쿡�� �˸�
							if(chatClient.roomInfoList.get(split[split.length-1]).getRoomAlarm()){
								if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
									chatClient.getTrayIcon().displayMessage(split[split.length-1],msg.substring(0,msg.indexOf("/room")-1) , MessageType.NONE);
								}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
									boolean focused = false;
									for (Map.Entry<String, NewRoomFrame> entry:chatClient.getRoomInfoList().entrySet()) {
										if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
											focused = true;
											break;
										}
									}
									if(!focused){
										chatClient.getTrayIcon().displayMessage(split[split.length-1],msg.substring(0,msg.indexOf("/room")-1) , MessageType.NONE);
									}
								}
							}
						}
					}else{
						if(split[0].equals(chatClient.getId())){	//���� ���� �޽����� ��쿡�� (��) ��� ����
							chatClient.getRoomInfoList().get(split[split.length-1]).getTa().append(split[0]+"(��) "+msg.substring(msg.indexOf(split[1]),msg.indexOf("/room")-1)+"\n");
							// �ش� �濡 ��Ŀ���� ���� ��쿡�� �˸�
							if(chatClient.roomInfoList.get(split[split.length-1]).getRoomAlarm()){
								if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
									chatClient.getTrayIcon().displayMessage(split[split.length-1],split[0]+"(��) "+msg.substring(msg.indexOf(split[1]),msg.indexOf("/room")-1) , MessageType.NONE);
								}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
									boolean focused = false;
									for (Map.Entry<String, NewRoomFrame> entry:chatClient.getRoomInfoList().entrySet()) {
										if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
											focused = true;
											break;
										}
									}
									if(!focused){
										chatClient.getTrayIcon().displayMessage(split[split.length-1],split[0]+"(��) "+msg.substring(msg.indexOf(split[1]),msg.indexOf("/room")-1) , MessageType.NONE);
									}
								}
							}
						}else{
							chatClient.getRoomInfoList().get(split[split.length-1]).getTa().append(msg.substring(0,msg.indexOf("/room")-1)+"\n");
							// �ش� �濡 ��Ŀ���� ���� ��쿡�� �˸�
							if(chatClient.roomInfoList.get(split[split.length-1]).getRoomAlarm()){
								if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
									chatClient.getTrayIcon().displayMessage(split[split.length-1],msg.substring(0,msg.indexOf("/room")-1) , MessageType.NONE);
								}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
									boolean focused = false;
									for (Map.Entry<String, NewRoomFrame> entry:chatClient.getRoomInfoList().entrySet()) {
										if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
											focused = true;
											break;
										}
									}
									if(!focused){
										chatClient.getTrayIcon().displayMessage(split[split.length-1],msg.substring(0,msg.indexOf("/room")-1) , MessageType.NONE);
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
		} 
	}
}

