package BigWise.Model;

import java.awt.Color;

/*
 * http://hq.sinajs.cn/list=sh601006
 这个url会返回一串文本，例如：
 var hq_str_sh601006="大秦铁路, 27.55, 27.25, 26.91, 27.55, 26.20, 26.91, 26.92, 
 22114263, 589824680, 4695, 26.91, 57590, 26.90, 14700, 26.89, 14300,
 26.88, 15100, 26.87, 3100, 26.92, 8900, 26.93, 14230, 26.94, 25150, 26.95, 15220, 26.96, 2008-01-11, 15:05:32";
 这个字符串由许多数据拼接在一起，不同含义的数据用逗号隔开了，按照程序员的思路，顺序号从0开始。
 0：”大秦铁路”，股票名字；
 1：”27.55″，今日开盘价；
 2：”27.25″，昨日收盘价；
 3：”26.91″，当前价格；
 4：”27.55″，今日最高价；
 5：”26.20″，今日最低价；
 6：”26.91″，竞买价，即“买一”报价；
 7：”26.92″，竞卖价，即“卖一”报价；
 8：”22114263″，成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；
 9：”589824680″，成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万；
 10：”4695″，“买一”申请4695股，即47手；
 11：”26.91″，“买一”报价；
 12：”57590″，“买二”
 13：”26.90″，“买二”
 14：”14700″，“买三”
 15：”26.89″，“买三”
 16：”14300″，“买四”
 17：”26.88″，“买四”
 18：”15100″，“买五”
 19：”26.87″，“买五”
 20：”3100″，“卖一”申报3100股，即31手；
 21：”26.92″，“卖一”报价
 (22, 23), (24, 25), (26,27), (28, 29)分别为“卖二”至“卖四的情况”
 30：”2008-01-11″，日期；
 31：”15:05:32″，时间；

 */
public class StockQuoteData {
	public int ID; // 每天共有48次得实时数据，0~47
	public String StockCode; // 股票代码
	public String StockName; // 股票名称
	public String StockDate; // 日期
	public String StockTime; // 时间
	public double OpenPriceToday; // 今日开盘价
	public double ClosePriceYestoday; // 昨日收盘价
	public double CurrentPrice; // 当前价格格
	public double HighPriceToday; // 今日最高价格
	public double LowPriceToday; // 今日最低价格
	public double TotalMoney; // 今日交易金额
	public double TotalNumber; // 今日交易量
	public double Buy1;
	public double Buy2;
	public double Buy3;
	public double Buy4;
	public double Buy5;
	public double Buy1Price;
	public double Buy2Price;
	public double Buy3Price;
	public double Buy4Price;
	public double Buy5Price;
	public double Sell1;
	public double Sell2;
	public double Sell3;
	public double Sell4;
	public double Sell5;
	public double Sell1Price;
	public double Sell2Price;
	public double Sell3Price;
	public double Sell4Price;
	public double Sell5Price;

	public int StockColor; // 0是白色，1是红色，2是绿色

	public StockQuoteData(int id, String code, String name, String date,
			String time, double openprice, double closeprice,
			double currentPrice, double maxprice, double lowPrice,
			double tradeaccout, double totaltrade, int color, double b1,
			double bp1, double b2, double bp2, double b3, double bp3,
			double b4, double bp4, double b5, double bp5, double s1,
			double sp1, double s2, double sp2, double s3, double sp3,
			double s4, double sp4, double s5, double sp5) {
		ID = id;
		StockCode = code;
		StockName = name;
		StockDate = date;
		StockTime = time;
		OpenPriceToday = openprice;
		ClosePriceYestoday = closeprice;
		CurrentPrice = currentPrice;
		HighPriceToday = maxprice;
		LowPriceToday = lowPrice;

		Buy1 = b1;
		Buy2 = b2;
		Buy3 = b3;
		Buy4 = b4;
		Buy5 = b5;
		Buy1Price = bp1;
		Buy2Price = bp2;
		Buy3Price = bp3;
		Buy4Price = bp4;
		Buy5Price = bp5;

		Sell1 = s1;
		Sell2 = s2;
		Sell3 = s3;
		Sell4 = s4;
		Sell5 = s5;
		Sell1Price = sp1;
		Sell2Price = sp2;
		Sell3Price = sp3;
		Sell4Price = sp4;
		Sell5Price = sp5;
		TotalNumber = tradeaccout;
		TotalMoney = totaltrade;
		StockColor = color;
	}

	public StockQuoteData() {

	}

	public String toString() {
		return StockCode + " | " + StockName + " | " + StockDate + " | "
				+ StockTime + " | " + OpenPriceToday + " | "
				+ ClosePriceYestoday + " | " + CurrentPrice + " | "
				+ HighPriceToday + " | " + LowPriceToday + " | " + TotalNumber
				+ " | " + TotalMoney + "| " + StockColor;
	}
}
