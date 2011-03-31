/*
 * 历史数据的控制
 */
package BigWise.Controller;
import javax.swing.*;

import BigWise.DataSpider.History.HistoryDataSpider;
import BigWise.Model.StockHistoryData;
import BigWise.Model.StockInfo;
import BigWise.Model.StockQuoteData;
import BigWise.Utility.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import java.util.Vector;
public class HistoryController extends Observable{
	private static HistoryController hc = new HistoryController();
	
	public final double ROWPERPAGE = 19;
	
	public Vector<StockHistoryData> HistoryDataList = new Vector<StockHistoryData>();					//  历史数据 
	public int total = 0;
	public int current = 0;
	
	public	Controller controller;
	// 单子模式
	public static HistoryController getHistoryControllerInstance()
	{
		return hc ;
	}
	private HistoryController()
	{
		controller = Controller.getControllerInstance();
		init(controller.StockCode);	
	}
	
	// 获取数据
	public void init(String code)
	{
		HistoryDataList.clear();

		System.out.println(code);
		try {
			Connection conn = DBUtil.getDbConn();
			Statement stmt = conn.createStatement();

			ResultSet result;

			result = stmt
					.executeQuery(" select * from HistoryData where code = '"
							+ code + "' order by StockDate  asc;");
			while (result.next()) {

				String date = result.getString("StockDate");
				double OpenPrice = result.getDouble("OpenPrice");
				double ClosePrice = result.getDouble("ClosePrice");
				double MaxPrice = result.getDouble("MaxPrice");
				double MinPrice = result.getDouble("MinPrice");
				double TradeAccout = result.getDouble("TradeAccount");
				double TotalTrade = result.getDouble("totalTrade");

				StockHistoryData history = new StockHistoryData(code, date,
						OpenPrice, ClosePrice, MaxPrice,
						MinPrice, TradeAccout,TotalTrade);
				// System.out.println(quote.toString() );
				HistoryDataList.add(history);
			}
			result.close();
			stmt.close();
			DBUtil.closeConnection(conn);

		} catch (SQLException e) {
			System.out.println("SQL Failed");
		}
		// 算出总数，求出第一页
		total = (int)Math.ceil(HistoryDataList.size()/ROWPERPAGE);
		
		current = 1;	
	}
	
	//列表的选择
	public void pageNext()
	{
		if(current >= total)
		{		
			current = total - 1;
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
	
	
	public double getMostMin()
	{
		double mostMin = 10000;
		for(int i = 0 ;  i < HistoryDataList.size(); ++i)
		{
			StockHistoryData history = (StockHistoryData)HistoryDataList.elementAt(i);
			double openPrice = history.OpenPrice;
			double minPrice = history.MinPrice;
			if(openPrice == 0)
				continue;
			else
			{
				if(minPrice < mostMin)
					mostMin	= minPrice;
			}
			
		}
		
		return mostMin;
	}
	
	public double getMostMax()
	{
		double mostMax = 0;
		for(int i = 0 ;  i < HistoryDataList.size(); ++i)
		{
			StockHistoryData history = (StockHistoryData)HistoryDataList.elementAt(i);
			double openPrice = history.OpenPrice;
			double maxPrice = history.MaxPrice;
			if(openPrice == 0)
				continue;
			else
			{
				if(maxPrice > mostMax)
					mostMax	= maxPrice;
			}	
		}
		
		return mostMax;
	}
	
	public static void main(String args[])
	{
		HistoryController hc =HistoryController.getHistoryControllerInstance();
		System.out.println(hc.getMostMin());
		System.out.println(hc.getMostMax());
	}
	
}
