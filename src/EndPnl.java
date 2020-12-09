import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
//게임오버 또는 타임오버가 됐을 때 셔터 닫히는 패널
//셔터 닫히는 걸 구현한 스레드
class EndPnlThread extends Thread{
	EndPnl pnl;
	public EndPnlThread(EndPnl pnl) {
		this.pnl = pnl;
	}
	@Override
	public void run() {
		for (int i = 0; i < 21; i++) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i==20){
				pnl.addGameoverBtn();
			}else{
				pnl.addBtn(i*30);
			}
		}
	}
}
//게임오버 또는 타임오버가 됐을 때 셔터 닫히는 패널
public class EndPnl extends JPanel{
	ImageIcon icon;
	JLabel btn;
	JLabel gameoverBtn;
	JLabel lbl;
	JLabel lbl2;
	GamePlayPanel gamePlayPnl;
	public EndPnl(GamePlayPanel gamePlayPnl) {
		this.gamePlayPnl = gamePlayPnl;
		setBounds(104, 97, 15*20, 30*20);
		setBackground(Color.black);
		setLayout(null);
		
		//이미지 사이즈 맞추는 작업
		icon = new ImageIcon("end.png");
		Image img = icon.getImage();
		img = img.getScaledInstance(300, 30, Image.SCALE_DEFAULT);
		icon = new ImageIcon(img);
		gameoverBtn = new JLabel(new ImageIcon("end2.png"));
	}
	void threadStart(int i){
		//마지막에 붙을 레이블을 미리 생성
		if(i==0){
			lbl = new JLabel("<html>GAME<br>OVER</html>");
			lbl2 = new JLabel("<html>GAME<br>OVER</html>");
		}else{
			lbl = new JLabel("<html>TIME<br>OVER</html>");
			lbl2 = new JLabel("<html>TIME<br>OVER</html>");
		}
		//이미지를 차례대로 붙이는 스레드 시작
		new EndPnlThread(this).start();
	}
	//스레드에서 레이블을 붙일때 사용
	void addBtn(int y){
		btn = new JLabel(icon);
		btn.setBounds(0,y,300,30);
		add(btn);
		repaint();
		revalidate();
	}
	void addGameoverBtn(){
		//다 붙이고 나서 레이블 위에 다른 레이블을 붙이면 밑에 덮이기 때문에 다 제거하고 다시 붙임
		removeAll();
		
		lbl.setBounds(45, 200, 250, 200);
		lbl2.setBounds(49, 204, 250, 200);
		lbl.setForeground(Color.pink);
		lbl2.setForeground(Color.red);
		lbl.setFont(new Font("Serif",Font.BOLD,70));
		lbl2.setFont(new Font("Serif",Font.BOLD,70));
		add(lbl);
		add(lbl2);
		//end.png를 다시 하나하나 붙이기는 힘들어서 그냥 end2.png 하나를 붙임
		
		gameoverBtn.setBounds(0,0,15*20,30*20);
		add(gameoverBtn);
		repaint();
		revalidate();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2초 쉬고 마지막 스코어 화면을 보여주는 메서드 호출
		gamePlayPnl.showScore();
	}
}

