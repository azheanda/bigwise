/*
 * 后台控制,抓取的后台程序
 */
package BigWise.Controller;
import javax.swing.*;

import BigWise.DataSpider.History.HistoryDataSpider;
import BigWise.DataSpider.Quote.QuoteDataSpider;
import BigWise.Model.StockHistoryData;
import BigWise.Model.StockInfo;
import BigWise.Utility.Utility;

import java.util.Observable;
import java.util.Vector;
public class DaemonController extends Thread{
	public QuoteDataSpider qds;
	public HistoryDataSpider hds;
	public int day;
	public DaemonController()
	{
		// 是否是新的一天的标志位
		day = Utility.getDay();
		qds = new QuoteDataSpider();
		hds = new HistoryDataSpider();
		qds.start();
		hds.start();
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				int date = Utility.getDay();
				if(date != day)
				{
					QuoteDataSpider.clearQuote();
					// 标志位置为新的一天
					day = date;
				}
				//Thread.sleep(21600000);
				Thread.sleep(3000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[])
	{
		DaemonController  daemon = new DaemonController();
		daemon.start();
	}
	
}
