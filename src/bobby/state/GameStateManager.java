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

package bobby.state;

import bobby.main.KeyHandler;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class GameStateManager implements Runnable {

	public static final int sleepTime = 5;
	
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAMEOVER = 3;
	public static final int CLOSED = 4;
	
	int status;
	
	GameLevel level;
	GameOverState gameOver;
	
	public GameStateManager() {
		status = RUNNING;
		level = new GameLevel();
		gameOver = new GameOverState();
	}
	
	public void update() {
		if (status == RUNNING) {
			boolean res = level.update();
			if (res == false)
				status = GAMEOVER;
			
			// save and load
			
			if (KeyHandler.getKeyStatus(KeyEvent.VK_F5) == KeyHandler.KEY_PRESS) { // save
				try {
					ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("save.dat"));
					stream.writeObject(level);
					stream.close();
				}
				catch (IOException ex) {
					Logger.getLogger(GameStateManager.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			
			if (KeyHandler.getKeyStatus(KeyEvent.VK_F6) == KeyHandler.KEY_PRESS) { // load
				try {
					ObjectInputStream stream = new ObjectInputStream(new FileInputStream("save.dat"));
					level = (GameLevel) stream.readObject();
					stream.close();
				}
				catch (IOException | ClassNotFoundException ex) {
					Logger.getLogger(GameStateManager.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		else if (status == GAMEOVER)
			if (gameOver.update() == false)
				status = CLOSED;
	}

	public void draw(Graphics g) {
		level.draw(g);
		
		if (status == GAMEOVER)
			gameOver.draw(g);
	}

	@Override
	public void run() {
		while (status == RUNNING) {
			KeyHandler.update();
			this.update();
			
			try {
				Thread.currentThread().sleep(sleepTime);
			}
			catch (InterruptedException ex) {
				Logger.getLogger(GameStateManager.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	public boolean isRunning() {
		return status != CLOSED;
	}
}
