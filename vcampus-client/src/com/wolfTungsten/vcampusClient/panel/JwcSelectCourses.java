//教务处——学生选课面板
package com.wolfTungsten.vcampusClient.panel;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;


public class JwcSelectCourses extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public JwcSelectCourses() {
		setSize(736,600);
		setLayout(null);
		
		String[][]tableValues= {};												   //表格数据数组
		String[] columnNames = {"课程名称","任课老师","上课地点","上课时间","    "}; //表格列名 第五列空出来放置选课退课按钮
		table = new JTable(tableValues,columnNames);
		JScrollPane scrollTable = new JScrollPane(table);
		scrollTable.setBounds(35, 100, 666, 465);
		table.setBounds(35, 100, 666, 465);
		add(scrollTable);
		table.getTableHeader().setFont(new Font("微软雅黑",Font.BOLD,14));
		scrollTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);//竖直滚轮总是显示

		}
}
