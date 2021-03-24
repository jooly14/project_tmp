package client;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ChatInviteRoomDetailDialog extends JDialog implements ActionListener{
	ChatClient chatClient;
	JLabel lblNumAndName, lblOwner;
	
	JTable tableParticipants;
	DefaultTableModel modelParticipants;
	String[] headerParticipants = {"채팅방 접속자"};
	String[][] contentParticipants = {};
	
	JButton btnOk, btnCancel;
	String[] split;
	String roomNum;
	String inviteFrom;
	public ChatInviteRoomDetailDialog(ChatClient chatClient,String[] split,String roomName) {
		super(chatClient,false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLayout(null);
		this.chatClient = chatClient;
		
		this.split = split.clone();
		roomNum = split[1];
		inviteFrom = split[2];
		lblNumAndName = new JLabel("["+roomNum+"] "+roomName);
		lblOwner = new JLabel("방장 : " + split[3]);
		
		
		//값이 수정되지 않도록
		modelParticipants = new DefaultTableModel(contentParticipants, headerParticipants){
			public boolean isCellEditable(int rowIndex, int colIndex){
				return false;
			}
		};
		tableParticipants = new JTable(modelParticipants);
		JScrollPane pane_tableParticipants = new JScrollPane(tableParticipants);
		
		for (int i = 4; i < split.length; i++) {
			modelParticipants.addRow(new String[]{split[i]});
		}
		tableParticipants.getTableHeader().setReorderingAllowed(false);
		tableParticipants.getTableHeader().setResizingAllowed(false);
		tableParticipants.setRowSorter(new TableRowSorter<DefaultTableModel>(modelParticipants));
		
		
		
		btnOk = new JButton("입장");
		btnCancel = new JButton("거절");
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
		
		JLabel lblInviteFrom = new JLabel(inviteFrom+"님께서 대화방에 초대하셨습니다");
		
		
		setSize(300,370);
		JLabel lblTitle = new JLabel("대화방 초대");
		lblTitle.setFont(new Font(null,Font.BOLD,30));
		lblTitle.setBounds(0,0,280,50);
		lblNumAndName.setBounds(0, 50, 280, 30);
		lblOwner.setBounds(0, 80, 280, 30);
		pane_tableParticipants.setBounds(0, 110, 280, 150);
		lblInviteFrom.setBounds(0, 260, 380, 30);
		lblInviteFrom.setForeground(Color.GREEN);
		btnOk.setBounds(80, 290, 100, 30);
		btnCancel.setBounds(180, 290, 100, 30);
		getContentPane().setBackground(Color.white);
		btnOk.setBackground(Color.white);
		btnCancel.setBackground(Color.white);
		pane_tableParticipants.getViewport().setBackground(Color.WHITE);
		tableParticipants.getTableHeader().setBackground(Color.WHITE);
		
		add(lblTitle);
		add(lblInviteFrom);
		add(btnOk);
		add(btnCancel);
		add(lblNumAndName);
		add(lblOwner);
		add(pane_tableParticipants);
		
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnOk){
			chatClient.sendMsgEnterRoom(roomNum);
			dispose();
		}else if(e.getSource() == btnCancel){
			chatClient.sendMsgRejectInvite(roomNum,inviteFrom);
			dispose();
		}
	}
	public String[] getSplit() {
		return split;
	}
	
}
