package bobby;

import java.awt.Color;
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
		
		// clear the screen
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, parent.getWidth(), parent.getHeight());
		
		for (SceneObject sobject : list)
			sobject.draw(g);
		
		// TODO: sleep
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
	
}
