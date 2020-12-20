import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ChatNewRoomDialog extends JDialog implements ActionListener{
	JTextField tfname;
	JButton btnOk, btnCancel;
	ChatClientPrac chatClientPrac;
	
	JCheckBox secretChkBox;
	JPasswordField pwField;
	public ChatNewRoomDialog(ChatClientPrac chatClientPrac) {
		super(chatClientPrac,true);
		this.chatClientPrac = chatClientPrac;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLayout(null);
		JLabel lblTitle = new JLabel("<< 대화방 생성 >>");
		add(lblTitle);
		JLabel lblname = new JLabel("대화방 이름");
		tfname = new JTextField();
		
		secretChkBox = new JCheckBox("비밀번호 설정");
		pwField = new JPasswordField();
		secretChkBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(secretChkBox.isSelected()){
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
		lblTitle.setBounds(120,0,150,30);
		lblname.setBounds(30,40,80,30);
		tfname.setBounds(150, 40, 200, 30);
		secretChkBox.setBounds(10, 80, 130, 30);
		pwField.setBounds(150,80,200,30);
		btnOk.setBounds(80, 120, 100, 30);
		btnCancel.setBounds(180, 120, 100, 30);
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
			}else{
				if(secretChkBox.isSelected()){
					if(pwField.getPassword().length==0){
						JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
					}else{
						chatClientPrac.createSecretRoom(tfname.getText(),pwField.getPassword());
						dispose();
					}
				}else{
					chatClientPrac.createRoom(tfname.getText());
					dispose();
				}
			}
		}else if(e.getSource()==btnCancel){
			dispose();
		}
			
	}
}
