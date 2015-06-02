package bobby.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.KeyStroke;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class KeyHandler implements KeyListener {

	private int[] keyStatus;
	
	public static int KEY_FREE = 0;
	public static int KEY_PRESS = 1;
	public static int KEY_DOWN = 2;
	public static int KEY_RELEASE = 3;
	
	public KeyHandler() {
		// 'z' = 90
		keyStatus = new int[90 + 1];
	}
	
	public void update() {
		for (int i = 0; i < keyStatus.length; i ++)
			if (keyStatus[i] == KEY_PRESS)
				keyStatus[i] = KEY_DOWN;
			else if (keyStatus[i] == KEY_RELEASE)
				keyStatus[i] = KEY_FREE;
	}
	
	// utility function
	public static int charToCode(char ch) {
		return KeyStroke.getKeyStroke(ch, 0).getKeyCode();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() <= 90)
			keyStatus[e.getKeyCode()] = KEY_PRESS;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() <= 90)
			keyStatus[e.getKeyCode()] = KEY_RELEASE;
	}
	
	public int getKeyStatus(int keycode) {
		return keyStatus[keycode];
	}
	
	public int getKeyStatus(char ch) {
		return getKeyStatus(charToCode(ch));
	}
	
}
