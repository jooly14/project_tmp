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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ChatClientPrac extends JFrame implements ActionListener{
	JPanel pnl;
	JTextArea ta;
	JTextField tf;
	
	NewRoomFrame newRoomFrame;
	
	JButton newRoomBtn;
	JButton enterBtn;
	JButton configurationBtn;
	
	PrintWriter pw;
	BufferedReader br;
	Socket socket;
	
	String id = "";
	
	HashMap<String, NewRoomFrame> roomInfoList;	//���� ������ �ִ� ä�ù�
	
	String[] headerRoom = {"��ȣ","��ȭ�� �̸�","��й�"};
	String[][] contentRoom = {};
	DefaultTableModel modelRoom;
	JTable tableRoom;
	JScrollPane pane_tableRoom;
	
	String[] headerAll = {"��� ������"};
	String[][] contentAll = {};
	DefaultTableModel modelAll;
	JTable tableAll;
	JScrollPane pane_tableAll;
	Vector<String> blockNameList = new Vector<>();
	
	TrayIcon trayIcon;
	ChatRoomDetailDialog chatRoomDetailDialog;
	ChatInviteRoomDetailDialog chatInviteRoomDetailDialog;
	
	boolean msgAlarm = true;
	Vector<String> bannedRoomList = new Vector<>();
	
	JTextField tfSearch;
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		roomInfoList = new HashMap<>();
		
		pnl = new JPanel();
		pnl.setLayout(null);
		
		tf = new JTextField();
		tf.addActionListener(this);
		ta =  new JTextArea();
		JScrollPane pane = new JScrollPane(ta);
		pnl.add(pane);
		pnl.add(tf);
		
		newRoomBtn = new JButton("��ȭ�� �����");
		newRoomBtn.addActionListener(this);
		
		modelRoom = new DefaultTableModel(contentRoom,headerRoom){
			public boolean isCellEditable(int rowIndex, int colIndex){
				return false;
			}
		};
		tableRoom = new JTable(modelRoom);
		tableRoom.getTableHeader().setReorderingAllowed(false);
		tableRoom.getTableHeader().setResizingAllowed(false);
		tableRoom.getColumn("��ȭ�� �̸�").setPreferredWidth(400);
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelRoom);
		tableRoom.setRowSorter(sorter);
		tfSearch = new JTextField();
		add(tfSearch);
		tfSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tfSearch.getText().length()==0){
					sorter.setRowFilter(null);
				}else{
					sorter.setRowFilter(RowFilter.regexFilter(tfSearch.getText()));
				}
			}
		});
		
		pane_tableRoom = new JScrollPane(tableRoom);
		pnl.add(pane_tableRoom);
		try {
			tableRoom.setDefaultRenderer(Class.forName("java.lang.Object"), new MyRenderer(this));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tableRoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					if(tableRoom.getSelectedRow()!=-1){
						boolean alreadyIn = false;
						for(Map.Entry<String, NewRoomFrame> entry : roomInfoList.entrySet()){
							if(tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).equals(entry.getKey())){
								alreadyIn = true;
							}
						}
						if(alreadyIn){
							JOptionPane.showMessageDialog(null, "�̹� �����Ͻ� ���Դϴ�.");
						}else{
							pw.println("/beforeEnterRoom "+tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).toString());
						}
					}
				}
			}
		});
		
		modelAll = new DefaultTableModel(contentAll,headerAll){
			public boolean isCellEditable(int rowIndex, int colIndex){
				return false;
			}
		};
		tableAll = new JTable(modelAll);
		tableAll.getTableHeader().setReorderingAllowed(false);
		tableAll.getTableHeader().setResizingAllowed(false);
		tableAll.setRowSorter(new TableRowSorter<DefaultTableModel>(modelAll));
		pane_tableAll = new JScrollPane(tableAll);
		pnl.add(pane_tableAll);
		try {
			tableAll.setDefaultRenderer(Class.forName("java.lang.Object"), new BlockRenderer(this));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem item1 = new JMenuItem("�ӼӸ� ����");
		JMenuItem item2 = new JMenuItem("�ӼӸ� ���� ����");
		item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int okCancelChk = JOptionPane.showConfirmDialog(null, tableAll.getValueAt(tableAll.getSelectedRow(), 0)+"���� �ӼӸ��� �����Ͻðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
				if(okCancelChk==JOptionPane.OK_OPTION){
					blockNameList.add(tableAll.getValueAt(tableAll.getSelectedRow(), 0).toString());
					JOptionPane.showMessageDialog(null, "�ӼӸ� ���ܵǾ����ϴ�.");
					modelAllDataChanged();
					for (Map.Entry<String, NewRoomFrame> entry: roomInfoList.entrySet()) {
						entry.getValue().modelAllDataChanged();
						entry.getValue().modelRoomDataChanged();
					}
				}
			}
		});
		
		item2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int okCancelChk = JOptionPane.showConfirmDialog(null, tableAll.getValueAt(tableAll.getSelectedRow(), 0)+"���� �ӼӸ� ������ �����Ͻðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
				if(okCancelChk==JOptionPane.OK_OPTION){
					blockNameList.remove(tableAll.getValueAt(tableAll.getSelectedRow(), 0).toString());
					JOptionPane.showMessageDialog(null, "�ӼӸ� ���� �����Ǿ����ϴ�.");
					modelAllDataChanged();
					for (Map.Entry<String, NewRoomFrame> entry: roomInfoList.entrySet()) {
						entry.getValue().modelAllDataChanged();
						entry.getValue().modelRoomDataChanged();
					}
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
		
		tableAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableAll.setRowSelectionInterval(tableAll.rowAtPoint(e.getPoint()), tableAll.rowAtPoint(e.getPoint()));
				if(e.getButton()==1){
					popupMenu.show(tableAll, e.getX(), e.getY());
				}
			}
		});
		
		enterBtn = new JButton("�� ����");
		enterBtn.addActionListener(this);
		
		configurationBtn = new JButton("ȯ�漳��");
		configurationBtn.addActionListener(this);
		
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
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int okChk = JOptionPane.NO_OPTION;
				if(roomInfoList.size()==0&&ta.getText().length()!=0){
					okChk = JOptionPane.showConfirmDialog(null, "���� ��ȭ ������ �����Ͻðڽ��ϱ�?");
				}else if(ta.getText().length()!=0){
					okChk = JOptionPane.showConfirmDialog(null, "<html>���� ��ȭ ������ �����Ͻðڽ��ϱ�?<br>ä�ù��� ��ȭ������ ������� �ʽ��ϴ�.</html>");
				}
				if(okChk==JOptionPane.OK_OPTION){
					FileWriter fwExit = null;
					PrintWriter pwExit = null;
					
					JFileChooser filechooser = new JFileChooser();
					FileNameExtensionFilter efilter = new FileNameExtensionFilter("�ؽ�Ʈ ����", "txt");
					filechooser.setFileFilter(efilter);
					filechooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
					filechooser.setSelectedFile(new File("chat_waitintroom_"+new SimpleDateFormat("yyyyMMddhhmmss").format(System.currentTimeMillis())+".txt"));
					filechooser.setMultiSelectionEnabled(false);
					int option = filechooser.showSaveDialog(null);
					
					if(option==JFileChooser.APPROVE_OPTION){
						try {
							fwExit = new FileWriter(filechooser.getSelectedFile(),false);
							pwExit = new PrintWriter(fwExit);
							pwExit.print(ta.getText());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}finally {
							try {
								if(fwExit!=null){
									fwExit.close();
								}
								if(pwExit!=null){
									pwExit.close();
								}
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						System.exit(0);
					}
				}else if(okChk==JOptionPane.NO_OPTION){
					System.exit(0);
				}
			}
		});
		
		setSize(620,620);
		pane_tableRoom.setBounds(0, 50, 500, 300);
		newRoomBtn.setBounds(0, 350, 150, 50);
		enterBtn.setBounds(150, 350, 150, 50);
		configurationBtn.setBounds(350, 300, 150, 50);
		pane.setBounds(0, 400, 500, 150);
		tf.setBounds(0, 550, 500, 30);
		JLabel lblTitle = new JLabel("ä�ù�");
		lblTitle.setBounds(0, 0, 100, 50);
		tfSearch.setBounds(300, 20, 200, 30);
		tfSearch.setHorizontalAlignment(JTextField.RIGHT);
		pane_tableAll.setBounds(500, 0, 100, 480);
		pnl.add(lblTitle);
		pnl.add(enterBtn);
		pnl.add(newRoomBtn);
		pnl.add(configurationBtn);
		add(pnl);
		setVisible(true);
		
	}
	public boolean alreadyInChk(Object value){
		if(roomInfoList.containsKey(value)){
			return true; 
		}else{
			return false;
		}
	}
	public boolean blockedChk(Object value){
		if(blockNameList.contains(value)){
			return true; 
		}
		return false;
	}
	public boolean bannedRoomChk(Object value){
		if(bannedRoomList.contains(value)){
			return true;
		}else{
			return false;
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
					pw.println("/beforeEnterRoom "+tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).toString());
				}
			}
		}else if(e.getSource() == configurationBtn){
			new WaitingRoomConfigDialog(this);
		}else{	//���ǿ��� �ؽ�Ʈ �ʵ��� �޽��� ���۽�
			pw.println(tf.getText()+" /room");
			tf.selectAll();
			
		}
	}
	public void createRoom(String name){
		newRoomFrame = new NewRoomFrame(this,name);
		newRoomFrame.getModelOwner().addRow(new String[]{id});
		pw.println("/createRoom "+name+" "+id+" /room");
	}
	public void createSecretRoom(String name,char[] password){
		newRoomFrame = new NewRoomFrame(this,name);
		newRoomFrame.getModelOwner().addRow(new String[]{id});
		pw.println("/createSecretRoom "+name+" "+id+" "+String.copyValueOf(password)+" /room");
	}
	public void sendMsgEnterRoom(String roomNum){
		pw.println("/enterRoom "+roomNum);
	}
	public void sendMsgEnterSecretRoom(String roomNum, char[] password){
		pw.println("/enterSecretRoom "+roomNum +" "+String.copyValueOf(password));
	}
	public void enterRoom(String name,String roomNum){
		newRoomFrame = new NewRoomFrame(this,name);
		newRoomFrame.setRoomNum(roomNum);
		roomInfoList.put(roomNum, newRoomFrame);
		newRoomFrame.setTitle("ä�ù� : "+name+" ( ä�ø� : "+id+" ) - "+roomNum);
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
		JOptionPane.showMessageDialog(this, "������Ͻ� �濡�� �����Ͻ� �� �����ϴ�.");
	}
	public Vector<String> getBlockNameList() {
		return blockNameList;
	}
	public void modelRoomDataChanged(){
		modelRoom.fireTableDataChanged();
	}
	public void modelAllDataChanged(){
		modelAll.fireTableDataChanged();
	}
	public void showInviteAccept(String[] split){
		JOptionPane.showConfirmDialog(this, split[1]+"���� "+ split[3]+"�� ��ȭ�濡 �ʴ��ϼ̽��ϴ�.", "", JOptionPane.OK_CANCEL_OPTION);
	}
	public void createBeforeEnterRoomDetail(String[] split){
		chatRoomDetailDialog = new ChatRoomDetailDialog(this,split);
	}
	public void wrongPassword(){
		JOptionPane.showMessageDialog(this, "��й�ȣ�� �߸� �Է��ϼ̽��ϴ�");
		chatRoomDetailDialog = new ChatRoomDetailDialog(this, chatRoomDetailDialog.getSplit());
	}
	public void correctPassword(){
		chatRoomDetailDialog.correctPassword();
	}
	public void inviteNo(String[] split){
		JOptionPane.showMessageDialog(this,"[["+split[3]+"]"+split[1]+"] �̹� �����Ͻ� ���Դϴ�");
	}
	public void createBeforeInviteRoomDetail(String[] split){
		chatInviteRoomDetailDialog = new ChatInviteRoomDetailDialog(this, split);
	}
	public void sendMsgRejectInvite(String roomNum, String inviteFrom){
		pw.println("/rejectInvite "+inviteFrom+" /room "+roomNum);
	}
	public void chgId(String newId){
		for(Map.Entry<String, NewRoomFrame> entry : roomInfoList.entrySet()){
			entry.getValue().setTitle(entry.getValue().getTitle().substring(0,entry.getValue().getTitle().lastIndexOf(id+" ) - "))+newId+" ) - "+entry.getKey());
		}
		pw.println("/chgId "+id+" "+newId+" /room");
		id = newId;
		setTitle(id+"���� ä��â");
	}
	public void setMsgAlarm(boolean chk){
		msgAlarm = chk;
	}
	public boolean isMsgAlarm() {
		return msgAlarm;
	}
	public void addBannedRoomList(String roomNum){
		bannedRoomList.add(roomNum);
	}
	public void removeBannedRoomList(String roomNum){
		bannedRoomList.remove(roomNum);
	}
	public void receiveFileChk(String[] split){
		roomInfoList.get(split[split.length-1]).receiveFileChk(split);
	}
	public void receiveFile(String[] split){
		String l = "";
		for (int i = 1; i < split.length; i++) {
			l += split[i]+" ";
		}
		pw.println("/receiveFile "+l);
	}
	public void completeSend(String[] split){
		roomInfoList.get(split[split.length-1]).completeSend(split);
	}
	public void completeReceive(String[] split){
		roomInfoList.get(split[split.length-1]).completeReceive(split);
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
					chatClientPrac.getNewRoomFrame().setTitle("ä�ù� : "+chatClientPrac.getNewRoomFrame().getRoomName()+" ( ä�ø� : "+chatClientPrac.getId()+" ) - "+split[1]);
					chatClientPrac.getRoomInfoList().put(split[1], chatClientPrac.getNewRoomFrame());
					chatClientPrac.getRoomInfoList().get(split[1]).setRoomNum(split[1]);
					for (int i = 0; i < chatClientPrac.getModelAll().getRowCount(); i++) {
						chatClientPrac.getRoomInfoList().get(split[1]).getModelAll().addRow(new String[]{chatClientPrac.getModelAll().getValueAt(i, 0).toString()});
					}
					chatClientPrac.modelRoomDataChanged();
				}else if(split[0].equals("/newRoom")){	//���ο� ���� ����� ���ǿ� �� ��� ����
					chatClientPrac.getModelRoom().addRow(new String[]{split[2],split[1],"NO"});
				}else if(split[0].equals("/newSecretRoom")){
					chatClientPrac.getModelRoom().addRow(new String[]{split[2],split[1],"YES"});
				}else if(split[0].equals("/removeRoom")){	//���� ������� ���� �� ��� ����
					for (int i = 0; i < chatClientPrac.getModelRoom().getRowCount(); i++) {
						if(chatClientPrac.getModelRoom().getValueAt(i, 0).equals(split[1])){
							chatClientPrac.getModelRoom().removeRow(i);
							chatClientPrac.removeBannedRoomList(split[1]);
							break;
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
					if(chatClientPrac.getBlockNameList().contains(split[1])){	//�ӼӸ� ������ ���̵� ���ܸ���Ʈ���� ����
						chatClientPrac.getBlockNameList().remove(split[1]);
					}
				}else if(split[0].equals("/user")){	//�α����ϸ� ���� ���� ����Ʈ ����
					chatClientPrac.getModelAll().addRow(new String[]{split[1]});
				}else if(split[0].equals("/roomlist")){
					chatClientPrac.getModelRoom().addRow(new String[]{split[1],split[2],split[3]});
				}else if(split[0].equals("/roomDetail")){
					chatClientPrac.createBeforeEnterRoomDetail(split);
				}else if(split[0].equals("/correctPassword")){
					chatClientPrac.correctPassword();
				}else if(split[0].equals("/inviteRoomDetail")){
					chatClientPrac.createBeforeInviteRoomDetail(split);
				}else if(split[0].equals("/inviteNo")){
					chatClientPrac.inviteNo(split);
				}else if(split[0].equals("/enterOk")){
					chatClientPrac.enterRoom(split[1],split[3]);
				}else if(split[0].equals("/wrongPassword")){
					chatClientPrac.wrongPassword();
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
					if(chatClientPrac.isMsgAlarm()){
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
						
					}
				}else if(split[0].equals("/routOk")){
					chatClientPrac.getRoomInfoList().remove(split[1]);
					chatClientPrac.modelRoomDataChanged();
				}else if(split[0].equals("/rchgOwner")){
					chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelOwner().removeRow(0);
					chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelOwner().addRow(new String[]{split[1]});
				}else if(split[0].equals("/routOwner")){	//������ ������ ������ ���ο� ������ ������ �ְ� ����	//������ ���� ������ ����� ������ ��
					//���ο� ���� ����
					pw.println("/newOwner "+chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().getValueAt(0, 0)+" /room "+split[split.length-1]);
					chatClientPrac.getRoomInfoList().remove(split[split.length-1]);
					chatClientPrac.modelRoomDataChanged();
				}else if(split[0].equals("/banned")){
					chatClientPrac.getRoomInfoList().get(split[3]).dispose();
					chatClientPrac.getRoomInfoList().remove(split[3]);
					chatClientPrac.addBannedRoomList(split[3]);
					chatClientPrac.modelRoomDataChanged();
					ta.append(split[3]+"�� ä�ù濡�� ����Ǽ̽��ϴ�.");
					if(chatClientPrac.isMsgAlarm()){
						if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null||KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
							chatClientPrac.getTrayIcon().displayMessage("����", split[3]+"�� ä�ù濡�� ����Ǽ̽��ϴ�.", MessageType.NONE);
						}
					}
				}else if(split[0].equals("/ban")){
					for (int i = 0; i < chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().getRowCount(); i++) {
						if(chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().getValueAt(i, 0).equals(split[1])){
							chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().removeRow(i);
							break;
						}
					}
					if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
						chatClientPrac.getTrayIcon().displayMessage(split[split.length-1], split[1]+"���� ����Ǽ̽��ϴ�.", MessageType.NONE);
					}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
						boolean focused = false;
						for (Map.Entry<String, NewRoomFrame> entry:chatClientPrac.getRoomInfoList().entrySet()) {
							if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
								focused = true;
								break;
							}
						}
						if(!focused){
							chatClientPrac.getTrayIcon().displayMessage(split[split.length-1], split[1]+"���� ����Ǽ̽��ϴ�.", MessageType.NONE);
						}
					}
				}else if(split[0].equals("/roomBanned")){
					chatClientPrac.showAlreadyBannedRoom();
				}else if(split[0].equals("/invite")){
					chatClientPrac.showInviteAccept(split);
				}else if(split[0].equals("/chgId")){
					//�ٸ� ��� ���̵� �����
					//blocklist ����
					//���̵� ����Ʈ ���� ����
					//���� ������ ���� ó���Ǿ���
					if(chatClientPrac.getBlockNameList().contains(split[1])){
						chatClientPrac.getBlockNameList().add(split[2]);
						chatClientPrac.getBlockNameList().remove(split[1]);
					}
					for (int i = 0; i < chatClientPrac.getModelAll().getRowCount(); i++) {
						if(chatClientPrac.getModelAll().getValueAt(i, 0).equals(split[1])){
							chatClientPrac.getModelAll().setValueAt(split[2], i, 0);
							ta.append(split[1]+"���� ���̵� "+split[2]+"�� ����Ǿ����ϴ�.\n");
							if(chatClientPrac.isMsgAlarm()){
								if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null||KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
									chatClientPrac.getTrayIcon().displayMessage("���̵� ����",split[1]+"���� ���̵� "+split[2]+"�� ����Ǿ����ϴ�.", MessageType.NONE);
								}
							}
							break;
						}
					}
					for(Map.Entry<String, NewRoomFrame> entry : chatClientPrac.getRoomInfoList().entrySet()){
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
					chatClientPrac.receiveFileChk(split.clone());
				}else if(split[0].equals("/receiveFile")){	//���� ���� ����
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(chatClientPrac.getId().equals(split[1])){
						new ReceiveFileClient(chatClientPrac, split.clone()).start();
					}else{
						new SendFileClient(chatClientPrac, split.clone()).start();
					}
					
				}else if(split[split.length-1].equals("/room")){	//���� ä��
					if(split[0].equals("[�ӼӸ�]")){//�ӼӸ� ���� ���� Ȯ��
						boolean blocked = false;
						for (int i = 0; i < chatClientPrac.getBlockNameList().size(); i++) {
							if(split[1].equals(chatClientPrac.getBlockNameList().get(i))){
								blocked = true;
								break;
							}
						}
						if(!blocked){
							ta.append(msg.substring(0,msg.indexOf("/room")-1)+"\n");
							if(chatClientPrac.isMsgAlarm()){
								if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null||KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
									chatClientPrac.getTrayIcon().displayMessage("����",msg.substring(0,msg.indexOf("/room")-1), MessageType.NONE);
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
						if(split[0].equals(chatClientPrac.getId())){	//���� ���� �޽���
							ta.append(split[0]+"(��) "+msg.substring(msg.indexOf(split[1]),msg.length()-6)+"\n");
						}else{
							ta.append(msg.substring(0,msg.length()-6)+"\n");
						}
					}
				}else if(split[split.length-2].equals("/room")){	//�� ä��
					if(split[0].equals("[�ӼӸ�]")){
						boolean blocked = false;	//�ӼӸ� ���� ���� Ȯ��
						for (int i = 0; i < chatClientPrac.getBlockNameList().size(); i++) {
							if(split[1].equals(chatClientPrac.getBlockNameList().get(i))){
								blocked = true;
								break;
							}
						}
						if(!blocked){
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
					}else{
						if(split[0].equals(chatClientPrac.getId())){	//���� ���� �޽����� ��쿡�� (��) ��� ����
							chatClientPrac.getRoomInfoList().get(split[split.length-1]).getTa().append(split[0]+"(��) "+msg.substring(msg.indexOf(split[1]),msg.indexOf("/room")-1)+"\n");
							// �ش� �濡 ��Ŀ���� ���� ��쿡�� �˸�
							if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()==null){
								chatClientPrac.getTrayIcon().displayMessage(split[split.length-1],split[0]+"(��) "+msg.substring(msg.indexOf(split[1]),msg.indexOf("/room")-1) , MessageType.NONE);
							}else if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() instanceof NewRoomFrame){
								boolean focused = false;
								for (Map.Entry<String, NewRoomFrame> entry:chatClientPrac.getRoomInfoList().entrySet()) {
									if(((NewRoomFrame)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow()).getRoomName().equals(entry.getValue().getRoomName())){
										focused = true;
										break;
									}
								}
								if(!focused){
									chatClientPrac.getTrayIcon().displayMessage(split[split.length-1],split[0]+"(��) "+msg.substring(msg.indexOf(split[1]),msg.indexOf("/room")-1) , MessageType.NONE);
								}
							}
						}else{
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
				}
					
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class SendFileClient extends Thread{
	ChatClientPrac chatClientPrac;
	String[] split;
	public SendFileClient(ChatClientPrac chatClientPrac,String[] split) {
		this.chatClientPrac = chatClientPrac;
		this.split =split;
	}
	@Override
	public void run() {
		BufferedInputStream bis =null;
		FileInputStream fis = null;
		BufferedOutputStream bos = null;
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 4444);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bos = new BufferedOutputStream(socket.getOutputStream());
			fis = new FileInputStream(split[2]);
			bis = new BufferedInputStream(fis);
			
			int data;
			while ((data = bis.read())!=-1) {
				bos.write(data);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(bos!=null){
					bos.close();
				}
				if(bis!=null){
					bis.close();
				}
				if(fis!= null){
					fis.close();
				}
				if(socket!=null){
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		chatClientPrac.completeSend(split.clone());

	}
}
class ReceiveFileClient extends Thread{
	ChatClientPrac chatClientPrac;
	String[] split;
	public ReceiveFileClient(ChatClientPrac chatClientPrac,String[] split) {
		this.chatClientPrac = chatClientPrac;
		this.split =split;

	}
	@Override
	public void run() {
		BufferedInputStream bis =null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 4444);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(new File(split[2]).exists()){
				System.out.println(split[2]);
				split[2] = split[2].split("[.]")[0]+ new SimpleDateFormat("yyyyMMddhhmmss").format(System.currentTimeMillis())+"."+split[2].split("[.]")[1];
			}
			bis = new BufferedInputStream(socket.getInputStream());
			fos = new FileOutputStream(split[2]);
			bos = new BufferedOutputStream(fos);
			
			int data;
			while ((data = bis.read())!=-1) {
				bos.write(data);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(bos!=null){
					bos.close();
				}
				if(bis!=null){
					bis.close();
				}
				if(fos!= null){
					fos.close();
				}
				if(socket!=null){
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		chatClientPrac.completeReceive(split.clone());

	}
}
