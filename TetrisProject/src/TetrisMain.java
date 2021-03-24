import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TetrisMain extends JFrame {
	GamePlayPanel pnl;
	FirstPnlParentPnl firstPnlParentPnl;
	boolean keyOk =true;
	ScorePnlParentPnl scorePnlParentPnl;
	ScorePnl scorePnl;
	int currentPnl = 0;
	
	public TetrisMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,868);
		setLayout(null);
		
		
		
		firstPnlParentPnl = new FirstPnlParentPnl();
		add(firstPnlParentPnl);
		this.addKeyListener(new KeyHandler(this));
		this.setFocusable(true);
		this.requestFocus();
		
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TetrisMain();
	}
	void showScore(int point){
		scorePnlParentPnl = new ScorePnlParentPnl(point, this);
		scorePnl = scorePnlParentPnl.getChildPnl();
		currentPnl=2;
		remove(pnl);
		add(scorePnlParentPnl);
		repaint();
		revalidate();
		
	}
	void showFirstPnl(){
		remove(scorePnlParentPnl);
		currentPnl=0;
		firstPnlParentPnl = new FirstPnlParentPnl();
		add(firstPnlParentPnl);
		repaint();
		revalidate();
	}
//	@Override
//	public void keyPressed(KeyEvent e) {
//		if(currentPnl==0){
//			pnl = new GamePlayPanel(this);
//			pnl.setLocation(0,0);
//			currentPnl=1;
//			try {
//				Thread.sleep(200);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			firstPnlParentPnl.chgIsRunning();
//			remove(firstPnlParentPnl);
//			add(pnl);
//			repaint();
//			revalidate();
//		}else if(currentPnl==1){
//			if(pnl.keyEventOK&&pnl.startStopEnd!=3){
//				pnl.yes = false;
//				if(keyOk){
//					keyOk = false;
//					if(pnl.startStopEnd==1){
//						if(e.getKeyCode()==KeyEvent.VK_LEFT){
//							pnl.moveLeft();
//						}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
//							pnl.moveRight();
//						}else if(e.getKeyCode()==KeyEvent.VK_UP){
//							pnl.rotate();
//						}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
//							pnl.moveDown();
//						}else if(e.getKeyCode()==KeyEvent.VK_SPACE){
//							if(!pnl.spbar_t.isRunning){
//								pnl.spbar_t = new Spacebar_Thread(pnl);
//								pnl.spbar_t.start();
//							}
//						}
//					}
//					if(e.getKeyCode()==KeyEvent.VK_ENTER){
//						if(pnl.startStopEnd==1){
//							pnl.startStopEnd = 2;
//							pnl.addPausePnl();
//						}else{
//							pnl.startStopEnd = 1;
//							pnl.removePausePnl();
//						}
//					}
//				}
//				keyOk = true;
//				pnl.yes = true;
//			}
//		}else if(currentPnl ==2){
//			if (e.getKeyChar() >= 0x61 && e.getKeyChar() <= 0x7A) {
//			    // ����(�ҹ���) OK!
//				scorePnl.addInitial((char)(e.getKeyChar()-0x20));
//			} 
//			else if (e.getKeyChar() >=0x41 && e.getKeyChar() <= 0x5A) {
//			    // ����(�빮��) OK!
//				scorePnl.addInitial(e.getKeyChar());
//			}else if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
//				scorePnl.removeInitial();
//			}else if(e.getKeyCode()==KeyEvent.VK_ENTER){
//				scorePnl.saveScore();
//			}
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

	
	   class KeyHandler implements KeyListener{
		   
	        /**
	        * keyPressed()���� �߻��� Ű�ڵ带 �Ʒ��� HashSet�� �����ϸ� Ÿ�̸� �̺�Ʈ �ڵ鷯����
	        * Ű�ڵ带 Ȯ���Ͽ� ȭ���� �����ϴ� �ڵ带 �����Ѵ�
	        * ���⼭ �÷��� �߿��� Set�� ������ ������ Ű�ڵ尡 �ߺ��Ǿ� �����ϴ� ���� ���� Ű�� �� ��
	        * HashSet���� �ش� Ű�ڵ带 �Ѱ��� �������ָ� ��� �̺�Ʈ ȿ���� ���ŵǹǷ� �̺�Ʈ�� ��� �����ϴ� ȿ���� �� �� �ִ�
	        */
	        HashSet<Integer> pressedKeys = new HashSet<Integer>();
	        Timer timer;
	 
	        TetrisMain tetrisMain;
	        public KeyHandler(TetrisMain tetrisMain)
	        {
	        	this.tetrisMain = tetrisMain;
	            timer = new Timer(50, new ActionListener(){ // 50ms���� �׼� �̺�Ʈ �߻�
	                @Override
	                public void actionPerformed(ActionEvent arg0) // 50ms���� �߻��� �׼� �̺�Ʈ ó��
	                {  
	                    if(!pressedKeys.isEmpty()){
	                        Iterator<Integer> i = pressedKeys.iterator();
	                        int n = 0;
	                        while(i.hasNext()){
	                            n = i.next();
	                            
	                            if(currentPnl==0){
	                    			pnl = new GamePlayPanel(tetrisMain);
	                    			pnl.setLocation(0,0);
	                    			currentPnl=1;
	                    			try {
	                    				Thread.sleep(200);
	                    			} catch (InterruptedException e1) {
	                    				// TODO Auto-generated catch block
	                    				e1.printStackTrace();
	                    			}
	                    			firstPnlParentPnl.chgIsRunning();
	                    			remove(firstPnlParentPnl);
	                    			add(pnl);
	                    			repaint();
	                    			revalidate();
	                    		}else if(currentPnl==1){
	                    			if(pnl.keyEventOK&&pnl.startStopEnd!=3){
	                    				pnl.yes = false;
	                    				if(keyOk){
	                    					keyOk = false;
	                    					if(pnl.startStopEnd==1){
	                    						if(n==KeyEvent.VK_LEFT){
	                    							pnl.moveLeft();
	                    						}else if(n==KeyEvent.VK_RIGHT){
	                    							pnl.moveRight();
	                    						}else if(n==KeyEvent.VK_UP){
	                    							pnl.rotate();
	                    						}else if(n==KeyEvent.VK_DOWN){
	                    							pnl.moveDown();
	                    						}else if(n==KeyEvent.VK_SPACE){
	                    							if(!pnl.spbar_t.isRunning){
	                    								pnl.spbar_t = new Spacebar_Thread(pnl);
	                    								pnl.spbar_t.start();
	                    							}
	                    						}
	                    					}
	                    					if(n==KeyEvent.VK_ENTER){
	                    						if(pnl.startStopEnd==1){
	                    							pnl.startStopEnd = 2;
	                    							pnl.addPausePnl();
	                    						}else{
	                    							pnl.startStopEnd = 1;
	                    							pnl.removePausePnl();
	                    						}
	                    					}
	                    				}
	                    				keyOk = true;
	                    				pnl.yes = true;
	                    			}
	                    		}else if(currentPnl ==2){
	                    			if (n >= 97 && n <= 122) {
	                    			    // ����(�ҹ���) OK!
	                    				scorePnl.addInitial((char)(n-32));
	                    			} 
	                    			else if (n >=65 && n <= 90) {
	                    			    // ����(�빮��) OK!
	                    				scorePnl.addInitial((char)n);
	                    			}else if(n==KeyEvent.VK_BACK_SPACE){
	                    				scorePnl.removeInitial();
	                    			}else if(n==KeyEvent.VK_ENTER){
	                    				scorePnl.saveScore();
	                    			}
	                    		}
	                            
	                            
	                            
	                            
	                            
	                            
	                            repaint();
	                        }
	                    }else {
	                        timer.stop();
	                    }
	                }
	            });
	        }
	 
	        @Override
	        public void keyPressed(KeyEvent keyEvent){
	            //�߻��� Ű�ڵ带 HsshSet�� �����Ѵ�
	            int keyCode = keyEvent.getKeyCode();
	            pressedKeys.add(keyCode);
	            if(!timer.isRunning()) timer.start();
	        }
	        @Override
	        public void keyReleased(KeyEvent keyEvent){
	            //HashSet���� Ű�ڵ带 �����Ѵ�
	            int keyCode = keyEvent.getKeyCode();
	            pressedKeys.remove(keyCode);
	        }
	        @Override
	        public void keyTyped(KeyEvent keyEvent){}
	    }
	}


