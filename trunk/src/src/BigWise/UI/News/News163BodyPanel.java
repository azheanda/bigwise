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
import BigWise.DataSpider.*;
import BigWise.DataSpider.Quote.QuoteDataSpider;
import BigWise.DataDefine.*;

import java.text.DecimalFormat;
import java.lang.Math;


public class News163BodyPanel extends JPanel implements ActionListener{


	private static final String InformationSpider = null;
	Vector<StockNews> NewsList = new Vector<StockNews>();
    int total;
    int current;
   
    JPanel pTopPanel;
    JTextArea titleLabel;
    
    JPanel pContentPanel;
    JTextArea contentLabel;    
    JTextArea judgeLabel;
    
    
    JPanel pDownPanel;
    JButton bNext = new JButton("下一条");
    JButton bPrevious = new JButton("上一条");
	
    public News163BodyPanel()
	{
		NewsList = InfomationSpider.GetInfomationData("", "");
		System.out.println(NewsList.size());
		
		total = NewsList.size() - 1;
		current = 0;
		
		setLayout(new BorderLayout());
		pTopPanel = new JPanel(new GridLayout(2,1));
		// 标题
		titleLabel = new JTextArea();
		pTopPanel.add(titleLabel);
		add(pTopPanel,"North");
		
		//内容
		pContentPanel = new JPanel();
		pContentPanel.setLayout(new GridLayout(2,1));
		contentLabel = new JTextArea();
		judgeLabel = new JTextArea();	
		contentLabel.setLineWrap(true);
		judgeLabel.setLineWrap(true);
		contentLabel.setEditable(true);
		judgeLabel.setEditable(true);
		pContentPanel.add(contentLabel);
		pContentPanel.add(judgeLabel);
		showNews(current);
		add(pContentPanel,"Center");
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

		 	showNews(current);
			//System.out.println(text);
	 }
	
	 public void showNews(int index)
	 {
		 titleLabel.setText(NewsList.elementAt(current).NewsTitle);
		 contentLabel.setText(NewsList.elementAt(current).NewsContent);
		 judgeLabel.setText(NewsList.elementAt(current).NewsJudge);
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
		    News163BodyPanel tmp = new News163BodyPanel();
		    frame.add(tmp);
		    frame.setSize(1000,720);
		    frame.setVisible(true);

		  }
}
