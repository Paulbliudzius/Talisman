package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import enemy.Enemy;
import game.abilities.CharacterAbilities;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Mar 18, 2014.
 */
public class GameRunner {
    public static int playerturn = 0;
    public static JFrame configFrame = null;
    public static ResourceBundle messages = ResourceBundle.getBundle(
	    "Englishstrings", new Locale("en", "US"));
    private static PlayerNamePanel characterConfigPanel;
    public static ArrayList<Player> players = new ArrayList<Player>();
    public static CharacterDeck characterDeck = new CharacterDeck();
    public static QuestDeck questDeck = new QuestDeck();
    public static int WIDTH = java.awt.GraphicsEnvironment
	    .getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    public static int HEIGHT = java.awt.GraphicsEnvironment
	    .getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    public static BoardMap map = new BoardMap();
    public static SpellsList spellDeck = new SpellsList();
    public static EnemyList enemyDeck = new EnemyList();
    public static itemDeck itemDeck = new itemDeck();
    public static AdventureDeck adventureDeck = new AdventureDeck();
    public static GameFrame frame = null;

    public static ArrayList<int[]> moves = new ArrayList<int[]>();

    /**
     * The different states of the game. ANY is only to be used for spells and
     * cards
     */
    public enum GameState {
	BEFORE_ROLL, MOVEMENT, BEFORE_COMBAT, DURING_COMBAT, AFTER_COMBAT, ANY, GAME_START
    }

    public static GameState currentState = GameState.GAME_START;
    public static ArrayList<Enemy> enemiesOnBoard = new ArrayList<Enemy>();
    public static int description;

    public GameRunner() throws IOException {
	characterDeck.shuffle();
    }

    public static void reItemsMap() {
	map = new BoardMap();
	adventureDeck = new AdventureDeck();
	characterDeck = new CharacterDeck();
    }

    public static void createMainMenu() throws IOException {

	MainMenuFrame frame = new MainMenuFrame();
	// frame.setLayout(new BorderLayout());
	JPanel pane = new JPanel();
	// pane.setLayout(new BorderLayout());

	pane.setOpaque(false);
	frame.addMainMenuButtons(pane);
	frame.add(pane);
	// pane.setSize(800,800);
	frame.setVisible(true);
    }

    /**
     * TODO Put here a description of what this method does.
     * 
     */
    protected static void startGameConfig() {
	// TODO start new game here
	configFrame = new JFrame(GameRunner.messages.getString("gameConfig"));
	JPanel panel = new ConfigurationPanel();
	configFrame.setLayout(new BorderLayout());
	// frame.setSize(WIDTH/2, HEIGHT/2);
	configFrame.add(panel);
	configFrame.pack();
	configFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	configFrame.setVisible(true);

    }

    protected static void startNewGame() throws IOException {
	configFrame.dispose();
	GameFrame frame = new GameFrame();
	// frame.setLayout(new GridBagLayout());
	JPanel pane = new JPanel();
	JPanel informationPane = new JPanel();
	// pane.setLayout(new GridLayout(10,10,0,5));
	pane.setOpaque(false);
	frame.addGameButtons(pane);
	JPanel content = new JPanel();
	content.setLayout(new BorderLayout());
	JPanel background = null;
	try {
	    background = new JPanel(new BorderLayout()) {
		BufferedImage background = ImageIO.read(new File(
			"talisman_board.jpg"));

		@Override
		public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    g.drawImage(this.background, 0, 0, getWidth(), getHeight(),
			    null);
		}
	    };
	} catch (IOException exception) {
	    System.out.println("Error in MainMenuFrame loading background");
	    exception.printStackTrace();
	}
	JPanel content2 = new JPanel();
	content2.setLayout(new BorderLayout());
	frame.addDisplayPanel(informationPane);
	informationPane.setOpaque(false);
	content2.add(informationPane, BorderLayout.CENTER);
	content2.add(pane, BorderLayout.WEST);
	content.add(content2, BorderLayout.NORTH);
	background.setOpaque(false);
	content.setOpaque(false);
	content2.setOpaque(false);
	background.add(content, BorderLayout.NORTH);
	frame.setContentPane(background);
	// frame.add(informationPane);
	// frame.add(pane);
	// pane.setSize(800,800);
	frame.setVisible(true);
	frame.resetEncounterSequence();
	GameRunner.frame = frame;
    }

    /**
	 * 
	 */
    protected static void startCharacterConfig(int currentPlayer) {

	configFrame.dispose();
	configFrame = new JFrame(GameRunner.messages.getString("charSelect"));
	// configFrame.setLayout(new GridLayout(2,1,10,10));
	configFrame.setLayout(new BoxLayout(configFrame.getContentPane(),
		BoxLayout.Y_AXIS));
	JLabel label = new JLabel(GameRunner.messages.getString("selectChar"));
	label.setForeground(Color.yellow.darker());
	label.setFont(new Font("Verdana", Font.BOLD, 12));
	label.setBackground(Color.black);
	// TODO: Center align title
	label.setAlignmentX(.5f);
	configFrame.add(label);
	characterConfigPanel = new PlayerNamePanel(currentPlayer);
	characterConfigPanel.setBounds(0, 0, 300, 600);
	configFrame.add(characterConfigPanel);
	configFrame.add(new CharacterPanel(currentPlayer));
	configFrame.pack();
	configFrame.setVisible(true);
    }

    /**
     * TODO Put here a description of what this method does.
     * 
     * @throws IOException
     * 
     */
    public static void returnToMainMenu() throws IOException {
	configFrame.dispose();
	createMainMenu();
    }

    public static void nextPlayer() {
	currentState = GameState.BEFORE_ROLL;
	checkPlayerAbilitiesAndEffects();
	if (players.get(playerturn).character.getLife() < 1) {
	    GameRunner.players.remove(GameRunner.playerturn);
	} else {
	    playerturn++;
	}
	if (playerturn == players.size()) {
	    playerturn = 0;
	}
	if (players.get(playerturn).lostTurns() > 0) {
	    players.get(playerturn).useTurn();
	    nextPlayer();
	} else if (players.get(playerturn).lostTurns() < 0) {
	    players.get(playerturn).roll();
	    nextPlayer();
	}
    }

    /**
     * Checks the during the currentState of the game.
     * 
     */
    private static void checkPlayerAbilitiesAndEffects() {
	Player current = players.get(playerturn);
	CharacterAbilities[] abilities = current.character.getAbilities();
	if (abilities != null) { // nullcheck for testing purposes
	    // for (int i = 0; i < abilities.length; i++) {
	    // if (abilities[i].getState() == GameState.ANY
	    // || abilities[i].getState() == currentState) {
	    // abilities[i].effect();
	    // System.out.println("I did an effect!");
	    // }
	    // }
	}

    }

    public static void addPlayer(Character character) {
	Player p1 = new Player(character,
		characterConfigPanel.nameField.getText());
	addPlayer(p1);

    }

    /**
     * TODO Put here a description of what this method does.
     * 
     * @param currentSpell
     */
    private static SoundPlayer audio;

    public static void playAudio(String string) {
	// audio effect
	try {
	    if (audio != null) {
		audio.stop();
	    }
	    audio = new SoundPlayer(string);
	    audio.start();
	} catch (IOException exception) {
	    System.out.println(GameRunner.messages.getString("errorSound"));
	    exception.printStackTrace();
	}
    }

    /**
     * Returns the localized string. For convnienence
     * 
     * @param messageName
     * @return
     */
    public static String getMessage(String messageName) {
	return GameRunner.messages.getString(messageName);
    }

    public static void addPlayer(Player player) {
	players.add(player);
    }

    public static void setLanguage(String lowerCase, String upperCase,
	    String stringClass) {
	Locale currentLocale;
	currentLocale = new Locale(lowerCase, upperCase);
	messages = ResourceBundle.getBundle(stringClass, currentLocale);
    }

}