package com.wolfTungsten.vcampusClient.frame;
//真的主面板
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.net.URL;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.wolfTungsten.vcampusClient.panel.JwcCurriculum;
import com.wolfTungsten.vcampusClient.panel.JwcExam;
import com.wolfTungsten.vcampusClient.panel.JwcExperiment;
import com.wolfTungsten.vcampusClient.panel.JwcSelectCourses;
import com.wolfTungsten.vcampusClient.panel.LibFindBooksPanel;

public class FunctionFrame extends JFrame implements MouseListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JPanel panel_right,panel_message;
	JPanel panel_message_info,panel_message_jwc,panel_message_lib,panel_message_shop,panel_message_bank;
	JPanel panel_info,panel_jwc,panel_lib,panel_shop,panel_bank;
	JButton button_info,button_jwc,button_lib,button_shop,button_bank;	
	JButton button_lib_select;
	JPanel panel_jwc_select,panel_jwc_curriculum,panel_jwc_exam,panel_jwc_experiment;
	JButton button_jwc_select,button_jwc_curriculum,button_jwc_exam,button_jwc_experiment;
	static Point origin = new Point();
	CardLayout cardLayout = new CardLayout();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.osLookAndFeelDecorated;
				org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
					FunctionFrame frame = new FunctionFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void buttonSet(JButton button) {
		//button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		//button.setBackground(Color.green);
	}
	public FunctionFrame() {
		super();
		this.setResizable(false);
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
	    setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel bgLabel_left=new JLabel();
		bgLabel_left.setBounds(0,0,64, 600);
		URL resource_left=FunctionFrame.class.getResource("siderbar-background0.JPG");
		ImageIcon imageIcon_left=new ImageIcon(resource_left);
		bgLabel_left.setIcon(imageIcon_left);
		getLayeredPane().add(bgLabel_left,new Integer(Integer.MIN_VALUE));
		bgLabel_left.setBounds(0,0,imageIcon_left.getIconWidth(), imageIcon_left.getIconHeight());
		Container cp=getContentPane();
		cp.setLayout(null);
		((JPanel)cp).setOpaque(false);
		
		URL resource_info=FunctionFrame.class.getResource("info-normal.jpg");
		ImageIcon imageIcon_info=new ImageIcon(resource_info);
		URL resource_lib=FunctionFrame.class.getResource("lib-normal.jpg");
		ImageIcon imageIcon_lib=new ImageIcon(resource_lib);
		URL resource_jwc=FunctionFrame.class.getResource("jwc-normal.jpg");
		ImageIcon imageIcon_jwc=new ImageIcon(resource_jwc);
		URL resource_shop=FunctionFrame.class.getResource("shop-normal.jpg");
		ImageIcon imageIcon_shop=new ImageIcon(resource_shop);
		URL resource_bank=FunctionFrame.class.getResource("bank-normal.jpg");
		ImageIcon imageIcon_bank=new ImageIcon(resource_bank);
		//个人信息======================================================================================
		button_info = new JButton();
		button_info.setBounds(0, 64, 64,64);
		button_info.setIcon(imageIcon_info);
		buttonSet(button_info);
		button_info.addMouseListener(this);
		contentPane.add(button_info);	
		//图书馆管理系统
		button_lib = new JButton();
		button_lib.setBounds(0, 128, 64, 64);	
		button_lib.setIcon(imageIcon_lib);
		buttonSet(button_lib);
		button_lib.addMouseListener(this);
		contentPane.add(button_lib);	
		//教务系统
		button_jwc = new JButton();
		button_jwc.setBounds(0, 192, 64, 64);	
		button_jwc.setIcon(imageIcon_jwc);
		buttonSet(button_jwc);
		button_jwc.addMouseListener(this);
		contentPane.add(button_jwc);	
		//购物系统
		button_shop = new JButton();
		button_shop.setBounds(0, 256, 64, 64);
		button_shop.setIcon(imageIcon_shop);
		buttonSet(button_shop);
		button_shop.addMouseListener(this);
		contentPane.add(button_shop);	
		//银行系统
		button_bank = new JButton();
		button_bank.setBounds(0, 320, 64, 64);
		button_bank.setIcon(imageIcon_bank);
		buttonSet(button_bank);
		button_bank.addMouseListener(this);
		contentPane.add(button_bank);
		
		//提示面板
		//个人信息提示面板===================================================================================
		panel_message_info = new JPanel();
		panel_message_info.setBounds(64, 0,100, 600);
		panel_message_info.setBackground(Color.PINK);
		panel_message_info.setVisible(false); 
		
		//教务处提示面板=================================
		panel_message_jwc = new JPanel();
		panel_message_jwc.setBounds(64, 0,100, 600);
		panel_message_jwc.setBackground(Color.BLACK);
		//”学生选课“按钮，管理JwcSelectCourse面板
		button_jwc_select=new JButton("学生选课");
		button_jwc_select.setBounds(64, 0,100, 90);
		button_jwc_select.addMouseListener(this);
		panel_message_jwc.add(button_jwc_select);
		
		//”课表查询“按钮，管理JwcCurriculum面板
		button_jwc_curriculum=new JButton("课表查询");
		button_jwc_curriculum.setBounds(64, 100,100, 90);
		button_jwc_curriculum.addMouseListener(this);
		panel_message_jwc.add(button_jwc_curriculum);
		
		//”考试助手“按钮，管理JwcExam面板
		button_jwc_exam=new JButton("考试助手");
		button_jwc_exam.setBounds(64, 200,100, 90);
		button_jwc_exam.addMouseListener(this);
		panel_message_jwc.add(button_jwc_exam);
		
		//”考试助手“按钮，管理JwcExperiment面板
		button_jwc_experiment=new JButton("实验助手");
		button_jwc_experiment.setBounds(64, 300,100, 90);
		button_jwc_experiment.addMouseListener(this);
		panel_message_jwc.add(button_jwc_experiment);
		
		panel_message_jwc.setVisible(false); 
		
		//图书馆提示面板=================================
		panel_message_lib = new JPanel();
		panel_message_lib.setBounds(64, 0,100, 600);
		panel_message_lib.setBackground(Color.RED);
		
		button_lib_select=new JButton("图书借阅");
		button_lib_select.setBounds(64, 0,100, 90);
		button_lib_select.addMouseListener(this);
		panel_message_lib.add(button_lib_select);
		
		panel_message_lib.setVisible(false); 
		
		//购物系统提示面板
		panel_message_shop = new JPanel();
		panel_message_shop.setBounds(64, 0,100, 600);
		panel_message_shop.setBackground(Color.YELLOW);
		panel_message_shop.setVisible(false); 
		
		//银行系统提示面板
		panel_message_bank = new JPanel(cardLayout);
		panel_message_bank.setBounds(64, 0,100, 600);
		panel_message_bank.setBackground(Color.GREEN);
		panel_message_bank.setVisible(false); 
	    //把提示信息面板加到contentPane
		contentPane.add(panel_message_info);
		contentPane.add(panel_message_jwc);
		contentPane.add(panel_message_lib);
		contentPane.add(panel_message_shop);
		contentPane.add(panel_message_bank);
		
		//右侧卡片面板==================================================================================
		panel_right = new JPanel(cardLayout);
		panel_right.setBounds(64, 0, 736, 600);
		contentPane.add(panel_right);
		
		//个人信息
		panel_info = new JPanel();
		panel_info.setBackground(new Color(255, 255, 204));
		panel_right.add("1", panel_info);		
		//图书馆
		//panel_lib = new JPanel();
		panel_lib = new LibFindBooksPanel();
		panel_lib.setBackground(new Color(255, 255, 255));
//		panel_lib.setBackground(new Color(153, 255, 255));
		panel_right.add("2", panel_lib);
		//教务处============skk四个面板在这里被新建，背景色太丑，你自己改一下哈
		panel_jwc_select=new JwcSelectCourses();
		panel_jwc_select.setBackground(new Color(255, 255, 153));
		panel_right.add("jwc_1", panel_jwc_select);		
		
		panel_jwc_curriculum=new JwcCurriculum();
		panel_jwc_curriculum.setBackground(Color.PINK);
		panel_right.add("jwc_2", panel_jwc_curriculum);	
		
		panel_jwc_exam=new JwcExam();
		panel_jwc_exam.setBackground(Color.GREEN);
		panel_right.add("jwc_3", panel_jwc_exam);	
		
		panel_jwc_experiment=new JwcExperiment();
		panel_jwc_experiment.setBackground(Color.RED);
		panel_right.add("jwc_4", panel_jwc_experiment);	
	    //购物系统
		panel_shop = new JPanel();
		panel_shop.setBackground(new Color(255, 204, 255));
		panel_right.add("4", panel_shop);	
		//银行系统
		panel_bank = new JPanel();
		panel_bank.setBackground(Color.WHITE);
		panel_right.add("5", panel_bank);
		//默认为卡片1
		cardLayout.show(panel_right, "1");
		//实现鼠标拖拽窗口的功能
		this.addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		origin.x = e.getX();   //记录鼠标按下时的坐标
	    		origin.y = e.getY();
	    	}
	    	
	    	public void mouseClicked(MouseEvent e){
	    		setExtendedState(JFrame.ICONIFIED);
	    	}
	    });
		
		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				int xOnScreen = e.getXOnScreen();
				int yOnScreen = e.getYOnScreen();
				int xNew = xOnScreen - origin.x ;
				int yNew = yOnScreen - origin.y;
				setLocation(xNew, yNew);  
			}
		});
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			if (e.getSource() ==button_info) {
				cardLayout.show(panel_right, "1");
				panel_message_info.setVisible(true); 
				panel_message_jwc.setVisible(false); 
				panel_message_lib.setVisible(false); 
				panel_message_shop.setVisible(false); 
				panel_message_bank.setVisible(false); 
			} else if (e.getSource() == button_lib_select) {
				cardLayout.show(panel_right, "2");
				panel_message_info.setVisible(false); 
				panel_message_jwc.setVisible(false); 
				panel_message_lib.setVisible(false); 
				panel_message_shop.setVisible(false); 
				panel_message_bank.setVisible(false); 
			} else if (e.getSource() == button_jwc_select) {
				cardLayout.show(panel_right, "jwc_1");
				panel_message_info.setVisible(false); 
				panel_message_jwc.setVisible(false); 
				panel_message_lib.setVisible(false); 
				panel_message_shop.setVisible(false); 
				panel_message_bank.setVisible(false); 
			} else if (e.getSource() == button_jwc_curriculum) {
				cardLayout.show(panel_right, "jwc_2");
				panel_message_info.setVisible(false); 
				panel_message_jwc.setVisible(false); 
				panel_message_lib.setVisible(false); 
				panel_message_shop.setVisible(false); 
				panel_message_bank.setVisible(false); 
			}  else if (e.getSource() == button_jwc_exam) {
				cardLayout.show(panel_right, "jwc_3");
				panel_message_info.setVisible(false); 
				panel_message_jwc.setVisible(false); 
				panel_message_lib.setVisible(false); 
				panel_message_shop.setVisible(false); 
				panel_message_bank.setVisible(false); 
			} else if (e.getSource() == button_jwc_experiment) {
				cardLayout.show(panel_right, "jwc_4");
				panel_message_info.setVisible(false); 
				panel_message_jwc.setVisible(false); 
				panel_message_lib.setVisible(false); 
				panel_message_shop.setVisible(false); 
				panel_message_bank.setVisible(false); 
			} else if (e.getSource() == button_shop) {
				 cardLayout.show(panel_right, "4");
				 panel_message_info.setVisible(false); 
				 panel_message_jwc.setVisible(false); 
				 panel_message_lib.setVisible(false); 
				 panel_message_shop.setVisible(true); 
				 panel_message_bank.setVisible(false); 
			}else if (e.getSource() == button_bank) {
				cardLayout.show(panel_right, "5");
				panel_message_info.setVisible(false); 
				panel_message_jwc.setVisible(false); 
				panel_message_lib.setVisible(false); 
				panel_message_shop.setVisible(false); 
				panel_message_bank.setVisible(true); 
			}
		}
	}
	
	
 
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
 
	}
 
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
 
	}
 
	// �����
	@Override
	public void mouseEntered(MouseEvent e) {
		URL resource_info_active=FunctionFrame.class.getResource("info-active.jpg");
		ImageIcon imageIcon_info_active=new ImageIcon(resource_info_active);
		URL resource_lib_active=FunctionFrame.class.getResource("lib-active.jpg");
		ImageIcon imageIcon_lib_active=new ImageIcon(resource_lib_active);
		URL resource_jwc_active=FunctionFrame.class.getResource("jwc-active.jpg");
		ImageIcon imageIcon_jwc_active=new ImageIcon(resource_jwc_active);
		URL resource_shop_active=FunctionFrame.class.getResource("shop-active.jpg");
		ImageIcon imageIcon_shop_active=new ImageIcon(resource_shop_active);
		URL resource_bank_active=FunctionFrame.class.getResource("bank-active.jpg");
		ImageIcon imageIcon_bank_active=new ImageIcon(resource_bank_active);
		if (e.getSource() ==button_info) {
			button_info.setIcon(imageIcon_info_active);
			contentPane.add(button_info);
			panel_message_info.setVisible(true); 
			panel_message_jwc.setVisible(false); 
			panel_message_lib.setVisible(false); 
			panel_message_shop.setVisible(false); 
			panel_message_bank.setVisible(false); 
		}else if(e.getSource() == button_lib){
			button_lib.setIcon(imageIcon_lib_active);
			contentPane.add(button_lib);
			panel_message_info.setVisible(false);
			panel_message_jwc.setVisible(false); 
			panel_message_lib.setVisible(true); 
			panel_message_shop.setVisible(false); 
			panel_message_bank.setVisible(false); 
		}else if (e.getSource() == button_jwc) {
			button_jwc.setIcon(imageIcon_jwc_active);
			contentPane.add(button_jwc);
			panel_message_info.setVisible(false);
			panel_message_jwc.setVisible(true); 
			panel_message_lib.setVisible(false); 
			panel_message_shop.setVisible(false); 
			panel_message_bank.setVisible(false); 
		}else if (e.getSource() == button_shop) {
			button_shop.setIcon(imageIcon_shop_active);
			contentPane.add(button_shop);
			panel_message_info.setVisible(false);
			panel_message_jwc.setVisible(false); 
			panel_message_lib.setVisible(false); 
			panel_message_shop.setVisible(true); 
			panel_message_bank.setVisible(false); 
		}else if (e.getSource() == button_bank) {
			button_bank.setIcon(imageIcon_bank_active);
			contentPane.add(button_bank);
			panel_message_info.setVisible(false);
			panel_message_jwc.setVisible(false); 
			panel_message_lib.setVisible(false); 
			panel_message_shop.setVisible(false); 
			panel_message_bank.setVisible(true); 
		}
	}
 
	// ����˳�
	@Override
	public void mouseExited(MouseEvent e) {
		URL resource_info=FunctionFrame.class.getResource("info-normal.jpg");
		ImageIcon imageIcon_info=new ImageIcon(resource_info);
		URL resource_lib=FunctionFrame.class.getResource("lib-normal.jpg");
		ImageIcon imageIcon_lib=new ImageIcon(resource_lib);
		URL resource_jwc=FunctionFrame.class.getResource("jwc-normal.jpg");
		ImageIcon imageIcon_jwc=new ImageIcon(resource_jwc);
		URL resource_shop=FunctionFrame.class.getResource("shop-normal.jpg");
		ImageIcon imageIcon_shop=new ImageIcon(resource_shop);
		URL resource_bank=FunctionFrame.class.getResource("bank-normal.jpg");
		ImageIcon imageIcon_bank=new ImageIcon(resource_bank);
		if (e.getSource() ==button_info) {
			button_info.setIcon(imageIcon_info);
			contentPane.add(button_info);
		}
		else if(e.getSource() == button_lib){
			button_lib.setIcon(imageIcon_lib);
			contentPane.add(button_lib);
		}
		else if(e.getSource() == button_jwc){
			button_jwc.setIcon(imageIcon_jwc);
			contentPane.add(button_jwc);
		}
		else if(e.getSource() == button_shop){
			button_shop.setIcon(imageIcon_shop);
			contentPane.add(button_shop);
		}
		else if(e.getSource() == button_bank){
			button_bank.setIcon(imageIcon_bank);
			contentPane.add(button_bank);
		}
	}
}
