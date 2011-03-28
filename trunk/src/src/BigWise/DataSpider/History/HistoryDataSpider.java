package BigWise.DataSpider.History;
import java.io.*;
import java.net.*;
import java.util.Vector;
import java.util.regex.*;
import java.util.logging.Logger;

import BigWise.Model.StockHistoryData;
import BigWise.Model.StockInfo;
import BigWise.Model.StockQuoteData;

/*
 * This Class Used to Get Day HisData.
 * method:
 * 最多可以查看20天的历史数据
 *  */

public class HistoryDataSpider {

	public StockHistoryData[] GetHistoryDataList(String begin, String end)
	{

		// 股票列表
		Vector<StockInfo> StockList = StockInfo.getStockListByKeyword("market", "*");
 		
		// 股票列表中的股票的只数
 		int StockNumber = StockList.size();
 		
 		// 行情数据
 		
		StockHistoryData[] HistoryData = new StockHistoryData[StockNumber];
 		
 		//System.out.println(StockNumber);
 		for(int i = 0; i < StockNumber; ++i)
 		{
 			
 			HistoryData[i] = GetDayData(StockList.elementAt(i),begin,end);
 		}
 		return HistoryData;
	}
	/*
	 * Back Up Data
	 */
	public StockHistoryData GetDayData(StockInfo stock,String begin, String end)
	{
		StockHistoryData tmp = new StockHistoryData();
		HistoryDataSpider test = new HistoryDataSpider();
		String code = stock.code;

			//test.extractor("http://hq.sinajs.cn/list=sh600000");
			test.extractor(code,begin,end);


		return tmp;
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

	public Vector<StockHistoryData> extractor(String code,String begin, String end) {
		Vector<StockHistoryData> HistoryDataList = new Vector<StockHistoryData>();
		
		String url = "http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/"+code +".phtml";
		
		// 文件输出流
		//FileOutputStream fos = new FileOutputStream(".\\"+code+".txt");
		//OutputStreamWriter ows = new OutputStreamWriter(fos);
		//try {
			// 获得网页文本内容
			String str = HistoryDataSpider.getDocumnetAt(url);
			// 创建提取股票数据来源的正则表达式
			Pattern gp_source = Pattern.compile("(?<=<th colspan=\"7\">|\"blue\">|</FONT>).*?(?=<|FONT|</th>)");
			Matcher mc = gp_source.matcher(str);
			String s1;
//			while (mc.find()) {
//				// 提取股票数据来源
//				s1 = String.valueOf(mc.group());
//				ows.write(s1);
//				System.out.printf("%s", mc.group());
//			}
//			System.out.println();
//			ows.write("\r\n");
//
//			// 输出股票数据条目
//			String s2;
//			Pattern gp_item = Pattern
//					.compile("(?<=<strong>).*?(?=</strong>)");
//			Matcher n = gp_item.matcher(str);
//			while (n.find()) {
//				s2 = String.valueOf(n.group());
//				ows.write(s2 + "          ");
//				System.out.printf("%-42s", n.group());
//			}

			// 提取股票数据详细情况
			Pattern gp_data = Pattern.compile("((?<=date=)(\\w*?)).*?(?=('>))|((?<=center\">)(\\d{1,7}?)).*?(?=(</div>))");
			Matcher m = gp_data.matcher(str);
			
			while (m.find())
			{
				StockHistoryData historyData = new StockHistoryData();
				historyData.StockCode = code;								// 代码
				historyData.StockDate = String.valueOf(m.group());			// 日期
				
				m.find();
				historyData.OpenPrice = String.valueOf(m.group());			// 开盘价
				
				m.find();
				historyData.MaxPrice = String.valueOf(m.group());
				
				m.find();
				historyData.ClosePrice = String.valueOf(m.group());
						
				m.find();
				historyData.MinPrice = String.valueOf(m.group());
				
				m.find();
				historyData.TradeAccount = String.valueOf(m.group());
				
				m.find();
				historyData.TotalTrade = String.valueOf(m.group());
				

				HistoryDataList.add(historyData);

					//ows.write("\r\n");
			}
		
		return	HistoryDataList;
	}

	public static void main(String[] args) 
	{
		HistoryDataSpider hds = new HistoryDataSpider();
		Vector<StockHistoryData> HistoryDataList = hds.extractor("600000","", "");
		System.out.println(HistoryDataList.size());
		for(int i = 0 ; i < HistoryDataList.size(); ++ i)
		{
			System.out.println(HistoryDataList.elementAt(i).StockDate);
		}
	}

}
