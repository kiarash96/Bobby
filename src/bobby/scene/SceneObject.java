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
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public abstract class SceneObject {
	
	protected double x, y; // upper left corner of the bounding box
	protected int w, h;
	
	protected SceneManager sm;
	
	public SceneObject(SceneManager sm, double x, double y, int w, int h) {
		this.sm = sm;
		
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;
	}
	
	public abstract void update();
	public abstract void draw(Graphics g);
	
	public Rectangle getBoundingBox() {
		return new Rectangle((int)x + 10, (int)y + 10, w - 20, h - 20);
	}
	
	public void drawWithOffset(Graphics g, Image img, double x, double y, int w, int h, int dir) {
		g.drawImage(img, (int) (x + (dir == -1 ? w : 0) - sm.offset), (int)y, dir*w, h, null);
	}
	
	public void drawWithOffset(Graphics g, Image img, double offset, double x, double y, int w, int h, int dir) {
		g.drawImage(img, (int) (x + (dir == -1 ? w : 0) - offset), (int)y, dir*w, h, null);
	}
	
}
