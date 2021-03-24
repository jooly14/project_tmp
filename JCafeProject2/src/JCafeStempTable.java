import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

//class JCafeStempTable extends JDialog implements ActionListener{
class JCafeStempTable extends JDialog implements ActionListener{
	JButton btnChangeNumber,btnRefresh;
	String[]strHeader={"��ȭ��ȣ","������ ����"};
	String[][]strTable;
	BufferedReader br;
	FileReader fr;
	DefaultTableModel model;
	JTable table;
	JPanel pnl;
	JScrollPane spTable;
	void init(boolean b){ //�� �ʱ�ȭ(Boolean���� ������ ���̺� ���� ����or�Ұ��� �ϰ�)
		this.setIconImage(new ImageIcon("JCafeData\\ImageData\\JCafe icon.png").getImage());
		setSize(400,500);
		model=new DefaultTableModel(strTable,strHeader);
		table=new JTable(model);
		spTable=new JScrollPane(table);
		showData();
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//�������
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for(int i = 0 ; i < tcm.getColumnCount() ; i++){
		      	tcm.getColumn(i).setCellRenderer(dtcr);  
		}
		
		btnRefresh=new JButton("���ΰ�ħ");
		btnChangeNumber=new JButton("��ȣ����");
		btnRefresh.setBackground(Color.WHITE);
		btnChangeNumber.setBackground(Color.WHITE);
		JLabel lblTitle=new JLabel("������ ����");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(160,5,100,30);
		spTable.setBounds(30,30,330,380);
		if(b){
			btnRefresh.setBounds(30, 420, 330, 30);
			table.setEnabled(false);
		}else{
			btnRefresh.setBounds(30, 420, 160, 30);
			btnChangeNumber.setBounds(200, 420, 160, 30);
		}
		this.setLayout(null);		
		this.add(lblTitle);
		this.add(spTable);
		this.add(btnRefresh);
		this.add(btnChangeNumber);		
		getContentPane().setBackground(new Color(0x252525));
		spTable.getViewport().setBackground(Color.WHITE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	JCafeStempTable(JCafeMain jcm) {//���ο��� ũ�����ӽ� ��ȣ���� �׼Ǹ����� x
		super(jcm,true);
		init(true);		
	}
	JCafeStempTable(JCafeManagerMenu jcmm){// ���⸸ ��ȣ���� �׼Ǹ����� �߰�
		super(jcmm,true);
		init(false);
	}
	void showData(){// ������������ �о�ͼ� ȭ�鿡 �ѷ��ִ� ����
		for(int i=0;i<model.getRowCount();)
			model.removeRow(i);
		try {
			fr = new FileReader(new File("JCafeData\\StempData"));//������������ ��������
			br = new BufferedReader(fr);//
			
			String l=null;
			while((l=br.readLine())!=null){
				String[] str=l.split("/");
				model.addRow(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fr !=null) fr.close();
				if(br != null) br.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	void numDataChange(){// ��ȣ���� Ŭ���� ������ �����ϴ� â ����, Ȯ�� ������ ��ȣ �����ϰ� ������ ������� ���ִ� ����
			PrintWriter pw=null;
			FileWriter fw = null;
			
			try {
				fw = new FileWriter(new File("JCafeData\\StempData"));// (true�����Ƿ� �ʱ�ȭ ��) StempData�� �ۼ��ϰڴ�. 
				pw = new PrintWriter(fw);
				
				JCafePhoneNum jcn=new JCafePhoneNum(this);	// PhoneNum ȭ�� ����
				String getNumber=jcn.getPhoneNum();		// getNumber�� �޴�����ȣ
				table.setValueAt(getNumber, table.getSelectedRow(), 0);//���̺� ���õ� ���� 0��° ���� getNumber�� �ִ´�.
				String data=null;
				for(int i=0;i<table.getRowCount();i++){
					data=table.getValueAt(i, 0)+"/"+table.getValueAt(i, 1);
					pw.println(data);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					if(fw!=null) fw.close();
					if(pw!=null) pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	@Override public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnRefresh)
			showData();//���ΰ�ħ
		if(e.getSource()==btnChangeNumber)
			numDataChange();//��ȣ����
	}
}