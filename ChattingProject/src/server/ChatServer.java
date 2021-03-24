package server;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import client.NewRoomFrame;

public class ChatServer extends JFrame implements ActionListener{
	//���� ���۽�Ű�� ���� �޼���
	public static void main(String[] args) {
		new ChatServer();
	}
	
	JPanel pnl;		
	JTextArea ta;	
	JTextField tf;
	
	String[] headerAll = {"��� ������"};	
	String[][] contentAll = {};
	DefaultTableModel modelAll;
	JTable tableAll;
	
	HashMap<String, Room> roomMap = new HashMap<>();	//�濡 ���� ������ ��� �ִ�. Ű�� ���ȣ
	
	ServerSocket serverSocket;
	Socket clientSocket;
	HashMap<String, PrintWriter> map;	//��ü ������� ���̵�� printWriter�� ����
	
	int roomNum;	//������ ���� ������ �� �������� �� ��ȣ�� �������ֱ� ���ؼ� �ʿ�	//�渶�� ������ ���ȣ�� ���� �� �ֵ��� ����	// ���ȣ + ���ó�¥ �� ���ȣ
	
	HashSet<String> prohibitWordList = new HashSet<>();
	ChatServerThread chatServerThread;
	//�ʱ�ȭ
	public ChatServer() {
		init();	
		setServer();
	}
	//Ŭ���̾�Ʈ�κ��� ������ �޾ƿ�
	public void setServer(){
		try {
			map = new HashMap<>();
			serverSocket = new ServerSocket(5555);
			ta.append("������ ��ٸ��� ���Դϴ�.\n");
			while (true) {																//�������� ������ ���� �����ϵ��� ��
				clientSocket = serverSocket.accept();
				chatServerThread = new ChatServerThread(this, map, clientSocket, ta, modelAll);	//������ ����
				chatServerThread.start();
			}
		}catch (BindException e) {
			JOptionPane.showMessageDialog(null, "������ �̹� ���� ���Դϴ�.");
			System.exit(0);
		}catch (SocketException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(serverSocket!=null){
					serverSocket.close();
				}
				if(clientSocket!=null){
					clientSocket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//ui �ʱ�ȭ
	public void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("����");
		setSize(616,400);
		setResizable(false);
		pnl = new JPanel();
		pnl.setBackground(Color.white);
		pnl.setLayout(null);
		
		tf = new JTextField();
		tf.addActionListener(this);
		tf.setBounds(0, 330, 400, 30);
		ta =  new JTextArea();
		ta.append("/howto �� �ۼ��Ͻø� ���� ������� Ȯ���Ͻ� �� �ֽ��ϴ�\n");
		ta.setEditable(false);
		JScrollPane pane = new JScrollPane(ta);
		pane.setBounds(0, 0, 400, 330);
		pnl.add(pane);
		pnl.add(tf);
		
		
		modelAll = new DefaultTableModel(contentAll,headerAll){	//���̺� ������ �Ұ����ϵ�����
			public boolean isCellEditable(int rowIndex, int colIndex){
				return false;
			}
		};	//��ü ����� ����� ������
		tableAll = new JTable(modelAll);
		tableAll.getTableHeader().setReorderingAllowed(false);	
		tableAll.getTableHeader().setResizingAllowed(false);
		tableAll.setRowSorter(new TableRowSorter<DefaultTableModel>(modelAll));	//������ �����ϵ���
		JScrollPane pane_tableAll = new JScrollPane(tableAll);
		pane_tableAll.getViewport().setBackground(Color.white);
		tableAll.getTableHeader().setBackground(Color.white);
		pane_tableAll.setBounds(400, 0, 200, 360);
		pnl.add(pane_tableAll);
		
		//����� ����� Ŭ���ϸ� ���õ� ���̵𿡰� �ӼӸ� �����Ⱑ ����
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem item1 = new JMenuItem("�ӼӸ� ������");
		popupMenu.add(item1);
		item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText("/w "+tableAll.getValueAt(tableAll.getSelectedRow(), 0)+" ");
				tf.requestFocus();
			}
		});
		tableAll.setComponentPopupMenu(popupMenu);
		
		tableAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableAll.setRowSelectionInterval(tableAll.rowAtPoint(e.getPoint()), tableAll.rowAtPoint(e.getPoint()));
				if(e.getButton()==1){
					popupMenu.show(tableAll, e.getX(), e.getY());
				}
			}
		});
		
		
		readPWordFileFirst();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					if(serverSocket!=null){
						serverSocket.close();
					}
					if(clientSocket!=null){
						clientSocket.close();
					}
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		
		Image img = Toolkit.getDefaultToolkit().getImage("big6.png");
		img = img.getScaledInstance(15, 15, Image.SCALE_DEFAULT);
		setIconImage(img);
		
		add(pnl);
		setVisible(true);
		tf.requestFocus();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//�ؽ�Ʈ�ʵ忡�� �̺�Ʈ�����ʰ� �ɷ�����
		String[] split = tf.getText().split(" ");
		if(split[0].equals("/howto")){
			ta.append("\n<< ���� >>\n");
			ta.append("�ӼӸ� : /w ����ھ��̵� �����ҳ���\n");
			ta.append("������ ��� : /pWord\n");
			ta.append("������ �߰� : /pWordAdd �����ܾ�\n");
			ta.append("������ ���� : /pWordRemove �����ܾ�\n\n");
			ta.setCaretPosition(ta.getDocument().getLength());
		}else if(split[0].equals("/pWord")){	//������ ������ ��� �о��
			readPWordFile();
		}else if(split[0].equals("/pWordAdd")){	// "/pWordAdd �����ܾ�"�������� ���� �����ܾ ��������Ϳ� ����
			if(split.length==1){	//������ �߸��� ���
				ta.append("\"/pWordAdd �����ܾ�\" �������� �ۼ����ּ���\n");
				ta.setCaretPosition(ta.getDocument().getLength());
			}else{
				addPwordFile(tf.getText().substring(tf.getText().indexOf(split[1])));
			}
		}else if(split[0].equals("/pWordRemove")){	// "/pWordRemove �����ܾ�"�������� ���� ��������Ϳ��� �����ܾ ����
			if(split.length==1){
				ta.append("\"/pWordRemove �����ܾ�\" �������� �ۼ����ּ���\n");
				ta.setCaretPosition(ta.getDocument().getLength());
			}else{
				removePwordFile(tf.getText().substring(tf.getText().indexOf(split[1])));
			}
		}else if(split[0].equals("/w")){
			if(split.length<3){
				ta.append("\"/w ����ھ��̵� �����ҳ���\" �������� �ۼ����ּ���\n");
				ta.setCaretPosition(ta.getDocument().getLength());
			}else{
				if(map.get(split[1])==null){	//�ش��ϴ� ���̵� ���� ��쿡�� null�� ��ȯ��
					ta.append(split[1]+"�� �ش��ϴ� ����� ���̵� �����ϴ�.\n");
					ta.setCaretPosition(ta.getDocument().getLength());
				}else{
					ta.append("[�ӼӸ�:"+split[1]+"����] [����]  : "+tf.getText().substring(tf.getText().indexOf(" ", 3)+1)+"\n");
					ta.setCaretPosition(ta.getDocument().getLength());
					map.get(split[1]).println("[�ӼӸ�] [����]  : "+tf.getText().substring(tf.getText().indexOf(" ", 3)+1)+" /server");
				}
			}
		}else{
			if(tf.getText().length()!=0){	//�ƹ��͵� �Է����� ���� ��� ���۵��� ����
				ta.append("[����] : "+tf.getText()+"\n");
				ta.setCaretPosition(ta.getDocument().getLength());
				for(Map.Entry<String, PrintWriter> entry: map.entrySet()){
					entry.getValue().println("[����] : "+tf.getText()+" /server");
				}
			}
		}
		tf.setText("");
		tf.requestFocus();
	} 
	//������ �����Ϳ��� �ش� ����� ����
	public void removePwordFile(String removeWord){
		boolean exist = false;	//�ش� ����� �������� �ʴ� ��� �˷��ֱ� ���ؼ�
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fr = new FileReader("ProhibitedWord.txt");
			br = new BufferedReader(fr);
			Vector<String> lList = new Vector<>();	//���⿡ ������ ��Ƽ� ������ �ܾ ���� �ٽ� ����
			String l = null;
			while ((l = br.readLine())!=null) {
				if(l.equals(removeWord)){
					exist = true;
				}else{
					lList.add(l);
				}
			}
			fw = new FileWriter("ProhibitedWord.txt",false);
			pw = new PrintWriter(fw,true);
			for (int i = 0; i < lList.size(); i++) {
				pw.println(lList.get(i));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(fr!=null){
					fr.close();
				}
				if(br!=null){
					br.close();
				}
				if(fw!=null){
					fw.close();
				}
				if(pw!=null){
					pw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(!exist){
			ta.append(removeWord +" �� �������� �ʴ� �������Դϴ�.\n");
			ta.setCaretPosition(ta.getDocument().getLength());
		}else{
			prohibitWordList.remove(removeWord);
			ta.append(removeWord+" �� ������� �����Ǿ����ϴ�\n");
			ta.setCaretPosition(ta.getDocument().getLength());
		}
	}
	
	//��������Ϳ� ����� �߰�
	public void addPwordFile(String addWord){
		boolean equalWord = false;	//�̹� �Ȱ��� �ܾ �����ϴ� ��� �˷��ֱ� ���ؼ�
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fr = new FileReader("ProhibitedWord.txt");
			br = new BufferedReader(fr);
			String l;
			while ((l = br.readLine())!=null) {
				if(l.equals(addWord)){
					equalWord = true;	
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				try {
					if(fr!=null){
						fr.close();
					}
					if(br!=null){
						br.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		if(equalWord){
			ta.append(addWord +" �� �̹� �����ϴ� �������Դϴ�.\n");
			ta.setCaretPosition(ta.getDocument().getLength());
		}else{	//�Ȱ��� �ܾ �������� �ʴ� ��� ��������Ϳ� ����� �߰�
			try {
				fw = new FileWriter("ProhibitedWord.txt",true);
				pw = new PrintWriter(fw,true);
				pw.println(addWord);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(fw!=null){
						fw.close();
					}
					if(pw!=null){
						pw.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			prohibitWordList.add(addWord);
			ta.append(addWord+" �� ����� �߰��Ǿ����ϴ�.\n");
			ta.setCaretPosition(ta.getDocument().getLength());
		}
	}
	
	//������ �����Ϳ��� ���������� ��� �о��
	public void readPWordFile(){
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("ProhibitedWord.txt");
			br = new BufferedReader(fr);
			String l;
			ta.append("\n<< ������ ��� >>\n");
			while ((l = br.readLine())!=null) {
				ta.append(l+"\n");
			}
			ta.setCaretPosition(ta.getDocument().getLength());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				try {
					if(fr!=null){
						fr.close();
					}
					if(br!=null){
						br.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	//������ �����Ϳ��� ���������� ��� �о��
	public void readPWordFileFirst(){
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("ProhibitedWord.txt");
			br = new BufferedReader(fr);
			String l;
			while ((l = br.readLine())!=null) {
				prohibitWordList.add(l);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(fr!=null){
					fr.close();
				}
				if(br!=null){
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//chatserverthread ���� ���
	public HashMap<String, Room> getRoomMap(){
		return roomMap;
	}
	//chatserverthread ���� ���
	//roomMap���� ��� ä�ù� ����� �������
	//ä�ù��� ���� �����ϸ� �߰�����
	public void createNewRoom(String roomNum,String owner,String roomName){
		roomMap.put(roomNum, new Room(owner,map.get(owner),roomName));
		ta.append(owner+"���� ��ȭ��("+roomNum+")�� �����ϼ̽��ϴ�.\n");
		ta.setCaretPosition(ta.getDocument().getLength());
	}
	//chatserverthread ���� ���
	//��й��� ������ ��� �н����带 �߰�����
	public void createNewSecretRoom(String roomNum,String owner,String roomName,String password){
		roomMap.put(roomNum, new Room(owner, map.get(owner), roomName));
		roomMap.get(roomNum).setSecretRoom(true);
		roomMap.get(roomNum).setPassword(password);
		ta.append(owner+"���� ��� ��ȭ��("+roomNum+")�� �����ϼ̽��ϴ�.\n");
		ta.setCaretPosition(ta.getDocument().getLength());
	}
	//chatserverthread ���� ���
	//���ο� ä�ù��� �����Ҷ� ������ ���ȣ�� ������ ����
	public int getRoomNum() {
		return roomNum++;
	}
	public JTable getTableAll() {
		return tableAll;
	}
	public HashSet<String> getProhibitWordList() {
		return prohibitWordList;
	}
	
	
	
}


