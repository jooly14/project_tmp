package client;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class WaitingRoomConfigDialog extends JDialog implements ActionListener{
	ChatClient chatClientPrac;
	JTextField twId;
	JButton btnChgId;
	
	JCheckBox msgAlarmCheckBox;
	JButton btnExit;
	public WaitingRoomConfigDialog(ChatClient chatClientPrac) {
		super(chatClientPrac,true);
		this.chatClientPrac = chatClientPrac;
		setSize(300,300);
		setLayout(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		JLabel lblTitle = new JLabel("<< 대기실 설정 >>");
		lblTitle.setBounds(0, 0, 280, 50);
		
		JLabel lblId = new JLabel("아이디 변경 : ");
		twId = new JTextField(chatClientPrac.getId());
		lblId.setBounds(0,50,100,30);
		twId.setBounds(100,50,150,30);
		btnChgId = new JButton("변경");
		btnChgId.setBounds(250,50,30,30);
		btnChgId.setMargin(new Insets(0, 0, 0, 0));
		btnChgId.addActionListener(this);
		
		msgAlarmCheckBox = new JCheckBox("대기실 알림 켜기(서버알림은 OFF불가)");
		msgAlarmCheckBox.setBounds(0, 100, 280, 50);
		msgAlarmCheckBox.addActionListener(this);
		if(chatClientPrac.isMsgAlarm()){
			msgAlarmCheckBox.setSelected(true);
		}else{
			msgAlarmCheckBox.setSelected(false);
		}
		
		btnExit = new JButton("닫기");
		btnExit.setBounds(100, 200, 100, 30);
		btnExit.addActionListener(this);		
		
		add(btnExit);
		add(msgAlarmCheckBox);
		add(btnChgId);
		add(lblTitle);
		add(lblId);
		add(twId);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== btnChgId){
			if(twId.getText().length()!=0&&!twId.getText().equals(chatClientPrac.getId())){	//빈 문자나 원래 아이디 그대로 변경하는 경우 작동 안함
				boolean sameId = false;
				for(int i =0; i < chatClientPrac.getModelAll().getRowCount();i++){
					if(chatClientPrac.getModelAll().getValueAt(i, 0).equals(twId.getText())){
						sameId = true;
						break;
					}
				}
				if(sameId){	//같은 아이디가 있는 경우 변경 불가
					JOptionPane.showMessageDialog(this, "해당 아이디가 이미 존재합니다.");
				}else{
					chatClientPrac.chgId(twId.getText());
					JOptionPane.showMessageDialog(this, "변경되었습니다.");
				}
			}
		}else if(e.getSource()==msgAlarmCheckBox){
			if(msgAlarmCheckBox.isSelected()){
				chatClientPrac.setMsgAlarm(true);
			}else{
				chatClientPrac.setMsgAlarm(false);
			}
		}else if(e.getSource() == btnExit){
			dispose();
		}
	}
}
