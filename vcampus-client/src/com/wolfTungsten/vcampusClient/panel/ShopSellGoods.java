package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;

import javax.swing.JTextField;

import javax.swing.filechooser.FileNameExtensionFilter;


import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;


import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ShopSellGoods extends JPanel implements ItemListener,FocusListener,ActionListener{
	private JTextField textField_goodName,textField_price,textField_number;
	JComboBox<String> comboBox_type; 
	JComboBox<String> comboBox_change;
	JTextField textField_photo;
	JTextArea textArea ;
	JButton addButton,cancelButton;
	String goodTypeStr,changeType;

	
	
	
	private String token;
	/**
	 * Create the panel.
	 */
	public ShopSellGoods(String Token) {
		token=Token;

		setSize(736,600);
		setLayout(null);//绝对布局
		
		JLabel lblNewLabel = new JLabel("物品名称");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel.setBounds(119, 76, 82, 25);
		add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("分类");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_3.setBounds(119, 136, 70, 15);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("价格");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_2.setBounds(119, 196, 54, 15);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_5 = new JLabel("上架数量");
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_5.setBounds(119, 256, 82, 15);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_1 = new JLabel("图片链接");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_1.setBounds(119, 316, 82, 15);
		add(lblNewLabel_1);
		
		
		JLabel label = new JLabel("描述");
		label.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label.setBounds(119, 390, 54, 15);
		add(label);
		
		textField_goodName = new JTextField();
		textField_goodName.setFont(new Font("微软雅黑", Font.BOLD, 12));
		textField_goodName.setBounds(234, 71, 306, 30);
		add(textField_goodName);
		textField_goodName.setColumns(10);
		
		textField_price = new JTextField();
		textField_price.setFont(new Font("微软雅黑", Font.BOLD, 12));
		textField_price.setBounds(234,190, 306, 30);
		add(textField_price);
		textField_price.setColumns(10);
		
		textField_number = new JTextField();
		textField_number.setFont(new Font("微软雅黑", Font.BOLD, 12));
		textField_number.setBounds(234,250, 306, 30);
		add(textField_number);
		textField_number.setColumns(10);
		
		textField_photo = new JTextField();//YHD图片往这里传！
		textField_photo.setFont(new Font("微软雅黑", Font.BOLD, 12));
		textField_photo.setBounds(234,310, 306, 30);
		add(textField_photo);
		textField_photo.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("微软雅黑", Font.BOLD, 13));
		textArea.addFocusListener(this);
		textArea.setBounds(234, 387, 350, 85);
		textArea.setLineWrap(true);//自动换行
		textArea.setText("请输入对该物品的描述、出手理由或者使用体验。");
		add(textArea);
		
		comboBox_type = new JComboBox<String>();
		comboBox_type.setFont(new Font("微软雅黑", Font.BOLD, 14));
		comboBox_type.setBounds(234, 133, 114, 30);
		comboBox_type.addItem("生活百货");
		comboBox_type.addItem("手机数码");
		
		comboBox_type.addItem("服饰配件");
		
		comboBox_type.addItem("零食美味");
		comboBox_type.addItemListener(this);
		add(comboBox_type);
		
		
		
		
		addButton = new JButton("确认添加");
		addButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		addButton.setBounds(200, 525, 93, 30);
		addButton.addActionListener(this);
		add(addButton);
		
		cancelButton = new JButton("清空取消");
		cancelButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		cancelButton.setBounds(448, 526, 93, 30);
		cancelButton.addActionListener(this);
		add(cancelButton);
		
		
		JLabel lblNewLabel_7 = new JLabel("元");
		lblNewLabel_7.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_7.setBounds(550, 188, 30, 30);
		add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("件");
		lblNewLabel_8.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_8.setBounds(550, 250, 30, 30);
		add(lblNewLabel_8);
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
	
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
		if(e.getSource()==addButton) {
			String goodNameStr=textField_goodName.getText();
			String goodTypeStr=(String)comboBox_type.getSelectedItem();
			String priceStr=textField_price.getText();
			String numberStr=textField_number.getText();
			String descriptionStr=textArea.getText();
			String photo = textField_photo.getText();
			if(goodNameStr.equals("")||goodNameStr==null) {
				JOptionPane.showMessageDialog(null, "请输商品名称！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(priceStr.equals("")||priceStr==null) {
				JOptionPane.showMessageDialog(null, "请输入您想售出的价格！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(numberStr.equals("")||numberStr==null) {
				JOptionPane.showMessageDialog(null, "请输入上架件数！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(photo.equals("")||photo==null) {
				JOptionPane.showMessageDialog(null, "请输入图片链接数！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if(descriptionStr.equals("请输入对该物品的描述、出手理由或者使用体验。")) {
				JOptionPane.showMessageDialog(null, "描述一栏不可为空！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}
			//缺添加图片
			Client.Request request = new Request();
			request.setPath("shop/addGoods");
			request.setToken(token);
			request.getParams().put("name", goodNameStr);
			request.getParams().put("price", Double.valueOf(priceStr));
			request.getParams().put("amount", Integer.valueOf(numberStr));
			request.getParams().put("description", descriptionStr);
			request.getParams().put("image", photo);			
			request.getParams().put("type", goodTypeStr);
			Response response = Client.fetch(request);
			
			if(response.getSuccess()) {
				JOptionPane.showMessageDialog(null, "添加成功!", "成功",JOptionPane.INFORMATION_MESSAGE); 
				textField_goodName.setText("");
	        	textField_price.setText("");
	        	textField_photo.setText("");
	        	textField_number.setText("");
	        	textArea.setText("请输入对该物品的描述、出手理由或者使用体验。");
			}
			else
				JOptionPane.showMessageDialog(null, "添加失败！", "失败",JOptionPane.ERROR_MESSAGE); 
			
			
			
			
		}
        if(e.getSource()==cancelButton) {
        	textField_goodName.setText("");
        	textField_price.setText("");
        	textField_number.setText("");
        	textField_photo.setText("");
        	textArea.setText("请输入对该物品的描述、出手理由或者使用体验。");
		}  	     
	}
}
