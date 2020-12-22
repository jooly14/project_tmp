package client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RoomConfigOwnerDialog extends JDialog implements ActionListener{
	NewRoomFrame newRoomFrame;
	
	JCheckBox roomAlarmChkBox;
	JButton btnSave;
	
	JCheckBox secretRoomChkBox;
	JButton btnSecretSave;
	JPasswordField pwField;
	
	JTextField tfRoomName;
	JButton btnRoomNameSave;
	boolean secretRoom;
	public RoomConfigOwnerDialog(NewRoomFrame newRoomFrame) {
		super(newRoomFrame, true);
		this.newRoomFrame = newRoomFrame;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(300, 500);
		setLayout(null);
		secretRoomChkBox = new JCheckBox("비밀방 설정");
		for (int i = 0; i < newRoomFrame.getChatClientPrac().getTableRoom().getRowCount(); i++) {
			if(newRoomFrame.getChatClientPrac().getTableRoom().getValueAt(i, 0).equals(newRoomFrame.getRoomNum())){
				secretRoom = newRoomFrame.getChatClientPrac().getTableRoom().getValueAt(i, 2).equals("YES");
				if(secretRoom){
					secretRoomChkBox.setSelected(true);
					add(pwField);
				}else{
					secretRoomChkBox.setSelected(false);
				}
			}
		}
		secretRoomChkBox.addActionListener(this);
		
		
		btnSecretSave = new JButton("저장");
		btnSecretSave.addActionListener(this);
		pwField = new JPasswordField();
		
		tfRoomName = new JTextField();
		tfRoomName.setText(newRoomFrame.getRoomName());
		tfRoomName.selectAll();
		btnRoomNameSave = new JButton("저장");
		btnRoomNameSave.addActionListener(this);
		
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
		}else if(e.getSource() == btnRoomNameSave){
			if(tfRoomName.getText().length()==0){
				JOptionPane.showMessageDialog(this, "대화방 이름을 입력하세요");
			}else{
				newRoomFrame.chgRoomName(tfRoomName.getText());
			}
		}else if(e.getSource() == btnSecretSave){
			if(secretRoom){
				if(secretRoomChkBox.isSelected()){
					if(pwField.getPassword().length==0){
						JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
					}else{
						newRoomFrame.getChatClientPrac().chatInRoom("/chgRoomPassWord "+String.valueOf(pwField.getPassword()), newRoomFrame.getRoomNum());
					}
				}else{
					newRoomFrame.getChatClientPrac().chatInRoom("/cancelSecretRoom", newRoomFrame.getRoomNum());
				}
			}else{
				if(secretRoomChkBox.isSelected()){
					if(pwField.getPassword().length==0){
						JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
					}else{
						newRoomFrame.getChatClientPrac().chatInRoom("/chgRoomPassWord "+String.valueOf(pwField.getPassword()), newRoomFrame.getRoomNum());
					}
				}else{
					//원래 비밀방이 아니었는데 비밀방 설정을 안 한 경우
				}
			}
		}else if(e.getSource() == secretRoomChkBox){
			if(secretRoomChkBox.isSelected()){
				add(pwField);
			}else{
				remove(pwField);
			}
			repaint();
			revalidate();
		}
	}
	
}
