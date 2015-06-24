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

package bobby.state;

import bobby.main.GamePanel;
import bobby.main.KeyHandler;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class GameOverState extends GameState {

	@Override
	public boolean update() {
		// TODO: make it work!!
		if (KeyHandler.getKeyStatus(KeyEvent.VK_ESCAPE) == KeyHandler.KEY_PRESS)
			return false;
		return true;
	}

	@Override
	public void draw(Graphics g) {
		String text = "GAME OVER!";
		g.setFont(new Font("TimesRoman", Font.BOLD, 64)); 
		g.setColor(Color.BLACK);
		Rectangle2D r =  g.getFontMetrics().getStringBounds(text, g);
        g.drawString(text, (int)(GamePanel.WIDTH/2 - r.getWidth()/2), (int)(GamePanel.HEIGHT/2 - r.getHeight()/2));
	}
	
}
