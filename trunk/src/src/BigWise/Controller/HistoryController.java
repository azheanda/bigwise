/*
 * ��ʷ���ݵĿ���
 */
package BigWise.Controller;
import javax.swing.*;

import BigWise.DataDefine.StockHistoryData;
import BigWise.DataDefine.StockInfo;
import BigWise.DataSpider.History.HistoryDataSpider;
import java.util.Observable;
import java.util.Vector;
public class HistoryController extends Observable{
	private static HistoryController hc = new HistoryController();
	
	public final double ROWPERPAGE = 19;
	
	public Vector<StockHistoryData> HistoryDataList = new Vector<StockHistoryData>();					//  ��ʷ���� 
	public int total = 0;
	public int current = 0;
	
	public	Controller c;
	// ����ģʽ
	public static HistoryController getHistoryControllerInstance()
	{
		return hc ;
	}
	private HistoryController()
	{
		c = Controller.getControllerInstance();
		init();	
	}
	
	// ��ȡ����
	public void init()
	{
		HistoryDataSpider hds = new HistoryDataSpider();
		
		Vector<StockHistoryData> hdl = hds.extractor(c.StockCode,"" , "");
		
		//System.out.println(hdl.size());
		for( int i = 0 ; i < hdl.size() ; ++i)
		{
			HistoryDataList.add((StockHistoryData)hdl.elementAt(i));
		}
		//System.out.println(HistoryDataList.size());
		//System.out.println(ROWPERPAGE);
		// ��������������һҳ
		total = (int)Math.ceil(HistoryDataList.size()/ROWPERPAGE);
		//System.out.println(total);
		current = 1;	
	}
	
	//�б��ѡ��
	public void pageNext()
	{
		if(current >= total)
		{		
			current = total - 1;
			return;
		}
		else
			current++;
	}
	
	public void pagePrevious()
	{
		if(current <= 1)
		{
			current = 1;
			return;
		}
		else 
			current--;
	}
	
		
	public String getMarket(String code)
	{
		 String prefix = code.substring(0, 1);
		 //System.out.println(prefix);
		 if(prefix.equals("0"))
			 return "sz";
		 else if (prefix.equals("6"))
			 return "sh";
		 
		 return "sh";
	}		
	
	
	public static void main(String args[])
	{
		HistoryController hc =HistoryController.getHistoryControllerInstance();
	}
	
}
