import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePnl extends JPanel{
	int point;
	public ScorePnl(int point) {
		this.point = point;
		setLayout(null);
		setBounds(0,0, 800, 868);
		JLabel b = new JLabel(new ImageIcon("menu.png"));
		b.setBounds(-10,0, 800, 835);
		
		JLabel lbl = new JLabel("BEST SCORE");
		lbl.setForeground(Color.white);
		lbl.setFont(new Font("Serif", Font.BOLD, 30));
		lbl.setBounds(190,0, 600, 100);
		add(lbl);
		add(b);
	}
}
