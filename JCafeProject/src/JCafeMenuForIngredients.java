import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.table.*;


class JCafeMenuForIngredients extends JDialog implements ActionListener,MouseListener{
	JLabel title;
	JButton checkBtn;
	JTable menuTable;
	DefaultTableModel model;
	String[] header={"메뉴명"};
	String contents[][] = {};
	JPanel pnlN, pnlS, pnlC;
	JComboBox comboMenu;
	
	BufferedReader brC = null;
	BufferedReader brL = null;
	BufferedReader brT = null;
	String fstrC="JCafeData/InitData/InitMenuData/"+File.separator+"coffee";
	
	int selRow = -1;
	String menuName;
	
	
	JCafeMenuForIngredients(JCafeNewMenuPnl nmp){
		super(nmp, true);
		this.setSize(500,550);
		
		pnlN = new JPanel();
		title=new JLabel("메뉴선택");
		String str[]={"coffee","latte","tea"};
		comboMenu = new JComboBox(str);
		comboMenu.addActionListener(this);
		pnlN.add(title);
		pnlN.add(comboMenu);
		
		//메뉴관리 테이블 구성
		pnlC = new JPanel();
		model=new DefaultTableModel(contents,header)
		{
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};//테이블 수정안되게 하는거
		menuTable = new JTable(model);
		menuTable.addMouseListener(this);
		menuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//하나의 행만 선택할 수 있도록 지정
		JScrollPane sp = new JScrollPane(menuTable);
		pnlC.add(sp);
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//Table 가운데정렬
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = menuTable.getColumnModel();
		for(int i = 0 ; i < tcm.getColumnCount() ; i++){
		      	tcm.getColumn(i).setCellRenderer(dtcr);  
		}
		//버튼
		JPanel pnlS = new JPanel();
		checkBtn = new JButton("확인");
		checkBtn.addActionListener(this);
		pnlS.add(checkBtn);
		
		//색상변경
		Color color=new Color(0x252525);
		pnlN.setBackground(color);
		pnlC.setBackground(color);
		pnlS.setBackground(color);
		title.setForeground(Color.WHITE);
		checkBtn.setBackground(Color.WHITE);
		comboMenu.setBackground(Color.WHITE);
		this.setIconImage(new ImageIcon("JCafeData\\ImageData\\JCafe icon.png").getImage());
		this.add(pnlN, "North");
		this.add(pnlC);
		this.add(pnlS, "South");
		this.setLocationRelativeTo(null);
		fileOpen();
		this.setVisible(true);
	}
	void fileOpen(){   //table에 파일 불러옴
		try{
			BufferedReader brC = new BufferedReader(new FileReader(fstrC));
			String l1 = null;
			while((l1 = brC.readLine())!=null){
				String dataC[] = l1.split("/");
				for(int i = 0; i < 1; i++){
					model.addRow(dataC);
				}
			}
			brC.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int index = comboMenu.getSelectedIndex(); 
		
		if(index == 0){
			model.setNumRows(0);
			fstrC = "JCafeData/InitData/InitMenuData/"+File.separator+"coffee";
			fileOpen();
		}else if(index == 1){
			model.setNumRows(0);
			fstrC = "JCafeData/InitData/InitMenuData/"+File.separator+"latte";
			fileOpen();
		}else if(index == 2){
			model.setNumRows(0);
			fstrC = "JCafeData/InitData/InitMenuData/"+File.separator+"tea";
			fileOpen();
		}
		if(arg0.getSource() == checkBtn){
			if(selRow == -1){
				JOptionPane.showMessageDialog(null, "선택하세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				repaint();
				revalidate();
			}else if(selRow >= 0){
				new JCafeNewCoffeeMaterial(this, comboMenu.getSelectedIndex(), menuName);
				dispose();
				repaint();
				revalidate();
			}
		}
	}
		
	
	@Override
	public void mouseClicked(MouseEvent e) {
		selRow = menuTable.getSelectedRow();
		menuName = (String)menuTable.getValueAt(selRow, 0);
	}
	public void mouseEntered(MouseEvent e) {	}
	public void mouseExited(MouseEvent e) {	}
	public void mousePressed(MouseEvent e) {	}
	public void mouseReleased(MouseEvent e) {	}
}
