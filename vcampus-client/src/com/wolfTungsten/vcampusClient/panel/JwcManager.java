package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.wolfTungsten.vcampusClient.component.MExamTableCellRendererDelete;
import com.wolfTungsten.vcampusClient.component.MExpTableCellRendererDelete;
import com.wolfTungsten.vcampusClient.component.MMTableCellEditorChange;
import com.wolfTungsten.vcampusClient.component.MMTableCellEditorDelete;
import com.wolfTungsten.vcampusClient.component.MMTableCellRendererChange;
import com.wolfTungsten.vcampusClient.component.MMTableCellRendererDelete;


import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;



public class JwcManager extends JPanel implements  ActionListener, FocusListener , MouseListener{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_courseName;
	private JTextField textField_courseLecture;
	private JTextField textField_courseTime;
	private JTextField textField_coursePlace;
	private JTextField textField_courseScore;
	private JTextField textField_courseCapacity;
	private JTextField textField_courseTestTime;
	private JButton button1,button2,button3,button4;
	private JButton button_courseAdd;
	private CardLayout layout;
	private JPanel cardPanel;
	JRadioButton radioButton_course ;
	JRadioButton radioButton_experiment; 
	ButtonGroup locationGroup;
	JCheckBox checkBox;
	JPanel panel_courseAdd,panel_experiment,panel_exam,panel_courseManage;
	private JTextField textField_search_name,textField_search_lecture,textField_search_courseName,textField_search_courseLecture
	,textField_search_expName,textField_search_expLecture;
	private DefaultTableModel tableModel1,tableModel2,tableModel3;
	private JTable table_manage,table_exam,table_experiment;
	private JScrollPane scrollPane1,scrollPane2,scrollPane3;
	JButton button_manage_search,button_exam_search,button_experiment_search;
	private JComboBox comboBoxTime,comboBoxWeek;

	//----------
	private String token;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked" })
	public JwcManager(String Token) {
		token = Token;
		setSize(736,600);
		setLayout(null);
		
		// 上方panel_guide 下方 panel_card
		//panel_guide
		JPanel panel_guide = new JPanel();
		panel_guide.setLayout(null);
		panel_guide.setBounds(0, 10, 736, 54);
		add(panel_guide);
		
		button1 = new JButton("课程管理");
		button1.setFont(new Font("微软雅黑", Font.BOLD, 16));
		button1.setBounds(75, 12, 100, 26);
		panel_guide.add(button1);
		button1.addActionListener(this);
		
		button2 = new JButton("教务添加");
		button2.setFont(new Font("微软雅黑", Font.BOLD, 16));
		button2.setBounds(237, 12, 100, 26);
		panel_guide.add(button2);
		button2.addActionListener(this);
		
		button3 = new JButton("考试录入");
		button3.setFont(new Font("微软雅黑", Font.BOLD, 16));
		button3.setBounds(399, 12, 100, 26);
		panel_guide.add(button3);
		button3.addActionListener(this);
		
		button4 = new JButton("实验录入");
		button4.setFont(new Font("微软雅黑", Font.BOLD, 16));
		button4.setBounds(561, 12, 100, 26);
		panel_guide.add(button4);
		button4.addActionListener(this);
		
	
		//下方的cardPanel 四个面板 课程管理 课程添加 考试录入 实验录入
		cardPanel = new JPanel();
		layout = new CardLayout();
		cardPanel.setLayout(layout);
		cardPanel.setBounds(0, 62, 736, 538);
		add(cardPanel);
		
		panel_courseAdd = new JPanel();
		panel_courseManage = new JPanel();
		panel_exam = new JPanel();
		panel_experiment = new JPanel();
		
		cardPanel.add(panel_courseManage,"card1");
		cardPanel.add(panel_courseAdd,"card2");
		cardPanel.add(panel_exam,"card3");
		cardPanel.add(panel_experiment,"card4");
		
//课程添加面板-------------------------------------------------------------------------------------------------
		panel_courseAdd.setLayout(null);
		panel_courseAdd.setBounds(0, 0, 736, 538);
		
		textField_courseName= new JTextField("");
		textField_courseName.setColumns(10);
		textField_courseName.setBounds(241, 70, 291, 21);
		panel_courseAdd.add(textField_courseName);
		
		JLabel label = new JLabel("名称：");
		label.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label.setBounds(100, 72, 77, 15);
		panel_courseAdd.add(label);
		
		JLabel label_1 = new JLabel("任课老师：");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_1.setBounds(100, 113, 77, 15);
		panel_courseAdd.add(label_1);
		
		JLabel label_2 = new JLabel("上课周次");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_2.setBounds(100, 154, 77, 15);
		panel_courseAdd.add(label_2);
		
		JLabel label_3 = new JLabel("上课时间");
		label_3.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_3.setBounds(100, 194, 74, 15);
		panel_courseAdd.add(label_3);
		
		JLabel label_4 = new JLabel("课程学分：");
		label_4.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_4.setBounds(100, 277, 74, 15);
		panel_courseAdd.add(label_4);
		
		JLabel label_5 = new JLabel("上课地点：");
		label_5.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_5.setBounds(100, 234, 77, 15);
		panel_courseAdd.add(label_5);
		
		JLabel label_6 = new JLabel("课程容量：");
		label_6.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_6.setBounds(100, 314, 74, 15);
		panel_courseAdd.add(label_6);
		
		JLabel label_7 = new JLabel("考试时间");
		label_7.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_7.setBounds(100, 352, 74, 15);
		panel_courseAdd.add(label_7);
		
		button_courseAdd = new JButton("添加");
		button_courseAdd.setFont(new Font("微软雅黑", Font.BOLD, 14));
		button_courseAdd.setBounds(188, 440, 93, 23);
		panel_courseAdd.add(button_courseAdd);
		button_courseAdd.addActionListener(this);
		
		JButton button_2 = new JButton("取消");
		button_2.setFont(new Font("微软雅黑", Font.BOLD, 14));
		button_2.setBounds(370, 440, 93, 23);
		panel_courseAdd.add(button_2);
		
		textField_courseLecture = new JTextField();
		textField_courseLecture.setColumns(10);
		textField_courseLecture.setBounds(241, 111, 291, 21);
		panel_courseAdd.add(textField_courseLecture);
		
		textField_courseTime = new JTextField();
		textField_courseTime.setColumns(10);
		textField_courseTime.setBounds(323, 192, 209, 21);
		panel_courseAdd.add(textField_courseTime);
		
		radioButton_course = new JRadioButton("课程");
		radioButton_course.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton_course.setSelected(true);
		radioButton_course.setFont(new Font("微软雅黑", Font.BOLD, 14));
		radioButton_course.setFocusPainted(false);
		radioButton_course.setBounds(188, 31, 93, 23);
		panel_courseAdd.add(radioButton_course);
		
		radioButton_experiment = new JRadioButton("实验");
		radioButton_experiment.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton_experiment.setFont(new Font("微软雅黑", Font.BOLD, 14));
		radioButton_experiment.setFocusPainted(false);
		radioButton_experiment.setBounds(370, 31, 93, 23);
		panel_courseAdd.add(radioButton_experiment);
		
		locationGroup = new ButtonGroup();
		locationGroup.add(radioButton_course);
		locationGroup.add(radioButton_experiment);
		radioButton_course.setSelected(true);
		
		textField_coursePlace = new JTextField("");
		textField_coursePlace.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_coursePlace.setBounds(241, 232, 291, 21);
		panel_courseAdd.add(textField_coursePlace);
		
		textField_courseScore = new JTextField();
		textField_courseScore.setColumns(10);
		textField_courseScore.setBounds(241, 275, 291, 21);
		panel_courseAdd.add(textField_courseScore);
		
		textField_courseCapacity = new JTextField("");
		textField_courseCapacity.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_courseCapacity.setBounds(241, 312, 291, 21);
		panel_courseAdd.add(textField_courseCapacity);
		
		textField_courseTestTime = new JTextField("");
		textField_courseTestTime.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_courseTestTime.setBounds(241, 350, 291, 21);
		panel_courseAdd.add(textField_courseTestTime);
		
		
		
		String[] listDataTime = new String[]{"上午", "下午", "晚上"};
		comboBoxTime = new JComboBox(listDataTime);
		comboBoxTime.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
		comboBoxTime.setBounds(241, 192, 72, 21);
		String[] listDataWeek = new String[] {"周一","周二","周三","周四","周五"};
		comboBoxWeek = new JComboBox(listDataWeek);
		comboBoxWeek.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
		comboBoxWeek.setLocation(241, 152);
		comboBoxWeek.setSize(72, 21);
		panel_courseAdd.add(comboBoxTime);
		panel_courseAdd.add(comboBoxWeek);
		
		
//课程管理------------------------------------------------------------------------------------------------------------
		panel_courseManage.setLayout(null);
		textField_search_name = new JTextField();
		textField_search_name.setSize(230, 21);
		textField_search_name.setLocation(70, 23);
		textField_search_name.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_search_name.setColumns(10);
		textField_search_name.addFocusListener(this);
		textField_search_name.setText("课程名称");
		panel_courseManage.add(textField_search_name);
		
		textField_search_lecture = new JTextField();
		textField_search_lecture.setSize(230, 21);
		textField_search_lecture.setLocation(330, 23);
		textField_search_lecture.addFocusListener(this);
		textField_search_lecture.setText("任课老师");
		panel_courseManage.add(textField_search_lecture);
		
		button_manage_search = new JButton("搜索");
		button_manage_search.setSize(93, 23);
		button_manage_search.setLocation(590, 22);
		button_manage_search.setFont(new Font("微软雅黑", Font.BOLD, 14));
		panel_courseManage.add(button_manage_search);
		button_manage_search.addActionListener(this);
		
		//滚动表格
		String[] columnNames1= {"课程名称","任课老师","上课周次","上课时间","上课地点","课程容量","课程学分","    "};//定义表格列名的数组
		//定义表格数据数组
		String[][] tableValues1= {};
		tableModel1=new DefaultTableModel(tableValues1,columnNames1);
		table_manage=new JTable(tableModel1) {
			public boolean isCellEditable(int row,int column){
                return false;
            }

		};//创建指定列名和数据的表格
		 //设置表数据居中显示
		DefaultTableCellRenderer cr1 = new DefaultTableCellRenderer();
		cr1.setHorizontalAlignment(JLabel.CENTER);
		table_manage.setDefaultRenderer(Object.class, cr1);
		scrollPane1 = new JScrollPane(table_manage);
		scrollPane1.setBounds(20, 68, 696, 443);
		panel_courseManage.add(scrollPane1);
		
		table_manage.addMouseListener(this);
		

		MMTableCellRendererDelete renderer1 = new MMTableCellRendererDelete();

		table_manage.getColumnModel().getColumn(table_manage.getColumnCount()-1).setCellRenderer(renderer1);


		table_manage.setCellSelectionEnabled(true); //这句话是使可以选择一个单元格
		//虚假的测试 之后删除
		tableModel1.addRow(new String[] {"计组","rgl","一","1 - 1","J3 404","30","2","",""});
		tableModel1.addRow(new String[] {"计组","rgl","一","1 - 1","J3 404","30","2","",""});
		tableModel1.addRow(new String[] {"计组","rgl","一","1 - 1","J3 404","30","2","",""});
		tableModel1.addRow(new String[] {"计组","rgl","一","1 - 1","J3 404","30","2","",""});
		tableModel1.addRow(new String[] {"计组","rgl","一","1 - 1","J3 404","30","2","",""});
		
	
//考试录入-----------------------------------------------------------
		panel_exam.setLayout(null);
		textField_search_courseName = new JTextField();
		textField_search_courseName.setSize(230, 21);
		textField_search_courseName.setLocation(70, 23);
		textField_search_courseName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_search_courseName.setColumns(10);
		textField_search_courseName.addFocusListener(this);
		textField_search_courseName.setText("课程名称");
		panel_exam.add(textField_search_courseName);
		
		textField_search_courseLecture = new JTextField();
		textField_search_courseLecture.setSize(230, 21);
		textField_search_courseLecture.setLocation(330, 23);
		textField_search_courseLecture.addFocusListener(this);
		textField_search_courseLecture.setText("任课老师");
		panel_exam.add(textField_search_courseLecture);
		
		button_exam_search = new JButton("搜索");
		button_exam_search.setSize(93, 23);
		button_exam_search.setLocation(590, 22);
		button_exam_search.setFont(new Font("微软雅黑", Font.BOLD, 14));
		panel_exam.add(button_exam_search);
		button_exam_search.addActionListener(this);
		
		//滚动表格
		String[] columnNames2= {"课程名称","任课老师","学生姓名","学生一卡通号","","成绩"};//定义表格列名的数组
		//定义表格数据数组
		String[][] tableValues2= {};
		tableModel2=new DefaultTableModel(tableValues2,columnNames2);
		table_exam=new JTable(tableModel2) {
			public boolean isCellEditable(int row,int column){
                if(column == table_exam.getColumnCount()-1 && column == table_exam.getColumnCount()-2)return true;
                else return false;
            }

		};//创建指定列名和数据的表格
		 //设置表数据居中显示
		DefaultTableCellRenderer cr2 = new DefaultTableCellRenderer();
		cr2.setHorizontalAlignment(JLabel.CENTER);
		table_exam.setDefaultRenderer(Object.class, cr2);
		scrollPane2 = new JScrollPane(table_exam);
		scrollPane2.setBounds(20, 68, 696, 443);
		panel_exam.add(scrollPane2);
		
		table_exam.addMouseListener(this);
		

		MExamTableCellRendererDelete renderer2 = new MExamTableCellRendererDelete();
		table_exam.getColumnModel().getColumn(table_exam.getColumnCount()-2).setCellRenderer(renderer2);

		table_exam.setCellSelectionEnabled(true); //这句话是使可以选择一个单元格
		//虚假测试 之后删除
		tableModel2.addRow(new String[] {"计组","rgl","skk","211","",null});
		tableModel2.addRow(new String[] {"计组","rgl","skk","211","",null});
		tableModel2.addRow(new String[] {"计组","rgl","skk","211","",null});
		tableModel2.addRow(new String[] {"计组","rgl","skk","211","",null});
		tableModel2.addRow(new String[] {"计组","rgl","skk","211","",null});
		
		
	
		
//实验录入---------------------------------------------
		panel_experiment.setLayout(null);
		textField_search_expName = new JTextField();
		textField_search_expName.setSize(230, 21);
		textField_search_expName.setLocation(70, 23);
		textField_search_expName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_search_expName.setColumns(10);
		textField_search_expName.addFocusListener(this);
		textField_search_expName.setText("实验名称");
		panel_experiment.add(textField_search_expName);
		
		textField_search_expLecture = new JTextField();
		textField_search_expLecture.setSize(230, 21);
		textField_search_expLecture.setLocation(330, 23);
		textField_search_expLecture.addFocusListener(this);
		textField_search_expLecture.setText("实验老师");
		panel_experiment.add(textField_search_expLecture);
		
		button_experiment_search = new JButton("搜索");
		button_experiment_search.setSize(93, 23);
		button_experiment_search.setLocation(590, 22);
		button_experiment_search.setFont(new Font("微软雅黑", Font.BOLD, 14));
		panel_experiment.add(button_experiment_search);
		button_experiment_search.addActionListener(this);
		//滚动表格
		String[] columnNames3= {"实验名称","实验任课老师","学生姓名","学生一卡通号","","成绩"};//定义表格列名的数组
		//定义表格数据数组
		String[][] tableValues3= {};
		tableModel3=new DefaultTableModel(tableValues3,columnNames3);
		table_experiment=new JTable(tableModel3) {
			public boolean isCellEditable(int row,int column){
                if(column == table_exam.getColumnCount()-1 && column == table_exam.getColumnCount()-2)return true;
                else return false;
            }
			};//创建指定列名和数据的表格
		 //设置表数据居中显示
		DefaultTableCellRenderer cr3 = new DefaultTableCellRenderer();
		cr3.setHorizontalAlignment(JLabel.CENTER);
		 table_experiment.setDefaultRenderer(Object.class, cr3);
		scrollPane3 = new JScrollPane(table_experiment);
		scrollPane3.setBounds(20, 68, 696, 443);
		panel_experiment.add(scrollPane3);
		table_experiment.addMouseListener(this);
		
		MExpTableCellRendererDelete renderer3 = new MExpTableCellRendererDelete();
		table_experiment.getColumnModel().getColumn(table_experiment.getColumnCount()-2).setCellRenderer(renderer3);
		
		table_experiment.setCellSelectionEnabled(true); //这句话是使可以选择一个单元格
		//虚假测试=== 之后删除
		tableModel3.addRow(new String[] {"实验","rgl","skk","211","",null});
		tableModel3.addRow(new String[] {"计组","rgl","skk","211","",null});
		tableModel3.addRow(new String[] {"计组","rgl","skk","211","",null});
		tableModel3.addRow(new String[] {"计组","rgl","skk","211","",null});
		tableModel3.addRow(new String[] {"计组","rgl","skk","211","",null});

	}


	


	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == button1) {
			layout.show(cardPanel,"card1");
		}else if(e.getSource() == button2) {
			layout.show(cardPanel,"card2");
		}else if(e.getSource() == button3) {
			layout.show(cardPanel,"card3");
		}else if(e.getSource() == button4) {
			layout.show(cardPanel,"card4");
		}
		
		//事件课程添加-==============================================================================================YHD看这里
		if(e.getSource() == button_courseAdd) {
			addCourse();// 课程添加的函数 ------------------------------------------在下面
		}
		
	//事件 三个界面的搜索键 分别是 课程管理 考试录入 实验录入====================================================
		if(e.getSource() == button_manage_search) {
			//两个搜索框  这是课程管理界面的搜索键 对应的两个文本框输入值
			String manage_name = textField_search_name.getText();
			String manage_lecture = textField_search_lecture.getText();
		}
		if(e.getSource() == button_manage_search) {
			//两个搜索框  这是考试录入界面的搜索键 对应的两个文本框输入值
			String exam_name = textField_search_courseName.getText();
			String exam_lecture = textField_search_courseLecture.getText();
		}
		if(e.getSource() == button_manage_search) {
			//两个搜索框  这是实验录入界面的搜索键 对应的两个文本框输入值
			String exp_name = textField_search_expName.getText();
			String exp_lecture = textField_search_expLecture.getText();
		}	
		
	}
	@SuppressWarnings("unused")
	//========= YHD======================= YHD===========================================addCourse()  YHD看这里=======================================
	private void addCourse() {
		// TODO 自动生成的方法存根
		
		//????????????????????????????? 是否根据课程类型进行添加  
		//例如只添加课程
		//是否需要前端直接添加到列表中 ？？？？？？？
		
		
		//
		
		//课程类型的值 (课程/实验)
		String type;
		if(radioButton_course.isSelected()) type = "课程";
		else if(radioButton_experiment.isSelected()) type ="实验";
		
		
		//名称的值
		String name = textField_courseName.getText();
		
		//老师的值
		String lecture = textField_courseLecture.getText();
		
		//上课周次  eg 
		//重要的是这个  (String) comboBoxWeek.getSelectedItem() 
		String week = (String) comboBoxWeek.getSelectedItem();
		
		//上课时间 eg (上午) + 8：00 9：45          重要的是这个 (String)comboBoxTime.getSelectedItem(),用于判断上午下午晚上 
		String time = (String) comboBoxTime.getSelectedItem() + textField_courseTime.getText();
		
		//上课地点
		String place = textField_coursePlace.getText();
		
		//课程学分 得到的是一个string 记得转换类型
		String score = textField_courseScore.getText();
		
		//课程容量 得到的是一个string 记得转换类型
		String capacity =  textField_courseCapacity.getText();
		//请求
		Client.Request request = new Request();
		request.setPath("EduAdmin/addCourse");
		request.setToken(token);
		request.getParams().put("name", name);
		request.getParams().put("lecturer", lecture);
		request.getParams().put("week", week);
		request.getParams().put("location", place);
		request.getParams().put("credits", Integer.valueOf(score));
		request.getParams().put("classtime", time);
		request.getParams().put("capacity", Integer.valueOf(capacity));
		Response response = Client.fetch(request);
		if(response.getSuccess())
			JOptionPane.showMessageDialog(null, "添加成功", "成功！",JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "添加失败请联系管理员", "失败！",JOptionPane.ERROR_MESSAGE);
		
		//考试时间 
		String testTime = textField_courseTestTime.getText();

		tableModel1.addRow(new String[] {name,lecture,week,time,place,capacity,score});
		
		
		
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == textField_search_name) {
			textField_search_name.setText("");
		}else if(e.getSource() == textField_search_lecture) {
			textField_search_lecture.setText("");
		}else if(e.getSource() == textField_search_courseName) {
			textField_search_courseName.setText("");
		}else if(e.getSource() == textField_search_courseLecture) {
			textField_search_courseLecture.setText("");
		}else if(e.getSource() == textField_search_expName) {
			textField_search_expName.setText("");
		}else if(e.getSource() == textField_search_expLecture) {
			textField_search_expLecture.setText("");
		}
		
	}


	@Override
	public void focusLost(FocusEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == textField_search_name) {
			textField_search_name.setText("课程名称");
		}else if(e.getSource() == textField_search_lecture) {
			textField_search_lecture.setText("任课老师");
		}else if(e.getSource() == textField_search_courseName) {
			textField_search_courseName.setText("课程名称");
		}else if(e.getSource() == textField_search_courseLecture) {
			textField_search_courseLecture.setText("任课老师");
		}else if(e.getSource() == textField_search_expName) {
			textField_search_expName.setText("实验名称");
		}else if(e.getSource() == textField_search_expLecture) {
			textField_search_expLecture.setText("实验老师");
		}
		
		
	}





	@SuppressWarnings("null")
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根	
		if(e.getClickCount()==1) {
			int row1 = table_manage.rowAtPoint(e.getPoint());
			int column1 = table_manage.columnAtPoint(e.getPoint());
			int row2 = table_exam.rowAtPoint(e.getPoint());
			int column2 = table_exam.columnAtPoint(e.getPoint());
			int row3 = table_experiment.rowAtPoint(e.getPoint());
			int column3 = table_experiment.columnAtPoint(e.getPoint());
			
//===================================这个里面是课程管理表格里面那个 删除课程 按钮的 鼠标事件 在这里添加 ======YHD看这里============================================
			if(column1 == table_manage.getColumnCount()-1 && e.getSource() == table_manage ) {
				int isDelete = JOptionPane.showConfirmDialog(null, "确定删除", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
				if(isDelete ==JOptionPane.YES_OPTION){
				 tableModel1.removeRow(row1);
				 //具体从这里开始 添加 
				}
			}
//===================================这里是成绩录入表格那个 成绩修改 按钮的鼠标事件 在这里添加 ===================
				if(column2 == table_exam.getColumnCount()-2 &&  e.getSource() == table_exam) {
					String str_examScore = JOptionPane.showInputDialog("输入该课程要更改的分数");
					if(Integer.valueOf(str_examScore).intValue() > -1 && Integer.valueOf(str_examScore).intValue() < 101 )
						tableModel2.setValueAt(str_examScore, row2, table_exam.getColumnCount()-1);
					else JOptionPane.showMessageDialog(null, "输入不符合要求，请重新输入");
							//上面我已经实现了 在这个表格表面更改了 
							//下面你实现数据库里的操作
					//输入的值 为   String 类型的    **str_examScore** ---------------------------------
						
						
					}
				
//===================================这里是实验录入表格那个 成绩修改 按钮的鼠标事件 在这里添加 ==========================
				if(column3 == table_experiment.getColumnCount()-2 && e.getSource() == table_experiment) {
					String str_expScore = JOptionPane.showInputDialog("输入该实验要更改的分数");
					if(Integer.valueOf(str_expScore).intValue() > -1 && Integer.valueOf(str_expScore).intValue() < 101)
						tableModel3.setValueAt(str_expScore, row3, tableModel3.getColumnCount()-1);
					else JOptionPane.showMessageDialog(null, "输入不符合要求，请重新输入");
							//上面我已经实现了 在这个表格表面更改了 
							//下面你实现数据库里的操作
					//输入的值 为    String 类型的   **str_expScore** ---------------------------------
								
						
						
					}
				}

	}





	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}





	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}





	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}





	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
}
