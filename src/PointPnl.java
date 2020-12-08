import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

class PointThread extends Thread{
	PointPnl pointPnl;
	int point;
	int currentPoint;
	public PointThread(PointPnl pointPnl,int point) {
		this.pointPnl = pointPnl;
		this.point = point;
		currentPoint = pointPnl.getPoint();
	}
	@Override
	public void run() {
		while(currentPoint!=point){
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
}

public class PointPnl extends JPanel{
	JLabel pointLbl;
	GamePlayPanel gamePlayPanel;
	PointThread pointThread;
	int bestScore;
	
	BestScorePnl bestScorePnl;
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
	void setPointLbl(int currentPoint){
		if(currentPoint>=bestScore){
			bestScorePnl.setPointLbl(currentPoint+"");
		}
		pointLbl.setText(currentPoint+"");
	}
	int getPoint(){
		return Integer.parseInt(pointLbl.getText());
	}
	void setPointLblText(int point){
		pointThread = new PointThread(this, point);
		pointThread.start();
	}
}
