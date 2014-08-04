package items.armour;

import enemy.Enemy;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 12, 2014.
 */

public class Platemail extends Armours {
    public boolean equipped = false;

    @Override
    public String getItemName() {
	return "Platemail";
    }

    @Override
    public String getItemDescription() {
	return "If you die, but rolled a 3,4,5, or 6 you do not loose a life";
    }

    @Override
    public Boolean hasEffect() {
	return true;
    }

    @Override
    public void effect() {
	/**
	 * TODO: Helmet effect here
	 * 
	 */
    }

    @Override
    public void equipEffect(Player player) {
	this.equipped = true;
	player.armourEquipped = true;

    }

    @Override
    public void unequipEffect(Player player) {
	this.equipped = false;
	player.armourEquipped = false;

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
    public void postBattleEffect(Player player, int playerRoll, Enemy enemy,
	    int enemyRoll, int result) {
	if (result == -1) {
	    if (playerRoll >= 3) {
		player.character.setCurrentLife(player.character.getLife() + 1);
	    }
	}

    }

}
