import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
//마지막 스코어 패널을 보여주는 패널
//ScorePnl에 배경이 문제 되는 부분을 해결하기 위해서 배경을 따로 뗌
//ScorePnl은 ScorePnlParentPnl의 자식 패널
public class ScorePnlParentPnl extends JPanel{
	int point;		//플레이어 점수
	TetrisMain main;
	JLabel back;		//배경
	ScorePnl scorePnl;	
	public ScorePnlParentPnl(int point ,TetrisMain main) {
		this.point = point;
		this.main = main;
		
		scorePnl = new ScorePnl(point, main);
		
		setBounds(0,0, 800, 868);
		setLayout(null);

		back = new JLabel(new ImageIcon("menu.png"));
		back.setBounds(-10,0, 800, 835);
		
		add(scorePnl);
		add(back);
	}
	//메인 프레임에서 scorePnl을 get하기 위한 메서드
	//메인 프레임에서 scorePnl의 메서드를 사용하기 때문에
	ScorePnl getChildPnl(){
		return scorePnl;
	}
}
