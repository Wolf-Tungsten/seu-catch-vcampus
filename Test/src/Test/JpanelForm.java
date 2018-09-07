//实现界面滑动效果例子
package Test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JpanelForm {

	/**
	 * @param args
	 */

	JFrame frame = new JFrame("动态panel");

	final JPanel p1 = new JPanel();// 滑动面板1

	final JPanel p2 = new JPanel();// 滑动面板2

	final JPanel p3 = new JPanel();// 滑动面板3

	JPanel contentPanel = new JPanel();// 主面板

	JPanel topPanel = new JPanel();// 顶面板

	final JPanel centerPanel = new JPanel();// 中间面板

	@SuppressWarnings("deprecation")
	public JpanelForm() {

		frame.setSize(600, 450);
		contentPanel.setBackground(Color.GRAY);
		frame.setContentPane(contentPanel);
		contentPanel.setLayout(new BorderLayout());

		topPanel.setBackground(Color.yellow);
		topPanel.setPreferredSize(new Dimension(contentPanel.getWidth(), 50));

		centerPanel.setBackground(Color.WHITE);
		centerPanel.setLayout(null);

		p1.setBackground(Color.BLUE);
		p2.setBackground(Color.GREEN);
		p3.setBackground(Color.RED);
		p1.add(new JLabel("===============panel1======="));
		p2.add(new JLabel("===============panel2======="));
		p3.add(new JLabel("===============panel3======="));

		JButton jb1 = new JButton("panel1");
		jb1.addActionListener(new java.awt.event.ActionListener() {// jb1事件

			public void actionPerformed(java.awt.event.ActionEvent e) {
				xiaoGuo(p1);

			}

		});
		JButton jb2 = new JButton("panel2");
		jb2.addActionListener(new java.awt.event.ActionListener() {// jb1事件

			public void actionPerformed(java.awt.event.ActionEvent e) {
				xiaoGuo(p2);
			}

		});
		JButton jb3 = new JButton("panel3");
		jb3.addActionListener(new java.awt.event.ActionListener() {// jb1事件

			public void actionPerformed(java.awt.event.ActionEvent e) {
				xiaoGuo(p3);
				System.out.println("panel3========================/n");
			}

		});

		JButton jb4 = new JButton("返回");
		jb4.addActionListener(new java.awt.event.ActionListener() {// jb1事件

			public void actionPerformed(java.awt.event.ActionEvent e) {
				returnPanel(p1);
			}

		});
		topPanel.add(jb1);
		topPanel.add(jb2);
		topPanel.add(jb3);
		topPanel.add(jb4);

		contentPanel.add(topPanel, BorderLayout.NORTH);
		contentPanel.add(centerPanel, BorderLayout.CENTER);

		frame.show();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// 返回效果
	public void returnPanel(final JPanel panel) {
		panel.setBounds(0, 0, centerPanel.getWidth(), centerPanel.getHeight());// 设置滑动初始位置
		int count = centerPanel.getComponentCount();// 获取centerPanel中控件数
		List<Component> list = new ArrayList<Component>();//
		for (Component comp : centerPanel.getComponents()) {
			list.add(comp);// 给list赋值
		}
		if (count > 0) {// 如果centerPanel中控件数大于0就执行效果
			for (int i = 0; i < count; i++) {
				Component comp = centerPanel.getComponent(i);// 获得该位置的控件

				if (comp instanceof JPanel) {// 判断控件类型
					final JPanel currentPanel = (JPanel) comp;// 获得当前panel
					if (currentPanel != panel) {

						new Thread() {

							public void run() {

								Rectangle rec = currentPanel.getBounds();// 获得当前面板的位置信息
								int y = centerPanel.getWidth();

								for (int i = 0; i >= -centerPanel.getWidth(); i -= 10) {
									// 设置面板位置
									currentPanel.setBounds(i, 0,
											centerPanel.getWidth(),
											centerPanel.getHeight());
									panel.setBounds(y, 0,
											centerPanel.getWidth(),
											centerPanel.getHeight());
									try {
										Thread.sleep(100);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									y -= 10;
								}

								centerPanel.remove(currentPanel);// 移除當前面板

								panel.setBounds(0, 0, centerPanel.getWidth(),
										centerPanel.getHeight());

							}
						}.start();
						break;
					}
				}
			}
		}

		if (!list.contains(panel)) {
			centerPanel.add(panel);// 添加要切换的面板
		}

		centerPanel.validate();// 重构内容面板
		centerPanel.repaint();// 重绘内容面板
	}

	// 滑动效果方法
	public void xiaoGuo(final JPanel panel) {
		panel.setBounds(0, 0, centerPanel.getWidth(), centerPanel.getHeight());// 设置滑动初始位置
		int count = centerPanel.getComponentCount();// 获取centerPanel中控件数
		List<Component> list = new ArrayList<Component>();//
		for (Component comp : centerPanel.getComponents()) {
			list.add(comp);// 给list赋值
		}
		if (count > 0) {// 如果centerPanel中控件数大于0就执行效果
			for (int i = 0; i < count; i++) {
				Component comp = centerPanel.getComponent(i);// 获得该位置的控件

				if (comp instanceof JPanel) {// 判断控件类型
					final JPanel currentPanel = (JPanel) comp;// 获得当前panel
					if (currentPanel != panel) {

						new Thread() {

							public void run() {

								Rectangle rec = currentPanel.getBounds();// 获得当前面板的位置信息
								int y = -centerPanel.getWidth();

								for (int i = 0; i <= centerPanel.getWidth(); i += 10) {
									// 设置面板位置
									currentPanel.setBounds(i, 0,
											centerPanel.getWidth(),
											centerPanel.getHeight());
									panel.setBounds(y, 0,
											centerPanel.getWidth(),
											centerPanel.getHeight());
									try {
										Thread.sleep(20);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									y += 10;
								}

								centerPanel.remove(currentPanel);// 移除當前面板

								panel.setBounds(0, 0, centerPanel.getWidth(),
										centerPanel.getHeight());

							}
						}.start();
						break;
					}
				}
			}
		}

		if (!list.contains(panel)) {
			centerPanel.add(panel);// 添加要切换的面板
		}

		centerPanel.validate();// 重构内容面板
		centerPanel.repaint();// 重绘内容面板
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JpanelForm();
	}

}

