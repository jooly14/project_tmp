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
//���� ����
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
		JLabel lblTitle = new JLabel("���� ����");
		lblTitle.setFont(new Font(null, Font.BOLD, 20));
		lblTitle.setBounds(0, 0, 280, 50);
		
		JLabel lblId = new JLabel("���̵� ���� : ");
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
		btnChgId = new JButton("����");
		btnChgId.setBounds(240,50,40,30);
		btnChgId.setMargin(new Insets(0, 0, 0, 0));
		btnChgId.addActionListener(this);
		
		msgAlarmCheckBox = new JCheckBox("���� �˸� �ѱ�(�����˸��� OFF�Ұ�)");
		msgAlarmCheckBox.setBounds(0, 80, 280, 50);
		msgAlarmCheckBox.addActionListener(this);
		if(chatClient.isMsgAlarm()){
			msgAlarmCheckBox.setSelected(true);
		}else{
			msgAlarmCheckBox.setSelected(false);
		}
		
		btnExit = new JButton("�ݱ�");
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
			if(tfId.getText().length()!=0&&!tfId.getText().equals(chatClient.getId())){	//�� ���ڳ� ���� ���̵� �״�� �����ϴ� ��� �۵� ����
				boolean sameId = false;
				for(int i =0; i < chatClient.getModelAll().getRowCount();i++){
					if(chatClient.getModelAll().getValueAt(i, 0).equals(tfId.getText())){
						sameId = true;
						break;
					}
				}
				if(sameId){	//���� ���̵� �ִ� ��� ���� �Ұ�
					JOptionPane.showMessageDialog(this, "�ش� ���̵� �̹� �����մϴ�.");
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
