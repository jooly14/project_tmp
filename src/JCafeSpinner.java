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

//발주 등록 버튼을 누르면 나오게 될 화면
//일주일 데이터는 꼭 있어야 함
//"JCafeData/SaleData/deadlineData/" 폴더 안에 데이터
public class JCafeSpinner extends JPanel implements ActionListener ,PropertyChangeListener{
	/*
	 * public static void main(String[] args) { new JCafeSpinner(); }
	 */
	JLabel laY, laM, laD;
	JButton btnOrder;
	JButton btnInsert;
	JButton btnDelete;
	JTable table;
	String str[] = { "재료명", "현 재고량", "표준사용량", "표준발주", "실제발주", "단위", "Over/Loss" };
	// deaddline/날짜 파일에 저장된 내용 : // 재료명/실제 인벤/단위/전일재고량/표준사용량/표준 재고량/overloss/비고
	// 표준 사용량은 일주일 단위로 사용했던 재료량을 표시
	// 표준 발주 = 현 재고량 - 표준사용량
	// 실제 발주는 직접 적을 수 있게 설정
	String strTable[][] = {};
	DefaultTableModel tmodel;

	String[][] useData = new String[6][];

	// 파일명이 날짜로 돼있어서 날짜계산에 사용될 변수들
	Calendar cal;
	String dt;

	int[] usageSum;// 표준사용량
	int[] overlossSum;// 표준오버로스

	// readUsage()에서 사용하는 변수
	String overlossStr = "";
	String usageStr = "";

	JTextField tfName; // 추가할 재료명
	JTextField tfQuantity; // 추가할 재료의 발주량
	JComboBox combo;// 추가할 재료의 단위
	// JTextField tfUnit;

	DeadLineRegistration deadLineRegistration;
	
	int firstChk=0;//propertychangelistener에서 처음 생성할때도 이벤트가 작동하는 것을 막기위해 변수사용

	public JCafeSpinner(DeadLineRegistration deadLineRegistration) {
		this.deadLineRegistration = deadLineRegistration;

		// this.setLocationRelativeTo(null);
		// this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);

		// 일주일 간 파일을 다 읽어오려고 날짜를 가져옴
		cal = Calendar.getInstance();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		dt = transFormat.format(cal.getTime());

		// 다른 값들은 수정할 수 없게 만들기 위한 부분
		// 실제 발주만 수정
		table = new JTable();

		table.setModel(new DefaultTableModel(strTable, str) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, true, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		// 모델을 따로 안 만들어서 받아오기
		tmodel = (DefaultTableModel) table.getModel();
		table.addPropertyChangeListener(this);
		
		
		// 테이블에 내용 넣기
		readFile(); // 마감할때 저장한 파일에서 데이터를 읽어옴
		getUsageSum(); // 한 주 단위로 사용되는 재료의 총 량을 계산
		setTableData(); // 테이블에 가져온 데이터를 표현


		// 테이블 내용을 가운데 정렬
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < str.length; i++) {
			table.getColumn(str[i]).setCellRenderer(celAlignCenter);
		}
		table.setRowSorter(new TableRowSorter(tmodel));	//정렬 기능
		// 테이블에서 재료명이 잘리는 부분을 수정
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
		btnOrder = new JButton("발주 등록");
		btnOrder.addActionListener(this);
		btnInsert = new JButton("재료 추가");
		btnInsert.addActionListener(this);
		btnDelete = new JButton("재료 삭제");
		btnDelete.addActionListener(this);
		btnPnl.add(btnInsert);
		btnPnl.add(btnDelete);
		btnPnl.add(new JLabel("              "));
		btnPnl.add(btnOrder);

		// 필요한 재료 추가할 수 있게 입력받는 부분
		JPanel inPnl1 = new JPanel();
		JPanel inPnl2 = new JPanel();
		JPanel inPnl3 = new JPanel();
		tfName = new JTextField(14);
		tfQuantity = new JTextField(10);
		String[] comboD = { "Kg", "L", "Box" };
		combo = new JComboBox(comboD);
		JLabel lbl1 = new JLabel("재료명:");
		inPnl1.add(lbl1);
		inPnl1.add(tfName);
		JLabel lbl2 = new JLabel("발주수량 :    ");
		inPnl2.add(lbl2);
		inPnl2.add(tfQuantity);
		JLabel lbl3 = new JLabel("단위 :              ");
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

		// 닫힐때 DeadLineRegistration가 다시 열리도록 설정
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

	// 오늘자 DeadlineData/오늘날짜 파일을 읽어온다.
	void readFile() {
		FileReader fr = null;
		BufferedReader br = null;
		String data[] = { "", "", "", "", "", "" };
		// 재료명/실제 인벤/단위/전일재고량/표준사용량/표준 재고량
		try {
			fr = new FileReader("JCafeData/SaleData/DeadlineData/" + dt);
			br = new BufferedReader(fr);
			String readData = "";
			while ((readData = br.readLine()) != null) {
				String[] deadlineData = readData.split("" + "/");
				for (int i = 0; i < data.length; i++) {
					// 각 행의 데이터를 열 단위로 한줄 저장한다.
					// data[0]에는 재료명1/재료명2/재료명3/...
					// data[1]에는 실제재고량1/실제재고량2/실제재고량3/...
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
		// data의 데이터를 useData[][] 이중배열에 넣음
		for (int i = 0; i < data.length; i++) {
			useData[i] = data[i].split("/");
		}

		// 확인용 코드
		/*
		 * for(int i=0;i<useData.length;i++){ for(int
		 * j=0;j<useData[i].length;j++){ System.out.print(useData[i][j]); }
		 * System.out.println(); }
		 */

	}

	// 표준 사용량이랑 표준 오버로스 구하는 메서드
	// 지난 일주일 마감 파일을 가져와서 표준 사용량과 표준오버로스를 계산
	void getUsageSum() {
		String[][] dayUsage = new String[7][];
		String[][] dayOverloss = new String[7][];
		String dayNotExist = "";
		int dayExist = 7;
		for (int i = 0; i < 7; i++) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
			String dt2 = transFormat.format(cal.getTime());
			// 일주일 단위의 파일을 읽는다.
			readUsage(dt2);
			dayUsage[i] = usageStr.split("/");
			dayOverloss[i] = overlossStr.split("/");
			if(usageStr.equals("")){
				dayNotExist = dayNotExist + dt2 +" ";
				dayExist--;
			}
			
			usageStr = "";
		}
			// 가져온 값을 계산하기 위해서 int로 변환			//재료명 갯수	//7일
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
			// 재료 당 표준사용량 표준 오버로스를 계산함
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
				JOptionPane.showMessageDialog(this,"<html>"+ dayNotExist+" 재고등록 파일이 없습니다.<br>"
						+ "표준사용량은 "+dayExist+"일치 사용량을 합산한 양입니다.</html>");
			}else{
				JOptionPane.showMessageDialog(this,"표준사용량은 "+dayExist+"일치 사용량을 합산한 양입니다.");
			}

	}

	// 일주일 사용량이랑 오버로스를 파일에서 불러오는 메서드
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

	// 테이블에 내용을 담는 메서드
	void setTableData() {
		/*
		 * for (int i = 0; i < useData.length; i++) { for (int j = 0; j <
		 * useData[i].length; j++) { System.out.print(i + "/" + j);
		 * System.out.println("/" + useData[i][j] + "/"); }
		 * System.out.println(); }
		 */
		// 테이블 헤더 순서 : "재료명", "현 재고량", "표준사용량", "표준발주", "실제발주", "단위",
		// "Over/Loss"
		
		String[] content = new String[7];
		for (int j = 0; j < useData[0].length; j++) {
			// System.out.println("/" + useData[i][j] + "/");
			content[0] = useData[0][j]; // 재료명
			content[1] = useData[1][j] + "   " + useData[2][j]; // 단위랑 함께 표현
			content[2] = usageSum[j] 
					+ "   " + useData[2][j];
			// 작은 단위로 표현되던 것을 큰단위로 변환해서 표준발주를 표현
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
			content[4] = ""; // 실제발주는 직접 입력
			// 단위를 표시
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
			int jopChk = JOptionPane.showConfirmDialog(this, "<html>발주 등록하시겠습니까?<br>발주 등록을 완료하신 후에는 수정하실 수 없습니다.</html>", "", JOptionPane.OK_CANCEL_OPTION);
			if (jopChk == JOptionPane.OK_OPTION) {
				saveOrder(); // 발주 등록 파일을 저장
				removeInventoryMaterial(); // 삭제된 재료명을 인벤토리에 반영 //추가된 재료명은 다음날
											// 입고될 때 반영
				deadLineRegistration.remove(this);
				deadLineRegistration.setSize(600, 210);
				deadLineRegistration.btnColorChangeFileExist(); // 발주 등록을 마치면 버튼
																// 색 변경
				// new DeadLineRegistration(); //해당 프레임이 열리기전에
				// DeadLineRegistration를 dispose해서 다시 생성
				// dispose();
			}
		} else if (e.getSource() == btnInsert) {
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
		} else {
			int selRow = table.getSelectedRow();
			removeMaterial(selRow);
			
		}
	}
	//재료 삭제 버튼을 눌렀을 때 해당 재료를 사용하는 음료 메뉴가 있는 경우 삭제 하지 못하게 한다.
	//메뉴를 삭제한 후에 해당 재료를 사용하는 음료메뉴가 없을 경우에만 삭제가 가능하도록 한다.
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
			JOptionPane.showMessageDialog(this,"<html>"+ existChk+ "에서 해당 재료가 사용되고 있어 재료를 삭제할 수 없습니다."
					+ "<br>해당 재료가 사용되지 않는 경우에만 삭제 가능합니다.</html>");
		}else{
			tmodel.removeRow(row);
		}
	}

	// 발주 등록하다가 재료를 삭제된 것이 있으면 인벤토리에서 해당 목록을 제거한다.
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
					break; // 재료명이 똑같은 것이 있으면 삭제되지 않았다는 의미니깐 반복문을 빠져나옴
				}
			}
			// 반복문을 다 돌아도 재료명이 똑같은 것을 찾지 못하면 chk가 false일 것이므로 해당 경우에는 삭제할 행을 저장
			if (!chk) {
				removeRowStr = removeRowStr + idx + "/";
			}
			idx++;
		}
		// 한 줄로 저장된 삭제될 행을 나눠 담음
		String[] idxArray = removeRowStr.split("/");

		// 삭제될 행이 정해졌으면 인벤토리파일을 읽어와서 해당 행을 삭제
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

	// 다음날 입고를 반영시키기 위해서 발주목록을 저장시킨다.
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
			// { "재료명", "현 재고량", "표준사용량", "표준발주", "실제발주","단위", "Over/Loss" };
			// 저장할 데이터는 재료명, 실제발주, 단위
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

	//숫자 입력되는 칸에 문자가 입력되는 경우를 예방
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



