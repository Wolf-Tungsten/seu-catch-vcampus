//���񴦡����α��ѯ���
package com.wolfTungsten.vcampusClient.panel;

import com.google.gson.internal.LinkedTreeMap;
import com.sun.source.tree.NewArrayTree;
//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;
import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;

//import sun.reflect.generics.scope.ClassScope;

import java.awt.Color;
import java.awt.Font;
import java.util.regex.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.SystemColor;
import java.awt.TextArea;

import javax.swing.SwingConstants;

//import com.sun.deploy.util.SessionState.Client;

public class JwcCurriculum extends JPanel implements MouseListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int weekNumber = 1; //��ʼ������Ϊ1

	//����������������� �����ֱ����16�ܵ����
	//������һ���ַ����� �������������嵽��Ƭ����
	
	JPanel panel;
	JPanel infoPanel; 
	JPanel mainPanel;

	//panel �ֳ� info��main  
	// info (700,89) main(700,13*37=481)
	JPanel coursePanel,weekPanel,timePanel;
	//main�ֳ������� week��ʾ���� time��ʾ��������� coursePanel ��ʾ�γ�
	//time (100,481) week(600.37) course(600,444 = 12 *37)
	//������ɫ (59��120��103)
	
	/**
	 * Create the panel.
	 *
	 */
	private JLabel courseMonMorning;
	private JLabel courseMonAfternoon;
	private JLabel courseMonNight;
	private JLabel courseTueMorning;
	private JLabel courseTueAfternoon;
	private JLabel courseTueNight;
	private JLabel courseWedMorning;
	private JLabel courseWedAfternoon;
	private JLabel courseWedNight;
	private JLabel courseThuMorning;
	private JLabel courseThuAfternoon;
	private JLabel courseThuNight;
	private JLabel courseFriMorning;
	private JLabel courseFriAfternoon;
	private JLabel courseFriNight;
	String token;
	@SuppressWarnings("unused")
	public JwcCurriculum(String Token) {
		token = Token;
		setSize(736,600);
		setLayout(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(18, 20, 700, 570);
		panel.setBackground(Color.PINK);
		add(panel);
		
		//panel�������� ���info��main
		infoPanel = new JPanel();
		infoPanel.setSize(700, 90);
		infoPanel.setLocation(0, 0);
		mainPanel = new JPanel();
		mainPanel.setSize(700, panel.getHeight()-infoPanel.getHeight());
		mainPanel.setLocation(0, 89);
		infoPanel.setBackground(SystemColor.info);
		mainPanel.setBackground(Color.GREEN);
		panel.add(infoPanel);
		panel.add(mainPanel);

		//info��������
		
		//main�������� time week course  13�� 13*37 = 481
		mainPanel.setLayout(null); //���þ��Բ��� ������Ҫ��
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
		JLabel morning = new JLabel("����");
		morning.setHorizontalAlignment(SwingConstants.CENTER);
		morning.setFont(new Font("΢���ź�", Font.BOLD, 20));
		morning.setSize(60, 185);
		morning.setLocation(0, 37);
		JLabel afternoon = new JLabel("����");
		afternoon.setHorizontalAlignment(SwingConstants.CENTER);
		afternoon.setFont(new Font("΢���ź�", Font.BOLD, 20));
		afternoon.setSize(60, 185);
		afternoon.setLocation(0, 217);
		JLabel night = new JLabel("����");
		night.setHorizontalAlignment(SwingConstants.CENTER);
		night.setFont(new Font("΢���ź�", Font.BOLD, 20));
		night.setSize(60, 74);
		night.setLocation(0, 407);
		JLabel time1 = new JLabel("1");
		time1.setHorizontalAlignment(SwingConstants.CENTER);
		time1.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		time1.setSize(40, 37);
		time1.setLocation(60, 37);
		JLabel time2 = new JLabel("2");
		time2.setHorizontalAlignment(SwingConstants.CENTER);
		time2.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		time2.setSize(40, 37);
		time2.setLocation(60, 74);
		JLabel time3 = new JLabel("3");
		time3.setSize(40, 37);
		time3.setLocation(60, 111);
		time3.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		time3.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel time4 = new JLabel("4");
		time4.setHorizontalAlignment(SwingConstants.CENTER);
		time4.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		time4.setSize(40, 37);
		time4.setLocation(60, 148);
		JLabel time5 = new JLabel("5");
		time5.setHorizontalAlignment(SwingConstants.CENTER);
		time5.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		time5.setSize(40, 37);
		time5.setLocation(60, 185);
		JLabel time6 = new JLabel("6");
		time6.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		time6.setHorizontalAlignment(SwingConstants.CENTER);
		time6.setSize(40, 37);
		time6.setLocation(60, 222);
		JLabel time7 = new JLabel("7");
		time7.setHorizontalAlignment(SwingConstants.CENTER);
		time7.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		time7.setSize(40, 37);
		time7.setLocation(60, 259);
		JLabel time8 = new JLabel("8");
		time8.setHorizontalAlignment(SwingConstants.CENTER);
		time8.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		time8.setSize(40, 37);
		time8.setLocation(60, 296);
		JLabel time9 = new JLabel("9");
		time9.setSize(40, 37);
		time9.setLocation(60, 333);
		time9.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		time9.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel time10 = new JLabel("10");
		time10.setHorizontalAlignment(SwingConstants.CENTER);
		time10.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		time10.setSize(40, 37);
		time10.setLocation(60, 370);
		JLabel time11 = new JLabel("11");
		time11.setHorizontalAlignment(SwingConstants.CENTER);
		time11.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		time11.setSize(40, 37);
		time11.setLocation(60, 407);
		JLabel time12 = new JLabel("12");
		time12.setHorizontalAlignment(SwingConstants.CENTER);
		time12.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
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
		JLabel mon = new JLabel("��һ");
		mon.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		mon.setHorizontalAlignment(SwingConstants.CENTER);
		mon.setSize(100, 37);
		JLabel tue = new JLabel("�ܶ�");
		tue.setHorizontalAlignment(SwingConstants.CENTER);
		tue.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		tue.setSize(120, 37);
		tue.setLocation(120, 0);
		JLabel wed = new JLabel("����");
		wed.setHorizontalAlignment(SwingConstants.CENTER);
		wed.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		wed.setSize(120, 37);
		wed.setLocation(240, 0);
		JLabel thu = new JLabel("����");
		thu.setHorizontalAlignment(SwingConstants.CENTER);
		thu.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		thu.setSize(120, 37);
		thu.setLocation(360, 0);
		JLabel fri = new JLabel("����");
		fri.setHorizontalAlignment(SwingConstants.CENTER);
		fri.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		fri.setSize(120, 37);
		fri.setLocation(480, 0);
		weekPanel.add(mon);
		weekPanel.add(tue);
		weekPanel.add(wed);
		weekPanel.add(thu);
		weekPanel.add(fri);

		//coursePanel
		
		coursePanel.setLayout(null);
		courseMonMorning = new JLabel();		 
		courseMonMorning.setHorizontalAlignment(SwingConstants.CENTER);
		courseMonMorning.setText("\u6D4B\u8BD5\u5927\u5C0F\u989C\u8272");
		courseMonMorning.setFont(new Font("΢���ź� Light", Font.BOLD, 14));
		courseMonMorning.setSize(120, 185);
		
		courseMonAfternoon = new JLabel();
		courseMonAfternoon.setHorizontalAlignment(SwingConstants.CENTER);
		courseMonAfternoon.setFont(new Font("΢���ź� Light", Font.BOLD, 14));
		courseMonAfternoon.setSize(120, 185);
		courseMonAfternoon.setLocation(0, 185);
		
		courseMonNight = new JLabel();
		courseMonNight.setFont(new Font("΢���ź� Light", Font.BOLD, 14));
		courseMonNight.setHorizontalAlignment(SwingConstants.CENTER);
		courseMonNight.setSize(120, 74);
		courseMonNight.setLocation(0, 370);
		
		courseTueMorning = new JLabel();
		courseTueMorning.setHorizontalAlignment(SwingConstants.CENTER);
		courseTueMorning.setFont(new Font("΢���ź� Light", Font.BOLD, 14));
		courseTueMorning.setSize(120, 185);
		courseTueMorning.setLocation(120, 0);

		courseTueAfternoon = new JLabel();
		courseTueAfternoon.setFont(new Font("΢���ź� Light", Font.BOLD, 14));
		courseTueAfternoon.setHorizontalAlignment(SwingConstants.CENTER);
		courseTueAfternoon.setSize(120, 185);
		courseTueAfternoon.setLocation(120, 185);
		
		courseTueNight = new JLabel();
		courseTueNight.setHorizontalAlignment(SwingConstants.CENTER);
		courseTueNight.setFont(new Font("΢���ź� Light", Font.BOLD, 14));
		courseTueNight.setSize(120, 74);
		courseTueNight.setLocation(120, 370);
		
		courseWedMorning = new JLabel();
		courseWedMorning.setHorizontalAlignment(SwingConstants.CENTER);
		courseWedMorning.setFont(new Font("΢���ź� Light", Font.BOLD, 14));
		courseWedMorning.setSize(120, 185);
		courseWedMorning.setLocation(240, 0);
		
		courseWedAfternoon = new JLabel();
		courseWedAfternoon.setHorizontalAlignment(SwingConstants.CENTER);
		courseWedAfternoon.setFont(new Font("΢���ź� Light", Font.BOLD, 14));
		courseWedAfternoon.setSize(120, 185);
		courseWedAfternoon.setLocation(240, 185);
		
		courseWedNight = new JLabel();
		courseWedNight.setHorizontalAlignment(SwingConstants.CENTER);
		courseWedNight.setFont(new Font("΢���ź� Light", Font.BOLD, 14));
		courseWedNight.setSize(120, 74);
		courseWedNight.setLocation(240, 370);
		
		courseThuMorning = new JLabel();
		courseThuMorning.setHorizontalAlignment(SwingConstants.CENTER);
		courseThuMorning.setFont(new Font("΢���ź� Light", Font.BOLD, 15));
		courseThuMorning.setSize(120, 185);
		courseThuMorning.setLocation(360, 0);
		
		courseThuAfternoon = new JLabel();
		courseThuAfternoon.setHorizontalAlignment(SwingConstants.CENTER);
		courseThuAfternoon.setFont(new Font("΢���ź� Light", Font.BOLD, 14));
		courseThuAfternoon.setSize(120, 185);
		courseThuAfternoon.setLocation(360, 185);

		courseThuNight = new JLabel();
		courseThuNight.setHorizontalAlignment(SwingConstants.CENTER);
		courseThuNight.setFont(new Font("΢���ź� Light", Font.BOLD, 14));
		courseThuNight.setSize(120, 74);
		courseThuNight.setLocation(360, 370);
		
		courseFriMorning = new JLabel();
		courseFriMorning.setHorizontalAlignment(SwingConstants.CENTER);
		courseFriMorning.setFont(new Font("΢���ź� Light", Font.ITALIC, 14));
		courseFriMorning.setSize(120, 185);
		courseFriMorning.setLocation(480, 0);
		
		courseFriAfternoon = new JLabel();
		courseFriAfternoon.setHorizontalAlignment(SwingConstants.CENTER);
		courseFriAfternoon.setFont(new Font("΢���ź� Light", Font.BOLD, 14));
		courseFriAfternoon.setSize(120, 185);
		courseFriAfternoon.setLocation(480, 185);
		
		courseFriNight = new JLabel();
		courseFriNight.setHorizontalAlignment(SwingConstants.CENTER);
		courseFriNight.setFont(new Font("΢���ź� Light", Font.BOLD, 14));
		courseFriNight.setSize(120, 74);
		courseFriNight.setLocation(480, 370);		
		
		coursePanel.add(courseMonMorning);
		coursePanel.add(courseMonAfternoon);
		coursePanel.add(courseMonNight);
		coursePanel.add(courseTueMorning);
		coursePanel.add(courseTueAfternoon);
		coursePanel.add(courseTueNight);
		coursePanel.add(courseWedMorning);
		coursePanel.add(courseWedAfternoon);
		coursePanel.add(courseWedNight);
		coursePanel.add(courseThuMorning);
		coursePanel.add(courseThuAfternoon);
		coursePanel.add(courseThuNight);
		coursePanel.add(courseFriMorning);
		coursePanel.add(courseFriAfternoon);
		coursePanel.add(courseFriNight);
		showCourse();
		
		}	
		private void TextArea() {
		// TODO Auto-generated method stub
		
	}
		//�α����ʾ 
		//�Զ���Ӻ���  
		//��Ҫ�õ� �������� String week (��һ) String day(����)
	/*	public void autoAddCourse(){
			if(week == "��һ" && day == "����") courseMonMorning.setText(          );
			else if(week == "��һ" && day == "����") courseMonAfternoon.setText(    );
			else if(week == "��һ" && day == "����") courseMonNight.setText(       );
			else if(week == "�ܶ�" && day == "����") courseTueMorning.setText(     );
			else if(week == "�ܶ�" && day == "����") courseTueAfternoon.setText(      );
			else if(week == "�ܶ�" && day == "����") courseTueNight.setText(        );
			else if(week == "����" && day == "����") courseWedMorning.setText(     );
			else if(week == "����" && day == "����") courseWedAfternoon.setText(      );
			else if(week == "����" && day == "����") courseWedNight.setText(     );
			else if(week == "����" && day == "����") courseThuMorning.setText(     );
			else if(week == "����" && day == "����") courseThuAfternoon.setText(     );
			else if(week == "����" && day == "����") courseThuNight.setText("");
			else if(week == "����" && day == "����") courseFriMorning.setText(     );
			else if(week == "����" && day == "����") courseFriAfternoon.setText(     );
			else if(week == "����" && day == "����") courseFriNight.setText(    );
	  }*/
 

	public void showCourse() {
		Client.Request request = new Request();
		request.setPath("EduAdmin/schedule");
		request.setToken(token);
		Client.Response response = Client.fetch(request);
		if(response.getSuccess()) 
		{
			ArrayList<LinkedTreeMap<String,Object>> courseList = 
					(ArrayList<LinkedTreeMap<String, Object>>)
					response.getBody().get("recordMaplist");
			for(LinkedTreeMap<String, Object> courseInfo:courseList) 
			{
				String courseName = (String) courseInfo.get("name");
				String lecturer = (String)courseInfo.get("lecturer");
				String classtime = (String)courseInfo.get("classtime");
				String week = (String)courseInfo.get("week");
				String day = classtime.split("\\d")[0];
				String time = classtime.split("[\\u4e00-\\u9fa5]")[2];
				String shwoText = new String();
				shwoText = String.format("<html><p>%s<br>%s<br>%s<br>%s</p></html>", courseName,lecturer,time,week);
				if(week.equals("��һ") && day.equals("����"))
					courseMonMorning.setText(shwoText);
				else if(week.equals("��һ") && day.equals("����")) 
					courseMonAfternoon.setText(shwoText);
				else if(week.equals("��һ") && day.equals("����"))
					courseMonNight.setText(shwoText);
				else if(week.equals("�ܶ�") && day.equals("����")) 
					courseTueMorning.setText(shwoText);
				else if(week.equals("�ܶ�") && day.equals("����")) 
					courseTueAfternoon.setText(shwoText);
				else if(week.equals("�ܶ�") && day.equals("����"))
					courseTueNight.setText(shwoText);
				else if(week.equals("����") && day.equals("����")) 
					courseWedMorning.setText(shwoText);
				else if(week.equals("����") && day.equals("����")) 
					courseWedAfternoon.setText(shwoText);
				else if(week.equals("����") && day.equals("����")) 
					courseWedNight.setText(shwoText);
				else if(week.equals("����") && day.equals("����"))
					courseThuMorning.setText(shwoText);
				else if(week.equals("����") && day.equals("����")) 
					courseThuAfternoon.setText(shwoText);
				else if(week.equals("����") && day.equals("����")) 
					courseThuNight.setText(shwoText);
				else if(week.equals("����") && day.equals("����")) 
					courseFriMorning.setText(shwoText);
				else if(week.equals("����") && day.equals("����"))
					courseFriAfternoon.setText(shwoText);
				else if(week.equals("����") && day.equals("����")) 
					courseFriNight.setText(shwoText);
						
			}
		}
	}



	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO �Զ����ɵķ������

		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

}
