/*
 * 后台控制,抓取的后台程序
 */
package BigWise.Controller;
import javax.swing.*;

import BigWise.DataSpider.History.HistoryDataSpider;
import BigWise.DataSpider.Quote.QuoteDataSpider;
import BigWise.Model.StockHistoryData;
import BigWise.Model.StockInfo;

import java.util.Observable;
import java.util.Vector;
public class DaemonController{
	public QuoteDataSpider qds;
	public HistoryDataSpider hds;
	public DaemonController()
	{
		qds = new QuoteDataSpider();
		hds = new HistoryDataSpider();
	}
	
	public void start()
	{
		qds.start();
	}
	
	public static void main(String args[])
	{
		DaemonController  daemon = new DaemonController();
		daemon.start();
	}
	
}
