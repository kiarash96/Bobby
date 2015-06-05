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

import java.awt.Image;
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
public class Sprite {
	
	private static final String prePath = "rc/img";
	
	private BufferedImage[] frames;
	private int currentFrame;
	private int delay, currentDelay;
	
	public Sprite() {
		delay = 1;
	}
	
	public void loadImage(String filepath) {
		frames = new BufferedImage[1];
		try {
			frames[0] = ImageIO.read(new File(prePath + "/" + filepath));
		}
		catch (IOException ex) {
			Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		currentFrame = 0;
		currentDelay = 0;
	}
	
	public void loadAnimatoion(String dir, String name, String ext, int frameCount) {
		frames = new BufferedImage[frameCount];
		for (int i = 0; i < frameCount; i ++)
			try {
				frames[i] = ImageIO.read(new File(prePath + "/" + dir + "/" + name + "/frame-" + (i+1) + "." + ext));
			}
			catch (IOException ex) {
				Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
			}
		
		currentFrame = 0;
		currentDelay = 0;
	}
	
	public void scale(int width, int height) {
		for (int i = 0; i < frames.length; i ++) {
			Image tmp = frames[i].getScaledInstance(width, height, Image.SCALE_SMOOTH);
			frames[i] = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			frames[i].createGraphics().drawImage(tmp, 0, 0, null);
			frames[i].getGraphics().dispose();
		}
	}
	
	public void setDelay(int delay) {
		this.delay = Math.max(delay, 1);
	}
	
	public void setFrame(int index) {
		this.currentFrame = index;
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
