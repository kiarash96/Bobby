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

import bobby.scene.Background;
import bobby.scene.HUD;
import bobby.scene.Player;
import bobby.scene.SceneManager;
import bobby.scene.enemies.Zombie;
import java.awt.Graphics;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class GameLevel extends GameState {

	SceneManager sm;
	
	public static int GROUND_LEVEL = 835;
	
	HUD hud;
	
	public GameLevel() {
		sm = new SceneManager();
		
		sm.add(new Background(sm, "rc/img/bg.png"));
		
		sm.add(new Player(sm, 10));
		
		
		sm.add(new Zombie(sm, 750, -700, 0.2));
		sm.add(new Zombie(sm, 950, -700, 0.2));
		sm.add(new Zombie(sm, 1150, -700, 0.2));
		
		hud = new HUD(sm);
		
		// debug
		// sm.showBoundingBox(true); 
	}
	
	@Override
	public void update() {
		sm.update();
		hud.update();
		
		// TODO: game over
		if (sm.getPlayer().health < 0)
			System.err.println("GAME OVER!!!!!!!!!!");
	}

	@Override
	public void draw(Graphics g) {
		sm.draw(g);
		hud.draw(g);
	}
	
}
