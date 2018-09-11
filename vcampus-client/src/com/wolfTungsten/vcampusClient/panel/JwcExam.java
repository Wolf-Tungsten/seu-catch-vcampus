//教务处——考试助手面板
package com.wolfTungsten.vcampusClient.panel;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JwcExam extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Create the panel.
	 */
	public JwcExam() {
		setSize(736,600);
		setLayout(null);
		String[][]tableValues= {};												   //表格数据数组
		String[] columnNames = {"考试课程名称","监考老师","考试地点","考试时间","剩余天数","考试成绩"}; //表格列名 第五列空出来放置选课退课按钮
		tableModel = new DefaultTableModel(tableValues,columnNames);
		table = new JTable(tableModel);
		JScrollPane scrollTable = new JScrollPane(table);
		scrollTable.setBounds(35, 100, 666, 465);
		table.setBounds(35, 100, 666, 465);
		add(scrollTable);
		table.getTableHeader().setFont(new Font("微软雅黑",Font.BOLD,14));
		
		//添加 课程 老师 地点 时间 天数 成绩 从数据库里面
		//成绩一开始为空  管理员添加之后显示
		//tableModel.addRow(); 
		//大概{"名字","老师","地点","时间","天数",""} 开始
	}

}
