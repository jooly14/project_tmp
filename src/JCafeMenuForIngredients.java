import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


class JCafeMenuForIngredients extends JDialog implements ActionListener,MouseListener{
	
	JLabel title;
	JButton checkBtn;
	JTable menuTable;
	DefaultTableModel model;
	String[] header={"�޴���"};
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
		super(nmp);
		this.setIconImage(new ImageIcon("JCafeData\\ImageData\\JCafe icon.png").getImage());
		this.setSize(500,550);
		
		pnlN = new JPanel();
		title=new JLabel("�޴�����");
		String str[]={"coffee","latte","tea"};
		comboMenu = new JComboBox(str);
		comboMenu.addActionListener(this);
		pnlN.add(title);
		pnlN.add(comboMenu);
		
		//�޴����� ���̺� ����
		pnlC = new JPanel();
		model=new DefaultTableModel(contents,header)
		{
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};//���̺� �����ȵǰ� �ϴ°�
		menuTable = new JTable(model);
		menuTable.addMouseListener(this);
		menuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//�ϳ��� �ุ ������ �� �ֵ��� ����
		JScrollPane sp = new JScrollPane(menuTable);
		pnlC.add(sp);
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//Table �������
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = menuTable.getColumnModel();
		for(int i = 0 ; i < tcm.getColumnCount() ; i++){
		      	tcm.getColumn(i).setCellRenderer(dtcr);  
		}
		//��ư
		JPanel pnlS = new JPanel();
		checkBtn = new JButton("Ȯ��");
		checkBtn.addActionListener(this);
		pnlS.add(checkBtn);
		
		repaint();
		revalidate();
		//���󺯰�
		Color color=new Color(0x252525);
		pnlN.setBackground(color);
		pnlC.setBackground(color);
		pnlS.setBackground(color);
		title.setForeground(Color.WHITE);
		checkBtn.setBackground(Color.WHITE);
		comboMenu.setBackground(Color.WHITE);
		
		this.add(pnlN, "North");
		this.add(pnlC);
		this.add(pnlS, "South");
		this.setLocationRelativeTo(null);
		fileOpen();
		this.setVisible(true);
	}
	void fileOpen(){   //table�� ���� �ҷ���
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
				JOptionPane.showMessageDialog(null, "�����ϼ���.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
			}else{
				if(index == 0) {
					new JCafeNewCoffeeMaterial(comboMenu.getSelectedIndex(), menuName);
				}else if(index == 1) {
					new JCafeNewLatteMaterial(comboMenu.getSelectedIndex(), menuName);
				}else if(index == 2) {
					new JCafeNewTeaMaterial(comboMenu.getSelectedIndex(), menuName);
				}
				this.setVisible(false);
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
