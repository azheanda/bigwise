/*
 * 实时数据的控制
 */
package BigWise.Controller;
import BigWise.DataSpider.History.HistoryDataSpider;
import BigWise.DataSpider.News.InfomationSpider;
import BigWise.DataSpider.News.JYTSSpider;
import BigWise.DataSpider.Quote.QuoteDataSpider;
import BigWise.Model.StockInfo;
import BigWise.Model.StockNews;
import BigWise.Model.StockQuoteData;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
public class JYTSController extends Observable{
	
	// 实时行情的控制
	private static JYTSController nc = new JYTSController();
	// 单子模式
	public static JYTSController getNewsControllerInstance()
	{
		return nc ;
	}
	
	
	public Vector<StockNews> JYTSList = new Vector<StockNews>();
	// 实时数据信息
	public final double NewsPerPage = 30;
	public int total = 0;
	public int current = 0;

	private JYTSController()
	{
		init();	
	}
	
	// 获取数据
	public void init()
	{
		JYTSList = JYTSSpider.GetInfomationData("", "");
		//System.out.println(NewsList.size());
		total = (int)Math.ceil(JYTSList.size()/NewsPerPage);
		//System.out.println(total);
		current = 1;
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
		JYTSController qc =JYTSController.getNewsControllerInstance();
	}
	
}
