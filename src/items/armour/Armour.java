package items.armour;

import enemy.Enemy;
import game.Player;

public class Armour extends Armours {
    public boolean equipped = false;

    @Override
    public String getItemName() {
	return "Armour";
    }

    @Override
    public String getItemDescription() {
	return "If you loose in battle, but rolled above a 4 you do not loose a life";
    }

    @Override
    public Boolean hasEffect() {
	return true;
    }

    @Override
    public void effect() {
	/**
	 * TODO: if defeated && roll > 3 life++ or don't lose life
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
	    if (playerRoll >= 4) {
		player.character.setCurrentLife(player.character.getLife() + 1);
	    }
	}

    }

}
