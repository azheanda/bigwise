package BigWise.DataDefine;

import java.util.Date;

/*
 * 历史数据的数据结构
 */
public class StockHisDataDefine {
	String stockCode;			// 股票代码
	Date stockDate;				// 日期
	double openPrice;			// 开盘价
	double closePrice;			// 收盘价
	double maxPrice;			// 最高价格
	double minPrice;			// 平均价格
	double tradeAcount;			// 交易量
	double totalTrade;			// 交易金额
	
	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public Date getStockDate() {
		return stockDate;
	}

	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}

	public double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}

	public double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getTradeacount() {
		return tradeAcount;
	}

	public void setTradeacount(double tradeAcount) {
		this.tradeAcount = tradeAcount;
	}

	public double getTotalTrade() {
		return totalTrade;
	}

	public void setTotaltrade(double totalTrade) {
		this.totalTrade = totalTrade;
	}
}
