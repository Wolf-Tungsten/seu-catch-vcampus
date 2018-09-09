//教务处——课表查询面板
package com.wolfTungsten.vcampusClient.panel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class JwcCurriculum extends JPanel implements MouseListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int weekNumber = 1; //初始化周数为1

	//创建了两个面板数组 用来分别代表16周的面板
	//创建了一个字符数组 用来方便添加面板到卡片类中
	
	JPanel panel;
	JPanel infoPanel; 
	JPanel mainPanel;
	//panel 分成 info和main  
	// info (700,89) main(700,13*37=481)
	JPanel coursePanel,weekPanel,timePanel;
	//main分成三部分 week显示星期 time显示上下午课数 coursePanel 显示课程
	//time (100,481) week(600.37) course(600,444 = 12 *37)
	//字体颜色 (59，120，103)
	/**
	 * Create the panel.
	 */

	@SuppressWarnings("unused")
	public JwcCurriculum() {
		setSize(736,600);
		setLayout(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(18, 20, 700, 570);
		panel.setBackground(Color.PINK);
		add(panel);
		
		//panel面板的设置 添加info和main
		infoPanel = new JPanel();
		infoPanel.setSize(700, 89);
		infoPanel.setLocation(0, 0);
		mainPanel = new JPanel();
		mainPanel.setSize(700, panel.getHeight()-infoPanel.getHeight());
		mainPanel.setLocation(0, 89);
		infoPanel.setBackground(SystemColor.info);
		mainPanel.setBackground(Color.GREEN);
		panel.add(infoPanel);
		panel.add(mainPanel);

		//info面板的设置
		
		//main面板的设置 time week course  13行 13*37 = 481
		mainPanel.setLayout(null); //设置绝对布局 （很重要）
		timePanel = new JPanel();
		weekPanel = new JPanel();
		weekPanel.setSize(600, 37);
		weekPanel.setLocation(100, 0);
		coursePanel = new JPanel();
		coursePanel.setSize(600, 444);
		coursePanel.setLocation(100, 37);
		timePanel.setBackground(Color.GRAY);
		weekPanel.setBackground(Color.LIGHT_GRAY);
		coursePanel.setBackground(SystemColor.activeCaptionBorder);
		timePanel.setBounds(0,0,100,mainPanel.getHeight());
		mainPanel.add(timePanel);
		mainPanel.add(weekPanel);
		mainPanel.add(coursePanel);
		
		//timePanel
		timePanel.setLayout(null);
		JLabel morning = new JLabel("上午");
		morning.setHorizontalAlignment(SwingConstants.CENTER);
		morning.setFont(new Font("微软雅黑", Font.BOLD, 20));
		morning.setSize(60, 185);
		morning.setLocation(0, 37);
		JLabel afternoon = new JLabel("下午");
		afternoon.setHorizontalAlignment(SwingConstants.CENTER);
		afternoon.setFont(new Font("微软雅黑", Font.BOLD, 20));
		afternoon.setSize(60, 185);
		afternoon.setLocation(0, 217);
		JLabel night = new JLabel("晚上");
		night.setHorizontalAlignment(SwingConstants.CENTER);
		night.setFont(new Font("微软雅黑", Font.BOLD, 20));
		night.setSize(60, 74);
		night.setLocation(0, 407);
		JLabel time1 = new JLabel("1");
		time1.setHorizontalAlignment(SwingConstants.CENTER);
		time1.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		time1.setSize(40, 37);
		time1.setLocation(60, 37);
		JLabel time2 = new JLabel("2");
		time2.setHorizontalAlignment(SwingConstants.CENTER);
		time2.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		time2.setSize(40, 37);
		time2.setLocation(60, 74);
		JLabel time3 = new JLabel("3");
		time3.setSize(40, 37);
		time3.setLocation(60, 111);
		time3.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		time3.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel time4 = new JLabel("4");
		time4.setHorizontalAlignment(SwingConstants.CENTER);
		time4.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		time4.setSize(40, 37);
		time4.setLocation(60, 148);
		JLabel time5 = new JLabel("5");
		time5.setHorizontalAlignment(SwingConstants.CENTER);
		time5.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		time5.setSize(40, 37);
		time5.setLocation(60, 185);
		JLabel time6 = new JLabel("6");
		time6.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		time6.setHorizontalAlignment(SwingConstants.CENTER);
		time6.setSize(40, 37);
		time6.setLocation(60, 222);
		JLabel time7 = new JLabel("7");
		time7.setHorizontalAlignment(SwingConstants.CENTER);
		time7.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		time7.setSize(40, 37);
		time7.setLocation(60, 259);
		JLabel time8 = new JLabel("8");
		time8.setHorizontalAlignment(SwingConstants.CENTER);
		time8.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		time8.setSize(40, 37);
		time8.setLocation(60, 296);
		JLabel time9 = new JLabel("9");
		time9.setSize(40, 37);
		time9.setLocation(60, 333);
		time9.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		time9.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel time10 = new JLabel("10");
		time10.setHorizontalAlignment(SwingConstants.CENTER);
		time10.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		time10.setSize(40, 37);
		time10.setLocation(60, 370);
		JLabel time11 = new JLabel("11");
		time11.setHorizontalAlignment(SwingConstants.CENTER);
		time11.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		time11.setSize(40, 37);
		time11.setLocation(60, 407);
		JLabel time12 = new JLabel("12");
		time12.setHorizontalAlignment(SwingConstants.CENTER);
		time12.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		time12.setSize(40, 37);
		time12.setLocation(60, 444);
		JLabel blank = new JLabel();
		blank.setSize(100, 37);
		timePanel.add(morning);
		timePanel.add(afternoon);
		timePanel.add(night);
		timePanel.add(time1);
		timePanel.add(time2);
		timePanel.add(time3);
		timePanel.add(time4);
		timePanel.add(time5);
		timePanel.add(time6);
		timePanel.add(time7);
		timePanel.add(time8);
		timePanel.add(time9);
		timePanel.add(time10);
		timePanel.add(time11);
		timePanel.add(time12);
		timePanel.add(blank);
		
			
		//weekPanel
		weekPanel.setLayout(null);
		JLabel mon = new JLabel("周一");
		mon.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		mon.setHorizontalAlignment(SwingConstants.CENTER);
		mon.setSize(100, 37);
		JLabel tue = new JLabel("周二");
		tue.setHorizontalAlignment(SwingConstants.CENTER);
		tue.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		tue.setSize(120, 37);
		tue.setLocation(120, 0);
		JLabel wed = new JLabel("周三");
		wed.setHorizontalAlignment(SwingConstants.CENTER);
		wed.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		wed.setSize(120, 37);
		wed.setLocation(240, 0);
		JLabel thu = new JLabel("周四");
		thu.setHorizontalAlignment(SwingConstants.CENTER);
		thu.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		thu.setSize(120, 37);
		thu.setLocation(360, 0);
		JLabel fri = new JLabel("周五");
		fri.setHorizontalAlignment(SwingConstants.CENTER);
		fri.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		fri.setSize(120, 37);
		fri.setLocation(480, 0);
		weekPanel.add(mon);
		weekPanel.add(tue);
		weekPanel.add(wed);
		weekPanel.add(thu);
		weekPanel.add(fri);
		
		//coursePanel
		coursePanel.setLayout(null);
		JLabel course1 = new JLabel();
		course1.setHorizontalAlignment(SwingConstants.CENTER);
		course1.setText("\u6D4B\u8BD5\u5927\u5C0F\u989C\u8272");
		course1.setFont(new Font("微软雅黑 Light", Font.BOLD, 14));
		course1.setSize(120, 185);
		JLabel course2 = new JLabel();
		course2.setHorizontalAlignment(SwingConstants.CENTER);
		course2.setFont(new Font("微软雅黑 Light", Font.BOLD, 14));
		course2.setSize(120, 185);
		course2.setLocation(0, 185);
		JLabel course3 = new JLabel();
		course3.setFont(new Font("微软雅黑 Light", Font.BOLD, 14));
		course3.setHorizontalAlignment(SwingConstants.CENTER);
		course3.setSize(120, 74);
		course3.setLocation(0, 370);
		JLabel course4 = new JLabel();
		course4.setHorizontalAlignment(SwingConstants.CENTER);
		course4.setFont(new Font("微软雅黑 Light", Font.BOLD, 14));
		course4.setSize(120, 185);
		course4.setLocation(120, 0);
		JLabel course5 = new JLabel();
		course5.setFont(new Font("微软雅黑 Light", Font.BOLD, 14));
		course5.setHorizontalAlignment(SwingConstants.CENTER);
		course5.setSize(120, 185);
		course5.setLocation(120, 185);
		JLabel course6 = new JLabel();
		course6.setHorizontalAlignment(SwingConstants.CENTER);
		course6.setFont(new Font("微软雅黑 Light", Font.BOLD, 14));
		course6.setSize(120, 74);
		course6.setLocation(120, 370);
		JLabel course7 = new JLabel();
		course7.setHorizontalAlignment(SwingConstants.CENTER);
		course7.setFont(new Font("微软雅黑 Light", Font.BOLD, 14));
		course7.setSize(120, 185);
		course7.setLocation(240, 0);
		JLabel course8 = new JLabel();
		course8.setHorizontalAlignment(SwingConstants.CENTER);
		course8.setFont(new Font("微软雅黑 Light", Font.BOLD, 14));
		course8.setSize(120, 185);
		course8.setLocation(240, 185);
		JLabel course9 = new JLabel();
		course9.setHorizontalAlignment(SwingConstants.CENTER);
		course9.setFont(new Font("微软雅黑 Light", Font.BOLD, 14));
		course9.setSize(120, 74);
		course9.setLocation(240, 370);
		JLabel course10 = new JLabel();
		course10.setHorizontalAlignment(SwingConstants.CENTER);
		course10.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
		course10.setSize(120, 185);
		course10.setLocation(360, 0);
		JLabel course11 = new JLabel();
		course11.setHorizontalAlignment(SwingConstants.CENTER);
		course11.setFont(new Font("微软雅黑 Light", Font.BOLD, 14));
		course11.setSize(120, 185);
		course11.setLocation(360, 185);
		JLabel course12 = new JLabel();
		course12.setHorizontalAlignment(SwingConstants.CENTER);
		course12.setFont(new Font("微软雅黑 Light", Font.BOLD, 14));
		course12.setSize(120, 74);
		course12.setLocation(360, 370);
		JLabel course13 = new JLabel();
		course13.setHorizontalAlignment(SwingConstants.CENTER);
		course13.setFont(new Font("微软雅黑 Light", Font.ITALIC, 14));
		course13.setSize(120, 185);
		course13.setLocation(480, 0);
		JLabel course14 = new JLabel();
		course14.setHorizontalAlignment(SwingConstants.CENTER);
		course14.setFont(new Font("微软雅黑 Light", Font.BOLD, 14));
		course14.setSize(120, 185);
		course14.setLocation(480, 185);
		JLabel course15 = new JLabel();
		course15.setHorizontalAlignment(SwingConstants.CENTER);
		course15.setFont(new Font("微软雅黑 Light", Font.BOLD, 14));
		course15.setSize(120, 74);
		course15.setLocation(480, 370);
		coursePanel.add(course1);
		coursePanel.add(course2);
		coursePanel.add(course3);
		coursePanel.add(course4);
		coursePanel.add(course5);
		coursePanel.add(course6);
		coursePanel.add(course7);
		coursePanel.add(course8);
		coursePanel.add(course9);
		coursePanel.add(course10);
		coursePanel.add(course11);
		coursePanel.add(course12);
		coursePanel.add(course13);
		coursePanel.add(course14);
		coursePanel.add(course15);
		
		
		}	







	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根

		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

}
