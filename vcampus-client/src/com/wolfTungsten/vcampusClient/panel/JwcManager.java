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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class JwcManager extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_courseName;
	private JTextField textField_courseLecture;
	private JTextField textField_courseWeek;
	private JTextField textField_courseTime;
	private JTextField textField_coursePlace;
	private JTextField textField_courseScore;
	private JTextField textField_courseCapacity;
	private JButton button1,button2,button3,button4;
	private JButton button_courseAdd;
	private CardLayout layout;
	private JPanel cardPanel;
	JRadioButton radioButton_course ;
	JRadioButton radioButton_experiment; 
	ButtonGroup locationGroup;
	JCheckBox checkBox;
	JPanel panel_courseAdd,panel_experiment,panel_exam,panel_courseManage;
	private JTextField textField_search1,textField_search2,textField_search3;
	private DefaultTableModel tableModel1,tableModel2,tableModel3;
	private JTable table1,table2,table3;
	private JScrollPane scrollPane1,scrollPane2,scrollPane3;
	JButton button_manage_search,button_exam_search,button_experiment_search;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	public JwcManager() {
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
		button1.addMouseListener(this);
		
		button2 = new JButton("教务添加");
		button2.setFont(new Font("微软雅黑", Font.BOLD, 16));
		button2.setBounds(237, 12, 100, 26);
		panel_guide.add(button2);
		button2.addMouseListener(this);
		
		button3 = new JButton("考试录入");
		button3.setFont(new Font("微软雅黑", Font.BOLD, 16));
		button3.setBounds(399, 12, 100, 26);
		panel_guide.add(button3);
		button3.addMouseListener(this);
		
		button4 = new JButton("实验录入");
		button4.setFont(new Font("微软雅黑", Font.BOLD, 16));
		button4.setBounds(561, 12, 100, 26);
		panel_guide.add(button4);
		button4.addMouseListener(this);
		
		
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
		
		button_courseAdd = new JButton("添加");
		button_courseAdd.setFont(new Font("微软雅黑", Font.BOLD, 14));
		button_courseAdd.setBounds(188, 440, 93, 23);
		panel_courseAdd.add(button_courseAdd);
		button_courseAdd.addMouseListener(this);
		
		JButton button_2 = new JButton("取消");
		button_2.setFont(new Font("微软雅黑", Font.BOLD, 14));
		button_2.setBounds(370, 440, 93, 23);
		panel_courseAdd.add(button_2);
		
		textField_courseLecture = new JTextField();
		textField_courseLecture.setColumns(10);
		textField_courseLecture.setBounds(241, 111, 291, 21);
		panel_courseAdd.add(textField_courseLecture);
		
		textField_courseWeek = new JTextField();
		textField_courseWeek.setColumns(10);
		textField_courseWeek.setBounds(241, 152, 291, 21);
		panel_courseAdd.add(textField_courseWeek);
		
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
		
		

		String[] listData = new String[]{"上午", "下午", "晚上"};
		JComboBox comboBox = new JComboBox(listData);
		comboBox.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
		comboBox.setBounds(241, 192, 72, 21);
		panel_courseAdd.add(comboBox);
		
		
//课程管理------------------------------------------------------------------------------------------------------------
		panel_courseManage.setLayout(null);
		textField_search1 = new JTextField();
		textField_search1.setSize(476, 21);
		textField_search1.setLocation(70, 23);
		textField_search1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_search1.setColumns(10);
		panel_courseManage.add(textField_search1);
		
		button_manage_search = new JButton("搜索");
		button_manage_search.setSize(93, 23);
		button_manage_search.setLocation(571, 22);
		button_manage_search.setFont(new Font("微软雅黑", Font.BOLD, 14));
		panel_courseManage.add(button_manage_search);
		
		//滚动表格
		String[] columnNames1= {"课程名称","任课老师","上课周次","上课时间","上课地点","课程容量","课程学分","    "};//定义表格列名的数组
		//定义表格数据数组
		String[][] tableValues1= {};
		tableModel1=new DefaultTableModel(tableValues1,columnNames1);
		table1=new JTable(tableModel1);//创建指定列名和数据的表格
		 //设置表数据居中显示
		DefaultTableCellRenderer cr1 = new DefaultTableCellRenderer();
		cr1.setHorizontalAlignment(JLabel.CENTER);
		 table1.setDefaultRenderer(Object.class, cr1);
		scrollPane1 = new JScrollPane(table1);
		scrollPane1.setBounds(20, 68, 696, 443);
		panel_courseManage.add(scrollPane1);
		
//考试录入-----------------------------------------------------------
		panel_exam.setLayout(null);
		textField_search2 = new JTextField();
		textField_search2.setSize(476, 21);
		textField_search2.setLocation(70, 23);
		textField_search2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_search2.setColumns(10);
		panel_exam.add(textField_search2);
		
		button_exam_search = new JButton("搜索");
		button_exam_search.setSize(93, 23);
		button_exam_search.setLocation(571, 22);
		button_exam_search.setFont(new Font("微软雅黑", Font.BOLD, 14));
		panel_exam.add(button_exam_search);
		
		//滚动表格
		String[] columnNames2= {"课程名称","任课老师","学生姓名","学生学号","    "};//定义表格列名的数组
		//定义表格数据数组
		String[][] tableValues2= {};
		tableModel2=new DefaultTableModel(tableValues2,columnNames2);
		table2=new JTable(tableModel2);//创建指定列名和数据的表格
		 //设置表数据居中显示
		DefaultTableCellRenderer cr2 = new DefaultTableCellRenderer();
		cr1.setHorizontalAlignment(JLabel.CENTER);
		 table2.setDefaultRenderer(Object.class, cr2);
		scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBounds(20, 68, 696, 443);
		panel_exam.add(scrollPane2);
		
//实验录入---------------------------------------------
		panel_experiment.setLayout(null);
		textField_search3 = new JTextField();
		textField_search3.setSize(476, 21);
		textField_search3.setLocation(70, 23);
		textField_search3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_search3.setColumns(10);
		panel_experiment.add(textField_search3);
		
		button_experiment_search = new JButton("搜索");
		button_experiment_search.setSize(93, 23);
		button_experiment_search.setLocation(571, 22);
		button_experiment_search.setFont(new Font("微软雅黑", Font.BOLD, 14));
		panel_experiment.add(button_experiment_search);
		
		//滚动表格
		String[] columnNames3= {"实验名称","实验任课老师","学生姓名","学生学号","    "};//定义表格列名的数组
		//定义表格数据数组
		String[][] tableValues3= {};
		tableModel3=new DefaultTableModel(tableValues3,columnNames3);
		table3=new JTable(tableModel3);//创建指定列名和数据的表格
		 //设置表数据居中显示
		DefaultTableCellRenderer cr3 = new DefaultTableCellRenderer();
		cr3.setHorizontalAlignment(JLabel.CENTER);
		 table3.setDefaultRenderer(Object.class, cr3);
		scrollPane3 = new JScrollPane(table3);
		scrollPane3.setBounds(20, 68, 696, 443);
		panel_experiment.add(scrollPane3);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		if (e.getClickCount() == 1) {
			if(e.getSource() == button1) {
				layout.show(cardPanel,"card1");
			}else if(e.getSource() == button2) {
				layout.show(cardPanel,"card2");
			}else if(e.getSource() == button3) {
				layout.show(cardPanel,"card3");
			}else if(e.getSource() == button4) {
				layout.show(cardPanel,"card4");
			}
			
			if(e.getSource() == button_courseAdd) {
				
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
