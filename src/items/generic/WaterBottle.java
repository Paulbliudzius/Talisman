package items.generic;

import game.Player;
import items.Object;

public class WaterBottle extends Object {
    @Override
    public String getType() {
	return "generic";
    }

    @Override
    public String getItemName() {
	return "Water Bottle";
    }

    @Override
    public String getItemDescription() {
	return "You do not lose a life in the Desert.";
    }

    @Override
    public Boolean hasEffect() {
	return true;
    }

    @Override
    public void effect() {
	/**
	 * TODO: prevent from dying in the desert
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
