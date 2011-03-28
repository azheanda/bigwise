/*
 * ��ʷ���ݵĿ���
 */
package BigWise.Controller;

import javax.swing.table.DefaultTableModel;

import BigWise.DataSpider.CapFlow.CapFlowDataSpider;

import BigWise.Model.StockCapFlowDetail;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Vector;
public class CapFlowController extends Observable{
	private static CapFlowController cfc = new CapFlowController();
	
	public final double ROWPERPAGE = 19;
	
	public Vector<StockCapFlowDetail> CapFlowList = new Vector<StockCapFlowDetail>();					//  �ʽ������� 
	public int total = 0;
	public int current = 0;
	Object columnNames[] = { "�ɽ�ʱ��", "�ɽ��۸�","�ǵ���","�۸�䶯","�ɽ���(��)","�ɽ���(Ԫ)"};
	
	public	Controller c;
	// ����ģʽ
	public static CapFlowController getCapFlowController()
	{
		return cfc ;
	}
	
	private CapFlowController()
	{
		c = Controller.getControllerInstance();
		init();	
	}
	
	// ��ȡ����
	public void init()
	{
		
		CapFlowList.clear();	
		
		try
		{
			CapFlowList = CapFlowDataSpider.GetCapFlowByCode(c.StockCode);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		// ��������������һҳ
		total = (int)Math.ceil(CapFlowList.size()/ROWPERPAGE);
		System.out.println(total);
		current = 1;	
	}
	
	//�б��ѡ��
	public void pageNext()
	{
		if(current >= total)
		{		
			current = total;
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
	
	// ����ģ��
	public DefaultTableModel GetModel()
	{
		
		//������
	    int column = columnNames.length;
	    
	    int row	= (int)ROWPERPAGE;
	    if(current == total)
	    {
	    	row = CapFlowList.size() % row;
	    }
	    //System.out.println(column + " " + row);
	    Object[][] rowData =  new Object[row][column];
	    int begin =0 , end = 0;
	    begin = (int)((current-1)*ROWPERPAGE);
	    
	    if(current == total)
	    {
	    	end = CapFlowList.size();
	    }
	    else
	    {
	    	end = (int)(current*ROWPERPAGE);
	    }
	    
	    for(int i = begin ; i < end ; ++i)
	    {
	    	StockCapFlowDetail capFlowDetail = CapFlowList.elementAt(i);
	    	int ID = (int)(i % ROWPERPAGE);
	    	DecimalFormat df = new DecimalFormat("0.00");
	    	rowData[ID][0] = capFlowDetail.StockTime;
	    	rowData[ID][1] = capFlowDetail.Price;
	    	rowData[ID][2] = capFlowDetail.UpDown;
	    	rowData[ID][3] = capFlowDetail.Variant;
	    	rowData[ID][4] = capFlowDetail.Number;
	    	rowData[ID][5] = capFlowDetail.Accout;
	    }
	    DefaultTableModel  model = new DefaultTableModel(rowData,columnNames);
	    
	    return model;
	}
	public static void main(String args[])
	{
		CapFlowController hc =CapFlowController.getCapFlowController();


		System.out.println(hc.current + "  " + hc.total);
	}
	
}
