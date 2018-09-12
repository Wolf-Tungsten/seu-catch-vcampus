package com.wolfTungsten.vcampusClient.panel;

import java.awt.Font;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.wolfTungsten.vcampusClient.frame.FunctionFrame;

public class BankRemain extends JPanel {

	JLabel label_cardNum,label_name;
	private JTextField textField_cardNum;
	private JTextField textField_name;
	private JLabel label;
	private JTextField textField;
	public BankRemain() {
		setSize(736,600);
		setLayout(null);//绝对布局
		
		label_cardNum = new JLabel("一卡通号：");
		label_cardNum.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_cardNum.setBounds(352, 13, 77, 30);
		add(label_cardNum);
		
		label_name = new JLabel("姓名:");
		label_name.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_name.setBounds(573, 13, 54, 30);
		add(label_name);
		
		textField_cardNum = new JTextField();
		textField_cardNum.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_cardNum.setBounds(439, 14, 109, 30);
		textField_cardNum.setEditable(false);
		textField_cardNum.setOpaque(false);
		add(textField_cardNum);
		textField_cardNum.setColumns(10);
		
		textField_name = new JTextField();
		textField_name.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_name.setBounds(637, 14, 89, 30);
		textField_name.setEditable(false);
		textField_name.setOpaque(false);
		add(textField_name);
		textField_name.setColumns(10);
		
		label = new JLabel("账户余额（元）");
		label.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label.setBounds(213, 147, 121, 49);
		add(label);
		
		textField = new JTextField();
		textField.setText("1000.00");
		textField.setFont(new Font("微软雅黑", Font.BOLD, 30));
		textField.setBounds(326, 223, 163, 80);
		textField.setEditable(false);
		textField.setOpaque(false);
		add(textField);
		textField.setColumns(10);
		
		URL resource=FunctionFrame.class.getResource("bankRemain.jpg");
		ImageIcon imageIcon=new ImageIcon(resource);
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(imageIcon);
		lblNewLabel.setBounds(213, 203, 100, 100);
		add(lblNewLabel);
		
		JLabel lblTips = new JLabel("Tips:");
		lblTips.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblTips.setBounds(158, 376, 54, 15);
		add(lblTips);
		//tips文本域
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textArea.setText("银行账户在线存、取款并非实时到账，余额显示可能有误差，请耐心地等待1-2个工作日。");
		textArea.setBounds(158, 414, 424, 49);
		textArea.setOpaque(false);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		add(textArea);
	}
}