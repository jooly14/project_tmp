import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class BlockRenderer extends DefaultTableCellRenderer {
	ChatClientPrac chatClientPrac;
	public BlockRenderer(ChatClientPrac chatClientPrac) {
		this.chatClientPrac = chatClientPrac;
	}
   @Override
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	   Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
		   if(chatClientPrac.blockedChk(value)){
			   cell.setBackground(Color.LIGHT_GRAY);
		   }else{
			   cell.setBackground(Color.white);
		   }
       return cell;
   }
}