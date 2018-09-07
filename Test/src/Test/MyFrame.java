 package Test;
import java.awt.Point;  
import java.awt.Rectangle;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.MouseAdapter;  
import java.awt.event.MouseEvent;  
import java.awt.event.MouseMotionAdapter;  
  
import javax.swing.JFrame;
import javax.swing.Timer;  
  
  
/** 
 * Swing靠边隐藏实例 
 * @author Administrator 
 * 
 */  
  
public class MyFrame implements ActionListener {  
	private static final long serialVersionUID = 1L;
  
    private Rectangle rect;  
    
    // 窗体离屏幕左边的距离  
    private int left;  
    
    // 窗体离屏幕右边的距离；  
    private int right;  
    
    // 屏幕的宽度；  
    private int screenXX;  
    
    // 窗体离屏幕顶部的距离  
    private int top;  
    
    // 窗体的宽  
    private int width;  
    
    // 窗体的高  
    private int height;  
    
    // 鼠标在窗体的位置  
    private Point point;  
    
    private Timer timer = new Timer(10, this);  
    private int xx, yy;  
    private boolean isDraging = false;   
    private JFrame jFrame = new JFrame();  
  
    public MyFrame() {  
        super();  
        timer.start();  
        jFrame.setTitle("窗体在屏幕边缘隐藏演示");  
        jFrame.setSize(400, 300);  
        jFrame.setLocation(400, 300);  
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        // jFrame.setAlwaysOnTop(true);  
        // jFrame.setUndecorated(true);  
        jFrame.setVisible(true);  
        moveFrame();  
    }  
  
    public void actionPerformed(ActionEvent e) {  
        left = jFrame.getLocationOnScreen().x;  
        top = jFrame.getLocationOnScreen().y;  
        width = jFrame.getWidth();  
        height = jFrame.getHeight();  
        screenXX = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;  
        right = screenXX - left - width;  
        
        // 获取窗体的轮廓  
        rect = new Rectangle(0, 0, width, height);  
        
        // 获取鼠标在窗体的位置  
        point = jFrame.getMousePosition();  
        
        if (left < 0 && isPtInRect(rect, point)) {  
            jFrame.setLocation(0, top); // 隐藏在左边，鼠标指到后显示窗体；  
        } else if (left > -5 && left < 5 && !(isPtInRect(rect, point))) {  
            jFrame.setLocation(left - width + 1, top); // 窗体移到左边便边缘隐藏到左边；  
        } else if ((top < 0 && left < 0) && isPtInRect(rect, point)) {// 窗体在左上角；  
            jFrame.setLocation(0, 0);// 窗口隐藏了，鼠标指到他，就显示出来；  
        } else if ((top > -5 && top < 5) && (left > -5 && left < 5)  
                && !(isPtInRect(rect, point))) {  
            // 当窗体的上边框与屏幕的顶端的距离小于5，  
            // 并且鼠标不再窗体上将窗体隐藏到屏幕的顶端  
            jFrame.setLocation(left - width + 1, 1);  
        } else if ((top < 0) && isPtInRect(rect, point)) {  
            jFrame.setLocation(left, 0);// 窗口隐藏了，鼠标指到他，就显示出来；  
        } else if (top > -5 && top < 5 && !(isPtInRect(rect, point))) {  
            // 当窗体的上边框与屏幕的顶端的距离小于5时，  
            // 并且鼠标不再窗体上将窗体隐藏到屏幕的顶端  
            jFrame.setLocation(left, 1 - height);  
        } else if (right < 0 && isPtInRect(rect, point)) {  
            jFrame.setLocation(screenXX - width + 1, top);// 隐藏在右边，鼠标指到后显示；  
        } else if (right > -5 && right < 5 && !(isPtInRect(rect, point))) {  
            jFrame.setLocation(screenXX - 1, top); // 窗体移到屏幕右边边缘隐藏到右边；  
        } else if (right < 0 && top < 0 && isPtInRect(rect, point)) {// 窗体在右上角；  
            jFrame.setLocation(screenXX - width + 1, 0);// 隐藏在右边，鼠标指到后显示；  
        } else if ((right > -5 && right < 5) && (top > -5 && top < 5)  
                && !(isPtInRect(rect, point))) {  
            jFrame.setLocation(screenXX - 1, 1); // 窗体移到屏幕右边边缘隐藏到右边；  
        }  
    }  
  
    public boolean isPtInRect(Rectangle rect, Point point) {  
        if (rect != null && point != null ) {  
            int x0 = rect.x;  
            int y0 = rect.y;  
            int x1 = rect.width;  
            int y1 = rect.height;  
            int x = point.x;  
            int y = point.y;  
            return x >= x0 && x < x1 && y >= y0 && y < y1;  
        }  
        return false;  
    }  
  
    public void moveFrame() {  
        jFrame.addMouseListener(new MouseAdapter() {  
            public void mousePressed(MouseEvent e) {  
                isDraging = true;  
                xx = e.getX();  
                yy = e.getY();  
            }  
  
            public void mouseReleased(MouseEvent e) {  
                isDraging = false;  
            }  
        });  
        jFrame.addMouseMotionListener(new MouseMotionAdapter() {  
            public void mouseDragged(MouseEvent e) {  
                if (isDraging) {  
                    int left = jFrame.getLocation().x;  
                    int top = jFrame.getLocation().y;  
                    jFrame.setLocation(left + e.getX() - xx, top + e.getY()  
                            - yy);  
                    jFrame.repaint();  
                }  
            }  
        });  
    }  
  
    public static void main(String[] args) {  
        new MyFrame();  
  
    }  
} 