import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
//������ ���ھ� �г��� �����ִ� �г�
//ScorePnl�� ����� ���� �Ǵ� �κ��� �ذ��ϱ� ���ؼ� ����� ���� ��
//ScorePnl�� ScorePnlParentPnl�� �ڽ� �г�
public class ScorePnlParentPnl extends JPanel{
	int point;		//�÷��̾� ����
	TetrisMain main;
	JLabel back;		//���
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
	//���� �����ӿ��� scorePnl�� get�ϱ� ���� �޼���
	//���� �����ӿ��� scorePnl�� �޼��带 ����ϱ� ������
	ScorePnl getChildPnl(){
		return scorePnl;
	}
}
