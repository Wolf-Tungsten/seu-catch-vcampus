//“存取款业务”面板
package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class BankSaveAndWithdraw extends JPanel implements ActionListener,FocusListener,ItemListener{
	private JTextField textField_amount;
	private JPasswordField textField_account_pass;
	private String token;
	JRadioButton radioButton_save,radioButton_withdraw;
	JButton okButton,cancelButton;
	boolean isSave=true;
	/**
	 * Create the panel.
	 */
	public BankSaveAndWithdraw(String Token) {
		token=Token;
		setSize(736,600);
		setLayout(null);//绝对布局
		//label横坐标x为158
		//textField横坐标为328，宽250
		JLabel label = new JLabel("业务选择：");
		label.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label.setBounds(158, 105, 84, 30);
		add(label);
		//“存款”按钮
		radioButton_save = new JRadioButton("存款");
		radioButton_save.setFont(new Font("微软雅黑", Font.BOLD, 14));
		radioButton_save.setBounds(316, 107, 84, 23);
		radioButton_save.setContentAreaFilled(false);
		radioButton_save.setFocusPainted(false);
		radioButton_save.addItemListener(this);
		add(radioButton_save);
	    //“取款”按钮
		radioButton_withdraw = new JRadioButton("取款");
		radioButton_withdraw.setFont(new Font("微软雅黑", Font.BOLD, 14));
		radioButton_withdraw.setBounds(476, 107, 84, 23);
		radioButton_withdraw.setContentAreaFilled(false);
		radioButton_withdraw.setFocusPainted(false);
		radioButton_withdraw.addItemListener(this);
		add(radioButton_withdraw);
		//把两个单选按钮加到同一个组
		ButtonGroup typeGroup=new ButtonGroup();
		typeGroup.add(radioButton_save);
		typeGroup.add(radioButton_withdraw);
		radioButton_save.setSelected(true);	
		
		//输入金额
		textField_amount = new JTextField();
		textField_amount.setBounds(328, 163, 250, 30);
		add(textField_amount);
		textField_amount.setColumns(10);
		//输入账号密码
		textField_account_pass = new JPasswordField();
		textField_account_pass.setBounds(328, 228, 250, 30);
		textField_account_pass.addFocusListener(this);
		add(textField_account_pass);
		textField_account_pass.setColumns(10);
		
		//“确认”按钮
		okButton = new JButton("确认");
		okButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		okButton.setBounds(158, 302, 111, 31);
		okButton.addActionListener(this);
		add(okButton);
		//“取消”按钮
		cancelButton = new JButton("取消");
		cancelButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		cancelButton.setBounds(467, 301, 111, 32);
		cancelButton.addActionListener(this);
		add(cancelButton);
		//标签们，忽略吧
		JLabel label_1 = new JLabel("金额：");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_1.setBounds(158, 163, 73, 30);
		add(label_1);
		JLabel label_2 = new JLabel("账号密码：");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_2.setBounds(158, 228, 84, 30);
		add(label_2);
		JLabel lblTips = new JLabel("Tips:");
		lblTips.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblTips.setBounds(158, 376, 54, 15);
		add(lblTips);
		//tips文本域
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textArea.setText("银行账户在线充值并非实时到账，请耐心地等待1-2个工作日。账户密码将自动保存在本地浏览器，请勿在公共设备上使用此功能。");
		textArea.setBounds(158, 414, 424, 49);
		textArea.setOpaque(false);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		add(textArea);
	}
	//按钮监听事件
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==okButton) {
			String amountStr=textField_amount.getText();
			String passStr=textField_account_pass.getText();
			if(amountStr.equals("")||amountStr==null) {
				JOptionPane.showMessageDialog(null, "输入金额不可为空！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(passStr.equals("")||passStr==null) {
				JOptionPane.showMessageDialog(null, "请输入六位支付密码！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(isSave==true) {
				Client.Request request = new Request();
				request.setPath("bank/deposit");
				request.setToken(token);
				request.getParams().put("secretPassword", Client.getMD5(passStr));
				request.getParams().put("value", amountStr);
				Response response = Client.fetch(request);
				if(response.getSuccess()) {
					JOptionPane.showMessageDialog(null, "存款成功！", "Tips",JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "支付密码错误！", "Tips",JOptionPane.ERROR_MESSAGE);
				}
			}else {
				Client.Request request = new Request();
				request.setPath("bank/withdraw");
				request.setToken(token);
				request.getParams().put("secretPassword", Client.getMD5(passStr));
				request.getParams().put("value", amountStr);
				Response response = Client.fetch(request);
				if(response.getSuccess()) {
					JOptionPane.showMessageDialog(null, "取款成功！", "Tips",JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "支付密码错误或余额不足！", "Tips",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			// TODO确认密码，存钱或取钱
			//JOptionPane.showMessageDialog(null, "账户密码错误！", "提示",JOptionPane.ERROR_MESSAGE);
			//JOptionPane.showMessageDialog(null, "存款成功！", "提示",JOptionPane.INFORMATION_MESSAGE);
			//JOptionPane.showMessageDialog(null, "取款成功！", "提示",JOptionPane.INFORMATION_MESSAGE);
		}else if(e.getSource()==cancelButton) {
			// TODO清空数据
			textField_amount.setText("");
			textField_account_pass.setText("");
		}
	}
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(radioButton_save.isSelected()) {
			isSave=true;
		}else if(radioButton_withdraw.isSelected()) {
			isSave=false;
		}
		
	}
}
