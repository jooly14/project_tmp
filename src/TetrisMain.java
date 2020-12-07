import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TetrisMain extends JFrame implements KeyListener{
	GamePlayPanel pnl;
	FirstPnl firstPnl;
	boolean keyOk =true;
	ScorePnl scorePnl;
	int currentPnl = 0;
	public TetrisMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,868);
		setLayout(null);
		firstPnl = new FirstPnl();
		
		
		add(firstPnl);
		
		
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
		
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TetrisMain();
	}
	void showScore(int point){
		scorePnl = new ScorePnl(point);
		currentPnl++;
		remove(firstPnl);
		add(scorePnl);
		repaint();
		revalidate();
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(currentPnl==0){
			showScore(100);
			
//			pnl = new GamePlayPanel(this);
//			pnl.setLocation(0,0);
//			currentPnl++;
//			try {
//				Thread.sleep(200);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			remove(firstPnl);
//			add(pnl);
//			repaint();
//			revalidate();
		}else if(currentPnl==1){
			if(pnl.keyEventOK&&pnl.startStopEnd!=3){
				pnl.yes = false;
				if(keyOk){
					keyOk = false;
					if(pnl.startStopEnd==1){
						if(e.getKeyCode()==KeyEvent.VK_LEFT){
							pnl.moveLeft();
						}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
							pnl.moveRight();
						}else if(e.getKeyCode()==KeyEvent.VK_UP){
							pnl.rotate();
						}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
							pnl.moveDown();
						}else if(e.getKeyCode()==KeyEvent.VK_SPACE){
							if(!pnl.spbar_t.isRunning){
								pnl.spbar_t = new Spacebar_Thread(pnl);
								pnl.spbar_t.start();
							}
						}
					}
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						System.out.println(1283972184);
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
