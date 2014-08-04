package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Outline for all the cards in the deck.
 */
public class CharacterDeck {
    private List<Character> cards = new ArrayList<Character>();

    public CharacterDeck() {
	/*
	 * All positions need set
	 */
	this.cards.add(new Character("Thief", Character.Alignment.NEUTRAL, 4,
		3, 3, 2, 1, new int[] { 20, 0 }));
	this.cards.add(new Character("Warrior", Character.Alignment.NEUTRAL, 5,
		4, 2, 1, 1, new int[] { 18, 0 }));
	this.cards.add(new Character("Alchemist", Character.Alignment.NEUTRAL,
		4, 2, 4, 3, 5, new int[] { 16, 0 }));
	this.cards.add(new Character("Amazon", Character.Alignment.NEUTRAL, 4,
		3, 3, 3, 1, new int[] { 14, 0 }));
	this.cards.add(new Character("Bounty Hunter", Character.Alignment.EVIL,
		4, 5, 2, 2, 1, new int[] { 1, 0 }));
	this.cards
		.add(new Character("Cat Burglar", Character.Alignment.NEUTRAL,
			4, 2, 5, 3, 1, new int[] { 2, 0 }));
	this.cards.add(new Character("Chivalric Knight",
		Character.Alignment.GOOD, 4, 3, 3, 2, 1, new int[] { 3, 0 }));
	this.cards.add(new Character("Conjurer", Character.Alignment.GOOD, 4,
		2, 4, 1, 1, new int[] { 22, 0 }));
	this.cards.add(new Character("Dark Cultist", Character.Alignment.EVIL,
		4, 3, 3, 1, 1, new int[] { 5, 0 }));
	// RealCards below
	this.cards.add(new Character("Assassin", Character.Alignment.EVIL, 4,
		3, 3, 3, 1, new int[] { 13, 0 }));
	this.cards.add(new Character("Druid", Character.Alignment.NEUTRAL, 4,
		2, 4, 4, 1, new int[] { 11, 0 }));
	this.cards.add(new Character("Dwarf", Character.Alignment.NEUTRAL, 5,
		3, 3, 5, 1, new int[] { 9, 0 }));
	this.cards.add(new Character("Elf", Character.Alignment.GOOD, 4, 3, 4,
		3, 1, new int[] { 14, 0 }));
	this.cards.add(new Character("Ghoul", Character.Alignment.EVIL, 4, 2,
		4, 4, 1, new int[] { 23, 0 }));
	this.cards.add(new Character("Minstrel", Character.Alignment.GOOD, 4,
		2, 4, 5, 1, new int[] { 12, 0 }));
	this.cards.add(new Character("Monk", Character.Alignment.GOOD, 4, 2, 3,
		5, 1, new int[] { 11, 0 }));
	this.cards.add(new Character("Priest", Character.Alignment.GOOD, 4, 2,
		4, 5, 1, new int[] { 10, 0 }));
	this.cards.add(new Character("Prophetess", Character.Alignment.GOOD, 4,
		2, 4, 2, 1, new int[] { 9, 0 }));
	this.cards.add(new Character("Sorceress", Character.Alignment.EVIL, 4,
		2, 4, 3, 1, new int[] { 0, 0 }));
	this.cards.add(new Character("Troll", Character.Alignment.NEUTRAL, 6,
		6, 1, 1, 1, new int[] { 7, 0 }));
	this.cards.add(new Character("Wizard", Character.Alignment.EVIL, 4, 2,
		5, 3, 1, new int[] { 6, 0 }));
    }

    public void shuffle() {
	Collections.shuffle(this.cards);
    }

    public Character takeTop() {
	Character ret = this.cards.get(0);
	this.cards.remove(0);
	return ret;
    }

    public List<Character> viewTop(int n) {
	if (n == 0) {
	    return null;
	}
	List<Character> ret = new ArrayList<Character>();
	for (int i = 0; i < n; i++) {
	    ret.add(this.cards.get(i));
	}
	return ret;
    }

    /**
     * Gets a position in the deck.
     * 
     * @param pos
     * @return
     */
    public Character getPositionInDeck(int pos) {
	return this.cards.get(pos);
    }

    public List<Character> viewAll() {
	int n = this.cards.size();
	List<Character> ret = new ArrayList<Character>();
	for (int i = 0; i < n; i++) {
	    ret.add(this.cards.get(i));
	}
	return ret;
    }
}
