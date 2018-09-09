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
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.wolfTungsten.vcampusClient.panel.BankBill;
import com.wolfTungsten.vcampusClient.panel.BankModifyPass;
import com.wolfTungsten.vcampusClient.panel.BankSaveAndWithdraw;
import com.wolfTungsten.vcampusClient.panel.BankTurnMoney;
import com.wolfTungsten.vcampusClient.panel.JwcCurriculum;
import com.wolfTungsten.vcampusClient.panel.JwcExam;
import com.wolfTungsten.vcampusClient.panel.JwcExperiment;
import com.wolfTungsten.vcampusClient.panel.JwcManager;
import com.wolfTungsten.vcampusClient.panel.JwcSelectCourses;
import com.wolfTungsten.vcampusClient.panel.LibFindBooksPanel;
import com.wolfTungsten.vcampusClient.panel.LibManager;
import com.wolfTungsten.vcampusClient.panel.LibMessage;
import java.awt.Font;

public class FunctionFrame extends JFrame implements MouseListener{
	private static final long serialVersionUID = 1L;
	private JLayeredPane contentPane;
	JPanel panel_right,panel_message;
	JPanel panel_message_info,panel_message_jwc,panel_message_lib,panel_message_shop,panel_message_bank;
	JPanel panel_info,panel_jwc,panel_lib,panel_shop,panel_bank;
	JButton button_info,button_jwc,button_lib,button_shop,button_bank;	
	JLabel label_lib_select,label_lib_message,label_lib_manager;
	JPanel panel_lib_select,panel_lib_message,panel_lib_manager;
	JLabel label_lib_exit,label_jwc_exit;
	JLabel label_blank1,label_blank2,label_blank3;
	JPanel panel_jwc_select,panel_jwc_curriculum,panel_jwc_exam,panel_jwc_experiment,panel_jwc_manager;
	JLabel label_jwc_select,label_jwc_curriculum,label_jwc_exam,label_jwc_experiment,label_jwc_manager;
	JPanel panel_bank_save_withdraw,panel_bank_turn_money,panel_bank_bill,panel_bank_modify_pass;
	JLabel label_bank_save_withdraw,label_bank_turn_money,label_bank_bill,label_bank_modify_pass;
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
	@SuppressWarnings("deprecation")
	public FunctionFrame() {
		super();
		this.setResizable(false);
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
	    setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel bgLabel_left=new JLabel();//==========================左侧绿色标签=============================
		bgLabel_left.setBounds(0,0,64, 600);
		URL resource_left=FunctionFrame.class.getResource("siderbar-background0.JPG");
		ImageIcon imageIcon_left=new ImageIcon(resource_left);
		bgLabel_left.setIcon(imageIcon_left);
		contentPane.add(bgLabel_left,new Integer(Integer.MIN_VALUE));
		bgLabel_left.setBounds(0,0,imageIcon_left.getIconWidth(), imageIcon_left.getIconHeight());

		

		//空白标签
		label_blank2 = new JLabel();
		label_blank2.setBounds(0,0,64,64);
		label_blank2.addMouseListener(this);
		contentPane.add(label_blank2);
		label_blank3 = new JLabel();
        label_blank3.setBounds(0,384,64,216);
        label_blank3.addMouseListener(this);
		contentPane.add(label_blank3);
	
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
		//个人信息=================个人信息按钮==================================
		button_info = new JButton();
		button_info.setBounds(0, 64, 64,64);
		button_info.setIcon(imageIcon_info);
		buttonSet(button_info);
		button_info.addMouseListener(this);
		contentPane.add(button_info,new Integer(10));	//个人信息按钮设置在10层
		//图书馆管理系统===========图书管理系统按钮==================================
		button_lib = new JButton();
		button_lib.setBounds(0, 128, 64, 64);	
		button_lib.setIcon(imageIcon_lib);
		buttonSet(button_lib);
		button_lib.addMouseListener(this);
		contentPane.add(button_lib,new Integer(10));	//图书管理系统按钮设置在10层
		//教务系统===========教务处系统按钮==================================
		button_jwc = new JButton();
		button_jwc.setBounds(0, 192, 64, 64);	
		button_jwc.setIcon(imageIcon_jwc);
		buttonSet(button_jwc);
		button_jwc.addMouseListener(this);
		contentPane.add(button_jwc,new Integer(10));	//教务处系统按钮设置在10层
		//购物系统===========购物系统按钮==================================
		button_shop = new JButton();
		button_shop.setBounds(0, 256, 64, 64);
		button_shop.setIcon(imageIcon_shop);
		buttonSet(button_shop);
		button_shop.addMouseListener(this);
		contentPane.add(button_shop,new Integer(10));	//购物系统按钮设置在10层
		//银行系统===========银行系统按钮==================================
		button_bank = new JButton();
		button_bank.setBounds(0, 320, 64, 64);
		button_bank.setIcon(imageIcon_bank);
		buttonSet(button_bank);
		button_bank.addMouseListener(this);
		contentPane.add(button_bank,new Integer(10));	//银行系统按钮设置在10层
		
		//提示面板
		//个人信息提示面板==========================个人信息提示面板===========================================
		panel_message_info = new JPanel();
		panel_message_info.setBounds(64, 0,150, 600);
		panel_message_info.setBackground(new Color(230,230,230));
		panel_message_info.setVisible(false); 
		
		//图书馆提示面板==============================图书馆提示面板===================================
		panel_message_lib = new JPanel();
		panel_message_lib.setBounds(64, 0,150, 600);
		panel_message_lib.setBackground(new Color(230,230,230));
		panel_message_lib.setLayout(null);
		//“图书检索”按钮（标签），关联LibFindBooksPanel	面板
		label_lib_select=new JLabel("图书检索",JLabel.CENTER);
		label_lib_select.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_lib_select.setForeground(new Color(59,120,103));
		label_lib_select.setBounds(0, 40,150, 50);
		label_lib_select.addMouseListener(this);
		panel_message_lib.add(label_lib_select);
		//“借还信息”按钮（标签），关联LibMessage面板
		label_lib_message=new JLabel("借还信息",JLabel.CENTER);
		label_lib_message.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_lib_message.setForeground(new Color(59,120,103));
		label_lib_message.setBounds(0, 90,150, 50);
		label_lib_message.addMouseListener(this);
		panel_message_lib.add(label_lib_message);
		//“管理员”按钮（标签），关联LibManager面板==============有权限的人才能看到这个按钮？？？？？？？？？？？？？？？？
		label_lib_manager=new JLabel("管理员",JLabel.CENTER);
		label_lib_manager.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_lib_manager.setForeground(new Color(59,120,103));
		label_lib_manager.setBounds(0, 140,150, 50);
		label_lib_manager.addMouseListener(this);
		panel_message_lib.add(label_lib_manager);
	    //虚假的空白标签
		label_lib_exit= new JLabel();
		label_lib_exit.setBounds(0,190,150,410);
		label_lib_exit.addMouseListener(this);
		panel_message_lib.add(label_lib_exit);
		
		panel_message_lib.setVisible(false); 
		
		//教务处提示面板==============================教务处提示面板===================================
		panel_message_jwc = new JPanel();
		panel_message_jwc.setBounds(64, 0,150, 600);
		panel_message_jwc.setBackground(new Color(230,230,230));
		panel_message_jwc.setLayout(null);
		
		//”学生选课“按钮，关联JwcSelectCourse面板
		label_jwc_select=new JLabel("学生选课",JLabel.CENTER);
		label_jwc_select.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_jwc_select.setForeground(new Color(59,120,103));
		label_jwc_select.setBounds(0, 40,150, 50);
		label_jwc_select.addMouseListener(this);
		panel_message_jwc.add(label_jwc_select);
		
		//”课表查询“按钮（标签），关联JwcCurriculum面板
		label_jwc_curriculum=new JLabel("课表查询",JLabel.CENTER);
		label_jwc_curriculum.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_jwc_curriculum.setForeground(new Color(59,120,103));
		label_jwc_curriculum.setBounds(0, 90,150, 50);
		label_jwc_curriculum.addMouseListener(this);
		panel_message_jwc.add(label_jwc_curriculum);
		
		//”考试助手“按钮（标签），关联JwcExam面板
		label_jwc_exam=new JLabel("考试助手",JLabel.CENTER);
		label_jwc_exam.setFont(new Font("微软雅黑", Font.BOLD, 14));		
		label_jwc_exam.setForeground(new Color(59,120,103));
		label_jwc_exam.setBounds(0, 140,150, 50);
		label_jwc_exam.addMouseListener(this);
		panel_message_jwc.add(label_jwc_exam);
		
		//”考试助手“按钮（标签），关联JwcExperiment面板
		label_jwc_experiment=new JLabel("实验助手",JLabel.CENTER);
		label_jwc_experiment.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_jwc_experiment.setForeground(new Color(59,120,103));
		label_jwc_experiment.setBounds(0, 190,150, 50);
		label_jwc_experiment.addMouseListener(this);
		panel_message_jwc.add(label_jwc_experiment);
		
		//”管理者“按钮（标签），关联JwcManager面板 ------------------------------权限
		label_jwc_manager=new JLabel("管理者",JLabel.CENTER);
		label_jwc_manager.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_jwc_manager.setForeground(new Color(59,120,103));
		label_jwc_manager.setBounds(0, 240,150, 50);
		label_jwc_manager.addMouseListener(this);
		panel_message_jwc.add(label_jwc_manager);
		
		//虚假的空白标签
		label_jwc_exit= new JLabel();
		label_jwc_exit.setBounds(0,290,150,310);
		label_jwc_exit.addMouseListener(this);
		panel_message_jwc.add(label_jwc_exit);
		
		panel_message_jwc.setVisible(false); 
		
		//购物系统提示面板==============================购物系统提示面板===================================
		panel_message_shop = new JPanel();
		panel_message_shop.setBounds(64, 0,150, 600);
		panel_message_shop.setBackground(Color.YELLOW);
		panel_message_shop.setVisible(false); 
		
		//银行系统提示面板==============================银行系统提示面板===================================
		panel_message_bank = new JPanel();
		panel_message_bank.setBounds(64, 0,150, 600);
		panel_message_bank.setBackground(new Color(230,230,230));
		panel_message_bank.setLayout(null);
         
		//“存取款业务”，管理BankSaveAndWithdraw面板
		//存款，从我输的到账户，取款，从账户取到银行
		label_bank_save_withdraw=new JLabel("存取款业务",JLabel.CENTER);
		label_bank_save_withdraw.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_bank_save_withdraw.setForeground(new Color(59,120,103));
		label_bank_save_withdraw.setBounds(0, 40,150, 50);
		label_bank_save_withdraw.addMouseListener(this);
		panel_message_bank.add(label_bank_save_withdraw);
		//”转账汇款“按钮，关联BankTurnMoney面板
		//从我的账户到另一个账户,
		label_bank_turn_money=new JLabel("转账汇款",JLabel.CENTER);
		label_bank_turn_money.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_bank_turn_money.setForeground(new Color(59,120,103));
		label_bank_turn_money.setBounds(0, 90,150, 50);
		label_bank_turn_money.addMouseListener(this);
		panel_message_bank.add(label_bank_turn_money);
		//“交易明细“按钮	,关联BankBill面板		
		label_bank_bill=new JLabel("交易明细",JLabel.CENTER);
		label_bank_bill.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_bank_bill.setForeground(new Color(59,120,103));
		label_bank_bill.setBounds(0, 140,150, 50);
		label_bank_bill.addMouseListener(this);
		panel_message_bank.add(label_bank_bill);
				
		//”修改账户密码“按钮，关联BankModifyPass面板
		label_bank_modify_pass=new JLabel("修改账户密码",JLabel.CENTER);
		label_bank_modify_pass.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_bank_modify_pass.setForeground(new Color(59,120,103));
		label_bank_modify_pass.setBounds(0, 190,150, 50);
		label_bank_modify_pass.addMouseListener(this);
		panel_message_bank.add(label_bank_modify_pass);
/*			   			
		//虚假的空白标签
		label_bank_exit= new JLabel();
		label_bank_exit.setBounds(0,240,150,360);
		label_bank_exit.addMouseListener(this);
		panel_message_bank.add(label_bank_exit);
	*/		
		
		panel_message_bank.setVisible(false); 
		
	    //把提示信息面板加到contentPane
		
		contentPane.add(panel_message_info,new Integer(5));
		contentPane.add(panel_message_jwc,new Integer(6));
		contentPane.add(panel_message_lib,new Integer(7));
		contentPane.add(panel_message_shop,new Integer(8));
		contentPane.add(panel_message_bank,new Integer(9));//分别将五个提示面板放置在比10层低的地方
		
		//右侧卡片面板==================================================================================
		//空白标签
		label_blank1=new JLabel();
		label_blank1.setBounds(214, 0,586, 600);////假如提示面板宽度不是150了这里要变动，800-64-宽=右边空白标签的宽
		label_blank1.addMouseListener(this);
		contentPane.add(label_blank1);
		label_blank2 = new JLabel();
		label_blank2.setBounds(0,0,64,64);
		label_blank2.addMouseListener(this);
		contentPane.add(label_blank2);
		label_blank3 = new JLabel();
        label_blank3.setBounds(0,384,64,216);
        label_blank3.addMouseListener(this);
		contentPane.add(label_blank3);		
		
		panel_right = new JPanel(cardLayout);
		panel_right.setBounds(64, 0, 736, 600);
		contentPane.add(panel_right,new Integer(10));//将右边面板放置在10层

		
		//个人信息=================================个人信息板块的面板们=======================================
		panel_info = new JPanel();
		panel_info.setBackground(new Color(255, 255, 204));
		panel_right.add("1", panel_info);		
		//图书馆=================================图书馆板块的面板们=======================================
		//”图书检索“面板
		panel_lib_select = new LibFindBooksPanel();
		panel_lib_select.setBackground(new Color(255, 255, 255));
		panel_right.add("lib_1", panel_lib_select);
		//“借还信息”面板
		panel_lib_message = new LibMessage();
		panel_lib_message.setBackground(new Color(255, 255, 255));
		panel_right.add("lib_2", panel_lib_message);
		//“管理员”面板
		panel_lib_manager = new LibManager();
//		panel_lib_manager.setBackground(new Color(255, 255, 204));
		panel_right.add("lib_3", panel_lib_manager);
		
		///教务处================================教务处板块的面板们=======================================
		//教务处============skk四个面板在这里被新建，背景色太丑，你自己改一下哈
		panel_jwc_select=new JwcSelectCourses();
		panel_jwc_select.setBackground(new Color(255, 255, 255));
		panel_right.add("jwc_1", panel_jwc_select);		
		
		panel_jwc_curriculum=new JwcCurriculum();
		panel_jwc_curriculum.setBackground(new Color(255, 255, 255));
		panel_right.add("jwc_2", panel_jwc_curriculum);	
		
		panel_jwc_exam=new JwcExam();
		panel_jwc_exam.setBackground(new Color(255, 255, 255));
		panel_right.add("jwc_3", panel_jwc_exam);	
		
		
		panel_jwc_experiment=new JwcExperiment();
		panel_jwc_experiment.setBackground(new Color(255, 255, 255));
		panel_right.add("jwc_4", panel_jwc_experiment);	
		
		panel_jwc_manager = new JwcManager();
		panel_jwc_manager.setBackground(new Color(255, 255, 255));
		panel_right.add("jwc_5", panel_jwc_manager);
	    //购物系统================================购物系统板块的面板们=======================================
		panel_shop = new JPanel();
		panel_shop.setBackground(new Color(255, 204, 255));
		panel_right.add("4", panel_shop);	
		//银行系统================================银行板块的面板们=======================================
		panel_bank_save_withdraw=new BankSaveAndWithdraw();
		panel_bank_save_withdraw.setBackground(new Color(255, 255, 255));
		panel_right.add("bank_1", panel_bank_save_withdraw);		
		
		panel_bank_turn_money=new BankTurnMoney();
		panel_bank_turn_money.setBackground(new Color(255, 255, 255));
		panel_right.add("bank_2", panel_bank_turn_money);	
		
		panel_bank_bill=new BankBill();
		panel_bank_bill.setBackground(new Color(255, 255, 255));
		panel_right.add("bank_3", panel_bank_bill);	
		
		
		panel_bank_modify_pass=new BankModifyPass();
		panel_bank_modify_pass.setBackground(new Color(255, 255, 255));
		panel_right.add("bank_4", panel_bank_modify_pass);
		
		contentPane.getParent().getParent();
			
		//实现鼠标拖拽窗口的功能
		this.addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		origin.x = e.getX();   //记录鼠标按下时的坐标
	    		origin.y = e.getY();
	    	}
	    	//瞎点就会缩小系列，这不就相当于缩小键吗？
//	    	public void mouseClicked(MouseEvent e){
//	    		setExtendedState(JFrame.ICONIFIED);
//	    	}
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
	//==========================================鼠标点击==========================================
	public void HideAllMessagePanel() {
		panel_message_info.setVisible(false); 
		panel_message_jwc.setVisible(false); 
		panel_message_lib.setVisible(false); 
		panel_message_shop.setVisible(false); 
		panel_message_bank.setVisible(false); 
		contentPane.add(panel_message_info,new Integer(5));
		contentPane.add(panel_message_jwc,new Integer(6));
		contentPane.add(panel_message_lib,new Integer(7));
		contentPane.add(panel_message_shop,new Integer(8));
		contentPane.add(panel_message_bank,new Integer(9));//分别将五个提示面板放置在比10层低的地方
	}
	
	@SuppressWarnings("deprecation")
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
				contentPane.setLayer(panel_message_info,new Integer(11));//将panel_message_info放置在比10层高的地方
				
			} else if (e.getSource() == label_lib_select) {
				cardLayout.show(panel_right, "lib_1");
				HideAllMessagePanel();
			} else if (e.getSource() == label_lib_message) {
				cardLayout.show(panel_right, "lib_2");
				HideAllMessagePanel();
			}else if (e.getSource() == label_lib_manager) {
				cardLayout.show(panel_right, "lib_3");
				HideAllMessagePanel();
				
			}else if (e.getSource() == label_jwc_select) {
				cardLayout.show(panel_right, "jwc_1");
				HideAllMessagePanel();
			} else if (e.getSource() == label_jwc_curriculum) {
				cardLayout.show(panel_right, "jwc_2");
				HideAllMessagePanel();
			}  else if (e.getSource() == label_jwc_exam) {
				cardLayout.show(panel_right, "jwc_3");
				HideAllMessagePanel();
			} else if (e.getSource() == label_jwc_experiment) {
				cardLayout.show(panel_right, "jwc_4");
				HideAllMessagePanel();	
			} else if (e.getSource() == label_jwc_manager) {
				cardLayout.show(panel_right, "jwc_5");
				HideAllMessagePanel();	
			} else if (e.getSource() == button_shop) {
				 cardLayout.show(panel_right, "4");
				 panel_message_info.setVisible(false); 
				 panel_message_jwc.setVisible(false); 
				 panel_message_lib.setVisible(false); 
				 panel_message_shop.setVisible(true); 
				 panel_message_bank.setVisible(false);  
				 contentPane.setLayer(panel_message_shop,new Integer(11));

			}else if (e.getSource() == label_bank_save_withdraw) {
				cardLayout.show(panel_right, "bank_1");
				HideAllMessagePanel();
			} else if (e.getSource() == label_bank_turn_money) {
				cardLayout.show(panel_right, "bank_2");
				HideAllMessagePanel();
			}  else if (e.getSource() == label_bank_bill) {
				cardLayout.show(panel_right, "bank_3");
				HideAllMessagePanel();
			} else if (e.getSource() == label_bank_modify_pass) {
				cardLayout.show(panel_right, "bank_4");
				HideAllMessagePanel();		
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
 
	//==========================================鼠标进入区域==========================================
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
			contentPane.setLayer(panel_message_info,new Integer(11));

		}else if(e.getSource() == button_lib){
			button_lib.setIcon(imageIcon_lib_active);
			contentPane.add(button_lib);
			panel_message_info.setVisible(false);
			panel_message_jwc.setVisible(false); 
			panel_message_lib.setVisible(true); 
			panel_message_shop.setVisible(false); 
			panel_message_bank.setVisible(false); 
			contentPane.setLayer(panel_message_lib,new Integer(11));

		}else if (e.getSource() == button_jwc) {
			button_jwc.setIcon(imageIcon_jwc_active);
			contentPane.add(button_jwc);
			panel_message_info.setVisible(false);
			panel_message_jwc.setVisible(true); 
			panel_message_lib.setVisible(false); 
			panel_message_shop.setVisible(false); 
			panel_message_bank.setVisible(false); 
			contentPane.setLayer(panel_message_jwc,new Integer(11));

		}else if (e.getSource() == button_shop) {
			button_shop.setIcon(imageIcon_shop_active);
			contentPane.add(button_shop);
			panel_message_info.setVisible(false);
			panel_message_jwc.setVisible(false); 
			panel_message_lib.setVisible(false); 
			panel_message_shop.setVisible(true); 
			panel_message_bank.setVisible(false); 
			contentPane.setLayer(panel_message_shop,new Integer(11));

		}else if (e.getSource() == button_bank) {
			button_bank.setIcon(imageIcon_bank_active);
			contentPane.add(button_bank);
			panel_message_info.setVisible(false);
			panel_message_jwc.setVisible(false); 
			panel_message_lib.setVisible(false); 
			panel_message_shop.setVisible(false); 
			panel_message_bank.setVisible(true); 
			contentPane.setLayer(panel_message_bank,new Integer(11));

		}
		
		 if (e.getSource() == label_lib_select) {
			 label_lib_select.setOpaque(true);
			 label_lib_select.setBackground(Color.WHITE);
		 }else if (e.getSource() == label_lib_message) {
			 label_lib_message.setOpaque(true);
			 label_lib_message.setBackground(Color.WHITE);
		 }else if (e.getSource() == label_lib_manager) {
			 label_lib_manager.setOpaque(true);
			 label_lib_manager.setBackground(Color.WHITE);
		 }
		 
		 if (e.getSource() == label_jwc_select) {
			 label_jwc_select.setOpaque(true);
			 label_jwc_select.setBackground(Color.WHITE);
		 }else if (e.getSource() == label_jwc_curriculum) {
			 label_jwc_curriculum.setOpaque(true);
			 label_jwc_curriculum.setBackground(Color.WHITE);
		 }else if (e.getSource() == label_jwc_exam) {
			 label_jwc_exam.setOpaque(true);
			 label_jwc_exam.setBackground(Color.WHITE);
		 }else if (e.getSource() == label_jwc_experiment) {
			 label_jwc_experiment.setOpaque(true);
			 label_jwc_experiment.setBackground(Color.WHITE);
		 } else if (e.getSource() == label_jwc_manager) {
			 label_jwc_manager.setOpaque(true);
			 label_jwc_manager.setBackground(Color.WHITE);
		 } 
		 
		 if (e.getSource() == label_bank_save_withdraw) {
			 label_bank_save_withdraw.setOpaque(true);
			 label_bank_save_withdraw.setBackground(Color.WHITE);
		 }else if (e.getSource() == label_bank_turn_money) {
			 label_bank_turn_money.setOpaque(true);
			 label_bank_turn_money.setBackground(Color.WHITE);
		 }else if (e.getSource() == label_bank_bill) {
			 label_bank_bill.setOpaque(true);
			 label_bank_bill.setBackground(Color.WHITE);
		 }else if (e.getSource() == label_bank_modify_pass) {
			 label_bank_modify_pass.setOpaque(true);
			 label_bank_modify_pass.setBackground(Color.WHITE);
		 } 


		 if (e.getSource() == label_blank1) {
			panel_message_info.setVisible(false);
			panel_message_jwc.setVisible(false); 
			panel_message_lib.setVisible(false); 
			panel_message_shop.setVisible(false); 
			panel_message_bank.setVisible(false); 
			contentPane.add(panel_message_info,new Integer(5));
			contentPane.add(panel_message_jwc,new Integer(6));
			contentPane.add(panel_message_lib,new Integer(7));
			contentPane.add(panel_message_shop,new Integer(8));
			contentPane.add(panel_message_bank,new Integer(9));//分别将五个提示面板放置在比10层低的地方
		 }else if (e.getSource() == label_blank2) {

				panel_message_info.setVisible(false);
				panel_message_jwc.setVisible(false); 
				panel_message_lib.setVisible(false); 
				panel_message_shop.setVisible(false); 
				panel_message_bank.setVisible(false); 
				contentPane.add(panel_message_info,new Integer(5));
				contentPane.add(panel_message_jwc,new Integer(6));
				contentPane.add(panel_message_lib,new Integer(7));
				contentPane.add(panel_message_shop,new Integer(8));
				contentPane.add(panel_message_bank,new Integer(9));//分别将五个提示面板放置在比10层低的地方
			}else if (e.getSource() == label_blank3) {
				panel_message_info.setVisible(false);
				panel_message_jwc.setVisible(false); 
				panel_message_lib.setVisible(false); 
				panel_message_shop.setVisible(false); 
				panel_message_bank.setVisible(false); 
				contentPane.add(panel_message_info,new Integer(5));
				contentPane.add(panel_message_jwc,new Integer(6));
				contentPane.add(panel_message_lib,new Integer(7));
				contentPane.add(panel_message_shop,new Integer(8));
				contentPane.add(panel_message_bank,new Integer(9));//分别将五个提示面板放置在比10层低的地方
			}

	}
 
	//==========================================鼠标离开区域==========================================
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
		if (e.getSource() == label_lib_select) {
			label_lib_select.setOpaque(true);
			 label_lib_select.setBackground(new Color(230,230,230));
		 }else if (e.getSource() == label_lib_message) {
			 label_lib_message.setOpaque(true);
			 label_lib_message.setBackground(new Color(230,230,230));
		 }else if (e.getSource() == label_lib_manager) {
			 label_lib_manager.setOpaque(true);
			 label_lib_manager.setBackground(new Color(230,230,230));
		 }
		
		if (e.getSource() == label_jwc_select) {
			 label_jwc_select.setOpaque(true);
			 label_jwc_select.setBackground(new Color(230,230,230));
		 }else if (e.getSource() == label_jwc_curriculum) {
			 label_jwc_curriculum.setOpaque(true);
			 label_jwc_curriculum.setBackground(new Color(230,230,230));
		 }else if (e.getSource() == label_jwc_exam) {
			 label_jwc_exam.setOpaque(true);
			 label_jwc_exam.setBackground(new Color(230,230,230));
		 }else if (e.getSource() == label_jwc_experiment) {
			 label_jwc_experiment.setOpaque(true);
			 label_jwc_experiment.setBackground(new Color(230,230,230));
		 }else if (e.getSource() == label_jwc_manager) {
			 label_jwc_manager.setOpaque(true);
			 label_jwc_manager.setBackground(new Color(230,230,230));
		 }
		
		if (e.getSource() == label_bank_save_withdraw) {
			label_bank_save_withdraw.setOpaque(true);
			label_bank_save_withdraw.setBackground(new Color(230,230,230));
		 }else if (e.getSource() == label_bank_turn_money) {
			 label_bank_turn_money.setOpaque(true);
			 label_bank_turn_money.setBackground(new Color(230,230,230));
		 }else if (e.getSource() == label_bank_bill) {
			 label_bank_bill.setOpaque(true);
			 label_bank_bill.setBackground(new Color(230,230,230));
		 }else if (e.getSource() == label_bank_modify_pass) {
			 label_bank_modify_pass.setOpaque(true);
			 label_bank_modify_pass.setBackground(new Color(230,230,230));
		 }
	}
}
