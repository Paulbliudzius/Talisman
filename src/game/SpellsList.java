package game;

import java.util.Collections;
import java.util.Stack;

import spells.Brainwave;
import spells.Energize;
import spells.Enrich;
import spells.Fireball;
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
 * @author roccoma. Created Apr 3, 2014.
 */
public class SpellsList {

    private Stack<Spell> spells = new Stack<Spell>();

    public SpellsList() {
	buildList();
	shuffle();
    }

    public int size() {
	return this.spells.size();
    }

    public Spell getSpell() {
	Spell ret = this.spells.pop();
	if (this.spells.empty()) {
	    buildList();
	    shuffle();
	}
	return ret;
    }

    /**
     * TODO Put here a description of what this method does.
     * 
     */
    private void buildList() {
	this.spells.push(new GustOfWind());
	this.spells.push(new Energize());
	this.spells.push(new Healing());
	this.spells.push(new Nullify());
	this.spells.push(new Strength());
	this.spells.push(new Enrich());
	this.spells.push(new Fireball(3));
	this.spells.push(new Fireball(4));
	this.spells.push(new Fireball(5));
	this.spells.push(new PsionicBlast());
	this.spells.push(new PsionicBlast());
	this.spells.push(new Random());
	this.spells.push(new Random());
	this.spells.push(new Brainwave());

    }

    private void shuffle() {
	Collections.shuffle(this.spells);
    }

}
