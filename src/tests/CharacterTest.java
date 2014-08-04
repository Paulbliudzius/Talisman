package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import game.Character;
import game.CharacterDeck;

import java.util.ArrayList;

import javax.swing.GroupLayout.Alignment;

import org.junit.Test;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Mar 27, 2014.
 */
public class CharacterTest {
    private int numberOfClasses = 24;
    private CharacterDeck deck = new CharacterDeck();
    private Character test = new Character("Dark Cultist",
	    Character.Alignment.EVIL, 5, 4, 3, 2, 1, new int[] { 5, 0 });

    @Test
    // testing getters now becuase we will modify them as the game goes on and
    // events occur that may change
    // a players stats (yet to be implented)
    public void testCharacterCreation() {
	assertNotNull(test);
    }

    @Test
    public void testInitialStats() {
	assertEquals(test.getLife(), 5);
	assertEquals(test.getStrength(), 4);
	assertEquals(test.getCraft(), 3);
	assertEquals(test.getFate(), 2);
	assertEquals(test.getGold(), 1);
    }
    @Test
    public void testCharacterAlignment(){
	assertEquals(Character.Alignment.EVIL, test.getAlignment());
	test.setAlignment(Character.Alignment.GOOD);
	assertEquals(Character.Alignment.GOOD, test.getAlignment());
    }
    @Test
    public void testSetCurrentLifeOverload(){
	test.setCurrentLife(6);
	assertEquals(5, test.getLife());
    }
    @Test
    public void testCharacterDeckCreation() {
	CharacterDeck deck = new CharacterDeck();
	assertNotNull(deck);
    }

    @Test
    public void testDeckGetpostionAndTakeTop() {
	assertEquals(deck.getPositionInDeck(0), deck.takeTop());
    }

    @Test
    public void testViewTop() {

	ArrayList<Character> test = new ArrayList<Character>();
	test.add(deck.getPositionInDeck(0));
	test.add(deck.getPositionInDeck(1));
	test.add(deck.getPositionInDeck(2));

	assertEquals(test, deck.viewTop(3));
    }

    @Test
    public void testViewTopAfterShuffle() {
	deck.shuffle();
	ArrayList<Character> test = new ArrayList<Character>();
	test.add(deck.getPositionInDeck(0));
	test.add(deck.getPositionInDeck(1));
	test.add(deck.getPositionInDeck(2));

	assertEquals(test, deck.viewTop(3));
    }
    
    @Test
    public void testAbilityText(){
	assertEquals("<html><div style=\"width:100px;\">When you attack another character you may choose to make the attack physic combat.<br>When you defeat another enemy you get a gift.<br>You Always have an Evil Alignment.<br></div></html>",test.getAbilityString());
    }
}
