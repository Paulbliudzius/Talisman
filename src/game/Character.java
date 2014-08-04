package game;

import game.abilities.Alchemist;
import game.abilities.Amazon;
import game.abilities.Assassin;
import game.abilities.BaseAbility;
import game.abilities.BountyHunter;
import game.abilities.CatBurglar;
import game.abilities.CharacterAbilities;
import game.abilities.ChivalricKnight;
import game.abilities.Conjurer;
import game.abilities.DarkCultist;
import game.abilities.Druid;
import game.abilities.Dwarf;
import game.abilities.Elf;
import game.abilities.Ghoul;
import game.abilities.Minstrel;
import game.abilities.Monk;
import game.abilities.Priest;
import game.abilities.Prophetess;
import game.abilities.Sorceress;
import game.abilities.Thief;
import game.abilities.Troll;
import game.abilities.Warrior;
import game.abilities.Wizard;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * TODO Put here a description of what this class does.
 * 
 */
public class Character {
    private String name;
    private Alignment alignment;
    private int currentLife;
    private int strength;
    private int craft;
    private int fate;
    private int maxFate;
    private int gold;
    private int strengthBounty = 0;
    private int craftBounty = 0;
    private int[] position = new int[2];
    private CharacterAbilities[] abilities;
    // private Object[] spells;
    // private Object[] followers;
    private Object[] trophies;
    private int maxLife;
    public BaseAbility abilitySet;

    public enum Alignment {
	NEUTRAL, EVIL, GOOD
    }

    /**
     * TODO Put here a description of what this constructor does.
     * 
     * @param name
     * @param neutral
     * @param life
     * @param strength
     * @param craft
     * @param fate
     * @param gold
     * @param position
     */
    public Character(String name, Alignment neutral, int life, int strength,
	    int craft, int fate, int gold, int[] position) {
	this.name = name;
	this.alignment = neutral;
	this.currentLife = life;
	this.setMaxLife(life);
	this.strength = strength;
	this.craft = craft;
	this.fate = fate;
	this.maxFate = fate;
	this.gold = gold;
	this.position[0] = position[0];
	this.position[1] = position[1];
	setAbilities(name);
    }

    public String getName() {
	return this.name;
    }

    public int getFate() {
	return this.fate;
    }

    public int getLife() {
	return this.currentLife;
    }

    public int getCraft() {
	return this.craft;
    }

    public int getStrength() {
	return this.strength;
    }

    public int getGold() {
	return this.gold;
    }

    public void moveCharacter(int[] pos) {
	this.position[0] = pos[0];
	this.position[1] = pos[1];
    }

    public int[] getPosition() {
	int[] pos = new int[2];
	pos[0] = this.position[0];
	pos[1] = this.position[1];
	return pos;
    }

    /**
     * Sets the field called 'strengthParam' to the given value.
     * 
     * @param strengthParam
     *            The strengthParam to set.
     */
    public void setStrength(int strengthParam) {
	this.strength = strengthParam;
    }

    /**
     * Returns the value of the field called 'maxLife'.
     * 
     * @return Returns the maxLife.
     */
    public int getMaxLife() {
	return this.maxLife;
    }

    /**
     * Sets the field called 'maxLife' to the given value.
     * 
     * @param maxLife
     *            The maxLife to set.
     */
    public void setMaxLife(int maxLife) {
	this.maxLife = maxLife;
    }

    /**
     * Returns the value of the field called 'alignment'.
     * 
     * @return Returns the alignment.
     */
    public Alignment getAlignment() {
	return this.alignment;
    }

    /**
     * Sets the field called 'alignment' to the given value.
     * 
     * @param alignment
     *            The alignment to set.
     */
    public void setAlignment(Alignment alignment) {
	this.alignment = alignment;
    }

    public void setCurrentLife(int lifeParam) {
	if (lifeParam > this.maxLife) {
	    this.currentLife = this.maxLife;
	    return;
	}
	this.currentLife = lifeParam;

    }

    /**
     * Returns the value of the field called 'abilities'.
     * 
     * @return Returns the abilities.
     */
    public CharacterAbilities[] getAbilities() {
	return this.abilities;
    }


    public void setFate(int fateParam) {
	if (fateParam > this.maxFate) {
	    this.fate = this.maxFate;
	    return;
	}
	this.fate = fateParam;
    }

    public void setMaxFate(int maxFateParam) {
	this.maxFate = maxFateParam;
    }

    public int getMaxFate() {
	return this.maxFate;
    }

    /**
     * Sets the field called 'Craft' to the given value.
     * 
     * @param craftParam
     *            The craft to set.
     */
    public void setCraft(int craftParam) {
	this.craft = craftParam;
    }

    /**
     * TODO Put here a description of what this method does.
     * 
     */
    public void useFate() {
	setFate(this.fate - 1);

    }

    /**
     * Returns the value of the field called 'craftBounty'.
     * 
     * @return Returns the craftBounty.
     */
    public int getCraftBounty() {
	return this.craftBounty;
    }

    /**
     * Sets the field called 'craftBounty' to the given value.
     * 
     * @param craftBounty
     *            The craftBounty to set.
     */
    public void setCraftBounty(int craftBounty) {
	this.craftBounty = craftBounty;
    }

    /**
     * Returns the value of the field called 'strengthBounty'.
     * 
     * @return Returns the strengthBounty.
     */
    public int getStrengthBounty() {
	return this.strengthBounty;
    }

    /**
     * Sets the field called 'strengthBounty' to the given value.
     * 
     * @param strengthBounty
     *            The strengthBounty to set.
     */
    public void setStrengthBounty(int strengthBounty) {
	this.strengthBounty = strengthBounty;
    }

    /**
     * TODO Put here a description of what this method does.
     * 
     */
    public String getAlignmentString() {
	if (this.alignment == Alignment.GOOD) {
	    return "Good";
	} else if (this.alignment == Alignment.EVIL) {
	    return "Evil";
	} else {
	    return "Neutral";
	}

    }

    public void addFate(int i) {
	this.fate += i;
    }

    public void addLife(int i) {
	this.currentLife += i;
	if (this.currentLife > this.maxLife) {
	    this.currentLife = this.maxLife;
	}

    }

    public void addStrength(int i) {
	this.strength = this.strength + i;

    }

    public void addCraft(int i) {
	this.craft += i;

    }

    public String getAbilityString() {
	CharacterAbilities[] abilities = abilitySet.getAbilities();
	String s = "<html><div style=\"width:100px;\">";
	for (int i = 0; i < abilities.length; i++) {
	    s += abilities[i].getDescription() + "<br>";
	}
	return s + "</div></html>";
    }

    public void setAbilities(String name) {
	switch (name) {
	case "Alchemist":
	    abilitySet = new Alchemist();
	    break;
	case "Amazon":
	    abilitySet = new Amazon();
	    break;
	case "Assassin":
	    abilitySet = new Assassin();
	    break;
	case "Bounty Hunter":
	    abilitySet = new BountyHunter();
	    break;
	case "Cat Burglar":
	    abilitySet = new CatBurglar();
	    break;
	case "Chivalric Knight":
	    abilitySet = new ChivalricKnight();
	    break;
	case "Conjurer":
	    abilitySet = new Conjurer();
	    break;
	case "Dark Cultist":
	    abilitySet = new DarkCultist();
	    break;
	case "Druid":
	    abilitySet = new Druid();
	    break;
	case "Dwarf":
	    abilitySet = new Dwarf();
	    break;
	case "Elf":
	    abilitySet = new Elf();
	    break;
	case "Ghoul":
	    abilitySet = new Ghoul();
	    break;
	case "Minstrel":
	    abilitySet = new Minstrel();
	    break;
	case "Monk":
	    abilitySet = new Monk();
	    break;
	case "Priest":
	    abilitySet = new Priest();
	    break;
	case "Prophetess":
	    abilitySet = new Prophetess();
	    break;
	case "Sorceress":
	    abilitySet = new Sorceress();
	    break;
	case "Thief":
	    abilitySet = new Thief();
	    break;
	case "Troll":
	    abilitySet = new Troll();
	    break;
	case "Warrior":
	    abilitySet = new Warrior();
	    break;
	case "Wizard":
	    abilitySet = new Wizard();
	    break;
	default:
	    abilitySet = new Warrior();
	    break;
	}
    }

    public ImageIcon getImage() {
	BufferedImage myPicture;
	try {
	    myPicture = ImageIO.read(new File("characters/" + name + ".png"));
	    ImageIcon icon = new ImageIcon(myPicture.getScaledInstance(100,
		    200, Image.SCALE_SMOOTH));
	    return icon;
	} catch (IOException e1) {
	    System.err.println("Error loading image for path: " + "characters/"
		    + name + ".png");
	    return new ImageIcon();
	}
    }

}
