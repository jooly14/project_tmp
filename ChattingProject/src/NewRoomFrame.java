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
	
	String roomNum;
	String roomName;
	
	ChatClientPrac chatClientPrac;
	public NewRoomFrame(ChatClientPrac chatClientPrac, String roomName) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		JPopupMenu popupMenu2 = new JPopupMenu();	//��ȭ�� ����� ���̺��� ���
		JMenuItem item2_1 = new JMenuItem("�ӼӸ� ����");
		JMenuItem item2_2 = new JMenuItem("�ӼӸ� ���� ����");
		JMenuItem item2_3 = new JMenuItem("���� ����");
		
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
				
			}
		});
		
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
	
	public void receiveFileChk(String[] split){
		int chkReceive = JOptionPane.showConfirmDialog(this, split[3]+"�Բ��� ������ ����("+split[2]+")�� ���۹����ðڽ��ϱ�?", "", JOptionPane.OK_CANCEL_OPTION);
		if(chkReceive == JOptionPane.OK_OPTION){
			chatClientPrac.receiveFile(split);
		}
	}
	public void completeSend(String[] split){
		JOptionPane.showMessageDialog(this, split[1]+"���� ����("+split[2]+") ������ �Ϸ��߽��ϴ�.");
	}
	public void completeReceive(String[] split){
		JOptionPane.showMessageDialog(this, split[3]+"���Լ� ����("+split[2]+") ������ �Ϸ��߽��ϴ�.");
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
		}else{						//��ȭ�� �ؽ�Ʈ �ʵ��� �޽����� ����
			chatClientPrac.chatInRoom(tf.getText(),roomNum);
			tf.selectAll();
		}
		
	}
}
