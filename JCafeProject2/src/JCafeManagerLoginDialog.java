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
	String id = "0000";	//�ӽ� ���̵�
	String pw = "0000";	//�ӽ� ��й�ȣ
	JTextField idTf;
	JPasswordField pwF;
	JCafeMain mainFrame;
	int chk;	//Ű�����ʿ��� �α��θ޼��� �����߿��� ����Ű ������ �� �α��θ޼��� ȣ���ϴ� ���� �������� ����
	
	public JCafeManagerLoginDialog(JCafeMain mainFrame){
		this.mainFrame = mainFrame;
		//this.setLocation(mainFrame.getX(),mainFrame.getY());	//ȭ�� ��ġ ����
		this.setIconImage(new ImageIcon("JCafeData\\ImageData\\JCafe icon.png").getImage());
		
		JLabel title = new JLabel(" MANAGER LOGIN ");
		
		
		JPanel pnlCenter = new JPanel(new GridLayout(0,1));
		JPanel pnlId = new JPanel();
		JPanel pnlPw = new JPanel();
		idTf = new JTextField(13);
		pwF = new JPasswordField(13);
		idTf.addKeyListener(this);
		pwF.addKeyListener(this);
		
		
		JLabel idlbl = new JLabel("���̵�      :      ");
		JLabel pwlbl = new JLabel("��й�ȣ   :      ");
		
		pnlId.add(idlbl);
		pnlId.add(idTf);
		pnlPw.add(pwlbl);
		pnlPw.add(pwF);
		
		btnOk = new JButton("�α���");
		btnCancel = new JButton("���");
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
		
		JPanel pnlOKCancel = new JPanel();
		pnlOKCancel.add(btnOk);
		pnlOKCancel.add(new JLabel("    "));
		pnlOKCancel.add(btnCancel);
		
		
		//���� �κ�
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
		idlbl.setFont(new Font("HY�߰��",Font.PLAIN,18));
		pwlbl.setForeground(Color.white);
		pwlbl.setFont(new Font("HY�߰��",Font.PLAIN,18));
		btnOk.setFont(new Font("HY�߰��",Font.PLAIN,18));
		btnCancel.setFont(new Font("HY�߰��",Font.PLAIN,18));
		
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
	//�α��� ��ư ������ �� �۵�
	void login(){
		if(idTf.getText().equals(id)&&pwF.getText().equals(pw)){
			chk=1;
			JOptionPane.showMessageDialog(this, "                      �α��εǾ����ϴ�.", "", JOptionPane.PLAIN_MESSAGE, null);
			mainFrame.logIn();
			new JCafeManagerMenu(mainFrame);
			
			dispose();
		}else{
			chk= 1;
			JOptionPane.showMessageDialog(this, "       ���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�.", "", JOptionPane.PLAIN_MESSAGE, null);
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
