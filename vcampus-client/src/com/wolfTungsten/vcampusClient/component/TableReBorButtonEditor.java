package com.wolfTungsten.vcampusClient.component;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class TableReBorButtonEditor extends DefaultCellEditor{
	   protected JButton button;
	   private String label;
       private boolean isPushed; 
       private String selectId;
       private String token;
       public TableReBorButtonEditor(JCheckBox checkBox,String Token) {
    	   super(checkBox);
    	   token=Token;
           button = new JButton();
           button.setOpaque(true);
           button.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
               fireEditingStopped();
             }
           });
	}
       public Component getTableCellEditorComponent(JTable table, Object value,
               boolean isSelected, int row, int column) {
             if (isSelected) {
               button.setForeground(table.getSelectionForeground());
               button.setBackground(table.getSelectionBackground());
             } else {
               button.setForeground(table.getForeground());
               button.setBackground(table.getBackground());
             }
             label = (value == null) ? "" : value.toString();
             //get the value of the first cell in this selected row
             //被选中行的第一列数的值赋予selectId，即书的编号
             selectId = table.getValueAt(row, 0).toString();
             if(label.equals("续借")) {
             isPushed = true;
             }
             else {
            	 isPushed = false;
             }
             return button;
           }

          //这里是点击借阅button执行的操作 
                 public Object getCellEditorValue() {
             if (isPushed) {
               
                                 //JOptionPane.showConfirmDialog(null, "请问是否要借阅此书？", "提示", JOptionPane.YES_NO_OPTION);
                                 //JOptionPane.showMessageDialog(parentComponent, message, title, messageType, icon);
                                 int op = JOptionPane.showConfirmDialog(null,"请问是否要续借此书？", "提示",JOptionPane.YES_NO_OPTION); 
                                 if(op==JOptionPane.YES_OPTION){  
                                	 //续借请求
                                	 System.out.println(selectId+"续借");
                                	 return new String("不可续借");   //一本书只能续借一次        	
                                 }else if(op==JOptionPane.NO_OPTION){    
                                	 
                                 } 
             }
             isPushed = false;
             return new String(label);
           }

           public boolean stopCellEditing() {
             isPushed = false;
             return super.stopCellEditing();
           }

           protected void fireEditingStopped() {
             super.fireEditingStopped();
           }
         
}

