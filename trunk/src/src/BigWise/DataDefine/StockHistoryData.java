package BigWise.DataDefine;

import java.util.Date;

/*
 * 历史数据的数据结构
 */
public class StockHistoryData {
	public String StockCode;				// 股票代码
	public String StockDate;				// 日期
	public String OpenPrice;				// 开盘价
	public String ClosePrice;				// 收盘价
	public String MaxPrice;					// 最高价格
	public String MinPrice;					// 平均价格
	public String TradeAccount;				// 交易量
	public String TotalTrade;				// 交易金额
	
	public String toString()
	{
		return StockCode + "|" + StockDate + "|" + OpenPrice + "|" + ClosePrice + "|" + MaxPrice + "|" + MinPrice + "|"
			+ TradeAccount + "|" + TotalTrade;
	}
}
