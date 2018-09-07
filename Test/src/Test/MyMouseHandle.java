package Test;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
 
class MyMouseHandle extends JFrame implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea text = new JTextArea();
 
	public MyMouseHandle() {
		super.setTitle("Crystal");// 设置标题
		JScrollPane pane = new JScrollPane(text);// 加入滚动条
		pane.setBounds(5, 5, 300, 200);// 设置绝对位置
		super.add(pane);// 向窗体中加入组件
		text.addMouseListener(this);// 加入mouse监听
		super.setSize(310, 210);
		super.setVisible(true);
		super.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(1);
			}
		});
	}
 
	public void mouseClicked(MouseEvent e)// 鼠标单击事件
	{
		int c = e.getButton();// 得到按下的鼠标键
		String mouseInfo = null;// 接收信息
		if (c == MouseEvent.BUTTON1)// 判断是鼠标左键按下
		{
			mouseInfo = "左键";
		} else if (c == MouseEvent.BUTTON3) {// 判断是鼠标右键按下
			mouseInfo = "右键";
		} else {
			mouseInfo = "滚轴";
		}
		text.append("鼠标单击：" + mouseInfo + ".\n");
	}
 
	public void mouseEntered(MouseEvent e)// 鼠标进入组件
	{
		text.append("鼠标进入组件.\n");
	}
 
	public void mouseExited(MouseEvent e)// 鼠标退出组件
	{
		text.append("鼠标退出组件.\n");
	}
 
	public void mousePressed(MouseEvent e)// 鼠标按下
	{
		text.append("鼠标按下.\n");
	}
 
	public void mouseReleased(MouseEvent e)// 鼠标松开
	{
		text.append("鼠标松开.\n");
	}


	public static void main(String[] args) {
		new MyMouseHandle();
	}
}