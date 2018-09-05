package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class LibManager extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	JPanel panel_top,panel_buttom;
	JPanel panel_all,panel_add,panel_delete;
	JButton button_all,button_add,button_delete,button_exit,button_select;
	CardLayout cardLayout=new CardLayout();
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTextField textField_1;
	private JLabel label_4;
	private JLabel label_5;
	private JTextField textField_name;
	private JTextField textField_author;
	private JTextField textField_publisher;
	private JTextField textField_2;
	private JButton btnNewButton;
	/**
	 * Create the panel.
	 */
	public LibManager() {
		setSize(736,600);
		setLayout(null);
		//面板管理=============================================================================
		//下方面板，卡片布局
		panel_buttom = new JPanel();
		panel_buttom.setBounds(0, 69, 736, 531);
		add(panel_buttom);
		panel_buttom.setLayout(cardLayout);
		//上方面板，绝对布局，放按钮的
		panel_top = new JPanel();
		panel_top.setBounds(0, 10, 736, 49);
		add(panel_top);
		panel_top.setLayout(null);
		
		panel_all=new JPanel();
//		panel_all.setBackground(Color.PINK);
		panel_buttom.add("all",panel_all);
		panel_all.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField.setBounds(70, 23, 476, 21);
		panel_all.add(textField);
		textField.setColumns(10);
		
		button_select = new JButton("搜索");
		button_select.setFont(new Font("微软雅黑", Font.BOLD, 14));
		button_select.setBounds(571, 22, 93, 23);
		button_select.addActionListener(this);
		
		//“在库图书”面板
		panel_all.add(button_select);	
		//滚动表格
		String[] columnNames= {"编号","书名","作者","出版社","馆藏地点","入库日期","借阅状态"};//定义表格列名的数组
		//定义表格数据数组
		String[][] tableValues= {};
		JTable table=new JTable(tableValues,columnNames);//创建指定列名和数据的表格
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 68, 696, 443);
		panel_all.add(scrollPane);
		
		//“图书入库”面板
		panel_add=new JPanel();
		panel_buttom.add("add",panel_add);
		panel_add.setLayout(null);
		
		textField_1 = new JTextField("");
		textField_1.setBounds(241, 60, 291, 21);
		panel_add.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("图书编号：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel.setBounds(85, 62, 77, 15);
		panel_add.add(lblNewLabel);
		
		JLabel label_name = new JLabel("书名：");
		label_name.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_name.setBounds(85, 103, 54, 15);
		panel_add.add(label_name);
		
		JLabel label_author = new JLabel("作者：");
		label_author.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_author.setBounds(85, 144, 54, 15);
		panel_add.add(label_author);
		
		JLabel label_publisher = new JLabel("出版社：");
		label_publisher.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_publisher.setBounds(85, 184, 74, 15);
		panel_add.add(label_publisher);
		
		JLabel label_3 = new JLabel("入库时间：");
		label_3.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_3.setBounds(82, 277, 74, 15);
		panel_add.add(label_3);
		
		label_4 = new JLabel("馆藏地点：");
		label_4.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_4.setBounds(82, 234, 77, 15);
		panel_add.add(label_4);
		
		label_5 = new JLabel("借阅状态：");
		label_5.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_5.setBounds(82, 312, 74, 15);
		panel_add.add(label_5);
		
		JButton okbutton = new JButton("添加");
		okbutton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		okbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		okbutton.setBounds(188, 440, 93, 23);
		panel_add.add(okbutton);
		
		JButton cancelbutton = new JButton("取消");
		cancelbutton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		cancelbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		cancelbutton.setBounds(370, 440, 93, 23);
		panel_add.add(cancelbutton);
		
		textField_name = new JTextField();
		textField_name.setBounds(241, 101, 291, 21);
		panel_add.add(textField_name);
		textField_name.setColumns(10);
		
		textField_author = new JTextField();
		textField_author.setBounds(241, 142, 291, 21);
		panel_add.add(textField_author);
		textField_author.setColumns(10);
		
		textField_publisher = new JTextField();
		textField_publisher.setBounds(241, 182, 291, 21);
		panel_add.add(textField_publisher);
		textField_publisher.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("四牌楼");
		rdbtnNewRadioButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		rdbtnNewRadioButton.setBounds(241, 230, 82, 23);
		panel_add.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("丁家桥");
		rdbtnNewRadioButton_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		rdbtnNewRadioButton_1.setBounds(355, 230, 77, 23);
		panel_add.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("九龙湖");
		rdbtnNewRadioButton_2.setFont(new Font("微软雅黑", Font.BOLD, 14));
		rdbtnNewRadioButton_2.setBounds(450, 230, 82, 23);
		panel_add.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("借出");
		rdbtnNewRadioButton_3.setFont(new Font("微软雅黑", Font.BOLD, 14));
		rdbtnNewRadioButton_3.setBounds(241, 308, 66, 23);
		panel_add.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("未借\r\n");
		rdbtnNewRadioButton_4.setFont(new Font("微软雅黑", Font.BOLD, 14));
		rdbtnNewRadioButton_4.setBounds(355, 308, 77, 23);
		panel_add.add(rdbtnNewRadioButton_4);
		//”图书出库“面板
		panel_delete=new JPanel();
		panel_delete.setLayout(null);
		panel_buttom.add("delete",panel_delete);
		
		textField_2 = new JTextField();
		textField_2.setBounds(86, 31, 322, 21);
		panel_delete.add(textField_2);
		textField_2.setColumns(10);
		
		btnNewButton = new JButton("搜索");
		btnNewButton.setBounds(431, 30, 93, 23);
		panel_delete.add(btnNewButton);
		
		String[] columnNames_1= {"编号","书名","作者","出版社","馆藏地点","入库日期","借阅状态","删除"};//定义表格列名的数组
		//定义表格数据数组
		String[][] tableValues_1= {};
		JTable table_1=new JTable(tableValues_1,columnNames_1);//创建指定列名和数据的表格
		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(20, 68, 696, 443);
		panel_delete.add(scrollPane_1);
		
		cardLayout.show(panel_buttom, "all");
		//按钮管理=============================================================================
		//显示所有在库图书信息的按钮
		button_all = new JButton("在库图书");
		button_all.setFont(new Font("微软雅黑", Font.BOLD, 14));
		button_all.setBounds(75, 12, 100, 26);
		button_all.addActionListener(this);
		panel_top.add(button_all);
		//添加图书按钮
		button_add = new JButton("图书入库");
		button_add.setFont(new Font("微软雅黑", Font.BOLD, 14));
		button_add.setBounds(237, 12, 100, 26);
		button_add.addActionListener(this);
		panel_top.add(button_add);
		//删除图书按钮
		button_delete = new JButton("图书出库");
		button_delete.setFont(new Font("微软雅黑", Font.BOLD, 14));
		button_delete.setBounds(399, 12, 100, 26);
		button_delete.addActionListener(this);
		panel_top.add(button_delete);
		//退出管理员模式
		button_exit = new JButton("退出管理");
		button_exit.setBounds(561, 12, 100, 26);
		panel_top.add(button_exit);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			if (e.getSource() ==button_all) {
				cardLayout.show(panel_buttom, "all");
			}else if (e.getSource() ==button_add) {
				cardLayout.show(panel_buttom, "add");
			}else if (e.getSource() ==button_delete) {
				cardLayout.show(panel_buttom, "delete");
			}
	}
}
