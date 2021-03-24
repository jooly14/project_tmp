import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//class JCafeStempTable extends JDialog implements ActionListener{
class JCafeStempTable extends JDialog implements ActionListener{
	JButton btnChangeNumber,btnRefresh;
	String[]strHeader={"전화번호","스탬프 갯수"};
	String[][]strTable;
	BufferedReader br;
	FileReader fr;
	DefaultTableModel model;
	JTable table;
	void init(Boolean b){ //값 초기화(Boolean으로 스템프 테이블 선택 가능or불가능 하게)
		setSize(400,500);
		
		model=new DefaultTableModel(strTable,strHeader);
		table=new JTable(model);
		JScrollPane spTable=new JScrollPane(table);
		showData();
		
		btnRefresh=new JButton("새로고침");
		btnChangeNumber=new JButton("번호변경");
		table.setEnabled(b);
		btnRefresh.addActionListener(this);
		
		JPanel pnl=new JPanel(new GridLayout(1,2));
		pnl.add(btnChangeNumber);
		pnl.add(btnRefresh);
		this.add(pnl,"South");
		this.add(spTable);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	JCafeStempTable(JCafeMain jcm) {//메인에서 크루접속시 번호변경 액션리스너 x
		super(jcm,true);
		init(false);
		btnChangeNumber.addActionListener(this);
	}
	JCafeStempTable(JCafeManagerMenu jcmm){// 여기만 번호변경 액션리스너 추가
		super(jcmm,true);
		init(true);
		btnChangeNumber.addActionListener(this);
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
			showData();//데이터 보여주세용
		if(e.getSource()==btnChangeNumber)
			numDataChange();//번호바꿔주세용
	}
}