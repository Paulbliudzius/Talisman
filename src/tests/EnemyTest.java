package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import enemy.Brigand;
import enemy.Doppleganger;
import enemy.Enemy;
import enemy.Ghast;
import enemy.Pitfiend;
import enemy.Shadow;
import enemy.Werewolf;
import enemy.generateGenericEnemy;
import game.Character;
import game.GameRunner;
import game.Player;

public class EnemyTest {
    private Werewolf werewolf;
    private Brigand brigand;
    private Character test;
    private Character test1;
    private Player testPlayer;
    private Player testPlayer1;

    @Before
    public void setup() {
	werewolf = new Werewolf();
	brigand = new Brigand();
	test = new Character("Developer", Character.Alignment.EVIL, 6, 6, 6, 6,
		6, new int[] { 5, 0 });
	test1 = new Character("Developer2", Character.Alignment.EVIL, 6, 6, 6,
		6, 6, new int[] { 5, 0 });
	testPlayer = new Player(test, "ERRRMERRGERD");
	testPlayer1 = new Player(test1, "ERRRMERRGERD");
    }

    @Test
    public void werewolfTest() {
	Assert.assertEquals("Werewolf", werewolf.getName());
	Assert.assertEquals(
		"If you lose you must fight the Werewolf again and you lose a life.",
		werewolf.getDescription());
	Assert.assertTrue(werewolf.power() >= 1 && werewolf.power() <= 12);
	Assert.assertEquals(0, werewolf.getBounty());
	Assert.assertEquals("strength", werewolf.getCombatType());
    }

    @Test
    public void brigandTest() {
	Assert.assertEquals("Brigand", brigand.getName());
	Assert.assertEquals("The Brigand", brigand.getDescription());
	Assert.assertEquals(4, brigand.getBounty());
	Assert.assertEquals(4, brigand.power());
	Assert.assertEquals("strength", brigand.getCombatType());
    }

    @Test
    public void testGenericEnemy() {
	Enemy bronzeDragon = new generateGenericEnemy("Bronze Dragon",
		"A mighty dragon lairs in this area", "dragon", "strength", 8,
		2);
	Assert.assertEquals(bronzeDragon.getName(), "Bronze Dragon");
	Assert.assertEquals(bronzeDragon.getDescription(),
		"A mighty dragon lairs in this area");
	Assert.assertEquals(bronzeDragon.getCombatType(),
		GameRunner.getMessage("strength"));
	Assert.assertEquals(bronzeDragon.getType(),
		GameRunner.getMessage("dragon"));

    }

    @Test
    public void testDoppleganger() {
	Doppleganger dopy = new Doppleganger();
	int result = testPlayer.playerFightsEnemy(1, dopy, 2);
	Assert.assertEquals(result, -1);
    }

    @Test
    public void testShadow() {
	Shadow boo = new Shadow();
	testPlayer.playerFightsEnemy(0, boo, 5);
	assertEquals(5, testPlayer.character.getCraft());
	assertEquals(6, testPlayer.character.getLife());

    }

    @Test
    public void testGhast() {
	Ghast boo = new Ghast();
	testPlayer.playerFightsEnemy(0, boo, 5);
	assertEquals(5, testPlayer.character.getStrength());
	assertEquals(5, testPlayer.character.getLife());

    }
    
    @Test
    public void testPitfiend() {
	Pitfiend fiend = new Pitfiend();
	assertEquals("Pitfiend", fiend.getName());
	assertEquals("Pitfiend", fiend.getType());
	assertEquals("strength", fiend.getCombatType());
	assertEquals("Defeat them all.", fiend.getDescription());
	assertEquals(4, fiend.power());
    }
    
}
