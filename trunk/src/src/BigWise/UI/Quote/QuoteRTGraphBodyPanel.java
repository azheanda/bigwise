package BigWise.UI.Quote;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BigWise.Controller.QuoteController;




public class QuoteRTGraphBodyPanel extends JPanel implements ActionListener {
	QuoteController qc;

	JPanel pContentPane;
	JPanel pDownPanel;

	JLabel showLabel;
	JButton bNext = new JButton("下一张");
	JButton bPrevious = new JButton("上一张");

	public QuoteRTGraphBodyPanel() {
		qc = QuoteController.getQuoteControllerInstance();

		setLayout(new BorderLayout());

		pContentPane = new JPanel(new BorderLayout());
		showLabel = new JLabel();
		add(showLabel);
		setVisible(true);
		String text = "<html><img src='http://image.sinajs.cn/newchart/min/n/"
				+ qc.controller.StockMarket + qc.controller.StockCode + ".gif'/><html>";
		// System.out.println(text);
		showLabel.setText(text);
		pContentPane.add(showLabel, "Center");
		pContentPane.setPreferredSize(new Dimension(5, 600));

		pDownPanel = new JPanel();
		pDownPanel.add(bPrevious);
		bPrevious.addActionListener(this);
		pDownPanel.add(bNext);
		bNext.addActionListener(this);
		pDownPanel.setSize(1000, 100);

		pContentPane.add(pDownPanel, "South");

		this.add(pContentPane, "Center");
	}

	public void refresh() {
		String text = "<html><img src='http://image.sinajs.cn/newchart/min/n/"
				+ qc.controller.StockMarket + qc.controller.StockCode + ".gif'/><html>";
		showLabel.setText(text);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == bNext) {
			qc.controller.CodeIndexNext();
		} else if (e.getSource() == bPrevious) {
			qc.controller.CodeIndexprevious();
		}
		refresh();
	}

	public String getMarket(String code) {
		String prefix = code.substring(0, 1);
		// System.out.println(prefix);
		if (prefix.equals("0"))
			return "sz";
		else if (prefix.equals("6"))
			return "sh";

		return "sh";
	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("QuoteRTBodyPanel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		QuoteRTGraphBodyPanel tmp = new QuoteRTGraphBodyPanel();
		frame.add(tmp);
		frame.setSize(1000, 720);
		frame.setVisible(true);

	}
}
