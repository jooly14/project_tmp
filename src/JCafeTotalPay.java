import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class JCafeTotalPay extends JPanel implements ActionListener {
	//������ ������ �����Ǹ� �հ�ݾ��� ��Ŭ���� totalSave()�޼ҵ忡�� ���Ϸ� ����ȴ�
	//�� ������ ��� �� ������ Ȯ�� �� �� �ִ�.
	//���� ������ �ڵ����� ������ ������ ������ְ� comboBox��Ͽ��� �� ���� �����.
	BufferedReader br=null;
	FileReader fr=null;
	PrintWriter pw=null;
	FileWriter fw=null;
	
	JCafeCloseSale c;
	JTable totalTable;
	DefaultTableModel model;
	String contents[][]={};
	//JButton cancel;
	int idx,plust;//���������� ����
	JPanel titlep;
	JComboBox tcombo;
	String combo="2020��6��/2020��7��/2020��8��/2020��9��/2020��10��/",t="";//combo�ʱ� �ڷ�� �ڵ� ������ �ȵǹǷ� �־��ش�.
	int cnt=0,cnt2=4;
	JLabel l1;
	
	JCafeTotalPay(){
		//super(deadLineRegistration,true);
		c=new JCafeCloseSale();
		c.dispose();
		
		this.setSize(500,550);
		//this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		titlep=new JPanel();
		l1=new JLabel(" �ϴ����Ǹ���Ȳ");
		l1.setFont(new Font("HY�߰��",Font.BOLD,15));
		//�Ѵ� �Ǹż����� ���� ���̺��� �������ش�.
		JPanel panel=new JPanel();
		String[] header={"��¥","����"};
		model=new DefaultTableModel(contents,header);
		totalTable=new JTable(model);
		totalTable.setEnabled(false);
		//totalTable.setPreferredScrollableViewportSize(new Dimension(500,2-500));

		JScrollPane totalSp=new JScrollPane(totalTable);
		Dimension ds=new Dimension(530,450);//��ũ�� ũ�� ���ϱ�
		totalSp.setPreferredSize(ds);//��ũ�� ũ�� ����
		panel.add(totalSp);
		totalSp.getViewport().setBackground(Color.WHITE);//table ���� ���� 
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//�������
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = totalTable.getColumnModel();
		for(int i = 0 ; i < tcm.getColumnCount() ; i++){
      		tcm.getColumn(i).setCellRenderer(dtcr);  
		}
		totalTable.setRowHeight(30);//���̺� ��ĭ�� ����
		
		totalTable.getColumn("��¥").setPreferredWidth(60); //table ���� ���� ���� ����
		totalTable.getColumn("����").setPreferredWidth(200);
		
		//â�� ���� ��ư�� �������ش�.
		//cancel=new JButton("���ư���");
		//cancel.addActionListener(this);
		//JPanel panel2=new JPanel();
		//panel2.add(cancel);
		
		fileOpen();//�� ���� �� ������ �ڵ����� �����ش�.,comboBox�� �� �迭�� ���⼭ ȣ��
	
		tcombo.setSelectedIndex(cnt2);//�޺��ڽ� �ʱ⿡ ���õǴ� ����  �ش��ϴ� �޷� �����ش�. ������ ������ �ϳ� �þ ���� 1�� �ð� �����ص�
		tcombo.addActionListener(this);
		titlep.add(tcombo);
		titlep.add(l1);
		
		Color color=new Color(0x252525);
		titlep.setBackground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		//panel2.setBackground(Color.WHITE);
		l1.setForeground(color);
		this.setBackground(Color.white);
		
		this.setSize(600, 500);
		this.setLayout(null);
		titlep.setBounds(0,100,555,40);
		panel.setBounds(0,140,555,500);
		this.add(titlep,"North");
		this.add(panel,"Center");
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==tcombo){
			fileOpen2();//�޺��ڽ��� ������ �� ���� �ش������� ������.
		}	
	}
	void setTCombo(){
		String str=c.day3;
		if(cnt==0){//ù�� ������ ������ combo��Ͽ� �߰����ش�.
			t=str;
			combo+=t+"/";
			cnt++;
			cnt2++;
		}
		if(t!=str){//���� �޶����� combo��Ͽ� ���λ��� ���� �����ش�.
			t=str;
			combo+=t+"/";
			cnt2++;//�޺��ڽ� �ʱ⿡ ���õǴ� ���� �ش��ϴ� �޷� �ٲ��ֱ� ���� +1 ����
		}
		String data[]=combo.split("/");// /�� ��� �־�� �������� �߶� comboBox������� �־��ش�.
		tcombo=new JComboBox(data);
		//tcombo.addActionListener(this);	
	}
	void fileOpen(){//������ �ش��ϴ� �� ���� ����
		fileClose();
		idx=0;
		try {
			fr=new FileReader("JCafeData/SaleData/TotalMonthlySales/"+c.day3);//�����̸��� ���� ���� ���� 
			br=new BufferedReader(fr);
			String l=null;
			while((l=br.readLine())!=null){//������ �о ���پ� ���̺� �־���
				String data[]=l.split("/");	
				model.addRow(data);
				idx++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				if(fr!=null)fr.close();
				if(br!=null)br.close();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
		setTCombo();
	}
	void fileOpen2(){//�޺��ڽ� ������ ���� ���� ����
		fileClose();//�̹� ���̺� �ö���ִ� ������ �����
		idx=0;
		String getCombo=tcombo.getSelectedItem().toString();
		try {
			fr=new FileReader("JCafeData/SaleData/TotalMonthlySales/"+getCombo);//�����̸��� �޾ƿ� ���� ����
			br=new BufferedReader(fr);
			String l=null;
			while((l=br.readLine())!=null){//���پ� ���̺� �־��ֱ�
				String data[]=l.split("/");	
				model.addRow(data);
				idx++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				if(fr!=null)fr.close();
				if(br!=null)br.close();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	void fileClose(){//���� ���� ���� ����ϵ��� ������
		for(int i=0;i<idx;idx--){
			model.removeRow(idx-1);
		}
	}

	void totalSave(){//(JCafeManagerMenu�� �����ϷḦ ������ �����)�Ǹ� ������ ���ŵɶ� ���� ���� ����(���� �ٸ��� �ٸ� ���Ͽ� ����)
		try {
			fw=new FileWriter("JCafeData/SaleData/TotalMonthlySales/"+c.day3,true);
			pw=new PrintWriter(fw);
			String dataA=c.totalReturn();
			pw.println(dataA);
	
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fw!=null)
				try {
					fw.close();
					if(pw!=null)pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
