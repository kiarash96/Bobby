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

package bobby.scene;

import bobby.main.KeyHandler;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class Player extends SceneObject {

	// jump action
	private int jumpState;
	private int currentHeight;
	private static final int maxJumpHeight = 100;
	
	public Player(SceneManager sm) {
		super(sm, 0, 500);
		
		jumpState = 0;
		currentHeight = 0;
	}

	@Override
	public void update() {
		if (KeyHandler.getKeyStatus(KeyEvent.VK_RIGHT) > 0)
			x ++;
		if (KeyHandler.getKeyStatus(KeyEvent.VK_LEFT) > 0)
			x --;
		
		if (KeyHandler.getKeyStatus(KeyEvent.VK_SPACE) == KeyHandler.KEY_PRESS && jumpState == 0)
			jumpState = 1;
		
		if (jumpState > 0) {
			int dy = (jumpState == 1 ? -1 : +1);
			
			currentHeight += -1 * dy;
			y += dy;
			
			if (currentHeight == 0 || currentHeight == maxJumpHeight)
				jumpState = (jumpState + 1) % 3;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(x, y, 10, 50);
	}
	
}
