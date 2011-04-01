package BigWise.UI;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.*;
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import BigWise.UI.RTLine.RTLine;
public class HeaderPanel extends JPanel implements ActionListener {
	
	JButton   bQuote   =   new   JButton( "ʵʱ");
	JButton   bHisData   =   new   JButton( "��ʷ");
	JButton   bNews   =   new   JButton( "����");
	JButton   bCapFlow   =   new   JButton( "�ʽ���");
	JButton   bAnalyzer   =   new   JButton( "����");
	Border roundedBorder = new LineBorder(Color.BLACK, 2, true);
	public HeaderPanel()
	{
		this.setLayout(new GridLayout(1,5,10,10));
		
		
		bQuote.setBorder(roundedBorder);
		bHisData.setBorder(roundedBorder);
		bNews.setBorder(roundedBorder);
		bCapFlow.setBorder(roundedBorder);
		bAnalyzer.setBorder(roundedBorder);
		
		this.add(bQuote);
		// ������ӹ������鴦�����ϲ��ϵ�о�û�б�Ҫ����ˣ���Ϊ��BigWise�еĻ�ͬ��������
		bQuote.addActionListener(this);
		this.add(bHisData);
		bHisData.addActionListener(this);
		this.add(bCapFlow); 
		this.add(bNews); 
		this.add(bAnalyzer); 
		this.setSize(100,100);      
	}
	public void actionPerformed(ActionEvent e)
	{
		//System.out.println("HeaderPanel");
	}
	
	public static void main(String[] argv) {
	   
		JFrame frame = new JFrame("test");
		Container c = frame.getContentPane();
		c.add(new HeaderPanel(),"North");
		c.setLayout(new FlowLayout());
		frame.setSize(1100, 700);
		frame.setVisible(true);
	  }
	
		 	
}
