package com.wolfTungsten.vcampusClient.component;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyDeleteButtonRender implements TableCellRenderer{

	 private JButton button;
	    private JPanel panel;
	    public MyDeleteButtonRender() {
	    	this.initButton();
	    	this.initPanel();
	    	this.panel.add(this.button);
	    }
		private void initPanel() {
			// TODO Auto-generated method stub
			this.panel=new JPanel();
			this.panel.setLayout(null);
		}
		private void initButton() {
			// TODO Auto-generated method stub
			this.button=new JButton();
			this.button.setBounds(0,0,85,27);
			this.button.setFont(new Font("微软雅黑", Font.BOLD, 14));
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			this.button.setText(value==null?"":String.valueOf(value));
			return this.panel;
		}

}
