package bobby;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class SceneManager implements Runnable {
	
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
	
	Player getPlayer() {
		for (SceneObject so : list)
			if (so instanceof Player)
				return (Player)so;
		
		return null;
	}

	ArrayList<SceneObject> getObjectList() {
		return list;
	}

	@Override
	public void run() {
		while (true) {
			for (SceneObject object : list)
				object.update();
			
			try {
				Thread.sleep(10); // TODO: find a better value!
			}
			catch (InterruptedException ex) {
				Logger.getLogger(SceneManager.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
}
