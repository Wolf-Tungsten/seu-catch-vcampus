package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import javafx.scene.control.ComboBox;

public class JwcManager extends JPanel implements  ActionListener, FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_courseName;
	private JTextField textField_courseLecture;
	private JTextField textField_courseTime;
	private JTextField textField_coursePlace;
	private JTextField textField_courseScore;
	private JTextField textField_courseCapacity;
	private JButton button1,button2,button3,button4;
	private JButton button_courseAdd;
	private CardLayout layout;
	private JPanel cardPanel;
	JRadioButton radioButton_course ;
	JRadioButton radioButton_experiment; 
	ButtonGroup locationGroup;
	JCheckBox checkBox;
	JPanel panel_courseAdd,panel_experiment,panel_exam,panel_courseManage;
	private JTextField textField_search_name,textField_search_lecture,textField_search_courseName,textField_search_courseLecture
	,textField_search_expName,textField_search_expLecture;
	private DefaultTableModel tableModel1,tableModel2,tableModel3;
	private JTable table1,table2,table3;
	private JScrollPane scrollPane1,scrollPane2,scrollPane3;
	JButton button_manage_search,button_exam_search,button_experiment_search;
	private JComboBox comboBoxTime,comboBoxWeek;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked" })
	public JwcManager() {
		setSize(736,600);
		setLayout(null);
		
		// �Ϸ�panel_guide �·� panel_card
		//panel_guide
		JPanel panel_guide = new JPanel();
		panel_guide.setLayout(null);
		panel_guide.setBounds(0, 10, 736, 54);
		add(panel_guide);
		
		button1 = new JButton("�γ̹���");
		button1.setFont(new Font("΢���ź�", Font.BOLD, 16));
		button1.setBounds(75, 12, 100, 26);
		panel_guide.add(button1);
		button1.addActionListener(this);
		
		button2 = new JButton("�������");
		button2.setFont(new Font("΢���ź�", Font.BOLD, 16));
		button2.setBounds(237, 12, 100, 26);
		panel_guide.add(button2);
		button2.addActionListener(this);
		
		button3 = new JButton("����¼��");
		button3.setFont(new Font("΢���ź�", Font.BOLD, 16));
		button3.setBounds(399, 12, 100, 26);
		panel_guide.add(button3);
		button3.addActionListener(this);
		
		button4 = new JButton("ʵ��¼��");
		button4.setFont(new Font("΢���ź�", Font.BOLD, 16));
		button4.setBounds(561, 12, 100, 26);
		panel_guide.add(button4);
		button4.addActionListener(this);
		
	
		//�·���cardPanel �ĸ���� �γ̹��� �γ���� ����¼�� ʵ��¼��
		cardPanel = new JPanel();
		layout = new CardLayout();
		cardPanel.setLayout(layout);
		cardPanel.setBounds(0, 62, 736, 538);
		add(cardPanel);
		
		panel_courseAdd = new JPanel();
		panel_courseManage = new JPanel();
		panel_exam = new JPanel();
		panel_experiment = new JPanel();
		
		cardPanel.add(panel_courseManage,"card1");
		cardPanel.add(panel_courseAdd,"card2");
		cardPanel.add(panel_exam,"card3");
		cardPanel.add(panel_experiment,"card4");
		
//�γ�������-------------------------------------------------------------------------------------------------
		panel_courseAdd.setLayout(null);
		panel_courseAdd.setBounds(0, 0, 736, 538);
		
		textField_courseName= new JTextField("");
		textField_courseName.setColumns(10);
		textField_courseName.setBounds(241, 70, 291, 21);
		panel_courseAdd.add(textField_courseName);
		
		JLabel label = new JLabel("���ƣ�");
		label.setFont(new Font("΢���ź�", Font.BOLD, 14));
		label.setBounds(100, 72, 77, 15);
		panel_courseAdd.add(label);
		
		JLabel label_1 = new JLabel("�ο���ʦ��");
		label_1.setFont(new Font("΢���ź�", Font.BOLD, 14));
		label_1.setBounds(100, 113, 77, 15);
		panel_courseAdd.add(label_1);
		
		JLabel label_2 = new JLabel("�Ͽ��ܴ�");
		label_2.setFont(new Font("΢���ź�", Font.BOLD, 14));
		label_2.setBounds(100, 154, 77, 15);
		panel_courseAdd.add(label_2);
		
		JLabel label_3 = new JLabel("�Ͽ�ʱ��");
		label_3.setFont(new Font("΢���ź�", Font.BOLD, 14));
		label_3.setBounds(100, 194, 74, 15);
		panel_courseAdd.add(label_3);
		
		JLabel label_4 = new JLabel("�γ�ѧ�֣�");
		label_4.setFont(new Font("΢���ź�", Font.BOLD, 14));
		label_4.setBounds(100, 277, 74, 15);
		panel_courseAdd.add(label_4);
		
		JLabel label_5 = new JLabel("�Ͽεص㣺");
		label_5.setFont(new Font("΢���ź�", Font.BOLD, 14));
		label_5.setBounds(100, 234, 77, 15);
		panel_courseAdd.add(label_5);
		
		JLabel label_6 = new JLabel("�γ�������");
		label_6.setFont(new Font("΢���ź�", Font.BOLD, 14));
		label_6.setBounds(100, 314, 74, 15);
		panel_courseAdd.add(label_6);
		
		button_courseAdd = new JButton("���");
		button_courseAdd.setFont(new Font("΢���ź�", Font.BOLD, 14));
		button_courseAdd.setBounds(188, 440, 93, 23);
		panel_courseAdd.add(button_courseAdd);
		button_courseAdd.addActionListener(this);
		
		JButton button_2 = new JButton("ȡ��");
		button_2.setFont(new Font("΢���ź�", Font.BOLD, 14));
		button_2.setBounds(370, 440, 93, 23);
		panel_courseAdd.add(button_2);
		
		textField_courseLecture = new JTextField();
		textField_courseLecture.setColumns(10);
		textField_courseLecture.setBounds(241, 111, 291, 21);
		panel_courseAdd.add(textField_courseLecture);
		
		textField_courseTime = new JTextField();
		textField_courseTime.setColumns(10);
		textField_courseTime.setBounds(323, 192, 209, 21);
		panel_courseAdd.add(textField_courseTime);
		
		radioButton_course = new JRadioButton("�γ�");
		radioButton_course.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton_course.setSelected(true);
		radioButton_course.setFont(new Font("΢���ź�", Font.BOLD, 14));
		radioButton_course.setFocusPainted(false);
		radioButton_course.setBounds(188, 31, 93, 23);
		panel_courseAdd.add(radioButton_course);
		
		radioButton_experiment = new JRadioButton("ʵ��");
		radioButton_experiment.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton_experiment.setFont(new Font("΢���ź�", Font.BOLD, 14));
		radioButton_experiment.setFocusPainted(false);
		radioButton_experiment.setBounds(370, 31, 93, 23);
		panel_courseAdd.add(radioButton_experiment);
		
		locationGroup = new ButtonGroup();
		locationGroup.add(radioButton_course);
		locationGroup.add(radioButton_experiment);
		radioButton_course.setSelected(true);
		
		textField_coursePlace = new JTextField("");
		textField_coursePlace.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		textField_coursePlace.setBounds(241, 232, 291, 21);
		panel_courseAdd.add(textField_coursePlace);
		
		textField_courseScore = new JTextField();
		textField_courseScore.setColumns(10);
		textField_courseScore.setBounds(241, 275, 291, 21);
		panel_courseAdd.add(textField_courseScore);
		
		textField_courseCapacity = new JTextField("");
		textField_courseCapacity.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		textField_courseCapacity.setBounds(241, 312, 291, 21);
		panel_courseAdd.add(textField_courseCapacity);
		
		

		String[] listDataTime = new String[]{"����", "����", "����"};
		comboBoxTime = new JComboBox(listDataTime);
		comboBoxTime.setFont(new Font("΢���ź� Light", Font.BOLD, 16));
		comboBoxTime.setBounds(241, 192, 72, 21);
		String[] listDataWeek = new String[] {"��һ","�ܶ�","����","����","����"};
		comboBoxWeek = new JComboBox(listDataWeek);
		comboBoxWeek.setFont(new Font("΢���ź� Light", Font.BOLD, 16));
		comboBoxWeek.setLocation(241, 152);
		comboBoxWeek.setSize(72, 21);
		panel_courseAdd.add(comboBoxTime);
		panel_courseAdd.add(comboBoxWeek);
		
		
//�γ̹���------------------------------------------------------------------------------------------------------------
		panel_courseManage.setLayout(null);
		textField_search_name = new JTextField();
		textField_search_name.setSize(230, 21);
		textField_search_name.setLocation(70, 23);
		textField_search_name.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		textField_search_name.setColumns(10);
		textField_search_name.addFocusListener(this);
		textField_search_name.setText("�γ�����");
		panel_courseManage.add(textField_search_name);
		
		textField_search_lecture = new JTextField();
		textField_search_lecture.setSize(230, 21);
		textField_search_lecture.setLocation(330, 23);
		textField_search_lecture.addFocusListener(this);
		textField_search_lecture.setText("�ο���ʦ");
		panel_courseManage.add(textField_search_lecture);
		
		button_manage_search = new JButton("����");
		button_manage_search.setSize(93, 23);
		button_manage_search.setLocation(590, 22);
		button_manage_search.setFont(new Font("΢���ź�", Font.BOLD, 14));
		panel_courseManage.add(button_manage_search);
		button_manage_search.addActionListener(this);
		
		//�������
		String[] columnNames1= {"�γ�����","�ο���ʦ","�Ͽ��ܴ�","�Ͽ�ʱ��","�Ͽεص�","�γ�����","�γ�ѧ��","    "};//����������������
		//��������������
		String[][] tableValues1= {};
		tableModel1=new DefaultTableModel(tableValues1,columnNames1);
		table1=new JTable(tableModel1);//����ָ�����������ݵı��
		 //���ñ����ݾ�����ʾ
		DefaultTableCellRenderer cr1 = new DefaultTableCellRenderer();
		cr1.setHorizontalAlignment(JLabel.CENTER);
		 table1.setDefaultRenderer(Object.class, cr1);
		scrollPane1 = new JScrollPane(table1);
		scrollPane1.setBounds(20, 68, 696, 443);
		panel_courseManage.add(scrollPane1);
		
//����¼��-----------------------------------------------------------
		panel_exam.setLayout(null);
		textField_search_courseName = new JTextField();
		textField_search_courseName.setSize(230, 21);
		textField_search_courseName.setLocation(70, 23);
		textField_search_courseName.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		textField_search_courseName.setColumns(10);
		textField_search_courseName.addFocusListener(this);
		textField_search_courseName.setText("�γ�����");
		panel_exam.add(textField_search_courseName);
		
		textField_search_courseLecture = new JTextField();
		textField_search_courseLecture.setSize(230, 21);
		textField_search_courseLecture.setLocation(330, 23);
		textField_search_courseLecture.addFocusListener(this);
		textField_search_courseLecture.setText("�ο���ʦ");
		panel_exam.add(textField_search_courseLecture);
		
		button_exam_search = new JButton("����");
		button_exam_search.setSize(93, 23);
		button_exam_search.setLocation(590, 22);
		button_exam_search.setFont(new Font("΢���ź�", Font.BOLD, 14));
		panel_exam.add(button_exam_search);
		button_exam_search.addActionListener(this);
		
		//�������
		String[] columnNames2= {"�γ�����","�ο���ʦ","ѧ������","ѧ��һ��ͨ��","    "};//����������������
		//��������������
		String[][] tableValues2= {};
		tableModel2=new DefaultTableModel(tableValues2,columnNames2);
		table2=new JTable(tableModel2);//����ָ�����������ݵı��
		 //���ñ����ݾ�����ʾ
		DefaultTableCellRenderer cr2 = new DefaultTableCellRenderer();
		cr1.setHorizontalAlignment(JLabel.CENTER);
		 table2.setDefaultRenderer(Object.class, cr2);
		scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBounds(20, 68, 696, 443);
		panel_exam.add(scrollPane2);
		
//ʵ��¼��---------------------------------------------
		panel_experiment.setLayout(null);
		textField_search_expName = new JTextField();
		textField_search_expName.setSize(230, 21);
		textField_search_expName.setLocation(70, 23);
		textField_search_expName.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		textField_search_expName.setColumns(10);
		textField_search_expName.addFocusListener(this);
		textField_search_expName.setText("ʵ������");
		panel_experiment.add(textField_search_expName);
		
		textField_search_expLecture = new JTextField();
		textField_search_expLecture.setSize(230, 21);
		textField_search_expLecture.setLocation(330, 23);
		textField_search_expLecture.addFocusListener(this);
		textField_search_expLecture.setText("ʵ����ʦ");
		panel_experiment.add(textField_search_expLecture);
		
		button_experiment_search = new JButton("����");
		button_experiment_search.setSize(93, 23);
		button_experiment_search.setLocation(590, 22);
		button_experiment_search.setFont(new Font("΢���ź�", Font.BOLD, 14));
		panel_experiment.add(button_experiment_search);
		button_experiment_search.addActionListener(this);
		//�������
		String[] columnNames3= {"ʵ������","ʵ���ο���ʦ","ѧ������","ѧ��һ��ͨ��","    "};//����������������
		//��������������
		String[][] tableValues3= {};
		tableModel3=new DefaultTableModel(tableValues3,columnNames3);
		table3=new JTable(tableModel3);//����ָ�����������ݵı��
		 //���ñ����ݾ�����ʾ
		DefaultTableCellRenderer cr3 = new DefaultTableCellRenderer();
		cr3.setHorizontalAlignment(JLabel.CENTER);
		 table3.setDefaultRenderer(Object.class, cr3);
		scrollPane3 = new JScrollPane(table3);
		scrollPane3.setBounds(20, 68, 696, 443);
		panel_experiment.add(scrollPane3);

	}


	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource() == button1) {
			layout.show(cardPanel,"card1");
		}else if(e.getSource() == button2) {
			layout.show(cardPanel,"card2");
		}else if(e.getSource() == button3) {
			layout.show(cardPanel,"card3");
		}else if(e.getSource() == button4) {
			layout.show(cardPanel,"card4");
		}
		
		//�¼��γ����-==============================================================================================
		if(e.getSource() == button_courseAdd) {
			addCourse();// �γ���ӵĺ��� ------------------------------------------������
		}
		
		//�¼� ��������������� �ֱ��� �γ̹��� ����¼�� ʵ��¼��====================================================
		if(e.getSource() == button_manage_search) {
			//����������  
			String manage_name = textField_search_name.getText();
			String manage_lecture = textField_search_lecture.getText();
		}
		if(e.getSource() == button_manage_search) {
			//���������� 
			String exam_name = textField_search_courseName.getText();
			String exam_lecture = textField_search_courseLecture.getText();
		}
		if(e.getSource() == button_manage_search) {
			//���������� 	
			String exp_name = textField_search_expName.getText();
			String exp_lecture = textField_search_expLecture.getText();
		}	
		
	}
	@SuppressWarnings("unused")
	//================================================================================addCourse() =======================================
	private void addCourse() {
		// TODO �Զ����ɵķ������
		//�γ����͵�ֵ (�γ�/ʵ��)
		String type;
		if(radioButton_course.isSelected()) type = "�γ�";
		else if(radioButton_experiment.isSelected()) type ="ʵ��";
		
		
		//���Ƶ�ֵ
		String name = textField_courseName.getText();
		
		//��ʦ��ֵ
		String lecture = textField_courseLecture.getText();
		
		//�Ͽ��ܴ�  eg 
		//��Ҫ�������  (String) comboBoxWeek.getSelectedItem() 
		String week = (String) comboBoxWeek.getSelectedItem();
		
		//�Ͽ�ʱ�� eg (����) + 8��00 9��45          ��Ҫ������� (String)comboBoxTime.getSelectedItem(),�����ж������������� 
		String time = (String) comboBoxTime.getSelectedItem() + textField_courseTime.getText();
		
		//�Ͽεص�
		String place = textField_coursePlace.getText();
		
		//�γ�ѧ�� �õ�����һ��string �ǵ�ת������
		String score = textField_courseScore.getText();
		
		//�γ����� �õ�����һ��string �ǵ�ת������
		String capacity =  textField_courseCapacity.getText();
		
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource() == textField_search_name) {
			textField_search_name.setText("");
		}else if(e.getSource() == textField_search_lecture) {
			textField_search_lecture.setText("");
		}else if(e.getSource() == textField_search_courseName) {
			textField_search_courseName.setText("");
		}else if(e.getSource() == textField_search_courseLecture) {
			textField_search_courseLecture.setText("");
		}else if(e.getSource() == textField_search_expName) {
			textField_search_expName.setText("");
		}else if(e.getSource() == textField_search_expLecture) {
			textField_search_expLecture.setText("");
		}
		
	}


	@Override
	public void focusLost(FocusEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource() == textField_search_name) {
			textField_search_name.setText("�γ�����");
		}else if(e.getSource() == textField_search_lecture) {
			textField_search_lecture.setText("�ο���ʦ");
		}else if(e.getSource() == textField_search_courseName) {
			textField_search_courseName.setText("�γ�����");
		}else if(e.getSource() == textField_search_courseLecture) {
			textField_search_courseLecture.setText("�ο���ʦ");
		}else if(e.getSource() == textField_search_expName) {
			textField_search_expName.setText("ʵ������");
		}else if(e.getSource() == textField_search_expLecture) {
			textField_search_expLecture.setText("ʵ����ʦ");
		}
		
		
	}
}
