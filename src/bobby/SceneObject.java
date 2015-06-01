package bobby;

import java.awt.Graphics;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public abstract class SceneObject {
	
	protected int x, y;
	
	protected SceneManager sm;
	protected KeyHandler kHandler;
	
	public SceneObject(SceneManager sm, KeyHandler kHandler, int x, int y) {
		this.sm = sm;
		this.kHandler = kHandler;
		
		this.x = x;
		this.y = y;
	}
	
	public abstract void update();
	public abstract void draw(Graphics g);
	
}
