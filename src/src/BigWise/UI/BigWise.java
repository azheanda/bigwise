package BigWise.UI;
import java.awt.*;

import javax.swing.*;

import BigWise.UI.Analyzer.AnalyzerBodyPanel;
import BigWise.UI.CapFlow.CapFlowBodyPanel;
import BigWise.UI.History.HistoryPanel;
import BigWise.UI.News.NewsPanel;
import BigWise.UI.Quote.QuotePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public   class   BigWise   {
	JFrame   mainFrame;
	Container thisContainer; 
    
	HeaderPanel pHeaderPanel;
    
    JPanel cards;
    QuotePanel pQuoteBodyPanel;
    HistoryPanel pHisDataBodyPanel;
    NewsPanel pNewsBodyPanel;
    AnalyzerBodyPanel pAnalyzerBodyPanel;
    CapFlowBodyPanel pCapFlowBodyPanel;
    
    
    NavPanel navPanel;
    
    StatePanel indexPanel;
    public void init()
    {
    	mainFrame = new JFrame("BigWise");
		thisContainer = mainFrame.getContentPane();
		thisContainer.setLayout(new BorderLayout());
		// �˵���
		pHeaderPanel = new HeaderPanel();
		thisContainer.add(pHeaderPanel, "North");
		
		// ������
		navPanel = NavPanel.getNavPanel();
		thisContainer.add(navPanel,"West");
		
		// �ײ������ݹ�ָ����
		indexPanel = new StatePanel();
		thisContainer.add(indexPanel,"South");
		

		cards = new JPanel(new CardLayout());
		thisContainer.add(cards,"Center");
		pQuoteBodyPanel = new QuotePanel();
		pHisDataBodyPanel = new HistoryPanel();
		pNewsBodyPanel = new NewsPanel();
		pAnalyzerBodyPanel = new AnalyzerBodyPanel();
		pCapFlowBodyPanel = new CapFlowBodyPanel();
		cards.add(pQuoteBodyPanel,"Quote");
		cards.add(pHisDataBodyPanel,"HisData");
		cards.add(pNewsBodyPanel,"News");
		cards.add(pCapFlowBodyPanel,"CapFlow");
		cards.add(pAnalyzerBodyPanel,"Analyzer");
		
		
		
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
		pHeaderPanel.bCapFlow.setActionCommand("CapFlow");
		pHeaderPanel.bCapFlow.addActionListener(cal);
		mainFrame.setBounds(50, 50, 1300, 800);
		mainFrame.setVisible(true);
    }
    
    
    public   static   void   main(String[]   args)   { 
    	
    	try {
    		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		}
		catch ( Exception e ) {
			System.out.println ("�޷�����������Ϊwindows���");
		}
    	BigWise   testPanel   =   new   BigWise(); 
    	testPanel.init();
    } 
} 
