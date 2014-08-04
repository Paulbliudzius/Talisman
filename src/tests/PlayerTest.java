package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import enemy.DuskwoodWraith;
import enemy.Enemy;
import enemy.Ogre;
import enemy.Wraith;
import game.Character;
import game.Dice;
import game.Player;
import items.weapons.Axe;

import org.junit.Before;
import org.junit.Test;

/**
 * The hub of test cases for testing of our project.
 */
public class PlayerTest {
    Character test;
    Player player = new Player(new Character("Alchemist",
	    Character.Alignment.NEUTRAL, 4, 2, 4, 1, 1, new int[] { 6, 0 }),
	    "Test");

    @Test
    public void testOfTest() {
	assertEquals(1, 1); // Test that the tests work.
    }

    @Test
    public void diceTest() {
	Dice dice1 = new Dice(1);
	Dice dice2 = new Dice(2);
	assertTrue("Between 1-6", dice1.roll() >= 1 && dice1.roll() <= 6);
	assertTrue("Between 1-6", dice1.roll() >= 1 && dice1.roll() <= 6);
	assertTrue("Between 1-6", dice1.roll() >= 1 && dice1.roll() <= 6);
	assertTrue("Between 1-6", dice1.roll() >= 1 && dice1.roll() <= 6);
	assertTrue("Between 1-6", dice1.roll() >= 1 && dice1.roll() <= 6);
	assertTrue("Between 1-6", dice1.roll() >= 1 && dice1.roll() <= 6);
	assertTrue("Between 1-6", dice1.roll() >= 1 && dice1.roll() <= 6);
	assertTrue("Between 1-6", dice1.roll() >= 1 && dice1.roll() <= 6);
	assertTrue("Between 1-6", dice1.roll() >= 1 && dice1.roll() <= 6);
	assertTrue("Between 1-12", dice2.roll() >= 1 && dice2.roll() <= 12);
	assertTrue("Between 1-12", dice2.roll() >= 1 && dice2.roll() <= 12);
	assertTrue("Between 1-12", dice2.roll() >= 1 && dice2.roll() <= 12);
	assertTrue("Between 1-12", dice2.roll() >= 1 && dice2.roll() <= 12);
	assertTrue("Between 1-12", dice2.roll() >= 1 && dice2.roll() <= 12);
	assertTrue("Between 1-12", dice2.roll() >= 1 && dice2.roll() <= 12);
	assertTrue("Between 1-12", dice2.roll() >= 1 && dice2.roll() <= 12);
	assertTrue("Between 1-12", dice2.roll() >= 1 && dice2.roll() <= 12);
	assertTrue("Between 1-12", dice2.roll() >= 1 && dice2.roll() <= 12);
    }

    @Before
    public void testCharacterCreation() {
	test = new Character("Developer", Character.Alignment.EVIL, 5, 4, 3, 1,
		1, new int[] { 5, 0 });
	assertNotNull(test);
	assertTrue(test.getName().equals("Developer"));
    }

    // @Test
    // public void playerMoveTest() {
    // Player player = new Player(new Character("Alchemist",
    // Character.Alignment.NEUTRAL, 4, 2, 4, 3, 1, new int[] { 6, 0 },
    // null), "Test");
    // assertTrue(player.getPosition()[0] == 6 && player.getPosition()[1] == 0);
    // player.moveCCW();
    // assertTrue(player.getPosition()[0] == 0 && player.getPosition()[1] == 0);
    // player.moveCCW();
    // assertTrue(player.getPosition()[0] == 18
    // && player.getPosition()[1] == 0);
    // player.moveCW();
    // assertTrue(player.getPosition()[0] == 0 && player.getPosition()[1] == 0);
    // }

    @Test
    public void testPlayerLoosesCombat() {
	Player testPlayer = new Player(test, "ERRRMERRGERD");
	Enemy wraith = new Wraith();
	// wraith fights with craft, base 5
	int result = testPlayer.playerFightsEnemy(1, wraith, 5);
	assertEquals(result, -1);
	assertEquals(testPlayer.character.getLife(), 4);
    }

    @Test
    public void testPlayerLosesTurn() {
	player.loseTurns(3);
	assertEquals(3, player.lostTurns());
	player.useTurn();
	assertEquals(2, player.lostTurns());
    }

    @Test
    public void testGetPlayerClass() {
	assertEquals("Alchemist", player.getPlayerClass());
    }

    @Test
    public void testPlayerWinsStrengthCombat() {
	Player testPlayer = new Player(test, "ERRRMERRGERD");
	Enemy ogre = new Ogre();
	// wraith fights with craft, base 5
	int result = testPlayer.playerFightsEnemy(10, ogre, 2);
	assertEquals(result, 1);
	assertEquals(testPlayer.character.getStrengthBounty(), ogre.getBounty());
    }

    @Test
    public void testPlayerWinsCraftCombat() {
	Player testPlayer = new Player(test, "ERRRMERRGERD");
	Enemy duskwood = new DuskwoodWraith();
	// wraith fights with craft, base 5
	int result = testPlayer.playerFightsEnemy(10, duskwood, 2);
	assertEquals(result, 1);
	assertEquals(testPlayer.character.getCraftBounty(),
		duskwood.getBounty());
    }

    @Test
    public void testPlayerDrawsCombat() {
	Player testPlayer = new Player(test, "ERRRMERRGERD");
	Enemy duskwood = new DuskwoodWraith();
	// wraith fights with craft, base 5
	int result = testPlayer.playerFightsEnemy(1, duskwood, 2);
	assertEquals(result, 0);
    }

    @Test
    public void testPlayerBasic() {
	Player testPlayer = new Player(test, "NameTest");
	assertTrue(testPlayer.getName().equals("NameTest"));
	testPlayer.setName("TestTest");
	assertTrue(testPlayer.getName().equals("TestTest"));
    }

    @Test
    public void testPlayerFunctions() {
	Player testPlayer = new Player(test, "NameTest");
	int beforeFate = testPlayer.character.getFate();
	testPlayer.useFate();
	assertEquals(beforeFate - 1, testPlayer.character.getFate());
    }

    @Test
    public void testPlayerGetsSpell() {
	Player testPlayer = new Player(test, "NameTest");
	testPlayer.spells.clear();
	testPlayer.giveSpell();

	assertEquals(1, testPlayer.getSpells().size());
    }

    @Test
    public void fateUseTest() {
	Player testPlayer = new Player(test, "NameTest");
	assertTrue(testPlayer.useFate());
	assertTrue(!testPlayer.useFate());
	testPlayer.addFate(1);
	assertEquals(1, testPlayer.character.getFate());
    }

    @Test
    public void testRemoveAndUnequippedItems() {
	Player dev = new Player(test, "help");
	Axe axe = new Axe();
	dev.items.add(axe);
	axe.equipEffect(dev);
	assertEquals(dev.character.getStrength(), 5);
	dev.removeAndUnequippedItems();
	assertEquals(dev.character.getStrength(), 4);
	assertEquals(dev.items.size(), 0);

    }
}
