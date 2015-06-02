package bobby.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import javax.swing.KeyStroke;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class KeyHandler implements KeyListener {

	private static class KeyEventPair {
		
		private int keycode;
		private int action;

		public KeyEventPair(int keycode, int action) {
			this.keycode = keycode;
			this.action = action;
		}
	
	}
		
	private static Queue<KeyEventPair> eventQ = new LinkedList<>();
	private static int[] keyStatus = new int[90 + 1];
	
	public static final int KEY_FREE = 0;
	public static final int KEY_PRESS = 1;
	public static final int KEY_DOWN = 2;
	public static final int KEY_RELEASE = 3;
	
	public void update() {
		for (int i = 0; i < keyStatus.length; i ++)
			if (keyStatus[i] == KEY_PRESS)
				keyStatus[i] = KEY_DOWN;
			else if (keyStatus[i] == KEY_RELEASE)
				keyStatus[i] = KEY_FREE;
		
		try {
			KeyEventPair event = eventQ.remove();
			if (event.action == KEY_PRESS && keyStatus[event.keycode] != KEY_DOWN) {
				keyStatus[event.keycode] = KEY_PRESS;
			}
			else if (event.action == KEY_RELEASE) {
				keyStatus[event.keycode] = KEY_RELEASE;
			}
		}
		catch (NoSuchElementException excep) {
			// it's okay! the queue is empty.
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() <= 90)
			eventQ.add(new KeyEventPair(e.getKeyCode(), KEY_PRESS));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() <= 90)
			eventQ.add(new KeyEventPair(e.getKeyCode(), KEY_RELEASE));
	}
	
	public static int getKeyStatus(int keycode) {
		return keyStatus[keycode];
	}
	
	public static int getKeyStatus(char ch) {
		return getKeyStatus(KeyStroke.getKeyStroke(ch, 0).getKeyCode());
	}
	
}
