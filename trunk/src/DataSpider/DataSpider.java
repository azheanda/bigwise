import java.net.*;
import java.io.*;
import java.util.regex.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class DataSpider {
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		String s;
		String str = null;
		String str1 = null;
		int daima;
		int year;
		int jidu;
		int i = 0;
		int j = 0 ;
		System.out.println("Enter the Stock Code");
		daima = sc.nextInt();
		System.out.println("Enter Year");
		year = sc.nextInt();
    System.out.println("Enter the Season:");
		jidu = sc.nextInt();
			
		// Get the Data
		URL gupiao = new URL("http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/"+daima+".phtml?year="+year+"&jidu="+jidu);
		// Read Line By Line
		BufferedReader br = new BufferedReader(new InputStreamReader(gupiao.openStream()));
		FileOutputStream fosout = new FileOutputStream("C:\\a.txt");
		File file=new File("C:\\b.txt");
		FileReader fr=new FileReader(file);
		BufferedReader in=new BufferedReader(br);
		OutputStreamWriter owsout = new OutputStreamWriter(fosout);
		while ((str = in.readLine()) != null){ 	
	 		owsout.write(str);
			owsout.write("\r\n") ;
		}
		System.out.println("Success");
		owsout.close();
		fosout.close();
	}
}