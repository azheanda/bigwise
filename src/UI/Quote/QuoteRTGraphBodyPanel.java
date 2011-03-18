package BigWise.UI.Quote;

import java.awt.*;
import java.io.*;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BigWise.DataSpider.*;
import BigWise.DataSpider.Quote.QuoteDataSpider;
import BigWise.DataDefine.*;

import java.text.DecimalFormat;
import java.lang.Math;


public class QuoteRTGraphBodyPanel extends JPanel implements ActionListener{
	String type;
	String keyword;
	Vector<String> CodeList;
	int total;
	int current;
	String StockCode;
	String StockMarket;
	
	JPanel pLeftPanel;
	JPanel pContentPane;
	JPanel pDownPanel;
	
	JButton   bALL   =   new   JButton( "所有股票列表 ");
	JButton   bSH   =   new   JButton( "沪A ");
    JButton   bSZ   =   new   JButton( "深A ");
    JButton   bHYBK   =   new   JButton( "行业列表 "); 
    BasicArrowButton bHYNav = new  BasicArrowButton(BasicArrowButton.SOUTH);

    JButton   bDQBK   =   new   JButton( "地区列表"); 
    BasicArrowButton bDQNav = new  BasicArrowButton(BasicArrowButton.SOUTH);
    
    JLabel showLabel;
    JButton bNext = new JButton("下一张");
    JButton bPrevious = new JButton("上一张");
	public QuoteRTGraphBodyPanel()
	{
		CodeList = StockInfo.getStockCodeList();
		total = CodeList.size();
		current = 1;
		StockCode = CodeList.elementAt(current);
		StockMarket = getMarket(StockCode);
		
		setLayout(new BorderLayout());
		// 左边栏
		pLeftPanel = new JPanel();		
		pLeftPanel.setLayout(new FlowLayout());
		pLeftPanel.add(bALL);
		bALL.addActionListener(this);
		pLeftPanel.add(bSH);
		bSH.addActionListener(this);
		pLeftPanel.add(bSZ);
		bSZ.addActionListener(this);
		pLeftPanel.add(bHYBK);
		bHYBK.addActionListener(this);
		pLeftPanel.add(bHYNav);
		pLeftPanel.add(bDQBK); 
		pLeftPanel.add(bDQNav);
		bDQBK.addActionListener(this);
		pLeftPanel.setSize(1000,50);
		pLeftPanel.setAlignmentY(LEFT_ALIGNMENT);
		this.add(pLeftPanel,"North");
				
		pContentPane = new JPanel(new BorderLayout());
		showLabel = new JLabel();
		add(showLabel);
		setVisible(true);
		String text = "<html><img src='http://image.sinajs.cn/newchart/min/n/"+StockMarket+StockCode+".gif'/><html>";
		//System.out.println(text);
		showLabel.setText(text);
	    pContentPane.add(showLabel,"Center");
	    pContentPane.setPreferredSize(new   Dimension(5,600));
	    
	    pDownPanel = new JPanel();
	    pDownPanel.add(bPrevious);
	    bPrevious.addActionListener(this);
	    pDownPanel.add(bNext);
	    bNext.addActionListener(this);
	    pDownPanel.setSize(1000,100);
	    
	    pContentPane.add(pDownPanel,"South");
	    
	    this.add(pContentPane,"Center");
	    
	    

	   
		
	}
	
	
	 public void actionPerformed(ActionEvent e) {
		
		 if(e.getSource() == bNext)
			{
				current += 1;
				if(current > total)
				{
					current = total;
					bNext.setEnabled(false);
					bPrevious.setEnabled(true);
					return;
				}
				else
				{
					bNext.setEnabled(true);
				}
				
				//System.out.println(current + " " + total);
			}
			else if(e.getSource() == bPrevious)
			{
				current -= 1;
				if(current <= 0)
				{
					current = 1;
					bPrevious.setEnabled(false);
					bNext.setEnabled(true);
					return;
				}
				else
				{
					bPrevious.setEnabled(true);
				}
			}
		 	StockCode = CodeList.elementAt(current);
		 	StockMarket = getMarket(StockCode);
			String text = "<html><img src='http://image.sinajs.cn/newchart/min/n/"+StockMarket+StockCode+".gif'/><html>";
			showLabel.setText(text);
			//System.out.println(text);
	 }
	
	 
	 public String getMarket(String code)
	 {
		 String prefix = code.substring(0, 1);
		 //System.out.println(prefix);
		 if(prefix.equals("0"))
			 return "sz";
		 else if (prefix.equals("6"))
			 return "sh";
		 
		 return "sh";
	 }
	 
	 
	 public static void main(String args[]) {
		    JFrame frame = new JFrame("QuoteRTBodyPanel");
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    QuoteRTGraphBodyPanel tmp = new QuoteRTGraphBodyPanel();
		    frame.add(tmp);
		    frame.setSize(1000,720);
		    frame.setVisible(true);

		  }
}
