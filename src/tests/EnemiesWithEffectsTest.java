package tests;

import static org.junit.Assert.assertEquals;
import enemy.Leech;
import enemy.TreasureGoblin;
import game.Character;
import game.Character.Alignment;
import game.Player;

import org.junit.Before;
import org.junit.Test;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 8, 2014.
 */
public class EnemiesWithEffectsTest {
    Player p1;

    @Before
    public void setup() {
	p1 = new Player(new Character("DEV", Alignment.NEUTRAL, 5, 5, 5, 5, 5,
		new int[] { 0, 0 }), "TestName");
    }

    @Test
    public void versusLeech() {
	Leech leech = new Leech();
	p1.playerFightsEnemy(0, leech, 10);
	assertEquals(p1.character.getStrength(), 4);
	p1.armourEquipped = true;
	p1.playerFightsEnemy(0, leech, 10);
	assertEquals(p1.character.getStrength(), 4);

	String desc = leech.generateWinEffect(p1);
	assertEquals(p1.character.getStrength(), 5);

    }

    @Test
    public void versusTreasureGoblin() {
	TreasureGoblin gobby = new TreasureGoblin();
	p1.items.clear();
	p1.playerFightsEnemy(1, gobby, 5);
	p1.playerFightsEnemy(10, gobby, 0);
	assertEquals(p1.character.getGold(), 5);
	assertEquals(p1.items.size(), 1);

    }
}
