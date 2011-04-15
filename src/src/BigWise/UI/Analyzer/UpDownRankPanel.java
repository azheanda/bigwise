package BigWise.UI.Analyzer;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import BigWise.Controller.CapFlowController;
import BigWise.Controller.HistoryController;
import BigWise.DataSpider.*;
import BigWise.Model.StockCapFlowDetail;
import BigWise.UI.CapFlow.CapFlowDetailBodyPanel;
import BigWise.UI.History.HistoryDataBodyPanel;
import BigWise.UI.History.HistoryKGraphBodyPanel;
import BigWise.UI.History.HistoryPanel;
import BigWise.UI.KLine.KLine;

// �ʽ����а� ���ý��յ����̼۸������յĿ��̼۸�
// �������� �ʽ��������ݵ����һ�ʵ��ǵ����Ϳ�����
public class UpDownRankPanel extends JPanel {

	CapFlowController cfc;

	JPanel pUpDownRankPanel;

	public UpDownRankPanel() {

		cfc = CapFlowController.getCapFlowController();
		cfc.init();

		Vector<StockCapFlowDetail> CapFlowList = new Vector<StockCapFlowDetail>(); // �ʽ�������

		this.setLayout(new BorderLayout());

		pUpDownRankPanel = new JPanel();

		add(pUpDownRankPanel);

		setLayout(new GridLayout(1, 1));
		setSize(1000, 600);
		setVisible(true);
	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("�ʽ���");
		UpDownRankPanel tmp = new UpDownRankPanel();
		frame.add(tmp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1050, 750);
		frame.setVisible(true);

	}

}
