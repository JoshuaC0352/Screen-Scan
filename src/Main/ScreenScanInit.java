package Main;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JFrame;

import NetInterface.YahooMail;

public class ScreenScanInit {

	public static JFrame scanWindow;
	
	public static boolean dontExit = true;
	
	private static BufferedImage previousImage;
	
	
	public static void main(String[] args) 
	{
				
		scanWindow = new ScanWindow();
		
		// Mouse adapter for draggin a window
		scanWindow.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {

               moveWindow();
            }
        });

	}
	
	
	
	public static void moveWindow()
	{
		Point mousePoint = MouseInfo.getPointerInfo().getLocation();
		
		ScreenScanInit.scanWindow.setLocation(new Point(mousePoint.x - 50, mousePoint.y - 50));
	}
																	
}
