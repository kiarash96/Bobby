package bobby;

import java.awt.Graphics;
import javax.swing.JApplet;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class BobbyApplet extends JApplet {
	
	private final int WIDTH = 1024, HEIGHT = 768;
	@Override
	public void init() {
		setSize(WIDTH, HEIGHT);
		setFocusable(true);
		requestFocus();
	}
	
	@Override
	public void start() {
		
	}
	
	@Override
	public void paint(Graphics g) {
		
	}
	
	@Override
	public void stop() {
		
	}
	
}
