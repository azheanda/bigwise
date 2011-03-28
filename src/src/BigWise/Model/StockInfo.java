package BigWise.Model;

import java.util.Vector;
import BigWise.Utility.DBUtil;
import java.sql.*;
public class StockInfo {
	
	public String code;				// 股票名称
	public String name;				// 股票代码
	public String market;				// 股票市场
	public String industry;					// 股票行业（行业板块）
	public String province;					// 股票的省份（省份板块）
	
	// 全属性的股票列表
	public StockInfo(String code,String name, String market, String industry, String province)
	{
		this.code = code;
		this.name = name;
		this.market = market;
		this.industry = industry;
		this.province = province;
	}
	// 根据关键值获取码值
	public static int getStockListNumberByKeyword(String type,String keyword)
	{
		Vector<StockInfo> StockList = getStockListByKeyword(type,keyword);
		return StockList.size();
	}
	// type包括市场，行业和地区
	public static Vector<StockInfo> getStockListByKeyword(String type, String keyword)
	{
		Vector<StockInfo> StockList = new Vector<StockInfo>();
		try
		{
			Connection conn = DBUtil.getDbConn();
			
			Statement stmt = conn.createStatement();
			ResultSet stockdata;
			//System.out.println(market);
			if(keyword != "*")
				stockdata = stmt.executeQuery("select * from stockinfo where "+ type + "='"+ keyword + "';");
			else
				stockdata = stmt.executeQuery("select * from stockinfo");
			while (stockdata.next()) 
			 {
			       String code = stockdata.getString("code");
			       String name = stockdata.getString("name");
			       String market = stockdata.getString("market");
			       String industry = stockdata.getString("industry");
			       String province = stockdata.getString("province");
			       
			      
			       StockInfo  stockinfo = new StockInfo(code,name,market,industry,province);
			       //System.out.println(stockinfo.code );
			       StockList.add(stockinfo);
			  } 
			stockdata.close();
			stmt.close();
			DBUtil.closeConnection(conn);
		}
		catch (SQLException ex)
		{
			System.out.println("failed");
		}
		return StockList;
		
	}
	
	// 所有股票的名称
	public static Vector<String> getStockNameList()
	{
		Vector<String> StockNameList = new Vector<String>();
		try
		{
			Connection conn = DBUtil.getDbConn();
			
			Statement stmt = conn.createStatement();
			ResultSet stockdata;
			//System.out.println(market);
				stockdata = stmt.executeQuery("select name from stockinfo");

			while (stockdata.next()) 
			 {
			       String name = stockdata.getString("name");

			       //System.out.println(stockinfo.code );
			       StockNameList.add(name);
			  } 
			stockdata.close();
			stmt.close();
			DBUtil.closeConnection(conn);
		}
		catch (SQLException ex)
		{
			System.out.println("failed");
		}
		return StockNameList;	
	}
	// 所有股票的代码
	public static Vector<String> getStockCodeList()
	{
		Vector<String> StockNameList = new Vector<String>();
		try
		{
			Connection conn = DBUtil.getDbConn();
			
			Statement stmt = conn.createStatement();
			ResultSet stockdata;
			//System.out.println(market);
				stockdata = stmt.executeQuery("select code from stockinfo");

			while (stockdata.next()) 
			 {
			       String code = stockdata.getString("code");

			       //System.out.println(stockinfo.code );
			       StockNameList.add(code);
			  } 
			stockdata.close();
			stmt.close();
			DBUtil.closeConnection(conn);
		}
		catch (SQLException ex)
		{
			System.out.println("failed");
		}
		return StockNameList;	
	}
	// 获取所有类型的 子类  类型包括：市场，板块，省份
	public static Vector<String> getTypeList(String type)
	{
		Vector<String> IndustryList = new Vector<String>();
		try
		{
			Connection conn = DBUtil.getDbConn();
			
			Statement stmt = conn.createStatement();
			ResultSet stockdata = stmt.executeQuery("select distinct "+ type +" from stockinfo;");

			while (stockdata.next()) 
			{
				IndustryList.add(stockdata.getString(type));
			} 
			
			stockdata.close();
			stmt.close();
			DBUtil.closeConnection(conn);
		}
		catch (SQLException ex)
		{
			System.out.println("failed");
		}

		return IndustryList;
	}
	
	
	
	
	public static void main(String args[])
	{

		Vector<String> NameList = StockInfo.getStockNameList();

		Vector<String> CodeList = StockInfo.getStockCodeList();
		for(int i = 0; i < NameList.size(); ++i)
		{
			System.out.print(NameList.elementAt(i));
			System.out.println(CodeList.elementAt(i));
		}
	}

}
