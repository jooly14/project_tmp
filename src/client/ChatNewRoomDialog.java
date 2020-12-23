package client;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
//새로운 대화방 생성
//ChatClient에서 newRoomBtn 클릭시 호출됨
public class ChatNewRoomDialog extends JDialog implements ActionListener{
	JTextField tfname;	//대화방 이름
	JButton btnOk, btnCancel;
	ChatClient chatClient;	
	
	JCheckBox secretChkBox;	//비밀방 설정 여부
	JPasswordField pwField;	//비말방 설정시 비밀번호
	public ChatNewRoomDialog(ChatClient chatClient) {
		super(chatClient,true);
		this.chatClient = chatClient;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setLayout(null);
		JLabel lblTitle = new JLabel("대화방 생성");
		lblTitle.setFont(new Font(null, Font.BOLD, 25));
		add(lblTitle);
		JLabel lblname = new JLabel("대화방 이름");
		tfname = new JTextField();
		tfname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent e) {	//대화방 이름은 30자 제한
				if(tfname.getText().length()>30){
					tfname.setText(tfname.getText().substring(0, 30));
				}
				
			};
		});
		
		secretChkBox = new JCheckBox("비밀번호 설정");
		pwField = new JPasswordField();
		pwField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_SPACE&&pwField.getPassword().length>0){
					pwField.setText(String.valueOf(pwField.getPassword()).substring(0,String.valueOf(pwField.getPassword()).indexOf(" ")));
				}
				if(pwField.getPassword().length>20){
					pwField.setText(String.valueOf(pwField.getPassword()).substring(0,20));
				}
			}
		});
		
		secretChkBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(secretChkBox.isSelected()){	//비밀방으로 설정한 경우에만 비밀번호 필드가 보여짐
					add(pwField);
				}else{
					remove(pwField);
				}
				repaint();
				revalidate();
			}
		});
		
		btnOk = new JButton("생성");
		btnCancel = new JButton("취소");
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
		
		Image img = Toolkit.getDefaultToolkit().getImage("big6.png");
		img = img.getScaledInstance(15, 15, Image.SCALE_DEFAULT);
		setIconImage(img);
		
		setSize(380,200);
		lblTitle.setBounds(120,0,200,40);
		lblname.setBounds(30,40,80,30);
		tfname.setBounds(150, 40, 200, 30);
		secretChkBox.setBounds(10, 80, 130, 30);
		pwField.setBounds(150,80,200,30);
		btnOk.setBounds(80, 120, 100, 30);
		btnCancel.setBounds(180, 120, 100, 30);
		getContentPane().setBackground(Color.WHITE);
		btnCancel.setBackground(Color.WHITE);
		btnOk.setBackground(Color.WHITE);
		secretChkBox.setBackground(Color.WHITE);
		
		add(secretChkBox);
		add(lblname);
		add(tfname);
		add(btnOk);
		add(btnCancel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnOk){
			if(tfname.getText().length()==0){
				JOptionPane.showMessageDialog(this, "대화방 이름을 입력하세요");
				tfname.selectAll();
				tfname.requestFocus();
			}else{
				if(secretChkBox.isSelected()){
					if(pwField.getPassword().length==0){
						JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
						pwField.requestFocus();
					}else if(String.valueOf(pwField.getPassword()).indexOf(" ")!=-1){
						JOptionPane.showMessageDialog(this, "비밀번호에는 공백을 포함할 수 없습니다");
						pwField.selectAll();
						pwField.requestFocus();
					}else{
						chatClient.createSecretRoom(tfname.getText(),pwField.getPassword());
						dispose();
					}
				}else{
					chatClient.createRoom(tfname.getText());
					dispose();
				}
			}
		}else if(e.getSource()==btnCancel){
			dispose();
		}
			
	}
}
