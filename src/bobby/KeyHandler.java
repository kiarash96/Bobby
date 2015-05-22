package bobby;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class KeyHandler implements KeyListener {

	private SceneManager sm; // reference to the global sm

	public KeyHandler(SceneManager sm) {
		this.sm = sm;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		sm.getPlayer().setX(sm.getPlayer().getX() + 1); // test player
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}
