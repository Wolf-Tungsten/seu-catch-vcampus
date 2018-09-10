//“账单明细“面板
package com.wolfTungsten.vcampusClient.panel;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.wolfTungsten.vcampusClient.component.TableReBorButtonEditor;
import javax.swing.JComboBox;

public class BankBill extends JPanel implements ItemListener{
	JLabel label_cardNum,label_name;
	private JTextField textField_cardNum;
	private JTextField textField_name;
	/**
	 * Create the panel.
	 */
	public BankBill() {
		setSize(736,600);
		setLayout(null);//绝对布局
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 118, 696, 472);
		add(scrollPane);
		
		String[] columnNames= {"时间","详情","金额"};//定义表格列名的数组
		//数据传到这里
		String[][] tableValues= {{"09-06 06:09","梅花餐厅","-10.50"},{"09-06 12:08","账户转入","+1000.00"}};
		DefaultTableModel tableModel=new DefaultTableModel(tableValues,columnNames);//创建指定列名和数据的表格	
		JTable table=new JTable(tableModel) {
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
		label_cardNum.setBounds(292, 51, 77, 15);
		add(label_cardNum);
		
		label_name = new JLabel("姓名:");
		label_name.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_name.setBounds(548, 51, 54, 15);
		add(label_name);
		
		textField_cardNum = new JTextField();
		textField_cardNum.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_cardNum.setBounds(403, 48, 96, 21);
		textField_cardNum.setEditable(false);
		textField_cardNum.setOpaque(false);
		add(textField_cardNum);
		textField_cardNum.setColumns(10);
		
		textField_name = new JTextField();
		textField_name.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_name.setBounds(627, 45, 89, 21);
		textField_name.setEditable(false);
		textField_name.setOpaque(false);
		add(textField_name);
		textField_name.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(20, 79, 77, 23);
		comboBox.addItem("今日");
		comboBox.addItem("近三天");
		comboBox.addItem("本月");
		add(comboBox);
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
