package game;

import game.CombatFrame.battle;
import game.EncounterSequence.GameState;
import game.abilities.BaseAbility;
import items.Item;
import items.magic.HealingPotion;

import java.util.ArrayList;

import spells.Spell;
import actions.QuestCheck;
import enemy.Enemy;

public class Player {
    public Character character;
    private String name;
    private Dice dice = new Dice(1);
    private int lastRoll = 6;
    private int lostTurns = 0;
    private boolean deadSent = false;
    public int inventoryCap = 4; // 4 initially, mules could improve
    private int gold;
    public int quest = -1;
    public boolean questComplete = false;
    public boolean weaponEquipped = false;
    public boolean holdsTwoWeapons = false;
    public boolean secondWeaponEquipped = false;
    public boolean magicEquipped = false;
    public boolean armourEquipped = false;
    public boolean muleEquipped = false;
    public boolean talisman = false;
    public ArrayList<Item> items = new ArrayList<Item>();
    public ArrayList<Spell> spells = new ArrayList<Spell>();
    public ArrayList<Enemy> followers = new ArrayList<Enemy>();
    ArrayList<Spell> spellEffects = new ArrayList<Spell>();
    private BaseAbility abilitySet;
    private Card drawnCard;
    public battle lastBattle;
    private int stayTurns = 0;

    public Player(Character character, String name) {
	this.character = character;
	this.gold = character.getGold();
	this.name = name;
	// System.out.print(name);
	abilitySet = character.abilitySet;
	abilitySet.setPlayer(this);
	HealingPotion pot = new HealingPotion();
	// this.items.add(new Mule());
	this.items.add(pot);
    }

    public String getName() {
	return this.name;
    }

    public String getPlayerClass() {
	return this.character.getName();
    }

    /*
     * Get turns current lost
     */
    public int lostTurns() {
	return this.lostTurns;
    }

    /*
     * Make a player lose turns
     */
    public void loseTurns(int turns) {
	if (this.lostTurns >= 0) {
	    this.lostTurns += turns;
	}
    }

    /*
 * 
 */
    public void useTurn() {
	if (this.lostTurns > 0) {
	    this.lostTurns--;
	}
    }

    public int roll() {
	this.lastRoll = this.dice.roll();
	if (this.lastRoll > 3 && lostTurns() < 0) {
	    this.lostTurns = 0;
	}
	return this.lastRoll;
    }

    public int getLastRoll() {
	return this.lastRoll;
    }

    public ArrayList<Spell> getSpells() {
	return this.spells;
    }

    public int[] getPosition() {
	return this.character.getPosition();
    }

    public void setPosition(int[] pos) {
	this.character.moveCharacter(pos);
    }

    /**
     * TODO Put here a description of what this method does.
     * 
     * @param string
     */
    public void setName(String string) {
	this.name = string;

    }

    /**
     * Gives the player a random spell.
     * 
     */
    public void giveSpell() {
	this.spells.add(GameRunner.spellDeck.getSpell());
    }

    /**
     * 
     * @param i
     */
    public void setLastRoll(int i) {
	this.lastRoll = i;

    }

    /**
     * TODO Put here a description of what this method does.
     * 
     * @return
     * 
     */
    public boolean useFate() {
	if (this.character.getFate() > 0) {
	    this.character.useFate();
	    return true;
	}
	return false;
    }

    /**
     * Method for players fighting enemies.
     * 
     * @param psychic
     * 
     * 
     * @return 1 if the player wins, -1 if they loose. 0 for a draw
     */
    public int playerFightsEnemy(int playerRoll, Enemy enemy, int enemyRoll) {
	int playerPower;
	String combatType = enemy.getCombatType();
	enemy.enemyPreBattle(this, playerRoll);
	for (Item wep : this.items) {
	    if (wep.equipped()) {
		wep.preBattleEffect(this, playerRoll, enemy, enemyRoll);
	    }
	}
	if (GameRunner.getMessage("strength").equals((combatType))) {
	    playerPower = this.character.getStrength();
	} else {
	    playerPower = this.character.getCraft();
	}
	if (playerPower + playerRoll > enemy.power() + enemyRoll) {
	    if (GameRunner.getMessage("strength") == combatType) {
		this.character.setStrengthBounty(this.character
			.getStrengthBounty() + enemy.getBounty());
	    } else {
		this.character.setCraftBounty(this.character.getCraftBounty()
			+ enemy.getBounty());
	    }
	    enemy.playerWinsSideEffect(this);
	    for (Item wep : this.items) {
		if (wep.equipped()) {
		    wep.postBattleEffect(this, playerRoll, enemy, enemyRoll, 1);
		}
	    }
	    new QuestCheck(4).act(this);
	    return 1;
	} else if (playerPower + playerRoll < enemy.power() + enemyRoll) {
	    this.character.setCurrentLife(this.character.getLife() - 1);
	    enemy.enemyWinsSideEffect(this);
	    for (Item wep : this.items) {
		if (wep.equipped()) {
		    wep.postBattleEffect(this, playerRoll, enemy, enemyRoll, -1);
		}
	    }
	    return -1;
	} else {
	    for (Item wep : this.items) {
		if (wep.equipped()) {
		    wep.postBattleEffect(this, playerRoll, enemy, enemyRoll, 0);
		}
	    }
	    return 0;
	}

    }

    public int playerFightsPlayer(int playerRoll, Player enemy, int enemyRoll,
	    boolean psychic) {
	int playerPower, enemyPower;
	// if ("strength" == combatType) {
	if (psychic) {
	    playerPower = this.character.getCraft();
	    enemyPower = enemy.character.getCraft();
	} else {
	    playerPower = this.character.getStrength();
	    enemyPower = enemy.character.getStrength();
	}
	// } else {
	// playerPower = this.character.getCraft();
	// }
	if (playerPower + playerRoll > enemyPower + enemyRoll) {
	    this.character.setStrengthBounty(this.character.getStrengthBounty()
		    + enemy.character.getStrengthBounty());
	    enemy.character.setCurrentLife(enemy.character.getLife() - 1);
	    new QuestCheck(11).act(this);
	    return 1;
	} else if (playerPower + playerRoll < enemyPower + enemyRoll) {
	    enemy.character.setStrengthBounty(this.character
		    .getStrengthBounty() + enemy.character.getStrengthBounty());
	    this.character.setCurrentLife(this.character.getLife() - 1);
	    return -1;
	} else {
	    return 0;
	}

    }

    public void drawCard(Card c) {
	drawnCard = c;

    }

    public Card getDrawnCard() {
	return drawnCard;
    }

    public void useCard() {
	drawnCard.use(this);
	drawnCard = null;
    }

    public void addItem(Item item) {
	this.items.add(item);
    }

    public void addGold(int gold) {
	this.gold += gold;
    }

    public void setGold(int gold) {
	this.gold = gold;
    }

    public int getGold() {
	return gold;
    }

    public ArrayList<Item> getItems() {
	return this.items;
    }

    // TODO this use the Action - DiscardItem.
    public void removeItem(int discard) {
	if (this.items.get(discard).equipped) {
	    this.items.get(discard).unequipEffect(this);
	}
	this.items.remove(discard);

    }

    public void movePos(int[] pos) {
	this.character.moveCharacter(pos);
    }

    public void killedSent() {
	this.deadSent = true;
    }

    public boolean deadSent() {
	return this.deadSent;
    }

    public BaseAbility getAbilitySet() {
	return abilitySet;
    }

    public boolean checkAbilities(GameState state) {
	if (abilitySet != null) {
	    return abilitySet.checkAbilities(state);
	} else {
	    return false;
	}
    }

    /**
     * Increases the turns to stay from 0 on up.
     * 
     * @param turnsLost
     */
    public void stayPosition(int turnsStayed) {
	this.stayTurns += turnsStayed;
    }

    /**
     * Removes the amount of turns to stay from the number
     * 
     */
    public void stayedTurn(int stay) {
	this.stayTurns -= stay;
    }

    /**
     * If the player needs to stay.
     * 
     * @return
     */
    public int stay() {
	return this.stayTurns;
    }

    public void equipWeapon() {
	if (!weaponEquipped) {
	    weaponEquipped = true;
	} else {
	    secondWeaponEquipped = true;
	}
    }

    public void unequipWeapon() {
	if (secondWeaponEquipped) {
	    secondWeaponEquipped = false;
	} else {
	    weaponEquipped = false;
	}
    }

    /**
     * Removes all items from the player and unequipps them
     * 
     */
    public void removeAndUnequippedItems() {

	for (int i = 0; i < this.items.size(); i++) {
	    if (this.items.get(i).equipped()) {
		this.items.get(i).unequipEffect(this);
	    }
	}
	this.armourEquipped = false;
	this.weaponEquipped = false;
	this.magicEquipped = false;
	this.items.clear();

    }

    public void addFate(int i) {
	character.addFate(i);
    }

    public void hasTalisman(boolean set) {
	this.talisman = set;
    }

    public void removeItem(Item item) {
	items.remove(item);
    }

    public boolean canAddItem() {
	return this.items.size() < this.inventoryCap;
    }

    public void questRemove() {
	this.quest = -1;
    }

    public void addStrength(int i) {
	this.character.addStrength(i);
    }

    public void addCraft(int i) {
	this.character.addCraft(i);
    }

    public void addLife(int i) {
	this.character.addLife(i);
    }

}
