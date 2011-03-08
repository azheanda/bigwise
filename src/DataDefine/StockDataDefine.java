package DataDefine;

import java.util.Date;

public class StockDataDefine {
	String stockCode;
	Date stockDate;
	double openPrice;
	double closePrice;
	double maxPrice;
	double minPrice;
	double tradeAcount;
	double totalTrade;
	
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
