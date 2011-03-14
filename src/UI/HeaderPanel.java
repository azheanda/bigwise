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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
public class HeaderPanel extends JPanel implements ActionListener {
	
	JButton   bQuote   =   new   JButton( "行情");
	JButton   bHisData   =   new   JButton( "历史数据");
	JButton   bNews   =   new   JButton( "资讯");
	JButton   bAnalyzer   =   new   JButton( "分析");
	JButton   bForum   =   new   JButton( "论坛");
	public HeaderPanel()
	{
		this.setLayout(new GridLayout(1,5));
		this.add(bQuote);
		// 这里添加过的事情处理在上层关系中就没有必要添加了，因为在BigWise中的会同样被处理
		bQuote.addActionListener(this);
		this.add(bHisData);
		bHisData.addActionListener(this);
		this.add(bNews); 
		this.add(bAnalyzer); 
		this.add(bForum); 
		this.setSize(100,100);      
	}
	public void actionPerformed(ActionEvent e)
	{
		//System.out.println("HeaderPanel");
	}
	
	public static void main(String[] argv) {
	    Vector rowData = new Vector();
	    for (int i = 0; i < 10; i++) {
	      Vector colData = new Vector(Arrays.asList("qq"));
	      rowData.add(colData);
	    }
	    
	    String[] columnNames = {"a","b","c"};
	    int len = columnNames.length;
	    
	    Vector columnNamesV = new Vector(Arrays.asList(columnNames));

	    JTable table = new JTable(rowData, columnNamesV);
	    JFrame f = new JFrame();
	    f.setSize(300, 300);
	    f.add(new JScrollPane(table));
	    f.setVisible(true);
	  }
	
		 	
}
