package com.wolfTungsten.vcampusClient.component;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MMTableCellRendererDelete implements TableCellRenderer{
	private JPanel panel;
	private JButton button;
	public MMTableCellRendererDelete() {
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
		this.button.setBounds(0,0,75,20);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO 自动生成的方法存根
		this.button.setText("删除课程");
		return this.panel;
	}

}
