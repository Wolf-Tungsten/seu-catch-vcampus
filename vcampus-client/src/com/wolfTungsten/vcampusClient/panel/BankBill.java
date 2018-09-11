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

	JComboBox comboBox_time,comboBox_bill_type;
	JScrollPane scrollPane;
	String[] columnNames= {"时间","对方姓名","对方账号","金额"};//定义表格列名的数组
	//TODO 传数据给tableValues
	String[][] tableValues= {{"09-06 06:09","梅花餐厅","123455","-10.50"},{"09-06 12:08","东南大学","321282","+1000.00"}};;
	DefaultTableModel tableModel;
	JTable table;

	private String token;
	/**
	 * Create the panel.
	 */

	public BankBill() {
		setSize(736,600);
		setLayout(null);//绝对布局
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 53, 716, 537);
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
		label_cardNum.setBounds(352, 13, 77, 30);
		add(label_cardNum);
		
		label_name = new JLabel("姓名:");
		label_name.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_name.setBounds(573, 13, 54, 30);
		add(label_name);
		
		textField_cardNum = new JTextField();
		textField_cardNum.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_cardNum.setBounds(439, 14, 109, 30);
		textField_cardNum.setEditable(false);
		textField_cardNum.setOpaque(false);
		add(textField_cardNum);
		textField_cardNum.setColumns(10);
		
		textField_name = new JTextField();
		textField_name.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_name.setBounds(637, 14, 89, 30);
		textField_name.setEditable(false);
		textField_name.setOpaque(false);
		add(textField_name);
		textField_name.setColumns(10);
		
		comboBox_time = new JComboBox();
		comboBox_time.setFont(new Font("微软雅黑", Font.BOLD, 12));
		comboBox_time.setBounds(10, 17, 80, 23);
		comboBox_time.addItem("三天内");
		comboBox_time.addItem("一月内");
		comboBox_time.addItem("三月内");
		comboBox_time.addItem("半年内");
		comboBox_time.addItem("一年内");
		add(comboBox_time);
		
		comboBox_bill_type = new JComboBox();
		comboBox_bill_type.setFont(new Font("微软雅黑", Font.BOLD, 12));
		comboBox_bill_type.setBounds(100, 17, 80, 23);
		comboBox_bill_type.addItem("总账单");
		comboBox_bill_type.addItem("收入账单");
		comboBox_bill_type.addItem("支出账单");
		add(comboBox_bill_type);
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if(comboBox_time.getSelectedIndex()==0) {
					if(comboBox_bill_type.getSelectedIndex()==0) {
					     //TODO 三天内总账单
						//eg.传数据给rowValue,"时间","对方姓名","对方账号","金额"
						String []rowValue= {};
						tableModel.addRow(rowValue);
					}else if(comboBox_bill_type.getSelectedIndex()==1) {
					     //TODO 三天内收入账单		 
					 }else if(comboBox_bill_type.getSelectedIndex()==2) {
					     //TODO 三天内支出账单
						 
					}
				}else if(comboBox_time.getSelectedIndex()==1) {	
					if(comboBox_bill_type.getSelectedIndex()==0) {
					     //TODO 一月内总账单
					}else if(comboBox_bill_type.getSelectedIndex()==1) {
					     //TODO 一月内收入账单		 
					 }else if(comboBox_bill_type.getSelectedIndex()==2) {
					     //TODO一月内 支出账单
						 
					}
				 }else if(comboBox_time.getSelectedIndex()==2) {
					 if(comboBox_bill_type.getSelectedIndex()==0) {
					     //TODO 三月内总账单
					}else if(comboBox_bill_type.getSelectedIndex()==1) {
					     //TODO 三月内收入账单		 
					 }else if(comboBox_bill_type.getSelectedIndex()==2) {
					     //TODO 三月内支出账单
						 
					}
				}else if(comboBox_time.getSelectedIndex()==3) {
					if(comboBox_bill_type.getSelectedIndex()==0) {
					     //TODO 半年内总账单
					}else if(comboBox_bill_type.getSelectedIndex()==1) {
					     //TODO 半年内收入账单		 
					 }else if(comboBox_bill_type.getSelectedIndex()==2) {
					     //TODO 半年内支出账单
						 
					}
				}else if(comboBox_time.getSelectedIndex()==4) {
					if(comboBox_bill_type.getSelectedIndex()==0) {
					     //TODO 一年内总账单
					}else if(comboBox_bill_type.getSelectedIndex()==1) {
					     //TODO 一年内收入账单		 
					 }else if(comboBox_bill_type.getSelectedIndex()==2) {
					     //TODO 一年内支出账单
						 
					}
				 }	  
				 
		 }
	}
}
