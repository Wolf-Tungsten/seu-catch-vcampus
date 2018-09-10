//选购商品
package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ShopSelect extends JPanel implements ActionListener{
	private JTextField textField_select;
	JButton button_select_goods ;
	JButton button_1,button_2,button_3,button_4,button_5,button_6;
	JPanel panel;
	private JLabel lblPhoto;
	private JLabel lblNewLabel;
	private JLabel lblPrice;
	private JLabel label;
	private JTextField textField;
	
	public ShopSelect() {
		setSize(736,600);
		setLayout(null);//绝对布局
		
		textField_select = new JTextField();
		textField_select.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_select.setBounds(58, 39, 543, 21);
		textField_select.addActionListener(this);
		add(textField_select);
		textField_select.setColumns(10);
		
		button_select_goods = new JButton("搜索");
		button_select_goods.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_select_goods.setBounds(633, 38, 93, 23);
		button_select_goods.addActionListener(this);
		add(button_select_goods);
		
		panel=new JPanel();
		panel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(panel,//在滚动面板上加入panel放置商品
		                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		panel.setPreferredSize(new Dimension(716,130+120*10));//panel的高度必须高于滚动面板
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 0, 677, 120);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblPhoto = new JLabel("photo");
		lblPhoto.setBounds(10, 10, 100, 100);
		panel_1.add(lblPhoto);
		
		lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(136, 10, 54, 15);
		panel_1.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(136, 49, 240, 61);
		panel_1.add(textArea);
		
		lblPrice = new JLabel("单价");
		lblPrice.setBounds(254, 10, 54, 15);
		panel_1.add(lblPrice);
		
		label = new JLabel("购买数量：");
		label.setBounds(417, 10, 73, 15);
		panel_1.add(label);
		
		textField = new JTextField();
		textField.setBounds(522, 7, 125, 21);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("马上购买");
		button.setBounds(417, 67, 93, 23);
		panel_1.add(button);
		
		JButton button_7 = new JButton("加入购物车");
		button_7.setBounds(552, 67, 93, 23);
		panel_1.add(button_7);
		
		
		scrollPane.setBounds(10, 120, 716, 470);
		add(scrollPane);
		
		button_1 = new JButton("生活百货");
		button_1.addActionListener(this);
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_1.setBounds(58, 70, 80, 23);
		add(button_1);
		
		button_2 = new JButton("生鲜水果");
		button_2.addActionListener(this);
		button_2.setBounds(148, 70, 80, 23);
		add(button_2);
		
		button_3 = new JButton("零食美味");
		button_3.addActionListener(this);
		button_3.setBounds(238, 70, 80, 23);
		add(button_3);
		
		button_4 = new JButton("手机数码");
		button_4.addActionListener(this);
		button_4.setBounds(328, 70, 80, 23);
		add(button_4);
		
		button_5 = new JButton("运动户外");
		button_5.addActionListener(this);
		button_5.setBounds(418, 70, 93, 23);
		add(button_5);
		
		button_6 = new JButton("服饰配件");
		button_6.addActionListener(this);
		button_6.setBounds(521, 70, 80, 23);
		add(button_6);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button_select_goods) {
			//搜索
		}
		if(e.getSource()==button_1) {
			textField_select.setText("生活百货");
		}else if(e.getSource()==button_2) {
			textField_select.setText("生鲜水果");
		}else if(e.getSource()==button_3) {
			textField_select.setText("零食美味");
		}else if(e.getSource()==button_4) {
			textField_select.setText("手机数码");
		}else if(e.getSource()==button_5) {
			textField_select.setText("运动户外");
		}else if(e.getSource()==button_6) {
			textField_select.setText("服饰配件");
		}
				
	}
}
