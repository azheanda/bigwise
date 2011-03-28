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

public class InfomationSpider {
	/*
	 * Back Up Data
	 */
	public static Vector<StockNews> GetInfomationData(String date,String url)
	{
		
		Vector<StockNews> NewsList = new Vector<StockNews>();
		
		InfomationSpider test = new InfomationSpider();
		try {
			//test.extractor("http://hq.sinajs.cn/list=sh600000");
			NewsList = test.extractor("http://finance.sina.com.cn/stock/cpbd/index.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return NewsList;
	}

	/*
	 * String GetDocumentAt(String url)
	 * static method, return the string of the html page.
	 */
	private static String getDocumnetAt(String urlString) 
	{
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
		//System.out.println(html_text.toString());
		return html_text.toString();
	}

	public Vector<StockNews> extractor(String urlString) throws IOException {
		// 文件输出流
		Vector<StockNews> list = new Vector<StockNews>();
		// 获得网页文本内容
		String str = InfomationSpider.getDocumnetAt(urlString);
		// 创建提取股票数据来源的正则表达式
		Pattern gp_source = Pattern.compile("<div class=\"hg_title\">(.+?)(</div>) | (<div class=\"hg_content\">)(.+?)(</div>)");
		Matcher mc = gp_source.matcher(str);

		while (mc.find()) 
		{
			// 提取股票数据来源
			String title = String.valueOf(mc.group(1));
			mc.find();
			String content = String.valueOf(mc.group());
			mc.find();
			String judge = String.valueOf(mc.group());
			
			//System.out.println(title + content + judge);
			
			StockNews news = new StockNews();
			news.NewsTitle = title;
			news.NewsContent = content;
			news.NewsJudge = judge;
			
			list.add(news);
		}
				
		return list;
	}
	
	public static void main(String args[])
	{
		Vector<StockNews> list = InfomationSpider.GetInfomationData("", "");
		System.out.println(list.size());
	}
}
