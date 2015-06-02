package bobby.main;

import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class GamePanel extends JPanel implements Runnable {
	
	public static final int WIDTH = 1024, HEIGHT = 768;
	
	boolean running;
	
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		
		new Thread(this).start();
	}

	@Override
	public void run() {
		running = true;
		
		while (running) {
			update();
			draw();
			// TODO: sleep for fixed fps
		}
	}

	private void update() {
		
	}
	
	private void draw() {
		
	}
	
}
