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
	//서버 시작시키는 메인 메서드
	public static void main(String[] args) {
		new ChatServer();
	}
	
	JPanel pnl;		
	JTextArea ta;	
	JTextField tf;
	
	String[] headerAll = {"모든 접속자"};	
	String[][] contentAll = {};
	DefaultTableModel modelAll;
	JTable tableAll;
	
	HashMap<String, Room> roomMap = new HashMap<>();	//방에 대한 정보를 담고 있다. 키는 방번호
	
	ServerSocket serverSocket;
	Socket clientSocket;
	HashMap<String, PrintWriter> map;	//전체 사용자의 아이디와 printWriter를 관리
	
	int roomNum;	//유저가 방을 생성할 때 서버에서 방 번호를 생성해주기 위해서 필요	//방마다 고유한 방번호를 가질 수 있도록 만듦	// 방번호 + 오늘날짜 가 방번호
	
	HashSet<String> prohibitWordList = new HashSet<>();
	ChatServerThread chatServerThread;
	//초기화
	public ChatServer() {
		init();	
		setServer();
	}
	//클라이언트로부터 연결을 받아옴
	public void setServer(){
		try {
			map = new HashMap<>();
			serverSocket = new ServerSocket(5555);
			ta.append("연결을 기다리는 중입니다.\n");
			while (true) {																//여러명의 유저와 연결 가능하도록 함
				clientSocket = serverSocket.accept();
				chatServerThread = new ChatServerThread(this, map, clientSocket, ta, modelAll);	//스레드 시작
				chatServerThread.start();
			}
		}catch (BindException e) {
			JOptionPane.showMessageDialog(null, "서버가 이미 실행 중입니다.");
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
	
	//ui 초기화
	public void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("서버");
		setSize(616,400);
		setResizable(false);
		pnl = new JPanel();
		pnl.setBackground(Color.white);
		pnl.setLayout(null);
		
		tf = new JTextField();
		tf.addActionListener(this);
		tf.setBounds(0, 330, 400, 30);
		ta =  new JTextArea();
		ta.append("/howto 를 작성하시면 서버 사용방법을 확인하실 수 있습니다\n");
		ta.setEditable(false);
		JScrollPane pane = new JScrollPane(ta);
		pane.setBounds(0, 0, 400, 330);
		pnl.add(pane);
		pnl.add(tf);
		
		
		modelAll = new DefaultTableModel(contentAll,headerAll){	//테이블 수정이 불가능하도록함
			public boolean isCellEditable(int rowIndex, int colIndex){
				return false;
			}
		};	//전체 사용자 목록을 보여줌
		tableAll = new JTable(modelAll);
		tableAll.getTableHeader().setReorderingAllowed(false);	
		tableAll.getTableHeader().setResizingAllowed(false);
		tableAll.setRowSorter(new TableRowSorter<DefaultTableModel>(modelAll));	//정렬이 가능하도록
		JScrollPane pane_tableAll = new JScrollPane(tableAll);
		pane_tableAll.getViewport().setBackground(Color.white);
		tableAll.getTableHeader().setBackground(Color.white);
		pane_tableAll.setBounds(400, 0, 200, 360);
		pnl.add(pane_tableAll);
		
		//사용자 목록을 클릭하면 선택된 아이디에게 귓속말 보내기가 가능
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem item1 = new JMenuItem("귓속말 보내기");
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
		//텍스트필드에만 이벤트리스너가 걸려있음
		String[] split = tf.getText().split(" ");
		if(split[0].equals("/howto")){
			ta.append("\n<< 사용법 >>\n");
			ta.append("귓속말 : /w 사용자아이디 전달할내용\n");
			ta.append("금지어 목록 : /pWord\n");
			ta.append("금지어 추가 : /pWordAdd 금지단어\n");
			ta.append("금지어 삭제 : /pWordRemove 금지단어\n\n");
			ta.setCaretPosition(ta.getDocument().getLength());
		}else if(split[0].equals("/pWord")){	//금지어 파일을 모두 읽어옴
			readPWordFile();
		}else if(split[0].equals("/pWordAdd")){	// "/pWordAdd 금지단어"형식으로 쓰면 금지단어를 금지어데이터에 저장
			if(split.length==1){	//형식이 잘못된 경우
				ta.append("\"/pWordAdd 금지단어\" 형식으로 작성해주세요\n");
				ta.setCaretPosition(ta.getDocument().getLength());
			}else{
				addPwordFile(tf.getText().substring(tf.getText().indexOf(split[1])));
			}
		}else if(split[0].equals("/pWordRemove")){	// "/pWordRemove 금지단어"형식으로 쓰면 금지어데이터에서 금지단어를 삭제
			if(split.length==1){
				ta.append("\"/pWordRemove 금지단어\" 형식으로 작성해주세요\n");
				ta.setCaretPosition(ta.getDocument().getLength());
			}else{
				removePwordFile(tf.getText().substring(tf.getText().indexOf(split[1])));
			}
		}else if(split[0].equals("/w")){
			if(split.length<3){
				ta.append("\"/w 사용자아이디 전달할내용\" 형식으로 작성해주세요\n");
				ta.setCaretPosition(ta.getDocument().getLength());
			}else{
				if(map.get(split[1])==null){	//해당하는 아이디가 없는 경우에는 null을 반환함
					ta.append(split[1]+"에 해당하는 사용자 아이디가 없습니다.\n");
					ta.setCaretPosition(ta.getDocument().getLength());
				}else{
					ta.append("[귓속말:"+split[1]+"에게] [공지]  : "+tf.getText().substring(tf.getText().indexOf(" ", 3)+1)+"\n");
					ta.setCaretPosition(ta.getDocument().getLength());
					map.get(split[1]).println("[귓속말] [공지]  : "+tf.getText().substring(tf.getText().indexOf(" ", 3)+1)+" /server");
				}
			}
		}else{
			if(tf.getText().length()!=0){	//아무것도 입력하지 않은 경우 전송되지 않음
				ta.append("[공지] : "+tf.getText()+"\n");
				ta.setCaretPosition(ta.getDocument().getLength());
				for(Map.Entry<String, PrintWriter> entry: map.entrySet()){
					entry.getValue().println("[공지] : "+tf.getText()+" /server");
				}
			}
		}
		tf.setText("");
		tf.requestFocus();
	} 
	//금지어 데이터에서 해당 금지어를 제거
	public void removePwordFile(String removeWord){
		boolean exist = false;	//해당 금지어가 존재하지 않는 경우 알려주기 위해서
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fr = new FileReader("ProhibitedWord.txt");
			br = new BufferedReader(fr);
			Vector<String> lList = new Vector<>();	//여기에 내용을 담아서 삭제된 단어만 빼고 다시 저장
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
			ta.append(removeWord +" 는 존재하지 않는 금지어입니다.\n");
			ta.setCaretPosition(ta.getDocument().getLength());
		}else{
			prohibitWordList.remove(removeWord);
			ta.append(removeWord+" 가 금지어에서 삭제되었습니다\n");
			ta.setCaretPosition(ta.getDocument().getLength());
		}
	}
	
	//금지어데이터에 금지어를 추가
	public void addPwordFile(String addWord){
		boolean equalWord = false;	//이미 똑같은 단어가 존재하는 경우 알려주기 위해서
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
			ta.append(addWord +" 는 이미 존재하는 금지어입니다.\n");
			ta.setCaretPosition(ta.getDocument().getLength());
		}else{	//똑같은 단어가 존재하지 않는 경우 금지어데이터에 금지어를 추가
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
			ta.append(addWord+" 가 금지어에 추가되었습니다.\n");
			ta.setCaretPosition(ta.getDocument().getLength());
		}
	}
	
	//금지어 데이터에서 금지어목록을 모두 읽어옴
	public void readPWordFile(){
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("ProhibitedWord.txt");
			br = new BufferedReader(fr);
			String l;
			ta.append("\n<< 금지어 목록 >>\n");
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
	//금지어 데이터에서 금지어목록을 모두 읽어옴
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
	//chatserverthread 에서 사용
	public HashMap<String, Room> getRoomMap(){
		return roomMap;
	}
	//chatserverthread 에서 사용
	//roomMap에는 모든 채팅방 목록이 들어있음
	//채팅방을 새로 생성하면 추가해줌
	public void createNewRoom(String roomNum,String owner,String roomName){
		roomMap.put(roomNum, new Room(owner,map.get(owner),roomName));
		ta.append(owner+"님이 대화방("+roomNum+")을 생성하셨습니다.\n");
		ta.setCaretPosition(ta.getDocument().getLength());
	}
	//chatserverthread 에서 사용
	//비밀방을 생성한 경우 패스워드를 추가해줌
	public void createNewSecretRoom(String roomNum,String owner,String roomName,String password){
		roomMap.put(roomNum, new Room(owner, map.get(owner), roomName));
		roomMap.get(roomNum).setSecretRoom(true);
		roomMap.get(roomNum).setPassword(password);
		ta.append(owner+"님이 비밀 대화방("+roomNum+")을 생성하셨습니다.\n");
		ta.setCaretPosition(ta.getDocument().getLength());
	}
	//chatserverthread 에서 사용
	//새로운 채팅방을 생성할때 고유한 방번호를 가지기 위해
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


