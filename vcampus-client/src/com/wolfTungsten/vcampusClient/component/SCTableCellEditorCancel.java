package com.wolfTungsten.vcampusClient.component;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SCTableCellEditorCancel extends DefaultCellEditor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JButton button;

	public SCTableCellEditorCancel() {
		super(new JTextField());
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
			//============================================================================在这里添加“取消选择”按钮的事件
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				//触发取消编辑的事件，不会调用tableModel的setValue方法。   
				SCTableCellEditorCancel.this.fireEditingCanceled();  
                // 这里可以做其它操作。   
				//从这里开始----------------------------------------------------
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
			this.button.setText("取消选择");
  
			return this.panel;  
		} 
}
