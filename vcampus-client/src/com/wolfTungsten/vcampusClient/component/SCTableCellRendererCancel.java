package com.wolfTungsten.vcampusClient.component;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class SCTableCellRendererCancel implements TableCellRenderer {
	private JPanel panel;
	private JButton button;
	public SCTableCellRendererCancel() {
		this.initPanel();
		this.initButton();
		this.panel.add(this.button);
	}
	private void initPanel() {
		this.panel =new JPanel();
		this.panel.setLayout(null);
	}
	private void initButton() {
		this.button = new JButton();
		this.button.setBounds(0,0,100,20);
	}
	

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO 自动生成的方法存根
		if(String.valueOf(value).equals("已选择")) {
			this.button.setEnabled(true);
			this.button.setText("取消选择");
		}
		else if (String.valueOf(value).equals("未选择")) {
			this.button.setEnabled(false);
			this.button.setText("无法取消选择");
		}
		return this.panel;
	}

}