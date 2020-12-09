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
//새로운 블록이 생기면 그 블록이 아래로 떨어지는 스레드
class NewBlockThread extends Thread{
	GamePlayPanel gamePlayPanel;
	boolean isRunning = true;
	int speed;	//점수가 달라지면 스피드가 달라지게 할 때 사용하는 변수
	public NewBlockThread(GamePlayPanel main,int speed) {
		this.gamePlayPanel = main;
		this.speed = speed;
	}
	@Override
	public void run() {
//		//게임이 끝난 지 않은 경우 스레드 시작
//		if(gamePlayPanel.startStopEnd!=3){
			while(isRunning){
				//실행 중일 때 블록이 아래로 떨어지는
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
				//종료되면 스레드 종료
				}else if(gamePlayPanel.startStopEnd==3){
					break;
				//일시중지 상태일 때 대기
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
//타이머 스레드
class TimeCounter extends Thread{
	GamePlayPanel gamePlayPanel;
	int timeLimit = 1000*60*5;	//시간 제한
	JLabel lbl;		//시간 레이블
	boolean isRunning = true;	
	
	long minutes;	//분 단위
	long seconds;	//초 단위
	public TimeCounter(GamePlayPanel main) {
		this.gamePlayPanel = main;
		minutes = (timeLimit / 1000) / 60 % 60;
		seconds = (timeLimit / 1000) % 60;
		lbl = new JLabel();
		//무조건 두자리 00:00 형태를 유지 
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
					gamePlayPanel.addEndPnl(1);		//게임 오버일때는 0 , 타임 오버 일때는 1
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
	int previousClear = 0;	//콤보로 줄을 깨면 점수를 더 주기 위해 필요한 변수
	
	int point;	//점수
	PointPnl pointPnl;	//포인트 보여주는 패널
	BestScorePnl bestScorePnl;	//최고 점수 보여주는 패널
	
	CycleManualPnl cycleManualPnl;	//하단에 매뉴얼 보여주는 패널
	
	boolean twice = false;	//바닥이나 다른 블록이랑 붙었을 때도 움직이는 시간을 주기 위해서 필요
	
	boolean threadRunOkay = true;	//키이벤트랑 스레드가 겹치지 않도록 하는 변수
	boolean keyEventOK = true;
	
	int startStopEnd = 1;	//현재 게임 상태를 보여주는 변수. 1은 실행 중 . 2는 일시중지. 3은 종료
	JPanel gamePanel;	//블록이 실제 보여지는 패널
	
	JLabel[] btnNew = new JLabel[4];	//새로운 블록
	NewBlockThread newBlockThread;	//새로운 블록이 움직이는 스레드	
	int currentBlock;	//현재 블록이 어떤 모양인지 저장하는 변수
	int nextBlock;		//다음 블록이 어떤 모양인지 저장하는 변수
	NextBlock nextBlockPnl;		//다음 블록을 보여주는 패널
	int rotateNum;		//현재 블록의 회전이 어떤 모양으로 하고 있는지 말해주는 변수
	TimeCounter timeCounter;	//현재 남은 시간 타이머 스레드
	Random random;		//블록 모양을 결정하기 위해 사용
	JLabel[][] stackedBlock = new JLabel[15][30];	//현재까지 누적된 블록들
	
	Spacebar_Thread spbar_t;	//스페이스바를 눌렀을 때 발생하는 스레드
	TetrisRotate tetrisRotate;	//회전을 할때 사용
	
	TetrisMain tetrisMain;	//메인 프레임
	
	int speed = 500;	//초기 스피드
	
	PausePnl pausePnl;	//일시정지 시 붙일 패널
	
	JLabel back;		//배경화면
	
	ArrayList<Integer> lineToClear;// 한줄 완성 했을 때 없앨 라인을 저장
	
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
		
		//스코어 레이블
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
		
		//베스트 스코어와 플레이어 스코어 패널
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
		
		//새로운 블록 생성
		newBlock();
		
		
		//블록 움직이는 스레드 시작
		newBlockThread = new NewBlockThread(this,speed);
		newBlockThread.start();
		
	}
	//새로운 블록 생성
	void newBlock(){
		//제일 위에 블록이 비어있지 않다면 게임 종료
		if(startStopEnd!=3){
			for(int i=0;i<stackedBlock.length;i++){
				if(stackedBlock[i][0]!=null){
					startStopEnd =3;
					spbar_t.interrupt();
					threadRunOkay =false;
					keyEventOK = false;
					newBlockThread.chgIsRunning();
					spbar_t.chgIsRunning();
					addEndPnl(0);	//게임 오버일때는 0 , 타임 오버 일때는 1
					break;
				}
			}
			
		}
		//게임 종료가 아니라면
		if(startStopEnd!=3){
			currentBlock = nextBlock;
			nextBlock = random.nextInt(7);
			nextBlockPnl.randomBlock(nextBlock);
			
			rotateNum = 0;
			
			for(int i=0;i<btnNew.length;i++){
				btnNew[i] = new JLabel();
				btnNew[i].setOpaque(true);
				
				randomBlock(btnNew[i],currentBlock,i);		//currentBlock에 따라서 다른 모양의 블록을 만들어냄
				
				btnNew[i].setBorder(BorderFactory.createRaisedBevelBorder());
				gamePanel.add(btnNew[i]);
			}
			
			
		}
		
	}
	//currentBlock에 따라서 다른 모양의 블록을 만들어냄
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
	//블록이 자동으로 아래로 한칸씩 떨어짐. 스레드에서 사용
	synchronized void moveBlock(){
		threadRunOkay = false;
		keyEventOK = false;
		//현재 블록의 밑에 다른 블록이 있거나 바닥이 있는 경우에는 밑으로 한칸 떨어지지 않음
		if(btnNew[0].getY()!=580&&btnNew[1].getY()!=580&&btnNew[2].getY()!=580&&btnNew[3].getY()!=580){
			if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20+1]==null&&stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20+1]==null&&
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20+1]==null&&stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20+1]==null){
				for(int i=0;i<btnNew.length;i++){
					btnNew[i].setLocation(btnNew[i].getX(), btnNew[i].getY()+20);
				}
			}
		}
		//stackedBlock에 누적되는 상태인지 확인
		stackCurrentBlock();
		threadRunOkay = true;
		keyEventOK = true;
	}
	synchronized void stackCurrentBlock(){
		boolean nop = false;	//바닥에 붙거나 다른 블록이랑 맞닿았을 때도 한번 움직일수 있는 기회를 주기 위해 사용하는 변수
		
		for (int i = 0; i < btnNew.length; i++) {
			//앞전에 바닥에 붙거나 다른 블록이랑 맞닿은 적이 있는지 확인후에 붙은 적이 있으면 stackedBlock에 추가하는 작업을 진행
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
	//stackBlock에 btnNew현재 블록을 추가
	synchronized void addStackedBlock(){
		for(int i=0;i<btnNew.length;i++){
			stackedBlock[btnNew[i].getX()/20][btnNew[i].getY()/20] = btnNew[i];
		}
		point +=100;
		clearLines();
	}
	//한 줄 완성된 것이 있는지 확인
	synchronized void clearLines(){
		boolean clear = true;	//한 줄 클리어 됐는지 확인
		lineToClear = new ArrayList<>();	//여러 줄 클리어 됐을 때 저장하는 arraylist
		for(int i=stackedBlock[0].length-1;i>=0;i--){	//밑에서부터 클리어된 라인이 있는지 확인
			for(int j=0;j<stackedBlock.length;j++){
				if(stackedBlock[j][i]==null){	//블록이 하나라도 없으면 clear false
					clear = false;
					break;
				}
			}
			if(clear){							//clear가 true면 lineToClear에 추가
				lineToClear.add(i);
			}else{
				clear = true;
			}
		}
		//클리어된 라인이 있다면 해당 라인을 삭제하고
		for(int i=0;i<lineToClear.size();i++){
			for(int j=0;j<stackedBlock.length;j++){
				gamePanel.remove(stackedBlock[j][lineToClear.get(i)]);
			}
			int next = 0;	//삭제될 라인이 여러줄 일 경우 다음 삭제될 라인을 가져와서 다음 삭제될 라인밑에 있는 블록들을 밑으로 이동
			if(lineToClear.size()>i+1){
				next = lineToClear.get(i+1);
			}else{
				next = -2;
			}
			for(int k=lineToClear.get(i);k>next+1;k--){
				for(int j=0;j<stackedBlock.length;j++){
					if(k<lineToClear.size()){		//블록을 삭제된 라인 수 만큼 밑으로 이동하면서 생긴 상단의 빈칸을 null로 채움
						stackedBlock[j][k] = null;
					}else{
						stackedBlock[j][k+i] = stackedBlock[j][k-1];	//stackedBlock에 데이터를 바꾸고
						if(stackedBlock[j][k-1]!=null){					//실제 보이는 블록의 위치도 변경
							stackedBlock[j][k-1].setLocation(stackedBlock[j][k-1].getX(), stackedBlock[j][k-1].getY()+20*(i+1));
						}
					}
				}
			}
		}
		//줄 깼을 때 점수 주기
		//한 줄 제거시 1000점
		//여러줄 제거시 1000*라인의 수
		//콤보로 제거시 해당 콤보만큼 곱한 점수
		if(lineToClear.size()!=0){
			point += 1000*lineToClear.size()*(previousClear+1);
			//콤보는 3배까지만 가능
			if(previousClear>3){
				previousClear=3;
			}else{
				previousClear++;
			}
		}else{
			previousClear=0;
		}
		
		//점수 대에 따라서 다른 스피드로 떨어짐
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
		
		
		pointPnl.setPointLblText(point);	//포인트 패널에 점수 변경
		lineToClear.clear();
		if(startStopEnd!=3){
			newBlock();							//새로운 블록 생성
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
	//일시중지 됐을 때 일시중지 패널 붙임
	void addPausePnl(){
		this.remove(back);
		this.remove(gamePanel);
		this.add(pausePnl);
		this.add(back);
		repaint();
		revalidate();
	}
	//일시중지 취소 됐을 때 원래 패널 다시 붙임
	void removePausePnl(){
		this.remove(pausePnl);
		this.remove(back);
		this.add(gamePanel);
		this.add(back);
		repaint();
		revalidate();
	}
	//게임 끝났을때 끝나는 패널 붙이기
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
	//프레임에 현재 화면을 제거하고 마지막 스코어 화면을 보여줌	//endpnl에서 호출
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
