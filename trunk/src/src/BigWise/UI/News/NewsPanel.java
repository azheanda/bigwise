package BigWise.UI.News;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.*;
import javax.swing.*;

public class NewsPanel extends JPanel{
	
	JTabbedPane jtp = new JTabbedPane();
	
	public NewsPanel()
	{

		jtp.addTab("��������", new NewsSinaBodyPanel());
		jtp.addTab("������ʾ", new JYTSSinaBodyPanel());
		add(jtp);
		setLayout(new GridLayout(1,1));
		setSize(1000,600);
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		JFrame frame = new JFrame("����");
		frame.add(new NewsPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1050,750);
		frame.setVisible(true);
		
	}
}
