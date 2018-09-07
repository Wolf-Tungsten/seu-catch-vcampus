//显示“借还信息”
package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.wolfTungsten.vcampusClient.component.TableButtonEditor;
import com.wolfTungsten.vcampusClient.component.TableReBorButtonEditor;
import java.awt.Font;
import javax.swing.JTextField;

public class LibMessage extends JPanel {
	JLabel label_cardNum,label_name;
	private JTextField textField_cardNum;
	private JTextField textField_name;
	/**
	 * Create the panel.
	 */
	public LibMessage() {
		setSize(736,600);
		setLayout(null);//绝对布局
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 76, 696, 514);
		add(scrollPane);
		
		String[] columnNames= {"编号","书名","作者","出版社","借阅时间","归还时间","到期时间","续借状态"};//定义表格列名的数组
		//数据传到这里！
		String[][] tableValues= {{"编号","书名","作者","出版社","借阅时间","归还时间","到期时间","可续借"},{"编号","书名","作者","出版社","借阅时间","归还时间","到期时间","不可续借"}};
		DefaultTableModel tableModel=new DefaultTableModel(tableValues,columnNames);//创建指定列名和数据的表格	
		JTable table=new JTable(tableModel);
		 //设置表数据居中显示
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		 table.setDefaultRenderer(Object.class, cr);
		//遍历所有行
		 int rowCount=table.getRowCount(); 
		 for(int i=0;i<rowCount;i++) {
			Object re_statement=tableModel.getValueAt(i, 7);
			System.out.println("statement:"+re_statement);
			//假如这本书状态是“可借出“，就在这行最后一列为其添加可选择”续借“按钮
			if(re_statement.equals("可续借")) {
				tableModel.setValueAt("续借", i, 7);//重置名字
			}
		 }
		//”续借“状态按钮添加，具体看component里的TableReBorButtonEidtor
		 table.getColumn("续借状态").setCellEditor(new TableReBorButtonEditor(new JCheckBox()));
		//我这里无法获取更改后的“续借状态”列里的信息，界面上点击续借后“续借”按钮会变为不可点击的“不可续借”按钮，但我这里怎么接受返回的信息呢？
		 //在TableReBorButtonEditor()里有返回
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
	}
}
