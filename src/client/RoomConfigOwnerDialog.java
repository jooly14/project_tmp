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
//������ ��ȭ�濡�� ������ Ŭ���� ���
public class RoomConfigOwnerDialog extends JDialog implements ActionListener{
	NewRoomFrame newRoomFrame;
	
	JCheckBox roomAlarmChkBox;	//��ȭ�� �˸� ����
	JButton btnExit;			//������ ��ư
	
	JCheckBox secretRoomChkBox;	//��й� ����
	JButton btnSecretSave;		//��й� ���� ����
	JPasswordField pwField;		//��й� ��й�ȣ
	
	JTextField tfRoomName;		//�� �̸�
	JButton btnRoomNameSave;	//�� �̸� ���� ����
	boolean secretRoom;			//���� ���� ��й��̾����� ����
	public RoomConfigOwnerDialog(NewRoomFrame newRoomFrame) {
		super(newRoomFrame, true);
		this.newRoomFrame = newRoomFrame;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(290, 270);
		setLayout(null);
		pwField = new JPasswordField();	//��й�ȣ �ʵ�� ��й� ������ �ϴ� ��쿡�� ���̱�
		secretRoomChkBox = new JCheckBox("��й� ����");
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
		
		btnSecretSave = new JButton("����");
		btnSecretSave.addActionListener(this);
		add(btnSecretSave);
		
		tfRoomName = new JTextField();
		tfRoomName.setText(newRoomFrame.getRoomName());
		tfRoomName.selectAll();
		btnRoomNameSave = new JButton("����");
		btnRoomNameSave.addActionListener(this);
		add(tfRoomName);
		add(btnRoomNameSave);
		
		tfRoomName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent e) {	//��ȭ�� �̸��� 30�� ����
				if(tfRoomName.getText().length()>30){
					tfRoomName.setText(tfRoomName.getText().substring(0, 30));
				}
				
			};
		});
		
		btnExit = new JButton("�ݱ�");
		btnExit.addActionListener(this);
		
		roomAlarmChkBox = new JCheckBox("��ȭ�� �˸� ����(���� �˸�OFF�� �Ұ�)");
		roomAlarmChkBox.addActionListener(this);
		if(newRoomFrame.getRoomAlarm()){
			roomAlarmChkBox.setSelected(true);
		}else{
			roomAlarmChkBox.setSelected(false);
		}
		
		JLabel lblTitle = new JLabel("��ȭ�� ����");
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
		}else if(e.getSource() == btnRoomNameSave){		//�� �̸� ����
			if(tfRoomName.getText().length()==0){
				JOptionPane.showMessageDialog(this, "��ȭ�� �̸��� �Է��ϼ���");
			}else{
				newRoomFrame.chgRoomName(tfRoomName.getText());
			}
		}else if(e.getSource() == btnSecretSave){
			if(secretRoom){
				if(secretRoomChkBox.isSelected()){
					if(pwField.getPassword().length==0){
						JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է��ϼ���");
					}else{
						newRoomFrame.getChatClientPrac().chatInRoom("/chgRoomPassWord "+String.valueOf(pwField.getPassword()), newRoomFrame.getRoomNum());
					}
				}else{
					newRoomFrame.getChatClientPrac().chatInRoom("/cancelSecretRoom", newRoomFrame.getRoomNum());
				}
			}else{
				if(secretRoomChkBox.isSelected()){
					if(pwField.getPassword().length==0){
						JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է��ϼ���");
					}else{
						newRoomFrame.getChatClientPrac().chatInRoom("/chgRoomPassWord "+String.valueOf(pwField.getPassword()), newRoomFrame.getRoomNum());
					}
				}else{
					//���� ��й��� �ƴϾ��µ� ��й� ������ �� �� ���
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
