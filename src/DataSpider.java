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
		System.out.println("输入股票代码");
		daima = sc.nextInt();
		System.out.println("输入要插入的年份");
		year = sc.nextInt();
		System.out.println("输入要插入的季度");
		jidu = sc.nextInt();
			
		//网页地址
		URL gupiao = new URL("http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/600000.phtml?year=2011&jidu=1");
		//创建输入流
		BufferedReader br = new BufferedReader(new InputStreamReader(gupiao.openStream()));
		//创建输出文档	
		FileOutputStream fosout = new FileOutputStream("C:\\a.txt");
		File file=new File("C:\\b.txt");
		FileReader fr=new FileReader(file);//创建文件输入流 
		BufferedReader in=new BufferedReader(br);//包装文件输入流，整行读取
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