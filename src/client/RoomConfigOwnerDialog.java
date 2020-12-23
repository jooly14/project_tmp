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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
//방장이 대화방에서 설정을 클릭한 경우
public class RoomConfigOwnerDialog extends JDialog implements ActionListener{
	NewRoomFrame newRoomFrame;
	
	JCheckBox roomAlarmChkBox;	//대화방 알림 설정
	JButton btnExit;			//나가기 버튼
	
	JCheckBox secretRoomChkBox;	//비밀방 설정
	JButton btnSecretSave;		//비밀방 설정 저장
	JPasswordField pwField;		//비밀방 비밀번호
	
	JTextField tfRoomName;		//방 이름
	JButton btnRoomNameSave;	//방 이름 변경 저장
	boolean secretRoom;			//설정 전에 비밀방이었는지 여부
	public RoomConfigOwnerDialog(NewRoomFrame newRoomFrame) {
		super(newRoomFrame, true);
		this.newRoomFrame = newRoomFrame;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(290, 270);
		setLayout(null);
		pwField = new JPasswordField();	//비밀번호 필드는 비밀방 설정을 하는 경우에만 붙이기
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
				break;
			}
		}
		secretRoomChkBox.addActionListener(this);
		add(secretRoomChkBox);
		
		pwField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(String.valueOf(pwField.getPassword()).indexOf(" ")!=-1){
					pwField.setText(String.valueOf(pwField.getPassword()).substring(0,String.valueOf(pwField.getPassword()).indexOf(" ")));
				}
				if(String.valueOf(pwField.getPassword()).length()>20){
					pwField.setText(String.valueOf(pwField.getPassword()).substring(0,20));
				}
			}
		});
		
		btnSecretSave = new JButton("저장");
		btnSecretSave.addActionListener(this);
		add(btnSecretSave);
		
		tfRoomName = new JTextField();
		tfRoomName.setText(newRoomFrame.getRoomName());
		tfRoomName.selectAll();
		btnRoomNameSave = new JButton("저장");
		btnRoomNameSave.addActionListener(this);
		add(tfRoomName);
		add(btnRoomNameSave);
		
		tfRoomName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent e) {	//대화방 이름은 30자 제한
				if(tfRoomName.getText().length()>30){
					tfRoomName.setText(tfRoomName.getText().substring(0, 30));
				}
				
			};
		});
		
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
		tfRoomName.setBounds(0, 100, 220, 30);
		btnRoomNameSave.setBounds(220, 100, 50, 30);
		secretRoomChkBox.setBounds(0, 130, 100, 50);
		pwField.setBounds(100, 140, 120, 30);
		btnSecretSave.setBounds(220, 140, 50, 30);
		btnExit.setBounds(80, 180, 100, 40);
		btnExit.setBackground(Color.WHITE);
		btnRoomNameSave.setBackground(Color.WHITE);
		btnSecretSave.setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		secretRoomChkBox.setBackground(Color.white);
		roomAlarmChkBox.setBackground(Color.WHITE);
		
		btnRoomNameSave.setMargin(new Insets(0, 0, 0, 0));
		btnSecretSave.setMargin(new Insets(0, 0, 0, 0));
	
		add(lblTitle);
		add(roomAlarmChkBox);
		add(btnExit);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnExit){
			dispose();
		}else if(e.getSource()==roomAlarmChkBox){
			if(roomAlarmChkBox.isSelected()){
				newRoomFrame.setRoomAlarm(true);
			}else{
				newRoomFrame.setRoomAlarm(false);
			}
		}else if(e.getSource() == btnRoomNameSave){		//방 이름 변경
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
