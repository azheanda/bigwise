package BigWise.UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClosableFrame extends JFrame{
	public ClosableFrame(String title)
	{
		super(title);
		addWindowListener(
				new WindowAdapter() {
					public void WindowClosing(WindowEvent e)
					{
						setVisible(false);
						dispose();
					}
				});
	}
	
	public static void main(String args[])
	{
		ClosableFrame cf = new ClosableFrame("Close");
		cf.setSize(100,100);
		cf.setVisible(true);
	}
}
