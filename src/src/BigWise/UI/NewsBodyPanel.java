package BigWise.UI;
import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BigWise.DataSpider.*;

public class NewsBodyPanel extends JPanel implements ActionListener{
    JTextArea tResult;
	
    public NewsBodyPanel()
	{
		this.setLayout(new BorderLayout());
	
		tResult = new JTextArea("×ÊÑ¶");
		this.add(tResult,"Center");
	}
	
	 public void actionPerformed(ActionEvent e) {
	 }
}
