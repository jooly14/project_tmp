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
//������ ���ھ� �г��� �����ִ� �г�
//ScorePnl�� ScorePnlParentPnl�� �ڽ� �г�

//scorePnl���� �۵��ϴ� ������
//�̸��� �ۼ��� ��ġ�� �����ִ� ���̺��� �����Ÿ��� ������
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
			if(playerName.getText().length()!=3){	//playername�� 3���ڰ� �ƴ� ��쿡�� �����Ÿ�
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
//ó�� ȭ�� �����ǰ� ���� ���ھ ������ ������� ������
class ScorePnl_Thread extends Thread{
	ScorePnl scorePnl;
	int playerInsertIndex;	//player�� 5�� �ȿ� ��� 4���� �۰ų� ���� ���̰� �����ǿ� �� ��� 4���� Ŭ ���̴�.
	public ScorePnl_Thread(ScorePnl scorePnl, int playerInsertIndex) {
		this.scorePnl =scorePnl;
		this.playerInsertIndex = playerInsertIndex;
	}
	@Override
	public void run() {
		if(playerInsertIndex>4){	//�����ǿ� �� �� ���
				for (int i = 0; i <6;i++) {
					for (int j = 640; j >= 0; j-=10) {		//x���� 10�� �����ϸ鼭 �����ʿ��� �������� �����
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						scorePnl.showScore(i,j);
					}
				}
		}else{						//�����ǿ� �� ���
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
		scorePnl.chgAfterShow();	//���ھ ��� ��Ÿ���� ���� Ű�̺�Ʈ�� �۵��ϵ���
	}
}
//������ ���ھ� �г��� �����ִ� �г�
public class ScorePnl extends JPanel{
	int point;	//�÷��̾� ����
	ArrayList<Integer> numList = new ArrayList<>();	//���Ͽ��� �о�� �����͸� ��� ����Ʈ
	ArrayList<String> nameList = new ArrayList<>();
	ArrayList<Integer> pointList = new ArrayList<>();
	int playerInsertIndex;	//�÷��̾ ��� �߰��� �ƴ��� ��ġ�� �˷���	//�� ���� +1�� �ϸ� ���
	
	JLabel lbl;		//�μ����� ��ҵ��� ���̱� ���� ���̺�				
	JLabel lbl2;
	JLabel lbl3;
	
	//����,�̸�,����Ʈ ���̺�
	//������ �ʿ�� ������ �� �����ϴ�...
	//�׳� ���� ���̱⸸ �ϸ� �Ǵ� �ǵ�
	//���̺� �ϳ��� ��� ��Ȱ���ص� �ɰ� ������
	ArrayList<JLabel> lblNum = new ArrayList<>();
	ArrayList<JLabel> lblName= new ArrayList<>();
	ArrayList<JLabel> lblPoint= new ArrayList<>();

	JLabel playerName;		//�÷��̾� �̸��� �޴� ���̺�
	boolean afterShow;		//�����尡 ������ ���� �Է��� �ޱ����ؼ�
	TetrisMain main;		
	
	JLabel line;			//�����Ÿ��� ����		//�̸� �Է�ĭ
	EnterName_Thread enterName_Thread;		//�̸� �Է�ĭ �����Ÿ��� ������
	
	public ScorePnl(int point ,TetrisMain main) {
		this.main = main;
		this.point = point;
		setLayout(null);
		setBounds(0,0, 800, 868);
		setOpaque(false);
		
		checkScore();	//���ھ� ������ �о��
		
		
		
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
	//�����忡�� ��� //���ھ ������
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
	//���ھ �����ִ� �����尡 ������ ȣ���ϴ� �޼���
	void chgAfterShow(){
		afterShow = true;
		line.setLocation(playerName.getX(), playerName.getY()+70);
		add(line);
		enterName_Thread = new EnterName_Thread(this, playerName);
		enterName_Thread.start();
	}
	//�̸� �Է�ĭ�� �����ִ� ���̺��� ����
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
	//�÷��̾��� ���ھ ������
	void checkScore(){
		readFile();
		playerInsertIndex = numList.size();		
		for (int i = 0; i < numList.size(); i++) {
			if(point>=pointList.get(i)){
				playerInsertIndex = i;	//�÷��̾� ������ ���� ������ ���ؼ� ��� �ٿ� �÷��̾� ������ �߰����� �˾Ƴ�
				break;
			}
		}
		if(playerInsertIndex== numList.size()){	//�÷��̾ �õ��� ��� �������� �߰�
			pointList.add(point);
			numList.add(playerInsertIndex+1);
			nameList.add("");
		}else{									
			pointList.add(playerInsertIndex, point);
			numList.add(playerInsertIndex, numList.get(playerInsertIndex));
			nameList.add(playerInsertIndex, "");
		}
		//�÷��̾ �õ��� �ƴѰ�� �� �ؿ� �������� ������ �ϳ��� +1 ����
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
	//score.txt ������ �о��
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
	//������ �Է½�
	void addInitial(char c){
		if(afterShow){	//�����尡 ������
			if(playerName.getText().length()<3){	//3���� ���� ���� ���
				playerName.setText(playerName.getText()+c);
				line.setLocation(line.getX()+25, line.getY());
			}
			if(playerName.getText().length()==3){	//3������ ���
				remove(line);
				remove(lbl2);
				add(lbl3);
				repaint();
				revalidate();
			}
		}
	}
	//�齺���̽� �Է½�
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
	//���͸� ������ ����
	void saveScore(){
		if(afterShow){
			if(playerName.getText().length()==3){
				//����
				FileWriter fw = null;
				PrintWriter pw = null;
				
				try {
					fw = new FileWriter("score.txt");
					pw = new PrintWriter(fw);
					//100�� ���� ���
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
