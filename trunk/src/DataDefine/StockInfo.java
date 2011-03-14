package BigWise.DataDefine;

public class StockInfo {
	
	// 深A
	public static  String[][] shStock = {
		{"600000","浦发银行","sh"},
		{"600036","招商银行","sh"},
		{"601939","建设银行","sh"},
		{"601328","交通银行","sh"},
		{"601398","工商银行","sh"},
		{"601288","农业银行","sh"}
	};
	
	// 沪A
	public static String[][] szStock = {
		{"000001","深发展A","sz"},
		{"002142","宁波银行","sz"}
	};
	
	public static String[][] zxb =
	{
		
	};
	
	// 获取不同的市场的股票的数量，码值
	public static int GetStockNumberByMarket(String market)
	{
		if(market == "sh")
			return shStock.length;
		else if(market == "sz")
			return szStock.length;
		else if(market == "all")
			return shStock.length + szStock.length;
		return 0;
	}
	// 获取股票的代码
	public static String[] getStockCodesByMarket(String market)
	{
		if(market == "sh")
		{
			String[] StockCodes = new String[shStock.length];
			for(int i = 0; i < StockCodes.length; ++i)
				StockCodes[i] = shStock[i][0];
			return StockCodes;
		}
		else if(market == "sz")
		{
			String[] StockCodes = new String[szStock.length];
			for(int i = 0; i < StockCodes.length; ++i)
				StockCodes[i] = szStock[i][0];
			return StockCodes;
		}
		else if(market == "all")
		{
			String[] StockCodes = new String[szStock.length + shStock.length];
			for(int i = 0; i < shStock.length; ++i)
				StockCodes[i] = shStock[i][0];
			for(int i = 0; i < szStock.length; ++i)
				StockCodes[i+shStock.length] = szStock[i][0];
			return StockCodes;
		}
		return null;
	}
	
	// 获取股票的名称
	public static String[] getStockNamesByMarket(String market)
	{
		if(market == "sh")
		{
			String[] StockNames = new String[shStock.length];
			for(int i = 0; i < StockNames.length; ++i)
				StockNames[i] = shStock[i][1];
			return StockNames;
		}
		else if(market == "sz")
		{
			String[] StockNames = new String[szStock.length];
			for(int i = 0; i < StockNames.length; ++i)
				StockNames[i] = szStock[i][1];
			return StockNames;
		}
		else if(market == "all")
		{
			String[] StockNames = new String[szStock.length + shStock.length];
			for(int i = 0; i < shStock.length; ++i)
				StockNames[i] = shStock[i][1];
			for(int i = 0; i < szStock.length; ++i)
				StockNames[i+shStock.length] = szStock[i][1];
			return StockNames;
		}
		return null;
	}
	public static void main(String args[])
	{
		StockInfo s = new StockInfo();
		for(int i = 0; i < StockInfo.GetStockNumberByMarket("all") ; i ++ )
		{
		System.out.print(StockInfo.getStockCodesByMarket("all")[i] + "/");
		System.out.println(StockInfo.getStockNamesByMarket("all")[i]);
		}
	}

}
