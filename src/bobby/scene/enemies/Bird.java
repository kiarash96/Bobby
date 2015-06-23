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
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class Bird extends Enemy {

	double dx, dy;
	
	double bx, by;
	int bw, bh;
	
	Sprite anim;
	int direction;
	
	public Bird(SceneManager sm, double bx, double by, int bw, int bh) {
		super(sm, bx + Math.random()*(bw - 100), by + Math.random()*(bh - 75), 100, 75);
		
		dx = +0.3;
		dy = +0.3;
		
		this.bx = bx;
		this.by = by;
		this.bw = bw;
		this.bh = bh;
		
		anim = new Sprite();
		anim.loadAnimatoion("rc/img/enemy/bird/", "flying", "png", 8);
		anim.setDelay(30);
		anim.scale(this.w, this.h);
		
		direction = +1;
	}

	@Override
	public void update() {
		
		
		x += dx;
		y += dy;
		
		if (x < bx || x + w > bx + bw) {
			dx *= - 1;
			direction *= -1;
		}
		if (y < by || y + h > by + bh)
			dy *= -1;
		
		anim.nextFrame();
	}

	@Override
	public void draw(Graphics g) {
		// debug
		g.setColor(Color.RED);
		g.drawRect((int)bx, (int)by, bw, bh);
		
		g.drawImage(anim.getCurrentImage(), (int)(x + (direction == -1 ? w : 0)), (int)y, direction*w, h, null);
	}
	
}
