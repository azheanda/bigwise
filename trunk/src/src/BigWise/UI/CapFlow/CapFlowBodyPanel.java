package BigWise.UI.CapFlow;
import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import BigWise.Controller.CapFlowController;
import BigWise.DataSpider.*;
import BigWise.UI.History.HistoryDataBodyPanel;
import BigWise.UI.History.HistoryKGraphBodyPanel;
import BigWise.UI.History.HistoryPanel;
import BigWise.UI.KLine.KLine;

public class CapFlowBodyPanel extends JPanel implements Observer{
   
	CapFlowController cfc;
	
	JTabbedPane jtp = new JTabbedPane();
	
	CapFlowDetailBodyPanel pCapFlowDetailPanel;
	JPanel  pCapFlowPanel;
	public CapFlowBodyPanel()
	{
		
		cfc = CapFlowController.getCapFlowController();
		
		this.setLayout(new BorderLayout());

		pCapFlowPanel = new JPanel();
		pCapFlowDetailPanel = new CapFlowDetailBodyPanel();

		
		
		jtp.addTab("�ɽ���ϸ",pCapFlowDetailPanel);
		jtp.addTab("�ʽ�������",pCapFlowPanel);

		add(jtp);
		
		
		cfc.c.addObserver(this);
		
		
		setLayout(new GridLayout(1,1));
		setSize(1000,600);
		setVisible(true);
	}
	public void update(Observable obj,Object arg)
	{
		//System.out.println("update");
		pCapFlowDetailPanel.refresh();	
	}
	public static void main(String args[])
	{
		JFrame frame = new JFrame("�ʽ���");
		CapFlowBodyPanel tmp = new CapFlowBodyPanel();
		frame.add(tmp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1050,750);
		frame.setVisible(true);

	}
	
}
