package bobby;

import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class MainFrame extends JFrame {
	
	public static final int WIDTH = 1024, HEIGHT = 768;
	private SceneManager sm;
	private ScreenPainter painter;
	private KeyHandler kHandler;
	
	public MainFrame() {
		super();
		
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLocationByPlatform(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Bobby is going to COLLEGE!");
		this.setFocusable(true);
		this.requestFocus();
		
		sm = new SceneManager();
		painter = new ScreenPainter(this);
		
		kHandler = new KeyHandler();
		this.addKeyListener(kHandler);
		
		// begin test
		sm.add(new Player(sm, kHandler));
		// end of test
		
		new Thread(sm).start();
		new Thread(painter).start();
	}
	
	@Override
	public void paint(Graphics g) {
		painter.draw(g, sm.getObjectList());
	}
	
	
	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
	
}
