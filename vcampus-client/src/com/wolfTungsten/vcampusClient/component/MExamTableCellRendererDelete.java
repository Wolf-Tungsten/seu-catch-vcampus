package com.wolfTungsten.vcampusClient.component;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MExamTableCellRendererDelete implements TableCellRenderer{
	private JPanel panel;
	private JButton button;
	public MExamTableCellRendererDelete() {
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
		this.button.setBounds(0,0,110,20);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO 自动生成的方法存根
		this.button.setText("成绩修改");
		return this.panel;
	}

}
