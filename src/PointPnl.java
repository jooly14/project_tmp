import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
//플레이어 점수를 보여주는 패널
//pointPnl의 스레드
//점수가 오를때 1점 단위로 빠르게 오른다.
class PointThread extends Thread{
	PointPnl pointPnl;
	int point;			//오른 점수
	int currentPoint;	//현재 점수
	boolean isRunning = true;;
	public PointThread(PointPnl pointPnl,int point) {
		this.pointPnl = pointPnl;
		this.point = point;
		currentPoint = pointPnl.getPoint();
	}
	@Override
	public void run() {
		while(isRunning&&currentPoint!=point){	//현재점수가 오른 점수가까지 올라갈때까지 진행
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentPoint++;
			pointPnl.setPointLbl(currentPoint);
		}
	}
	void chgIsRunning(){
		isRunning = false;
	}
}

//플레이어 점수를 보여주는 패널
public class PointPnl extends JPanel{
	JLabel pointLbl;
	GamePlayPanel gamePlayPanel;	//게임 패널
	PointThread pointThread;		//포인트 올라가는 스레드
	int bestScore;					//베스트 스코어랑 플레이어 스코어랑 같은지 확인하기 위한 베스트 스코어
	
	BestScorePnl bestScorePnl;		//베스트 스코어 패널
	public PointPnl(GamePlayPanel gamePlayPanel, int point, int bestScore,BestScorePnl bestScorePnl) {
		setBounds(445,760,330,50);
		setBackground(new Color(0x9292ab));
		
		this.gamePlayPanel = gamePlayPanel;
		this.bestScore = bestScore;
		this.bestScorePnl = bestScorePnl;
		
		pointLbl = new JLabel();
		pointLbl.setFont(new Font("Serif", Font.BOLD, 30));
		pointLbl.setForeground(Color.white);
		pointLbl.setText(point+"");
		add(pointLbl);
	}
	//포인트가 상승하는 스레드에서 사용하는 메서드
	void setPointLbl(int currentPoint){
		//플레이어 스코어가 베스트 스코어보다 크면 플레이어 스코어가 플레이어 스코어와 연동 
		if(currentPoint>=bestScore){
			bestScorePnl.setPointLbl(currentPoint+"");
		}
		pointLbl.setText(currentPoint+"");
	}
	//스레드에서 사용하기 위해 포인트를 주는 메서드
	int getPoint(){
		return Integer.parseInt(pointLbl.getText());
	}
	//게임 패널에서 점수가 오를때마다 새로운 스레드를 생성
	void setPointLblText(int point){
		if(pointThread!=null){
			pointThread.chgIsRunning();
			pointThread = new PointThread(this, point);
			pointThread.start();
		}else{
			pointThread = new PointThread(this, point);
			pointThread.start();
		}
	}
}
