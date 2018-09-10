//教务处——实验助手面板
package com.wolfTungsten.vcampusClient.panel;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JwcExperiment extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Create the panel.
	 */
	public JwcExperiment() {
		setSize(736,600);
		setLayout(null);
		String[][]tableValues= {};												   //表格数据数组
		String[] columnNames = {"实验名称","实验任课老师","实验地点","实验时间","剩余天数","实验成绩"}; 
		tableModel = new DefaultTableModel(tableValues,columnNames);
		table = new JTable(tableModel);
		JScrollPane scrollTable = new JScrollPane(table);
		scrollTable.setBounds(35, 100, 666, 465);
		table.setBounds(35, 100, 666, 465);
		add(scrollTable);
		table.getTableHeader().setFont(new Font("微软雅黑",Font.BOLD,14));
		
		//添加 实验名称 老师 地点 时间 天数 成绩 从数据库里面  地点时间老师全部可以用上课时间任课老师  天数随便编 
		//成绩一开始为空  管理员添加之后显示
		//tableModel.addRow();
		//大概{"名字","老师","地点","时间","天数",""} 开始
	}

}
