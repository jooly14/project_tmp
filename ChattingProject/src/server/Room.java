package server;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class Room {
	HashMap<String,PrintWriter> Participants;	//ä�ù濡 ���� ����� ����� ���� //���̵�� printWriter�� ������ ����
	String owner;								//����
	String name;								//���̸�
	Vector<String> banned;						//�濡�� ����� ��� ���
	
	String password;							//��д�ȭ���� ��� ��й�ȣ
	boolean secretRoom;							//��д�ȭ������ �ƴ���
	public Room(String owner,PrintWriter pw,String name) {	//ä�ù� ����
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
