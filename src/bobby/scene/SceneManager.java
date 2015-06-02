package bobby.scene;

import java.awt.Graphics;
import java.util.ArrayList;
/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class SceneManager {
	
	private ArrayList<SceneObject> list;
	
	public SceneManager() {
		list = new ArrayList<>();
	}
	
	public void add(SceneObject so) {
		list.add(so);
	}
	
	public void remove(SceneObject so) {
		list.remove(so);
	}

	public void update() {
		for (SceneObject object : list)
			object.update();
	}
	
	public void draw(Graphics g) {
		for (SceneObject object : list)
			object.draw(g);
	}
	
}
