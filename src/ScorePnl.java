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
//마지막 스코어 패널을 보여주는 패널
//ScorePnl은 ScorePnlParentPnl의 자식 패널

//scorePnl에서 작동하는 스레드
//이름을 작성할 위치를 보여주는 레이블이 깜빡거리는 스레드
class EnterName_Thread extends Thread{
	boolean isRunning = true;
	boolean show = true;
	ScorePnl scorePnl;
	JLabel playerName;
	public EnterName_Thread(ScorePnl scorePnl, JLabel playerName) {
		this.scorePnl = scorePnl;
		this.playerName = playerName;
	}
	@Override
	public void run() {
		while (isRunning) {
			if(playerName.getText().length()!=3){	//playername가 3글자가 아닌 경우에만 깜빡거림
				if(show){
					scorePnl.removeLineLbl();
					show = false;
				}else{
					scorePnl.addLineLbl();
					show = true;
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	void chgIsRunning(){
		isRunning = false;
	}
}
//처음 화면 생성되고 나서 스코어가 옆에서 날라오는 스레드
class ScorePnl_Thread extends Thread{
	ScorePnl scorePnl;
	int playerInsertIndex;	//player가 5등 안에 들면 4보다 작거나 같을 것이고 순위권에 못 들면 4보다 클 것이다.
	public ScorePnl_Thread(ScorePnl scorePnl, int playerInsertIndex) {
		this.scorePnl =scorePnl;
		this.playerInsertIndex = playerInsertIndex;
	}
	@Override
	public void run() {
		if(playerInsertIndex>4){	//순위권에 못 든 경우
				for (int i = 0; i <6;i++) {
					for (int j = 640; j >= 0; j-=10) {		//x값이 10씩 감소하면서 오른쪽에서 왼쪽으로 날라옴
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						scorePnl.showScore(i,j);
					}
				}
		}else{						//순위권에 든 경우
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
		scorePnl.chgAfterShow();	//스코어가 모두 나타나고 나서 키이벤트가 작동하도록
	}
}
//마지막 스코어 패널을 보여주는 패널
public class ScorePnl extends JPanel{
	int point;	//플레이어 점수
	ArrayList<Integer> numList = new ArrayList<>();	//파일에서 읽어온 데이터를 담는 리스트
	ArrayList<String> nameList = new ArrayList<>();
	ArrayList<Integer> pointList = new ArrayList<>();
	int playerInsertIndex;	//플레이어가 어디에 추가가 됐는지 위치를 알려줌	//이 값에 +1을 하면 등수
	
	JLabel lbl;		//부수적인 요소들을 붙이기 위한 레이블				
	JLabel lbl2;
	JLabel lbl3;
	
	//순위,이름,포인트 레이블
	//저장할 필요는 없었을 거 같긴하다...
	//그냥 만들어서 붙이기만 하면 되는 건데
	//레이블 하나로 계속 재활용해도 될거 같은데
	ArrayList<JLabel> lblNum = new ArrayList<>();
	ArrayList<JLabel> lblName= new ArrayList<>();
	ArrayList<JLabel> lblPoint= new ArrayList<>();

	JLabel playerName;		//플레이어 이름을 받는 레이블
	boolean afterShow;		//스레드가 끝나고 나서 입력을 받기위해서
	TetrisMain main;		
	
	JLabel line;			//깜빡거리는 라인		//이름 입력칸
	EnterName_Thread enterName_Thread;		//이름 입력칸 깜빡거리는 스레드
	
	public ScorePnl(int point ,TetrisMain main) {
		this.main = main;
		this.point = point;
		setLayout(null);
		setBounds(0,0, 800, 868);
		setOpaque(false);
		
		checkScore();	//스코어 파일을 읽어옴
		
		
		
		lbl3 = new JLabel("<<  PRESS ENTER TO SAVE  >>");
		lbl3.setForeground(Color.white);
		lbl3.setFont(new Font("Serif", Font.BOLD, 20));
		lbl3.setBounds(240,25+50, 500, 100);
		
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
		
		line = new JLabel();
		line.setOpaque(true);
		line.setBackground(Color.WHITE);
		line.setBounds(0,0,14,2);
		
		new ScorePnl_Thread(this,playerInsertIndex).start();
		
	}
	//스레드에서 사용 //스코어를 보여줌
	void showScore(int i, int j){
		
		if(playerInsertIndex>4){
			if(i==5){
				if(j==640){
					lblNum.add(new JLabel(numList.get(playerInsertIndex)+""));
					lblNum.get(lblNum.size()-1).setForeground(Color.white);
					lblNum.get(lblNum.size()-1).setFont(new Font("Serif", Font.BOLD, 30));
					lblNum.get(lblNum.size()-1).setBounds(150+j,80*6+40, 200, 100);
					add(lblNum.get(lblNum.size()-1));
					playerName = new JLabel(nameList.get(playerInsertIndex));
					playerName.setForeground(Color.blue);
					playerName.setFont(new Font("Serif", Font.BOLD, 30));
					playerName.setBounds(350+j,80*6+40, 200, 100);
					add(playerName);
					lblPoint.add(new JLabel(pointList.get(playerInsertIndex)+""));
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
					lblNum.add(new JLabel(numList.get(i)+""));
					lblNum.get(lblNum.size()-1).setForeground(Color.white);
					lblNum.get(lblNum.size()-1).setFont(new Font("Serif", Font.BOLD, 30));
					lblNum.get(lblNum.size()-1).setBounds(150+j,80*(i+1)+40, 200, 100);
					add(lblNum.get(lblNum.size()-1));
					lblName.add(new JLabel(nameList.get(i)));
					lblName.get(lblName.size()-1).setForeground(Color.white);
					lblName.get(lblName.size()-1).setFont(new Font("Serif", Font.BOLD, 30));
					lblName.get(lblName.size()-1).setBounds(350+j,80*(i+1)+40, 200, 100);
					add(lblName.get(lblName.size()-1));
					lblPoint.add(new JLabel(pointList.get(i)+""));
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
					lblNum.add(new JLabel(numList.get(playerInsertIndex)+""));
					lblNum.get(lblNum.size()-1).setForeground(Color.white);
					lblNum.get(lblNum.size()-1).setFont(new Font("Serif", Font.BOLD, 30));
					lblNum.get(lblNum.size()-1).setBounds(150+j,80*(i+1)+40, 200, 100);
					add(lblNum.get(lblNum.size()-1));
					playerName = new JLabel(nameList.get(playerInsertIndex));
					playerName.setForeground(Color.blue);
					playerName.setFont(new Font("Serif", Font.BOLD, 30));
					playerName.setBounds(350+j,80*(i+1)+40, 200, 100);
					add(playerName);
					lblPoint.add(new JLabel(pointList.get(playerInsertIndex)+""));
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
					lblNum.add(new JLabel(numList.get(i)+""));
					lblNum.get(lblNum.size()-1).setForeground(Color.white);
					lblNum.get(lblNum.size()-1).setFont(new Font("Serif", Font.BOLD, 30));
					lblNum.get(lblNum.size()-1).setBounds(150+j,80*(i+1)+40, 200, 100);
					add(lblNum.get(lblNum.size()-1));
					lblName.add(new JLabel(nameList.get(i)));
					lblName.get(lblName.size()-1).setForeground(Color.white);
					lblName.get(lblName.size()-1).setFont(new Font("Serif", Font.BOLD, 30));
					lblName.get(lblName.size()-1).setBounds(350+j,80*(i+1)+40, 200, 100);
					add(lblName.get(lblName.size()-1));
					lblPoint.add(new JLabel(pointList.get(i)+""));
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
		repaint();
		revalidate();
	}
	//스코어가 보여주는 스레드가 끝날때 호출하는 메서드
	void chgAfterShow(){
		afterShow = true;
		line.setLocation(playerName.getX(), playerName.getY()+70);
		add(line);
		enterName_Thread = new EnterName_Thread(this, playerName);
		enterName_Thread.start();
	}
	//이름 입력칸을 보여주는 레이블을 붙임
	void addLineLbl(){
		add(line);
		repaint();
		revalidate();
	}
	void removeLineLbl(){
		remove(line);
		repaint();
		revalidate();
	}
	//플레이어의 스코어를 가져옴
	void checkScore(){
		readFile();
		playerInsertIndex = numList.size();		
		for (int i = 0; i < numList.size(); i++) {
			if(point>=pointList.get(i)){
				playerInsertIndex = i;	//플레이어 점수와 기존 점수를 비교해서 어느 줄에 플레이어 점수를 추가할지 알아냄
				break;
			}
		}
		if(playerInsertIndex== numList.size()){	//플레이어가 꼴등인 경우 마지막에 추가
			pointList.add(point);
			numList.add(playerInsertIndex+1);
			nameList.add("");
		}else{									
			pointList.add(playerInsertIndex, point);
			numList.add(playerInsertIndex, numList.get(playerInsertIndex));
			nameList.add(playerInsertIndex, "");
		}
		//플레이어가 꼴등이 아닌경우 그 밑에 순위들의 순위를 하나씩 +1 해줌
		//
		if(playerInsertIndex!=numList.size()-1){
			for (int i = playerInsertIndex+1; i < numList.size(); i++) {
				if(point!=pointList.get(i)){
					numList.set(i, numList.get(i)+1);
				}
			}
		}
		
		
//		if(point==pointList.get(playerInsertIndex)){
//			for (int i = playerInsertIndex+1; i < numList.size(); i++) {
//				if(point!=pointList.get(i)){
//					numList.set(i, numList.get(i)+1);
//				}
//			}
//		}else{
//			for (int i = playerInsertIndex+1; i < numList.size(); i++) {
//					numList.set(i, numList.get(i)+1);
//			}
//		}
	}
	//score.txt 파일을 읽어옴
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
				numList.add(Integer.parseInt(split[0]));
				nameList.add(split[1]);
				pointList.add(Integer.parseInt(split[2]));
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
	//영문자 입력시
	void addInitial(char c){
		if(afterShow){	//스레드가 끝난후
			if(playerName.getText().length()<3){	//3글자 보다 적은 경우
				playerName.setText(playerName.getText()+c);
				line.setLocation(line.getX()+25, line.getY());
			}else if(playerName.getText().length()==3){	//3글자인 경우
				remove(line);
				remove(lbl2);
				add(lbl3);
				repaint();
				revalidate();
			}
		}
	}
	//백스페이스 입력시
	void removeInitial(){
		if(afterShow){
			if(playerName.getText().length()==3){
				add(line);
				remove(lbl3);
				add(lbl2);
				repaint();
				revalidate();
			}
			if(playerName.getText().length()!=0){
				playerName.setText(playerName.getText().substring(0, playerName.getText().length()-1));
				line.setLocation(line.getX()-25, line.getY());
			}
			
		}
	}
	//엔터를 누르면 저장
	void saveScore(){
		if(afterShow){
			if(playerName.getText().length()==3){
				//저장
				FileWriter fw = null;
				PrintWriter pw = null;
				
				try {
					fw = new FileWriter("score.txt");
					pw = new PrintWriter(fw);
					//100등 까지 기록
					for (int j = 0; j <(numList.size()>100?100:numList.size()); j++) {
						if(j==playerInsertIndex){
							pw.println(numList.get(j)+"/"+playerName.getText()+"/"+pointList.get(j));
						}else{
							pw.println(numList.get(j)+"/"+nameList.get(j)+"/"+pointList.get(j));
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
