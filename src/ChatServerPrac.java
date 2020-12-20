import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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
	
	int roomNum;
	
	
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
		
		
		modelAll = new DefaultTableModel(contentAll,headerAll){
			public boolean isCellEditable(int rowIndex, int colIndex){
				return false;
			}
		};	//전체 사용자 목록을 보여줌
		tableAll = new JTable(modelAll);
		tableAll.getTableHeader().setReorderingAllowed(false);
		tableAll.getTableHeader().setResizingAllowed(false);
		JScrollPane pane_tableAll = new JScrollPane(tableAll);
		pane_tableAll.setBounds(400, 0, 200, 360);
		pnl.add(pane_tableAll);
		
		
		Image img = Toolkit.getDefaultToolkit().getImage("big6.png");
		img = img.getScaledInstance(15, 15, Image.SCALE_DEFAULT);
		setIconImage(img);
		
		add(pnl);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] split = tf.getText().split(" ");
		if(split[0].equals("/w")){
			ta.append("[귓속말:"+split[1]+"에게] [공지]  : "+tf.getText().substring(tf.getText().indexOf(" ", 3)+1)+"\n");
			ta.setCaretPosition(ta.getDocument().getLength());
			map.get(split[1]).println("[귓속말] [공지]  : "+tf.getText().substring(tf.getText().indexOf(" ", 3)+1)+" /server");
		}else{
			ta.append("[공지] : "+tf.getText()+"\n");
			ta.setCaretPosition(ta.getDocument().getLength());
			for(Map.Entry<String, PrintWriter> entry: map.entrySet()){
				entry.getValue().println("[공지] : "+tf.getText()+" /server");
			}
		}
		tf.selectAll();
	} 
	
	public HashMap<String, Room> getRoomMap(){
		return roomMap;
	}
	
	public void createNewRoom(String[] split,String roomNum){
		roomMap.put(roomNum, new Room(split[2],map.get(split[2]),split[1]));
	}
	public void createNewSecretRoom(String[] split, String roomNum){
		roomMap.put(roomNum, new Room(split[2], map.get(split[2]), split[1]));
		roomMap.get(roomNum).setSecretRoom(true);
		roomMap.get(roomNum).setPassword(split[3]);
	}
	public int getRoomNum() {
		return roomNum++;
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
			boolean exist = true;
			while(exist){
				exist = false;
				id = br.readLine();
				System.out.println(id);
				for (int i = 0; i < chatServerPrac.modelAll.getRowCount(); i++) {
					if(chatServerPrac.modelAll.getValueAt(i, 0).equals(id)){
						pw.println("/exist");
						exist = true;
						break;
					}
				}
			}
			pw.println("/comein");
			
			ta.append(id+"님이 입장하셨습니다."+"\n");
			ta.setCaretPosition(ta.getDocument().getLength());
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
			synchronized (chatServerPrac.getRoomMap()) {
				System.out.println(chatServerPrac.getRoomMap().size());
				for(Map.Entry<String, Room> entry: chatServerPrac.getRoomMap().entrySet()){
					pw.println("/roomlist "+entry.getKey()+" "+entry.getValue().getName()+" "+(entry.getValue().isSecretRoom()?"YES":"NO"));
				}
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
					ta.append(msg+"\n");
					ta.setCaretPosition(ta.getDocument().getLength());
					split = msg.split(" ");
					if(split[split.length-1].equals("/room")){	//대기실에서 보낸 메시지
						if(split[0].equals("/createRoom")){		//새로운 방 생성
							createNewRoom(split);				
						}else if(split[0].equals("/createSecretRoom")){
							createSecretRoom(split);
						}else if(split[0].equals("/w")){
							map.get(split[1]).println("[귓속말] "+id+" : "+msg.substring(msg.indexOf(split[2])));
						}else{
							broadcast(id+" : "+msg);	//대기실에서 메시지 보내기
							ta.append(id+" : "+msg+"\n");
						}
					}else if(split[0].equals("/ban")){
						if(chatServerPrac.getRoomMap().get(split[3]).getOwner().equals(id)){
							for(Map.Entry<String, PrintWriter> entry : chatServerPrac.getRoomMap().get(split[3]).getParticipants().entrySet()){
								if(entry.getKey().equals(split[1])){
									entry.getValue().println("/banned "+entry.getKey()+" /room "+split[3]);
								}else{
									entry.getValue().println(msg);
								}
							}
							chatServerPrac.getRoomMap().get(split[3]).getParticipants().remove(split[1]);
							chatServerPrac.getRoomMap().get(split[3]).getBanned().add(split[1]);
						}else{
							pw.println("방장만 강퇴 기능을 사용하실 수 있습니다. /room "+split[3]);
						}
					}else if(split[0].equals("/exitRoom")){		//대화방에서 나갈때
						if(chatServerPrac.getRoomMap().get(split[1]).getOwner().equals(id)&&chatServerPrac.getRoomMap().get(split[1]).getParticipants().size()!=1){	//방장이 나갈때
							//방장 권한 넘기기
							pw.println("/routOwner "+id+" /room "+split[1]);
						}else if(chatServerPrac.getRoomMap().get(split[1]).getParticipants().size()!=1){	//일반 멤버가 나갈때
							chatServerPrac.getRoomMap().get(split[1]).removeMem(id);
							broadcastRoom("/rout "+id+" /room "+split[1],split[1]);
							pw.println("/routOk "+split[1]);	//진행 순서문제로 필요한 부분
						}else{	//방장 혼자 남은 방에서 나갈때
							chatServerPrac.getRoomMap().remove(split[1]);
							broadcast("/removeRoom "+split[1]);
							pw.println("/routOk "+split[1]);	//진행 순서문제로 필요한 부분
						}
					}else if(split[0].equals("/beforeEnterRoom")){
						if(chatServerPrac.getRoomMap().get(split[1]).getBanned().contains(id)){
							pw.println("/roomBanned");
						}else{
							String participants = "";
							for(Map.Entry<String, PrintWriter> entry : chatServerPrac.getRoomMap().get(split[1]).getParticipants().entrySet()){
								participants += entry.getKey()+" ";
							}
							pw.println("/roomDetail "+split[1]+" "+chatServerPrac.getRoomMap().get(split[1]).getName()
									+" "+(chatServerPrac.getRoomMap().get(split[1]).isSecretRoom()?"YES":"NO")
									+" "+chatServerPrac.getRoomMap().get(split[1]).getOwner()
									+" "+participants);
						}
					}else if(split[0].equals("/enterRoom")){	//대화방 입장시
						
							pw.println("/enterOk "+chatServerPrac.getRoomMap().get(split[1]).getName()+" /room "+split[1]);
							broadcastRoom("/rin "+id+" /room "+split[1],split[1]);
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							informRoomUser(split[1]);
							informRoomOwner(split[1]);
							chatServerPrac.getRoomMap().get(split[1]).addMem(id, pw);
					}else if(split[0].equals("/newOwner")){
						chatServerPrac.getRoomMap().get(split[split.length-1]).setOwner(split[1]);
						chatServerPrac.getRoomMap().get(split[split.length-1]).removeMem(id);
						broadcastRoom("/rchgOwner "+split[1]+" /room "+split[3],split[3]);
						broadcastRoom("/rout "+id+" /room "+split[3],split[3]);
					}else if(split[0].equals("/ca")){
						if(chatServerPrac.getRoomMap().get(split[3]).getOwner().equals(id)){
							chatServerPrac.getRoomMap().get(split[3]).setOwner(split[1]);
							broadcastRoom("/rchgOwner "+split[1]+" /room "+split[3],split[3]);
						}else{
							pw.println("방장만 방장 변경 기능을 사용하실 수 있습니다. /room "+split[3]);
						}
					}else if(split[0].equals("/invite")){
						if(chatServerPrac.getRoomMap().get(split[3]).getBanned().contains(split[1])){
							if(chatServerPrac.getRoomMap().get(split[3]).getOwner().equals(id)){//방장만 강퇴된 사람 초대 가능
								map.get(split[1]).println("/invite "+id+" /room "+split[4]);	//초대한 사람의 아이디와 방의 이름
							}else{
								pw.println("강퇴된 유저는 방장만 초대하실 수 있습니다. /room "+split[3]);
							}
						}else{
							map.get(split[1]).println("/invite "+id+" /room "+split[4]);	//초대한 사람의 아이디와 방의 이름
						}
					}else if(split[split.length-2].equals("/room")){	
						
						if(chatServerPrac.getRoomMap().get(split[split.length-1]).getOwner().equals(id)){
							if(split[0].equals("/w")){
								if(chatServerPrac.getRoomMap().get(split[split.length-1]).getParticipants().containsKey(split[1])){//방멤버가 아닌 사람에게 귓속말을 했을 경우에는 대기실에 해당내용을 보냄
									map.get(split[1]).println("[귓속말] "+id+" (방장) : "+msg.substring(msg.indexOf(split[2])));
								}else{	
									map.get(split[1]).println("[귓속말] "+id+" : "+msg.substring(msg.indexOf(split[2]),msg.indexOf(split[split.length-1])-1));
								}
							}else{
								broadcastRoom(id+" (방장) : "+msg,split[split.length-1]);
							}
						}else{
							if(split[0].equals("/w")){
								if(chatServerPrac.getRoomMap().get(split[split.length-1]).getParticipants().containsKey(split[1])){
									map.get(split[1]).println("[귓속말] "+id+" : "+msg.substring(msg.indexOf(split[2])));
								}else{
									map.get(split[1]).println("[귓속말] "+id+" : "+msg.substring(msg.indexOf(split[2]),msg.indexOf(split[split.length-1])-1));
								}
							}else{
								broadcastRoom(id+" : "+msg,split[split.length-1]);
							}
						}
					}
				}
			} catch (IOException e) {
				Vector<String> removeRoomNum = new Vector<>();
				//갑자기 나가버릴때 방에서도 나가야됨
				for(Map.Entry<String, Room> entry : chatServerPrac.getRoomMap().entrySet()){
					if(entry.getValue().getParticipants().get(id)!=null){
						if(entry.getValue().getOwner().equals(id)&&entry.getValue().getParticipants().size()!=1){	//방장이 나갈때
							//방장 권한 넘기기
							for(Map.Entry<String, PrintWriter> entry2 : entry.getValue().getParticipants().entrySet()){
								if(!entry2.getKey().equals(id)){
									entry.getValue().setOwner(entry2.getKey());
									break;
								}
							}
							broadcastRoom("/rchgOwner "+entry.getValue().getOwner()+" /room "+entry.getKey(),entry.getKey());
							broadcastRoom("/rout "+id+" /room "+entry.getKey(),entry.getKey());
						}else if(entry.getValue().getParticipants().size()!=1){	//일반 멤버가 나갈때
							broadcastRoom("/rout "+id+" /room "+entry.getKey(),entry.getKey());
						}else{	//방장 혼자 남은 방에서 나갈때
							removeRoomNum.add(entry.getKey());
							broadcast("/removeRoom "+entry.getKey());
						}
						entry.getValue().getParticipants().remove(id);
					}

				}
				for (int i = 0; i < removeRoomNum.size(); i++) {
					chatServerPrac.getRoomMap().remove(removeRoomNum.get(i));
				}
				
				
				map.remove(id);
				synchronized (map) {
					ta.append(id+"님이 나가셨습니다."+"\n");
					ta.setCaretPosition(ta.getDocument().getLength());
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
	public void informRoomOwner(String roomNum){
		pw.println("/roomuserO "+chatServerPrac.getRoomMap().get(roomNum).getOwner()+" /room "+roomNum);
	}
	public void broadcast(String msg){
		synchronized (map) {
			for(Map.Entry<String, PrintWriter> entry: map.entrySet()){
				entry.getValue().println(msg);
			}
		}
	}
	public void createNewRoom(String[] split){
		String roomNum = chatServerPrac.getRoomNum()+"";
		pw.println("/roomNum "+roomNum);
		synchronized (map) {
			for(Map.Entry<String, PrintWriter> entry:map.entrySet()){
				entry.getValue().println("/newRoom "+split[1]+" "+roomNum);
			}
		}
		chatServerPrac.createNewRoom(split,roomNum);
	}
	public void createSecretRoom(String[] split){
		String roomNum = chatServerPrac.getRoomNum()+"";
		pw.println("/roomNum "+roomNum);
		synchronized (map) {
			for(Map.Entry<String, PrintWriter> entry:map.entrySet()){
				entry.getValue().println("/newSecretRoom "+split[1]+" "+roomNum);
			}
		}
		chatServerPrac.createNewSecretRoom(split,roomNum);
	}
}