package items.magic;

import game.Player;
import items.Item;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 12, 2014.
 */
public class HealingPotion extends Item {

    @Override
    public String getItemName() {
	// TODO Auto-generated method stub.
	return "Potion of healing";
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub.
	return "magic";
    }

    @Override
    public String getItemDescription() {
	// TODO Auto-generated method stub.
	return "Consume to regain all lives";
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
	player.character.setCurrentLife(player.character.getMaxLife());
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
