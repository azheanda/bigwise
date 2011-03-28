package BigWise.UI;

import java.awt.BorderLayout;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import BigWise.Controller.Controller;

public class NavPanel extends JPanel {

	Controller controller;
	JTree marketTree;
	JTree bkTree;
	JTree dqTree;
	DefaultMutableTreeNode root;
	DefaultMutableTreeNode child_1, child_2;
	DefaultMutableTreeNode a, b, c, d, e, f;

	public NavPanel() {
		controller = Controller.getControllerInstance();
		System.out.println(controller.CodeList.size());
		
		

		root = new DefaultMutableTreeNode("ROOT");
		child_1 = new DefaultMutableTreeNode("child_1");
		child_2 = new DefaultMutableTreeNode("child_2");
		a = new DefaultMutableTreeNode("A");
		b = new DefaultMutableTreeNode("B");
		c = new DefaultMutableTreeNode("C");
		d = new DefaultMutableTreeNode("D");
		e = new DefaultMutableTreeNode("E");
		f = new DefaultMutableTreeNode("F");

		child_1.add(a);
		a.add(b);
		a.add(c);
		child_2.add(d);
		d.add(e);
		d.add(f);
		root.add(child_1);
		root.add(child_2);
		root.add(child_1);
		root.add(child_2);
		JTree tree = new JTree(root);
		JTree tree1 = new JTree(root);

		// 树节点可编辑
		// tree.setEditable(true);

		tree.setRowHeight(120);
		add(tree,"West");
		add(tree1,"East");
	}
	
	public static NavPanel getNavPanel()
	{
		return new NavPanel();
	}
	public static void main(String args[])
	{
		JFrame frame = new JFrame("导航树");
	    frame.add(NavPanel.getNavPanel());
	    frame.setSize(1000,700);
	    frame.setVisible(true);
	}
	
}