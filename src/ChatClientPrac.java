import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ChatClientPrac extends JFrame implements ActionListener{
	JPanel pnl;
	JTextArea ta;
	JTextField tf;
	
	NewRoomFrame newRoomFrame;
	
	JButton newRoomBtn;
	JButton enterBtn;
	
	PrintWriter pw;
	Socket socket;
	
	String id;
	
	HashMap<String, NewRoomFrame> roomInfoList;	//현재 접속해 있는 채팅방
	
	String[] headerRoom = {"번호","대화방 이름"};
	String[][] contentRoom = {};
	DefaultTableModel modelRoom;
	JTable tableRoom;
	JScrollPane pane_tableRoom;
	
	String[] headerAll = {"모든 접속자"};
	String[][] contentAll = {};
	DefaultTableModel modelAll;
	JTable tableAll;
	JScrollPane pane_tableAll;
	
	public ChatClientPrac() {
		init();
		setClient();
	}
	public void setClient(){
		try {
			id = JOptionPane.showInputDialog("아이디");
			setTitle(id+"님의 채팅창");
			socket = new Socket("127.0.0.1",5555);
			pw = new PrintWriter(socket.getOutputStream(),true);
			pw.println(id);
			new ClientThread(id, socket, ta, modelAll, this).start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(820,600);
		
		roomInfoList = new HashMap<>();
		
		pnl = new JPanel();
		pnl.setLayout(null);
		
		tf = new JTextField();
		tf.addActionListener(this);
		tf.setBounds(0, 500, 600, 30);
		ta =  new JTextArea();
		JScrollPane pane = new JScrollPane(ta);
		pane.setBounds(0, 350, 600, 150);
		pnl.add(pane);
		pnl.add(tf);
		
		newRoomBtn = new JButton("대화방 만들기");
		newRoomBtn.setBounds(0, 300, 150, 50);
		newRoomBtn.addActionListener(this);
		
		modelRoom = new DefaultTableModel(contentRoom,headerRoom);
		tableRoom = new JTable(modelRoom);
		pane_tableRoom = new JScrollPane(tableRoom);
		pane_tableRoom.setBounds(0, 0, 600, 300);
		pnl.add(pane_tableRoom);
		
		modelAll = new DefaultTableModel(contentAll,headerAll);
		tableAll = new JTable(modelAll);
		pane_tableAll = new JScrollPane(tableAll);
		pane_tableAll.setBounds(600, 0, 200, 480);
		pnl.add(pane_tableAll);
		
		
		
		enterBtn = new JButton("방 입장");
		enterBtn.setBounds(150, 300, 150, 50);
		enterBtn.addActionListener(this);
		
		pnl.add(enterBtn);
		pnl.add(newRoomBtn);
		add(pnl);
		setVisible(true);
		
	}
	public void createRoom(String name, String roomNum){
		newRoomFrame = new NewRoomFrame(this,roomNum,name);
		newRoomFrame.setTitle("채팅방 : "+name+"(채팅명 : "+id+") - "+roomNum);
		pw.println("/createRoom "+name+" "+id+" "+roomNum+" /room");
		roomInfoList.put(roomNum, newRoomFrame);
	}
	public void enterRoom(String name,String roomNum){
		newRoomFrame = new NewRoomFrame(this,roomNum,name);
		roomInfoList.put(roomNum, newRoomFrame);
		newRoomFrame.setTitle("채팅방 : "+name+"(채팅명 : "+id+") - "+roomNum);
	}
	public HashMap<String, NewRoomFrame> getRoomInfoList() {
		return roomInfoList;
	}
	public NewRoomFrame getNewRoomPnl(){
		return newRoomFrame;
	}
	public static void main(String[] args) {
		new ChatClientPrac();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==newRoomBtn){
			new ChatNewRoomDialog(this);
		}else if(e.getSource()== enterBtn){
			if(tableRoom.getSelectedRow()!=-1){
				enterRoom(tableRoom.getValueAt(tableRoom.getSelectedRow(), 1).toString(),tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).toString());
				pw.println("/enterRoom "+tableRoom.getValueAt(tableRoom.getSelectedRow(), 0).toString());
			}
		}else{
			pw.println(tf.getText()+" /room");
			tf.selectAll();
			
		}
	}
	public void chatInRoom(String msg,String roomNum){
		pw.println(msg+" /room "+roomNum);
	}
	public void exitRoom(String roomNum){
		pw.println("/exitRoom "+roomNum);
	}
	public JTable getTableRoom() {
		return tableRoom;
	}
	public JTable getTableAll() {
		return tableAll;
	}
	public DefaultTableModel getModelRoom() {
		return modelRoom;
	}
	public DefaultTableModel getModelAll() {
		return modelAll;
	}
	public String getId() {
		return id;
	}
	
	
}
class ClientThread extends Thread{
	Socket socket;
	JTextArea ta;
	DefaultTableModel model;
	BufferedReader br;
	PrintWriter pw;
	ChatClientPrac chatClientPrac;
	public ClientThread(String id,Socket socket, JTextArea ta,DefaultTableModel model,ChatClientPrac chatClientPrac) {
		this.socket = socket;
		this.ta = ta;
		this.model = model;
		this.chatClientPrac =chatClientPrac;
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		String msg;
		String[] split = null;
		try {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while ((msg = br.readLine())!=null) {
				System.out.println(msg);
				split = msg.split(" ");
				if(split[split.length-1].equals("/server")){	//서버에서 온 메시지는 모든 방에 뿌리기
					ta.append(msg.substring(0,msg.length()-8));
					for(Map.Entry<String, NewRoomFrame> entry : chatClientPrac.getRoomInfoList().entrySet()){
						entry.getValue().getTa().append(msg.substring(0,msg.length()-8)+"\n");
					}
				}else if(split[0].equals("/newRoom")){	//새로운 방이 생기면 대기실에 방 목록 변경
					chatClientPrac.getModelRoom().addRow(new String[]{split[2],split[1]});
				}else if(split[0].equals("/removeRoom")){	//방이 사라지면 대기실 방 목록 변경
					for (int i = 0; i < chatClientPrac.getModelRoom().getRowCount(); i++) {
						if(chatClientPrac.getModelRoom().getValueAt(i, 0).equals(split[1])){
							chatClientPrac.getModelRoom().removeRow(i);
						}
					}
				}else if(split[0].equals("/uin")){
						chatClientPrac.getModelAll().addRow(new String[]{split[1]});
				}else if(split[0].equals("/uout")){
					for (int i = 0; i < chatClientPrac.getModelAll().getRowCount(); i++) {
						if(chatClientPrac.getModelAll().getValueAt(i, 0).equals(split[1])){
							chatClientPrac.getModelAll().removeRow(i);
							break;
						}
					}
				}else if(split[0].equals("/user")){
					chatClientPrac.getModelAll().addRow(new String[]{split[1]});
				}else if(split[split.length-1].equals("/room")){
					ta.append(msg.substring(0, msg.length()-6)+"\n");
				}else if(split[0].equals("/roomuser")){
					chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().addRow(new String[]{split[1]});
				}else if(split[0].equals("/rin")){
					chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().addRow(new String[]{split[1]});
				}else if(split[0].equals("/rout")){
					for (int i = 0; i < chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().getRowCount(); i++) {
						if(chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().getValueAt(i, 0).equals(split[1])){
							chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().removeRow(i);
							break;
						}
					}
				}else if(split[0].equals("/routOwner")){
					//새로운 방장 선정
					pw.println("/newOwner "+chatClientPrac.getRoomInfoList().get(split[split.length-1]).getModelRoom().getValueAt(0, 0)+" /room "+split[split.length-1]);
				}else if(split[split.length-1].equals("/room")){
					ta.append(msg.substring(0,msg.length()-6)+"\n");
				}else if(split[split.length-2].equals("/room")){
					chatClientPrac.getRoomInfoList().get(split[split.length-1]).getTa().append(msg.substring(0,msg.indexOf("/room")-1)+"\n");
				}
					
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
