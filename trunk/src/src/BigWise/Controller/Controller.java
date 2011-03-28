/*
 * 控制
 */
package BigWise.Controller;
import javax.swing.*;

import BigWise.DataSpider.History.HistoryDataSpider;
import BigWise.Model.StockHistoryData;
import BigWise.Model.StockInfo;

import java.util.Observable;
import java.util.Vector;
public class Controller extends Observable{
	private static Controller c = new Controller();
	
	public final double ROWPERPAGE = 19;
	
	// 页面的股票信息
	public Vector<String> CodeList;									//  股票列表
	public String StockCode;
	public String StockMarket;
	public int CodeIndex;											// 股票在股票列表中的位置
	public int CodeLength;
	
	// 单子模式
	public static Controller getControllerInstance()
	{
		return c ;
	}
	private Controller()
	{
		init();	
	}
	
	// 获取数据
	public void init()
	{	
		CodeList = StockInfo.getStockCodeList();		
		CodeLength = CodeList.size();
		
		CodeIndex =	0;			  
		StockCode = CodeList.elementAt(0);
		StockMarket = getMarket(StockCode);
	}
	
	
	// 股票的翻动,上下翻动股票列表
	public void CodeIndexNext()
	{
		if(CodeIndex >= CodeLength)
		{		
			CodeIndex = CodeLength - 1;
			return;
		}
		else
			CodeIndex++;
		StockCode = CodeList.elementAt(CodeIndex);
		StockMarket = getMarket(StockCode);
		CodeUpdate();
	}
	
	public void CodeIndexprevious()
	{
		if(CodeIndex <= 0)
		{
			CodeIndex = 0;
		}
		else 
			CodeIndex--;
		
		StockCode = CodeList.elementAt(CodeIndex);
		StockMarket = getMarket(StockCode);
		CodeUpdate();	
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
	
	// Observer的事件，股票发生转变
	public void CodeUpdate()
	{
		setChanged();
		notifyObservers(new Integer(CodeIndex));
	}
	
	public static void main(String args[])
	{
		Controller  c = Controller.getControllerInstance();
	}
	
}
