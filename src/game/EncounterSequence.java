package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import spells.Spell;
import actions.Action;
import actions.FightCharacters;
import actions.FightEnemy;
import actions.MovePlayerPos;
import enemy.Enemy;

public class EncounterSequence {
    public GameState state;
    private Player player;
    private boolean usedRollFate;
    private int chosen;
    public int actionChoiceSize;
    public Action[] actionChoices;

    public enum GameState {
	CHARACTER_CREATION, BEFORE_ROLL, MOVEMENT_CHOICE, POST_MOVEMENT, BEFORE_COMBAT, COMBAT_PROJECTION, PRE_ASSASINATE, TAKE_TROPHY, DURING_COMBAT, WON_COMBAT, DRAW_COMBAT, LOST_COMBAT, POST_COMBAT, ANY, GAME_START, SPACE_INTERACTION, ITEM_SELECTION, CAST_SPELL, COMBAT_CHOICE, COMBAT_ROLL_TWO, MONK_COMBAT, ATTACK_LESSER, AID_COMBAT, ANIMALS_DRAGONS
    }

    GameFrame f;
    private int[][] moveSpaces = new int[8][2];
    private String[] moveName;
    private String[] moveDesc;
    private Action[] moves;
    private int moveChoices;

    public EncounterSequence(GameFrame frame) {
	f = frame;
	player = GameRunner.players.get(GameRunner.playerturn);
	f.updatePlayerLabel(player);
	int[] pos = player.getPosition();
	state = GameState.BEFORE_ROLL;
	player.checkAbilities(state);
    }

    public void buttonHandler(String name) {
	if (GameRunner.players.size() == 1) {
	    JPanel background = null;
	    try {
		background = new JPanel(new BorderLayout()) {
		    BufferedImage background = ImageIO.read(new File(
			    "GameOver.png"));

		    @Override
		    public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(this.background, 0, 0, getWidth(),
				getHeight(), null);
		    }
		};
	    } catch (IOException exception) {
		System.out.println("Error in MainMenuFrame loading background");
		exception.printStackTrace();
	    }
	    int enSize = GameRunner.enemiesOnBoard.size();
	    for (int count = 0; count < enSize; count++) {
		GameRunner.enemiesOnBoard.remove(0);
	    }
	    GameRunner.playAudio("you_win_sound_effect.wav");
	    GameRunner.frame.setContentPane(background);
	    GameRunner.frame.resize(1920, 1080);
	    return;
	}
	player.checkAbilities(state);
	if (GameFrame.actionButton != null) {
	    GameFrame.actionButton.setVisible(false);
	}
	// System.out.println(name + state.name());
	if (state == GameState.BEFORE_ROLL) {
	    if (name == "Roll") {
		int roll = player.roll();
		rolled(roll, this.player);
		if (!usedRollFate) {
		    GameFrame.fateButton.setVisible(true);
		} else {
		    GameFrame.fateButton.setVisible(false);
		}

		state = GameState.MOVEMENT_CHOICE;
	    }
	} else if (state == GameState.MOVEMENT_CHOICE) {

	    if (name == "Fate" && !usedRollFate && player.useFate()) {
		f.resetNextPlayerButtons();
		usedRollFate = true;
		state = GameState.BEFORE_ROLL;
	    }
	    if (name == "PreChoice") {
		GameFrame.NextPlayerButton.setVisible(false);
		GameFrame.rollButton.setVisible(false);
		GameFrame.choices.setVisible(true);
		GameFrame.select.setVisible(true);
		GameFrame.descButton.setVisible(true);
	    }
	    if (name == "Select") {
		GameFrame.choices.setVisible(false);
		GameFrame.descButton.setVisible(false);
		GameFrame.fateButton.setVisible(false);
		GameFrame.NextPlayerButton.setVisible(true);
		this.moves[GameFrame.choices.getSelectedIndex()].act(player);
		state = GameState.POST_MOVEMENT;
		int enemyOn = this.enemyOnSpace();
		if (enemyOn != -1) {

		    FightEnemy newFight = new FightEnemy(
			    GameRunner.enemiesOnBoard.get(enemyOn));
		    GameRunner.enemiesOnBoard.remove(enemyOn);
		    GameRunner.moves.clear();
		    newFight.act(player);
		} else {
		    playersOnSpace();
		    GameRunner.moves.clear();
		}
	    }
	    if (name == "Description") {
		GameRunner.description = GameFrame.choices.getSelectedIndex();
		// System.out.println(this.moveDesc[GameFrame.choices
		// .getSelectedIndex()]);
	    }
	    f.repaint();
	} else if (state == GameState.SPACE_INTERACTION) {
	    if (name == "Fate" && !usedRollFate && player.useFate()) {
		usedRollFate = true;
		buttonHandler("PreRoll");
	    }
	    if (name == "PreRoll") {
		GameFrame.preRoll();
		GameFrame.NextPlayerButton.setVisible(false);
		GameFrame.fateButton.setVisible(false);
	    }
	    if (name == "Roll") {
		this.chosen = new Dice(this.actionChoiceSize / 6).roll();
		Player player = GameRunner.players.get(GameRunner.playerturn);

		if (player.character.getFate() > 0 && !usedRollFate) {
		    GameFrame.fateButton.setVisible(true);
		}
		if (player.stay() > 0) {
		    GameFrame.fateButton.setVisible(false);
		    this.chosen = 0;
		    player.stayedTurn(1);
		} else if (player.stay() < 0) {
		    GameFrame.fateButton.setVisible(false);
		    this.chosen = 0;
		    player.stayedTurn(-1);
		}
		GameFrame.lastRoll.setText("Rolled " + this.chosen);
		GameFrame.rollButton.setVisible(false);
		GameFrame.select.setVisible(true);
	    }
	    if (name == "Select") {
		GameFrame.select.setVisible(false);
		state = GameState.POST_MOVEMENT;
		if (GameFrame.choices.isVisible()) {
		    // choiced
		    GameFrame.choices.setVisible(false);
		    GameFrame.NextPlayerButton.setVisible(true);
		    this.actionChoices[GameFrame.choices.getSelectedIndex()]
			    .act(player);
		} else {
		    // rolled
		    GameFrame.fateButton.setVisible(false);
		    GameFrame.NextPlayerButton.setVisible(true);
		    this.actionChoices[this.chosen - 1].act(player);
		}
		if (player.getDrawnCard() != null) {
		    player.useCard();
		}
	    }
	    if (name == "PreChoice") {
		GameFrame.NextPlayerButton.setVisible(false);
		GameFrame.rollButton.setVisible(false);
		GameFrame.choices.setVisible(true);
		GameFrame.select.setVisible(true);
	    }
	    f.repaint();
	} else if (state == GameState.POST_MOVEMENT) {
	    f.repaint();
	    if (name == "No Encounter") {
		spaceEncounter();
	    }
	}
    }

    /**
     * returns -1 if no enemy on space. otherwise returns enemy position in
     * enemies on board array
     * 
     * @return
     */
    private int enemyOnSpace() {
	for (int i = 0; i < GameRunner.enemiesOnBoard.size(); i++) {
	    int[] playPos = player.getPosition();
	    int[] enemyPos = GameRunner.enemiesOnBoard.get(i).getPosition();
	    if (playPos[0] == enemyPos[0] && playPos[1] == enemyPos[1]) {
		return i;
	    }
	}
	return -1;
    }

    /**
     * This is an ugly call. Don't mess with this call. Questions? Ask Paul.
     */
    public void rolled(int roll, Player playerParam) {
	Player player = playerParam;
	f.lastRoll.setText("Rolled: " + roll);
	GameRunner.playAudio("dice_roll.wav");
	int[] playerPos = player.getPosition();
	int[] ccw = new int[2];
	int[] cw = new int[2];
	ccw[1] = playerPos[1];
	cw[1] = playerPos[1];
	int[] occw = new int[2];
	int[] ocw = new int[2];
	occw[1] = playerPos[1] - 1;
	ocw[1] = playerPos[1] - 1;
	int[] iccw = new int[2];
	int[] icw = new int[2];
	iccw[1] = playerPos[1] + 1;
	icw[1] = playerPos[1] + 1;
	int max = 24;
	boolean moveToOuter = false;
	boolean moveToInner = false;
	ArrayList<int[]> options = new ArrayList<int[]>();
	if (playerPos[0] == 8 && playerPos[1] == 2) {
	    options.add(new int[] { 8, 2 });
	} else {
	    if (player.deadSent() && playerPos[1] == 0) {
		moveToInner = true;
	    }
	    if (playerPos[1] == 1) {
		max = 16;
		moveToOuter = true;
	    }
	    if (playerPos[1] == 2) {

		// NEED TO ADD ABILITY TO MAKE IT TO Region 0
		max = 8;
		if (playerPos[0] - 1 < 0) {
		    cw[0] = (max + (playerPos[0] - 1));
		} else {
		    cw[0] = playerPos[0] - 1;
		}
		if (playerPos[0] + 1 > max - 1) {
		    ccw[0] = (playerPos[0] + 1 - max);
		} else {
		    ccw[0] = playerPos[0] + 1;
		}
		options.add(ccw);
		options.add(cw);
		if (playerPos[0] == 0) {
		    if (playerPos[0] - roll < -1) {
			options.add(new int[] { roll - playerPos[0] - 1, 1 });
			options.add(new int[] { 16 - (roll - playerPos[0] - 1),
				1 });
		    } else {
			options.add(new int[] { 0, 1 });
		    }
		    if (roll == 5) {
			options.add(new int[] { 4, 0 });
		    }
		    if (roll == 6) {
			options.add(new int[] { 3, 0 });
			options.add(new int[] { 5, 0 });
		    }
		}
	    } else {
		if (playerPos[0] - roll < 0) {
		    cw[0] = (max + (playerPos[0] - roll));
		} else {
		    cw[0] = playerPos[0] - roll;
		}
		if (playerPos[0] + roll > max - 1) {
		    ccw[0] = (playerPos[0] + roll - max);
		} else {
		    ccw[0] = playerPos[0] + roll;
		}
		options.add(ccw);
		options.add(cw);

		if (moveToOuter) {
		    if ((playerPos[0] + roll) > 3 && 3 > (playerPos[0] - roll)) {
			if (playerPos[0] > 3) {
			    if (playerPos[0] - roll - 3 == -1) {
				options.add(new int[] { 4, 0 });
			    } else {
				options.add(new int[] {
					4 - (playerPos[0] - roll - 2), 0 });
				if (4 + (playerPos[0] - roll - 2) < 0) {
				    options.add(new int[] {
					    24 + (4 + (playerPos[0] - roll - 2)),
					    0 });
				} else {
				    options.add(new int[] {
					    4 + (playerPos[0] - roll - 2), 0 });
				}
			    }
			} else {
			    if (playerPos[0] + roll - 3 == 1) {
				options.add(new int[] { 4, 0 });
			    } else {
				options.add(new int[] {
					4 + (playerPos[0] + roll - 4), 0 });
				if (4 - (playerPos[0] + roll - 4) < 0) {
				    options.add(new int[] {
					    24 + (4 - (playerPos[0] + roll - 4)),
					    0 });
				} else {
				    options.add(new int[] {
					    4 - (playerPos[0] + roll - 4), 0 });
				}
			    }
			}
		    } else if (playerPos[0] + roll > 19
			    && 19 > playerPos[0] - roll) {
			if (playerPos[0] + roll == 20) {
			    options.add(new int[] { 4, 0 });
			} else {
			    options.add(new int[] {
				    4 + (playerPos[0] + roll - 20), 0 });
			    if (4 + (playerPos[0] + roll - 20) < 0) {
				options.add(new int[] {
					24 + (4 + (playerPos[0] + roll - 20)),
					0 });
			    } else {
				options.add(new int[] {
					4 + (playerPos[0] + roll - 20), 0 });
			    }
			}
		    }
		}
		if (moveToInner) {
		    if ((playerPos[0] + roll) > 4 && 4 > (playerPos[0] - roll)) {
			if (playerPos[0] > 4) {
			    if (playerPos[0] - roll - 4 == -1) {
				options.add(new int[] { 3, 1 });
			    } else {
				options.add(new int[] {
					3 - (playerPos[0] - roll - 5), 1 });
				if (3 - (playerPos[0] + roll - 5) < 0) {
				    options.add(new int[] {
					    16 + (3 - (playerPos[0] + roll - 5)),
					    1 });
				} else {
				    options.add(new int[] {
					    3 - (playerPos[0] + roll - 5), 1 });
				}
			    }
			} else {
			    if (playerPos[0] + roll - 4 == 1) {
				options.add(new int[] { 3, 1 });
			    } else {
				options.add(new int[] {
					3 + (playerPos[0] + roll - 5), 1 });
				if (3 - (playerPos[0] + roll - 5) < 0) {
				    options.add(new int[] {
					    16 + (3 - (playerPos[0] + roll - 5)),
					    1 });
				} else {
				    options.add(new int[] {
					    3 - (playerPos[0] + roll - 5), 1 });
				}
			    }
			}
		    } else if (playerPos[0] + roll > 28
			    && 28 > playerPos[0] - roll) {
			options.add(new int[] { 3, 1 });
		    }
		}
	    }
	}

	int count = options.size();
	Action[] moveOptions = new Action[count];
	for (int i = 0; i < count; i++) {
	    moveOptions[i] = new MovePlayerPos(options.get(i));
	}
	int[][] spaces = new int[count][2];
	for (int i = 0; i < count; i++) {
	    spaces[i] = options.get(i);
	}
	String[] moveName = new String[count];
	for (int i = 0; i < count; i++) {
	    moveName[i] = Integer.toString(i + 1) + ": "
		    + GameRunner.map.getSpace(options.get(i)).getName();
	}
	String[] moveDesc = new String[count];
	for (int i = 0; i < count; i++) {
	    moveDesc[i] = GameRunner.map.getSpace(options.get(i)).getDesc();
	}
	new MovementChoice(count, moveOptions, spaces, moveName, moveDesc)
		.act(player);
    }

    public void playersOnSpace() {
	state = GameState.SPACE_INTERACTION;
	int[] pos = player.getPosition();
	if (GameRunner.map.enemyOnSpace(pos)) {
	    startFight(GameRunner.map.getEnemyOnSpace(pos));
	} else {
	    checkPlayersOnBoard();
	}

    }

    public void spaceEncounter() {
	GameRunner.map.getSpace(player.getPosition()).interact(player);
	if (player.getDrawnCard() != null) {
	    player.useCard();
	}
    }

    public void startFight(Enemy enemy) {
	JButton buttons[] = new JButton[5];
	JButton fight = new JButton("Fight");
	GameFrame.applyButtonFormat(fight);
	fight.addActionListener(GameFrame.startFight(player, enemy));
	buttons[0] = fight;
	GameFrame.displayCard.setCard("<html> " + enemy.getName()
		+ "<br> Type " + enemy.getType() + " " + enemy.getCombatType()
		+ " " + enemy.power() + "</html>", enemy.getDescription(),
		buttons);
	GameFrame.displayCard.setVisible(true);
	GameFrame.disableGamePane();
    }

    /**
     * This will be used for space and card interactions, when allowing the
     * player have a choice in cards
     */
    public void changeState(GameState stateParam) {
	this.state = stateParam;
    }

    public void checkPlayersOnBoard() {
	ArrayList<Player> players = GameRunner.map.characterContents(player
		.getPosition());
	if (player.checkAbilities(GameState.ATTACK_LESSER)
		&& !player.getPosition().equals(new int[] { 2, 3 })) {
	    ArrayList<Player> playerDump = new ArrayList<Player>();
	    for (int i = 0; i < players.size(); i++) {
		if (players.get(i).character.getStrength() < player.character
			.getStrength()) {
		    playerDump.add(players.get(i));
		}
	    }
	    for (int i = 0; i < playerDump.size(); i++) {
		players.remove(playerDump.get(i));
	    }
	}
	Player[] player = new Player[players.size()];
	if (players.size() > 0) {
	    for (int i = 0; i < players.size(); i++) {
		player[i] = players.get(i);
	    }

	    new FightCharacters(player).act(this.player);
	} else {
	    spaceEncounter();
	}
    }

    public void endTurn() {
	player.lastBattle = null;
	checkEffects();
	GameRunner.nextPlayer();
	f.resetEncounterSequence();
    }

    /**
     * This will be used for space and card interactions, when allowing the
     * player have a choice in cards
     */
    public void actionChoice(int size, Action[] actions) {
	actionChoiceSize = size;
	actionChoices = actions;
    }

    public static void checkEffects() {
	Player current = GameRunner.players.get(GameRunner.playerturn);
	if (current.spellEffects.isEmpty()) {
	    // System.out.println("No spell effect on turn end.");
	} else {
	    for (int i = 0; i < current.spellEffects.size(); i++) {
		Spell currentEffect = current.spellEffects.get(i);
		if (currentEffect.getDuration() == 1) {
		    currentEffect.endOfDuration();
		    current.spellEffects.remove(currentEffect);
		} else {
		    currentEffect.duration = (currentEffect.duration - 1);
		}
	    }
	}

    }

    /**
     * TODO Put here a description of what this method does.
     * 
     * @return
     */
    public GameState getCurrentState() {
	// TODO Auto-generated method stub.
	return state;
    }

    public Action[] getMoves() {
	return moves;
    }

    /**
     * This is used in the new movement selection box for holding which spaces
     * are able to be moved to, their names, and their descriptions, in hopes
     * that this will then be able to generate a number on the space for the
     * user to select in the combo box and then get a description.
     * 
     * @param choicesAmount
     * @param moves
     * @param names
     * @param descriptions
     * @param spaces
     */
    public void movementActionChoice(int choicesAmount, Action[] moves,
	    String[] names, String[] descriptions, int[][] spaces) {
	this.moveSpaces = spaces;
	this.moveName = names;
	this.moveDesc = descriptions;
	this.moves = new Action[choicesAmount];
	GameRunner.moves.clear();
	for (int count = 0; count < choicesAmount; count++) {
	    GameRunner.moves.add(spaces[count]);
	    this.moves[count] = moves[count];
	}
	this.moves = moves;
	this.moveChoices = choicesAmount;
    }
}