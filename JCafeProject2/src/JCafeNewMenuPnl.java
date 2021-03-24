import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

class JCafeNewMenuPnl extends JDialog implements ActionListener{
	JButton newMenuBtn, newIngreBtn;
	JPanel newMenuP;
	
	JCafeManagerMenu c;
	JCafeNewMenuPnl(JCafeManagerMenu c){
		this.setIconImage(new ImageIcon("JCafeData\\ImageData\\JCafe icon.png").getImage());
		this.c = c;
	}
	JCafeNewMenuPnl(){
		this.setIconImage(new ImageIcon("JCafeData\\ImageData\\JCafe icon.png").getImage());
		
		this.setSize(300,100);
		this.setTitle("메뉴 등록");
		
		newMenuP = new JPanel();
		newMenuBtn = new JButton("메뉴 추가/삭제");
		newIngreBtn = new JButton("메뉴 설정");
		newMenuBtn.addActionListener(this);
		newIngreBtn.addActionListener(this);
		newMenuP.add(newMenuBtn);
		newMenuP.add(newIngreBtn);
		repaint();
		revalidate();
		//색상 변경
		Color color=new Color(0x252525);
		newMenuP.setBackground(color);
		newMenuBtn.setBackground(Color.WHITE);
		newIngreBtn.setBackground(Color.WHITE);
		
		this.add(newMenuP);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(newMenuBtn == e.getSource()){
			new JCafenewMenu(c, this);
		}else if(newIngreBtn == e.getSource()){
			repaint();
			revalidate();
			new JCafeMenuForIngredients(this);
		}
	}
	
}