import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyRenderer extends DefaultTableCellRenderer {
	ChatClientPrac chatClientPrac;
	public MyRenderer(ChatClientPrac chatClientPrac) {
		this.chatClientPrac = chatClientPrac;
	}
   @Override
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	   Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
	   if(column==0){
		   if(chatClientPrac.alreadyInChk(value)){
			   cell.setBackground(Color.cyan);
		   }else{
			   cell.setBackground(Color.white);
		   }
	   }
       return cell;
   }
}