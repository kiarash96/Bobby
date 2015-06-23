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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class SceneManager {
	
	private ArrayList<SceneObject> list;
	
	// references
	private Player player;
	private Background background;
	
	// for camera
	public static double scrollSpeed = 1.0;
	public double offset;
	
	private boolean showBB;
	
	public SceneManager() {
		list = new ArrayList<>();
		showBB = false;
		offset = 0.0;
	}
	
	public void showBoundingBox(boolean value) {
		showBB = value;
	}
	
	public void add(SceneObject so) {
		list.add(so);
		
		if (so instanceof Player)
			player = (Player)so;
		if (so instanceof Background)
			background = (Background)so;
	}
	
	public void remove(SceneObject so) {
		if (so instanceof Player)
			player = null;
		if (so instanceof Background)
			background = null;
		list.remove(so);
	}

	public void update() {
		// TODO: add conditions
		if (KeyHandler.getKeyStatus(KeyEvent.VK_RIGHT) > 0)
			offset += scrollSpeed;
		if (KeyHandler.getKeyStatus(KeyEvent.VK_LEFT) > 0)
			offset --;
		
		for (SceneObject object : list)
			object.update();
	}
	
	public void draw(Graphics g) {
		for (SceneObject object : list) {
			object.draw(g);
			if (showBB) {
				Rectangle box = object.getBoundingBox();
				g.setColor(Color.red);
				g.drawRect(box.x, box.y, box.width, box.height);
			}
		}
	}

	// getter functions
	
	public ArrayList<SceneObject> getList() {
		return list;
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
