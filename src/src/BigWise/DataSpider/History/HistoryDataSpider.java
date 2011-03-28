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
 * �����Բ鿴20�����ʷ����
 *  */

public class HistoryDataSpider {

	public StockHistoryData[] GetHistoryDataList(String begin, String end)
	{

		// ��Ʊ�б�
		Vector<StockInfo> StockList = StockInfo.getStockListByKeyword("market", "*");
 		
		// ��Ʊ�б��еĹ�Ʊ��ֻ��
 		int StockNumber = StockList.size();
 		
 		// ��������
 		
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
			// ����ָ���Ʊ��ַ������
			URL url = new URL(urlString);
			// ��������
			URLConnection uc = url.openConnection();
			// ����������
			BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			// ����ҳ���ݷŵ�������
			String line = null;
			while ((line = reader.readLine()) != null) {
				html_text.append(line + " ");
			}
			// �ر�������
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
		
		// �ļ������
		//FileOutputStream fos = new FileOutputStream(".\\"+code+".txt");
		//OutputStreamWriter ows = new OutputStreamWriter(fos);
		//try {
			// �����ҳ�ı�����
			String str = HistoryDataSpider.getDocumnetAt(url);
			// ������ȡ��Ʊ������Դ��������ʽ
			Pattern gp_source = Pattern.compile("(?<=<th colspan=\"7\">|\"blue\">|</FONT>).*?(?=<|FONT|</th>)");
			Matcher mc = gp_source.matcher(str);
			String s1;
//			while (mc.find()) {
//				// ��ȡ��Ʊ������Դ
//				s1 = String.valueOf(mc.group());
//				ows.write(s1);
//				System.out.printf("%s", mc.group());
//			}
//			System.out.println();
//			ows.write("\r\n");
//
//			// �����Ʊ������Ŀ
//			String s2;
//			Pattern gp_item = Pattern
//					.compile("(?<=<strong>).*?(?=</strong>)");
//			Matcher n = gp_item.matcher(str);
//			while (n.find()) {
//				s2 = String.valueOf(n.group());
//				ows.write(s2 + "          ");
//				System.out.printf("%-42s", n.group());
//			}

			// ��ȡ��Ʊ������ϸ���
			Pattern gp_data = Pattern.compile("((?<=date=)(\\w*?)).*?(?=('>))|((?<=center\">)(\\d{1,7}?)).*?(?=(</div>))");
			Matcher m = gp_data.matcher(str);
			
			while (m.find())
			{
				StockHistoryData historyData = new StockHistoryData();
				historyData.StockCode = code;								// ����
				historyData.StockDate = String.valueOf(m.group());			// ����
				
				m.find();
				historyData.OpenPrice = String.valueOf(m.group());			// ���̼�
				
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
