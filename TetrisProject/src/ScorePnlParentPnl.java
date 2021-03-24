import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePnlParentPnl extends JPanel{
	int point;
	TetrisMain main;
	JLabel b;
	ScorePnl scorePnl;
	public ScorePnlParentPnl(int point ,TetrisMain main) {
		this.point = point;
		this.main = main;
		
		scorePnl = new ScorePnl(point, main);
		
		setBounds(0,0, 800, 868);
		setLayout(null);

		b = new JLabel(new ImageIcon("menu.png"));
		b.setBounds(-10,0, 800, 835);
		
		add(scorePnl);
		add(b);
		
	}
	ScorePnl getChildPnl(){
		return scorePnl;
	}
}
