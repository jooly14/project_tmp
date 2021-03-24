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


class JCafeNewLatteMaterial extends JDialog implements ActionListener{
	JTable table;
	DefaultTableModel model;
	String[] header={"����", "����", "��뷮"};
	String contents[][] = {};
	JPanel pnlC, pnlS;
	JButton applyBtn, cancelBtn;
	File file = new File("JCafeData/InitData/InitMaterialData/", "latte_material");
	File fileTemp = new File("JCafeData/InitData/InitMaterialData/", "latte_temp");
	File fileM = new File("JCafeData/InitData/InitMaterialData/", "la_temp");
	int sel;
	String mName;
	
	JCafeNewLatteMaterial(int sel, String mName){
		this.setIconImage(new ImageIcon("JCafeData\\ImageData\\JCafe icon.png").getImage());
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
		repaint();
		revalidate();
		//���󺯰�
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
	
	void readName(){	//���Ͽ��� �о�ͼ� ���̺� Column1, Column2�� ä���ݴϴ�.
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
			bw = new BufferedWriter(new FileWriter(fileTemp, true));
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
					bw.newLine();
				}
			}
			
			bw.write(mName+"/");
			for(int i = 0; i < table.getRowCount(); i++){
				if(table.getValueAt(i, 2) != null){
					String ingre = table.getValueAt(i, 0)+"";	//���
					String gram = table.getValueAt(i, 1)+"";	//g or ml
					String cnt = table.getValueAt(i, 2)+"";		//������� ��
					bw.write(ingre + "/" + gram + "/" + cnt + "/");	//��) �Ƹ�/����/g/14
				}
			}
			bw.newLine();
			br.close();
			bw.close();
			file.delete();
			fileTemp.renameTo(file);
		}catch(Exception e1){
			e1.printStackTrace();
		}
		
	}
	
	void showingData(){//���Ͽ� �ִ� �ڷ� ���̺� ��������
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(file));
			String l = null;
			while((l = br.readLine()) != null){
				String aa[] = l.split("/");
				if(aa[0].equals(mName)) {	//�տ��� Ŭ���� �޴��̸��� coffee_material�� �ִٸ�
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == applyBtn){		//���Ͽ� �����
			if(sel == 1){
				file = new File("JCafeData/InitData/InitMaterialData/", "latte_material");
				fileSave();
				dispose();
			}else if(e.getSource() == cancelBtn){ 
				repaint();
				revalidate();
				dispose();
			}
		}
	}
}