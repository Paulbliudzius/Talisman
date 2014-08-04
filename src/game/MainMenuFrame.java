package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Mar 19, 2014.
 */
public class MainMenuFrame extends JFrame {

    public static int WIDTH = java.awt.GraphicsEnvironment
	    .getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    public static int HEIGHT = java.awt.GraphicsEnvironment
	    .getLocalGraphicsEnvironment().getMaximumWindowBounds().height;

    private SoundPlayer audio;
    public boolean playMusic = true;

    public MainMenuFrame() throws IOException {
	setTitle("Main Menu");
	setSize(this.WIDTH, this.HEIGHT);
	try {
	    setContentPane(new JPanel() {
		BufferedImage background = ImageIO.read(new File(
			"TalismanMainMenu.jpg"));

		@Override
		public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    g.drawImage(this.background, 0, 0, getWidth(), getHeight(),
			    null);
		}
	    });
	} catch (IOException exception) {
	    System.out.println(GameRunner.messages.getString("errorMenuFrame"));
	    exception.printStackTrace();
	}
	GameRunner.playAudio("TalismanMainMenuMusic.wav");
	setVisible(true);
	// setResizable(false);

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void addMainMenuButtons(Container pane) {
	JButton startGameButtonE = newGameButton("English", 0);
	JButton startGameButtonG = newGameButton("Deustch", 1);
	// JButton startGameButtonS = newGameButton("Espanol", 2);
	JButton rules = rulesButton();
	// rules.setLayout(null);
	startGameButtonE.setBounds(new Rectangle(80, 80, 80, 80));
	startGameButtonG.setBounds(new Rectangle(80, 80, 80, 80));
	// startGameButtonS.setBounds(new Rectangle(80, 80, 80, 80));
	// rules.setSize(new Dimension(50,500));
	rules.setVisible(true);
	// rules.setBounds((new Rectangle(50, 50,50,50)));
	// rules.setLocation(50, 50);
	pane.add(BorderLayout.CENTER, startGameButtonE);
	pane.add(BorderLayout.CENTER, startGameButtonG);
	// pane.add(BorderLayout.CENTER, startGameButtonS);
	pane.add(BorderLayout.CENTER, rules);

	pane.setVisible(true);

    }

    /**
     * TODO Put here a description of what this method does.
     * 
     * @return
     */
    public JButton rulesButton() {
	JButton rulesButton = new JButton("Rules");
	GameFrame.applyButtonFormat(rulesButton);
	ActionListener rulesWebPage = new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		String path = "http://ecx.images-amazon.com/images/I/B1vK9yiS-zS.pdf";
		try {
		    Desktop.getDesktop().browse(URI.create(path));
		} catch (IOException exception) {
		    System.out.println(GameRunner.messages
			    .getString("alchemistName"));
		    exception.printStackTrace();
		}
	    }
	};
	rulesButton.addActionListener(rulesWebPage);
	return rulesButton;
    }

    public JButton newGameButton(String string, int caseInt) {
	JButton newGame = new JButton(string);
	GameFrame.applyButtonFormat(newGame);
	ActionListener newGameStarter = new newGameLanguage(caseInt);
	newGame.addActionListener(newGameStarter);
	return newGame;
    }

    public class newGameLanguage implements ActionListener {
	private int caseInt;

	newGameLanguage(int caseIntParam) {
	    caseInt = caseIntParam;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    if (this.caseInt == 0) {
		GameRunner.setLanguage("en", "US", "Englishstrings");
	    } else if (this.caseInt == 1) {
		GameRunner.setLanguage("de", "DE", "Germanstrings");
	    } else if (this.caseInt == 2) {
		// GameRunner.setLanguage("es", "ES", "Spanishstrings");
	    }
	    GameRunner.playAudio("clickNoise.wav");
	    GameRunner.reItemsMap();
	    launchConfigWindow();

	}

    }

    private void launchConfigWindow() {
	this.dispose();
	GameRunner.startGameConfig();

    }

    private void buttonClickSound() throws IOException {
	GameRunner.playAudio("clickNoise.wav");
    }

}
