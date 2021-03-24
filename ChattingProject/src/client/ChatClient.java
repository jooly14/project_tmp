package client;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
import javax.swing.UIManager;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ChatClient extends JFrame implements ActionListener{
	public static void main(String[] args) {
		new ChatClient();
	}
	
	JPanel pnl;
	JTextArea ta;
	JTextField tf;
	
	NewRoomFrame newRoomFrame;
	
	JButton newRoomBtn;	//방 생성
	JButton enterBtn;	//방 입장
	JButton configurationBtn;	//설정버튼
	
	PrintWriter pw;
	BufferedReader br;
	Socket socket;
	
	String id = "";
	
	HashMap<String, NewRoomFrame> roomInfoList;	//현재 접속해 있는 채팅방
	
	String[] headerRoom = {"번호","대화방 이름","비밀방"};	//채팅방의 리스트
	String[][] contentRoom = {};
	DefaultTableModel modelRoom;
	JTable tableRoom;
	JScrollPane pane_tableRoom;
	
	String[] headerAll = {"모든 접속자"};				//모든 접속자 리스트
	String[][] contentAll = {};
	DefaultTableModel modelAll;
	JTable tableAll;
	JScrollPane pane_tableAll;
	
	Vector<String> blockNameList = new Vector<>();	//귓속말 차단된 아이디 리스트를 저장
	
	TrayIcon trayIcon;								//알림을 위해서 사용
	
	ChatRoomDetailDialog chatRoomDetailDialog;		//대화방 입장 전 대화방에 대한 정보를 확인
	ChatInviteRoomDetailDialog chatInviteRoomDetailDialog;
	
	ChatClientThread chatClientThread;
	
	boolean msgAlarm = true;						//대기실 채팅 알림 여부
	Vector<String> bannedRoomList = new Vector<>();	//강퇴된 대화방 리스트를 저장
	
	JTextField tfSearch;							//대화방을 검색하기 위한 텍스트필드
	
	JButton btnSave;
	//초기화
	public ChatClient() {
		init();
		setClient();
	}
	String idAddress;
	//서버와의 연결
	public void setClient(){
		idAddress = JOptionPane.showInputDialog("접속할 IP주소를 입력하세요.","127.0.0.1");
		try {
			while(id.equals("")){	//아이디를 입력하지 않은 경우 거절
				id = JOptionPane.showInputDialog("아이디");
				if(id==null){		//닫기를 누른 경우에는 null
					int i = JOptionPane.showConfirmDialog(this, "종료하시겠습니까?", "", JOptionPane.OK_CANCEL_OPTION);
					if(i == JOptionPane.OK_OPTION){
						System.exit(0);
					}else{
						id = "";
					}
				}else if(id.indexOf(" ")!=-1){
					JOptionPane.showMessageDialog(this, "아이디에는 공백을 포함할 수 없습니다.");
					id = "";
				}else if(id.length()>20){
					JOptionPane.showMessageDialog(this, "아이디는 20자를 초과할 수 없습니다.");
					id = "";
				}
			}
			socket = new Socket(idAddress,5555);
			pw = new PrintWriter(socket.getOutputStream(),true);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String strExist = null;
			pw.println(id);
			while(true){
				if((strExist = br.readLine()).equals("/exist")){
					JOptionPane.showMessageDialog(this, "이미 존재하는 아이디 입니다.");
					id = "";
					while(id.equals("")){
						id = JOptionPane.showInputDialog("아이디");
						if(id==null){		//닫기를 누른 경우에는 null
							int i = JOptionPane.showConfirmDialog(this, "종료하시겠습니까?", "", JOptionPane.OK_CANCEL_OPTION);
							if(i == JOptionPane.OK_OPTION){
								System.exit(0);
							}else{
								id = "";
							}
						}else if(id.indexOf(" ")!=-1){
							JOptionPane.showMessageDialog(this, "아이디에는 공백을 포함할 수 없습니다.");
							id = "";
						}else if(id.indexOf("/")!=-1){
							JOptionPane.showMessageDialog(this, "아이디에는 /를 포함할 수 없습니다.");
							id = "";
						}else if(id.length()>20){
							JOptionPane.showMessageDialog(this, "아이디는 20자를 초과할 수 없습니다.");
							id = "";
						}
					}
					pw.println(id);
					
				}else if(strExist.equals("/prohibit")){
					JOptionPane.showMessageDialog(this, "비속어를 포함한 아이디는 사용할 수 없습니다.");
					id = "";
					while(id.equals("")){
						id = JOptionPane.showInputDialog("아이디");
						if(id==null){		//닫기를 누른 경우에는 null
							int i = JOptionPane.showConfirmDialog(this, "종료하시겠습니까?", "", JOptionPane.OK_CANCEL_OPTION);
							if(i == JOptionPane.OK_OPTION){
								System.exit(0);
							}else{
								id = "";
							}
						}else if(id.indexOf(" ")!=-1){
							JOptionPane.showMessageDialog(this, "아이디에는 공백을 포함할 수 없습니다.");
							id = "";
						}else if(id.indexOf("/")!=-1){
							JOptionPane.showMessageDialog(this, "아이디에는 /를 포함할 수 없습니다.");
							id = "";
						}else if(id.length()>20){
							JOptionPane.showMessageDialog(this, "아이디는 20자를 초과할 수 없습니다.");
							id = "";
						}
					}
					pw.println(id);
				}else{
					break;
				}
			}
			
			setTitle(id+"님의 채팅창");
			chatClientThread = new ChatClientThread(id, socket, ta, modelAll, this ,pw ,br);
			chatClientThread.start();
		} catch (ConnectException e) {
			JOptionPane.showMessageDialog(this, "서버가 작동하지 않습니다.");
			System.exit(0);
		}	catch (SocketException e) {
			JOptionPane.showMessageDialog(this, "잘못된 IP주소 입니다.");
			System.exit(0);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init(){
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		roomInfoList = new HashMap<>();
		
		UIManager UI=new UIManager();
		 UI.put("OptionPane.background",new ColorUIResource(255,255,255));
		 UI.put("Panel.background",new ColorUIResource(255,255,255));
		 
		pnl = new JPanel();
		pnl.setLayout(null);
		
		tf = new JTextField();
		tf.addActionListener(this);
		ta =  new JTextArea();
		ta.setEditable(false);
		JScrollPane pane = new JScrollPane(ta);
		pnl.add(pane);
		pnl.add(tf);
		
		newRoomBtn = new JButton("대화방 생성");
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
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelRoom);
		tableRoom.setRowSorter(sorter);
		//채팅방 검색기능
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
		//대화방 중에 입장한 방이나 강퇴 당한 방의 색을 변경
		try {
			tableRoom.setDefaultRenderer(Class.forName("java.lang.Object"), new MyRenderer(this));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//대화방 테이블을 더블클릭한 경우 입장버튼을 누른 것과 같은 이벤트
		tableRoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					if(tableRoom.getSelectedRow()!=-1){
						boolean alreadyIn = false;
						for(Map.Entry<String, NewRoomFrame> entry : roomInfoList.entrySet()){	//이미 들어간 방의 경우 다시 들어갈 수 없다.
							if(tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).equals(entry.getKey())){
								alreadyIn = true;
							}
						}
						if(alreadyIn){
							JOptionPane.showMessageDialog(null, "이미 입장하신 방입니다.");
						}else{
							pw.println("/beforeEnterRoom "+tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).toString());
						}
					}
				}
			}
		});
		
		//전체 사용자 리스트
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
		//귓속말 차단된 경우 테이블 행 색 변경
		try {
			tableAll.setDefaultRenderer(Class.forName("java.lang.Object"), new BlockRenderer(this));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//사용자 리스트 테이블에 팝업메뉴 추가
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem item1 = new JMenuItem("귓속말 차단");
		JMenuItem item2 = new JMenuItem("귓속말 차단 해제");
		JMenuItem item3 = new JMenuItem("귓속말 보내기");
		item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int okCancelChk = JOptionPane.showConfirmDialog(null, tableAll.getValueAt(tableAll.getSelectedRow(), 0)+"님의 귓속말을 차단하시겠습니까?", "", JOptionPane.OK_CANCEL_OPTION);
				if(okCancelChk==JOptionPane.OK_OPTION){
					blockNameList.add(tableAll.getValueAt(tableAll.getSelectedRow(), 0).toString());	//귓속말 차단리스트에 추가
					JOptionPane.showMessageDialog(null, "귓속말 차단되었습니다.");
					modelAllDataChanged();																//차단된 사용자는 색깔 변경
					for (Map.Entry<String, NewRoomFrame> entry: roomInfoList.entrySet()) {				//각 방에 사용자 리스트에도 색깔 변경
						entry.getValue().modelAllDataChanged();
						entry.getValue().modelRoomDataChanged();
					}
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
					modelAllDataChanged();
					for (Map.Entry<String, NewRoomFrame> entry: roomInfoList.entrySet()) {
						entry.getValue().modelAllDataChanged();
						entry.getValue().modelRoomDataChanged();
					}
				}
			}
		});
		item3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText("/w "+tableAll.getValueAt(tableAll.getSelectedRow(), 0)+" ");
				tf.requestFocus();
			}
		});
		popupMenu.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				if(tableAll.getSelectedRow()!=-1){
					if(blockNameList.contains(tableAll.getValueAt(tableAll.getSelectedRow(), 0))){	//귓속말 차단리스트에 추가된 사용자의 경우 차단해제 버튼 활성화
						popupMenu.remove(item1);
						popupMenu.remove(item3);
						popupMenu.add(item2);
					}else{
						popupMenu.remove(item2);
						popupMenu.add(item3);
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
		
		//사용자 테이블 클릭시 팝업메뉴 나타나도록 마우스리스너 추가
		tableAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableAll.setRowSelectionInterval(tableAll.rowAtPoint(e.getPoint()), tableAll.rowAtPoint(e.getPoint()));
				if(e.getButton()==1){
					popupMenu.show(tableAll, e.getX(), e.getY());
				}
			}
		});
		
		enterBtn = new JButton("방 입장");
		enterBtn.addActionListener(this);
		
		configurationBtn = new JButton("설정");
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
		
		btnSave = new JButton("대화내용 저장");
		btnSave.addActionListener(this);
		
		//나중에 작업할 예정
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pw.println("/close");
				for(Map.Entry<String, NewRoomFrame> entry: roomInfoList.entrySet()){
					if(entry.getValue().getModelOwner().getValueAt(0, 0).equals(id)){
						pw.println("/newOwner "+entry.getValue().getModelRoom().getValueAt(0, 0)+" /room "+entry.getKey());
					}
				}
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
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				System.exit(0);
			}
		});
		
		//사이즈 지정
		setSize(615,620);
		setBackground(Color.white);
		pnl.setBackground(Color.WHITE);
		
		pane_tableRoom.setBounds(0, 50, 500, 300);
		newRoomBtn.setBounds(0, 350, 150, 30);
		enterBtn.setBounds(150, 350, 150, 30);
		configurationBtn.setBounds(300, 350, 150, 30);
		btnSave.setBounds(450, 350, 150, 30);
		
		btnSave.setBackground(Color.white);
		newRoomBtn.setBackground(Color.white);
		enterBtn.setBackground(Color.white);
		configurationBtn.setBackground(Color.white);
		pane_tableAll.getViewport().setBackground(Color.white);
		tableAll.getTableHeader().setBackground(Color.white);
		pane_tableRoom.getViewport().setBackground(Color.white);
		tableRoom.getTableHeader().setBackground(Color.white);
		btnSave.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.LIGHT_GRAY));
		newRoomBtn.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1,Color.LIGHT_GRAY));
		enterBtn.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.LIGHT_GRAY));
		configurationBtn.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1,Color.LIGHT_GRAY));
		
		
		pane.setBounds(0, 380, 600, 170);
		tf.setBounds(0, 550, 600, 30);
		JLabel lblTitle = new JLabel("채팅방");
		lblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblTitle.setBounds(10, 0, 100, 50);
		
		JLabel lblSearch = new JLabel();
		ImageIcon icon = new ImageIcon("search.png");
		Image img2 = icon.getImage();
		img2 = img2.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon = new ImageIcon(img2);
		lblSearch.setIcon(icon);
		lblSearch.setBounds(360,20,30,30);
		add(lblSearch);
		
		tfSearch.setBounds(400, 20, 200, 30);
		tfSearch.setHorizontalAlignment(JTextField.RIGHT);
		pane_tableAll.setBounds(500, 50, 100, 300);
		pnl.add(lblTitle);
		pnl.add(enterBtn);
		pnl.add(newRoomBtn);
		pnl.add(configurationBtn);
		pnl.add(btnSave);
		add(pnl);
		setVisible(true);
	}
	//대기실에 대화한 내역이 있으면 해당 대화내용을 저장할 것인지 여부를 물어봄
	public void saveTa(){
		int okChk = JOptionPane.CANCEL_OPTION;
		if(ta.getText().length()!=0){
			okChk = JOptionPane.showConfirmDialog(this, "<html>대기실 대화 내용을 저장하시겠습니까?<br>채팅방의 대화내용은 저장되지 않습니다.</html>", "", JOptionPane.OK_CANCEL_OPTION);
		}else{
			JOptionPane.showMessageDialog(this, "저장할 내용이 없습니다.");
		}
		if(okChk==JOptionPane.OK_OPTION){
			FileWriter fwTa = null;
			PrintWriter pwTa = null;
			
			JFileChooser filechooser = new JFileChooser();
			FileNameExtensionFilter efilter = new FileNameExtensionFilter("텍스트 파일", "txt");
			filechooser.setFileFilter(efilter);
			filechooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
			filechooser.setSelectedFile(new File("chat_waitintroom_"+new SimpleDateFormat("yyyyMMddhhmmss").format(System.currentTimeMillis())+".txt"));
			filechooser.setMultiSelectionEnabled(false);
			int option = filechooser.showSaveDialog(null);
			
			if(option==JFileChooser.APPROVE_OPTION){
				try {
					fwTa = new FileWriter(filechooser.getSelectedFile(),false);
					pwTa = new PrintWriter(fwTa);
					pwTa.print(ta.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					try {
						if(fwTa!=null){
							fwTa.close();
						}
						if(pwTa!=null){
							pwTa.close();
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	}
	
	//MyRenderer 에서 사용
	//이미 들어간 방의 경우 배경색이 노랑색
	public boolean alreadyInChk(Object value){
		if(roomInfoList.containsKey(value)){
			return true; 
		}else{
			return false;
		}
	}
	//BlockRenderer 에서 사용
	//귓속말 차단된 사용자는 배경색이 회색
	public boolean blockedChk(Object value){
		if(blockNameList.contains(value)){
			return true; 
		}
		return false;
	}
	//MyRenderer 에서 사용
	//강퇴된 방의 경우에는 배경색이 회색
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
					pw.println("/beforeEnterRoom "+tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).toString());
				}
			}
		}else if(e.getSource() == configurationBtn){
			new WaitingRoomConfigDialog(this);
		}else if(e.getSource() == btnSave){
			saveTa();
		}else{	//대기실에서 텍스트 필드의 메시지 전송시
			if(tf.getText().substring(0,2).equals("/w")){
				if(tf.getText().split(" ").length<3){
					ta.append("\"/w 사용자아이디 귓속말로보낼내용\" 형식으로 작성해주세요\n");
					ta.setCaretPosition(ta.getDocument().getLength());
				}else{
					boolean exist = false;
					for (int i = 0; i < tableAll.getRowCount(); i++) {
						if(tableAll.getValueAt(i, 0).equals(tf.getText().split(" ")[1])){
							exist = true;
							break;
						}
					}
					if(exist){
						pw.println(tf.getText()+" /room");
					}else{
						if(tf.getText().split(" ")[1].equals(id)){
							ta.append("[혼잣말] "+id+" : "+tf.getText().substring(tf.getText().indexOf(id)+id.length()+1)+"\n");
							ta.setCaretPosition(ta.getDocument().getLength());
						}else{
							ta.append(tf.getText().split(" ")[1]+"에 해당하는 사용자 아이디가 없습니다.\n");
							ta.setCaretPosition(ta.getDocument().getLength());
							
						}
					}
				}
			}else{
				pw.println("//"+tf.getText()+" /room");
			}
			tf.setText("");
			tf.requestFocus();
		}
	}
	public void pwCreateNewRoom(){
		newRoomFrame.dispose();
		JOptionPane.showMessageDialog(this, "대화방 이름은 비속어를 포함할 수 없습니다.");
		new ChatNewRoomDialog(this);
	}
	//ChatNewRoomDialog 에서 btnOk 클릭시 호출
	public void createRoom(String name){
		newRoomFrame = new NewRoomFrame(this,name);
		newRoomFrame.getModelOwner().addRow(new String[]{id});	//방장 설정
		pw.println("/createRoom "+name+" /room");
	}
	//ChatNewRoomDialog 에서 btnOk 클릭시 호출
	public void createSecretRoom(String name,char[] password){
		newRoomFrame = new NewRoomFrame(this,name);
		newRoomFrame.getModelOwner().addRow(new String[]{id});
		pw.println("/createSecretRoom "+name+" "+String.copyValueOf(password)+" /room");
	}
	
	
	public void sendMsgEnterRoom(String roomNum){	//방 정보 다이얼로그에서 방 입장을 선택한 경우	//초대를 수락한 경우
		pw.println("/enterRoom "+roomNum);
	}
	public void sendMsgEnterSecretRoom(String roomNum, char[] password){	//방 정보 다이얼로그에서 방 입장을 선택한 경우
		pw.println("/enterSecretRoom "+roomNum +" "+String.copyValueOf(password));
	}
	public void enterRoom(String name,String roomNum){			//방에 입장을 선택해서 방에 들어가는 것이 허가된 경우 대화방 프레임을 생성함
		newRoomFrame = new NewRoomFrame(this,name);
		newRoomFrame.setRoomNum(roomNum);
		roomInfoList.put(roomNum, newRoomFrame);
		newRoomFrame.setTitle("채팅방 : "+name+" ( 채팅명 : "+id+" ) - "+roomNum);
		for (int i = 0; i < modelAll.getRowCount(); i++) {
			roomInfoList.get(roomNum).getModelAll().addRow(new String[]{modelAll.getValueAt(i, 0).toString()});
		}
		modelRoomDataChanged();
		pw.println("/enterRoomEnd "+roomNum);	//방이 제대로 생성되고 난 후 서버에 생성완료를 전달하면 서버에서 해당 방에 참여자 정보를 제공해줌
	}
	public void chatInRoom(String msg,String roomNum){	//대화방에서 메시지를 보낼 경우
		pw.println(msg+" /room "+roomNum);
	}
	public void exitRoom(String roomNum){		//대화방에서 나갈때
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
	
	public void setId(String id) {
		this.id = id;
	}

	public TrayIcon getTrayIcon() {
		return trayIcon;
	}
	public NewRoomFrame getNewRoomFrame() {
		return newRoomFrame;
	}
	//강퇴된 방에 입장을 시도하는 경우 강퇴된 방임을 알림
	public void showAlreadyBannedRoom(){
		JOptionPane.showMessageDialog(this, "강퇴당하신 방에는 입장하실 수 없습니다.");
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
		JOptionPane.showConfirmDialog(this, split[1]+"님이 "+ split[3]+"번 대화방에 초대하셨습니다.", "", JOptionPane.OK_CANCEL_OPTION);
	}
	//방에 입장하기 전에 방정보를 보여줌
	public void createBeforeEnterRoomDetail(String[] split){
		String roomName1 = null;
		for (int i = 0; i < tableRoom.getRowCount(); i++) {		//방이름 찾기
			if(tableRoom.getValueAt(i, 0).equals(split[1])){
				roomName1 = tableRoom.getValueAt(i, 1).toString();
				break;
			}
		}
		chatRoomDetailDialog = new ChatRoomDetailDialog(this,split,roomName1);
	}
	public void wrongPassword(){		//비밀 대화방의 비밀번호를 틀린 경우
		JOptionPane.showMessageDialog(this, "비밀번호를 잘못 입력하셨습니다");
		chatRoomDetailDialog = new ChatRoomDetailDialog(this, chatRoomDetailDialog.getSplit(),chatRoomDetailDialog.getRoomName());
	}
	public void correctPassword(){	//비밀 대화방의 비밀번호가 일치하는 경우
		chatRoomDetailDialog.correctPassword();
	}
	public void inviteNo(String[] split,String msg){	//이미 초대에 응해서 입장을 한 경우 여러번 초대를 해서 초대메시지에 수락한 경우
		JOptionPane.showMessageDialog(this,"[["+split[3]+"]"+msg.substring(split[0].length()+1,msg.lastIndexOf(split[split.length-2])-1)+"] 이미 입장하신 방입니다");
	}
	public void createBeforeInviteRoomDetail(String[] split){
		String roomName1 = null;
		for (int i = 0; i < tableRoom.getRowCount(); i++) {		//방이름 찾기
			if(tableRoom.getValueAt(i, 0).equals(split[1])){
				roomName1 = tableRoom.getValueAt(i, 1).toString();
				break;
			}
		}
		chatInviteRoomDetailDialog = new ChatInviteRoomDetailDialog(this, split,roomName1);
	}
	public void sendMsgRejectInvite(String roomNum, String inviteFrom){	//초대를 거절한 경우
		pw.println("/rejectInvite "+inviteFrom+" /room "+roomNum);
	}
	//대기실 설정에서 아이디 변경
	public void chgId(String newId){
		pw.println("/chgId "+id+" "+newId+" /room");
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
	public void receiveFileChk(String[] split){	//파일을 전송받을 것인지 확인
		roomInfoList.get(split[split.length-1]).receiveFileChk(split);
	}
	public void receiveFile(String[] split,String saveFileRoot){	//파일 받는 것이 확정
		pw.println("/receiveFile "+split[1]+" "+saveFileRoot+" "+split[3]+" "+split[2]+" "+split[4]+" "+split[5]);
	}
	public void completeSend(String[] split){	//전송이 완료되었음을 알림
		roomInfoList.get(split[split.length-1]).completeSend(split);
	}
	public void completeReceive(String[] split){	//전송이 완료되었음을 알림
		roomInfoList.get(split[split.length-1]).completeReceive(split);
	}
	public void pwchgId(){
		JOptionPane.showMessageDialog(this, "비속어를 포함한 아이디를 사용하실 수 없습니다.");
	}
	public void chgIdOk(){
		JOptionPane.showMessageDialog(this, "아이디가 변경되었습니다.");
	}
	public void pwChgRoomName(String roomNum){
		roomInfoList.get(roomNum).pwChgRoomName();
	}
	public void chgRoomNameOK(String roomNum){
		roomInfoList.get(roomNum).chgRoomNameOK();
	}
	public void cancelSecretRoomOk(String roomNum){
		roomInfoList.get(roomNum).cancelSecretRoomOk();
	}
	public void chgRoomPassWordOk(String roomNum){
		roomInfoList.get(roomNum).chgRoomPassWordOk();
	}
}
