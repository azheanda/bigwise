package BigWise.UI.History;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.*;
import javax.swing.*;

public class HistoryPanel extends JPanel{
	
	JTabbedPane jtp = new JTabbedPane();
	
	public HistoryPanel()
	{

		jtp.addTab("��ʷ����", new HistoryDataBodyPanel() );
		jtp.addTab("��ʷ��K��ͼ", new HistoryKGraphBodyPanel("daily"));
		jtp.addTab("��ʷ��K��ͼ", new HistoryKGraphBodyPanel("weekly"));
		jtp.addTab("��ʷ��K��ͼ", new HistoryKGraphBodyPanel("monthly"));
		add(jtp);
		setLayout(new GridLayout(1,1));
		setSize(1000,600);
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		JFrame frame = new JFrame("��ʷ");
		frame.add(new HistoryPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1050,750);
		frame.setVisible(true);
		
	}
}
