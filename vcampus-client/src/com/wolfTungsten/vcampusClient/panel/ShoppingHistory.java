package com.wolfTungsten.vcampusClient.panel;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ShoppingHistory extends JPanel {
	String[] columnNames= {"订单号","交易物品","时间","数量","交易金额"};//定义表格列名的数组
	String[][] tableValues= {};
	DefaultTableModel tableModel;
	JTable table;
	JLabel label_cardNum,label_name;
	private JTextField textField_cardNum;
	private JTextField textField_name;
	/**
	 * Create the panel.
	 */
	public ShoppingHistory() {
		setSize(736,600);
		setLayout(null);//绝对布局
		
		JScrollPane scrollPane = new JScrollPane();//创建显示表格的滚动面板
		scrollPane.setBounds(10, 76, 696, 501);
		add(scrollPane);
		
		tableModel=new DefaultTableModel(tableValues,columnNames);//创建指定列名和数据的表格	
		table=new JTable(tableModel) {
			public boolean isCellEditable(int row, int column)
            {
                       return false;//表格不允许被编辑
            }
		};
		 //设置表数据居中显示
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, cr);
	
		scrollPane.setViewportView(table);
		
		label_cardNum = new JLabel("一卡通号：");
		label_cardNum.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_cardNum.setBounds(292, 36, 77, 30);
		add(label_cardNum);
		
		label_name = new JLabel("姓名:");
		label_name.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_name.setBounds(548, 36, 54, 30);
		add(label_name);
		
		textField_cardNum = new JTextField();
		textField_cardNum.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_cardNum.setBounds(403, 39, 96, 30);
		textField_cardNum.setEditable(false);
		textField_cardNum.setOpaque(false);
		add(textField_cardNum);
		textField_cardNum.setColumns(10);
		
		textField_name = new JTextField();
		textField_name.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_name.setBounds(627, 39, 89, 30);
		textField_name.setEditable(false);
		textField_name.setOpaque(false);
		add(textField_name);
		textField_name.setColumns(10);
	}

}
