//���񴦡����α��ѯ���
package com.wolfTungsten.vcampusClient.panel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JwcCurriculum extends JPanel implements MouseListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int weekNumber = 1; //��ʼ������Ϊ1
	JPanel panel;
	//�����ĸ����ֱ��ʾ1-8�ܵ��� 1-8��˫�� 9-16�ܵ��� 9-16��˫�� �Ŀγ̱�
	JPanel cardPanel1,cardPanel2,cardPanel3,cardPanel4 = new JPanel();
	//����������������� �����ֱ����16�ܵ����
	//������һ���ַ����� �������������嵽��Ƭ����
	JPanel infoPanel1,infoPanel2,infoPanel3,infoPanel4,infoPanel5,infoPanel6,infoPanel7,infoPanel8
	,infoPanel9,infoPanel10,infoPanel11,infoPanel12,infoPanel13,infoPanel14,infoPanel15,infoPanel16;
	JPanel[] infoPanel = new JPanel[] {infoPanel1,infoPanel2,infoPanel3,infoPanel4,infoPanel5,infoPanel6,infoPanel7,infoPanel8
			,infoPanel9,infoPanel10,infoPanel11,infoPanel12,infoPanel13,infoPanel14,infoPanel15,infoPanel16};
	JPanel splitPanel1,splitPanel2,splitPanel3,splitPanel4,splitPanel5,splitPanel6
	,splitPanel7,splitPanel8,splitPanel9,splitPanel10,splitPanel11,splitPanel12
	,splitPanel13,splitPanel14,splitPanel15,splitPanel16;
	JPanel[] splitPanel = new JPanel[] {splitPanel1,splitPanel2,splitPanel3,splitPanel4,splitPanel5,splitPanel6
			,splitPanel7,splitPanel8,splitPanel9,splitPanel10,splitPanel11,splitPanel12,splitPanel13,splitPanel14,splitPanel15,splitPanel16};
	String[] week = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"};

	/**
	 * Create the panel.
	 */

	public JwcCurriculum() {
		setSize(736,600);
		setLayout(null);
		
		CardLayout layout = new CardLayout(); //���ÿ�Ƭ����
		panel = new JPanel(layout);
		panel.setBounds(38, 30, 660, 540);
		panel.setBackground(new Color(255,211,222));
		add(panel);
		for (int i = 0;i<16;i++) {
			splitPanel[i] = new JPanel();
			splitPanel[i].setBackground(Color.BLACK);
			splitPanel[i].setLayout(null);
			infoPanel[i] = new JPanel();
			splitPanel[i].add(infoPanel[i]);			
			
			
			infoPanel[i].setBounds(0,0,660,75);
			infoPanel[i].setBackground(new Color(230,230,230));
			if(i==0 && i==4 && i==8 && i==12) {
				splitPanel[i].add(cardPanel1);
				cardPanel1.setBounds(0,75,666,390);
			}else if(i==1 && i==5 && i==9 && i==13) {
				splitPanel[i].add(cardPanel2);
				cardPanel2.setBounds(0,75,666,390);
			}else if(i==2 && i==6 && i==10 && i==14) {
				splitPanel[i].add(cardPanel3);
				cardPanel3.setBounds(0,75,666,390);
			}else if(i==3 && i==7 && i==11 && i==15) {
				splitPanel[i].add(cardPanel4);
				cardPanel4.setBounds(0,75,666,390);
			}
			panel.add(week[i],splitPanel[i]);
			
			//info�������
			JLabel label_nextWeek = new JLabel("��һ��");
			JLabel label_lastWeek = new JLabel("��һ��");
			label_nextWeek.setFont(new Font("΢���ź�",Font.BOLD,14));
			label_lastWeek.setFont(new Font("΢���ź�",Font.BOLD,14)); 
			label_lastWeek.setBounds(10,10,50,55);
			label_nextWeek.setBounds(600,10,50,55);
			label_lastWeek.addMouseListener(this);

	
			layout.show(panel,"1");

		}	







	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO �Զ����ɵķ������

		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

}
