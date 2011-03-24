package BigWise.UI.History;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import BigWise.Controller.HistoryController;
import BigWise.UI.KLine.KLine;
import BigWise.UI.RTLine.RTLine;

public class HistoryPanel extends JPanel implements Observer{
	
	HistoryController hc;
	
	JTabbedPane jtp = new JTabbedPane();
	HistoryDataBodyPanel pHistoryDataBodyPanel;
	HistoryKGraphBodyPanel  pHistoryDailyKLinePanel;
	HistoryKGraphBodyPanel  pHistoryWeeklyKLinePanel;
	HistoryKGraphBodyPanel  pHistoryMonthlyKLinePanel;
	KLine pKLine;
	public HistoryPanel()
	{
		hc = HistoryController.getHistoryControllerInstance();
		
		hc.c.CodeIndexNext();
		System.out.println(hc.c.CodeIndex +"|" + hc.c.CodeLength + hc.c.StockCode);
		
		pHistoryDailyKLinePanel = new HistoryKGraphBodyPanel("daily");
		pHistoryWeeklyKLinePanel = new HistoryKGraphBodyPanel("weekly");
		pHistoryMonthlyKLinePanel = new HistoryKGraphBodyPanel("monthly");
		pHistoryDataBodyPanel = new HistoryDataBodyPanel();
		JPanel pKLinePanel = new JPanel();
		pKLine = KLine.getKLine();
		pKLinePanel.add(pKLine);

		
		
		jtp.addTab("��ʷ��K��ͼ",pHistoryDailyKLinePanel );
		jtp.addTab("��ʷ��K��ͼ",pHistoryWeeklyKLinePanel);
		jtp.addTab("��ʷ��K��ͼ",pHistoryMonthlyKLinePanel);
		jtp.addTab("��ʷ����", pHistoryDataBodyPanel);
		jtp.addTab("��ʷ��K", pKLinePanel);

		add(jtp);
		hc.c.addObserver(this);
		
		setLayout(new GridLayout(1,1));
		setSize(1000,600);
		setVisible(true);
	}
	

	public void update(Observable obj,Object arg)
	{
		System.out.println("update");
		pHistoryDataBodyPanel.refresh();
		pHistoryDailyKLinePanel.refresh();
		pHistoryWeeklyKLinePanel.refresh();
		pHistoryMonthlyKLinePanel.refresh();
	}
	
	public HistoryPanel HistoryPanel()
	{
		return this;
	}
	

	public static void main(String args[])
	{
		JFrame frame = new JFrame("��ʷ");
		HistoryPanel tmp = new HistoryPanel();
		frame.add(tmp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1050,750);
		frame.setVisible(true);

	}
}
