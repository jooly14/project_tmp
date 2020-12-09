import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
//첫화면 panel
//FirstPnlParentPnl의 자식 패널
public class FirstPnl extends JPanel{
	JLabel lbl;
	FirstPnl_Thread t;	//lbl이 깜빡거리게 하는 스레드
	
	JLabel light1;	//폭죽을 스레드로 구현하려고 했는데 너무 지저분해져서 그냥 놔두기로 했다.
	JLabel light2;
	JLabel light3;
//	boolean lightThreadIsRunning = true;
	
	public FirstPnl() {
		setLayout(null);
		setBounds(0,0, 800, 868);
		setOpaque(false);
	
		light1 = new JLabel(setLightSize(new ImageIcon("light1.png"),80,110));
		light2 = new JLabel(setLightSize(new ImageIcon("light2.png"),70,100));
		light3 = new JLabel(setLightSize(new ImageIcon("light3.png"),65,90));
		light1.setBounds(100, 160, 80, 110);
		light3.setBounds(580, 250,70, 100);
		light2.setBounds(480, 70, 65, 90);
		add(light1);
		add(light2);
		add(light3);
		
		
		lbl = new JLabel("PRESS ANY KEY TO START!!");
		lbl.setForeground(Color.white);
		lbl.setFont(new Font("Serif", Font.BOLD, 30));
		lbl.setBounds(190, 600, 600, 50);
		
		showPnl();	//처음에 lbl을 붙임
		
		t = new FirstPnl_Thread(this);
		t.start();
		//폭죽 스레드는 너무 지저분해
//		new Light_Thread(this,light1).start();
//		new Light_Thread(this,light2).start();
//		new Light_Thread(this,light3).start();
		
	}
	//이미지의 사이즈를 맞게 바꾸는 메서드
	ImageIcon setLightSize(ImageIcon icon, int w, int h){
		Image img = icon.getImage();
		img = img.getScaledInstance(w, h, Image.SCALE_DEFAULT);
		return new ImageIcon(img);
	}
	//lbl을 붙이는 메서드	//스레드에서 사용
	void showPnl(){
		add(lbl);
		repaint();
		revalidate();
	}
	//lbl을 제거하는 메서드 //스레드에서 사용
	void hidePnl(){
		remove(lbl);
		repaint();
		revalidate();
	}
	//스레드를 정지시키는 메서드	//firstPnlParentPnl에서 사용
	void chgIsRunning(){
		t.chgIsRunning();
	}
	
//	폭죽 깜빡거리는 스레드에서 사용
//	boolean getLightThreadIsRunning(){
//		return lightThreadIsRunning;
//	}
//	void showLight(JLabel light){
//		add(light);
//		repaint();
//		revalidate();
//	}
//	void removeLight(JLabel light){
//		remove(light);
//		repaint();
//		revalidate();
//	}
//	void chgLightThreadIsRunning(){
//		lightThreadIsRunning = false;
//	}
}

//레이블 깜빡거리는 스레드
class FirstPnl_Thread extends Thread{
	FirstPnl firstPnl;
	boolean exist = true;
	boolean isRunning = true;
	public FirstPnl_Thread(FirstPnl firstPnl) {
		this.firstPnl = firstPnl;
	}
	@Override
	public void run() {
		while (isRunning) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			if(exist){
				firstPnl.hidePnl();
				exist = false;
			}else{
				
				firstPnl.showPnl();
				exist = true;
			}
			
		}
		
	}
	void chgIsRunning(){
		isRunning = false;
	}
}
//폭죽 깜빡거리는 스레드
//class Light_Thread extends Thread{
//	FirstPnl firstPnl;
//	JLabel light;
//	Random ran;
//	boolean show = false;
//	int sleepTime;
//	public Light_Thread(FirstPnl firstPnl, JLabel light) {
//		this.firstPnl = firstPnl;
//		this.light = light; 
//		ran = new Random();
//	}
//	@Override
//	public void run() {
//		while (firstPnl.getLightThreadIsRunning()) {
//			sleepTime = ran.nextInt(5000)+500;
//			if(show){
//				try {
//					Thread.sleep(sleepTime);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				firstPnl.removeLight(light);
//				show = false;
//			}else{
//				try {
//					Thread.sleep(200);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				firstPnl.showLight(light);
//				show = true;
//			}
//		}
//		
//	}
//}