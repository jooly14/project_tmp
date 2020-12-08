import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
class ScorePnl_Thread extends Thread{
	ScorePnl scorePnl;
//	boolean isRunning = true;
	int playerInsertIndex;
	public ScorePnl_Thread(ScorePnl scorePnl, int playerInsertIndex) {
		this.scorePnl =scorePnl;
		this.playerInsertIndex = playerInsertIndex;
	}
	@Override
	public void run() {
		if(playerInsertIndex>4){
				for (int i = 0; i <6;i++) {
					for (int j = 640; j >= 0; j-=10) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						scorePnl.showScore(i,j);
					}
				}
			
		}else{
			for (int i = 0; i < 5;i++) {
				for (int j = 640; j >= 0; j-=10) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					scorePnl.showScore(i,j);
				}
			}					
		}
		scorePnl.chgAfterShow();
			
	}
//	void chgIsRunning(){
//		isRunning = false;
//	}
}
public class ScorePnl extends JPanel{
	int point;
	ArrayList<Integer> nums = new ArrayList<>();
	ArrayList<String> names = new ArrayList<>();
	ArrayList<Integer> points = new ArrayList<>();
	int playerInsertIndex;
	JLabel lbl;
	JLabel lbl2;
	ArrayList<JLabel> lblNum = new ArrayList<>();
	ArrayList<JLabel> lblName= new ArrayList<>();
	ArrayList<JLabel> lblPoint= new ArrayList<>();
	JLabel b;
	JLabel playerName;
	String nameP;
	boolean afterShow;
	TetrisMain main;
	public ScorePnl(int point ,TetrisMain main) {
		this.main = main;
		this.point = point;
		setLayout(null);
		setBounds(0,0, 800, 868);
		b = new JLabel(new ImageIcon("menu.png"));
		b.setBounds(-10,0, 800, 835);
		
		checkScore();
		
		lbl = new JLabel("BEST SCORE");
		lbl.setForeground(Color.red);
		lbl.setFont(new Font("Serif", Font.BOLD, 50));
		lbl.setBounds(230,25, 500, 100);
		lbl2 = new JLabel("<<  ENTER YOUR NAME  >>");
		lbl2.setForeground(Color.white);
		lbl2.setFont(new Font("Serif", Font.BOLD, 20));
		lbl2.setBounds(260,25+50, 500, 100);
		add(lbl);
		add(lbl2);
		add(b);
		
		new ScorePnl_Thread(this,playerInsertIndex).start();
//		showScore();
	}
	void showScore(int i, int j){
		
		for (int j2 = 0; j2 < lblName.size(); j2++) {
				add(lblName.get(j2));
		}
		for (int j2 = 0; j2 < lblPoint.size(); j2++) {
			add(lblPoint.get(j2));
		}
		for (int j2 = 0; j2 < lblNum.size(); j2++) {
			add(lblNum.get(j2));
		}
		if(playerName!=null){
			add(playerName);
		}
		if(playerInsertIndex>4){
			if(i==5){
				if(j==640){
					lblNum.add(new JLabel(nums.get(playerInsertIndex)+""));
					lblNum.get(lblNum.size()-1).setForeground(Color.white);
					lblNum.get(lblNum.size()-1).setFont(new Font("Serif", Font.BOLD, 30));
					lblNum.get(lblNum.size()-1).setBounds(150+j,80*6+40, 200, 100);
					add(lblNum.get(lblNum.size()-1));
					playerName = new JLabel(names.get(playerInsertIndex));
					playerName.setForeground(Color.blue);
					playerName.setFont(new Font("Serif", Font.BOLD, 30));
					playerName.setBounds(350+j,80*6+40, 200, 100);
					add(playerName);
					lblPoint.add(new JLabel(points.get(playerInsertIndex)+""));
					lblPoint.get(lblPoint.size()-1).setForeground(Color.white);
					lblPoint.get(lblPoint.size()-1).setFont(new Font("Serif", Font.BOLD, 30));
					lblPoint.get(lblPoint.size()-1).setBounds(550+j,80*6+40, 200, 100);
					add(lblPoint.get(lblPoint.size()-1));
				}else{
					lblNum.get(lblNum.size()-1).setBounds(150+j,80*6+40, 200, 100);
					playerName.setBounds(350+j,80*6+40, 200, 100);
					lblPoint.get(lblPoint.size()-1).setBounds(550+j,80*6+40, 200, 100);
					add(lblNum.get(lblNum.size()-1));
					add(playerName);
					add(lblPoint.get(lblPoint.size()-1));

				}
			}else{
				if(j==640){
					lblNum.add(new JLabel(nums.get(i)+""));
					lblNum.get(lblNum.size()-1).setForeground(Color.white);
					lblNum.get(lblNum.size()-1).setFont(new Font("Serif", Font.BOLD, 30));
					lblNum.get(lblNum.size()-1).setBounds(150+j,80*(i+1)+40, 200, 100);
					add(lblNum.get(lblNum.size()-1));
					lblName.add(new JLabel(names.get(i)));
					lblName.get(lblName.size()-1).setForeground(Color.white);
					lblName.get(lblName.size()-1).setFont(new Font("Serif", Font.BOLD, 30));
					lblName.get(lblName.size()-1).setBounds(350+j,80*(i+1)+40, 200, 100);
					add(lblName.get(lblName.size()-1));
					lblPoint.add(new JLabel(points.get(i)+""));
					lblPoint.get(lblPoint.size()-1).setForeground(Color.white);
					lblPoint.get(lblPoint.size()-1).setFont(new Font("Serif", Font.BOLD, 30));
					lblPoint.get(lblPoint.size()-1).setBounds(550+j,80*(i+1)+40, 200, 100);
					add(lblPoint.get(lblPoint.size()-1));
				}else{
					lblNum.get(lblNum.size()-1).setBounds(150+j,80*(i+1)+40, 200, 100);
					lblName.get(lblName.size()-1).setBounds(350+j,80*(i+1)+40, 200, 100);
					lblPoint.get(lblPoint.size()-1).setBounds(550+j,80*(i+1)+40, 200, 100);
					add(lblNum.get(lblNum.size()-1));
					add(lblName.get(lblName.size()-1));
					add(lblPoint.get(lblPoint.size()-1));

				}
			}
		}else{
			if(i==playerInsertIndex){
				if(j==640){
					lblNum.add(new JLabel(nums.get(playerInsertIndex)+""));
					lblNum.get(lblNum.size()-1).setForeground(Color.white);
					lblNum.get(lblNum.size()-1).setFont(new Font("Serif", Font.BOLD, 30));
					lblNum.get(lblNum.size()-1).setBounds(150+j,80*(i+1)+40, 200, 100);
					add(lblNum.get(lblNum.size()-1));
					playerName = new JLabel(names.get(playerInsertIndex));
					playerName.setForeground(Color.blue);
					playerName.setFont(new Font("Serif", Font.BOLD, 30));
					playerName.setBounds(350+j,80*(i+1)+40, 200, 100);
					add(playerName);
					lblPoint.add(new JLabel(points.get(playerInsertIndex)+""));
					lblPoint.get(lblPoint.size()-1).setForeground(Color.white);
					lblPoint.get(lblPoint.size()-1).setFont(new Font("Serif", Font.BOLD, 30));
					lblPoint.get(lblPoint.size()-1).setBounds(550+j,80*(i+1)+40, 200, 100);
					add(lblPoint.get(lblPoint.size()-1));
				}else{
					lblNum.get(lblNum.size()-1).setBounds(150+j,80*(i+1)+40, 200, 100);
					playerName.setBounds(350+j,80*(i+1)+40, 200, 100);
					lblPoint.get(lblPoint.size()-1).setBounds(550+j,80*(i+1)+40, 200, 100);
					add(lblNum.get(lblNum.size()-1));
					add(playerName);
					add(lblPoint.get(lblPoint.size()-1));

				}
			}else{
				if(j==640){
					lblNum.add(new JLabel(nums.get(i)+""));
					lblNum.get(lblNum.size()-1).setForeground(Color.white);
					lblNum.get(lblNum.size()-1).setFont(new Font("Serif", Font.BOLD, 30));
					lblNum.get(lblNum.size()-1).setBounds(150+j,80*(i+1)+40, 200, 100);
					add(lblNum.get(lblNum.size()-1));
					lblName.add(new JLabel(names.get(i)));
					lblName.get(lblName.size()-1).setForeground(Color.white);
					lblName.get(lblName.size()-1).setFont(new Font("Serif", Font.BOLD, 30));
					lblName.get(lblName.size()-1).setBounds(350+j,80*(i+1)+40, 200, 100);
					add(lblName.get(lblName.size()-1));
					lblPoint.add(new JLabel(points.get(i)+""));
					lblPoint.get(lblPoint.size()-1).setForeground(Color.white);
					lblPoint.get(lblPoint.size()-1).setFont(new Font("Serif", Font.BOLD, 30));
					lblPoint.get(lblPoint.size()-1).setBounds(550+j,80*(i+1)+40, 200, 100);
					add(lblPoint.get(lblPoint.size()-1));
				}else{
					lblNum.get(lblNum.size()-1).setBounds(150+j,80*(i+1)+40, 200, 100);
					lblName.get(lblName.size()-1).setBounds(350+j,80*(i+1)+40, 200, 100);
					lblPoint.get(lblPoint.size()-1).setBounds(550+j,80*(i+1)+40, 200, 100);
					add(lblNum.get(lblNum.size()-1));
					add(lblName.get(lblName.size()-1));
					add(lblPoint.get(lblPoint.size()-1));
				}
			}
		}
		remove(lbl);
		remove(lbl2);
		remove(b);
		add(lbl);
		add(lbl2);
		add(b);
		repaint();
		revalidate();
	}
	void chgAfterShow(){
		afterShow = true;
	}
	void showScore(){
		if(playerInsertIndex>4){
			for (int i = 0; i < 5; i++) {
				lbl2 = new JLabel(nums.get(i)+"");
				lbl2.setForeground(Color.white);
				lbl2.setFont(new Font("Serif", Font.BOLD, 30));
				lbl2.setBounds(150,80*(i+1)+40, 200, 100);
				add(lbl2);
				lbl2 = new JLabel(names.get(i));
				lbl2.setForeground(Color.white);
				lbl2.setFont(new Font("Serif", Font.BOLD, 30));
				lbl2.setBounds(350,80*(i+1)+40, 200, 100);
				add(lbl2);
				lbl2 = new JLabel(points.get(i)+"");
				lbl2.setForeground(Color.white);
				lbl2.setFont(new Font("Serif", Font.BOLD, 30));
				lbl2.setBounds(550,80*(i+1)+40, 200, 100);
				add(lbl2);
			}
			lbl2 = new JLabel(nums.get(playerInsertIndex)+"");
			lbl2.setForeground(Color.white);
			lbl2.setFont(new Font("Serif", Font.BOLD, 30));
			lbl2.setBounds(150,80*6+40, 200, 100);
			add(lbl2);
			playerName = new JLabel(names.get(playerInsertIndex));
			playerName.setForeground(Color.blue);
			playerName.setFont(new Font("Serif", Font.BOLD, 30));
			playerName.setBounds(350,80*6+40, 200, 100);
			add(playerName);
			lbl2 = new JLabel(points.get(playerInsertIndex)+"");
			lbl2.setForeground(Color.white);
			lbl2.setFont(new Font("Serif", Font.BOLD, 30));
			lbl2.setBounds(550,80*6+40, 200, 100);
			add(lbl2);
		}else{
			for (int i = 0; i < 5; i++) {
				if(i==playerInsertIndex){
					lbl2 = new JLabel(nums.get(i)+"");
					lbl2.setForeground(Color.white);
					lbl2.setFont(new Font("Serif", Font.BOLD, 30));
					lbl2.setBounds(150,80*(i+1)+40, 200, 100);
					add(lbl2);
					playerName = new JLabel(names.get(i));
					playerName.setForeground(Color.blue);
					playerName.setFont(new Font("Serif", Font.BOLD, 30));
					playerName.setBounds(350,80*(i+1)+40, 200, 100);
					add(playerName);
					lbl2 = new JLabel(points.get(i)+"");
					lbl2.setForeground(Color.white);
					lbl2.setFont(new Font("Serif", Font.BOLD, 30));
					lbl2.setBounds(550,80*(i+1)+40, 200, 100);
					add(lbl2);
				}else{
					lbl2 = new JLabel(nums.get(i)+"");
					lbl2.setForeground(Color.white);
					lbl2.setFont(new Font("Serif", Font.BOLD, 30));
					lbl2.setBounds(150,80*(i+1)+40, 200, 100);
					add(lbl2);
					lbl2 = new JLabel(names.get(i));
					lbl2.setForeground(Color.white);
					lbl2.setFont(new Font("Serif", Font.BOLD, 30));
					lbl2.setBounds(350,80*(i+1)+40, 200, 100);
					add(lbl2);
					lbl2 = new JLabel(points.get(i)+"");
					lbl2.setForeground(Color.white);
					lbl2.setFont(new Font("Serif", Font.BOLD, 30));
					lbl2.setBounds(550,80*(i+1)+40, 200, 100);
					add(lbl2);
					
				}
			}
			
		}
	}
	void checkScore(){
		readFile();
		playerInsertIndex = nums.size();
		for (int i = 0; i < nums.size(); i++) {
			if(point>=points.get(i)){
				playerInsertIndex = i;
				break;
			}
		}
		if(playerInsertIndex== nums.size()){
			points.add(point);
			nums.add(playerInsertIndex+1);
			names.add("");
		}else{
			points.add(playerInsertIndex, point);
			nums.add(playerInsertIndex, nums.get(playerInsertIndex));
			names.add(playerInsertIndex, "");
		}
		if(point==points.get(playerInsertIndex)){
			for (int i = playerInsertIndex+1; i < nums.size(); i++) {
				if(point!=points.get(i)){
					nums.set(i, nums.get(i)+1);
				}
			}
		}else{
			for (int i = playerInsertIndex+1; i < nums.size(); i++) {
					nums.set(i, nums.get(i)+1);
			}
		}
//		for (int i = 0; i < nums.size(); i++) {
//			System.out.println(nums.get(i)+" "+names.get(i)+" "+points.get(i));
//		}
	}
	void readFile(){
		FileReader fr = null;
		BufferedReader br = null;
		String str = null;
		String[] split = null;
		try {
			fr = new FileReader("score.txt");
			br = new BufferedReader(fr);
			while((str=br.readLine())!=null){
				split = str.split("/");
				nums.add(Integer.parseInt(split[0]));
				names.add(split[1]);
				points.add(Integer.parseInt(split[2]));
			}			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				try {
					if(br!=null){
					br.close();
					}
					if(fr!= null){
						fr.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	void addInitial(char c){
		if(afterShow){
			if(playerName.getText().length()<3){
				playerName.setText(playerName.getText()+c);
			}
			if(playerName.getText().length()==3){
				nameP = playerName.getText();
				lbl2 = new JLabel("<<  PRESS ENTER TO SAVE  >>");
				lbl2.setForeground(Color.white);
				lbl2.setFont(new Font("Serif", Font.BOLD, 20));
				lbl2.setBounds(240,25+50, 500, 100);
				removeAll();
				add(lbl2);
				add(lbl);
				showScore();
				playerName.setText(nameP);
				add(b);
				repaint();
				revalidate();
			}
		}
	}
	void removeInitial(){
		if(afterShow){
			if(playerName.getText().length()==3){
				lbl2 = new JLabel("<<  ENTER YOUR NAME  >>");
				lbl2.setForeground(Color.white);
				lbl2.setFont(new Font("Serif", Font.BOLD, 20));
				lbl2.setBounds(260,25+50, 500, 100);
				removeAll();
				add(lbl2);
				add(lbl);
				showScore();
				playerName.setText(nameP);
				add(b);
				repaint();
				revalidate();
			}
			if(playerName.getText().length()!=0){
				playerName.setText(playerName.getText().substring(0, playerName.getText().length()-1));
			}
			
		}
	}
	void saveScore(){
		if(afterShow){
			if(playerName.getText().length()==3){
				//¿˙¿Â
				FileWriter fw = null;
				PrintWriter pw = null;
				
				try {
					fw = new FileWriter("score.txt");
					pw = new PrintWriter(fw);
					for (int j = 0; j < nums.size(); j++) {
						if(j==playerInsertIndex){
							pw.println(nums.get(j)+"/"+playerName.getText()+"/"+points.get(j));
						}else{
							pw.println(nums.get(j)+"/"+names.get(j)+"/"+points.get(j));
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						if(pw!=null){
							pw.close();
						}
						if(fw!=null){
							fw.close();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				main.showFirstPnl();
			}
			
		}
	}
}
