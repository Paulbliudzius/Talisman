package tests;

import items.weapons.Sword;
import game.Character;
import game.Character.Alignment;
import game.EncounterSequence.GameState;
import game.Player;
import game.abilities.BaseAbility;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbilitiesTest {
    Player p1;
    Player p2;
    Player p3;
    Player p4;
    Player p5;
    Player p6;
    Player p7;
    Player p8;
    Player p9;
    Player p10;
    BaseAbility p1Abilities;
    private BaseAbility p2Abilities, p3Abilities, p4Abilities, p5Abilities,
	    p6Abilities, p7Abilities, p8Abilities, p9Abilities, p10Abilities;

    @Before
    public void setup() {
	p1 = new Player(new Character("Amazon", Alignment.NEUTRAL, 4, 3, 3, 3,
		1, new int[] { 0, 0 }), "TestName");
	p2 = new Player(new Character("Bounty Hunter", Alignment.NEUTRAL, 4, 3,
		3, 3, 1, new int[] { 0, 0 }), "TestName");
	p3 = new Player(new Character("Dark Cultist", Character.Alignment.EVIL,
		4, 3, 3, 1, 1, new int[] { 12, 1 }), "TestName");
	p4 = new Player(new Character("Wizard", Character.Alignment.EVIL, 4, 2,
		5, 3, 1, new int[] { 1, 0 }), "TestName");
	p5 = new Player(new Character("Troll", Character.Alignment.NEUTRAL, 6,
		6, 1, 1, 1, new int[] { 1, 0 }), "TestName");
	p6 = new Player(new Character("Prophetess", Character.Alignment.GOOD,
		4, 2, 4, 2, 1, new int[] { 1, 0 }), "TestName");
	p7 = new Player(new Character("Sorceress", Character.Alignment.EVIL, 4,
		2, 4, 3, 1, new int[] { 1, 0 }), "TestName");
	p8 = new Player(new Character("Ghoul", Character.Alignment.EVIL, 4, 2,
		4, 4, 1, new int[] { 1, 0 }), "TestName");
	p9 = new Player(new Character("Conjurer", Character.Alignment.GOOD, 4,
		2, 4, 1, 1, new int[] { 1, 2 }), "TestName");
	p10 = new Player(new Character("Monk", Character.Alignment.GOOD, 4,
		2, 4, 1, 1, new int[] { 1, 2 }), "TestName");
	p1Abilities = p1.getAbilitySet();
	p2Abilities = p2.getAbilitySet();
	p3Abilities = p3.getAbilitySet();
	p4Abilities = p4.getAbilitySet();
	p5Abilities = p5.getAbilitySet();
	p6Abilities = p6.getAbilitySet();
	p7Abilities = p7.getAbilitySet();
	p8Abilities = p8.getAbilitySet();
	p9Abilities = p9.getAbilitySet();
	p10Abilities = p10.getAbilitySet();
    }

    @Test
    public void hasAbilities() {
	Assert.assertNotNull(p1Abilities);
    }

    @Test
    public void usableTest() {
	Assert.assertTrue(p1Abilities.checkAbilities(GameState.BEFORE_ROLL));
	Assert.assertTrue(p3Abilities.checkAbilities(GameState.COMBAT_CHOICE));
    }

    @Test
    public void hasDescriptions(){
	Assert.assertEquals(p1Abilities.getAbilities()[0].getDescription(),"Roll 2 Dice for Movement.");
	Assert.assertEquals(p1Abilities.getAbilities()[1].getDescription(),"Fight again after losing or a stand-off.");
	Assert.assertEquals(p2Abilities.getAbilities()[0].getDescription(), "Gain one gold from defeating a character with a different alignment in combat.");
	Assert.assertEquals(p2Abilities.getAbilities()[1].getDescription(), "Gain one gold from killing an enemy in combat.");
	Assert.assertEquals(p2Abilities.getAbilities()[2].getDescription(), "Automatically win a stand-off.");
	Assert.assertEquals(p4Abilities.getAbilities()[0].getDescription(), "You begin the game with two Spells.");
	Assert.assertEquals(p4Abilities.getAbilities()[1].getDescription(), "During game, you always have at least one Spell.");
	Assert.assertEquals(p4Abilities.getAbilities()[2].getDescription(), "When you attack another character you may choose to make the attack physic combat.");
	Assert.assertEquals(p5Abilities.getAbilities()[0].getDescription(),"Regenerate health if a six is rolled instead of moving.");
	Assert.assertEquals(p9Abilities.getAbilities()[1].getDescription(), "Always have at least 1 Spell.");
    }

    @Test
    public void wizardAbilities() {
	Assert.assertEquals(2, p4.spells.size());
	p4.spells.remove(0);
	p4.spells.remove(0);
	p4Abilities.checkAbilities(GameState.ANY);
	Assert.assertTrue(p4Abilities.checkAbilities(GameState.COMBAT_CHOICE));
	Assert.assertEquals(1, p4.spells.size());
    }

    @Test
    public void conjurerAbilities() {
	Assert.assertEquals(1, p9.spells.size());
	p9.spells.remove(0);
	p9Abilities.checkAbilities(GameState.ANY);
	Assert.assertEquals(1, p9.spells.size());
    }

    @Test
    public void prophetessAbilities() {
	Assert.assertEquals(1, p6.spells.size());
    }

    @Test
    public void sorceressAbilities() {
	Assert.assertEquals(1, p7.spells.size());
    }

    @Test
    public void ghoulAbilities() {
	Assert.assertTrue(p8Abilities.checkAbilities(GameState.COMBAT_CHOICE));
    }
    @Test
    public void monkAbilities() {
	new Sword().equipEffect(p10);
		p10Abilities.checkAbilities(GameState.ANY);
		Assert.assertEquals(2, p10.character.getStrength());
    }
}
