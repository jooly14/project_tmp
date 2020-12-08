import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FirstPnl extends JPanel{
	JLabel backLbl;
	JLabel lbl;
	FirstPnl_Thread t;
	public FirstPnl() {
		setLayout(null);
		setBounds(0,0, 800, 868);
		
		
		backLbl = new JLabel(new ImageIcon("tetris_sky_back.png"));
		backLbl.setBounds(-10,0, 800, 835);
		
		lbl = new JLabel("PRESS ANY KEY TO START!!");
		lbl.setForeground(Color.white);
		lbl.setFont(new Font("Serif", Font.BOLD, 30));
		lbl.setBounds(190, 600, 600, 50);
		
		showPnl();
		
		t = new FirstPnl_Thread(this);
		t.start();
	}
	void showPnl(){
		
		add(lbl);
		add(backLbl);
		repaint();
		revalidate();
	}
	void hidePnl(){
		remove(lbl);
		repaint();
		revalidate();
	}
	void chgIsRunning(){
		t.chgIsRunning();
	}
}
class FirstPnl_Thread extends Thread{
	FirstPnl firstPnl;
	boolean exist = true;
	boolean isRunning = true;
	public FirstPnl_Thread(FirstPnl firstPnl) {
		this.firstPnl = firstPnl;
	}
	@Override
	public void run() {
		while (isRunning) {
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(exist){
				firstPnl.hidePnl();
				exist = false;
			}else{
				firstPnl.showPnl();
				exist = true;
			}
			
		}
		
	}
	void chgIsRunning(){
		isRunning = false;
	}
}