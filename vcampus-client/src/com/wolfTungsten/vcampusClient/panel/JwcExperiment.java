//���񴦡���ʵ���������
package com.wolfTungsten.vcampusClient.panel;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JwcExperiment extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public JwcExperiment() {
		setSize(736,600);
		setLayout(null);
		String[][]tableValues= {};												   //�����������
		String[] columnNames = {"ʵ������","ʵ���ο���ʦ","ʵ��ص�","ʵ��ʱ��","ʣ������","ʵ��ɼ�"}; //������� �����пճ�������ѡ���˿ΰ�ť
		table = new JTable(tableValues,columnNames);
		JScrollPane scrollTable = new JScrollPane(table);
		scrollTable.setBounds(35, 100, 666, 465);
		table.setBounds(35, 100, 666, 465);
		add(scrollTable);
		table.getTableHeader().setFont(new Font("΢���ź�",Font.BOLD,14));

	}

}
