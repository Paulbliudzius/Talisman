package game;

/**
 * The main class... everything starts here.
 *
 * @author roccoma.
 *         Created Mar 18, 2014.
 */
import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;

public class Main {

	/**
	 * Launches welcome menu
	 *
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		new GameRunner();
		GameRunner.createMainMenu();
		
	}

}
