package items.followers;

import game.Player;

public class RidingHorse extends Follower {

    @Override
    public String getItemName() {
	return "Riding Horse";
    }

    @Override
    public String getItemDescription() {
	return "The Riding Horse lets you add two die together for movement";
    }

    @Override
    public Boolean hasEffect() {
	return true;
    }

    @Override
    public void effect() {
	// TODO Auto-generated method stub

    }

    @Override
    public void equipEffect(Player player) {
	// TODO Auto-generated method stub

    }

    @Override
    public void unequipEffect(Player player) {
	// TODO Auto-generated method stub

    }

    @Override
    public boolean equipped() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public int getCost() {
	// TODO Auto-generated method stub
	return 0;
    }

}
