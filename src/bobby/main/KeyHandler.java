/*
 * The MIT License
 *
 * Copyright 2015 Kiarash Korki <kiarash96@users.sf.net>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package bobby.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
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
	private static int[] keyStatus = new int[120 + 1];
	
	public static final int KEY_FREE = 0;
	public static final int KEY_PRESS = 1;
	public static final int KEY_DOWN = 2;
	public static final int KEY_RELEASE = 3;
	
	public static void update() {
		for (int i = 0; i < keyStatus.length; i ++)
			if (keyStatus[i] == KEY_PRESS)
				keyStatus[i] = KEY_DOWN;
			else if (keyStatus[i] == KEY_RELEASE)
				keyStatus[i] = KEY_FREE;
		
		KeyEventPair event = eventQ.poll();
		if (event != null) {
			if (event.action == KEY_PRESS && keyStatus[event.keycode] != KEY_DOWN) {
				keyStatus[event.keycode] = KEY_PRESS;
			}
			else if (event.action == KEY_RELEASE) {
				keyStatus[event.keycode] = KEY_RELEASE;
			}
		}
		
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() <= 120)
			eventQ.add(new KeyEventPair(e.getKeyCode(), KEY_PRESS));
		
		//System.err.println("Pres: " + e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() <= 120)
			eventQ.add(new KeyEventPair(e.getKeyCode(), KEY_RELEASE));
	}
	
	public static int getKeyStatus(int keycode) {
		return keyStatus[keycode];
	}
	
	public static int getKeyStatus(char ch) {
		return getKeyStatus(KeyStroke.getKeyStroke(ch, 0).getKeyCode());
	}
	
}
