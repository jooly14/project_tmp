package client;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
//대기실 설정
public class WaitingRoomConfigDialog extends JDialog implements ActionListener{
	ChatClient chatClient;
	JTextField tfId;
	JButton btnChgId;
	
	JCheckBox msgAlarmCheckBox;
	JButton btnExit;
	public WaitingRoomConfigDialog(ChatClient chatClient) {
		super(chatClient,true);
		this.chatClient = chatClient;
		setSize(300,210);
		setLayout(null);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		JLabel lblTitle = new JLabel("대기실 설정");
		lblTitle.setFont(new Font(null, Font.BOLD, 20));
		lblTitle.setBounds(0, 0, 280, 50);
		
		JLabel lblId = new JLabel("아이디 변경 : ");
		tfId = new JTextField(chatClient.getId());
		tfId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(tfId.getText().length()>20){
					tfId.setText(tfId.getText().substring(0,20));
				}
				if(tfId.getText().indexOf(" ")!=-1){
					tfId.setText(tfId.getText().substring(0,tfId.getText().indexOf(" ")));
				}
			}
		});
		lblId.setBounds(0,50,90,30);
		tfId.setBounds(90,50,150,30);
		btnChgId = new JButton("변경");
		btnChgId.setBounds(240,50,40,30);
		btnChgId.setMargin(new Insets(0, 0, 0, 0));
		btnChgId.addActionListener(this);
		
		msgAlarmCheckBox = new JCheckBox("대기실 알림 켜기(서버알림은 OFF불가)");
		msgAlarmCheckBox.setBounds(0, 80, 280, 50);
		msgAlarmCheckBox.addActionListener(this);
		if(chatClient.isMsgAlarm()){
			msgAlarmCheckBox.setSelected(true);
		}else{
			msgAlarmCheckBox.setSelected(false);
		}
		
		btnExit = new JButton("닫기");
		btnExit.setBounds(90, 130, 100, 30);
		btnExit.addActionListener(this);	
		btnExit.setBackground(Color.WHITE);
		btnChgId.setBackground(Color.white);
		getContentPane().setBackground(Color.white);
		msgAlarmCheckBox.setBackground(Color.WHITE);
		
		add(btnExit);
		add(msgAlarmCheckBox);
		add(btnChgId);
		add(lblTitle);
		add(lblId);
		add(tfId);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== btnChgId){
			if(tfId.getText().length()!=0&&!tfId.getText().equals(chatClient.getId())){	//빈 문자나 원래 아이디 그대로 변경하는 경우 작동 안함
				boolean sameId = false;
				for(int i =0; i < chatClient.getModelAll().getRowCount();i++){
					if(chatClient.getModelAll().getValueAt(i, 0).equals(tfId.getText())){
						sameId = true;
						break;
					}
				}
				if(sameId){	//같은 아이디가 있는 경우 변경 불가
					JOptionPane.showMessageDialog(this, "해당 아이디가 이미 존재합니다.");
				}else{
					chatClient.chgId(tfId.getText());
				}
			}
		}else if(e.getSource()==msgAlarmCheckBox){
			if(msgAlarmCheckBox.isSelected()){
				chatClient.setMsgAlarm(true);
			}else{
				chatClient.setMsgAlarm(false);
			}
		}else if(e.getSource() == btnExit){
			dispose();
		}
	}
}
