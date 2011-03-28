package BigWise.UI.Analyzer;
import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BigWise.DataSpider.*;
import BigWise.UI.History.HistoryDataBodyPanel;
import BigWise.UI.History.HistoryKGraphBodyPanel;
import BigWise.UI.History.HistoryPanel;
import BigWise.UI.KLine.KLine;

public class AnalyzerBodyPanel extends JPanel{
   
	JTabbedPane jtp = new JTabbedPane();
	
	JPanel pDetailPanel;
	JPanel  pCapFlowPanel;
	public AnalyzerBodyPanel()
	{
		this.setLayout(new BorderLayout());

		pCapFlowPanel = new JPanel();
		pDetailPanel = new JPanel();

		
		
		jtp.addTab("资金排名",pDetailPanel);
		jtp.addTab("CR指标",pCapFlowPanel);

		add(jtp);
		setLayout(new GridLayout(1,1));
		setSize(1000,600);
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		JFrame frame = new JFrame("资金流");
		AnalyzerBodyPanel tmp = new AnalyzerBodyPanel();
		frame.add(tmp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1050,750);
		frame.setVisible(true);

	}
	
}
