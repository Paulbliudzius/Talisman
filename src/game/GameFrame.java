package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import actions.Action;
import enemy.Enemy;

public class GameFrame extends JFrame {
    private static Action actionPerform;
    public SoundPlayer audio;
    public static JPanel gamePane;
    public static boolean spellsVisible = false;
    public static boolean statsVisible = false;
    public static boolean inventoryVisible = false;
    public static boolean descriptionVisible = false;

    public static BoardOverlayPane movement;
    public static JButton rollButton;
    public static JButton fateButton;
    public static JButton NextPlayerButton;
    public static JButton toggleSpells;
    public static JButton toggleInventory;
    public static JButton toggleStats;
    public static JButton descButton;
    public static JButton select;
    public static JButton actionButton;
    public static JLabel currentPlayer;
    public static JLabel lastRoll;
    public static JLabel playPosition;
    public static SpellsPanel spellsPanel;
    public static StatsPanel statsPanel;
    public static InventoryPanel inventoryPanel;
    public static TakeItemPanel stealPanel;
    public static MovementDescriptionPanel moveDescription;
    public static DisplayCardPane displayCard;
    public static CombatFrame combatFrame;
    public static EncounterSequence sequence;
    public static JPanel background;
    public static JComboBox<String> choices = new JComboBox<String>(
	    new DefaultComboBoxModel(new String[] { "1", "2" }));
    public static RollAndEffectPanel rollAndEffectPanel;

    public GameFrame() throws IOException {
	setTitle("Main Menu");
	spellsPanel = new SpellsPanel();
	GameFrame.rollAndEffectPanel = new RollAndEffectPanel();
	statsPanel = new StatsPanel();
	inventoryPanel = new InventoryPanel();
	stealPanel = new TakeItemPanel();
	stealPanel.setVisible(false);
	displayCard = new DisplayCardPane();
	combatFrame = new CombatFrame();
	moveDescription = new MovementDescriptionPanel();
	setSize(MainMenuFrame.WIDTH, MainMenuFrame.HEIGHT);
	try {
	    setContentPane(new JPanel() {
		BufferedImage background = ImageIO.read(new File(
			"talisman_board.jpg"));

		@Override
		public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    g.drawImage(this.background, 0, 0, getWidth(), getHeight(),
			    null);
		}
	    });
	} catch (IOException exception) {
	    System.out.println("Error in MainMenuFrame loading background");
	    exception.printStackTrace();
	}
	movement = new BoardOverlayPane();
	setGlassPane(movement);
	setVisible(true);
	movement.setVisible(true);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void changeImg(String backImg) {
	try {
	    background = new JPanel(new BorderLayout()) {
		BufferedImage background = ImageIO
			.read(new File("GameOver.png"));

		@Override
		public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    g.drawImage(this.background, 0, 0, getWidth(), getHeight(),
			    null);
		}
	    };
	} catch (IOException exception) {
	    System.out.println("Error in GameOver");
	    exception.printStackTrace();
	}

    }

    /**
     * Panel for displaying spells, inventory, and stats.
     * 
     * @param DisplayPanel
     */
    public void addDisplayPanel(Container DisplayPanel) {
	formatPane(GameFrame.spellsPanel);
	formatPane(GameFrame.inventoryPanel);
	formatPane(stealPanel);
	formatPane(GameFrame.statsPanel);
	formatPane(GameFrame.displayCard);
	formatPane(GameFrame.combatFrame);
	formatPane(GameFrame.moveDescription);
	formatPane(GameFrame.rollAndEffectPanel);

	DisplayPanel.add(GameFrame.spellsPanel);
	DisplayPanel.add(GameFrame.inventoryPanel);
	DisplayPanel.add(GameFrame.stealPanel);
	DisplayPanel.add(GameFrame.statsPanel);
	DisplayPanel.add(GameFrame.displayCard);
	DisplayPanel.add(GameFrame.combatFrame);
	DisplayPanel.add(GameFrame.moveDescription);
	DisplayPanel.add(GameFrame.rollAndEffectPanel);

    }

    public static void disableGamePane() {
	GameFrame.gamePane.setVisible(false);
    }

    public static void enableGamePane() {
	GameFrame.gamePane.setVisible(true);
    }

    public void addGameButtons(JPanel gamePane) {
	currentPlayer = createdFormattedLabel(GameRunner.players.get(0)
		.getName()
		+ " the "
		+ GameRunner.players.get(0).getPlayerClass());
	rollButton = rollButton();
	fateButton = fateButton();
	descButton = PanelButton(GameRunner.messages.getString("description"),
		new boolean[] { false, false, false, true });
	toggleSpells = PanelButton(GameRunner.messages.getString("spells"),
		new boolean[] { false, true, false, false });
	NextPlayerButton = NextPlayerButton();
	toggleStats = PanelButton(GameRunner.messages.getString("stats"),
		new boolean[] { true, false, false, false });
	select = selectButton();
	actionButton = actionButton("PlaceHolder", null);
	toggleInventory = PanelButton(
		GameRunner.messages.getString("inventory"), new boolean[] {
			false, false, true, false });
	JPanel pane = new JPanel();
	pane.setLayout(new GridLayout(3, 10));
	pane.setPreferredSize(new Dimension(400, 100));
	pane.add(rollButton);
	lastRoll = new JLabel("No Roll Yet");
	GameFrame.applyLabelFormat(lastRoll);
	pane.add(toggleSpells);
	pane.add(toggleStats);
	pane.add(toggleInventory);
	pane.add(lastRoll);
	pane.add(fateButton);
	pane.add(choices);
	pane.add(select);
	pane.add(descButton);
	pane.add(actionButton);
	pane.setOpaque(true);
	int[] pos = GameRunner.players.get(GameRunner.playerturn).getPosition();
	playPosition = createdFormattedLabel("Position : " + pos[0] + ", "
		+ pos[1]);

	pane.add(NextPlayerButton);
	GameFrame.choices.setVisible(false);
	GameFrame.rollButton.setVisible(true);
	GameFrame.fateButton.setVisible(false);
	GameFrame.NextPlayerButton.setVisible(false);
	GameFrame.select.setVisible(false);
	GameFrame.descButton.setVisible(false);
	GameFrame.actionButton.setVisible(false);
	pane.setVisible(true);
	pane.setBackground(Color.black);
	GameFrame.gamePane = pane;
	gamePane.add(pane);
    }

    public static JButton actionButton(String name, Action toPerform) {
	if (actionButton == null) {
	    actionButton = new JButton(name);
	    ActionListener newGameStarter = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    actionPerform.act(GameRunner.players
			    .get(GameRunner.playerturn));
		    actionButton.setVisible(false);
		}

	    };
	    actionButton.addActionListener(newGameStarter);
	    applyButtonFormat(actionButton);
	} else {
	    actionButton.setText(name);
	}
	actionPerform = toPerform;
	actionButton.setVisible(true);
	return actionButton;
    }

    private JButton selectButton() {
	JButton selectButton = new JButton(
		GameRunner.messages.getString("select"));
	applyButtonFormat(selectButton);
	ActionListener newGameStarter = new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		GameFrame.select.setVisible(false);
		GameFrame.moveDescription.setVisible(false);
		sequence.buttonHandler("Select");
	    }

	};
	selectButton.addActionListener(newGameStarter);
	return selectButton;
    }

    /**
     * TODO Put here a description of what this method does.
     * 
     * @return
     */
    public JButton fateButton() {
	JButton fateButton = new JButton(
		GameRunner.messages.getString("useFate"));
	applyButtonFormat(fateButton);
	ActionListener newGameStarter = new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		sequence.buttonHandler("Fate");
		GameFrame.fateButton.setVisible(false);
	    }

	};
	fateButton.addActionListener(newGameStarter);
	return fateButton;
    }

    public static void applyButtonFormat(JButton button) {
	button.setBackground(new Color(156, 93, 82));
	button.setFont(new Font("Verdana", Font.BOLD, 12));
    }

    public static void applyLabelFormat(JLabel label) {
	label.setForeground(Color.white);
	label.setFont(new Font("Verdana", Font.BOLD, 12));
    }

    /**
     * TODO Put here a description of what this method does.
     * 
     * @return
     */
    public JButton rollButton() {
	JButton rollButton = new JButton(GameRunner.messages.getString("roll"));
	applyButtonFormat(rollButton);
	ActionListener newGameStarter = new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		sequence.buttonHandler("Roll");
	    }
	};
	rollButton.addActionListener(newGameStarter);
	return rollButton;
    }

    public void movementButtonsSet() {
	GameFrame.fateButton.setVisible(false);
	GameFrame.NextPlayerButton.setVisible(true);
	movement.revalidate();
	movement.repaint();
    }

    public JButton NextPlayerButton() {
	JButton npButton = new JButton(GameRunner.messages.getString("endTurn"));
	applyButtonFormat(npButton);
	ActionListener newGameStarter = new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		resetNextPlayerButtons();
		lastRoll.setText(" ");
		resetPanels();
		sequence.endTurn();
		movement.repaint();

	    }
	};
	npButton.addActionListener(newGameStarter);
	return npButton;
    }

    public void resetNextPlayerButtons() {
	resetPanels();
	GameFrame.rollButton.setVisible(true);
	GameFrame.NextPlayerButton.setVisible(false);
    }

    /**
     * TODO Put here a description of what this method does.
     * 
     */
    public static void resetPanels() {
	GameFrame.descriptionVisible = false;
	GameFrame.spellsVisible = false;
	GameFrame.statsVisible = false;
	GameFrame.inventoryVisible = false;
	GameFrame.stealPanel.refresh();
	GameFrame.statsPanel.refresh();
	GameFrame.spellsPanel.refresh();
	GameFrame.inventoryPanel.refresh();
	setInfoPanels();
    }

    public static void formatPane(JPanel panel) {
	Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	Border loweredbevel = BorderFactory.createLoweredBevelBorder();
	Border matte = BorderFactory.createMatteBorder(3, 3, 3, 3,
		Color.YELLOW.darker());
	Border compound = BorderFactory.createCompoundBorder(raisedbevel,
		loweredbevel);
	panel.setBorder(BorderFactory.createCompoundBorder(matte, compound));
	panel.setBackground(Color.BLACK);

    }

    /**
     * Name and Stat,Spell,Inventory,Description for boolean
     * 
     * @param buttonName
     * @param displays
     *            Stat,Spell,Inventory,Description
     * @return Button
     */
    public JButton PanelButton(String buttonName, boolean[] displays) {
	JButton button = new JButton(buttonName);
	applyButtonFormat(button);
	button.setVisible(true);
	ActionListener viewPanel = new PanelListener(displays);
	button.addActionListener(viewPanel);
	return button;
    }

    // public JButton viewStats() {
    // JButton button = new JButton(GameRunner.messages.getString("stats"));
    // applyButtonFormat(button);
    // button.setVisible(true);
    // ActionListener viewStats = new ActionListener() {
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // GameFrame.statsPanel.refresh();
    // GameFrame.spellsVisible = false;
    // GameFrame.statsVisible = !GameFrame.statsVisible;
    // GameFrame.inventoryVisible = false;
    // GameFrame.setInfoPanels();
    //
    // }
    // };
    // button.addActionListener(viewStats);
    // return button;
    // }

    /**
     * TODO Put here a description of what this method does.
     * 
     */

    public static void setInfoPanels() {
	GameFrame.spellsPanel.setVisible(GameFrame.spellsVisible);
	GameFrame.statsPanel.setVisible(GameFrame.statsVisible);
	GameFrame.inventoryPanel.setVisible(GameFrame.inventoryVisible);
	GameFrame.moveDescription.setVisible(GameFrame.descriptionVisible);
    }

    public JLabel createdFormattedLabel(String name) {
	JLabel label = new JLabel(name);
	label.setForeground(Color.white);
	label.setFont(new Font("Verdana", Font.BOLD, 16));
	this.add(label);
	return label;
    }

    public void resetEncounterSequence() {

	movement.repaint();
	sequence = new EncounterSequence(this);
    }

    public void updatePlayerLabel(Player p) {
	currentPlayer.setText(p.getName() + " the " + p.getPlayerClass());
    }

    /**
     * TODO Put here a description of what this method does.
     * 
     */
    public static void preRoll() {
	GameFrame.lastRoll.setText(GameRunner.messages.getString("roll"));
	GameFrame.rollButton.setVisible(true);
	GameFrame.fateButton.setVisible(false);
	GameFrame.NextPlayerButton.setVisible(false);

    }

    /**
     * TODO Put here a description of what this method does.
     * 
     * @param player
     * @param enemy
     * @return
     */
    public static ActionListener startFight(Player player, Enemy enemy) {
	// TODO Auto-generated method stub.

	class fight implements ActionListener {
	    private Player player;
	    private Enemy enemy;

	    public fight(Player player, Enemy enemy) {
		this.player = player;
		this.enemy = enemy;
	    }

	    @Override
	    public void actionPerformed(ActionEvent arg0) {

		GameFrame.displayCard.setVisible(false);
		GameFrame.displayCard.removeAll();
		GameFrame.combatFrame.setVisible(true);
		GameFrame.combatFrame.generateComponents(this.enemy,
			this.player);
	    }
	}
	return new fight(player, enemy);
    }

    public static ActionListener startFight(Player player, Player enemy) {
	// TODO Auto-generated method stub.

	class fight implements ActionListener {
	    private Player player;
	    private Player enemy;

	    public fight(Player player, Player enemy) {
		this.player = player;
		this.enemy = enemy;
	    }

	    @Override
	    public void actionPerformed(ActionEvent arg0) {

		GameFrame.displayCard.setVisible(false);
		GameFrame.displayCard.removeAll();
		GameFrame.combatFrame.setVisible(true);
		GameFrame.combatFrame.generateComponents(this.enemy,
			this.player);
	    }
	}
	return new fight(player, enemy);
    }
}
