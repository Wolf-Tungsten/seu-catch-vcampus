package com.wolfTungsten.vcampusClient.component;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class SCTableCellRendererAdd implements TableCellRenderer {
	private JPanel panel;
	private JButton button;
	public SCTableCellRendererAdd() {
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
		// TODO �Զ����ɵķ������
		this.button.setText("�γ�ѡ��");
		return this.panel;
	}

}