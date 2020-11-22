import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class JCafeNewMenuPnl extends JDialog implements ActionListener{
	JButton newMenuBtn, newIngreBtn;
	JPanel newMenuP;
	
	JCafeManagerMenu c;
	JCafeNewMenuPnl(JCafeManagerMenu c){
		this.c = c;
	}
	JCafeNewMenuPnl(){
		
		this.setSize(300,100);
		this.setTitle("메뉴 등록");
		
		newMenuP = new JPanel();
		newMenuBtn = new JButton("메뉴 추가/삭제");
		newIngreBtn = new JButton("메뉴 설정");
		newMenuBtn.addActionListener(this);
		newIngreBtn.addActionListener(this);
		newMenuP.add(newMenuBtn);
		newMenuP.add(newIngreBtn);
		
		this.add(newMenuP);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(newMenuBtn == e.getSource()){
			new JCafenewMenu(c, this);
		}else if(newIngreBtn == e.getSource()){
			//new JCafeMenuForIngredients(this);
		}
	}
	
}