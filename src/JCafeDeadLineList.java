import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class JCafeDeadLineList extends JFrame implements ActionListener, PropertyChangeListener {
	//테스트용 코드
	public static void main(String[] args) {
		new JCafeDeadLineList();
	}
	
	JTable table;
	DefaultTableModel model;
	JPanel pnlBtn;
	JButton  btnSet, btnReset;
	String[] header = { "재료명", "실 재고량", "단위", "전일재고량", "표준재고량", "loss/over", "비고" };

	int firstChk;	//propertychangelistner이벤트에서 사용하려고 생성
	String[][] content; // 모델에 add시킬 배열 //필요한 모든 데이터를 가지고 있을 예정
	JCafeGetStockData gst = new JCafeGetStockData();	//테이블에 추가할 데이터 가져오기 위해서 클래스 생성
	
	public JCafeDeadLineList() {
		this.setSize(600, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		table = new JTable();
		// 수량만 수정 가능하게 만들었음
		table.setModel(new DefaultTableModel(new Object[][] {},header){	
			boolean[] columnEditables = new boolean[] { false, true, false, false, false, false, true };
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		// 모델을 변수에 담지 않고 무명클래스로 만들어서 테이블에서 모델을 따로 받아옴
		model = (DefaultTableModel) table.getModel();

		//JCafeGetStockData을 통해서 가져온 사용량 데이터를 테이블에 추가
		content = new String[gst.inventoryArray[0].length][7];
		for (int i = 0; i < gst.inventoryArray[0].length; i++) {
			content[i][0] = gst.inventoryArray[0][i];	//재료명
			content[i][1] = "";	//실재고량은 입력할 칸
			content[i][2] = gst.inventoryArray[2][i];	//단위
			content[i][3] = gst.inventoryArray[1][i];	//전일재고량
			content[i][4] = (Integer.parseInt(gst.inventoryArray[1][i]) - gst.useTotal[i]) + "";	//표준재고량//전일재고량에서 하루 사용량을 뺌
			content[i][5] = "0";	//overloss는 처음에는 0인데 실재고량을 입력하면 자동으로 계산
			content[i][6] = "";		//비고
			model.addRow(content[i]);
		}
		//실 재고량 입력 할때마다 오버로스가 수정되게끔 이벤트를 달았다.
		table.addPropertyChangeListener(this);
		
		//테이블 내용을 가운데 정렬
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		for(int i=0;i<header.length;i++){
			table.getColumn(header[i]).setCellRenderer(celAlignCenter);
		}
		
		//테이블에서 재료명이 잘리는 부분을 수정
		table.getColumn(header[0]).setPreferredWidth(230);
		table.getColumn(header[1]).setPreferredWidth(130);
		table.getColumn(header[3]).setPreferredWidth(100);
		table.getColumn(header[4]).setPreferredWidth(100);
		table.getColumn(header[5]).setPreferredWidth(100);

		JScrollPane sp = new JScrollPane(table);
		
		//화면 꺼질때 다시 DeadLineRegistration을 띄우기 위해서 
				//마감 파일이 있으면 버튼색을 바꾸려다보니..
				this.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						new DeadLineRegistration();
					}
				});
		
		

		btnSet = new JButton("완료");
		btnReset = new JButton("초기화");
		btnSet.addActionListener(this);
		btnReset.addActionListener(this);

		pnlBtn = new JPanel();
		pnlBtn.add(btnSet);
		pnlBtn.add(btnReset);
		
		
		
		
		
		
		
		this.getContentPane().setBackground(new Color(0x003E00));
		pnlBtn.setBackground(null);
		//sp.setBorder(BorderFactory.createEmptyBorder());
		table.setRowSorter(new TableRowSorter(model));	//정렬 기능 넣었지만 쓸일은 없겠죠..
		table.setShowHorizontalLines(false);
		table.setRowHeight(30);
		table.getTableHeader().setBackground(Color.WHITE);
		
		
		sp.setBounds(10,100,570,600);
		pnlBtn.setBounds(10,710,570,300);
		this.setLayout(null);
		this.add(sp);
		this.add(pnlBtn);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnSet) {// 완료
			saveFile();	//완료 누르면 발주 등록에 사용할 파일을 저장
			saveChangeInventoryFile();	//실제 인벤토리에 실재고량을 반영시킨다.
			new DeadLineRegistration();	
			dispose();
		} else if (arg0.getSource() == btnReset) {// 작성한 실 재고량을 초기화시킴
			for(int i=0;i<table.getRowCount();i++){
				table.setValueAt("", i, 1);	//실재고량 빈칸으로 만들고
				table.setValueAt("0", i, 5);	//overloss 다시 0으로 만들었음
			}
		}
	}

	
	//발주 등록할때 사용하기 위해서 파일로 저장
	void saveFile() {
		//저장될 데이터 순서 :재료명/실제 인벤/단위/전일재고량/표준사용량/표준 재고량/overloss/비고
		//저장 파일명JCafeData/SaleData/DealineData/20201121
		//  20201119_jcafe_deadlinelist.txt
		Date date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		String dt = transFormat.format(date);
		
		FileWriter fw = null;
		PrintWriter pw = null;
		//해당 폴더가 없으면 폴더를 만듦
		File dir = new File("JCafeData/SaleData/DealineData/");
		if(!dir.isDirectory()){
			dir.mkdirs();
		}
		try {
			fw = new FileWriter("JCafeData/SaleData/DealineData/"+dt);
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
	
	//인벤토리에 실제 재고량을 반영시킨다.
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
	
	
	
	//실 재고량에 값 입력 시에 over/loss 칸에 계산 후 자동입력 되게 하는 이벤트메서드
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (firstChk != 0&&table.getSelectedRow()!=-1) {
			String currentInventory = (String) (table.getValueAt(table.getSelectedRow(), 1));
			int intCurrentInventory = 0;	//실재고량
			int standardInventory = 0;		//표준재고량
			if (!(currentInventory.equals(""))) {
				intCurrentInventory = Integer.parseInt(currentInventory);
				standardInventory = Integer.parseInt((String) (table.getValueAt(table.getSelectedRow(), 4)));
				// 실재고량 - 표준재고량 = over/loss
				table.setValueAt((intCurrentInventory - standardInventory)+"", table.getSelectedRow(), 5);
			}
		}
		//propertychangelistner를 붙여놓으니까
		//처음 생성될때도 해당 이벤트가 실행되는 문제가 있음
		//그래서 확인을 위한 변수를 넣었음
		firstChk = 1;
	}
}
