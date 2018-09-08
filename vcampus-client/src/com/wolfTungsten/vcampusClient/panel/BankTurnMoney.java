//“转账”面板
package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class BankTurnMoney extends JPanel implements ActionListener{
	private JTextField textField_to_account;
	private JTextField textField_amount;
	private JPasswordField textField_pass;
	JButton okButton,cancelButton;
	/**
	 * Create the panel.
	 */
	public BankTurnMoney() {
		setSize(736,600);
		setLayout(null);//绝对布局
		//label横坐标x为158
		//textField横坐标为328，宽250
		JLabel label = new JLabel("转入账号：");
		label.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label.setBounds(158, 96, 82, 30);
		add(label);
		
		JLabel lblNewLabel = new JLabel("转入金额：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel.setBounds(158, 163, 82, 30);
		add(lblNewLabel);
		
		JLabel label_1 = new JLabel("账号密码：");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_1.setBounds(158, 228, 82, 30);
		add(label_1);
		
		textField_to_account = new JTextField();
		textField_to_account.setFont(new Font("微软雅黑", Font.BOLD, 14));
		textField_to_account.setBounds(328, 97, 250, 30);
		add(textField_to_account);
		textField_to_account.setColumns(10);
		
		textField_amount = new JTextField();
		textField_amount.setFont(new Font("微软雅黑", Font.BOLD, 14));
		textField_amount.setBounds(328, 164, 250, 30);
		add(textField_amount);
		textField_amount.setColumns(10);
		
		textField_pass = new JPasswordField();
		textField_pass.setFont(new Font("微软雅黑", Font.BOLD, 14));
		textField_pass.setBounds(328, 229, 250, 30);
		add(textField_pass);
		textField_pass.setColumns(10);
		
		okButton = new JButton("确认");
		okButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		okButton.setBounds(158, 300, 111, 30);
		okButton.addActionListener(this);
		add(okButton);
		
		cancelButton = new JButton("取消");
		cancelButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		cancelButton.setBounds(467, 300, 111, 30);
		cancelButton.addActionListener(this);
		add(cancelButton);
		
		JLabel lblNewLabel_1 = new JLabel("Tips:");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_1.setBounds(158, 361, 65, 19);
		add(lblNewLabel_1);
		
		JTextArea txtrrn = new JTextArea();
		txtrrn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtrrn.setText("银行账户在线转账并非实时到账，请耐心地等待1-2个工作日。账户\r\n密码将自动保存在本地浏览器，请勿在公共设备上使用此功能。");
		txtrrn.setBounds(158, 403, 427, 67);
		txtrrn.setOpaque(false);
		txtrrn.setEditable(false);
		add(txtrrn);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==okButton) {
			// TODO Auto-generated method stub
			String toAccountStr=textField_to_account.getText();
			String amountStr=textField_amount.getText();
			String passStr=textField_pass.getText();
			if(toAccountStr.equals("")||toAccountStr==null) {
				JOptionPane.showMessageDialog(null, "转入账户不可为空！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(amountStr.equals("")||amountStr==null) {
				JOptionPane.showMessageDialog(null, "转入金额不可为空！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(passStr.equals("")||passStr==null) {
				JOptionPane.showMessageDialog(null, "请输入六位支付密码！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if(e.getSource()==cancelButton) {
			textField_to_account.setText("");
			textField_amount.setText("");
			textField_pass.setText("");
		}
	}
}
