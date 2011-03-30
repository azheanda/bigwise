package BigWise.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.sun.corba.se.spi.activation.InitialNameServicePackage.NameAlreadyBoundHelper;

import BigWise.Controller.Controller;
import BigWise.Model.StockInfo;
import BigWise.Utility.Utility;

public class NavPanel extends JPanel {

	Controller controller;
	JPanel panel;
	JScrollPane jspPanel;
	JTree tree;
	
	DefaultMutableTreeNode root1;
	
	
	// 构造函数
	public NavPanel() 
	{
		controller = Controller.getControllerInstance();
		
		setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		root1 = new DefaultMutableTreeNode("股票列表");
		
		makeTreePanel("market");
		makeTreePanel("industry");
		makeTreePanel("province");
		
		tree = new JTree(root1);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				// TODO Auto-generated method stub
				 DefaultMutableTreeNode note= (DefaultMutableTreeNode) tree.getLastSelectedPathComponent(); //返回最后选中的结点          
	             String name=note.toString();//获得这个结点的名称
	             int length = name.length();
	             int begin = name.indexOf("(");
	             if(begin < 0)
	            	 return;
	             String code = name.substring(begin+1,length-1);
	             //System.out.println(code);
	             controller.StockCode = code;
	             controller.StockMarket = Utility.getMarket(code);
	             controller.CodeUpdate();
			}
		});
		
		
		jspPanel = new JScrollPane(tree);
		jspPanel.setPreferredSize(new Dimension(190,100));;
		
		panel.add(jspPanel);
		
		
		this.add(panel,"West");
		
	}
	
	
	public void makeTreePanel(String type)
	{
		DefaultMutableTreeNode root;
		DefaultMutableTreeNode child;
		
		Vector<String> marketList = StockInfo.getTypeList(type);
		//System.out.println(marketList.size());
		
		root = new DefaultMutableTreeNode(type);
		
		for(int i = 0 ; i< marketList.size(); ++i)
		{
			String childNode = marketList.elementAt(i);
			child = new DefaultMutableTreeNode(childNode);
			
			Vector<StockInfo> stock = StockInfo.getStockListByKeyword(type, childNode);
			//System.out.println(stock.size());
			for(int j = 0; j < stock.size(); ++j)
			{
				StockInfo leafNode = stock.elementAt(j);
				String name = leafNode.name;
				String code = leafNode.code;
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(name + "(" + code + ")");
				child.add(node);
			}
			root.add(child);
		}
		
		root1.add(root);
		
	}
	
	
	
	public static NavPanel getNavPanel()
	{
		return new NavPanel();
	}
	public static void main(String args[])
	{
		JFrame frame = new JFrame("导航树");
	    frame.add(NavPanel.getNavPanel());
	    frame.setSize(1500,700);
	    frame.setVisible(true);
	}
}