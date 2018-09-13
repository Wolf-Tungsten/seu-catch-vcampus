//“账单明细“面板
package com.wolfTungsten.vcampusClient.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.google.gson.internal.LinkedTreeMap;
import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;
import com.wolfTungsten.vcampusClient.component.TableReBorButtonEditor;
import javax.swing.JComboBox;

public class BankBill extends JPanel implements ItemListener{
	JLabel label_cardNum,label_name;
	private JTextField textField_cardNum;
	private JTextField textField_name;
	int time=0;
    int type=0;
	JComboBox comboBox_time,comboBox_bill_type;
	JScrollPane scrollPane;
	String[] columnNames= {"时间","对方姓名","对方账号","金额"};//定义表格列名的数组
	//TODO 传数据给tableValues
	String[][] tableValues= {};
	DefaultTableModel tableModel;
	JTable table=new JTable(tableModel);

	private String token;
	/**
	 * Create the panel.
	 */

	public BankBill(String Token) {
		token=Token;
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
		table.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 25));// 设置表头大小
        head.setFont(new Font("微软雅黑", Font.BOLD, 14));// 设置表格字体
        table.setRowHeight(28);// 设置表格行宽
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, cr);
		scrollPane.setViewportView(table);
		
		label_cardNum = new JLabel("一卡通号：");
		label_cardNum.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		label_cardNum.setBounds(312, 13, 77, 30);
		add(label_cardNum);
		
		label_name = new JLabel("姓名:");
		label_name.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		label_name.setBounds(553, 13, 43, 30);
		add(label_name);
		
		textField_cardNum = new JTextField();
		textField_cardNum.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		textField_cardNum.setBounds(399, 13, 120, 30);
		textField_cardNum.setEditable(false);
		textField_cardNum.setOpaque(false);
		add(textField_cardNum);
		textField_cardNum.setColumns(10);
		
		textField_name = new JTextField();
		textField_name.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		textField_name.setBounds(606, 14, 120, 30);
		textField_name.setEditable(false);
		textField_name.setOpaque(false);
		add(textField_name);
		textField_name.setColumns(10);
		
		comboBox_time = new JComboBox();
		comboBox_time.setFocusable(false);
		comboBox_time.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});
		comboBox_time.setFont(new Font("微软雅黑", Font.BOLD, 13));
		comboBox_time.setBounds(10, 12, 80, 30);
		comboBox_time.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {		
					if(comboBox_time.getSelectedIndex()==0) {
						time=0;
						typeBill(type,duration(time));
						
						System.out.println("time:"+time);
					}else if(comboBox_time.getSelectedIndex()==1) {
						time=1;
						System.out.println("time:"+time);
						typeBill(type,duration(time));
					}else if(comboBox_time.getSelectedIndex()==2) {
						System.out.println("yhddsb1");
						time=2;
						System.out.println("time:"+time);
						typeBill(type,duration(time));
					}else if(comboBox_time.getSelectedIndex()==3) {
						time=3;
						System.out.println("time:"+time);
						typeBill(type,duration(time));
					}else if(comboBox_time.getSelectedIndex()==4) {
						time=4;
						System.out.println("time:"+time);
						typeBill(type,duration(time));
					}
				}
				
			}
			
		});
		comboBox_time.addItemListener(this);
		comboBox_time.addItem("三天内");
		comboBox_time.addItem("一月内");
		comboBox_time.addItem("三月内");
		comboBox_time.addItem("半年内");
		comboBox_time.addItem("一年内");
		add(comboBox_time);
		
		comboBox_bill_type = new JComboBox();
		comboBox_bill_type.setFont(new Font("微软雅黑", Font.BOLD, 13));
		comboBox_bill_type.setBounds(96, 12, 80,30);
		comboBox_bill_type.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {		
					if(comboBox_bill_type.getSelectedIndex()==0) {
						type=0;
						System.out.println("type:"+type);
						typeBill(type,duration(time));
					}else if(comboBox_bill_type.getSelectedIndex()==1) {
						type=1;
						
						System.out.println("type:"+type);
						typeBill(type,duration(time));
					}else if(comboBox_bill_type.getSelectedIndex()==2) {
						
						type=2;
						int a =time;
						System.out.println("type:"+type);
						typeBill(type,duration(time));
					}
				}
				
			}
			
		});
		
		comboBox_bill_type.addItemListener(this);
		comboBox_bill_type.addItem("总账单");
		comboBox_bill_type.addItem("收入账单");
		comboBox_bill_type.addItem("支出账单");
		add(comboBox_bill_type);
		
		
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
	/*		if (e.getStateChange() == ItemEvent.SELECTED) {
				
				if(comboBox_time.getSelectedIndex()==0) {
					
					if(comboBox_bill_type ==null) System.out.println("null");
					if(comboBox_bill_type.getSelectedIndex()==0) {
						
						Client.Request request = new Request();
						request.setPath("bank/bill");
						request.setToken(token);
						request.getParams().put("period", 259200);
						Response response = Client.fetch(request);
						HashMap<String, Object> bill = new HashMap<>();
						ArrayList<LinkedTreeMap<String, Object>> billList = (ArrayList<LinkedTreeMap<String, Object>>) response
								.getBody().get("bill");
						
						int rowcount = bill.size();
						
						for (int i = 0; i < rowcount; i++) {
							LinkedTreeMap<String, Object> billMap = billList.get(i);
						

							String []rowValue = new String[4];
							rowValue[0] = (String) billMap.get("createtime");
							rowValue[1] = (String) billMap.get("otherName");
							rowValue[2] = (String) billMap.get("otherCardnum");
							rowValue[3] = (String) billMap.get("value");
							tableModel.addRow(rowValue);
						}
						
						String myName=(String)response.getBody().get("myName");
						textField_name.setText(myName);
						String myCardNum=(String)response.getBody().get("myCardnum");
						textField_cardNum.setText(myCardNum);

					     //TODO 三天内总账单
						//eg.传数据给rowValue,"时间","对方姓名","对方账号","金额"
		
							
						}
						
						
					}else if(comboBox_bill_type.getSelectedIndex()==1) {
						Client.Request request = new Request();
						request.setPath("bank/toBill");
						request.setToken(token);
						request.getParams().put("period", 259200);
						Response response = Client.fetch(request);
						String myName=(String)response.getBody().get("myName");
						textField_name.setText(myName);
						String myCardNum=(String) response.getBody().get("myCardnum");
						textField_cardNum.setText(myCardNum);
						HashMap<String, Object> toBill = new HashMap<>();
						ArrayList<LinkedTreeMap<String, Object>> toBillList = (ArrayList<LinkedTreeMap<String, Object>>) response
								.getBody().get("toBill");
						
						int rowcount = toBill.size();
						
						for (int i = 0; i < rowcount; i++) {
							LinkedTreeMap<String, Object> toBillMap = toBillList.get(i);
							if (toBillMap == null)
								System.out.println("sssss");

							String []rowValue = new String[4];
							rowValue[0] = (String) toBillMap.get("createtime");
							rowValue[1] = (String) toBillMap.get("fromName");
							rowValue[2] = (String) toBillMap.get("fromCardnum");
							rowValue[3] = (String) toBillMap.get("value");
							tableModel.addRow(rowValue);
						}
						

					     //TODO 三天内收入账单		 
					 }else if(comboBox_bill_type.getSelectedIndex()==2) {
						    Client.Request request = new Request();
						    request.setPath("bank/fromBill");
							request.setToken(token);
							request.getParams().put("period", 259200);
							Response response = Client.fetch(request);
							String myName=(String)response.getBody().get("myName");
							textField_name.setText(myName);
							String myCardNum=(String) response.getBody().get("myCardnum");
							textField_cardNum.setText(myCardNum);
							HashMap<String, Object> fromBill = new HashMap<>();
							ArrayList<LinkedTreeMap<String, Object>> fromBillList = (ArrayList<LinkedTreeMap<String, Object>>) response
									.getBody().get("fromBill");
							
							int rowcount = fromBill.size();
							
							for (int i = 0; i < rowcount; i++) {
								LinkedTreeMap<String, Object> fromBillMap = fromBillList.get(i);
								if (fromBillMap == null)
									System.out.println("sssss");

								String []rowValue = new String[4];
								rowValue[0] = (String) fromBillMap.get("createtime");
								rowValue[1] = (String) fromBillMap.get("toName");
								rowValue[2] = (String) fromBillMap.get("toCardnum");
								rowValue[3] = (String) fromBillMap.get("value");
								tableModel.addRow(rowValue);
							}
			
					     //TODO 三天内支出账单
						 
					}
				}else if(comboBox_time.getSelectedIndex()==1) {	
					if(comboBox_bill_type.getSelectedIndex()==0) {
						Client.Request request = new Request();
						request.setPath("bank/bill");
						request.setToken(token);
						request.getParams().put("period", 2592000);
						Response response = Client.fetch(request);
						String myName=(String)response.getBody().get("myName");
						textField_name.setText(myName);
						String myCardNum=(String) response.getBody().get("myCardnum");
						textField_cardNum.setText(myCardNum);
						HashMap<String, Object> bill = new HashMap<>();
						ArrayList<LinkedTreeMap<String, Object>> billList = (ArrayList<LinkedTreeMap<String, Object>>) response
								.getBody().get("bill");
						
						int rowcount = bill.size();
						
						for (int i = 0; i < rowcount; i++) {
							LinkedTreeMap<String, Object> billMap = billList.get(i);
							if (billMap == null)
								System.out.println("sssss");

							String []rowValue = new String[4];
							rowValue[0] = (String) billMap.get("createtime");
							rowValue[1] = (String) billMap.get("otherName");
							rowValue[2] = (String) billMap.get("otherCardnum");
							rowValue[3] = (String) billMap.get("value");
							tableModel.addRow(rowValue);
						}
					     //TODO 一月内总账单
					}else if(comboBox_bill_type.getSelectedIndex()==1) {
						Client.Request request = new Request();
						request.setPath("bank/toBill");
						request.setToken(token);
						request.getParams().put("period", 2592000);
						Response response = Client.fetch(request);
						String myName=(String)response.getBody().get("myName");
						textField_name.setText(myName);
						String myCardNum=(String) response.getBody().get("myCardnum");
						textField_cardNum.setText(myCardNum);
						HashMap<String, Object> toBill = new HashMap<>();
						ArrayList<LinkedTreeMap<String, Object>> toBillList = (ArrayList<LinkedTreeMap<String, Object>>) response
								.getBody().get("toBill");
						
						int rowcount = toBill.size();
						
						for (int i = 0; i < rowcount; i++) {
							LinkedTreeMap<String, Object> toBillMap = toBillList.get(i);
							if (toBillMap == null)
								System.out.println("sssss");

							String []rowValue = new String[4];
							rowValue[0] = (String) toBillMap.get("createtime");
							rowValue[1] = (String) toBillMap.get("fromName");
							rowValue[2] = (String) toBillMap.get("fromCardnum");
							rowValue[3] = (String) toBillMap.get("value");
							tableModel.addRow(rowValue);
						}
					     //TODO 一月内收入账单		 
					 }else if(comboBox_bill_type.getSelectedIndex()==2) {
						 Client.Request request = new Request();
						 request.setPath("bank/fromBill");
							request.setToken(token);
							request.getParams().put("period", 2592000);
							Response response = Client.fetch(request);
							String myName=(String)response.getBody().get("myName");
							textField_name.setText(myName);
							String myCardNum=(String) response.getBody().get("myCardnum");
							textField_cardNum.setText(myCardNum);
							HashMap<String, Object> fromBill = new HashMap<>();
							ArrayList<LinkedTreeMap<String, Object>> fromBillList = (ArrayList<LinkedTreeMap<String, Object>>) response
									.getBody().get("fromBill");
							
							int rowcount = fromBill.size();
							
							for (int i = 0; i < rowcount; i++) {
								LinkedTreeMap<String, Object> fromBillMap = fromBillList.get(i);
								if (fromBillMap == null)
									System.out.println("sssss");

								String []rowValue = new String[4];
								rowValue[0] = (String) fromBillMap.get("createtime");
								rowValue[1] = (String) fromBillMap.get("toName");
								rowValue[2] = (String) fromBillMap.get("toCardnum");
								rowValue[3] = (String) fromBillMap.get("value");
								tableModel.addRow(rowValue);
							}
					     //TODO一月内 支出账单
						 
					}
				 }else if(comboBox_time.getSelectedIndex()==2) {
					 if(comboBox_bill_type.getSelectedIndex()==0) {
						 Client.Request request = new Request();
						 request.setPath("bank/bill");
							request.setToken(token);
							request.getParams().put("period", 7776000);
							Response response = Client.fetch(request);
							String myName=(String)response.getBody().get("myName");
							textField_name.setText(myName);
							String myCardNum=(String) response.getBody().get("myCardnum");
							textField_cardNum.setText(myCardNum);
							HashMap<String, Object> bill = new HashMap<>();
							ArrayList<LinkedTreeMap<String, Object>> billList = (ArrayList<LinkedTreeMap<String, Object>>) response
									.getBody().get("bill");
							
							int rowcount = bill.size();
							
							for (int i = 0; i < rowcount; i++) {
								LinkedTreeMap<String, Object> billMap = billList.get(i);
								if (billMap == null)
									System.out.println("sssss");

								String []rowValue = new String[4];
								rowValue[0] = (String) billMap.get("createtime");
								rowValue[1] = (String) billMap.get("otherName");
								rowValue[2] = (String) billMap.get("otherCardnum");
								rowValue[3] = (String) billMap.get("value");
								tableModel.addRow(rowValue);
							}
					     //TODO 三月内总账单
					}else if(comboBox_bill_type.getSelectedIndex()==1) {
						Client.Request request = new Request();
						request.setPath("bank/toBill");
						request.setToken(token);
						request.getParams().put("period", 7776000);
						Response response = Client.fetch(request);
						String myName=(String)response.getBody().get("myName");
						textField_name.setText(myName);
						String myCardNum=(String) response.getBody().get("myCardnum");
						textField_cardNum.setText(myCardNum);
						HashMap<String, Object> toBill = new HashMap<>();
						ArrayList<LinkedTreeMap<String, Object>> toBillList = (ArrayList<LinkedTreeMap<String, Object>>) response
								.getBody().get("toBill");
						
						int rowcount = toBill.size();
						
						for (int i = 0; i < rowcount; i++) {
							LinkedTreeMap<String, Object> toBillMap = toBillList.get(i);
							if (toBillMap == null)
								System.out.println("sssss");

							String []rowValue = new String[4];
							rowValue[0] = (String) toBillMap.get("createtime");
							rowValue[1] = (String) toBillMap.get("fromName");
							rowValue[2] = (String) toBillMap.get("fromCardnum");
							rowValue[3] = (String) toBillMap.get("value");
							tableModel.addRow(rowValue);
						}
					     //TODO 三月内收入账单		 
					 }else if(comboBox_bill_type.getSelectedIndex()==2) {
						 Client.Request request = new Request();
						 request.setPath("bank/fromBill");
							request.setToken(token);
							request.getParams().put("period", 7776000);
							Response response = Client.fetch(request);
							String myName=(String)response.getBody().get("myName");
							textField_name.setText(myName);
							String myCardNum=(String) response.getBody().get("myCardnum");
							textField_cardNum.setText(myCardNum);
							HashMap<String, Object> fromBill = new HashMap<>();
							ArrayList<LinkedTreeMap<String, Object>> fromBillList = (ArrayList<LinkedTreeMap<String, Object>>) response
									.getBody().get("fromBill");
							
							int rowcount = fromBill.size();
							
							for (int i = 0; i < rowcount; i++) {
								LinkedTreeMap<String, Object> fromBillMap = fromBillList.get(i);
								if (fromBillMap == null)
									System.out.println("sssss");

								String []rowValue = new String[4];
								rowValue[0] = (String) fromBillMap.get("createtime");
								rowValue[1] = (String) fromBillMap.get("toName");
								rowValue[2] = (String) fromBillMap.get("toCardnum");
								rowValue[3] = (String) fromBillMap.get("value");
								tableModel.addRow(rowValue);
							}
						
					     //TODO 三月内支出账单
						 
					}
				}else if(comboBox_time.getSelectedIndex()==3) {
					if(comboBox_bill_type.getSelectedIndex()==0) {
						Client.Request request = new Request();
						request.setPath("bank/bill");
						request.setToken(token);
						request.getParams().put("period", 15552000);
						Response response = Client.fetch(request);
						String myName=(String)response.getBody().get("myName");
						textField_name.setText(myName);
						String myCardNum=(String) response.getBody().get("myCardnum");
						textField_cardNum.setText(myCardNum);
						HashMap<String, Object> bill = new HashMap<>();
						ArrayList<LinkedTreeMap<String, Object>> billList = (ArrayList<LinkedTreeMap<String, Object>>) response
								.getBody().get("bill");
						
						int rowcount = bill.size();
						
						for (int i = 0; i < rowcount; i++) {
							LinkedTreeMap<String, Object> billMap = billList.get(i);
							if (billMap == null)
								System.out.println("sssss");

							String []rowValue = new String[4];
							rowValue[0] = (String) billMap.get("createtime");
							rowValue[1] = (String) billMap.get("otherName");
							rowValue[2] = (String) billMap.get("otherCardnum");
							rowValue[3] = (String) billMap.get("value");
							tableModel.addRow(rowValue);
						}
						
					     //TODO 半年内总账单
					}else if(comboBox_bill_type.getSelectedIndex()==1) {
						Client.Request request = new Request();
						request.setPath("bank/toBill");
						request.setToken(token);
						request.getParams().put("period", 15552000);
						Response response = Client.fetch(request);
						String myName=(String)response.getBody().get("myName");
						textField_name.setText(myName);
						String myCardNum=(String) response.getBody().get("myCardnum");
						textField_cardNum.setText(myCardNum);
						HashMap<String, Object> toBill = new HashMap<>();
						ArrayList<LinkedTreeMap<String, Object>> toBillList = (ArrayList<LinkedTreeMap<String, Object>>) response
								.getBody().get("toBill");
						
						int rowcount = toBill.size();
						
						for (int i = 0; i < rowcount; i++) {
							LinkedTreeMap<String, Object> toBillMap = toBillList.get(i);
							if (toBillMap == null)
								System.out.println("sssss");

							String []rowValue = new String[4];
							rowValue[0] = (String) toBillMap.get("createtime");
							rowValue[1] = (String) toBillMap.get("fromName");
							rowValue[2] = (String) toBillMap.get("fromCardnum");
							rowValue[3] = (String) toBillMap.get("value");
							tableModel.addRow(rowValue);
						}
					 }else if(comboBox_bill_type.getSelectedIndex()==2) {
						 Client.Request request = new Request();
						 request.setPath("bank/fromBill");
							request.setToken(token);
							request.getParams().put("period", 15552000);
							Response response = Client.fetch(request);
							String myName=(String)response.getBody().get("myName");
							textField_name.setText(myName);
							String myCardNum=(String) response.getBody().get("myCardnum");
							textField_cardNum.setText(myCardNum);
							HashMap<String, Object> fromBill = new HashMap<>();
							ArrayList<LinkedTreeMap<String, Object>> fromBillList = (ArrayList<LinkedTreeMap<String, Object>>) response
									.getBody().get("fromBill");
							
							int rowcount = fromBill.size();
							
							for (int i = 0; i < rowcount; i++) {
								LinkedTreeMap<String, Object> fromBillMap = fromBillList.get(i);
								if (fromBillMap == null)
									System.out.println("sssss");

								String []rowValue = new String[4];
								rowValue[0] = (String) fromBillMap.get("createtime");
								rowValue[1] = (String) fromBillMap.get("toName");
								rowValue[2] = (String) fromBillMap.get("toCardnum");
								rowValue[3] = (String) fromBillMap.get("value");
								tableModel.addRow(rowValue);
							}
						
					     //TODO 半年内支出账单
						 
					}
				}else if(comboBox_time.getSelectedIndex()==4) {
					if(comboBox_bill_type.getSelectedIndex()==0) {
						Client.Request request = new Request();
						request.setPath("bank/bill");
						request.setToken(token);
						request.getParams().put("period", 31536000);
						Response response = Client.fetch(request);
						String myName=(String)response.getBody().get("myName");
						textField_name.setText(myName);
						String myCardNum=(String) response.getBody().get("myCardnum");
						textField_cardNum.setText(myCardNum);
						HashMap<String, Object> bill = new HashMap<>();
						ArrayList<LinkedTreeMap<String, Object>> billList = (ArrayList<LinkedTreeMap<String, Object>>) response
								.getBody().get("bill");
						
						int rowcount = bill.size();
						
						for (int i = 0; i < rowcount; i++) {
							LinkedTreeMap<String, Object> billMap = billList.get(i);
							if (billMap == null)
								System.out.println("sssss");

							String []rowValue = new String[4];
							rowValue[0] = (String) billMap.get("createtime");
							rowValue[1] = (String) billMap.get("otherName");
							rowValue[2] = (String) billMap.get("otherCardnum");
							rowValue[3] = (String) billMap.get("value");
							tableModel.addRow(rowValue);
						}
						
					     //TODO 一年内总账单
					}else if(comboBox_bill_type.getSelectedIndex()==1) {
						Client.Request request = new Request();
						request.setPath("bank/toBill");
						request.setToken(token);
						request.getParams().put("period", 31536000);
						Response response = Client.fetch(request);
						String myName=(String)response.getBody().get("myName");
						textField_name.setText(myName);
						String myCardNum=(String) response.getBody().get("myCardnum");
						textField_cardNum.setText(myCardNum);
						HashMap<String, Object> toBill = new HashMap<>();
						ArrayList<LinkedTreeMap<String, Object>> toBillList = (ArrayList<LinkedTreeMap<String, Object>>) response
								.getBody().get("toBill");
						
						int rowcount = toBill.size();
						
						for (int i = 0; i < rowcount; i++) {
							LinkedTreeMap<String, Object> toBillMap = toBillList.get(i);
							if (toBillMap == null)
								System.out.println("sssss");

							String []rowValue = new String[4];
							rowValue[0] = (String) toBillMap.get("createtime");
							rowValue[1] = (String) toBillMap.get("fromName");
							rowValue[2] = (String) toBillMap.get("fromCardnum");
							rowValue[3] = (String) toBillMap.get("value");
							tableModel.addRow(rowValue);
						}
					     //TODO 一年内收入账单		 
					 }else if(comboBox_bill_type.getSelectedIndex()==2) {
						 Client.Request request = new Request();
						 request.setPath("bank/fromBill");
							request.setToken(token);
							request.getParams().put("period", 31536000);
							Response response = Client.fetch(request);
							String myName=(String)response.getBody().get("myName");
							textField_name.setText(myName);
							String myCardNum=(String) response.getBody().get("myCardnum");
							textField_cardNum.setText(myCardNum);
							HashMap<String, Object> fromBill = new HashMap<>();
							ArrayList<LinkedTreeMap<String, Object>> fromBillList = (ArrayList<LinkedTreeMap<String, Object>>) response
									.getBody().get("fromBill");
							
							int rowcount = fromBill.size();
							
							for (int i = 0; i < rowcount; i++) {
								LinkedTreeMap<String, Object> fromBillMap = fromBillList.get(i);
								if (fromBillMap == null)
									System.out.println("sssss");

								String []rowValue = new String[4];
								rowValue[0] = (String) fromBillMap.get("createtime");
								rowValue[1] = (String) fromBillMap.get("toName");
								rowValue[2] = (String) fromBillMap.get("toCardnum");
								rowValue[3] = (String) fromBillMap.get("value");
								tableModel.addRow(rowValue);
							}
					     //TODO 一年内支出账单
						 
					}
				 }	 */ 
				 
		 }
	
	public void showBill(long duration) {
		Client.Request request = new Request();
		request.setPath("bank/bill");
		request.setToken(token);
		request.getParams().put("period", 31536000);
		Response response = Client.fetch(request);
		String myName=(String)response.getBody().get("myName");
		textField_name.setText(myName);
		String myCardNum=(String) response.getBody().get("myCardNum");
		textField_cardNum.setText(myCardNum);
		HashMap<String, Object> bill = new HashMap<>();
		ArrayList<LinkedTreeMap<String, Object>> billList = (ArrayList<LinkedTreeMap<String, Object>>) response
				.getBody().get("bill");
		
		int rowcount =billList==null?0: billList.size();
		tableModel.setRowCount(0);
		for (int i = 0; i < rowcount; i++) {
			LinkedTreeMap<String, Object> billMap = billList.get(i);
			if (billMap == null)
				break;

			String []rowValue = new String[4];
			rowValue[0] = (String) billMap.get("createTime");
			rowValue[1] = (String) billMap.get("otherName");
			rowValue[2] = (String) billMap.get("otherCardnum");
			rowValue[3] = (String)billMap.get("value");
			tableModel.addRow(rowValue);
		}
		
		
		
		
	}
	
	public void showfrombill(long duration) {
		Client.Request request = new Request();
		 request.setPath("bank/fromBill");
			request.setToken(token);
			request.getParams().put("period", duration);
			Response response = Client.fetch(request);
			String myName=(String)response.getBody().get("myName");
			textField_name.setText(myName);
			String myCardNum=(String) response.getBody().get("myCardnum");
			textField_cardNum.setText(myCardNum);
			HashMap<String, Object> fromBill = new HashMap<>();
			ArrayList<LinkedTreeMap<String, Object>> fromBillList = (ArrayList<LinkedTreeMap<String, Object>>) response
					.getBody().get("fromBill");
			tableModel.setRowCount(0);
			int rowcount = fromBillList==null?0:fromBillList.size();
			System.out.println(rowcount);
			for (int i = 0; i < rowcount; i++) {
				LinkedTreeMap<String, Object> fromBillMap = fromBillList.get(i);
				if (fromBillMap == null)
					break;

				String []rowValue = new String[4];
				rowValue[0] = (String) fromBillMap.get("createTime");
				rowValue[1] = (String) fromBillMap.get("toName");
				rowValue[2] = (String) fromBillMap.get("toCardnum");
				rowValue[3] =String.valueOf((double)fromBillMap.get("value"));
				System.out.println(rowValue[0]+","+rowValue[1]+","+rowValue[2]+","+rowValue[3]);
				tableModel.addRow(rowValue);
			}
			
		
	}
	public void showtobill(long duration) {
		Client.Request request = new Request();
		request.setPath("bank/toBill");
		request.setToken(token);
		request.getParams().put("period", duration);
		Response response = Client.fetch(request);
		String myName=(String)response.getBody().get("myName");
		textField_name.setText(myName);
		String myCardNum=(String) response.getBody().get("myCardnum");
		textField_cardNum.setText(myCardNum);
		HashMap<String, Object> toBill = new HashMap<>();
		ArrayList<LinkedTreeMap<String, Object>> toBillList = (ArrayList<LinkedTreeMap<String, Object>>) response
				.getBody().get("toBill");
		
		int rowcount = toBillList==null?0:toBillList.size();
		tableModel.setRowCount(0);
		for (int i = 0; i < rowcount; i++) {
			LinkedTreeMap<String, Object> toBillMap = toBillList.get(i);
			if (toBillMap == null)
				break;

			String []rowValue = new String[4];
			rowValue[0] = (String) toBillMap.get("createTime");
			rowValue[1] = (String) toBillMap.get("fromName");
			rowValue[2] = (String) toBillMap.get("fromCardnum");
			rowValue[3] =String.valueOf((double)toBillMap.get("value"));;	
			tableModel.addRow(rowValue);
		
	}
		
		
	}
	public long duration(int time) {
		if(time==0) {
			return 3*24*3600;
		}else if(time==1) {
			return 30*24*3600;
		}else if(time==2) {
			return 3*30*24*3600;
		}else if(time==3) {
			return 6*30*24*3600;
		}else if(time==4) {
			return 12*30*3600;
		}
		return 3*24*3600;
	}
	public void typeBill(int type,long duration) {
		if(type==0) {
			showBill(duration);
		}else if(type==1){
			showtobill(duration);
		}else
			showfrombill(duration);
	}
}
