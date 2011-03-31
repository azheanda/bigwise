package BigWise.UI.History;

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

import BigWise.Controller.HistoryController;
import BigWise.DataSpider.*;
import BigWise.DataSpider.Quote.QuoteDataSpider;
import BigWise.Model.*;

import java.text.DecimalFormat;
import java.lang.Math;


public class HistoryKGraphBodyPanel extends JPanel implements ActionListener{

	HistoryController hc;

	String KLineType;
	
	JPanel pContentPane;
    JLabel showLabel;
    
    
	JPanel pDownPanel;
    JButton bNext = new JButton("下一张");
    JButton bPrevious = new JButton("上一张");
	
    public HistoryKGraphBodyPanel(String _KLineType)
	{
		KLineType = _KLineType;				// K线类型
    	init();	
	}
    
	public void init()
	{
		hc = HistoryController.getHistoryControllerInstance();
		
		this.setLayout(new BorderLayout());		
		pContentPane = new JPanel(new BorderLayout());
		showLabel = new JLabel();
		pContentPane.add(showLabel,"Center");
	    pContentPane.setPreferredSize(new   Dimension(800,600));
	    this.add(pContentPane,"Center");
	    
	    pDownPanel = new JPanel();
	    pDownPanel.add(bPrevious);
	    bPrevious.addActionListener(this);
	    pDownPanel.add(bNext);
	    bNext.addActionListener(this);
	    pDownPanel.setSize(1000,100);    
	    this.add(pDownPanel,"South");
	    
	    refresh();
	    setSize(1000,600);  
	    setVisible(true);
	    
	}

	public void refresh()
	{
		//System.out.println(hc.StockMarket + "" + hc.StockCode + hc.CodeIndex);
		String text = "<html><img src='http://image.sinajs.cn/newchart/"+KLineType+"/n/"+hc.controller.StockMarket+hc.controller.StockCode+".gif'/><html>";
		//System.out.println(text);
		showLabel.setText(text); 
	}
	public void actionPerformed(ActionEvent e) {
		
		 // 会通知上层次面板更新
		 if(e.getSource() == bNext)
		 {
			 hc.controller.CodeIndexNext();				
		 }
		 else if(e.getSource() == bPrevious)
		 {
			 hc.controller.CodeIndexprevious();
		 }
		
		 refresh();
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
	 
	 public HistoryKGraphBodyPanel getHistoryKGraphPanel()
	 {
		 return this;
	 }
	 public static void main(String args[]) {
		    JFrame frame = new JFrame("QuoteRTBodyPanel");
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    HistoryKGraphBodyPanel tmp = new HistoryKGraphBodyPanel("daily");
		    frame.add(tmp);
		    frame.setSize(1000,720);
		    frame.setVisible(true);
		  }
}
