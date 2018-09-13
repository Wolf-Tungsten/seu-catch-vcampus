package com.wolfTungsten.vcampusClient.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.component.RButton;
import com.wolfTungsten.vcampusClient.component.RButton2;
import com.wolfTungsten.vcampusClient.component.RoundBorder;

import java.awt.Font;
import java.lang.*;
public class LoginUI extends JFrame implements ActionListener, KeyListener,MouseListener, FocusListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	JButton loginButton;
	JButton registerButton;
	JButton exitButton;
	JButton minButton;

	static Point origin = new Point();
	private JTextField textField_card;
	private JPasswordField passwordField;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblNewLabel;
	private JButton btnNewButton;

	public void textSet(JTextField field) {
		field.setOpaque(false);
		field.setBorder(new RoundBorder(Color.LIGHT_GRAY));
	}

	public void passwordTextSet(JPasswordField field) {
		field.setOpaque(false);
		field.setBorder(new RoundBorder(Color.LIGHT_GRAY));
	}

	public void textSet2(JTextField field) {
		field.setBackground(new Color(255, 255, 255));
		field.setPreferredSize(new Dimension(150, 28));
		MatteBorder border = new MatteBorder(0, 0, 2, 0, new Color(192, 192, 192));
		field.setBorder(border);
	}

	public void passwordTextSet2(JPasswordField field) {
		field.setBackground(new Color(255, 255, 255));
		field.setPreferredSize(new Dimension(150, 28));
		MatteBorder border = new MatteBorder(0, 0, 2, 0, new Color(192, 192, 192));
		field.setBorder(border);
	}

	public void buttonSet(JButton button) {
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		// button.setBorderPainted(false);
		// button.setBackground(Color.green);
	}

	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.osLookAndFeelDecorated;
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
					LoginUI frame = new LoginUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the frame.
	public LoginUI() {
		this.setResizable(false);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		loginButton = new RButton("登录");
		loginButton.setBounds(604, 350, 83, 26);
		loginButton.addActionListener(this);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		this.getRootPane().setDefaultButton(this.loginButton);
		loginButton.addKeyListener(this);
		contentPane.add(loginButton);

		registerButton = new RButton2("注册");
		registerButton.setBounds(522, 352, 66, 23);
		registerButton.addActionListener(this);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.add(registerButton);

		exitButton = new RButton2("×");
		exitButton.setBounds(746, 10, 44, 23);
		exitButton.setFont(new Font("微软雅黑", Font.BOLD, 16));
		exitButton.setForeground(Color.WHITE);
		exitButton.addActionListener(this);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonSet(exitButton);
		contentPane.add(exitButton);

		textField_card = new JTextField("用户名/一卡通号");
		textField_card.setForeground(Color.GRAY);
		textField_card.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_card.setBounds(522, 252, 165, 26);
		textSet2(textField_card);
		textField_card.addFocusListener(this);
		contentPane.add(textField_card);
		textField_card.setColumns(10);

		passwordField = new JPasswordField("********");
		passwordField.setForeground(Color.GRAY);
		passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		passwordField.setBounds(522, 298, 165, 26);
		textSet2(passwordField);
		textField_card.addFocusListener(this);
		contentPane.add(passwordField);
		passwordField.setColumns(10);

		JLabel bgLabel = new JLabel();
		bgLabel.setBounds(0, 0, 640, 480);
		URL resource = LoginUI.class.getResource("Beauty.jpg");
		
		if(resource==null)System.out.println("resource为空");
		ImageIcon imageIcon = new ImageIcon(resource);
		bgLabel.setIcon(imageIcon);
		getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
		bgLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
		Container cp = getContentPane();
		cp.setLayout(null);
		((JPanel) cp).setOpaque(false);

		label = new JLabel("\u8EAB\u4EFD\u8BA4\u8BC1\u767B\u5F55");
		label.setBackground(Color.WHITE);
		label.setForeground(new Color(0, 102, 51));
		label.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label.setBounds(522, 204, 108, 26);
		contentPane.add(label);

		label_1 = new JLabel("\u865A\u62DF\u6821\u56ED\u4FE1\u606F\u7CFB\u7EDF");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 44));
		label_1.setBounds(77, 252, 381, 51);
		contentPane.add(label_1);

		lblNewLabel = new JLabel("V1.0.0");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 22));
		lblNewLabel.setBounds(353, 313, 83, 26);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(497, 168, 218, 240);
		URL resource_1 = LoginUI.class.getResource("white.jpg");
		ImageIcon imageIcon_1 = new ImageIcon(resource_1);
		lblNewLabel_1.setIcon(imageIcon_1);
		contentPane.add(lblNewLabel_1);
		
		minButton = new RButton2("-");
		minButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		minButton.setBounds(692, 10, 44, 23);
		minButton.addActionListener(this);
		contentPane.add(minButton);

		//实现鼠标拖拽窗口的功能
				this.addMouseListener(new MouseAdapter(){
			    	public void mousePressed(MouseEvent e) {
			    		origin.x = e.getX();   //记录鼠标按下时的坐标
			    		origin.y = e.getY();
			    	}
			    	//瞎点就会缩小系列，这不就相当于缩小键吗？
//			    	public void mouseClicked(MouseEvent e){
//			    		setExtendedState(JFrame.ICONIFIED);
//			    	}
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
		if(e.getSource()==minButton)
		{		
			setExtendedState(JFrame.ICONIFIED);
		}
		if (e.getActionCommand().equals("×")) {
			 int op = JOptionPane.showConfirmDialog(null,"请问是否要退出系统？", "提示",JOptionPane.YES_NO_OPTION); 
             if(op==JOptionPane.YES_OPTION){  
			         System.exit(0);
             }else {
            	 return;
             }
		}
		if (e.getSource() == loginButton) {
			String userStr = textField_card.getText();
			String passStr = new String(passwordField.getPassword());
			System.out.println(String.format("用户名-%s-密码-%s", userStr, passStr));
			
			// construct request object
			Client.Request request = new Client.Request();
			request.setPath("user/login");
			request.getParams().put("cardnum", userStr);
			request.getParams().put("hash_password", Client.getMD5(passStr));
			
			// fetchs
			Client.Response response = Client.fetch(request);
			System.out.println(String.format("token-%s", (String)response.getBody().get("token")));
			if (response.getSuccess()) {
				this.dispose();
				FunctionFrame frame_1 = new FunctionFrame((String)response.getBody().get("token"));
				frame_1.setVisible(true);
			} else {
				 JOptionPane.showMessageDialog(null, "登录失败，请检查用户名和密码是否正确", "登录失败",JOptionPane.ERROR_MESSAGE);  
			}			
		}
		if (e.getSource() == registerButton) {
			this.hide();
			RegisterFrame register_frame = new RegisterFrame();
			register_frame.setVisible(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void focusGained(FocusEvent e) {
		String userStr = textField_card.getText();
		String passStr = new String(passwordField.getPassword());
		
		if (userStr.equals("用户名/一卡通号")) {
			textField_card.setText("");
		}
		if (passStr.equals("********")) {
			passwordField.setText("");
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		String userStr = textField_card.getText();
		String passStr = new String(passwordField.getPassword());
		/*
		 * if(userStr.equals("")) { textField_card.setText("�û���/һ��ͨ��"); }
		 * if(passStr.equals("")) { passwordField.setText("********"); }
		 */
		if ((userStr.equals("用户名/一卡通号") && passStr.equals("********")) || (userStr.equals("") && passStr.equals(""))) {
			textField_card.setText("用户名/一卡通号");
			passwordField.setText("********");
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		int k=e.getKeyCode();
		if(k==e.VK_ENTER) {
			String userStr = textField_card.getText();
			String passStr = new String(passwordField.getPassword());
			System.out.println(String.format("用户名-%s-密码-%s", userStr, passStr));
			
			// construct request object
			Client.Request request = new Client.Request();
			request.setPath("user/login");
			request.getParams().put("cardnum", userStr);
			request.getParams().put("hash_password", Client.getMD5(passStr));
			
			// fetchs
			Client.Response response = Client.fetch(request);
			System.out.println(String.format("token-%s", (String)response.getBody().get("token")));
			if (response.getSuccess()) {
				this.dispose();
				FunctionFrame frame_1 = new FunctionFrame((String)response.getBody().get("token"));
				frame_1.setVisible(true);
			} else {
				 JOptionPane.showMessageDialog(null, "登录失败，请检查用户名和密码是否正确", "登录失败",JOptionPane.ERROR_MESSAGE);  
			}			
		}
	}

}
