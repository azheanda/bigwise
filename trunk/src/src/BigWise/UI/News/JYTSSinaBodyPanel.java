package BigWise.UI.News;

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

import BigWise.Controller.JYTSController;
import BigWise.Controller.NewsController;
import BigWise.DataSpider.*;
import BigWise.DataSpider.Quote.QuoteDataSpider;
import BigWise.Model.*;

import java.text.DecimalFormat;
import java.lang.Math;


public class JYTSSinaBodyPanel extends JPanel implements ActionListener{

	public JYTSController jc;
	Vector<StockNews> JYTSList = new Vector<StockNews>();
	
	String NewsType;
   
    JPanel pContentPanel;
    JLabel[] contentLabel;    
    
    
    JPanel pDownPanel;
    JButton bNext = new JButton("下一页");
    JButton bPrevious = new JButton("上一页");
	
    public JYTSSinaBodyPanel()
	{
    	jc = JYTSController.getNewsControllerInstance();
    	
    	JYTSList = jc.JYTSList;
		//System.out.println(NewsList.size());
		
		setLayout(new BorderLayout());
		
		//内容
		pContentPanel = new JPanel();
		contentLabel = new JLabel[(int)jc.NewsPerPage];
		showNews();
		
		
		//翻页
		pDownPanel = new JPanel();
	    pDownPanel.add(bPrevious);
	    bPrevious.addActionListener(this);
	    pDownPanel.add(bNext);
	    bNext.addActionListener(this);
	    pDownPanel.setSize(1000,100);
	    add(pDownPanel,"South");
		
	    setSize(1000,300);
		setVisible(true);
	    
	}
	
	
	 public void actionPerformed(ActionEvent e) {
		
		 if(e.getSource() == bNext)
			{
			 jc.pageNext();
			}
			else if(e.getSource() == bPrevious)
			{
				jc.pagePrevious();
			}	
		 pContentPanel.removeAll();
		 showNews();
	 }
	
	 public void showNews()
	 { 
		 pContentPanel.setLayout(new GridLayout((int)jc.NewsPerPage,1));
		 
		 int begin =(int)( (jc.current - 1) * jc.NewsPerPage);
		 int end =(int)(  jc.current * jc.NewsPerPage);
		 if( jc.current == jc.total)
		 {
			 end = JYTSList.size();
		 }
		 
		 int ID = 0;
		 for(int i = begin; i < end; ++i)
		 {
			 ID = i % ((int)jc.NewsPerPage);
			 //System.out.println(ID);
			 StockNews news = JYTSList.elementAt(i);
			 contentLabel[ID] = new JLabel();
			 contentLabel[ID].setText("<html><div style='" +
			 		"border:1px solid black;background-color:white;color:gray;'>" + news.NewsContent + "</div></html>");
			 pContentPanel.add(contentLabel[ID]);
		 }
		  
		 pContentPanel.validate();
		 this.add(pContentPanel,"Center");
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
		    JFrame frame = new JFrame("交易提示");
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    JYTSSinaBodyPanel tmp = new JYTSSinaBodyPanel();
		    frame.add(tmp);
		    frame.setSize(1000,720);
		    frame.setVisible(true);

		  }
}
