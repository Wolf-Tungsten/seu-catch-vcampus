//购物车面板，上限是120.和淘宝一样，您怎么看？
package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ShoppingCart extends JPanel {
     JPanel panel;
     JButton payButton,cancelButton;
     int len=119;
     JPanel []goodPanel = new JPanel[len];
    
     JCheckBox []checkBox=new JCheckBox[120];
     JLabel []label_photo=new JLabel[120];
     JLabel []label_type=new JLabel[120];
     JLabel []label_price=new JLabel[120];
     JTextField []textField_number=new JTextField[120];
     JButton []button_add=new JButton[120];
     JButton []button_sub=new JButton[120];
     
     JCheckBox checkBox1=new JCheckBox();
     JLabel label_photo1=new JLabel();
     JLabel label_type1=new JLabel();
     JLabel label_price1=new JLabel();
     JTextField textField_number1=new JTextField();
     JButton button_add1=new JButton();
     JButton button_sub1=new JButton();
     int high_1=20;//商品图片、类别
     int high_2=44;//商品名称
     int high_3=76;//商品价格
     int high_4=44;//商品数量，加、减
     int gap=120;
     
     private JTextField textField;




    
	/**
	 * Create the panel.
	 */
	public ShoppingCart() {
		setSize(736,600);
		setLayout(null);//绝对布局
		
		panel=new JPanel();
		panel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(panel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		 for (int i = 0; i < goodPanel.length; i++) {
			      goodPanel[i] = new JPanel();
			      goodPanel[i].setLayout(null);
		  }
		
		
		scrollPane.setBounds(10, 35, 716, 490);
		add(scrollPane);
		
		JLabel label = new JLabel("我的购物车");
		label.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label.setBounds(10, 10, 91, 15);
		add(label);
		
		payButton = new JButton("结算清单");
		payButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		payButton.setBounds(392, 545, 93, 27);
		add(payButton);
		
		JButton btnNewButton = new JButton("返回购物");
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		btnNewButton.setBounds(578, 548, 93, 27);
		add(btnNewButton);
	}
}
/*  public void NewGoodPanel (JPanel panel,int x,int y,int width,int height,int gap,
JCheckBox checkBox,JLabel label_photo,JLabel label_type,JLabel label_price,JTextField textField_number,
JButton button_add,JButton button_sub,
String goodName,String photo,String goodType,String price,String amount ) {

panel.setLayout(null);

checkBox = new JCheckBox(goodName);//"商品名称"
checkBox.setBounds(x-4, y+35, width-515, height-95);//(6,45+gap,106,25)
panel.add(checkBox);

//   	 JLabel label_photo = new JLabel(image);//商品图片

label_photo = new JLabel(photo);
label_photo.setBounds(x+130, y, width-521, height-20);//(140, 10, 100, 100),像素100x100可以不
panel.add(label_photo);

label_type = new JLabel(goodType);//"（商品类别）"
label_type.setBounds(x+268, y, width-550, height-105);//(278, 10, 54, 15)
panel.add(label_type);

label_price = new JLabel("¥："+price);//"（价格）"
label_price.setBounds(x+268, y+89, width-550, height-105);//(278, 79, 54, 15)
panel.add(label_price);

textField_number = new JTextField(amount);//购买数量
textField_number.setBounds(x+503, y+34,width-555, height-99);//(493, 48, 66, 21)
panel.add(textField_number);
textField_number.setColumns(10);
//减按钮
button_add = new JButton("-");
button_add.setBounds(x+451,  y+34, width-579, height-97);//(441, 47, 42, 23);
panel.add(button_add);
//加按钮
button_sub = new JButton("+");
button_sub.setBounds(x+579,  y+34, width-579, height-97);//(569, 47, 42, 23);
panel.add(button_sub);
}*/


/*    public class ShopNewGoodsPanel extends JPanel {
//"lishi牌洗发水","照片","生活用品","56","10" 
String goodName="lishi牌洗发水";
String photo="照片";
String goodType="生活用品";
String price="56";
String amount="10";
int x=10,y=10,width=621,height=130;
public ShopNewGoodsPanel() {
	setSize(677,140);
	setLayout(null);//绝对布局
	
	JCheckBox checkBox = new JCheckBox(goodName);//"商品名称"
	     checkBox.setBounds(x-4, y+35, width-515, height-95);//(6,45+gap,106,25)
		add(checkBox);
		 
//	 JLabel label_photo = new JLabel(image);//商品图片
		
		 JLabel label_photo = new JLabel(photo);
		label_photo.setBounds(x+130, y, width-521, height-20);//(140, 10, 100, 100),像素100x100可以不
	 add(label_photo);
	
	 JLabel label_type = new JLabel(goodType);//"（商品类别）"
	 label_type.setBounds(x+268, y, width-550, height-105);//(278, 10, 54, 15)
	 add(label_type);
	
	JLabel label_price = new JLabel("¥："+price);//"（价格）"
	label_price.setBounds(x+268, y+89, width-550, height-105);//(278, 79, 54, 15)
	add(label_price);

	JTextField textField_number = new JTextField(amount);//购买数量
	textField_number.setBounds(x+503, y+34,width-555, height-99);//(493, 48, 66, 21)
	add(textField_number);
	textField_number.setColumns(10);
	//减按钮
	JButton button_add = new JButton("-");
	button_add.setBounds(x+451,  y+34, width-579, height-97);//(441, 47, 42, 23);
	add(button_add);
	//加按钮
	JButton button_sub = new JButton("+");
	button_sub.setBounds(x+579,  y+34, width-579, height-97);//(569, 47, 42, 23);
	add(button_sub);
}

}*/
