//图书馆-借阅图书Panel
package com.wolfTungsten.vcampusClient.panel;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.google.gson.internal.LinkedTreeMap;
import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;
import com.wolfTungsten.vcampusClient.component.TableButtonEditor;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
public class LibFindBooksPanel extends JPanel implements FocusListener,ActionListener, MouseListener{
	private static final long serialVersionUID = 1L;
	private JTextField textField_select;
	JButton button_select;
	CardLayout cardLayout=new CardLayout();

	String[] columnNames= {"编号","书名","作者","出版社","馆藏地点","状态"};//定义表格列名的数组
	String[][] tableValues= {};
	DefaultTableModel tableModel;
	JTable table;
	String token ;

	

	// Create the panel.
	public LibFindBooksPanel(String Token) {
		setSize(736,600);
		setLayout(null);//绝对布局
		token = Token;
		//按书名/作者检索的输入文本框
		textField_select= new JTextField();
		textField_select.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_select.setForeground(Color.LIGHT_GRAY);
		textField_select.setText("书名/作者");
		textField_select.setBounds(20, 20, 560, 36);
		textField_select.addFocusListener(this);
		textField_select.addMouseListener(this);
		
		add(textField_select);
		textField_select.setColumns(10);
		//“搜索”按钮
		button_select = new JButton("搜索");
		button_select.setFont(new Font("微软雅黑", Font.BOLD, 14));

		



		button_select.setBounds(590, 20, 126, 36);
		button_select.addActionListener(this);
		add(button_select);		
		
		JScrollPane scrollPane = new JScrollPane();//创建显示表格的滚动面板
		scrollPane.setBounds(20, 76, 696, 514);
		add(scrollPane);
		
		scrollPane.addMouseListener(this);
	
		//定义表格数据数组
		/**
		 *aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
		 */
		//==============这些值传过来的时候就带有“未借出”“已借出”的状态，方便下面判断是否加“借阅”按钮,yhd说偷偷摸摸加了
		//==============测试数据===========================================

		tableModel=new DefaultTableModel(tableValues,columnNames);//创建指定列名和数据的表格	
		table=new JTable(tableModel);
		 //设置表数据居中显示
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		 table.setDefaultRenderer(Object.class, cr);
		//遍历所有行
		
		//我这里无法获取更改后的“状态”列里的信息，界面上点击借阅后“借阅”按钮会变为不可点击的“已借出”按钮，但我这里怎么接受返回的信息呢？
		 //在TablerButtonEditor()里有返回
		scrollPane.setViewportView(table);

	}
	
	//文本框内显示提示内容
	//这里有个问题，移出此面板后再移动回来，原来的检索记录还在，这是不是很呆？（其实也还行...)
	@Override
	public void focusGained(FocusEvent e) {	
		//为什么点"借阅“按钮，文本框也会清空，关你什么事？?????体验极差呀,因为这样一旦二级菜单出现在该页面上。bug就出现了。。。
		//已验证：和BautyEye美化包无关
		if(e.getSource()==textField_select) {
			textField_select.setText("");
		}
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
				Client.Request request = new Request();
				request.setPath("book/queryByFlag");
				request.setToken(token);
				request.getParams().put("author", select_key);
				request.getParams().put("name", select_key);
				Client.Response response = Client.fetch(request);
				if(response.getSuccess())
				{
					ArrayList<LinkedTreeMap<String, Object>> booksinfoList =
							(ArrayList<LinkedTreeMap<String, Object>>) response.getBody().get("booksInfoMapList");
//					String[] columnNames= {"编号","书名","作者","出版社","馆藏地点","状态"}
					
					for(LinkedTreeMap<String,Object> bookinfo:booksinfoList) {
						String[] rowValues = new String[6];			
						rowValues[0] = (String) bookinfo.get("uuid");
						rowValues[1]= (String) bookinfo.get("name");
						rowValues[2] = (String) bookinfo.get("author");
						rowValues[3] = (String) bookinfo.get("publisher");
						rowValues[4] = (String) bookinfo.get("location");
						if((boolean) bookinfo.get("isReturn")) {
						rowValues[5] = "未借出";
						}else rowValues[5] = "已借出";
						tableModel.addRow(rowValues);						
					}
					 int rowCount=table.getRowCount(); 
						 for(int i=0;i<rowCount;i++) {
							Object statement=tableModel.getValueAt(i, 5);
//							System.out.println("statement:"+statement);
							//假如这本书状态是“未借出“，就在这行最后一列为其添加可选择”借阅“按钮
							if(statement.equals("未借出")) {
								tableModel.setValueAt("借阅", i, 5);//重置名字						
							}
						 }
						//”借阅“状态按钮添加，具体看component里的TableButtonEidtor
						table.getColumn("状态").setCellEditor(new TableButtonEditor(new JCheckBox(),token));		
				}else {
					 JOptionPane.showMessageDialog(null, "没有找到此书", "查询失败",JOptionPane.ERROR_MESSAGE); 
					
				}

			   //怎么检索？？？
			//往表格中添加新的行
			//String[] rowValues= {};
		    //tableModel.addRow(rowValues);
			}
		}
	}
	
	public void mouseEntered(MouseEvent e) {
		dispatchEvent(e);
	}
	
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
