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

public class ChatRoomDetailDialog extends JDialog implements ActionListener{
	ChatClient chatClient;
	JLabel lblNumAndName, lblOwner;
	
	JTable tableParticipants;
	DefaultTableModel modelParticipants;
	String[] headerParticipants = {"ä�ù� ������"};
	String[][] contentParticipants = {};
	
	JButton btnOk, btnCancel;
	JPasswordField pwField;
	JLabel lblPassword;
	String[] split;
	
	String secret;	
	String roomNum;
	String roomName;
	public ChatRoomDetailDialog(ChatClient chatClient,String[] split,String roomName) {
		super(chatClient,true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLayout(null);
		this.chatClient = chatClient;
		this.roomName = roomName;
		this.split = split.clone();
		roomNum = split[1];
		
		lblNumAndName = new JLabel("["+roomNum+"] "+roomName);
		lblOwner = new JLabel("���� : " + split[3]);
		lblNumAndName.setBounds(0, 0, 380, 50);
		lblOwner.setBounds(0, 50, 380, 50);
		
		//�� ���� �ȵǵ���
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
		btnCancel = new JButton("���");
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
		
		
		add(btnOk);
		add(btnCancel);
		add(lblNumAndName);
		add(lblOwner);
		add(pane_tableParticipants);
		
		secret = split[2];
		if(secret.equals("YES")){
			secretRoom(split);
		}else{
			notSecretRoom(split);
		}
		setVisible(true);
	}
	public void secretRoom(String[] split){
		setSize(400,500);
		
		pwField = new JPasswordField();
		lblPassword = new JLabel("��й�ȣ : ");
		lblPassword.setBounds(0, 300, 80, 30);
		pwField.setBounds(100, 300, 200, 30);
		
		btnOk.setBounds(80, 330, 100, 30);
		btnCancel.setBounds(180, 330, 100, 30);
		
		add(pwField);
		add(lblPassword);
		pwField.requestFocus();
	}
	public void notSecretRoom(String[] split){
		setSize(400,450);
		btnOk.setBounds(80, 300, 100, 30);
		btnCancel.setBounds(180, 300, 100, 30);
		
	}
	public void correctPassword(){	//��� ��ȭ���� ��й�ȣ�� ��ġ�ϴ� ���
		dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnOk){
			if(secret.equals("YES")){
				if(pwField.getPassword().length==0){
					JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է��ϼ���");
					pwField.requestFocus();
				}else{
					chatClient.sendMsgEnterSecretRoom(roomNum,pwField.getPassword());
					dispose();
				}
			}else{
				chatClient.sendMsgEnterRoom(roomNum);
				dispose();
			}
		}else if(e.getSource() == btnCancel){
			dispose();
		}
	}
	public String[] getSplit() {
		return split;
	}
	public String getRoomName() {
		return roomName;
	}
	
	
}
