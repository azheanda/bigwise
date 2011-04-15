package BigWise.DataSpider.CapFlow;
import java.net.*;

import BigWise.DataSpider.History.HistoryDataSpider;
import BigWise.Model.StockCapFlowDetail;
import BigWise.Model.StockHistoryData;
import BigWise.Model.StockInfo;
import BigWise.Model.StockQuoteData;
import BigWise.Utility.Utility;

import java.io.*;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CapFlowDataSpider {
	
	
	public static Vector<StockCapFlowDetail> GetCapFlowByCode(String code) throws IOException
	{
		
		// 资金流数据 成交明细
		Vector<StockCapFlowDetail> CapFlowDetailList = new Vector<StockCapFlowDetail>();
		
		
		String market = Utility.getMarket(code);
		
		String url = "http://vip.stock.finance.sina.com.cn/quotes_service/view/vMS_tradedetail.php?symbol="+market+code;

		String html_text= CapFlowDataSpider.getDocumnetAt(url);
		
		Pattern gp_source = Pattern.compile("<th>(\\d{2}:\\d{2}:\\d{2})</th>|<td>([%\\d+.,-]*?)</td>");
		Matcher m = gp_source.matcher(html_text);
		
		
		while (m.find())
		{
			
			StockCapFlowDetail capflowData = new StockCapFlowDetail();
			capflowData.StockCode = code;								// 代码
			
			capflowData.StockTime = String.valueOf(m.group(1));			// 时间
			
			m.find();
			capflowData.Price = String.valueOf(m.group(2));			// 成交价
			
			m.find();
			capflowData.UpDown = String.valueOf(m.group(2));			// 涨跌幅
			
			m.find();
			capflowData.Variant = String.valueOf(m.group(2));			// 价格变动
			
			m.find();
			capflowData.Number = String.valueOf(m.group(2));			// 成交量
			
			m.find();
			capflowData.Accout = String.valueOf(m.group(2));			// 成交额
			
			
			CapFlowDetailList.add(capflowData);

		}
		
		//System.out.println(CapFlowDetailList.size());
		
		return CapFlowDetailList;
			
	}
	
	/*
	 * String GetDocumentAt(String url)
	 * static method, return the string of the html page.
	 */
	private static String getDocumnetAt(String urlString) {
		StringBuffer html_text = new StringBuffer();
		try {
			// 创建指向股票网址的链接
			URL url = new URL(urlString);
			// 创建链接
			URLConnection uc = url.openConnection();
			// 创建输入流
			BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			// 将网页内容放到缓冲区
			String line = null;
			while ((line = reader.readLine()) != null) {
				html_text.append(line + " ");
			}
			// 关闭输入流
			reader.close();
		} catch (MalformedURLException e) {
			System.out.print("invalid url:" + urlString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return html_text.toString();
	}

	
	public static void main(String[] args)
	{
		Vector<StockCapFlowDetail> CapFlowDetailList = new Vector<StockCapFlowDetail>();
		try
		{
			CapFlowDetailList = CapFlowDataSpider.GetCapFlowByCode("000001");
		}
		catch(IOException e)
		{
			
		}
		
		System.out.println(CapFlowDetailList.size());
		System.out.println((CapFlowDetailList.elementAt(0)).toString());
	}
}