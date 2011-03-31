package BigWise.UI.KLine;

import java.awt.*;

import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
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
	int xLength = 1000;
	int yLength = 600;
	int xStep = 20;
	double yStep = 20;
	int number = 80; // 仅支持50根K线
	int leftSize = 20;
	int downSize = 80;
	double MostMax;
	double MostMin;
	int index = 0;
	int KLineWidth = 16;
	
	int xCross = 0;
	int yCross = 0;

	DecimalFormat df = new DecimalFormat("0.00");

	public KLine() {
		addMouseListener(new MouseListener() {

			public void mousePressed(java.awt.event.MouseEvent arg0) {

			}

			public void mouseReleased(java.awt.event.MouseEvent arg0) {
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
			}

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getX() + " || " + e.getY());
				xCross = e.getX();
				yCross = e.getY();
				repaint();
				
			}
		});

		hc = HistoryController.getHistoryControllerInstance();
		hc.controller.CodeIndexNext();
		hc.init(hc.controller.StockCode);
		// KLine的大小
		setSize(xLength, yLength);

		number = hc.HistoryDataList.size();

		xStep = xLength / number;

		// System.out.print(yStep);
		KLineWidth = xStep * 4 / 5;
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, xLength, yLength);
		hc.init(hc.controller.StockCode);
		MostMax = hc.getMostMax();
		MostMin = hc.getMostMin();

		Vector<StockHistoryData> list = hc.HistoryDataList;

		// System.out.print(((StockHistoryData)list.elementAt(0)).StockCode);
		drawCordinate(g);
		drawCross(g);
		index = xStep / 2;
		for (int i = list.size() - 1; i >= 0; --i) {
			// System.out.print(((StockHistoryData)list.elementAt(i)).StockDate);
			drawKLine((StockHistoryData) list.elementAt(i), g);
		}
	}

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
			g.drawString(df.format(price), 0,
					(int) (yMax - i * step + downSize));
		}

		g.setColor(Color.black);
		g.drawRect(leftSize, yMax + downSize - 1, xMax, 1);
		g.setFont(new Font("Romas", 12, 9));
		g.setColor(Color.red);
		for (int i = 0; i < number; ++i) {
			StockHistoryData historyData = hc.HistoryDataList.elementAt(i);
			String date = historyData.StockDate;
			//System.out.print(date);
			date = date.substring(6, 10);
			g.drawString(date, leftSize + (int) xStep * i, yMax + downSize + 10);
		}

	}

	public void drawKLine(StockHistoryData history, Graphics g) {
		// 绘制坐标系
		Dimension d = getSize();
		int yMax = d.height - 2 * downSize;

		double step = yMax / (MostMax - MostMin);

		double openPrice = history.OpenPrice - MostMin;
		double closePrice = history.ClosePrice - MostMin;

		double maxPrice = history.MaxPrice - MostMin;
		double minPrice = history.MinPrice - MostMin;

		int Max = (int) (yMax - maxPrice * step + downSize);

		int Min = (int) (yMax - minPrice * step + downSize);
		int Open = (int) (yMax - openPrice * step + downSize);
		int Close = (int) (yMax - closePrice * step + downSize);
		int KLineHeight = (int) (Math.abs(openPrice - closePrice) * step);

		if (openPrice > closePrice) {
			g.setColor(new Color(0, 255, 255));
			g.drawLine(index + leftSize, Max, index + leftSize, Min);
			g.fillRect(index + leftSize - KLineWidth / 2, Open, KLineWidth,
					(int) KLineHeight);
		} else {
			g.setColor(new Color(255, 0, 0));
			g.drawLine(index + leftSize, Max, index + leftSize, Min);
			g.fillRect(index + leftSize - KLineWidth / 2, Close, KLineWidth,
					(int) KLineHeight);
		}

		index = index + xStep;
	}

	public void drawCross(Graphics g)
	{
		Dimension d = getSize();

		// 绘制坐标系
		int xMax = d.width - 2 * leftSize;
		int yMax = d.height - 2 * downSize;
		if(xCross != 0 && yCross != 0)
		{
			g.setColor(Color.yellow);
			g.drawRect(leftSize, yCross, xMax, 1);
			g.drawRect(xCross, downSize,1, yMax);
		}
		
	}
	public void refresh() {
		repaint();
	}

	public static KLine getKLine() {
		return new KLine();
	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("test");
		Container c = frame.getContentPane();
		frame.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		final KLine k = KLine.getKLine();
		p.add(k);
		c.add(p, "Center");

		JButton b = new JButton("next");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				k.hc.controller.CodeIndexNext();
				k.hc.init(k.hc.controller.StockCode);
				k.repaint();
			}
		});

		c.add(b, "South");
		c.setLayout(new FlowLayout());
		frame.setSize(1100, 700);
		frame.setVisible(true);
	}
}
