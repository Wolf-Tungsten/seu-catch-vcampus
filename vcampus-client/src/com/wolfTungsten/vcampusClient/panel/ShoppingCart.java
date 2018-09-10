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
import java.util.ArrayList;
import java.util.List;

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
     JButton dialogOkButton;
     int gap=120;
     
     private JTextField textField;

   public void NewGoodPanel (JPanel panel,int x,int y,int width,int height,int gap,
    		 JCheckBox checkBox,JLabel label_photo,JLabel label_type,JLabel label_price,JTextField textField_number,
    		 String []result ) {
	          panel.setBounds(x,y+gap,width,height);
	         
	          checkBox.setText(result[0]);//"商品名称"
    		 checkBox.setBounds(x-4, y+35, width-571, height-95);//(6,45+gap,106,25)
    		 panel.add(checkBox);
    		 
    		 label_photo.setText(result[1]);
//    		JLabel label_photo = new JLabel(image);//商品图片
    		 label_photo.setBounds(x+102, y, width-577, height-20);//(140, 10, 100, 100),像素100x100可以不
    		 panel.add(label_photo);

    		 label_type.setText(result[2]);//"（商品类别）"
    		 label_type.setBounds(x+268, y+30, width-550, height-105);//(278, 10, 54, 15)
    		 panel.add(label_type);
    		 
    		 JLabel label_yuan=new JLabel("¥：");
    		 label_yuan.setBounds(x+268,  y+69, width-647, height-105);//(278, 79, 30, 15);
    		 panel.add(label_yuan);
    		 
    		 label_price.setText(result[3]);//"（价格）"
    		 label_price.setBounds(x+298, y+69, width-550, height-105);//(308, 79, 54, 15)
    		 panel.add(label_price);

    		 textField_number.setText(result[4]);//购买数量
    		 textField_number.setBounds(x+503, y+34,width-552, height-99);//(493, 48, 125, 21)
    		 panel.add(textField_number);
    		 textField_number.setColumns(10);
    		
    		 JLabel label_amount=new JLabel("购买数量：");
    		 label_amount.setBounds(x+401,  y+34, width-600, height-97);//(441, 47, 42, 23);
    		 panel.add(label_amount);
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
			 checkBox[i].addActionListener(this);
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
		 panel=new JPanel();
		panel.setLayout(null);
			JScrollPane scrollPane = new JScrollPane(panel,//在滚动面板上加入panel放置商品
	                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
	                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
	    /*
	     * 测试数据
	     */

		String [][]result=new String[][]{{"袁皓东","photo","生活用品","12","1"},{"袁皓西","photo","服饰配件","30","3"}}; //商品名称，商品照片，商品类别（e.g.生活百货），价格，购买数量
		NewGoodPanel(goodPanel[0],10,10,677,120,0,checkBox[0],label_photo[0],label_type[0],label_price[0],textField_number[0],result[0]);
		panel.add(goodPanel[0]);
		 
		 //以这个为例，只要获取这是第几次添加，以及要传输的数据，就可以用这种愚蠢的方式添加商品信息到购物车。。。。。。。
	    int i=1;
        NewGoodPanel(goodPanel[i],10,10,677,120,gap*i,checkBox[i],label_photo[i],label_type[i],label_price[i],textField_number[i],result[i]);
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
			//  计算选中物品多少钱
			double cost=0.0;
			for(int i=0;i<len;i++) {	
				if(checkBox[i].isSelected()) {
					double x =Double.parseDouble(label_price[i].getText());
					double y=Double.parseDouble(textField_number[i].getText());
					cost+=x*y;
				}
			}
			String money=String.valueOf(cost);
			String payPassword="123456";//需要把用户消费密码传递过来
			JPasswordField pwd = new JPasswordField();
			Object[] message = {"本次消费共计"+money+"元\n请输入支付密码：", pwd};
			JOptionPane.showConfirmDialog(null, message, "Tips", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	        String passStr=pwd.getText();//获取输入对话框中的密码
	        //判断消费密码是否正确
			if(passStr.equals(payPassword)) {
				 JOptionPane.showMessageDialog(null, "支付成功！", "Tips",JOptionPane.INFORMATION_MESSAGE);  
			}else if (!passStr.equals(payPassword)&&!passStr.equals("")&&passStr!=null){
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