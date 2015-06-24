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

package bobby.scene.enemies;

import bobby.scene.SceneManager;
import bobby.scene.Sprite;
import static bobby.scene.enemies.Zombie.HEIGHT;
import bobby.state.GameLevel;
import java.awt.Graphics;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class Spike extends Enemy {
	
	Sprite image;
	
	public Spike(SceneManager sm, double x, String name) {
		super(sm, x, 0, 0, 0);
		
		if (name.equals("a")) {
			w = 95; h = 100;
		}
		else if (name.equals("b")) {
			w = 120; h = 70;
		}
		
		y = GameLevel.GROUND_LEVEL - h;
		
		image = new Sprite();
		image.loadImage("rc/img/enemy/spike/" + name + ".png");
		image.scale(w, h);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics g) {
		super.drawWithOffset(g, image.getCurrentImage(), x, y, w, h, +1);
	}
	
}
