package BigWise.UI;
import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public   class   BigWise   {
	JFrame   mainFrame;
	Container thisContainer; 
    HeaderPanel pHeaderPanel;
    JPanel cards;
    
    QuoteBodyPanel pQuoteBodyPanel;
    HisDataBodyPanel pHisDataBodyPanel;
    NewsBodyPanel pNewsBodyPanel;
    AnalyzerBodyPanel pAnalyzerBodyPanel;
    ForumBodyPanel pForumBodyPanel;
    
    public void init()
    {
    	mainFrame = new JFrame("BigWise");
		thisContainer = mainFrame.getContentPane();
		thisContainer.setLayout(new BorderLayout());
		pHeaderPanel = new HeaderPanel();
		thisContainer.add(pHeaderPanel, "North");
		
		cards = new JPanel(new CardLayout());
		thisContainer.add(cards,"Center");
		pQuoteBodyPanel = new QuoteBodyPanel();
		pHisDataBodyPanel = new HisDataBodyPanel();
		pNewsBodyPanel = new NewsBodyPanel();
		pAnalyzerBodyPanel = new AnalyzerBodyPanel();
		pForumBodyPanel = new ForumBodyPanel();
		cards.add(pQuoteBodyPanel,"Quote");
		cards.add(pHisDataBodyPanel,"HisData");
		cards.add(pNewsBodyPanel,"News");
		cards.add(pAnalyzerBodyPanel,"Analyzer");
		cards.add(pForumBodyPanel,"Forum");
		
		class ControlActionListenter implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            CardLayout cl = (CardLayout) (cards.getLayout());
	            String cmd = e.getActionCommand();
	            cl.show(cards,cmd);
	        }
	    }
		
		ControlActionListenter cal = new ControlActionListenter();
		pHeaderPanel.bQuote.setActionCommand("Quote");
		pHeaderPanel.bQuote.addActionListener(cal);
		pHeaderPanel.bHisData.setActionCommand("HisData");
		pHeaderPanel.bHisData.addActionListener(cal);
		pHeaderPanel.bNews.setActionCommand("News");
		pHeaderPanel.bNews.addActionListener(cal);
		pHeaderPanel.bAnalyzer.setActionCommand("Analyzer");
		pHeaderPanel.bAnalyzer.addActionListener(cal);
		pHeaderPanel.bForum.setActionCommand("Forum");
		pHeaderPanel.bForum.addActionListener(cal);
		mainFrame.setBounds(300, 100, 800, 400);
		mainFrame.setVisible(true);
		
    }
    
    
    public   static   void   main(String[]   args)   { 
    	BigWise   testPanel   =   new   BigWise(); 
    	testPanel.init();
    } 
} 
