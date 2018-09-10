//显示“借还信息”
package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;
import com.wolfTungsten.vcampusClient.component.TableButtonEditor;
import com.wolfTungsten.vcampusClient.component.TableReBorButtonEditor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class LibMessage extends JPanel {
	JLabel label_cardNum,label_name;
	private JTextField textField_cardNum;
	private JTextField textField_name;
	String token ;
	String[][] tableValues;
	String name;
	String cardnum;
	private JButton button_returnbook;
	private JLabel lblTips;
	private JTextArea textArea;
	/**
	 * Create the panel.
	 */
	public LibMessage(String Token,String[][] tableValue,String Name,String Cardnum) {
		tableValues = tableValue;
		token = Token;
		name = Name;
		cardnum = Cardnum;
		setSize(736,600);
		setLayout(null);//绝对布局
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 76, 696, 428);
		add(scrollPane);
		
		String[] columnNames= {"编号","书名","作者","出版社","借阅时间","归还时间","到期时间","续借状态"};//定义表格列名的数组
		//数据传到这里！

		DefaultTableModel tableModel=new DefaultTableModel(tableValues,columnNames);//创建指定列名和数据的表格	
		JTable table=new JTable(tableModel) {
			public boolean isCellEditable(int row, int column)
            {
				if(column!=7) {
                       return false;//表格不允许被编辑
				}else
					return true;
            }
		};
		 //设置表数据居中显示
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		 table.setDefaultRenderer(Object.class, cr);
		//遍历所有行
		 int rowCount=table.getRowCount(); 
		 for(int i=0;i<rowCount;i++) {
			Object re_statement=tableModel.getValueAt(i, 7);
	//		System.out.println("statement:"+re_statement);
			//假如这本书状态是“可借出“，就在这行最后一列为其添加可选择”续借“按钮
			if(re_statement.equals("可续借")) {
				tableModel.setValueAt("续借", i, 7);//重置名字
			}
		 }
		//”续借“状态按钮添加，具体看component里的TableReBorButtonEidtor
		 table.getColumn("续借状态").setCellEditor(new TableReBorButtonEditor(new JCheckBox(),token));
		//我这里无法获取更改后的“续借状态”列里的信息，界面上点击续借后“续借”按钮会变为不可点击的“不可续借”按钮，但我这里怎么接受返回的信息呢？
		 //在TableReBorButtonEditor()里有返回
		 
		 /*
		  * yhd看过来，我从这里开始添加的
		  */
		 table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//设置表格的选择模式为单选
		 
		scrollPane.setViewportView(table);
		
		label_cardNum = new JLabel("一卡通号：");
		label_cardNum.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_cardNum.setBounds(292, 30, 77, 32);
		add(label_cardNum);
		
		label_name = new JLabel("姓名:");
		label_name.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_name.setBounds(548, 30, 54, 32);
		add(label_name);
		
		textField_cardNum = new JTextField();
		textField_cardNum.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_cardNum.setBounds(403, 30, 96, 32);
		textField_cardNum.setEditable(false);
		textField_cardNum.setOpaque(false);
		textField_cardNum.setText(cardnum);
		add(textField_cardNum);
		textField_cardNum.setColumns(10);
		
		textField_name = new JTextField();
		textField_name.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_name.setBounds(627, 30, 89, 32);
		textField_name.setEditable(false);
		textField_name.setOpaque(false);
		textField_name.setText(name);
		add(textField_name);
		textField_name.setColumns(10);
		/*
		 * 还书按钮在这里
		 */
		button_returnbook = new JButton("归还此书");
		button_returnbook.setFont(new Font("微软雅黑", Font.BOLD, 14));
		button_returnbook.setBounds(620, 528, 96, 33);
		button_returnbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				if(selectedRow!=-1) {
//					request.setPath("book/returnBook");
//					request.getParams().put("uuid","c942a5d9-cc21-4d08-8847-ce94a68b2364");
//					response = Client.fetch(request);
					Client.Request request = new Request();
					request.setPath("book/returnBook");
					String uuid = (String) tableModel.getValueAt(selectedRow, 0);
					request.setToken(token);
					request.getParams().put("uuid", uuid);
					Response response = Client.fetch(request);
					if (response.getSuccess())
					{
						tableModel.setValueAt("已归还", selectedRow, 6);
						tableModel.setValueAt("/", selectedRow, 7);
					} else
						JOptionPane.showMessageDialog(null, "归还失败", "失败", JOptionPane.ERROR_MESSAGE);
						
			//		tableModel.setValueAt("归还时间（我没有）", selectedRow, 5);
					
					
					}
				}
		});
		add(button_returnbook);
		
		lblTips = new JLabel("Tips：");
		lblTips.setFont(new Font("微软雅黑", Font.BOLD, 12));
		lblTips.setBounds(20, 514, 54, 15);
		add(lblTips);
		
		textArea = new JTextArea();
		textArea.setText("每本书的借阅期限为30天，在未过期时可续借一次，续借期限为30天。未过期前，选中要归还的书后点击”归还此书“，即可正常还书。");
		textArea.setBounds(84, 514, 415, 47);
		textArea.setLineWrap(true);
		textArea.setOpaque(false);
		textArea.setEditable(false);
		add(textArea);
	}
}
