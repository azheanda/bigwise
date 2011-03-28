/*
 * ����
 */
package BigWise.Controller;
import javax.swing.*;

import BigWise.DataSpider.History.HistoryDataSpider;
import BigWise.Model.StockHistoryData;
import BigWise.Model.StockInfo;

import java.util.Observable;
import java.util.Vector;
public class Controller extends Observable{
	private static Controller c = new Controller();
	
	public final double ROWPERPAGE = 19;
	
	// ҳ��Ĺ�Ʊ��Ϣ
	public Vector<String> CodeList;									//  ��Ʊ�б�
	public String StockCode;
	public String StockMarket;
	public int CodeIndex;											// ��Ʊ�ڹ�Ʊ�б��е�λ��
	public int CodeLength;
	
	// ����ģʽ
	public static Controller getControllerInstance()
	{
		return c ;
	}
	private Controller()
	{
		init();	
	}
	
	// ��ȡ����
	public void init()
	{	
		CodeList = StockInfo.getStockCodeList();		
		CodeLength = CodeList.size();
		
		CodeIndex =	0;			  
		StockCode = CodeList.elementAt(0);
		StockMarket = getMarket(StockCode);
	}
	
	
	// ��Ʊ�ķ���,���·�����Ʊ�б�
	public void CodeIndexNext()
	{
		if(CodeIndex >= CodeLength)
		{		
			CodeIndex = CodeLength - 1;
			return;
		}
		else
			CodeIndex++;
		StockCode = CodeList.elementAt(CodeIndex);
		StockMarket = getMarket(StockCode);
		CodeUpdate();
	}
	
	public void CodeIndexprevious()
	{
		if(CodeIndex <= 0)
		{
			CodeIndex = 0;
		}
		else 
			CodeIndex--;
		
		StockCode = CodeList.elementAt(CodeIndex);
		StockMarket = getMarket(StockCode);
		CodeUpdate();	
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
	
	// Observer���¼�����Ʊ����ת��
	public void CodeUpdate()
	{
		setChanged();
		notifyObservers(new Integer(CodeIndex));
	}
	
	public static void main(String args[])
	{
		Controller  c = Controller.getControllerInstance();
	}
	
}
