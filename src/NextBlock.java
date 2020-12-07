import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NextBlock extends JPanel{
	GamePlayPanel main;
	JLabel btnNext[]= new JLabel[4];
	int x = 0;
	int x2 = 40;
	int y = 20;
	public NextBlock(GamePlayPanel main) {
		this.main = main;
		this.setLayout(null);
		for (int i = 0; i < btnNext.length; i++) {
			btnNext[i] = new JLabel();
			btnNext[i].setOpaque(true);
			btnNext[i].setBorder(BorderFactory.createRaisedBevelBorder());
			add(btnNext[i]);
		}
	}
	void randomBlock(int nextBlock){
		for (int i = 0; i < btnNext.length; i++) {
			switch (nextBlock) {
			case 0:
				btnNext[i].setBounds(x*20+x2,20*i+y,20,20);
				btnNext[i].setBackground(Color.red);
				break;
			case 1:
				if(i<3){
					btnNext[i].setBounds(x*20+x2,20*i+y,20,20);
				}else{
					btnNext[i].setBounds((x+1)*20+x2,20*(i-1)+y,20,20);
				}
				btnNext[i].setBackground(Color.orange);
				break;
			case 2:
				if(i<3){
					btnNext[i].setBounds(x*20+x2,20*i+y,20,20);
				}else{
					btnNext[i].setBounds((x-1)*20+x2,20*(i-1)+y,20,20);
				}
				btnNext[i].setBackground(Color.yellow);
				break;
			case 3:
				if(i<3){
					btnNext[i].setBounds(x*20+x2,20*i+y,20,20);
				}else{
					btnNext[i].setBounds((x+1)*20+x2,20*(i-2)+y,20,20);
				}
				btnNext[i].setBackground(Color.green);
				break;
			case 4:
				if(i<2){
					btnNext[i].setBounds(x*20+x2,20*i+y,20,20);
				}else if(i<3){
					btnNext[i].setBounds((x+1)*20+x2,20*(i-1)+y,20,20);
				}else{
					btnNext[i].setBounds((x+1)*20+x2,20*(i-1)+y,20,20);
				}
				btnNext[i].setBackground(Color.cyan);
				break;
			case 5:
				if(i<2){
					btnNext[i].setBounds(x*20+x2,20*i+y,20,20);
				}else if(i<3){
					btnNext[i].setBounds((x-1)*20+x2,20*(i-1)+y,20,20);
				}else{
					btnNext[i].setBounds((x-1)*20+x2,20*(i-1)+y,20,20);
				}
				btnNext[i].setBackground(Color.blue);
				break;
			case 6:
				if(i<2){
					btnNext[i].setBounds(x*20+x2,20*i+y,20,20);
				}else{
					btnNext[i].setBounds((x+1)*20+x2,20*(i-2)+y,20,20);
				}
				btnNext[i].setBackground(Color.magenta);
				break;
			}
		}
		
	}
}
