package bobby;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.KeyStroke;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class KeyHandler implements KeyListener {

	private boolean[] keyStatus;
	
	public KeyHandler() {
		// 'z' = 90
		keyStatus = new boolean[90 + 1];
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() <= 90)
			keyStatus[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() <= 90)
			keyStatus[e.getKeyCode()] = false;
	}
	
	
	public boolean isKeyDown(int keycode) {
		return keyStatus[keycode];
	}
	
	public boolean isKeyDown(char ch) {
		return keyStatus[KeyStroke.getKeyStroke(ch, 0).getKeyCode()];
	}
	
}
