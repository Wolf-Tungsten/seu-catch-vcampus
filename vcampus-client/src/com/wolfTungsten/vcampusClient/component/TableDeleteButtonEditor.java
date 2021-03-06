
//LibManager()“检索图书”中“删除“这列中用来删除书籍的按钮
package com.wolfTungsten.vcampusClient.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;

public class TableDeleteButtonEditor extends DefaultCellEditor {
	   protected JButton button;
	   private String label;
       private boolean isPushed; 
       private String selectId;
       String deleteID;
       String token;
       public TableDeleteButtonEditor(JCheckBox checkBox,String Token) {
    	
    	   super(checkBox);
    	   token = Token;
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
             if(label.equals("已删除")) {
                 isPushed = false;
                 }
                 else {
                	 isPushed = true;
                 }
             return button;
           }

          //这里是点击button执行的操作 
           public Object getCellEditorValue() {
              if (isPushed) {
                 int op = JOptionPane.showConfirmDialog(null,"请问是否要删除此书？", "提示",JOptionPane.YES_NO_OPTION); 
                 if(op==JOptionPane.YES_OPTION){  
                 System.out.println(selectId+"删除");
                 deleteID=selectId;
                 Client.Request  request = new Request();
                 
                 request.setPath("book/deleteBook");
                 ArrayList<String> uuidlist = new ArrayList<>();
                 uuidlist.add(deleteID);
                 request.getParams().put("uuidList", uuidlist);
                 Response response = Client.fetch(request);
                 if(response.getSuccess())return new String("已删除");
                 else {
                	 JOptionPane.showMessageDialog(null, "删除失败，请联系后台", "删除失败",JOptionPane.ERROR_MESSAGE);
                 }
                 
                        //返回显示”已删除“          	
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
