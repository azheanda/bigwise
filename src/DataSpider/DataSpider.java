package BigWise.DataSpider;
import java.net.*;
import java.io.*;
import java.util.regex.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class DataSpider {
	public static void main(String[] args) throws IOException {

		String str = "";
		// Get the Data
		URL gupiao = new URL("http://hq.sinajs.cn/list=sh601006");
		// Read Line By Line
		BufferedReader br = new BufferedReader(new InputStreamReader(gupiao.openStream()));
		FileOutputStream fosout = new FileOutputStream(".\\a.txt");
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