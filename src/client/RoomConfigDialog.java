package client;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
//방장이 아닌 사용자가 대화방에서 설정을 클릭하면 나타남
public class RoomConfigDialog extends JDialog implements ActionListener{
	NewRoomFrame newRoomFrame;
	
	JCheckBox roomAlarmChkBox;
	JButton btnExit;
	public RoomConfigDialog(NewRoomFrame newRoomFrame) {
		super(newRoomFrame, true);
		this.newRoomFrame = newRoomFrame;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(300, 200);
		setLayout(null);
		
		btnExit = new JButton("닫기");
		btnExit.addActionListener(this);
		
		roomAlarmChkBox = new JCheckBox("대화방 알림 끄기(서버 알림OFF는 불가)");
		roomAlarmChkBox.addActionListener(this);
		if(newRoomFrame.getRoomAlarm()){
			roomAlarmChkBox.setSelected(true);
		}else{
			roomAlarmChkBox.setSelected(false);
		}
		
		JLabel lblTitle = new JLabel("대화방 설정");
		lblTitle.setFont(new Font(null, Font.BOLD, 30));
		
		lblTitle.setBounds(0, 0, 280, 50);
		roomAlarmChkBox.setBounds(0, 50, 280, 50);
		btnExit.setBounds(85, 100, 100, 40);
		btnExit.setBackground(Color.white);
		getContentPane().setBackground(Color.white);
		roomAlarmChkBox.setBackground(Color.WHITE);
		
		add(lblTitle);
		add(roomAlarmChkBox);
		add(btnExit);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnExit){		
			dispose();
		}else if(e.getSource()==roomAlarmChkBox){	//방 알림 켜고 끄기
			if(roomAlarmChkBox.isSelected()){
				newRoomFrame.setRoomAlarm(true);
			}else{
				newRoomFrame.setRoomAlarm(false);
			}
		}
		
	}
	
}
