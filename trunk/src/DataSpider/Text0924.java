package DataSpider;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text0924 {

	public static void main(String[] args) throws IOException {
		String s;
		int i = 0;
		URL url = new URL(" http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/600004.phtml?year=2010");
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		FileOutputStream fos = new FileOutputStream(".\\GuPiao.txt");
		OutputStreamWriter ows = new OutputStreamWriter(fos);
		Pattern q = Pattern.compile("((?<=g>)(\\w*?)).*?(?=(</strong>))");
		Pattern p = Pattern.compile("((?<=date=)(\\w*?)).*?(?=('>))|((?<=center\">)(\\d{1,7}?)).*?(?=(</div>))");
		String str = null;
		while ((str = br.readLine()) != null) {
			Matcher n = q.matcher(str);
			Matcher m = p.matcher(str);
			while (n.find()) {
				s = String.valueOf(n.group());
				ows.write(s + "		 ");
				System.out.printf("%-44s", n.group());

			}
			while (m.find()){
				if (i == 0) {
					System.out.println();
					ows.write("\r\n");
				}
				i++;
				s = String.valueOf(m.group());
				ows.write(s + "	   ");
				System.out.printf("%-22s", m.group());
				if (i % 7 == 0) {
					System.out.println();
					ows.write("\r\n");
				}
			}
		}
		ows.close();
		fos.close();
		br.close();
		br.close();

	}

}
