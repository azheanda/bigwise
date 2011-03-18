package BigWise.UI.History;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.*;
import javax.swing.*;

public class HistoryPanel extends JPanel{
	
	JTabbedPane jtp = new JTabbedPane();
	
	public HistoryPanel()
	{

		jtp.addTab("历史数据", new HistoryDataBodyPanel() );
		jtp.addTab("历史日K线图", new HistoryKGraphBodyPanel("daily"));
		jtp.addTab("历史周K线图", new HistoryKGraphBodyPanel("weekly"));
		jtp.addTab("历史月K线图", new HistoryKGraphBodyPanel("monthly"));
		add(jtp);
		setLayout(new GridLayout(1,1));
		setSize(1000,600);
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		JFrame frame = new JFrame("历史");
		frame.add(new HistoryPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1050,750);
		frame.setVisible(true);
		
	}
}
