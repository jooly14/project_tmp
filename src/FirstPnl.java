import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FirstPnl extends JPanel{
	public FirstPnl() {
		setLayout(null);
		setBounds(0,0, 800, 868);
		JLabel b = new JLabel(new ImageIcon("tetris_sky_back.png"));
		b.setBounds(-10,0, 800, 835);
		
		JLabel lbl = new JLabel("PRESS ANY KEY TO START!!");
		lbl.setForeground(Color.white);
		lbl.setFont(new Font("Serif", Font.BOLD, 30));
		lbl.setBounds(190, 600, 600, 50);
		add(lbl);
		add(b);
		
	}
}
