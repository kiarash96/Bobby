package bobby.main;

import javax.swing.JFrame;

/**
 *
 * @author Kiarash Korki <kiarash96@users.sf.net>
 */
public class Game {

	public static void main(String[] args) {
		JFrame window = new JFrame("Bobby goes to University!");
		window.add(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	
}
