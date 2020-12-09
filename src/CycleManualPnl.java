import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
//게임 패널의 하단에 돌아가는 매뉴얼을 보여주는 패널
//돌아가는 매뉴얼을 구현하는 스레드
class CycleManualPnl_Thread extends Thread{
	CycleManualPnl cycleManualPnl;
	GamePlayPanel gamePlayPanel;
	public CycleManualPnl_Thread(CycleManualPnl cycleManualPnl,GamePlayPanel gamePlayPanel) {
		this.cycleManualPnl =cycleManualPnl;
		this.gamePlayPanel = gamePlayPanel;
	}
	@Override
	public void run() {
		//게임이 진행중일 때만 움직이도록 설정
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
//게임 패널의 하단에 돌아가는 매뉴얼을 보여주는 패널
public class CycleManualPnl extends JPanel{
	GamePlayPanel gamePlayPanel;
	JLabel lbl;
	JLabel lbl2;
	
	boolean afterAdd = false;	//레이블이 붙었는지 확인
	int oneOrTwo = 1;			//현재 붙어있는 레이블이 몇개 인지 확인
	String comment = "방향키와 스페이스바를 이용해서 조작할 수 있습니다. 엔터키로 게임을 일시중지할 수 있습니다.";
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
		//afterAdd가 false이면 처음에 한번 레이블을 붙인다.
		if(afterAdd){
			//oneOrTwo변수가 1이면 lbl만 붙은 상태. 2이면 lbl과 lbl2가 붙은 상태. 3이면 lbl2만 붙은 상태. 4이면 lbl2와 lbl이 붙은 상태
			if(oneOrTwo==1){
				lbl.setLocation(lbl.getX()-10, lbl.getY());
				if(lbl.getX()<-550){
					oneOrTwo = 2;
					afterAdd = false;
				}
			}else if(oneOrTwo==2){//두개 다 붙어야될때 //lbl1이 먼저
				lbl.setLocation(lbl.getX()-10, lbl.getY());
				lbl2.setLocation(lbl2.getX()-10, lbl2.getY());
				if(lbl.getX()<-900){
					oneOrTwo = 3;
					afterAdd = false;
				}
			}else if(oneOrTwo==3){//lbl2만 붙을때
				lbl2.setLocation(lbl2.getX()-10, lbl2.getY());
				if(lbl2.getX()<-550){
					oneOrTwo = 4;
					afterAdd = false;
				}
			}else if(oneOrTwo==4){//두개 다 붙어야될 때 //lbl2가 먼저
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
			}else if(oneOrTwo==2){//두개 다 붙어야될때 //lbl1이 먼저
				add(lbl2);
			}else if(oneOrTwo==3){//lbl2만 붙을때
				remove(lbl);
				lbl.setLocation(330, 0);
			}else if(oneOrTwo==4){//두개 다 붙어야될 때 //lbl2가 먼저
				add(lbl);
			}
			afterAdd = true;
			repaint();
			revalidate();
		}
	}
}
