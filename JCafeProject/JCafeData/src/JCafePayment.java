import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	void init(){// ������Ʈ �߰�& �̺�Ʈ �߰�
		pnlCard=new JPanel(null);
		pnlCash=new JPanel(null);
		tfInstallment=new JTextField(3);
		tfCardNumber=new JTextField(10);
		btnCard1=new JButton("ī��");
		btnCash1=new JButton("����");
		btnCard2=new JButton("ī��");
		btnCash2=new JButton("����");
		btnStemp1=new JButton("<HTML><center>������<br> ���");
		btnStemp2=new JButton("<HTML><center>������<br> ���");
		btnStempAccumulate1=new JButton("<HTML><center>������<br> ����");
		btnStempAccumulate2=new JButton("<HTML><center>������<br> ����");
		btnCashreceipts=new JButton("<HTML><center>����<br>������");
		
		tfPrice=new JTextField();
		tfPrice.setEnabled(false);

		btnReceipts=new JButton("����");
		btnProofOfExpenditure=new JButton("�����");
		btnPayment=new JButton("����");
		btnGoBack=new JButton("����ȭ��");
		
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
	public JCafePayment(JCafeMain jc) {//�� �Ѱܹް� UI ����, ��������ó��
		super(jc,true);
		this.jc = jc;
		model=jc.model;
		for(int i=0;i<jc.tableOrderList.getRowCount();i++)// �ֹ��Ǵ� ���� ���� ����
			coffeeCnt+=Integer.parseInt((String) jc.tableOrderList.getValueAt(i,1));
		init();
		tfPrice.setText(jc.priceTf.getText());
		setSize(300,330);
		
		JPanel pnlSouth=new JPanel();
		JLabel lblTotal=new JLabel("�����ݾ�");
		JLabel lblWon=new JLabel("��");
		
		tfInstallment.setText("1");//ó�� ī����� �Һ� 1����
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
		//���ݰ��� ȭ�� ��ǥ
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
		//���ݰ��� ȭ�� �߰�
		JLabel lblInstallment=new JLabel("����"); 
		JLabel lblCardNumber=new JLabel("ī���ȣ");

		btnCash2.setBounds(15,25,80,50);
		btnCard2.setBounds(105,25,80,50);
		btnStemp2.setBounds(195,25,80,50);
		btnStempAccumulate2.setBounds(105,155,80,40);
		tfInstallment.setBounds(10, 105, 20, 30);
		tfCardNumber.setBounds(10, 205, 250, 30);
		lblInstallment.setBounds(40,105,60,30);
		lblCardNumber.setBounds(15,160,60,30);
		//ī����� ȭ�� ��ǥ
		pnlCard.add(lblCardNumber);
		pnlCard.add(lblInstallment);
		pnlCard.add(btnCash2);
		pnlCard.add(btnCard2);
		pnlCard.add(btnStemp2);
		pnlCard.add(btnStempAccumulate2);
		pnlCard.add(tfInstallment);
		pnlCard.add(tfCardNumber);
		//ī����� ȭ�� �߰�
		pnlSouth.add(btnPayment);
		pnlSouth.add(btnGoBack);
		btnCard1.setBackground(Color.WHITE);
		btnCash1.setBackground(Color.PINK);
		btnStemp1.setBackground(Color.WHITE);
		btnStemp2.setBackground(Color.WHITE);
		btnProofOfExpenditure.setBackground(Color.WHITE);
		btnReceipts.setBackground(Color.WHITE);
		btnCashreceipts.setBackground(Color.WHITE);
		btnStempAccumulate1.setBackground(Color.white);
		
		btnProofOfExpenditure.setEnabled(false);
		btnReceipts.setEnabled(false);
		
		this.add(pnlCash);
		this.add(pnlSouth,"South");
		
		setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	void clickUseStemp(){//����������ư Ŭ�� �̺�Ʈ
		JCafeUseStamp jus=new JCafeUseStamp(this);
		if(jus.save){//��������� �Ϸ�Ǹ� ����� ���ǹ�
			model=jus.model;
			useStempCnt=jus.useStemp*5;	//����� ������ ����
			userStemp=jus.stempNum;		//���� ������ ����
			userPhoneNum=jus.userNumber;//����� ��ȭ��ȣ
			coffeeCnt=jus.coffeeCnt;	//Ŀ�� ��
		}
	}
	void clickCash(){// ���ݹ�ư Ŭ�� �̺�Ʈ
		btnCard1.setBackground(Color.white);
		btnCash1.setBackground(Color.pink);
		btnCard2.setBackground(Color.white);
		btnCash2.setBackground(Color.pink);
		btnStempAccumulate1.setBackground(Color.white);
		btnStempAccumulate2.setBackground(Color.white);
		this.remove(pnlCard);
		this.add(pnlCash);
		repaint();
		revalidate();
	}
	void clickCard(){// ī���ư Ŭ�� �̺�Ʈ
		btnCard1.setBackground(Color.pink);
		btnCard2.setBackground(Color.pink);
		
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
	void clickPayment(){// ������ư Ŭ�� �̺�Ʈ
		JCafePhoneNum jcpn;
		payment=true; // ���� �ߴ��� ���ߴ��� ����
		if(btnCash1.getBackground()==Color.pink)payToCash=true;//���ݰ���
		else if(btnCard1.getBackground()==Color.pink)payToCash=false;//ī�����
			
		if(btnStempAccumulate1.getBackground()==Color.pink||btnStempAccumulate2.getBackground()==Color.pink) {//����������?
			jcpn=new JCafePhoneNum(this);
			if(jcpn.confirm) {//���� ������
				JCafeSaveSalesData.saveSalesData(model);//�Ǹ��ڷᴩ��
				new JCafeDaySaleData(jc, this);// JCafeDaySaleData������ ���ϸ�(���ϳ�¥)�� ������ �����ϴ� Ŭ����
				userPhoneNum=jcpn.getPhoneNum();
				new JCafeReceipt(this,payToCash);
			}
		}else if(btnCashreceipts.getBackground()==Color.PINK) {//���ݿ�����?
			jcpn=new JCafePhoneNum(this);
			if(jcpn.confirm) {//���� ������
				JCafeSaveSalesData.saveSalesData(model);//�Ǹ��ڷᴩ��
				new JCafeDaySaleData(jc, this);// JCafeDaySaleData������ ���ϸ�(���ϳ�¥)�� ������ �����ϴ� Ŭ����
				new JCafeReceipt(this,payToCash);
			}
		}else {
			JCafeSaveSalesData.saveSalesData(model);//�Ǹ��ڷᴩ��
			new JCafeDaySaleData(jc, this);// JCafeDaySaleData������ ���ϸ�(���ϳ�¥)�� ������ �����ϴ� Ŭ����
			new JCafeReceipt(this,payToCash);
		}
		
		if(userPhoneNum!=null) {//���������� ������ ������ȭ �����ִ� ���ǹ�
			new JCafeUseStempToSave(userPhoneNum,userStemp);//������ ���� ������ȭ(���Ͽ� ����)
			new JCafeStempAdd(userPhoneNum, (coffeeCnt - (useStempCnt/5) ) ); // ������ �߰� (�������� ������ Ŀ�� �� ��ŭ ������ ����������)
		}
		
	}
	void clickGoBack(){// ����ȭ���ư Ŭ�� �̺�Ʈ
		dispose();
		payment=false;
	}
	void clickProofOfExpenditure(){// ���ݿ����� ����ڹ�ư Ŭ�� �̺�Ʈ
		btnProofOfExpenditure.setBackground(Color.pink);
		btnReceipts.setBackground(Color.WHITE);
		repaint();
		revalidate();
	}
	void clickReceipts(){// ���ݿ����� ���ι�ư Ŭ�� �̺�Ʈ
		btnProofOfExpenditure.setBackground(Color.WHITE);
		btnReceipts.setBackground(Color.pink);
		repaint();
		revalidate();
	}
	void clickCashreceipts() {// ���ݿ����� Ŭ�� �̺�Ʈ
		if(btnCashreceipts.getBackground()==Color.WHITE) {
			btnProofOfExpenditure.setEnabled(true);
			btnReceipts.setEnabled(true);
			btnCashreceipts.setBackground(Color.PINK);
		}
		else if(btnCashreceipts.getBackground()==Color.PINK) {
			btnCashreceipts.setBackground(Color.WHITE);
			btnReceipts.setBackground(Color.WHITE);
			btnProofOfExpenditure.setBackground(Color.WHITE);
			btnProofOfExpenditure.setEnabled(false);
			btnReceipts.setEnabled(false);
		}
	}
	void clickBtnStempAccumulate() {//���������� ��ư Ŭ�� �̺�Ʈ
		if(btnStempAccumulate1.getBackground()==Color.WHITE||btnStempAccumulate2.getBackground()==Color.WHITE) {
			btnStempAccumulate1.setBackground(Color.pink);
			btnStempAccumulate2.setBackground(Color.pink);
		}else if(btnStempAccumulate1.getBackground()==Color.pink||btnStempAccumulate2.getBackground()==Color.pink) {
			btnStempAccumulate1.setBackground(Color.WHITE);
			btnStempAccumulate2.setBackground(Color.WHITE);
		}
		
	}
	
	@Override public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnStemp1||e.getSource()==btnStemp2) clickUseStemp();	//��������� ��ư
		if(e.getSource()==btnCash1||e.getSource()==btnCash2) clickCash();	// ���ݹ�ư
		if(e.getSource()==btnCard1||e.getSource()==btnCard2) clickCard();	// ī���ư
		if(e.getSource()==btnPayment)clickPayment();// ������ư
		if(e.getSource()==btnGoBack) clickGoBack(); // ����ȭ�� ��ư
		if(e.getSource()==btnProofOfExpenditure) clickProofOfExpenditure(); // ����� �������� ��ư
		if(e.getSource()==btnReceipts) clickReceipts(); // ���� ���ݿ����� ��ư
		if(e.getSource()==btnCashreceipts) clickCashreceipts();//���ݿ����� ��ưŬ��
		if(e.getSource()==btnStempAccumulate1||e.getSource()==btnStempAccumulate2) clickBtnStempAccumulate();//������ư
	}
}