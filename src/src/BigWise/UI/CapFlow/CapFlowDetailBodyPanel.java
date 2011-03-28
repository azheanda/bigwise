package BigWise.UI.CapFlow;

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

import BigWise.Controller.CapFlowController;
import BigWise.Controller.QuoteController;
import BigWise.DataSpider.*;
import BigWise.DataSpider.Quote.QuoteDataSpider;
import BigWise.Model.*;

import java.text.DecimalFormat;
import java.lang.Math;


public class CapFlowDetailBodyPanel extends JPanel implements ActionListener,ListSelectionListener{
   
	public CapFlowController cfc;

	 JPanel pTopPanel;
	 JLabel topLabel;
	 
	JScrollPane pContentPane;
	JTable tTable;
  
   
    
	JPanel pDownPanel;
    JButton bNext = new JButton("��һҳ");
    JButton bPrevious = new JButton("��һҳ");
    
    
	public CapFlowDetailBodyPanel()
	{
		cfc = CapFlowController.getCapFlowController();
		
		pTopPanel = new JPanel();
		topLabel = new JLabel();
		pTopPanel.add(topLabel);
		this.add(pTopPanel,"South");
		
		init();
	    
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
	
	public void init()
	{
		topLabel.setText(cfc.c.StockCode);
		DefaultTableModel model = cfc.GetModel();
	    tTable = makeTable(model);
	}
	
	
	public void refresh()
	{
		init();
		pContentPane.getViewport().add(tTable,null);
		pContentPane.validate();
	}
	
	 public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bNext)
		{
			cfc.pageNext();
		}
		else if(e.getSource() == bPrevious)
		{
			cfc.pagePrevious();
		}
		refresh();
	   
	 }
	 public void valueChanged(ListSelectionEvent e)
	 {
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
							
								
							//������ż����ɫ
						     if (row % 2 == 0){
						    	 setBackground(new Color(185,215,250)); // ���������е�ɫ
						    	 //setBackground(Color.BLACK);
						     }
						     else if (row % 2 == 1){
						    	setBackground(new Color(237, 237, 237)); // ����ż���е�ɫ
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
		    CapFlowDetailBodyPanel tmp = new CapFlowDetailBodyPanel();
		    frame.add(tmp);
		    frame.setSize(1000,720);
		    frame.setVisible(true);

		  }
}
