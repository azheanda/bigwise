package BigWise.UI;
import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BigWise.DataSpider.*;

public class HisDataBodyPanel extends JPanel implements ActionListener{
   
	JPanel pLeftPanel;
	JPanel pContentPanel;
	JButton   bALL   =   new   JButton( "所有股票列表 ");
	JButton   bSH   =   new   JButton( "沪A ");
    JButton   bSZ   =   new   JButton( "深A ");
    JButton   bZXB  =   new   JButton( "中小板 ");
    JButton   bCYB   =   new   JButton( "创业板");
    JButton   bHYBK   =   new   JButton( "行业列表 "); 
    JButton   bGNBK   =   new   JButton( "概念板块 "); 
    JButton   bDQBK   =   new   JButton( "地区列表"); 
	public HisDataBodyPanel()
	{
		this.setLayout(new BorderLayout());

		// 左边栏
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
