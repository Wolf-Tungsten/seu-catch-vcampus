package com.wolfTungsten.vcampusClient.panel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;

public class InfoPassword extends JPanel implements ActionListener{
	private JTextField textField_cardNum;
	private JPasswordField textField_originalPass;
	private JPasswordField textField_newPass;
	private JPasswordField textField_rePass;
	JButton okButton,cancelButton;
	String token;
	/**
	 * Create the panel.
	 */
	public InfoPassword(String Token) {
		token = Token;
		setSize(736,600);
		setLayout(null);//绝对布局
		JLabel label = new JLabel("一卡通号：");
		label.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label.setBounds(158, 100 ,100, 30);
		add(label);
		
		JLabel label_1 = new JLabel("原始密码：");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_1.setBounds(158, 170, 100, 30);
		add(label_1);
		
		JLabel lblNewLabel = new JLabel("新密码：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel.setBounds(158, 240, 100, 30);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("确认新密码：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_1.setBounds(158, 310, 100, 30);
		add(lblNewLabel_1);
		
		textField_cardNum = new JTextField();
		textField_cardNum.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_cardNum.setBounds(328, 106, 250, 30);
		add(textField_cardNum);
		textField_cardNum.setColumns(10);
		
		textField_originalPass = new JPasswordField();
		textField_originalPass.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_originalPass.setBounds(328, 176, 250, 30);
		add(textField_originalPass);
		textField_originalPass.setColumns(10);
		
		textField_newPass = new JPasswordField();
		textField_newPass.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_newPass.setBounds(328, 246, 250, 30);
		add(textField_newPass);
		textField_newPass.setColumns(10);
		
		textField_rePass = new JPasswordField();
		textField_rePass.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_rePass.setBounds(328, 316, 250, 30);
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
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==okButton) {
			String cardNumStr=textField_cardNum.getText();
			String orPassStr=textField_originalPass.getText();
			String newPassStr=textField_newPass.getText();
			String rePassStr=textField_rePass.getText();
			//TODO 传给客户端的用户信息
			String myCardNum=null;//“我”的一卡通号
			String myPass=null;//“我”的原始密码
			if(cardNumStr.equals("")||cardNumStr==null) {
				JOptionPane.showMessageDialog(null, "请输入一卡通号！", "Tips",JOptionPane.ERROR_MESSAGE);
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
			}else if(!orPassStr.equals(myPass )) {
				JOptionPane.showMessageDialog(null, "原始密码输入不正确", "Tips",JOptionPane.ERROR_MESSAGE); 
		    	 return;
			}
			Client.Request request = new Request();
			request.setPath("user/modifyPwd");
			request.getParams().put("oldpwd", Client.getMD5(orPassStr));
			request.getParams().put("newpwd", Client.getMD5(newPassStr));
			request.getParams().put("cardnum", cardNumStr);
			request.setToken(token);
			Response response = Client.fetch(request);
			if(response.getSuccess())
				JOptionPane.showMessageDialog(null, "修改成功！", "Tips",JOptionPane.INFORMATION_MESSAGE);
			else {
				String result = (String) response.getBody().get("result");
				JOptionPane.showMessageDialog(null,result , "错误",JOptionPane.ERROR_MESSAGE);	
			}	
			textField_cardNum.setText("");
			textField_originalPass.setText("");
			textField_newPass.setText("");
			textField_rePass.setText("");
		}
		if(e.getSource()==cancelButton) {
			textField_cardNum.setText("");
			textField_originalPass.setText("");
			textField_newPass.setText("");
			textField_rePass.setText("");
		}
	}
}
