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

public class ChatInviteRoomDetailDialog extends JDialog implements ActionListener{
	ChatClientPrac chatClientPrac;
	JLabel lblNumAndName, lblOwner;
	
	JTable tableParticipants;
	DefaultTableModel modelParticipants;
	String[] headerParticipants = {"ä�ù� ������"};
	String[][] contentParticipants = {};
	
	JButton btnOk, btnCancel;
	String[] split;
	String roomNum;
	String inviteFrom;
	public ChatInviteRoomDetailDialog(ChatClientPrac chatClientPrac,String[] split) {
		super(chatClientPrac);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLayout(null);
		this.chatClientPrac = chatClientPrac;
		
		this.split = split.clone();
		roomNum = split[1];
		inviteFrom = split[3];
		lblNumAndName = new JLabel("["+roomNum+"] "+split[2]);
		lblOwner = new JLabel("���� : " + split[4]);
		lblNumAndName.setBounds(0, 0, 380, 50);
		lblOwner.setBounds(0, 50, 380, 50);
		
		modelParticipants = new DefaultTableModel(contentParticipants, headerParticipants);
		tableParticipants = new JTable(modelParticipants);
		JScrollPane pane_tableParticipants = new JScrollPane(tableParticipants);
		pane_tableParticipants.setBounds(0, 100, 380, 200);
		for (int i = 5; i < split.length; i++) {
			modelParticipants.addRow(new String[]{split[i]});
		}
		
		
		
		btnOk = new JButton("����");
		btnCancel = new JButton("����");
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
		
		JLabel lblInviteFrom = new JLabel(inviteFrom+"�Բ��� ��ȭ�濡 �ʴ��ϼ̽��ϴ�");
		lblInviteFrom.setBounds(0, 300, 380, 30);
		
		setSize(400,500);
		btnOk.setBounds(80, 330, 100, 30);
		btnCancel.setBounds(180, 330, 100, 30);
		
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
			chatClientPrac.sendMsgEnterRoom(roomNum);
			dispose();
		}else if(e.getSource() == btnCancel){
			chatClientPrac.sendMsgRejectInvite(roomNum,inviteFrom);
			dispose();
		}
	}
	public String[] getSplit() {
		return split;
	}
	
}
