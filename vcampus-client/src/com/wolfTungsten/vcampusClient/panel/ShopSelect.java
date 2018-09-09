//选购商品
package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;

public class ShopSelect extends JPanel implements ActionListener{
	private JTextField textField_select;

	/**
	 * Create the panel.
	 */
	public ShopSelect() {
		setSize(736,600);
		setLayout(null);//绝对布局
		
		textField_select = new JTextField();
		textField_select.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_select.setBounds(58, 39, 487, 21);
		textField_select.addActionListener(this);
		add(textField_select);
		textField_select.setColumns(10);
		
		JButton button_select = new JButton("搜索");
		button_select.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_select.setBounds(588, 38, 93, 23);
		button_select.addActionListener(this);
		add(button_select);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 100, 623, 436);
		add(scrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
