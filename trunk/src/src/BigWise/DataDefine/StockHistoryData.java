package BigWise.Model;

import java.util.Date;

/*
 * ��ʷ���ݵ����ݽṹ
 */
public class StockHistoryData {
	public String StockCode;				// ��Ʊ����
	public String StockDate;				// ����
	public double OpenPrice;				// ���̼�
	public double ClosePrice;				// ���̼�
	public double MaxPrice;					// ��߼۸�
	public double MinPrice;					// ��ͼ۸�
	public double TradeAccount;				// ������
	public double TotalTrade;				// ���׽��
	
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
