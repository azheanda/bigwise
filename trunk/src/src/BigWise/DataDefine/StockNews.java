package BigWise.DataDefine;

public class StockNews {
	public String NewsDate;
	public String NewsTime;
	public String NewsTitle;
	public String NewsContent;
	public String NewsJudge;
	
	public String toString()
	{
		return NewsDate + NewsTime + NewsTitle + " | " + NewsContent + "Judge";
	}
}
