//教务处——学生选课面板
package com.wolfTungsten.vcampusClient.panel;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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


public class JwcSelectCourses extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private JLabel label_cardNum,label_name;
	private JTextField textField_cardNum ;
	private JTextField textField_name;
	private String[] columnNames = {"课程名称","任课老师","上课地点","上课时间","  ","   "}; //表格列名 第五列空出来放置选课退课按钮
	private String token;
	String[][]tableValues;   //表格数据数组
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
												
		String[] columnNames = {"课程编号","课程名称","任课老师","上课地点","上课时间","  ","   "}; //表格列名 倒数两列列空出来放置选课退课按钮

		model = new DefaultTableModel(tableValues, columnNames);
		table = new JTable(model) {
			public boolean isCellEditable(int row,int column){
                if(column == table.getColumnCount()-1 && column == table.getColumnCount()-2)return true;
                else return false;
			}
		};
		DefaultTableCellRenderer er = new DefaultTableCellRenderer();
		er.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, er);
		table.addMouseListener(this);
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

	//  SCTableCellEditorAdd editor1 = new SCTableCellEditorAdd();
	//  SCTableCellEditorCancel editor2 = new SCTableCellEditorCancel();

		table.getColumnModel().getColumn(table.getColumnCount()-2).setCellRenderer(renderer1);
		table.getColumnModel().getColumn(table.getColumnCount()-1).setCellRenderer(renderer2);
	//  table.getColumnModel().getColumn(table.getColumnCount()-2).setCellEditor(editor1);
	//  table.getColumnModel().getColumn(table.getColumnCount()-1).setCellEditor(editor2);
	//	table.setRowSelectionAllowed(false);// 禁止表格的选择功能。不然在点击按钮时表格的整行都会被选中。
		
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

		model.addRow(new String[] {"01","计组","任国林","","","已选择","已选择"});
		model.addRow(new String[] {"02","计组","徐造林","","","已选择","已选择"});
		model.addRow(new String[] {"03","信号","王世杰","","","已选择","已选择"});
		model.addRow(new String[] {"04","信号","123","","","已选择","已选择"});
		model.addRow(new String[] {"05","高数","213","","","已选择","已选择"});
		model.addRow(new String[] {"06","几代","321","","","未选择","未选择"});
		model.addRow(new String[] {"07","离散","李凯","","","未选择","未选择"});
		model.addRow(new String[] {"08","算法","方效林","","","未选择","未选择"});
		
//上面两个标签 用于显示用户 姓名 和 一卡通号码

		String username = textField_name.getText();
		String cardNum  = textField_cardNum.getText();


	}

	@SuppressWarnings("unused")
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		if(e.getClickCount()==1) {
			int row = table.rowAtPoint(e.getPoint());
			int column = table.columnAtPoint(e.getPoint());
	//====================================================课程选择 倒数第二列 ==============================		
			if(column == table.getColumnCount()-2 && String.valueOf(table.getValueAt(row, column)).equals("未选择")) {
				//此时只有未选择时才可以点击
				int isOK = JOptionPane.showConfirmDialog(null, "确定选择该课程？", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
				if(isOK == JOptionPane.YES_OPTION) {
				   table.setValueAt("已选择", row, column);
				   table.setValueAt("已选择", row, column+1); //此时column和column是倒数第二和倒数第一列
				   //从这里开始 ---------------------- 课程选择
				   
				   
				   
				   
				}
			}
			
	//====================================================取消选择 倒数第一列==================================
			if(column == table.getColumnCount()-1 && String.valueOf(table.getValueAt(row, column)).equals("已选择")) {
				//从这里开始 只有已选择才可以点击
				int isCancel = JOptionPane.showConfirmDialog(null, "确定取消选择该课程？", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
				if(isCancel == JOptionPane.YES_OPTION){			
					table.setValueAt("未选择",row,column-1);
					table.setValueAt("未选择", row, column); //此时column-1和column是倒数第二和倒数第一列
					//从这里开始----------------------------取消选择
					
					
				}
			}
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

}


