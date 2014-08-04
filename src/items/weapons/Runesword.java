package items.weapons;

import enemy.Enemy;
import game.GameRunner;
import game.Player;
import items.Item;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 12, 2014.
 */
public class Runesword extends Item {

    @Override
    public String getItemName() {
	// TODO Auto-generated method stub.
	return "RuneSword";
    }

    @Override
    public String getItemDescription() {
	// TODO Auto-generated method stub.
	return "<html>No good player may weild the runesword. <br>+1 strength. +1 life if you defeat another player or a monster type</html>";
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
	if (player.character.getAlignmentString() == "Good") {
	    player.items.remove(this);
	} else {
	    player.magicEquipped = true;
	    player.character.setStrength(player.character.getStrength() + 1);
	    this.equipped = true;
	}
    }

    @Override
    public void unequipEffect(Player player) {
	player.magicEquipped = false;
	this.equipped = false;
	player.character.setStrength(player.character.getStrength() - 1);

    }

    @Override
    public boolean equipped() {
	// TODO Auto-generated method stub.
	return this.equipped;
    }

    @Override
    public int getCost() {
	// TODO Auto-generated method stub.
	return 0;
    }

    @Override
    public void preBattleEffect(Player player, int playerRoll, Enemy enemy,
	    int enemyRoll) {
	// none

    }

    @Override
    public void postBattleEffect(Player player, int playerRoll, Enemy enemy,
	    int enemyRoll, int result) {

	if (result == 1 && this.equipped) {
	    if (enemy.getType() == GameRunner.getMessage("monster")) {
		player.character.setCurrentLife(player.character.getLife() + 1);
	    }
	}

    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub.
	return "magic";
    }

}
