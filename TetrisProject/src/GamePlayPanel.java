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
class NewBlock extends Thread{
	GamePlayPanel main;
	boolean isRunning = true;
	int speed;
	public NewBlock(GamePlayPanel main,int speed) {
		this.main = main;
		this.speed = speed;
	}
	@Override
	public void run() {
		if(main.startStopEnd!=3){
//			main.newBlock();
			
			while(isRunning){
				if(main.startStopEnd==1){
					try {
							Thread.sleep(speed);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(main.yes){
						main.moveBlock();
					}
					
				}else if(main.startStopEnd==3){
					break;
				}else if(main.startStopEnd==2){
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
class TimeCounter extends Thread{
	GamePlayPanel main;
	int timeLimit = 1000*60*3;
	long limit;
	long timeRemaining;
	JLabel lbl;
	boolean isRunning = true;
//	long milliseconds = 4000000;
//	long hours = (milliseconds / 1000) / 60 / 60 % 24;
//	long minutes = (milliseconds / 1000) / 60 % 60;
//	long seconds = (milliseconds / 1000) % 60;
//
//	System.out.println("hours: " + hours + ", minutes: " + minutes + ", seconds: " + seconds);
//	System.out.format("%02d:%02d:%02d", hours, minutes, seconds);
	
	long minutes = (timeLimit / 1000) / 60 % 60;
	long seconds = (timeLimit / 1000) % 60;
	
	public TimeCounter(GamePlayPanel main) {
		this.main = main;
		
		limit = Calendar.getInstance().getTimeInMillis()+timeLimit;
		timeRemaining =limit-Calendar.getInstance().getTimeInMillis();
		minutes = (timeRemaining / 1000) / 60 % 60;
		seconds = (timeRemaining / 1000) % 60;
		lbl = new JLabel();
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
		while (timeRemaining!=0&&isRunning) {
			if(main.startStopEnd==1){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				timeRemaining =limit-Calendar.getInstance().getTimeInMillis();
				minutes = (timeRemaining / 1000) / 60 % 60;
				seconds = (timeRemaining / 1000) % 60;
				if(minutes<10&&seconds<10){
					lbl.setText("0"+minutes+":0"+seconds);
				}else if(minutes<10){
					lbl.setText("0"+minutes+":"+seconds);
				}else if(seconds<10){
					lbl.setText(minutes+":0"+seconds);
				}else{
					lbl.setText(minutes+":"+seconds);
				}
	
				if(timeRemaining<=0){
//					lbl.setText("TIME OUT");
					main.startStopEnd =3;	
					main.addEndPnl(1);
					break;
				}
			}else if(main.startStopEnd==2){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				limit += 1000;
			}

		}
	}
	void chgIsRunning(){
		isRunning = false;
	}
}
class GamePnl extends JPanel{
	JButton[][] btns = new JButton[15][30];
	JButton btn;
	public GamePnl() {
		setLayout(null);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
public class GamePlayPanel extends JPanel{
	int previousClear = 0;
	int point;
	PointPnl pointPnl;
	BestScorePnl bestScorePnl;
	
	CycleManualPnl cycleManualPnl;
	
	boolean twice = false;
	boolean yes = true;
	boolean keyEventOK = true;
	int startStopEnd = 1;
	GamePnl pnl;
	JLabel[] btnNew = new JLabel[4];
	NewBlock t;
	int currentBlock;
	int nextBlock;
	int rotateNum;
	TimeCounter counter;
	Random random;
	JLabel[][] stackedBlock = new JLabel[15][30];
	Spacebar_Thread spbar_t;
	TetrisRotate tetrisRotate;
	
	NextBlock nb;
	TetrisMain main;
	
	int speed = 500;	//�ʱ� ���ǵ�
	
	PausePnl pausePnl;
	
	JLabel back;
	
	
	public GamePlayPanel(TetrisMain main) {
		this.main = main;
		// TODO Auto-generated constructor stub
		setSize(800,868);
		setLayout(null);
		random = new Random();
		setBackground(Color.BLACK);
//		JButton btn = new JButton();
//		btn.setBounds(7*20,300,20,20);
//		stackedBlock[0][0] = btn;
		
		pausePnl = new PausePnl();
		
		
		back = new JLabel(new ImageIcon("tetris_main.png"));
		back.setBounds(-10, 30, 800, 800);
		
	
		
		nb = new NextBlock(this);
		nb.setBounds(550,400,200,150);
		nb.setBackground(new Color(0x242554));
		
		nextBlock = random.nextInt(7);
		
		tetrisRotate = new TetrisRotate(this);
		
		counter = new TimeCounter(this);
		counter.start();
		
		
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
		
		bestScorePnl = new BestScorePnl(this);
		add(bestScorePnl);
		pointPnl = new PointPnl(this,point,bestScorePnl.getBestScore(),bestScorePnl);
		
		cycleManualPnl = new CycleManualPnl(this);
		add(cycleManualPnl);
		
		
		pnl = new GamePnl();
		pnl.setBounds(104, 97, 15*20, 30*20);
		pnl.setBackground(Color.black);
		add(pointPnl);
		add(nb);
		add(pnl);
		add(back);
		newBlock();
		t = new NewBlock(this,speed);
		t.start();
		spbar_t = new Spacebar_Thread(this);
		spbar_t.chgIsRunning();
		
	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new TetrisMain();
//	}
	void newBlock(){
		if(startStopEnd!=3){
			for(int i=0;i<stackedBlock.length;i++){
				if(stackedBlock[i][0]!=null){
					//��
					startStopEnd =3;
					yes =false;
					keyEventOK = false;
					System.out.println("end");
					addEndPnl(0);
					break;
				}
			}
			
		}
		if(startStopEnd!=3){
			currentBlock = nextBlock;
//		currentBlock = 5;
			nextBlock = random.nextInt(7);
			nb.randomBlock(nextBlock);
			rotateNum = 0;
			System.out.println(currentBlock);
			for(int i=0;i<btnNew.length;i++){
				btnNew[i] = new JLabel();
				btnNew[i].setOpaque(true);
				randomBlock(btnNew[i],currentBlock,i);
				
//			if(stackedBlock[btnNew[i].getX()/20][0]!=null){
//				counter.chgIsRunning();
//				t.chgIsRunning();
//				spbar_t.chgIsRunning();
//			}
				
				btnNew[i].setBorder(BorderFactory.createRaisedBevelBorder());
				pnl.add(btnNew[i]);
			}
			
			
		}
		
	}
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
	synchronized void moveBlock(){
		yes = false;
		keyEventOK = false;
		if(btnNew[0].getY()!=580&&btnNew[1].getY()!=580&&btnNew[2].getY()!=580&&btnNew[3].getY()!=580){
			if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20+1]==null&&stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20+1]==null&&
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20+1]==null&&stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20+1]==null){
				for(int i=0;i<btnNew.length;i++){
					btnNew[i].setLocation(btnNew[i].getX(), btnNew[i].getY()+20);
				}
			}
		}
		stackCurrentBlock();
		yes = true;
		keyEventOK = true;
	}
	synchronized void stackCurrentBlock(){
		boolean nop = false;
		for (int i = 0; i < btnNew.length; i++) {
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
					t.chgIsRunning();
					spbar_t.chgIsRunning();
					addStackedBlock();
					twice= false;
					nop = false;
					break;
				}else if(stackedBlock[btnNew[i].getX()/20][btnNew[i].getY()/20+1]!=null){
					t.chgIsRunning();
					spbar_t.chgIsRunning();
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
//
//		for(int i=0;i<stackedBlock.length;i++){
//			for(int j=0;j<stackedBlock[0].length;j++){
//				if(stackedBlock[i][j]!=null){
//					for(int k =0;k<btnNew.length;k++){
//						if(btnNew[k].getX()==stackedBlock[i][j].getX()&&btnNew[k].getY()+20==stackedBlock[i][j].getY()){
//							t.chgIsRunning();
//							spbar_t.chgIsRunning();
//							addStackedBlock();
//							for(int l =0;l<btnNew.length;l++){
//								if(btnNew[k].getY()==20){
//									counter.chgIsRunning();
//									return;
//								}
//							}
//							
//							return;
//						}
//					}
//				}
//			}
//		}
//		for(int i=0;i<btnNew.length;i++){
//			if(btnNew[i].getY()==580){
//				t.chgIsRunning();
//				spbar_t.chgIsRunning();
//				addStackedBlock();
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				t = new NewBlock(this,speed);
//				t.start();
//				break;
//			}
//		}

	}
	synchronized void addStackedBlock(){
		for(int i=0;i<btnNew.length;i++){
			stackedBlock[btnNew[i].getX()/20][btnNew[i].getY()/20] = btnNew[i];
//			System.out.println("!!!!!!!"+btnNew[i].getX()/20+" "+btnNew[i].getY()/20);
		}
		point +=100;
		clearLines();
	}
	synchronized void clearLines(){
		System.out.println("clearLines start");
		boolean clear = true;
		ArrayList<Integer> lineToClear = new ArrayList<>();
		for(int i=stackedBlock[0].length-1;i>=0;i--){
			for(int j=0;j<stackedBlock.length;j++){
				if(stackedBlock[j][i]==null){
					clear = false;
					break;
				}
			}
			if(clear){
				lineToClear.add(i);
			}else{
				clear = true;
			}
		}
		for(int i=0;i<lineToClear.size();i++){
			for(int j=0;j<stackedBlock.length;j++){
				pnl.remove(stackedBlock[j][lineToClear.get(i)]);
			}
			int next = 0;
			if(lineToClear.size()>i+1){
				next = lineToClear.get(i+1);
			}else{
				next = -2;
			}
			for(int k=lineToClear.get(i);k>next+1;k--){
				for(int j=0;j<stackedBlock.length;j++){
					if(k<lineToClear.size()){
						stackedBlock[j][k] = null;
					}else{
						stackedBlock[j][k+i] = stackedBlock[j][k-1];
						if(stackedBlock[j][k-1]!=null){
							stackedBlock[j][k-1].setLocation(stackedBlock[j][k-1].getX(), stackedBlock[j][k-1].getY()+20*(i+1));
						}
					}
				}
			}
		}
		//�� ���� �� ���� �ֱ�
		if(lineToClear.size()!=0){
			point += 1000*lineToClear.size()*(previousClear+1);
			if(previousClear>3){
				previousClear=3;
			}else{
				previousClear++;
			}
		}else{
			previousClear=0;
		}
		pointPnl.setPointLblText(point);
		newBlock();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t = new NewBlock(this,speed);
		t.start();
		yes = true;
		keyEventOK = true;
		repaint();
		revalidate();
		
		if(point>30000){
			speed = 60;
		}else if(point>20000){
			speed = 80;
		}else if(point>15000){
			speed = 100;
		}else if(point>10000){
			speed = 200;
		}else if(point>5000){
			speed = 300;
		}else if(point>500){
			speed = 400;
		}	
			
			
	}
	
	void addPausePnl(){
		this.remove(back);
		this.remove(pnl);
		this.add(pausePnl);
		this.add(back);
		repaint();
		revalidate();
	}
	void removePausePnl(){
		this.remove(pausePnl);
		this.remove(back);
		this.add(pnl);
		this.add(back);
		repaint();
		revalidate();
	}
	void addEndPnl(int i){
		this.remove(back);
		this.remove(pnl);
		this.add(new EndPnl(i,this));
		this.add(pnl);
		this.add(back);
		repaint();
		revalidate();
	}
	void showScore(){
		main.showScore(point);
	}
//	@Override
//	public void keyPressed(KeyEvent e) {
//		if(keyEventOK){
//			yes = false;
//			if(e.getKeyCode()==KeyEvent.VK_LEFT){
//				moveLeft();
//			}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
//				moveRight();
//			}else if(e.getKeyCode()==KeyEvent.VK_UP){
//				rotate();
//			}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
//					moveDown();
//			}else if(e.getKeyCode()==KeyEvent.VK_SPACE){
//					if(!spbar_t.isRunning){
//						spbar_t = new Spacebar_Thread(this);
//						spbar_t.start();
//					}
//			}
//			yes = true;
//		}
//	}
//	@Override
//	public void keyReleased(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void keyTyped(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
	synchronized void moveLeft(){
		boolean no = false;
		for(int j=0;j<btnNew.length;j++){
			if(btnNew[j].getX()==0){
				no = true;
			}
			if(btnNew[j].getX()/20-1>=0){
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
			if(btnNew[j].getX()/20+1<15){
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
			if(btnNew[j].getY()==28*20){
				no = true;
			}
			if(btnNew[j].getY()/20+2<30){
				if(stackedBlock[btnNew[j].getX()/20][btnNew[j].getY()/20+2]!=null){
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
	GamePlayPanel main;
	boolean isRunning = true;
	public Spacebar_Thread(GamePlayPanel main) {
		this.main = main;
	}
	@Override
	public void run() {
		if(main.startStopEnd!=3){
			while (isRunning) {
				if(main.startStopEnd==1){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(main.yes){
						main.moveBlock();
					}
				}else if(main.startStopEnd==3){
					break;
				}else if(main.startStopEnd==2){
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
