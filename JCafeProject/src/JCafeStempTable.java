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
	String[]strHeader={"전화번호","스탬프 갯수"};
	String[][]strTable;
	BufferedReader br;
	FileReader fr;
	DefaultTableModel model;
	JTable table;
	JPanel pnl;
	JScrollPane spTable;
	void init(boolean b){ //값 초기화(Boolean으로 스템프 테이블 선택 가능or불가능 하게)
		this.setIconImage(new ImageIcon("JCafeData\\ImageData\\JCafe icon.png").getImage());
		setSize(400,500);
		model=new DefaultTableModel(strTable,strHeader);
		table=new JTable(model);
		spTable=new JScrollPane(table);
		showData();
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//가운데정렬
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for(int i = 0 ; i < tcm.getColumnCount() ; i++){
		      	tcm.getColumn(i).setCellRenderer(dtcr);  
		}
		
		btnRefresh=new JButton("새로고침");
		btnChangeNumber=new JButton("번호변경");
		btnRefresh.setBackground(Color.WHITE);
		btnChangeNumber.setBackground(Color.WHITE);
		JLabel lblTitle=new JLabel("스탬프 관리");
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
	JCafeStempTable(JCafeMain jcm) {//메인에서 크루접속시 번호변경 액션리스너 x
		super(jcm,true);
		init(true);		
	}
	JCafeStempTable(JCafeManagerMenu jcmm){// 여기만 번호변경 액션리스너 추가
		super(jcmm,true);
		init(false);
	}
	void showData(){// 스템프데이터 읽어와서 화면에 뿌려주는 역할
		for(int i=0;i<model.getRowCount();)
			model.removeRow(i);
		try {
			fr = new FileReader(new File("JCafeData\\StempData"));//스템프데이터 가져오기
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
	void numDataChange(){// 번호변경 클릭시 데이터 변경하는 창 띄우고, 확인 누르면 번호 변경하고 데이터 저장까지 해주는 역할
			PrintWriter pw=null;
			FileWriter fw = null;
			
			try {
				fw = new FileWriter(new File("JCafeData\\StempData"));// (true없으므로 초기화 후) StempData를 작성하겠다. 
				pw = new PrintWriter(fw);
				
				JCafePhoneNum jcn=new JCafePhoneNum(this);	// PhoneNum 화면 띄우기
				String getNumber=jcn.getPhoneNum();		// getNumber가 휴대폰번호
				table.setValueAt(getNumber, table.getSelectedRow(), 0);//테이블에 선택된 행의 0번째 값에 getNumber을 넣는다.
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
			showData();//새로고침
		if(e.getSource()==btnChangeNumber)
			numDataChange();//번호변경
	}
}