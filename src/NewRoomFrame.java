import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
	
	
	
	JButton exitBtn;
	JButton btnRoom, btnAll;
	
	String roomNum;
	String roomName;
	
	ChatClientPrac chatClientPrac;
	public NewRoomFrame(ChatClientPrac chatClientPrac,String roomNum, String roomName) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(820, 0);
		setSize(820,600);
		
		this.roomName = roomName;
		this.roomNum = roomNum;
		this.chatClientPrac = chatClientPrac;
		
		modelRoom = new DefaultTableModel(contentRoom, headerRoom);
		tableRoom = new JTable(modelRoom);
		pane_tableRoom = new JScrollPane(tableRoom);
		pane_tableRoom.setBounds(600, 30, 200, 500);
		
		modelAll = new DefaultTableModel(contentAll, headerAll);
		tableAll = new JTable(modelAll);
		pane_tableAll = new JScrollPane(tableAll);
		pane_tableAll.setBounds(600, 30, 200, 500);
		
		tf = new JTextField();
		tf.addActionListener(this);
		tf.setBounds(0, 400, 600, 30);
		ta =  new JTextArea();
		JScrollPane pane = new JScrollPane(ta);
		pane.setBounds(0, 0, 600, 400);
		add(pane_tableRoom);
		add(pane);
		add(tf);
		
		btnRoom = new JButton("채팅방 접속자");
		btnAll = new JButton("전체 접속자");
		btnRoom.setBounds(600, 0, 100, 30);
		btnAll.setBounds(700, 0, 100, 30);
		btnRoom.addActionListener(this);
		btnAll.addActionListener(this);
		btnRoom.setMargin(new Insets(0, 0, 0, 0));
		btnAll.setMargin(new Insets(0, 0, 0, 0));
		add(btnRoom);
		add(btnAll);
		
		exitBtn = new JButton("나가기");
		exitBtn.setBounds(0, 480, 150, 50);
		exitBtn.addActionListener(this);
		
		add(exitBtn);
		
		getContentPane().
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
	
	public JTextArea getTa() {
		return ta;
	}

	public DefaultTableModel getModelRoom() {
		return modelRoom;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==exitBtn){
			chatClientPrac.exitRoom(roomNum);
			dispose();
		}else if(e.getSource()==btnAll){
			remove(pane_tableRoom);
			add(pane_tableAll);			
		}else if(e.getSource()==btnRoom){
			remove(pane_tableAll);
			add(pane_tableRoom);			
		}else{
			chatClientPrac.chatInRoom(tf.getText(),roomNum);
			tf.selectAll();
		}
		
	}
}
