package BigWise.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


class IndexPanel extends JPanel {
	
	JPanel IndexPanel;
	JLabel IndexSH;
	JLabel IndexSZ;
	
	JLabel AboutLabel;
	JTextField Search;
	
	public IndexPanel() {		
		setLayout(new GridLayout(1,4));
		IndexPanel = new JPanel();
		IndexSH=new JLabel("sh");
		IndexSZ=new JLabel("sz");
		
		//lblState.setAlignment(Label.LEFT);
		IndexPanel.add(IndexSH);
		IndexPanel.add(IndexSZ);	

		AboutLabel = new JLabel("联系方式：18721899843");
		Search = new JTextField(8);
		Search.addKeyListener( new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getKeyChar() == KeyEvent.VK_ENTER)
				{
					System.out.println("hello");
				}
			}
		});
		IndexPanel.add(AboutLabel);
		IndexPanel.add(Search);
		
		add(IndexPanel);
	}
	
	public static void main(String args[])
	{
		JFrame frame = new JFrame("底部大盘指数");
		frame.setLayout(new BorderLayout());
	    frame.add(new IndexPanel(),"South");
	    frame.setSize(1000,700);
	    frame.setVisible(true);
	}
}