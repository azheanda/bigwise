package BigWise.Model;


// �ɽ���ϸ
public class StockCapFlowDetail 
{
	public String StockCode;				// ��Ʊ����
	public String StockName;				// ��Ʊ����
	public String StockDate;				// ����
	public String StockTime;				// ����
	public String Price;					// �ɽ��۸�
	public String UpDown;					// �ǵ���
	public String Variant;					// �۸�䶯
	public String Number;					// �ɽ���
	public String Accout;					// �ɽ���

	public String toString()
	{
		return StockCode + " | " + StockName + " | " + StockDate + " | " + StockTime + " | " + Price + " | " + UpDown + " | " +Variant + "|" + Number + " | "
		+ Accout ;
	}
}
