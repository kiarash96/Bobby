package bobby.scene;

import java.awt.Graphics;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public abstract class SceneObject {
	
	protected int x, y;
	
	protected SceneManager sm;
	
	public SceneObject(SceneManager sm, int x, int y) {
		this.sm = sm;
		
		this.x = x;
		this.y = y;
	}
	
	public abstract void update();
	public abstract void draw(Graphics g);
	
}
