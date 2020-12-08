import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
class CycleManualPnl_Thread extends Thread{
	CycleManualPnl cycleManualPnl;
	GamePlayPanel gamePlayPanel;
	public CycleManualPnl_Thread(CycleManualPnl cycleManualPnl,GamePlayPanel gamePlayPanel) {
		this.cycleManualPnl =cycleManualPnl;
		this.gamePlayPanel = gamePlayPanel;
	}
	@Override
	public void run() {
		while (gamePlayPanel.startStopEnd!=3) {
			if(gamePlayPanel.startStopEnd==1){
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cycleManualPnl.moveManual();
			}else if(gamePlayPanel.startStopEnd==2){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
public class CycleManualPnl extends JPanel{
	GamePlayPanel gamePlayPanel;
	JLabel lbl;
	JLabel lbl2;
	boolean afterAdd = false;
	int oneOrTwo = 1;
	String comment = "����Ű�� �����̽��ٸ� �̿��ؼ� ������ �� �ֽ��ϴ�. ����Ű�� ������ �Ͻ������� �� �ֽ��ϴ�.";
	public CycleManualPnl(GamePlayPanel gamePlayPanel) {
		this.gamePlayPanel = gamePlayPanel;
		setLayout(null);
		setBackground(new Color(0x242554));
		setBounds(85, 765, 340, 30);
		lbl = new JLabel(comment);
		lbl2 = new JLabel(comment);
		lbl.setFont(new Font("Serif", Font.BOLD, 20));
		lbl.setForeground(new Color(0x936caa));
		lbl2.setFont(new Font("Serif", Font.BOLD, 20));
		lbl2.setForeground(new Color(0x936caa));
		lbl.setBounds(330, 0, 900, 30);
		lbl2.setBounds(330, 0, 900, 30);
		add(lbl);
		new CycleManualPnl_Thread(this,gamePlayPanel).start();
	}
	void chgOneOrTwo(){
		if(oneOrTwo==3){
			oneOrTwo=1;
		}else{
			oneOrTwo++;
		}
	}
	void moveManual(){
		if(afterAdd){
			if(oneOrTwo==1){
				lbl.setLocation(lbl.getX()-10, lbl.getY());
				if(lbl.getX()<-550){
					oneOrTwo = 2;
					afterAdd = false;
				}
			}else if(oneOrTwo==2){//�ΰ� �� �پ�ߵɶ� //lbl1�� ����
				lbl.setLocation(lbl.getX()-10, lbl.getY());
				lbl2.setLocation(lbl2.getX()-10, lbl2.getY());
				if(lbl.getX()<-900){
					oneOrTwo = 3;
					afterAdd = false;
				}
			}else if(oneOrTwo==3){//lbl2�� ������
				lbl2.setLocation(lbl2.getX()-10, lbl2.getY());
				if(lbl2.getX()<-550){
					oneOrTwo = 4;
					afterAdd = false;
				}
			}else if(oneOrTwo==4){//�ΰ� �� �پ�ߵ� �� //lbl2�� ����
				lbl.setLocation(lbl.getX()-10, lbl.getY());
				lbl2.setLocation(lbl2.getX()-10, lbl2.getY());
				if(lbl2.getX()<-900){
					oneOrTwo = 1;
					afterAdd = false;
				}
			}
		}else{
			if(oneOrTwo==1){
				remove(lbl2);
				lbl2.setLocation(330, 0);
			}else if(oneOrTwo==2){//�ΰ� �� �پ�ߵɶ� //lbl1�� ����
				add(lbl2);
			}else if(oneOrTwo==3){//lbl2�� ������
				remove(lbl);
				lbl.setLocation(330, 0);
			}else if(oneOrTwo==4){//�ΰ� �� �پ�ߵ� �� //lbl2�� ����
				add(lbl);
			}
			afterAdd = true;
			repaint();
			revalidate();
		}
	}
}
