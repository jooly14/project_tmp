import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JCafeMain extends JFrame implements ActionListener{
	JButton[] btnMenu;
	JPanel pnlMenu,pnlNorth,pnlSouth,pnlSouthRight,pnlTable,pnlCategory,totalPnl;
	String[][] strMenu2 = JCafeGetMenuToMakeButton.getInfo("coffee");
	String[] strMenu = new String[strMenu2.length];
	String[] strCnt = new String[strMenu2.length];
	String[] strPrice = new String[strMenu2.length];
	JPanel pnlMain;
	JButton btnManager,btnCancel,btnPayment,btnStemp;
	JTable tableOrderList;
	String[][]strOrderList;
	String[]strOrderListHead={"�̸�","����","�ݾ�"};
	DefaultTableModel model;
	JScrollPane spOrderList;
	boolean loginChk;
	JButton[] categoryBtns = new JButton[5];
	String[] categoryTitle = {"COFFEE","LATTE","TEA","",""};	
	JTextField totalTf, cntTf, priceTf;//�հ�, �ֹ�����, �� �ݾ�
	
	void init(){
		setSize(600,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnMenu=new JButton[25];
		JCafeGetMenuToMakeButton.setData(strMenu2,strMenu,strCnt,strPrice);
		btnManager=new JButton("������");
		btnCancel=new JButton("���");		
		btnPayment=new JButton("����");
		btnStemp=new JButton("������");
		
		pnlMenu=new JPanel();
		
		model=new DefaultTableModel(strOrderList, strOrderListHead)
		{
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};//���̺� �����ȵǰ� �ϴ°�
		for(int i=0;i<categoryTitle.length;i++){
			categoryBtns[i] = new JButton(categoryTitle[i]);
			categoryBtns[i].addActionListener(this);
		}
		
		tableOrderList=new JTable(model);
		spOrderList=new JScrollPane(tableOrderList);
		for(int i=0;i<25;i++){
			if(strMenu.length>i){
				btnMenu[i]=new JButton();
				btnMenu[i].addActionListener(this);
				btnMenu[i].setMargin(new java.awt.Insets(0, 0, 0, 0));
				setBtnName(strMenu[i],btnMenu[i]);
			}
			 else
				btnMenu[i]=new JButton();
		}
		
		totalTf = new JTextField("�հ�");
		cntTf  = new JTextField("0");
		priceTf = new JTextField("0");
		totalTf.setEditable(false);
		cntTf.setEditable(false);
		priceTf.setEditable(false);
		
	}
	public JCafeMain() {
		init();
		
		pnlNorth=new JPanel();
		pnlMenu=new JPanel(new GridLayout(0,5,2,2));
		pnlSouth=new JPanel(new GridLayout(0,2));
		
		btnStemp.addActionListener(this);
		btnManager.addActionListener(this);
		btnCancel.addActionListener(this);
		btnPayment.addActionListener(this);
		
		totalPnl = new JPanel(new GridLayout(0,3));
		totalPnl.add(totalTf);
		totalPnl.add(cntTf);
		totalPnl.add(priceTf);
		
		pnlSouthRight=new JPanel(new GridLayout(2,2,5,5));
		pnlSouthRight.add(btnManager);
		pnlSouthRight.add(btnStemp);
		pnlSouthRight.add(btnPayment);
		pnlSouthRight.add(btnCancel);
		pnlTable=new JPanel(new BorderLayout());
		pnlTable.add(spOrderList);
		pnlTable.add(totalPnl, "South");
		pnlSouth.add(pnlTable);
		pnlSouth.add(pnlSouthRight);
		
		for(int i=0;i<25;i++)
			pnlMenu.add(btnMenu[i]);
		JLabel lblMain=new JLabel(new ImageIcon("JCafeData/ImageData/JCafe logo.PNG"));
		pnlNorth.add(lblMain);
		//600 x 800������
		this.setLayout(null);
		pnlNorth.setBounds(10,10,560,100);
		this.add(pnlNorth);
		
		pnlMenu.setBounds(10, 170, 560, 350);		
		this.add(pnlMenu);
		pnlSouth.setBounds(10,530,560,220);
		this.add(pnlSouth);
		
		pnlCategory = new JPanel(new GridLayout(1,0));
		for(int i=0;i<categoryBtns.length;i++){
			if(!(categoryBtns[i].getText().equals(""))){
				categoryBtns[i].setBackground(Color.white);
				pnlCategory.add(categoryBtns[i]);
			}
		}
		pnlCategory.setBounds(10,130,560,30);
		
		this.add(pnlCategory);
		setUseColorMain();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	void setUseColorMain(){
		Color color=new Color(0x003E00);
		pnlMenu.setBackground(color);
		pnlSouth.setBackground(color);
		pnlNorth.setBackground(color);
		pnlCategory.setBackground(color);
		totalPnl.setBackground(color);
		pnlSouthRight.setBackground(color);
		pnlTable.setBackground(color);
		spOrderList.getViewport().setBackground(Color.WHITE);//table ���� ���� 
		this.getContentPane().setBackground(color);
	}
	void logIn(){loginChk = true;}
	void logOut(){loginChk = false;}
	void clickMenu(ActionEvent e) {//�޴�Ŭ�� �̺�Ʈ
		int currentRow = -1;
		int intCnt = 1;
		for (int j = 0; j < strMenu.length; j++) {
			if (e.getSource() == btnMenu[j]) {
				for (int i = 0; i < tableOrderList.getRowCount(); i++) {
					if (tableOrderList.getValueAt(i, 0).equals(strMenu[j])) {
						currentRow = i;
						break;
					}
				} // �̹� �ִ��� Ȯ��! ������ �ִ� ���� ��ġ�� currentRow�� ����

				if (currentRow != -1) { // �����̶� �ִٸ�
					String cnt = (Integer.parseInt((String) tableOrderList.getValueAt(currentRow, 1)) + 1) + ""; // ����
					String price = (Integer.parseInt((String) tableOrderList.getValueAt(currentRow, 2))
							+ (Integer.parseInt((String) strPrice[j]))) + ""; // ����
					tableOrderList.setValueAt(cnt, currentRow, 1);
					tableOrderList.setValueAt(price, currentRow, 2);
				} else {
					String[] menu = { strMenu[j], strCnt[j], strPrice[j] };
					model.addRow(menu);
				}
			}
		}
		printTotalPrice();
	}
	void clickMenuTab(ActionEvent e) {//�޴� �� Ŭ�� �̺�Ʈ
		String fileNametoMakeBtn = null;
		if(((JButton)e.getSource()).getText().equals("COFFEE")){
			fileNametoMakeBtn = "coffee";
		}else if(((JButton)e.getSource()).getText().equals("LATTE")){
			fileNametoMakeBtn = "latte";
		}else if(((JButton)e.getSource()).getText().equals("TEA")){
			fileNametoMakeBtn = "tea";
		}
		strMenu2 = JCafeGetMenuToMakeButton.getInfo(fileNametoMakeBtn);
		strMenu = new String[strMenu2.length];
		strCnt = new String[strMenu2.length];
		strPrice = new String[strMenu2.length];
		JCafeGetMenuToMakeButton.setData(strMenu2, strMenu, strCnt, strPrice);
		pnlMenu.removeAll();
		
		for (int i = 0; i < 25; i++) {
			if (strMenu.length > i) {
				btnMenu[i] = new JButton();
				btnMenu[i].addActionListener(this);
				btnMenu[i].setMargin(new java.awt.Insets(0, 0, 0, 0));
				setBtnName(strMenu[i],btnMenu[i]);
				pnlMenu.add(btnMenu[i]);
			} else
				btnMenu[i] = new JButton();
				pnlMenu.add(btnMenu[i]);
		}
		pnlMenu.setLayout(new GridLayout(0, 5, 2, 2));
		pnlMenu.setBounds(10, 170, 560, 350);
	}
	//��ư �޴��� �߸��� ���� �ذ��� �޼��� -
	void setBtnName(String menuName, JButton btn){
		int index1 = -1;
		int index2 = -1;
		String btnNameStr = "";
		if(menuName.length()>2){
			index1 = menuName.indexOf(" ",3);
		}
		if(menuName.length()>6){
			index2 = menuName.indexOf(" ",index1+1);
		}
		if(index1==-1&&index2==-1){
			btnNameStr = menuName;
		}else if(index2==-1){
			btnNameStr = menuName.substring(0, index1) +
		"<br>"+ menuName.substring(index1+1);
		}else{
			btnNameStr = menuName.substring(0, index1) +
					"<br>"+ menuName.substring(index1+1,index2)+
					menuName.substring(index2+1);
		}
		btn.setText("<html><center>"+btnNameStr+"</html>");
	}
	void selRowDelete(){//��� Ŭ�� �̺�Ʈ
		boolean selected=false;
		
		for(int i = 0; i < tableOrderList.getRowCount(); i++){
			if(tableOrderList.isRowSelected(i)) selected=true;
		}
		if(selected) {
			model.removeRow(tableOrderList.getSelectedRow());
			printTotalPrice();
		}else {
			model.setNumRows(0);
			ResettotalPrice();
		}
		
	}
	void printTotalPrice() {//�հ� �ݾ� ���
		int cntInt=0;
		int priceInt=0;
		for(int i=0;i<tableOrderList.getRowCount();i++) {
			cntInt=cntInt+(Integer.parseInt((String)tableOrderList.getValueAt(i,1)));
			priceInt=priceInt+(Integer.parseInt((String)tableOrderList.getValueAt(i,2)));
		}
		cntTf.setText(cntInt+"");
		priceTf.setText(priceInt+"");
	}
	void clickManager(){//������ Ŭ�� �̺�Ʈ
		if(loginChk==false){
			new JCafeManagerLoginDialog(this);
		}else{
			new JCafeManagerMenu(this);
		}
	}
	void ResettotalPrice() {//�հ� �ݾ� �ʱ�ȭ
		cntTf.setText("0");
		priceTf.setText("0");
	}
	void clickPayment(){ //���� Ŭ�� �̺�Ʈ
		if(tableOrderList.getRowCount()!=0) {
			JCafePayment jcp=new JCafePayment(this);
			if(jcp.payment){
				model.setNumRows(0);
				ResettotalPrice();
			}
		}
	}

	
	@Override public void actionPerformed(ActionEvent e) {
		if	   (e.getSource()==btnPayment) 		clickPayment();//����
		else if(e.getSource()==btnManager)		clickManager();//������
		else if(e.getSource()==btnStemp) new JCafeStempTable(this); // ������
		else if(e.getSource()==btnCancel) 		selRowDelete(); // ���
		else if(((JButton)(e.getSource())).getParent().equals(pnlCategory)) clickMenuTab(e);//�޴��� 
		else clickMenu(e);// �޴�Ŭ��
		repaint();
		revalidate();
	}
	
	public static void main(String[] args) {
		new JCafeMain();
		}
}