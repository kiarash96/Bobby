package bobby;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class Player extends SceneObject {

	public Player(SceneManager sm, KeyHandler kHandler) {
		super(sm, kHandler, MainFrame.WIDTH/2, MainFrame.HEIGHT/2);
	}

	@Override
	public void update() {
		if (kHandler.isKeyDown('D'))
			this.x ++;
		
		if (kHandler.isKeyDown('A'))
			this.x --;
		
		if (kHandler.isKeyDown('W'))
			this.y --;
		
		if (kHandler.isKeyDown('S'))
			this.y ++;
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO: Put an image here!
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 40, 100);
	}
	
}
