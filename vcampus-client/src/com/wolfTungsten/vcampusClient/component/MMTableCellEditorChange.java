package com.wolfTungsten.vcampusClient.component;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MMTableCellEditorChange extends DefaultCellEditor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton button;

	public MMTableCellEditorChange() {
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
		this.button.setBounds(0,0,75,20);
		
		this.button.addActionListener(new ActionListener()
        {  
			@Override
			//==========================================================================================��������ӡ��γ�ѡ�񡱰�ť���¼�
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				//触发取消编辑的事件，不会调用tableModel的setValue方法
				MMTableCellEditorChange.this.fireEditingCanceled();  
                //从这里开始其他操作 
				
				
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
			this.button.setText("修改信息");
  
			return this.panel;  
		} 
}


