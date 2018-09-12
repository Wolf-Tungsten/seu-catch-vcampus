package com.wolfTungsten.vcampusClient.component;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;

public class SCTableCellEditorAdd extends DefaultCellEditor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JButton button;

	private boolean isCourseSelected;

	private String label;



	private String token;
	private String selectId;
	public SCTableCellEditorAdd(String Token) {
		

		super(new JTextField());
		token = Token;
		// TODO 自动生成的构造函数存根

		this.setClickCountToStart(1);
		this.initButton();
		this.initPanel();
		this.panel.add(this.button);
		}

		private void initButton() {

		// TODO 自动生成的方法存根

		this.button = new JButton();  
		this.button.setBounds(0,0,100,20);
		
		
		
		
		this.button.addActionListener(new ActionListener()
        {  
			@Override
			//==========================================================================================��������ӡ��γ�ѡ�񡱰�ť���¼�
			public void actionPerformed(ActionEvent e) {
       
				// TODO 自动生成的方法存根
				  fireEditingStopped();
				//触发取消编辑的事件，不会调用tableModel的setValue方法
			//	SCTableCellEditorAdd.this.fireEditingCanceled();  
                //从这里开始其他操作 

				Client.Request request = new Request();
				request.setPath("EduAdmin/selCourse");
				request.setToken(token);
				request.getParams().put("uuid", selectId);
				Response response = new Response();
				response =Client.fetch(request);
				if(response.getSuccess()) {
					
					
				}

			} ;
        });
		}
	

		private void initPanel() {
		// TODO 自动生成的方法存根
		this.panel = new JPanel();  
        this.panel.setLayout(null);  
		
		}
		@Override  
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)  
		{  

			if(String.valueOf(value)=="已选择") isCourseSelected = true;
			else if(String.valueOf(value) == "未选择") isCourseSelected = false;
			label = (value == null) ? "" : value.toString(); 
			
			if (isCourseSelected = true) table.getModel().setValueAt("已选择", row, column);
			else if(isCourseSelected = false) table.getModel().setValueAt("未选择", row, column);

			this.button.setText("添加课程");
			if (isSelected) {
	               button.setForeground(table.getSelectionForeground());
	               button.setBackground(table.getSelectionBackground());
	             } else {
	               button.setForeground(table.getForeground());
	               button.setBackground(table.getBackground());
	             }
			selectId = table.getValueAt(row, 0).toString();
			
			
  

			return this.panel;  
		} 
		 public Object getCellEditorValue() {
			 if(!isCourseSelected) 
			 {
				//如果该课程是未选择状态
				int isOK = JOptionPane.showConfirmDialog(null, "确定选择", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
				if(isOK == JOptionPane.YES_OPTION)
				{
					//确认操作
					
					
					return new String("已选择");
					
				
				}

			}
			 isCourseSelected = true;
			return new String (label);


			 
		 }
}

