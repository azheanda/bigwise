package BigWise.DataSpider.Quote;

import java.net.*;

import BigWise.Model.StockInfo;
import BigWise.Model.StockQuoteData;
import BigWise.Utility.DBUtil;
import BigWise.Utility.Utility;

import java.io.*;
import java.util.Vector;

import java.sql.*;

public class QuoteDataSpider {

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

	public static void main(String[] args) {
		// 每天有48组数据
		for (int i = 0; i < 48; ++i) {
			try {
				QuoteDataSpider.GetQuoteDataByKeyword("market", "*");
			} catch (IOException e) {
				System.out.print("failed");
			}
		}

	}
}