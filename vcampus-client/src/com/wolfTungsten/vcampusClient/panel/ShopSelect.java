//选购商品
package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class ShopSelect extends JPanel implements ActionListener,ItemListener{
	private JTextField textField_select;
	JButton button_select_goods ;
	JButton button_1,button_2,button_3,button_4;
	JScrollPane scrollPane;//滚动面板
	JPanel panel;//放在滚动面板上的面板
	private JLabel label_photo_text;
	private JLabel label_photo_name_text;
	private JLabel label_price_text;
	private JLabel label_amount_text;//
	private JTextField textField_amount_text;
	JTextArea textArea_description_text;
	JButton button_buy_text,button_add_text;
	JComboBox comboBox;//排序方式
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
     int []bounds= {10,10,677,120};
     int count=4;//检索到几件商品
     
	public void NewGoodPanel (JPanel panel,int []bounds,int gap,
			//商品照片，名字，单价，描述，购买数量
   		 JLabel label_photo,JLabel label_good_name,JLabel label_price,JTextField textField_number,JTextArea textArea_description,
   		JButton button_buy,JButton button_add,
   		 String []goods ) {
		
		panel.setBounds(bounds[0],bounds[1]+gap,bounds[2],bounds[3]);
		
		label_photo.setText(goods[0]);//商品照片
		label_photo.setBounds(10, 10, 100, 100);
		panel.add(label_photo);
		
		label_good_name = new JLabel(goods[1]);//商品名称
		label_good_name.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label_good_name.setBounds(136, 10, 120, 15);
		panel.add(label_good_name);
		
		JLabel label_yuan = new JLabel("¥:");
		label_yuan.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label_yuan.setBounds(254, 10, 22, 15);
		panel.add(label_yuan);
		
		label_price.setText(goods[2]);//商品价格
		label_price.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label_price.setBounds(278, 10, 150, 15);
		panel.add(label_price);
		
		JLabel label_amount=new JLabel("购买数量：");
		label_amount.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label_amount.setBounds(417, 30, 73, 25);
		panel.add(label_amount);
		
		textField_number.setText(goods[3]);//购买数量
		textField_number.setBounds(510, 30, 137, 25);
		panel.add(textField_number);
		textField_number.setColumns(10);
		
		textArea_description.setText(goods[4]);//商品描述
		textArea_description.setBounds(136, 30, 240, 80);
		textArea_description.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textArea_description.setLineWrap(true);
		textArea_description.setEditable(false);
		textArea_description.setOpaque(false);
		panel.add(textArea_description);
		
		button_buy .setText("立即购买");
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
		textField_select.setBounds(10, 10, 591, 30);
		textField_select.addActionListener(this);
		add(textField_select);
		textField_select.setColumns(10);
		
		button_select_goods = new JButton("搜索");
		button_select_goods.setFont(new Font("微软雅黑", Font.BOLD, 14));
		button_select_goods.setBounds(633, 10, 93, 30);
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
			 button_buy[i].addActionListener(this);
			 button_buy[i].setLayout(null);
	     }
		 for (int i = 0; i <button_add.length; i++) {
			 button_add[i] = new JButton();
			 button_add[i].addActionListener(this);
			 button_add[i].setLayout(null);
	     }
		panel=new JPanel();
		panel.setLayout(null);
		scrollPane = new JScrollPane(panel,//在滚动面板上加入panel放置商品
			                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		String [][]goods=new String[][]{{"photo","袁皓东牌大猪蹄子","100","","鲜嫩肥美，活有余罪，罪不可赦，秀色可餐，项目组长，是个好人！\n免邮快递，送货上门，断货ing"},
			{"photo","高睿昊牌大猪蹄子","100","","鲜嫩肥美，秀色可餐，是个好人！"},{"photo","skk牌鸽子","100","","鲜嫩肥美，活有余罪，罪不可赦，秀色可餐，大鸽鸽，是个好人！"},{"photo","skk牌鸽子汤","100","","鲜嫩肥美，活有余罪，罪不可赦，秀色可餐，小鸽鸽，是个好人！"}}; //商品照片，名字，单价，购买数量，描述
		
		int i=0;
	    for(i=0;i<count;i++) {    //count:检索到几件商品
	    	 NewGoodPanel (goodPanel[i],bounds,gap*i,		
			   		 label_photo[i],label_good_name[i],label_price[i],textField_number[i],textArea_description[i],button_buy[i],button_add[i],
			   		 goods[i] ) ;    
		    panel.add(goodPanel[i]);
	    }
		panel.setPreferredSize(new Dimension(716,130+120*i));//panel的高度必须高于滚动面板
		
		scrollPane.setBounds(10, 90, 716, 500);
		add(scrollPane);
		
		button_1 = new JButton("日用品");
		button_1.addActionListener(this);
		button_1.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_1.setBounds(10, 57, 80, 23);
		add(button_1);
		
		button_2 = new JButton("零食");
		button_2.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_2.addActionListener(this);
		button_2.setBounds(100, 57, 57, 23);
		add(button_2);
		
		button_3 = new JButton("衣物");
		button_3.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_3.addActionListener(this);
		button_3.setBounds(167, 57, 57, 23);
		add(button_3);
		
		button_4 = new JButton("美妆");
		button_4.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_4.addActionListener(this);
		button_4.setBounds(234, 57, 57, 23);
		add(button_4);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("微软雅黑", Font.BOLD, 12));
		comboBox.setBounds(625, 50, 101, 30);
		comboBox.addItem("综合");
		comboBox.addItem("信用");
		comboBox.addItem("销量");
		comboBox.addItem("价格降序");
		comboBox.addItem("价格升序");
		comboBox.addItemListener(this);
		add(comboBox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button_select_goods) {
			//TODO 搜索
			/*
			 * 
		    String [][]goods=new String[][]{};
		    int i=0;
		    for(i=0;i<count;i++) {    //count:检索到几件商品
		    	 NewGoodPanel (goodPanel[i],bounds,gap*i,		
				   		 label_photo[i],label_good_name[i],label_price[i],textField_number[i],textArea_description[i],button_buy[i],button_add[i],
				   		 goods[i] ) ;    
			    panel.add(goodPanel[i]);
		    }
			panel.setPreferredSize(new Dimension(716,130+120*i));//panel的高度必须高于滚动面板
			 */
		}
		if(e.getSource()==button_1) {
			textField_select.setText("日用品");
		}else if(e.getSource()==button_2) {
			textField_select.setText("零食");
		}else if(e.getSource()==button_3) {
			textField_select.setText("衣物");
		}else if(e.getSource()==button_4) {
			textField_select.setText("美妆");
		}
		for(int i=0;i<count;i++) {
			if(e.getSource()==button_buy[i]) {  //点击”立即购买“按钮
				if(!textField_number[i].getText().equals("")&&textField_number[i].getText()!=null&&Integer.parseInt(textField_number[i].getText())>0) {
				   double cost=0.0;
				   double x =Double.parseDouble(label_price[i].getText());
				   double y=Double.parseDouble(textField_number[i].getText());
				   cost=x*y;
				   String money=String.valueOf(cost);
				   String payPassword="123456";
				   //TODO 需要把用户消费密码传递过来
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
				}else {
					JOptionPane.showMessageDialog(null, "请输入有效的购买数量！", "Tips",JOptionPane.ERROR_MESSAGE);  
				}
			}else if(e.getSource()==button_add[i]) {   //点击”添加到购物车“按钮
				String nameStr=label_good_name[i].getText();
				String priceStr=label_price[i].getText();
				String numberStr=textField_number[i].getText();
				//TODO 添加到购物车
			}
		}				
	}
	public void itemStateChanged(ItemEvent e) {
		  if (e.getStateChange() == ItemEvent.SELECTED) {
 //             comboBox.getSelectedIndex() ;
//              comboBox.getSelectedItem());
			  if(comboBox.getSelectedIndex()==0) {
				//TODO 综合排序
			  }else if(comboBox.getSelectedIndex()==1) {
				  //TODO 信用排序			 
			  }else if(comboBox.getSelectedIndex()==2) {
				  //TODO 销量
			  }else if(comboBox.getSelectedIndex()==3) {
				//TODO 价格降序
			  }else if(comboBox.getSelectedIndex()==4) {
				//TODO 价格升序
			  }	  
          }
	}
}
