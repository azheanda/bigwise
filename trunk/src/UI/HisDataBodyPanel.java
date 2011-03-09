package BigWise.UI;
import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BigWise.DataSpider.*;

public class HisDataBodyPanel extends JPanel implements ActionListener{
   
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
	public HisDataBodyPanel()
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
	
		this.add(pLeftPanel,"North");
		repaint();
	}
	
	 public void actionPerformed(ActionEvent e) 
	 {
		 System.out.println("HisData Panel");
	 }
}
