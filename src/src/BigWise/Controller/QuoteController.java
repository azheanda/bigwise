/*
 * ʵʱ���ݵĿ���
 */
package BigWise.Controller;
import BigWise.DataSpider.History.HistoryDataSpider;
import BigWise.DataSpider.Quote.QuoteDataSpider;
import BigWise.Model.StockInfo;
import BigWise.Model.StockQuoteData;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
public class QuoteController extends Observable{
	
	// ʵʱ����Ŀ���
	private static QuoteController qc = new QuoteController();
	// ����ģʽ
	public static QuoteController getQuoteControllerInstance()
	{
		return qc ;
	}
	
	
	
	public String type;
	public String keyword;

	public Controller c;
	
	// ʵʱ������Ϣ
	public final double ROWPERPAGE = 19;
	public int total = 0;
	public int current = 0;
	public Vector<StockQuoteData> quote;
	public StockQuoteData[] QuoteData;									//   ���� 
	Object columnNames[] = { "ID", "����","����", "���¼�","��߼�","��ͼ�","���տ��̼�","�������̼�","����(��)","���(��)"};
	
	private QuoteController()
	{
		c = Controller.getControllerInstance();
		
		type = "market";
		keyword = "sh";
		init();	
	}
	
	// ��ȡ����
	public void init()
	{
		
		GetData(type,keyword);
	}
	
	// ��ȡ����
	public void GetData(String type, String keyword)
	{
		QuoteData = new StockQuoteData[StockInfo.getStockListNumberByKeyword(type,keyword)]; 
		try
		{
			QuoteData =  QuoteDataSpider.GetQuoteDataByKeyword(type,keyword);
		}
		catch (IOException ex)
		{
			System.out.println("failed");
		}	
		// ��������������һҳ
		//System.out.println(QuoteData.length);
		total = (int)Math.ceil(QuoteData.length/ROWPERPAGE);
		//System.out.println(total);
		current = 1;
	}
	
	// ����ģ��
	public DefaultTableModel GetModel()
	{
		
		//������
	    int column = columnNames.length;
	    
	    int row	= (int)ROWPERPAGE;
	    if(current == total)
	    {
	    	row = QuoteData.length % row;
	    }
	    //System.out.println(column + " " + row);
	    Object[][] rowData =  new Object[row][column];
	    int begin =0 , end = 0;
	    
	    
	    begin = (int)((current-1)*ROWPERPAGE);
	    
	    if(current == total)
	    {
	    	end = QuoteData.length;
	    }
	    else
	    {
	    	end = (int)(current*ROWPERPAGE);
	    }
	    
	    for(int i = begin ; i < end ; ++i)
	    {
	    	int ID = (int)(i % ROWPERPAGE);
	    	DecimalFormat df = new DecimalFormat("0.00");
	    	rowData[ID][0] = i + 1;
	    	rowData[ID][1] = QuoteData[i].StockCode;
	    	rowData[ID][2] = QuoteData[i].StockName;
	    	rowData[ID][3] = df.format(QuoteData[i].CurrentPrice);
	    	rowData[ID][4] = df.format(QuoteData[i].HighPriceToday);
	    	rowData[ID][5] = df.format(QuoteData[i].LowPriceToday);
	    	rowData[ID][6] = df.format(QuoteData[i].OpenPriceToday);
	    	rowData[ID][7] = df.format(QuoteData[i].ClosePriceYestoday);
	    	rowData[ID][8] = df.format(QuoteData[i].TotalTrade/1000000);	// ÿ100��Ϊ1��
	    	rowData[ID][9] = df.format(QuoteData[i].TradeAccount/100000000);
	    }
	    DefaultTableModel  model = new DefaultTableModel(rowData,columnNames);
	    
	    return model;
	}
	
	//�б��ѡ��
	public void pageNext()
	{
		if(current >= total)
		{		
			current = total;
			return;
		}
		else
			current++;
	}
	
	public void pagePrevious()
	{
		if(current <= 1)
		{
			current = 1;
			return;
		}
		else 
			current--;
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
	

	
	public static void main(String args[])
	{
		QuoteController qc =QuoteController.getQuoteControllerInstance();
	}
	
}
