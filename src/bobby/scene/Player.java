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

import bobby.main.GamePanel;
import bobby.main.KeyHandler;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class Player extends SceneObject {

	private class JumpAction extends Action {
		
		private int height = 0;
		private static final int maxHeight = 100;

		@Override
		public void run() {
			isRunning = true;
			
			while (height < maxHeight) {
				height ++;
				Player.this.y --;
				yield();
			}
			while (height > 0) {
				height --;
				Player.this.y ++;
				yield();
			}
			
			isRunning = false;
		}
		
	}
	
	public Player(SceneManager sm) {
		super(sm, GamePanel.WIDTH/2, GamePanel.HEIGHT/2);
	}

	@Override
	public void update() {
		if (KeyHandler.getKeyStatus(KeyEvent.VK_RIGHT) > 0)
			x ++;
		if (KeyHandler.getKeyStatus(KeyEvent.VK_LEFT) > 0)
			x --;
		
		if (KeyHandler.getKeyStatus(KeyEvent.VK_SPACE) == KeyHandler.KEY_PRESS && JumpAction.isRunning == false)
			new Thread(new JumpAction()).start();
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(x, y, 10, 50);
	}
	
}
