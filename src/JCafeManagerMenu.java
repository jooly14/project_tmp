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
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JCafeManagerMenu extends JDialog implements ActionListener{
	JButton btnLogOut, btnNewMenu, btnPaymentData, btnDeadLineRegistration;
			//로그아웃 	   /메뉴등록		/결제내역		 /마감등록
	JCafeMain mainFrame;
	
	public JCafeManagerMenu(JCafeMain mainFrame){
		this.mainFrame = mainFrame;
		this.setLocation(mainFrame.getX(),mainFrame.getY());	//화면 위치 설정
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		
		JPanel pnl_btn = new JPanel();
		btnLogOut = new JButton("로그 아웃");
		btnLogOut.addActionListener(this);
		
		btnNewMenu = new JButton("메뉴 등록");
		btnNewMenu.addActionListener(this);
		
		btnDeadLineRegistration = new JButton("마감 등록");
		btnDeadLineRegistration.addActionListener(this);
		
		btnPaymentData = new JButton("결제 내역");
		btnPaymentData.addActionListener(this);
		
		
		
		pnl_btn.add(btnNewMenu);
		pnl_btn.add(btnPaymentData);
		pnl_btn.add(btnDeadLineRegistration);
		pnl_btn.add(btnLogOut);
		
		JLabel title = new JLabel("관리자 메뉴");
		
		
		//ui부분
		this.getContentPane().setBackground(new Color(0x003E00));
		title.setForeground(Color.WHITE);
		title.setFont(new Font("HY견고딕",Font.PLAIN,35));
		title.setBounds(200,10,300,50);
		pnl_btn.setLayout(new GridLayout(1,0,0,0));
		pnl_btn.setBackground(new Color(0x121212));
		pnl_btn.setBounds(10,90,565,50);
		btnPaymentData.setBackground(new Color(0x121212));
		btnPaymentData.setBorder(BorderFactory.createEmptyBorder());
		btnPaymentData.setForeground(Color.white);
		btnNewMenu.setBackground(new Color(0x121212));
		btnNewMenu.setBorder(BorderFactory.createEmptyBorder());
		btnNewMenu.setForeground(Color.white);
		btnDeadLineRegistration.setBackground(new Color(0x121212));
		btnDeadLineRegistration.setBorder(BorderFactory.createEmptyBorder());
		btnDeadLineRegistration.setForeground(Color.white);
		btnLogOut.setBackground(new Color(0x121212));
		btnLogOut.setBorder(BorderFactory.createEmptyBorder());
		btnLogOut.setForeground(Color.white);
		
		
		btnPaymentData.setFont(new Font("HY견고딕",Font.PLAIN,25));
		btnNewMenu.setFont(new Font("HY견고딕",Font.PLAIN,25));
		btnDeadLineRegistration.setFont(new Font("HY견고딕",Font.PLAIN,25));
		btnLogOut.setFont(new Font("HY견고딕",Font.PLAIN,25));
		
		JPanel pnl = new JPanel();
		pnl.setBackground(new Color(0x121212));
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
			int confirmChk =JOptionPane.showConfirmDialog(this, "로그아웃 하시겠습니까?","로그아웃",JOptionPane.YES_NO_OPTION);
			if(confirmChk == JOptionPane.YES_OPTION){
				mainFrame.logOut();
				dispose();
			}
		}else if(e.getSource()==btnNewMenu){
			new JCafenewMenu(c, nmp);
		}else if(e.getSource()==btnDeadLineRegistration){
			new DeadLineRegistration();
		}else if(e.getSource()==btnPaymentData){
			new JCafeCloseSale();
		}
	}
}

class DeadLineRegistration extends JDialog implements ActionListener{ //마감등록 메뉴
	JButton stock,order,attend,newMenu,exit;
	JCafeManagerMenu jmm;
	
	DeadLineRegistration(){
		
		this.setSize(200,250);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		String str[]={"금일재고등록","발주등록","매출확인","메뉴등록","마감완료"};

		JPanel panel=new JPanel();

		stock=new JButton("금일재고등록");
		order=new JButton("발주등록");
		attend=new JButton("매출확인");
		newMenu=new JButton("메뉴등록");
		exit=new JButton("마감완료");
		
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
		
		//금일 재고 등록과 발주등록을 마친 경우 오렌지 색으로 버튼 색이 변함
		Date date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		String dt = transFormat.format(date);
		
		File dir = new File("JCafeData/SaleData/DealineData/"+dt);
		if (dir.exists()) {
			stock.setBackground(Color.orange);
		}
				
		File dir2 = new File("JCafeData/OrderRegistration/"+dt);
		if (dir2.exists()) {
			order.setBackground(Color.orange);
		}
		
		panel.setLayout(new GridLayout(0,1,10,10));
		panel.add(stock);
		panel.add(order);
		panel.add(attend);
		panel.add(newMenu);
		panel.add(exit);
		
		this.add(panel);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	DeadLineRegistration(JCafeManagerMenu jmm){
		this.jmm = jmm;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JCafeTotalPay c=null;
		if(e.getSource()==stock){
			new JCafeDeadLineList();
			dispose();
		}
		else if(e.getSource()==order){
			Date date = new Date();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
			String dt = transFormat.format(date);
			
			File dir = new File("JCafeData/SaleData/DealineData/"+dt);
			if (dir.exists()) {
				new JCafeSpinner();
				dispose();
			}else{
				JOptionPane.showMessageDialog(this, "금일 재고등록이 완료되지 않았습니다.");
			}
		}
		else if(e.getSource()==attend){
			c=new JCafeTotalPay(this);
		}
		else if(e.getSource()==newMenu){
			new JCafeNewMenuPnl();
		}
		else if(e.getSource()==exit){
			c.totalSave();
		}
		
	}
}


