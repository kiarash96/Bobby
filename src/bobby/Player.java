package bobby;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class Player extends SceneObject {

	@Override
	public void draw(Graphics g) {
		// TODO: Put an image here!
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 40, 100);
	}
	
}
