package client;
import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.jar.Attributes.Name;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class NewRoomFrame extends JFrame implements ActionListener{
	JTextArea ta;
	JTextField tf;
	JTable tableRoom;
	DefaultTableModel modelRoom;
	String[] headerRoom = {"ä�ù� ������"};
	String[][] contentRoom = {};
	JScrollPane pane_tableRoom;
	
	JTable tableAll;
	DefaultTableModel modelAll;
	String[] headerAll = {"��ü ������"};
	String[][] contentAll = {};
	JScrollPane pane_tableAll;
	
	JTable tableOwner;
	DefaultTableModel modelOwner;
	String[] headerOwner = {"����"};
	String[][] contentOwner = {};
	JScrollPane pane_tableOwner;
	
	JButton exitBtn;
	JButton btnRoom, btnAll;
	JButton btnConfig;
	JButton btnSave;
	
	String roomNum;
	String roomName;
	
	boolean roomAlarm = true;
	
	ChatClient chatClient;
	public NewRoomFrame(ChatClient chatClient, String roomName) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(620,520);
		
		UIManager UI=new UIManager();
		 UI.put("OptionPane.background",new ColorUIResource(255,255,255));
		 UI.put("Panel.background",new ColorUIResource(255,255,255));
		
		this.roomName = roomName;
		this.chatClient = chatClient;
		
		modelRoom = new DefaultTableModel(contentRoom, headerRoom){
			public boolean isCellEditable(int rowIndex, int colIndex){
				return false;
			}
		};
		tableRoom = new JTable(modelRoom);
		tableRoom.getTableHeader().setReorderingAllowed(false);
		tableRoom.getTableHeader().setResizingAllowed(false);
		tableRoom.setRowSorter(new TableRowSorter<DefaultTableModel>(modelRoom));
		pane_tableRoom = new JScrollPane(tableRoom);
		try {
			tableRoom.setDefaultRenderer(Class.forName("java.lang.Object"), new BlockRenderer(chatClient));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		modelAll = new DefaultTableModel(contentAll, headerAll){
			public boolean isCellEditable(int rowIndex, int colIndex){
				return false;
			}
		};
		tableAll = new JTable(modelAll);
		tableAll.getTableHeader().setReorderingAllowed(false);
		tableAll.getTableHeader().setResizingAllowed(false);
		tableAll.setRowSorter(new TableRowSorter<DefaultTableModel>(modelAll));
		pane_tableAll = new JScrollPane(tableAll);
		try {
			tableAll.setDefaultRenderer(Class.forName("java.lang.Object"), new BlockRenderer(chatClient));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JPopupMenu popupMenu1 = new JPopupMenu();	//��ü ����� ���̺��� ���
		JMenuItem item1_0 = new JMenuItem("�ӼӸ� ������");
		JMenuItem item1_1 = new JMenuItem("�ӼӸ� ����");
		JMenuItem item1_2 = new JMenuItem("�ӼӸ� ���� ����");
		JMenuItem item1_3 = new JMenuItem("�ʴ��ϱ�");
		popupMenu1.add(item1_3);
		
		JPopupMenu popupMenu2 = new JPopupMenu();	//��ȭ�� ����� ���̺��� ���
		JMenuItem item2_0 = new JMenuItem("�ӼӸ� ������");
		JMenuItem item2_1 = new JMenuItem("�ӼӸ� ����");
		JMenuItem item2_2 = new JMenuItem("�ӼӸ� ���� ����");
		JMenuItem item2_3 = new JMenuItem("���� ����");
		JMenuItem item2_4 = new JMenuItem("���� ����");		//��ȭ�� ����ڵ鳢���� ���� ���۰���
		JMenuItem item2_4_2 = new JMenuItem("��ο��� ���� ����");		//��ȭ�� ����ڵ鳢���� ���� ���۰���
		JMenuItem itme2_5 = new JMenuItem("����");
		
		
		item1_0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText("/w "+tableAll.getValueAt(tableAll.getSelectedRow(), 0)+" ");
				tf.requestFocus();
			}
		});
		item1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int okCancelChk = JOptionPane.showConfirmDialog(null, tableAll.getValueAt(tableAll.getSelectedRow(), 0)+"���� �ӼӸ��� �����Ͻðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
				if(okCancelChk==JOptionPane.OK_OPTION){
					chatClient.getBlockNameList().add(tableAll.getValueAt(tableAll.getSelectedRow(), 0).toString());
					JOptionPane.showMessageDialog(null, "�ӼӸ� ���ܵǾ����ϴ�.");
					modelRoomDataChanged();
					modelAllDataChanged();
					chatClient.modelAllDataChanged();
				}
			}
		});
		item1_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int okCancelChk = JOptionPane.showConfirmDialog(null, tableAll.getValueAt(tableAll.getSelectedRow(), 0)+"���� �ӼӸ� ������ �����Ͻðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
				if(okCancelChk==JOptionPane.OK_OPTION){
					chatClient.getBlockNameList().remove(tableAll.getValueAt(tableAll.getSelectedRow(), 0).toString());
					JOptionPane.showMessageDialog(null, "�ӼӸ� ���� �����Ǿ����ϴ�.");
					modelRoomDataChanged();
					modelAllDataChanged();
					chatClient.modelAllDataChanged();

				}
			}
		});
		item1_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chatClient.chatInRoom("/invite "+tableAll.getValueAt(tableAll.getSelectedRow(), 0), roomNum);
			}
		});
		
		
		popupMenu1.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				if(tableAll.getSelectedRow()!=-1){
					if(chatClient.getBlockNameList().contains(tableAll.getValueAt(tableAll.getSelectedRow(), 0))){
						popupMenu1.remove(item1_1);
						popupMenu1.remove(item1_0);
						popupMenu1.add(item1_2);
					}else{
						popupMenu1.remove(item1_2);
						popupMenu1.add(item1_0);
						popupMenu1.add(item1_1);
					}
				}else{
					popupMenu1.setVisible(false);
				}
			}
			
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
			public void popupMenuCanceled(PopupMenuEvent e) {}
		});
		
		item2_0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText("/w "+tableRoom.getValueAt(tableRoom.getSelectedRow(), 0)+" ");
				tf.requestFocus();
			}
		});
		item2_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int okCancelChk = JOptionPane.showConfirmDialog(null, tableRoom.getValueAt(tableRoom.getSelectedRow(), 0)+"���� �ӼӸ��� �����Ͻðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
				if(okCancelChk==JOptionPane.OK_OPTION){
					chatClient.getBlockNameList().add(tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).toString());
					JOptionPane.showMessageDialog(null, "�ӼӸ� ���ܵǾ����ϴ�.");
					modelRoomDataChanged();
					modelAllDataChanged();
					chatClient.modelAllDataChanged();

				}
			}
		});
		item2_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int okCancelChk = JOptionPane.showConfirmDialog(null, tableRoom.getValueAt(tableRoom.getSelectedRow(), 0)+"���� �ӼӸ� ������ �����Ͻðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
				if(okCancelChk==JOptionPane.OK_OPTION){
					chatClient.getBlockNameList().remove(tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).toString());
					JOptionPane.showMessageDialog(null, "�ӼӸ� ���� �����Ǿ����ϴ�.");
					modelRoomDataChanged();
					modelAllDataChanged();
					chatClient.modelAllDataChanged();

				}
			}
		});
		item2_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int chkOkCancel = JOptionPane.showConfirmDialog(null, tableRoom.getValueAt(tableRoom.getSelectedRow(), 0)+"�Կ��� ���� �ڰ��� �ֽðڽ��ϱ�?", "",  JOptionPane.OK_CANCEL_OPTION);
				if(chkOkCancel == JOptionPane.OK_OPTION){
					chatClient.chatInRoom("/ca "+tableRoom.getValueAt(tableRoom.getSelectedRow(), 0),roomNum);
				}
			}
		});
		item2_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
				fileChooser.setMultiSelectionEnabled(false);
				int option = fileChooser.showOpenDialog(null);
				if(option == JFileChooser.APPROVE_OPTION){
					File file = fileChooser.getSelectedFile();
					chatClient.chatInRoom("/sendFile "+tableRoom.getValueAt(tableRoom.getSelectedRow(), 0)+" "+file.getAbsolutePath(),roomNum);
				}
			}
		});
		item2_4_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
				fileChooser.setMultiSelectionEnabled(false);
				int option = fileChooser.showOpenDialog(null);
				if(option == JFileChooser.APPROVE_OPTION){
					File file = fileChooser.getSelectedFile();
					chatClient.chatInRoom("/sendFile all "+file.getAbsolutePath(),roomNum);
				}
			}
		});
		itme2_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chatClient.chatInRoom("/ban "+tableRoom.getValueAt(tableRoom.getSelectedRow(), 0), roomNum);
			}
		});
		popupMenu2.add(item2_4);
		popupMenu2.add(item2_4_2);
		popupMenu2.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				if(tableRoom.getSelectedRow()!=-1){
					if(chatClient.getBlockNameList().contains(tableRoom.getValueAt(tableRoom.getSelectedRow(), 0))){
						popupMenu2.remove(item2_1);
						popupMenu2.remove(item2_0);
						popupMenu2.add(item2_2);
					}else{
						popupMenu2.remove(item2_2);
						popupMenu2.add(item2_1);
						popupMenu2.add(item2_0);
					}
					if(modelOwner.getValueAt(0, 0).equals(chatClient.getId())){	//������ ��쿡�� ���� ���� �޴��������� ���̵���
						popupMenu2.add(item2_3);
						popupMenu2.add(itme2_5);
					}else{
						popupMenu2.remove(item2_3);
						popupMenu2.remove(itme2_5);
					}	
					
				}else{
					popupMenu2.setVisible(false);
				}
			}
			
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
			public void popupMenuCanceled(PopupMenuEvent e) {}
		});
		tableAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableAll.setRowSelectionInterval(tableAll.rowAtPoint(e.getPoint()), tableAll.rowAtPoint(e.getPoint()));
				if(e.getButton()==1){
					popupMenu1.show(tableAll, e.getX(), e.getY());
				}
			}
		});
		tableRoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableRoom.setRowSelectionInterval(tableRoom.rowAtPoint(e.getPoint()), tableRoom.rowAtPoint(e.getPoint()));
				if(e.getButton()==1){
					popupMenu2.show(tableRoom, e.getX(), e.getY());
				}
			}
		});
		
		
		
		tableAll.setComponentPopupMenu(popupMenu1);
		tableRoom.setComponentPopupMenu(popupMenu2);
		
		modelOwner = new DefaultTableModel(contentOwner, headerOwner){
			public boolean isCellEditable(int rowIndex, int colIndex){
				return false;
			}
		};
		tableOwner = new JTable(modelOwner);
		tableOwner.getTableHeader().setReorderingAllowed(false);
		tableOwner.getTableHeader().setResizingAllowed(false);
		pane_tableOwner = new JScrollPane(tableOwner);
		add(pane_tableOwner);
		
		tf = new JTextField();
		tf.addActionListener(this);
		tf.setBounds(0, 400, 500, 30);
		pane_tableRoom.setBounds(500, 81, 100, 349);
		pane_tableAll.setBounds(500, 81, 100, 349);
		pane_tableOwner.setBounds(500, 40, 100, 41);

		ta =  new JTextArea();
		ta.setEditable(false);
		JScrollPane pane = new JScrollPane(ta);
		pane.setBounds(0, 0, 500, 400);
		add(pane_tableRoom);
		add(pane);
		add(tf);
		
		btnRoom = new JButton("<html>ä�ù�<br>������</html>");
		btnAll = new JButton("<html>��ü<br>������</html>");
		btnRoom.setBounds(550, 0, 50, 40);
		btnAll.setBounds(500, 0, 50, 40);
		btnRoom.addActionListener(this);
		btnAll.addActionListener(this);
		btnRoom.setMargin(new Insets(0, 0, 0, 0));
		btnAll.setMargin(new Insets(0, 0, 0, 0));
		btnAll.setBackground(Color.white);
		btnRoom.setBackground(Color.white);
		tableAll.getTableHeader().setBackground(Color.white);
		tableRoom.getTableHeader().setBackground(Color.white);
		tableOwner.getTableHeader().setBackground(Color.white);
		pane_tableAll.getViewport().setBackground(Color.white);
		pane_tableOwner.getViewport().setBackground(Color.white);
		pane_tableRoom.getViewport().setBackground(Color.white);
		getContentPane().setBackground(Color.white);
		setResizable(false);
		
		btnRoom.setBorder(BorderFactory.createLoweredBevelBorder());
		btnAll.setBorder(BorderFactory.createRaisedBevelBorder());
		add(btnRoom);
		add(btnAll);
		
		exitBtn = new JButton("������");
		exitBtn.addActionListener(this);
		
		add(exitBtn);
		
		Image img = Toolkit.getDefaultToolkit().getImage("big6.png");
		img = img.getScaledInstance(15, 15, Image.SCALE_DEFAULT);
		setIconImage(img);
		
		btnConfig = new JButton("����");
		btnConfig.addActionListener(this);
		btnSave = new JButton("��ȭ���� ����");
		btnSave.addActionListener(this);
		exitBtn.setBounds(300, 450, 150, 30);
		btnConfig.setBounds(150, 450, 150, 30);
		btnSave.setBounds(0, 450, 150, 30);
		exitBtn.setBackground(Color.WHITE);
		btnConfig.setBackground(Color.WHITE);
		btnSave.setBackground(Color.WHITE);
//		btnSave.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.black));
//		btnConfig.setBorder(BorderFactory.createMatteBorder(1,0,1,0,Color.black));
//		exitBtn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.black));
		
		add(btnConfig);
		add(btnSave);
		
		setLayout(null);
		setVisible(true);
		setFocusable(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				requestFocus();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				chatClient.exitRoom(roomNum);
			}
		});
		
	}
	public void saveTa(){
		int okChk = JOptionPane.CANCEL_OPTION;
		if(ta.getText().length()!=0){
			okChk = JOptionPane.showConfirmDialog(this, "ä�ù� ��ȭ ������ �����Ͻðڽ��ϱ�?","",JOptionPane.OK_CANCEL_OPTION);
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
			filechooser.setSelectedFile(new File("chat_room_"+roomName+"_"+new SimpleDateFormat("yyyyMMddhhmmss").format(System.currentTimeMillis())+".txt"));
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
	public void pwChgRoomName(){
		JOptionPane.showMessageDialog(this, "��ȭ�� �̸��� ��Ӿ ������ �� �����ϴ�.");
	}
	public void chgRoomNameOK(){
		JOptionPane.showMessageDialog(this, "��ȭ�� �̸��� ����Ǿ����ϴ�.");
	}
	public void cancelSecretRoomOk(){
		JOptionPane.showMessageDialog(this, "��� ��ȭ�� ������ ��ҵǾ����ϴ�.");
	}
	public void chgRoomPassWordOk(){
		JOptionPane.showMessageDialog(this, "��ȭ�� ��й�ȣ�� �缳���Ǿ����ϴ�.");
	}
	public void chgRoomName(String newRoomName){	//��ȭ�� �������� ��ȭ�� �̸� ����
		chatClient.chatInRoom("/chgRoomName "+newRoomName, roomNum);
	}
	public void modelRoomDataChanged(){
		modelRoom.fireTableDataChanged();
	}
	public void modelAllDataChanged(){
		modelAll.fireTableDataChanged();
	}
	
	public DefaultTableModel getModelOwner() {
		return modelOwner;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomName() {
		return roomName;
	}

	public JTextArea getTa() {
		return ta;
	}

	public DefaultTableModel getModelAll() {
		return modelAll;
	}

	public DefaultTableModel getModelRoom() {
		return modelRoom;
	}
	
	public String getRoomNum() {
		return roomNum;
	}
	public ChatClient getChatClientPrac() {
		return chatClient;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public void receiveFileChk(String[] split){	//������ ���۹��� ������ Ȯ��	//��� ��ġ�� ���� �� ������
//		File file = new File(split[2]); 
		int chkReceive = JOptionPane.showConfirmDialog(this, split[3]+"�Բ��� ������ ����("+split[2].substring(split[2].lastIndexOf(File.separator)+1)+")�� ���۹����ðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
		if(chkReceive == JOptionPane.OK_OPTION){
			JFileChooser filechooser2 = new JFileChooser();
			filechooser2.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
			filechooser2.setSelectedFile(new File(split[2].substring(split[2].lastIndexOf(File.separator)+1)));
			filechooser2.setMultiSelectionEnabled(false);
			int option = filechooser2.showSaveDialog(null);
			while (true) {
				if(option == JFileChooser.APPROVE_OPTION){
					if(filechooser2.getSelectedFile().exists()){
						int selectChk = JOptionPane.showConfirmDialog(this, "���� �̸��� ���� ������ �����մϴ�. ������ ����ðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
						if(selectChk==JOptionPane.OK_OPTION){
							break;
						}else{
							option = filechooser2.showSaveDialog(null);
						}
					}else{
						break;
					}
							
				}else{
					break;
				}
			}
			chatClient.receiveFile(split,filechooser2.getSelectedFile().getAbsolutePath());
		}
	}
	public void completeSend(String[] split){	//������ �Ϸ�Ǿ����� �˸�
		JOptionPane.showMessageDialog(this, split[1]+"���� ����("+split[4].substring(split[4].lastIndexOf(File.separator)+1)+") ������ �Ϸ��߽��ϴ�.");
	}
	public void completeReceive(String[] split){	//������ �Ϸ�Ǿ����� �˸�
		JOptionPane.showMessageDialog(this, split[3]+"���Լ� ����("+split[2].substring(split[2].lastIndexOf(File.separator)+1)+") ������ �Ϸ��߽��ϴ�.");
	}
	
	public boolean getRoomAlarm() {
		return roomAlarm;
	}
	public void setRoomAlarm(boolean roomAlarm) {
		this.roomAlarm = roomAlarm;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==exitBtn){	//��ȭ�� ������ ��ư
			chatClient.exitRoom(roomNum);
			dispose();
		}else if(e.getSource()==btnAll){	//��� ���� ����Ʈ ����
			btnRoom.setBorder(BorderFactory.createRaisedBevelBorder());
			btnAll.setBorder(BorderFactory.createLoweredBevelBorder());
			remove(pane_tableRoom);
			add(pane_tableAll);			
		}else if(e.getSource()==btnRoom){	//��ȭ�� ���� ����Ʈ ����
			btnRoom.setBorder(BorderFactory.createLoweredBevelBorder());
			btnAll.setBorder(BorderFactory.createRaisedBevelBorder());
			remove(pane_tableAll);
			add(pane_tableRoom);			
		}else if(e.getSource()==btnConfig){	//������ ��ȭ�濡�� ������ Ŭ���� ���
			if(chatClient.getId().equals(tableOwner.getValueAt(0, 0))){
				new RoomConfigOwnerDialog(this);
			}else{
				new RoomConfigDialog(this);
			}
		}else if(e.getSource() == btnSave){	//��ȭ�� ��ȭ���� ����
			saveTa();
		}else{						//��ȭ�� �ؽ�Ʈ �ʵ��� �޽����� ����
			if(tf.getText().length()>2){
				if(tf.getText().substring(0,2).equals("/w")){
					if(tf.getText().split(" ").length<3){
						ta.append("\"/w ����ھ��̵� �ӼӸ��κ�������\" �������� �ۼ����ּ���.\n");
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
							chatClient.chatInRoom(tf.getText(),roomNum);
						}else{
							if(tf.getText().split(" ")[1].equals(chatClient.getId())){
								ta.append("[ȥ�㸻] "+chatClient.getId()+" : "+tf.getText().substring(tf.getText().indexOf(chatClient.getId())+chatClient.getId().length()+1)+"\n");
								ta.setCaretPosition(ta.getDocument().getLength());
							}else{
								ta.append(tf.getText().split(" ")[1]+"�� �ش��ϴ� ����� ���̵� �����ϴ�.\n");
								ta.setCaretPosition(ta.getDocument().getLength());
								
							}
						}
					}
				}else{
					chatClient.chatInRoom("//"+tf.getText(),roomNum);
				}
			}else{
				chatClient.chatInRoom("//"+tf.getText(),roomNum);
			}
			tf.setText("");;
			tf.requestFocus();
		}
		
	}
}
