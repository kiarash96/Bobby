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
import bobby.state.GameLevel;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class Worm extends Enemy {

	public static int WIDTH = 80;
	public static int HEIGHT = 120;
	
	private static int IDLE = 1;
	private static int ATTACK = 2;
	private int status;
	
	private Sprite idleAnim;
	private Sprite attackAnim;
	
	public Worm(SceneManager sm, double x) {
		super(sm, x, GameLevel.GROUND_LEVEL - HEIGHT, WIDTH, HEIGHT);
		
		status = IDLE;
		
		idleAnim = new Sprite();
		idleAnim.loadAnimatoion("rc/img/enemy/worm/", "idle", "png", 2);
		idleAnim.setDelay(50);
		idleAnim.scale(w, h);
		
		attackAnim = new Sprite();
		attackAnim.loadAnimatoion("rc/img/enemy/worm/", "attack", "png", 9);
		attackAnim.setDelay(10);
		attackAnim.scale(w, h);
	}

	@Override
	public void update() {
		if (status == ATTACK)
			attackAnim.nextFrame();
		else
			idleAnim.nextFrame();
	}

	@Override
	public void draw(Graphics g) {
		Image img = (status == ATTACK ? attackAnim.getCurrentImage() : idleAnim.getCurrentImage());
		super.drawWithOffset(g, img, x, y, w, h, +1);
	}
	
}
