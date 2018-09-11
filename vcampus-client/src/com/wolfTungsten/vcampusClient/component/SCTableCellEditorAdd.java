package com.wolfTungsten.vcampusClient.component;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SCTableCellEditorAdd extends DefaultCellEditor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JButton button;

	public SCTableCellEditorAdd() {
		super(new JTextField());
		// TODO �Զ����ɵĹ��캯�����
		this.setClickCountToStart(1);
		this.initButton();
		this.initPanel();
		this.panel.add(this.button);
		}

		private void initButton() {
		// TODO �Զ����ɵķ������
		this.button = new JButton();  
		this.button.setBounds(0,0,100,20);
		
		
		
		
		this.button.addActionListener(new ActionListener()
        {  
			@Override
			//==========================================================================================��������ӡ��γ�ѡ�񡱰�ť���¼�
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				//����ȡ���༭���¼����������tableModel��setValue������   
				SCTableCellEditorAdd.this.fireEditingCanceled();  
                // �������������������   
				//�����￪ʼ-----------------------------------------------------------------------------
			} ;
        });
		}
	

		private void initPanel() {
		// TODO �Զ����ɵķ������
		this.panel = new JPanel();  
        this.panel.setLayout(null);  
		
		}
		@Override  
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)  
		{  
			this.button.setText("�γ�ѡ��");
  
			return this.panel;  
		} 
}
