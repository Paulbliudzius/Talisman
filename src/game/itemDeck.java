package game;

import items.Item;
import items.armour.Armour;
import items.armour.Helmet;
import items.armour.Platemail;
import items.armour.Shield;
import items.magic.Doomsword;
import items.magic.Doughnut;
import items.magic.HealingPotion;
import items.magic.HolyLance;
import items.magic.MagicRing;
import items.magic.PotionOfStrength;
import items.magic.SolomonsCrown;
import items.weapons.Axe;
import items.weapons.Runesword;
import items.weapons.Sword;

import java.util.Collections;
import java.util.Stack;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 8, 2014.
 */
public class itemDeck {

    private Stack<Item> items = new Stack<Item>();

    public itemDeck() {
	buildList();
	shuffle();
    }

    public int size() {
	return this.items.size();
    }

    public Item getItem() {
	Item ret = this.items.pop();
	if (this.items.empty()) {
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
	for (int i = 0; i < 2; i++) {
	    this.items.push(new Axe());
	    this.items.push(new Sword());
	    this.items.push(new Helmet());
	    this.items.push(new Shield());
	    this.items.push(new Armour());
	}
	this.items.push(new MagicRing());
	this.items.push(new Runesword());
	this.items.push(new Platemail());
	this.items.push(new HolyLance());
	this.items.push(new Doomsword());
	for (int i = 0; i < 2; i++) {
	    this.items.push(new PotionOfStrength());
	}
	this.items.push(new SolomonsCrown());
	for (int i = 0; i < 3; i++) {
	    this.items.push(new HealingPotion());
	}
	for (int i = 0; i < 3; i++) {
	    this.items.push(new Doughnut());
	}

    }

    private void shuffle() {
	Collections.shuffle(this.items);
    }

}
