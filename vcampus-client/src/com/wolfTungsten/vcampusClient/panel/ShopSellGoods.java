package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ShopSellGoods extends JPanel implements ItemListener,FocusListener,ActionListener{
	private JTextField textField_goodName,textField_price;
	JComboBox<String> comboBox_type; 
	JComboBox<String> comboBox_change;
	private JTextField textField_postage;
	JTextArea textArea ;
	JButton addButton,cancelButton;
	String goodTypeStr,changeType;
	private JLabel label_2;
	/**
	 * Create the panel.
	 */
	public ShopSellGoods() {
		setSize(736,600);
		setLayout(null);//绝对布局
		
		JLabel lblNewLabel = new JLabel("物品名称：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel.setBounds(85, 60, 82, 25);
		add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("分类：");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_3.setBounds(85, 120, 70, 15);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("价格：");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_2.setBounds(85, 180, 54, 15);
		add(lblNewLabel_2);
		
		
		JLabel lblNewLabel_1 = new JLabel("交易方式：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_1.setBounds(85, 240, 82, 15);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("邮费：");
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_4.setBounds(85, 300, 54, 15);
		add(lblNewLabel_4);
		
		JLabel label = new JLabel("描述：");
		label.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label.setBounds(85, 360, 54, 15);
		add(label);
		
		textField_goodName = new JTextField();
		textField_goodName.setFont(new Font("微软雅黑", Font.BOLD, 12));
		textField_goodName.setBounds(200, 55, 280, 30);
		add(textField_goodName);
		textField_goodName.setColumns(10);
		
		textField_price = new JTextField();
		textField_price.setFont(new Font("微软雅黑", Font.BOLD, 12));
		textField_price.setBounds(200,174, 280, 30);
		add(textField_price);
		textField_price.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("微软雅黑", Font.BOLD, 13));
		textArea.addFocusListener(this);
		textArea.setBounds(200, 357, 349, 85);
		textArea.setLineWrap(true);//自动换行
		textArea.setText("请输入对该物品的描述、出手理由或者使用体验。");
		add(textArea);
		
		comboBox_type = new JComboBox<String>();
		comboBox_type.setFont(new Font("微软雅黑", Font.BOLD, 14));
		comboBox_type.setBounds(200, 117, 114, 23);
		comboBox_type.addItem("生活百货");
		comboBox_type.addItem("手机数码");
		comboBox_type.addItem("运动户外");
		comboBox_type.addItem("服饰配件");
		comboBox_type.addItem("生鲜水果");
		comboBox_type.addItem("零食美味");
		comboBox_type.addItemListener(this);
		add(comboBox_type);
		
		comboBox_change = new JComboBox<String>();
		comboBox_change.setFont(new Font("微软雅黑", Font.BOLD, 14));
		comboBox_change.setBounds(200, 237, 114, 23);
		comboBox_change.addItem("当面交易");
		comboBox_change.addItem("同城速递");
		comboBox_change.addItem("其他");
		comboBox_change.addItemListener(this);
		add(comboBox_change);
		
		textField_postage = new JTextField("0");
		textField_postage.setFont(new Font("微软雅黑", Font.BOLD, 12));
		textField_postage.setBounds(200, 298, 280, 30);
		textField_postage.setEditable(false);
		add(textField_postage);
		textField_postage.setColumns(10);
		
		JLabel label_1 = new JLabel("元");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_1.setBounds(495, 301, 54, 30);
		add(label_1);
		
		addButton = new JButton("确认添加");
		addButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		addButton.setBounds(200, 496, 93, 23);
		addButton.addActionListener(this);
		add(addButton);
		
		cancelButton = new JButton("清空取消");
		cancelButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		cancelButton.setBounds(448, 497, 93, 23);
		cancelButton.addActionListener(this);
		add(cancelButton);
		
		label_2 = new JLabel("元");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_2.setBounds(490, 180, 54, 24);
		add(label_2);
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		  if (e.getStateChange() == ItemEvent.SELECTED) {
 //             System.out.println("选中: " + comboBox_type.getSelectedIndex() + " = " + comboBox_type.getSelectedItem());
//              System.out.println("选中: " + comboBox_change.getSelectedIndex() + " = " + comboBox_change.getSelectedItem());
			  if(comboBox_change.getSelectedIndex()==0) {
				  textField_postage.setText("0");
				  textField_postage.setEditable(false);
			  }
			  if(comboBox_change.getSelectedIndex()==1||comboBox_change.getSelectedIndex()==2) {
				  textField_postage.setEditable(true);
			  }
          }
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
			textArea.setText("");

	}
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if(textArea.getText().equals("")||textArea.getText()==null) {
			textArea.setText("请输入对该物品的描述、出手理由或者使用体验。");
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==addButton) {
			String goodNameStr=textField_goodName.getText();
			String goodTypeStr=(String)comboBox_type.getSelectedItem();
			String priceStr=textField_price.getText();
			String changeTypeStr=(String)comboBox_change.getSelectedItem();
			String postageStr=textField_postage.getText();
			String descriptionStr=textArea.getText();
			if(goodNameStr.equals("")||goodNameStr==null) {
				JOptionPane.showMessageDialog(null, "请输商品名称！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(priceStr.equals("")||priceStr==null) {
				JOptionPane.showMessageDialog(null, "请输入您想售出的价格！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(postageStr.equals("")||postageStr==null) {
				JOptionPane.showMessageDialog(null, "请输入转手所需经费！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if(descriptionStr.equals("请输入对该物品的描述、出手理由或者使用体验。")) {
				JOptionPane.showMessageDialog(null, "描述一栏不可为空！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				JOptionPane.showMessageDialog(null, "成功上架！", "Tips",JOptionPane.INFORMATION_MESSAGE);
			}
		}
        if(e.getSource()==cancelButton) {
        	textField_goodName.setText("");
        	textField_price.setText("");
        	textField_postage.setText("0");
        	textArea.setText("请输入对该物品的描述、出手理由或者使用体验。");
		}
	}
}
