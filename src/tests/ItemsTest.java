package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import enemy.Enemy;
import enemy.Ogre;
import enemy.Wraith;
import enemy.generateGenericEnemy;
import game.Character;
import game.Character.Alignment;
import game.GameRunner;
import game.Player;
import game.itemDeck;
import items.Item;
import items.armour.Armour;
import items.armour.Helmet;
import items.armour.Platemail;
import items.armour.Shield;
import items.generic.BagOfGold;
import items.magic.Doomsword;
import items.magic.HealingPotion;
import items.magic.HolyLance;
import items.magic.MagicRing;
import items.magic.PotionOfStrength;
import items.magic.SkullWand;
import items.magic.SolomonsCrown;
import items.weapons.Axe;
import items.weapons.GoblinSword;
import items.weapons.Runesword;
import items.weapons.Sword;

import org.junit.Before;
import org.junit.Test;

import adventureCards.RandomItem;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 2, 2014.
 */
public class ItemsTest {
    private Player playerN;
    private RandomItem rand;

    @Before
    public void setup() {
	playerN = new Player(
		new Character("Alchemist", Character.Alignment.NEUTRAL, 4, 4,
			4, 4, 1, new int[] { 6, 0 }), "Dev");
	GameRunner.players.clear();
	GameRunner.addPlayer(playerN);
	GameRunner.playerturn = 0;
	this.rand = new RandomItem();
    }

    @Test
    public void testDeck() {
	itemDeck items = new itemDeck();
	int size = items.size();
	items.getItem();
	assertEquals(size - 1, items.size());
	for (int i = 0; i < 50; i++) {
	    items.getItem();
	}
    }

    @Test
    public void testAxe() {
	Item axe = new Axe();
	axe.equipEffect(playerN);
	assertEquals(playerN.weaponEquipped, true);
	assertEquals(playerN.character.getStrength(), 5);
	assertTrue(axe.equipped());
	axe.unequipEffect(playerN);
	assertEquals(playerN.weaponEquipped, false);
	assertEquals(playerN.character.getStrength(), 4);

	this.rand.effect(playerN, axe);
	this.playerN.inventoryCap = 0;
	this.rand.effect(playerN, axe);
    }

    @Test
    public void testSword() {
	Item sword = new Sword();
	sword.equipEffect(playerN);
	assertEquals(playerN.weaponEquipped, true);
	assertEquals(playerN.character.getStrength(), 5);
	assertTrue(sword.equipped());
	sword.unequipEffect(playerN);
	assertEquals(playerN.weaponEquipped, false);
	assertEquals(playerN.character.getStrength(), 4);
	this.rand.effect(playerN, sword);

    }

    @Test
    public void testMagicRing() {
	Item ring = new MagicRing();
	ring.equipEffect(playerN);
	assertTrue(ring.equipped());
	assertEquals(playerN.character.getStrength(), 5);
	assertEquals(playerN.character.getCraft(), 5);
	ring.unequipEffect(playerN);
	assertEquals(playerN.character.getStrength(), 4);
	assertEquals(playerN.character.getCraft(), 4);
	this.rand.effect(playerN, ring);

    }

    @Test
    public void testBagOfGold() {
	Item goldSack = new BagOfGold();
	assertEquals("Exchange immediately for 1 gold and then"
		+ "place this card on the discard pile.",
		goldSack.getItemDescription());
	goldSack.effect();
	assertEquals(6, playerN.getGold());
    }

    @Test
    public void testGoblinSword() {
	GoblinSword sword = new GoblinSword();
	sword.equipEffect(playerN);
	assertEquals(playerN.character.getStrength(), 8);
	assertEquals(playerN.character.getCraft(), 5);
	sword.unequipEffect(playerN);
	assertEquals(playerN.character.getLife(), 2);
	sword.equipEffect(playerN);
	sword.unequipEffect(playerN);
	assertEquals(playerN.character.getLife(), 1);
	sword.equipEffect(playerN);
	assertTrue(sword.equipped);
	sword.unequipEffect(playerN);
	assertEquals(playerN.character.getLife(), 1);
	this.rand.effect(playerN, sword);

    }

    @Test
    public void testRunesword() {
	playerN.character.setAlignment(Alignment.GOOD);
	playerN.items.clear();
	Runesword sword = new Runesword();
	playerN.items.add(sword);
	sword.equipEffect(playerN);
	assertEquals(playerN.items.size(), 0);
	playerN.character.setAlignment(Alignment.EVIL);
	playerN.items.add(sword);
	sword.equipEffect(playerN);
	assertTrue(sword.equipped);
	assertEquals(5, playerN.character.getStrength());
	playerN.playerFightsEnemy(4, new Ogre(), 0);
	assertEquals(4, playerN.character.getLife());
	playerN.playerFightsEnemy(0, new Wraith(), 1);
	assertEquals(3, playerN.character.getLife());
	sword.unequipEffect(playerN);
	assertEquals(playerN.character.getStrength(), 4);
    }

    @Test
    public void testSkullWand() {
	playerN.character.setAlignment(Alignment.GOOD);
	playerN.items.clear();
	SkullWand sword = new SkullWand();
	playerN.items.add(sword);
	sword.equipEffect(playerN);
	assertEquals(playerN.items.size(), 0);
	playerN.character.setAlignment(Alignment.EVIL);
	playerN.items.add(sword);
	sword.equipEffect(playerN);
	assertTrue(sword.equipped);
	assertEquals(5, playerN.character.getStrength());
	sword.unequipEffect(playerN);
	assertEquals(playerN.character.getStrength(), 4);
    }

    @Test
    public void testHolyLance() {
	playerN.character.setAlignment(Alignment.EVIL);
	playerN.items.clear();
	HolyLance sword = new HolyLance();
	playerN.items.add(sword);
	sword.equipEffect(playerN);
	assertEquals(playerN.items.size(), 0);
	playerN.character.setAlignment(Alignment.GOOD);
	playerN.items.add(sword);
	sword.equipEffect(playerN);
	Enemy dragon = new generateGenericEnemy("Dragon",
		"A Dragon is terrorizing this area", "dragon", "strength", 7, 2);
	sword.preBattleEffect(playerN, 3, dragon, 3);
	assertEquals(playerN.character.getStrength(), 7);
	sword.postBattleEffect(playerN, 3, dragon, 3, 1);
	assertEquals(playerN.character.getStrength(), 5);
	sword.unequipEffect(playerN);
	assertEquals(playerN.character.getStrength(), 4);

    }

    @Test
    public void testShield() {
	Shield shield = new Shield();
	playerN.addItem(shield);
	shield.equipEffect(playerN);
	Ogre ogre = new Ogre();
	playerN.playerFightsEnemy(5, ogre, 15);
	assertEquals(playerN.character.getLife(), 4);
	playerN.playerFightsEnemy(1, ogre, 15);
	assertEquals(playerN.character.getLife(), 3);
	shield.unequipEffect(playerN);
	assertFalse(playerN.armourEquipped);
    }

    @Test
    public void testHelmet() {
	Helmet helmet = new Helmet();
	playerN.addItem(helmet);
	helmet.equipEffect(playerN);
	Ogre ogre = new Ogre();
	playerN.playerFightsEnemy(6, ogre, 15);
	assertEquals(playerN.character.getLife(), 4);
	playerN.playerFightsEnemy(1, ogre, 15);
	assertEquals(playerN.character.getLife(), 3);
	helmet.unequipEffect(playerN);
	assertFalse(playerN.armourEquipped);
    }

    @Test
    public void testPlatemail() {
	Platemail platemail = new Platemail();
	playerN.addItem(platemail);
	platemail.equipEffect(playerN);
	Ogre ogre = new Ogre();
	playerN.playerFightsEnemy(3, ogre, 15);
	assertEquals(playerN.character.getLife(), 4);
	playerN.playerFightsEnemy(1, ogre, 15);
	assertEquals(playerN.character.getLife(), 3);
	platemail.unequipEffect(playerN);
	assertFalse(playerN.armourEquipped);
    }

    @Test
    public void testArmour() {
	Armour armour = new Armour();
	playerN.addItem(armour);
	armour.equipEffect(playerN);
	Ogre ogre = new Ogre();
	playerN.playerFightsEnemy(4, ogre, 15);
	assertEquals(playerN.character.getLife(), 4);
	playerN.playerFightsEnemy(1, ogre, 15);
	assertEquals(playerN.character.getLife(), 3);
	armour.unequipEffect(playerN);
	assertFalse(playerN.armourEquipped);
    }

    @Test
    public void testDoomSword() {
	Doomsword sword = new Doomsword();
	playerN.addItem(sword);
	sword.equipEffect(playerN);
	Ogre ogre = new Ogre();
	sword.postBattleEffect(playerN, 1, ogre, 1, 1);
	assertEquals(playerN.character.getLife(), 3);
	playerN.playerFightsEnemy(1, ogre, 15);
	assertEquals(playerN.character.getLife(), 1);
	sword.unequipEffect(playerN);
	assertFalse(playerN.magicEquipped);
    }

    @Test
    public void testPotionOfStrength() {
	playerN.items.clear();
	PotionOfStrength pot = new PotionOfStrength();
	playerN.addItem(pot);
	pot.equipEffect(playerN);
	assertEquals(0, playerN.items.size());
	assertEquals(6, playerN.character.getStrength());
    }

    @Test
    public void testPotionOfHealing() {
	playerN.items.clear();
	HealingPotion pot = new HealingPotion();
	playerN.addItem(pot);
	playerN.character.setCurrentLife(1);
	pot.equipEffect(playerN);
	assertEquals(0, playerN.items.size());
	assertEquals(4, playerN.character.getLife());
    }

    @Test
    public void testCrownOfSolomon() {
	playerN.items.clear();
	SolomonsCrown crown = new SolomonsCrown();
	playerN.addItem(crown);
	crown.equipEffect(playerN);
	assertEquals(1, playerN.items.size());
	assertEquals(6, playerN.character.getCraft());
	crown.unequipEffect(playerN);
	assertEquals(4, playerN.character.getCraft());

    }

}
