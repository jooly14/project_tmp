package client;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyRenderer extends DefaultTableCellRenderer {
	ChatClient chatClient;
	public MyRenderer(ChatClient chatClient) {
		this.chatClient = chatClient;
	}
   @Override
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	   Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
	   if(column==0){
		   if(chatClient.bannedRoomChk(value)){
			   cell.setBackground(Color.lightGray);
		   }else if(chatClient.alreadyInChk(value)){
			   cell.setBackground(Color.yellow);
		   }else{
			   cell.setBackground(Color.white);
		   }
	   }
       return cell;
   }
}