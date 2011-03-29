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

import BigWise.Controller.Controller;
import BigWise.Controller.QuoteController;
import BigWise.DataSpider.*;
import BigWise.DataSpider.Quote.QuoteDataSpider;
import BigWise.Model.*;

import java.text.DecimalFormat;
import java.lang.Math;


public class QuoteDataBodyPanel extends JPanel implements ActionListener{
   
	public QuoteController qc;
	

	JScrollPane pContentPane;
	JTable tTable;
    Object columnNames[] = { "ID", "����","����", "���¼�","��߼�","��ͼ�","���տ��̼�","�������̼�","����(��)","���(��)"};
    
    
	JPanel pDownPanel;
    JButton bNext = new JButton("��һҳ");
    JButton bPrevious = new JButton("��һҳ");

    
	public QuoteDataBodyPanel()
	{
		qc = QuoteController.getQuoteControllerInstance();
			
		init();
	    
	    pContentPane = new JScrollPane(tTable);
	    pContentPane.setPreferredSize(new   Dimension(1000,30*(int)(qc.ROWPERPAGE + 1))); 
	    this.add(pContentPane);
	    
	    pDownPanel = new JPanel();
	    pDownPanel.add(bPrevious);
	    bPrevious.addActionListener(this);
	    pDownPanel.add(bNext);
	    bNext.addActionListener(this);
	    pDownPanel.setPreferredSize(new Dimension(1000,50));
	    this.add(pDownPanel);
		
	}
	
	public void init()
	{
		
		DefaultTableModel model = qc.GetModel();
	    tTable = makeTable(model);
	}
	
	public void refresh()
	{
		qc.GetData(qc.controller.StockCode);
		init();
		pContentPane.getViewport().add(tTable,null);
		pContentPane.validate();
	}
	
	 public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == bNext)
		{
			qc.pageNext();
		}
		else if(e.getSource() == bPrevious)
		{
			qc.pagePrevious();
		}
		init();
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
