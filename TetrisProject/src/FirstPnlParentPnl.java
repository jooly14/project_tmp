import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FirstPnlParentPnl extends JPanel{
	FirstPnl firstPnl;
	JLabel backLbl;
	public FirstPnlParentPnl() {
		firstPnl = new FirstPnl();
		add(firstPnl);
		
		backLbl = new JLabel(new ImageIcon("tetris_sky_back.png"));
		backLbl.setBounds(-10,0, 800, 835);
		add(backLbl);
		setLayout(null);
		setBounds(0,0, 800, 868);
		
	}
	JPanel getFirstPnl(){
		return firstPnl;
	}
	void chgIsRunning(){
		firstPnl.chgIsRunning();
//		firstPnl.chgLightThreadIsRunning();
	}
}
