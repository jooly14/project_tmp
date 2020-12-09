import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
//일시정지 패널
public class PausePnl extends JPanel{
	public PausePnl() {
		JLabel lbl = new JLabel("PAUSE");
		JLabel lbl2 = new JLabel("PAUSE");
		lbl.setBounds(70, 250, 200, 50);
		lbl2.setBounds(74, 254, 200, 50);
		lbl.setForeground(Color.white);
		lbl2.setForeground(Color.pink);
		lbl.setFont(new Font("Serif",Font.BOLD,50));
		lbl2.setFont(new Font("Serif",Font.BOLD,50));
		add(lbl);
		add(lbl2);
		setLayout(null);
		setBounds(104, 97, 15*20, 30*20);
		setBackground(Color.black);
	}
}
