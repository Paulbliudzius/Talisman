package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import enemy.Ogre;
import enemy.Wraith;
import game.Character;
import game.CombatFrame;
import game.Player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CombatTest {
    CombatFrame frame;
    Character test, test1;
    Player testPlayer, testPlayer1;

    @Before
    public void setup() {
	frame = new CombatFrame();
	test = new Character("Developer", Character.Alignment.EVIL, 6, 6, 6, 6,
		6, new int[] { 5, 0 });
	test1 = new Character("Developer2", Character.Alignment.EVIL, 6, 6, 6,
		6, 6, new int[] { 5, 0 });
	testPlayer = new Player(test, "ERRRMERRGERD");
	testPlayer1 = new Player(test1, "ERRRMERRGERD");
    }

    @Test
    public void combatFrameInitializes() {
	Assert.assertNotNull(frame);
    }

    @Test
    public void battleCallPlayer() {
	assertTrue(frame.new battle(testPlayer, testPlayer1, 1, frame).fightingPlayer);
    }

    @Test
    public void battleCallEnemy() {
	assertTrue(!frame.new battle(testPlayer, new Ogre(), 1, frame).fightingPlayer);
    }

    @Test
    public void testPlayerWinsPVPStrengthCombat() {
	// wraith fights with craft, base 5
	int result = testPlayer.playerFightsPlayer(10, testPlayer1, 2,false);
	assertEquals(result, 1);
    }
    @Test
    public void testPlayerWinsPVPCraftCombat() {
	// wraith fights with craft, base 5
	int result = testPlayer.playerFightsPlayer(10, testPlayer1, 2,true);
	assertEquals(result, 1);
    }
    @Test
    public void testGenerateOpponentStrengthText() {
	String[] test = frame.generateOpponentText(new Ogre(), 2);
	assertEquals(test[0], "Ogre strength: 5 CombatBonus : 2");
	assertEquals(test[2], "7");
    }

    @Test
    public void testGenerateOpponentCraftText() {
	String[] test = frame.generateOpponentText(new Wraith(), 2);
	assertEquals(test[0], "wraith craft: 5 CombatBonus : 2");
	assertEquals(test[2], "7");
    }

    @Test
    public void testGenerateOpponentPlayerText() {
	String[] test = frame.generateOpponentText(this.testPlayer, 2);
	assertEquals(test[0], "ERRRMERRGERD: 6 CombatBonus : 2");
    }

    @Test
    public void testGeneratePlayerStrengthText() {
	String[] test = frame.generatePlayerText(this.testPlayer, "Strength");
	assertEquals(test[0], "ERRRMERRGERD base Strength: 6");
    }

    @Test
    public void testGeneratePlayerCraftText() {

	String[] test = frame.generatePlayerText(this.testPlayer, "Craft");
	assertEquals(test[0], "ERRRMERRGERD base Craft: 6");
    }

    @Test
    public void testGenerateFullLabelsStrength() {
	String[] test = frame.generateLabels(this.testPlayer, new Ogre(), 3);
	assertEquals(test[0], "Ogre strength: 5 CombatBonus : 3");
	assertEquals(test[1], "ERRRMERRGERD base Strength: 6");
	assertEquals(test[2], "Roll at least a 3 to win");

    }

    @Test
    public void testGenerateFullLabelsCraft() {
	String[] test = frame.generateLabels(this.testPlayer, new Wraith(), 3);
	assertEquals(test[0], "wraith craft: 5 CombatBonus : 3");
	assertEquals(test[1], "ERRRMERRGERD base Craft: 6");
	assertEquals(test[2], "Roll at least a 3 to win");

    }

    @Test
    public void testGenerateFullLabelsCraftFlawlessVic() {
	String[] test = frame.generateLabels(this.testPlayer, new Wraith(), 0);
	assertEquals(test[0], "wraith craft: 5 CombatBonus : 0");
	assertEquals(test[1], "ERRRMERRGERD base Craft: 6");
	assertEquals(test[2], "Deliver your killing blow!");

    }

    @Test
    public void testGenerateFullLabelsCraftCannotWin() {
	String[] test = frame.generateLabels(this.testPlayer, new Wraith(), 10);
	assertEquals(test[0], "wraith craft: 5 CombatBonus : 10");
	assertEquals(test[1], "ERRRMERRGERD base Craft: 6");
	assertEquals(test[2], "Foolish Traveler, you cannot win!");

    }
}
