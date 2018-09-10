//选购商品
package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ShopSelect extends JPanel implements ActionListener{
	private JTextField textField_select;
	JButton button_select_goods ;
	JButton button_1,button_2,button_3,button_4,button_5,button_6;
	JPanel panel;
	private JLabel label_photo_text;
	private JLabel label_photo_name_text;
	private JLabel label_price_text;
	private JLabel label_amount_text;//
	private JTextField textField_amount_text;
	JTextArea textArea_description_text;
	JButton button_buy_text,button_add_text;
	
	 int len=300;
	 JPanel []goodPanel = new JPanel[len];
	 JLabel []label_photo=new JLabel[len];
	 JLabel []label_good_name=new JLabel[len];
	 JLabel []label_price=new JLabel[len];
	 JTextField []textField_number=new JTextField[len];
	 JTextArea []textArea_description=new JTextArea[len];
	 JButton []button_buy=new JButton[len];
	 JButton []button_add=new JButton[len];
     int gap=130;
     
	//panelBounds(10.0.667.120)
	public void NewGoodPanel (JPanel panel,int x,int y,int width,int height,int gap,
			//商品照片，名字，单价，描述，购买数量
   		 JLabel label_photo,JLabel label_good_name,JLabel label_price,JTextField textField_number,JTextArea textArea_description,
   		JButton button_buy,JButton button_add,
   		 String []goods ) {
		
		panel.setBounds(x,y+gap,width,height);
		
		label_photo.setText(goods[0]);//商品照片
		label_photo.setBounds(10, 10, 100, 100);
		panel.add(label_photo);
		
		label_good_name = new JLabel(goods[1]);//商品名称
		label_good_name.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label_good_name.setBounds(136, 10, 78, 15);
		panel.add(label_good_name);
		
		JLabel label_yuan = new JLabel("¥:");
		label_yuan.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label_yuan.setBounds(254, 10, 22, 15);
		panel.add(label_yuan);
		
		label_yuan.setText(goods[2]);//商品价格
		label_yuan.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label_price.setBounds(400, 10, 150, 15);
		panel.add(label_price);
		
		JLabel label_amount=new JLabel("购买数量");
		label_amount.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label_amount.setBounds(417, 10, 73, 15);
		panel.add(label_amount);
		
		textField_number.setText(goods[3]);//购买数量
		textField_number.setBounds(522, 7, 125, 21);
		panel.add(textField_number);
		textField_number.setColumns(10);
		
		textArea_description.setText(goods[4]);//商品描述
		textArea_description.setBounds(136, 49, 240, 61);
		textArea_description.setLineWrap(true);
		panel.add(textArea_description);
		
		button_buy .setText("马上购买");
		button_buy.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_buy.setBounds(417, 67, 93, 23);
		panel.add(button_buy);
		
		button_add .setText("加入购物车");
		button_add.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_add.setBounds(552, 67, 93, 23);
		panel.add(button_add);
		
	}
	public ShopSelect() {
		setSize(736,600);
		setLayout(null);//绝对布局
		
		textField_select = new JTextField();
		textField_select.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_select.setBounds(58, 39, 543, 21);
		textField_select.addActionListener(this);
		add(textField_select);
		textField_select.setColumns(10);
		
		button_select_goods = new JButton("搜索");
		button_select_goods.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_select_goods.setBounds(633, 38, 93, 23);
		button_select_goods.addActionListener(this);
		add(button_select_goods);
		//控件数组们初始化
		 for (int i = 0; i < goodPanel.length; i++) {
		      goodPanel[i] = new JPanel();
		      goodPanel[i].setLayout(null);
	     }
		 for (int i = 0; i < label_photo.length; i++) {
			 label_photo[i] = new JLabel();
			 label_photo[i].setLayout(null);
	     }
		 for (int i = 0; i < label_good_name.length; i++) {
			 label_good_name[i] = new JLabel();
			 label_good_name[i].setLayout(null);
	     }
		 for (int i = 0; i < label_price.length; i++) {
			 label_price[i] = new JLabel();
			 label_price[i].setLayout(null);
	     }
		 for (int i = 0; i < textField_number.length; i++) {
			 textField_number[i] = new JTextField();
			 textField_number[i].setLayout(null);
	     }
		 for (int i = 0; i < textArea_description.length; i++) {
			 textArea_description[i] = new JTextArea();
			 textArea_description[i].setLayout(null);
	     }
		 for (int i = 0; i <button_buy.length; i++) {
			 button_buy[i] = new JButton();
			 button_buy[i].setLayout(null);
	     }
		 for (int i = 0; i <button_add.length; i++) {
			 button_add[i] = new JButton();
			 button_add[i].setLayout(null);
	     }
		panel=new JPanel();
		panel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(panel,//在滚动面板上加入panel放置商品
			                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		String [][]goods=new String[][]{{"photo","袁皓东","100","0","生活用品"},
			{"photo","高睿昊","100","0","大猪蹄子"},{"photo","skk","100","0","大猪蹄子"}}; //商品照片，名字，单价，购买数量，描述
		int i=0;
	    NewGoodPanel (goodPanel[0],10,0,677,120,0,		
		   		 label_photo[i],label_good_name[i],label_price[i],textField_number[i],textArea_description[i],button_buy[i],button_add[i],
		   		 goods[i] ) ;
	    panel.add(goodPanel[i]);
	    
	    i++;
	    NewGoodPanel (goodPanel[i],10,0,677,120,gap,		
		   		 label_photo[i],label_good_name[i],label_price[i],textField_number[i],textArea_description[i],button_buy[i],button_add[i],
		   		 goods[i] ) ;
	    panel.add(goodPanel[i]);
	    
	    i++;
	    NewGoodPanel (goodPanel[i],10,0,677,120,gap*2,		
		   		 label_photo[i],label_good_name[i],label_price[i],textField_number[i],textArea_description[i],button_buy[i],button_add[i],
		   		 goods[i] ) ;    
	    panel.add(goodPanel[i]);
		panel.setPreferredSize(new Dimension(716,130+120*10));//panel的高度必须高于滚动面板
/*
 * 标准		
 
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 0, 677, 120);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		label_photo_text = new JLabel("photo");
		label_photo_text.setBounds(10, 10, 100, 100);
		panel_1.add(label_photo_text);
		
		label_photo_name_text = new JLabel("大猪蹄子");
		label_photo_name_text.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_photo_name_text.setBounds(136, 10, 78, 15);
		panel_1.add(label_photo_name_text);
		
		textArea_description_text = new JTextArea("");
		textArea_description_text.setBounds(136, 49, 240, 61);
		textArea_description_text.setLineWrap(true);
		panel_1.add(textArea_description_text);
		
		label_price_text = new JLabel("¥:");
		label_price_text.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_price_text.setBounds(254, 10, 22, 15);
		panel_1.add(label_price_text);
		
		label_amount_text = new JLabel("购买数量：");
		label_amount_text.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_amount_text.setBounds(417, 10, 73, 15);
		panel_1.add(label_amount_text);
		
		textField_amount_text = new JTextField();
		textField_amount_text.setBounds(522, 7, 125, 21);
		panel_1.add(textField_amount_text);
		textField_amount_text.setColumns(10);
		
		button_buy_text = new JButton("马上购买");
		button_buy_text.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_buy_text.setBounds(417, 67, 93, 23);
		panel_1.add(button_buy_text);
		
		button_add_text = new JButton("加入购物车");
		button_add_text.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_add_text.setBounds(552, 67, 93, 23);
		panel_1.add(button_add_text);
		
		JLabel lblNewLabel = new JLabel("100");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel.setBounds(274, 10, 102, 15);
		panel_1.add(lblNewLabel);
/*
 * stop		
 */
		
		scrollPane.setBounds(10, 120, 716, 470);
		add(scrollPane);
		
		button_1 = new JButton("生活百货");
		button_1.addActionListener(this);
		button_1.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_1.setBounds(58, 70, 80, 23);
		add(button_1);
		
		button_2 = new JButton("生鲜水果");
		button_2.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_2.addActionListener(this);
		button_2.setBounds(148, 70, 80, 23);
		add(button_2);
		
		button_3 = new JButton("零食美味");
		button_3.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_3.addActionListener(this);
		button_3.setBounds(238, 70, 80, 23);
		add(button_3);
		
		button_4 = new JButton("手机数码");
		button_4.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_4.addActionListener(this);
		button_4.setBounds(328, 70, 80, 23);
		add(button_4);
		
		button_5 = new JButton("运动户外");
		button_5.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_5.addActionListener(this);
		button_5.setBounds(418, 70, 93, 23);
		add(button_5);
		
		button_6 = new JButton("服饰配件");
		button_6.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_6.addActionListener(this);
		button_6.setBounds(521, 70, 80, 23);
		add(button_6);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button_select_goods) {
			//搜索
		}
		if(e.getSource()==button_1) {
			textField_select.setText("生活百货");
		}else if(e.getSource()==button_2) {
			textField_select.setText("生鲜水果");
		}else if(e.getSource()==button_3) {
			textField_select.setText("零食美味");
		}else if(e.getSource()==button_4) {
			textField_select.setText("手机数码");
		}else if(e.getSource()==button_5) {
			textField_select.setText("运动户外");
		}else if(e.getSource()==button_6) {
			textField_select.setText("服饰配件");
		}
				
	}
}
