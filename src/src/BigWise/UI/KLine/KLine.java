package BigWise.UI.KLine;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import BigWise.Controller.HistoryController;
import BigWise.Controller.QuoteController;
import BigWise.DataSpider.*;
import BigWise.Model.StockHistoryData;
import BigWise.Model.StockQuoteData;
import BigWise.UI.Quote.QuoteDataBodyPanel;
import BigWise.UI.RTLine.RTLine;

public class KLine extends Canvas {
	public HistoryController hc;
	int xLength= 1000;
	int yLength = 600;
	int xStep = 20;
	double yStep = 20;
	final int number = 80;		// 仅支持50根K线
	double mostMax;
	double mostMin;
	int index = 0;
	int KLineWidth = 16;
	public KLine()
	{
		hc = HistoryController.getHistoryControllerInstance();
		hc.c.CodeIndexNext();
		hc.init();
		// KLine的大小
		setSize(xLength,yLength);
		
		xStep = xLength / number;
		
		
		//System.out.print(yStep);
		KLineWidth = xStep*4/5;
	}

	public void paint(Graphics g) 
	{
		g.clearRect(0, 0, xLength, yLength);
		hc.init();
		mostMax = hc.getMostMax();
		mostMin = hc.getMostMin();
		yStep = yLength/(mostMax - mostMin);	// 每0.01的市值表示的像素值
		Vector<StockHistoryData> list = hc.HistoryDataList;
		
		//System.out.print(((StockHistoryData)list.elementAt(0)).StockCode);
		drawCordinate(g);
		
		index = xStep / 2;
		for(int i = list.size() - 1; i >= 0; --i)
		{
			//System.out.print(((StockHistoryData)list.elementAt(i)).StockDate);
			drawKLine((StockHistoryData)list.elementAt(i), g);	
		}
	}

	public void drawCordinate(Graphics g)
	{
		g.setColor(Color.black);
		Dimension d = getSize();

		// 绘制坐标系
		int xMax = d.width;
		int yMax = d.height;
		
		g.drawRect(0, 0, 1, yMax);
		
		// 不能从上面的开头开始画
		g.drawRect(1, yMax-1, xMax, 1);
	}

	
	public void drawKLine(StockHistoryData history,Graphics g)
	{
		// 
		double maxMin = yLength - yStep*(Double.parseDouble(history.MaxPrice)- mostMin);
		double minMin = yLength - yStep*(Double.parseDouble(history.MinPrice)- mostMin);
		double openMin = yLength - yStep*(Double.parseDouble(history.OpenPrice)- mostMin);
		double closeMin = yLength - yStep*(Double.parseDouble(history.ClosePrice)- mostMin);
		//System.out.println(maxMin + "|" + minMin + "|" + openMin +"|" +  closeMin);

		double KLineHeight = Math.abs(openMin - closeMin);
		if(openMin < closeMin)
		{
			g.setColor(new Color(0,255,255));
			g.drawLine(index,(int)minMin, index,(int)maxMin);
			g.fillRect(index - KLineWidth/2, (int)openMin, KLineWidth,(int)KLineHeight);
		}	
		else
		{
			g.setColor(new Color(255,0,0));
			g.drawLine(index,(int)minMin, index,(int)maxMin);
			g.fillRect(index - KLineWidth/2, (int)closeMin, KLineWidth,(int)KLineHeight);
		}
			
		index = index + xStep;
	}
	
	public static KLine getKLine()
	{
		return new KLine();
	}
	public static void main(String args[]) {
		JFrame frame = new JFrame("test");
		Container c = frame.getContentPane();
		frame.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		final KLine k = KLine.getKLine();
		p.add(k);
		c.add(p,"Center");

		JButton b = new JButton("next");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				k.hc.c.CodeIndexNext();
				k.hc.init();
				k.repaint();
			}
		});
		
		c.add(b,"South");
		c.setLayout(new FlowLayout());
		frame.setSize(1100, 700);
		frame.setVisible(true);
	}
}
