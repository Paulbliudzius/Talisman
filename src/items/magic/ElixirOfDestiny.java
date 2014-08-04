package items.magic;

import enemy.Enemy;
import game.Player;
import items.Object;

public class ElixirOfDestiny extends Object {
    public boolean equipped = false;

    @Override
    public String getType() {
	return "generic";
    }

    @Override
    public String getItemName() {
	return "Elixir Of Destiny";
    }

    @Override
    public String getItemDescription() {
	return "You may drink the Elixir of Destiny at any time. When you do, roll 1 die:"
		+ "Poison; lose 1 life"
		+ "Gain 1 fate"
		+ "Gain 1 fate"
		+ "Gain 1 fate"
		+ "Gain 1 fate"
		+ "Gain 2 fate"
		+ "The empty vessel is then placed on the discard pile.";
    }

    @Override
    public Boolean hasEffect() {
	return true;
    }

    @Override
    public void effect() {
	/**
	 * TODO: onroll actions
	 */
    }

    @Override
    public int goldBonus() {
	return 0;
    }

    @Override
    public void equipEffect(Player player) {
	// TODO Auto-generated method stub.

    }

    @Override
    public void unequipEffect(Player player) {
	// TODO Auto-generated method stub.

    }

    @Override
    public boolean equipped() {
	// TODO Auto-generated method stub.
	return this.equipped;
    }

    @Override
    public int getCost() {
	// TODO Auto-generated method stub
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
