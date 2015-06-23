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
import java.awt.Image;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class Background extends SceneObject {

	private class Layer {
		Sprite img;	
		int x, y;
	}
	
	Layer[][] layers;
	
	public Background(SceneManager sm, String path, String name, int cnt) {
		super(sm, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		layers = new Layer[cnt][2];
		
		for (int i = 0; i < layers.length; i ++) {
			layers[i] = new Layer[2];
			for (int j = 0; j < 2; j ++) {
				layers[i][j] = new Layer();
				layers[i][j].img = new Sprite();
				layers[i][j].img.loadImage(path + "/" + name + "/layer-" + (i + 1) + ".png");
				layers[i][j].img.scale(w*layers[i][j].img.getCurrentImage().getHeight()/h, h);
			}
		}
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < layers.length; i ++) {
			double offset = sm.offset/((layers.length - i)*(layers.length - i)); // for parallex effect
			int w = layers[i][0].img.getCurrentImage().getWidth();
			Image img = layers[i][0].img.getCurrentImage();
			
			for (int j = 0; j < 2; j ++) {
				super.drawWithOffset(g, img, offset, layers[i][j].x, layers[i][j].y,
										w, h, +1);
				
				// continuesly put the second image after the first one
				if (layers[i][j].x - offset + w < GamePanel.WIDTH)
					layers[i][(j + 1)%2].x = layers[i][j].x + w;
			}
		}
	}
	
}
