import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class JCafePhoneNum extends JDialog implements ActionListener,KeyListener {
	JButton[] btnKeypad;// 0~15까지 버튼이 담겨있음
	JLabel lbl;// _ _ _ - _ _ _ _ - _ _ _ _ 이게 표현될 레이블. 번호가 입력될때마다 하나씩 지워지고 채워지고를 반복한다.
	String[]strNumber=new String[11];//입력되는 번호가 각각 담길 배열
	int order;//몇번째 번호 입력중인지 세어줄 변수
	boolean confirm; //
	
	void init() {//자료 초기화 밑 화면표현
		setSize(250,300);
		
		String[] strKeypad= {"1","2","3","4","5","6","7","8","9","","0","정정","확인","초기화","취소"};
		btnKeypad=new JButton[strKeypad.length];//버튼배열 길이 지정
		JPanel pnlCenter=new JPanel(new GridLayout(5,3));
		for(int i=0;i<btnKeypad.length;i++) {
			btnKeypad[i]=new JButton(strKeypad[i]);	// 버튼배열 생성
			btnKeypad[i].addActionListener(this);	// 액션리스너
			pnlCenter.add(btnKeypad[i]);			// 센터패널에 붙이기
		}
		btnKeypad[9].setEnabled(false);				// 아무것도 없는 버튼 비활성화
		for(int i=0;i<strNumber.length;i++) strNumber[i]="_";
		
		lbl=new JLabel("_ _ _ - _ _ _ _ - _ _ _ _");// 처음에 번호입력 전 노출될 Label
		JPanel pnlNorth=new JPanel();
		pnlNorth.add(lbl);
		
		this.add(pnlNorth,"North");
		this.add(pnlCenter);
		this.addKeyListener(this);
		this.setVisible(true);
	}
	public JCafePhoneNum(JCafePayment jcp) {//생성자
		super(jcp,true);
		init();
		this.requestFocus(true);//키를 입력 할 수 있게끔 포커스를 프레임이 가진다.
	}
	public JCafePhoneNum(JCafeStempTable jcst) {//생성자
		super(jcst,true);
		init();
		this.requestFocus(true);//키를 입력 할 수 있게끔 포커스를 프레임이 가진다.
	}
	
	String getPhoneNum() {//입력된 폰번호 반환
		String str="";
		for(int i=0;i<11;i++)
			str=str+strNumber[i];
		return str;
	}
	void changeLabel() { // 레이블 표현해주는 메소드
		lbl.setText("");
		for(int i=0;i<11;i++) {
			if(i==3||i==7)
				lbl.setText(lbl.getText()+" - ");
			lbl.setText( lbl.getText() + strNumber[i]+" ");
		}
		repaint();
		revalidate();
		requestFocus();//키보드로 입력할 수도 있게
	}
	void clickBtn(ActionEvent e) {//버튼 클릭 이벤트
		JButton clickBtn=(JButton)e.getSource();
		if(e.getSource()==btnKeypad[11]) { // 정정
			if(order==0) strNumber[order]="_";
			else strNumber[order--]="_";
		} else if(e.getSource()==btnKeypad[12]){//확인
			if(strNumber[10].equals("_")) {
				JOptionPane.showMessageDialog(this, "번호를 확인해주세요");
			}else {
				confirm=true;
				setVisible(false);
			}
				
		} else if(e.getSource()==btnKeypad[13]){//초기화
			for(int i=0;i<11;i++)
				strNumber[i]="_";
			order=0;
		} else if(e.getSource()==btnKeypad[14]){//취소
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
	void inputKeyboard(KeyEvent e) {// 키보드 입력 이벤트(숫자만 입력가능, 백스페이스 사용가능)
		if(order>=0&&order<11) {
			if( (47<e.getKeyCode()&&e.getKeyCode()<58) || (95<e.getKeyCode()&&e.getKeyCode()<106) ) {
				strNumber[order]=String.valueOf(e.getKeyChar());
				order++;
				requestFocus(true);
			}
		}else if(order>10||order<0){// 잘못입력하면 토스트 하고 초기화
			JOptionPane.showMessageDialog(this, "잘 못 입력하셨습니다.");
			for(int i=0;i<11;i++)
				strNumber[i]="_";
			order=0;
		}else if(e.getKeyCode()==8) {
			strNumber[--order]="_";
		}
		changeLabel();
	}
	boolean getConfirm() {// 확인눌렀으면 true 아니면 false 반환
		return confirm;
	}
	
	@Override public void actionPerformed(ActionEvent e) {clickBtn(e);}
	@Override public void keyPressed(KeyEvent e) {inputKeyboard(e);}
	@Override public void keyReleased(KeyEvent e) {}
	@Override public void keyTyped(KeyEvent e) {}
}