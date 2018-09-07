package Test;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;

public class MouseInfo extends JFrame {

    private final JPanel contentPanel = new JPanel();
    JLabel value_x = null;
    JLabel value_y = null;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            MouseInfo info_frame = new MouseInfo();
            info_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            info_frame.setVisible(true);
            info_frame.setAlwaysOnTop(true);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Point point = java.awt.MouseInfo.getPointerInfo().getLocation();
                    // System.out.println("Location:x=" + point.x + ", y=" +
                    // point.y);
                  
                  
                    info_frame.value_x.setText("" + point.x);
                    info_frame.value_y.setText("" + point.y);
                }
            }, 100, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public MouseInfo() {
        setTitle("\u9F20\u6807\u5750\u6807\u83B7\u53D6\u5668");
        setBounds(100, 100, 217, 156);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblx = new JLabel("\u5750\u6807x:");
        lblx.setFont(new Font("����", Font.PLAIN, 15));
        lblx.setBounds(22, 27, 66, 31);
        contentPanel.add(lblx);

        JLabel lbly = new JLabel("\u5750\u6807y:");
        lbly.setFont(new Font("����", Font.PLAIN, 15));
        lbly.setBounds(22, 68, 66, 31);
        contentPanel.add(lbly);

        value_x = new JLabel("0");
        value_x.setForeground(Color.BLUE);
        value_x.setFont(new Font("����", Font.PLAIN, 20));
        value_x.setBounds(82, 27, 66, 31);
        contentPanel.add(value_x);

        value_y = new JLabel("0");
        value_y.setForeground(Color.BLUE);
        value_y.setFont(new Font("����", Font.PLAIN, 20));
        value_y.setBounds(82, 68, 66, 31);
        contentPanel.add(value_y);
    }
}