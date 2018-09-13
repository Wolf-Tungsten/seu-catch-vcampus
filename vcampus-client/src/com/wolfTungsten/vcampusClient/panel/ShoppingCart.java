//购物车面板，上限是120.和淘宝一样，您怎么看？
package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
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
     int []bounds= {10,10,677,120};
     private JTextField textField;

     int count;//购物车里有几件商品
     String token;
     String [][]result ;
     String [] UXGuuidlist;

     private JButton deleteButton;
     
   public void NewGoodPanel (JPanel panel,int []bounds,int gap,
    		 JCheckBox checkBox,JLabel label_photo,JLabel label_price,JTextField textField_number,
    		 String []result ) {
	          panel.setBounds(bounds[0],bounds[1]+gap,bounds[2],bounds[3]);
	         
	          checkBox.setText(result[0]);//"商品名称"
    		 checkBox.setBounds(9, 45, 150,30);
    		 checkBox.setFont(new Font("微软雅黑", Font.BOLD, 12));
    		 panel.add(checkBox);
    		 
    		 label_photo.setText(result[1]);
//    		JLabel label_photo = new JLabel(image);//商品图片
    		 //TODO 亲，图片怎么过来呢
    		 label_photo.setBounds(160, 10, 100, 100);//(140, 10, 100, 100),像素100x100可以不
    		 panel.add(label_photo);

//    		 label_type.setText(result[2]);//"（商品类别）"
//    		 label_type.setBounds(278, 30, 54, 15);
 //   		 panel.add(label_type);
    		 
    		 JLabel label_yuan=new JLabel("¥：");
    		 label_yuan.setFont(new Font("微软雅黑", Font.BOLD, 12));
    		 label_yuan.setBounds(278, 49, 30, 30);
    		 panel.add(label_yuan);
    		 
    		 label_price.setText(result[2]);//"（价格）"
    		 label_price.setFont(new Font("微软雅黑", Font.BOLD, 12));
    		 label_price.setBounds(308, 49, 54, 30);
    		 panel.add(label_price);

    		 textField_number.setText(result[2]);//购买数量
    		 textField_number.setBounds(520, 48, 125, 30);
    		 textField_number.setFont(new Font("微软雅黑", Font.BOLD, 12));
    		 panel.add(textField_number);
    		 textField_number.setColumns(10);
    		
    		 JLabel label_amount=new JLabel("购买数量：");
    		 label_amount.setFont(new Font("微软雅黑", Font.BOLD, 12));
    		 label_amount.setBounds(440, 47, 60, 30);
    		 panel.add(label_amount);
    		 }


	public ShoppingCart(String Token , String r[][],String[] uxguuidlist) {
		UXGuuidlist = uxguuidlist;
		
		result = r;
		token = Token;
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

		//String [][]result=new String[][]{{"袁皓东牌洗发水","photo","12","1"},{"袁皓西","photo","30","3"}}; 
		//商品名称，商品照片，商品类别（e.g.生活百货），价格，购买数量 
		 //TODO 以这个为例，只要获取这是第几次添加，以及要传输的数据，就可以用这种愚蠢的方式添加商品信息到购物车。。。。。。。
	    int i=0;
	    count = UXGuuidlist.length;
	    for(i =0;i<count;i++){
           NewGoodPanel(goodPanel[i],bounds,gap*i,checkBox[i],label_photo[i],label_price[i],textField_number[i],result[i]);
		   panel.add(goodPanel[i]);
		}
		panel.setPreferredSize(new Dimension(716,130+gap*i));//panel的高度必须高于滚动面板
	
		scrollPane.setBounds(10, 35, 716, 502);
		add(scrollPane);
		
		JLabel label = new JLabel("我的购物车");
		label.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label.setBounds(10, 10, 91, 15);
		add(label);
		
		payButton = new JButton("结算清单");
		payButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		payButton.addActionListener(this);
		payButton.setBounds(617, 548, 93, 27);
		add(payButton);
		
		deleteButton = new JButton("移出购物车");
		deleteButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		deleteButton.setBounds(482, 547, 112, 30);
		deleteButton.addActionListener(this);
		add(deleteButton);
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// 点击“结算清单”按钮
		if(e.getSource()==payButton) {
			//  计算选中物品多少钱
			double cost=0.0;
			for(int i=0;i<count;i++) {	
				if(checkBox[i].isSelected()) {
					if(textField_number[i].getText().equals("")||textField_number[i].getText()==null) {
						JOptionPane.showMessageDialog(null, "商品所购数量不可为空！", "Tips",JOptionPane.ERROR_MESSAGE);  
					}
					else if(!textField_number[i].getText().equals("")&&textField_number[i].getText()!=null){
						double x =Double.parseDouble(label_price[i].getText());
						double y=Double.parseDouble(textField_number[i].getText());
						cost+=x*y;
					}
				}
			}
			if(cost>0.0) {
				String money=String.valueOf(cost);
				String payPassword="123456";//需要把用户消费密码传递过来
				JPasswordField pwd = new JPasswordField();
				Object[] message = {"本次消费共计"+money+"元\n请输入支付密码：", pwd};
				JOptionPane.showConfirmDialog(null, message, "Tips", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		        String passStr=pwd.getText();//获取输入对话框中的密码
		        ArrayList<HashMap<String, Object>> uxgmaplist = new ArrayList<>();
		        
		        for(int i=0;i<count;i++) {
		        	HashMap<String, Object> uxgmap = new HashMap<>();
		        	if(checkBox[i].isSelected()) {
		        		if(!textField_number[i].getText().equals("")&&textField_number[i].getText()!=null)
		        		{
		        			uxgmap.put("uuid", UXGuuidlist[i]);
		        			uxgmap.put("amount", Integer.valueOf(textField_number[i].getText()));
		        			uxgmap.put("cost", Double.valueOf(label_price[i].getText()));
		        			uxgmaplist.add(uxgmap);
		        		}
		        	}
		        }		        
		        Client.Request request = new Request();
		        request.setToken(token);
		        request.setPath("shop/purchaseByCart");
		        request.getParams().put("secretPassword", Client.getMD5(passStr));
		        request.getParams().put("Goodinfomaplist", uxgmaplist);
		        
		        Response response = Client.fetch(request);
		        
				if(response.getSuccess()) {
					 JOptionPane.showMessageDialog(null, "支付成功！", "Tips",JOptionPane.INFORMATION_MESSAGE);  
				} 
				else {
					JOptionPane.showMessageDialog(null, "支付失败！", "Tips",JOptionPane.ERROR_MESSAGE);  
				}
			}
		}	
		
		if(e.getSource()==deleteButton) {		  
			 int op = JOptionPane.showConfirmDialog(null,"请问是否要删除所选商品？", "提示",JOptionPane.YES_NO_OPTION); 
             if(op==JOptionPane.YES_OPTION){ 
            	 ArrayList<String> deletelist = new ArrayList<>();
            	 for(int i=0;i<count;i++) {	
      				if(checkBox[i].isSelected()) {
      					deletelist.add(UXGuuidlist[i]);
      				}
      				}
            	 Client.Request request = new Request();
				request.setToken(token);
				request.setPath("shop/cartremove");
				request.getParams().put("deletelist",deletelist);
            	 Response response = Client.fetch(request);
            	 if(response.getSuccess()) {
            	 for(int i=0;i<count;i++) {	
     				if(checkBox[i].isSelected()) {
     					checkBox[i].setSelected(false);
     					checkBox[i].setEnabled(false);
     					textField_number[i].setFont(new Font("微软雅黑", Font.BOLD, 16));
     					textField_number[i].setText("该商品已失效!");
     					textField_number[i].setEditable(false);
     					textField_number[i].setOpaque(false);		
     				}
     			}
            	 JOptionPane.showMessageDialog(null, "删除成功！", "Tips",JOptionPane.INFORMATION_MESSAGE);
            	 return;
            	 
            	 }
            	 
             }else{
            	 JOptionPane.showMessageDialog(null, "删除失败！", "Tips",JOptionPane.ERROR_MESSAGE);
             }
		}
	
	}	
}
