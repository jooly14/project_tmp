import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class JCafeUseStamp extends JDialog implements ActionListener{
	boolean save;
	JPanel pnl;
	JTable table;
	int changeLine;
	
	int useStemp; // 스탬프 몇개 사용했는지 담음
	JLabel lblStemp; // 스템프 몇개 남았는지 표현
	String[][]strPrice; //메뉴 테이블에서 하나의 가격을 담음
	String[][]strTable;		//메뉴 테이블을 String배열로 담은것 > 스템프 사용 후의 테이블값을 담을 것
	String[] tableHeader={"이름","수량","금액"};	//헤더
	DefaultTableModel model;
	String userNumber=null;		//손님 번호 
	String userStempCnt=null;	
	int stempNum,coffeeCnt;				//손님 스탬프갯수, 총 커피수량
	JButton btnEnter,btnGoBack;
	JButton btnUse,btnComplete,btnCancel;
	JTextField tfPhoneNum;
	
	String changeStemp;
	public JCafeUseStamp(JCafePayment jcp) {// 처음 UI, 전역변수처리
		super(jcp,true);
		
		model=jcp.jc.model;
		init();
		
		pnl=new JPanel();
		JLabel lbl=new JLabel("전화번호");
		pnl.add(lbl);
		pnl.add(tfPhoneNum);
		pnl.add(btnEnter);
		pnl.add(btnCancel);
		getContentPane().setBackground(new Color(0x252525));
		setLocation(800, 455);
		this.add(pnl);
		this.setLocale(null);
		this.pack();
		setVisible(true);
	}
	void init(){// 컴포넌트 추가& 이벤트 추가 등등 자료 초기화
		table=new JTable(model);
		this.setIconImage(new ImageIcon("JCafeData\\ImageData\\JCafe icon.png").getImage());
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for(int i = 0 ; i < tcm.getColumnCount() ; i++)
		      	tcm.getColumn(i).setCellRenderer(dtcr);
		//가운데정렬
		for(int i=0;i<table.getRowCount();i++)
			coffeeCnt+=Integer.parseInt((String) table.getValueAt(i, 1));
		btnCancel=new JButton("취소");
		btnEnter=new JButton("확인");
		btnUse=new JButton("사용");
		btnComplete=new JButton("완료");
		btnGoBack=new JButton("취소");
		
		btnCancel.addActionListener(this);
		btnEnter.addActionListener(this);
		btnUse.addActionListener(this);
		btnComplete.addActionListener(this);
		btnGoBack.addActionListener(this);
		
		strPrice=new String[table.getRowCount()][3];
		strTable=new String[table.getRowCount()][3];
		
		for(int i=0;i<table.getRowCount();i++){
			int n=Integer.parseInt((String) table.getValueAt(i,1));//n은 커피 수량
			for(int j=0;j<3;j++){
				strTable[i][j]=(String)table.getValueAt(i,j);
				if(j==2){//strPrice[RowCnt][2]의 경우 총가격/수량 해서 하나당 가격을 strPrice [RowCnt][2]에 전부담기
					int a=Integer.parseInt(strTable[i][j])/Integer.parseInt(strTable[i][1]);
					strPrice[i][j]=""+a;
				}
			}
		}
		
		tfPhoneNum=new JTextField(10);
	}
	void stempEnter(){ // 초기화면에서 번호 입력 후 확인 누르면 나타나는 화면
		this.remove(pnl);
		setSize(500,550);
		setLocation(700, 255);
		JScrollPane sp=new JScrollPane(table);
		sp.getViewport().setBackground(Color.WHITE);
		pnl=new JPanel(null);
		pnl.setBackground(new Color(0x252525));
		lblStemp=new JLabel(tfPhoneNum.getText()+"님 가능한 스템프 : " + stempNum +" 개");
		lblStemp.setForeground(Color.WHITE);
		JLabel lblNorth=new JLabel("※스템프 5개 적립시 음료 1잔 무료");
		lblNorth.setForeground(Color.WHITE);
		lblNorth.setBounds(150,10,250,20);
		sp.setBounds(10, 30, 460, 430);
		btnUse.setBounds(100,465,80,30);
		btnComplete.setBounds(190,465,80,30);
		btnCancel.setBounds(280,465,80,30);
		btnUse.setBackground(Color.WHITE);
		btnComplete.setBackground(Color.WHITE);
		btnCancel.setBackground(Color.WHITE);
		
		pnl.add(lblNorth);
		pnl.add(sp);
		pnl.add(btnUse);
		pnl.add(btnComplete);
		pnl.add(btnCancel);
		
		this.add(pnl);
		this.add(lblStemp,"South");
	}
	void clickBtnUse(){ // 사용버튼 누르는 이벤트
			if(stempNum>=5){
				if((Integer.parseInt( strTable[table.getSelectedRow()][2] )>0)){
					useStemp++; //사용한 스템프 더해주기
					stempNum-=5;//보유 스템프 깎기
					lblStemp.setText(tfPhoneNum.getText()+"님 가능한 스템프 : " + stempNum +" 개"+
									  "               사용 한 스템프 : "+(useStemp*5)+" 개");
					//몇개 남았고 몇개 사용하는지 텍스트로 보여줄 label
					
					int a=Integer.parseInt(strTable[table.getSelectedRow()][2]);//테이블의 금액
					int b=Integer.parseInt(strPrice[table.getSelectedRow()][2]);//선택된거 한개 금액
					a=a-b; //테이블금액에서 한개금액 뺀거
					
					strTable[table.getSelectedRow()][2]=a+""; // 배열에서 금액 뺀걸로 저장
					table.setValueAt(a, table.getSelectedRow(), 2);// 테이블 보이는거 바꿔주기
					repaint();
					revalidate();
				} else JOptionPane.showMessageDialog(this, "사용할 수 있는 제품이 아닙니다.");
			}else JOptionPane.showMessageDialog(this, "스템프가 부족합니다.");
	}
	boolean phoneNumCheck(){// 입력한 번호가 스템프 데이터에 있으면 userNumber, userStempCnt 저장 후 true반환, 없으면 false 반환
		FileReader fr = null;
		BufferedReader br=null;
		try {
			fr=new FileReader(new File("JCafeData\\StempData"));
			br=new BufferedReader(fr);
			
			String l=null;
			while((l=br.readLine())!=null){
				String[]str=l.split("/");//str[0]에 전화번호 담는다.
				if(str[0].equals(tfPhoneNum.getText())){//전화번호랑 tfPhoneNum에 입력된거랑 같으면
					userNumber=str[0];					//userNumber에 전화번호 저장
					userStempCnt=str[1];				//userStempCnt에 스템프수량 저장(string)
					stempNum=Integer.parseInt(str[1]);	//stempNum에 스템프수량 저장(int)
					return true;						//입력한번호가 데이터에 있으면 true 반환해줌
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}try{
			if(fr!=null) fr.close();
			if(br!=null) br.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return false; //입력한 번호가 데이터에 없으면 false 반환해줌
	}
	void clickBtnComplete(){// 완료버튼 누르면 model,save 전역변수로 최종저장 후 화면꺼짐
		model=new DefaultTableModel(strTable,tableHeader);
		save=true;

		dispose();
	}
	void clickBtnCancel(){// 취소버튼 누르면 save=false 하고 화면꺼짐
		save=false;
		dispose();
	}
	void clickEnter() {// 확인버튼 이벤트
		if(phoneNumCheck()){
			stempEnter();
		}else
			JOptionPane.showMessageDialog(this, "입력한 번호의 정보가 없습니다.");
	}
	
	@Override public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnEnter) clickEnter();// 확인 버튼
		else if(e.getSource()==btnGoBack) dispose(); // 취소 버튼
		//여기까지 JCafePayment에서 스템프선택 직후 화면, 밑으로는 전화번호 확인 후 화면
		else if(e.getSource()==btnUse) clickBtnUse(); // 사용 버튼
		else if(e.getSource()==btnComplete) clickBtnComplete();//완료 버튼//save=true, 모델넘겨주기
		else if(e.getSource()==btnCancel)clickBtnCancel(); // 취소 버튼// save=false, 모델 안넘겨줌
	}
}