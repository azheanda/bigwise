package BigWise.UI.History;

import java.awt.*;
import java.io.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BigWise.Controller.HistoryController;
import BigWise.DataSpider.*;
import BigWise.DataSpider.History.HistoryDataSpider;
import BigWise.DataSpider.Quote.QuoteDataSpider;
import BigWise.Model.*;

import java.text.DecimalFormat;
import java.lang.Math;


public class HistoryDataBodyPanel extends JPanel implements ActionListener{
   
	HistoryController hc;
	
	JPanel pLeftPanel;
	JLabel showLabel;
	
	JScrollPane pContentPane;
	JTable tTable;
    Object columnNames[] = { "ID","日期", "开盘价", "收盘价","最高价","最低价","总手(万)","金额(亿)"};
	
	
    JPanel pDownPanel;
    JButton bNext = new JButton("下一页");
    JButton bPrevious = new JButton("上一页");
	
    public HistoryDataBodyPanel()
	{
    	hc = HistoryController.getHistoryControllerInstance();

		showLabel = new JLabel(hc.c.StockCode);
		pLeftPanel = new JPanel();
		pLeftPanel.add(showLabel);
		this.add(pLeftPanel);
    	init();
    	
    	pContentPane = new JScrollPane(tTable);
 	    pContentPane.setPreferredSize(new Dimension(1000,600));
 	    
 	    this.add(pContentPane);
 	    
 	    
 	    pDownPanel = new JPanel();
 	    pDownPanel.add(bPrevious);
 	    bPrevious.addActionListener(this);
 	    pDownPanel.add(bNext);
 	    bNext.addActionListener(this);
 	    pDownPanel.setSize(1000,100);
 	    this.add(pDownPanel);
	}
    
    public void init()
    {	
    	hc.init();
		DefaultTableModel model = GetModel(hc.HistoryDataList);  
		tTable = makeTable(model);  
    }
	public void refresh()
	{
		showLabel.setText(hc.c.StockCode);
		init();
		pContentPane.getViewport().add(tTable,null);
	    pContentPane.validate();
	}
	public HistoryDataBodyPanel getHistoryDataPanel()
	{
		return this;
	}
	// 设置模型
	public DefaultTableModel GetModel(Vector<StockHistoryData> list)
	{
		//System.out.println(list.size());
		//内容栏
	    int column = columnNames.length;
	    
	    int row	= (int)(hc.ROWPERPAGE);
	    if(hc.current == hc.total)
	    {
	    	row = list.size() % row;
	    }
	    //System.out.println(column + " " + row);
	    Object[][] rowData =  new Object[row][column];
	    int begin =0 , end = 0;
	    begin = (int)((hc.current-1)*(hc.ROWPERPAGE));
	    if(hc.current == hc.total)
	    {
	    	end = list.size();
	    }
	    else
	    {
	    	end = (int)(hc.current*(hc.ROWPERPAGE));
	    }
	    
	    for(int i = begin ; i < end ; ++i)
	    {
	    	int ID = (int)(i % (hc.ROWPERPAGE));
	    	
	    	DecimalFormat df = new DecimalFormat("0.00");
	    	StockHistoryData historyData = list.elementAt(i);
	    	//System.out.println(historyData.StockDate);
	    	rowData[ID][0] = i + 1;
	    	rowData[ID][1] = historyData.StockDate;
	    	rowData[ID][2] = historyData.OpenPrice;
	    	rowData[ID][3] = historyData.ClosePrice;
	    	rowData[ID][4] = historyData.MaxPrice;
	    	rowData[ID][5] = historyData.MinPrice;
	    	rowData[ID][6] = historyData.TotalTrade;		// 每100股为1手
	    	rowData[ID][7] = historyData.TradeAccount;
	    	
	    }
	    DefaultTableModel  model = new DefaultTableModel(rowData,columnNames);
	    
	    return model;
	}
	 public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == bNext)
		{
			hc.pageNext();
			//System.out.println(current + " " + total);
		}
		else if(e.getSource() == bPrevious)
		{
			hc.pagePrevious();
		}
		
	    DefaultTableModel updateModel = GetModel(hc.HistoryDataList);
	    tTable = makeTable(updateModel);
	    pContentPane.getViewport().add(tTable,null);
	    pContentPane.validate();
	 }

	 public JTable makeTable(DefaultTableModel model)
	 {
		 JTable table = new JTable(model)
		 {
			 private static final long serialVersionUID = 1826425922704464300L;
			 public boolean isCellEditable(int row, int column) { 
				    return false;
				   }
		 };
		 TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel)table.getModel());
		 table.setRowSorter(sorter);
		 
		 //设置行高
		 table.setRowHeight(30);
		  
		  //设置表格线
		  table.setGridColor(SystemColor.controlHighlight);
		  
		  //设置字体
		  table.setFont(new Font("Menu.font", Font.ROMAN_BASELINE, 12));
		  // 设置表头居中显示
		  JTableHeader tbh = table.getTableHeader();
		  DefaultTableCellRenderer  renderer = (DefaultTableCellRenderer)tbh.getDefaultRenderer();
		  renderer.setHorizontalAlignment(SwingConstants.CENTER); 
		 try
		 {
			  DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {		    
				  private static final long serialVersionUID = 1826425922704465800L;
					    public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,
					      int row, int column) {
					    	
					    	// 股票的涨跌是跟昨日收盘价格相比较
							// 设置每只股票的颜色
					    	// 收盘价格
							double ClosePrice = Double.parseDouble(table.getModel().getValueAt(row, 3).toString());
							// 开盘价格
							double OpenPrice = Double.parseDouble(table.getModel().getValueAt(row, 2).toString());
							//System.out.println(CurrentPrice + " |" + ClosePriceYestoday);
						
							if((int)ClosePrice ==  0)
								setForeground(Color.WHITE);//白色
							else if(OpenPrice > ClosePrice)
								setForeground(new Color(80,255,80));//绿色
							else if(OpenPrice <= ClosePrice)
								setForeground(new Color(255,82,82));//红色
					    	
								
							//设置奇偶行颜色
						     if (row % 2 == 0){
						    	 setBackground(new Color(185,215,250)); // 设置奇数行底色
						    	 //setBackground(Color.BLACK);
						     }
						     else if (row % 2 == 1){
						    	 setBackground(new Color(237, 237, 237)); // 设置偶数行底色
						    	 //setBackground(Color.BLACK); // 设置偶数行底色
						     }
						         
						     //如果为数值则居中对齐
						     Pattern p = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
						     String sv = (value != null ? value.toString() : "");  
						     if(p.matcher(sv).matches())
						     {
						    	 this.setHorizontalAlignment(SwingConstants.CENTER);
						     }
						     else{
						      this.setHorizontalAlignment(SwingConstants.CENTER);
						     } 
						     
						     return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
						    }
					    	
			};
			   
			for (int i = 0; i < table.getColumnCount(); i++)
			{
			   TableColumn tc = table.getColumn(table.getColumnName(i));
			   tc.setCellRenderer(tcr);
			}
			   
		 } catch (Exception ex)
		 {
			 ex.printStackTrace();
		 }
		
		table.getColumnModel().getColumn(0).setPreferredWidth(27);
		table.getColumnModel().getColumn(1).setPreferredWidth(77);
		table.getColumnModel().getColumn(2).setPreferredWidth(77);
		table.getColumnModel().getColumn(3).setPreferredWidth(57);
		table.getColumnModel().getColumn(4).setPreferredWidth(57);
		table.getColumnModel().getColumn(5).setPreferredWidth(57);
		table.getColumnModel().getColumn(6).setPreferredWidth(87);
		table.getColumnModel().getColumn(7).setPreferredWidth(87);


		Container c = table.getParent();
		  if(c instanceof JViewport){
		   JViewport jp = (JViewport)c;
		   jp.setBackground(new Color(250, 250, 250));
		  }
		return table;
	 }
	 
	 
	 public static void main(String args[]) {
		    JFrame frame = new JFrame("QuoteBodyPanel");
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    HistoryDataBodyPanel tmp = new HistoryDataBodyPanel();
		    frame.add(tmp);
		    frame.setSize(1000,720);
		    frame.setVisible(true);

		  }
}
