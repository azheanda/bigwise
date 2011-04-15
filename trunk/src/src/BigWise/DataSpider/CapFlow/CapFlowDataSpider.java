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
		
		// �ʽ������� �ɽ���ϸ
		Vector<StockCapFlowDetail> CapFlowDetailList = new Vector<StockCapFlowDetail>();
		
		
		String market = Utility.getMarket(code);
		
		String url = "http://vip.stock.finance.sina.com.cn/quotes_service/view/vMS_tradedetail.php?symbol="+market+code;

		String html_text= CapFlowDataSpider.getDocumnetAt(url);
		
		Pattern gp_source = Pattern.compile("<th>(\\d{2}:\\d{2}:\\d{2})</th>|<td>([%\\d+.,-]*?)</td>");
		Matcher m = gp_source.matcher(html_text);
		
		
		while (m.find())
		{
			
			StockCapFlowDetail capflowData = new StockCapFlowDetail();
			capflowData.StockCode = code;								// ����
			
			capflowData.StockTime = String.valueOf(m.group(1));			// ʱ��
			
			m.find();
			capflowData.Price = String.valueOf(m.group(2));			// �ɽ���
			
			m.find();
			capflowData.UpDown = String.valueOf(m.group(2));			// �ǵ���
			
			m.find();
			capflowData.Variant = String.valueOf(m.group(2));			// �۸�䶯
			
			m.find();
			capflowData.Number = String.valueOf(m.group(2));			// �ɽ���
			
			m.find();
			capflowData.Accout = String.valueOf(m.group(2));			// �ɽ���
			
			
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