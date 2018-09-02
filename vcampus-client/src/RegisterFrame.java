//ע��ҳ�棬ѡ����ݣ���дһ��ͨ�����������֤�ţ����գ���ַ������
//����֤���������Ƿ���ͬ
//����δ��дѡ��ʱ������ʾ
//��д�ɹ�������ȷ�ϡ�������ȡ������ص���ת����¼����
//δ�������ݿ�
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class RegisterFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;//���л�ʱ���ְ汾�ļ�����
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
	JRadioButton studentRadioButton = new JRadioButton("ѧ��");
	JRadioButton teacherRadioButton = new JRadioButton("�̹�");
	JButton exitButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		button.setContentAreaFilled(false);//��ť͸��
		button.setFocusPainted(false);
	}
	public RegisterFrame() {
		this.setResizable(false);
		this.setSize(640,480);
		this.setLocationRelativeTo(null);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel cardNumLabel = new JLabel("�û�����");
		cardNumLabel.setForeground(Color.WHITE);
		cardNumLabel.setFont(new Font("΢���ź�", Font.BOLD, 14));
		cardNumLabel.setBounds(88, 95, 76, 15);
		contentPane.add(cardNumLabel);
		
		JLabel identityLabel = new JLabel("������");
		identityLabel.setForeground(Color.WHITE);
		identityLabel.setFont(new Font("΢���ź�", Font.BOLD, 14));
		identityLabel.setBounds(88, 140, 54, 15);
		contentPane.add(identityLabel);
		
		JLabel idCardNumLabel = new JLabel("���֤�ţ�");
		idCardNumLabel.setForeground(Color.WHITE);
		idCardNumLabel.setFont(new Font("΢���ź�", Font.BOLD, 14));
		idCardNumLabel.setBounds(88, 185, 76, 15);
		contentPane.add(idCardNumLabel);
		
		JLabel birthdateLabel = new JLabel("�������ڣ�");
		birthdateLabel.setForeground(Color.WHITE);
		birthdateLabel.setFont(new Font("΢���ź�", Font.BOLD, 14));
		birthdateLabel.setBounds(88, 230, 76, 15);
		contentPane.add(birthdateLabel);
		
		JLabel addressLabel = new JLabel("��ַ��");
		addressLabel.setForeground(Color.WHITE);
		addressLabel.setFont(new Font("΢���ź�", Font.BOLD, 14));
		addressLabel.setBounds(88, 275, 76, 15);
		contentPane.add(addressLabel);
		
		JLabel passwordLabel = new JLabel("���룺");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("΢���ź�", Font.BOLD, 14));
		passwordLabel.setBounds(88, 320, 54, 15);
		contentPane.add(passwordLabel);
		
		JLabel rePassLabel = new JLabel("ȷ�����룺");
		rePassLabel.setForeground(Color.WHITE);
		rePassLabel.setFont(new Font("΢���ź�", Font.BOLD, 14));
		rePassLabel.setBounds(88, 365, 76, 15);
		contentPane.add(rePassLabel);
		
		cardNumField = new JTextField();
		cardNumField.setForeground(Color.WHITE);
		cardNumField.setBounds(200, 95, 140, 21);
		contentPane.add(cardNumField);
		textSet(cardNumField);
		cardNumField.setColumns(10);
		
		identityField = new JTextField();
		identityField.setForeground(Color.WHITE);
		identityField.setBounds(200, 140, 140, 21);
		contentPane.add(identityField);
		textSet(identityField);
		identityField.setColumns(10);
		
		idCardNumField = new JTextField();
		idCardNumField.setForeground(Color.WHITE);
		idCardNumField.setBounds(200, 185, 140, 21);
		contentPane.add(idCardNumField);
		textSet(idCardNumField);
		idCardNumField.setColumns(10);
		
		birthdateField = new JTextField();
		birthdateField.setForeground(Color.WHITE);
		birthdateField.setBounds(200, 230, 140, 21);
		contentPane.add(birthdateField);
		textSet(birthdateField);
		birthdateField.setColumns(10);
		
		addressField = new JTextField();
		addressField.setForeground(Color.WHITE);
		addressField.setBounds(200, 275, 140, 21);
		contentPane.add(addressField);
		textSet(addressField);
		addressField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.WHITE);
		passwordField.setBounds(200, 320, 140, 21);
		contentPane.add(passwordField);
		textSet(passwordField);
		passwordField.setColumns(10);
		
		rePassField = new JPasswordField();
		rePassField.setForeground(Color.WHITE);
		rePassField.setBounds(200,365, 140, 21);
		contentPane.add(rePassField);
		textSet(rePassField);
		rePassField.setColumns(10);
		studentRadioButton.setForeground(Color.WHITE);
		studentRadioButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		//JRadioButton studentRadioButton = new JRadioButton("ѧ��");
		studentRadioButton.setBounds(84, 50, 80, 23);
		contentPane.add(studentRadioButton);
		studentRadioButton.setText("ѧ��");
		studentRadioButton.setContentAreaFilled(false);
		studentRadioButton.setFocusPainted(false);//û�н���
		teacherRadioButton.setForeground(Color.WHITE);
		teacherRadioButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		//JRadioButton teacherRadioButton = new JRadioButton("�̹�");
		teacherRadioButton.setBounds(200, 50, 80, 23);
		contentPane.add(teacherRadioButton);
		teacherRadioButton.setText("�̹�");
		teacherRadioButton.setContentAreaFilled(false);
		teacherRadioButton.setFocusPainted(false);//û�н���
		//teacherRadioButton.setBorderPainted(false);//��ťȥ�߿�
		ButtonGroup privilegeGroup=new ButtonGroup();
		privilegeGroup.add(studentRadioButton);
		privilegeGroup.add(teacherRadioButton);
		studentRadioButton.setSelected(true);
		
		okButton= new RButton("ȷ��");
		okButton.addActionListener(this);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		okButton.setBounds(89,410, 100, 25);
		contentPane.add(okButton);
		
		cancelButton= new RButton("ȡ��");
		cancelButton.addActionListener(this);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancelButton.setBounds(240, 410, 100, 25);
		contentPane.add(cancelButton);
		
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
	    
		//��ʾ��Ϣ�ı�ǩ
		JLabel bgLabel = new JLabel();
		bgLabel.setBounds(0, 0, 640, 480);
		//���ñ���
		URL resource=LoginUI.class.getResource("/Rainbow.JPG");
		ImageIcon imageIcon=new ImageIcon(resource);
		bgLabel.setIcon(imageIcon);
		getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
		bgLabel.setBounds(0,0,imageIcon.getIconWidth(), imageIcon.getIconHeight());//���ñ�����ǩ��λ��  
		Container cp=getContentPane();  
		cp.setLayout(null);   	  
		((JPanel)cp).setOpaque(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okButton) {
			String cardNameStr = cardNumField.getText();
			String identityStr = new String(identityField.getText());
			String idCardNumStr = new String(idCardNumField.getText());
			String birthdateStr = new String(birthdateField.getText());
			String addressStr = new String(addressField.getText());
			String passwordStr = new String(passwordField.getPassword());
			String rePassStr = new String(rePassField.getPassword());
			String studPrivilege=new String(studentRadioButton.getText());
			String teachPrivilege=new String(teacherRadioButton.getText());
			
			if (cardNameStr==null||cardNameStr.equals("")) {
		    	   JOptionPane.showMessageDialog(null, "�û�������Ϊ��Ŷ~", "Tips",JOptionPane.ERROR_MESSAGE);  
		    	   return;
		     }
		    else if (identityStr==null||identityStr.equals("")) {
		    	   JOptionPane.showMessageDialog(null, "��������Ϊ��Ŷ~", "Tips",JOptionPane.ERROR_MESSAGE);  
		    	   return;
			}
		    else if (idCardNumStr==null||idCardNumStr.equals("")) {
		    	   JOptionPane.showMessageDialog(null, "���֤�Ų���Ϊ��Ŷ~", "Tips",JOptionPane.ERROR_MESSAGE);  
		    	   return;
			}
		    else if (birthdateStr==null||birthdateStr.equals("")) {
		    	   JOptionPane.showMessageDialog(null, "�������ڲ���Ϊ��Ŷ~", "Tips",JOptionPane.ERROR_MESSAGE);  
		    	   return;
			}
		    else if (addressStr==null||addressStr.equals("")) {
		    	   JOptionPane.showMessageDialog(null, "��ַ����Ϊ��Ŷ~", "Tips",JOptionPane.ERROR_MESSAGE);  
		    	   return;
			}
			
		    else if (passwordStr==null||passwordStr.equals("")) {
		    	   JOptionPane.showMessageDialog(null, "���벻��Ϊ��Ŷ~", "Tips",JOptionPane.ERROR_MESSAGE);  
		    	   return;
			}
		    else if (rePassStr==null||rePassStr.equals("")) {
		    	   JOptionPane.showMessageDialog(null, "ȷ�����벻��Ϊ��Ŷ~", "Tips",JOptionPane.ERROR_MESSAGE);  
		    	   return;
		    }
		    else if(!passwordStr.equals(rePassStr)) {
		    	 JOptionPane.showMessageDialog(null, "�������������Ҫ��ͬŶ~", "Tips",JOptionPane.ERROR_MESSAGE); 
		    	   return;
			}
		    else {
		    	//System.out.println("��ݣ�"+(studentRadioButton.isSelected()?studentRadioButton.getText():teacherRadioButton.getText()));
		    	this.hide();
				LoginUI login_frame = new LoginUI();
				login_frame.setVisible(true);
		    }
		}
		if(e.getSource() == cancelButton) {
			this.dispose();
			LoginUI login_frame1 = new LoginUI();
			login_frame1.setVisible(true);
		}
		if(e.getActionCommand().equals("��"))
		{
			System.exit(0);
		}
	}
}
