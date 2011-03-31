package BigWise.UI.RTLine;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Vector;

import BigWise.Controller.Controller;
import BigWise.Controller.QuoteController;
import BigWise.DataSpider.*;
import BigWise.Model.StockQuoteData;

public class RTLine extends Canvas {
	public QuoteController qc;
	double CurrentPrice;
	int xAxis = 0;
	double PriceBefore;
	int xAxisBefore = 0;
	double AverBefore;
	// 左栏和右栏的大小
	int leftSize = 30;
	int downSize = 100;

	double MostMax;
	double MostMin;
	double OpenPrice;
	double MaxTrade; // 最大成交量

	double xStep = 0;
	double yStep = 0;
	int xLength = 1000;
	int yLength = 600;
	int number = 48; // 每小时12根，共48根
	public Controller controller;

	DecimalFormat df = new DecimalFormat("0.00");

	public RTLine() {

		qc = QuoteController.getQuoteControllerInstance();
		controller = Controller.getControllerInstance();

		// System.out.println(yStep);
		setSize(xLength, yLength);
	}

	public void paint(Graphics g) {

		qc.init();
		// 画实时数据
		Vector<StockQuoteData> quoteList = qc.quoteList;

		// 出去两倍的左边栏，剩下的距离的长度
		xStep = (xLength - 2 * leftSize) / number;

		MostMax = qc.getMostMax();
		MostMin = qc.getMostMin();
		OpenPrice = quoteList.elementAt(0).OpenPriceToday;

		MaxTrade = qc.getMostTrade();

		// System.out.println(MostMax + ":" + MostMin);
		yStep = (yLength - 2 * downSize) / (MostMax - MostMin);

		xAxis = leftSize + 3;

		// 上一次的价格和坐标
		xAxisBefore = xAxis;
		PriceBefore = quoteList.elementAt(0).CurrentPrice;
		AverBefore = quoteList.elementAt(0).TradeAccount
				/ quoteList.elementAt(0).TotalTrade;

		// 画坐标系
		drawCordinate(g);
		drawTradeCordinate(g);

		for (int i = 0; i < quoteList.size(); ++i) {
			StockQuoteData quote = quoteList.elementAt(i);

			drawRTLine(quote, g);
			drawTradeLine(qc.tradeNumberList.elementAt(i), g);
			drawAverageLine(quote, g);

		}

	}

	// 坐标轴
	public void drawCordinate(Graphics g) {

		g.setColor(Color.black);
		Dimension d = getSize();

		// 绘制坐标系
		int xMax = d.width - 2 * leftSize;
		int yMax = d.height - 2 * downSize;

		// System.out.println(xMax + "|" + yMax);
		// 纵轴
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

		// 不能从上面的开头开始画，横轴
		g.setColor(Color.black);
		g.drawRect(leftSize, yMax + downSize - 1, xMax, 1);
		g.setFont(new Font("Romas", 12, 9));
		g.setColor(Color.red);
		g.drawString("9:30", leftSize, yMax + downSize + 10);
		g.drawString("10:30", leftSize + (int) xStep * 12, yMax + downSize + 10);
		g.drawString("11:30", leftSize + (int) xStep * 24, yMax + downSize + 10);
		g.drawString("14:00", leftSize + (int) xStep * 36, yMax + downSize + 10);
		g.drawString("15:00", leftSize + (int) xStep * 48, yMax + downSize + 10);
	}

	// 成交量坐标轴
	public void drawTradeCordinate(Graphics g) {

		g.setColor(Color.black);
		Dimension d = getSize();

		// 绘制坐标系
		int xMax = d.width - 2 * leftSize;
		int yMax = d.height - 2 * downSize;

		// System.out.println(xMax + "|" + yMax);
		// 纵轴
		g.drawRect(leftSize, yMax + downSize + 10, 1, downSize - 20);
		for (int i = 0; i <= 4; ++i) {
			double value = MaxTrade / 4;
			double step = (downSize - 20) / 4;
			double price = value * i;

			g.drawString(df.format(price), 0,
					(int) (yMax + 2 * downSize - 10 - i * step));
		}

		// 不能从上面的开头开始画，横轴
		g.setColor(Color.black);
		g.drawRect(leftSize, yMax + 2 * downSize - 10, xMax, 1);
		g.setFont(new Font("Romas", 12, 9));
		g.setColor(Color.red);
		g.drawString("9:30", leftSize, yMax + 2 * downSize);
		g.drawString("10:30", leftSize + (int) xStep * 12, yMax + 2 * downSize);
		g.drawString("11:30", leftSize + (int) xStep * 24, yMax + 2 * downSize);
		g.drawString("14:00", leftSize + (int) xStep * 36, yMax + 2 * downSize);
		g.drawString("15:00", leftSize + (int) xStep * 48, yMax + 2 * downSize);
	}

	public void drawRTLine(StockQuoteData quote, Graphics g) {

		double Current = (quote.CurrentPrice - MostMin) * yStep;
		g.setColor(Color.blue);
		g.setFont(new Font("Romas", 10, 7));
		g.drawString(quote.CurrentPrice + "", xAxis,
				(int) (yLength - Current - downSize));

		g.setColor(Color.blue);
		g.drawLine(xAxisBefore, (int) (yLength - PriceBefore - downSize),
				xAxis, (int) (yLength - Current - downSize));

		PriceBefore = Current;

	}

	public void drawTradeLine(double var, Graphics g) {
		Dimension d = getSize();

		// 绘制坐标系
		int xMax = d.width - 2 * leftSize;
		int yMax = d.height - 2 * downSize;
		double step = MaxTrade / (downSize - 20);

		double value = var / step;
		// System.out.println(value);
		g.setColor(Color.pink);
		g.fillRect(xAxis, (int) (yMax + downSize + 10 + downSize - 20 - value),
				(int) xStep - 4, (int) value);

	}

	public void drawAverageLine(StockQuoteData quote, Graphics g) {

		double aver = quote.TradeAccount / quote.TotalTrade;

		double Average = (aver - MostMin) * yStep;
		g.setColor(Color.blue);
		g.setFont(new Font("Romas", 10, 7));
		// g.drawString(df.format(aver), xAxis,
		// (int) (yLength - Average - downSize + 3));

		g.setColor(Color.yellow);
		g.drawLine(xAxisBefore, (int) (yLength - AverBefore - downSize), xAxis,
				(int) (yLength - Average - downSize));

		xAxisBefore = xAxis;
		AverBefore = Average;
		xAxis += xStep;

	}

	// 刷新
	public void refresh() {
		repaint();
	}

	public static RTLine getRTLine() {
		return new RTLine();
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
