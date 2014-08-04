package game;

import enemy.Brigand;
import enemy.Enemy;
import enemy.Farmer;
import enemy.Pitfiend;
import enemy.Sentinel;
import enemy.Spirit;
import enemy.Werewolf;
import game.Character.Alignment;
import game.EncounterSequence.GameState;
import items.armour.Armour;
import items.armour.Helmet;
import items.armour.Shield;
import items.weapons.Axe;
import items.weapons.Sword;

import java.util.ArrayList;

import actions.Action;
import actions.AlignmentBased;
import actions.BuyItem;
import actions.ChangeAlignment;
import actions.Choice;
import actions.CryptEncounter;
import actions.DeathRoll;
import actions.DrawCard;
import actions.EnterCrownOption;
import actions.FightEnemy;
import actions.HealForGold;
import actions.KillAll;
import actions.LoseTurn;
import actions.MineEncounter;
import actions.MovePlayerPos;
import actions.MultiAction;
import actions.PLACEHOLDER;
import actions.PortalOfPower;
import actions.QuestCheck;
import actions.RandomMove;
import actions.RollDice;
import actions.Safe;
import actions.StatChange;
import actions.StayNextTurn;
import actions.WarlockEncounter;
import adventureCards.DrawEnemy;
import adventureCards.DrawSpell;

/**
 * The map that holds and handles all of the board spaces.
 * 0-----+-----+-----+-----+-----+-----+-----0 | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
 * |-----1-----|-----|-----|-----|-----1-----| | 23 | 0 | 1 | 2 | 3 | 4 | 7 |
 * |-----|-----2-----|-----|-----2-----|-----| | 22 | 15 | 0 | 1 | 2 | 5 | 8 |
 * |-----|-----|-----|-----|-----|-----|-----| | 21 | 14 | 7 | | 3 | 6 | 9 |
 * |-----|-----|-----|-----|-----|-----|-----| | 20 | 13 | 6 | 5 | 4 | 7 | 10 |
 * |-----|-----2-----|-----|-----2-----|-----| | 19 | 12 | 11 | 10 | 9 | 8 | 11
 * | |-----1-----|-----|-----|-----|-----1-----| | 18 | 17 | 16 | 15 | 14 | 13 |
 * 12 | 0-----+-----+-----+-----+-----+-----+-----0
 */

public class BoardMap {
    /*
     * map[Space Position][Region] Region 0 is the outer most layer (largest)
     */
    private BoardSpace[][] map = new BoardSpace[24][3];

    public BoardMap() {
	// For each BoardSpace, there needs to be added a new space.
	// 24+16+8 spaces\
	float xProgression = (GameRunner.WIDTH - 50) / 7.f;
	float yProgression = (GameRunner.HEIGHT - 320) / 7.f;
	float yProgressionRight = (GameRunner.HEIGHT - 200) / 7.f;
	int j = 0;
	int i = 0;
	// //Region 0
	String plains = GameRunner.messages.getString("drawAdventure");
	String woods = GameRunner.messages.getString("drawAdventure");
	String hills = GameRunner.messages.getString("drawAdventure");
	String fields = GameRunner.messages.getString("drawAdventure");
	String forest = GameRunner.messages.getString("forestSpace");
	String tavern = GameRunner.messages.getString("tavernSpace");
	String crags = GameRunner.messages.getString("cragsSpace");
	String sentinel = GameRunner.messages.getString("sentinelSpace");
	String chapel = GameRunner.messages.getString("chapelSpace");
	String village = GameRunner.messages.getString("villageSpace");
	String graveyard = GameRunner.messages.getString("graveyardSpace");
	String ruins = GameRunner.messages.getString("drawTwoAdventure");
	addSpace(
		j,
		i,
		new BoardSpace(
			GameRunner.messages.getString("village"),
			new Choice(
				3,
				new Action[] {
					new Choice(
						6,
						new Action[] {
							new BuyItem(
								new Helmet(), 2),
							new BuyItem(
								new Sword(), 2),
							new BuyItem(new Axe(),
								3),
							new BuyItem(
								new Shield(), 3),
							new BuyItem(
								new Armour(), 4),
							new Safe() },
						new String[] {
							"2G-"
								+ GameRunner.messages
									.getString("helmet"),
							"2G-"
								+ GameRunner.messages
									.getString("sword"),
							"3G-"
								+ GameRunner.messages
									.getString("axe"),
							"3G-"
								+ GameRunner.messages
									.getString("shield"),
							"4G-"
								+ GameRunner.messages
									.getString("armour"),
							GameRunner.messages
								.getString("doNothing") })
						.setStateCheck(GameState.ITEM_SELECTION),
					new HealForGold(),
					new RollDice(6, new Action[] {
						new ChangeAlignment(
							Alignment.EVIL),
						new Safe(),
						new Safe(),
						new ChangeAlignment(
							Alignment.GOOD),
						new StatChange("Craft", 1),
						new DrawSpell() }, village) },
				new String[] {
					GameRunner.messages
						.getString("blacksmith"),
					GameRunner.messages.getString("healer"),
					GameRunner.messages
						.getString("rollDice") }), 80,
			170, village));
	j++;
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("fields"),
		new DrawCard(1), (int) (xProgression * j++) + 40, 170, fields));
	addSpace(j, i,
		new BoardSpace(GameRunner.messages.getString("graveyard"),
			new AlignmentBased(new StatChange("Life", -1),
				new Safe(), new RollDice(6, new Action[] {
					new LoseTurn(1),
					new StatChange("Life", 1),
					new StatChange("Life", 1),
					new StatChange("Life", 1),
					new DrawSpell(), new DrawSpell() },
					graveyard)),
			(int) (xProgression * j++) + 40, 170, graveyard));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("woods"),
		new DrawCard(1), (int) (xProgression * j++) + 40, 170, woods));
	addSpace(
		j,
		i,
		new BoardSpace(GameRunner.messages.getString("sentinel"),
			new Choice(2, new Action[] { new DrawCard(1),
				new DrawEnemy(new Sentinel()) },
				new String[] {
					GameRunner.messages
						.getString("drawCard"),
					GameRunner.messages
						.getString("fightSentinel") }),
			(int) (xProgression * j++) + 40, 170, sentinel));
	addSpace(j, i, new BoardSpace("Hills", new DrawCard(1),
		(int) (xProgression * j++) + 40, 170, hills));
	addSpace(
		j,
		i,
		new BoardSpace(GameRunner.messages.getString("chapel"),
			new AlignmentBased(new Choice(2, new Action[] {
				new StatChange("AllLife", 0),
				new RollDice(6, new Action[] { new Safe(),
					new Safe(), new Safe(), new Safe(),
					new StatChange("Life", 1),
					new DrawSpell() }, chapel) },
				new String[] {
					GameRunner.messages
						.getString("fullHeal"),
					GameRunner.messages
						.getString("rollDice") }),
				new HealForGold(), new StatChange("Life", -1)),
			(int) (xProgression * j++) + 40, 170, chapel));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("fields"),
		new DrawCard(1), MainMenuFrame.WIDTH - 180,
		(int) (yProgressionRight * (j++ - 6)) + 160, fields));

	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("crags"),
		new RollDice(6, new Action[] { new FightEnemy(new Spirit()),
			new LoseTurn(1), new LoseTurn(1), new Safe(),
			new Safe(), new StatChange("Craft", 1) }, crags),
		MainMenuFrame.WIDTH - 180,
		(int) (yProgressionRight * (j++ - 6)) + 160, crags));

	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("plains"),
		new DrawCard(1), MainMenuFrame.WIDTH - 180,
		(int) (yProgressionRight * (j++ - 6)) + 160, plains));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("woods"),
		new DrawCard(1), MainMenuFrame.WIDTH - 180,
		(int) (yProgressionRight * (j++ - 6)) + 160, woods));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("fields"),
		new DrawCard(1), MainMenuFrame.WIDTH - 180,
		(int) (yProgressionRight * (j++ - 6)) + 160, fields));
	addSpace(
		j,
		i,
		new BoardSpace(
			GameRunner.messages.getString("city"),
			new Choice(
				2,
				new Action[] {
					new MultiAction(2, new Action[] {
						new QuestCheck(7),
						new HealForGold() }),
					new RollDice(6, new Action[] {
						new StatChange("Life", +1),
						new StatChange("Strength", -1),
						new StatChange("Craft", -1),
						new StatChange("Craft", 1),
						new StatChange("Strength", 1),
						new DrawSpell() }, GameRunner
						.getMessage("citySpace")) },
				new String[] {
					GameRunner.messages.getString("doctor"),
					GameRunner.messages
						.getString("rollDice") }),
			(int) (xProgression * (18 - j++)) + 40,
			MainMenuFrame.HEIGHT - 150, GameRunner.messages
				.getString("citySpace")));

	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("fields"),
		new DrawCard(1), (int) (xProgression * (18 - j++)) + 40,
		MainMenuFrame.HEIGHT - 180, fields));
	addSpace(j, i, new BoardSpace("Hills", new DrawCard(1),
		(int) (xProgression * (18 - j++)) + 40,
		MainMenuFrame.HEIGHT - 150, hills));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("plains"),
		new DrawCard(1), (int) (xProgression * (18 - j++)) + 40,
		MainMenuFrame.HEIGHT - 150, plains));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("woods"),
		new DrawCard(1), (int) (xProgression * (18 - j++)) + 40,
		MainMenuFrame.HEIGHT - 150, woods));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("plains"),
		new DrawCard(1), (int) (xProgression * (18 - j++)) + 40,
		MainMenuFrame.HEIGHT - 150, plains));
	addSpace(
		j,
		i,
		new BoardSpace(
			GameRunner.messages.getString("tavern"),
			new MultiAction(
				2,
				new Action[] {
					new QuestCheck(9),
					new RollDice(
						6,
						new Action[] {
							new LoseTurn(1),
							new FightEnemy(
								new Farmer()),
							new StatChange("Gold",
								-1),
							new StatChange("Gold",
								1),
							new Choice(
								2,
								new Action[] {
									new RandomMove(
										1),
									new Safe() },
								new String[] {
									GameRunner.messages
										.getString("randomTeleport"),
									GameRunner.messages
										.getString("stay") }),
							new Choice(
								2,
								new Action[] {
									new MovePlayerPos(
										new int[] {
											12,
											1 }),
									new Safe() },
								new String[] {
									GameRunner.messages
										.getString("boatTemple"),
									GameRunner.messages
										.getString("stay") }) },
						tavern) }),
			(int) (xProgression * (18 - j++)) + 40,
			MainMenuFrame.HEIGHT - 150, tavern));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("fields"),
		new DrawCard(1), 80, (int) (yProgression * (25 - j++)) + 180,
		fields));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("ruins"),
		new DrawCard(2), 80, (int) (yProgression * (24 - j++)) + 180,
		ruins));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("plains"),
		new DrawCard(1), 80, (int) (yProgression * (24 - j++)) + 180,
		plains));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("woods"),
		new RollDice(6, new Action[] { new FightEnemy(new Brigand()),
			new LoseTurn(1), new LoseTurn(1), new Safe(),
			new Safe(), new StatChange("Craft", 1) },
			GameRunner.messages.getString("forestSpace")), 80,
		(int) (yProgression * (24 - j++)) + 180, forest));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("fields"),
		new DrawCard(1), 80, (int) (yProgression * (24 - j++)) + 180,
		fields));
	// //Region 1
	j = 0;
	i = 1;
	int[][] spacePos = new int[24][3];
	for (j = 0; j < 16; j++) {
	    if (j < 4) {
		spacePos[j][0] = (int) (xProgression * (1 + j)) + 45;
		spacePos[j][1] = (int) (yProgressionRight) + 170;
	    } else if (j > 3 && j < 9) {
		spacePos[j][0] = MainMenuFrame.WIDTH - 130 - (int) xProgression;
		spacePos[j][1] = (int) (yProgressionRight * (j - 2)) + 50;
	    } else if (j >= 9 && j <= 11) {
		spacePos[j][0] = (int) (xProgression * (13 - j)) + 60;
		spacePos[j][1] = MainMenuFrame.HEIGHT - 180
			- (int) yProgression;
	    } else {
		spacePos[j][0] = (int) xProgression + 10;
		spacePos[j][1] = (int) (yProgression * (18 - j)) + 100;
	    }
	}
	j = 0;
	addSpace(
		j,
		i,
		new BoardSpace(GameRunner.messages.getString("portal"),
			new Choice(3, new Action[] { new DrawCard(1),
				new PortalOfPower("Strength"),
				new PortalOfPower("Craft") }, new String[] {
				GameRunner.messages.getString("drawCard"),
				GameRunner.messages.getString("strengthRoll"),
				GameRunner.messages.getString("craftRoll") }),
			spacePos[j][0], spacePos[j++][1], GameRunner.messages
				.getString("portalSpace")));
	addSpace(
		j,
		i,
		new BoardSpace("Black Knight", new MultiAction(2, new Action[] {
			new QuestCheck(8),
			new Choice(2, new Action[] {
				new StatChange("Life", -1),
				new StatChange("Gold", -1) }, new String[] {
				"Lose 1 Life", "Lose 1 Gold" }) }),
			spacePos[j][0], spacePos[j++][1], GameRunner.messages
				.getString("blackKnightSpace")));
	addSpace(j, i,
		new BoardSpace(GameRunner.messages.getString("hiddenValley"),
			new DrawCard(3), spacePos[j][0], spacePos[j++][1],
			GameRunner.messages.getString("drawThreeAdventure")));
	addSpace(j, i,
		new BoardSpace(GameRunner.messages.getString("cursedGlade"),
			new DrawCard(1), spacePos[j][0], spacePos[j++][1],
			GameRunner.messages.getString("drawAdventure")));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("fields"),
		new DrawCard(1), spacePos[j][0], spacePos[j++][1],
		GameRunner.messages.getString("drawAdventure")));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("runes"),
		new DrawCard(1), spacePos[j][0], spacePos[j++][1],
		GameRunner.messages.getString("drawAdventure")));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("chasm"),
		new DrawCard(2), spacePos[j][0], spacePos[j++][1],
		GameRunner.messages.getString("drawTwoAdventure")));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("hills"),
		new DrawCard(1), spacePos[j][0], spacePos[j++][1],
		GameRunner.messages.getString("drawAdventure")));
	addSpace(
		j,
		i,
		new BoardSpace(GameRunner.messages.getString("warlockCave"),
			new WarlockEncounter(), spacePos[j][0],
			spacePos[j++][1], GameRunner.messages
				.getString("warlockSpace")));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("desert"),
		new MultiAction(2, new Action[] { new StatChange("Life", -1),
			new DrawCard(1) }), spacePos[j][0], spacePos[j++][1],
		GameRunner.messages.getString("desertSpace")));

	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("oasis"),
		new DrawCard(2), spacePos[j][0], spacePos[j++][1],
		GameRunner.messages.getString("drawTwoAdventure")));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("desert"),
		new MultiAction(2, new Action[] { new StatChange("Life", -1),
			new DrawCard(1) }), spacePos[j][0], spacePos[j++][1],
		GameRunner.messages.getString("desertSpace")));
	addSpace(
		j,
		i,
		new BoardSpace(GameRunner.messages.getString("temple"),
			new RollDice(12, new Action[] {
				new StatChange("Life", -2),
				new StatChange("Life", -1),
				new StatChange("Gold", -1),
				new LoseTurn(1),
				new StatChange("Strength", 1),
				new StatChange("Craft", 1),
				new DrawSpell(),
				new PLACEHOLDER("New Talisman"),
				new StatChange("Fate", 2),
				new MultiAction(2, new Action[] {
					new QuestCheck(10),
					new StatChange("Life", 2) }),
				new DrawSpell(), new DrawSpell() },
				GameRunner.messages.getString("templeSpace")),
			spacePos[j][0], spacePos[j++][1], GameRunner.messages
				.getString("templeSpace")));
	// addSpace(j, i, new
	// BoardSpace(GameRunner.messages.getString("desert"),
	// new MultiAction(2, new Action[] { new StatChange("Life", -1),
	// new DrawCard(1) }), spacePos[j][0], spacePos[j++][1],
	// GameRunner.messages.getString("desertSpace")));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("woods"),
		new DrawCard(1), spacePos[j][0], spacePos[j++][1],
		GameRunner.messages.getString("drawAdventure")));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("runes"),
		new DrawCard(1), spacePos[j][0], spacePos[j++][1],
		GameRunner.messages.getString("drawAdventure")));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("castle"),
		new HealForGold(), spacePos[j][0], spacePos[j++][1],
		GameRunner.messages.getString("healForGold")));

	// //Region 2
	i = 2;
	spacePos = new int[24][3];
	for (j = 0; j < 9; j++) {
	    if (j == 8) {
		spacePos[j][0] = (int) (xProgression * (3)) + 60;
		spacePos[j][1] = (int) (yProgression * (4)) + 110;
	    } else if (j < 2) {
		spacePos[j][0] = (int) (xProgression * (2 + j)) + 60;
		spacePos[j][1] = (int) (yProgressionRight * 2) + 170;
	    } else if (j >= 2 && j < 5) {
		spacePos[j][0] = MainMenuFrame.WIDTH - 40 - (int) xProgression
			* 2;
		spacePos[j][1] = (int) (yProgressionRight * j) + 160;
	    } else if (j == 5) {
		spacePos[j][0] = (int) (xProgression * (8 - j)) + 60;
		spacePos[j][1] = (int) (yProgressionRight * (j - 1)) + 140;
	    } else {
		spacePos[j][0] = (int) xProgression * 2 - 40;
		spacePos[j][1] = (int) (yProgression * (11 - j)) + 110;
	    }
	}
	j = 0;
	addSpace(j, i,
		new BoardSpace(GameRunner.messages.getString("plainPeril"),
			new Safe(), spacePos[j][0], spacePos[j++][1],
			GameRunner.messages.getString("perilSpace")));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("mines"),
		new MineEncounter(), spacePos[j][0], spacePos[j++][1],
		GameRunner.messages.getString("mineSpace")));
	addSpace(
		j,
		i,
		new BoardSpace(GameRunner.messages.getString("vampiretower"),
			new RollDice(6, new Action[] {
				new StatChange("Life", -1),
				new StatChange("Life", -1),
				new StatChange("Life", -2),
				new StatChange("Life", -2),
				new StatChange("Life", -3),
				new StatChange("Life", -3) },
				GameRunner.messages.getString("vampSpace")),
			spacePos[j][0], spacePos[j++][1], GameRunner.messages
				.getString("vampSpace")));
	addSpace(
		j,
		i,
		new BoardSpace(GameRunner.messages.getString("pits"),
			new MultiAction(2, new Action[] {
				new StayNextTurn(-new Dice(1).roll()),
				new FightEnemy(new Pitfiend()) }),
			spacePos[j][0], spacePos[j++][1], GameRunner.messages
				.getString("pitSpace")));
	addSpace(
		j,
		i,
		new BoardSpace(GameRunner.messages.getString("valleyoffire"),
			new EnterCrownOption(), spacePos[j][0],
			spacePos[j++][1], GameRunner.messages
				.getString("valleySpace")));
	addSpace(
		j,
		i,
		new BoardSpace(GameRunner.messages.getString("werewolf"),
			new MultiAction(2, new Action[] { new StayNextTurn(1),
				new FightEnemy(new Werewolf()) }),
			spacePos[j][0], spacePos[j++][1], GameRunner.messages
				.getString("werewolfSpace")));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("death"),
		new DeathRoll(), spacePos[j][0], spacePos[j++][1],
		GameRunner.messages.getString("deathSpace")));
	addSpace(j, i, new BoardSpace(GameRunner.messages.getString("crypt"),
		new CryptEncounter(), spacePos[j][0], spacePos[j++][1],
		GameRunner.messages.getString("cryptSpace")));
	addSpace(
		j,
		i,
		new BoardSpace("Crown of Command", new KillAll(),
			spacePos[j][0], spacePos[j++][1], GameRunner.messages
				.getString("CoC")));

    }

    public BoardSpace getSpace(int[] pos) {
	return this.map[pos[0]][pos[1]];
    }

    public boolean containsCharacter(int[] pos) {
	if (characterContents(pos).size() == 0) {
	    return false;
	}
	return true;
    }

    public boolean enemyOnSpace(int[] pos) {
	ArrayList<Enemy> enimies = GameRunner.enemiesOnBoard;
	for (int i = 0; i < enimies.size(); i++) {
	    if (pos == enimies.get(i).getPosition()) {
		return true;
	    }
	}
	return false;
    }

    public Enemy getEnemyOnSpace(int[] pos) {
	ArrayList<Enemy> enimies = GameRunner.enemiesOnBoard;
	for (int i = 0; i < enimies.size(); i++) {
	    if (pos == enimies.get(i).getPosition()) {
		return GameRunner.enemiesOnBoard.get(i);
	    }
	}
	return null;
    }

    public ArrayList<Player> characterContents(int[] pos) {
	ArrayList<Player> players = new ArrayList<Player>();
	for (int i = 0; i < GameRunner.players.size(); i++) {
	    if (GameRunner.players.get(i).getPosition()[0] == pos[0]
		    && GameRunner.players.get(i).getPosition()[1] == pos[1]
		    && i != GameRunner.playerturn) {
		players.add(GameRunner.players.get(i));
	    }
	}
	return players;
    }

    public int[] getLocation(int[] pos) {
	return getSpace(pos).getLocation();
    }

    private void addSpace(int space, int region, BoardSpace bSpace) {
	this.map[space][region] = bSpace;
    }

}
