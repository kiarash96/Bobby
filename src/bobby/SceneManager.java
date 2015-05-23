package bobby;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JApplet;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class SceneManager extends Thread {
	
	Image buffer; // second buffer
	
	private final JApplet parent;
	private ArrayList<SceneObject> list;
	
	public SceneManager(JApplet parent) {
		list = new ArrayList<>();
		this.parent = parent;
		
		buffer = parent.createImage(parent.getWidth(), parent.getHeight());
	}
	
	public void draw(Graphics g) {
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
	
	public void add(SceneObject so) {
		list.add(so);
	}
	
	public void remove(SceneObject so) {
		list.remove(so);
	}
	
	Player getPlayer() {
		for (SceneObject so : list)
			if (so instanceof Player)
				return (Player)so;
		
		return null;
	}
	
}
