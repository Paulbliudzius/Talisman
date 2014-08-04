package items;

import enemy.Enemy;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Mar 26, 2014.
 */
public abstract class Item {
    /**
     * Returns the item name
     * 
     * @return
     */
    public boolean equipped = false;

    public abstract String getItemName();

    public abstract String getType();

    /**
     * Returns the item's description
     * 
     * @return
     */
    public abstract String getItemDescription();

    /**
     * Does the item has an effect beside increase a stat.
     * 
     * @return
     */
    public abstract Boolean hasEffect();

    /**
     * this method applies that effect.
     * 
     */
    public abstract void effect();

    /**
     * What happens when the item is equipped.
     * 
     */
    public abstract void equipEffect(Player player);

    /**
     * What should happen when the item is unequipped
     * 
     */
    public abstract void unequipEffect(Player player);

    /**
     * returns if the item is equipped or not
     * 
     * @return
     */
    public boolean equipped() {
	return this.equipped;
    }

    public abstract int getCost();

    /**
     * TODO Put here a description of what this method does.
     * 
     * @param player
     * @param playerRoll
     * @param enemy
     * @param enemyRoll
     */
    public void preBattleEffect(Player player, int playerRoll, Enemy enemy,
	    int enemyRoll) {
	// initially do nothing, can be overrided for effect.
    }

    /**
     * TODO Put here a description of what this method does.
     * 
     * @param player
     * @param playerRoll
     * @param enemy
     * @param enemyRoll
     */
    public void postBattleEffect(Player player, int playerRoll, Enemy enemy,
	    int enemyRoll, int result) {
	// initially do nothing, can be overrided for effect.
    }

}
