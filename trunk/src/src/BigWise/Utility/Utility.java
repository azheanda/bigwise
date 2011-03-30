
package BigWise.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

public class Utility {

	// 根据代码获取市场
	public static String getMarket(String code)
	{
		 String prefix = code.substring(0, 1);
		 //System.out.println(prefix);
		 if(prefix.equals("0"))
			 return "sz";
		 else if (prefix.equals("6"))
			 return "sh";
		 
		 return "sh";
	}
	
	// 是否是股市时间
	public static boolean IsMarketTime()
	{
		boolean flag = false;
		Calendar now   = Calendar.getInstance();
//		int    year = now.get( Calendar.YEAR );
//		int    date = now.get( Calendar.DAY_OF_MONTH );
//		int    month = now.get( Calendar.MONTH ) + 1;
		int AmPm = now.get(Calendar.AM_PM);
		int    hour = now.get( Calendar.HOUR ) + 12*AmPm;
		int    min   = now.get( Calendar.MINUTE );
//		int    sec   = now.get( Calendar.SECOND );
		if(hour == 9  && min >= 30)
			flag = true;
		else if(hour == 10)
			flag = true;
		else if(hour == 11 && min <= 30)
			flag = true;
		else if(hour == 13)
			flag =true;
		else if(hour == 14)
			flag = true;
		System.out.println(flag);
		return flag;
	}
	
	public static void main(String[] args){
		System.out.println(Utility.getMarket("600000"));
		Utility.IsMarketTime();
	}
}