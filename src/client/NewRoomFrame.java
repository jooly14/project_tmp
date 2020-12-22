package client;
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
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
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
	
	ChatClient chatClientPrac;
	public NewRoomFrame(ChatClient chatClientPrac, String roomName) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(620,600);
		
		this.roomName = roomName;
		this.chatClientPrac = chatClientPrac;
		
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
		pane_tableRoom.setBounds(500, 90, 100, 400);
		try {
			tableRoom.setDefaultRenderer(Class.forName("java.lang.Object"), new BlockRenderer(chatClientPrac));
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
		pane_tableAll.setBounds(500, 90, 100, 400);
		try {
			tableAll.setDefaultRenderer(Class.forName("java.lang.Object"), new BlockRenderer(chatClientPrac));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JPopupMenu popupMenu1 = new JPopupMenu();	//��ü ����� ���̺��� ���
		JMenuItem item1_1 = new JMenuItem("�ӼӸ� ����");
		JMenuItem item1_2 = new JMenuItem("�ӼӸ� ���� ����");
		JMenuItem item1_3 = new JMenuItem("�ʴ��ϱ�");
		popupMenu1.add(item1_3);
		
		JPopupMenu popupMenu2 = new JPopupMenu();	//��ȭ�� ����� ���̺��� ���
		JMenuItem item2_1 = new JMenuItem("�ӼӸ� ����");
		JMenuItem item2_2 = new JMenuItem("�ӼӸ� ���� ����");
		JMenuItem item2_3 = new JMenuItem("���� ����");
		JMenuItem item2_4 = new JMenuItem("���� ����");
		
		item1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int okCancelChk = JOptionPane.showConfirmDialog(null, tableAll.getValueAt(tableAll.getSelectedRow(), 0)+"���� �ӼӸ��� �����Ͻðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
				if(okCancelChk==JOptionPane.OK_OPTION){
					chatClientPrac.getBlockNameList().add(tableAll.getValueAt(tableAll.getSelectedRow(), 0).toString());
					JOptionPane.showMessageDialog(null, "�ӼӸ� ���ܵǾ����ϴ�.");
					modelRoomDataChanged();
					modelAllDataChanged();
					chatClientPrac.modelAllDataChanged();
				}
			}
		});
		item1_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int okCancelChk = JOptionPane.showConfirmDialog(null, tableAll.getValueAt(tableAll.getSelectedRow(), 0)+"���� �ӼӸ� ������ �����Ͻðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
				if(okCancelChk==JOptionPane.OK_OPTION){
					chatClientPrac.getBlockNameList().remove(tableAll.getValueAt(tableAll.getSelectedRow(), 0).toString());
					JOptionPane.showMessageDialog(null, "�ӼӸ� ���� �����Ǿ����ϴ�.");
					modelRoomDataChanged();
					modelAllDataChanged();
					chatClientPrac.modelAllDataChanged();

				}
			}
		});
		item1_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chatClientPrac.chatInRoom("/invite "+tableAll.getValueAt(tableAll.getSelectedRow(), 0), roomNum);
			}
		});
		
		
		popupMenu1.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				if(tableAll.getSelectedRow()!=-1){
					if(chatClientPrac.getBlockNameList().contains(tableAll.getValueAt(tableAll.getSelectedRow(), 0))){
						popupMenu1.remove(item1_1);
						popupMenu1.add(item1_2);
					}else{
						popupMenu1.remove(item1_2);
						popupMenu1.add(item1_1);
					}
				}else{
					popupMenu1.setVisible(false);
				}
			}
			
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
			public void popupMenuCanceled(PopupMenuEvent e) {}
		});
		item2_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int okCancelChk = JOptionPane.showConfirmDialog(null, tableRoom.getValueAt(tableRoom.getSelectedRow(), 0)+"���� �ӼӸ��� �����Ͻðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
				if(okCancelChk==JOptionPane.OK_OPTION){
					chatClientPrac.getBlockNameList().add(tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).toString());
					JOptionPane.showMessageDialog(null, "�ӼӸ� ���ܵǾ����ϴ�.");
					modelRoomDataChanged();
					modelAllDataChanged();
					chatClientPrac.modelAllDataChanged();

				}
			}
		});
		item2_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int okCancelChk = JOptionPane.showConfirmDialog(null, tableRoom.getValueAt(tableRoom.getSelectedRow(), 0)+"���� �ӼӸ� ������ �����Ͻðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
				if(okCancelChk==JOptionPane.OK_OPTION){
					chatClientPrac.getBlockNameList().remove(tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).toString());
					JOptionPane.showMessageDialog(null, "�ӼӸ� ���� �����Ǿ����ϴ�.");
					modelRoomDataChanged();
					modelAllDataChanged();
					chatClientPrac.modelAllDataChanged();

				}
			}
		});
		item2_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int chkOkCancel = JOptionPane.showConfirmDialog(null, tableRoom.getValueAt(tableRoom.getSelectedRow(), 0)+"�Կ��� ���� �ڰ��� �ֽðڽ��ϱ�?", "",  JOptionPane.OK_CANCEL_OPTION);
				if(chkOkCancel == JOptionPane.OK_OPTION){
					chatClientPrac.chatInRoom("/ca "+tableRoom.getValueAt(tableRoom.getSelectedRow(), 0),roomNum);
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
//					System.out.println(file.getName().substring(file.getName().lastIndexOf(file.separator)+1));
					chatClientPrac.chatInRoom("/sendFile "+tableRoom.getValueAt(tableRoom.getSelectedRow(), 0)+" "+file.getAbsolutePath(),roomNum);
				}
			}
		});
		popupMenu2.add(item2_4);
		popupMenu2.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				if(tableRoom.getSelectedRow()!=-1){
					if(chatClientPrac.getBlockNameList().contains(tableRoom.getValueAt(tableRoom.getSelectedRow(), 0))){
						popupMenu2.remove(item2_1);
						popupMenu2.add(item2_2);
					}else{
						popupMenu2.remove(item2_2);
						popupMenu2.add(item2_1);
					}
					if(modelOwner.getValueAt(0, 0).equals(getTitle().split(" ")[6])){
						popupMenu2.add(item2_3);
					}else{
						popupMenu2.remove(item2_3);
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
		pane_tableOwner.setBounds(500, 40, 100, 50);
		add(pane_tableOwner);
		
		tf = new JTextField();
		tf.addActionListener(this);
		tf.setBounds(0, 400, 500, 30);
		ta =  new JTextArea();
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
		
		btnRoom.setBorder(BorderFactory.createLoweredBevelBorder());
		btnAll.setBorder(BorderFactory.createRaisedBevelBorder());
		add(btnRoom);
		add(btnAll);
		
		exitBtn = new JButton("������");
		exitBtn.setBounds(0, 480, 150, 50);
		exitBtn.addActionListener(this);
		
		add(exitBtn);
		
		Image img = Toolkit.getDefaultToolkit().getImage("big6.png");
		img = img.getScaledInstance(15, 15, Image.SCALE_DEFAULT);
		setIconImage(img);
		
		btnConfig = new JButton("����");
		btnConfig.addActionListener(this);
		btnConfig.setBounds(150, 480, 150, 50);
		btnSave = new JButton("��ȭ���� ����");
		btnSave.addActionListener(this);
		btnSave.setBounds(300, 480, 150, 50);
		
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
				chatClientPrac.exitRoom(roomNum);
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
	public void chgRoomName(String newRoomName){
//		setTitle("ä�ù� : "+name+" ( ä�ø� : "+id+" ) - "+roomNum);
//		setTitle(getTitle().substring(0,getTitle().indexOf(roomName))+newRoomName+getTitle().substring(getTitle().indexOf(" ( ä�ø� : ")));
		chatClientPrac.chatInRoom("/chgRoomName "+roomName+" "+newRoomName, roomNum);
//		setRoomName(newRoomName);
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
		return chatClientPrac;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public void receiveFileChk(String[] split){
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
			chatClientPrac.receiveFile(split,filechooser2.getSelectedFile().getAbsolutePath());
		}
	}
	public void completeSend(String[] split){
		JOptionPane.showMessageDialog(this, split[1]+"���� ����("+split[4].substring(split[4].lastIndexOf(File.separator)+1)+") ������ �Ϸ��߽��ϴ�.");
	}
	public void completeReceive(String[] split){
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
			int okChk = JOptionPane.NO_OPTION;
			if(ta.getText().length()!=0){
				okChk = JOptionPane.showConfirmDialog(null, "ä�ù� ��ȭ ������ �����Ͻðڽ��ϱ�?");
			}
			if(okChk==JOptionPane.OK_OPTION){
				FileWriter fwExit = null;
				PrintWriter pwExit = null;
				
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter efilter = new FileNameExtensionFilter("�ؽ�Ʈ ����", "txt");
				filechooser.setFileFilter(efilter);
				filechooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
				filechooser.setSelectedFile(new File("chat_room_"+roomName+"_"+new SimpleDateFormat("yyyyMMddhhmmss").format(System.currentTimeMillis())+".txt"));
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
					chatClientPrac.exitRoom(roomNum);
					dispose();
				}
			}else if(okChk==JOptionPane.NO_OPTION){
				chatClientPrac.exitRoom(roomNum);
				dispose();
			}
			
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
		}else if(e.getSource()==btnConfig){
			if(chatClientPrac.getId().equals(tableOwner.getValueAt(0, 0))){
				new RoomConfigOwnerDialog(this);
			}else{
				new RoomConfigDialog(this);
			}
		}else{						//��ȭ�� �ؽ�Ʈ �ʵ��� �޽����� ����
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
						chatClientPrac.chatInRoom(tf.getText(),roomNum);
					}else{
						if(tf.getText().split(" ")[1].equals(chatClientPrac.getId())){
							ta.append("[ȥ�㸻] "+chatClientPrac.getId()+" : "+tf.getText().substring(tf.getText().indexOf(chatClientPrac.getId())+chatClientPrac.getId().length()+1));
							ta.setCaretPosition(ta.getDocument().getLength());
						}else{
							ta.append(tf.getText().split(" ")[1]+"�� �ش��ϴ� ����� ���̵� �����ϴ�.\n");
							ta.setCaretPosition(ta.getDocument().getLength());
							
						}
					}
				}
			}else{
				chatClientPrac.chatInRoom("//"+tf.getText(),roomNum);
			}
			tf.setText("");;
			tf.requestFocus();
		}
		
	}
}
