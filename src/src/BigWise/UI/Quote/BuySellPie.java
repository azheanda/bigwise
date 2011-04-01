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
	// �����������Ĵ�С
	int leftSize = 30;
	int downSize = 100;

	double xStep = 0;
	double yStep = 0;
	int xLength = 1000;
	int yLength = 600;
	int number = 48; // ÿСʱ12������48��
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
		// ��ʵʱ����
		Vector<StockQuoteData> quoteList = qc.quoteList;
		StockQuoteData quote = quoteList.elementAt(quoteList.size() - 1);

		System.out.println("hello");
		
		g.drawString("" + quote.StockName +"	"+ quote.StockCode + quote.StockTime,
				10, 10);
		g.setColor(Color.magenta);
		g.drawString("��һ:" + quote.Buy1 + "", leftSize, downSize);
		g.drawString("���:" + quote.Buy2 + "", leftSize, 2 * downSize);
		g.drawString("����:" + quote.Buy3 + "", leftSize, 3 * downSize);
		g.drawString("����:" + quote.Buy4 + "", leftSize, 4 * downSize);
		g.drawString("����:" + quote.Buy5 + "", leftSize, 5 * downSize);

		g.drawString(quote.Buy1Price + "", 4 * leftSize, downSize);
		g.drawString(quote.Buy2Price + "", 4 * leftSize, 2 * downSize);
		g.drawString(quote.Buy3Price + "", 4 * leftSize, 3 * downSize);
		g.drawString(quote.Buy4Price + "", 4 * leftSize, 4 * downSize);
		g.drawString(quote.Buy5Price + "", 4 * leftSize, 5 * downSize);
		
		g.setColor(Color.pink);
		g.drawString("��һ:" + quote.Sell1 + "", 7 * leftSize, downSize);
		g.drawString("����:" + quote.Sell2 + "", 7 * leftSize, 2 * downSize);
		g.drawString("����:" + quote.Sell3 + "", 7 * leftSize, 3 * downSize);
		g.drawString("����:" + quote.Sell4 + "", 7 * leftSize, 4 * downSize);
		g.drawString("����:" + quote.Sell5 + "", 7 * leftSize, 5 * downSize);
		g.drawString(quote.Sell1Price + "", 10 * leftSize, downSize);
		g.drawString(quote.Sell2Price + "", 10 * leftSize, 2 * downSize);
		g.drawString(quote.Sell3Price + "", 10 * leftSize, 3 * downSize);
		g.drawString(quote.Sell4Price + "", 10 * leftSize, 4 * downSize);
		g.drawString(quote.Sell5Price + "", 10 * leftSize, 5 * downSize);

	}

	// ˢ��
	public void refresh() {
		repaint();
	}

	public static BuySellPie getBuySellPie() {
		return new BuySellPie();
	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("����ͼ");
		Container c = frame.getContentPane();

		JPanel p = new JPanel();
		p.add( BuySellPie.getBuySellPie());
		c.add(p);
		c.setLayout(new FlowLayout());
		frame.setSize(1100, 700);
		frame.setVisible(true);
	}
}
