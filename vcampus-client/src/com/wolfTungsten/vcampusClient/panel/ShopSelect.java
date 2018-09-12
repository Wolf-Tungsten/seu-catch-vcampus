//选购商品
package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.google.gson.internal.LinkedTreeMap;
import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
     int gap=120;
     int []bounds= {10,10,677,120};
     int count=4;//检索到几件商品
     String [][]goodstable ;
     String token;
     ArrayList<LinkedTreeMap<String,Object>> goodinfomaplist ;
     ArrayList<String> good_uuidlist = new ArrayList<>();
	public void NewGoodPanel (JPanel panel,int []bounds,int gap,
			//商品照片，名字，单价，描述，购买数量
   		 JLabel label_photo,JLabel label_good_name,JLabel label_price,JTextField textField_number,JTextArea textArea_description,
   		JButton button_buy,JButton button_add,
   		 String []goods ) {
		
		panel.setBounds(bounds[0],bounds[1]+gap,bounds[2],bounds[3]);
		
		label_photo.setText(goods[0]);//商品照片
		label_photo.setBounds(10, 10, 100, 100);
		panel.add(label_photo);
		
		label_good_name.setText(goods[1]);//商品名称
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

	public ShopSelect(String Token) {
		token = Token;
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
		
		int i=0;
//	    for(i=0;i<count;i++) {    //count:检索到几件商品
//	    	 NewGoodPanel (goodPanel[i],bounds,gap*i,		
//			   		 label_photo[i],label_good_name[i],label_price[i],textField_number[i],textArea_description[i],button_buy[i],button_add[i],
//			   		 goodstable[i] ) ;    
//		    panel.add(goodPanel[i]);
//	    }
		
		
		scrollPane.setBounds(10, 90, 716, 500);
		add(scrollPane);
		
		button_1 = new JButton("生活百货");
		button_1.addActionListener(this);
		button_1.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_1.setBounds(10, 57, 80, 23);
		add(button_1);
		
		button_2 = new JButton("手机数码");
		button_2.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_2.addActionListener(this);
		button_2.setBounds(100, 57, 57, 23);
		add(button_2);
		
		button_3 = new JButton("服饰配件");
		button_3.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_3.addActionListener(this);
		button_3.setBounds(167, 57, 57, 23);
		add(button_3);
		
		button_4 = new JButton("零食美味");
		button_4.setFont(new Font("微软雅黑", Font.BOLD, 12));
		button_4.addActionListener(this);
		button_4.setBounds(234, 57, 57, 23);
		add(button_4);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("微软雅黑", Font.BOLD, 12));
		comboBox.setBounds(625, 50, 101, 30);
		
		comboBox.addItem("价格降序");
		comboBox.addItem("价格升序");
		comboBox.addItemListener(this);
		add(comboBox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button_select_goods) {
			selectGood();
		}
		if(e.getSource()==button_1) {
			queryByType("生活百货");
		
		}else if(e.getSource()==button_2) {
			queryByType("手机数码");
			
		}else if(e.getSource()==button_3) {
			queryByType("服饰配件");
		
		}else if(e.getSource()==button_4) {
			queryByType("零食美味");
			
		}
		for(int i=0;i<count;i++) {
			if(e.getSource()==button_buy[i]) {  //点击”立即购买“按钮
				if(!textField_number[i].getText().equals("")&&textField_number[i].getText()!=null&&Integer.parseInt(textField_number[i].getText())>0) {
				   double cost=0.0;
				   double x =Double.parseDouble(label_price[i].getText());
				   double y=Double.parseDouble(textField_number[i].getText());
				   cost=x*y;
				   String money=String.valueOf(cost);
				  
				   //TODO 需要把用户消费密码传递过来
				   JPasswordField pwd = new JPasswordField();
				   Object[] message = {"本次消费共计"+money+"元\n请输入支付密码：", pwd};
				   JOptionPane.showConfirmDialog(null, message, "Tips", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		           String passStr=pwd.getText();//获取输入对话框中的密码
		           String gooduuid = good_uuidlist.get(i);
    
		           Client.Request request = new Request();
			       request.setToken(token);
			       request.setPath("shop/purchaseNow");
			       request.getParams().put("uuid", gooduuid);
			       request.getParams().put("secretPassword", Client.getMD5(pwd.getText()));
			       request.getParams().put("amount", y);
			       request.getParams().put("price", x);
			       Response response = Client.fetch(request);
	           
		           //判断消费密码是否正确
		           if(response.getSuccess()) {
					    JOptionPane.showMessageDialog(null, "支付成功！", "Tips",JOptionPane.INFORMATION_MESSAGE);  
				   }else if (!response.getSuccess()){
					   JOptionPane.showMessageDialog(null, response.getBody().get("result"), "Tips",JOptionPane.ERROR_MESSAGE);  
				   }else if (passStr.equals("")||passStr==null){
					   JOptionPane.showMessageDialog(null, "支付失败！", "Tips",JOptionPane.ERROR_MESSAGE);  
				   }
				}else {
					JOptionPane.showMessageDialog(null, "请输入有效的购买数量！", "Tips",JOptionPane.ERROR_MESSAGE);  
				}
			}else if(e.getSource()==button_add[i]) {   //点击”添加到购物车“按钮
					addpurchaseCart(i);
				//TODO 添加到购物车
			}
		}				
	}
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED)
		{
			// comboBox.getSelectedIndex() ;
//              comboBox.getSelectedItem());
			if (comboBox.getSelectedIndex() == 0)
			{
				sort(0);
			} else if (comboBox.getSelectedIndex() == 1)
			{
				sort(1);
			}

		}
	}
	public void selectGood() {
		Client.Request request = new Request();
		request.setPath("shop/queryBySel");
		request.setToken(token);
		request.getParams().put("name", textField_select.getText());
		Response response = Client.fetch(request);
		goodinfomaplist = (ArrayList<LinkedTreeMap<String, Object>>) response.getBody().get("goodsinfomaplist");
		int row = goodinfomaplist==null?0:goodinfomaplist.size();
		if(response.getSuccess()&&row!=0) {		
		count = row;
		String[][] goodinfo = new String[row][5];
		good_uuidlist = new ArrayList<>();
		for(int i=0;i<goodinfomaplist.size();i++) {
			LinkedTreeMap<String,Object> goodinfomap = goodinfomaplist.get(i);
			goodinfo[i][0] = (String)goodinfomap.get("image");
			goodinfo[i][1] = (String)goodinfomap.get("name");
			goodinfo[i][2] = String.valueOf((double)goodinfomap.get("price"));
			goodinfo[i][3] = "";
			goodinfo[i][4] = (String) goodinfomap.get("description");
			good_uuidlist.add((String)goodinfomap.get("uuid"));
		}
		goodstable = goodinfo;
		panel.removeAll();
		panel.updateUI();
	    for(int i=0;i<count;i++) {    //count:检索到几件商品
	    	 NewGoodPanel (goodPanel[i],bounds,gap*i,		
			   		 label_photo[i],label_good_name[i],label_price[i],textField_number[i],textArea_description[i],button_buy[i],button_add[i],
			   		 goodstable[i] ) ;    
		    panel.add(goodPanel[i]);
	    }
	    panel.setPreferredSize(new Dimension(716,130+gap*count));//panel的高度必须高于滚动面板
		}
		else 
			JOptionPane.showMessageDialog(null, "没有找到该商品", "Tips",JOptionPane.ERROR_MESSAGE);
	}
	//根据类型返回商品
	public void queryByType(String type) {
		Client.Request request = new Request();
		request.setPath("shop/queryByFlag");
		request.getParams().put("type", type);
		request.setToken(token);
		Response response = Client.fetch(request);
		goodinfomaplist =(ArrayList<LinkedTreeMap<String, Object>>) response.getBody().get("Goodinfomaplist");
		int row = goodinfomaplist==null?0:goodinfomaplist.size();
	
		if(response.getSuccess()&&row!=0) {
		count = row;
		System.out.println(row+"/"+count);
		String[][] goodinfo = new String[row][5];
		good_uuidlist = new ArrayList<>();
		for(int i=0;i<row;i++) {
			LinkedTreeMap<String,Object> goodinfomap = goodinfomaplist.get(i);
			goodinfo[i][0] = (String)goodinfomap.get("image");
			goodinfo[i][1] = (String)goodinfomap.get("name");
			goodinfo[i][2] = String.valueOf((double)goodinfomap.get("price"));
			goodinfo[i][3] = "";
			goodinfo[i][4] = (String) goodinfomap.get("description");
			good_uuidlist.add((String)goodinfomap.get("uuid"));
		}
		
		goodstable = goodinfo;
		panel.removeAll();
		panel.updateUI();
	    for(int i=0;i<count;i++) {    //count:检索到几件商品
	    	 NewGoodPanel (goodPanel[i],bounds,gap*i,		
			   		 label_photo[i],label_good_name[i],label_price[i],textField_number[i],textArea_description[i],button_buy[i],button_add[i],
			   		 goodstable[i] ) ;    
		    panel.add(goodPanel[i]);
	    }
	    panel.setPreferredSize(new Dimension(716,130+gap*count));//panel的高度必须高于滚动面板
		}
		else 
			JOptionPane.showMessageDialog(null, "没有找到该商品", "Tips",JOptionPane.ERROR_MESSAGE);
	}
	class sortdown implements Comparator{

		@Override
		public int compare(Object o1, Object o2)
		{
			LinkedTreeMap<String,Object> a = (LinkedTreeMap<String,Object>) o1;
			LinkedTreeMap<String,Object> b = (LinkedTreeMap<String,Object>) o2;
			if((double)a.get("price")<(double)b.get("price"))
				return 1;
			return -1;
		}
		
	}
	class sortup implements Comparator{

		@Override
		public int compare(Object o1, Object o2)
		{
			LinkedTreeMap<String,Object> a = (LinkedTreeMap<String,Object>) o1;
			LinkedTreeMap<String,Object> b = (LinkedTreeMap<String,Object>) o2;
			if((double)a.get("price")>(double)b.get("price"))
				return 1;
			return -1;
		}
		
	}
	
	public void sort(int flag) {
		if(flag==0) {
		Collections.sort(goodinfomaplist, new sortdown());
		}else if(flag==1) {
			Collections.sort(goodinfomaplist, new sortup());
		}
		String[][] goodinfo = new String[count][5];
		good_uuidlist = new ArrayList<>();
		for(int i=0;i<count;i++) {
			LinkedTreeMap<String,Object> goodinfomap = goodinfomaplist.get(i);
			goodinfo[i][0] = (String)goodinfomap.get("image");
			goodinfo[i][1] = (String)goodinfomap.get("name");
			goodinfo[i][2] = String.valueOf((double)goodinfomap.get("price"));
			goodinfo[i][3] = "";
			goodinfo[i][4] = (String) goodinfomap.get("description");
			good_uuidlist.add((String)goodinfomap.get("uuid"));
			
		}
		
		goodstable = goodinfo;
		panel.removeAll();
		panel.updateUI();
	    for(int i=0;i<count;i++) {    //count:检索到几件商品
	    	 NewGoodPanel (goodPanel[i],bounds,gap*i,		
			   		 label_photo[i],label_good_name[i],label_price[i],textField_number[i],textArea_description[i],button_buy[i],button_add[i],
			   		 goodstable[i] ) ;    
		    panel.add(goodPanel[i]);
		
	}
	    panel.setPreferredSize(new Dimension(716,130+gap*count));//panel的高度必须高于滚动面板
	
	}	
	public void addpurchaseCart(int i) {
		LinkedTreeMap<String,Object> goodinfo = goodinfomaplist.get(i);
		String gooduuid = (String) goodinfo.get("uuid");
		
		String nameStr=label_good_name[i].getText();
		String priceStr=label_price[i].getText();
		String numberStr=textField_number[i].getText();
		Client.Request request = new Request();
		request.setPath("shop/addCart");
		request.getParams().put("good_id", gooduuid);
		request.getParams().put("cost", Double.valueOf(priceStr));
		request.getParams().put("amount", Integer.valueOf(numberStr));	
		request.setToken(token);
		Response response = Client.fetch(request);
		if(response.getSuccess())
			JOptionPane.showMessageDialog(null, "添加成功！", "Tips",JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "添加失败", "Tips",JOptionPane.ERROR_MESSAGE);	
	}
	
	
//	{"photo","袁皓东牌大猪蹄子","100","","鲜嫩肥美，活有余罪，罪不可赦，秀色可餐，项目组长，是个好人！\n免邮快递，送货上门，断货ing"}
}
