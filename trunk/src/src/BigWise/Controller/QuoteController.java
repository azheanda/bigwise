/*
 * 实时数据的控制
 */
package BigWise.Controller;

import BigWise.Model.StockQuoteData;
import BigWise.Utility.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class QuoteController extends Observable {

	// 实时行情的控制
	private static QuoteController qc = new QuoteController();

	// 单子模式
	public static QuoteController getQuoteControllerInstance() {
		return qc;
	}

	public String type;
	public String keyword;

	public Controller controller;
	
	
	// 实时数据信息
	public final double ROWPERPAGE = 20;
	public int total = 0;
	public int current = 0;
	public Vector<StockQuoteData> quoteList = new Vector<StockQuoteData>();
	public Vector<StockQuoteData> NewQuoteList = new Vector<StockQuoteData>();
	public Vector<Double> tradeNumberList = new Vector<Double>();
	Object columnNames[] = { "ID", "代码", "名称", "最新价", "最高价", "最低价", "今日开盘价",
			"昨日收盘价", "总手(万)", "金额(亿)" };

	public int number = 240;	// 每天的实时跟实时数据线
	
	
	private QuoteController() {
		controller = Controller.getControllerInstance();
		init();
	}

	// 获取数据
	public void init() {

		GetData(controller.StockCode);
		GetNewestData();
		tradeNumberList = getTradeList();
	}

	// 获取数据
	public void GetData(String code) {

		quoteList.clear();

		try {
			Connection conn = DBUtil.getDbConn();
			Statement stmt = conn.createStatement();

			ResultSet result;

			result = stmt
					.executeQuery(" select * from quotedata where code = '"
							+ code + "';");

			while (result.next()) {

				int ID = result.getInt("ID");
				String name = result.getString("name");
				String date = result.getString("date");
				String time = result.getString("time");
				double OpenPrice = result.getDouble("OpenPrice");
				double ClosePrice = result.getDouble("ClosePrice");
				double CurrentPrice = result.getDouble("CurrentPrice");
				double MaxPrice = result.getDouble("MaxPrice");
				double MinPrice = result.getDouble("MinPrice");
				double TradeAccout = result.getDouble("TradeAccout");
				double TotalTrade = result.getDouble("TotalTrade");
				double b1 = result.getDouble("b1");
				double bp1 = result.getDouble("bp1");
				double b2 = result.getDouble("b2");
				double bp2 = result.getDouble("bp2");
				double b3 = result.getDouble("b3");
				double bp3 = result.getDouble("bp3");
				double b4 = result.getDouble("b4");
				double bp4 = result.getDouble("bp4");
				double b5 = result.getDouble("b5");
				double bp5 = result.getDouble("bp5");
				double s1 = result.getDouble("s1");
				double sp1 = result.getDouble("sp1");
				double s2 = result.getDouble("s2");
				double sp2 = result.getDouble("sp2");
				double s3 = result.getDouble("s3");
				double sp3 = result.getDouble("sp3");
				double s4 = result.getDouble("s4");
				double sp4 = result.getDouble("sp4");
				double s5 = result.getDouble("s5");
				double sp5 = result.getDouble("sp5");
				int color = result.getInt("Color");

				StockQuoteData quote = new StockQuoteData(ID, code, name, date,
						time, OpenPrice, ClosePrice, CurrentPrice, MaxPrice,
						MinPrice, TradeAccout, TotalTrade, color, b1,bp1,b2,bp2,b3,bp3,b4,bp4,b5,bp5,s1,sp1,s2,sp2,s3,sp3,s4,sp4,s5,sp5);
				// System.out.println(quote.toString() );
				quoteList.add(quote);
			}
			result.close();
			stmt.close();
			DBUtil.closeConnection(conn);

		} catch (SQLException e) {
			System.out.println("SQL Failed");
		}

		// 算出总数，求出第一页
		// System.out.println(quoteList.size());
		total = (int) Math.ceil(quoteList.size() / ROWPERPAGE);
		// System.out.println(total);
		current = 1;
	}
	
	// 获取最新数据
	public void GetNewestData() {

		NewQuoteList.clear();

		try {
			Connection conn = DBUtil.getDbConn();
			Statement stmt = conn.createStatement();

			ResultSet result;

			result = stmt
					.executeQuery("select * from quotedata where ID = (select max(ID) from quotedata);");

			while (result.next()) {

				int ID = result.getInt("ID");
				String name = result.getString("name");
				String date = result.getString("date");
				String time = result.getString("time");
				String code = result.getString("code");
				double OpenPrice = result.getDouble("OpenPrice");
				double ClosePrice = result.getDouble("ClosePrice");
				double CurrentPrice = result.getDouble("CurrentPrice");
				double MaxPrice = result.getDouble("MaxPrice");
				double MinPrice = result.getDouble("MinPrice");
				double TradeAccout = result.getDouble("TradeAccout");
				double TotalTrade = result.getDouble("TotalTrade");
				double b1 = result.getDouble("b1");
				double bp1 = result.getDouble("bp1");
				double b2 = result.getDouble("b2");
				double bp2 = result.getDouble("bp2");
				double b3 = result.getDouble("b3");
				double bp3 = result.getDouble("bp3");
				double b4 = result.getDouble("b4");
				double bp4 = result.getDouble("bp4");
				double b5 = result.getDouble("b5");
				double bp5 = result.getDouble("bp5");
				double s1 = result.getDouble("s1");
				double sp1 = result.getDouble("sp1");
				double s2 = result.getDouble("s2");
				double sp2 = result.getDouble("sp2");
				double s3 = result.getDouble("s3");
				double sp3 = result.getDouble("sp3");
				double s4 = result.getDouble("s4");
				double sp4 = result.getDouble("sp4");
				double s5 = result.getDouble("s5");
				double sp5 = result.getDouble("sp5");
				int color = result.getInt("Color");

				StockQuoteData quote = new StockQuoteData(ID, code, name, date,
						time, OpenPrice, ClosePrice, CurrentPrice, MaxPrice,
						MinPrice, TradeAccout, TotalTrade, color, b1,bp1,b2,bp2,b3,bp3,b4,bp4,b5,bp5,s1,sp1,s2,sp2,s3,sp3,s4,sp4,s5,sp5);
				// System.out.println(quote.toString() );
				NewQuoteList.add(quote);
			}
			result.close();
			stmt.close();
			DBUtil.closeConnection(conn);

		} catch (SQLException e) {
			System.out.println("SQL Failed");
		}

		// 算出总数，求出第一页
//		System.out.println(NewQuoteList.size());
		total = (int) Math.ceil(quoteList.size() / ROWPERPAGE);
		// System.out.println(total);
		current = 1;
	}

	// 设置模型
	public DefaultTableModel GetModel() {

		// 内容栏
		int column = columnNames.length;

		int row = (int) ROWPERPAGE;
		if (current == total) {
			row = quoteList.size() % row;
		}
		// System.out.println(column + " " + row);
		Object[][] rowData = new Object[row][column];
		int begin = 0, end = 0;

		begin = (int) ((current - 1) * ROWPERPAGE);

		if (current == total) {
			end = quoteList.size();
		} else {
			end = (int) (current * ROWPERPAGE);
		}

		for (int i = begin; i < end; ++i) {
			int ID = (int) (i % ROWPERPAGE);
			DecimalFormat df = new DecimalFormat("0.00");
			StockQuoteData quote = quoteList.elementAt(i);
			rowData[ID][1] = quote.StockCode;
			rowData[ID][2] = quote.StockName;
			rowData[ID][3] = df.format(quote.CurrentPrice);
			rowData[ID][4] = df.format(quote.HighPriceToday);
			rowData[ID][5] = df.format(quote.LowPriceToday);
			rowData[ID][6] = df.format(quote.OpenPriceToday);
			rowData[ID][7] = df.format(quote.ClosePriceYestoday);
			rowData[ID][8] = df.format(quote.TotalNumber / 1000000); // 每100股为1手
			rowData[ID][9] = df.format(quote.TotalMoney / 100000000);
		}
		DefaultTableModel model = new DefaultTableModel(rowData, columnNames);

		return model;
	}
	
	// 设置最新的模型
	public DefaultTableModel GetNewestModel() {

		// 内容栏
		int column = columnNames.length;

		int row = (int) ROWPERPAGE;
		if (current == total) {
			row = NewQuoteList.size() % row;
		}
		// System.out.println(column + " " + row);
		Object[][] rowData = new Object[row][column];
		int begin = 0, end = 0;

		begin = (int) ((current - 1) * ROWPERPAGE);

		if (current == total) {
			end = NewQuoteList.size();
		} else {
			end = (int) (current * ROWPERPAGE);
		}

		
		for (int i = begin; i < end; ++i) {
			int ID = (int) (i % ROWPERPAGE);
			DecimalFormat df = new DecimalFormat("0.00");
			StockQuoteData quote = NewQuoteList.elementAt(i);
			rowData[ID][0] = quote.ID;
			rowData[ID][1] = quote.StockCode;
			rowData[ID][2] = quote.StockName;
			rowData[ID][3] = df.format(quote.CurrentPrice);
			rowData[ID][4] = df.format(quote.HighPriceToday);
			rowData[ID][5] = df.format(quote.LowPriceToday);
			rowData[ID][6] = df.format(quote.OpenPriceToday);
			rowData[ID][7] = df.format(quote.ClosePriceYestoday);
			rowData[ID][8] = df.format(quote.TotalNumber / 1000000); // 每100股为1手
			rowData[ID][9] = df.format(quote.TotalMoney / 100000000);
		}
		DefaultTableModel model = new DefaultTableModel(rowData, columnNames);

		return model;
	}

	// 列表的选择
	public void pageNext() {
		if (current >= total) {
			current = total;
			return;
		} else
			current++;
	}

	public void pagePrevious() {
		if (current <= 1) {
			current = 1;
			return;
		} else
			current--;
	}

	//
	public double getMostMin() {
		double mostMin = 10000;
		for (int i = 0; i < quoteList.size(); ++i) {
			StockQuoteData quote = (StockQuoteData) quoteList.elementAt(i);
			double openPrice = quote.CurrentPrice;
			if (openPrice < mostMin)
				mostMin = openPrice;
		}

		return mostMin;
	}

	//
	public double getMostMax() {
		double mostMax = 0;
		for (int i = 0; i < quoteList.size(); ++i) {
			StockQuoteData quote = (StockQuoteData) quoteList.elementAt(i);
			double openPrice = quote.CurrentPrice;
			if (openPrice > mostMax)
				mostMax = openPrice;
		}
		return mostMax;
	}

	// 最大成交量
	public double getMostTrade() {
		double mostMaxTrade = 0;
		for (int i = 0; i < tradeNumberList.size(); ++i) {
			double total = tradeNumberList.elementAt(i);// 总成交手数
			if (total > mostMaxTrade)
				mostMaxTrade = total;
		}
		return mostMaxTrade;
	}

	public String getMarket(String code) {
		String prefix = code.substring(0, 1);
		// System.out.println(prefix);
		if (prefix.equals("0"))
			return "sz";
		else if (prefix.equals("6"))
			return "sh";

		return "sh";
	}

	public Vector<Double> getTradeList() {
		Vector<Double> tradeNumberList = new Vector<Double>();
		double before = quoteList.elementAt(0).TotalNumber / 100;
		tradeNumberList.add(before);
		for (int i = 1; i < quoteList.size(); ++i) {
			StockQuoteData quote = (StockQuoteData) quoteList.elementAt(i);
			double current = quote.TotalNumber / 100 - before;
			// System.out.println(current);
			tradeNumberList.add(current);
			before += current;
		}
		return tradeNumberList;

	}

	public static void main(String args[]) {
		QuoteController qc = QuoteController.getQuoteControllerInstance();
		qc.GetNewestModel();
		System.out.println(qc.getMostTrade());
	}

}
