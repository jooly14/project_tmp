import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ChatRoomDetailDialog extends JDialog{
	ChatClientPrac chatClientPrac;
	JLabel lblNumAndName, lblOwner;
	JTable tableParticipants;
	DefaultTableModel modelParticipants;
	String[] headerParticipants = {"盲泼规 立加磊"};
	public ChatRoomDetailDialog(ChatClientPrac chatClientPrac,String[] split) {
		super(chatClientPrac,true);
		this.chatClientPrac = chatClientPrac;
		lblNumAndName = new JLabel("["+split[1]+"] "+split[2]);
		lblOwner = new JLabel("规厘 : " + split[4]);
		
		if(split[3].equals("YES")){
			secretRoom(split);
		}else{
			notSecretRoom(split);
		}
		setVisible(true);
	}
	public void secretRoom(String[] split){
		setSize(400,500);
		
	}
	public void notSecretRoom(String[] split){
		setSize(400,500);
		
	}
}けけけ
