package BigWise.Model;

import java.awt.Color;

/*
 * http://hq.sinajs.cn/list=sh601006
 ���url�᷵��һ���ı������磺
 var hq_str_sh601006="������·, 27.55, 27.25, 26.91, 27.55, 26.20, 26.91, 26.92, 
 22114263, 589824680, 4695, 26.91, 57590, 26.90, 14700, 26.89, 14300,
 26.88, 15100, 26.87, 3100, 26.92, 8900, 26.93, 14230, 26.94, 25150, 26.95, 15220, 26.96, 2008-01-11, 15:05:32";
 ����ַ������������ƴ����һ�𣬲�ͬ����������ö��Ÿ����ˣ����ճ���Ա��˼·��˳��Ŵ�0��ʼ��
 0����������·������Ʊ���֣�
 1����27.55�壬���տ��̼ۣ�
 2����27.25�壬�������̼ۣ�
 3����26.91�壬��ǰ�۸�
 4����27.55�壬������߼ۣ�
 5����26.20�壬������ͼۣ�
 6����26.91�壬����ۣ�������һ�����ۣ�
 7����26.92�壬�����ۣ�������һ�����ۣ�
 8����22114263�壬�ɽ��Ĺ�Ʊ�������ڹ�Ʊ������һ�ٹ�Ϊ������λ��������ʹ��ʱ��ͨ���Ѹ�ֵ����һ�٣�
 9����589824680�壬�ɽ�����λΪ��Ԫ����Ϊ��һĿ��Ȼ��ͨ���ԡ���Ԫ��Ϊ�ɽ����ĵ�λ������ͨ���Ѹ�ֵ����һ��
 10����4695�壬����һ������4695�ɣ���47�֣�
 11����26.91�壬����һ�����ۣ�
 12����57590�壬�������
 13����26.90�壬�������
 14����14700�壬��������
 15����26.89�壬��������
 16����14300�壬�����ġ�
 17����26.88�壬�����ġ�
 18����15100�壬�����塱
 19����26.87�壬�����塱
 20����3100�壬����һ���걨3100�ɣ���31�֣�
 21����26.92�壬����һ������
 (22, 23), (24, 25), (26,27), (28, 29)�ֱ�Ϊ���������������ĵ������
 30����2008-01-11�壬���ڣ�
 31����15:05:32�壬ʱ�䣻

 */
public class StockQuoteData {
	public int ID; // ÿ�칲��48�ε�ʵʱ���ݣ�0~47
	public String StockCode; // ��Ʊ����
	public String StockName; // ��Ʊ����
	public String StockDate; // ����
	public String StockTime; // ʱ��
	public double OpenPriceToday; // ���տ��̼�
	public double ClosePriceYestoday; // �������̼�
	public double CurrentPrice; // ��ǰ�۸��
	public double HighPriceToday; // ������߼۸�
	public double LowPriceToday; // ������ͼ۸�
	public double TradeAccount; // ���ս�����
	public double TotalTrade; // ���ս��׽��
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

	public int StockColor; // 0�ǰ�ɫ��1�Ǻ�ɫ��2����ɫ

	public StockQuoteData(int id, String code, String name, String date,
			String time, double openprice, double closeprice,
			double currentPrice, double maxprice, double lowPrice,
			double tradeaccout, double totaltrade, int color, double b1,
			double b2, double b3, double b4, double b5, double bp1, double bp2,
			double bp3, double bp4, double bp5, double s1, double s2,
			double s3, double s4, double s5, double sp1, double sp2, double sp3,
			double sp4, double sp5) {
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
		TradeAccount = tradeaccout;
		TotalTrade = totaltrade;
		StockColor = color;
	}

	public StockQuoteData() {

	}

	public String toString() {
		return StockCode + " | " + StockName + " | " + StockDate + " | "
				+ StockTime + " | " + OpenPriceToday + " | "
				+ ClosePriceYestoday + " | " + CurrentPrice + " | "
				+ HighPriceToday + " | " + LowPriceToday + " | " + TotalTrade
				+ " | " + TradeAccount + "| " + StockColor;
	}
}
