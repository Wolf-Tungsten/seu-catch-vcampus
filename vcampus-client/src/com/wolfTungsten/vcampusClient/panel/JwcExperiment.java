//教务处——实验助手面板
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
		String[][]tableValues= {};												   //表格数据数组
		String[] columnNames = {"实验名称","实验任课老师","实验地点","实验时间","剩余天数","实验成绩"}; //表格列名 第五列空出来放置选课退课按钮
		table = new JTable(tableValues,columnNames);
		JScrollPane scrollTable = new JScrollPane(table);
		scrollTable.setBounds(35, 100, 666, 465);
		table.setBounds(35, 100, 666, 465);
		add(scrollTable);
		table.getTableHeader().setFont(new Font("微软雅黑",Font.BOLD,14));

	}

}
