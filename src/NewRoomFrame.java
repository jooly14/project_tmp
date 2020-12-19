import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
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
import javax.swing.table.TableModel;

public class NewRoomFrame extends JFrame implements ActionListener{
	JTextArea ta;
	JTextField tf;
	JTable tableRoom;
	DefaultTableModel modelRoom;
	String[] headerRoom = {"채팅방 접속자"};
	String[][] contentRoom = {};
	JScrollPane pane_tableRoom;
	
	JTable tableAll;
	DefaultTableModel modelAll;
	String[] headerAll = {"전체 접속자"};
	String[][] contentAll = {};
	JScrollPane pane_tableAll;
	
	JTable tableOwner;
	DefaultTableModel modelOwner;
	String[] headerOwner = {"방장"};
	String[][] contentOwner = {};
	JScrollPane pane_tableOwner;
	
	
	
	JButton exitBtn;
	JButton btnRoom, btnAll;
	
	String roomNum;
	String roomName;
	
	ChatClientPrac chatClientPrac;
	public NewRoomFrame(ChatClientPrac chatClientPrac, String roomName) {
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
		pane_tableRoom = new JScrollPane(tableRoom);
		pane_tableRoom.setBounds(500, 90, 100, 400);
		
		modelAll = new DefaultTableModel(contentAll, headerAll){
			public boolean isCellEditable(int rowIndex, int colIndex){
				return false;
			}
		};
		tableAll = new JTable(modelAll);
		tableAll.getTableHeader().setReorderingAllowed(false);
		tableAll.getTableHeader().setResizingAllowed(false);
		pane_tableAll = new JScrollPane(tableAll);
		pane_tableAll.setBounds(500, 90, 100, 400);
		JPopupMenu popupMenu1 = new JPopupMenu();	//전체 사용자 테이블에서 사용
		JMenuItem item1_1 = new JMenuItem("귓속말 차단");
		JMenuItem item1_2 = new JMenuItem("귓속말 차단 해제");
		JPopupMenu popupMenu2 = new JPopupMenu();	//대화방 사용자 테이블에서 사용
		JMenuItem item2_1 = new JMenuItem("귓속말 차단");
		JMenuItem item2_2 = new JMenuItem("귓속말 차단 해제");
		JMenuItem item2_3 = new JMenuItem("방장 변경");
		
		item1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int okCancelChk = JOptionPane.showConfirmDialog(null, tableAll.getValueAt(tableAll.getSelectedRow(), 0)+"님의 귓속말을 차단하시겠습니까?", "", JOptionPane.OK_CANCEL_OPTION);
				if(okCancelChk==JOptionPane.OK_OPTION){
					chatClientPrac.getBlockNameList().add(tableAll.getValueAt(tableAll.getSelectedRow(), 0).toString());
					JOptionPane.showMessageDialog(null, "귓속말 차단되었습니다.");
				}
			}
		});
		item1_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int okCancelChk = JOptionPane.showConfirmDialog(null, tableAll.getValueAt(tableAll.getSelectedRow(), 0)+"님의 귓속말 차단을 해제하시겠습니까?", "", JOptionPane.OK_CANCEL_OPTION);
				if(okCancelChk==JOptionPane.OK_OPTION){
					chatClientPrac.getBlockNameList().remove(tableAll.getValueAt(tableAll.getSelectedRow(), 0).toString());
					JOptionPane.showMessageDialog(null, "귓속말 차단 해제되었습니다.");
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
				int okCancelChk = JOptionPane.showConfirmDialog(null, tableRoom.getValueAt(tableRoom.getSelectedRow(), 0)+"님의 귓속말을 차단하시겠습니까?", "", JOptionPane.OK_CANCEL_OPTION);
				if(okCancelChk==JOptionPane.OK_OPTION){
					chatClientPrac.getBlockNameList().add(tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).toString());
					JOptionPane.showMessageDialog(null, "귓속말 차단되었습니다.");
				}
			}
		});
		item2_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int okCancelChk = JOptionPane.showConfirmDialog(null, tableRoom.getValueAt(tableRoom.getSelectedRow(), 0)+"님의 귓속말 차단을 해제하시겠습니까?", "", JOptionPane.OK_CANCEL_OPTION);
				if(okCancelChk==JOptionPane.OK_OPTION){
					chatClientPrac.getBlockNameList().remove(tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).toString());
					JOptionPane.showMessageDialog(null, "귓속말 차단 해제되었습니다.");
				}
			}
		});
		item2_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int chkOkCancel = JOptionPane.showConfirmDialog(null, tableRoom.getValueAt(tableRoom.getSelectedRow(), 0)+"님에게 방장 자격을 주시겠습니까?", "",  JOptionPane.OK_CANCEL_OPTION);
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
			}
		});
		tableRoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableRoom.setRowSelectionInterval(tableRoom.rowAtPoint(e.getPoint()), tableRoom.rowAtPoint(e.getPoint()));
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
		
		btnRoom = new JButton("<html>채팅방<br>접속자</html>");
		btnAll = new JButton("<html>전체<br>접속자</html>");
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
		
		exitBtn = new JButton("나가기");
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
				chatClientPrac.exitRoom(roomNum);
			}
		});
		
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==exitBtn){	//대화방 나가기 버튼
			chatClientPrac.exitRoom(roomNum);
			dispose();
		}else if(e.getSource()==btnAll){	//모든 유저 리스트 보기
			btnRoom.setBorder(BorderFactory.createRaisedBevelBorder());
			btnAll.setBorder(BorderFactory.createLoweredBevelBorder());
			remove(pane_tableRoom);
			add(pane_tableAll);			
		}else if(e.getSource()==btnRoom){	//대화방 유저 리스트 보기
			btnRoom.setBorder(BorderFactory.createLoweredBevelBorder());
			btnAll.setBorder(BorderFactory.createRaisedBevelBorder());
			remove(pane_tableAll);
			add(pane_tableRoom);			
		}else{						//대화방 텍스트 필드의 메시지를 전송
			chatClientPrac.chatInRoom(tf.getText(),roomNum);
			tf.selectAll();
		}
		
	}
}
