package bobby;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JApplet;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class ScreenManager extends Thread {
	
	private final JApplet parent;
	private ArrayList<SceneObject> list;
	
	public ScreenManager(JApplet parent) {
		list = new ArrayList<>();
		this.parent = parent;
	}
	
	public void draw(Graphics g) {
		// TODO: implement double buffering
		for (SceneObject sobject : list)
			sobject.draw(g);
	}
	
	public void run() {
		while (true)
			parent.repaint();
	}
	
}
