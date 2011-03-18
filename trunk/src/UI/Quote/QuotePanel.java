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

		jtp.addTab("ʵʱ����", new QuoteDataBodyPanel() );
		jtp.addTab("��ʱͼ", new QuoteRTGraphBodyPanel());
		jtp.addTab("ʵʱK��ͼ", new QuoteKGraphBodyPanel());
		add(jtp);
		setLayout(new GridLayout(1,1));
		setSize(1000,600);
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		JFrame frame = new JFrame("ʵʱ");
		frame.add(new QuotePanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1050,750);
		frame.setVisible(true);
		
	}
}
