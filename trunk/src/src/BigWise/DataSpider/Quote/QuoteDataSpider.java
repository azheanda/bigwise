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

		// ��Ʊ�б�
		Vector<StockInfo> StockList = StockInfo.getStockListByKeyword(type,
				keyword);
		// ��Ʊ�б��еĹ�Ʊ��ֻ��
		int StockNumber = StockList.size();
		System.out.println(StockNumber);

		// �������ݣ�59ֻ��Ʊ����������
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

		// ץȡ�����ۼ�
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
		quoteData.ID = ID; // ��ID��ץȡ���ݣ�ÿ��48��
		quoteData.StockCode = code; // ��Ʊ����
		quoteData.StockName = name; // ��Ʊ����
		quoteData.StockDate = tmp[30]; // ����
		quoteData.StockTime = tmp[31].substring(0, tmp[31].length() - 3); // ʱ��
		quoteData.OpenPriceToday = Double.parseDouble(tmp[1]); // ���տ��̼۸�
		quoteData.ClosePriceYestoday = Double.parseDouble(tmp[2]); // �������̼۸�
		quoteData.CurrentPrice = Double.parseDouble(tmp[3]); // ���ڼ۸�
		quoteData.HighPriceToday = Double.parseDouble(tmp[4]); // ������߼۸�
		quoteData.LowPriceToday = Double.parseDouble(tmp[5]); // ������ͼ۸�

		quoteData.TotalTrade = Double.parseDouble(tmp[8]); // ���ճɽ�����
		quoteData.TradeAccount = Double.parseDouble(tmp[9]); // �����ܽ��׶�

		quoteData.Buy1 = Double.parseDouble(tmp[10]); // ��һ
		quoteData.Buy2 = Double.parseDouble(tmp[12]); // ��һ
		quoteData.Buy3 = Double.parseDouble(tmp[14]); // ��һ
		quoteData.Buy4 = Double.parseDouble(tmp[16]); // ��һ
		quoteData.Buy5 = Double.parseDouble(tmp[18]); // ��һ
		quoteData.Buy1Price = Double.parseDouble(tmp[11]); // ��һ
		quoteData.Buy2Price = Double.parseDouble(tmp[13]); // ��һ
		quoteData.Buy3Price = Double.parseDouble(tmp[15]); // ��һ
		quoteData.Buy4Price = Double.parseDouble(tmp[17]); // ��һ
		quoteData.Buy5Price = Double.parseDouble(tmp[19]); // ��һ

		quoteData.Sell1 = Double.parseDouble(tmp[20]); // ��һ
		quoteData.Sell2 = Double.parseDouble(tmp[22]); // ��һ
		quoteData.Sell3 = Double.parseDouble(tmp[24]); // ��һ
		quoteData.Sell4 = Double.parseDouble(tmp[26]); // ��һ
		quoteData.Sell5 = Double.parseDouble(tmp[28]); // ��һ
		quoteData.Sell1Price = Double.parseDouble(tmp[21]); // ��һ
		quoteData.Sell2Price = Double.parseDouble(tmp[23]); // ��һ
		quoteData.Sell3Price = Double.parseDouble(tmp[25]); // ��һ
		quoteData.Sell4Price = Double.parseDouble(tmp[27]); // ��һ
		quoteData.Sell5Price = Double.parseDouble(tmp[29]); // ��һ

		// ��Ʊ���ǵ��Ǹ��������̼۸���Ƚ�
		if (quoteData.CurrentPrice < quoteData.ClosePriceYestoday)
			quoteData.StockColor = 2;// ��ɫ
		else if (quoteData.CurrentPrice >= quoteData.ClosePriceYestoday)
			quoteData.StockColor = 1;// ��ɫ
		else if ((int) (quoteData.CurrentPrice) == 0)
			quoteData.StockColor = 0;// ��ɫ

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
					// ÿ��5����ץȡһ��ʵʱ����
					System.out.println("catch RT data");
					QuoteDataSpider.GetQuoteDataByKeyword("market", "*");
					Thread.sleep(300000);
				} else {
					// �����г�ʱ�䣬ÿ����Сʱ���һ��
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
		// ÿ����48������
		QuoteDataSpider qds = new QuoteDataSpider();
		qds.start();
	}
}