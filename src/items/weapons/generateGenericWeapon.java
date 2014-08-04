package items.weapons;

import enemy.Enemy;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 12, 2014.
 */
public class generateGenericWeapon extends Weapon {

    private String description;
    private String name;
    private String[] stats;
    private int[] amount;

    /**
     * Useful for generating weapons with simple + and - stat abilities.
     * 
     * @param name
     * @param description
     * @param stats
     * @param amount
     *            , the length of stats and amount must be equal.
     */
    public generateGenericWeapon(String name, String description,
	    String[] stats, int[] amount) {
	this.name = name;
	this.description = description;
	this.stats = stats;
	this.amount = amount;

    }

    @Override
    public String getItemName() {
	// TODO Auto-generated method stub.
	return this.name;
    }

    @Override
    public String getItemDescription() {
	// TODO Auto-generated method stub.
	return this.description;
    }

    @Override
    public Boolean hasEffect() {
	// TODO Auto-generated method stub.
	return false;
    }

    @Override
    public void effect() {
	// TODO Auto-generated method stub.

    }

    @Override
    public void equipEffect(Player player) {
	for (int i = 0; i < stats.length; i++) {
	    if (this.stats[i] == "strength") {
		;
	    }
	}
	// player.character.s

    }

    @Override
    public void unequipEffect(Player player) {
	// TODO Auto-generated method stub.

    }

    @Override
    public boolean equipped() {
	// TODO Auto-generated method stub.
	return false;
    }

    @Override
    public int getCost() {
	// TODO Auto-generated method stub.
	return 0;
    }

    @Override
    public int strengthBonus() {
	// TODO Auto-generated method stub.
	return 0;
    }

    @Override
    public int craftBonus() {
	// TODO Auto-generated method stub.
	return 0;
    }

    @Override
    public void preBattleEffect(Player player, int playerRoll, Enemy enemy,
	    int enemyRoll) {
	// TODO Auto-generated method stub.

    }

    @Override
    public void postBattleEffect(Player player, int playerRoll, Enemy enemy,
	    int enemyRoll, int result) {
	// TODO Auto-generated method stub.

    }

}
