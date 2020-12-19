import java.awt.AWTException;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
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
	
	HashMap<String, NewRoomFrame> roomInfoList;	//현재 접속해 있는 채팅방
	
	String[] headerRoom = {"번호","대화방 이름"};
	String[][] contentRoom = {};
	DefaultTableModel modelRoom;
	JTable tableRoom;
	JScrollPane pane_tableRoom;
	
	String[] headerAll = {"모든 접속자"};
	String[][] contentAll = {};
	DefaultTableModel modelAll;
	JTable tableAll;
	JScrollPane pane_tableAll;
	Vector<String> blockNameList = new Vector<>();
	
	TrayIcon trayIcon;
	public ChatClientPrac() {
		init();
		setClient();
	}
	public void setClient(){
		try {
			while(id.equals("")){
				id = JOptionPane.showInputDialog("아이디");
				if(id==null){
					int i = JOptionPane.showConfirmDialog(this, "종료하시겠습니까?", "", JOptionPane.OK_CANCEL_OPTION);
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
				JOptionPane.showMessageDialog(this, "이미 존재하는 아이디 입니다.");
				id = "";
				while(id.equals("")){
					id = JOptionPane.showInputDialog("아이디");
					if(id==null){
						int i = JOptionPane.showConfirmDialog(this, "종료하시겠습니까?", "", JOptionPane.OK_CANCEL_OPTION);
						if(i == JOptionPane.OK_OPTION){
							System.exit(0);
						}else{
							id = "";
						}
					}
				}
				pw.println(id);
			}
			
			
			setTitle(id+"님의 채팅창");
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
		
		newRoomBtn = new JButton("대화방 만들기");
		newRoomBtn.setBounds(0, 300, 150, 50);
		newRoomBtn.addActionListener(this);
		
		modelRoom = new DefaultTableModel(contentRoom,headerRoom){
			public boolean isCellEditable(int rowIndex, int colIndex){
				return false;
			}
		};
		tableRoom = new JTable(modelRoom);
		tableRoom.getTableHeader().setReorderingAllowed(false);
		tableRoom.getTableHeader().setResizingAllowed(false);
		tableRoom.getColumn("대화방 이름").setPreferredWidth(400);
		pane_tableRoom = new JScrollPane(tableRoom);
		pane_tableRoom.setBounds(0, 0, 500, 300);
		pnl.add(pane_tableRoom);
		try {
			tableRoom.setDefaultRenderer(Class.forName("java.lang.Object"), new MyRenderer(this));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		modelAll = new DefaultTableModel(contentAll,headerAll){
			public boolean isCellEditable(int rowIndex, int colIndex){
				return false;
			}
		};
		tableAll = new JTable(modelAll);
		tableAll.getTableHeader().setReorderingAllowed(false);
		tableAll.getTableHeader().setResizingAllowed(false);
		pane_tableAll = new JScrollPane(tableAll);
		pane_tableAll.setBounds(500, 0, 100, 480);
		pnl.add(pane_tableAll);
		
		tableAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableAll.setRowSelectionInterval(tableAll.rowAtPoint(e.getPoint()), tableAll.rowAtPoint(e.getPoint()));
			}
		});
		
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem item1 = new JMenuItem("귓속말 차단");
		JMenuItem item2 = new JMenuItem("귓속말 차단 해제");
		item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int okCancelChk = JOptionPane.showConfirmDialog(null, tableAll.getValueAt(tableAll.getSelectedRow(), 0)+"님의 귓속말을 차단하시겠습니까?", "", JOptionPane.OK_CANCEL_OPTION);
				if(okCancelChk==JOptionPane.OK_OPTION){
					blockNameList.add(tableAll.getValueAt(tableAll.getSelectedRow(), 0).toString());
					JOptionPane.showMessageDialog(null, "귓속말 차단되었습니다.");
				}
			}
		});
		
		item2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int okCancelChk = JOptionPane.showConfirmDialog(null, tableAll.getValueAt(tableAll.getSelectedRow(), 0)+"님의 귓속말 차단을 해제하시겠습니까?", "", JOptionPane.OK_CANCEL_OPTION);
				if(okCancelChk==JOptionPane.OK_OPTION){
					blockNameList.remove(tableAll.getValueAt(tableAll.getSelectedRow(), 0).toString());
					JOptionPane.showMessageDialog(null, "귓속말 차단 해제되었습니다.");
				}
			}
		});
		popupMenu.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				if(tableAll.getSelectedRow()!=-1){
					if(blockNameList.contains(tableAll.getValueAt(tableAll.getSelectedRow(), 0))){
						popupMenu.remove(item1);
						popupMenu.add(item2);
					}else{
						popupMenu.remove(item2);
						popupMenu.add(item1);
					}
				}else{
					popupMenu.setVisible(false);
				}
			}
			
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
			public void popupMenuCanceled(PopupMenuEvent e) {}
		});
		tableAll.setComponentPopupMenu(popupMenu);
		
		
		enterBtn = new JButton("방 입장");
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
	public boolean alreadyInChk(Object value){
		for(Map.Entry<String, NewRoomFrame> entry : roomInfoList.entrySet()){
			if(entry.getKey().equals(value)){
				return true; 
			}
		}
		return false;
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
		if(e.getSource()==newRoomBtn){	//새로운 방 만들기
			new ChatNewRoomDialog(this);
		}else if(e.getSource()== enterBtn){	//대화방 리스트에서 선택 후 입장 버튼을 누른 경우
			if(tableRoom.getSelectedRow()!=-1){
				boolean alreadyIn = false;
				for(Map.Entry<String, NewRoomFrame> entry : roomInfoList.entrySet()){
					if(tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).equals(entry.getKey())){
						alreadyIn = true;
					}
				}
				if(alreadyIn){
					JOptionPane.showMessageDialog(this, "이미 입장하신 방입니다.");
				}else{
					
					pw.println("/enterRoom "+tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).toString());
				}
			}
		}else{	//대기실에서 텍스트 필드의 메시지 전송시
			pw.println(tf.getText()+" /room");
			tf.selectAll();
			
		}
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
		newRoomFrame.setTitle("채팅방 : "+name+" ( 채팅명 : "+id+" ) - "+roomNum);
		for (int i = 0; i < modelAll.getRowCount(); i++) {
			roomInfoList.get(roomNum).getModelAll().addRow(new String[]{modelAll.getValueAt(i, 0).toString()});
		}
		modelRoomDataChanged();
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
	public void showAlreadyBannedRoom(){
		JOptionPane.showMessageDialog(this, "강퇴당하신 방에는 입장하실 수 없습니다.");
	}
	public Vector<String> getBlockNameList() {
		return blockNameList;
	}
	public void modelRoomDataChanged(){
		modelRoom.fireTableDataChanged();
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
				if(split[split.length-1].equals("/server")){	//서버에서 온 메시지는 모든 방에 뿌리기
					ta.append(msg.substring(0,msg.length()-8)+"\n");
					for(Map.Entry<String, NewRoomFrame> entry : chatClientPrac.getRoomInfoList().entrySet()){
						entry.getValue().getTa().append(msg.substring(0,msg.length()-8)+"\n");
					}
					if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
						chatClientPrac.getTrayIcon().displayMessage("Server", msg.substring(0,msg.length()-8), MessageType.NONE);
					}
				}else if(split[0].equals("/roomNum")){	//새로운 방을 생성하면 서버로부터 방번호를 받아옴
					chatClientPrac.getNewRoomFrame().setTitle("채팅방 : "+chatClientPrac.getNewRoomFrame().getRoomName()+" ( 채팅명 : "+chatClientPrac.getId()+" ) - "+split[1]);
					chatClientPrac.getRoomInfoList().put(split[1], chatClientPrac.getNewRoomFrame());
					chatClientPrac.getRoomInfoList().get(split[1]).setRoomNum(split[1]);
					for (int i = 0; i < chatClientPrac.getModelAll().getRowCount(); i++) {
						chatClientPrac.getRoomInfoList().get(split[1]).getModelAll().addRow(new String[]{chatClientPrac.getModelAll().getValueAt(i, 0).toString()});
					}
					chatClientPrac.modelRoomDataChanged();
				}else if(split[0].equals("/newRoom")){	//새로운 방이 생기면 대기실에 방 목록 변경
					chatClientPrac.getModelRoom().addRow(new String[]{split[2],split[1]});
				}else if(split[0].equals("/removeRoom")){	//방이 사라지면 대기실 방 목록 변경
					for (int i = 0; i < chatClientPrac.getModelRoom().getRowCount(); i++) {
						if(chatClientPrac.getModelRoom().getValueAt(i, 0).equals(split[1])){
							chatClientPrac.getModelRoom().removeRow(i);
						}
					}
				}else if(split[0].equals("/uin")){	//새로운 유저 로그인
						chatClientPrac.getModelAll().addRow(new String[]{split[1]});
						for (Map.Entry<String, NewRoomFrame> entry: chatClientPrac.getRoomInfoList().entrySet()) {
							entry.getValue().getModelAll().addRow(new String[]{split[1]});
						}

				}else if(split[0].equals("/uout")){	//다른 유저 로그아웃 시
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
					if(chatClientPrac.getBlockNameList().contains(split[1])){	//귓속말 차단한 아이디를 차단리스트에서 제외
						chatClientPrac.getBlockNameList().remove(split[1]);
					}
				}else if(split[0].equals("/user")){	//로그인하면 기존 유저 리스트 저장
					chatClientPrac.getModelAll().addRow(new String[]{split[1]});
				}else if(split[0].equals("/roomlist")){
					chatClientPrac.getModelRoom().addRow(new String[]{split[1],split[2]});
				}else if(split[0].equals("/enterOk")){
					chatClientPrac.enterRoom(split[1],split[3]);
				}else if(split[0].equals("/roomuser")){	//대화방 입장 시 기존 유저 리스트 저장
					chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().addRow(new String[]{split[1]});
				}else if(split[0].equals("/roomuserO")){//대화방 입장 시 방장 정보를 저장
					chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelOwner().addRow(new String[]{split[1]});
				}else if(split[0].equals("/rin")){	//대화방에 새로운 유저 입장 //해당 대화방에 포커스가 없을 때만 알림
					chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().addRow(new String[]{split[1]});
					if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
						chatClientPrac.getTrayIcon().displayMessage(split[split.length-1], split[1]+"님이 입장하셨습니다.", MessageType.NONE);
					}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
						boolean focused = false;
						for (Map.Entry<String, NewRoomFrame> entry:chatClientPrac.getRoomInfoList().entrySet()) {
							if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
								focused = true;
								break;
							}
						}
						if(!focused){
							chatClientPrac.getTrayIcon().displayMessage(split[split.length-1], split[1]+"님이 입장하셨습니다.", MessageType.NONE);
						}
					}
				}else if(split[0].equals("/rout")){	//대화방에 기존 유저 퇴장
					for (int i = 0; i < chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().getRowCount(); i++) {
						if(chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().getValueAt(i, 0).equals(split[1])){
							chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().removeRow(i);
							break;
						}
					}
					if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
						chatClientPrac.getTrayIcon().displayMessage(split[split.length-1], split[1]+"님이 퇴장하셨습니다.", MessageType.NONE);
					}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
						boolean focused = false;
						for (Map.Entry<String, NewRoomFrame> entry:chatClientPrac.getRoomInfoList().entrySet()) {
							if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
								focused = true;
								break;
							}
						}
						if(!focused){
							chatClientPrac.getTrayIcon().displayMessage(split[split.length-1], split[1]+"님이 퇴장하셨습니다.", MessageType.NONE);
						}
					}
				}else if(split[0].equals("/routOk")){
					chatClientPrac.getRoomInfoList().remove(split[1]);
					chatClientPrac.modelRoomDataChanged();
				}else if(split[0].equals("/rchgOwner")){
					chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelOwner().removeRow(0);
					chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelOwner().addRow(new String[]{split[1]});
				}else if(split[0].equals("/routOwner")){	//방장이 나갈때 방장이 새로운 방장을 선정해 주고 나감	//들어온지 가장 오래된 멤버가 방장이 됨
					//새로운 방장 선정
					pw.println("/newOwner "+chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().getValueAt(0, 0)+" /room "+split[split.length-1]);
					chatClientPrac.getRoomInfoList().remove(split[split.length-1]);
					chatClientPrac.modelRoomDataChanged();
				}else if(split[0].equals("/banned")){
					chatClientPrac.getRoomInfoList().get(split[3]).dispose();
					chatClientPrac.getRoomInfoList().remove(split[3]);
					chatClientPrac.modelRoomDataChanged();
					ta.append(split[3]+"번 채팅방에서 강퇴되셨습니다.");
					
					if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null||KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
						chatClientPrac.getTrayIcon().displayMessage("대기실", split[3]+"번 채팅방에서 강퇴되셨습니다.", MessageType.NONE);
					}
				}else if(split[0].equals("/ban")){
					for (int i = 0; i < chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().getRowCount(); i++) {
						if(chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().getValueAt(i, 0).equals(split[1])){
							chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().removeRow(i);
							break;
						}
					}
					if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
						chatClientPrac.getTrayIcon().displayMessage(split[split.length-1], split[1]+"님이 강퇴되셨습니다.", MessageType.NONE);
					}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
						boolean focused = false;
						for (Map.Entry<String, NewRoomFrame> entry:chatClientPrac.getRoomInfoList().entrySet()) {
							if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
								focused = true;
								break;
							}
						}
						if(!focused){
							chatClientPrac.getTrayIcon().displayMessage(split[split.length-1], split[1]+"님이 강퇴되셨습니다.", MessageType.NONE);
						}
					}
				}else if(split[0].equals("/roomBanned")){
					chatClientPrac.showAlreadyBannedRoom();
				}else if(split[split.length-1].equals("/room")){	//대기실 채팅
					if(split[0].equals("[귓속말]")){//귓속말 차단 여부 확인
						boolean blocked = false;
						for (int i = 0; i < chatClientPrac.getBlockNameList().size(); i++) {
							if(split[1].equals(chatClientPrac.getBlockNameList().get(i))){
								blocked = true;
								break;
							}
						}
						if(!blocked){
							ta.append(msg.substring(0,msg.length()-6)+"\n");
							
							if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null||KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
								chatClientPrac.getTrayIcon().displayMessage("대기실",msg.substring(0,msg.length()-6), MessageType.NONE);
							}
						}
					}else{
						ta.append(msg.substring(0,msg.length()-6)+"\n");
					}
				}else if(split[split.length-2].equals("/room")){	//방 채팅
					if(split[0].equals("[귓속말]")){
						boolean blocked = false;	//귓속말 차단 여부 확인
						for (int i = 0; i < chatClientPrac.getBlockNameList().size(); i++) {
							if(split[1].equals(chatClientPrac.getBlockNameList().get(i))){
								blocked = true;
								break;
							}
						}
						if(!blocked){
							chatClientPrac.getRoomInfoList().get(split[split.length-1]).getTa().append(msg.substring(0,msg.indexOf("/room")-1)+"\n");
							// 해당 방에 포커스가 없을 경우에만 알림
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
					}else{
						chatClientPrac.getRoomInfoList().get(split[split.length-1]).getTa().append(msg.substring(0,msg.indexOf("/room")-1)+"\n");
						// 해당 방에 포커스가 없을 경우에만 알림
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
					
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
