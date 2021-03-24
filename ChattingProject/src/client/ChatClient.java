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
	
	JButton newRoomBtn;	//�� ����
	JButton enterBtn;	//�� ����
	JButton configurationBtn;	//������ư
	
	PrintWriter pw;
	BufferedReader br;
	Socket socket;
	
	String id = "";
	
	HashMap<String, NewRoomFrame> roomInfoList;	//���� ������ �ִ� ä�ù�
	
	String[] headerRoom = {"��ȣ","��ȭ�� �̸�","��й�"};	//ä�ù��� ����Ʈ
	String[][] contentRoom = {};
	DefaultTableModel modelRoom;
	JTable tableRoom;
	JScrollPane pane_tableRoom;
	
	String[] headerAll = {"��� ������"};				//��� ������ ����Ʈ
	String[][] contentAll = {};
	DefaultTableModel modelAll;
	JTable tableAll;
	JScrollPane pane_tableAll;
	
	Vector<String> blockNameList = new Vector<>();	//�ӼӸ� ���ܵ� ���̵� ����Ʈ�� ����
	
	TrayIcon trayIcon;								//�˸��� ���ؼ� ���
	
	ChatRoomDetailDialog chatRoomDetailDialog;		//��ȭ�� ���� �� ��ȭ�濡 ���� ������ Ȯ��
	ChatInviteRoomDetailDialog chatInviteRoomDetailDialog;
	
	ChatClientThread chatClientThread;
	
	boolean msgAlarm = true;						//���� ä�� �˸� ����
	Vector<String> bannedRoomList = new Vector<>();	//����� ��ȭ�� ����Ʈ�� ����
	
	JTextField tfSearch;							//��ȭ���� �˻��ϱ� ���� �ؽ�Ʈ�ʵ�
	
	JButton btnSave;
	//�ʱ�ȭ
	public ChatClient() {
		init();
		setClient();
	}
	String idAddress;
	//�������� ����
	public void setClient(){
		idAddress = JOptionPane.showInputDialog("������ IP�ּҸ� �Է��ϼ���.","127.0.0.1");
		try {
			while(id.equals("")){	//���̵� �Է����� ���� ��� ����
				id = JOptionPane.showInputDialog("���̵�");
				if(id==null){		//�ݱ⸦ ���� ��쿡�� null
					int i = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
					if(i == JOptionPane.OK_OPTION){
						System.exit(0);
					}else{
						id = "";
					}
				}else if(id.indexOf(" ")!=-1){
					JOptionPane.showMessageDialog(this, "���̵𿡴� ������ ������ �� �����ϴ�.");
					id = "";
				}else if(id.length()>20){
					JOptionPane.showMessageDialog(this, "���̵�� 20�ڸ� �ʰ��� �� �����ϴ�.");
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
					JOptionPane.showMessageDialog(this, "�̹� �����ϴ� ���̵� �Դϴ�.");
					id = "";
					while(id.equals("")){
						id = JOptionPane.showInputDialog("���̵�");
						if(id==null){		//�ݱ⸦ ���� ��쿡�� null
							int i = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
							if(i == JOptionPane.OK_OPTION){
								System.exit(0);
							}else{
								id = "";
							}
						}else if(id.indexOf(" ")!=-1){
							JOptionPane.showMessageDialog(this, "���̵𿡴� ������ ������ �� �����ϴ�.");
							id = "";
						}else if(id.indexOf("/")!=-1){
							JOptionPane.showMessageDialog(this, "���̵𿡴� /�� ������ �� �����ϴ�.");
							id = "";
						}else if(id.length()>20){
							JOptionPane.showMessageDialog(this, "���̵�� 20�ڸ� �ʰ��� �� �����ϴ�.");
							id = "";
						}
					}
					pw.println(id);
					
				}else if(strExist.equals("/prohibit")){
					JOptionPane.showMessageDialog(this, "��Ӿ ������ ���̵�� ����� �� �����ϴ�.");
					id = "";
					while(id.equals("")){
						id = JOptionPane.showInputDialog("���̵�");
						if(id==null){		//�ݱ⸦ ���� ��쿡�� null
							int i = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
							if(i == JOptionPane.OK_OPTION){
								System.exit(0);
							}else{
								id = "";
							}
						}else if(id.indexOf(" ")!=-1){
							JOptionPane.showMessageDialog(this, "���̵𿡴� ������ ������ �� �����ϴ�.");
							id = "";
						}else if(id.indexOf("/")!=-1){
							JOptionPane.showMessageDialog(this, "���̵𿡴� /�� ������ �� �����ϴ�.");
							id = "";
						}else if(id.length()>20){
							JOptionPane.showMessageDialog(this, "���̵�� 20�ڸ� �ʰ��� �� �����ϴ�.");
							id = "";
						}
					}
					pw.println(id);
				}else{
					break;
				}
			}
			
			setTitle(id+"���� ä��â");
			chatClientThread = new ChatClientThread(id, socket, ta, modelAll, this ,pw ,br);
			chatClientThread.start();
		} catch (ConnectException e) {
			JOptionPane.showMessageDialog(this, "������ �۵����� �ʽ��ϴ�.");
			System.exit(0);
		}	catch (SocketException e) {
			JOptionPane.showMessageDialog(this, "�߸��� IP�ּ� �Դϴ�.");
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
		
		newRoomBtn = new JButton("��ȭ�� ����");
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
		//ä�ù� �˻����
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
		//��ȭ�� �߿� ������ ���̳� ���� ���� ���� ���� ����
		try {
			tableRoom.setDefaultRenderer(Class.forName("java.lang.Object"), new MyRenderer(this));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//��ȭ�� ���̺��� ����Ŭ���� ��� �����ư�� ���� �Ͱ� ���� �̺�Ʈ
		tableRoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					if(tableRoom.getSelectedRow()!=-1){
						boolean alreadyIn = false;
						for(Map.Entry<String, NewRoomFrame> entry : roomInfoList.entrySet()){	//�̹� �� ���� ��� �ٽ� �� �� ����.
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
		
		//��ü ����� ����Ʈ
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
		//�ӼӸ� ���ܵ� ��� ���̺� �� �� ����
		try {
			tableAll.setDefaultRenderer(Class.forName("java.lang.Object"), new BlockRenderer(this));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//����� ����Ʈ ���̺� �˾��޴� �߰�
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem item1 = new JMenuItem("�ӼӸ� ����");
		JMenuItem item2 = new JMenuItem("�ӼӸ� ���� ����");
		JMenuItem item3 = new JMenuItem("�ӼӸ� ������");
		item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int okCancelChk = JOptionPane.showConfirmDialog(null, tableAll.getValueAt(tableAll.getSelectedRow(), 0)+"���� �ӼӸ��� �����Ͻðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
				if(okCancelChk==JOptionPane.OK_OPTION){
					blockNameList.add(tableAll.getValueAt(tableAll.getSelectedRow(), 0).toString());	//�ӼӸ� ���ܸ���Ʈ�� �߰�
					JOptionPane.showMessageDialog(null, "�ӼӸ� ���ܵǾ����ϴ�.");
					modelAllDataChanged();																//���ܵ� ����ڴ� ���� ����
					for (Map.Entry<String, NewRoomFrame> entry: roomInfoList.entrySet()) {				//�� �濡 ����� ����Ʈ���� ���� ����
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
					if(blockNameList.contains(tableAll.getValueAt(tableAll.getSelectedRow(), 0))){	//�ӼӸ� ���ܸ���Ʈ�� �߰��� ������� ��� �������� ��ư Ȱ��ȭ
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
		
		//����� ���̺� Ŭ���� �˾��޴� ��Ÿ������ ���콺������ �߰�
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
		
		configurationBtn = new JButton("����");
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
		
		btnSave = new JButton("��ȭ���� ����");
		btnSave.addActionListener(this);
		
		//���߿� �۾��� ����
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
		
		//������ ����
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
		JLabel lblTitle = new JLabel("ä�ù�");
		lblTitle.setFont(new Font("���� ���", Font.BOLD, 30));
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
	//���ǿ� ��ȭ�� ������ ������ �ش� ��ȭ������ ������ ������ ���θ� ���
	public void saveTa(){
		int okChk = JOptionPane.CANCEL_OPTION;
		if(ta.getText().length()!=0){
			okChk = JOptionPane.showConfirmDialog(this, "<html>���� ��ȭ ������ �����Ͻðڽ��ϱ�?<br>ä�ù��� ��ȭ������ ������� �ʽ��ϴ�.</html>", "", JOptionPane.OK_CANCEL_OPTION);
		}else{
			JOptionPane.showMessageDialog(this, "������ ������ �����ϴ�.");
		}
		if(okChk==JOptionPane.OK_OPTION){
			FileWriter fwTa = null;
			PrintWriter pwTa = null;
			
			JFileChooser filechooser = new JFileChooser();
			FileNameExtensionFilter efilter = new FileNameExtensionFilter("�ؽ�Ʈ ����", "txt");
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
	
	//MyRenderer ���� ���
	//�̹� �� ���� ��� ������ �����
	public boolean alreadyInChk(Object value){
		if(roomInfoList.containsKey(value)){
			return true; 
		}else{
			return false;
		}
	}
	//BlockRenderer ���� ���
	//�ӼӸ� ���ܵ� ����ڴ� ������ ȸ��
	public boolean blockedChk(Object value){
		if(blockNameList.contains(value)){
			return true; 
		}
		return false;
	}
	//MyRenderer ���� ���
	//����� ���� ��쿡�� ������ ȸ��
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
		}else if(e.getSource() == btnSave){
			saveTa();
		}else{	//���ǿ��� �ؽ�Ʈ �ʵ��� �޽��� ���۽�
			if(tf.getText().substring(0,2).equals("/w")){
				if(tf.getText().split(" ").length<3){
					ta.append("\"/w ����ھ��̵� �ӼӸ��κ�������\" �������� �ۼ����ּ���\n");
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
							ta.append("[ȥ�㸻] "+id+" : "+tf.getText().substring(tf.getText().indexOf(id)+id.length()+1)+"\n");
							ta.setCaretPosition(ta.getDocument().getLength());
						}else{
							ta.append(tf.getText().split(" ")[1]+"�� �ش��ϴ� ����� ���̵� �����ϴ�.\n");
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
		JOptionPane.showMessageDialog(this, "��ȭ�� �̸��� ��Ӿ ������ �� �����ϴ�.");
		new ChatNewRoomDialog(this);
	}
	//ChatNewRoomDialog ���� btnOk Ŭ���� ȣ��
	public void createRoom(String name){
		newRoomFrame = new NewRoomFrame(this,name);
		newRoomFrame.getModelOwner().addRow(new String[]{id});	//���� ����
		pw.println("/createRoom "+name+" /room");
	}
	//ChatNewRoomDialog ���� btnOk Ŭ���� ȣ��
	public void createSecretRoom(String name,char[] password){
		newRoomFrame = new NewRoomFrame(this,name);
		newRoomFrame.getModelOwner().addRow(new String[]{id});
		pw.println("/createSecretRoom "+name+" "+String.copyValueOf(password)+" /room");
	}
	
	
	public void sendMsgEnterRoom(String roomNum){	//�� ���� ���̾�α׿��� �� ������ ������ ���	//�ʴ븦 ������ ���
		pw.println("/enterRoom "+roomNum);
	}
	public void sendMsgEnterSecretRoom(String roomNum, char[] password){	//�� ���� ���̾�α׿��� �� ������ ������ ���
		pw.println("/enterSecretRoom "+roomNum +" "+String.copyValueOf(password));
	}
	public void enterRoom(String name,String roomNum){			//�濡 ������ �����ؼ� �濡 ���� ���� �㰡�� ��� ��ȭ�� �������� ������
		newRoomFrame = new NewRoomFrame(this,name);
		newRoomFrame.setRoomNum(roomNum);
		roomInfoList.put(roomNum, newRoomFrame);
		newRoomFrame.setTitle("ä�ù� : "+name+" ( ä�ø� : "+id+" ) - "+roomNum);
		for (int i = 0; i < modelAll.getRowCount(); i++) {
			roomInfoList.get(roomNum).getModelAll().addRow(new String[]{modelAll.getValueAt(i, 0).toString()});
		}
		modelRoomDataChanged();
		pw.println("/enterRoomEnd "+roomNum);	//���� ����� �����ǰ� �� �� ������ �����ϷḦ �����ϸ� �������� �ش� �濡 ������ ������ ��������
	}
	public void chatInRoom(String msg,String roomNum){	//��ȭ�濡�� �޽����� ���� ���
		pw.println(msg+" /room "+roomNum);
	}
	public void exitRoom(String roomNum){		//��ȭ�濡�� ������
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
	//����� �濡 ������ �õ��ϴ� ��� ����� ������ �˸�
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
	//�濡 �����ϱ� ���� �������� ������
	public void createBeforeEnterRoomDetail(String[] split){
		String roomName1 = null;
		for (int i = 0; i < tableRoom.getRowCount(); i++) {		//���̸� ã��
			if(tableRoom.getValueAt(i, 0).equals(split[1])){
				roomName1 = tableRoom.getValueAt(i, 1).toString();
				break;
			}
		}
		chatRoomDetailDialog = new ChatRoomDetailDialog(this,split,roomName1);
	}
	public void wrongPassword(){		//��� ��ȭ���� ��й�ȣ�� Ʋ�� ���
		JOptionPane.showMessageDialog(this, "��й�ȣ�� �߸� �Է��ϼ̽��ϴ�");
		chatRoomDetailDialog = new ChatRoomDetailDialog(this, chatRoomDetailDialog.getSplit(),chatRoomDetailDialog.getRoomName());
	}
	public void correctPassword(){	//��� ��ȭ���� ��й�ȣ�� ��ġ�ϴ� ���
		chatRoomDetailDialog.correctPassword();
	}
	public void inviteNo(String[] split,String msg){	//�̹� �ʴ뿡 ���ؼ� ������ �� ��� ������ �ʴ븦 �ؼ� �ʴ�޽����� ������ ���
		JOptionPane.showMessageDialog(this,"[["+split[3]+"]"+msg.substring(split[0].length()+1,msg.lastIndexOf(split[split.length-2])-1)+"] �̹� �����Ͻ� ���Դϴ�");
	}
	public void createBeforeInviteRoomDetail(String[] split){
		String roomName1 = null;
		for (int i = 0; i < tableRoom.getRowCount(); i++) {		//���̸� ã��
			if(tableRoom.getValueAt(i, 0).equals(split[1])){
				roomName1 = tableRoom.getValueAt(i, 1).toString();
				break;
			}
		}
		chatInviteRoomDetailDialog = new ChatInviteRoomDetailDialog(this, split,roomName1);
	}
	public void sendMsgRejectInvite(String roomNum, String inviteFrom){	//�ʴ븦 ������ ���
		pw.println("/rejectInvite "+inviteFrom+" /room "+roomNum);
	}
	//���� �������� ���̵� ����
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
	public void receiveFileChk(String[] split){	//������ ���۹��� ������ Ȯ��
		roomInfoList.get(split[split.length-1]).receiveFileChk(split);
	}
	public void receiveFile(String[] split,String saveFileRoot){	//���� �޴� ���� Ȯ��
		pw.println("/receiveFile "+split[1]+" "+saveFileRoot+" "+split[3]+" "+split[2]+" "+split[4]+" "+split[5]);
	}
	public void completeSend(String[] split){	//������ �Ϸ�Ǿ����� �˸�
		roomInfoList.get(split[split.length-1]).completeSend(split);
	}
	public void completeReceive(String[] split){	//������ �Ϸ�Ǿ����� �˸�
		roomInfoList.get(split[split.length-1]).completeReceive(split);
	}
	public void pwchgId(){
		JOptionPane.showMessageDialog(this, "��Ӿ ������ ���̵� ����Ͻ� �� �����ϴ�.");
	}
	public void chgIdOk(){
		JOptionPane.showMessageDialog(this, "���̵� ����Ǿ����ϴ�.");
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
