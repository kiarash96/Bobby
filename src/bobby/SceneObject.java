package bobby;

import java.awt.Graphics;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public abstract class SceneObject {
	
	private int x, y;

	public SceneObject() {
		
	}
	
	public SceneObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}

	
	public abstract void draw(Graphics g);
	
}
