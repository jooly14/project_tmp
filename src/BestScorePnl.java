import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
//베스트 스코어를 가져오는 패널
public class BestScorePnl extends JPanel{
	JLabel pointLbl;
	int bestScore;
	public BestScorePnl() {
		setBounds(450,665,330,50);
		setBackground(new Color(0x9292ab));
		
		pointLbl = new JLabel();
		pointLbl.setFont(new Font("Serif", Font.BOLD, 30));
		pointLbl.setForeground(new Color(0xf7d000));
		
		readBest();
		add(pointLbl);
	}
	//PointPnl에 베스트스코어를 줌
	int getBestScore(){
		return bestScore;
	}
	void setPointLbl(String pointStr){
		pointLbl.setText(pointStr);
	}
	//최고 점수를 읽어옴
	void readBest(){
		FileReader fr = null;
		BufferedReader br = null;
		String str = null;
		String[] split = null;
		try {
			fr = new FileReader("score.txt");
			br = new BufferedReader(fr);
			str=br.readLine();
			split = str.split("/");
			pointLbl.setText(split[2]);	
			bestScore = Integer.parseInt(split[2]);
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
}
