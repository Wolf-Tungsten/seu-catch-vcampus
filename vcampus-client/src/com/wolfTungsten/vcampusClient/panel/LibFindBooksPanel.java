//图书馆-借阅图书Panel
package com.wolfTungsten.vcampusClient.panel;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
public class LibFindBooksPanel extends JPanel implements FocusListener,ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField textField_select;
	JButton button_select;
	CardLayout cardLayout=new CardLayout();
	
	// Create the panel.
	public LibFindBooksPanel() {
		setSize(736,600);
		setLayout(null);//绝对布局
		
		//按书名/作者检索的输入文本框
		textField_select= new JTextField();
		textField_select.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_select.setForeground(Color.LIGHT_GRAY);
		textField_select.setText("书名/作者");
		textField_select.setBounds(153, 93, 319, 24);
		textField_select.addFocusListener(this);
		
		add(textField_select);
		textField_select.setColumns(10);
		//“搜索”按钮
		button_select = new JButton("搜索");
		button_select.setFont(new Font("微软雅黑", Font.BOLD, 14));
		button_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_select.setBounds(493, 93, 93, 24);
		button_select.addActionListener(this);
		add(button_select);		
		
		JPanel panel = new JPanel(cardLayout);
		panel.setBounds(58, 161, 620, 401);
		add(panel);
		
		JPanel panel_book_table=new JPanel();
		panel_book_table.setLayout(null);
		panel.add("1",panel_book_table);
		
		JScrollPane scrollPane = new JScrollPane();//创建显示表格的滚动面板
		scrollPane.setBounds(10, 10, 600, 381);
		panel_book_table.add(scrollPane);
		String[] columnNames= {"编号","书名","作者","出版社","馆藏地点","状态"};//定义表格列名的数组
		//定义表格数据数组
		String[][] tableValues= {{"B612","java","xxx","seu","jlh","未借出"}};
		DefaultTableModel tableModel=new DefaultTableModel(tableValues,columnNames);//创建指定列名和数据的表格	
		JTable table=new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		JPanel panel_borrow=new JPanel();
		panel.add("2",panel_borrow);
		
	}
	
	//文本框内显示提示内容
	//这里有个问题，移出此面板后再移动回来，原来的检索记录还在，这是不是很呆？（其实也还行...)
	@Override
	public void focusGained(FocusEvent e) {	
		//这里问题就是，比如输入“xxx”后再点击一下，原来的东西“xxx”就没了。。。。
		textField_select.setText("");
	}
	@Override
	public void focusLost(FocusEvent e) {
		if(textField_select.getText().equals(""))
		{
			textField_select.setText("书名/作者");		
		}
	}
	//检索按钮事件响应
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button_select)
		{
			//点击“搜索”按钮，获取当前输入文本框中的文本（不获取"书名/作者"），进行检索
			if(!textField_select.getText().equals("书名/作者")) {
				String select_key=textField_select.getText();
			   //怎么检索？？？
			}
		}
	}
}
