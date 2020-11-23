import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

class GetTimesale extends JDialog implements ActionListener{  //당일 판매표를 파일로 만들어줌
	BufferedReader br=null;
	FileReader fr=null;
	PrintWriter pw=null;
	FileWriter fw=null;
	JTable dleTable;
	String contents[][]={};
	DefaultTableModel model;
	JButton delete,no;
	int x;
	
	GetTimesale(JCafeCloseSale s){  //당일 판매표를 파일로 만들어줌
		super(s,true);
		this.setIconImage(new ImageIcon("JCafeData\\ImageData\\JCafe icon.png").getImage());
		this.setSize(500,500);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JPanel panel=new JPanel();
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		
		String[] header={"시간","품목","수량","금액"};                      //table 만들기
		model=new DefaultTableModel(contents,header);
		dleTable=new JTable(model);
		JScrollPane dleSp=new JScrollPane(dleTable);

		panel.add(dleSp);
		dleSp.getViewport().setBackground(Color.WHITE);//table 배경색 설정 
		
		dleTable.getColumn("시간").setPreferredWidth(60);
		dleTable.getColumn("품목").setPreferredWidth(200);
		dleTable.getColumn("수량").setPreferredWidth(20);
		dleTable.getColumn("금액").setPreferredWidth(50);
		
		delete=new JButton("판매취소");
		delete.setBackground(Color.WHITE);
		no=new JButton("돌아가기");
		no.setBackground(Color.WHITE);
		panel2.add(delete);
		panel2.add(no);
		delete.addActionListener(this);
		no.addActionListener(this);
		
		
		fileOpen();
		
		JLabel label=new JLabel(dleTable.getValueAt(0, 0)+" 판매내역");
		panel1.add(label);
		
		//setColor//////////////////////////////////////////////////////////////////////////////////
		Color color=new Color(0x252525);
		panel.setBackground(color);
		panel1.setBackground(color);
		panel2.setBackground(color);
		label.setForeground(Color.WHITE);
		this.getContentPane().setBackground(color);
		
		this.add(panel1,"North");
		this.add(panel);
		this.add(panel2,"South");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==delete){
			int result=JOptionPane.showConfirmDialog(this, "판매취소하시겠습니까?");
			//System.out.println("ok");테스트코드
			if(result==JOptionPane.YES_NO_OPTION){
				x=5;
				dispose();
			}
			else
				x=0;
		}
		else if(e.getSource()==no){
			x=0;
			dispose();
		}
		
	}
	int reTurn(){
		return x;
	}
	void fileOpen(){//그 날짜의 파일 열기
		try {
			fr=new FileReader("JCafeData/SaleData/CanclePayment/Delete");//파일이름을 받아온 날짜로 지정
			br=new BufferedReader(fr);
			String l=null;
			
			while((l=br.readLine())!=null){
				String data[]=l.split("/");					
				model.addRow(data);
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
	
	void fileSave(){//판매 정보가 갱신될때 마다 새로저장
		try {
			fw=new FileWriter("JCafeData/SaleData/CanclePayment/Delete");
			pw=new PrintWriter(fw);
			String dataA="";
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
////////////////////////////////////////////////////////////////////////////
public class JCafeCloseSale extends JDialog implements ActionListener{  //금일 판매량, 금액이 저장될 공간
	JTable closeTable;
	String contents[][]={};
	JButton cancel;
	JLabel totalLabel,tm1,title;//총 판매금액이 들어갈 레이블,시간,날짜
	String totalc;
	DefaultTableModel model;
	BufferedReader br=null;
	FileReader fr=null;
	PrintWriter pw=null;
	FileWriter fw=null;
	int idx;//삭제된줄의 개수
	UtilDateModel dmodel;
	String day1,day2,day3;
	int row;//마우스 클릭위치
	JComboBox tm2;
	String combo[],fcombo[];
	int cnt=0;//콤보박스 시간 카운트
	int total;
	JPanel titlep;
	JDatePickerImpl datePicker;
	JPanel panel,panel2;
	JScrollPane closeSp;
	
	PrintWriter pw2=null;//선택한 시간에 팔린것 파일로 따로 저장
	FileWriter fw2=null;
	
	JCafeCloseSale(){
		this.setSize(500,550);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	
		titlep=new JPanel();
		tm1=new JLabel("시간");
		title=new JLabel("날짜");
	
		//titlep.add(title);
		
		//날짜 선택 별도 파일 다운받아야함
		dmodel=new UtilDateModel();
		JDatePanelImpl datePanel=new JDatePanelImpl(dmodel);
		datePicker=new JDatePickerImpl(datePanel);
		//titlep.add(datePicker);
		day1=(dmodel.getYear()+""+(dmodel.getMonth()+1)+""+dmodel.getDay());
		day2=(dmodel.getYear()+"년 "+(dmodel.getMonth()+1)+"월 "+dmodel.getDay()+"일 ");
		//System.out.println(day1); //테스트파일
		datePicker.addActionListener(this);
		
		SimpleDateFormat format=new SimpleDateFormat("yyyy년MM월");
		Date day=new Date();
		day3=format.format(getYesterday(day));//하루전 날짜 받아와야 마감완료를 안해도 전날 달까지 매출을 확인할 수 있다.
		
		
		//금일 판매량이들어갈 테이블
		panel=new JPanel();
		String[] header={"시간","품목","수량","금액"};
		model=new DefaultTableModel(contents,header);
		closeTable=new JTable(model);
		closeSp=new JScrollPane(closeTable);
		panel.add(closeSp);
		
		closeTable.getColumn("시간").setPreferredWidth(60); //table 가로 길이 각각 조정
		closeTable.getColumn("품목").setPreferredWidth(200);
		closeTable.getColumn("수량").setPreferredWidth(20);
		closeTable.getColumn("금액").setPreferredWidth(50);
		
		totalLabel=new JLabel();
		cancel=new JButton("돌아가기");
		cancel.addActionListener(this);
		panel2=new JPanel();
		panel2.add(totalLabel);
		panel2.add(cancel);
		
		setColor();
		
		
		fileOpen();//파일을 테이블에 열어야 시간정보를 받아와 콤보박스에 넣을 수 있다./////////////////////////////////
		//콤보박스에 시간 넣어주기
		
		//tm2=new JComboBox(fcombo);
		//tm2.addActionListener(this);
		titlep.add(tm1);
		titlep.add(tm2);
		
		this.add(titlep,"North");
		this.add(panel,"Center");
		this.add(panel2,"South");
		this.setLocationRelativeTo(null);//다이얼이 중간에 띄워지도록
		this.setVisible(true);
		
	}
	public static Date getYesterday(Date today){//하루전 날짜 받아오기
		if(today==null){
			throw new IllegalStateException ( "today is null" );
		}
		Date yesterday=new Date();
		yesterday.setTime(today.getTime()-((long)1000*60*60*24));
		return yesterday;
	}
	void setColor(){
		Color color=new Color(0x252525);
		titlep.setBackground(color);
		cancel.setBackground(Color.white);
		panel.setBackground(color);
		panel2.setBackground(color);
		closeSp.getViewport().setBackground(Color.WHITE);//table 배경색 설정 
		totalLabel.setForeground(Color.WHITE);//하루 총 판매수익 글씨 하얀색으로
		tm1.setForeground(Color.WHITE);
		title.setForeground(Color.WHITE);
		getContentPane().setBackground(color);
	}
	
	void setcomboBox(){//시간선택창에 나올 시간들을 콤보박스에 넣어준다.
		combo=new String[closeTable.getRowCount()];//테이블의 Row만큼 콤보 크기를 키워준다
		if(closeTable.getRowCount()==1){//첫주문이 1잔만 들어왔을떄 
			combo[cnt++]=closeTable.getValueAt(0,0).toString();
		}
		for(int i=1;i<closeTable.getRowCount();i++){
			String s1=closeTable.getValueAt(i-1,0).toString();
			String s2=closeTable.getValueAt(i,0).toString();
			if(i<closeTable.getRowCount()-1){
				if(s1.equals(s2)!=true){//앞시간과 뒷시간을 비교 다르면 앞시간을 넣어준다
					combo[cnt++]=s1;
					}
			}
			else if(i==closeTable.getRowCount()-1){//마지막 주문의 경우
				//System.out.println(s1+" "+s2);테스트코드
				//System.out.println(s1.equals(s2));
				if(s1.equals(s2)!=true){//앞시간과 주문시간이 같지 않으면 앞과 뒤 주문 모두의 시간을 저장
					combo[cnt++]=s1;
					combo[cnt++]=s2;
				}
				else{//앞시간과 주문시간이 같으면 뒷시간 저장
					combo[cnt++]=s2;
				}
			}	
		}
		fcombo=new String[cnt];
		for(int i=0;i<cnt;i++){
			fcombo[i]=combo[i];
		}/*
		int x=fcombo.length;    //테스트코드 콤보박스에 잘 들어가고 있는지
		System.out.println("-------------------------");
		for(int j=0;j<x;j++){
			System.out.println(fcombo[j]);
		}*/
		cnt=0;
		tm2=new JComboBox(fcombo);
		tm2.addActionListener(this);
		titlep.removeAll();
		
		titlep.add(title);
		titlep.add(datePicker);
		titlep.add(tm1);
		titlep.add(tm2);
		
		this.repaint();
		this.revalidate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {                              //ActionListener
		if(e.getSource()==tm2){
			String getCombo=tm2.getSelectedItem().toString();//콤보박스에서 선택한 시간
					try {
						fw2=new FileWriter("JCafeData/SaleData/DaySaleData/"+day1);       //원하는 시간대의 주문만 따로 Delete파일에 넣어준다.
						pw2=new PrintWriter(fw2);
						for(int i=0;i<closeTable.getRowCount();i++){
							String getRow=closeTable.getValueAt(i,0).toString();
							if(getRow.equals(getCombo)==true){
								String dataB="";
								dataB=closeTable.getValueAt(i,0).toString()+"/"+closeTable.getValueAt(i,1).toString()+"/"+closeTable.getValueAt(i,2).toString()+"/"+closeTable.getValueAt(i, 3);
								//System.out.println(dataB);
								pw2.println(dataB);
							}
						}
				
					} catch (IOException e1) {
						e1.printStackTrace();
					}finally{
						if(fw2!=null)
							try {
								fw2.close();
								if(pw2!=null)pw2.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
					}	                                              //여기까지
			GetTimesale g=new GetTimesale(this);                    //원하는 시간대의 주문만 보여주는 다이얼로그
			//System.out.println(g.reTurn());                        
			
			if(g.reTurn()==5){ //g.reTurn() 판매 취소를 누르면 5를 반환해줌
				fileSave2();//선택된 시간대 정보만 빼고 원래 day파일에 저장해줌 (선택된 시간의 주문 삭제)
				fileOpen();// 삭제한뒤 파일을 새로 열어주야함
			}
		}
		else if(e.getSource()==cancel){
			dispose();
		}
		else {
			day1=(dmodel.getYear()+""+(dmodel.getMonth()+1)+""+dmodel.getDay()+"");
			day2=(dmodel.getYear()+"년 "+(dmodel.getMonth()+1)+"월 "+dmodel.getDay()+"일 ");
			//System.out.println(day1); //테스트코드
			fileOpen();
			//fileSave();
		}
	}
	
	void fileOpen(){//그 날짜의 파일 열기
		fileClose();//이전 파일을 닫아야 다음 파일이 열리게
		total=0;//선택한 날짜 판매 총금액 초기화
		try {
			fr=new FileReader("JCafeData/SaleData/DaySaleData/"+day1);//파일이름을 받아온 날짜로 지정
			br=new BufferedReader(fr);
			String l=null;
			
			while((l=br.readLine())!=null){
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
		
		setcomboBox(); 
		
		//////////그날 매출
		for(int i=0;i<closeTable.getRowCount();i++){
			String str=(String) closeTable.getValueAt(i, 3);
			total+=Integer.parseInt(str);
		}			
		//System.out.println(total); 테스트코드
		DecimalFormat dc=new DecimalFormat("#,###");
		totalc=String.format("%,d", total);     //3자리마다 , 찍어줌
		totalLabel.setText(day2+"총 수입 : "+totalc+"원");		
	}
	
	String totalReturn(){//////////////////////하루 판매수입 반환해주는 메소드
		return day2+"/"+totalc+"원 ";
		
	}
	void fileClose(){//새로 파일 열때 깔끔하도록 지워줌
		for(int i=0;i<idx;idx--){
			model.removeRow(idx-1);
		}
	}
	void fileSave2(){
		try {
			fw=new FileWriter("JCafeData/SaleData/DaySaleData/"+day1);
			pw=new PrintWriter(fw);
			String getCombo=tm2.getSelectedItem().toString();
			String dataA="";
			for(int i=0;i<closeTable.getRowCount();i++){
				String getRow=closeTable.getValueAt(i,0).toString();
				if(getRow.equals(getCombo)!=true){
					dataA=closeTable.getValueAt(i,0).toString()+"/"+closeTable.getValueAt(i,1).toString()+"/"+closeTable.getValueAt(i,2).toString()+"/"+closeTable.getValueAt(i, 3);
					//System.out.println(dataA);  테스트코드
					pw.println(dataA);
				}
			}
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

