//管理员
package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.google.gson.internal.LinkedTreeMap;
import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;
import com.wolfTungsten.vcampusClient.component.DateChooser;
import com.wolfTungsten.vcampusClient.component.TableButtonEditor;
import com.wolfTungsten.vcampusClient.component.TableDeleteButtonEditor;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class LibManager extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	JPanel panel_top,panel_buttom;
	JPanel panel_all,panel_add,panel_delete;
	JButton button_all,button_add,button_delete,button_exit,button_select;
	CardLayout cardLayout=new CardLayout();
	private JTextField textField_select;
	private JScrollPane scrollPane;
	private JLabel label_num;
	private JLabel label_location;
	private JLabel label_station;
	private JTextField textField_num;
	private JTextField textField_name;
	private JTextField textField_author;
	private JTextField textField_publisher;
	private JTextField textField_2;
	private JButton okbutton,cancelbutton,btnNewButton;
	private DefaultTableModel tableModel;
	private JTable table,table_1;
	DefaultTableModel tableModel_1;
	ButtonGroup locationGroup;
	JCheckBox checkBox;
	JRadioButton rdbtnNewRadioButton = new JRadioButton("四牌楼");
	JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("丁家桥");
	JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("九龙湖");
	JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("未借出");
	JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("已借出");
	String[] columnNames= {"编号","书名","作者","出版社","馆藏地点","入库日期","借阅状态"};//在库
	String[] columnNames_1= {"编号","书名","作者","出版社","馆藏地点","入库日期","借阅状态","操作"};//出库
	String[][] tableValues;//在库
	String[][] tableValues_1;//出库
	String token;
	private JTextField textField_time;
	/**
	 * Create the panel.
	 */
	public LibManager(String Token) {
		token = Token ;
		
		setSize(736,600);
		setLayout(null);
		//面板管理=============================================================================
		//======================================下方面板，卡片布局===============================
		panel_buttom = new JPanel();
		panel_buttom.setBounds(0, 69, 736, 531);
		add(panel_buttom);
		panel_buttom.setLayout(cardLayout);
		//=====================================上方面板，绝对布局，放按钮的======================
		panel_top = new JPanel();
		panel_top.setBounds(0, 10, 736, 49);
		add(panel_top);
		panel_top.setLayout(null);
		
		panel_all=new JPanel();
//		panel_all.setBackground(Color.PINK);
		panel_buttom.add("all",panel_all);
		panel_all.setLayout(null);
		
		textField_select = new JTextField();
		textField_select.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_select.setBounds(70, 23, 476, 21);
		panel_all.add(textField_select);
		textField_select.setColumns(10);
		
		button_select = new JButton("搜索");
		button_select.setFont(new Font("微软雅黑", Font.BOLD, 14));
		button_select.setBounds(571, 22, 93, 23);
		button_select.addActionListener(this);
		
		//=============================“在库图书”面板=======================================
		panel_all.add(button_select);	
		//滚动表格
		//定义表格列名的数组
		//定义表格数据数组
		tableModel=new DefaultTableModel(tableValues,columnNames);
		table=new JTable(tableModel) {
			public boolean isCellEditable(int row, int column)
            {		
                       return false;//表格不允许被编辑
            }
		};//创建指定列名和数据的表格
		 //设置表数据居中显示
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, cr);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 68, 696, 443);
		panel_all.add(scrollPane);
		
		//=================================“图书入库”面板===========================================
		panel_add=new JPanel();
		panel_buttom.add("add",panel_add);
		panel_add.setLayout(null);
		/*		
		textField_num = new JTextField("");
		textField_num.setBounds(241, 60, 291, 21);
		panel_add.add(textField_num);
		textField_num.setColumns(10);
		
		JLabel label_num = new JLabel("图书编号：");
		label_num.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_num.setBounds(85, 62, 77, 15);
		panel_add.add(label_num);*/
		
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
		label_location = new JLabel("馆藏地点：");
		label_location.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_location.setBounds(82, 234, 77, 15);
		panel_add.add(label_location);
		
		/*		JLabel label_time = new JLabel("入库时间：");
		label_time.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_time.setBounds(82, 277, 74, 15);
		panel_add.add(label_time);
		
		
		
		label_station = new JLabel("借阅状态：");
		label_station.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_station.setBounds(82, 312, 74, 15);
		panel_add.add(label_station);
*/		
		okbutton = new JButton("添加");
		okbutton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		okbutton.addActionListener(this);
		okbutton.setBounds(188, 440, 93, 23);
		panel_add.add(okbutton);
		
		cancelbutton = new JButton("取消");
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
		/*
		 * "馆藏地点“的三个选项按钮
		 */
		//"四牌楼"
		rdbtnNewRadioButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		rdbtnNewRadioButton.setBounds(241, 230, 82, 23);
		rdbtnNewRadioButton.setFocusPainted(false);
		rdbtnNewRadioButton.addActionListener(this);
		panel_add.add(rdbtnNewRadioButton);
		//"丁家桥"
		rdbtnNewRadioButton_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		rdbtnNewRadioButton_1.setBounds(355, 230, 77, 23);
		rdbtnNewRadioButton_1.setFocusPainted(false);
		rdbtnNewRadioButton_1.addActionListener(this);
		panel_add.add(rdbtnNewRadioButton_1);
		//"九龙湖"
		rdbtnNewRadioButton_2.setFont(new Font("微软雅黑", Font.BOLD, 14));
		rdbtnNewRadioButton_2.setBounds(450, 230, 82, 23);
		rdbtnNewRadioButton_2.addActionListener(this);
		panel_add.add(rdbtnNewRadioButton_2);
		
		locationGroup=new ButtonGroup();
		locationGroup.add(rdbtnNewRadioButton);
		locationGroup.add(rdbtnNewRadioButton_1);
		locationGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton.setSelected(true);
/*		
		//状态选择的两个选项按钮
		//"已借出"
		rdbtnNewRadioButton_3.setFont(new Font("微软雅黑", Font.BOLD, 14));
		rdbtnNewRadioButton_3.setBounds(241, 308, 82, 23);
		rdbtnNewRadioButton_3.setFocusPainted(false);
		panel_add.add(rdbtnNewRadioButton_3);
		
		//"未借出"
		rdbtnNewRadioButton_4.setFont(new Font("微软雅黑", Font.BOLD, 14));
		rdbtnNewRadioButton_4.setBounds(355, 308, 77, 23);
		rdbtnNewRadioButton_4.setFocusPainted(false);
		panel_add.add(rdbtnNewRadioButton_4);
		
		ButtonGroup stationGroup=new ButtonGroup();
		stationGroup.add(rdbtnNewRadioButton_3);
		stationGroup.add(rdbtnNewRadioButton_4);
		rdbtnNewRadioButton_3.setSelected(true);
	
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		textField_time = new JTextField("单击以选择日期");
		textField_time.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		dateChooser.register(textField_time);
		textField_time.setBounds(241, 275, 291, 21);
		panel_add.add(textField_time);*/	
//		textField_time.setColumns(10);
		
		
		//=============================”图书出库“面板=========================================
		panel_delete=new JPanel();
		panel_delete.setLayout(null);
		panel_buttom.add("delete",panel_delete);
		
		textField_2 = new JTextField();
		textField_2.setBounds(77, 31, 442, 21);
		panel_delete.add(textField_2);
		textField_2.setColumns(10);
		
		btnNewButton = new JButton("搜索");//出库的搜索按钮
		btnNewButton.setBounds(565, 30, 93, 23);
		btnNewButton.addActionListener(this);
		panel_delete.add(btnNewButton);
		
		//创建滚动面板上的表格
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 68, 696, 443);
		panel_delete.add(scrollPane_1);
		//定义表格列名的数组
		//定义表格数据数组
		
		tableModel_1=new DefaultTableModel(tableValues_1,columnNames_1);//创建指定列名和数据的表格	
		table_1=new JTable(tableModel_1) {
			public boolean isCellEditable(int row, int column)
            {
				if(column!=7) {
                       return false;//表格不允许被编辑
				}else
					return true;
            }
		};
		 //设置表数据居中显示
		DefaultTableCellRenderer cr_1 = new DefaultTableCellRenderer();
		cr_1.setHorizontalAlignment(JLabel.CENTER);
		 table_1.setDefaultRenderer(Object.class, cr_1);
		 
		 //这个删除按钮的class在component的TableDeleteButtonEditor()里
		 checkBox = new JCheckBox();
		 checkBox.addActionListener(this);
		 table_1.getColumn("操作").setCellEditor(new TableDeleteButtonEditor(checkBox,token));
		 //我这里无法获取更改后的“操作”列里的信息，界面上点击删除后“删除”按钮会变为不可点击的“已删除”按钮，但我这里怎么接受返回的信息呢？
		 //在TableDeleteButtonEditor()里有返回
		 scrollPane_1.setViewportView(table_1);
		 
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
		button_exit.setFont(new Font("微软雅黑", Font.BOLD, 14));
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
			if(e.getSource()==okbutton) {				
				addBook();
				}
			if (e.getSource() ==checkBox) {
				int rowCount=table_1.getRowCount(); 
				 for(int i=0;i<rowCount;i++) {
//					Object statement=tableModel_1.getValueAt(i, 7);
//					if(statement.equals("已删除")) {
//						tableModel_1.removeRow(i);
					System.out.println("statement真的删了？:");
					}
				 }
			if(e.getSource()==button_select) {
				findbook();
			}
			if(e.getSource() == btnNewButton) {
				System.out.println("选了");
				findBooktodelete();
			}

	}

	//在库面板的搜索书
	public void findbook() {
		String select_key = textField_select.getText();
		Client.Request request = new Request();
		request.setPath("book/queryByFlag");
		request.setToken(token);
		request.getParams().put("author", select_key);
		request.getParams().put("name", select_key);
		Client.Response response = Client.fetch(request);
		if(response.getSuccess()) {
			tableModel.setRowCount(0);

			ArrayList<LinkedTreeMap<String, Object>> booksinfoList = 
					(ArrayList<LinkedTreeMap<String, Object>>) response
					.getBody().get("booksInfoMapList");
			for(LinkedTreeMap<String,Object> bookinfo:booksinfoList) {
				String[] rowValues = new String[7];			
				rowValues[0] = (String) bookinfo.get("uuid");
				rowValues[1]= (String) bookinfo.get("name");
				rowValues[2] = (String) bookinfo.get("author");
				rowValues[3] = (String) bookinfo.get("publisher");
				rowValues[4] = (String) bookinfo.get("location");
				long createTime = (long)(double)bookinfo.get("createTime")*1000;
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
				rowValues[5] = df.format(new Date(createTime));
				if((boolean) bookinfo.get("isReturn")) {
				rowValues[6] = "未借出";
				}else rowValues[6] = "已借出";
				tableModel.addRow(rowValues);						
			}
			int rowCount = table.getRowCount();
			for (int i = 0; i < rowCount; i++)
			{
				Object statement = tableModel.getValueAt(i, 6);
//				System.out.println("statement:"+statement);
				// 假如这本书状态是“未借出“，就在这行最后一列为其添加可选择”借阅“按钮
				if (statement.equals("未借出"))
				{
					tableModel.setValueAt("可借阅", i, 6);// 重置名字
				}
			}
			
		}else {
			 JOptionPane.showMessageDialog(null, "库存里没有此书", "查询失败",JOptionPane.ERROR_MESSAGE); 
		}
		
	}
	//出库面板的搜索书
	public void findBooktodelete(){
		String select_key = textField_2.getText();
		Client.Request request = new Request();
		request.setPath("book/queryByFlag");
		request.setToken(token);
		request.getParams().put("author", select_key);
		request.getParams().put("name", select_key);
		Client.Response response = Client.fetch(request);
		if(response.getSuccess()) {
			tableModel_1.setRowCount(0);

			ArrayList<LinkedTreeMap<String, Object>> booksinfoList = 
					(ArrayList<LinkedTreeMap<String, Object>>) response
					.getBody().get("booksInfoMapList");
			for(LinkedTreeMap<String,Object> bookinfo:booksinfoList) {
				String[] rowValues = new String[8];			
				rowValues[0] = (String) bookinfo.get("uuid");
				rowValues[1]= (String) bookinfo.get("name");
				rowValues[2] = (String) bookinfo.get("author");
				rowValues[3] = (String) bookinfo.get("publisher");
				rowValues[4] = (String) bookinfo.get("location");
				long createTime = (long)(double)bookinfo.get("createTime")*1000;
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
				rowValues[5] = df.format(new Date(createTime));
				if((boolean) bookinfo.get("isReturn")) {
				rowValues[6] = "未借出";
				}else rowValues[6] = "已借出";
				rowValues[7]="点我删除";
				tableModel_1.addRow(rowValues);						
			}
			int rowCount = table.getRowCount();
			for (int i = 0; i < rowCount; i++)
			{
				Object statement = tableModel_1.getValueAt(i, 6);
//				System.out.println("statement:"+statement);
				// 假如这本书状态是“未借出“，就在这行最后一列为其添加可选择”借阅“按钮
				if (statement.equals("未借出"))
				{
					tableModel_1.setValueAt("可借阅", i, 6);// 重置名字
				}
				
			}
			}else
			{
				
			}
			
	}
	
	
	
	public void addBook() {
		Client.Request request = new Request();
		request.setPath("book/addBook");
		request.setToken(token);
		request.getParams().put("name", textField_name.getText());
		request.getParams().put("author", textField_author.getText());
		request.getParams().put("publisher", textField_publisher.getText());
		String value = " ";
		if(rdbtnNewRadioButton.isSelected()) value =rdbtnNewRadioButton.getText();
		if(rdbtnNewRadioButton_1.isSelected()) value = rdbtnNewRadioButton_1.getText();
		if(rdbtnNewRadioButton_2.isSelected()) value =rdbtnNewRadioButton.getText();
		request.getParams().put("location", value);
		request.getParams().put("isbn", "ABC");
		Response response = Client.fetch(request);
		if(response.getSuccess()) {
			JOptionPane.showMessageDialog(null, "添加成功", "成功",JOptionPane.INFORMATION_MESSAGE); 
		}else {
			JOptionPane.showMessageDialog(null, "添加失败，请联系后台", "失败",JOptionPane.ERROR_MESSAGE); 
		}	
	}
	
	public static long dateTimeStampSecond(String date_str,String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(date_str).getTime()/1000;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
