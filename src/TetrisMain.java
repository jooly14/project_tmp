import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Thread.State;
import java.util.Calendar;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
class NewBlock extends Thread{
	TetrisMain main;
	boolean isRunning = true;
	public NewBlock(TetrisMain main) {
		this.main = main;
	}
	@Override
	public void run() {
		main.newBlock();
		while(isRunning){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			main.moveBlock();
		}
	}
	void chgIsRunning(){
		isRunning = false;
	}
}
class TimeCounter extends Thread{
	TetrisMain main;
	int timeLimit = 1000*60*5;
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
	
	public TimeCounter(TetrisMain main) {
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
		lbl.setBounds(500,30,100,100);
		main.add(lbl);
	}
	@Override
	public void run() {
		while (timeRemaining!=0&&isRunning) {
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

			if(timeRemaining<0){
				lbl.setText("TIME OUT");
				break;
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
public class TetrisMain extends JFrame implements KeyListener{
	GamePnl pnl;
	JButton[] btnNew = new JButton[4];
	NewBlock t;
	int currentBlock;
	int rotateNum;
	TimeCounter counter;
	Random random;
	JButton[][] stackedBlock = new JButton[15][30];
	Spacebar_Thread spbar_t;
	TetrisRotate tetrisRotate;
	public TetrisMain() {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,700);
		setLayout(null);
		random = new Random();
		
		
//		JButton btn = new JButton();
//		btn.setBounds(7*20,300,20,20);
//		stackedBlock[0][0] = btn;
		
		
		pnl = new GamePnl();
		pnl.addKeyListener(this);
		pnl.setFocusable(true);
		pnl.requestFocus();
		
		
		tetrisRotate = new TetrisRotate(this);
		
		pnl.setBounds(30, 30, 15*20, 30*20);
		add(pnl);
		t = new NewBlock(this);
		t.start();
		counter = new TimeCounter(this);
		counter.start();
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TetrisMain();
	}
	void newBlock(){
		currentBlock = random.nextInt(7);
		rotateNum = 0;
		System.out.println(currentBlock);
		for(int i=0;i<btnNew.length;i++){
			btnNew[i] = new JButton();
		
			randomBlock(btnNew[i],currentBlock,i);
			btnNew[i].setBorder(BorderFactory.createRaisedBevelBorder());
			pnl.add(btnNew[i]);
		}
		
	}
	void randomBlock(JButton btnNew, int ran, int i){
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
		for(int i=0;i<btnNew.length;i++){
			if(btnNew[i].getY()!=580){
				btnNew[i].setLocation(btnNew[i].getX(), btnNew[i].getY()+20);
			}
		}
		stackCurrentBlock();
	}
	synchronized void stackCurrentBlock(){
		for(int i=0;i<stackedBlock.length;i++){
			for(int j=0;j<stackedBlock[0].length;j++){
				if(stackedBlock[i][j]!=null){
					for(int k =0;k<btnNew.length;k++){
						if(btnNew[k].getX()==stackedBlock[i][j].getX()&&btnNew[k].getY()+20==stackedBlock[i][j].getY()){
							t.chgIsRunning();
							addStackedBlock();
							for(int l =0;l<btnNew.length;l++){
								if(btnNew[k].getY()==20){
									counter.chgIsRunning();
									return;
								}
							}
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							t = new NewBlock(this);
							t.start();
							return;
						}
					}
				}
			}
		}
		for(int i=0;i<btnNew.length;i++){
			if(btnNew[i].getY()==580){
				t.chgIsRunning();
				addStackedBlock();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				t = new NewBlock(this);
				t.start();
				break;
			}
		}
		
	}
	synchronized void addStackedBlock(){
		for(int i=0;i<btnNew.length;i++){
			stackedBlock[btnNew[i].getX()/20][btnNew[i].getY()/20] = btnNew[i];
//			System.out.println("!!!!!!!"+btnNew[i].getX()/20+" "+btnNew[i].getY()/20);
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			moveLeft();
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			moveRight();
		}else if(e.getKeyCode()==KeyEvent.VK_UP){
			
			
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			moveDown();
		}else if(e.getKeyCode()==KeyEvent.VK_SPACE){
			spbar_t = new Spacebar_Thread(this);
			spbar_t.start();
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	void moveLeft(){
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
	void moveRight(){
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
	void moveDown(){
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
	void rotate(){
		switch (currentBlock) {
		case 0:
			tetrisRotate.rotate0();
			break;
		case 1:
			tetrisRotate.rotate1();
			break;
		case 2:
			tetrisRotate.rotate2();
			break;
		case 3:
			tetrisRotate.rotate3();
			break;
		case 4:
			tetrisRotate.rotate4();
			break;
		case 5:
			tetrisRotate.rotate5();
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
class Spacebar_Thread extends Thread{
	TetrisMain main;
	boolean isRunning = true;
	public Spacebar_Thread(TetrisMain main) {
		this.main = main;
	}
	@Override
	public void run() {
		while (isRunning) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			main.moveBlock();
		}
	}
	void chgIsRunning(){
		isRunning = false;
	}
}
