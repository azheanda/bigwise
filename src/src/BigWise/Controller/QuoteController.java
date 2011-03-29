/*
 * 实时数据的控制
 */
package BigWise.Controller;

import BigWise.DataSpider.History.HistoryDataSpider;
import BigWise.DataSpider.Quote.QuoteDataSpider;
import BigWise.Model.StockInfo;
import BigWise.Model.StockQuoteData;
import BigWise.Utility.DBUtil;

import java.io.IOException;
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

	Object columnNames[] = { "ID", "代码", "名称","最新价", "最高价", "最低价", "今日开盘价",
			"昨日收盘价", "总手(万)", "金额(亿)" };

	private QuoteController() {
		controller = Controller.getControllerInstance();

		init();
	}

	// 获取数据
	public void init()
	{
		GetData(controller.StockCode);
	}

	// 获取数据
	public void GetData(String code) {
		
		quoteList.clear();
		
		try {
			Connection conn = DBUtil.getDbConn();
			Statement stmt = conn.createStatement();

			ResultSet result;

			result = stmt.executeQuery(" select * from quotedata where code = '"+ code + "';");

			
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
				int color = result.getInt("Color");
				
				StockQuoteData quote = new StockQuoteData(ID, code, name, date,
						time, OpenPrice, ClosePrice, CurrentPrice, MaxPrice,
						MinPrice, TradeAccout, TotalTrade, color);
				//System.out.println(quote.toString() );
				quoteList.add(quote);
			}
			result.close();
			stmt.close();
			DBUtil.closeConnection(conn);

		} catch (SQLException e) {
			System.out.println("SQL Failed");
		}

		// 算出总数，求出第一页
		//System.out.println(quoteList.size());
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
			rowData[ID][0] = quote.ID;
			rowData[ID][1] = quote.StockCode;
			rowData[ID][2] = quote.StockName;
			rowData[ID][3] = df.format(quote.CurrentPrice);
			rowData[ID][4] = df.format(quote.HighPriceToday);
			rowData[ID][5] = df.format(quote.LowPriceToday);
			rowData[ID][6] = df.format(quote.OpenPriceToday);
			rowData[ID][7] = df.format(quote.ClosePriceYestoday);
			rowData[ID][8] = df.format(quote.TotalTrade / 1000000); // 每100股为1手
			rowData[ID][9] = df.format(quote.TradeAccount / 100000000);
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

	public String getMarket(String code) {
		String prefix = code.substring(0, 1);
		// System.out.println(prefix);
		if (prefix.equals("0"))
			return "sz";
		else if (prefix.equals("6"))
			return "sh";

		return "sh";
	}

	public static void main(String args[]) {
		QuoteController qc = QuoteController.getQuoteControllerInstance();
	}

}
