package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class JwcManager extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Create the panel.
	 */
	public JwcManager() {
		setSize(736,600);
		setLayout(null);
		
		// ÉÏ·½panel_guide ÏÂ·½ panel_card
		//panel_guide
		JPanel panel_guide = new JPanel();
		panel_guide.setLayout(null);
		panel_guide.setBounds(0, 10, 736, 54);
		add(panel_guide);
		
		JButton button1 = new JButton("¿Î³ÌÌí¼Ó");
		button1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 16));
		button1.setBounds(75, 12, 100, 26);
		panel_guide.add(button1);
		
		JButton button2 = new JButton("¿Î³Ì");
		button2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 16));
		button2.setBounds(237, 12, 100, 26);
		panel_guide.add(button2);
		
		JButton button3 = new JButton("Í¼ÊéÈë¿â");
		button3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 16));
		button3.setBounds(399, 12, 100, 26);
		panel_guide.add(button3);
		
		JButton button4 = new JButton("Í¼ÊéÈë¿â");
		button4.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 16));
		button4.setBounds(561, 12, 100, 26);
		panel_guide.add(button4);
		
		JPanel cardPanel = new JPanel();
		cardPanel.setLayout(null);
		cardPanel.setBounds(0, 62, 736, 538);
		add(cardPanel);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 736, 538);
		cardPanel.add(panel);
		
		textField = new JTextField("");
		textField.setColumns(10);
		textField.setBounds(241, 60, 291, 21);
		panel.add(textField);
		
		JLabel label = new JLabel("Í¼Êé±àºÅ£º");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
		label.setBounds(85, 62, 77, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("ÊéÃû£º");
		label_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
		label_1.setBounds(85, 103, 54, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("×÷Õß£º");
		label_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
		label_2.setBounds(85, 144, 54, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("³ö°æÉç£º");
		label_3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
		label_3.setBounds(85, 184, 74, 15);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Èë¿âÊ±¼ä£º");
		label_4.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
		label_4.setBounds(82, 277, 74, 15);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("¹Ý²ØµØµã£º");
		label_5.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
		label_5.setBounds(82, 234, 77, 15);
		panel.add(label_5);
		
		JButton button = new JButton("Ìí¼Ó");
		button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
		button.setBounds(188, 440, 93, 23);
		panel.add(button);
		
		JButton button_2 = new JButton("È¡Ïû");
		button_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
		button_2.setBounds(370, 440, 93, 23);
		panel.add(button_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(241, 101, 291, 21);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(241, 142, 291, 21);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(241, 182, 291, 21);
		panel.add(textField_3);
		
		JRadioButton radioButton = new JRadioButton("ËÄÅÆÂ¥");
		radioButton.setSelected(true);
		radioButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
		radioButton.setFocusPainted(false);
		radioButton.setBounds(241, 230, 82, 23);
		panel.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("¶¡¼ÒÇÅ");
		radioButton_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
		radioButton_1.setFocusPainted(false);
		radioButton_1.setBounds(355, 230, 77, 23);
		panel.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("¾ÅÁúºþ");
		radioButton_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
		radioButton_2.setFocusPainted(false);
		radioButton_2.setBounds(450, 230, 82, 23);
		panel.add(radioButton_2);
		
		textField_4 = new JTextField("µ¥»÷ÒÔÑ¡ÔñÈÕÆÚ");
		textField_4.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		textField_4.setBounds(241, 275, 291, 21);
		panel.add(textField_4);
		
		//

	}
}
