import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class JCafenewMenu extends JDialog implements ActionListener{
	JTable table;
	JTextField tname,tpay,tcont;//새메뉴,금액,수량 받는 text
	int cnt;//수량 int형 변환
	String contents[][]={};
	int idx,plusidx=0,minusidx=0;
	JButton newMenu,delMenu;//메뉴추가,삭제 버튼
	DefaultTableModel model;
	/////
	String content; //메뉴 추가시 사용 input()메소드

	BufferedReader br=null;//set
	FileReader fr=null;
	PrintWriter pw=null;//save
	FileWriter fw = null;
	String fstr="JCafeData/InitData/InitMenuData/coffee";
	
	int row;//마우스로 클릭한 위치
	
	JComboBox comboMenu;
	
	JCafenewMenu(JCafeManagerMenu c, JCafeNewMenuPnl np){
		super(c);
		this.setIconImage(new ImageIcon("JCafeData\\ImageData\\JCafe icon.png").getImage());
		this.setSize(500,550);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		JLabel title=new JLabel("메뉴관리");
		JPanel titlep=new JPanel();
		
		String str[]={"coffee","latte","tea"};
		comboMenu=new JComboBox(str);
		comboMenu.addActionListener(this);
		titlep.add(title,"Center");
		titlep.add(comboMenu);
		
	
		JPanel panel=new JPanel();
		JPanel panel2=new JPanel();
		
		//메뉴관리 테이블 구성
		String[] header={"메뉴","가격","수량"};
		model=new DefaultTableModel(contents,header);
		table=new JTable(model);
		JScrollPane sp=new JScrollPane(table);
		panel.add(sp);
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//table 가운데정렬
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for(int i = 0 ; i < tcm.getColumnCount() ; i++){
		      	tcm.getColumn(i).setCellRenderer(dtcr);  
		}
		//메뉴 금액 입력, 추가 삭제 하는 부분
		JLabel name=new JLabel("메뉴:");
		tname=new JTextField(5);
		JLabel pay=new JLabel("금액:");
		tpay=new JTextField(5);
		JLabel cont=new JLabel("수량");
		tcont=new JTextField(5);
		newMenu=new JButton("메뉴추가");
		delMenu=new JButton("메뉴삭제");
		
		newMenu.addActionListener(this);
		delMenu.addActionListener(this);

		sp.getViewport().setBackground(Color.WHITE);//table 배경색 설정 
		panel2.add(name);
		panel2.add(tname);
		panel2.add(pay);
		panel2.add(tpay);
		panel2.add(cont);
		panel2.add(tcont);
		panel2.add(newMenu);
		panel2.add(delMenu);
		
		Color color=new Color(0x252525);	//panel 바탕색 변경
		titlep.setBackground(color);
		panel.setBackground(color);
		panel2.setBackground(color);
		//색상변경
		title.setForeground(Color.WHITE);
		name.setForeground(Color.WHITE);
		pay.setForeground(Color.WHITE);
		cont.setForeground(Color.WHITE);
		newMenu.setBackground(Color.WHITE);
		delMenu.setBackground(Color.WHITE);
		comboMenu.setBackground(Color.WHITE);
		
		this.add(panel);
		this.add(titlep,"North");
		this.add(panel2,"South");
		this.setLocationRelativeTo(null);
		fileOpen();  //창 열릴때 자동으로 파일 불러오게
		this.setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {//버튼 클릭시
		String combo=comboMenu.getSelectedItem().toString();
		if(e.getSource()==newMenu){  //새메뉴 추가
			input();
			tname.setText("");
			tpay.setText("");
			tcont.setText("");
			plusidx++;
		
		}
		else if(e.getSource()==delMenu){  //클릭한 행 지우기
			row = table.getSelectedRow();
			model.removeRow(row);
			minusidx++;
		}
		fileSave();
		if(combo=="coffee"){
			fileClose();
			fstr="JCafeData/InitData/InitMenuData/coffee";
			fileOpen();
		}	
		else if(combo=="latte"){
			fileClose();
			fstr="JCafeData/InitData/InitMenuData/latte";
			fileOpen();
		}
		else if(combo=="tea"){
			fileClose();
			fstr="JCafeData/InitData/InitMenuData/tea";
			fileOpen();
		}
		repaint();
		revalidate();
	}
	
	void fileOpen(){   //table에 파일 불러옴
		
		try {
			
			fr=new FileReader(fstr);//파일경로 지정
			br=new BufferedReader(fr);//읽어온 파일 buffer에 담기 버퍼는 한줄
			
			String l=null;
			while((l=br.readLine())!=null){//버퍼에담긴거 한줄씩 읽어오기 빈줄 나올때까지
				String data[]=l.split("/");
				model.addRow(data);
				idx++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
				try {
					if(fr!=null)fr.close();
					if(br!=null)br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}


	void fileSave(){//정보가 갱신될때 마다 새로저장
		
		
		try{
			fw = new FileWriter(fstr);//저장위치  true 안써줘서 초기화하고 새로 저장
			pw = new PrintWriter(fw);
			
			
			String dataA="";
			for(int i=0;i<table.getRowCount();i++){	
				dataA=table.getValueAt(i, 0)+"/"+table.getValueAt(i, 1)+"/"+table.getValueAt(i, 2);//새로운 파일에 저장
				pw.println(dataA);
				
			}
			
		}catch (IOException e1) {
			e1.printStackTrace();
		}finally {
			try {
				if(fw !=null) fw.close();
				if(pw != null) pw.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	void input(){ //메뉴추가
		content=tname.getText()+"/"+tpay.getText()+"/"+tcont.getText();
		String data[]=content.split("/");
		model.addRow(data);
	}
	void fileClose(){//comboBox에서 커피,라떼,티 선택시 앞에 내용 지울때 사용
		
		for(int i=0;i<idx+plusidx-minusidx;idx--)
		{
			model.removeRow(idx-1+plusidx-minusidx);
			}
		
	}
}

