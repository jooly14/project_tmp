import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TetrisMain extends JFrame implements ActionListener,KeyListener{
	GamePlayPanel pnl;
	JPanel firstPnl;
	public TetrisMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,868);
		setLayout(null);
		firstPnl = new JPanel();
		firstPnl.setLayout(null);
		firstPnl.setBounds(0,0, 800, 868);
		JButton b = new JButton(new ImageIcon("tetris_sky_back.png"));
		b.setBounds(-10,0, 800, 835);
		firstPnl.add(b);
		add(firstPnl);
		b.addActionListener(this);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TetrisMain();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		remove(firstPnl);
		pnl = new GamePlayPanel();
		pnl.setLocation(0,0);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
		add(pnl);
		repaint();
		revalidate();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(pnl.keyEventOK){
			pnl.yes = false;
			
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
				if(pnl.startStopEnd==1){
					pnl.startStopEnd = 2;
				}else{
					pnl.startStopEnd = 1;
				}
			}
			pnl.yes = true;
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
