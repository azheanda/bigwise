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
		
		jtp.addTab("��ʱͼ",	pRTLinePanel);
		jtp.addTab("��ʱͼ",	pQuoteRTGraphBodyPanel);
		jtp.addTab("ʵʱK��ͼ", pQuoteKGraphPanel);
		jtp.addTab("ʵʱ����", pQuoteDataBodyPanel );
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
		JFrame frame = new JFrame("ʵʱ");
		frame.add(new QuotePanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1050,750);
		frame.setVisible(true);
		
	}
}
