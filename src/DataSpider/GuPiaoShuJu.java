import java.io.*;
import java.net.*;
import java.util.regex.*;

public class GuPiaoShuJu {

	private static String getDocumnetAt(String urlString) {
		StringBuffer html_text = new StringBuffer();

		try {
			// ����ָ���Ʊ��ַ������
			URL url = new URL(urlString);
			// ��������
			URLConnection uc = url.openConnection();
			// ����������
			BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			// ����ҳ���ݷŵ�������
			String line = null;
			while ((line = reader.readLine()) != null) {
				html_text.append(line + " ");
			}
			// �ر�������
			reader.close();
		} catch (MalformedURLException e) {
			System.out.print("invalid url:" + urlString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return html_text.toString();
	}

	public void extractor(String urlString) throws IOException {
		// �ļ������
		FileOutputStream fos = new FileOutputStream("C:\\a.txt");
		OutputStreamWriter ows = new OutputStreamWriter(fos);
		try {
			// �����ҳ�ı�����
			String str = GuPiaoShuJu.getDocumnetAt(urlString);
			// ������ȡ��Ʊ������Դ��������ʽ
			Pattern gp_source = Pattern.compile("(?<=<th colspan=\"7\">|\"blue\">|</FONT>).*?(?=<|FONT|</th>)");
			Matcher mc = gp_source.matcher(str);
			String s1;
			while (mc.find()) {
				// ��ȡ��Ʊ������Դ
				s1 = String.valueOf(mc.group());
				ows.write(s1);
				System.out.printf("%s", mc.group());
			}
			System.out.println();
			ows.write("\r\n");

			// �����Ʊ������Ŀ
			String s2;
			Pattern gp_item = Pattern
					.compile("(?<=<strong>).*?(?=</strong>)");
			Matcher n = gp_item.matcher(str);
			while (n.find()) {
				s2 = String.valueOf(n.group());
				ows.write(s2 + "          ");
				System.out.printf("%-42s", n.group());
			}

			// ��ȡ��Ʊ������ϸ���
			Pattern gp_data = Pattern.compile("((?<=date=)(\\w*?)).*?(?=('>))|((?<=center\">)(\\d{1,7}?)).*?(?=(</div>))");
			Matcher m = gp_data.matcher(str);
			String s3;
			int i = 0;
			while (m.find()) {
				if (i == 0)
					System.out.println();
				i++;
				s3 = String.valueOf(m.group());
				ows.write(s3 + "          ");

				System.out.printf("%-20s", m.group());
				if (i % 7 == 0) {
					System.out.println();
					ows.write("\r\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PatternSyntaxException e) {
			System.out.println("Regular expression syntax error");
		} catch (IllegalStateException e) {
			System.out.println("Do not find the pattern");
		} finally {
			if (ows != null) {
				ows.close();
				fos.close();
			}
		}
	}

	public static void main(String[] args) {
		GuPiaoShuJu test = new GuPiaoShuJu();
		try {
			test.extractor("http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/600000.phtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
