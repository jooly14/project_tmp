import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	// ����� ������ ����/ ���� ������ ����/������ �߰��� Ŀ�� ��
	String userPhoneNum;
	boolean payment;
	boolean payToCash;
	JCafeMain jc;
	Color color = new Color(0xcbe2a8);
//	Color colorGreen = new Color(0x003E00);
	
	void init(){// ������Ʈ �߰�& �̺�Ʈ �߰�
		this.setIconImage(new ImageIcon("JCafeData\\ImageData\\������.png").getImage());
		pnlCard=new JPanel(null);
		pnlCash=new JPanel(null);
		tfInstallment=new JTextField(3);
		tfInstallment.addKeyListener(new KeyAdapter() {
			@Override public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
					return;
				}
			}
		});
		tfCardNumber=new JTextField(10);
		tfCardNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
					return;
				}
			}
		});
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
	
	void clickUseStemp(){//����������ư Ŭ�� �̺�Ʈ
		JCafeUseStamp jus=new JCafeUseStamp(this);
		if(jus.save){//��������� �Ϸ�Ǹ� ����� ���ǹ�
			model=jus.model;
			useStempCnt=jus.useStemp+jus.useStemp*5;	//���� ����� ������ ����+����Ͽ� ������ Ŀ�Ǹ�ŭ ������ ������ �ȵ�
			userStemp=jus.stempNum;		//���� ������ ����
			userPhoneNum=jus.userNumber;//����� ��ȭ��ȣ
			btnStempAccumulate1.setBackground(color);
			btnStempAccumulate2.setBackground(color);
		}
	}
	void clickCash(){// ���ݹ�ư Ŭ�� �̺�Ʈ
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
	void clickCard(){// ī���ư Ŭ�� �̺�Ʈ
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
	void clickPayment(){// ������ư Ŭ�� �̺�Ʈ
		JCafePhoneNum jcpn;
		payment=true; // ���� �ߴ��� ���ߴ��� ����
		if(btnCash1.getBackground()==color)payToCash=true;//���ݰ���
		else if(btnCard1.getBackground()==color)payToCash=false;//ī�����
			
		if(btnStempAccumulate1.getBackground()==color||btnStempAccumulate2.getBackground()==color) {//����������?
			jcpn=new JCafePhoneNum(this);
			if(jcpn.confirm) {//������ ture��
				userPhoneNum=jcpn.getPhoneNum();
				JCafeSaveSalesData.saveSalesData(model);//�Ǹ��ڷᴩ��
				new JCafeDaySaleData(jc, this);// JCafeDaySaleData������ ���ϸ�(���ϳ�¥)�� ������ �����ϴ� Ŭ����
				JCafeStempAdd jcsa=new JCafeStempAdd(jcpn.getPhoneNum(), (coffeeCnt - (useStempCnt) ) ); // �����Ŀ��- (�������� ������ Ŀ�Ǵ� ����X)
				userStemp=jcsa.userStemp;
				new JCafeReceipt(this,payToCash);
			}
		}else if(btnCashreceipts.getBackground()==color) {//���ݿ�����?
			jcpn=new JCafePhoneNum(this);
			if(jcpn.confirm) {//���� ������
				userPhoneNum=jcpn.getPhoneNum();
				JCafeSaveSalesData.saveSalesData(model);//�Ǹ��ڷᴩ��
				new JCafeDaySaleData(jc, this);// JCafeDaySaleData������ ���ϸ�(���ϳ�¥)�� ������ �����ϴ� Ŭ����
				new JCafeReceipt(this,payToCash);
			}
		}else {
			JCafeSaveSalesData.saveSalesData(model);//�Ǹ��ڷᴩ��
			new JCafeDaySaleData(jc, this);// JCafeDaySaleData������ ���ϸ�(���ϳ�¥)�� ������ �����ϴ� Ŭ����
			new JCafeReceipt(this,payToCash);
		}
		
	}
	void clickGoBack(){// ����ȭ���ư Ŭ�� �̺�Ʈ
		dispose();
		payment=false;
	}
	void clickProofOfExpenditure(){// ���ݿ����� ����ڹ�ư Ŭ�� �̺�Ʈ
		btnProofOfExpenditure.setBackground(color);
		btnReceipts.setBackground(Color.WHITE);
		repaint();
		revalidate();
	}
	void clickReceipts(){// ���ݿ����� ���ι�ư Ŭ�� �̺�Ʈ
		btnProofOfExpenditure.setBackground(Color.WHITE);
		btnReceipts.setBackground(color);
		repaint();
		revalidate();
	}
	void clickCashreceipts() {// ���ݿ����� Ŭ�� �̺�Ʈ
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
	void clickBtnStempAccumulate() {//���������� ��ư Ŭ�� �̺�Ʈ
		if(btnStempAccumulate1.getBackground()==Color.WHITE||btnStempAccumulate2.getBackground()==Color.WHITE) {
			btnStempAccumulate1.setBackground(color);
			btnStempAccumulate2.setBackground(color);
		}else if(btnStempAccumulate1.getBackground()==color||btnStempAccumulate2.getBackground()==color) {
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