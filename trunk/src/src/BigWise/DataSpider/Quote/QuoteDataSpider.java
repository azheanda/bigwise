package BigWise.DataSpider.Quote;

import java.net.*;

import BigWise.Model.StockInfo;
import BigWise.Model.StockQuoteData;
import BigWise.Utility.DBUtil;
import BigWise.Utility.Utility;

import java.io.*;
import java.util.Vector;

import java.sql.*;

public class QuoteDataSpider extends Thread {

	public int day = 0;
	public static int ID = 0;

	public static Vector<StockQuoteData> GetQuoteDataByKeyword(String type,
			String keyword) throws IOException {

		// 股票列表
		Vector<StockInfo> StockList = StockInfo.getStockListByKeyword(type,
				keyword);
		// 股票列表中的股票的只数
		int StockNumber = StockList.size();
		System.out.println(StockNumber);

		// 行情数据，59只股票的行情数据
		Vector<StockQuoteData> QuoteDataList = new Vector<StockQuoteData>();

		// System.out.println(StockNumber);

		for (int i = 0; i < StockNumber; ++i) {

			QuoteDataList.add(GetQuoteDataByStockCode(StockList.elementAt(i)));
		}

		try {
			Connection conn = DBUtil.getDbConn();

			Statement stmt = conn.createStatement();

			for (int i = 0; i < QuoteDataList.size(); ++i) {
				StockQuoteData quote = QuoteDataList.elementAt(i);
				String code = quote.StockCode;
				String name = quote.StockName;
				String date = quote.StockDate;
				String time = quote.StockTime;
				int ID = quote.ID;

				String sql = "insert into QuoteData values(" + ID + ",'" + code
						+ "','" + name + "','" + date + "','" + time + "',"
						+ quote.OpenPriceToday + "," + quote.ClosePriceYestoday
						+ "," + quote.CurrentPrice + "," + quote.HighPriceToday
						+ ", " + quote.LowPriceToday + "," + quote.TradeAccount
						+ "," + quote.TotalTrade + "," + quote.StockColor
						+ ");";
				stmt.execute(sql);
			}

			stmt.close();
			DBUtil.closeConnection(conn);
			// System.out.println("finished");
		} catch (SQLException ex) {
			System.out.println("failed");
		}

		// 抓取次数累加
		ID++;
		return QuoteDataList;
	}

	public static StockQuoteData GetQuoteDataByStockCode(StockInfo stockinfo)
			throws IOException {

		String code = stockinfo.code;
		String name = stockinfo.name;
		String market = Utility.getMarket(code);
		StringBuffer html_text = new StringBuffer();

		// Get the Data
		URL quote = new URL("http://hq.sinajs.cn/list=" + market + code);
		// Read Line By Line
		BufferedReader br = new BufferedReader(new InputStreamReader(
				quote.openStream()));
		BufferedReader in = new BufferedReader(br);

		String line = null;
		while ((line = in.readLine()) != null) {
			html_text.append(line + " ");
		}
		String quoteResult = html_text.toString();
		String[] tmp = quoteResult.split(",");

		StockQuoteData quoteData = new StockQuoteData();
		quoteData.ID = ID; // 第ID次抓取数据，每天48次
		quoteData.StockCode = code; // 股票代码
		quoteData.StockName = name; // 股票名称
		quoteData.StockDate = tmp[30]; // 日期
		quoteData.StockTime = tmp[31].substring(0, tmp[31].length() - 3); // 时间
		quoteData.OpenPriceToday = Double.parseDouble(tmp[1]); // 今日开盘价格
		quoteData.ClosePriceYestoday = Double.parseDouble(tmp[2]); // 昨日收盘价格
		quoteData.CurrentPrice = Double.parseDouble(tmp[3]); // 现在价格
		quoteData.HighPriceToday = Double.parseDouble(tmp[4]); // 今日最高价格
		quoteData.LowPriceToday = Double.parseDouble(tmp[5]); // 今日最低价格

		quoteData.TotalTrade = Double.parseDouble(tmp[8]); // 今日成交手数
		quoteData.TradeAccount = Double.parseDouble(tmp[9]); // 今日总交易额

		quoteData.Buy1 = Double.parseDouble(tmp[10]); // 买一
		quoteData.Buy2 = Double.parseDouble(tmp[12]); // 买一
		quoteData.Buy3 = Double.parseDouble(tmp[14]); // 买一
		quoteData.Buy4 = Double.parseDouble(tmp[16]); // 买一
		quoteData.Buy5 = Double.parseDouble(tmp[18]); // 买一
		quoteData.Buy1Price = Double.parseDouble(tmp[11]); // 买一
		quoteData.Buy2Price = Double.parseDouble(tmp[13]); // 买一
		quoteData.Buy3Price = Double.parseDouble(tmp[15]); // 买一
		quoteData.Buy4Price = Double.parseDouble(tmp[17]); // 买一
		quoteData.Buy5Price = Double.parseDouble(tmp[19]); // 买一

		quoteData.Sell1 = Double.parseDouble(tmp[20]); // 卖一
		quoteData.Sell2 = Double.parseDouble(tmp[22]); // 卖一
		quoteData.Sell3 = Double.parseDouble(tmp[24]); // 卖一
		quoteData.Sell4 = Double.parseDouble(tmp[26]); // 卖一
		quoteData.Sell5 = Double.parseDouble(tmp[28]); // 卖一
		quoteData.Sell1Price = Double.parseDouble(tmp[21]); // 卖一
		quoteData.Sell2Price = Double.parseDouble(tmp[23]); // 卖一
		quoteData.Sell3Price = Double.parseDouble(tmp[25]); // 卖一
		quoteData.Sell4Price = Double.parseDouble(tmp[27]); // 卖一
		quoteData.Sell5Price = Double.parseDouble(tmp[29]); // 卖一

		// 股票的涨跌是跟昨日收盘价格相比较
		if (quoteData.CurrentPrice < quoteData.ClosePriceYestoday)
			quoteData.StockColor = 2;// 绿色
		else if (quoteData.CurrentPrice >= quoteData.ClosePriceYestoday)
			quoteData.StockColor = 1;// 红色
		else if ((int) (quoteData.CurrentPrice) == 0)
			quoteData.StockColor = 0;// 绿色

		// System.out.println(quoteData.toString());

		return quoteData;

	}

	public static void clearQuote() {
		try {
			Connection conn = DBUtil.getDbConn();

			Statement stmt = conn.createStatement();
			String sql = "delete from quotedata;";
			stmt.execute(sql);

			stmt.close();
			DBUtil.closeConnection(conn);
			// System.out.println("finished");
		} catch (SQLException ex) {
			System.out.println("failed");
		}
	}

	public void run() {

		while (true) {
			try {
				if (Utility.IsMarketTime()) {
					// 每隔5分钟抓取一次实时数据
					System.out.println("catch RT data");
					QuoteDataSpider.GetQuoteDataByKeyword("market", "*");
					Thread.sleep(300000);
				} else {
					// 不是市场时间，每隔半小时检测一次
					Thread.sleep(1800000);
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		// 每天有48组数据
		QuoteDataSpider qds = new QuoteDataSpider();
		qds.start();
	}
}