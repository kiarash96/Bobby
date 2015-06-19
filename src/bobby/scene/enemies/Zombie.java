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
import bobby.scene.SceneObject;
import bobby.scene.Sprite;
import java.awt.Graphics;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class Zombie extends SceneObject {

	// moves from minX to maxX
	private double minX, maxX;
	private double dx;
	private int direction;
	
	private Sprite animation;
	
	public Zombie(SceneManager sm, double x, double y, double dist, double speed, int dir) {
		super(sm, x, y, 60, 66);
		
		minX = Math.min(x, x + dist);
		maxX = Math.max(x, x + dist);
		dx = speed;
		direction = dir;
		
		animation = new Sprite();
		animation.loadAnimatoion("rc/img/enemy/zombie/", "idle", "png", 2);
		animation.scale(w, h);
		animation.setDelay(75);
	}
	
	@Override
	public void update() {
		if ((dx > 0 && x + dx > maxX) || (dx < 0 && x - dx < minX)) {
			dx *= -1;
			direction *= -1;
		}
		
		x += dx;
		
		animation.nextFrame();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(animation.getCurrentImage(), (int) (x + (direction == -1 ? w : 0)), (int)y, direction*w, h, null);
	}
	
}
