/*
 * 实时数据的控制
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
	
	// 实时行情的控制
	private static QuoteController qc = new QuoteController();
	// 单子模式
	public static QuoteController getQuoteControllerInstance()
	{
		return qc ;
	}
	
	
	
	public String type;
	public String keyword;

	public Controller c;
	
	// 实时数据信息
	public final double ROWPERPAGE = 19;
	public int total = 0;
	public int current = 0;
	public Vector<StockQuoteData> quote;
	public StockQuoteData[] QuoteData;									//   数据 
	Object columnNames[] = { "ID", "代码","名称", "最新价","最高价","最低价","今日开盘价","昨日收盘价","总手(万)","金额(亿)"};
	
	private QuoteController()
	{
		c = Controller.getControllerInstance();
		
		type = "market";
		keyword = "sh";
		init();	
	}
	
	// 获取数据
	public void init()
	{
		
		GetData(type,keyword);
	}
	
	// 获取数据
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
		// 算出总数，求出第一页
		//System.out.println(QuoteData.length);
		total = (int)Math.ceil(QuoteData.length/ROWPERPAGE);
		//System.out.println(total);
		current = 1;
	}
	
	// 设置模型
	public DefaultTableModel GetModel()
	{
		
		//内容栏
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
	    	rowData[ID][8] = df.format(QuoteData[i].TotalTrade/1000000);	// 每100股为1手
	    	rowData[ID][9] = df.format(QuoteData[i].TradeAccount/100000000);
	    }
	    DefaultTableModel  model = new DefaultTableModel(rowData,columnNames);
	    
	    return model;
	}
	
	//列表的选择
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
