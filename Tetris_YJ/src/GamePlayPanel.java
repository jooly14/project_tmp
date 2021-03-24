import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//���ο� ����� ����� �� ����� �Ʒ��� �������� ������
class NewBlockThread extends Thread{
	GamePlayPanel gamePlayPanel;
	boolean isRunning = true;
	int speed;	//������ �޶����� ���ǵ尡 �޶����� �� �� ����ϴ� ����
	public NewBlockThread(GamePlayPanel main,int speed) {
		this.gamePlayPanel = main;
		this.speed = speed;
	}
	@Override
	public void run() {
//		//������ ���� �� ���� ��� ������ ����
//		if(gamePlayPanel.startStopEnd!=3){
			while(isRunning){
				//���� ���� �� ����� �Ʒ��� ��������
				if(gamePlayPanel.startStopEnd==1){
					try {
							Thread.sleep(speed);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(gamePlayPanel.threadRunOkay){
						gamePlayPanel.moveBlock();
					}
				//����Ǹ� ������ ����
				}else if(gamePlayPanel.startStopEnd==3){
					break;
				//�Ͻ����� ������ �� ���
				}else if(gamePlayPanel.startStopEnd==2){
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
//		}
	}
	void chgIsRunning(){
		isRunning = false;
	}
}
//Ÿ�̸� ������
class TimeCounter extends Thread{
	GamePlayPanel gamePlayPanel;
	int timeLimit = 1000*60*5;	//�ð� ����
	JLabel lbl;		//�ð� ���̺�
	boolean isRunning = true;	
	
	long minutes;	//�� ����
	long seconds;	//�� ����
	public TimeCounter(GamePlayPanel main) {
		this.gamePlayPanel = main;
		minutes = (timeLimit / 1000) / 60 % 60;
		seconds = (timeLimit / 1000) % 60;
		lbl = new JLabel();
		//������ ���ڸ� 00:00 ���¸� ���� 
		if(minutes<10&&seconds<10){
			lbl.setText("0"+minutes+":0"+seconds);
		}else if(minutes<10){
			lbl.setText("0"+minutes+":"+seconds);
		}else if(seconds<10){
			lbl.setText(minutes+":0"+seconds);
		}else{
			lbl.setText(minutes+":"+seconds);
		}
		
		lbl.setBounds(575,540,100,100);
		lbl.setForeground(Color.BLACK);
		lbl.setFont(new Font("Serif", Font.BOLD, 30));
		main.add(lbl);
	}
	@Override
	public void run() {
		while (timeLimit!=0&&isRunning) {
			if(gamePlayPanel.startStopEnd==1){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				timeLimit-=1000;
				minutes = (timeLimit / 1000) / 60 % 60;
				seconds = (timeLimit / 1000) % 60;
				if(minutes<10&&seconds<10){
					lbl.setText("0"+minutes+":0"+seconds);
				}else if(minutes<10){
					lbl.setText("0"+minutes+":"+seconds);
				}else if(seconds<10){
					lbl.setText(minutes+":0"+seconds);
				}else{
					lbl.setText(minutes+":"+seconds);
				}
	
				if(timeLimit<=0){
					gamePlayPanel.startStopEnd =3;	
					gamePlayPanel.addEndPnl(1);		//���� �����϶��� 0 , Ÿ�� ���� �϶��� 1
					break;
				}
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
	void chgIsRunning(){
		isRunning = false;
	}
}
public class GamePlayPanel extends JPanel{
	int previousClear = 0;	//�޺��� ���� ���� ������ �� �ֱ� ���� �ʿ��� ����
	
	int point;	//����
	PointPnl pointPnl;	//����Ʈ �����ִ� �г�
	BestScorePnl bestScorePnl;	//�ְ� ���� �����ִ� �г�
	
	CycleManualPnl cycleManualPnl;	//�ϴܿ� �Ŵ��� �����ִ� �г�
	
	boolean twice = false;	//�ٴ��̳� �ٸ� ����̶� �پ��� ���� �����̴� �ð��� �ֱ� ���ؼ� �ʿ�
	
	boolean threadRunOkay = true;	//Ű�̺�Ʈ�� �����尡 ��ġ�� �ʵ��� �ϴ� ����
	boolean keyEventOK = true;
	
	int startStopEnd = 1;	//���� ���� ���¸� �����ִ� ����. 1�� ���� �� . 2�� �Ͻ�����. 3�� ����
	JPanel gamePanel;	//����� ���� �������� �г�
	
	JLabel[] btnNew = new JLabel[4];	//���ο� ���
	NewBlockThread newBlockThread;	//���ο� ����� �����̴� ������	
	int currentBlock;	//���� ����� � ������� �����ϴ� ����
	int nextBlock;		//���� ����� � ������� �����ϴ� ����
	NextBlock nextBlockPnl;		//���� ����� �����ִ� �г�
	int rotateNum;		//���� ����� ȸ���� � ������� �ϰ� �ִ��� �����ִ� ����
	TimeCounter timeCounter;	//���� ���� �ð� Ÿ�̸� ������
	Random random;		//��� ����� �����ϱ� ���� ���
	JLabel[][] stackedBlock = new JLabel[15][30];	//������� ������ ��ϵ�
	
	Spacebar_Thread spbar_t;	//�����̽��ٸ� ������ �� �߻��ϴ� ������
	TetrisRotate tetrisRotate;	//ȸ���� �Ҷ� ���
	
	TetrisMain tetrisMain;	//���� ������
	
	int speed = 500;	//�ʱ� ���ǵ�
	
	PausePnl pausePnl;	//�Ͻ����� �� ���� �г�
	
	JLabel back;		//���ȭ��
	
	ArrayList<Integer> lineToClear;// ���� �ϼ� ���� �� ���� ������ ����
	
	EndPnl endPnl;
	public GamePlayPanel(TetrisMain main) {
		this.tetrisMain = main;
		
		setSize(800,868);
		setLocation(0,0);
		setLayout(null);
		setBackground(Color.BLACK);
		
		endPnl = new EndPnl(this);
		
		back = new JLabel(new ImageIcon("tetris_main.png"));
		back.setBounds(-10, 30, 800, 800);
		
		
		random = new Random();
		nextBlockPnl = new NextBlock();
		nextBlockPnl.setBounds(550,400,200,150);
		nextBlockPnl.setBackground(new Color(0x242554));
		
		nextBlock = random.nextInt(7);
		
		pausePnl = new PausePnl();
		
		tetrisRotate = new TetrisRotate(this);
	
		timeCounter = new TimeCounter(this);
		timeCounter.start();
		
		//���ھ� ���̺�
		JLabel player = new JLabel("PLAYER SCORE");
		player.setBounds(515,720,300,50);
		player.setFont(new Font("Serif", Font.BOLD, 25));
		player.setForeground(Color.white);
		add(player);
		JLabel bestPlayer = new JLabel("BEST SCORE");
		bestPlayer.setBounds(535,630,300,50);
		bestPlayer.setFont(new Font("Serif", Font.BOLD, 25));
		bestPlayer.setForeground(new Color(0xf7d000));
		add(bestPlayer);
		
		//����Ʈ ���ھ�� �÷��̾� ���ھ� �г�
		bestScorePnl = new BestScorePnl();
		add(bestScorePnl);
		pointPnl = new PointPnl(this,point,bestScorePnl.getBestScore(),bestScorePnl);
		add(pointPnl);
		
		cycleManualPnl = new CycleManualPnl(this);
		add(cycleManualPnl);
		
		
		gamePanel = new JPanel();
		gamePanel.setBounds(104, 97, 15*20, 30*20);
		gamePanel.setBackground(Color.black);
		gamePanel.setLayout(null);
		add(gamePanel);
		
		add(nextBlockPnl);
		add(back);
		
		//���ο� ��� ����
		newBlock();
		
		
		//��� �����̴� ������ ����
		newBlockThread = new NewBlockThread(this,speed);
		newBlockThread.start();
		
	}
	//���ο� ��� ����
	void newBlock(){
		//���� ���� ����� ������� �ʴٸ� ���� ����
		if(startStopEnd!=3){
			for(int i=0;i<stackedBlock.length;i++){
				if(stackedBlock[i][0]!=null){
					startStopEnd =3;
					spbar_t.interrupt();
					threadRunOkay =false;
					keyEventOK = false;
					newBlockThread.chgIsRunning();
					spbar_t.chgIsRunning();
					addEndPnl(0);	//���� �����϶��� 0 , Ÿ�� ���� �϶��� 1
					break;
				}
			}
			
		}
		//���� ���ᰡ �ƴ϶��
		if(startStopEnd!=3){
			currentBlock = nextBlock;
			nextBlock = random.nextInt(7);
			nextBlockPnl.randomBlock(nextBlock);
			
			rotateNum = 0;
			
			for(int i=0;i<btnNew.length;i++){
				btnNew[i] = new JLabel();
				btnNew[i].setOpaque(true);
				
				randomBlock(btnNew[i],currentBlock,i);		//currentBlock�� ���� �ٸ� ����� ����� ����
				
				btnNew[i].setBorder(BorderFactory.createRaisedBevelBorder());
				gamePanel.add(btnNew[i]);
			}
			
			
		}
		
	}
	//currentBlock�� ���� �ٸ� ����� ����� ����
	void randomBlock(JLabel btnNew, int ran, int i){
		switch (ran) {
		case 0:
			btnNew.setBounds(7*20,20*i,20,20);
			btnNew.setBackground(Color.red);
			break;
		case 1:
			if(i<3){
				btnNew.setBounds(7*20,20*i,20,20);
			}else{
				btnNew.setBounds((7+1)*20,20*(i-1),20,20);
			}
			btnNew.setBackground(Color.orange);
			break;
		case 2:
			if(i<3){
				btnNew.setBounds(7*20,20*i,20,20);
			}else{
				btnNew.setBounds((7-1)*20,20*(i-1),20,20);
			}
			btnNew.setBackground(Color.yellow);
			break;
		case 3:
			if(i<3){
				btnNew.setBounds(7*20,20*i,20,20);
			}else{
				btnNew.setBounds((7+1)*20,20*(i-2),20,20);
			}
			btnNew.setBackground(Color.green);
			break;
		case 4:
			if(i<2){
				btnNew.setBounds(7*20,20*i,20,20);
			}else if(i<3){
				btnNew.setBounds((7+1)*20,20*(i-1),20,20);
			}else{
				btnNew.setBounds((7+1)*20,20*(i-1),20,20);
			}
			btnNew.setBackground(Color.cyan);
			break;
		case 5:
			if(i<2){
				btnNew.setBounds(7*20,20*i,20,20);
			}else if(i<3){
				btnNew.setBounds((7-1)*20,20*(i-1),20,20);
			}else{
				btnNew.setBounds((7-1)*20,20*(i-1),20,20);
			}
			btnNew.setBackground(Color.blue);
			break;
		case 6:
			if(i<2){
				btnNew.setBounds(7*20,20*i,20,20);
			}else{
				btnNew.setBounds((7+1)*20,20*(i-2),20,20);
			}
			btnNew.setBackground(Color.magenta);
			break;
		}
	}
	//����� �ڵ����� �Ʒ��� ��ĭ�� ������. �����忡�� ���
	synchronized void moveBlock(){
		threadRunOkay = false;
		keyEventOK = false;
		//���� ����� �ؿ� �ٸ� ����� �ְų� �ٴ��� �ִ� ��쿡�� ������ ��ĭ �������� ����
		if(btnNew[0].getY()!=580&&btnNew[1].getY()!=580&&btnNew[2].getY()!=580&&btnNew[3].getY()!=580){
			if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20+1]==null&&stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20+1]==null&&
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20+1]==null&&stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20+1]==null){
				for(int i=0;i<btnNew.length;i++){
					btnNew[i].setLocation(btnNew[i].getX(), btnNew[i].getY()+20);
				}
			}
		}
		//stackedBlock�� �����Ǵ� �������� Ȯ��
		stackCurrentBlock();
		threadRunOkay = true;
		keyEventOK = true;
	}
	synchronized void stackCurrentBlock(){
		boolean nop = false;	//�ٴڿ� �ٰų� �ٸ� ����̶� �´���� ���� �ѹ� �����ϼ� �ִ� ��ȸ�� �ֱ� ���� ����ϴ� ����
		
		for (int i = 0; i < btnNew.length; i++) {
			//������ �ٴڿ� �ٰų� �ٸ� ����̶� �´��� ���� �ִ��� Ȯ���Ŀ� ���� ���� ������ stackedBlock�� �߰��ϴ� �۾��� ����
			if(!twice){
				if(btnNew[i].getY()==580){
					twice = true;
					break;
				}else if(stackedBlock[btnNew[i].getX()/20][btnNew[i].getY()/20+1]!=null){
					twice = true;
					break;
				}
			}else{
				if(btnNew[i].getY()==580){
					newBlockThread.chgIsRunning();
					if(spbar_t!=null){
						spbar_t.chgIsRunning();
						spbar_t.interrupt();
					}
					addStackedBlock();
					twice= false;
					nop = false;
					break;
				}else if(stackedBlock[btnNew[i].getX()/20][btnNew[i].getY()/20+1]!=null){
					newBlockThread.chgIsRunning();
					if(spbar_t!=null){
						spbar_t.chgIsRunning();
						spbar_t.interrupt();
					}
					addStackedBlock();
					twice= false;
					nop = false;
					break;
				}else{
					nop = true;
				}
			}
		}
		
		if(nop){
			twice = false;
		}
	}
	//stackBlock�� btnNew���� ����� �߰�
	synchronized void addStackedBlock(){
		for(int i=0;i<btnNew.length;i++){
			stackedBlock[btnNew[i].getX()/20][btnNew[i].getY()/20] = btnNew[i];
		}
		point +=100;
		clearLines();
	}
	//�� �� �ϼ��� ���� �ִ��� Ȯ��
	synchronized void clearLines(){
		boolean clear = true;	//�� �� Ŭ���� �ƴ��� Ȯ��
		lineToClear = new ArrayList<>();	//���� �� Ŭ���� ���� �� �����ϴ� arraylist
		for(int i=stackedBlock[0].length-1;i>=0;i--){	//�ؿ������� Ŭ����� ������ �ִ��� Ȯ��
			for(int j=0;j<stackedBlock.length;j++){
				if(stackedBlock[j][i]==null){	//����� �ϳ��� ������ clear false
					clear = false;
					break;
				}
			}
			if(clear){							//clear�� true�� lineToClear�� �߰�
				lineToClear.add(i);
			}else{
				clear = true;
			}
		}
		//Ŭ����� ������ �ִٸ� �ش� ������ �����ϰ�
		for(int i=0;i<lineToClear.size();i++){
			for(int j=0;j<stackedBlock.length;j++){
				gamePanel.remove(stackedBlock[j][lineToClear.get(i)]);
			}
			int next = 0;	//������ ������ ������ �� ��� ���� ������ ������ �����ͼ� ���� ������ ���ιؿ� �ִ� ��ϵ��� ������ �̵�
			if(lineToClear.size()>i+1){
				next = lineToClear.get(i+1);
			}else{
				next = -2;
			}
			for(int k=lineToClear.get(i);k>next+1;k--){
				for(int j=0;j<stackedBlock.length;j++){
					if(k<lineToClear.size()){		//����� ������ ���� �� ��ŭ ������ �̵��ϸ鼭 ���� ����� ��ĭ�� null�� ä��
						stackedBlock[j][k] = null;
					}else{
						stackedBlock[j][k+i] = stackedBlock[j][k-1];	//stackedBlock�� �����͸� �ٲٰ�
						if(stackedBlock[j][k-1]!=null){					//���� ���̴� ����� ��ġ�� ����
							stackedBlock[j][k-1].setLocation(stackedBlock[j][k-1].getX(), stackedBlock[j][k-1].getY()+20*(i+1));
						}
					}
				}
			}
		}
		//�� ���� �� ���� �ֱ�
		//�� �� ���Ž� 1000��
		//������ ���Ž� 1000*������ ��
		//�޺��� ���Ž� �ش� �޺���ŭ ���� ����
		if(lineToClear.size()!=0){
			point += 1000*lineToClear.size()*(previousClear+1);
			//�޺��� 3������� ����
			if(previousClear>3){
				previousClear=3;
			}else{
				previousClear++;
			}
		}else{
			previousClear=0;
		}
		
		//���� �뿡 ���� �ٸ� ���ǵ�� ������
		if(point>60000){
			speed = 60;
		}else if(point>30000){
			speed = 80;
		}else if(point>20000){
			speed = 100;
		}else if(point>10000){
			speed = 200;
		}else if(point>5000){
			speed = 300;
		}else if(point>500){
			speed = 400;
		}	
		
		
		pointPnl.setPointLblText(point);	//����Ʈ �гο� ���� ����
		lineToClear.clear();
		if(startStopEnd!=3){
			newBlock();							//���ο� ��� ����
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
				newBlockThread = new NewBlockThread(this,speed);
				newBlockThread.start();
			threadRunOkay = true;
			keyEventOK = true;
			
		}
		repaint();
		revalidate();
	}
	//�Ͻ����� ���� �� �Ͻ����� �г� ����
	void addPausePnl(){
		this.remove(back);
		this.remove(gamePanel);
		this.add(pausePnl);
		this.add(back);
		repaint();
		revalidate();
	}
	//�Ͻ����� ��� ���� �� ���� �г� �ٽ� ����
	void removePausePnl(){
		this.remove(pausePnl);
		this.remove(back);
		this.add(gamePanel);
		this.add(back);
		repaint();
		revalidate();
	}
	//���� �������� ������ �г� ���̱�
	void addEndPnl(int i){
		this.remove(back);
		this.remove(gamePanel);
		this.add(endPnl);
		endPnl.threadStart(i);
		this.add(gamePanel);
		this.add(back);
		repaint();
		revalidate();
	}
	//�����ӿ� ���� ȭ���� �����ϰ� ������ ���ھ� ȭ���� ������	//endpnl���� ȣ��
	void showScore(){
		tetrisMain.showScore(point);
	}
	
	synchronized void moveLeft(){
		boolean no = false;
		for(int j=0;j<btnNew.length;j++){
			if(btnNew[j].getX()==0){
				no = true;
			}
			if(!no&&btnNew[j].getX()/20-1>=0){
				if(stackedBlock[btnNew[j].getX()/20-1][btnNew[j].getY()/20]!=null){
					no = true;
				}
			}
		}
		if(!no){
			for(int i=0;i<btnNew.length;i++){
				btnNew[i].setLocation(btnNew[i].getX()-20, btnNew[i].getY());
			}
		}

	}
	
	synchronized void moveRight(){
		boolean no = false;
		for(int j=0;j<btnNew.length;j++){
			if(btnNew[j].getX()==14*20){
				no = true;
			}
			if(!no&&btnNew[j].getX()/20+1<15){
				if(stackedBlock[btnNew[j].getX()/20+1][btnNew[j].getY()/20]!=null){
					no = true;
				}
			}
		}
		if(!no){
			for(int i=0;i<btnNew.length;i++){
				btnNew[i].setLocation(btnNew[i].getX()+20, btnNew[i].getY());
			}
		}

	}
	synchronized void moveDown(){
		boolean no = false;
		for(int j=0;j<btnNew.length;j++){
			if(btnNew[j].getY()>=29*20){
				no = true;
			}
			if(!no&&btnNew[j].getY()/20!=580){
				if(stackedBlock[btnNew[j].getX()/20][btnNew[j].getY()/20+1]!=null){
					no = true;
				}
			}
		}
		if(!no){
			for(int i=0;i<btnNew.length;i++){
				btnNew[i].setLocation(btnNew[i].getX(), btnNew[i].getY()+20);
			}
		}
	}
	synchronized void rotate(){
		if(btnNew[3]!=null){
			switch (currentBlock) {
			case 0:
				tetrisRotate.rotate0(rotateNum,btnNew,stackedBlock);
				break;
			case 1:
				tetrisRotate.rotate1(rotateNum,btnNew,stackedBlock);
				break;
			case 2:
				tetrisRotate.rotate2(rotateNum,btnNew,stackedBlock);
				break;
			case 3:
				tetrisRotate.rotate3(rotateNum,btnNew,stackedBlock);
				break;
			case 4:
				tetrisRotate.rotate4(rotateNum,btnNew,stackedBlock);
				break;
			case 5:
				tetrisRotate.rotate5(rotateNum,btnNew,stackedBlock);
				break;
			case 6:
				break;
			}
			if(rotateNum<3){
				rotateNum++;
			}else{
				rotateNum = 0;
			}
			
		}
	}
	
}

class Spacebar_Thread extends Thread{
	GamePlayPanel gamePlayPanel;
	boolean isRunning = true;
	public Spacebar_Thread(GamePlayPanel gamePlayPanel) {
		this.gamePlayPanel = gamePlayPanel;
		setName("space Thread");
	}
	@Override
	public void run() {
		if(gamePlayPanel.startStopEnd!=3){
			while (!isInterrupted()&&isRunning) {
				if(gamePlayPanel.startStopEnd==1){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
//						e.printStackTrace();
					}
					if(gamePlayPanel.threadRunOkay){
						gamePlayPanel.moveBlock();
					}
				}else if(gamePlayPanel.startStopEnd==3){
					break;
				}else if(gamePlayPanel.startStopEnd==2){
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
	}
	void chgIsRunning(){
		isRunning = false;
	}
}
