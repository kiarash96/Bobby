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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class Animation {
	
	private BufferedImage[] frames;
	private int currentFrame;
	private int delay, currentDelay;
	
	public Animation(String dir, String name, int frameCount, int delay) {
		frames = new BufferedImage[frameCount];
		for (int i = 0; i < frameCount; i ++)
			try {
				frames[i] = ImageIO.read(new File(dir + "/" + name + "_" + i + ".png"));
			}
			catch (IOException ex) {
				Logger.getLogger(Animation.class.getName()).log(Level.SEVERE, null, ex);
			}
		
		currentFrame = 0;
		
		this.delay = Math.max(delay, 1);
		currentDelay = 0;
	}
	
	public void nextFrame() {
		if (currentDelay == 0)
			currentFrame = (currentFrame + 1) % frames.length;
		
		currentDelay = (currentDelay + 1) % delay;
	}
	
	public BufferedImage getCurrentImage() {
		return frames[currentFrame];
	}
	
}
