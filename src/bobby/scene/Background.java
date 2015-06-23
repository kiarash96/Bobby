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
import java.awt.Graphics;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class Background extends SceneObject {

	private Sprite[] layers;
	
	public Background(SceneManager sm, String path, String name, int cnt) {
		super(sm, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		layers = new Sprite[cnt];
		for (int i = 0; i < layers.length; i ++) {
			layers[i] = new Sprite();
			layers[i].loadImage(path + "/" + name + "/layer-" + (i + 1) + ".png");
			layers[i].scale(w*layers[i].getCurrentImage().getHeight()/h, h);
		}
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < layers.length; i ++)
			super.drawWithOffset(g, layers[i].getCurrentImage(), sm.offset/(layers.length - i), x, y, layers[i].getCurrentImage().getWidth(), h, +1);
	}
	
}
