//购物车面板，上限是120.和淘宝一样，您怎么看？
package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ShoppingCart extends JPanel implements ActionListener{
     JPanel panel;
     JButton payButton,cancelButton;
     int len=119;
     JPanel []goodPanel = new JPanel[len];
     JCheckBox []checkBox=new JCheckBox[len];
     JLabel []label_photo=new JLabel[len];
     JLabel []label_type=new JLabel[len];
     JLabel []label_price=new JLabel[len];
     JTextField []textField_number=new JTextField[len];
     JButton []button_add=new JButton[len];
     JButton []button_sub=new JButton[len];
     JButton dialogOkButton;
     int gap=120;
     
     private JTextField textField;

   public void NewGoodPanel (JPanel panel,int x,int y,int width,int height,int gap,
    		 JCheckBox checkBox,JLabel label_photo,JLabel label_type,JLabel label_price,JTextField textField_number,
    		 String goodName,String photo,String goodType,String price,String amount ) {
	   
	          panel.setBounds(x,y+gap,width,height);
	         
             checkBox.setText(goodName);//"商品名称"
    		 checkBox.setBounds(x-4, y+35, width-571, height-95);//(6,45+gap,106,25)
    		 panel.add(checkBox);

//    		JLabel label_photo = new JLabel(image);//商品图片
    		 label_photo.setText(photo);
    		 label_photo.setBounds(x+102, y, width-577, height-20);//(140, 10, 100, 100),像素100x100可以不
    		 panel.add(label_photo);

    		 label_type.setText(goodType);//"（商品类别）"
    		 label_type.setBounds(x+268, y+30, width-550, height-105);//(278, 10, 54, 15)
    		 panel.add(label_type);

    		 label_price.setText("¥："+price);//"（价格）"
    		 label_price.setBounds(x+268, y+69, width-550, height-105);//(278, 79, 54, 15)
    		 panel.add(label_price);

    		 textField_number.setText(amount);//购买数量
    		 textField_number.setBounds(x+503, y+34,width-611, height-99);//(493, 48, 66, 21)
    		 panel.add(textField_number);
    		 textField_number.setColumns(10);
    		
    		 JLabel label=new JLabel("购买数量");
    		 label.setBounds(x+401,  y+34, width-600, height-97);//(441, 47, 42, 23);
    		 panel.add(label);
    		 }


	public ShoppingCart() {
		setSize(736,600);
		setLayout(null);//绝对布局
		
		 for (int i = 0; i < goodPanel.length; i++) {
			      goodPanel[i] = new JPanel();
			      goodPanel[i].setLayout(null);
		  }
		 for (int i = 0; i < checkBox.length; i++) {
			 checkBox[i] = new JCheckBox();
	     }
		 for (int i = 0; i < label_photo.length; i++) {
			 label_photo[i] = new JLabel();
	     }
		 for (int i = 0; i < label_type.length; i++) {
			 label_type[i] = new JLabel();
	     }
		 for (int i = 0; i < label_price.length; i++) {
			 label_price[i] = new JLabel();
	     }
		 for (int i = 0; i < textField_number.length; i++) {
			 textField_number[i] = new JTextField();
	     }
		 for (int i = 0; i < button_add.length; i++) {
			 button_add[i] = new JButton();
	     }
		 for (int i = 0; i < button_sub.length; i++) {
			 button_sub[i] = new JButton();
	     }
		 panel=new JPanel();
		panel.setLayout(null);
			JScrollPane scrollPane = new JScrollPane(panel,//在滚动面板上加入panel放置商品
	                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
	                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
	    /*
	     * 测试数据
	     */
		 NewGoodPanel(goodPanel[0],10,10,677,120,0,checkBox[0],label_photo[0],label_type[0],label_price[0],textField_number[0],
				 "yhd","photo","aaa","12","1"); //商品名称，商品照片，商品类别（e.g.生活百货），价格，购买数量
		 panel.add(goodPanel[0]);
		 
		 NewGoodPanel(goodPanel[1],10,10,677,120,gap*1,checkBox[1],label_photo[1],label_type[1],label_price[1],textField_number[1],
				 "yhx","photo","aaa","10","0"); 
		 panel.add(goodPanel[1]);
		 //以这个为例，只要获取这是第几次添加，以及要传输的数据，就可以用这种愚蠢的方式添加商品信息到购物车。。。。。。。
		 int i=2;
		 NewGoodPanel(goodPanel[i],10,10,677,120,gap*i,checkBox[i],label_photo[i],label_type[i],label_price[i],textField_number[i],
				 "yhn","photo","ccc","9","2"); 
		 panel.add(goodPanel[i]);
		 
		 i++;
		 NewGoodPanel(goodPanel[i],10,10,677,120,gap*i,checkBox[i],label_photo[i],label_type[i],label_price[i],textField_number[i],
				 "yhn","photo","ccc","9","2"); 
		 panel.add(goodPanel[i]);
		 
		 panel.setPreferredSize(new Dimension(716,130+gap*i));//panel的高度必须高于滚动面板
		
	
		scrollPane.setBounds(10, 35, 716, 490);
		
		add(scrollPane);
		
		JLabel label = new JLabel("我的购物车");
		label.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label.setBounds(10, 10, 91, 15);
		add(label);
		
		payButton = new JButton("结算清单");
		payButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		payButton.addActionListener(this);
		payButton.setBounds(613, 544, 93, 27);
		add(payButton);
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// 点击“结算清单”按钮
		if(e.getSource()==payButton) {
			// TODO 计算。。。。。。
			String money=null;
			String pass="123456";
			JPasswordField pwd = new JPasswordField();

			Object[] message = {"本次消费共计"+money+"元\n请输入支付密码：", pwd};

			JOptionPane.showConfirmDialog(null, message, "Tips", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	        String passStr=pwd.getText();
			if(passStr.equals(pass)) {
				 JOptionPane.showMessageDialog(null, "支付成功！", "Tips",JOptionPane.INFORMATION_MESSAGE);  
			}else if (!passStr.equals(pass)&&!passStr.equals("")&&passStr!=null){
				JOptionPane.showMessageDialog(null, "支付密码错误！", "Tips",JOptionPane.ERROR_MESSAGE);  
			}else if (passStr.equals("")||passStr==null){
				JOptionPane.showMessageDialog(null, "支付失败！", "Tips",JOptionPane.ERROR_MESSAGE);  
			}
		}
			
	}


	


	
}
/*	  public void NewPanel(JPanel paneltext,JLabel labeltext,String name,int gap) {
paneltext.setBounds(10,10+gap,160,100);

labeltext.setText(name);
labeltext.setBounds(10, 10, 80, 20);
paneltext.add(labeltext);
}
NewPanel(goodPanel[2],label_type[2],"yhd",0);
panel.add(goodPanel[2]);
NewPanel(goodPanel[3],label_type[3],"yhd",100);
panel.add(goodPanel[3]);
NewPanel(goodPanel[4],label_type[4],"yhn",200);
panel.add(goodPanel[4]);
NewPanel(goodPanel[5],label_type[5],"yhb",300);
panel.add(goodPanel[5]);
NewPanel(goodPanel[6],label_type[6],"yhm",400);
panel.add(goodPanel[6]);
NewPanel(goodPanel[7],label_type[7],"yha",500);
panel.add(goodPanel[7]);
NewPanel(goodPanel[8],label_type[8],"yha",600);
panel.add(goodPanel[8]);
panel.setPreferredSize(new Dimension(716,1000));
*/ 