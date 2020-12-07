import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
class EndPnlThread extends Thread{
	EndPnl pnl;
	
	public EndPnlThread(EndPnl pnl) {
		this.pnl = pnl;
	}
	@Override
	public void run() {
		for (int i = 0; i < 21; i++) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i==20){
				pnl.addGameoverBtn();
			}else{
				pnl.addBtn(i*30);
			}
		}
	}
}
public class EndPnl extends JPanel{
	ImageIcon icon;
	JLabel btn;
	JLabel gameoverBtn;
	JLabel lbl;
	JLabel lbl2;
	GamePlayPanel gpPnl;
	public EndPnl(int i,GamePlayPanel gpPnl) {
		this.gpPnl = gpPnl;
		setBounds(104, 97, 15*20, 30*20);
		setBackground(Color.black);
		setLayout(null);
		if(i==0){
			lbl = new JLabel("<html>GAME<br>OVER</html>");
			lbl2 = new JLabel("<html>GAME<br>OVER</html>");
		}else{
			lbl = new JLabel("<html>TIME<br>OVER</html>");
			lbl2 = new JLabel("<html>TIME<br>OVER</html>");
		}
		
		icon = new ImageIcon("end.png");
		Image img = icon.getImage();
		img = img.getScaledInstance(300, 30, Image.SCALE_DEFAULT);
		icon = new ImageIcon(img);
		new EndPnlThread(this).start();
	}
	void addBtn(int y){
		btn = new JLabel(icon);
		btn.setBounds(0,y,300,30);
		add(btn);
		repaint();
		revalidate();
	}
	void addGameoverBtn(){
		removeAll();
		
		lbl.setBounds(45, 200, 250, 200);
		lbl2.setBounds(49, 204, 250, 200);
		lbl.setForeground(Color.pink);
		lbl2.setForeground(Color.red);
		lbl.setFont(new Font("Serif",Font.BOLD,70));
		lbl2.setFont(new Font("Serif",Font.BOLD,70));
		add(lbl);
		add(lbl2);
		gameoverBtn = new JLabel(new ImageIcon("end2.png"));
		gameoverBtn.setBounds(0,0,15*20,30*20);
		add(gameoverBtn);
		repaint();
		revalidate();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gpPnl.showScore();
	}
}

