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
		
		
		jtp.addTab("�ʽ�����",pDetailPanel);
		jtp.addTab("CRָ��",pCapFlowPanel);
		jtp.addTab("����ѡ��",a);
		jtp.addTab("����DK��",b);
		jtp.addTab("�����ź�",c);
		jtp.addTab("T+O�ʽ�ȫ��ͼ",d);
		jtp.addTab("T+O�ɽ�͸��",e);
		jtp.addTab("�̿��춯",f);
		jtp.addTab("��������",g);
		jtp.addTab("���׵���",h);

		add(jtp);
		setLayout(new GridLayout(1,1));
		setSize(1000,600);
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		JFrame frame = new JFrame("�ʽ���");
		AnalyzerBodyPanel tmp = new AnalyzerBodyPanel();
		frame.add(tmp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1050,750);
		frame.setVisible(true);

	}
	
}
