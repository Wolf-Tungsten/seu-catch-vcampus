package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankNewPassword extends JPanel implements ActionListener{
	private JTextField textField_cardNum;
	private JPasswordField textField_newPass;
	private JPasswordField textField_rePass;
	JButton okButton,cancelButton;
	private JTextField textField_ID;
	private String token;
	/**
	 * Create the panel.
	 */
	public BankNewPassword(String Token) {
		token=Token;
		setSize(736,600);
		setLayout(null);//绝对布局
		
		JLabel lblNewLabel = new JLabel("新建支付密码");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 10, 94, 26);
		add(lblNewLabel);
		
		JLabel label = new JLabel("一卡通号：");
		label.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label.setBounds(158, 100 ,100, 30);
		add(label);
		
		JLabel lblNewLabel1 = new JLabel("支付密码：");
		lblNewLabel1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel1.setBounds(158, 230, 100, 30);
		add(lblNewLabel1);
		
		JLabel lblNewLabel_1 = new JLabel("确认新密码：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_1.setBounds(158, 300, 100, 30);
		add(lblNewLabel_1);
		
		textField_cardNum = new JTextField();
		textField_cardNum.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_cardNum.setBounds(328, 106, 250, 30);
		add(textField_cardNum);
		textField_cardNum.setColumns(10);
		
		textField_newPass = new JPasswordField();
		textField_newPass.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_newPass.setBounds(328, 230, 250, 30);
		add(textField_newPass);
		textField_newPass.setColumns(10);
		
		textField_rePass = new JPasswordField();
		textField_rePass.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_rePass.setBounds(328, 300, 250, 30);
		add(textField_rePass);
		textField_rePass.setColumns(10);
		
		//“确认”按钮
		okButton = new JButton("确认");
		okButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		okButton.setBounds(158, 387, 111, 31);
		okButton.addActionListener(this);
		add(okButton);
		//“取消”按钮
		cancelButton = new JButton("取消");
		cancelButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		cancelButton.setBounds(447, 386, 111, 32);
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
			String newPassStr=textField_newPass.getText();
			String rePassStr=textField_rePass.getText();
			if(cardNumStr.equals("")||cardNumStr==null) {
				JOptionPane.showMessageDialog(null, "请输入一卡通号！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(IDStr.equals("")||IDStr==null) {
				JOptionPane.showMessageDialog(null, "请输入身份证号！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
		
			}else if(newPassStr.equals("")||newPassStr==null) {
				JOptionPane.showMessageDialog(null, "请输入支付密码！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(rePassStr.equals("")||rePassStr==null) {
				JOptionPane.showMessageDialog(null, "请确认新密码！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(!newPassStr.equals(rePassStr)) {
		    	 JOptionPane.showMessageDialog(null, "两次输入密码不一致~", "Tips",JOptionPane.ERROR_MESSAGE); 
		    	 return;
			}
			Client.Request request = new Request();
			request.setPath("bank/register");
			request.setToken(token);
			request.getParams().put("cardnum", cardNumStr);
			request.getParams().put("idcardNum", IDStr);
			request.getParams().put("secretPassword", Client.getMD5("newPassStr"));
			Response response = Client.fetch(request);
			if(!response.getSuccess()) {
				JOptionPane.showMessageDialog(null, "注册失败", "Tips",JOptionPane.ERROR_MESSAGE); 
	    	 return;
	    	 }
		}else if(e.getSource()==cancelButton) {
			textField_cardNum.setText("");
			textField_newPass.setText("");
			textField_rePass.setText("");
		}
		
	}

}
