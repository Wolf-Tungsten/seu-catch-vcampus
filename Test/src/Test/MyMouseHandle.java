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
		super.setTitle("Crystal");// ���ñ���
		JScrollPane pane = new JScrollPane(text);// ���������
		pane.setBounds(5, 5, 300, 200);// ���þ���λ��
		super.add(pane);// �����м������
		text.addMouseListener(this);// ����mouse����
		super.setSize(310, 210);
		super.setVisible(true);
		super.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(1);
			}
		});
	}
 
	public void mouseClicked(MouseEvent e)// ��굥���¼�
	{
		int c = e.getButton();// �õ����µ�����
		String mouseInfo = null;// ������Ϣ
		if (c == MouseEvent.BUTTON1)// �ж�������������
		{
			mouseInfo = "���";
		} else if (c == MouseEvent.BUTTON3) {// �ж�������Ҽ�����
			mouseInfo = "�Ҽ�";
		} else {
			mouseInfo = "����";
		}
		text.append("��굥����" + mouseInfo + ".\n");
	}
 
	public void mouseEntered(MouseEvent e)// ���������
	{
		text.append("���������.\n");
	}
 
	public void mouseExited(MouseEvent e)// ����˳����
	{
		text.append("����˳����.\n");
	}
 
	public void mousePressed(MouseEvent e)// ��갴��
	{
		text.append("��갴��.\n");
	}
 
	public void mouseReleased(MouseEvent e)// ����ɿ�
	{
		text.append("����ɿ�.\n");
	}


	public static void main(String[] args) {
		new MyMouseHandle();
	}
}