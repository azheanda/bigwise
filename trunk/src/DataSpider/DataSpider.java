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
			// ����All�߼�
			if(market == "all")
			{
				if(i < StockInfo.GetStockNumberByMarket("sh"))
				{
					QuoteData[i] = GetQuoteDataByStockCode("sh",StockCodes[i],StockNames[i]);
				}
				else
					QuoteData[i] = GetQuoteDataByStockCode("sz",StockCodes[i],StockNames[i]);
			}
			// ��ͨ�г��߼�
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
		quoteData.StockCode = code;																	// ��Ʊ����
		quoteData.StockName = name;																	// ��Ʊ����
		quoteData.StockDate = tmp[30];																// ����
		quoteData.StockTime = tmp[31].substring(0,tmp[31].length()-3);								// ʱ��
		quoteData.OpenPriceToday = Double.parseDouble(tmp[1]); 										// ���տ��̼۸�
		quoteData.ClosePriceYestoday = Double.parseDouble(tmp[2]); 									// �������̼۸�
		quoteData.CurrentPrice = Double.parseDouble(tmp[3]); 										// ���տ��̼۸�
		quoteData.HighPriceToday = Double.parseDouble(tmp[4]); 										// ������߼۸�
		quoteData.LowPriceToday = Double.parseDouble(tmp[5]); 										// ������ͼ۸�
		quoteData.TotalTrade = Double.parseDouble(tmp[8]); 											// ���ճɽ�����
		quoteData.TradeAccount = Double.parseDouble(tmp[9]); 										// �����ܽ��׶�
		
		
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