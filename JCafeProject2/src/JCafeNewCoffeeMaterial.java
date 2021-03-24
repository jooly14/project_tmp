import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


class JCafeNewCoffeeMaterial extends JDialog implements ActionListener{
	JTable table;
	DefaultTableModel model;
	String[] header={"재료명", "단위", "사용량"};
	String contents[][] = {};
	JPanel pnlC, pnlS;
	JButton applyBtn, cancelBtn;
	File file = new File("JCafeData/InitData/InitMaterialData/", "coffee_material");
	File fileTemp = new File("JCafeData/InitData/InitMaterialData/", "coffe_temp");
	File fileM = new File("JCafeData/InitData/InitMaterialData/", "co_temp");
	int sel;
	String mName;
	
	
	JCafeNewCoffeeMaterial(int sel, String mName){
		this.setIconImage(new ImageIcon("JCafeData\\ImageData\\JCafe icon.png").getImage());
		this.sel = sel;
		this.mName = mName;
	
		this.setSize(500,550);
		//재료관리 테이블 구성
		pnlC = new JPanel();
		model=new DefaultTableModel(contents,header)
		{	//테이블 column0, 1은 수정안되고 마지막 열만 수정가능하다.
			public boolean isCellEditable(int row, int column){
				if(column == 0){
					return false;
				}else if(column == 1){
					return false;
				}else{
					return true;
				}
			}
		};
		table = new JTable(model);
		JScrollPane sp = new JScrollPane(table);
		pnlC.add(sp);
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//가운데정렬
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for(int i = 0 ; i < tcm.getColumnCount() ; i++){
		      	tcm.getColumn(i).setCellRenderer(dtcr);  
		}
		//버튼
		JPanel pnlS = new JPanel();
		applyBtn = new JButton("적용");
		cancelBtn = new JButton("취소");
		applyBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		pnlS.add(applyBtn);
		pnlS.add(cancelBtn);
		repaint();
		revalidate();
		//색상변경
		Color color=new Color(0x252525);
		pnlC.setBackground(color);
		pnlS.setBackground(color);
		applyBtn.setBackground(Color.WHITE);
		cancelBtn.setBackground(Color.WHITE);
		
		this.add(pnlC);
		this.add(pnlS, "South");
		this.setLocationRelativeTo(null);
		readName();
		showingData();
		this.setVisible(true);
	}
	
	void readName(){	//파일에서 읽어와서 테이블 Column1, Column2를 채워줍니다.
		BufferedReader br = null;
				
		try {
			br = new BufferedReader(new FileReader(fileM));
			String l = null;
			while((l = br.readLine()) != null){
				String mt[] = l.split("/");				
				model.addRow(mt);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void fileSave(){
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		try{
			
			br = new BufferedReader(new FileReader(file));
			bw = new BufferedWriter(new FileWriter(fileTemp, true));//가상파일
			String line = null;
			
			String save="";
			String[]str=null;
			
			while((line = br.readLine())!=null){
				save =save+line+"!";
			}
			str=save.split("!");
			
			for(int i = 0; i < str.length; i++) {
				String strs[] = str[i].split("/");
				if(strs[0].equals(mName)) {
				
				}else {
					bw.write(str[i]);
					//coffe_material에서 같은 메뉴가있으면 그 메뉴줄 빼고 '가상파일'에 '나머지'를 저장한다.
					bw.newLine();
				}
			}
			//'나머지' 저장한 거에 방금 선택한 메뉴와 재료들을 맨밑에 저장한다.
			bw.write(mName+"/");
			for(int i = 0; i < table.getRowCount(); i++){
				if(table.getValueAt(i, 2) != null){
					String ingre = table.getValueAt(i, 0)+"";	//재료
					String cnt = table.getValueAt(i, 2)+"";		//적어넣은 숫
					bw.write(ingre + "/" + cnt + "/");	//예) 아메/우유/14
				}
			}
			bw.newLine();
			br.close();
			bw.close();
			file.delete(); //원래있던파일 삭제
			fileTemp.renameTo(file);//가상파일의 이름을 원래있던 파일 이름으로 이름변경
		}catch(Exception e1){
			e1.printStackTrace();
		}
		
	}
	
	void showingData(){//파일에 있는 자료 테이블에 나오게함
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(file));
			String l = null;
			while((l = br.readLine()) != null){
				String aa[] = l.split("/");
				if(aa[0].equals(mName)) {	//앞에서 클릭한 메뉴이름이 coffee_material에 있다면
					for(int i = 0; i < table.getRowCount(); i++){
						for(int x = 0; x < aa.length; x++){
							if(aa[x].equals(table.getValueAt(i, 0))){	//해당 재료이름 가져오기(ex. 원두, 우유...)
								table.setValueAt(aa[x+1], i, 2);		//해당 재료이름 옆에 숫자 가져오기
							}
						}
					}
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == applyBtn){		//파일에 저장됨
			if(sel == 0){		
				fileSave();
				dispose();
			}
		}else if(e.getSource() == cancelBtn){ 
			repaint();
			revalidate();
			dispose();
		}
	}
}