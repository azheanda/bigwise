package BigWiseUI;
import java.awt.*;
import javax.swing.*;
import   java.awt.event.MouseAdapter; 
import   java.awt.event.MouseEvent; 
import   java.awt.Graphics2D; 

public   class   Dotty   extends   JFrame   { 
    JPanel   panel   =   new   JPanel(); 
    JPanel   pnlCtl   =   new   JPanel(); 
    JButton   button   =   new   JButton( "Draw   Rectangle ");
    JButton   button1   =   new   JButton( "Draw Line "); 
    JButton   button2   =   new   JButton( "Draw Point "); 
    JButton   button3   =   new   JButton( "Draw Circle "); 
    JButton   button4   =   new   JButton( "Clear   all "); 
    int left = 20;
    public   Dotty()
    { 
        Container   cp   =   this.getContentPane(); 
        cp.add(panel,BorderLayout.CENTER); 
        button.addMouseListener(new   MouseAdapter(){ 
            public   void   mouseClicked(MouseEvent   me) 
            { 
            	if(left > 700)
            		left  = 0;
                Graphics2D   g2   =   (Graphics2D)   panel.getGraphics(); 
                g2.drawRect(left,20,30,300); 
                left += 40;
             } 
        }); 
        button1.addMouseListener(new   MouseAdapter(){ 
            public   void   mouseClicked(MouseEvent   me) 
            { 
            	if(left > 700)
            		left  = 0;
                Graphics2D   g2   =   (Graphics2D)   panel.getGraphics(); 
                g2.drawLine(left,20,left+30,320); 
                left += 40;
             } 
        }); 
        button2.addMouseListener(new   MouseAdapter(){ 
            public   void   mouseClicked(MouseEvent   me) 
            { 
            	if(left > 700)
            		left  = 0;
                Graphics2D   g2   =   (Graphics2D)   panel.getGraphics(); 
                g2.drawOval(left,20,30,300); 
                left += 40;
             } 
        }); 
        button3.addMouseListener(new   MouseAdapter(){ 
            public   void   mouseClicked(MouseEvent   me) 
            { 
            	if(left > 700)
            		left  = 0;
                Graphics2D   g2   =   (Graphics2D)   panel.getGraphics(); 
                g2.drawRect(left,20,30,300); 
                left += 40;
                
             } 
        }); 
        
        button4.addMouseListener(new   MouseAdapter(){ 
            public   void   mouseClicked(MouseEvent   me) 
            { 
                panel.getGraphics().clearRect(0,0,800,400); 
            } 
        }); 
        pnlCtl.add(button); 
        pnlCtl.add(button1); 
        pnlCtl.add(button2); 
        pnlCtl.add(button3); 
        pnlCtl.add(button4); 
        cp.add(pnlCtl,BorderLayout.NORTH); 
        setSize(800,600); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.show(); 
    } 
    public   static   void   main(String[]   args)   { 
    	Dotty   testPanel   =   new   Dotty(); 
    } 
} 
//public class Dotty {
//	private static final int HREF = 20,VREF = 20,LEN = 20;
//	private JPanel canvas;
//	private int nDots;
//	private int nDrawn;
//	private int firstRed = 0;
//	
//	public Dotty(){
//		canvas = null;
//	    nDots = 0;
//	}
//	
//    public Dotty(JPanel canv,int dots){
//		canvas = canv;
//		nDots = dots;
//	}
//	
//	public void draw(){
//		 Graphics2D   g   =   (Graphics2D) canvas.getGraphics();
//		for(nDrawn = 0;nDrawn < nDots;nDrawn++){
//			int x = HREF + (int)(Math.random() * LEN);
//			int y = VREF + (int)(Math.random() * LEN);
//			g.fillOval(x, y, 3, 3);
//			if((Math.random() < 0.001) && (firstRed == 0)){
//				g.setColor(Color.red);
//				firstRed = nDrawn;
//			}
//		}
//	}
//	
//	public void clear(){
//		Graphics2D   g   =   (Graphics2D) canvas.getGraphics();
//		g.setColor(canvas.getBackground());
//		g.fillRect(HREF, VREF, LEN + 3, LEN + 3);
//		System.out.print("Number of dots drawn since first red = " + (nDrawn - firstRed));
//	}
//	
//	public static void main(String[] args){
//		Dotty t = new Dotty();
//		t.clear();
//		t.draw();
//		
//	}
//
//}
