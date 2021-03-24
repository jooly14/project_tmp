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

public class ChatRoomDetailDialog extends JDialog implements ActionListener{
	ChatClientPrac chatClientPrac;
	JLabel lblNumAndName, lblOwner;
	
	JTable tableParticipants;
	DefaultTableModel modelParticipants;
	String[] headerParticipants = {"채팅방 접속자"};
	String[][] contentParticipants = {};
	
	JButton btnOk, btnCancel;
	JPasswordField pwField;
	JLabel lblPassword;
	String[] split;
	String secret;
	String roomNum;
	public ChatRoomDetailDialog(ChatClientPrac chatClientPrac,String[] split) {
		super(chatClientPrac,true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLayout(null);
		this.chatClientPrac = chatClientPrac;
		
		this.split = split.clone();
		roomNum = split[1];
		lblNumAndName = new JLabel("["+roomNum+"] "+split[2]);
		lblOwner = new JLabel("방장 : " + split[4]);
		lblNumAndName.setBounds(0, 0, 380, 50);
		lblOwner.setBounds(0, 50, 380, 50);
		
		modelParticipants = new DefaultTableModel(contentParticipants, headerParticipants);
		tableParticipants = new JTable(modelParticipants);
		JScrollPane pane_tableParticipants = new JScrollPane(tableParticipants);
		pane_tableParticipants.setBounds(0, 100, 380, 200);
		for (int i = 5; i < split.length; i++) {
			modelParticipants.addRow(new String[]{split[i]});
		}
		
		
		btnOk = new JButton("입장");
		btnCancel = new JButton("취소");
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
		
		
		add(btnOk);
		add(btnCancel);
		add(lblNumAndName);
		add(lblOwner);
		add(pane_tableParticipants);
		
		secret = split[3];
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
		lblPassword = new JLabel("비밀번호 : ");
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
	public void correctPassword(){
		dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnOk){
			if(secret.equals("YES")){
				if(pwField.getPassword().length==0){
					JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
					pwField.requestFocus();
				}else{
					chatClientPrac.sendMsgEnterSecretRoom(roomNum,pwField.getPassword());
					dispose();
				}
			}else{
				chatClientPrac.sendMsgEnterRoom(roomNum);
				dispose();
			}
		}else if(e.getSource() == btnCancel){
			dispose();
		}
	}
	public String[] getSplit() {
		return split;
	}
	
}
