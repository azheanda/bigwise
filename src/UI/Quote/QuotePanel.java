package BigWise.UI.Quote;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.*;
import javax.swing.*;

public class QuotePanel extends JPanel{
	
	JTabbedPane jtp = new JTabbedPane();
	
	public QuotePanel()
	{

		jtp.addTab("实时数据", new QuoteDataBodyPanel() );
		jtp.addTab("分时图", new QuoteRTGraphBodyPanel());
		jtp.addTab("实时K线图", new QuoteKGraphBodyPanel());
		add(jtp);
		setLayout(new GridLayout(1,1));
		setSize(1000,600);
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		JFrame frame = new JFrame("实时");
		frame.add(new QuotePanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1050,750);
		frame.setVisible(true);
		
	}
}
