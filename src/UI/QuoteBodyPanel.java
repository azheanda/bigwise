package BigWise.UI;
import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BigWise.DataSpider.*;

public class QuoteBodyPanel extends JPanel implements ActionListener{
   
	JPanel pLeftPanel;
	JScrollPane pContentPane;
	JButton   bALL   =   new   JButton( "所有股票列表 ");
	JButton   bSH   =   new   JButton( "沪A ");
    JButton   bSZ   =   new   JButton( "深A ");
    JButton   bZXB  =   new   JButton( "中小板 ");
    JButton   bCYB   =   new   JButton( "创业板");
    JButton   bHYBK   =   new   JButton( "行业列表 "); 
    JButton   bGNBK   =   new   JButton( "概念板块 "); 
    JButton   bDQBK   =   new   JButton( "地区列表"); 
    JTable tTable;
	public QuoteBodyPanel()
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
		
		
		//内容栏
		Object rowData[][] = 
	    {
	    		{"1","600000","浦发银行","0","0","0","0","0","0","0"},
	    		{"1","600000","浦发银行","0","0","0","0","0","0","0"},
	    		{"1","600000","浦发银行","0","0","0","0","0","0","0"},
	    		{"1","600000","浦发银行","0","0","0","0","0","0","0"},
	    		{"1","600000","浦发银行","0","0","0","0","0","0","0"},
	    		{"1","600000","浦发银行","0","0","0","0","0","0","0"},
	    		{"1","600000","浦发银行","0","0","0","0","0","0","0"},
	    		{"1","600000","浦发银行","0","0","0","0","0","0","0"},
	    		{"1","600000","浦发银行","0","0","0","0","0","0","0"},
	    		{"1","600000","浦发银行","0","0","0","0","0","0","0"}
	      };
	    Object columnNames[] = { "ID", "股市代码", "名称","最新价","最高价","最低价","涨跌","涨跌幅","总手","金额" };
	    tTable = new JTable(rowData, columnNames);
	    pContentPane = new JScrollPane(tTable);
		this.add(pContentPane,"Center");
		
	}
	
	 public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bALL)
		{
		
		}
		else if(e.getSource() == bSH)
		{
	
		}
		else if(e.getSource() == bSZ)
		{
	
		}
		else if(e.getSource() == bCYB)
		{
			
		}
		else if(e.getSource() == bZXB)
		{
			
		}
		else if(e.getSource() == bHYBK)
		{
			
		}
		else if(e.getSource() == bGNBK)
		{
		
		}
		else if(e.getSource() == bDQBK)
		{
		
		}
	 }
	 
	 public static void main(String args[]) {
		    JFrame frame = new JFrame();
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    QuoteBodyPanel tmp = new QuoteBodyPanel();
		    frame.add(tmp, BorderLayout.CENTER);
		    frame.setSize(800, 250);
		    frame.setVisible(true);

		  }
}
