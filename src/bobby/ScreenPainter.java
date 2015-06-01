package bobby;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class ScreenPainter implements Runnable {
	
	Image buffer; // second buffer
	
	private final JFrame parent;
	
	public ScreenPainter(JFrame parent) {
		this.parent = parent;
		
		buffer = new BufferedImage(parent.getWidth(), parent.getHeight(), BufferedImage.TYPE_INT_BGR);
	}

	public void draw(Graphics g, ArrayList<SceneObject> list) {
		Graphics offG = buffer.getGraphics();
		
		// clear the screen
		offG.setColor(Color.WHITE);
		offG.fillRect(0, 0, parent.getWidth(), parent.getHeight());
		
		for (SceneObject sobject : list)
			sobject.draw(offG);
		
		// draw second buffer on the applet
		g.drawImage(buffer, 0, 0, parent);
		
		
		
		try {
			Thread.sleep(1000/60); // 60 fps
		}
		catch (InterruptedException ex) {
			Logger.getLogger(SceneManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	@Override
	public void run() {
		while (true)
			parent.repaint();
	}
	
}
