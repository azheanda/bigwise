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
		System.out.println("�����Ʊ����");
		daima = sc.nextInt();
		System.out.println("����Ҫ��������");
		year = sc.nextInt();
		System.out.println("����Ҫ����ļ���");
		jidu = sc.nextInt();
			
		//��ҳ��ַ
		URL gupiao = new URL("http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/600000.phtml?year=2011&jidu=1");
		//����������
		BufferedReader br = new BufferedReader(new InputStreamReader(gupiao.openStream()));
		//��������ĵ�	
		FileOutputStream fosout = new FileOutputStream("C:\\a.txt");
		File file=new File("C:\\b.txt");
		FileReader fr=new FileReader(file);//�����ļ������� 
		BufferedReader in=new BufferedReader(br);//��װ�ļ������������ж�ȡ
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