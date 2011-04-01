package BigWise.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


class StatePanel extends JPanel {
	JLabel IndexSH;
	JLabel IndexSZ;
	

	public StatePanel() {
		setLayout(new GridLayout(1,2));
		IndexSH=new JLabel("sh");
		
		IndexSZ=new JLabel("sz");
		
		//lblState.setAlignment(Label.LEFT);
		add(IndexSH);
		add(IndexSZ);	
	}
	
	public static void main(String args[])
	{
		JFrame frame = new JFrame("底部大盘指数");
		frame.setLayout(new BorderLayout());
	    frame.add(new StatePanel(),"South");
	    frame.setSize(1000,700);
	    frame.setVisible(true);
	}
}