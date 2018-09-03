package com.wolfTungsten.vcampusClient.frame;
//真的主面板
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

public class FunctionFrame extends JFrame implements MouseListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JPanel panel_right;
	JPanel panel_info,panel_jwc,panel_lib,panel_shop,panel_bank;
	JButton button_info,button_jwc,button_lib,button_shop,button_bank;	
	
	
		
	
	
	
	
	
	CardLayout cardLayout = new CardLayout();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		//个人信息
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
		
		panel_right = new JPanel(cardLayout);
		panel_right.setBounds(64, 0, 736, 600);
		contentPane.add(panel_right);
		
		panel_info = new JPanel();
		panel_info.setBackground(new Color(255, 255, 204));
		panel_right.add("1", panel_info);
		
		panel_jwc=new JPanel();
		panel_jwc.setBackground(new Color(255, 255, 153));
		panel_right.add("2", panel_jwc);
		
		//
		panel_lib = new JPanel();
		panel_lib.setBackground(new Color(153, 255, 255));
		panel_right.add("3", panel_lib);
		
		//�·�����Ҳ�ġ����С����
		panel_shop = new JPanel();
		panel_shop.setBackground(new Color(255, 204, 255));
		panel_right.add("4", panel_shop);
		
		//�·�����Ҳ�ġ�ͼ��ݡ����
		panel_bank = new JPanel();
		panel_bank.setBackground(Color.WHITE);
		panel_right.add("5", panel_bank);
		
		cardLayout.show(panel_right, "1");
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			if (e.getSource() ==button_info) {
				cardLayout.show(panel_right, "1");
			} else if (e.getSource() == button_jwc) {
				cardLayout.show(panel_right, "2");
			} else if (e.getSource() == button_lib) {
				cardLayout.show(panel_right, "3");
			} else if (e.getSource() == button_shop) {
				 cardLayout.show(panel_right, "4");
			}else if (e.getSource() == button_bank) {
				 cardLayout.show(panel_right, "5");
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
		}else if(e.getSource() == button_lib){
			button_lib.setIcon(imageIcon_lib_active);
			contentPane.add(button_lib);
		}else if (e.getSource() == button_jwc) {
			button_jwc.setIcon(imageIcon_jwc_active);
			contentPane.add(button_jwc);
		}else if (e.getSource() == button_shop) {
			button_shop.setIcon(imageIcon_shop_active);
			contentPane.add(button_shop);
		}else if (e.getSource() == button_bank) {
			button_bank.setIcon(imageIcon_bank_active);
			contentPane.add(button_bank);
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
