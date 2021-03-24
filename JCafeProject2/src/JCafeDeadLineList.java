import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class JCafeDeadLineList extends JPanel implements ActionListener, PropertyChangeListener{
	//�׽�Ʈ�� �ڵ�
	/*public static void main(String[] args) {
		new JCafeDeadLineList();
	}*/
	
	JTable table;
	DefaultTableModel model;
	JPanel pnlBtn;
	JButton  btnSet, btnReset;
	String[] header = { "����", "�� ���", "����", "�������", "ǥ�����", "loss/over", "���" };

	int firstChk;	//propertychangelistner�̺�Ʈ���� ����Ϸ��� ����
	String[][] content; // �𵨿� add��ų �迭 //�ʿ��� ��� �����͸� ������ ���� ����
	JCafeGetStockData gst = new JCafeGetStockData();	//���̺� �߰��� ������ �������� ���ؼ� Ŭ���� ����
	DeadLineRegistration deadLineRegistration;
	public JCafeDeadLineList(DeadLineRegistration deadLineRegistration) {
		//this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.deadLineRegistration = deadLineRegistration;
		table = new JTable();
		// ������ ���� �����ϰ� �������
		table.setModel(new DefaultTableModel(new Object[][] {},header){	
			boolean[] columnEditables = new boolean[] { false, true, false, false, false, false, true };
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		// ���� ������ ���� �ʰ� ����Ŭ������ ���� ���̺��� ���� ���� �޾ƿ�
		model = (DefaultTableModel) table.getModel();

		//JCafeGetStockData�� ���ؼ� ������ ��뷮 �����͸� ���̺� �߰�
		content = new String[gst.inventoryArray[0].length][7];
		for (int i = 0; i < gst.inventoryArray[0].length; i++) {
			content[i][0] = gst.inventoryArray[0][i];	//����
			content[i][1] = "";	//������� �Է��� ĭ
			content[i][2] = gst.inventoryArray[2][i];	//����
			content[i][3] = gst.inventoryArray[1][i];	//�������
			content[i][4] = (Integer.parseInt(gst.inventoryArray[1][i]) - gst.useTotal[i]) + "";	//ǥ�����//����������� �Ϸ� ��뷮�� ��
			content[i][5] = "0";	//overloss�� ó������ 0�ε� ������� �Է��ϸ� �ڵ����� ���
			content[i][6] = "";		//���
			model.addRow(content[i]);
		}
		//�� ��� �Է� �Ҷ����� �����ν��� �����ǰԲ� �̺�Ʈ�� �޾Ҵ�.
		table.addPropertyChangeListener(this);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
					return;
				}
			}
			
			
		});
		
		//���̺� ������ ��� ����
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		for(int i=0;i<header.length;i++){
			table.getColumn(header[i]).setCellRenderer(celAlignCenter);
		}
		
		//���̺��� ������ �߸��� �κ��� ����
		table.getColumn(header[0]).setPreferredWidth(230);
		table.getColumn(header[1]).setPreferredWidth(130);
		table.getColumn(header[3]).setPreferredWidth(100);
		table.getColumn(header[4]).setPreferredWidth(100);
		table.getColumn(header[5]).setPreferredWidth(100);

		JScrollPane sp = new JScrollPane(table);
		
		//ȭ�� ������ �ٽ� DeadLineRegistration�� ���� ���ؼ� 
				//���� ������ ������ ��ư���� �ٲٷ��ٺ���..
				/*this.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						new DeadLineRegistration();
					}
				});*/
		
		

		btnSet = new JButton("�Ϸ�");
		btnReset = new JButton("�ʱ�ȭ");
		btnSet.addActionListener(this);
		btnReset.addActionListener(this);

		pnlBtn = new JPanel();
		pnlBtn.add(btnSet);
		pnlBtn.add(new JLabel("       "));
		pnlBtn.add(btnReset);
		
		
		
		
		
		//ui����
		btnReset.setBackground(new Color(0x252525));
		btnReset.setPreferredSize(new Dimension(120,30));
		btnReset.setForeground(Color.white);
		btnSet.setBackground(new Color(0x252525));
		btnSet.setPreferredSize(new Dimension(120,30));
		btnSet.setForeground(Color.white);
		
		
		//this.getContentPane().setBackground(new Color(0x003E00));
		pnlBtn.setBackground(null);
		//sp.setBorder(BorderFactory.createEmptyBorder());
		table.setRowSorter(new TableRowSorter(model));	//���� ���
		table.setShowHorizontalLines(false);
		table.setRowHeight(30);
		//table.getTableHeader().setBackground(new Color(0x003E00));
		
		this.setSize(600, 700);
		sp.setBounds(0,100,555,500);
		pnlBtn.setBounds(0,610,555,300);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		this.add(sp);
		this.add(pnlBtn);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnSet) {// �Ϸ�
			int okCancelChk =JOptionPane.showConfirmDialog(this, "<html>���� ��� ����� �Ϸ��Ͻðڽ��ϱ�? <br>������� �Ϸ�� ���Ŀ��� �����Ͻ� �� �����ϴ�.</html>", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
			if(okCancelChk==JOptionPane.OK_OPTION){
				saveFile();	//�Ϸ� ������ ���� ��Ͽ� ����� ������ ����
				saveChangeInventoryFile();	//���� �κ��丮�� ������� �ݿ���Ų��.
				deadLineRegistration.remove(this);
				deadLineRegistration.setSize(600,210);
				deadLineRegistration.btnColorChangeFileExist();	//��� ����� �Ϸ�Ǹ� ��ư ���� �����
				//new DeadLineRegistration();	
				//dispose();
			}
			
		} else if (arg0.getSource() == btnReset) {// �ۼ��� �� ����� �ʱ�ȭ��Ŵ
			for(int i=0;i<table.getRowCount();i++){
				table.setValueAt("", i, 1);	//����� ��ĭ���� �����
				table.setValueAt("0", i, 5);	//overloss �ٽ� 0���� �������
			}
		}
	}

	
	//���� ����Ҷ� ����ϱ� ���ؼ� ���Ϸ� ����
	void saveFile() {
		//����� ������ ���� :����/���� �κ�/����/�������/ǥ�ػ�뷮/ǥ�� ���/overloss/���
		//���� ���ϸ�JCafeData/SaleData/DealineData/20201121
		//  20201119_jcafe_deadlinelist.txt
		Date date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		String dt = transFormat.format(date);
		
		FileWriter fw = null;
		PrintWriter pw = null;
		//�ش� ������ ������ ������ ����
		File dir = new File("JCafeData/SaleData/DeadlineData/");
		if(!dir.isDirectory()){
			dir.mkdirs();
		}
		try {
			fw = new FileWriter("JCafeData/SaleData/DeadlineData/"+dt);
			pw = new PrintWriter(fw);
			for(int i=0;i<table.getRowCount();i++){
				content[i][1] = (String)table.getValueAt(i, 1);
				content[i][5] = (String)table.getValueAt(i, 5);
				String saveD = content[i][0]+"/"+ 
				(content[i][1].equals("")?content[i][4]:content[i][1])+"/"+
				gst.inventoryArray[2][i]+"/"+ content[i][3]+"/"+
				gst.useTotal[i]+"/"+ content[i][4]+"/"+ content[i][5]+"/"+ content[i][6];
				pw.println(saveD);				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pw != null) {pw.close();}
				if (fw != null) {fw.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//�κ��丮�� ���� ����� �ݿ���Ų��.
	void saveChangeInventoryFile(){
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter("JCafeData/inventory");
			pw = new PrintWriter(fw);
			for(int i=0;i<table.getRowCount();i++){
				String saveD = gst.inventoryArray[0][i]+"/"+(content[i][1].equals("")?content[i][4]:content[i][1])+"/"+
								gst.inventoryArray[2][i];
				pw.println(saveD);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pw != null) {pw.close();}
				if (fw != null) {fw.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	//�� ����� �� �Է� �ÿ� over/loss ĭ�� ��� �� �ڵ��Է� �ǰ� �ϴ� �̺�Ʈ�޼���
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (firstChk != 0&&table.getSelectedRow()!=-1) {
			String currentInventory = (String) (table.getValueAt(table.getSelectedRow(), 1));
			int intCurrentInventory = 0;	//�����
			int standardInventory = 0;		//ǥ�����
				try {
					if (!(currentInventory.equals(""))) {
					intCurrentInventory = Integer.parseInt(currentInventory);
					standardInventory = Integer.parseInt((String) (table.getValueAt(table.getSelectedRow(), 4)));
					// ����� - ǥ����� = over/loss
					table.setValueAt((intCurrentInventory - standardInventory)+"", table.getSelectedRow(), 5);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(this, "���ڸ� �Է��ϼ���");
					table.setValueAt("", table.getSelectedRow(), 1);
					table.setValueAt("0", table.getSelectedRow(), 5);
				} finally {
				}
		}
		//propertychangelistner�� �ٿ������ϱ�
		//ó�� �����ɶ��� �ش� �̺�Ʈ�� ����Ǵ� ������ ����
		//�׷��� Ȯ���� ���� ������ �־���
		firstChk = 1;
	}
}
