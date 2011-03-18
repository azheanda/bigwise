package BigWise.UI.Quote;

import java.awt.*;
import java.io.*;
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
import BigWise.DataSpider.*;
import BigWise.DataSpider.Quote.QuoteDataSpider;
import BigWise.DataDefine.*;

import java.text.DecimalFormat;
import java.lang.Math;


public class QuoteDataBodyPanel extends JPanel implements ActionListener,ListSelectionListener{
   
	private static double ROWPERPAGE = 19;
	public int total = 0;
	public int current = 0;
	StockQuoteData[] QuoteData;					//   ���� 
	JPanel pLeftPanel;
	JScrollPane pContentPane;
	JPanel pDownPanel;
	JButton   bALL   =   new   JButton( "���й�Ʊ�б� ");
	JButton   bSH   =   new   JButton( "��A ");
    JButton   bSZ   =   new   JButton( "��A ");
    JButton   bHYBK   =   new   JButton( "��ҵ�б� "); 
    BasicArrowButton bHYNav = new  BasicArrowButton(BasicArrowButton.SOUTH);
   
    JButton   bDQBK   =   new   JButton( "�����б�"); 
    BasicArrowButton bDQNav = new  BasicArrowButton(BasicArrowButton.SOUTH);
    
    JTable tTable;
    Object columnNames[] = { "ID", "����","����", "���¼�","��߼�","��ͼ�","���տ��̼�","�������̼�","����(��)","���(��)"};
    JButton bNext = new JButton("��һҳ");
    JButton bPrevious = new JButton("��һҳ");
	public QuoteDataBodyPanel()
	{
		// �����
		pLeftPanel = new JPanel();		
		pLeftPanel.setLayout(new FlowLayout());
		pLeftPanel.add(bALL);
		bALL.addActionListener(this);
		pLeftPanel.add(bSH);
		bSH.addActionListener(this);
		pLeftPanel.add(bSZ);
		bSZ.addActionListener(this);
		pLeftPanel.add(bHYBK);
		bHYBK.addActionListener(this);
		pLeftPanel.add(bHYNav);
		pLeftPanel.add(bDQBK); 
		pLeftPanel.add(bDQNav);
		bDQBK.addActionListener(this);
		pLeftPanel.setSize(1000,50);
		pLeftPanel.setAlignmentY(LEFT_ALIGNMENT);
		this.add(pLeftPanel);
		
			
		GetData("market","sh");	
		DefaultTableModel model = GetModel();
	    tTable = makeTable(model);
	    pContentPane = new JScrollPane(tTable);
	    pContentPane.setPreferredSize(new   Dimension(1000,600));
	    
	    this.add(pContentPane);
	    
	    
	    pDownPanel = new JPanel();
	    pDownPanel.add(bPrevious);
	    bPrevious.addActionListener(this);
	    pDownPanel.add(bNext);
	    bNext.addActionListener(this);
	    pDownPanel.setSize(1000,100);
	    this.add(pDownPanel);
		
	}
	
	// ��ȡ����
	public void GetData(String type, String keyword)
	{
		QuoteData = new StockQuoteData[StockInfo.getStockListNumberByKeyword(type,keyword)]; 
		try
		{
			QuoteData =  QuoteDataSpider.GetQuoteDataByKeyword(type,keyword);
		}
		catch (IOException ex)
		{
			System.out.println("failed");
		}	
		// ��������������һҳ
		total = (int)Math.ceil(QuoteData.length/ROWPERPAGE);
		//System.out.println(total);
		current = 1;
	}
	
	// ����ģ��
	public DefaultTableModel GetModel()
	{
		
		//������
	    int column = columnNames.length;
	    
	    int row	= (int)ROWPERPAGE;
	    if(current == total)
	    {
	    	row = QuoteData.length % row;
	    }
	    //System.out.println(column + " " + row);
	    Object[][] rowData =  new Object[row][column];
	    int begin =0 , end = 0;
	    begin = (int)((current-1)*ROWPERPAGE);
	    if(current == total)
	    {
	    	end = QuoteData.length;
	    }
	    else
	    {
	    	end = (int)(current*ROWPERPAGE);
	    }
	    
	    for(int i = begin ; i < end ; ++i)
	    {
	    	int ID = (int)(i % ROWPERPAGE);
	    	DecimalFormat df = new DecimalFormat("0.00");
	    	rowData[ID][0] = i + 1;
	    	rowData[ID][1] = QuoteData[i].StockCode;
	    	rowData[ID][2] = QuoteData[i].StockName;
	    	rowData[ID][3] = df.format(QuoteData[i].CurrentPrice);
	    	rowData[ID][4] = df.format(QuoteData[i].HighPriceToday);
	    	rowData[ID][5] = df.format(QuoteData[i].LowPriceToday);
	    	rowData[ID][6] = df.format(QuoteData[i].OpenPriceToday);
	    	rowData[ID][7] = df.format(QuoteData[i].ClosePriceYestoday);
	    	rowData[ID][8] = df.format(QuoteData[i].TotalTrade/1000000);	// ÿ100��Ϊ1��
	    	rowData[ID][9] = df.format(QuoteData[i].TradeAccount/100000000);
	    }
	    DefaultTableModel  model = new DefaultTableModel(rowData,columnNames);
	    
	    return model;
	}
	 public void actionPerformed(ActionEvent e) {
		String type = "";
		String keyword = "";
		if(e.getSource() == bALL)
		{
			type = "market";
			keyword = "*";
			GetData(type, keyword);
		}
		else if(e.getSource() == bSH)
		{
			type = "market";
			keyword = "sh";
			GetData(type, keyword);
		}
		else if(e.getSource() == bSZ)
		{
			type = "market";
			keyword = "sz";
			GetData(type, keyword);
		}
		else if(e.getSource() == bHYBK)
		{
			type = "industry";
			Vector<String> IndustryList = StockInfo.getTypeList(type);
			JList list = new JList(IndustryList);
			this.add(list,"West");
			keyword = "*";
			GetData(type, keyword);
		}
		else if(e.getSource() == bDQBK)
		{
			type = "province";
			Vector<String> ProvinceList = StockInfo.getTypeList(type);
			JList list = new JList(ProvinceList);
			list.addListSelectionListener(this);
			this.add(list,"West");
			keyword = "*";
			GetData(type, keyword);
		}
		
		else if(e.getSource() == bNext)
		{
			current += 1;
			if(current > total)
			{
				current = total;
				bNext.setEnabled(false);
				bPrevious.setEnabled(true);
				return;
			}
			else
			{
				bNext.setEnabled(true);

			}
			//System.out.println(current + " " + total);
		}
		else if(e.getSource() == bPrevious)
		{
			current -= 1;
			if(current <= 0)
			{
				current = 1;
				bPrevious.setEnabled(false);
				bNext.setEnabled(true);
				return;
			}
			else
			{
				bPrevious.setEnabled(true);
			}
			//System.out.println(current + " " + total);
		}
		
	    DefaultTableModel updateModel = GetModel();
	    tTable = makeTable(updateModel);
	    pContentPane.getViewport().add(tTable,null);
	    pContentPane.validate();
	 }
	 public void valueChanged(ListSelectionEvent e)
	 {
		 //System.out.println("hello");
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
		 
		 //�����и�
		 table.setRowHeight(30);
		  
		  //���ñ����
		  table.setGridColor(SystemColor.controlHighlight);
		  
		  //��������
		  table.setFont(new Font("Menu.font", Font.ROMAN_BASELINE, 12));
		  // ���ñ�ͷ������ʾ
		  JTableHeader tbh = table.getTableHeader();
		  DefaultTableCellRenderer  renderer = (DefaultTableCellRenderer)tbh.getDefaultRenderer();
		  renderer.setHorizontalAlignment(SwingConstants.CENTER); 
		 try
		 {
			  DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {		    
				  private static final long serialVersionUID = 1826425922704465800L;
					    public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,
					      int row, int column) {
							
							// ����ÿֻ��Ʊ����ɫ
					    	// ʵʱ�۸�
							double CurrentPrice = Double.parseDouble(table.getModel().getValueAt(row, 3).toString());
							// �������̼۸�
							double ClosePriceYestoday = Double.parseDouble(table.getModel().getValueAt(row, 7).toString());
							//System.out.println(CurrentPrice + " |" + ClosePriceYestoday);
							// ��Ʊ���ǵ��Ǹ��������̼۸���Ƚ�
							if((int)CurrentPrice ==  0)
								setForeground(Color.WHITE);//��ɫ
							else if(CurrentPrice < ClosePriceYestoday)
								setForeground(Color.GREEN);//��ɫ
							else if(CurrentPrice >= ClosePriceYestoday)
								setForeground(Color.RED);//��ɫ
					    	
								
							//������ż����ɫ
						     if (row % 2 == 0){
						    	 //setBackground(new Color(185,215,250)); // ���������е�ɫ
						    	 //setBackground(Color.BLACK);
						     }
						     else if (row % 2 == 1){
						    	 //setBackground(new Color(237, 237, 237)); // ����ż���е�ɫ
						    	// setBackground(Color.BLACK); // ����ż���е�ɫ
						     }
						         
						     //���Ϊ��ֵ����ж���
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
		table.getColumnModel().getColumn(8).setPreferredWidth(87);
		table.getColumnModel().getColumn(9).setPreferredWidth(87);


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
		    QuoteDataBodyPanel tmp = new QuoteDataBodyPanel();
		    frame.add(tmp);
		    frame.setSize(1000,720);
		    frame.setVisible(true);

		  }
}
