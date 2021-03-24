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
			//로그아웃 	   /스템프관리		/결제내역		 /마감등록
	JCafeMain mainFrame;
	
	public JCafeManagerMenu(JCafeMain mainFrame){
		super(mainFrame,true);
		JCafeIpGo.ipgo();
		this.mainFrame = mainFrame;
		this.setLocation(mainFrame.getX(),mainFrame.getY());	//화면 위치 설정
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JPanel pnl_btn = new JPanel();
		btnLogOut = new JButton("로그 아웃");
		btnLogOut.addActionListener(this);
		
		btnStempTable = new JButton("스템프관리");
		btnStempTable.addActionListener(this);
		
		btnDeadLineRegistration = new JButton("마감 등록");
		btnDeadLineRegistration.addActionListener(this);
		
		btnPaymentData = new JButton("결제 내역");
		btnPaymentData.addActionListener(this);
		
		
		
		pnl_btn.add(btnStempTable);
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
		
		
		btnPaymentData.setFont(new Font("HY견고딕",Font.PLAIN,25));
		btnStempTable.setFont(new Font("HY견고딕",Font.PLAIN,25));
		btnDeadLineRegistration.setFont(new Font("HY견고딕",Font.PLAIN,25));
		btnLogOut.setFont(new Font("HY견고딕",Font.PLAIN,25));
		
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
			int confirmChk =JOptionPane.showConfirmDialog(this, "로그아웃 하시겠습니까?","로그아웃",JOptionPane.YES_NO_OPTION);
			if(confirmChk == JOptionPane.YES_OPTION){
				mainFrame.logOut();
				dispose();
			}
		}else if(e.getSource()==btnStempTable){
			new JCafeStempTable(this);
		}else if(e.getSource()==btnDeadLineRegistration){
			new DeadLineRegistration(this);
		}else if(e.getSource()==btnPaymentData){
			new JCafeCloseSale(this);
		}
	}
}

class DeadLineRegistration extends JDialog implements ActionListener{ //마감등록 메뉴
	JButton stock,order,attend,newMenu,exit;
	JCafeManagerMenu jmm;
	JCafeDeadLineList dll;
	JCafeSpinner spinner;
	JCafeTotalPay total;
	
	public DeadLineRegistration(JCafeManagerMenu jmm){
		super(jmm, true);
		this.jmm =jmm;
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
		stock.setFont(new Font("HY견고딕",Font.PLAIN,19));
		order.setForeground(Color.WHITE);
		order.setFont(new Font("HY견고딕",Font.PLAIN,20));
		attend.setForeground(Color.WHITE);
		attend.setFont(new Font("HY견고딕",Font.PLAIN,20));
		newMenu.setForeground(Color.WHITE);
		newMenu.setFont(new Font("HY견고딕",Font.PLAIN,20));
		exit.setForeground(Color.WHITE);
		exit.setFont(new Font("HY견고딕",Font.PLAIN,20));
		
		JLabel titleLbl = new JLabel("마감 등록");
		titleLbl.setForeground(new Color(0x252525));
		titleLbl.setFont(new Font("HY견고딕",Font.PLAIN,35));
		
		titleLbl.setBounds(20,13,300,50);
		btnUI.setBounds(0,70,585,5);
		panel.setBounds(0,80,585,80);
		btnUI2.setBounds(0,165,620,5);
		
		//금일 재고 등록과 발주등록을 마친 경우 오렌지 색으로 버튼 색이 변함
		btnColorChangeFileExist();
			
		
		this.getContentPane().setBackground(Color.WHITE);
		this.setSize(600,210);
		this.setLayout(null);
		this.add(panel);
		this.add(btnUI);
		this.add(btnUI2);
		this.add(titleLbl);
		this.setLocation(jmm.getX(),jmm.getY());	//화면 위치 설정
		this.setVisible(true);
		
	}
	
	
	//당일 재고 등록과 발주 등록 파일이 존재하는 경우 해당 버튼 색을 변경시킴
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
			if(dll!=null){this.remove(dll);}	//해당 프레임에 패널로 붙이는 형식으로 하려고 방식을 버튼 클릭할때마다 기존에 있던 패널 떼고 새 패널 붙게 조절
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
				JOptionPane.showMessageDialog(this, "오늘 판매 기록이 없습니다.");
				this.setSize(600,210);
			}else if(file4.exists()){
				JOptionPane.showMessageDialog(this, "금일 재고 등록이 이미 완료되었습니다.");
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
				JOptionPane.showMessageDialog(this, "금일 재고등록이 완료되지 않았습니다.");
				this.setSize(600,210);
			}else if(file2.exists()){
				JOptionPane.showMessageDialog(this, "발주 등록이 이미 완료되었습니다.");
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
			if(dll!=null){this.remove(dll);}	//해당 프레임에 패널로 붙이는 형식으로 하려고 방식을 버튼 클릭할때마다 기존에 있던 패널 떼고 새 패널 붙게 조절
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
			new JCafeNewMenuPnl(this);
		}
		else if(e.getSource()==exit){
			if(dll!=null){this.remove(dll);this.setSize(600,210);this.repaint();this.revalidate();}
			if(spinner!=null){this.remove(spinner);this.setSize(600,210);this.repaint();this.revalidate();}
			if(total!=null){this.remove(total);this.setSize(600,210);this.repaint();this.revalidate();}
			total =	new JCafeTotalPay();
			total.totalSave();
			int result=JOptionPane.showConfirmDialog(this, "마감완료시 시스템이 종료됩니다. 종료하시겠습니까?");
			if(result==JOptionPane.YES_NO_OPTION){
				System.exit(0);
			}
		}
		
	}
}