	//���񴦡��������������
package com.wolfTungsten.vcampusClient.panel;

import java.awt.Font;
import java.lang.reflect.Array;
import java.util.ArrayList;

import com.google.gson.internal.LinkedTreeMap;
import com.wolfTungsten.vcampusClient.client.Client;
import com.wolfTungsten.vcampusClient.client.Client.Request;
import com.wolfTungsten.vcampusClient.client.Client.Response;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class JwcExam extends JPanel{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	String token;

	/**
	 * Create the panel.
	 */
	public JwcExam(String Token) {
		token = Token;
		setSize(736,600);
		setLayout(null);
		String[][]tableValues= {};												   //�����������
		String[] columnNames = {"���Կγ�����","�࿼��ʦ","���Եص�","����ʱ��","ʣ������","���Գɼ�"}; //������� �����пճ�������ѡ���˿ΰ�ť
		tableModel = new DefaultTableModel(tableValues,columnNames);
		table = new JTable(tableModel);
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);                       //����
		JScrollPane scrollTable = new JScrollPane(table);
		scrollTable.setBounds(35, 100, 666, 465);
		table.setBounds(35, 100, 666, 465);
		add(scrollTable);
		table.getTableHeader().setFont(new Font("΢���ź�",Font.BOLD,14));
		
		//��� �γ� ��ʦ �ص� ʱ�� ���� �ɼ� �����ݿ����� 
		//���Եص�ʱ����ʦ ȫ���������Ͽ�ʱ���ο���ʦ   �������� 
		//�ɼ�һ��ʼΪnull  ����Ա��ʵ��ɼ�¼��֮����ʾ
		//���{"����","��ʦ","�ص�","ʱ��","����",""} ��ʼ
		//��ӱ���еĺ���
		//tableModel.addRow(new String[]{}); 
		tableModel.addRow(new String[] {"�ź�","������","J2-404","14��00-16��00","1",null});
		showExam();
	}
	
	public void showExam() {
		Request request = new Request();
		request.setPath("helper/showStuExam");
		request.setToken(token);
		Response response = Client.fetch(request);
		if(response.getSuccess())
		{
			
			ArrayList<LinkedTreeMap<String, Object>> examList = 
					(ArrayList<LinkedTreeMap<String, Object>>) response.getBody().get("examMaplist");
			for(LinkedTreeMap<String, Object> examInfo:examList)
			{
				String name = (String)examInfo.get("name");
				String lecturer = (String)examInfo.get("lecturer");
				String location = (String)examInfo.get("location");
				//duration�ǿ��Ծ�����ʱ��
				String duration = (String)examInfo.get("duration");
				long starttime = (long)(double)examInfo.get("startTime");
				
				long time = (starttime-System.currentTimeMillis())/3600/1000/24;
				String lastTime = Long.toString(time);			
				//����û�мӳɼ���Ϣ��
				tableModel.addRow(new String[] {name,lecturer,location,duration,lastTime,null});					
			}
		}
	}

}
