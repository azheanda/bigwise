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


public class NewsSinaBodyPanel extends JPanel implements ActionListener{


	Vector<StockNews> NewsList = new Vector<StockNews>();
    int total;
    int current;
   
    JPanel pTopPanel;
    JLabel titleLabel;
    JLabel timeLabel;
    
    JScrollPane pContentPane;
    JTextArea showArea;
    
    JPanel pDownPanel;
    JButton bNext = new JButton("��һ��");
    JButton bPrevious = new JButton("��һ��");
	public NewsSinaBodyPanel()
	{
		for (int i = 1; i <= 10; ++i)
		{
			StockNews news = new StockNews();
			news.NewsDate = "2010-1-2";
			news.NewsTime = "18:00:2";
			news.NewsTitle = "���ǵ�"+ i +"������";
			news.NewsContent = "��" + i + "�˶����湫˾�����μ���̩�λ��������µ�һ�Ҽ����Ρ��λ������κ�������ҩ����ֳ�Լ������Դ����Ϊһ��Ĵ���";
			NewsList.add(news);
		}
		
		total = NewsList.size() - 1;
		current = 0;
		
		setLayout(new BorderLayout());
		pTopPanel = new JPanel(new GridLayout(2,1));
		// ����
		titleLabel = new JLabel();
		pTopPanel.add(titleLabel);
		//ʱ��
		timeLabel = new JLabel();
		
		pTopPanel.add(timeLabel);
		add(pTopPanel,"North");
		
		//����
		showArea = new JTextArea();
		showArea.setSize(100,100);
		pContentPane = new JScrollPane(showArea);
		add(pContentPane,"Center");
		
		showNews(current);
		
		//��ҳ
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
		 timeLabel.setText(NewsList.elementAt(current).NewsDate +" " +  NewsList.elementAt(current).NewsTime);
		 showArea.setText(NewsList.elementAt(current).NewsContent);
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
