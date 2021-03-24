import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class TetrisMain extends JFrame implements KeyListener{
	public static void main(String[] args) {
		new TetrisMain();
	}
	GamePlayPanel gamePlayPanel;	//���� ���� �г�
	FirstPnlParentPnl firstPnlParentPnl; //ù ȭ�� �г�
	ScorePnlParentPnl scorePnlParentPnl;	//�� ���ھ� ȭ��
	ScorePnl scorePnl;	//���ھ� ȭ�鿡 �ڽ� �г� - ����� �޼��尡 �־ ���� �޾ƿ�
	int currentPnl = 0;	//���� �г��� ��� �г����� �����ִ� ���� //0�̸� ùȭ��. 1�̸� ���� ȭ��. 2�̸� ������ ���ھ� ȭ�� //Ű�̺�Ʈ ó���� ���� �ϱ� ���ؼ� ��� 
	
	
	public TetrisMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,868);
		setLayout(null);
		
		//ó�� ȭ���� ���δ�
		firstPnlParentPnl = new FirstPnlParentPnl();
		add(firstPnlParentPnl);
		
		//Ű�����ʸ� ���δ�
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
		
		setVisible(true);
	}
	//������ ���ھ� ȭ���� �����ִ� �޼���
	void showScore(int point){
		scorePnlParentPnl = new ScorePnlParentPnl(point, this);	//���ھ� ȭ���� ���� ����
		scorePnl = scorePnlParentPnl.getChildPnl();	//���ھ� �ڽ� �г��� �޾ƿ� //����� �޼��尡 �־ ���� �޾ƿ�
		currentPnl=2;	//���� �г� ���� ����
		
		remove(gamePlayPanel);	
		add(scorePnlParentPnl);
		repaint();
		revalidate();
	}
	//ó�� ȭ���� �����ִ� �޼���
	void showFirstPnl(){
		firstPnlParentPnl = new FirstPnlParentPnl(); //ù ȭ���� ���� ����
		currentPnl=0;	//���� �г� ���� ����
		
		remove(scorePnlParentPnl);
		add(firstPnlParentPnl);
		repaint();
		revalidate();
	}
	//Ű������ ����
	@Override
	public void keyPressed(KeyEvent e) {
		//ù ȭ���� �� Ű �̺�Ʈ
		if(currentPnl==0){
			currentPnl=1;
			gamePlayPanel = new GamePlayPanel(this);
			firstPnlParentPnl.chgIsRunning();	//ù ȭ�鿡 ���� �Ÿ��� ���̺� �����带 ����
			
			//��� �����ٰ�
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//ùȭ���� �����ϰ� ���� �г��� ����
			remove(firstPnlParentPnl);
			add(gamePlayPanel);
			repaint();
			revalidate();
			
		//���� ȭ���϶� Ű �̺�Ʈ
		}else if(currentPnl==1){
			//keyEventOK�� threadRunOkay�� Ű�̺�Ʈ�� ������ �����̴� �����尡 ������ �� ����� ������ ���ֱ����� ����
			//startStopEnd�� ������ �Ͻ����� �ǰų� ���� �Ǿ����� Ȯ�� �ϴ� ����
			if(gamePlayPanel.keyEventOK&&gamePlayPanel.startStopEnd!=3){
				gamePlayPanel.threadRunOkay = false;
					//startStopEnd=1�� ���� ���� ���� ���̶�� �ǹ�
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
							//�����̽� �ٸ� ������ ���� �Ʒ��� �����̴� ������� ���߰� �����̽��ٽ�����(������ ��������)�� ����
							if(gamePlayPanel.startStopEnd!=3){
								if(gamePlayPanel.spbar_t==null||!gamePlayPanel.spbar_t.isRunning){
									gamePlayPanel.spbar_t = new Spacebar_Thread(gamePlayPanel);
									gamePlayPanel.spbar_t.start();
								}
							}
						}
					}
					//startStopEnd=2�� �Ͻ������� �ǹ�
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
		//������ ���ھ� ȭ���϶� Ű �̺�Ʈ
		}else if(currentPnl ==2){
			//�̴ϼȷδ� �����ڸ� �Է¹޵��� �ϰ� �ҹ��ڸ� �ᵵ �빮�ڷ� ��ȯ�ǵ��� ������� 
			if (e.getKeyChar() >= 0x61 && e.getKeyChar() <= 0x7A) {
				scorePnl.addInitial((char)(e.getKeyChar()-0x20));
			} 
			else if (e.getKeyChar() >=0x41 && e.getKeyChar() <= 0x5A) {
				scorePnl.addInitial(e.getKeyChar());
			}else if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
				scorePnl.removeInitial();
			//���͸� ������ ����
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
