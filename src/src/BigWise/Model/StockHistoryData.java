package BigWise.Model;

import java.util.Date;

/*
 * 历史数据的数据结构
 */
public class StockHistoryData {
	public String StockCode;				// 股票代码
	public String StockDate;				// 日期
	public double OpenPrice;				// 开盘价
	public double ClosePrice;				// 收盘价
	public double MaxPrice;					// 最高价格
	public double MinPrice;					// 最低价格
	public double TradeAccount;				// 交易量
	public double TotalTrade;				// 交易金额
	
	public StockHistoryData()
	{}
	
	public StockHistoryData(String code, String date, double open, double close, double max, double min, double number, double amount)
	{
		StockCode = code;
		StockDate = date;
		OpenPrice = open;
		ClosePrice = close;
		MaxPrice = max;
		MinPrice = min;
		TradeAccount = number;
		TotalTrade = amount;
	}
	public String toString()
	{
		return StockCode + "|" + StockDate + "|" + OpenPrice + "|" + ClosePrice + "|" + MaxPrice + "|" + MinPrice + "|"
			+ TradeAccount + "|" + TotalTrade;
	}
}
