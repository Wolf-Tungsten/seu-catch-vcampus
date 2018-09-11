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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.google.gson.internal.LinkedTreeMap;
import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;
import com.wolfTungsten.vcampusClient.panel.BankBill;
import com.wolfTungsten.vcampusClient.panel.BankModifyPass;
import com.wolfTungsten.vcampusClient.panel.BankNewPassword;
import com.wolfTungsten.vcampusClient.panel.BankSaveAndWithdraw;
import com.wolfTungsten.vcampusClient.panel.BankTurnMoney;
import com.wolfTungsten.vcampusClient.panel.InfoModify;
import com.wolfTungsten.vcampusClient.panel.InfoPassword;
import com.wolfTungsten.vcampusClient.panel.InfoSystemMain;
import com.wolfTungsten.vcampusClient.panel.JwcCurriculum;
import com.wolfTungsten.vcampusClient.panel.JwcExam;
import com.wolfTungsten.vcampusClient.panel.JwcExperiment;
import com.wolfTungsten.vcampusClient.panel.JwcManager;
import com.wolfTungsten.vcampusClient.panel.JwcSelectCourses;
import com.wolfTungsten.vcampusClient.panel.LibFindBooksPanel;
import com.wolfTungsten.vcampusClient.panel.LibManager;
import com.wolfTungsten.vcampusClient.panel.LibMessage;
import com.wolfTungsten.vcampusClient.panel.ShopManager;
import com.wolfTungsten.vcampusClient.panel.ShopSelect;
import com.wolfTungsten.vcampusClient.panel.ShopSellGoods;
import com.wolfTungsten.vcampusClient.panel.ShoppingCart;
import com.wolfTungsten.vcampusClient.panel.ShoppingHistory;

import java.awt.Font;

public class FunctionFrame extends JFrame implements MouseListener{
	private static final long serialVersionUID = 1L;
	private JLayeredPane contentPane;
	JPanel panel_right,panel_message;
	JPanel panel_message_info,panel_message_jwc,panel_message_lib,panel_message_shop,panel_message_bank;
	JButton button_info,button_jwc,button_lib,button_shop,button_bank;	
	
	
	JLabel label_lib_exit,label_jwc_exit;
	JLabel label_blank1,label_blank2,label_blank3;

	//个人信息面板们
	JLabel label_info_main,label_info_modify,label_info_pass;
	JPanel panel_info_main,panel_info_modify,panel_info_pass;
	//图书馆面板们
	JLabel label_lib_select,label_lib_message,label_lib_manager;
	JPanel panel_lib_select,panel_lib_message,panel_lib_manager;
	//教务处面板们
	JPanel panel_jwc_select,panel_jwc_curriculum,panel_jwc_exam,panel_jwc_experiment,panel_jwc_manager;
	JLabel label_jwc_select,label_jwc_curriculum,label_jwc_exam,label_jwc_experiment,label_jwc_manager;
	
	//商城面板们
	JPanel panel_shop_select,panel_shop_cart,panel_shop_sell,panel_shop_history,panel_shop_manager;
	JLabel label_shop_select,label_shop_cart,label_shop_sell,label_shop_history,label_shop_manager;
	//银行面板们
	JPanel panel_bank_save_withdraw,panel_bank_turn_money,panel_bank_bill,panel_bank_modify_pass,panel_bank_newPass;
	JLabel label_bank_save_withdraw,label_bank_turn_money,label_bank_bill,label_bank_modify_pass;
	
	static Point origin = new Point();
	CardLayout cardLayout = new CardLayout();
	String token;
	private JLabel label;
	private JButton NewAccountPassButton;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.osLookAndFeelDecorated;
				org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
					FunctionFrame frame = new FunctionFrame("");
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
	public FunctionFrame(String Token) {
		
		super();
		token=Token;
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
		panel_message_info.setLayout(null);
		//“系统首页”按钮（标签），关联InfoSystemMain面板
		label_info_main=new JLabel("系统首页",JLabel.CENTER);
		label_info_main.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_info_main.setForeground(new Color(59,120,103));
		label_info_main.setBounds(0, 40,150, 50);
		label_info_main.addMouseListener(this);
		panel_message_info.add(label_info_main);
		//“个人信息维护”按钮（标签），关联InfoModify面板
		label_info_modify=new JLabel("个人信息维护",JLabel.CENTER);
		label_info_modify.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_info_modify.setForeground(new Color(59,120,103));
		label_info_modify.setBounds(0, 90,150, 50);
		label_info_modify.addMouseListener(this);
		panel_message_info.add(label_info_modify);
		//“修改登录密码”按钮（标签），关联InfoPassword面板
		label_info_pass=new JLabel("修改登录密码",JLabel.CENTER);
		label_info_pass.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_info_pass.setForeground(new Color(59,120,103));
		label_info_pass.setBounds(0, 140,150, 50);
		label_info_pass.addMouseListener(this);
		panel_message_info.add(label_info_pass);
		
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
		label_lib_manager=new JLabel("图书管理员",JLabel.CENTER);
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
		panel_message_shop.setBackground(new Color(230,230,230));
		panel_message_shop.setLayout(null);
		//“淘乐购“
		label_shop_select=new JLabel("淘乐购",JLabel.CENTER);
		label_shop_select.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_shop_select.setForeground(new Color(59,120,103));
		label_shop_select.setBounds(0, 40,150, 50);
		label_shop_select.addMouseListener(this);
		panel_message_shop.add(label_shop_select);
		//”我的购物车“
		label_shop_cart=new JLabel("购物车",JLabel.CENTER);
		label_shop_cart.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_shop_cart.setForeground(new Color(59,120,103));
		label_shop_cart.setBounds(0, 90,150, 50);
		label_shop_cart.addMouseListener(this);
		panel_message_shop.add(label_shop_cart);
		
		//“闲置换钱”
		label_shop_sell=new JLabel("闲置换钱",JLabel.CENTER);
		label_shop_sell.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_shop_sell.setForeground(new Color(59,120,103));
		label_shop_sell.setBounds(0, 140,150, 50);
		label_shop_sell.addMouseListener(this);
		panel_message_shop.add(label_shop_sell);
		
		//”我的订单“
		label_shop_history=new JLabel("我的订单",JLabel.CENTER);
		label_shop_history.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_shop_history.setForeground(new Color(59,120,103));
		label_shop_history.setBounds(0, 190,150, 50);
		label_shop_history.addMouseListener(this);
		panel_message_shop.add(label_shop_history);
		
		//”商店管理员“
		label_shop_manager=new JLabel("商店管理员",JLabel.CENTER);
		label_shop_manager.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_shop_manager.setForeground(new Color(59,120,103));
		label_shop_manager.setBounds(0, 240,150, 50);
		label_shop_manager.addMouseListener(this);
		panel_message_shop.add(label_shop_manager);
		
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
		panel_info_main = new InfoSystemMain();
		panel_info_main.setBackground(new Color(255, 255, 255));
		panel_right.add("info_1", panel_info_main);		
		cardLayout.show(panel_right, "info_1");//默认显示“系统主页”
//		panel_info_modify = new InfoModify(token);
//		panel_info_modify.setBackground(new Color(255, 255, 255));
//		panel_right.add("info_2", panel_info_modify);		
		
		panel_info_pass = new InfoPassword(token);
		panel_info_pass.setBackground(new Color(255, 255, 255));
		panel_right.add("info_3", panel_info_pass);		
		//图书馆=================================图书馆板块的面板们=======================================
		//”图书检索“面板
		panel_lib_select = new LibFindBooksPanel(token);
		panel_lib_select.setBackground(new Color(255, 255, 255));
		panel_right.add("lib_1", panel_lib_select);
		//“借还信息”面板

		
//		HashMap<String,Object> borrowRecord = borrowRecordrequest(token);
//		String[][] tablevalue = (String[][]) borrowRecord.get("tablevalue");
//		String name = (String) borrowRecord.get("name");
//		String cardnum = (String) borrowRecord.get("cardnum");
//		panel_lib_message = new LibMessage(token,tablevalue,name,cardnum);
//		panel_lib_message.setBackground(new Color(255, 255, 255));
//		panel_right.add("lib_2", panel_lib_message);

		//“管理员”面板
		panel_lib_manager = new LibManager(token);
		panel_lib_manager.setBackground(new Color(255, 255, 255));
		panel_right.add("lib_3", panel_lib_manager);
		
		///教务处================================教务处板块的面板们=======================================
		//教务处============skk四个面板在这里被新建，背景色太丑，你自己改一下哈
//		panel_jwc_select=new JwcSelectCourses(token);
//		panel_jwc_select.setBackground(new Color(255, 255, 255));
//		panel_right.add("jwc_1", panel_jwc_select);		
		
		panel_jwc_curriculum=new JwcCurriculum();
		panel_jwc_curriculum.setBackground(new Color(255, 255, 255));
		panel_right.add("jwc_2", panel_jwc_curriculum);	
		
		panel_jwc_exam=new JwcExam();
		panel_jwc_exam.setBackground(new Color(255, 255, 255));
		panel_right.add("jwc_3", panel_jwc_exam);	
		
		panel_jwc_experiment=new JwcExperiment();
		panel_jwc_experiment.setBackground(new Color(255, 255, 255));
		panel_right.add("jwc_4", panel_jwc_experiment);	
		
		panel_jwc_manager = new JwcManager(token);
		panel_jwc_manager.setBackground(new Color(255, 255, 255));
		panel_right.add("jwc_5", panel_jwc_manager);

	    //购物系统================================购物系统板块的面板们=======================================
		panel_shop_select=new ShopSelect();
		panel_shop_select.setBackground(new Color(255, 255, 255));
		panel_right.add("shop_1", panel_shop_select);		
		
		panel_shop_cart=new ShoppingCart();
		panel_shop_cart.setBackground(new Color(255, 255, 255));
		panel_right.add("shop_2", panel_shop_cart);	
		
		panel_shop_sell=new ShopSellGoods();
		panel_shop_sell.setBackground(new Color(255, 255, 255));
		panel_right.add("shop_3", panel_shop_sell);	
		
		panel_shop_history=new ShoppingHistory();
		panel_shop_history.setBackground(new Color(255, 255, 255));
		panel_right.add("shop_4", panel_shop_history);	
		
		panel_shop_manager=new ShopManager();
		panel_shop_manager.setBackground(new Color(255, 255, 255));
		panel_right.add("shop_5", panel_shop_manager);	
		
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
		
		
		panel_bank_modify_pass=new BankModifyPass(token);
		panel_bank_modify_pass.setBackground(new Color(255, 255, 255));
		panel_right.add("bank_4", panel_bank_modify_pass);
		
		panel_bank_newPass=new BankNewPassword();//若用户没有支付密码，则会提示先设置支付密码
		panel_bank_newPass.setBackground(new Color(255, 255, 255));
		panel_right.add("bank_5", panel_bank_newPass);
		
			
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
	
	@SuppressWarnings({ "deprecation", "null" })
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			if (e.getSource() ==label_info_main) {
				cardLayout.show(panel_right, "info_1");
				HideAllMessagePanel();
/*				cardLayout.show(panel_right, "1");
				panel_message_info.setVisible(true); 
				panel_message_jwc.setVisible(false); 
				panel_message_lib.setVisible(false); 
				panel_message_shop.setVisible(false); 
				panel_message_bank.setVisible(false); 
				contentPane.setLayer(panel_message_info,new Integer(11));//将panel_message_info放置在比10层高的地方
	*/			
			} else if (e.getSource() == label_info_modify) {
				//传用户个人信息
				HashMap<String,Object> userinfo = userinfo(token);
				panel_info_modify = new InfoModify(token,userinfo);
				panel_info_modify.setBackground(new Color(255, 255, 255));
				panel_right.add("info_2", panel_info_modify);	
				
				cardLayout.show(panel_right, "info_2");
				HideAllMessagePanel();	
			} else if (e.getSource() == label_info_pass) {
				cardLayout.show(panel_right, "info_3");
				HideAllMessagePanel();
				
			} else if (e.getSource() == label_lib_select) {
				cardLayout.show(panel_right, "lib_1");
				HideAllMessagePanel();
			} else if (e.getSource() == label_lib_message) {///////////
				HashMap<String,Object> borrowRecord = borrowRecordrequest(token);
				String[][] tablevalue = (String[][]) borrowRecord.get("tablevalue");
				String name = (String) borrowRecord.get("name");
				String cardnum = (String) borrowRecord.get("cardnum");
				panel_lib_message = new LibMessage(token,tablevalue,name,cardnum);
				panel_lib_message.setBackground(new Color(255, 255, 255));
				panel_right.add("lib_2", panel_lib_message);
				cardLayout.show(panel_right, "lib_2");
				HideAllMessagePanel();
				
				
				
			}else if (e.getSource() == label_lib_manager) {
				cardLayout.show(panel_right, "lib_3");
				HideAllMessagePanel();
				
			}else if (e.getSource() == label_jwc_select) {
				HashMap<String,Object> courselist = courseAll(token);
				String[][] tablevalue = (String[][]) courselist.get("tablevalue");	
				String name = (String) courselist.get("name");
				String cardnum = (String) courselist.get("cardnum");
				panel_jwc_select=new JwcSelectCourses(token,tablevalue,name,cardnum);
				panel_jwc_select.setBackground(new Color(255, 255, 255));
				panel_right.add("jwc_1", panel_jwc_select);					
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
				HideAllMessagePanel();
				
			}	else if (e.getSource() == label_shop_select) {
					cardLayout.show(panel_right, "shop_1");
					HideAllMessagePanel();
				} else if (e.getSource() == label_shop_cart) {
					cardLayout.show(panel_right, "shop_2");
					HideAllMessagePanel();
				}  else if (e.getSource() == label_shop_sell) {
					cardLayout.show(panel_right, "shop_3");
					HideAllMessagePanel();
				} else if (e.getSource() == label_shop_history) {
					cardLayout.show(panel_right, "shop_4");
					HideAllMessagePanel();
					
				} else if (e.getSource() == label_shop_manager) {
					cardLayout.show(panel_right, "shop_5");
					HideAllMessagePanel();
					
				}else if (e.getSource() == label_bank_save_withdraw) {
				cardLayout.show(panel_right, "bank_1");
				HideAllMessagePanel();
			} else if (e.getSource() == label_bank_turn_money) {
				cardLayout.show(panel_right, "bank_2");
				HideAllMessagePanel();
			}  else if (e.getSource() == label_bank_bill) {
					String payPassword="123456";
					//TODO 需要把用户消费密码传递过来
					JPasswordField pwd = new JPasswordField();
					Object[] message = {"请输入账号密码：", pwd};
					JOptionPane.showConfirmDialog(null, message, "Tips", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					String passStr=pwd.getText();//获取输入对话框中的密码
					if(passStr.equals(payPassword)) {
						cardLayout.show(panel_right, "bank_3");
						HideAllMessagePanel();
					}else {
						JOptionPane.showMessageDialog(null, "密码错误！", "Tips",JOptionPane.ERROR_MESSAGE); 
				}
			} else if (e.getSource() == label_bank_modify_pass) {
				    String originalPass=null;//TODO 每次点击这个label,要把用户支付密码传递给我
				    if(originalPass!=null) {
						cardLayout.show(panel_right, "bank_4");//如果密码非空，就可以修改
						HideAllMessagePanel();			
				    }else {
				    	cardLayout.show(panel_right, "bank_5");//如果密码是空，就新建密码
						HideAllMessagePanel();			
				    }
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
		 if (e.getSource() == label_info_main) {
			 label_info_main.setOpaque(true);
			 label_info_main.setBackground(Color.WHITE);
		 }else if (e.getSource() == label_info_modify) {
			 label_info_modify.setOpaque(true);
			 label_info_modify.setBackground(Color.WHITE); 
		 }else if (e.getSource() == label_info_pass) {
			 label_info_pass.setOpaque(true);
			 label_info_pass.setBackground(Color.WHITE);
			 
		 }else if (e.getSource() == label_lib_select) {
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
		 
		 if (e.getSource() == label_shop_select) {
			 label_shop_select.setOpaque(true);
			 label_shop_select.setBackground(Color.WHITE);
		 }else if (e.getSource() == label_shop_cart) {
			 label_shop_cart.setOpaque(true);
			 label_shop_cart.setBackground(Color.WHITE);
		 }else if (e.getSource() == label_shop_sell) {
			 label_shop_sell.setOpaque(true);
			 label_shop_sell.setBackground(Color.WHITE);
		 }else if (e.getSource() == label_shop_history) {
			 label_shop_history.setOpaque(true);
			 label_shop_history.setBackground(Color.WHITE);
		 } else if (e.getSource() == label_shop_manager) {
			 label_shop_manager.setOpaque(true);
			 label_shop_manager.setBackground(Color.WHITE);
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
		if (e.getSource() == label_info_main) {
			label_info_main.setOpaque(true);
			label_info_main.setBackground(new Color(230,230,230));
		 }else if (e.getSource() == label_info_modify) {
			 label_info_modify.setOpaque(true);
			 label_info_modify.setBackground(new Color(230,230,230));
		 }else if (e.getSource() == label_info_pass) {
			 label_info_pass.setOpaque(true);
			 label_info_pass.setBackground(new Color(230,230,230));
			 
		 }else if (e.getSource() == label_lib_select) {
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
		
		if (e.getSource() == label_shop_select) {
			label_shop_select.setOpaque(true);
			label_shop_select.setBackground(new Color(230,230,230));
		 }else if (e.getSource() == label_shop_cart) {
			 label_shop_cart.setOpaque(true);
			 label_shop_cart.setBackground(new Color(230,230,230));
		 }else if (e.getSource() == label_shop_sell) {
			 label_shop_sell.setOpaque(true);
			 label_shop_sell.setBackground(new Color(230,230,230));
		 }else if (e.getSource() == label_shop_history) {
			 label_shop_history.setOpaque(true);
			 label_shop_history.setBackground(new Color(230,230,230));
		 }else if (e.getSource() == label_shop_manager) {
			 label_shop_manager.setOpaque(true);
			 label_shop_manager.setBackground(new Color(230,230,230));
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
	
	public static  HashMap<String,Object> borrowRecordrequest(String token){
		HashMap<String,Object> borrowRecord = new HashMap<>();
		Client.Response response = new Response();
		Client.Request request = new Request();
		request.setPath("book/borrowRecord");
		request.setToken(token);
		response = Client.fetch(request);
		ArrayList<LinkedTreeMap<String, Object>> recordMaplist
		=(ArrayList<LinkedTreeMap<String, Object>>) response.getBody().get("recordMaplist");
		int rowcount = recordMaplist.size();
		String[][] tablevalue = new String[rowcount][8];
		for(int i=0;i<rowcount;i++) {
			LinkedTreeMap<String,Object> recordMap = recordMaplist.get(i);
			if(recordMap==null)System.out.println("sssss");
			
			tablevalue[i][0]=(String) recordMap.get("uuid");
			tablevalue[i][1]=(String) recordMap.get("name");
			tablevalue[i][2] = (String) recordMap.get("author");
			tablevalue[i][3] = (String) recordMap.get("publisher");
			long borrowdate = (long)(double)recordMap.get("borrowdate")*1000;
			long returndate = (long)(double)recordMap.get("returndate")*1000;
			long deaddate = (long)(double)recordMap.get("deaddate")*1000;
			int isreturn =(int)(double)recordMap.get("isReturn");
			Date dateb = new Date(borrowdate);
			Date dater = new Date(returndate);
			Date dated = new Date(deaddate);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
			tablevalue[i][4]= df.format(dateb);
			if(returndate!=0) {
				tablevalue[i][5]=df.format(dater);
			}
			else tablevalue[i][5] = "未归还";
			tablevalue[i][6] = df.format(dated);
			
			if ((deaddate - borrowdate)/1000 < 59 * 24 * 3600)
				tablevalue[i][7] = "可续借";
			else
				tablevalue[i][7] = "不可续借";
			if(returndate!=0)tablevalue[i][7]="/";
		}
		borrowRecord.put("tablevalue", tablevalue);
		borrowRecord.put("name", response.getBody().get("username"));
		borrowRecord.put("cardnum", response.getBody().get("cardnum"));
		//String[] columnNames= {"编号","书名","作者","出版社","借阅时间","归还时间","到期时间","续借状态"}		
		return borrowRecord;
		
	}
	
	public static HashMap<String,Object> courseAll(String token) {
		HashMap<String,Object> cao = new HashMap<>();
		Client.Request request = new Request();
		request.setPath("EduAdmin/queryAllCourse");
		request.setToken(token);
		Response response = Client.fetch(request);
		ArrayList<LinkedTreeMap<String, Object>> coursemaplist = 
				(ArrayList<LinkedTreeMap<String, Object>>) response.getBody().get("courseMaplist");
		int row = coursemaplist.size();
		String[][] valuetable = new String[row][6];
		//private String[] columnNames = {"课程名称","任课老师","上课地点","上课时间","  ","   "}
		for(int i= 0;i<row;i++) {
			LinkedTreeMap<String, Object> coursemap = coursemaplist.get(i);
			valuetable[i][0] = (String) coursemap.get("name");
			valuetable[i][1] =(String) coursemap.get("lecturer");
			valuetable[i][2] =(String) coursemap.get("location");
			valuetable[i][3] = (String)coursemap.get("classtime");
			valuetable[i][4] = (String)coursemap.get(" ");
			valuetable[i][5] = (String)coursemap.get("   ");
						
		}
		cao.put("tablevalue", valuetable);
		cao.put("name", response.getBody().get("username"));
		cao.put("cardnum", response.getBody().get("cardnum"));
		
		return cao;
		
	}
	
	public static HashMap<String, Object> userinfo(String token) {
		Client.Request request = new Request();
		request.setToken(token);
		request.setPath("user/userinfo");
		Response response = Client.fetch(request);

		if(response.getSuccess())
			return response.getBody();
		else
			return new HashMap<>();
	
	}
	
}
