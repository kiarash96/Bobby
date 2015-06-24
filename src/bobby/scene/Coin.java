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

import java.awt.Graphics;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */

public class Coin extends SceneObject {

	public static int WIDTH = 50;
	public static int HEIGHT = 50;
	
	private Sprite anim;
	
	public Coin(SceneManager sm, double x, double y) {
		super(sm, x, y, WIDTH, HEIGHT);
		
		anim = new Sprite();
		anim.loadAnimatoion("rc/img/items/", "coin", "png", 10);
		anim.setDelay(15);
		anim.setBase(0, 600);
		anim.scale(w, h);
	}

	@Override
	public void update() {
		anim.nextFrame();
	}

	@Override
	public void draw(Graphics g) {
		super.drawWithOffset(g, anim.getCurrentImage(), x, y, w, h, +1);
	}
	
}
