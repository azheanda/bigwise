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
		JPanel a = new JPanel();
		JPanel b = new JPanel();
		JPanel c = new JPanel();
		JPanel d = new JPanel();
		JPanel e = new JPanel();
		JPanel f = new JPanel();
		JPanel g = new JPanel();
		JPanel h = new JPanel();
		
		
		jtp.addTab("资金排名",pDetailPanel);
		jtp.addTab("CR指标",pCapFlowPanel);
		jtp.addTab("条件选股",a);
		jtp.addTab("趋势DK线",b);
		jtp.addTab("趋势信号",c);
		jtp.addTab("T+O资金全景图",d);
		jtp.addTab("T+O成交透析",e);
		jtp.addTab("盘口异动",f);
		jtp.addTab("买卖力道",g);
		jtp.addTab("贡献点数",h);

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
