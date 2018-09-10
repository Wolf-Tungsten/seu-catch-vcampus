//“修改密码”面板
package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class BankModifyPass extends JPanel implements ActionListener{
	private JTextField textField_cardNum;
	private JPasswordField textField_originalPass;
	private JPasswordField textField_newPass;
	private JPasswordField textField_rePass;
	JButton okButton,cancelButton;
	private JTextField textField_ID;

	/**
	 * Create the panel.
	 */
	public BankModifyPass() {
		setSize(736,600);
		setLayout(null);//绝对布局
		//label横坐标x为158
		//textField横坐标为328，宽250
		JLabel label = new JLabel("一卡通号：");
		label.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label.setBounds(158, 100 ,100, 30);
		add(label);
		
		JLabel label_1 = new JLabel("原始密码：");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_1.setBounds(158, 210, 100, 30);
		add(label_1);
		
		JLabel lblNewLabel = new JLabel("新密码：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel.setBounds(158, 280, 100, 30);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("确认新密码：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_1.setBounds(158, 350, 100, 30);
		add(lblNewLabel_1);
		
		textField_cardNum = new JTextField();
		textField_cardNum.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_cardNum.setBounds(328, 106, 250, 30);
		add(textField_cardNum);
		textField_cardNum.setColumns(10);
		
		textField_originalPass = new JPasswordField();
		textField_originalPass.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_originalPass.setBounds(328, 216, 250, 30);
		add(textField_originalPass);
		textField_originalPass.setColumns(10);
		
		textField_newPass = new JPasswordField();
		textField_newPass.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_newPass.setBounds(328, 286, 250, 30);
		add(textField_newPass);
		textField_newPass.setColumns(10);
		
		textField_rePass = new JPasswordField();
		textField_rePass.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_rePass.setBounds(328, 356, 250, 30);
		add(textField_rePass);
		textField_rePass.setColumns(10);
		
		//“确认”按钮
		okButton = new JButton("确认");
		okButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		okButton.setBounds(158, 427, 111, 31);
		okButton.addActionListener(this);
		add(okButton);
		//“取消”按钮
		cancelButton = new JButton("取消");
		cancelButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		cancelButton.setBounds(447, 426, 111, 32);
		cancelButton.addActionListener(this);
		add(cancelButton);
		
		JLabel lblTips = new JLabel("Tips:");
		lblTips.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblTips.setBounds(158, 494, 54, 15);
		add(lblTips);
		//tips文本域
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textArea.setText("账户密码将自动保存在本地浏览器，请勿在公共设备上使用此功能。");
		textArea.setBounds(158, 534, 420, 31);
		textArea.setOpaque(false);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		add(textArea);
		
		JLabel lblNewLabel_2 = new JLabel("身份证号：");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_2.setBounds(158, 165, 70, 15);
		add(lblNewLabel_2);
		
		textField_ID = new JTextField();//身份证号
		textField_ID.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_ID.setBounds(328, 163, 250, 30);
		add(textField_ID);
		textField_ID.setColumns(10);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==okButton) {
			//从文本框中获取的数据
			String cardNumStr=textField_cardNum.getText();
			String IDStr=textField_ID.getText();
			String orPassStr=textField_originalPass.getText();
			String newPassStr=textField_newPass.getText();
			String rePassStr=textField_rePass.getText();
			//TODO 传给客户端的用户信息
			String myCardNum=null;//“我”的一卡通号
			String myID=null;//“我”的身份证号
			String myPass=null;//“我”的原始密码
			if(cardNumStr.equals("")||cardNumStr==null) {
				JOptionPane.showMessageDialog(null, "请输入一卡通号！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(IDStr.equals("")||IDStr==null) {
				JOptionPane.showMessageDialog(null, "请输入身份证号！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(orPassStr.equals("")||orPassStr==null) {
				JOptionPane.showMessageDialog(null, "请输入原始密码！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(newPassStr.equals("")||newPassStr==null) {
				JOptionPane.showMessageDialog(null, "请输入新密码！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if(rePassStr.equals("")||rePassStr==null) {
				JOptionPane.showMessageDialog(null, "请确认新密码！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}  else if(!newPassStr.equals(rePassStr)) {
		    	 JOptionPane.showMessageDialog(null, "两次输入密码不一致~", "Tips",JOptionPane.ERROR_MESSAGE); 
		    	 return;
			}else if(!cardNumStr.equals(myCardNum )) {
				JOptionPane.showMessageDialog(null, "一卡通号不正确", "Tips",JOptionPane.ERROR_MESSAGE); 
		    	 return;
			}else if(!IDStr.equals(myID )) {
				JOptionPane.showMessageDialog(null, "身份证号不正确", "Tips",JOptionPane.ERROR_MESSAGE); 
		    	 return;
			}else if(!orPassStr.equals(myPass )) {
				JOptionPane.showMessageDialog(null, "原始密码输入不正确", "Tips",JOptionPane.ERROR_MESSAGE); 
		    	 return;
			}
		}
		if(e.getSource()==cancelButton) {
			textField_cardNum.setText("");
			textField_originalPass.setText("");
			textField_newPass.setText("");
			textField_rePass.setText("");
		}
	}
}
