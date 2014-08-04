package tests;

import static org.junit.Assert.assertEquals;
import game.Character;
import game.Player;
import game.SpellsList;

import java.io.IOException;

import org.junit.Test;

import spells.Energize;
import spells.GustOfWind;
import spells.Healing;
import spells.Nullify;
import spells.PsionicBlast;
import spells.Random;
import spells.Spell;
import spells.Strength;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Apr 4, 2014.
 */
public class SpellTests {
    /**
     * test the creation and functionallity of the spell deck.
     */
    @Test
    public void testSpellDeck() {
	SpellsList spells = new SpellsList();
	int size = spells.size();
	spells.getSpell();
	assertEquals(size - 1, spells.size());
    }

    /**
     * 
     * @return a test player
     */
    public Player p1() {
	return new Player(new Character("Dev1", Character.Alignment.NEUTRAL, 6,
		6, 6, 6, 6, new int[] { 1, 0 }), "Dev1");
    }

    // to make testing easier, each characters default stat will be 6
    /**
     * Test energize
     * 
     */
    @Test
    public void testEnergize() {
	Player one = p1();
	int initialS = one.character.getStrength();
	Spell energize = new Energize();
	energize.ability(one);
	assertEquals(12, one.character.getStrength());
	energize.endOfDuration();
	energize.generateText();
	assertEquals(initialS, one.character.getStrength());
    }

    /**
     * Test Nullify
     * 
     */
    @Test
    public void testNullify() {
	Player one = p1();
	one.spells.add(new Healing());
	one.spells.add(new Strength());
	Spell nullify = new Nullify();
	nullify.ability(one);
	nullify.generateText();
	assertEquals(0, one.spells.size());
    }

    /**
     * Test the healing spell
     * 
     */
    @Test
    public void testHealing() {
	Player one = p1();
	Spell healing = new Healing();
	one.character.setCurrentLife(1);
	healing.ability(one);
	healing.generateText();
	assertEquals(one.character.getLife(), 6);
    }

    /**
     * Test Strength
     * 
     */
    @Test
    public void testStrength() {
	Player one = p1();
	one.character.getStrength();
	Spell strength = new Strength();
	strength.ability(one);
	assertEquals(7, one.character.getStrength());
	strength.endOfDuration();
	assertEquals(6, one.character.getStrength());
    }

    @Test
    public void testGustOfWind() throws IOException {
	Player one = p1();
	one.roll();
	// new GameFrame();
	// GameFrame.lastRoll = null;

	int roll = one.getLastRoll();
	Spell gustOfWind = new GustOfWind();
	gustOfWind.ability(one);
	gustOfWind.generateText();
	assertEquals(one.getLastRoll(), roll * 2);

    }

    @Test
    public void testRandom() {
	Player one = p1();
	Random rand = new Random();
	rand.seed = 1;
	rand.ability(one);
	assertEquals(one.character.getStrength(), 7);

	rand.seed = 2;
	rand.ability(one);
	assertEquals(one.character.getStrength(), 6);

	rand.seed = 3;
	rand.ability(one);
	assertEquals(one.character.getCraft(), 7);

	rand.seed = 4;
	rand.ability(one);
	assertEquals(one.character.getCraft(), 6);

	rand.seed = 5;
	rand.ability(one);
	assertEquals(one.character.getLife(), 5);

	rand.seed = 6;
	rand.ability(one);
	assertEquals(one.getGold(), 0);
	rand.generateText();
    }

    @Test
    public void testPsionicBlast() {
	Player one = p1();
	PsionicBlast b = new PsionicBlast();
	b.ability(one);
	assertEquals(one.character.getStrength(), 12);
	b.endOfDuration();
	assertEquals(one.character.getStrength(), 6);
	b.generateText();

    }

    @Test
    public void spellText() {
	Spell strength = new Strength();
	String[] text = strength.generateText();
	assertEquals(text[0], "Strength");
	assertEquals(
		text[1],
		"Cast on yourself at the start of your turn. Gain 1 Strength until the end of the turn.");

    }
}
