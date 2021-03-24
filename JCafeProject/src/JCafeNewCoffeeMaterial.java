import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.table.*;


class JCafeNewCoffeeMaterial extends JDialog implements ActionListener{
	JTable table;
	DefaultTableModel model;
	String[] header={"재료명", "단위", "사용량"};
	String contents[][] = {};
	JPanel pnlC, pnlS;
	JButton applyBtn, cancelBtn;
	File file = new File("JCafeData/InitData/InitMaterialData/", "coffee_material");
	File fileM = new File("JCafeData/", "inventory");
	int sel;
	String mName;
	
	JCafeNewCoffeeMaterial(JCafeMenuForIngredients cmfi, int sel, String mName){
		super(cmfi, true);
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
		
		//색상변경
		Color color=new Color(0x252525);
		pnlC.setBackground(color);
		pnlS.setBackground(color);
		applyBtn.setBackground(Color.WHITE);
		cancelBtn.setBackground(Color.WHITE);
		this.setIconImage(new ImageIcon("JCafeData\\ImageData\\JCafe icon.png").getImage());
		this.add(pnlC);
		this.add(pnlS, "South");
		this.setLocationRelativeTo(null);
		readName();
		showingData();
		this.setVisible(true);
	}
	
	void readName(){	//파일에서 읽어와서 테이블 (Column1, Column2)를 채워줍니다.
		BufferedReader br = null;
				
		try {
			br = new BufferedReader(new FileReader(fileM));
			String l = null;
			while((l = br.readLine()) != null){
				String mt[] = l.split("/");		
				String mts[] = new String[2];
				mts[0] = mt[0];
				mts[1] = mt[2];
				model.addRow(mts);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	void showingData(){//파일에 있는 자료(숫자) 테이블에 나오게함(Column3)
		BufferedReader br = null;
		if(sel == 0){
			file = new File("JCafeData/InitData/InitMaterialData/", "coffee_material");
		}else if(sel == 1){
			file = new File("JCafeData/InitData/InitMaterialData/", "latte_material");
		}else if(sel == 2){
			file = new File("JCafeData/InitData/InitMaterialData/", "tea_material");
		}
		
		try {
			br = new BufferedReader(new FileReader(file));
			String l = null;
			while((l = br.readLine()) != null){
				String aa[] = l.split("/");
				if(aa[0].equals(mName)) {	//앞에서 클릭한 메뉴이름이 파일에 있다면
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
	
	void fileSave(){	//파일 저장
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		try{
			
			br = new BufferedReader(new FileReader(file));			
			String line = null;
			int i=0;
			while((line = br.readLine())!=null){
				String[]str=line.split("/");				
				if(!mName.equals(str[0])){		//앞에서 선택한 이름이 아닐 때 마다 (int)i++
					i++;
				}
			}
			br.close();
			br = new BufferedReader(new FileReader(file));
			String data[] = new String[i];		//앞에서 선택한 이름이 아닌 줄 수 만큼 data배열로 만듬
			int j=0;
			while((line = br.readLine())!=null){
				String[]str=line.split("/");
				if(!mName.equals(str[0])){		//앞에서 선택한 메뉴이름이 아니라면, 아닌 내용만 data[]에 저장
					data[j]=line;
					j++;
				}
			}
			
			String[] inputData = new String[data.length+1]; //마지막에 선택한 이름 한줄 넣을거라서 +1 == inputData[2]
			
			for(int u=0;u<inputData.length-1;u++){	
				inputData[u] = data[u];						//먼저 inputData[0]에 앞에 저장된 data[]를 넣는다.
			}
			
			//마지막 선택한 메뉴이름에 해당하는 재료와 숫자 저장(inputData[1]에 넣을거임)
			String s2=mName+"/";
			for(int k=0;k<table.getRowCount();k++){
				if(table.getValueAt(k, 2)!= null){			//셀이 클릭이 됬어도 Column3에 아무것도 안적혀있거나 안적었을시 넘어가고,
					if(!table.getValueAt(k, 2).equals("")){	//Column3에 숫자가 적혀있는 것들만 저장
						s2+=table.getValueAt(k, 0)+"/"+table.getValueAt(k, 2)+"/";
					}
				}				
			}
			s2=s2.substring(0, s2.lastIndexOf("/"));		//한 줄 끝에 "/"는 삭제
			inputData[inputData.length-1]=s2;				//inputData[1]에 마지막 내가 선택한 메뉴 저장
			
			
			bw = new BufferedWriter(new FileWriter(file));	//마지막으로 파일에 모두 저장
			for(int d = 0; d < inputData.length; d++) {
				bw.write(inputData[d]);
				bw.newLine();
			}
			bw.close();
			
		}catch(Exception e1){
			e1.printStackTrace();
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == applyBtn){		//파일에 저장됨
			if(sel == 0){		
				file = new File("JCafeData/InitData/InitMaterialData/", "coffee_material");
				fileSave();
				dispose();
				repaint();
				revalidate();
			}
			else if(sel == 1){
				file = new File("JCafeData/InitData/InitMaterialData/", "latte_material");
				fileSave();
				dispose();
				repaint();
				revalidate();
			}else if(sel == 2){
				file = new File("JCafeData/InitData/InitMaterialData/", "tea_material");
				fileSave();
				dispose();
				repaint();
				revalidate();
			}
		}else if(e.getSource() == cancelBtn){ 
			repaint();
			revalidate();
			dispose();
			this.setVisible(false);
		}
		
	}
}