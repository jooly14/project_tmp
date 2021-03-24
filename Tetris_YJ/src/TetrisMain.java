import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class TetrisMain extends JFrame implements KeyListener{
	public static void main(String[] args) {
		new TetrisMain();
	}
	GamePlayPanel gamePlayPanel;	//게임 실행 패널
	FirstPnlParentPnl firstPnlParentPnl; //첫 화면 패널
	ScorePnlParentPnl scorePnlParentPnl;	//끝 스코어 화면
	ScorePnl scorePnl;	//스코어 화면에 자식 패널 - 사용할 메서드가 있어서 따로 받아옴
	int currentPnl = 0;	//현재 패널이 어느 패널인지 말해주는 변수 //0이면 첫화면. 1이면 게임 화면. 2이면 마지막 스코어 화면 //키이벤트 처리를 따로 하기 위해서 사용 
	
	
	public TetrisMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,868);
		setLayout(null);
		
		//처음 화면을 붙인다
		firstPnlParentPnl = new FirstPnlParentPnl();
		add(firstPnlParentPnl);
		
		//키리스너를 붙인다
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
		
		setVisible(true);
	}
	//마지막 스코어 화면을 보여주는 메서드
	void showScore(int point){
		scorePnlParentPnl = new ScorePnlParentPnl(point, this);	//스코어 화면을 새로 생성
		scorePnl = scorePnlParentPnl.getChildPnl();	//스코어 자식 패널을 받아옴 //사용할 메서드가 있어서 따로 받아옴
		currentPnl=2;	//현재 패널 변수 변경
		
		remove(gamePlayPanel);	
		add(scorePnlParentPnl);
		repaint();
		revalidate();
	}
	//처음 화면을 보여주는 메서드
	void showFirstPnl(){
		firstPnlParentPnl = new FirstPnlParentPnl(); //첫 화면을 새로 생성
		currentPnl=0;	//현재 패널 변수 변경
		
		remove(scorePnlParentPnl);
		add(firstPnlParentPnl);
		repaint();
		revalidate();
	}
	//키리스너 구현
	@Override
	public void keyPressed(KeyEvent e) {
		//첫 화면일 때 키 이벤트
		if(currentPnl==0){
			currentPnl=1;
			gamePlayPanel = new GamePlayPanel(this);
			firstPnlParentPnl.chgIsRunning();	//첫 화면에 깜빡 거리는 레이블 스레드를 종료
			
			//잠시 쉬었다가
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//첫화면을 제거하고 게임 패널을 붙임
			remove(firstPnlParentPnl);
			add(gamePlayPanel);
			repaint();
			revalidate();
			
		//게임 화면일때 키 이벤트
		}else if(currentPnl==1){
			//keyEventOK와 threadRunOkay는 키이벤트와 밑으로 움직이는 스레드가 겹쳤을 때 생기는 문제를 없애기위한 변수
			//startStopEnd는 게임이 일시중지 되거나 종료 되었는지 확인 하는 변수
			if(gamePlayPanel.keyEventOK&&gamePlayPanel.startStopEnd!=3){
				gamePlayPanel.threadRunOkay = false;
					//startStopEnd=1은 현재 게임 실행 중이라는 의미
					if(gamePlayPanel.startStopEnd==1){
						if(e.getKeyCode()==KeyEvent.VK_LEFT){
							gamePlayPanel.moveLeft();
						}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
							gamePlayPanel.moveRight();
						}else if(e.getKeyCode()==KeyEvent.VK_UP){
							gamePlayPanel.rotate();
						}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
							gamePlayPanel.moveDown();
						}else if(e.getKeyCode()==KeyEvent.VK_SPACE){
							//스페이스 바를 누르면 원래 아래로 움직이는 스레드는 멈추고 스페이스바스레드(빠르게 떨어지는)를 생성
							if(gamePlayPanel.startStopEnd!=3){
								if(gamePlayPanel.spbar_t==null||!gamePlayPanel.spbar_t.isRunning){
									gamePlayPanel.spbar_t = new Spacebar_Thread(gamePlayPanel);
									gamePlayPanel.spbar_t.start();
								}
							}
						}
					}
					//startStopEnd=2은 일시중지를 의미
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						if(gamePlayPanel.startStopEnd==1){
							gamePlayPanel.startStopEnd = 2;
							gamePlayPanel.addPausePnl();
						}else{
							gamePlayPanel.startStopEnd = 1;
							gamePlayPanel.removePausePnl();
						}
					}
				
				gamePlayPanel.threadRunOkay = true;
			}
		//마지막 스코어 화면일때 키 이벤트
		}else if(currentPnl ==2){
			//이니셜로는 영문자만 입력받도록 하고 소문자를 써도 대문자로 변환되도록 만들었음 
			if (e.getKeyChar() >= 0x61 && e.getKeyChar() <= 0x7A) {
				scorePnl.addInitial((char)(e.getKeyChar()-0x20));
			} 
			else if (e.getKeyChar() >=0x41 && e.getKeyChar() <= 0x5A) {
				scorePnl.addInitial(e.getKeyChar());
			}else if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
				scorePnl.removeInitial();
			//엔터를 누르면 저장
			}else if(e.getKeyCode()==KeyEvent.VK_ENTER){
				scorePnl.saveScore();
			}
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

}
