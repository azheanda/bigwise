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
	public QuotePanel()
	{
		qc = QuoteController.getQuoteControllerInstance();
		pQuoteDataBodyPanel = new QuoteDataBodyPanel();
		pQuoteRTGraphBodyPanel = new QuoteRTGraphBodyPanel();
		pQuoteKGraphPanel	= new QuoteKGraphBodyPanel();
		JPanel pRTLinePanel = new JPanel();
		pRTPanel = new RTLine();
		pRTLinePanel.add(pRTPanel);
		
		jtp.addTab("分时图",	pRTLinePanel);
		jtp.addTab("分时图",	pQuoteRTGraphBodyPanel);
		jtp.addTab("实时K线图", pQuoteKGraphPanel);
		jtp.addTab("实时数据", pQuoteDataBodyPanel );
		add(jtp);
		
		qc.controller.addObserver(this);
		
		setLayout(new GridLayout(1,1));
		setSize(1000,600);
		setVisible(true);
	}
	
	
	public void update(Observable obj,Object arg)
	{
		//System.out.println("update");
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
