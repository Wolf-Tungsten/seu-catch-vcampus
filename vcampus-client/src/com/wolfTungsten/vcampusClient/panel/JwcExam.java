	//教务处——考试助手面板
package com.wolfTungsten.vcampusClient.panel;

import java.awt.Font;
import java.lang.reflect.Array;
import java.util.ArrayList;

import com.google.gson.internal.LinkedTreeMap;
import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class JwcExam extends JPanel{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	String token;

	/**
	 * Create the panel.
	 */
	public JwcExam(String Token) {
		token = Token;
		setSize(736,600);
		setLayout(null);
		String[][]tableValues= {};												   //表格数据数组
		String[] columnNames = {"考试课程名称","监考老师","考试地点","考试时间","剩余天数","考试成绩"}; //表格列名 第五列空出来放置选课退课按钮
		tableModel = new DefaultTableModel(tableValues,columnNames);
		table = new JTable(tableModel);
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);                       //居中
		JScrollPane scrollTable = new JScrollPane(table);
		scrollTable.setBounds(35, 100, 666, 465);
		table.setBounds(35, 100, 666, 465);
		add(scrollTable);
		table.getTableHeader().setFont(new Font("微软雅黑",Font.BOLD,14));
		
		//添加 课程 老师 地点 时间 天数 成绩 从数据库里面 
		//考试地点时间老师 全部可以用上课时间任课老师   天数随便编 
		//成绩一开始为null  管理员在实验成绩录入之后显示
		//大概{"名字","老师","地点","时间","天数",""} 开始
		//添加表格列的函数
		//tableModel.addRow(new String[]{}); 
		tableModel.addRow(new String[] {"信号","王世杰","J2-404","14：00-16：00","1",null});
		showExam();
	}
	
	public void showExam() {
		Request request = new Request();
		request.setPath("helper/showStuExam");
		request.setToken(token);
		Response response = Client.fetch(request);
		if(response.getSuccess())
		{
			
			ArrayList<LinkedTreeMap<String, Object>> examList = 
					(ArrayList<LinkedTreeMap<String, Object>>) response.getBody().get("examMaplist");
			for(LinkedTreeMap<String, Object> examInfo:examList)
			{
				String name = (String)examInfo.get("name");
				String lecturer = (String)examInfo.get("lecturer");
				String location = (String)examInfo.get("location");
				//duration是考试经过的时间
				String duration = (String)examInfo.get("duration");
				long starttime = (long)(double)examInfo.get("startTime");
				
				long time = (starttime-System.currentTimeMillis())/3600/1000/24;
				String lastTime = Long.toString(time);			
				//这里没有加成绩信息，
				tableModel.addRow(new String[] {name,lecturer,location,duration,lastTime,null});					
			}
		}
	}

}
