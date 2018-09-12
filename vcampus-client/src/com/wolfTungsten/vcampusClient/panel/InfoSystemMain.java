//系统首页，放告示还是放图，看着办吧，就是一进来默认在这块面板上
package com.wolfTungsten.vcampusClient.panel;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.wolfTungsten.vcampusClient.frame.FunctionFrame;

public class InfoSystemMain extends JPanel {

	/**
	 * Create the panel.
	 */
	public InfoSystemMain() {
		setSize(736,600);
		setLayout(null);//绝对布局
		URL resource = FunctionFrame.class.getResource("mainBG.jpg");
		ImageIcon imageIcon = new ImageIcon(resource);
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(imageIcon);
		lblNewLabel.setBounds(0, 0, 736, 600);
		add(lblNewLabel, new Integer(Integer.MIN_VALUE));
	}

}
