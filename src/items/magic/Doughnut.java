package items.magic;

import game.Player;
import items.Item;
import spells.Strength;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 12, 2014.
 */
public class Doughnut extends Item {

    @Override
    public String getItemName() {
	// TODO Auto-generated method stub.
	return "Doughnut";
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub.
	return "magic";
    }

    @Override
    public String getItemDescription() {
	// TODO Auto-generated method stub.
	return "Consume to gain 1 strength until the end of your turn";
    }

    @Override
    public Boolean hasEffect() {
	// TODO Auto-generated method stub.
	return null;
    }

    @Override
    public void effect() {
	// TODO Auto-generated method stub.

    }

    @Override
    public void equipEffect(Player player) {
	player.items.remove(this);
	Strength strength = new Strength();
	strength.ability(player);

    }

    @Override
    public void unequipEffect(Player player) {
	// na, one use then treated like a spell because of duration effect

    }

    @Override
    public int getCost() {
	// TODO Auto-generated method stub.
	return 0;
    }

}
