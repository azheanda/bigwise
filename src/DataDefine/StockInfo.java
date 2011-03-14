package BigWise.DataDefine;

public class StockInfo {
	
	// ��A
	public static  String[][] shStock = {
		{"600000","�ַ�����","sh"},
		{"600036","��������","sh"},
		{"601939","��������","sh"},
		{"601328","��ͨ����","sh"},
		{"601398","��������","sh"},
		{"601288","ũҵ����","sh"}
	};
	
	// ��A
	public static String[][] szStock = {
		{"000001","�չA","sz"},
		{"002142","��������","sz"}
	};
	
	public static String[][] zxb =
	{
		
	};
	
	// ��ȡ��ͬ���г��Ĺ�Ʊ����������ֵ
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
	// ��ȡ��Ʊ�Ĵ���
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
	
	// ��ȡ��Ʊ������
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
