package server;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class Room {
	HashMap<String,PrintWriter> Participants;	//채팅방에 속한 사용자 목록을 포함 //아이디와 printWriter를 가지고 있음
	String owner;								//방장
	String name;								//방이름
	Vector<String> banned;						//방에서 강퇴된 사람 목록
	
	String password;							//비밀대화방인 경우 비밀번호
	boolean secretRoom;							//비밀대화방인지 아닌지
	public Room(String owner,PrintWriter pw,String name) {	//채팅방 생성
		Participants = new HashMap<>();
		Participants.put(owner,pw);
		this.owner = owner;
		this.name = name;
		banned = new Vector<>();
	}
	
	public Vector<String> getBanned() {
		return banned;
	}

	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, PrintWriter> getParticipants() {
		return Participants;
	}
	public void addMem(String name,PrintWriter pw){
		Participants.put(name,pw);
	}
	public void removeMem(String name){
		Participants.remove(name);
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSecretRoom() {
		return secretRoom;
	}
	public void setSecretRoom(boolean secretRoom) {
		this.secretRoom = secretRoom;
	}
	
}
