package tests;

import static org.junit.Assert.assertEquals;
import game.Character;
import game.Character.Alignment;
import game.Player;
import items.Item;
import items.weapons.Axe;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import adventureCards.BlackHole;
import adventureCards.Devil;
import adventureCards.FoolsGold;
import adventureCards.Mephisotpheles;
import adventureCards.Pestilence;
import adventureCards.RandomItem;
import adventureCards.Talisman;
import adventureCards.bagOfGold;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 1, 2014.
 */
public class adventureCardTest {
    Player playerS = new Player(new Character("Alchemist",
	    Character.Alignment.NEUTRAL, 4, 4, 4, 4, 4, new int[] { 6, 3 }),
	    "Dev");
    Player playerN = new Player(new Character("Alchemist",
	    Character.Alignment.NEUTRAL, 4, 4, 4, 4, 4, new int[] { 6, 0 }),
	    "Dev");

    @Test
    public void testDevil() {
	Player playerG = new Player(new Character("Alchemist",
		Character.Alignment.GOOD, 4, 4, 4, 4, 4, new int[] { 6, 0 }),
		"Dev");
	Player playerE = new Player(new Character("sl", Alignment.EVIL, 4, 4,
		4, 4, 4, new int[] { 6, 0 }), "Dev");

	Devil devil = new Devil();
	devil.effect(playerG);
	assertEquals(playerG.character.getLife(), 3);
	devil.effect(playerN);
	assertEquals(playerN.character.getLife(), 4);
	devil.effect(playerE);
	assertEquals(4, playerE.character.getLife());
	playerE.character.setCurrentLife(3);
	devil.effect(playerE);
	assertEquals(4, playerE.character.getLife());

    }

    @Test
    public void testBagOfGold() {

	bagOfGold single = new bagOfGold(1);
	bagOfGold dub = new bagOfGold(2);

	single.effect(playerN);
	Assert.assertEquals(playerN.getGold(), 6);
	dub.effect(playerN);
	Assert.assertEquals(playerN.getGold(), 8);
    }

    @Test
    public void testFoolsGold() {

	FoolsGold fool = new FoolsGold();

	fool.effect(playerN);
	Assert.assertEquals(playerN.getGold(), 0);

    }

    @Test
    public void testMephisotpheles() {
	Player playerG = new Player(new Character("Alchemist",
		Character.Alignment.GOOD, 4, 4, 4, 4, 4, new int[] { 6, 0 }),
		"Dev");
	Player playerE = new Player(new Character("sl", Alignment.EVIL, 4, 4,
		4, 4, 4, new int[] { 6, 0 }), "Dev");
	Mephisotpheles mes = new Mephisotpheles();
	mes.effect(playerG);
	assertEquals(playerG.character.getAlignment(), Alignment.EVIL);
	mes.effect(playerN);
	assertEquals(playerN.character.getAlignment(), Alignment.EVIL);
	mes.effect(playerE);
	assertEquals(playerE.character.getCraft(), 5);
    }

    @Test
    public void testPestilence() {
	Player playerS2 = new Player(new Character("Alchemist",
		Character.Alignment.GOOD, 4, 4, 4, 4, 4, new int[] { 6, 3 }),
		"Dev");
	Player playerD = new Player(new Character("sl", Alignment.EVIL, 4, 4,
		4, 4, 4, new int[] { 1, 1 }), "Dev");
	ArrayList<Player> players = new ArrayList<Player>();
	players.add(playerS);
	players.add(playerS2);
	players.add(playerD);
	Pestilence pest = new Pestilence();
	pest.effect(players, 3);
	assertEquals(playerS.character.getLife(), 3);
	assertEquals(playerS2.character.getLife(), 3);
	assertEquals(playerD.character.getLife(), 4);
    }

    @Test
    public void testRandomItem() {
	RandomItem action = new RandomItem();
	playerS.items.clear();
	Item axe = new Axe();
	action.effect(playerS, axe);
	assertEquals(playerS.items.size(), 1);
	action.effect(playerS, axe);
	action.effect(playerS, axe);
	action.effect(playerS, axe);
	action.effect(playerS, axe);

	assertEquals(playerS.items.size(), playerS.inventoryCap);
    }

    @Test
    public void blackHoleTest() {
	BlackHole act = new BlackHole();
	Axe axe = new Axe();
	playerS.items.add(axe);
	axe.equipEffect(playerS);
	act.effect(playerS);
	assertEquals(4, playerS.character.getStrength());
	assertEquals(0, playerS.items.size());
	playerS.character.setStrength(10);
	playerS.items.add(axe);
	axe.equipEffect(playerS);
	act.effect(playerS);
	assertEquals(11, playerS.character.getStrength());
	assertEquals(1, playerS.items.size());

    }

    @Test
    public void talismanTest() {
	Player player = new Player(new Character("sl", Alignment.EVIL, 4, 4, 4,
		4, 4, new int[] { 1, 1 }), "Dev");
	Assert.assertEquals(false, player.talisman);
	new Talisman().act(player);
	Assert.assertTrue(player.talisman);
    }
    // @Test
    // public void testInstructor() {
    // Player playerS = new Player(new Character("Alchemist",
    // Character.Alignment.NEUTRAL, 4, 4, 4, 4, 10,
    // new int[] { 6, 3 }), "Dev");
    // Instructor instructor = new Instructor();
    // instructor.effect(playerS, "craft");
    // Assert.assertEquals(5, playerS.character.getCraft());
    // Assert.assertEquals(7, playerS.getGold());
    // instructor.effect(playerS, "strength");
    // Assert.assertEquals(5, playerS.character.getStrength());
    // Assert.assertEquals(4, playerS.getGold());
    //
    // }

}
