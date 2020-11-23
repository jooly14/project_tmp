import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

class JCafePayment extends JDialog implements ActionListener{
	JButton btnCard1,btnCard2,btnCash1,btnCash2,btnPayment,btnGoBack,btnStemp1,btnStemp2,btnStempAccumulate1,btnStempAccumulate2;
	JTextField tfInstallment,tfCardNumber,tfPrice;
	JButton btnReceipts,btnProofOfExpenditure,btnCashreceipts,btnNull;
	JPanel pnlCash,pnlCard;
	DefaultTableModel model;
	int useStempCnt,userStemp,coffeeCnt;
	String userPhoneNum;
	boolean payment;
	boolean payToCash;
	JCafeMain jc;
	Color color = new Color(0xcbe2a8);
//	Color colorGreen = new Color(0x003E00);
	
	void init(){// 컴포넌트 추가& 이벤트 추가
		this.setIconImage(new ImageIcon("JCafeData\\ImageData\\JCafe icon.png").getImage());
		this.setIconImage(new ImageIcon("JCafeData\\ImageData\\아이콘.png").getImage());
		pnlCard=new JPanel(null);
		pnlCash=new JPanel(null);
		tfInstallment=new JTextField(3);
		tfCardNumber=new JTextField(10);
		btnCard1=new JButton("카드");
		btnCash1=new JButton("현금");
		btnCard2=new JButton("카드");
		btnCash2=new JButton("현금");
		btnStemp1=new JButton("<HTML><center>스탬프<br> 사용");
		btnStemp2=new JButton("<HTML><center>스탬프<br> 사용");
		btnStempAccumulate1=new JButton("<HTML><center>스탬프<br> 적립");
		btnStempAccumulate2=new JButton("<HTML><center>스탬프<br> 적립");
		btnCashreceipts=new JButton("<HTML><center>현금<br>영수증");
		
		tfPrice=new JTextField();
		tfPrice.setEnabled(false);

		btnReceipts=new JButton("개인");
		btnProofOfExpenditure=new JButton("사업자");
		btnPayment=new JButton("결제");
		btnGoBack=new JButton("이전화면");
		
		btnStempAccumulate1.addActionListener(this);
		btnStempAccumulate2.addActionListener(this);
		btnCard1.addActionListener(this);
		btnCash1.addActionListener(this);
		btnStemp1.addActionListener(this);
		btnCard2.addActionListener(this);
		btnCash2.addActionListener(this);
		btnStemp2.addActionListener(this);
		btnCashreceipts.addActionListener(this);
		
		btnProofOfExpenditure.addActionListener(this);
		btnReceipts.addActionListener(this);
		btnPayment.addActionListener(this);
		btnGoBack.addActionListener(this);
	}
	public JCafePayment(JCafeMain jc) {//모델 넘겨받고 UI 설정, 전역변수처리
		super(jc,true);
		this.jc = jc;
		model=jc.model;
		for(int i=0;i<jc.tableOrderList.getRowCount();i++)// 주문되는 음료 수량 저장
			coffeeCnt+=Integer.parseInt((String) jc.tableOrderList.getValueAt(i,1));
		init();
		tfPrice.setText(jc.priceTf.getText());
		setSize(300,330);
		
		JPanel pnlSouth=new JPanel();
		JLabel lblTotal=new JLabel("결제금액");
		JLabel lblWon=new JLabel("원");
		
		tfInstallment.setText("1");//처음 카드결제 할부 1개월
		btnPayment.setEnabled(true);
		btnCash1.setBounds(15,25,80,50);
		btnCard1.setBounds(105,25,80,50);
		btnStemp1.setBounds(195,25,80,50);
		btnCashreceipts.setBounds(15,95,80,50);
		btnReceipts.setBounds(105,95,80,50);
		btnProofOfExpenditure.setBounds(195,95,80,50);
		btnStempAccumulate1.setBounds(105,155,80,40);
		lblTotal.setBounds(15,160,100,50);
		lblWon.setBounds(205,197,50,50);
		tfPrice.setBounds(15,205,185,30);
		//현금결재 화면 좌표
		pnlCash.add(btnCash1);
		pnlCash.add(btnCard1);
		pnlCash.add(btnStemp1);
		pnlCash.add(btnReceipts);
		pnlCash.add(btnProofOfExpenditure);
		pnlCash.add(btnCashreceipts);
		pnlCash.add(btnStempAccumulate1);
		pnlCash.add(lblTotal);
		pnlCash.add(lblWon);
		pnlCash.add(tfPrice);
		//현금결재 화면 추가
		JLabel lblInstallment=new JLabel("개월"); 
		JLabel lblCardNumber=new JLabel("카드번호");

		btnCash2.setBounds(15,25,80,50);
		btnCard2.setBounds(105,25,80,50);
		btnStemp2.setBounds(195,25,80,50);
		btnStempAccumulate2.setBounds(105,155,80,40);
		tfInstallment.setBounds(10, 105, 20, 30);
		tfCardNumber.setBounds(10, 205, 250, 30);
		lblInstallment.setBounds(40,105,60,30);
		lblCardNumber.setBounds(15,160,60,30);
		//카드결재 화면 좌표
		pnlCard.add(lblCardNumber);
		pnlCard.add(lblInstallment);
		pnlCard.add(btnCash2);
		pnlCard.add(btnCard2);
		pnlCard.add(btnStemp2);
		pnlCard.add(btnStempAccumulate2);
		pnlCard.add(tfInstallment);
		pnlCard.add(tfCardNumber);
		//카드결재 화면 추가
		pnlSouth.add(btnPayment);
		pnlSouth.add(btnGoBack);
		btnCard1.setBackground(Color.WHITE);
		btnCash1.setBackground(color);
		btnStemp1.setBackground(Color.WHITE);
		btnStemp2.setBackground(Color.WHITE);
		btnProofOfExpenditure.setBackground(Color.WHITE);
		btnReceipts.setBackground(Color.WHITE);
		btnCashreceipts.setBackground(Color.WHITE);
		btnStempAccumulate1.setBackground(Color.white);
		
//		getContentPane().setBackground(colorGreen);
		btnPayment.setBackground(color);
		btnPayment.setPreferredSize(new Dimension(88,28));
		btnGoBack.setBackground(Color.white);
		pnlCard.setBackground(null);
		pnlCash.setBackground(null);
		pnlSouth.setBackground(null);
		
		btnProofOfExpenditure.setEnabled(false);
		btnReceipts.setEnabled(false);
		
		this.add(pnlCash);
		this.add(pnlSouth,"South");
		
		setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	void clickUseStemp(){//스템프사용버튼 클릭 이벤트
		JCafeUseStamp jus=new JCafeUseStamp(this);
		if(jus.save){//스템프사용 완료되면 실행될 조건문
			model=jus.model;
			useStempCnt=jus.useStemp*5;	//사용한 스템프 갯수
			userStemp=jus.stempNum;		//남은 스템프 갯수
			userPhoneNum=jus.userNumber;//사용자 전화번호
			coffeeCnt=jus.coffeeCnt;	//커피 수
		}
	}
	void clickCash(){// 현금버튼 클릭 이벤트
		btnCard1.setBackground(Color.white);
		btnCash1.setBackground(color);
		btnCard2.setBackground(Color.white);
		btnCash2.setBackground(color);
		btnStempAccumulate1.setBackground(Color.white);
		btnStempAccumulate2.setBackground(Color.white);
		this.remove(pnlCard);
		this.add(pnlCash);
		repaint();
		revalidate();
	}
	void clickCard(){// 카드버튼 클릭 이벤트
		btnCard1.setBackground(color);
		btnCard2.setBackground(color);
		
		btnStemp1.setBackground(Color.white);
		btnStempAccumulate1.setBackground(Color.white);
		btnStempAccumulate2.setBackground(Color.white);
		btnCashreceipts.setBackground(Color.white);
		btnProofOfExpenditure.setEnabled(false);
		btnReceipts.setEnabled(false);
		btnProofOfExpenditure.setBackground(Color.white);
		btnReceipts.setBackground(Color.white);
		btnCash1.setBackground(Color.white);
		btnCash2.setBackground(Color.white);
		this.remove(pnlCash);
		this.add(pnlCard);
		repaint();
		revalidate();
	}
	void clickPayment(){// 결제버튼 클릭 이벤트
		JCafePhoneNum jcpn;
		payment=true; // 결제 했는지 안했는지 저장
		if(btnCash1.getBackground()==color)payToCash=true;//현금결재
		else if(btnCard1.getBackground()==color)payToCash=false;//카드결재
			
		if(btnStempAccumulate1.getBackground()==color||btnStempAccumulate2.getBackground()==color) {//스탬프적립?
			jcpn=new JCafePhoneNum(this);
			if(jcpn.confirm) {//결제 했으면
				JCafeSaveSalesData.saveSalesData(model);//판매자료누적
				new JCafeDaySaleData(jc, this);// JCafeDaySaleData폴더에 파일명(당일날짜)로 데이터 저장하는 클래스
				userPhoneNum=jcpn.getPhoneNum();
				new JCafeReceipt(this,payToCash);
			}
		}else if(btnCashreceipts.getBackground()==color) {//현금영수증?
			jcpn=new JCafePhoneNum(this);
			if(jcpn.confirm) {//결제 했으면
				JCafeSaveSalesData.saveSalesData(model);//판매자료누적
				new JCafeDaySaleData(jc, this);// JCafeDaySaleData폴더에 파일명(당일날짜)로 데이터 저장하는 클래스
				new JCafeReceipt(this,payToCash);
			}
		}else {
			JCafeSaveSalesData.saveSalesData(model);//판매자료누적
			new JCafeDaySaleData(jc, this);// JCafeDaySaleData폴더에 파일명(당일날짜)로 데이터 저장하는 클래스
			new JCafeReceipt(this,payToCash);
		}
		
		if(userPhoneNum!=null) {//스탬프적립 했으면 데이터화 시켜주는 조건문
			new JCafeUseStempToSave(userPhoneNum,userStemp);//스탬프 갯수 데이터화(파일에 저장)
			new JCafeStempAdd(userPhoneNum, (coffeeCnt - (useStempCnt/5) ) ); // 스템프 추가 (스탬프로 결제한 커피 수 만큼 스탬프 쌓이지않음)
		}
		
	}
	void clickGoBack(){// 이전화면버튼 클릭 이벤트
		dispose();
		payment=false;
	}
	void clickProofOfExpenditure(){// 현금영수증 사업자버튼 클릭 이벤트
		btnProofOfExpenditure.setBackground(color);
		btnReceipts.setBackground(Color.WHITE);
		repaint();
		revalidate();
	}
	void clickReceipts(){// 현금영수증 개인버튼 클릭 이벤트
		btnProofOfExpenditure.setBackground(Color.WHITE);
		btnReceipts.setBackground(color);
		repaint();
		revalidate();
	}
	void clickCashreceipts() {// 현금영수증 클릭 이벤트
		if(btnCashreceipts.getBackground()==Color.WHITE) {
			btnProofOfExpenditure.setEnabled(true);
			btnReceipts.setEnabled(true);
			btnCashreceipts.setBackground(color);
		}
		else if(btnCashreceipts.getBackground()==color) {
			btnCashreceipts.setBackground(Color.WHITE);
			btnReceipts.setBackground(Color.WHITE);
			btnProofOfExpenditure.setBackground(Color.WHITE);
			btnProofOfExpenditure.setEnabled(false);
			btnReceipts.setEnabled(false);
		}
	}
	void clickBtnStempAccumulate() {//스탬프적립 버튼 클릭 이벤트
		if(btnStempAccumulate1.getBackground()==Color.WHITE||btnStempAccumulate2.getBackground()==Color.WHITE) {
			btnStempAccumulate1.setBackground(color);
			btnStempAccumulate2.setBackground(color);
		}else if(btnStempAccumulate1.getBackground()==color||btnStempAccumulate2.getBackground()==color) {
			btnStempAccumulate1.setBackground(Color.WHITE);
			btnStempAccumulate2.setBackground(Color.WHITE);
		}
		
	}
	
	@Override public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnStemp1||e.getSource()==btnStemp2) clickUseStemp();	//스템프사용 버튼
		if(e.getSource()==btnCash1||e.getSource()==btnCash2) clickCash();	// 현금버튼
		if(e.getSource()==btnCard1||e.getSource()==btnCard2) clickCard();	// 카드버튼
		if(e.getSource()==btnPayment)clickPayment();// 결제버튼
		if(e.getSource()==btnGoBack) clickGoBack(); // 이전화면 버튼
		if(e.getSource()==btnProofOfExpenditure) clickProofOfExpenditure(); // 사업자 지출증빙 버튼
		if(e.getSource()==btnReceipts) clickReceipts(); // 개인 현금영수증 버튼
		if(e.getSource()==btnCashreceipts) clickCashreceipts();//현금영수증 버튼클릭
		if(e.getSource()==btnStempAccumulate1||e.getSource()==btnStempAccumulate2) clickBtnStempAccumulate();//적립버튼
	}
}