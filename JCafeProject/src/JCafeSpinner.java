import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

//���� ��� ��ư�� ������ ������ �� ȭ��
//������ �����ʹ� �� �־�� ��
//"JCafeData/SaleData/deadlineData/" ���� �ȿ� ������
public class JCafeSpinner extends JPanel implements ActionListener ,PropertyChangeListener{
	/*
	 * public static void main(String[] args) { new JCafeSpinner(); }
	 */
	JLabel laY, laM, laD;
	JButton btnOrder;
	JButton btnInsert;
	JButton btnDelete;
	JTable table;
	String str[] = { "����", "�� �����", "ǥ�ػ�뷮", "ǥ�ع���", "��������", "����", "Over/Loss" };
	// deaddline/��¥ ���Ͽ� ����� ���� : // ����/���� �κ�/����/���������/ǥ�ػ�뷮/ǥ�� �����/overloss/���
	// ǥ�� ��뷮�� ������ ������ ����ߴ� ��ᷮ�� ǥ��
	// ǥ�� ���� = �� ����� - ǥ�ػ�뷮
	// ���� ���ִ� ���� ���� �� �ְ� ����
	String strTable[][] = {};
	DefaultTableModel tmodel;

	String[][] useData = new String[6][];

	// ���ϸ��� ��¥�� ���־ ��¥��꿡 ���� ������
	Calendar cal;
	String dt;

	int[] usageSum;// ǥ�ػ�뷮
	int[] overlossSum;// ǥ�ؿ����ν�

	// readUsage()���� ����ϴ� ����
	String overlossStr = "";
	String usageStr = "";

	JTextField tfName; // �߰��� ����
	JTextField tfQuantity; // �߰��� ����� ���ַ�
	JComboBox combo;// �߰��� ����� ����
	// JTextField tfUnit;

	DeadLineRegistration deadLineRegistration;
	
	int firstChk=0;//propertychangelistener���� ó�� �����Ҷ��� �̺�Ʈ�� �۵��ϴ� ���� �������� �������

	public JCafeSpinner(DeadLineRegistration deadLineRegistration) {
		this.deadLineRegistration = deadLineRegistration;

		// this.setLocationRelativeTo(null);
		// this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);

		// ������ �� ������ �� �о������ ��¥�� ������
		cal = Calendar.getInstance();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		dt = transFormat.format(cal.getTime());

		// �ٸ� ������ ������ �� ���� ����� ���� �κ�
		// ���� ���ָ� ����
		table = new JTable();

		table.setModel(new DefaultTableModel(strTable, str) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, true, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		// ���� ���� �� ���� �޾ƿ���
		tmodel = (DefaultTableModel) table.getModel();
		table.addPropertyChangeListener(this);
		
		
		// ���̺��� ���� �ֱ�
		readFile(); // �����Ҷ� ������ ���Ͽ��� �����͸� �о��
		getUsageSum(); // �� �� ������ ���Ǵ� ����� �� ���� ���
		setTableData(); // ���̺��� ������ �����͸� ǥ��


		// ���̺� ������ ��� ����
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < str.length; i++) {
			table.getColumn(str[i]).setCellRenderer(celAlignCenter);
		}
		table.setRowSorter(new TableRowSorter(tmodel));	//���� ���
		// ���̺����� ������ �߸��� �κ��� ����
		table.getColumn(str[0]).setPreferredWidth(150);
		table.getColumn(str[2]).setPreferredWidth(80);
		table.getColumn(str[3]).setPreferredWidth(50);
		table.getColumn(str[5]).setPreferredWidth(30);
		
		/*
		 * table.getColumn(str[1]).setPreferredWidth(100);
		 * table.getColumn(str[3]).setPreferredWidth(100);
		 * table.getColumn(str[4]).setPreferredWidth(100);
		 */

		JScrollPane sp = new JScrollPane(table);

		JPanel btnPnl = new JPanel();
		btnOrder = new JButton("���� ���");
		btnOrder.addActionListener(this);
		btnInsert = new JButton("��� �߰�");
		btnInsert.addActionListener(this);
		btnDelete = new JButton("��� ����");
		btnDelete.addActionListener(this);
		btnPnl.add(btnInsert);
		btnPnl.add(btnDelete);
		btnPnl.add(new JLabel("              "));
		btnPnl.add(btnOrder);

		// �ʿ��� ��� �߰��� �� �ְ� �Է¹޴� �κ�
		JPanel inPnl1 = new JPanel();
		JPanel inPnl2 = new JPanel();
		JPanel inPnl3 = new JPanel();
		tfName = new JTextField(14);
		tfQuantity = new JTextField(10);
		String[] comboD = { "Kg", "L", "Box" };
		combo = new JComboBox(comboD);
		JLabel lbl1 = new JLabel("����:");
		inPnl1.add(lbl1);
		inPnl1.add(tfName);
		JLabel lbl2 = new JLabel("���ּ��� :    ");
		inPnl2.add(lbl2);
		inPnl2.add(tfQuantity);
		JLabel lbl3 = new JLabel("���� :              ");
		inPnl3.add(lbl3);
		inPnl3.add(combo);
		inPnl1.setBackground(null);
		inPnl2.setBackground(null);
		inPnl3.setBackground(null);
		combo.setPreferredSize(new Dimension(120, 20));
		tfQuantity.addKeyListener(new KeyAdapter() {
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
		
		
		// ui

		btnOrder.setBackground(new Color(0x252525));
		btnOrder.setPreferredSize(new Dimension(120, 30));
		btnOrder.setForeground(Color.white);
		btnInsert.setBackground(new Color(0x252525));
		btnInsert.setPreferredSize(new Dimension(120, 30));
		btnInsert.setForeground(Color.white);
		btnDelete.setBackground(new Color(0x252525));
		btnDelete.setPreferredSize(new Dimension(120, 30));
		btnDelete.setForeground(Color.white);


		table.setShowHorizontalLines(false);
		table.setRowHeight(30);

		JPanel insertPnl = new JPanel(new GridLayout(0, 2));
		insertPnl.add(inPnl1);
		insertPnl.add(inPnl2);
		insertPnl.add(inPnl3);

		// ������ DeadLineRegistration�� �ٽ� �������� ����
		/*
		 * this.addWindowListener(new WindowAdapter() {
		 * 
		 * @Override public void windowClosing(WindowEvent e) { // TODO
		 * Auto-generated method stub new DeadLineRegistration(); } });
		 */

		this.setSize(600, 600);
		sp.setBounds(0, 10, 555, 435);
		insertPnl.setBounds(0, 450, 555, 70);
		btnPnl.setBounds(0, 520, 555, 50);
		btnPnl.setBackground(Color.white);
		insertPnl.setBackground(Color.white);
		this.setBackground(Color.white);
		this.add(sp);
		this.add(insertPnl);
		this.add(btnPnl);
		this.setVisible(true);
	}

	// ������ DeadlineData/���ó�¥ ������ �о�´�.
	void readFile() {
		FileReader fr = null;
		BufferedReader br = null;
		String data[] = { "", "", "", "", "", "" };
		// ����/���� �κ�/����/���������/ǥ�ػ�뷮/ǥ�� �����
		try {
			fr = new FileReader("JCafeData/SaleData/DeadlineData/" + dt);
			br = new BufferedReader(fr);
			String readData = "";
			while ((readData = br.readLine()) != null) {
				String[] deadlineData = readData.split("" + "/");
				for (int i = 0; i < data.length; i++) {
					// �� ���� �����͸� �� ������ ���� �����Ѵ�.
					// data[0]���� ����1/����2/����3/...
					// data[1]���� ���������1/���������2/���������3/...
					data[i] = data[i] + deadlineData[i] + "/";
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// data�� �����͸� useData[][] ���߹迭�� ����
		for (int i = 0; i < data.length; i++) {
			useData[i] = data[i].split("/");
		}

		// Ȯ�ο� �ڵ�
		/*
		 * for(int i=0;i<useData.length;i++){ for(int
		 * j=0;j<useData[i].length;j++){ System.out.print(useData[i][j]); }
		 * System.out.println(); }
		 */

	}

	// ǥ�� ��뷮�̶� ǥ�� �����ν� ���ϴ� �޼���
	// ���� ������ ���� ������ �����ͼ� ǥ�� ��뷮�� ǥ�ؿ����ν��� ���
	void getUsageSum() {
		String[][] dayUsage = new String[7][];
		String[][] dayOverloss = new String[7][];
		String dayNotExist = "";
		int dayExist = 7;
		for (int i = 0; i < 7; i++) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
			String dt2 = transFormat.format(cal.getTime());
			// ������ ������ ������ �д´�.
			readUsage(dt2);
			dayUsage[i] = usageStr.split("/");
			dayOverloss[i] = overlossStr.split("/");
			if(usageStr.equals("")){
				dayNotExist = dayNotExist + dt2 +" ";
				dayExist--;
			}
			
			usageStr = "";
		}
			// ������ ���� ����ϱ� ���ؼ� int�� ��ȯ			//���� ����	//7��
			int[][] usageIntArray = new int[useData[0].length][dayUsage.length];
			int[][] overlossIntArray = new int[useData[0].length][dayOverloss.length];
			for (int i = 0; i < useData[0].length; i++) {
				for (int j = 0; j < dayUsage.length; j++) {
					try {
						usageIntArray[i][j] = Integer.parseInt(dayUsage[j][i]);
						overlossIntArray[i][j] = Integer.parseInt(dayOverloss[j][i]);
					} catch (Exception e) {
						usageIntArray[i][j] = 0;
						overlossIntArray[i][j] = 0;
					}
				}
			}
			// ��� �� ǥ�ػ�뷮 ǥ�� �����ν��� �����
			usageSum = new int[usageIntArray.length];
			overlossSum = new int[overlossIntArray.length];
			int sumU = 0;
			int sumOv = 0;
			for (int j = 0; 
					j < usageIntArray.length; j++) {
				for (int i = 0; i < usageIntArray[0].length; i++) {
					sumU = sumU + usageIntArray[j][i];
					sumOv = sumOv + overlossIntArray[j][i];
				}
				usageSum[j] = sumU;
				overlossSum[j] = sumOv;
				sumU = 0;
				sumOv = 0;
				
			}
			if(dayExist!=7){
				JOptionPane.showMessageDialog(this,"<html>"+ dayNotExist+" ������ ������ �����ϴ�.<br>"
						+ "ǥ�ػ�뷮�� "+dayExist+"��ġ ��뷮�� �ջ��� ���Դϴ�.</html>");
			}else{
				JOptionPane.showMessageDialog(this,"ǥ�ػ�뷮�� "+dayExist+"��ġ ��뷮�� �ջ��� ���Դϴ�.");
			}

	}

	// ������ ��뷮�̶� �����ν��� ���Ͽ��� �ҷ����� �޼���
	void readUsage(String dt2) {
		File file = new File("JCafeData/SaleData/DeadlineData/" + dt2);
		if (file.exists()) {
			FileReader fr = null;
			BufferedReader br = null;

			try {
				fr = new FileReader("JCafeData/SaleData/DeadlineData/" + dt2);
				br = new BufferedReader(fr);
				String readData = "";
				while ((readData = br.readLine()) != null) {
					String[] deadlineData = readData.split("" + "/");
					usageStr = usageStr + deadlineData[4] + "/";
					overlossStr = overlossStr + deadlineData[6] + "/";
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null) {
						br.close();
					}
					if (fr != null) {
						fr.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ���̺��� ������ ��� �޼���
	void setTableData() {
		/*
		 * for (int i = 0; i < useData.length; i++) { for (int j = 0; j <
		 * useData[i].length; j++) { System.out.print(i + "/" + j);
		 * System.out.println("/" + useData[i][j] + "/"); }
		 * System.out.println(); }
		 */
		// ���̺� ��� ���� : "����", "�� �����", "ǥ�ػ�뷮", "ǥ�ع���", "��������", "����",
		// "Over/Loss"
		
		String[] content = new String[7];
		for (int j = 0; j < useData[0].length; j++) {
			// System.out.println("/" + useData[i][j] + "/");
			content[0] = useData[0][j]; // ����
			content[1] = useData[1][j] + "   " + useData[2][j]; // ������ �Բ� ǥ��
			content[2] = usageSum[j] 
					+ "   " + useData[2][j];
			// ���� ������ ǥ���Ǵ� ���� ū������ ��ȯ�ؼ� ǥ�ع��ָ� ǥ��
			if ((usageSum[j] - Integer.parseInt(useData[1][j])) < 0) {
				if (useData[2][j].equals("ml")) {
					content[3] = "0   L";
				} else if (useData[2][j].equals("g")) {
					content[3] = "0   Kg";
				} else if (useData[2][j].equals("ea")) {
					content[3] = "0   Box";
				} else {
					content[3] = "0";
				}
			} else {
				int content3 = (usageSum[j] - Integer.parseInt(useData[1][j]));
				if (useData[2][j].equals("ml")) {
					content[3] = content3 / 1000 +1 + "   L";
				} else if (useData[2][j].equals("g")) {
					content[3] = content3 / 1000 +1 + "   Kg";
				} else if (useData[2][j].equals("ea")) {
					content[3] = content3 / 24 +1 + "   Box";
				} else {
					content[3] = content3 + "";
				}
			}
			content[4] = ""; // �������ִ� ���� �Է�
			// ������ ǥ��
			if (useData[2][j].equals("ml")) {
				content[5] = "L";
			} else if (useData[2][j].equals("g")) {
				content[5] = "Kg";
			} else if (useData[2][j].equals("ea")) {
				content[5] = "Box";
			} else {
				content[5] = "";
			}
			content[6] = overlossSum[j] + "   " + useData[2][j]; // overloss
			tmodel.addRow(content);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOrder) {
			int jopChk = JOptionPane.showConfirmDialog(this, "<html>���� ����Ͻðڽ��ϱ�?<br>���� ����� �Ϸ��Ͻ� �Ŀ��� �����Ͻ� �� �����ϴ�.</html>", "", JOptionPane.OK_CANCEL_OPTION);
			if (jopChk == JOptionPane.OK_OPTION) {
				saveOrder(); // ���� ��� ������ ����
				removeInventoryMaterial(); // ������ ������ �κ��丮�� �ݿ� //�߰��� ������ ������
											// �԰��� �� �ݿ�
				deadLineRegistration.remove(this);
				deadLineRegistration.setSize(600, 210);
				deadLineRegistration.btnColorChangeFileExist(); // ���� ����� ��ġ�� ��ư
																// �� ����
				// new DeadLineRegistration(); //�ش� �������� ����������
				// DeadLineRegistration�� dispose�ؼ� �ٽ� ����
				// dispose();
			}
		} else if (e.getSource() == btnInsert) {
			if(tfName.getText().equals("")||tfQuantity.getText().equals("")){
				JOptionPane.showMessageDialog(this, "���� �Է��ϼ���");
			}else{
				String[] modelData = new String[7];
				modelData[0] = tfName.getText();
				modelData[1] = "";
				modelData[2] = "";
				modelData[3] = "";
				modelData[4] = tfQuantity.getText();
				modelData[5] = combo.getSelectedItem().toString();
				modelData[6] = "";
				tmodel.addRow(modelData);
				tfName.setText("");
				tfQuantity.setText("");
				combo.setSelectedIndex(0);
			}
		} else {
			int selRow = table.getSelectedRow();
			if(selRow==-1){
				JOptionPane.showMessageDialog(this, "��Ḧ ���� �� ��ư�� �����ּ���");
			}else{
				removeMaterial(selRow);
			}
			
		}
	}
	//��� ���� ��ư�� ������ �� �ش� ��Ḧ ����ϴ� ���� �޴��� �ִ� ��� ���� ���� ���ϰ� �Ѵ�.
	//�޴��� ������ �Ŀ� �ش� ��Ḧ ����ϴ� ����޴��� ���� ��쿡�� ������ �����ϵ��� �Ѵ�.
	void removeMaterial(int row){
		String[] filenameforRemove = {"coffee_material","latte_material","tea_material"};
		FileReader fr = null;
		BufferedReader br = null;
		String existChk = null;
		for(int i=0;i<filenameforRemove.length;i++){
			try {
				fr = new FileReader("JcafeData/InitData/InitMaterialData/"+filenameforRemove[i]);
				br = new BufferedReader(fr);
				String[] materialData = null;
				String readData = "";
				while((readData=br.readLine())!=null){
					materialData = readData.split("/");
					for(int j=1;j<materialData.length;j+=2){
						if(materialData[j].equals(table.getValueAt(row, 0))){
							existChk = materialData[0];
						}
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try{
					if(br!=null){br.close();}
					if(fr!=null){fr.close();}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(existChk!=null){
			JOptionPane.showMessageDialog(this,"<html>"+ existChk+ "���� �ش� ��ᰡ ���ǰ� �־� ��Ḧ ������ �� �����ϴ�."
					+ "<br>�ش� ��ᰡ ������ �ʴ� ��쿡�� ���� �����մϴ�.</html>");
		}else{
			tmodel.removeRow(row);
		}
	}

	// ���� ����ϴٰ� ��Ḧ ������ ���� ������ �κ��丮���� �ش� ����� �����Ѵ�.
	void removeInventoryMaterial() {
		String[] tableMaterialName = new String[table.getRowCount()];
		for (int i = 0; i < tableMaterialName.length; i++) {
			tableMaterialName[i] = (String) table.getValueAt(i, 0);
		}
		int idx = 0;
		String removeRowStr = "";
		for (int i = 0; i < useData[0].length; i++) {
			boolean chk = false;
			for (int j = 0; j < tableMaterialName.length; j++) {
				if (useData[0][i].equals(tableMaterialName[j])) {
					chk = true;
					break; // ������ �Ȱ��� ���� ������ �������� �ʾҴٴ� �ǹ̴ϱ� �ݺ����� ��������
				}
			}
			// �ݺ����� �� ���Ƶ� ������ �Ȱ��� ���� ã�� ���ϸ� chk�� false�� ���̹Ƿ� �ش� ��쿡�� ������ ���� ����
			if (!chk) {
				removeRowStr = removeRowStr + idx + "/";
			}
			idx++;
		}
		// �� �ٷ� ����� ������ ���� ���� ����
		String[] idxArray = removeRowStr.split("/");

		// ������ ���� ���������� �κ��丮������ �о�ͼ� �ش� ���� ����
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter("JCafeData/inventory");
			pw = new PrintWriter(fw);
			for (int i = 0; i < useData[0].length; i++) {
				for (int j = 0; j < idxArray.length; j++) {
					if (!(idxArray[0].equals(""))) {
						if (i == Integer.parseInt(idxArray[j])) {
							// System.out.println(idxArray[j]);
							// System.out.println(useData[0][j]);
							break;
						} else {
							pw.println(useData[0][i] + "/" + useData[1][i] + "/" + useData[2][i]);
						}

					} else {
						pw.println(useData[0][i] + "/" + useData[1][i] + "/" + useData[2][i]);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pw != null) {
					pw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// ������ �԰��� �ݿ���Ű�� ���ؼ� ���ָ���� �����Ų��.
	void saveOrder() {
		// Order registration
		Date date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		String dt = transFormat.format(date);

		File dir = new File("JCafeData/OrderRegistration/");
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}

		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter("JCafeData/OrderRegistration/" + dt);
			pw = new PrintWriter(fw);
			int rowCnt = tmodel.getRowCount();
			String saveData = "";
			// { "����", "�� �����", "ǥ�ػ�뷮", "ǥ�ع���", "��������","����", "Over/Loss" };
			// ������ �����ʹ� ����, ��������, ����
			for (int i = 0; i < rowCnt; i++) {
				saveData = table.getValueAt(i, 0) + "/"
						+ (table.getValueAt(i, 4).equals("") ? "0" : table.getValueAt(i, 4)) + "/"
						+ table.getValueAt(i, 5);
				pw.println(saveData);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pw != null) {
					pw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//���� �ԷµǴ� ĭ�� ���ڰ� �ԷµǴ� ��츦 ����
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (firstChk != 0&&table.getSelectedRow()!=-1) {
			try {
				Integer.parseInt((String) (table.getValueAt(table.getSelectedRow(), 4)));
			} catch (NumberFormatException e) {
				table.setValueAt("",table.getSelectedRow(), 4);
			}
			
		}
		firstChk=1;
	}
}


