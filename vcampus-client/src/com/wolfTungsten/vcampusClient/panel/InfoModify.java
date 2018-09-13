package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;
import com.wolfTungsten.vcampusClient.component.DateChooserForReg;

public class InfoModify extends JPanel implements ActionListener{
	JButton modifyButton;
	private JTextField textField_cardNum,textField_name,textField_IDnum,textField_birthdate,textField_address;
	private JButton okButton;
	private JButton cancelButton;
	private String token;
	private HashMap<String, Object>userinfo = new HashMap<>();
	/**
	 * Create the panel.
	 */
	public InfoModify(String Token, HashMap<String, Object> Userinfo) {
		token = Token;
		userinfo.putAll(Userinfo);
		setSize(736,600);
		setLayout(null);//绝对布局
		
		modifyButton = new JButton("编辑个人信息");
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		modifyButton.setFont(new Font("微软雅黑", Font.BOLD, 16));
		modifyButton.setBounds(80, 60, 162, 30);
		modifyButton.addActionListener(this);
		add(modifyButton);
		
		JLabel label_cardNum = new JLabel("一卡通号：");
		label_cardNum.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label_cardNum.setBounds(80, 120, 81, 30);
		add(label_cardNum);
		
		JLabel label_name = new JLabel("姓名：");
		label_name.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label_name.setBounds(80, 180, 81, 30);
		add(label_name);
		
		JLabel label_IDnum = new JLabel("身份证号：");
		label_IDnum.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label_IDnum.setBounds(80, 240, 81, 30);
		add(label_IDnum);
		
		JLabel label_birthdate = new JLabel("出生日期：");
		label_birthdate.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label_birthdate.setBounds(80, 300, 81, 30);
		add(label_birthdate);
		
		JLabel label_address = new JLabel("地址：");
		label_address.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label_address.setBounds(80, 360, 81, 30);
		add(label_address);
		
		//“一卡通号”本文框，不可编辑
		textField_cardNum = new JTextField();
		textField_cardNum.setText((String)userinfo.get("cardnum"));
		textField_cardNum.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_cardNum.setBounds(257, 120, 290, 30);
		textField_cardNum.setEditable(false);
		textField_cardNum.setOpaque(false);
		add(textField_cardNum);
		textField_cardNum.setColumns(10);
		//姓名”本文框，可编辑
		textField_name = new JTextField();
		textField_name.setText((String)userinfo.get("username"));
		textField_name.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_name.setBounds(257, 180, 290, 30);
		textField_name.setEditable(false);
		add(textField_name);
		textField_name.setColumns(10);
		//身份证号”本文框，不可编辑
		textField_IDnum = new JTextField();
		textField_IDnum.setText((String)userinfo.get("idcardNum"));
		textField_IDnum.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_IDnum.setBounds(257, 240, 290, 30);
		textField_IDnum.setEditable(false);
		textField_IDnum.setOpaque(false);
		add(textField_IDnum);
		textField_IDnum.setColumns(10);
		//“出生日期”本文框，bu可编辑
		textField_birthdate = new JTextField();
		String date_str = timestampToString((long)(double)userinfo.get("birthdate"), "yyyy-MM-dd");
		textField_birthdate.setText(date_str);
		textField_birthdate.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_birthdate.setBounds(257,300, 290, 30);
		textField_birthdate.setEditable(false);
		textField_birthdate.setOpaque(false);
		add(textField_birthdate);
		textField_birthdate.setColumns(10);
		//“地址”本文框，可编辑
		textField_address = new JTextField();
		textField_address.setText((String)userinfo.get("address"));
		textField_address.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		textField_address.setBounds(257, 360, 290, 30);
		textField_address.setEditable(false);
		add(textField_address);
		textField_address.setColumns(10);
		
		okButton = new JButton("确认修改");
		okButton.setFont(new Font("微软雅黑", Font.BOLD, 16));
		okButton.setBounds(80, 434, 120, 30);
		okButton.addActionListener(this);
		add(okButton);
		
		cancelButton = new JButton("取消");
		cancelButton.setFont(new Font("微软雅黑", Font.BOLD, 16));
		cancelButton.setBounds(427, 434, 120, 30);
		cancelButton.addActionListener(this);
		add(cancelButton);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==modifyButton) {
			//点击“编辑个人信息”
			textField_name.setEditable(true);
//			textField_birthdate.setEditable(true);
			textField_address.setEditable(true);
			
			
		}
		String newNameStr=textField_name.getText();
		String newBirthdateStr=textField_birthdate.getText();
		String newAddressStr=textField_address.getText();
		
		if(e.getSource()==okButton) {
			//点击“确认”
			if(newNameStr.equals("")||newNameStr==null) {
				JOptionPane.showMessageDialog(null, "请输入姓名！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(newAddressStr.equals("")||newAddressStr==null) {
				JOptionPane.showMessageDialog(null, "请输入地址！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}
			int op=JOptionPane.showConfirmDialog(null, "是否保存此次修改？", "Tips", JOptionPane.YES_NO_OPTION);
			{
				if(op==JOptionPane.YES_OPTION) {
					Client.Request request = new Request();
					request.setToken(token);
					request.setPath("user/modifyuserinfo");
					request.getParams().put("username", textField_name.getText());
					request.getParams().put("address", textField_address.getText());
					Response response = Client.fetch(request);
					if(response.getSuccess())
						JOptionPane.showMessageDialog(null, "修改成功", "成功！",JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "修改失败，请联系管理员", "失败！",JOptionPane.ERROR_MESSAGE);
					
					
				}
			}
			
		}else if(e.getSource()==cancelButton) {
			//TODO 怎么恢复原来的数据？
			textField_name.setEditable(false);
			textField_birthdate.setEditable(false);
			textField_address.setEditable(false);
		}
	}
	
	public String timestampToString(long timestamp,String format) {
		try {  
			
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return sdf.format(new Date(timestamp*1000)) ; 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return ""; 
		
	}
}
