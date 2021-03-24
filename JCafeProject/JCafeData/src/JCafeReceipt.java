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
		pnlNorth.add(new JLabel("[��  ��  ��]"));
		this.add(pnlNorth,"North");
		pnlCenter=new JPanel(null);
		
		long time= System.currentTimeMillis();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd              [����ð�]  kk:mm:ss");
		strThisTime=sdf.format(time);
		
		JLabel lblTime=new JLabel(strThisTime);
		JLabel lblBasicInfo=new JLabel("<HTML>[�����] J-Cafe ������<br>[����ڹ�ȣ] 196-73-00438<br>[��   ��] �λ�� �λ����� ������ ������� 808���� 97<br>[��ǥ��] �����                    [TEL] 051-826-7056<br>[������]");
		JLabel lblHeader1=new JLabel("==========================================");
		JLabel lblHeader2=new JLabel("   ��     ǰ     ��                       ��  ��         ����        ��  ��");
		JLabel lblHeader3=new JLabel("-------------------------------------------------------------------------");
		
		ProductsPrint(order, row); //��ǰ���� ���
		
		JLabel lblTotal1=new JLabel("-------------------------------------------------------------------------");
		JLabel lblTotal2=new JLabel(" ��              ��  ");
		JLabel lblTotalPrice=new JLabel(getTotalPrice(order));//�հ�ݾ�
		JLabel lblStemp=new JLabel(" ����   ������");
		JLabel lblStempInfo=new JLabel(stempInfo+"  ��");
		JLabel lblTotal3=new JLabel("-------------------------------------------------------------------------");
		
		//Label ����
		int width=300;

		lblTime.setBounds(60,79,width,20);//����ð� ���
		lblBasicInfo.setBounds(10,5,width, 90);
		lblHeader1.setBounds(10,100,width,20);//===
		lblHeader2.setBounds(10,115,width,20);//��ǰ��
		lblHeader3.setBounds(10,130,width,20);//-----
		
		lblTotal1.setBounds(10, 145+(row*15), width, 20);//------- 
		lblTotal2.setBounds(10, 160+(row*15), width, 20);//�հ�
		lblStemp.setBounds(10, 180+(row*15), width, 20);//������
		lblTotalPrice.setBounds(265, 160+(row*15), width, 20);//�հ�ݾ�
		lblStempInfo.setBounds(265, 180+(row*15), width, 20);//������
		lblTotal3.setBounds(10, 190+(row*15), width, 20);//-------
		//Label ��ǥ
		
		pnlCenter.add(lblStempInfo);
		pnlCenter.add(lblStemp);
		pnlCenter.add(lblTime);
		pnlCenter.add(lblBasicInfo);
		pnlCenter.add(lblHeader1);//===
		pnlCenter.add(lblHeader2);//��ǰ��
		pnlCenter.add(lblHeader3);//===
		
		pnlCenter.add(lblTotal1);//-------
		pnlCenter.add(lblTotal2);//��       ��
		pnlCenter.add(lblTotalPrice);//�հ� �ݾ� 
		pnlCenter.add(lblTotal3);//-------
		if(cash) {
			payToCash(order,row);
			setSize(335,(485+row*15));
		}else {
			payToCard(order,row);
			setSize(335,(580+row*15));
		}
		//�гο� ���������� �߰�
		this.add(pnlCenter);
		this.setVisible(true);
	}
	public JCafeReceipt(JCafePayment jcp,boolean cash) {//<���ݰ���̸� cash=true, ī�����̸�cash=false
		String[][]order=stringToModel(jcp.model);
		stempInfo(jcp);//stempInfo�� ���� �� ���������� ����
		userNum=jcp.userPhoneNum;//��ȭ��ȣ
		strCardNum=jcp.tfCardNumber.getText();//ī���ȣ
		strInstallment=jcp.tfInstallment.getText();//�Һΰ�����
		
		init(order,cash);
	}

	String[][] stringToModel(DefaultTableModel model) {//�𵨿� ����ִ� ���� String 2�߹迭�� �ٲ㼭 ��ȯ
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
	String getTotalPrice(String [][]str) {//�� �ݾ� ���� ��ȯ
			int totalPrice=0;
			for(int i=0;i<str.length;i++)
				totalPrice+=Integer.parseInt(str[i][3]);
			
		return totalPrice+"";
	}
	void ProductsPrint(String[][]order,int row) {//������ ��ǰ�� ���
		JLabel[][] lblProductsInfo=new JLabel[order.length][order[0].length];
		for(int i=0;i<order.length;i++) {
			for(int j=0;j<4;j++) {
				lblProductsInfo[i][j]=new JLabel(order[i][j]);// �迭 ���� ����
				pnlCenter.add(lblProductsInfo[i][j]);
			}
			lblProductsInfo[i][0].setBounds(10, 130+((i+1)*15), 300, 20);
			lblProductsInfo[i][1].setBounds(155, 130+((i+1)*15), 300, 20);
			lblProductsInfo[i][2].setBounds(225, 130+((i+1)*15), 300, 20);
			lblProductsInfo[i][3].setBounds(265, 130+((i+1)*15), 300, 20);
		}
	}
	void payToCash(String[][]order,int row) {//���ݰ�����
		int width=300;
		JLabel lblReceiptsHead=new JLabel("���ݿ�����(�ҵ����)");
		JLabel lblReceipts=new JLabel("<HTML>�ŷ��ڹ�ȣ<br>���� ��ȣ<br>����û���� http://���ݿ�����.kr�� ���! ����: 126<br>-------------------------------------------------------------------------<br>�б����¿����� ���� ���ɿ��� ���۵˴ϴ�.<br>�б����� �Ű�� 117 - �λ��������û<br><br>*��ǰ ��ǰ�� �� �������� ���� �����Ͽ� �ֽñ� �ٶ��ϴ�.<br>");
		if(userNum!=null) {
			JLabel lblPhoneNum=new JLabel(""+userNum);//���ݿ����� ��ȣ
			lblPhoneNum.setBounds(80,275,300,20);
			pnlCenter.add(lblPhoneNum);
		}
		
		lblReceiptsHead.setBounds(100, 210+(row*15), width, 20);
		lblReceipts.setBounds(10, 225+(row*15), width, 200);
		
		
		pnlCenter.add(lblReceiptsHead);
		pnlCenter.add(lblReceipts);
	}
	void payToCard(String[][]order,int row) {//ī�������
		int width=300;
		JLabel lblReceiptsHead=new JLabel("*** �ſ��������(����) ***");
		JLabel lblReceipts=new JLabel("<HTML>ī �� �� ��:<br>ī �� �� ȣ:<br>�� ȿ �� ��:<br>�� �� �� ��:<br>�� �� �� ��:<br>��   ��   ��:<br>�� �� �� ��:<br>�� �� �� ȣ:<br>�� �� �� ��:<br>-------------------------------------------------------------------------<br>�б����¿����� ���� ���ɿ��� ���۵˴ϴ�.<br>�б����� �Ű�� 117 - �λ��������û<br><br>*��ǰ ��ǰ�� �� �������� ���� �����Ͽ� �ֽñ� �ٶ��ϴ�.<br>");
		JLabel lblCardCompany=new JLabel("BNK(�λ�����)");
		JLabel lblCardNum=new JLabel(strCardNum);
		
		JLabel lblInstallment=new JLabel(strInstallment);
		JLabel lblSalePrice=new JLabel((Integer.parseInt(getTotalPrice(order))/10*9)+" ��");
		JLabel lblVAT=new JLabel((Integer.parseInt(getTotalPrice(order))/10)+"   ��");
		JLabel lblApprovalNumber=new JLabel(getTotalPrice(order)+" ��");
		
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
	void stempInfo(JCafePayment jcp) {//�����ϰ� �� �� ������ ������ ���? stempInfo�� ����
		if(jcp.btnStempAccumulate1.getBackground()==Color.PINK||jcp.btnStempAccumulate2.getBackground()==Color.PINK)
			stempInfo=jcp.userStemp-jcp.useStempCnt+jcp.coffeeCnt-jcp.useStempCnt;
	}
}