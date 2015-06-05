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

import bobby.main.KeyHandler;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
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
public class Player extends SceneObject {
	
	private int status;
	private int direction;
	private static final int IDLE = 0;
	private static final int RUN = 1;
	
	// jump variables
	private int jumpStatus; // 0 = not jumping		1 = going up		2 = falling down
	private static final int maxJumpHeight = 120;
	private int currentJumpHeight;
	
	// Animations
	private Sprite idleAnimation;
	private Sprite runAnimation;
	
	// jump images
	private Sprite jumpUpSprite;
	private Sprite jumpFallSprite;
	
	public Player(SceneManager sm) {
		super(sm, 50, 704, 90, 135);
		
		status = IDLE;
		direction = +1;
		
		jumpStatus = 0;
		currentJumpHeight = 0;
		
		// load animations
		
		idleAnimation = new Sprite();
		idleAnimation.loadAnimatoion("/player", "idle", "png", 2);
		idleAnimation.setDelay(100);
		idleAnimation.scale(w, h);
		
		runAnimation = new Sprite();
		runAnimation.loadAnimatoion("/player", "run", "png", 6);
		runAnimation.setDelay(25);
		runAnimation.scale(w, h);
		
		// load jump images
		
		jumpUpSprite = new Sprite();
		jumpUpSprite.loadImage("/player/jump/jump-up.png");
		jumpUpSprite.scale(w, h);
		
		jumpFallSprite = new Sprite();
		jumpFallSprite.loadImage("/player/jump/jump-fall.png");
		jumpFallSprite.scale(w, h);
	}

	@Override
	public void update() {
		status = IDLE;
		if (KeyHandler.getKeyStatus(KeyEvent.VK_RIGHT) > 0) {
			x ++;
			direction = +1;
			status = RUN;
		}
		if (KeyHandler.getKeyStatus(KeyEvent.VK_LEFT) > 0) {
			x --;
			direction = -1;
			status = RUN;
		}
		
		if (KeyHandler.getKeyStatus(KeyEvent.VK_SPACE) == KeyHandler.KEY_PRESS && jumpStatus == 0)
			jumpStatus = 1;
			
		// update jump
		if (jumpStatus == 1) {
			currentJumpHeight ++;
			y --;

			if (currentJumpHeight == maxJumpHeight)
				jumpStatus = 2;
		}
		else if (jumpStatus == 2) {
			currentJumpHeight --;
			y ++;

			if (currentJumpHeight == 0)
				jumpStatus = 0;
		}
		
		// next frame in animation
		if (status == IDLE)
			idleAnimation.nextFrame();
		else if (status == RUN)
			runAnimation.nextFrame();
	}

	@Override
	public void draw(Graphics g) {
		BufferedImage image;
		if (status == RUN)
			image = runAnimation.getCurrentImage();
		else // idle
			image = idleAnimation.getCurrentImage();
		
		if (jumpStatus == 1)
			image = jumpUpSprite.getCurrentImage();
		else if (jumpStatus == 2)
			image = jumpFallSprite.getCurrentImage();
		
		g.drawImage(image, x + (direction == -1 ? w : 0), y, direction*w, h, null);
	}
	
}
