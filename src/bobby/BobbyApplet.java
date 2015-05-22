package bobby;

import java.awt.Graphics;
import javax.swing.JApplet;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class BobbyApplet extends JApplet {
	
	private final int WIDTH = 1024, HEIGHT = 768;
	
	private ScreenManager sm;
	
	@Override
	public void init() {
		sm = new ScreenManager(this);
		
		setSize(WIDTH, HEIGHT);
		setFocusable(true);
		requestFocus();
	}
	
	@Override
	public void start() {
		sm.start();
	}
	
	@Override
	public void paint(Graphics g) {
		sm.draw(g);
	}
	
	@Override
	public void stop() {
		sm.stop();
	}
	
}
