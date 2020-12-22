package client;
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
	String[] headerParticipants = {"ä�ù� ������"};
	String[][] contentParticipants = {};
	
	JButton btnOk, btnCancel;
	String[] split;
	String roomNum;
	String inviteFrom;
	public ChatInviteRoomDetailDialog(ChatClient chatClient,String[] split,String roomName) {
		super(chatClient);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLayout(null);
		this.chatClient = chatClient;
		
		this.split = split.clone();
		roomNum = split[1];
		inviteFrom = split[2];
		lblNumAndName = new JLabel("["+roomNum+"] "+roomName);
		lblOwner = new JLabel("���� : " + split[3]);
		lblNumAndName.setBounds(0, 0, 380, 50);
		lblOwner.setBounds(0, 50, 380, 50);
		
		//���� �������� �ʵ���
		modelParticipants = new DefaultTableModel(contentParticipants, headerParticipants){
			public boolean isCellEditable(int rowIndex, int colIndex){
				return false;
			}
		};
		tableParticipants = new JTable(modelParticipants);
		JScrollPane pane_tableParticipants = new JScrollPane(tableParticipants);
		pane_tableParticipants.setBounds(0, 100, 380, 200);
		for (int i = 4; i < split.length; i++) {
			modelParticipants.addRow(new String[]{split[i]});
		}
		tableParticipants.getTableHeader().setReorderingAllowed(false);
		tableParticipants.getTableHeader().setResizingAllowed(false);
		tableParticipants.setRowSorter(new TableRowSorter<DefaultTableModel>(modelParticipants));
		
		
		
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
