//教务处——考试助手面板
package com.wolfTungsten.vcampusClient.panel;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JwcExam extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public JwcExam() {
		setSize(736,600);
		setLayout(null);
		String[][]tableValues= {};												   //表格数据数组
		String[] columnNames = {"考试课程名称","监考老师","考试地点","考试时间","剩余天数","考试成绩"}; //表格列名 第五列空出来放置选课退课按钮
		table = new JTable(tableValues,columnNames);
		JScrollPane scrollTable = new JScrollPane(table);
		scrollTable.setBounds(35, 100, 666, 465);
		table.setBounds(35, 100, 666, 465);
		add(scrollTable);
		table.getTableHeader().setFont(new Font("微软雅黑",Font.BOLD,14));

	}

}
