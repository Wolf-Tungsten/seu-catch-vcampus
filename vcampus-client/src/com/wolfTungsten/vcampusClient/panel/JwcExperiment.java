//���񴦡���ʵ���������
package com.wolfTungsten.vcampusClient.panel;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class JwcExperiment extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Create the panel.
	 */
	public JwcExperiment() {
		setSize(736,600);
		setLayout(null);
		String[][]tableValues= {};												   //�����������
		String[] columnNames = {"ʵ������","ʵ���ο���ʦ","ʵ��ص�","ʵ��ʱ��","ʣ������","ʵ��ɼ�"}; 
		tableModel = new DefaultTableModel(tableValues,columnNames);
		table = new JTable(tableModel);
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER); 						//����
		JScrollPane scrollTable = new JScrollPane(table);
		scrollTable.setBounds(35, 100, 666, 465);
		table.setBounds(35, 100, 666, 465);
		add(scrollTable);
		table.getTableHeader().setFont(new Font("΢���ź�",Font.BOLD,14));
		
		//��� ʵ������ ��ʦ �ص� ʱ�� ���� �ɼ� �����ݿ�����  
		//ʵ��ĵص� ʱ�� ��ʦ���ǽ������ʱ���ֵ  ֻ�ñ����� 
		//�ɼ�һ��ʼΪnull  ����Ա�ڳɼ�¼��֮�����ʾ
		//���{"����","��ʦ","�ص�","ʱ��","����",""} ��ʼ
		//��ӱ���еĺ��� 
		//tableModel.addRow(new String[]{});   
		tableModel.addRow(new String[]{"�������","YHD","404","18��30-20��00","2",null});
	}

}
