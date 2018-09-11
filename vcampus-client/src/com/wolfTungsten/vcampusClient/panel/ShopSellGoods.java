package com.wolfTungsten.vcampusClient.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;

import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ShopSellGoods extends JPanel implements ItemListener,FocusListener,ActionListener{
	private JTextField textField_goodName,textField_price,textField_number;
	JComboBox<String> comboBox_type; 
	JComboBox<String> comboBox_change;
	private JTextField textField_postage;
	JTextArea textArea ;
	JButton addButton,cancelButton;
	String goodTypeStr,changeType;
	JLabel label_photo;
	JButton photoButton;
	private JLabel label_2;
	private JLabel label_3;
	
	public void PushPhoto(JButton photoButton,JLabel label_photo){
        JFileChooser chooser = new JFileChooser(); //创建一个文件选择器对象
        chooser.setDialogTitle("选择上传图片");
        chooser.setMultiSelectionEnabled(false);//设置文件不可多选     
        //创建一个文件过滤器对象 过滤出JPG PNG格式的文件（这里过滤只是再文件选择器中显示过滤，而不是别的文件不能选择！！）
        FileNameExtensionFilter filter = new FileNameExtensionFilter("图片(*.jpg,*.png)","jpg","png");       
        chooser.setFileFilter(filter);//将过滤器设置进文件选择器
        
          int returnVal = chooser.showOpenDialog(null);//设置一个打开文件选择器的触发组件 这个方法的返回值是int型 返回的是两个常量 1 0
        System.out.println("aaaaaa");
        //判断returnVar的值，如果返回的是APPROVE_OPTION，则用户选择了YES或者OK，也就是确定了上传的文件
        //APPROVE_OPTION对应的常量为0
        if(returnVal == JFileChooser.APPROVE_OPTION){
            //得到选择的文件
        	System.out.println("bbbbbb");
            File f = chooser.getSelectedFile();
            //创建一个fileName得到选择文件的名字
            String fileName =chooser.getSelectedFile().getName();
          //  String fileName =f.getName();
            //lastIndexOf(".") 返回"."在文件名中最后一次出现的下标
            //substring(int index)从指定的index开始截取后面的字符串
            //比如： a.txt 最后一次出现的.下标是 1 substring(1)就是从下标1的位置开始截取 截取后的新字符串为 .txt
            //所以这里需要+1 才能只截取文件类型 txt
            String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
            //判断选择的文件是否是图片文件 必须排除不是的情况 不然后续操作会报错
            if(!(prefix.equals("jpg") || prefix.equals("png"))){
                JOptionPane.showMessageDialog(new JDialog(),":请选择.jpg 或 .png格式的图片");
                return;
            }
            //设置文件长传的目的路径 这里设置一个相对路径
            String path = "up";
            //创建一个file对象 File对象可以是一个具体文件 也可以是一个文件夹 这里我们用到的是文件夹
            File dir = new File(path);
            //获取该文件夹下的全部文件到fs
            File[] fs = dir.listFiles();
            /*
             * 
             */
            //创建一个HashSet对象 用于存放path文件夹中所有文件的 "文件名"
            HashSet<String> set = new HashSet<String>();
            //使用foreach将fs中的所有文件对象的文件名都add进set集合中 
            for(File a : fs){
                set.add(a.getName());
            }
            //contains() 查看集合中是否包含指定的String数据
            if(set.contains(f.getName())){
                JOptionPane.showMessageDialog(new JDialog(),f.getName()+":文件已存在");
                return;
            }
            //创建文件的字节输入输出流
            FileInputStream input = null;
            FileOutputStream out = null;
            try {
                //读取文件选择器选择的文件f
                input = new FileInputStream(f);
                byte[] buffer = new byte[1024];
                //创建一个新的文件 名为f.getName() 的文件到 文件夹 path中
                File des = new File(path,f.getName());
                //输出新创建的这个文件des
                out = new FileOutputStream(des);
                int len = 0 ;
                //通过文件选择器对象拿到选择的文件.拿到该文件的绝对路径
                String absolutePath = chooser.getSelectedFile().getAbsolutePath();
                //创建一个ImageIcon对象 传入图片文件的绝对路径 通过这个对象得到图片的 长 宽
                ImageIcon imageIcon = new ImageIcon(absolutePath);
                int hight = imageIcon.getIconHeight();
                int witdh = imageIcon.getIconWidth();
                //判断hight和witdh是否符合要求 符合要求的进行上传操作，也就是将图片文件写到指定的文件夹中
                if(hight < 200 && witdh <200){
                    while((len = input.read(buffer)) != -1){
                        out.write(buffer, 0, len);
                    }
                    //将写的内容从缓存中立即输出
                    out.flush();
                    /**
                     * 注意：
                     *         我们之前的ImageIcon对象指向的是用户上传的文件的绝对路径 而不是我们服务器(也就是我们指定的目标文件夹)的文件
                     *         虽然图片是一样的 但是我们上传成功后 显示的应该是服务器上的图片！而不是用户本地的。
                     */
                    //拿到Image对象，设置Image的自适应高度和宽度 SCALE_DEFAULT为自适应属性
                    imageIcon = new ImageIcon(path+"\\"+f.getName());
                    Image img = imageIcon.getImage();
                    img = img.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                    //将自适应调整后的图片设置到imageIcon，再将ImageIcon设置到需要显示这个图片的组件中 在这里是设置进标签中
                    imageIcon.setImage(img);
                   System.out.println("失败");
                    label_photo.setIcon(imageIcon);
                    JOptionPane.showMessageDialog(null, "上传成功！", "提示",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "请上传200*200大小的图片!", "提示",JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException e) {
            	JOptionPane.showMessageDialog(null, "上传失败！", "提示",JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "上传失败！", "提示",JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally{
                try {
                    out.close();
                    input.close();
                } catch (IOException e) {
                  e.printStackTrace();
                }
            }
        }
   }
	
	public ShopSellGoods() {
		setSize(736,600);
		setLayout(null);//绝对布局
		
		JLabel lblNewLabel = new JLabel("物品名称：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel.setBounds(63, 65, 82, 25);
		add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("分类：");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_3.setBounds(63, 125, 70, 15);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("价格：");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_2.setBounds(63, 185, 54, 15);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_5 = new JLabel("上架数量：");
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_5.setBounds(63, 245, 82, 15);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_1 = new JLabel("交易方式：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_1.setBounds(63, 305, 82, 15);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("邮费：");
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_4.setBounds(63, 365, 54, 15);
		add(lblNewLabel_4);
		
		JLabel label = new JLabel("描述：");
		label.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label.setBounds(63, 425, 54, 15);
		add(label);
		
		textField_goodName = new JTextField();
		textField_goodName.setFont(new Font("微软雅黑", Font.BOLD, 12));
		textField_goodName.setBounds(178, 60, 215, 30);
		add(textField_goodName);
		textField_goodName.setColumns(10);
		
		textField_price = new JTextField();
		textField_price.setFont(new Font("微软雅黑", Font.BOLD, 12));
		textField_price.setBounds(178,179, 215, 30);
		add(textField_price);
		textField_price.setColumns(10);
		
		textField_number = new JTextField();
		textField_number.setFont(new Font("微软雅黑", Font.BOLD, 12));
		textField_number.setBounds(178,239, 215, 30);
		add(textField_number);
		textField_number.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("微软雅黑", Font.BOLD, 13));
		textArea.addFocusListener(this);
		textArea.setBounds(178, 422, 451, 85);
		textArea.setLineWrap(true);//自动换行
		textArea.setText("请输入对该物品的描述、出手理由或者使用体验。");
		add(textArea);
		
		comboBox_type = new JComboBox<String>();
		comboBox_type.setFont(new Font("微软雅黑", Font.BOLD, 14));
		comboBox_type.setBounds(178, 122, 114, 23);
		comboBox_type.addItem("生活百货");
		comboBox_type.addItem("手机数码");
		comboBox_type.addItem("运动户外");
		comboBox_type.addItem("服饰配件");
		comboBox_type.addItem("生鲜水果");
		comboBox_type.addItem("零食美味");
		comboBox_type.addItemListener(this);
		add(comboBox_type);
		
		comboBox_change = new JComboBox<String>();
		comboBox_change.setFont(new Font("微软雅黑", Font.BOLD, 14));
		comboBox_change.setBounds(178, 302, 114, 23);
		comboBox_change.addItem("当面交易");
		comboBox_change.addItem("同城速递");
		comboBox_change.addItem("其他");
		comboBox_change.addItemListener(this);
		add(comboBox_change);
		
		textField_postage = new JTextField("0");
		textField_postage.setFont(new Font("微软雅黑", Font.BOLD, 12));
		textField_postage.setBounds(178, 363, 215, 30);
		textField_postage.setEditable(false);
		add(textField_postage);
		textField_postage.setColumns(10);
		
		JLabel label_1 = new JLabel("元");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_1.setBounds(361, 360, 54, 30);
		add(label_1);
		
		addButton = new JButton("确认添加");
		addButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		addButton.setBounds(200, 556, 93, 23);
		addButton.addActionListener(this);
		add(addButton);
		
		cancelButton = new JButton("清空取消");
		cancelButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		cancelButton.setBounds(448, 557, 93, 23);
		cancelButton.addActionListener(this);
		add(cancelButton);
		
		label_2 = new JLabel("元");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_2.setBounds(361, 180, 54, 24);
		add(label_2);
		
		label_photo= new JLabel("");
		label_photo.setBounds(505, 106, 100, 100);
		add(label_photo);
		
		photoButton = new JButton("点击上传商品图片");
		photoButton.setFont(new Font("微软雅黑", Font.BOLD, 14));		
		photoButton.setBounds(483, 240, 146, 30);
		photoButton.addActionListener(this);
		add(photoButton);
		
		JTextArea txtrjpgpng = new JTextArea();
		txtrjpgpng.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtrjpgpng.setText("(请上传.jpg或.png格式，200*200以内的图片）");
		txtrjpgpng.setBounds(487, 284, 142, 64);
		txtrjpgpng.setLineWrap(true);
		txtrjpgpng.setEditable(false);
		txtrjpgpng.setOpaque(false);
		add(txtrjpgpng);
		
		label_3 = new JLabel("图片预览");
		label_3.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label_3.setBounds(483, 65, 54, 15);
		add(label_3);
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		  if (e.getStateChange() == ItemEvent.SELECTED) {
 //             System.out.println("选中: " + comboBox_type.getSelectedIndex() + " = " + comboBox_type.getSelectedItem());
//              System.out.println("选中: " + comboBox_change.getSelectedIndex() + " = " + comboBox_change.getSelectedItem());
			  if(comboBox_change.getSelectedIndex()==0) {
				  textField_postage.setText("0");
				  textField_postage.setEditable(false);
			  }
			  if(comboBox_change.getSelectedIndex()==1||comboBox_change.getSelectedIndex()==2) {
				  textField_postage.setEditable(true);
			  }
          }
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
			textArea.setText("");

	}
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if(textArea.getText().equals("")||textArea.getText()==null) {
			textArea.setText("请输入对该物品的描述、出手理由或者使用体验。");
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		   if(e.getSource()==photoButton) {
             //	PushPhoto(photoButton,label_photo);
		}
		if(e.getSource()==addButton) {
			String goodNameStr=textField_goodName.getText();
			String goodTypeStr=(String)comboBox_type.getSelectedItem();
			String priceStr=textField_price.getText();
			String numberStr=textField_number.getText();
			String changeTypeStr=(String)comboBox_change.getSelectedItem();
			String postageStr=textField_postage.getText();
			String descriptionStr=textArea.getText();
			if(goodNameStr.equals("")||goodNameStr==null) {
				JOptionPane.showMessageDialog(null, "请输商品名称！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(priceStr.equals("")||priceStr==null) {
				JOptionPane.showMessageDialog(null, "请输入您想售出的价格！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(numberStr.equals("")||numberStr==null) {
				JOptionPane.showMessageDialog(null, "请输入上架件数！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(postageStr.equals("")||postageStr==null) {
				JOptionPane.showMessageDialog(null, "请输入转手所需经费！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if(descriptionStr.equals("请输入对该物品的描述、出手理由或者使用体验。")) {
				JOptionPane.showMessageDialog(null, "描述一栏不可为空！", "Tips",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				JOptionPane.showMessageDialog(null, "成功上架！", "Tips",JOptionPane.INFORMATION_MESSAGE);
			}
		}
        if(e.getSource()==cancelButton) {
        	textField_goodName.setText("");
        	textField_price.setText("");
        	textField_postage.setText("0");
        	textField_number.setText("");
        	textArea.setText("请输入对该物品的描述、出手理由或者使用体验。");
		}  	     
	}
}
