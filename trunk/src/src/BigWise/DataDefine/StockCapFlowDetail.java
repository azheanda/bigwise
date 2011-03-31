package BigWise.Model;


// 成交明细
public class StockCapFlowDetail 
{
	public String StockCode;				// 股票代码
	public String StockName;				// 股票名称
	public String StockDate;				// 日期
	public String StockTime;				// 日期
	public String Price;					// 成交价格
	public String UpDown;					// 涨跌幅
	public String Variant;					// 价格变动
	public String Number;					// 成交量
	public String Accout;					// 成交额

	public String toString()
	{
		return StockCode + " | " + StockName + " | " + StockDate + " | " + StockTime + " | " + Price + " | " + UpDown + " | " +Variant + "|" + Number + " | "
		+ Accout ;
	}
}
