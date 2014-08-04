package tests;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import enemy.Enemy;
import enemy.Ogre;
import game.Character;
import game.Character.Alignment;
import game.EncounterSequence;
import game.EncounterSequence.GameState;
import game.EnemyList;
import game.GameFrame;
import game.GameRunner;
import game.Player;
import game.RollAndEffectPanel;
import game.SpellsList;
import items.armour.Helmet;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import actions.Action;
import actions.AlignmentBased;
import actions.BuyItem;
import actions.ChangeAlignment;
import actions.CryptEncounter;
import actions.DeathRoll;
import actions.DiscardItem;
import actions.DrawSpell;
import actions.EncounterSpace;
import actions.FightCharacters;
import actions.GenerateFightDisplay;
import actions.HealForGold;
import actions.HealPayGold;
import actions.KillAll;
import actions.LoseTurn;
import actions.MineEncounter;
import actions.MovePlayerPos;
import actions.MultiAction;
import actions.PLACEHOLDER;
import actions.PortalOfPower;
import actions.RandomMove;
import actions.RecieveQuest;
import actions.Regenerate;
import actions.Rematch;
import actions.RollTwo;
import actions.Safe;
import actions.SetLastMovementRoll;
import actions.StatChange;
import actions.StayNextTurn;

public class ActionTest {

    /*
     * Tests the creation of the map
     */
    @Before
    @Test
    public void changeStat() {
	Player player = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 2,
			4, 3, 1, new int[] { 6, 0 }), "Test");
	Action strength = new StatChange("Strength", 1);
	Action craft = new StatChange("Craft", 1);
	Action life = new StatChange("Life", 1);
	Action maxLife = new StatChange("Max Life", 1);
	Action fate = new StatChange("Fate", 1);
	Action maxFate = new StatChange("Max Fate", 1);
	Action gold = new StatChange("Gold", 1);
	Action negGold = new StatChange("Gold", -1);
	Action doNothingGold = new StatChange("Gold", -20);
	game.Character currentChar = player.character;
	int oldStrength = currentChar.getStrength();
	int oldCraft = currentChar.getCraft();
	int oldMaxLife = currentChar.getMaxLife();
	int oldLife = currentChar.getLife();
	int oldMaxFate = currentChar.getMaxFate();
	int oldFate = currentChar.getFate();
	int oldGold = player.getGold();
	strength.act(player);
	craft.act(player);
	maxLife.act(player);
	life.act(player);
	maxFate.act(player);
	fate.act(player);
	gold.act(player);
	assertEquals("Str", oldStrength + 1, currentChar.getStrength());
	assertEquals("Craft", oldCraft + 1, currentChar.getCraft());
	assertEquals("ML", oldMaxLife + 1, currentChar.getMaxLife());
	assertEquals("L", oldLife + 1, currentChar.getLife());
	assertEquals("MF", oldMaxFate + 1, currentChar.getMaxFate());
	assertEquals("F", oldFate + 1, currentChar.getFate());
	assertEquals("G", oldGold + 1, player.getGold());
	negGold.act(player);
	assertEquals("G", oldGold, player.getGold());
	doNothingGold.act(player);
	assertEquals("G", oldGold, player.getGold());
	fate = new StatChange("Fate", +30);
	fate.act(player);
	assertEquals("FMax", currentChar.getMaxFate(), currentChar.getFate());
	life = new StatChange("Life", -20);
	life.act(player);
	assertEquals("LBottom", 0, currentChar.getLife());
	life = new StatChange("AllLife", 0);
	life.act(player);
	assertEquals("HealFull", currentChar.getMaxLife(),
		currentChar.getLife());
	new StatChange("Life", -2).act(player);
	gold.act(player);
	gold.act(player);
	oldGold = player.getGold();
	new HealPayGold(2).act(player);
	assertEquals("PaidGold", player.getGold(), oldGold - 2);
	assertEquals("PaidHealed", currentChar.getMaxLife(),
		currentChar.getLife());

    }

    @Test
    public void changeAlignment() {
	Player player = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 2,
			4, 3, 1, new int[] { 6, 0 }), "Test");
	new ChangeAlignment(Alignment.GOOD).act(player);
	player.character.getAlignmentString().equals("GOOD");
    }

    @Test
    public void regenerateHealth() {
	Action regenerate = new Regenerate();
	int turn = GameRunner.playerturn;
	Player p = GameRunner.players.get(turn);
	p.character.setCurrentLife(1);
	regenerate.act(p);
	Assert.assertEquals(2, p.character.getLife());
	// Assert.assertNotEquals(turn, GameRunner.playerturn);
    }

    @Test
    public void multiAction() {
	Player player = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 2,
			4, 3, 1, new int[] { 6, 0 }), "Test");
	Action strength = new StatChange("Strength", 1);
	Action multiAction = new MultiAction(3, new Action[] { strength,
		strength, strength });
	game.Character currentChar = player.character;
	int oldStrength = currentChar.getStrength();
	multiAction.act(player);
	assertEquals(oldStrength + 3, currentChar.getStrength());
    }

    @Test
    public void AlignmentBasedAction() {
	Player playerN = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 2,
			4, 3, 1, new int[] { 6, 0 }), "Test");
	Player playerG = new Player(new Character("Alchemist",
		Character.Alignment.GOOD, 4, 2, 4, 3, 1, new int[] { 6, 0 }),
		"Test");
	Player playerE = new Player(new Character("Alchemist",
		Character.Alignment.EVIL, 4, 2, 4, 3, 1, new int[] { 6, 0 }),
		"Test");
	Action alBasedAction = new AlignmentBased(new LoseTurn(1), new Safe(),
		new PLACEHOLDER("Test"));
	alBasedAction.act(playerG);
	alBasedAction.act(playerN);
	alBasedAction.act(playerE);
	assertEquals(playerG.lostTurns(), 1);
    }

    GameFrame frame;
    EncounterSequence sequence;
    Field f;
    Player p;

    @Before
    public void initialize() {
	MockitoAnnotations.initMocks(this);
	try {
	    new GameRunner();
	} catch (IOException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	GameRunner.setLanguage("en", "US", "Englishstrings");
	GameRunner.enemyDeck = new EnemyList();
	GameRunner.spellDeck = new SpellsList();
	GameRunner.addPlayer(new Player(GameRunner.characterDeck
		.getPositionInDeck(0), "TestName"));
	GameRunner.addPlayer(new Player(GameRunner.characterDeck
		.getPositionInDeck(1), "TestName2"));
	try {
	    this.frame = Mockito.mock(GameFrame.class);
	} catch (Exception exception) {
	    // TODO Auto-generated catch-block stub.
	    exception.printStackTrace();
	}
	sequence = new EncounterSequence(frame);
	GameFrame.sequence = sequence;
	GameFrame.select = Mockito.mock(JButton.class);
	GameFrame.NextPlayerButton = Mockito.mock(JButton.class);
	GameFrame.fateButton = Mockito.mock(JButton.class);
	GameFrame.choices = Mockito.mock(JComboBox.class);
	GameFrame.rollButton = Mockito.mock(JButton.class);
	GameFrame.gamePane = Mockito.mock(JPanel.class);
	GameFrame.lastRoll = Mockito.mock(JLabel.class);
	GameFrame.descButton = Mockito.mock(JButton.class);
	try {
	    f = sequence.getClass().getDeclaredField("state");
	} catch (NoSuchFieldException | SecurityException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	f.setAccessible(true);
	p = GameRunner.players.get(0);
    }

    @Test
    public void HealAction() {
	Player player = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 2,
			4, 3, 1, new int[] { 6, 0 }), "Test");
	HealForGold heal = new HealForGold();
	Action[] actions = heal.generateActions(player);
	assertEquals(heal.getChoicesString().length, actions.length);
	assertEquals(actions.length, 1);
	assertTrue(actions[0] instanceof HealPayGold);
	new StatChange("Life", -2).act(player);
	heal = new HealForGold();
	actions = heal.generateActions(player);
	assertEquals(actions.length, 3);
    }

    @Test
    public void PortalTest() {
	new PortalOfPower("Craft").act(new Player(new Character("sl",
		Alignment.EVIL, 4, 4, 4, 4, 4, new int[] { 1, 1 }), "Dev"));
	assertEquals(GameFrame.sequence.actionChoiceSize, 12);
	new PortalOfPower("Strength").act(new Player(new Character("sl",
		Alignment.EVIL, 4, 4, 4, 4, 4, new int[] { 1, 1 }), "Dev"));
	assertEquals(GameFrame.sequence.actionChoiceSize, 12);
	assertThat(GameFrame.sequence.actionChoices[0],
		instanceOf(MovePlayerPos.class));
	assertThat(GameFrame.sequence.actionChoices[11],
		instanceOf(StatChange.class));
    }

    @Test
    public void rollTwoAction() {
	RollTwo roll = new RollTwo();
	assertEquals(2, roll.values().length);
    }

    @Test
    public void setLastMovementAction() {
	SetLastMovementRoll roll = new SetLastMovementRoll(3);
	roll.act(p);
	assertEquals(3, p.getLastRoll());
    }

    @Test
    public void drawSpellAction() {
	p.spells.clear();
	new DrawSpell().act(p);
	assertEquals(1, p.getSpells().size());
    }

    @Test
    public void rematchAction() {
	Rematch r = new Rematch();
	p.character.setCurrentLife(p.character.getMaxLife() - 1);
	GameFrame.sequence.state = GameState.LOST_COMBAT;
	r.act(p);
	assertEquals(p.character.getMaxLife(), p.character.getLife());
    }

    @Test
    public void generateFightDisplayTest() {
	Player player = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 2,
			4, 3, 1, new int[] { 6, 0 }), "Test");
	assertEquals(new GenerateFightDisplay(player).act(),
		"<html> Test<br> Strength  2</html>");
	Enemy enemy = new Ogre();
	assertEquals(new GenerateFightDisplay(enemy).act(),
		"<html> Ogre<br> Type monster strength 5</html>");
    }

    @Test
    public void FightCharactersTest() {
	Player player = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 2,
			4, 3, 1, new int[] { 6, 0 }), "Test");
	Player enemy = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 2,
			4, 3, 1, new int[] { 6, 0 }), "Enemy1");
	Player enemy2 = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 2,
			4, 3, 1, new int[] { 6, 0 }), "Enemy2");
	FightCharacters fighter = new FightCharacters(new Player[] { enemy,
		enemy2 });
	fighter.act(player);
	String[] choices = fighter.getChoicesString();
	Action[] actions = fighter.generateActions(player);
	assertEquals(choices.length, actions.length);
	assertTrue(actions[2] instanceof EncounterSpace);
	assertTrue(choices[2].equals("Don't Fight"));
    }

    @Test
    public void DeathDiceTest() {
	DeathRoll death = new DeathRoll();
	Action[] actions = death.generateActions(3);
	assertTrue(actions[0] instanceof MultiAction);
	assertTrue(actions[2] instanceof StayNextTurn);
	assertTrue(actions[8] instanceof Safe);
    }

    @Test
    public void BuyItemTest() {
	BuyItem buy = new BuyItem(new Helmet(), 2);
	Player player = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 2,
			4, 3, 4, new int[] { 6, 0 }), "Test");
	int remove = player.getItems().size();
	for (int i = 0; i < remove; i++) {
	    player.removeItem(0);
	}
	buy.act(player);
	buy.act(player);
	buy.act(player);
	assertEquals(player.getItems().size(), 2);
	assertEquals(player.getGold(), 1);
    }

    @Test
    public void randomActionMoveTest() {
	Player player = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 2,
			4, 3, 4, new int[] { 0, 0 }), "Test");
	new RandomMove(2).act(player);
	assertEquals(player.getPosition()[1], 2);
    }

    @Test
    public void discardItemTest() {
	BuyItem buy = new BuyItem(new Helmet(), 2);
	Player player = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 2,
			4, 3, 4, new int[] { 6, 0 }), "Test");
	int remove = player.getItems().size();
	for (int i = 0; i < remove; i++) {
	    player.removeItem(0);
	}
	buy.act(player);
	assertEquals(player.getItems().size(), 1);
	new DiscardItem(0).act(player);
	assertEquals(player.getItems().size(), 0);
    }

    @Test
    public void stayNextTurnTest() {
	Player player = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 2,
			4, 3, 4, new int[] { 6, 0 }), "Test");
	new StayNextTurn(2).act(player);
	assertEquals(player.stay(), 2);
	player.stayedTurn(2);
	assertEquals(player.stay(), 0);
	new StayNextTurn(-2).act(player);
	assertEquals(player.stay(), -2);
	new StayNextTurn(-3).act(player);
	assertEquals(player.stay(), -2);
	player.stayedTurn(-1);
	new StayNextTurn(-1).act(player);
	assertEquals(player.stay(), 0);
    }

    @Test
    public void cryptTest() {
	CryptEncounter action = new CryptEncounter();
	Player player = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 1,
			1, 3, 4, new int[] { 7, 2 }), "Test");
	action.move(0, player);
	Assert.assertArrayEquals(player.getPosition(), new int[] { 7, 2 });
	action.move(2, player);
	Assert.assertArrayEquals(player.getPosition(), new int[] { 0, 2 });
	action.move(4, player);
	Assert.assertArrayEquals(player.getPosition(), new int[] { 0, 1 });
	action.move(5, player);
	Assert.assertArrayEquals(player.getPosition(), new int[] { 8, 1 });
	action.move(10, player);
	Assert.assertArrayEquals(player.getPosition(), new int[] { 12, 0 });
    }

    @Test
    public void mineTest() {
	MineEncounter action = new MineEncounter();
	Player player = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 1,
			1, 3, 4, new int[] { 7, 2 }), "Test");
	action.move(0, player);
	Assert.assertArrayEquals(player.getPosition(), new int[] { 7, 2 });
	action.move(2, player);
	Assert.assertArrayEquals(player.getPosition(), new int[] { 0, 2 });
	action.move(4, player);
	Assert.assertArrayEquals(player.getPosition(), new int[] { 0, 1 });
	action.move(5, player);
	Assert.assertArrayEquals(player.getPosition(), new int[] { 8, 1 });
	action.move(10, player);
	Assert.assertArrayEquals(player.getPosition(), new int[] { 18, 0 });
    }

    @Test
    public void crownTest() {
	Player player = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 1,
			1, 3, 4, new int[] { 7, 2 }), "MEEP");
	Player playerd = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 1,
			1, 3, 4, new int[] { 7, 2 }), "HEAP");
	Player playere = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 1,
			1, 3, 4, new int[] { 7, 2 }), "BLEEP");
	ArrayList<Player> list = new ArrayList<Player>();
	list.add(player);
	list.add(playerd);
	list.add(playere);
	new KillAll().kill(list, player);
	Assert.assertEquals(player.character.getLife(), 4);
	Assert.assertEquals(playerd.character.getLife(), 3);
	Assert.assertEquals(playere.character.getLife(), 3);
    }
    
    @Test
    public void receiveQuestTest() {
	Player player = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 2,
			1, 3, 4, new int[] { 7, 2 }), "MEEP");
	player.quest=0;
	GameFrame.rollAndEffectPanel=Mockito.mock(RollAndEffectPanel.class);
	new RecieveQuest().act(player);
	Assert.assertEquals(-1, player.quest);
	assertTrue(player.questComplete);
    }
    
}
