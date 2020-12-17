import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicBorders.SplitPaneBorder;
import javax.swing.plaf.basic.DefaultMenuLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class ChatServerPrac extends JFrame implements ActionListener{
	public static void main(String[] args) {
		new ChatServerPrac();
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
	HashMap<String, PrintWriter> map;	//client의 id와 printwriter를 가지고 있다.
	
	//초기화
	public ChatServerPrac() {
		init();	
		setServer();
	}
	//클라이언트로부터 연결을 받아옴
	public void setServer(){
		try {
			map = new HashMap<>();
			serverSocket = new ServerSocket(5555);
			ta.append("연결을 기다리는 중입니다.\n");
			while (true) {
				clientSocket = serverSocket.accept();
				new serverThread(this,map,clientSocket,ta,modelAll).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//ui 초기화
	public void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,400);
		setTitle("서버");
		pnl = new JPanel();
		pnl.setLayout(null);
		
		tf = new JTextField();
		tf.addActionListener(this);
		tf.setBounds(0, 330, 400, 30);
		ta =  new JTextArea();
		JScrollPane pane = new JScrollPane(ta);
		pane.setBounds(0, 0, 400, 330);
		pnl.add(pane);
		pnl.add(tf);
		
		
		modelAll = new DefaultTableModel(contentAll,headerAll);	//전체 사용자 목록을 보여줌
		tableAll = new JTable(modelAll);
		JScrollPane pane_tableAll = new JScrollPane(tableAll);
		pane_tableAll.setBounds(400, 0, 200, 360);
		pnl.add(pane_tableAll);
		
		add(pnl);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] split = tf.getText().split(" ");
		if(split[0].equals("/w")){
			ta.append("[공지 (귓속말:"+split[1]+"에게)]  : "+tf.getText().substring(tf.getText().indexOf(" ", 3)+1)+"\n");
			map.get(split[1]).println("[공지 (귓속말)]  : "+tf.getText().substring(tf.getText().indexOf(" ", 3)+1)+" /server");
		}else{
			ta.append("[공지] : "+tf.getText()+"\n");
			for(Map.Entry<String, PrintWriter> entry: map.entrySet()){
				entry.getValue().println("[공지] : "+tf.getText()+" /server");
			}
		}
		tf.selectAll();
	} 
	
	public HashMap<String, Room> getRoomMap(){
		return roomMap;
	}
	
	public void createNewRoom(String[] split){
		roomMap.put(split[3], new Room(split[2],map.get(split[2]),split[1]));
	}
}
class serverThread extends Thread{
	HashMap<String, PrintWriter> map;	//현재 접속자
	Socket clientSocket;				//나중에 닫으려고
	JTextArea ta;						//서버 창
	DefaultTableModel modelAll;			//모든 접속자 테이블 모델
	BufferedReader br;
	PrintWriter pw;
	String id;							
	ChatServerPrac chatServerPrac;
	public serverThread(ChatServerPrac chatServerPrac,HashMap<String, PrintWriter> map,Socket clientSocket, JTextArea ta,DefaultTableModel modelAll) {
		this.map =map;
		this.clientSocket = clientSocket;
		this.ta = ta;
		this.modelAll =modelAll;
		this.chatServerPrac =chatServerPrac;
		
		
		try {
			pw = new PrintWriter(clientSocket.getOutputStream(),true);
			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			id = br.readLine();
			ta.append(id+"님이 입장하셨습니다."+"\n");
			synchronized (map) {
				//map에 더하기 전에 기존 멤버들에게 입장을 알림
				for(Map.Entry<String, PrintWriter> entry: map.entrySet()){
					entry.getValue().println("/uin "+id);
					entry.getValue().println(id+"님이 입장하셨습니다. /room");
				}
				//새로 입장한 멤버에게 기존 멤버 명단을 뿌려줌
				for(String s:map.keySet()){
					pw.println("/user " +s);
				}
				map.put(id, pw);
			}
			modelAll.addRow(new String[]{id.toString()});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		String msg = null;
		String[] split = null;
			try {
				while((msg = br.readLine())!=null){
					split = msg.split(" ");
					if(split[split.length-1].equals("/room")){	//대기실에서 보낸 메시지
						if(split[0].equals("/createRoom")){		//새로운 방 생성
							createNewRoom(split);				
						}else{
							broadcast(id+" : "+msg);	//대기실에서 메시지 보내기
							ta.append(id+" : "+msg+"\n");
						}
					}else if(split[0].equals("/exitRoom")){		//대화방에서 나갈때
						if(chatServerPrac.getRoomMap().get(split[1]).getOwner().equals(id)&&chatServerPrac.getRoomMap().get(split[1]).getParticipants().size()!=1){	//방장이 나갈때
							//방장 권한 넘기기
							pw.println("/routOwner "+id+" /room "+split[1]);
						}else if(chatServerPrac.getRoomMap().get(split[1]).getParticipants().size()!=1){	//일반 멤버가 나갈때
							chatServerPrac.getRoomMap().get(split[1]).removeMem(id);
							broadcastRoom("/rout "+id+" /room "+split[1],split[1]);
						}else{	
							chatServerPrac.getRoomMap().remove(split[1]);
							broadcast("/removeRoom "+split[1]);
						}
					}else if(split[0].equals("/enterRoom")){	//대화방 입장시
						broadcastRoom("/rin "+id+" /room "+split[1],split[1]);
						informRoomUser(split[1]);
						chatServerPrac.getRoomMap().get(split[1]).addMem(id, pw);
					}else if(split[0].equals("/newOwner")){
						chatServerPrac.getRoomMap().get(split[split.length-1]).setOwner(split[1]);
						chatServerPrac.getRoomMap().get(split[split.length-1]).removeMem(id);
						broadcastRoom("/rout "+id+" /room "+split[3],split[3]);
					}else if(split[split.length-2].equals("/room")){	
						broadcastRoom(id+" : "+msg,split[split.length-1]);
					}
				}
			} catch (IOException e) {
				map.remove(id);
				synchronized (map) {
					ta.append(id+"님이 나가셨습니다."+"\n");
					for(Map.Entry<String, PrintWriter> entry:map.entrySet()){
						entry.getValue().println("/uout "+id);
						entry.getValue().println(id+"님이 나가셨습니다. /room");
					}
				}
				for (int i = 0; i < modelAll.getRowCount(); i++) {
					if(modelAll.getValueAt(i, 0).equals(id)){
						modelAll.removeRow(i);
						break;
					}
				}
			}
	}
	public void broadcastRoom(String msg,String roomNum){
		for(Map.Entry<String, PrintWriter> entry:chatServerPrac.getRoomMap().get(roomNum).getParticipants().entrySet()){
			entry.getValue().println(msg);
		}
	}
	public void informRoomUser(String roomNum){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Map.Entry<String, PrintWriter> entry:chatServerPrac.getRoomMap().get(roomNum).getParticipants().entrySet()){
			pw.println("/roomuser "+entry.getKey()+" /room "+roomNum);
		}
		
	}
	public void broadcast(String msg){
		synchronized (map) {
			for(Map.Entry<String, PrintWriter> entry: map.entrySet()){
				entry.getValue().println(msg);
			}
		}
	}
	public void createNewRoom(String[] split){
		chatServerPrac.createNewRoom(split);
		synchronized (map) {
			for(Map.Entry<String, PrintWriter> entry:map.entrySet()){
				entry.getValue().println("/newRoom "+split[1]+" "+split[3]);
			}
		}
	}
}