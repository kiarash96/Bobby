package bobby.main;

import bobby.state.GameStateManager;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class GamePanel extends JPanel implements Runnable {
	
	// TODO: add scale
	public static final int WIDTH = 1024, HEIGHT = 768;
	
	// Buffer image
	Image image;
	
	GameStateManager gsm;
	KeyHandler kHandler;
	
	// game thread
	Thread thread;
	boolean running;
	
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
	}

	@Override
	public void addNotify() {
		super.addNotify();
		
		if (thread == null) {
			kHandler = new KeyHandler();
			this.addKeyListener(kHandler);
			
			thread = new Thread(this);
			thread.start();
		}
	}
	
	@Override
	public void run() {
		running = true;
		gsm = new GameStateManager();
		
		while (running) {
			// update
			kHandler.update();
			gsm.update();
			
			// draw on buffer
			gsm.draw(image.getGraphics());
			
			// draw on screen
			this.getGraphics().drawImage(image, 0, 0, null);
			
			// TODO: sleep for fixed fps
		}
	}

}
