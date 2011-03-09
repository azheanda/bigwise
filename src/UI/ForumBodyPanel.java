package BigWise.UI;
import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BigWise.DataSpider.*;

public class ForumBodyPanel extends JPanel implements ActionListener{
   
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
    JTextArea tResult;
	public ForumBodyPanel()
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
	
		this.add(pLeftPanel,"West");
		
		tResult = new JTextArea("论坛");
		this.add(tResult,"Center");
	}
	
	 public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bALL)
		{
			tResult.setText("所有实时行情");
		}
		else if(e.getSource() == bSH)
		{
			tResult.setText("上海行情");
		}
		else if(e.getSource() == bSZ)
		{
			tResult.setText("深圳实时行情");
		}
		else if(e.getSource() == bCYB)
		{
			tResult.setText("创业板实时行情");
		}
		else if(e.getSource() == bZXB)
		{
			tResult.setText("中小板实时行情");
		}
		else if(e.getSource() == bHYBK)
		{
			tResult.setText("行业板块实时行情");
		}
		else if(e.getSource() == bGNBK)
		{
			tResult.setText("概念板块实时行情");
		}
		else if(e.getSource() == bDQBK)
		{
			tResult.setText("地区板块实时行情");
		}
	 }
}
