import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
//�÷��̾� ������ �����ִ� �г�
//pointPnl�� ������
//������ ������ 1�� ������ ������ ������.
class PointThread extends Thread{
	PointPnl pointPnl;
	int point;			//���� ����
	int currentPoint;	//���� ����
	boolean isRunning = true;;
	public PointThread(PointPnl pointPnl,int point) {
		this.pointPnl = pointPnl;
		this.point = point;
		currentPoint = pointPnl.getPoint();
	}
	@Override
	public void run() {
		while(isRunning&&currentPoint!=point){	//���������� ���� ���������� �ö󰥶����� ����
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

//�÷��̾� ������ �����ִ� �г�
public class PointPnl extends JPanel{
	JLabel pointLbl;
	GamePlayPanel gamePlayPanel;	//���� �г�
	PointThread pointThread;		//����Ʈ �ö󰡴� ������
	int bestScore;					//����Ʈ ���ھ�� �÷��̾� ���ھ�� ������ Ȯ���ϱ� ���� ����Ʈ ���ھ�
	
	BestScorePnl bestScorePnl;		//����Ʈ ���ھ� �г�
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
	//����Ʈ�� ����ϴ� �����忡�� ����ϴ� �޼���
	void setPointLbl(int currentPoint){
		//�÷��̾� ���ھ ����Ʈ ���ھ�� ũ�� �÷��̾� ���ھ �÷��̾� ���ھ�� ���� 
		if(currentPoint>=bestScore){
			bestScorePnl.setPointLbl(currentPoint+"");
		}
		pointLbl.setText(currentPoint+"");
	}
	//�����忡�� ����ϱ� ���� ����Ʈ�� �ִ� �޼���
	int getPoint(){
		return Integer.parseInt(pointLbl.getText());
	}
	//���� �гο��� ������ ���������� ���ο� �����带 ����
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
