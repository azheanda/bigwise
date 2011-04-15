package BigWise.DataSpider.Quote;
import java.net.*;

import BigWise.Model.StockInfo;
import BigWise.Model.StockQuoteData;

import java.io.*;
import java.util.Vector;

public class QuoteKGraphSpider {
	
	
	public static StockQuoteData[] GetQuoteDataByKeyword(String type,String keyword) throws IOException
	{
		
		// ��Ʊ�б�
		Vector<StockInfo> StockList = StockInfo.getStockListByKeyword(type, keyword);
		// ��Ʊ�б��еĹ�Ʊ��ֻ��
		int StockNumber = StockList.size();
		
		// ��������
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
		quoteData.StockCode = code;																	// ��Ʊ����
		quoteData.StockName = name;																	// ��Ʊ����
		quoteData.StockDate = tmp[30];																// ����
		quoteData.StockTime = tmp[31].substring(0,tmp[31].length()-3);								// ʱ��
		quoteData.OpenPriceToday = Double.parseDouble(tmp[1]); 										// ���տ��̼۸�
		quoteData.ClosePriceYestoday = Double.parseDouble(tmp[2]); 									// �������̼۸�
		quoteData.CurrentPrice = Double.parseDouble(tmp[3]); 										// ���ڼ۸�
		quoteData.HighPriceToday = Double.parseDouble(tmp[4]); 										// ������߼۸�
		quoteData.LowPriceToday = Double.parseDouble(tmp[5]); 										// ������ͼ۸�
		quoteData.TotalNumber = Double.parseDouble(tmp[8]); 											// ���ճɽ�����
		quoteData.TotalMoney = Double.parseDouble(tmp[9]); 										//�����ܽ��׶�
		// ��Ʊ���ǵ��Ǹ��������̼۸���Ƚ�
		if(quoteData.CurrentPrice < quoteData.ClosePriceYestoday)
			quoteData.StockColor = 2;//��ɫ
		else if(quoteData.CurrentPrice >= quoteData.ClosePriceYestoday)
			quoteData.StockColor = 1;//��ɫ
		else if((int)(quoteData.CurrentPrice) ==  0)
			quoteData.StockColor = 0;//��ɫ
		
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