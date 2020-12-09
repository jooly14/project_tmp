import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
//첫화면 panel
//배경을 다시 붙여야 되는 문제 때문에 따로 parentpanel을 만들었다
//그 위에 childpanel을 붙여서 사용
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
	void chgIsRunning(){
		firstPnl.chgIsRunning();
//		firstPnl.chgLightThreadIsRunning();
	}
//안쓰는 메서드인 거 같다
//	JPanel getFirstPnl(){
//		return firstPnl;
//	}
}
