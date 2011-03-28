package BigWise.DataSpider.News;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import BigWise.Model.StockNews;

/*
 * ������ʾ
 */
public class JYTSSpider {
	/*
	 * Back Up Data
	 */
	public static Vector<StockNews> GetInfomationData(String date,String url)
	{
		
		Vector<StockNews> JYTSList = new Vector<StockNews>();
		
		JYTSSpider test = new JYTSSpider();
		try {
			//test.extractor("http://hq.sinajs.cn/list=sh600000");
			JYTSList = test.extractor("http://biz.finance.sina.com.cn/stock/company/notice.php");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return JYTSList;
	}

	/*
	 * String GetDocumentAt(String url)
	 * static method, return the string of the html page.
	 */
	private static String getDocumnetAt(String urlString) 
	{
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
		//System.out.println(html_text.toString());
		return html_text.toString();
	}

	public Vector<StockNews> extractor(String urlString) throws IOException {
		// �ļ������
		Vector<StockNews> list = new Vector<StockNews>();
		// �����ҳ�ı�����
		String str = JYTSSpider.getDocumnetAt(urlString);
		// ������ȡ��Ʊ������Դ��������ʽ���</dt>
		Pattern gp_source = Pattern.compile("<dt(.+?)</dt>");
		Matcher mc = gp_source.matcher(str);

		while (mc.find()) 
		{
			// ��ȡ��Ʊ������Դ
			String content = String.valueOf(mc.group());
			
		
			
			//System.out.println(content);
			StockNews news = new StockNews();
			news.NewsContent = content;
			list.add(news);
		}
				
		return list;
	}
	
	public static void main(String args[])
	{
		Vector<StockNews> list = JYTSSpider.GetInfomationData("", "");
		System.out.println(list.size());
	}
}
