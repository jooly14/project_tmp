import java.awt.Color;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class JCafeReceipt extends JFrame{
	JPanel pnlCenter; 
	int stempInfo;
	String userNum;
	String strThisTime;
	String strCardNum,strInstallment;
	void init(String[][]order,boolean cash) {
		int row=order.length;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pnlNorth=new JPanel();
		pnlNorth.add(new JLabel("[영  수  증]"));
		this.add(pnlNorth,"North");
		pnlCenter=new JPanel(null);
		
		long time= System.currentTimeMillis();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd              [매출시간]  kk:mm:ss");
		strThisTime=sdf.format(time);
		
		JLabel lblTime=new JLabel(strThisTime);
		JLabel lblBasicInfo=new JLabel("<HTML>[매장명] J-Cafe 서면점<br>[사업자번호] 196-73-00438<br>[주   소] 부산시 부산진구 전포동 전포대로 808번길 97<br>[대표자] 김경태                    [TEL] 051-826-7056<br>[매출일]");
		JLabel lblHeader1=new JLabel("==========================================");
		JLabel lblHeader2=new JLabel("   상     품     명                       단  가         수량        금  액");
		JLabel lblHeader3=new JLabel("-------------------------------------------------------------------------");
		
		ProductsPrint(order, row); //제품정보 출력
		
		JLabel lblTotal1=new JLabel("-------------------------------------------------------------------------");
		JLabel lblTotal2=new JLabel(" 합              계  ");
		JLabel lblTotalPrice=new JLabel(getTotalPrice(order));//합계금액
		JLabel lblStemp=new JLabel(" 누적   스템프");
		JLabel lblStempInfo=new JLabel(stempInfo+"  개");
		JLabel lblTotal3=new JLabel("-------------------------------------------------------------------------");
		
		//Label 생성
		int width=300;

		lblTime.setBounds(60,79,width,20);//현재시간 출력
		lblBasicInfo.setBounds(10,5,width, 90);
		lblHeader1.setBounds(10,100,width,20);//===
		lblHeader2.setBounds(10,115,width,20);//상품명
		lblHeader3.setBounds(10,130,width,20);//-----
		
		lblTotal1.setBounds(10, 145+(row*15), width, 20);//------- 
		lblTotal2.setBounds(10, 160+(row*15), width, 20);//합계
		lblStemp.setBounds(10, 180+(row*15), width, 20);//스템프
		lblTotalPrice.setBounds(265, 160+(row*15), width, 20);//합계금액
		lblStempInfo.setBounds(265, 180+(row*15), width, 20);//스템프
		lblTotal3.setBounds(10, 190+(row*15), width, 20);//-------
		//Label 좌표
		
		pnlCenter.add(lblStempInfo);
		pnlCenter.add(lblStemp);
		pnlCenter.add(lblTime);
		pnlCenter.add(lblBasicInfo);
		pnlCenter.add(lblHeader1);//===
		pnlCenter.add(lblHeader2);//상품명
		pnlCenter.add(lblHeader3);//===
		
		pnlCenter.add(lblTotal1);//-------
		pnlCenter.add(lblTotal2);//합       계
		pnlCenter.add(lblTotalPrice);//합계 금액 
		pnlCenter.add(lblTotal3);//-------
		if(cash) {
			payToCash(order,row);
			setSize(335,(485+row*15));
		}else {
			payToCard(order,row);
			setSize(335,(580+row*15));
		}
		//패널에 영수증내용 추가
		this.add(pnlCenter);
		this.setVisible(true);
	}
	public JCafeReceipt(JCafePayment jcp,boolean cash) {//<현금계산이면 cash=true, 카드계산이면cash=false
		String[][]order=stringToModel(jcp.model);
		stempInfo(jcp);//stempInfo에 결제 후 누적스탬프 저장
		userNum=jcp.userPhoneNum;//전화번호
		strCardNum=jcp.tfCardNumber.getText();//카드번호
		strInstallment=jcp.tfInstallment.getText();//할부개월수
		
		init(order,cash);
	}

	String[][] stringToModel(DefaultTableModel model) {//모델에 들어있는 값을 String 2중배열로 바꿔서 반환
		 int x=model.getRowCount();
		 int y=model.getColumnCount();
		 
		 String[][]str=new String[x][y+1];
		 	for(int i=0;i<x;i++) {
		 		str[i][0]=(String) model.getValueAt(i, 0);
		 		str[i][1]=""+(Integer.parseInt((String)model.getValueAt(i, 2)))/(Integer.parseInt((String)model.getValueAt(i, 1)));
		 		str[i][2]=(String) model.getValueAt(i, 1);
		 		str[i][3]=(String) model.getValueAt(i, 2);
		 	}
		 return str;
	}
	String getTotalPrice(String [][]str) {//총 금액 얼만지 반환
			int totalPrice=0;
			for(int i=0;i<str.length;i++)
				totalPrice+=Integer.parseInt(str[i][3]);
			
		return totalPrice+"";
	}
	void ProductsPrint(String[][]order,int row) {//결제한 제품들 출력
		JLabel[][] lblProductsInfo=new JLabel[order.length][order[0].length];
		for(int i=0;i<order.length;i++) {
			for(int j=0;j<4;j++) {
				lblProductsInfo[i][j]=new JLabel(order[i][j]);// 배열 전부 생성
				pnlCenter.add(lblProductsInfo[i][j]);
			}
			lblProductsInfo[i][0].setBounds(10, 130+((i+1)*15), 300, 20);
			lblProductsInfo[i][1].setBounds(155, 130+((i+1)*15), 300, 20);
			lblProductsInfo[i][2].setBounds(225, 130+((i+1)*15), 300, 20);
			lblProductsInfo[i][3].setBounds(265, 130+((i+1)*15), 300, 20);
		}
	}
	void payToCash(String[][]order,int row) {//현금계산출력
		int width=300;
		JLabel lblReceiptsHead=new JLabel("현금영수증(소득공제)");
		JLabel lblReceipts=new JLabel("<HTML>거래자번호<br>승인 번호<br>국세청문의 http://현금영수증.kr에 등록! 문의: 126<br>-------------------------------------------------------------------------<br>학교폭력예방은 작은 관심에서 시작됩니다.<br>학교폭력 신고는 117 - 부산지방경찰청<br><br>*물품 반품시 본 영수증을 필히 지참하여 주시기 바랍니다.<br>");
		if(userNum!=null) {
			JLabel lblPhoneNum=new JLabel(""+userNum);//현금영수증 번호
			lblPhoneNum.setBounds(80,275,300,20);
			pnlCenter.add(lblPhoneNum);
		}
		
		lblReceiptsHead.setBounds(100, 210+(row*15), width, 20);
		lblReceipts.setBounds(10, 225+(row*15), width, 200);
		
		
		pnlCenter.add(lblReceiptsHead);
		pnlCenter.add(lblReceipts);
	}
	void payToCard(String[][]order,int row) {//카드계산출력
		int width=300;
		JLabel lblReceiptsHead=new JLabel("*** 신용승인정보(고객용) ***");
		JLabel lblReceipts=new JLabel("<HTML>카 드 종 류:<br>카 드 번 호:<br>유 효 기 간:<br>할 부 개 월:<br>판 매 금 액:<br>부   가   세:<br>승 인 금 액:<br>승 인 번 호:<br>승 인 일 시:<br>-------------------------------------------------------------------------<br>학교폭력예방은 작은 관심에서 시작됩니다.<br>학교폭력 신고는 117 - 부산지방경찰청<br><br>*물품 반품시 본 영수증을 필히 지참하여 주시기 바랍니다.<br>");
		JLabel lblCardCompany=new JLabel("BNK(부산은행)");
		JLabel lblCardNum=new JLabel(strCardNum);
		
		JLabel lblInstallment=new JLabel(strInstallment);
		JLabel lblSalePrice=new JLabel((Integer.parseInt(getTotalPrice(order))/10*9)+" 원");
		JLabel lblVAT=new JLabel((Integer.parseInt(getTotalPrice(order))/10)+"   원");
		JLabel lblApprovalNumber=new JLabel(getTotalPrice(order)+" 원");
		
		long time= System.currentTimeMillis();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd                     kk:mm:ss");
		
		JLabel lblThisTime=new JLabel(sdf.format(time));
		
		lblReceiptsHead.setBounds(75, 210+(row*15), width, 20);
		lblCardCompany.setBounds(90,230+(row*15),width,20);
		lblCardNum.setBounds(90,248+(row*15),width,20);
		
		lblInstallment.setBounds(90,285+(row*15),width,20);
		lblSalePrice.setBounds(90,302+(row*15),width,20);
		lblVAT.setBounds(90,321+(row*15),width,20);
		lblApprovalNumber.setBounds(90,339+(row*15),width,20);
		
		lblThisTime.setBounds(90,375+(row*15),width,20);
		
		lblReceipts.setBounds(10, 225+(row*15), width, 280);

		pnlCenter.add(lblApprovalNumber);
		pnlCenter.add(lblCardCompany);
		pnlCenter.add(lblCardNum);
		pnlCenter.add(lblInstallment);
		pnlCenter.add(lblSalePrice);
		pnlCenter.add(lblVAT);
		pnlCenter.add(lblThisTime);
		pnlCenter.add(lblReceiptsHead);
		pnlCenter.add(lblReceipts);
	}
	void stempInfo(JCafePayment jcp) {//결제하고 난 후 누적된 스탬프 몇갠지? stempInfo에 저장
		if(jcp.btnStempAccumulate1.getBackground()==Color.PINK||jcp.btnStempAccumulate2.getBackground()==Color.PINK)
			stempInfo=jcp.userStemp-jcp.useStempCnt+jcp.coffeeCnt-jcp.useStempCnt;
	}
}