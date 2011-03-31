package BigWise.DataSpider.History;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.regex.*;
import java.util.logging.Logger;

import BigWise.DataSpider.Quote.QuoteDataSpider;
import BigWise.Model.StockHistoryData;
import BigWise.Model.StockInfo;
import BigWise.Model.StockQuoteData;
import BigWise.Utility.DBUtil;
import BigWise.Utility.Utility;

/*
 * This Class Used to Get Day HisData.
 * method:
 * �����Բ鿴20�����ʷ����
 *  */

public class HistoryDataSpider extends Thread {

	public static Vector<StockHistoryData> GetHistoryDataList() {

		// ��Ʊ�б�
		Vector<StockInfo> StockList = StockInfo.getStockListByKeyword("market",
				"*");

		// ��Ʊ�б��еĹ�Ʊ��ֻ��
		int StockNumber = StockList.size();

		// ��������

		Vector<StockHistoryData> HistoryDataList = new Vector<StockHistoryData>();

		// System.out.println(StockNumber);
		for (int i = 0; i < StockNumber; ++i) {
			Vector<StockHistoryData> list = new Vector<StockHistoryData>();
			list = HistoryDataSpider.extractor(StockList.elementAt(i));
			for (int j = 0; j < list.size(); ++j) {
				HistoryDataList.add(list.elementAt(j));
			}

		}

		try {
			Connection conn = DBUtil.getDbConn();

			Statement stmt = conn.createStatement();

			for (int i = 0; i < HistoryDataList.size(); ++i) {
				StockHistoryData history = HistoryDataList.elementAt(i);
				String code = history.StockCode;
				String date = history.StockDate;

				String sql = "insert into HistoryData values('" + code + "','"
						+ date + "'," + history.OpenPrice + ","
						+ history.ClosePrice + "," + history.MaxPrice + ","
						+ history.MinPrice + ", " + history.TradeAccount + ","
						+ history.TotalTrade + ");";
				System.out.println(sql);
				stmt.execute(sql);
			}

			stmt.close();
			DBUtil.closeConnection(conn);
		} catch (SQLException ex) {
			System.out.println("failed");
		}

		return HistoryDataList;
	}

	/*
	 * String GetDocumentAt(String url) static method, return the string of the
	 * html page.
	 */
	private static String getDocumnetAt(String urlString) {
		StringBuffer html_text = new StringBuffer();
		try {
			// ����ָ���Ʊ��ַ������
			URL url = new URL(urlString);
			// ��������
			URLConnection uc = url.openConnection();
			// ����������
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					uc.getInputStream()));
			// ����ҳ���ݷŵ�������
			String line = null;
			while ((line = reader.readLine()) != null) {
				html_text.append(line + " ");
			}
			// �ر�������
			reader.close();
		} catch (MalformedURLException e) {
			System.out.print("invalid url:" + urlString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return html_text.toString();
	}

	public static Vector<StockHistoryData> extractor(StockInfo stock) {
		String code = stock.code;
		Vector<StockHistoryData> HistoryDataList = new Vector<StockHistoryData>();

		String url = "http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/"
				+ code + ".phtml";

		// �ļ������
		// FileOutputStream fos = new FileOutputStream(".\\"+code+".txt");
		// OutputStreamWriter ows = new OutputStreamWriter(fos);
		// try {
		// �����ҳ�ı�����
		String str = HistoryDataSpider.getDocumnetAt(url);

		// ��ȡ��Ʊ������ϸ���
		Pattern gp_data = Pattern
				.compile("((?<=date=)(\\w*?)).*?(?=('>))|((?<=center\">)(\\d{1,7}?)).*?(?=(</div>))");
		Matcher m = gp_data.matcher(str);

		while (m.find()) {
			StockHistoryData historyData = new StockHistoryData();
			historyData.StockCode = code; // ����
			historyData.StockDate = String.valueOf(m.group()); // ����

			m.find();
			historyData.OpenPrice = Double
					.parseDouble(String.valueOf(m.group())); // ���̼�

			m.find();
			historyData.MaxPrice = Double
					.parseDouble(String.valueOf(m.group()));	// ���̼�
			m.find();
			historyData.ClosePrice = Double.parseDouble(String.valueOf(m
					.group()));

			m.find();
			historyData.MinPrice = Double
					.parseDouble(String.valueOf(m.group()));

			m.find();
			historyData.TradeAccount = Double.parseDouble(String.valueOf(m
					.group()));

			m.find();
			historyData.TotalTrade = Double.parseDouble(String.valueOf(m
					.group()));

			HistoryDataList.add(historyData);

			// ows.write("\r\n");
		}

		return HistoryDataList;
	}

	public void run() {
		while (true) {
			try {
				// ÿ��ץȡһ����ʷ����
				System.out.println("catch History data");
				HistoryDataSpider.GetHistoryDataList();
				Thread.sleep(86400000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Vector<StockHistoryData> HistoryDataList = HistoryDataSpider
				.GetHistoryDataList();
		System.out.println(HistoryDataList.size());
	}

}
