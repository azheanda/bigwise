package BigWise.UI.Quote;

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

public class BuySellPie extends Canvas {
	public QuoteController qc;
	double CurrentPrice;
	int xAxis = 0;
	double PriceBefore;
	int xAxisBefore = 0;
	double AverBefore;
	// 左栏和右栏的大小
	int leftSize = 30;
	int downSize = 100;

	double xStep = 0;
	double yStep = 0;
	int xLength = 1000;
	int yLength = 600;
	int number = 48; // 每小时12根，共48根
	public Controller controller;

	DecimalFormat df = new DecimalFormat("0.00");

	public BuySellPie() {

		qc = QuoteController.getQuoteControllerInstance();
		controller = Controller.getControllerInstance();

		// System.out.println(yStep);
		setSize(xLength, yLength);
	}

	public void paint(Graphics g) {

		qc.init();
		// 画实时数据
		Vector<StockQuoteData> quoteList = qc.quoteList;
		StockQuoteData quote = quoteList.elementAt(quoteList.size() - 4);


		g.drawString("" + quote.StockName + "	" + quote.StockCode
				+ quote.StockTime, 10, 10);
		g.setColor(Color.black);
		g.drawString("买一:" + quote.Buy1 + "", leftSize, downSize);
		g.drawString("买二:" + quote.Buy2 + "", leftSize, 2 * downSize);
		g.drawString("买三:" + quote.Buy3 + "", leftSize, 3 * downSize);
		g.drawString("买四:" + quote.Buy4 + "", leftSize, 4 * downSize);
		g.drawString("买五:" + quote.Buy5 + "", leftSize, 5 * downSize);

		g.drawString(quote.Buy1Price + "", 4 * leftSize, downSize);
		g.drawString(quote.Buy2Price + "", 4 * leftSize, 2 * downSize);
		g.drawString(quote.Buy3Price + "", 4 * leftSize, 3 * downSize);
		g.drawString(quote.Buy4Price + "", 4 * leftSize, 4 * downSize);
		g.drawString(quote.Buy5Price + "", 4 * leftSize, 5 * downSize);

		g.setColor(Color.black);
		g.drawString("卖一:" + quote.Sell1 + "", 7 * leftSize, downSize);
		g.drawString("卖二:" + quote.Sell2 + "", 7 * leftSize, 2 * downSize);
		g.drawString("卖三:" + quote.Sell3 + "", 7 * leftSize, 3 * downSize);
		g.drawString("卖四:" + quote.Sell4 + "", 7 * leftSize, 4 * downSize);
		g.drawString("卖五:" + quote.Sell5 + "", 7 * leftSize, 5 * downSize);
		g.drawString(quote.Sell1Price + "", 10 * leftSize, downSize);
		g.drawString(quote.Sell2Price + "", 10 * leftSize, 2 * downSize);
		g.drawString(quote.Sell3Price + "", 10 * leftSize, 3 * downSize);
		g.drawString(quote.Sell4Price + "", 10 * leftSize, 4 * downSize);
		g.drawString(quote.Sell5Price + "", 10 * leftSize, 5 * downSize);

		int xCenter = getWidth() /7 *5;
		int yCenter = getHeight() / 2;
		int radius = (int) (Math.min(getWidth(), getHeight() * 0.33));
		int x = xCenter - radius;
		int y = yCenter - radius;

		double b1 = quote.Buy1;
		double b2 = quote.Buy2;
		double b3 = quote.Buy3;
		double b4 = quote.Buy4;
		double b5 = quote.Buy5;
		double s1 = quote.Sell1;
		double s2 = quote.Sell2;
		double s3 = quote.Sell3;
		double s4 = quote.Sell4;
		double s5 = quote.Sell5;
		
		double total = b1 + b2 + b3 + b4 + b5 + s1 + s2 + s3 + s4 + s5;
		double angleB1 = Math.round(360 * b1 / total);
		double angleB2 = Math.round( 360 * b2 / total);
		double angleB3 = Math.round(360 * b3 / total);
		double angleB4 = Math.round( 360 * b4 / total);
		double angleB5 =  Math.round(360 * b5 / total);
		double angleS1 =  Math.round(360 * s1 / total);
		double angleS2 =  Math.round(360 * s2 / total);
		double angleS3 =  Math.round(360 * s3 / total);
		double angleS4 =  Math.round(360 * s4 / total);
		double angleS5 =  Math.round(360 * s5 / total);
		
		System.out.println(angleB1 + "" + angleB2);
		
		g.setColor(new Color(255,0,0));
		g.fillArc(x, y, 2 * radius, 2 * radius, 0, (int)(angleB1) );
		g.setColor(new Color(205,0,0));
		g.fillArc(x, y, 2 * radius, 2 * radius, (int)(angleB1), (int)(angleB2) );
		g.setColor(new Color(155,0,0));
		g.fillArc(x, y, 2 * radius, 2 * radius, (int)(angleB1)+ (int)(angleB2), (int)(angleB3) );
		g.setColor(new Color(105,0,0));
		g.fillArc(x, y, 2 * radius, 2 * radius, (int)(angleB1)+ (int)(angleB2)+(int)(angleB3), (int)(angleB4) );
		g.setColor(new Color(55,0,0));
		g.fillArc(x, y, 2 * radius, 2 * radius, (int)(angleB1)+ (int)(angleB2)+(int)(angleB3)+ (int)(angleB4), (int)(angleB5) );
		
		
		g.setColor(new Color(0,255,0));
		g.fillArc(x, y, 2 * radius, 2 * radius, (int)(angleB1)+ (int)(angleB2)+(int)(angleB3)+ (int)(angleB4)+ (int)(angleB5), (int)(angleS1) );
		g.setColor(new Color(0,205,0));
		g.fillArc(x, y, 2 * radius, 2 * radius, (int)(angleB1)+ (int)(angleB2)+(int)(angleB3)+ (int)(angleB4)+ (int)(angleB5)+(int)(angleS1), (int)(angleS2) );
		g.setColor(new Color(0,155,0));
		g.fillArc(x, y, 2 * radius, 2 * radius, (int)(angleB1)+ (int)(angleB2)+(int)(angleB3)+ (int)(angleB4)+ (int)(angleB5)+(int)(angleS1)+(int)(angleS2), (int)(angleS3) );
		g.setColor(new Color(0,105,0));
		g.fillArc(x, y, 2 * radius, 2 * radius, (int)(angleB1)+ (int)(angleB2)+(int)(angleB3)+ (int)(angleB4)+ (int)(angleB5)+(int)(angleS1)+(int)(angleS2) + (int)(angleS3), (int)(angleS4) );
		g.setColor(new Color(0,55,0));
		g.fillArc(x, y, 2 * radius, 2 * radius, (int)(angleB1)+ (int)(angleB2)+(int)(angleB3)+ (int)(angleB4)+ (int)(angleB5)+(int)(angleS1)+(int)(angleS2) + (int)(angleS3)+(int)(angleS4), (int)(angleS5) );
		
	}

	// 刷新
	public void refresh() {
		repaint();
	}

	public static BuySellPie getBuySellPie() {
		return new BuySellPie();
	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("买卖图");
		Container c = frame.getContentPane();

		JPanel p = new JPanel();
		p.add(BuySellPie.getBuySellPie());
		c.add(p);
		c.setLayout(new FlowLayout());
		frame.setSize(1100, 700);
		frame.setVisible(true);
	}
}
