package bobby;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JApplet;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class SceneManager extends Thread {
	
	private final JApplet parent;
	private ArrayList<SceneObject> list;
	
	public SceneManager(JApplet parent) {
		list = new ArrayList<>();
		this.parent = parent;
	}
	
	public void draw(Graphics g) {
		// TODO: implement double buffering
		for (SceneObject sobject : list)
			sobject.draw(g);
	}
	
	@Override
	public void run() {
		while (true)
			parent.repaint();
	}
	
}
