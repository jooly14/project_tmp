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
import java.util.HashSet;
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
	
	String[] headerAll = {"��� ������"};
	String[][] contentAll = {};
	DefaultTableModel modelAll;
	JTable tableAll;
	
	HashMap<String, Room> roomMap = new HashMap<>();	//�濡 ���� ������ ��� �ִ�. Ű�� ���ȣ
	
	ServerSocket serverSocket;
	Socket clientSocket;
	HashMap<String, PrintWriter> map;	//client�� id�� printwriter�� ������ �ִ�.
	
	int roomNum;
	//�ʱ�ȭ
	public ChatServerPrac() {
		init();	
		setServer();
	}
	//Ŭ���̾�Ʈ�κ��� ������ �޾ƿ�
	public void setServer(){
		try {
			map = new HashMap<>();
			serverSocket = new ServerSocket(5555);
			ta.append("������ ��ٸ��� ���Դϴ�.\n");
			while (true) {
				clientSocket = serverSocket.accept();
				new serverThread(this,map,clientSocket,ta,modelAll).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//ui �ʱ�ȭ
	public void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,400);
		setTitle("����");
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
		
		
		modelAll = new DefaultTableModel(contentAll,headerAll);	//��ü ����� ����� ������
		tableAll = new JTable(modelAll);
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
			ta.append("[���� (�ӼӸ�:"+split[1]+"����)]  : "+tf.getText().substring(tf.getText().indexOf(" ", 3)+1)+"\n");
			ta.setCaretPosition(ta.getDocument().getLength());
			map.get(split[1]).println("[���� (�ӼӸ�)]  : "+tf.getText().substring(tf.getText().indexOf(" ", 3)+1)+" /server");
		}else{
			ta.append("[����] : "+tf.getText()+"\n");
			ta.setCaretPosition(ta.getDocument().getLength());
			for(Map.Entry<String, PrintWriter> entry: map.entrySet()){
				entry.getValue().println("[����] : "+tf.getText()+" /server");
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
	public int getRoomNum() {
		return roomNum++;
	}
}
class serverThread extends Thread{
	HashMap<String, PrintWriter> map;	//���� ������
	Socket clientSocket;				//���߿� ��������
	JTextArea ta;						//���� â
	DefaultTableModel modelAll;			//��� ������ ���̺� ��
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
			
			ta.append(id+"���� �����ϼ̽��ϴ�."+"\n");
			ta.setCaretPosition(ta.getDocument().getLength());
			synchronized (map) {
				//map�� ���ϱ� ���� ���� ����鿡�� ������ �˸�
				for(Map.Entry<String, PrintWriter> entry: map.entrySet()){
					entry.getValue().println("/uin "+id);
					entry.getValue().println(id+"���� �����ϼ̽��ϴ�. /room");
				}
				//���� ������ ������� ���� ��� ����� �ѷ���
				for(String s:map.keySet()){
					pw.println("/user " +s);
				}
				map.put(id, pw);
			}
			synchronized (chatServerPrac.getRoomMap()) {
				System.out.println(chatServerPrac.getRoomMap().size());
				for(Map.Entry<String, Room> entry: chatServerPrac.getRoomMap().entrySet()){
					pw.println("/roomlist "+entry.getKey()+" "+entry.getValue().getName());
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
					if(split[split.length-1].equals("/room")){	//���ǿ��� ���� �޽���
						if(split[0].equals("/createRoom")){		//���ο� �� ����
							createNewRoom(split);				
						}else{
							broadcast(id+" : "+msg);	//���ǿ��� �޽��� ������
							ta.append(id+" : "+msg+"\n");
						}
					}else if(split[0].equals("/exitRoom")){		//��ȭ�濡�� ������
						if(chatServerPrac.getRoomMap().get(split[1]).getOwner().equals(id)&&chatServerPrac.getRoomMap().get(split[1]).getParticipants().size()!=1){	//������ ������
							//���� ���� �ѱ��
							pw.println("/routOwner "+id+" /room "+split[1]);
						}else if(chatServerPrac.getRoomMap().get(split[1]).getParticipants().size()!=1){	//�Ϲ� ����� ������
							chatServerPrac.getRoomMap().get(split[1]).removeMem(id);
							broadcastRoom("/rout "+id+" /room "+split[1],split[1]);
							pw.println("/routOk "+split[1]);	//���� ���������� �ʿ��� �κ�
						}else{	//���� ȥ�� ���� �濡�� ������
							chatServerPrac.getRoomMap().remove(split[1]);
							broadcast("/removeRoom "+split[1]);
							pw.println("/routOk "+split[1]);	//���� ���������� �ʿ��� �κ�
						}
					}else if(split[0].equals("/enterRoom")){	//��ȭ�� �����
						broadcastRoom("/rin "+id+" /room "+split[1],split[1]);
						informRoomUser(split[1]);
						informRoomOwner(split[1]);
						chatServerPrac.getRoomMap().get(split[1]).addMem(id, pw);
					}else if(split[0].equals("/newOwner")){
						chatServerPrac.getRoomMap().get(split[split.length-1]).setOwner(split[1]);
						chatServerPrac.getRoomMap().get(split[split.length-1]).removeMem(id);
						broadcastRoom("/rchgOwner "+split[1]+" /room "+split[3],split[3]);
						broadcastRoom("/rout "+id+" /room "+split[3],split[3]);
					}else if(split[split.length-2].equals("/room")){	
						
						if(chatServerPrac.getRoomMap().get(split[split.length-1]).getOwner().equals(id)){
							broadcastRoom(id+"(����) : "+msg,split[split.length-1]);
						}else{
							broadcastRoom(id+" : "+msg,split[split.length-1]);
						}
					}
				}
			} catch (IOException e) {
				Vector<String> removeRoomNum = new Vector<>();
				//���ڱ� ���������� �濡���� �����ߵ�
				for(Map.Entry<String, Room> entry : chatServerPrac.getRoomMap().entrySet()){
					if(entry.getValue().getParticipants().get(id)!=null){
						if(entry.getValue().getOwner().equals(id)&&entry.getValue().getParticipants().size()!=1){	//������ ������
							//���� ���� �ѱ��
							for(Map.Entry<String, PrintWriter> entry2 : entry.getValue().getParticipants().entrySet()){
								if(!entry2.getKey().equals(id)){
									entry.getValue().setOwner(entry2.getKey());
									break;
								}
							}
							broadcastRoom("/rchgOwner "+entry.getValue().getOwner()+" /room "+entry.getKey(),entry.getKey());
							broadcastRoom("/rout "+id+" /room "+entry.getKey(),entry.getKey());
						}else if(entry.getValue().getParticipants().size()!=1){	//�Ϲ� ����� ������
							broadcastRoom("/rout "+id+" /room "+entry.getKey(),entry.getKey());
						}else{	//���� ȥ�� ���� �濡�� ������
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
					ta.append(id+"���� �����̽��ϴ�."+"\n");
					ta.setCaretPosition(ta.getDocument().getLength());
					for(Map.Entry<String, PrintWriter> entry:map.entrySet()){
						entry.getValue().println("/uout "+id);
						entry.getValue().println(id+"���� �����̽��ϴ�. /room");
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
}