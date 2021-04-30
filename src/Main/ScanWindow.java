package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Paint;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ScanWindow extends JFrame 
{
	public static int windowWidth = 340;
	public static int windowHeight = 600;
	
	public static boolean mouseDown = false;
	
	public JPanel panel; 
	
	ScanWindow()
	{
		
		super("GradientTranslucentWindow");
	
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBackground(new Color(0,0,0,0.1f));
		setSize(windowWidth, windowHeight);
		setVisible(true);
		setAlwaysOnTop(true);
		
		
		panel = new JPanel() 
		{
	        @Override
	        protected void paintComponent(Graphics g) 
	        {
	            setLayout(new FlowLayout());

	            
	        	Graphics2D g2 = (Graphics2D) g;
	        	
	        	
	        	g2.setStroke(new BasicStroke(5));
	        	g2.setColor(Color.GREEN);
	        	g2.drawRect(0, 0, windowWidth - 5, windowHeight - 5);
	        	
	        	//g2.setColor(new Color(50,0,0));
	        	//g2.fillRect(0, 0, windowWidth - 6, windowHeight - 6);
	        }
	        
	        
		};
	
		JButton scan = new JButton("Start Scan");
		scan.addActionListener(e -> 
		{
			remove(scan);
			revalidate();
			repaint();
			//scan.hide();
//			panel.revalidate();
//			panel.repaint();
			Globals.startScanning();
		});
		panel.add(scan);
		panel.setBounds(0, 0, windowWidth, windowHeight);
		setContentPane(panel);
		panel.repaint();
		
		


	}
	
	
	

}
