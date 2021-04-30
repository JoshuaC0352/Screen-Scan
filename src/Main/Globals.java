package Main;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import NetInterface.YahooMail;

public class Globals 
{
	
	/**
	 * captures the screen image occupied by the window and returns it as a buffered image
	 * @return BufferedImage
	 */
	public static BufferedImage scanCapture()
	{
		Robot robot;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Failed to create robot!");
			return null;
		}
		
		Rectangle captureRegion = new Rectangle();
		Point windowLoc = ScreenScanInit.scanWindow.getLocation();
		
		
		captureRegion.setLocation(windowLoc);
		captureRegion.width = ScreenScanInit.scanWindow.getWidth();
		captureRegion.height = ScreenScanInit.scanWindow.getHeight();
		
		
		return robot.createScreenCapture(captureRegion);
	}
	
	public static void startScanning()
	{
		
		Thread screenScan = new Thread()
		{
			public void run()
			{
				BufferedImage previousImage = null;
				long messageTime = 0;
				boolean allowMessage = true;
				
				while(true)
				{
					// Sets refresh rate to once every 5 seconds
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					// Capture a screenshot inside the boxed region
					BufferedImage currentImage = Globals.scanCapture();
					
					// If there hasn't been a message for the last 10 minutes
					if(!allowMessage && System.currentTimeMillis() - messageTime > 600000)
					//if(!allowMessage && System.currentTimeMillis() - messageTime > 30000)
					{
						allowMessage = true;
					}
					
					if(previousImage != null)
					{
						if(!bufferedImagesEqual(currentImage, previousImage) && allowMessage)
						{
							playAudioFile(new File(System.getProperty("user.dir") 
									      +"\\alertFile.wav"));
							
							YahooMail.sendPhoneNotification();
							YahooMail.sendPhoneNotification();
							
							
							messageTime = System.currentTimeMillis();
							allowMessage = false;
							
							
						}
						else if(!bufferedImagesEqual(currentImage, previousImage) 
								&& !allowMessage)
						{
							messageTime = System.currentTimeMillis();
						}
					}
					
					
					previousImage = currentImage;
				}
			}
		};
		
		screenScan.start();
	}
	
	public static boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
	    System.out.println("Comparing Images");
		
		if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
	        for (int x = 0; x < img1.getWidth(); x++) {
	            for (int y = 0; y < img1.getHeight(); y++) {
	                if (img1.getRGB(x, y) != img2.getRGB(x, y))
	                    return false;
	            }
	        }
	    } else {
	        return false;
	    }
	    return true;
	}
	
	public static boolean playAudioFile(File soundFile) 
	{
		try
		{
			//File f = new File("./" + soundFile);
			//audioIn =   
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(soundFile.toURI().toURL()));
			clip.start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
		
		
		
	}
	
	
}
