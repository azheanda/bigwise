package BigWise.UI.Quote;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import BigWise.Controller.QuoteController;
import BigWise.UI.RTLine.RTLine;

public class QuotePanel extends JPanel implements Observer{
	
	QuoteController qc;
	JTabbedPane jtp = new JTabbedPane();
	QuoteDataBodyPanel pQuoteDataBodyPanel;
	QuoteRTGraphBodyPanel	pQuoteRTGraphBodyPanel;
	QuoteKGraphBodyPanel pQuoteKGraphPanel;
	RTLine pRTPanel;
	BuySellPie pBuySellPie;
	QuoteRankBodyPanel pRankPanel;							// 沪深排行榜
	QuoteProfitRankBodyPanel pSYLPanel;						// 收益率
	JPanel pSJLPanel;			// 市净率
	public QuotePanel()
	{
		qc = QuoteController.getQuoteControllerInstance();
		pQuoteDataBodyPanel = new QuoteDataBodyPanel();
		pQuoteRTGraphBodyPanel = new QuoteRTGraphBodyPanel();
		pQuoteKGraphPanel	= new QuoteKGraphBodyPanel();
		JPanel pRTLinePanel = new JPanel();
		pRTPanel = RTLine.getRTLine();
		pRTLinePanel.add(pRTPanel);
		
		JPanel pRTBuySellPanel = new JPanel();
		pBuySellPie = BuySellPie.getBuySellPie();
		pRTBuySellPanel.add(pBuySellPie);
		
		pRankPanel = new QuoteRankBodyPanel();
		pSYLPanel = new QuoteProfitRankBodyPanel();
		pSJLPanel = new JPanel();
		jtp.addTab("分时图",	pRTLinePanel);
		jtp.addTab("实时K线图", pQuoteKGraphPanel);
		jtp.addTab("买卖图",	pRTBuySellPanel);
		jtp.addTab("沪深数据概览", pQuoteDataBodyPanel );
		jtp.addTab("实时沪深排行榜",pRankPanel);
		jtp.addTab("实时收益率排行", pSYLPanel );

		add(jtp);
		
		qc.controller.addObserver(this);
		
		setLayout(new GridLayout(1,1));
		setSize(1000,600);
		setVisible(true);
	}
	
	
	public void update(Observable obj,Object arg)
	{
		//System.out.println("update");
		pRTPanel.refresh();
		pBuySellPie.refresh();
		pQuoteDataBodyPanel.refresh();
		pQuoteRTGraphBodyPanel.refresh();
		pQuoteKGraphPanel.refresh();
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
