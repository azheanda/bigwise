package BigWise.UI.Analyzer;

/*
 * һ������С����123��4 һ���Ǹ��ݷ������۵�1/2�㷨����2/5��3/5�㷨;����ͳһ��1/2�㷨��
 */

import java.awt.*;
import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Vector;

import BigWise.Controller.Controller;
import BigWise.Controller.QuoteController;
import BigWise.Model.StockQuoteData;

public class DKData extends Canvas {
	// ʵʱ���������
	public QuoteController qc;
	public Controller controller;
	
	
	// �����������Ĵ�С
	int leftSize = 30;
	int downSize = 100;

	double MostMax;
	double MostMin;
	double OpenPrice;
	double MaxTrade; // ���ɽ��� 
	double CurrentPrice;
	int xAxis = 0;
	double PriceBefore;
	int xAxisBefore = 0;
	double AverBefore;
	double xStep = 0;
	double yStep = 0;
	int xLength = 1020;
	int yLength = 600;
	int xMax =0;
	int yMax = 0;
	// ����������ʱ��ᶪʧ����
	int number; // ÿСʱ60������240��
	

	// ��ʽˢ
	DecimalFormat df = new DecimalFormat("0.00");

	public DKData() {
		qc = QuoteController.getQuoteControllerInstance();
		controller = Controller.getControllerInstance();
		number = qc.number;
		// System.out.println(yStep);
		setSize(xLength, yLength);
		setBackground(Color.black);
	}

	public void paint(Graphics g) {

		qc.init();
		// ��ʵʱ����
		Vector<StockQuoteData> quoteList = qc.quoteList;

		// ����Ϊ��
		if(quoteList.size() == 0)
		{
			return;
		}
		
		// ÿ��Ĭ�ϵĵ���ʼ�۸������
		MostMax = qc.getMostMax();
		MostMin = qc.getMostMin();
		OpenPrice = quoteList.elementAt(0).OpenPriceToday;
		MaxTrade = qc.getMostTrade();		// ���ɽ���
		
		PriceBefore = OpenPrice;
		AverBefore = quoteList.elementAt(0).TotalNumber
				/ quoteList.elementAt(0).TotalNumber;
		
		
		
		// ��ȥ�������������ʣ�µľ���ĳ���,int -> double
		// ��������ϵ
		Dimension d = getSize();
		xMax = d.width - 2 * leftSize;
		yMax = d.height - 2 * downSize;
		xStep = (xLength - 2 * leftSize) / (number+0.0);
		yStep = (yLength - 2 * downSize) / (MostMax - MostMin);
		xAxis = leftSize + 1;
		xAxisBefore = xAxis;
		
		// ������ϵ
		drawCordinate(g);
		drawTradeCordinate(g);

		for (int i = 0; i < quoteList.size();++i) {
			StockQuoteData quote = quoteList.elementAt(i);
			drawRTLine(quote, g);
			drawTradeLine(qc.tradeNumberList.elementAt(i), g);
			drawAverageLine(quote, g);
		}

	}

	// ������
	public void drawCordinate(Graphics g) {

		g.setColor(Color.red);

		// ����
		g.drawRect(leftSize, downSize, 1, yMax);
		g.setColor(Color.red);
		for (int i = 0; i <= 12; ++i) {
			double value = (MostMax - MostMin) / 12;
			double step = yMax / 12;
			double price = (MostMin + value * i);
			if (price > OpenPrice)
				g.setColor(Color.red);
			if (price < OpenPrice)
				g.setColor(Color.green);
			g.drawString(df.format(price), 0,
					(int) (yMax - i * step + downSize));
		}

		// ���ܴ�����Ŀ�ͷ��ʼ��������
		g.setColor(Color.red);
		g.drawRect(leftSize, yMax + downSize - 1, xMax, 1);
		g.setFont(new Font("Romas", 12, 9));
		g.setColor(Color.red);
		double step = xMax / 4;
		g.drawString("9:30", leftSize, yMax + downSize + 10);
		g.drawString("10:30", leftSize + (int) (step), yMax + downSize + 10);
		g.drawString("11:30", leftSize + (int) (step*2), yMax + downSize + 10);
		g.drawString("14:00", leftSize + (int) (step*3), yMax + downSize + 10);
		g.drawString("15:00", leftSize + (int) (step*4), yMax + downSize + 10);
	}

	// �ɽ���������
	public void drawTradeCordinate(Graphics g) {

		g.setColor(Color.red);

		// System.out.println(xMax + "|" + yMax);
		// ����
		g.drawRect(leftSize, yMax + downSize + 10, 1, downSize - 20);
		for (int i = 0; i <= 4; ++i) {
			double value = MaxTrade / 4;
			double step = (downSize - 20) / 4;
			double price = value * i;

			g.drawString(df.format(price), 0,
					(int) (yMax + 2 * downSize - 10 - i * step));
		}

		// ���ܴ�����Ŀ�ͷ��ʼ��������
		g.setColor(Color.red);
		g.drawRect(leftSize, yMax + 2 * downSize - 10, xMax, 1);
	}

	public void drawRTLine(StockQuoteData quote, Graphics g) {

		double Current = (quote.CurrentPrice - MostMin) * yStep;
		//g.setColor(Color.white);
//		g.setFont(new Font("Romas", 10, 7));
//		g.drawString(quote.CurrentPrice + "", xAxis,
//				(int) (yLength - Current - downSize));

		g.setColor(Color.white);
		g.drawLine(xAxisBefore, (int) (yLength - PriceBefore - downSize),
				xAxis, (int) (yLength - Current - downSize));
//		System.out.println(xAxis + "  " +xAxisBefore);
		PriceBefore = Current;

	}

	public void drawTradeLine(double var, Graphics g) {
		double step = MaxTrade / (downSize - 20);

		double value = var / step;
//		 System.out.println(var + "  " + MaxTrade + " " +value);
		g.setColor(Color.pink);
		g.fillRect(xAxis, (int) (yMax + downSize + 10 + downSize - 20 - value),
				(int) xStep - 1, (int) value);

	}

	public void drawAverageLine(StockQuoteData quote, Graphics g) {

		double aver = quote.TotalNumber/ quote.TotalMoney;
		
		double Average = (aver - MostMin) * yStep;
		
		
		g.setColor(Color.blue);
		g.setFont(new Font("Romas", 10, 7));

		g.setColor(Color.yellow);
		g.drawLine(xAxisBefore, (int) (yLength - AverBefore - downSize), xAxis,
				(int) (yLength - Average - downSize));

		xAxisBefore = xAxis;
		AverBefore = Average;
		xAxis += xStep;

	}

	// ˢ��
	public void refresh() {
		repaint();
	}

	public static DKData getRTLine() {
		return new DKData();
	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("test");
		Container c = frame.getContentPane();

		JPanel p = new JPanel();
		p.add(new DKData());
		c.add(p);
		c.setLayout(new FlowLayout());
		frame.setSize(1100, 700);
		frame.setVisible(true);
	}
}

