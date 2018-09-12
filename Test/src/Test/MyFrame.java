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
 * Swing��������ʵ�� 
 * @author Administrator 
 * 
 */  
  
public class MyFrame implements ActionListener {  
	private static final long serialVersionUID = 1L;
  
    private Rectangle rect;  
    
    // ��������Ļ��ߵľ���  
    private int left;  
    
    // ��������Ļ�ұߵľ��룻  
    private int right;  
    
    // ��Ļ�Ŀ�ȣ�  
    private int screenXX;  
    
    // ��������Ļ�����ľ���  
    private int top;  
    
    // ����Ŀ�  
    private int width;  
    
    // ����ĸ�  
    private int height;  
    
    // ����ڴ����λ��  
    private Point point;  
    
    private Timer timer = new Timer(10, this);  
    private int xx, yy;  
    private boolean isDraging = false;   
    private JFrame jFrame = new JFrame();  
  
    public MyFrame() {  
        super();  
        timer.start();  
        jFrame.setTitle("��������Ļ��Ե������ʾ");  
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
        
        // ��ȡ���������  
        rect = new Rectangle(0, 0, width, height);  
        
        // ��ȡ����ڴ����λ��  
        point = jFrame.getMousePosition();  
        
        if (left < 0 && isPtInRect(rect, point)) {  
            jFrame.setLocation(0, top); // ��������ߣ����ָ������ʾ���壻  
        } else if (left > -5 && left < 5 && !(isPtInRect(rect, point))) {  
            jFrame.setLocation(left - width + 1, top); // �����Ƶ���߱��Ե���ص���ߣ�  
        } else if ((top < 0 && left < 0) && isPtInRect(rect, point)) {// ���������Ͻǣ�  
            jFrame.setLocation(0, 0);// ���������ˣ����ָ����������ʾ������  
        } else if ((top > -5 && top < 5) && (left > -5 && left < 5)  
                && !(isPtInRect(rect, point))) {  
            // ��������ϱ߿�����Ļ�Ķ��˵ľ���С��5��  
            // ������겻�ٴ����Ͻ��������ص���Ļ�Ķ���  
            jFrame.setLocation(left - width + 1, 1);  
        } else if ((top < 0) && isPtInRect(rect, point)) {  
            jFrame.setLocation(left, 0);// ���������ˣ����ָ����������ʾ������  
        } else if (top > -5 && top < 5 && !(isPtInRect(rect, point))) {  
            // ��������ϱ߿�����Ļ�Ķ��˵ľ���С��5ʱ��  
            // ������겻�ٴ����Ͻ��������ص���Ļ�Ķ���  
            jFrame.setLocation(left, 1 - height);  
        } else if (right < 0 && isPtInRect(rect, point)) {  
            jFrame.setLocation(screenXX - width + 1, top);// �������ұߣ����ָ������ʾ��  
        } else if (right > -5 && right < 5 && !(isPtInRect(rect, point))) {  
            jFrame.setLocation(screenXX - 1, top); // �����Ƶ���Ļ�ұ߱�Ե���ص��ұߣ�  
        } else if (right < 0 && top < 0 && isPtInRect(rect, point)) {// ���������Ͻǣ�  
            jFrame.setLocation(screenXX - width + 1, 0);// �������ұߣ����ָ������ʾ��  
        } else if ((right > -5 && right < 5) && (top > -5 && top < 5)  
                && !(isPtInRect(rect, point))) {  
            jFrame.setLocation(screenXX - 1, 1); // �����Ƶ���Ļ�ұ߱�Ե���ص��ұߣ�  
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
        String str = "2018-08-21/9:00-12:00";
        String[] strs =str.split("/");
        System.out.println(strs[0] + strs[1]);
  
    }  
} 