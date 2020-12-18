import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.FocusManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ChatClientPrac extends JFrame implements ActionListener{
	JPanel pnl;
	JTextArea ta;
	JTextField tf;
	
	NewRoomFrame newRoomFrame;
	
	JButton newRoomBtn;
	JButton enterBtn;
	
	PrintWriter pw;
	BufferedReader br;
	Socket socket;
	
	String id = "";
	
	HashMap<String, NewRoomFrame> roomInfoList;	//���� ������ �ִ� ä�ù�
	
	String[] headerRoom = {"��ȣ","��ȭ�� �̸�"};
	String[][] contentRoom = {};
	DefaultTableModel modelRoom;
	JTable tableRoom;
	JScrollPane pane_tableRoom;
	
	String[] headerAll = {"��� ������"};
	String[][] contentAll = {};
	DefaultTableModel modelAll;
	JTable tableAll;
	JScrollPane pane_tableAll;
	
	TrayIcon trayIcon;
	public ChatClientPrac() {
		init();
		setClient();
	}
	public void setClient(){
		try {
			while(id.equals("")){
				id = JOptionPane.showInputDialog("���̵�");
				if(id==null){
					int i = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
					if(i == JOptionPane.OK_OPTION){
						System.exit(0);
					}else{
						id = "";
					}
				}
			}
			socket = new Socket("127.0.0.1",5555);
			pw = new PrintWriter(socket.getOutputStream(),true);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String strExist = null;
			pw.println(id);
			while((strExist = br.readLine()).equals("/exist")){
				JOptionPane.showMessageDialog(this, "�̹� �����ϴ� ���̵� �Դϴ�.");
				id = "";
				while(id.equals("")){
					id = JOptionPane.showInputDialog("���̵�");
					if(id==null){
						int i = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
						if(i == JOptionPane.OK_OPTION){
							System.exit(0);
						}else{
							id = "";
						}
					}
				}
				pw.println(id);
			}
			
			
			setTitle(id+"���� ä��â");
			new ClientThread(id, socket, ta, modelAll, this).start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(620,600);
		
		roomInfoList = new HashMap<>();
		
		pnl = new JPanel();
		pnl.setLayout(null);
		
		tf = new JTextField();
		tf.addActionListener(this);
		tf.setBounds(0, 500, 500, 30);
		ta =  new JTextArea();
		JScrollPane pane = new JScrollPane(ta);
		pane.setBounds(0, 350, 500, 150);
		pnl.add(pane);
		pnl.add(tf);
		
		newRoomBtn = new JButton("��ȭ�� �����");
		newRoomBtn.setBounds(0, 300, 150, 50);
		newRoomBtn.addActionListener(this);
		
		modelRoom = new DefaultTableModel(contentRoom,headerRoom);
		tableRoom = new JTable(modelRoom);
		pane_tableRoom = new JScrollPane(tableRoom);
		pane_tableRoom.setBounds(0, 0, 500, 300);
		pnl.add(pane_tableRoom);
		
		modelAll = new DefaultTableModel(contentAll,headerAll);
		tableAll = new JTable(modelAll);
		pane_tableAll = new JScrollPane(tableAll);
		pane_tableAll.setBounds(500, 0, 100, 480);
		pnl.add(pane_tableAll);
		
		
		
		enterBtn = new JButton("�� ����");
		enterBtn.setBounds(150, 300, 150, 50);
		enterBtn.addActionListener(this);
		
		
		SystemTray systemTray = SystemTray.getSystemTray();
		Image img = Toolkit.getDefaultToolkit().getImage("big6.png");
		img = img.getScaledInstance(15, 15, Image.SCALE_DEFAULT);
		trayIcon = new TrayIcon(img);
		try {
			systemTray.add(trayIcon);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setIconImage(img);
		
		pnl.add(enterBtn);
		pnl.add(newRoomBtn);
		add(pnl);
		setVisible(true);
		
	}
	public void createRoom(String name){
		newRoomFrame = new NewRoomFrame(this,name);
		newRoomFrame.getModelOwner().addRow(new String[]{id});
		pw.println("/createRoom "+name+" "+id+" /room");
	}
	public void enterRoom(String name,String roomNum){
		newRoomFrame = new NewRoomFrame(this,name);
		newRoomFrame.setRoomNum(roomNum);
		roomInfoList.put(roomNum, newRoomFrame);
		newRoomFrame.setTitle("ä�ù� : "+name+"(ä�ø� : "+id+") - "+roomNum);
		for (int i = 0; i < modelAll.getRowCount(); i++) {
			roomInfoList.get(roomNum).getModelAll().addRow(new String[]{modelAll.getValueAt(i, 0).toString()});
		}
	}
	public HashMap<String, NewRoomFrame> getRoomInfoList() {
		return roomInfoList;
	}
	public NewRoomFrame getNewRoomPnl(){
		return newRoomFrame;
	}
	public static void main(String[] args) {
		new ChatClientPrac();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==newRoomBtn){	//���ο� �� �����
			new ChatNewRoomDialog(this);
		}else if(e.getSource()== enterBtn){	//��ȭ�� ����Ʈ���� ���� �� ���� ��ư�� ���� ���
			if(tableRoom.getSelectedRow()!=-1){
				boolean alreadyIn = false;
				for(Map.Entry<String, NewRoomFrame> entry : roomInfoList.entrySet()){
					if(tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).equals(entry.getKey())){
						alreadyIn = true;
					}
				}
				if(alreadyIn){
					JOptionPane.showMessageDialog(this, "�̹� �����Ͻ� ���Դϴ�.");
				}else{
					enterRoom(tableRoom.getValueAt(tableRoom.getSelectedRow(), 1).toString(),tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).toString());
					pw.println("/enterRoom "+tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).toString());
				}
			}
		}else{	//���ǿ��� �ؽ�Ʈ �ʵ��� �޽��� ���۽�
			pw.println(tf.getText()+" /room");
			tf.selectAll();
			
		}
	}
	public void chatInRoom(String msg,String roomNum){
		pw.println(msg+" /room "+roomNum);
	}
	public void exitRoom(String roomNum){
		pw.println("/exitRoom "+roomNum);
		
	}
	public JTable getTableRoom() {
		return tableRoom;
	}
	public JTable getTableAll() {
		return tableAll;
	}
	public DefaultTableModel getModelRoom() {
		return modelRoom;
	}
	public DefaultTableModel getModelAll() {
		return modelAll;
	}
	public String getId() {
		return id;
	}
	public TrayIcon getTrayIcon() {
		return trayIcon;
	}
	public NewRoomFrame getNewRoomFrame() {
		return newRoomFrame;
	}
	
	
	
}
class ClientThread extends Thread{
	Socket socket;
	JTextArea ta;
	DefaultTableModel model;
	BufferedReader br;
	PrintWriter pw;
	ChatClientPrac chatClientPrac;
	public ClientThread(String id,Socket socket, JTextArea ta,DefaultTableModel model,ChatClientPrac chatClientPrac) {
		this.socket = socket;
		this.ta = ta;
		this.model = model;
		this.chatClientPrac =chatClientPrac;
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		String msg;
		String[] split = null;
		try {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while ((msg = br.readLine())!=null) {
//		System.out.println(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow().getName());
				System.out.println(msg);
				split = msg.split(" ");
				if(split[split.length-1].equals("/server")){	//�������� �� �޽����� ��� �濡 �Ѹ���
					ta.append(msg.substring(0,msg.length()-8)+"\n");
					for(Map.Entry<String, NewRoomFrame> entry : chatClientPrac.getRoomInfoList().entrySet()){
						entry.getValue().getTa().append(msg.substring(0,msg.length()-8)+"\n");
					}
					if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
						chatClientPrac.getTrayIcon().displayMessage("Server", msg.substring(0,msg.length()-8), MessageType.NONE);
					}
				}else if(split[0].equals("/roomNum")){	//���ο� ���� �����ϸ� �����κ��� ���ȣ�� �޾ƿ�
					chatClientPrac.getNewRoomFrame().setTitle("ä�ù� : "+chatClientPrac.getNewRoomFrame().getRoomName()+"(ä�ø� : "+chatClientPrac.getId()+") - "+split[1]);
					chatClientPrac.getRoomInfoList().put(split[1], chatClientPrac.getNewRoomFrame());
					chatClientPrac.getRoomInfoList().get(split[1]).setRoomNum(split[1]);
					for (int i = 0; i < chatClientPrac.getModelAll().getRowCount(); i++) {
						chatClientPrac.getRoomInfoList().get(split[1]).getModelAll().addRow(new String[]{chatClientPrac.getModelAll().getValueAt(i, 0).toString()});
					}
				}else if(split[0].equals("/newRoom")){	//���ο� ���� ����� ���ǿ� �� ��� ����
					chatClientPrac.getModelRoom().addRow(new String[]{split[2],split[1]});
				}else if(split[0].equals("/removeRoom")){	//���� ������� ���� �� ��� ����
					for (int i = 0; i < chatClientPrac.getModelRoom().getRowCount(); i++) {
						if(chatClientPrac.getModelRoom().getValueAt(i, 0).equals(split[1])){
							chatClientPrac.getModelRoom().removeRow(i);
						}
					}
				}else if(split[0].equals("/uin")){	//���ο� ���� �α���
						chatClientPrac.getModelAll().addRow(new String[]{split[1]});
						for (Map.Entry<String, NewRoomFrame> entry: chatClientPrac.getRoomInfoList().entrySet()) {
							entry.getValue().getModelAll().addRow(new String[]{split[1]});
						}

				}else if(split[0].equals("/uout")){	//�ٸ� ���� �α׾ƿ� ��
					for (int i = 0; i < chatClientPrac.getModelAll().getRowCount(); i++) {
						if(chatClientPrac.getModelAll().getValueAt(i, 0).equals(split[1])){
							chatClientPrac.getModelAll().removeRow(i);
							break;
						}
					}
					for (Map.Entry<String, NewRoomFrame> entry: chatClientPrac.getRoomInfoList().entrySet()) {
						for (int i = 0; i < entry.getValue().getModelAll().getRowCount(); i++) {
							if(entry.getValue().getModelAll().getValueAt(i, 0).equals(split[1])){
								entry.getValue().getModelAll().removeRow(i);
							}
						}
					}

				}else if(split[0].equals("/user")){	//�α����ϸ� ���� ���� ����Ʈ ����
					chatClientPrac.getModelAll().addRow(new String[]{split[1]});
				}else if(split[0].equals("/roomlist")){
					chatClientPrac.getModelRoom().addRow(new String[]{split[1],split[2]});
				}else if(split[0].equals("/roomuser")){	//��ȭ�� ���� �� ���� ���� ����Ʈ ����
					chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().addRow(new String[]{split[1]});
				}else if(split[0].equals("/roomuserO")){//��ȭ�� ���� �� ���� ������ ����
					chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelOwner().addRow(new String[]{split[1]});
				}else if(split[0].equals("/rin")){	//��ȭ�濡 ���ο� ���� ���� //�ش� ��ȭ�濡 ��Ŀ���� ���� ���� �˸�
					chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().addRow(new String[]{split[1]});
					if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
						chatClientPrac.getTrayIcon().displayMessage(split[split.length-1], split[1]+"���� �����ϼ̽��ϴ�.", MessageType.NONE);
					}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
						boolean focused = false;
						for (Map.Entry<String, NewRoomFrame> entry:chatClientPrac.getRoomInfoList().entrySet()) {
							if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
								focused = true;
								break;
							}
						}
						if(!focused){
							chatClientPrac.getTrayIcon().displayMessage(split[split.length-1], split[1]+"���� �����ϼ̽��ϴ�.", MessageType.NONE);
						}
					}
				}else if(split[0].equals("/rout")){	//��ȭ�濡 ���� ���� ����
					for (int i = 0; i < chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().getRowCount(); i++) {
						if(chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().getValueAt(i, 0).equals(split[1])){
							chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().removeRow(i);
							break;
						}
					}
					if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
						chatClientPrac.getTrayIcon().displayMessage(split[split.length-1], split[1]+"���� �����ϼ̽��ϴ�.", MessageType.NONE);
					}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
						boolean focused = false;
						for (Map.Entry<String, NewRoomFrame> entry:chatClientPrac.getRoomInfoList().entrySet()) {
							if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
								focused = true;
								break;
							}
						}
						if(!focused){
							chatClientPrac.getTrayIcon().displayMessage(split[split.length-1], split[1]+"���� �����ϼ̽��ϴ�.", MessageType.NONE);
						}
					}
				}else if(split[0].equals("/routOk")){
					chatClientPrac.getRoomInfoList().remove(split[1]);
				}else if(split[0].equals("/rchgOwner")){
					chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelOwner().removeRow(0);
					chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelOwner().addRow(new String[]{split[1]});
				}else if(split[0].equals("/routOwner")){	//������ ������ ������ ���ο� ������ ������ �ְ� ����	//������ ���� ������ ����� ������ ��
					//���ο� ���� ����
					pw.println("/newOwner "+chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().getValueAt(0, 0)+" /room "+split[split.length-1]);
					chatClientPrac.getRoomInfoList().remove(split[split.length-1]);
				}else if(split[split.length-1].equals("/room")){	//���� ä��
					ta.append(msg.substring(0,msg.length()-6)+"\n");
				}else if(split[split.length-2].equals("/room")){	//�� ä��
					chatClientPrac.getRoomInfoList().get(split[split.length-1]).getTa().append(msg.substring(0,msg.indexOf("/room")-1)+"\n");
					// �ش� �濡 ��Ŀ���� ���� ��쿡�� �˸�
					if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
						chatClientPrac.getTrayIcon().displayMessage(split[split.length-1],msg.substring(0,msg.indexOf("/room")-1) , MessageType.NONE);
					}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
						boolean focused = false;
						for (Map.Entry<String, NewRoomFrame> entry:chatClientPrac.getRoomInfoList().entrySet()) {
							if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
								focused = true;
								break;
							}
						}
						if(!focused){
							chatClientPrac.getTrayIcon().displayMessage(split[split.length-1],msg.substring(0,msg.indexOf("/room")-1) , MessageType.NONE);
						}
					}
				}
					
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
