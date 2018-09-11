//教务处——学生选课面板
package com.wolfTungsten.vcampusClient.panel;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.wolfTungsten.vcampusClient.component.SCTableCellEditorAdd;
import com.wolfTungsten.vcampusClient.component.SCTableCellEditorCancel;
import com.wolfTungsten.vcampusClient.component.SCTableCellRendererAdd;
import com.wolfTungsten.vcampusClient.component.SCTableCellRendererCancel;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;


public class JwcSelectCourses extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel label_cardNum,label_name;
	private JTextField textField_cardNum ;
	private JTextField textField_name;
	private String[] columnNames = {"课程名称","任课老师","上课地点","上课时间","  ","   "}; //表格列名 第五列空出来放置选课退课按钮
	private String token;
	String[][]tableValues;
	String name;
	String cardNum;
	/**
	 * Create the panel.
	 * @param textField_name 
	 * @param <ActionPanelEditorRenderer>
	 */
	public  JwcSelectCourses(String Token,String[][] Table,String Name,String CardNum) {
		token = Token;
		name = Name;
		cardNum = CardNum;
		setSize(736,600);
		setLayout(null);
		tableValues=Table;												   //表格数据数组
		
		model = new DefaultTableModel(tableValues, columnNames);
		table = new JTable(model);
		DefaultTableCellRenderer er = new DefaultTableCellRenderer();
		er.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, er);
		
		JScrollPane scrollTable = new JScrollPane(table);
		scrollTable.setBounds(35, 100, 666, 465);
		table.setBounds(35, 100, 666, 465);
		add(scrollTable);
		table.getTableHeader().setFont(new Font("微软雅黑",Font.BOLD,14));
		scrollTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);//竖直滚轮总是显示
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//table.setCellSelectionEnabled(true); 这句话是使可以选择一个单元格
		
		label_cardNum = new JLabel("一卡通号：");
		label_cardNum.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_cardNum.setBounds(277, 52, 77, 15);
		add(label_cardNum);
		
		textField_cardNum  = new JTextField();
		textField_cardNum.setText(cardNum);
		textField_cardNum.setOpaque(false);
		textField_cardNum.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_cardNum.setEditable(false);
		textField_cardNum.setColumns(10);
		textField_cardNum.setBounds(388, 49, 96, 21);
		add(textField_cardNum);
		
		label_name = new JLabel("姓名:");
		
		label_name.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_name.setBounds(533, 52, 54, 15);
		add(label_name);
		
		textField_name = new JTextField();
		textField_name.setText(name);
		textField_name.setOpaque(false);
		textField_name.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_name.setEditable(false);
		textField_name.setColumns(10);
		textField_name.setBounds(612, 46, 89, 21);
		add(textField_name);
		
		

		
		SCTableCellRendererAdd renderer1 = new SCTableCellRendererAdd();
		SCTableCellRendererCancel renderer2 = new SCTableCellRendererCancel();
		SCTableCellEditorAdd editor1 = new SCTableCellEditorAdd();
		SCTableCellEditorCancel editor2 = new SCTableCellEditorCancel();
		table.getColumnModel().getColumn(table.getColumnCount()-2).setCellRenderer(renderer1);
		table.getColumnModel().getColumn(table.getColumnCount()-1).setCellRenderer(renderer2);
		table.getColumnModel().getColumn(table.getColumnCount()-2).setCellEditor(editor1);
		table.getColumnModel().getColumn(table.getColumnCount()-1).setCellEditor(editor2);
		table.setRowSelectionAllowed(false);// 禁止表格的选择功能。不然在点击按钮时表格的整行都会被选中。
		
		//“课程选择按钮” 在 com.wolfTungsten.vcampusClient.component 的SCTableCellEditorAdd.java里面
		//“取消选择按钮” 在 com.wolfTungsten.vcampusClient.component 的SCTableCellEditorCancel.java里面

//在这里虚假的加了两个用于测试=============================================================================================
//		model.addRow(new String[] {"计组","","","","",""});
//		model.addRow(new String[] {"信号","","","","",""});
//		model.addRow(new String[] {"计组","","","","",""});
//		model.addRow(new String[] {"信号","","","","",""});
//		model.addRow(new String[] {"计组","","","","",""});
//		model.addRow(new String[] {"信号","","","","",""});
//		model.addRow(new String[] {"高数","","","","",""});
//		model.addRow(new String[] {"体育","","","","",""});

	}
}


