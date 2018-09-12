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

import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;

import com.wolfTungsten.vcampusClient.client.Client;

public class BankModifyPass extends JPanel implements ActionListener{
	private JPasswordField textField_originalPass;
	private JPasswordField textField_newPass;
	private JPasswordField textField_rePass;
	JButton okButton,cancelButton;


	private String token;

	/**
	 * Create the panel.
	 */
	public BankModifyPass(String Token) {
		token=Token;
		setSize(736,600);
		setLayout(null);
		
		JLabel label_1 = new JLabel("原始密码：");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_1.setBounds(158, 100, 100, 30);
		add(label_1);
		
		JLabel lblNewLabel = new JLabel("新密码：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel.setBounds(158, 170, 100, 30);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("确认新密码：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_1.setBounds(158, 240, 100, 30);
		add(lblNewLabel_1);
		
		textField_originalPass = new JPasswordField();
		textField_originalPass.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_originalPass.setBounds(328, 106, 250, 30);
		add(textField_originalPass);
		textField_originalPass.setColumns(10);
		
		textField_newPass = new JPasswordField();
		textField_newPass.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_newPass.setBounds(328, 176, 250, 30);
		add(textField_newPass);
		textField_newPass.setColumns(10);
		
		textField_rePass = new JPasswordField();
		textField_rePass.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_rePass.setBounds(328, 246, 250, 30);
		add(textField_rePass);
		textField_rePass.setColumns(10);
		
		//“确认”按钮
		okButton = new JButton("确认");
		okButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		okButton.setBounds(158, 317, 111, 31);
		okButton.addActionListener(this);
		add(okButton);
		//“取消”按钮
		cancelButton = new JButton("取消");
		cancelButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		cancelButton.setBounds(447, 316, 111, 32);
		cancelButton.addActionListener(this);
		add(cancelButton);
		
		JLabel lblTips = new JLabel("Tips:");
		lblTips.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblTips.setBounds(158, 384, 54, 15);
		add(lblTips);
		//tips文本域
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textArea.setText("账户密码将自动保存在本地浏览器，请勿在公共设备上使用此功能。");
		textArea.setBounds(158, 424, 420, 31);
		textArea.setOpaque(false);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		add(textArea);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==okButton) {
			//从文本框中获取的数据
			String orPassStr=textField_originalPass.getText();
			String newPassStr=textField_newPass.getText();
			String rePassStr=textField_rePass.getText();

			if(orPassStr.equals("")||orPassStr==null) {
				JOptionPane.showMessageDialog(null, "请输入原始密码！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(newPassStr.equals("")||newPassStr==null) {
				JOptionPane.showMessageDialog(null, "请输入新密码！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(rePassStr.equals("")||rePassStr==null) {
				JOptionPane.showMessageDialog(null, "请确认新密码！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(!newPassStr.equals(rePassStr)) {
		    	 JOptionPane.showMessageDialog(null, "两次输入密码不一致~", "Tips",JOptionPane.ERROR_MESSAGE); 
		    	return;
			}
			
			Client.Request request = new Request();
			request.setPath("bank/secretPassword");
			request.setToken(token);
			request.getParams().put("secretPassword", orPassStr);
			request.getParams().put("newSecretPassword", newPassStr);
			Response response = Client.fetch(request);
			if(response.getSuccess()) {
				 JOptionPane.showMessageDialog(null, "修改密码成功", "Tips",JOptionPane.ERROR_MESSAGE); 
				 return;
			}else {
				 JOptionPane.showMessageDialog(null, "修改密码失败，请检查原始密码是否正确", "Tips",JOptionPane.ERROR_MESSAGE); 
				 return;
			}
		}
		
		if(e.getSource()==cancelButton) {
			textField_originalPass.setText("");
			textField_newPass.setText("");
			textField_rePass.setText("");
		}
		}
	}

