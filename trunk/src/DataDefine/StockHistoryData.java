package BigWise.DataDefine;

import java.util.Date;

/*
 * ��ʷ���ݵ����ݽṹ
 */
public class StockHistoryData {
	public String StockCode;				// ��Ʊ����
	public String StockDate;				// ����
	public String OpenPrice;				// ���̼�
	public String ClosePrice;				// ���̼�
	public String MaxPrice;					// ��߼۸�
	public String MinPrice;					// ƽ���۸�
	public String TradeAccount;				// ������
	public String TotalTrade;				// ���׽��
	
	public String toString()
	{
		return StockCode + "|" + StockDate + "|" + OpenPrice + "|" + ClosePrice + "|" + MaxPrice + "|" + MinPrice + "|"
			+ TradeAccount + "|" + TotalTrade;
	}
}
