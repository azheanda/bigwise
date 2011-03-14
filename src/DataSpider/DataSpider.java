package BigWise.DataSpider;
import java.net.*;
import BigWise.DataDefine.StockInfo;
import BigWise.DataDefine.StockQuoteData;
import java.io.*;


public class DataSpider {
	
	
	public static StockQuoteData[] GetQuoteDataByMarket(String market) throws IOException
	{
		
		StockQuoteData[] QuoteData = new StockQuoteData[StockInfo.GetStockNumberByMarket(market)];
		String[] StockCodes = StockInfo.getStockCodesByMarket(market);
		String[] StockNames = StockInfo.getStockNamesByMarket(market);
		
		for(int i = 0; i < StockInfo.GetStockNumberByMarket(market); ++i)
		{
			// 对于All逻辑
			if(market == "all")
			{
				if(i < StockInfo.GetStockNumberByMarket("sh"))
				{
					QuoteData[i] = GetQuoteDataByStockCode("sh",StockCodes[i],StockNames[i]);
				}
				else
					QuoteData[i] = GetQuoteDataByStockCode("sz",StockCodes[i],StockNames[i]);
			}
			// 普通市场逻辑
			else
				QuoteData[i] = GetQuoteDataByStockCode(market,StockCodes[i],StockNames[i]);
		}
		return QuoteData;
	}
	
	
	public static StockQuoteData GetQuoteDataByStockCode(String market,String code,String name) throws IOException
	{
		
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
		//System.out.println(quoteResult);
		String[] tmp = quoteResult.split(",");
		
		StockQuoteData quoteData = new StockQuoteData();
		quoteData.StockCode = code;																	// 股票代码
		quoteData.StockName = name;																	// 股票名称
		quoteData.StockDate = tmp[30];																// 日期
		quoteData.StockTime = tmp[31].substring(0,tmp[31].length()-3);								// 时间
		quoteData.OpenPriceToday = Double.parseDouble(tmp[1]); 										// 今日开盘价格
		quoteData.ClosePriceYestoday = Double.parseDouble(tmp[2]); 									// 昨日收盘价格
		quoteData.CurrentPrice = Double.parseDouble(tmp[3]); 										// 今日开盘价格
		quoteData.HighPriceToday = Double.parseDouble(tmp[4]); 										// 今日最高价格
		quoteData.LowPriceToday = Double.parseDouble(tmp[5]); 										// 今日最低价格
		quoteData.TotalTrade = Double.parseDouble(tmp[8]); 											// 今日成交手数
		quoteData.TradeAccount = Double.parseDouble(tmp[9]); 										// 今日总交易额
		
		
		//System.out.println(quoteData.toString());
		
		return quoteData;
		
	}
	public static void main(String[] args)
	{
		try{
			DataSpider.GetQuoteDataByMarket("all");
		}
		catch(IOException e)
		{
			System.out.print("failed");
		}
		
	}
}