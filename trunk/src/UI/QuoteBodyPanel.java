package BigWise.UI;
import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BigWise.DataSpider.*;

public class QuoteBodyPanel extends JPanel implements ActionListener{
   
	JPanel pLeftPanel;
	JScrollPane pContentPane;
	JButton   bALL   =   new   JButton( "���й�Ʊ�б� ");
	JButton   bSH   =   new   JButton( "��A ");
    JButton   bSZ   =   new   JButton( "��A ");
    JButton   bZXB  =   new   JButton( "��С�� ");
    JButton   bCYB   =   new   JButton( "��ҵ��");
    JButton   bHYBK   =   new   JButton( "��ҵ�б� "); 
    JButton   bGNBK   =   new   JButton( "������ "); 
    JButton   bDQBK   =   new   JButton( "�����б�"); 
    JTable tTable;
	public QuoteBodyPanel()
	{
		this.setLayout(new BorderLayout());

		// �����
		pLeftPanel = new JPanel();
		pLeftPanel.setLayout(new GridLayout(8,1));
		pLeftPanel.add(bALL);
		bALL.addActionListener(this);
		pLeftPanel.add(bSH);
		bSH.addActionListener(this);
		pLeftPanel.add(bSZ);
		bSZ.addActionListener(this);
		pLeftPanel.add(bZXB);
		bZXB.addActionListener(this);
		pLeftPanel.add(bCYB);
		bCYB.addActionListener(this);
		pLeftPanel.add(bHYBK);
		bHYBK.addActionListener(this);
		pLeftPanel.add(bGNBK);
		bGNBK.addActionListener(this);
		pLeftPanel.add(bDQBK); 
		bDQBK.addActionListener(this);
		pLeftPanel.setSize(100,30);
		this.add(pLeftPanel,"West");
		
		
		//������
		Object rowData[][] = 
	    {
	    		{"1","600000","�ַ�����","0","0","0","0","0","0","0"},
	    		{"1","600000","�ַ�����","0","0","0","0","0","0","0"},
	    		{"1","600000","�ַ�����","0","0","0","0","0","0","0"},
	    		{"1","600000","�ַ�����","0","0","0","0","0","0","0"},
	    		{"1","600000","�ַ�����","0","0","0","0","0","0","0"},
	    		{"1","600000","�ַ�����","0","0","0","0","0","0","0"},
	    		{"1","600000","�ַ�����","0","0","0","0","0","0","0"},
	    		{"1","600000","�ַ�����","0","0","0","0","0","0","0"},
	    		{"1","600000","�ַ�����","0","0","0","0","0","0","0"},
	    		{"1","600000","�ַ�����","0","0","0","0","0","0","0"}
	      };
	    Object columnNames[] = { "ID", "���д���", "����","���¼�","��߼�","��ͼ�","�ǵ�","�ǵ���","����","���" };
	    tTable = new JTable(rowData, columnNames);
	    pContentPane = new JScrollPane(tTable);
		this.add(pContentPane,"Center");
		
	}
	
	 public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bALL)
		{
		
		}
		else if(e.getSource() == bSH)
		{
	
		}
		else if(e.getSource() == bSZ)
		{
	
		}
		else if(e.getSource() == bCYB)
		{
			
		}
		else if(e.getSource() == bZXB)
		{
			
		}
		else if(e.getSource() == bHYBK)
		{
			
		}
		else if(e.getSource() == bGNBK)
		{
		
		}
		else if(e.getSource() == bDQBK)
		{
		
		}
	 }
	 
	 public static void main(String args[]) {
		    JFrame frame = new JFrame();
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    QuoteBodyPanel tmp = new QuoteBodyPanel();
		    frame.add(tmp, BorderLayout.CENTER);
		    frame.setSize(800, 250);
		    frame.setVisible(true);

		  }
}
