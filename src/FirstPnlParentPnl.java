import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
//ùȭ�� panel
//����� �ٽ� �ٿ��� �Ǵ� ���� ������ ���� parentpanel�� �������
//�� ���� childpanel�� �ٿ��� ���
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
//�Ⱦ��� �޼����� �� ����
//	JPanel getFirstPnl(){
//		return firstPnl;
//	}
}
