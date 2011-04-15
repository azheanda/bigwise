package BigWise.DataSpider.Quote;
import java.net.*;

import BigWise.Model.StockInfo;
import BigWise.Model.StockQuoteData;

import java.io.*;
import java.util.Vector;

public class QuoteKGraphSpider {
	
	
	public static StockQuoteData[] GetQuoteDataByKeyword(String type,String keyword) throws IOException
	{
		
		// 股票列表
		Vector<StockInfo> StockList = StockInfo.getStockListByKeyword(type, keyword);
		// 股票列表中的股票的只数
		int StockNumber = StockList.size();
		
		// 行情数据
		StockQuoteData[] QuoteData = new StockQuoteData[StockNumber];
		
		//System.out.println(StockNumber);
		for(int i = 0; i < StockNumber; ++i)
		{
			
				QuoteData[i] = GetQuoteDataByStockCode(StockList.elementAt(i));
		}
		return QuoteData;
	}
	
	
	public static StockQuoteData GetQuoteDataByStockCode(StockInfo stockinfo) throws IOException
	{
		String market = stockinfo.market;
		String code = stockinfo.code;
		String name = stockinfo.name;
		StringBuffer html_text = new StringBuffer();
		
		// Get the Data
		URL quote = new URL("http://hq.sinajs.cn/list="+market+code);
		// Read Line By Line
		BufferedReader br = new BufferedReader(new InputStreamReader(quote.openStream()));
		BufferedReader in=new BufferedReader(br);
		
		String line = null;
		while ((line = in.readLine()) != null) {
			html_text.append(line + " ");
		}
		String quoteResult = html_text.toString();
		String[] tmp = quoteResult.split(",");
		
		StockQuoteData quoteData = new StockQuoteData();
		quoteData.StockCode = code;																	// 股票代码
		quoteData.StockName = name;																	// 股票名称
		quoteData.StockDate = tmp[30];																// 日期
		quoteData.StockTime = tmp[31].substring(0,tmp[31].length()-3);								// 时间
		quoteData.OpenPriceToday = Double.parseDouble(tmp[1]); 										// 今日开盘价格
		quoteData.ClosePriceYestoday = Double.parseDouble(tmp[2]); 									// 昨日收盘价格
		quoteData.CurrentPrice = Double.parseDouble(tmp[3]); 										// 现在价格
		quoteData.HighPriceToday = Double.parseDouble(tmp[4]); 										// 今日最高价格
		quoteData.LowPriceToday = Double.parseDouble(tmp[5]); 										// 今日最低价格
		quoteData.TotalNumber = Double.parseDouble(tmp[8]); 											// 今日成交手数
		quoteData.TotalMoney = Double.parseDouble(tmp[9]); 										//今日总交易额
		// 股票的涨跌是跟昨日收盘价格相比较
		if(quoteData.CurrentPrice < quoteData.ClosePriceYestoday)
			quoteData.StockColor = 2;//绿色
		else if(quoteData.CurrentPrice >= quoteData.ClosePriceYestoday)
			quoteData.StockColor = 1;//红色
		else if((int)(quoteData.CurrentPrice) ==  0)
			quoteData.StockColor = 0;//绿色
		
		//System.out.println(quoteData.toString());
		
		return quoteData;
		
	}
	public static void main(String[] args)
	{
		try{
			QuoteKGraphSpider.GetQuoteDataByKeyword("market","sh");
		}
		catch(IOException e)
		{
			System.out.print("failed");
		}
		
	}
}