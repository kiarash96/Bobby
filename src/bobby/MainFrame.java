package bobby;

import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class MainFrame extends JFrame {
	
	private final int WIDTH = 1024, HEIGHT = 768;
	private SceneManager sm;
	
	
	public MainFrame() {
		super();
		
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLocationByPlatform(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Bobby is going to COLLEGE!");
		this.setFocusable(true);
		this.requestFocus();
		
		sm = new SceneManager(this);
		
		// begin test
		sm.add(new Player());
		this.addKeyListener(new KeyHandler(sm));
		// end of test
		
		sm.start();
	}
	
	@Override
	public void paint(Graphics g) {
		sm.draw(g);
	}
	
	
	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
	
}
