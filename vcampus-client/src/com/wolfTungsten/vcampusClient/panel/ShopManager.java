package com.wolfTungsten.vcampusClient.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.google.gson.internal.LinkedTreeMap;
import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ShopManager extends JPanel implements ActionListener {
	JScrollPane scrollPane;//滚动面板
	JPanel panel;//放在滚动面板上的面板
	private JTextField textField_select;
	JButton button_select_goods ;
	JButton saveButton;
	JButton modifyAllButton;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;
	private JTextField textField_3;
	int len=200;
	JPanel []goodPanel=new JPanel[len];
	int []bounds= {0, 35, 695, 90};
	int gap=100;
	JTextField []textField_bianhao=new JTextField[len];
	JTextField []textField_good_name=new JTextField[len];
	JTextField []textField_price=new JTextField[len];
	JTextField []textField_number=new JTextField[len];
	JTextArea []textArea_description=new JTextArea[len];
	JButton []button_modify=new JButton[len];
	JButton []button_delete=new JButton[len];
	 int count=4;//检索到几件商品
	String token;
	String[][] goodstable;
	 ArrayList<LinkedTreeMap<String,Object>> goodinfomaplist ;
     ArrayList<String> good_uuidlist = new ArrayList<>();
	 
	public void NewGoodPanel (JPanel panel,int []bounds,int gap,
			//商品编号，名称，价格，购买数量，描述	
   		 JTextField textField_bianhao,JTextField textField_good_name,JTextField textField_price,JTextField textField_number,JTextArea textArea_description,
   		JButton button_modify,JButton button_delete,
   		 String []goods ) {
		panel.setBounds(bounds[0],bounds[1]+gap,bounds[2],bounds[3]);
		
		textField_bianhao .setText(goods[0]);//商品编号
		textField_bianhao.setFont(new Font("微软雅黑", Font.BOLD, 12));
		textField_bianhao.setEditable(false);
		textField_bianhao.setBounds(10, 30, 77, 30);
        panel.add(textField_bianhao);
        textField_bianhao.setColumns(10);
        
        textField_good_name.setText(goods[1]);//商品名称
        textField_good_name.setFont(new Font("微软雅黑", Font.BOLD, 12));
        textField_good_name.setEditable(false);
        textField_good_name.setBounds(97, 30, 143, 30);
        panel.add(textField_good_name);
        textField_good_name.setColumns(10);
        
        JLabel label_yuan = new JLabel("¥：");
        label_yuan.setFont(new Font("微软雅黑", Font.BOLD, 12));
        label_yuan.setBounds(250, 37, 19, 15);
        panel.add(label_yuan);
        
        textField_price.setText(goods[2]);//商品价格
        textField_price.setFont(new Font("微软雅黑", Font.BOLD, 12));
        textField_price.setEditable(false);
        textField_price.setBounds(279, 30, 57, 30);
        panel.add(textField_price);
        textField_price.setColumns(10);
        
        textField_number.setText(goods[3]);//商品余量
        textField_number.setFont(new Font("微软雅黑", Font.BOLD, 12));
        textField_number.setBounds(346, 30, 57, 30);
        textField_number.setEditable(false);
        panel.add(textField_number);
        textField_number.setColumns(10);
        
        JLabel label_jian = new JLabel("件");
        label_jian.setFont(new Font("微软雅黑", Font.BOLD, 12));
        label_jian.setBounds(413, 37, 24, 15);
        panel.add(label_jian);
        
        textArea_description.setText(goods[4]);
        textArea_description.setBounds(447, 10, 171, 70);        textArea_description.setEditable(false);
        textArea_description.setFont(new Font("微软雅黑", Font.BOLD, 12));
        textArea_description.setLineWrap(true);
        panel.add(textArea_description);
        
        button_modify .setText("编辑");
        button_modify.setFont(new Font("微软雅黑", Font.BOLD, 12));
        button_modify.setBounds(628, 10, 57, 30);
        panel.add(button_modify);
        
        button_delete.setText("删除");
        button_delete.setFont(new Font("微软雅黑", Font.BOLD, 12));
        button_delete.setBounds(628, 50, 57, 30);
        panel.add(button_delete);
	}
	public ShopManager(String Token){
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
		panel=new JPanel();
		panel.setLayout(null);
		scrollPane = new JScrollPane(panel,//在滚动面板上加入panel放置商品
			                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      
      //控件数组们初始化
		 for (int i = 0; i < goodPanel.length; i++) {
		      goodPanel[i] = new JPanel();
		      goodPanel[i].setLayout(null);
	     }
		 for (int i = 0; i < textField_bianhao.length; i++) {
			 textField_bianhao[i] = new JTextField();
			 textField_bianhao[i].setLayout(null);
	     }
		 for (int i = 0; i < textField_good_name.length; i++) {
			 textField_good_name[i] = new JTextField();
			 textField_good_name[i].setLayout(null);
	     }
		 for (int i = 0; i < textField_price.length; i++) {
			 textField_price[i] = new JTextField();
			 textField_price[i].setLayout(null);
	     }
		 for (int i = 0; i < textField_number.length; i++) {
			 textField_number[i] = new JTextField();
			 textField_number[i].setLayout(null);
	     }
		 for (int i = 0; i < textArea_description.length; i++) {
			 textArea_description[i] = new JTextArea();
			 textArea_description[i].setLayout(null);
	     }
		 for (int i = 0; i <button_modify.length; i++) {
			 button_modify[i] = new JButton();
			 button_modify[i].addActionListener(this);
			 button_modify[i].setLayout(null);
	     }
		 for (int i = 0; i <button_delete.length; i++) {
			 button_delete[i] = new JButton();
			 button_delete[i].addActionListener(this);
			 button_delete[i].setLayout(null);
	     }
      
		
		 goodstable=new String[][] {{"123456","飘柔牌洗发水","38.0","20","飘柔，你值得拥有。"},{"123456","飘柔牌洗发水","38.0","20","飘柔，你值得拥有。"},
			 {"123456","拉芳牌洗发水","38.0","20","拉芳，你值得拥有。"},{"123456","沙宣牌洗发水","38.0","20","沙宣，你值得拥有。"}};
//		  int i=0;
//		    for(i=0;i<count;i++) {    //count:检索到几件商品
//		    	 NewGoodPanel (goodPanel[i],bounds,gap*i,		
//		    			 textField_bianhao[i],textField_good_name[i],textField_price[i],textField_number[i],textArea_description[i],button_modify[i],button_delete[i],
//		    			 goodstable[i] ) ;    
//			    panel.add(goodPanel[i]);
//		    }
//		    
//		panel.setPreferredSize(new Dimension(716,130+100*i));//panel的高度必须高于滚动面板
		    
        JLabel label = new JLabel("商品编号");
        label.setFont(new Font("微软雅黑", Font.BOLD, 12));
        label.setBounds(23, 10, 54, 15);
        panel.add(label);
        
        JLabel label_1 = new JLabel("商品名称");
        label_1.setFont(new Font("微软雅黑", Font.BOLD, 12));
        label_1.setBounds(144, 10, 54, 15);
        panel.add(label_1);
        
        JLabel label_2 = new JLabel("商品描述");
        label_2.setFont(new Font("微软雅黑", Font.BOLD, 12));
        label_2.setBounds(498, 10, 54, 15);
        panel.add(label_2);
        
        JLabel label_3 = new JLabel("商品价格");
        label_3.setFont(new Font("微软雅黑", Font.BOLD, 12));
        label_3.setBounds(272, 10, 54, 15);
        panel.add(label_3);
        
        JLabel label_4 = new JLabel("商品库存");
        label_4.setFont(new Font("微软雅黑", Font.BOLD, 12));
        label_4.setBounds(359, 10, 54, 15);
        panel.add(label_4);
        
        JLabel label_6 = new JLabel("操作");
        label_6.setFont(new Font("微软雅黑", Font.BOLD, 12));
        label_6.setBounds(642, 10, 31, 15);
        panel.add(label_6);
		
		scrollPane.setBounds(10, 50, 716, 500);
		add(scrollPane);
		
		saveButton = new JButton("保存");
		saveButton.setBounds(633, 560, 93,30);
		saveButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		saveButton.addActionListener(this);
		add(saveButton);
		
		modifyAllButton = new JButton("一键编辑所有");
		modifyAllButton.addActionListener(this);
		modifyAllButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		modifyAllButton.setBounds(496, 560, 127,30);
		add(modifyAllButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button_select_goods) {
			selectGood();
		}
		for(int i=0;i<count;i++) {
			if(e.getSource()==button_modify[i]) {
			     textField_good_name[i].setEditable(true);
			     textField_price[i].setEditable(true);
			     textField_number[i].setEditable(true);
			     textArea_description[i].setEditable(true);
			}
			if(e.getSource()==button_delete[i]) {
				int op = JOptionPane.showConfirmDialog(null,"请问是否要删除此商品？", "提示",JOptionPane.YES_NO_OPTION); 
				 if(op==JOptionPane.YES_OPTION) {
					 	Response response = deleteGood(i);
					 	if(response.getSuccess()) {
					    textField_good_name[i].setEditable(false);
					    textField_price[i].setEditable(false);
					    textField_number[i].setText("/");
					    textField_number[i].setEditable(false);
					    textArea_description[i].setText("该商品已失效");
					    textArea_description[i].setEditable(false);
					    button_modify[i].setEnabled(false);
					    button_delete[i].setEnabled(false);
					 	}else {
					 		JOptionPane.showMessageDialog(null, "删除失败,","Tips",JOptionPane.ERROR_MESSAGE);
					 	}
					    //TODO 返回删除后的信息
			    }
			}
		}
		if(e.getSource()==saveButton) {
			for(int i=0;i<count;i++) {						
				if( textField_good_name[i].getText().equals("")||textField_good_name[i].getText()==null) {
					JOptionPane.showMessageDialog(null, "商品名称不可为空！", "Tips",JOptionPane.ERROR_MESSAGE); 
				}else if(  textField_price[i].getText().equals("")|| textField_price[i].getText()==null) {
					JOptionPane.showMessageDialog(null, "商品价格不可为空！", "Tips",JOptionPane.ERROR_MESSAGE); 
				}else if(   textField_number[i].getText().equals("")||  textField_number[i].getText()==null||textField_number[i].getText().equals("0")) {
					JOptionPane.showMessageDialog(null, "商品余量不可为空！", "Tips",JOptionPane.ERROR_MESSAGE); 
				}else if(   textArea_description[i].getText().equals("")||  textArea_description[i].getText()==null) {
					JOptionPane.showMessageDialog(null, "商品描述不可为空！", "Tips",JOptionPane.ERROR_MESSAGE); 
				}			
			}
			int op = JOptionPane.showConfirmDialog(null,"请问是否要保存此次修改？", "提示",JOptionPane.YES_NO_OPTION); 
			int a=0;
			 if(op==JOptionPane.YES_OPTION) {//Goodinfomaplist
				 ArrayList<LinkedTreeMap<String,Object>> updateMaplist = new ArrayList<>();
				 for(int i=0;i<count;i++) {
					 updatelist() ;
					Client.Request request = new Request();
					request.setPath("shop/updateGood");
					request.setToken(token);
					request.getParams().put("name", textField_good_name[i].getText());
					request.getParams().put("uuid", good_uuidlist.get(i));
					request.getParams().put("price",  Double.valueOf(textField_price[i].getText()));
					request.getParams().put("description", textArea_description[i].getText());
					request.getParams().put("amount", Integer.valueOf(textField_number[i].getText()));
					Response response = Client.fetch(request);
					if (response.getSuccess())a++;
					
				 }
				
				 if(a==count)
					 JOptionPane.showMessageDialog(null, "修改成功", "Tips",JOptionPane.INFORMATION_MESSAGE);
				 else
					 JOptionPane.showMessageDialog(null, "修改失败，请联系管理员", "Tips",JOptionPane.ERROR_MESSAGE);
				  //TODO 返回修改后的信息
			 }else {
				return;
			 }
		}
		if(e.getSource()==modifyAllButton) {
			for(int i=0;i<count;i++) {
				     textField_good_name[i].setEditable(true);
				     textField_price[i].setEditable(true);
				     textField_number[i].setEditable(true);
				     textArea_description[i].setEditable(true);
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
			goodinfo[i][0] = (String)goodinfomap.get("uuid");
			goodinfo[i][1] = (String)goodinfomap.get("name");
			goodinfo[i][2] = String.valueOf((double)goodinfomap.get("price"));
			goodinfo[i][3] = String.valueOf((int)(double)goodinfomap.get("amount"));
			goodinfo[i][4] = (String) goodinfomap.get("description");
			good_uuidlist.add((String)goodinfomap.get("uuid"));
			button_modify[i].setEnabled(true);
		    button_delete[i].setEnabled(true);
		}
			goodstable = goodinfo;
			panel.removeAll();
			panel.updateUI();

			for (int i = 0; i < count; i++) { // count:检索到几件商品
				NewGoodPanel(goodPanel[i], bounds, gap * i, textField_bianhao[i], textField_good_name[i],
						textField_price[i], textField_number[i], textArea_description[i], button_modify[i],
						button_delete[i], goodstable[i]);
				panel.add(goodPanel[i]);
			}
//			{"123456","飘柔牌洗发水","38.0","20","飘柔，你值得拥有。"}
			panel.setPreferredSize(new Dimension(716, 130 + 100 * count));// panel的高度必须高于滚动面板
		}else 
			JOptionPane.showMessageDialog(null, "没有找到该商品", "Tips",JOptionPane.ERROR_MESSAGE);
	}
	
	public Response deleteGood(int i) {
		Client.Request request = new Request();
		request.setPath("shop/deleteGoods");
		request.setToken(token);
		ArrayList<String> deletelist = new ArrayList<>();
		deletelist.add(good_uuidlist.get(i));
		request.getParams().put("deletelist", deletelist);
		return Client.fetch(request);
		
		
	}
	//
	public void updatelist() {
		ArrayList<LinkedTreeMap<String,Object>> updateMaplist = new ArrayList<>();
		 for(int i=0;i<count;i++) {
			 LinkedTreeMap<String,Object> goodinfo = new LinkedTreeMap<>();
			 goodinfo.put("uuid", good_uuidlist.get(i));
			 goodinfo.put("name", textField_good_name[i].getText());
			 goodinfo.put("description", textArea_description[i]);
			 goodinfo.put("amount", Integer.valueOf(textField_number[i].getText()));
			 goodinfo.put("price", Double.valueOf(textField_price[i].getText()));
			 
			 textField_good_name[i].setEditable(false);
		     textField_price[i].setEditable(false);
		     textField_number[i].setEditable(false);
		     textArea_description[i].setEditable(false);
		     updateMaplist.add(goodinfo);
		 }
		 Client.Request request = new Request();
		 request.setPath("shop/updateGoods");
		 request.setToken(token);
		 request.getParams().put("Goodinfomaplist", updateMaplist);
		 Response response = Client.fetch(request);
		 if(response.getSuccess())
			 JOptionPane.showMessageDialog(null, "修改成功", "Tips",JOptionPane.INFORMATION_MESSAGE);
		 else
			 JOptionPane.showMessageDialog(null, "修改失败，请联系管理员", "Tips",JOptionPane.ERROR_MESSAGE);
	}
}
