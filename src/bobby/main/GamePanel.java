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

package bobby.main;

import bobby.state.GameStateManager;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class GamePanel extends JPanel implements Runnable {
	
	// TODO: add scale
	public static final int WIDTH = 1024, HEIGHT = 768;
	
	// Buffer image
	private Image image;
	
	private GameStateManager gsm;
	private KeyHandler kHandler;
	
	// game thread
	private Thread thread;
	private boolean running;
	
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
	}

	@Override
	public void addNotify() {
		super.addNotify();
		
		if (thread == null) {
			kHandler = new KeyHandler();
			this.addKeyListener(kHandler);
			
			thread = new Thread(this);
			thread.start();
		}
	}
	
	@Override
	public void run() {
		running = true;
		gsm = new GameStateManager();
		
		while (running) {
			// update
			kHandler.update();
			gsm.update();
			
			// draw on buffer
			gsm.draw(image.getGraphics());
			
			// draw on screen
			this.getGraphics().drawImage(image, 0, 0, null);
			
			// TODO: sleep for fixed fps
		}
	}

}
