//��¼ҳ��
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.Font;

public class LoginUI extends JFrame implements ActionListener,MouseListener,FocusListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	JButton loginButton;
	JButton registerButton;
	JButton exitButton;
    //RButton registerButton;  
    
	static Point origin=new Point();
	private JTextField textField_card;
	private JTextField textField_pass;
	private JPasswordField passwordField;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblNewLabel;
	//��д�ı���,Բ��
		public void textSet (JTextField field) {
			field.setOpaque(false);
			field.setBorder(new RoundBorder(Color.LIGHT_GRAY));
		}
		public void passwordTextSet (JPasswordField field) {
			field.setOpaque(false);
			field.setBorder(new RoundBorder(Color.LIGHT_GRAY));
		}
		//��д�ı���,ֻ����
		public void textSet2(JTextField field) {
			field.setBackground(new Color(255, 255, 255));
			field.setPreferredSize(new Dimension(150, 28));
			MatteBorder border = new MatteBorder(0, 0, 2, 0, new Color(192, 192,192));
			field.setBorder(border);
		}
		public void passwordTextSet2(JPasswordField field) {
			field.setBackground(new Color(255, 255, 255));
			field.setPreferredSize(new Dimension(150, 28));
			MatteBorder border = new MatteBorder(0, 0, 2, 0, new Color(192, 192,192));
			field.setBorder(border);
		}
	//��д��ť
		public void buttonSet(JButton button) {
			button.setContentAreaFilled(false);//��ť͸��
			button.setFocusPainted(false);//û�н���
			//button.setBorderPainted(false);//��ťȥ�߿�
			//button.setBackground(Color.green);//��ť���ñ���ɫ
		}
	
	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 //Create the frame.
	public LoginUI() {
		this.setResizable(false);
		this.setSize(640,480);
		this.setLocationRelativeTo(null);//����
		setUndecorated(true);//ȡ����������Ч��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//��¼��ť
		loginButton= new RButton("��¼");
		loginButton.setBounds(468, 304, 83, 26);
		loginButton.addActionListener(this);//����
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.add(loginButton);
		
		//ע�ᰴť
		registerButton= new RButton2("ע��");
		registerButton.setBounds(386, 306, 66, 23);
		registerButton.addActionListener(this);//����
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.add(registerButton);
		
		//�˳���ť
	    exitButton = new JButton("\u00D7");
	    exitButton.setBounds(564, 10, 66, 23);
		exitButton.setFont(new Font("΢���ź�", Font.BOLD, 16));
		exitButton.setForeground(Color.WHITE);
		exitButton.addActionListener(this);
		exitButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			}
		});
		buttonSet(exitButton);//��ť͸��
		contentPane.add(exitButton);
		
		//�û��������ı���
		textField_card = new JTextField("�û���/һ��ͨ��");
		textField_card.setForeground(Color.GRAY);
		textField_card.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		textField_card.setBounds(386, 206, 165, 26);
		textSet2(textField_card);
		textField_card.addFocusListener (this);
		contentPane.add(textField_card);
		textField_card.setColumns(10);
		
		//���������ı���
		passwordField = new JPasswordField("********");
		passwordField.setForeground(Color.GRAY);
		passwordField.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		passwordField.setBounds(386, 252, 165, 26);
		textSet2(passwordField);
		textField_card.addFocusListener (this);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JLabel bgLabel = new JLabel();
		bgLabel.setBounds(0, 0, 640, 480);
		//���ñ���
		URL resource=LoginUI.class.getResource("/BeautifulStars.JPG");
		ImageIcon imageIcon=new ImageIcon(resource);
		bgLabel.setIcon(imageIcon);
		getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
		bgLabel.setBounds(0,0,imageIcon.getIconWidth(), imageIcon.getIconHeight());//���ñ�����ǩ��λ��  
		Container cp=getContentPane();  
		cp.setLayout(null);   	  
		((JPanel)cp).setOpaque(false);
		
		label = new JLabel("\u8EAB\u4EFD\u8BA4\u8BC1\u767B\u5F55");
		label.setBackground(Color.WHITE);
		label.setForeground(new Color(0, 102, 51));
		label.setFont(new Font("΢���ź�", Font.BOLD, 16));
		label.setBounds(386, 158, 108, 26);
		contentPane.add(label);
		
		label_1 = new JLabel("\u865A\u62DF\u6821\u56ED\u4FE1\u606F\u7CFB\u7EDF");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("΢���ź�", Font.BOLD, 30));
		label_1.setBounds(61, 200, 249, 51);
		contentPane.add(label_1);
		
		lblNewLabel = new JLabel("V1.0.0");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("΢���ź�", Font.BOLD, 12));
		lblNewLabel.setBounds(247, 257, 48, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(361, 118, 218, 240);
		URL resource_1=LoginUI.class.getResource("/white.JPG");
		ImageIcon imageIcon_1=new ImageIcon(resource_1);
		lblNewLabel_1.setIcon(imageIcon_1);
		contentPane.add(lblNewLabel_1);
		
		//����ƶ�
		addMouseListener((MouseListener) new MouseAdapter() {
			public void mousePresseded(MouseEvent e)
			{
				origin.x=e.getX();
				origin.y=e.getY();
			}
		});
		
		addMouseMotionListener((MouseMotionListener) new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				Point p=getLocation();
				setLocation(p.x+e.getX()-origin.x,p.y+e.getY()-origin.y);
			}
		});
		
	}
	
	    //��X����ť��Ӧ�¼�
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("��"))
			{
				System.exit(0);
			}
			if(e.getSource() == loginButton) {
				String userStr = textField_card.getText();
				String passStr=new String(passwordField.getPassword());
				//��������!!!!
				if(textField_card.getText().equals("111")&&String.valueOf(passwordField.getPassword()).equals("111")) {
					this.dispose();
					FunctionFrame frame_1 = new FunctionFrame();
					frame_1.setVisible(true);
				}	
			}
			if(e.getSource() == registerButton) {
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
 
	// �����
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	// ����˳�
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	

     @Override
     public void focusGained(FocusEvent e)
     {
    	 String userStr = textField_card.getText();
		 String passStr=new String(passwordField.getPassword());
		 if(userStr.equals("�û���/һ��ͨ��")) {
			 textField_card.setText("");
		 }
		 if(passStr.equals("********")) {
        	passwordField.setText("");
		 }
		
     }
    @Override
     public void focusLost(FocusEvent e)
     {
    	String userStr = textField_card.getText();
		String passStr=new String(passwordField.getPassword());
		/*if(userStr.equals("")) {
			 textField_card.setText("�û���/һ��ͨ��");
		 }
		 if(passStr.equals("")) {
        	passwordField.setText("********");
		 }*/
		if((userStr.equals("�û���/һ��ͨ��")&& passStr.equals("********"))||(userStr.equals("")&& passStr.equals(""))){
			textField_card.setText("�û���/һ��ͨ��");
        	passwordField.setText("********");
		}
     }
    
}
