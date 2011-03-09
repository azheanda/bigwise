package BigWise.UI;
import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BigWise.DataSpider.*;

public class ForumBodyPanel extends JPanel implements ActionListener{
   
	JPanel pLeftPanel;
	JPanel pContentPanel;
	JButton   bALL   =   new   JButton( "���й�Ʊ�б� ");
	JButton   bSH   =   new   JButton( "��A ");
    JButton   bSZ   =   new   JButton( "��A ");
    JButton   bZXB  =   new   JButton( "��С�� ");
    JButton   bCYB   =   new   JButton( "��ҵ��");
    JButton   bHYBK   =   new   JButton( "��ҵ�б� "); 
    JButton   bGNBK   =   new   JButton( "������ "); 
    JButton   bDQBK   =   new   JButton( "�����б�"); 
    JTextArea tResult;
	public ForumBodyPanel()
	{
		this.setLayout(new BorderLayout());

		// �����
		pLeftPanel = new JPanel();
		pLeftPanel.setLayout(new GridLayout(8,1));
		pLeftPanel.add(bALL);
		bALL.addActionListener(this);
		pLeftPanel.add(bSH);
		bSH.addActionListener(this);
		pLeftPanel.add(bSZ);
		bSZ.addActionListener(this);
		pLeftPanel.add(bZXB);
		bZXB.addActionListener(this);
		pLeftPanel.add(bCYB);
		bCYB.addActionListener(this);
		pLeftPanel.add(bHYBK);
		bHYBK.addActionListener(this);
		pLeftPanel.add(bGNBK);
		bGNBK.addActionListener(this);
		pLeftPanel.add(bDQBK); 
		bDQBK.addActionListener(this);
		pLeftPanel.setSize(100,30);
	
		this.add(pLeftPanel,"West");
		
		tResult = new JTextArea("��̳");
		this.add(tResult,"Center");
	}
	
	 public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bALL)
		{
			tResult.setText("����ʵʱ����");
		}
		else if(e.getSource() == bSH)
		{
			tResult.setText("�Ϻ�����");
		}
		else if(e.getSource() == bSZ)
		{
			tResult.setText("����ʵʱ����");
		}
		else if(e.getSource() == bCYB)
		{
			tResult.setText("��ҵ��ʵʱ����");
		}
		else if(e.getSource() == bZXB)
		{
			tResult.setText("��С��ʵʱ����");
		}
		else if(e.getSource() == bHYBK)
		{
			tResult.setText("��ҵ���ʵʱ����");
		}
		else if(e.getSource() == bGNBK)
		{
			tResult.setText("������ʵʱ����");
		}
		else if(e.getSource() == bDQBK)
		{
			tResult.setText("�������ʵʱ����");
		}
	 }
}
