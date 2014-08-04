package game;

import enemy.Enemy;
import game.EncounterSequence.GameState;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Apr 16, 2014.
 */
public class CombatFrame extends JPanel {
    JLabel neededRoll;
    boolean psychic;

    public CombatFrame() {
	this.setVisible(false);
	this.setLayout(new GridLayout(0, 3));

    }

    public String[] generateLabels(Player player, Object enemyOpen, int roll) {
	String[] text = new String[3];
	String[] opponent = generateOpponentText(enemyOpen, roll);
	text[0] = opponent[0];
	String[] playerText = generatePlayerText(player, opponent[1]);
	text[1] = playerText[0];
	text[2] = generateComponentsHelper(playerText[1], opponent[2], roll,
		player, enemyOpen);
	return text;
    }

    public String[] generateOpponentText(Object enemyOpen, int roll) {
	int enemyPower = 0;
	String[] text = new String[4];
	String combatType = "Strength";
	if (enemyOpen instanceof Player) { // for PVP combat
	    Player enemy = (Player) enemyOpen;
	    text[0] = enemy.getName() + ": " + enemy.character.getStrength()
		    + " CombatBonus : " + roll;
	    enemyPower = enemy.character.getStrength() + roll;
	    if (enemy.checkAbilities(GameState.COMBAT_PROJECTION)) {
		enemyPower += 1;
	    }
	    if (enemy.checkAbilities(GameState.MONK_COMBAT) && !psychic) {
		enemyPower += enemy.character.getCraft();
	    }
	} else { // for PVE combat
	    Enemy enemy = (Enemy) enemyOpen;
	    text[0] = enemy.getName() + " " + enemy.getCombatType() + ": "
		    + enemy.power() + " CombatBonus : " + roll;
	    combatType = enemy.getCombatType();
	    enemyPower = enemy.power() + roll;
	    if (enemy.getCombatType().equals("strength")) {

		combatType = "Strength";

	    } else {
		combatType = "Craft";
	    }
	}
	text[1] = combatType;
	text[2] = enemyPower + "";
	return text;
    }

    public String[] generatePlayerText(Player player, String combatType) {
	int playerPower = 0;
	String[] text = new String[3];
	if (combatType == "Strength") {
	    playerPower = player.character.getStrength();
	} else {
	    playerPower = player.character.getCraft();
	}
	if (player.checkAbilities(GameState.COMBAT_PROJECTION)) {
	    playerPower += 1;
	}
	if (player.checkAbilities(GameState.MONK_COMBAT) && !psychic) {
	    playerPower += player.character.getCraft();
	}
	text[0] = player.getName() + " base " + combatType + ": " + playerPower;
	text[1] = playerPower + "";
	return text;
    }

    public void generateComponents(Object enemyOpen, Player player) {
	JButton specialCombat = null;
	this.setVisible(true);
	Dice combatDice = new Dice(1);
	int enemyRoll = combatDice.roll();
	JLabel versus = new JLabel(" Versus ");
	GameFrame.applyLabelFormat(versus);
	String[] text = generateLabels(player, enemyOpen, enemyRoll);
	JLabel opponent = new JLabel(text[0]);
	GameFrame.applyLabelFormat(opponent);
	this.add(opponent);
	this.add(versus);
	JLabel playerInfo = new JLabel(text[1]);
	GameFrame.applyLabelFormat(playerInfo);
	this.add(playerInfo);

	this.add(new JLabel(""));
	neededRoll = new JLabel(text[2]);
	GameFrame.applyLabelFormat(neededRoll);
	this.add(neededRoll);
	this.add(new JLabel(""));
	JButton roll = new JButton("Roll!");
	GameFrame.applyButtonFormat(roll);
	roll.addActionListener(new battle(player, enemyOpen, enemyRoll, this));
	this.add(roll);

    }

    public String generateComponentsHelper(String playerPow, String enemyPow,
	    int enemyRoll, Player player, Object enemy) {
	JButton specialCombat = null;
	JButton psychicCombat = null;
	boolean assasin = (player.checkAbilities(GameState.PRE_ASSASINATE) && !player
		.getPosition().equals(new int[] { 3, 2 }));
	int playerPower = Integer.parseInt(playerPow);
	int enemyPower = Integer.parseInt(enemyPow);
	for (int i = 0; i < GameRunner.players.size(); i++) {
	    if (!(enemy instanceof Player)
		    && GameRunner.players.get(i).checkAbilities(
			    GameState.AID_COMBAT)
		    && player.getPosition()[0] == GameRunner.players.get(i)
			    .getPosition()[0]) {
		specialCombat = new JButton("Aid");
		specialCombat.addActionListener(new battle(player, enemy,
			enemyRoll, this, GameRunner.players.get(i)));
		GameFrame.applyButtonFormat(specialCombat);
		add(specialCombat);
	    }
	}
	String neededRollToWin = neededToRollText(playerPower, enemyPower,
		enemyRoll, assasin);
	if (player.checkAbilities(GameState.COMBAT_CHOICE)) {
	    psychicCombat = new JButton("Switch Combat");
	    psychicCombat.addActionListener(new swapCombat(playerPower,
		    enemyPower, enemyRoll, assasin));
	}
	if (assasin) {
	    specialCombat = new JButton("Assasinate");
	    if (enemy instanceof Player) {
		specialCombat.addActionListener(new battle(player, enemy, 0,
			this));
	    } else {
		specialCombat.addActionListener(new battle(player, enemy, 0,
			this));
	    }

	    GameFrame.applyButtonFormat(specialCombat);
	}
	if (specialCombat != null) {
	    this.add(specialCombat);
	}

	return neededRollToWin;
    }

    public String neededToRollText(int playerPower, int enemyPower,
	    int enemyRoll, boolean assassin) {
	String neededRollToWin;
	if (playerPower < enemyPower && (enemyPower - playerPower) <= 6) {
	    neededRollToWin = "Roll at least a "
		    + (enemyPower - playerPower + 1) + " to win";
	} else if (playerPower < enemyPower && (enemyPower - playerPower) > 6) {
	    neededRollToWin = "Foolish Traveler, you cannot win!";
	} else {
	    neededRollToWin = "Deliver your killing blow!";
	}
	// }
	if (assassin) {
	    neededRollToWin += "/n Or Assasinate with";
	    enemyPower -= enemyRoll;
	    if (playerPower >= enemyPower) {
		neededRollToWin += " any number!";
	    } else {
		neededRollToWin += " at least a "
			+ (playerPower - enemyPower + 1);
	    }
	}
	return neededRollToWin;
    }

    public class swapCombat implements ActionListener {
	public int playerPower, enemyPower, enemyRoll;
	public boolean assasin;

	public swapCombat(int playerPower, int enemyPower, int enemyRoll,
		boolean assasin) {
	    this.playerPower = playerPower;
	    this.enemyPower = enemyPower;
	    this.enemyRoll = enemyRoll;
	    this.assasin = assasin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    neededRoll.setText(neededToRollText(playerPower, enemyPower,
		    enemyRoll, assasin));
	    psychic = !psychic;
	}

    }

    public class battle implements ActionListener {

	private Player player;
	public Object enemy;
	private int enemyRoll;
	public CombatFrame fightFrame;
	public boolean fightingPlayer;
	public boolean finalFight;
	public Player toLoseTurn;

	public battle(Player player, Object enemy, int enemyRoll,
		CombatFrame fightFrame) {
	    this.player = player;
	    this.fightingPlayer = false;
	    if (enemy instanceof Player) {
		this.fightingPlayer = true;
	    }
	    this.enemy = enemy;
	    this.enemyRoll = enemyRoll;
	    this.fightFrame = fightFrame;
	    if (player.lastBattle != null) {
		finalFight = true;
	    }
	    this.player.lastBattle = this;
	}

	public battle(Player player2, Object enemy2, int enemyRoll2,
		CombatFrame combatFrame, Player player3) {
	    this(player2, enemy2, enemyRoll2, combatFrame);
	    toLoseTurn = player3;
	}

	public battle setFinalFight(boolean last) {
	    finalFight = last;
	    return this;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    if (toLoseTurn != null) {
		toLoseTurn.setPosition(player.getPosition());
		toLoseTurn.loseTurns(1);
		enemyRoll -= toLoseTurn.character.getStrength();
	    }
	    GameState currentState = GameFrame.sequence.getCurrentState();
	    this.fightFrame.removeAll();
	    this.fightFrame.setLayout(new GridLayout(0, 1));
	    Dice die = new Dice(1);
	    int roll = die.roll();
	    if (player.getAbilitySet()
		    .checkAbilities(GameState.COMBAT_ROLL_TWO)) {
		roll = Math.max(roll, die.roll());
	    }
	    String information = "";
	    int result;
	    if (fightingPlayer) {
		result = this.player.playerFightsPlayer(roll,
			(Player) this.enemy, this.enemyRoll, psychic);
	    } else {
		result = this.player.playerFightsEnemy(roll,
			(Enemy) this.enemy, this.enemyRoll);
	    }

	    if (result == -1) {
		information = "You lost the Battle! You have "
			+ this.player.character.getLife() + " lives";
		if (!fightingPlayer) {
		    if (((Enemy) this.enemy).lives > 0) {
			GameRunner.enemiesOnBoard.add((Enemy) this.enemy);
			((Enemy) this.enemy).setPosition(this.player
				.getPosition());
		    }
		}
		GameRunner.playAudio("scream1.wav");
		GameFrame.sequence.state = GameState.LOST_COMBAT;
	    } else if (result == 1) {
		if (fightingPlayer) {
		    information = "Victory! You gain bounty";
		    if (toLoseTurn != null) {
			toLoseTurn.quest++;
		    }

		} else {
		    ((Enemy) this.enemy).postDefeatAbility();
		    ((Enemy) this.enemy).lives--;
		    if (((Enemy) this.enemy).lives > 0) {
			GameRunner.enemiesOnBoard.add((Enemy) this.enemy);
			((Enemy) this.enemy).setPosition(this.player
				.getPosition());
		    }
		    information = "Victory! You gain "
			    + ((Enemy) this.enemy).getBounty() + " "
			    + ((Enemy) this.enemy).getCombatType() + " bounty";
		}
		GameRunner.playAudio("cheer.wav");
		GameFrame.sequence.state = GameState.WON_COMBAT;
	    } else {
		if (!fightingPlayer) {
		    if (((Enemy) this.enemy).lives <= 0) {
			GameRunner.enemiesOnBoard.add((Enemy) this.enemy);
			((Enemy) this.enemy).setPosition(this.player
				.getPosition());
		    }
		}
		information = "Its a draw!";
		GameFrame.sequence.state = GameState.DRAW_COMBAT;
	    }
	    GameFrame.movement.repaint();
	    JLabel info = new JLabel(information);
	    GameFrame.applyLabelFormat(info);
	    this.fightFrame.add(info);
	    JButton okay = new JButton("Okay");
	    GameFrame.applyButtonFormat(okay);
	    okay.addActionListener(new disposePanel(this.fightFrame));
	    this.fightFrame.add(okay);
	    this.fightFrame.revalidate();
	    // System.out.println("i make it");

	    player.checkAbilities(GameFrame.sequence.state);
	    if (!fightingPlayer) {
		if (((Enemy) this.enemy).getName() == GameRunner
			.getMessage("fireball")) {
		    GameFrame.sequence.state = currentState;
		    // System.out.println("i make it");
		}
	    }

	}

	class disposePanel implements ActionListener {
	    private CombatFrame frame;

	    public disposePanel(CombatFrame frame) {
		this.frame = frame;
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {
		this.frame.removeAll();
		this.frame.revalidate();
		this.frame.setVisible(false);
		GameFrame.enableGamePane();

	    }

	}
    }
}
