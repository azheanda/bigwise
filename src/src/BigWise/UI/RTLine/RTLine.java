package BigWise.UI.RTLine;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BigWise.Controller.QuoteController;
import BigWise.DataDefine.StockQuoteData;
import BigWise.DataSpider.*;

public class RTLine extends Canvas {
	public QuoteController qc;
	double CurrentPrice;
	int xAxis = 0;
	double CurrentPriceBefore = 0;
	int xAxisBefore = 0;

	int number = 0;

	RTLine() {
		qc = QuoteController.getQuoteControllerInstance();
		setSize(1000, 600);
	}

	public void paint(Graphics g) {
		xAxis = 0;
		drawCordinate(g);
		

		System.out.println(qc.total);
		StockQuoteData[] quote = qc.QuoteData;
		for(int i = 0; i <quote.length ; ++i)
			drawRTLine(quote[i], g);
		

	}

	public void drawCordinate(Graphics g) {
		g.setColor(Color.black);
		Dimension d = getSize();

		// 绘制坐标系
		int xMax = d.width;
		int yMax = d.height;

		g.drawRect(0, 0, 1, yMax);

		// 不能从上面的开头开始画
		g.drawRect(1, yMax - 1, xMax, 1);
	}

	public void drawRTLine(StockQuoteData quote, Graphics g)
	{
		System.out.println(quote.CurrentPrice);
		
			xAxis += 5;
			CurrentPrice = quote.CurrentPrice * 2 ;
			//System.out.println(CurrentPrice);
			g.drawLine(xAxisBefore, (int) CurrentPriceBefore, xAxis,
					(int) CurrentPrice);
			xAxisBefore = xAxis;
			CurrentPriceBefore = CurrentPrice;
		
	}
	public static void main(String args[]) {
		JFrame frame = new JFrame("test");
		Container c = frame.getContentPane();
		c.add(new RTLine());
		c.setLayout(new FlowLayout());
		frame.setSize(1100, 700);
		frame.setVisible(true);
	}
}
