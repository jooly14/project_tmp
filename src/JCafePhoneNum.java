import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JCafePhoneNum extends JDialog implements ActionListener,KeyListener {
	JButton[] btnKeypad;// 0~15���� ��ư�� �������
	JLabel lbl;// _ _ _ - _ _ _ _ - _ _ _ _ �̰� ǥ���� ���̺�. ��ȣ�� �Էµɶ����� �ϳ��� �������� ä������ �ݺ��Ѵ�.
	String[]strNumber=new String[11];//�ԷµǴ� ��ȣ�� ���� ��� �迭
	int order;//���° ��ȣ �Է������� ������ ����
	boolean confirm; //
	
	Color color = new Color(0xcbe2a8);
	void init() {//�ڷ� �ʱ�ȭ �� ȭ��ǥ��
		setSize(300,330);
		this.setIconImage(new ImageIcon("JCafeData\\ImageData\\JCafe icon.png").getImage());
		String[] strKeypad= {"1","2","3","4","5","6","7","8","9","","0","����","Ȯ��","�ʱ�ȭ","���"};
		btnKeypad=new JButton[strKeypad.length];//��ư�迭 ���� ����
		JPanel pnlCenter=new JPanel(new GridLayout(5,3));
		for(int i=0;i<btnKeypad.length;i++) {
			btnKeypad[i]=new JButton(strKeypad[i]);	// ��ư�迭 ����
			btnKeypad[i].addActionListener(this);	// �׼Ǹ�����
			if(i/3==0||i/3==2||i/3==4){
				btnKeypad[i].setBackground(color);
			}else{
				btnKeypad[i].setBackground(Color.white);
			}
			btnKeypad[i].setFont(new Font("Dialog", Font.BOLD, 13));
			btnKeypad[i].setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			pnlCenter.add(btnKeypad[i]);			// �����гο� ���̱�
		}
		btnKeypad[9].setEnabled(false);				// �ƹ��͵� ���� ��ư ��Ȱ��ȭ
		for(int i=0;i<strNumber.length;i++) strNumber[i]="_";
		 btnKeypad[0].addKeyListener(this);
		lbl=new JLabel("_ _ _ - _ _ _ _ - _ _ _ _");// ó���� ��ȣ�Է� �� ����� Label
		lbl.setFont(new Font("Dialog", Font.BOLD, 20));
		JPanel pnlNorth=new JPanel();
		pnlNorth.add(lbl);
		
		this.add(pnlNorth,"North");
		this.add(pnlCenter);
		this.addKeyListener(this);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public JCafePhoneNum(JCafePayment jcp) {//������
		super(jcp,true);
		init();
		this.requestFocus(true);//Ű�� �Է� �� �� �ְԲ� ��Ŀ���� �������� ������.
	}
	public JCafePhoneNum(JCafeStempTable jcst) {//������
		super(jcst,true);
		init();
		this.requestFocus(true);//Ű�� �Է� �� �� �ְԲ� ��Ŀ���� �������� ������.
	}
	
	String getPhoneNum() {//�Էµ� ����ȣ ��ȯ
		String str="";
		for(int i=0;i<11;i++)
			str=str+strNumber[i];
		return str;
	}
	void changeLabel() { // ���̺� ǥ�����ִ� �޼ҵ�
		lbl.setText("");
		for(int i=0;i<11;i++) {
			if(i==3||i==7)
				lbl.setText(lbl.getText()+" - ");
			lbl.setText( lbl.getText() + strNumber[i]+" ");
		}
		repaint();
		revalidate();
		requestFocus();//Ű����� �Է��� ���� �ְ�
	}
	void clickBtn(ActionEvent e) {//��ư Ŭ�� �̺�Ʈ
		JButton clickBtn=(JButton)e.getSource();
		if(e.getSource()==btnKeypad[11]) { // ����
			if(order==0) strNumber[order]="_";
			else strNumber[order--]="_";
		} else if(e.getSource()==btnKeypad[12]){//Ȯ��
			if(strNumber[10].equals("_")) {
				JOptionPane.showMessageDialog(this, "��ȣ�� Ȯ�����ּ���");
			}else {
				confirm=true;
				setVisible(false);
			}
				
		} else if(e.getSource()==btnKeypad[13]){//�ʱ�ȭ
			for(int i=0;i<11;i++)
				strNumber[i]="_";
			order=0;
		} else if(e.getSource()==btnKeypad[14]){//���
			confirm=false;
			dispose();
		}else {
			strNumber[order]=clickBtn.getText();
			order++;
			requestFocus(true);
		}
		if(order<0) order++;
		else if(order>10) order--;
		changeLabel();
	}
	void inputKeyboard(KeyEvent e) {// Ű���� �Է� �̺�Ʈ(���ڸ� �Է°���, �齺���̽� ��밡��)
		if(order>=0&&order<11) {
			if( (47<e.getKeyCode()&&e.getKeyCode()<58) || (95<e.getKeyCode()&&e.getKeyCode()<106) ) {
				strNumber[order]=String.valueOf(e.getKeyChar());
				order++;
				requestFocus(true);
			}
		}else if(order>10||order<0){// �߸��Է��ϸ� �佺Ʈ �ϰ� �ʱ�ȭ
			JOptionPane.showMessageDialog(this, "�� �� �Է��ϼ̽��ϴ�.");
			for(int i=0;i<11;i++)
				strNumber[i]="_";
			order=0;
		}else if(e.getKeyCode()==8) {
			strNumber[--order]="_";
		}
		changeLabel();
	}
	boolean getConfirm() {// Ȯ�δ������� true �ƴϸ� false ��ȯ
		return confirm;
	}
	
	@Override public void actionPerformed(ActionEvent e) {clickBtn(e);}
	@Override public void keyPressed(KeyEvent e) {inputKeyboard(e);}
	@Override public void keyReleased(KeyEvent e) {}
	@Override public void keyTyped(KeyEvent e) {}
}