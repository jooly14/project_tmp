import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JCafeManagerMenu extends JDialog implements ActionListener{
	JButton btnLogOut, btnStempTable, btnPaymentData, btnDeadLineRegistration;
			//�α׾ƿ� 	   /����������		/��������		 /�������
	JCafeMain mainFrame;
	
	public JCafeManagerMenu(JCafeMain mainFrame){
		this.mainFrame = mainFrame;
		this.setLocation(mainFrame.getX(),mainFrame.getY());	//ȭ�� ��ġ ����
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		
		JPanel pnl_btn = new JPanel();
		btnLogOut = new JButton("�α� �ƿ�");
		btnLogOut.addActionListener(this);
		
		btnStempTable = new JButton("����������");
		btnStempTable.addActionListener(this);
		
		btnDeadLineRegistration = new JButton("���� ���");
		btnDeadLineRegistration.addActionListener(this);
		
		btnPaymentData = new JButton("���� ����");
		btnPaymentData.addActionListener(this);
		
		
		
		pnl_btn.add(btnStempTable);
		pnl_btn.add(btnPaymentData);
		pnl_btn.add(btnDeadLineRegistration);
		pnl_btn.add(btnLogOut);
		
		JLabel title = new JLabel("������ �޴�");
		
		
		//ui�κ�
		this.getContentPane().setBackground(new Color(0x003E00));
		title.setForeground(Color.WHITE);
		title.setFont(new Font("HY�߰��",Font.PLAIN,35));
		title.setBounds(200,10,300,50);
		pnl_btn.setLayout(new GridLayout(1,0,0,0));
		pnl_btn.setBackground(new Color(0x252525));
		pnl_btn.setBounds(10,90,565,50);
		btnPaymentData.setBackground(new Color(0x252525));
		btnPaymentData.setBorder(BorderFactory.createEmptyBorder());
		btnPaymentData.setForeground(Color.white);
		btnStempTable.setBackground(new Color(0x252525));
		btnStempTable.setBorder(BorderFactory.createEmptyBorder());
		btnStempTable.setForeground(Color.white);
		btnDeadLineRegistration.setBackground(new Color(0x252525));
		btnDeadLineRegistration.setBorder(BorderFactory.createEmptyBorder());
		btnDeadLineRegistration.setForeground(Color.white);
		btnLogOut.setBackground(new Color(0x252525));
		btnLogOut.setBorder(BorderFactory.createEmptyBorder());
		btnLogOut.setForeground(Color.white);
		
		
		btnPaymentData.setFont(new Font("HY�߰��",Font.PLAIN,25));
		btnStempTable.setFont(new Font("HY�߰��",Font.PLAIN,25));
		btnDeadLineRegistration.setFont(new Font("HY�߰��",Font.PLAIN,25));
		btnLogOut.setFont(new Font("HY�߰��",Font.PLAIN,25));
		
		JPanel pnl = new JPanel();
		pnl.setBackground(new Color(0x252525));
		pnl.setBounds(0,65,600,150);
		
		JButton btn = new JButton();
		btn.setBackground(Color.WHITE);
		btn.setBounds(20,65,545,3);
		
		add(btn);
		add(pnl_btn);
		add(pnl);
		add(title);
		setLayout(null);
		setSize(600,200);
		setVisible(true);
		title.requestFocus();
	}
	
	JCafeNewMenuPnl nmp;
	JCafeManagerMenu c;
	JCafeManagerMenu(JCafeManagerMenu c, JCafeNewMenuPnl nmp){
		this.nmp = nmp;
		this.c = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnLogOut){
			int confirmChk =JOptionPane.showConfirmDialog(this, "�α׾ƿ� �Ͻðڽ��ϱ�?","�α׾ƿ�",JOptionPane.YES_NO_OPTION);
			if(confirmChk == JOptionPane.YES_OPTION){
				mainFrame.logOut();
				dispose();
			}
		}else if(e.getSource()==btnStempTable){
			new JCafeStempTable(this);
		}else if(e.getSource()==btnDeadLineRegistration){
			new DeadLineRegistration(this);
		}else if(e.getSource()==btnPaymentData){
			new JCafeCloseSale();
		}
	}
}

class DeadLineRegistration extends JDialog implements ActionListener{ //������� �޴�
	JButton stock,order,attend,newMenu,exit;
	JCafeManagerMenu jmm;
	JCafeDeadLineList dll;
	JCafeSpinner spinner;
	JCafeTotalPay total;
	
	public DeadLineRegistration(JCafeManagerMenu jmm){
		this.jmm =jmm;
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		String str[]={"���������","���ֵ��","����Ȯ��","�޴����","�����Ϸ�"};

		JPanel panel=new JPanel();

		stock=new JButton("���������");
		order=new JButton("���ֵ��");
		attend=new JButton("����Ȯ��");
		newMenu=new JButton("�޴����");
		exit=new JButton("�����Ϸ�");
		
		Color color=new Color(255,228,225);
		panel.setBackground(color.white);
		
		stock.setBackground(color);
		order.setBackground(color);
		attend.setBackground(color);
		newMenu.setBackground(color);
		exit.setBackground(color);
		
		stock.addActionListener(this);
		order.addActionListener(this);
		attend.addActionListener(this);
		newMenu.addActionListener(this);
		exit.addActionListener(this);
		
		
		panel.setLayout(new GridLayout(1,0,0,0));
		panel.add(stock);
		panel.add(order);
		panel.add(attend);
		panel.add(newMenu);
		panel.add(exit);
		
		//ui
		JButton btnUI = new JButton();
		btnUI.setBackground(new Color(0x252525));
		btnUI.setEnabled(false);
		JButton btnUI2 = new JButton();
		btnUI2.setBackground(new Color(0x252525));
		btnUI2.setEnabled(false);
		panel.setBackground(new Color(0x252525));
		stock.setBackground(new Color(0x252525));
		order.setBackground(new Color(0x252525));
		attend.setBackground(new Color(0x252525));
		newMenu.setBackground(new Color(0x252525));
		exit.setBackground(new Color(0x252525));
		stock.setBorder(BorderFactory.createEmptyBorder());
		order.setBorder(BorderFactory.createEmptyBorder());
		attend.setBorder(BorderFactory.createEmptyBorder());
		newMenu.setBorder(BorderFactory.createEmptyBorder());
		exit.setBorder(BorderFactory.createEmptyBorder());
		stock.setForeground(Color.WHITE);
		stock.setFont(new Font("HY�߰��",Font.PLAIN,19));
		order.setForeground(Color.WHITE);
		order.setFont(new Font("HY�߰��",Font.PLAIN,20));
		attend.setForeground(Color.WHITE);
		attend.setFont(new Font("HY�߰��",Font.PLAIN,20));
		newMenu.setForeground(Color.WHITE);
		newMenu.setFont(new Font("HY�߰��",Font.PLAIN,20));
		exit.setForeground(Color.WHITE);
		exit.setFont(new Font("HY�߰��",Font.PLAIN,20));
		
		JLabel titleLbl = new JLabel("���� ���");
		titleLbl.setForeground(new Color(0x252525));
		titleLbl.setFont(new Font("HY�߰��",Font.PLAIN,35));
		
		titleLbl.setBounds(20,13,300,50);
		btnUI.setBounds(0,70,585,5);
		panel.setBounds(0,80,585,80);
		btnUI2.setBounds(0,165,620,5);
		
		//���� ��� ��ϰ� ���ֵ���� ��ģ ��� ������ ������ ��ư ���� ����
		btnColorChangeFileExist();
			
		
		this.getContentPane().setBackground(Color.WHITE);
		this.setSize(600,210);
		this.setLayout(null);
		this.add(panel);
		this.add(btnUI);
		this.add(btnUI2);
		this.add(titleLbl);
		this.setLocation(jmm.getX(),jmm.getY());	//ȭ�� ��ġ ����
		this.setVisible(true);
		
	}
	
	
	//���� ��� ��ϰ� ���� ��� ������ �����ϴ� ��� �ش� ��ư ���� �����Ŵ
	void btnColorChangeFileExist(){
		Date date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		String dt = transFormat.format(date);
		
		File dir = new File("JCafeData/SaleData/DeadlineData/"+dt);
		if (dir.exists()) {
			stock.setBackground(new Color(0x606060));
		}
				
		File dir2 = new File("JCafeData/OrderRegistration/"+dt);
		if (dir2.exists()) {
			order.setBackground(new Color(0x484848));
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==stock){
			if(dll!=null){this.remove(dll);}	//�ش� �����ӿ� �гη� ���̴� �������� �Ϸ��� ����� ��ư Ŭ���Ҷ����� ������ �ִ� �г� ���� �� �г� �ٰ� ����
			if(spinner!=null){this.remove(spinner);}
			if(total!=null){this.remove(total);}
			Date date = new Date();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
			String dt = transFormat.format(date);
			File file1 = new File("./JCafeData/SaleData/SellMenuData/"+dt+"/coffee");
			File file2 = new File("./JCafeData/SaleData/SellMenuData/"+dt+"/latte");
			File file3 = new File("./JCafeData/SaleData/SellMenuData/"+dt+"/tea");
			File file4 = new File("JCafeData/SaleData/DeadlineData/"+dt);
			if(!(file1.exists())||!(file2.exists())||!(file3.exists())){
				JOptionPane.showMessageDialog(this, "���� �Ǹ� ����� �����ϴ�.");
				this.setSize(600,210);
			}else if(file4.exists()){
				JOptionPane.showMessageDialog(this, "���� ��� ����� �̹� �Ϸ�Ǿ����ϴ�.");
				this.setSize(600,210);
			}else{
				dll =	new JCafeDeadLineList(this);
				dll.setBounds(20,90,585,800);
				this.setSize(600,800);
				this.add(dll);
			}
			this.repaint();
			this.revalidate();
		}
		else if(e.getSource()==order){
			if(dll!=null){this.remove(dll);}
			if(spinner!=null){this.remove(spinner);}
			if(total!=null){this.remove(total);}
			
			Date date = new Date();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
			String dt = transFormat.format(date);
			
			File file1 = new File("JCafeData/SaleData/DeadlineData/"+dt);
			File file2 = new File("JCafeData/OrderRegistration/" + dt);
			if (!file1.exists()) {
				JOptionPane.showMessageDialog(this, "���� ������� �Ϸ���� �ʾҽ��ϴ�.");
				this.setSize(600,210);
			}else if(file2.exists()){
				JOptionPane.showMessageDialog(this, "���� ����� �̹� �Ϸ�Ǿ����ϴ�.");
				this.setSize(600,210);
			}else{
				spinner = new JCafeSpinner(this);
				spinner.setBounds(20,180,585,830);
				this.setSize(600,800);
				this.add(spinner);
			}
			this.repaint();
			this.revalidate();
		}
		else if(e.getSource()==attend){
			if(dll!=null){this.remove(dll);}	//�ش� �����ӿ� �гη� ���̴� �������� �Ϸ��� ����� ��ư Ŭ���Ҷ����� ������ �ִ� �г� ���� �� �г� �ٰ� ����
			if(spinner!=null){this.remove(spinner);}
			if(total!=null){this.remove(total);}
			total =	new JCafeTotalPay();
			total.setBounds(20,90,585,800);
			this.setSize(600,800);
			this.add(total);
			this.repaint();
			this.revalidate();
		}
		else if(e.getSource()==newMenu){
			if(dll!=null){this.remove(dll);this.setSize(600,210);this.repaint();this.revalidate();}
			if(spinner!=null){this.remove(spinner);this.setSize(600,210);this.repaint();this.revalidate();}
			if(total!=null){this.remove(total);this.setSize(600,210);this.repaint();this.revalidate();}
			new JCafeNewMenuPnl();
		}
		else if(e.getSource()==exit){
			if(dll!=null){this.remove(dll);this.setSize(600,210);this.repaint();this.revalidate();}
			if(spinner!=null){this.remove(spinner);this.setSize(600,210);this.repaint();this.revalidate();}
			if(total!=null){this.remove(total);this.setSize(600,210);this.repaint();this.revalidate();}
			total =	new JCafeTotalPay();
			total.totalSave();
			int result=JOptionPane.showConfirmDialog(this, "�����Ϸ�� �ý����� ����˴ϴ�. �����Ͻðڽ��ϱ�?");
			if(result==JOptionPane.YES_NO_OPTION){
				System.exit(0);
			}
		}
		
	}
}