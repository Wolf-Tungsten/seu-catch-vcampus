//���񴦡��������������
package com.wolfTungsten.vcampusClient.panel;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JwcExam extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Create the panel.
	 */
	public JwcExam() {
		setSize(736,600);
		setLayout(null);
		String[][]tableValues= {};												   //�����������
		String[] columnNames = {"���Կγ�����","�࿼��ʦ","���Եص�","����ʱ��","ʣ������","���Գɼ�"}; //������� �����пճ�������ѡ���˿ΰ�ť
		tableModel = new DefaultTableModel(tableValues,columnNames);
		table = new JTable(tableModel);
		JScrollPane scrollTable = new JScrollPane(table);
		scrollTable.setBounds(35, 100, 666, 465);
		table.setBounds(35, 100, 666, 465);
		add(scrollTable);
		table.getTableHeader().setFont(new Font("΢���ź�",Font.BOLD,14));
		
		//��� �γ� ��ʦ �ص� ʱ�� ���� �ɼ� �����ݿ�����
		//�ɼ�һ��ʼΪ��  ����Ա���֮����ʾ
		//tableModel.addRow(); 
		//���{"����","��ʦ","�ص�","ʱ��","����",""} ��ʼ
	}

}
