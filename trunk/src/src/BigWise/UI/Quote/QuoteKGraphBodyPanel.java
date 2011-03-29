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

import BigWise.Controller.QuoteController;
import BigWise.DataSpider.*;
import BigWise.DataSpider.Quote.QuoteDataSpider;
import BigWise.Model.*;

import java.text.DecimalFormat;
import java.lang.Math;


public class QuoteKGraphBodyPanel extends JPanel implements ActionListener{
	
	QuoteController qc;


	JPanel pContentPane;
	JPanel pDownPanel;
	
	
    
    JLabel showLabel;
    JButton bNext = new JButton("下一张");
    JButton bPrevious = new JButton("上一张");
	public QuoteKGraphBodyPanel()
	{
		qc = QuoteController.getQuoteControllerInstance();
		
		
		setLayout(new BorderLayout());
		
				
		pContentPane = new JPanel(new BorderLayout());
		showLabel = new JLabel();
		add(showLabel);
		setVisible(true);
		String text = "<html><img src='http://image.sinajs.cn/newchart/daily/n/"+qc.controller.StockMarket+qc.controller.StockCode+".gif'/><html>";
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
	
	public void refresh()
	{
		String text = "<html><img src='http://image.sinajs.cn/newchart/daily/n/"+qc.controller.StockMarket+qc.controller.StockCode+".gif'/><html>";
		showLabel.setText(text);
	}
	 public void actionPerformed(ActionEvent e) {
		
		 if(e.getSource() == bNext)
			{
				qc.controller.CodeIndexNext();
				
				//System.out.println(current + " " + total);
			}
			else if(e.getSource() == bPrevious)
			{
				qc.controller.CodeIndexprevious();
			}
		 	
		 refresh();
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
		    QuoteKGraphBodyPanel tmp = new QuoteKGraphBodyPanel();
		    frame.add(tmp);
		    frame.setSize(1000,720);
		    frame.setVisible(true);

		  }
}
