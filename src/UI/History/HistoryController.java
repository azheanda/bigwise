package BigWise.UI.History;
import javax.swing.*;

import BigWise.DataDefine.StockHistoryData;
import BigWise.DataSpider.History.HistoryDataSpider;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
public abstract class HistoryController implements Runnable {
	long RefreshTime = 10000;
	Thread t = new Thread(this);
	
	private static double ROWPERPAGE = 19;
	public int total = 0;
	public int current = 0;
	Vector<StockHistoryData> HistoryDataList;					//   历史数据 
	String StockCode;
	String StockName;
	
	public HistoryController()
	{
		GetData();
		// 算出总数，求出第一页
		total = (int)Math.ceil(HistoryDataList.size()/ROWPERPAGE);
		//System.out.println(total);
		current = 1;
		t.start();
	}
	
	// 获取数据
	public void GetData()
	{
		HistoryDataSpider hds = new HistoryDataSpider();
		
		HistoryDataList = hds.extractor("600000","", "");	
		
	}
	
	
	public abstract void refresh();
	
	
	public void run()
	{
		while(true)
		{
			try
			{
				GetData();
				refresh();
				Thread.sleep(RefreshTime);
			}
			catch (InterruptedException e) {
				System.out.println("Interunpt!");
			}
		}
	}
	
}
