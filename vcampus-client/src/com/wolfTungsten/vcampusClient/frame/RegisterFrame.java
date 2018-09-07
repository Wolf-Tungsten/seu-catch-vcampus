package com.wolfTungsten.vcampusClient.frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.component.DateChooser;
import com.wolfTungsten.vcampusClient.component.DateChooserForReg;
import com.wolfTungsten.vcampusClient.component.RButton;
import com.wolfTungsten.vcampusClient.component.RoundBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Point;

public class RegisterFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	static Point origin = new Point();
	private JPanel contentPane;
	private JTextField cardNumField;
	private JTextField identityField;
	private JTextField idCardNumField;
	private JTextField birthdateField;
	private JTextField addressField;
	private JPasswordField passwordField;
	private JPasswordField rePassField;
	JButton okButton;
	JButton cancelButton;
	JRadioButton studentRadioButton = new JRadioButton("学生");
	JRadioButton teacherRadioButton = new JRadioButton("教工");
	JButton exitButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.osLookAndFeelDecorated;
				    org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
					RegisterFrame frame = new RegisterFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//��д�ı���
	public void textSet (JTextField field) {
		field.setOpaque(false);
		field.setBorder(new RoundBorder(Color.LIGHT_GRAY));
	}
	/**
	 * Create the frame.
	 */
	public void buttonSet(JButton button) {
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
	}
	public RegisterFrame() {
		this.setResizable(false);
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel cardNumLabel = new JLabel("一卡通号：");
		cardNumLabel.setForeground(Color.WHITE);
		cardNumLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		cardNumLabel.setBounds(88, 95, 76, 15);
		contentPane.add(cardNumLabel);
		
		JLabel identityLabel = new JLabel("姓名：");
		identityLabel.setForeground(Color.WHITE);
		identityLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		identityLabel.setBounds(88, 140, 54, 15);
		contentPane.add(identityLabel);
		
		JLabel idCardNumLabel = new JLabel("身份证号：");
		idCardNumLabel.setForeground(Color.WHITE);
		idCardNumLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		idCardNumLabel.setBounds(88, 185, 76, 15);
		contentPane.add(idCardNumLabel);
		
		JLabel birthdateLabel = new JLabel("出生日期：");
		birthdateLabel.setForeground(Color.WHITE);
		birthdateLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		birthdateLabel.setBounds(88, 230, 76, 15);
		contentPane.add(birthdateLabel);
		
		JLabel addressLabel = new JLabel("地址：");
		addressLabel.setForeground(Color.WHITE);
		addressLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		addressLabel.setBounds(88, 275, 76, 15);
		contentPane.add(addressLabel);
		
		JLabel passwordLabel = new JLabel("密码：");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		passwordLabel.setBounds(88, 320, 54, 15);
		contentPane.add(passwordLabel);
		
		JLabel rePassLabel = new JLabel("确认密码：");
		rePassLabel.setForeground(Color.WHITE);
		rePassLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		rePassLabel.setBounds(88, 365, 76, 15);
		contentPane.add(rePassLabel);
		
		cardNumField = new JTextField();
		cardNumField.setForeground(Color.WHITE);
		cardNumField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		cardNumField.setBounds(200, 95, 140, 21);
		contentPane.add(cardNumField);
		textSet(cardNumField);
		cardNumField.setColumns(10);
		
		
		identityField = new JTextField();
		identityField.setOpaque(false);
		identityField.setForeground(Color.WHITE);
		identityField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		identityField.setBounds(200, 140, 140, 21);
		contentPane.add(identityField);
		textSet(identityField);
		identityField.setColumns(10);
		
		idCardNumField = new JTextField();
		idCardNumField.setForeground(Color.WHITE);
		idCardNumField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		idCardNumField.setBounds(200, 185, 140, 21);
		contentPane.add(idCardNumField);
		textSet(idCardNumField);
		idCardNumField.setColumns(10);
		
		DateChooserForReg dateChooser = DateChooserForReg.getInstance("yyyy-MM-dd");
		birthdateField = new JTextField();
		birthdateField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		birthdateField.setBounds(200, 230, 140, 21);
		dateChooser.register(birthdateField);
		textSet(birthdateField);	
		contentPane.add(birthdateField);
		
		addressField = new JTextField();
		addressField.setForeground(Color.WHITE);
		addressField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		addressField.setBounds(200, 275, 140, 21);
		contentPane.add(addressField);
		textSet(addressField);
		addressField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.WHITE);
		passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		passwordField.setBounds(200, 320, 140, 21);
		contentPane.add(passwordField);
		textSet(passwordField);
		passwordField.setColumns(10);
		
		rePassField = new JPasswordField();
		rePassField.setForeground(Color.WHITE);
		rePassField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		rePassField.setBounds(200,365, 140, 21);
		contentPane.add(rePassField);
		textSet(rePassField);
		rePassField.setColumns(10);
		studentRadioButton.setForeground(Color.WHITE);
		studentRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentRadioButton.setBounds(84, 50, 80, 23);
		contentPane.add(studentRadioButton);
		studentRadioButton.setText("学生");
		studentRadioButton.setContentAreaFilled(false);
		studentRadioButton.setFocusPainted(false);
		teacherRadioButton.setForeground(Color.WHITE);
		teacherRadioButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		teacherRadioButton.setBounds(200, 50, 80, 23);
		contentPane.add(teacherRadioButton);
		teacherRadioButton.setText("教工");
		teacherRadioButton.setContentAreaFilled(false);
		teacherRadioButton.setFocusPainted(false);

		ButtonGroup privilegeGroup=new ButtonGroup();
		privilegeGroup.add(studentRadioButton);
		privilegeGroup.add(teacherRadioButton);
		studentRadioButton.setSelected(true);
		
		okButton= new RButton("确认");
		okButton.addActionListener(this);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		okButton.setBounds(89,410, 100, 25);
		contentPane.add(okButton);
		
		cancelButton= new RButton("取消");
		cancelButton.addActionListener(this);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancelButton.setBounds(240, 410, 100, 25);
		contentPane.add(cancelButton);
		
		exitButton = new JButton("×");
	    exitButton.setBounds(724, 10, 66, 23);
		exitButton.setFont(new Font("微软雅黑", Font.BOLD, 16));
		exitButton.setForeground(Color.WHITE);
		exitButton.addActionListener(this);
		exitButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			}
		});
		buttonSet(exitButton);
		contentPane.add(exitButton);
	    

		JLabel bgLabel = new JLabel();
		bgLabel.setBounds(0, 0, 640, 480);
		URL resource=LoginUI.class.getResource("Rainbow.JPG");
		ImageIcon imageIcon=new ImageIcon(resource);
		bgLabel.setIcon(imageIcon);
		getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
		bgLabel.setBounds(0,0,imageIcon.getIconWidth(), imageIcon.getIconHeight());
		Container cp=getContentPane();  
		cp.setLayout(null);   	  
		((JPanel)cp).setOpaque(false);
		
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okButton) {
			String cardNameStr = new String(cardNumField.getText());
			String identityStr = new String(identityField.getText());
			String idCardNumStr = new String(idCardNumField.getText());
			//String birthdateStr = new String(birthdateField.getText());
			String addressStr = new String(addressField.getText());
			String passwordStr = new String(passwordField.getPassword());
			String rePassStr = new String(rePassField.getPassword());
			String studPrivilege = new String(studentRadioButton.getText());
			String teachPrivilege = new String(teacherRadioButton.getText());
			
			if (cardNameStr==null||cardNameStr.equals("")) {
		    	   JOptionPane.showMessageDialog(null, "一卡通号不可为空~", "Tips",JOptionPane.ERROR_MESSAGE);  
		    	   return;
		     }
		    else if (identityStr==null||identityStr.equals("")) {
		    	   JOptionPane.showMessageDialog(null, "姓名不可为空~", "Tips",JOptionPane.ERROR_MESSAGE);  
		    	   return;
			}
		    else if (idCardNumStr==null||idCardNumStr.equals("")) {
		    	   JOptionPane.showMessageDialog(null, "身份证号不可为空~", "Tips",JOptionPane.ERROR_MESSAGE);  
		    	   return;
			}
		    else if (addressStr==null||addressStr.equals("")) {
		    	   JOptionPane.showMessageDialog(null, "地址不可为空~", "Tips",JOptionPane.ERROR_MESSAGE);  
		    	   return;
			}
			
		    else if (passwordStr==null||passwordStr.equals("")) {
		    	   JOptionPane.showMessageDialog(null, "密码不可为空~", "Tips",JOptionPane.ERROR_MESSAGE);  
		    	   return;
			}
		    else if (rePassStr==null||rePassStr.equals("")) {
		    	   JOptionPane.showMessageDialog(null, "确认密码不可为空~", "Tips",JOptionPane.ERROR_MESSAGE);  
		    	   return;
		    }
		    else if(!passwordStr.equals(rePassStr)) {
		    	 JOptionPane.showMessageDialog(null, "两次输入密码不一致~", "Tips",JOptionPane.ERROR_MESSAGE); 
		    	   return;
			}
		    else {
		    	Client.Request request = new Client.Request();
		    	request.setPath("user/register");
		    	request.getParams().put("username", identityStr);
		    	request.getParams().put("cardnum", cardNameStr);
		    	request.getParams().put("hash_password", Client.getMD5(passwordStr));
		    	request.getParams().put("photo", "");
		    	if (studentRadioButton.isSelected()) {
		    		request.getParams().put("privilege", 0);
		    		request.getParams().put("identity", 0);
		    	} else {
		    		request.getParams().put("privilege", 1);
		    		request.getParams().put("identity", 1);
		    	}
		    	request.getParams().put("address", addressStr);
		    	request.getParams().put("idcardNum", idCardNumStr);

		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		        // 指定一个日期
		        try {
					Date date = dateFormat.parse(idCardNumStr.substring(6, 13));
					request.getParams().put("birthdate", date.getTime() / 1000);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        Client.Response response = Client.fetch(request);
		        
		        if (response.getSuccess()) {
		        	// 注册成功
		        	JOptionPane.showMessageDialog(null, "注册成功，将跳转到登录界面", "提示", JOptionPane.DEFAULT_OPTION);  
		        	this.setVisible(false);
					LoginUI login_frame = new LoginUI();
					login_frame.setVisible(true);
		        } else {
		        	 JOptionPane.showMessageDialog(null, "请检查网络及服务器状态", "注册失败", JOptionPane.ERROR_MESSAGE);  
		        }
		        
		    	
		    }
		}
		if(e.getSource() == cancelButton) {
			this.dispose();
			LoginUI login_frame1 = new LoginUI();
			login_frame1.setVisible(true);
		}
		if(e.getActionCommand().equals("×"))
		{
			System.exit(0);
		}
	}
}
