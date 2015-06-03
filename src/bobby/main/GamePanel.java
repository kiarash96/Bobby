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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
	
	public static final int SCALE = 2;
	public static final int WIDTH = 640, HEIGHT = 480;
	
	private static final int FPS = 60;
	
	// Buffer image
	private Image image;
	
	private GameStateManager gsm;
	
	private Thread renderThread; // graphics and keyboard update thread
	private Thread gsmThread; // logic thread
	
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
	}

	@Override
	public void addNotify() {
		super.addNotify();
		
		if (renderThread == null) {
			this.addKeyListener(new KeyHandler());
			
			renderThread = new Thread(this);
			renderThread.start();
		}
		
		if (gsmThread == null) {
			gsm = new GameStateManager();
			
			gsmThread = new Thread(gsm);
			gsmThread.start();
		}
	}
	
	@Override
	public void run() {
		while (true) {
			long start = System.nanoTime();
			
			Graphics g = image.getGraphics();
			
			// clear the buffer
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			// draw on buffer
			gsm.draw(g);
			
			// draw the buffer on the screen
			this.getGraphics().drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
			
			long elapsed = System.nanoTime() - start;
			long wait = (long)(Math.max(0.0, 1000.0/FPS - (double)(elapsed)/(1000 * 1000)));
			
			try {
				Thread.sleep(wait);
			}
			catch (InterruptedException ex) {
				Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

}
