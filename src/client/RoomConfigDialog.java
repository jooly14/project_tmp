package client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class RoomConfigDialog extends JDialog implements ActionListener{
	NewRoomFrame newRoomFrame;
	
	JCheckBox roomAlarmChkBox;
	JButton btnSave;
	public RoomConfigDialog(NewRoomFrame newRoomFrame) {
		super(newRoomFrame, true);
		this.newRoomFrame = newRoomFrame;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(300, 200);
		setLayout(null);
		
		btnSave = new JButton("닫기");
		btnSave.addActionListener(this);
		
		roomAlarmChkBox = new JCheckBox("대화방 알림 끄기(서버 알림OFF는 불가)");
		roomAlarmChkBox.addActionListener(this);
		if(newRoomFrame.getRoomAlarm()){
			roomAlarmChkBox.setSelected(true);
		}else{
			roomAlarmChkBox.setSelected(false);
		}
		
		JLabel lblTitle = new JLabel("<< 대화방 설정 >>");
		
		lblTitle.setBounds(0, 0, 280, 50);
		roomAlarmChkBox.setBounds(0, 50, 280, 50);
		btnSave.setBounds(80, 100, 100, 100);
		
		add(lblTitle);
		add(roomAlarmChkBox);
		add(btnSave);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSave){
			dispose();
		}else if(e.getSource()==roomAlarmChkBox){
			if(roomAlarmChkBox.isSelected()){
				newRoomFrame.setRoomAlarm(true);
			}else{
				newRoomFrame.setRoomAlarm(false);
			}
		}
		
	}
	
}
