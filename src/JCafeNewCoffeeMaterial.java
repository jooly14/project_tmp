import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.table.*;


class JCafeNewCoffeeMaterial extends JDialog implements ActionListener{
	JTable table;
	DefaultTableModel model;
	String[] header={"����", "����", "��뷮"};
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
		
		//������ ���̺� ����
		pnlC = new JPanel();
		model=new DefaultTableModel(contents,header)
		{	//���̺� column0, 1�� �����ȵǰ� ������ ���� ���������ϴ�.
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
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//�������
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for(int i = 0 ; i < tcm.getColumnCount() ; i++){
		      	tcm.getColumn(i).setCellRenderer(dtcr);  
		}
		//��ư
		JPanel pnlS = new JPanel();
		applyBtn = new JButton("����");
		cancelBtn = new JButton("���");
		applyBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		pnlS.add(applyBtn);
		pnlS.add(cancelBtn);
		
		//���󺯰�
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
	
	void readName(){	//���Ͽ��� �о�ͼ� ���̺� (Column1, Column2)�� ä���ݴϴ�.
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
	
	
	
	void showingData(){//���Ͽ� �ִ� �ڷ�(����) ���̺� ��������(Column3)
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
				if(aa[0].equals(mName)) {	//�տ��� Ŭ���� �޴��̸��� ���Ͽ� �ִٸ�
					for(int i = 0; i < table.getRowCount(); i++){
						for(int x = 0; x < aa.length; x++){
							if(aa[x].equals(table.getValueAt(i, 0))){	//�ش� ����̸� ��������(ex. ����, ����...)
								table.setValueAt(aa[x+1], i, 2);		//�ش� ����̸� ���� ���� ��������
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
	
	void fileSave(){	//���� ����
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		try{
			
			br = new BufferedReader(new FileReader(file));			
			String line = null;
			int i=0;
			while((line = br.readLine())!=null){
				String[]str=line.split("/");				
				if(!mName.equals(str[0])){		//�տ��� ������ �̸��� �ƴ� �� ���� (int)i++
					i++;
				}
			}
			br.close();
			br = new BufferedReader(new FileReader(file));
			String data[] = new String[i];		//�տ��� ������ �̸��� �ƴ� �� �� ��ŭ data�迭�� ����
			int j=0;
			while((line = br.readLine())!=null){
				String[]str=line.split("/");
				if(!mName.equals(str[0])){		//�տ��� ������ �޴��̸��� �ƴ϶��, �ƴ� ���븸 data[]�� ����
					data[j]=line;
					j++;
				}
			}
			
			String[] inputData = new String[data.length+1]; //�������� ������ �̸� ���� �����Ŷ� +1 == inputData[2]
			
			for(int u=0;u<inputData.length-1;u++){	
				inputData[u] = data[u];						//���� inputData[0]�� �տ� ����� data[]�� �ִ´�.
			}
			
			//������ ������ �޴��̸��� �ش��ϴ� ���� ���� ����(inputData[1]�� ��������)
			String s2=mName+"/";
			for(int k=0;k<table.getRowCount();k++){
				if(table.getValueAt(k, 2)!= null){			//���� Ŭ���� �� Column3�� �ƹ��͵� �������ְų� ���������� �Ѿ��,
					if(!table.getValueAt(k, 2).equals("")){	//Column3�� ���ڰ� �����ִ� �͵鸸 ����
						s2+=table.getValueAt(k, 0)+"/"+table.getValueAt(k, 2)+"/";
					}
				}				
			}
			s2=s2.substring(0, s2.lastIndexOf("/"));		//�� �� ���� "/"�� ����
			inputData[inputData.length-1]=s2;				//inputData[1]�� ������ ���� ������ �޴� ����
			
			
			bw = new BufferedWriter(new FileWriter(file));	//���������� ���Ͽ� ��� ����
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
		
		if(e.getSource() == applyBtn){		//���Ͽ� �����
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