package items.generic;

import game.Player;
import items.Object;

public class Raft extends Object {
    @Override
    public String getType() {
	return "generic";
    }

    @Override
    public String getItemName() {
	return "Raft";
    }

    @Override
    public String getItemDescription() {
	return "On your next turn, instead of your normal move, "
		+ "you may choose to cross the River to a space directly opposite"
		+ " the one you are in. Whether you cross or not, place the Raft"
		+ " on the discard pile, as it cannot be carried with you.";
    }

    @Override
    public Boolean hasEffect() {
	return true;
    }

    @Override
    public void effect() {
	/**
	 * TODO: Move action
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
	return false;
    }

    @Override
    public int getCost() {
	// TODO Auto-generated method stub
	return 0;
    }

}
