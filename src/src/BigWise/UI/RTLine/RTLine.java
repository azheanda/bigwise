package BigWise.UI.RTLine;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import BigWise.Controller.Controller;
import BigWise.Controller.QuoteController;
import BigWise.DataSpider.*;
import BigWise.Model.StockQuoteData;

public class RTLine extends Canvas {
	public QuoteController qc;
	double CurrentPrice;
	int xAxis = 0;
	double CurrentPriceBefore = 0;
	int xAxisBefore = 0;

	int xLength = 1000;
	int yLength = 600;
	int number = 48;		// ÿСʱ12������48��
	public Controller controller;
	
	
	public RTLine() {
		
		qc = QuoteController.getQuoteControllerInstance();
		controller = Controller.getControllerInstance();
		
		setSize(xLength, yLength);
	}

	public void paint(Graphics g) {
		xAxis = 0;
		
		// ������ϵ
		drawCordinate(g);
		

		// ��ʵʱ����
		Vector<StockQuoteData> quoteList = qc.quoteList;
		for(int i = 0; i < quoteList.size() ; ++i)
			drawRTLine(quoteList.elementAt(i), g);	

	}

	public void drawCordinate(Graphics g) {
		
		g.setColor(Color.black);
		Dimension d = getSize();

		// ��������ϵ
		int xMax = d.width;
		int yMax = d.height;

		g.drawRect(0, 0, 1, yMax);

		// ���ܴ�����Ŀ�ͷ��ʼ��
		g.drawRect(1, yMax - 1, xMax, 1);
	}

	public void drawRTLine(StockQuoteData quote, Graphics g)
	{
			//System.out.println(quote.CurrentPrice);
		
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
		
		JPanel p = new JPanel();
		p.add(new RTLine());
		c.add(p);
		c.setLayout(new FlowLayout());
		frame.setSize(1100, 700);
		frame.setVisible(true);
	}
}
