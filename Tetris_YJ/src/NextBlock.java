import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
//다음 블록을 보여주는 패널
public class NextBlock extends JPanel{
	JLabel btnNext[]= new JLabel[4];
	int x = 0;
	int x2 = 50;	//x축 위치조절
	int y = 20;		//y축 위치조절
	JLabel nextBlock;		
	 
	public NextBlock() {
		this.setLayout(null);
		nextBlock = new JLabel("NEXT BLOCK");
		nextBlock.setForeground(Color.WHITE);
		nextBlock.setFont(new Font("Serif", Font.BOLD, 20));
		nextBlock.setBounds(0,100, 150, 50);
		add(nextBlock);
		
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
