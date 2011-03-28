package BigWise.Model;

import java.util.Vector;
import BigWise.Utility.DBUtil;
import java.sql.*;
public class StockInfo {
	
	public String code;				// ��Ʊ����
	public String name;				// ��Ʊ����
	public String market;				// ��Ʊ�г�
	public String industry;					// ��Ʊ��ҵ����ҵ��飩
	public String province;					// ��Ʊ��ʡ�ݣ�ʡ�ݰ�飩
	
	// ȫ���ԵĹ�Ʊ�б�
	public StockInfo(String code,String name, String market, String industry, String province)
	{
		this.code = code;
		this.name = name;
		this.market = market;
		this.industry = industry;
		this.province = province;
	}
	// ���ݹؼ�ֵ��ȡ��ֵ
	public static int getStockListNumberByKeyword(String type,String keyword)
	{
		Vector<StockInfo> StockList = getStockListByKeyword(type,keyword);
		return StockList.size();
	}
	// type�����г�����ҵ�͵���
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
	
	// ���й�Ʊ������
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
	// ���й�Ʊ�Ĵ���
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
	// ��ȡ�������͵� ����  ���Ͱ������г�����飬ʡ��
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
