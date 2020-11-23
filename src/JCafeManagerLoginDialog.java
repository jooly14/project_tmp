import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sun.glass.events.KeyEvent;

public class JCafeManagerLoginDialog extends JDialog implements ActionListener,KeyListener{
	JButton btnOk, btnCancel;
	String id = "0000";	//임시 아이디
	String pw = "0000";	//임시 비밀번호
	JTextField idTf;
	JPasswordField pwF;
	JCafeMain mainFrame;
	int chk;	//키리스너에서 로그인메서드 실행중에도 엔터키 누르면 또 로그인메서드 호출하는 오류 잡으려고 만듦
	
	public JCafeManagerLoginDialog(JCafeMain mainFrame){
		this.mainFrame = mainFrame;
		//this.setLocation(mainFrame.getX(),mainFrame.getY());	//화면 위치 설정
		this.setIconImage(new ImageIcon("JCafeData\\ImageData\\JCafe icon.png").getImage());
		
		JLabel title = new JLabel(" MANAGER LOGIN ");
		
		
		JPanel pnlCenter = new JPanel(new GridLayout(0,1));
		JPanel pnlId = new JPanel();
		JPanel pnlPw = new JPanel();
		idTf = new JTextField(13);
		pwF = new JPasswordField(13);
		idTf.addKeyListener(this);
		pwF.addKeyListener(this);
		
		
		JLabel idlbl = new JLabel("아이디      :      ");
		JLabel pwlbl = new JLabel("비밀번호   :      ");
		
		pnlId.add(idlbl);
		pnlId.add(idTf);
		pnlPw.add(pwlbl);
		pnlPw.add(pwF);
		
		btnOk = new JButton("로그인");
		btnCancel = new JButton("취소");
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
		
		JPanel pnlOKCancel = new JPanel();
		pnlOKCancel.add(btnOk);
		pnlOKCancel.add(new JLabel("    "));
		pnlOKCancel.add(btnCancel);
		
		
		//데코 부분
		this.setUndecorated(true);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(0x003E00));
		pnlId.setBackground(null);
		pnlPw.setBackground(null);
		pnlOKCancel.setBackground(null);
		btnOk.setPreferredSize(new Dimension(120,30));
		btnCancel.setPreferredSize(new Dimension(120,30));
		btnOk.setBackground(Color.white);
		btnCancel.setBackground(Color.white);
		btnOk.setForeground(new Color(0x003E00));
		btnCancel.setForeground(new Color(0x003E00));
		title.setBounds(15,40,400,50);
		pnlId.setBounds(0,140,380,50);
		pnlPw.setBounds(0,200,380,50);
		pnlOKCancel.setBounds(0,280,380,60);
		idTf.setBorder(BorderFactory.createEmptyBorder());
		idTf.setPreferredSize(new Dimension(50,24));
		pwF.setBorder(BorderFactory.createEmptyBorder());
		pwF.setPreferredSize(new Dimension(50,24));
		
		
		title.setForeground(Color.white);
		title.setFont(new Font("Verdana Bold",Font.PLAIN,35));
		idlbl.setForeground(Color.white);
		idlbl.setFont(new Font("HY견고딕",Font.PLAIN,18));
		pwlbl.setForeground(Color.white);
		pwlbl.setFont(new Font("HY견고딕",Font.PLAIN,18));
		btnOk.setFont(new Font("HY견고딕",Font.PLAIN,18));
		btnCancel.setFont(new Font("HY견고딕",Font.PLAIN,18));
		
		JButton btn1 = new JButton();
		btn1.setBackground(Color.WHITE);
		btn1.setBounds(10,25,370,3);
		add(btn1);
		JButton btn2 = new JButton();
		btn2.setBackground(Color.WHITE);
		btn2.setBounds(10,105,370,3);
		add(btn2);
		add(title);
		add(pnlId);
		add(pnlPw);
		add(pnlOKCancel);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(390,350);
		this.setLocationRelativeTo(null);
		setVisible(true);
		idTf.requestFocus();
	}
	
	@Override public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnOk){
			login();
		}else{
			dispose();
		}
	}
	//로그인 버튼 눌렀을 때 작동
	void login(){
		if(idTf.getText().equals(id)&&pwF.getText().equals(pw)){
			chk=1;
			JOptionPane.showMessageDialog(this, "                      로그인되었습니다.", "", JOptionPane.PLAIN_MESSAGE, null);
			mainFrame.logIn();
			new JCafeManagerMenu(mainFrame);
			
			dispose();
		}else{
			chk= 1;
			JOptionPane.showMessageDialog(this, "       아이디 또는 비밀번호가 틀렸습니다.", "", JOptionPane.PLAIN_MESSAGE, null);
			idTf.selectAll();
			pwF.setText("");
			idTf.requestFocus();
		}
	}
	

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		if(KeyEvent.VK_ENTER==e.getKeyCode()){
			if(chk==0){
				login();
			}else{
				chk=0;
			}	
		}
		
	}
	public void keyPressed(java.awt.event.KeyEvent arg0) {}
	public void keyTyped(java.awt.event.KeyEvent arg0) {}
}
