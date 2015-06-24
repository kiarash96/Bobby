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

import bobby.main.GamePanel;
import bobby.scene.Background;
import bobby.scene.HUD;
import bobby.scene.Player;
import bobby.scene.SceneManager;
import bobby.scene.enemies.Bird;
import bobby.scene.enemies.Spike;
import bobby.scene.enemies.Zombie;
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class GameLevel extends GameState implements Serializable {

	SceneManager sm;
	
	public static int GROUND_LEVEL = 835;
	
	transient HUD hud;
	
	public GameLevel() {
		sm = new SceneManager();
		
		sm.add(new Background(sm, "rc/img/", "background", 3));
		
		
		sm.add(new Zombie(sm, 1300, -1300, 0.2));
		sm.add(new Zombie(sm, 1500, -1500, 0.2));
		sm.add(new Zombie(sm, 1700, -1700, 0.2));
		
		sm.add(new Bird(sm, 500, 400, 700, 300));
		
		sm.add(new Spike(sm, 2000, "a"));
		
		sm.add(new Zombie(sm, 2700, -550, 0.5));
		sm.add(new Zombie(sm, 2800, -650, 0.5));
		
		sm.add(new Spike(sm, 3100, "b"));
		
		sm.add(new Bird(sm, 2800, 600, 800, 170));
		
		sm.add(new Player(sm, GamePanel.WIDTH/3));
		
		hud = new HUD(sm);
		
		// debug
		// sm.showBoundingBox(true); 
	}
	
	@Override
	public boolean update() {
		sm.update();
		hud.update();
		
		if (sm.getPlayer().health < 0)
			return false;
		return true;
	}

	@Override
	public void draw(Graphics g) {
		sm.draw(g);
		hud.draw(g);
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		
		hud = new HUD(sm);
	}
}
