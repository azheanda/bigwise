package BigWise.UI;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BigWise.DataSpider.*;
import BigWise.DataDefine.*;

public class QuoteBodyPanel extends JPanel implements ActionListener{
   
	JPanel pLeftPanel;
	JScrollPane pContentPane;
	JButton   bALL   =   new   JButton( "���й�Ʊ�б� ");
	JButton   bSH   =   new   JButton( "��A ");
    JButton   bSZ   =   new   JButton( "��A ");
    JButton   bHYBK   =   new   JButton( "��ҵ�б� "); 
    JButton   bGNBK   =   new   JButton( "������ "); 
    JButton   bDQBK   =   new   JButton( "�����б�"); 
    JTable tTable;
    Object columnNames[] = { "ID", "����","����", "���¼�","��߼�","��ͼ�","���տ��̼�","�������̼�","����","���","����","ʱ��"};
	public QuoteBodyPanel()
	{
		

		// �����
		pLeftPanel = new JPanel();
		pLeftPanel.setLayout(new GridLayout(1,6));
		pLeftPanel.setSize(100,30);
		pLeftPanel.add(bALL);
		bALL.addActionListener(this);
		pLeftPanel.add(bSH);
		bSH.addActionListener(this);
		pLeftPanel.add(bSZ);
		bSZ.addActionListener(this);
		pLeftPanel.add(bHYBK);
		bHYBK.addActionListener(this);
		pLeftPanel.add(bGNBK);
		bGNBK.addActionListener(this);
		pLeftPanel.add(bDQBK); 
		bDQBK.addActionListener(this);
		this.add(pLeftPanel,"North");
		
		StockQuoteData[] QuoteData = new StockQuoteData[StockInfo.GetStockNumberByMarket("all")]; 
		try
		{
			QuoteData =  DataSpider.GetQuoteDataByMarket("all");
		}
		catch (IOException ex)
		{
			System.out.println("failed");
		}

		//������
	    
	    Object[][] rowData =  new Object[QuoteData.length][columnNames.length];
	    for(int i = 0 ; i < QuoteData.length ; ++i)
	    {
	    	rowData[i][0] = i + 1;
	    	rowData[i][1] = QuoteData[i].StockCode;
	    	rowData[i][2] = QuoteData[i].StockName;
	    	rowData[i][3] = QuoteData[i].CurrentPrice;
	    	rowData[i][4] = QuoteData[i].HighPriceToday;
	    	rowData[i][5] = QuoteData[i].LowPriceToday;
	    	rowData[i][6] = QuoteData[i].OpenPriceToday;
	    	rowData[i][7] = QuoteData[i].ClosePriceYestoday;
	    	rowData[i][8] = QuoteData[i].TotalTrade;
	    	rowData[i][9] = QuoteData[i].TradeAccount;
	    	rowData[i][10] = QuoteData[i].StockDate;
	    	rowData[i][11] = QuoteData[i].StockTime;
	    }
	    DefaultTableModel  model = new DefaultTableModel(rowData,columnNames);
	    tTable = new JTable(model);
	    tTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tTable.getColumnModel().getColumn(0).setPreferredWidth(27);
	    tTable.getColumnModel().getColumn(1).setPreferredWidth(127);
	    tTable.getColumnModel().getColumn(2).setPreferredWidth(127);
	    tTable.getColumnModel().getColumn(3).setPreferredWidth(127);
	    tTable.getColumnModel().getColumn(4).setPreferredWidth(127);
	    tTable.setSize(1000,800);
	    pContentPane = new JScrollPane(tTable);
	    
	    this.add(pContentPane,"Center");
		
	}
	
	 public void actionPerformed(ActionEvent e) {
		String market = "";
		if(e.getSource() == bALL)
		{
			market = "all";
		}
		else if(e.getSource() == bSH)
		{
			market = "sh";	
		}
		else if(e.getSource() == bSZ)
		{
			market = "sz";
		}
		else
			market ="sh";
		StockQuoteData[] QuoteData = new StockQuoteData[StockInfo.GetStockNumberByMarket(market)]; 
		try
		{
			QuoteData =  DataSpider.GetQuoteDataByMarket(market);
		}
		catch (IOException ex)
		{
			System.out.println("failed");
		}

		//������   
	    Object[][] rowData =  new Object[QuoteData.length][columnNames.length];
	    for(int i = 0 ; i < QuoteData.length ; ++i)
	    {
	    	rowData[i][0] = i + 1;
	    	rowData[i][1] = QuoteData[i].StockCode;
	    	rowData[i][2] = QuoteData[i].StockName;
	    	rowData[i][3] = QuoteData[i].CurrentPrice;
	    	rowData[i][4] = QuoteData[i].HighPriceToday;
	    	rowData[i][5] = QuoteData[i].LowPriceToday;
	    	rowData[i][6] = QuoteData[i].OpenPriceToday;
	    	rowData[i][7] = QuoteData[i].ClosePriceYestoday;
	    	rowData[i][8] = QuoteData[i].TotalTrade;
	    	rowData[i][9] = QuoteData[i].TradeAccount;
	    	rowData[i][10] = QuoteData[i].StockDate;
	    	rowData[i][11] = QuoteData[i].StockTime;
	    }
	    
	    DefaultTableModel updateModel = new DefaultTableModel(rowData,columnNames);
	    tTable.setModel(updateModel);
	    pContentPane.getViewport().add(tTable,null);
	    pContentPane.validate();
	 }
	 
	 public static void main(String args[]) {
		    JFrame frame = new JFrame("test");
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    QuoteBodyPanel tmp = new QuoteBodyPanel();
		    frame.add(tmp);
		    frame.setSize(1600,400);
		    frame.setVisible(true);

		  }
}
