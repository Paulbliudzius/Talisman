package items.magic;

import enemy.Enemy;
import game.GameRunner;
import game.Player;
import items.weapons.Weapon;

public class HolyLance extends Weapon {
    public boolean equipped = false;

    @Override
    public String getItemName() {
	return "Magic";
    }

    @Override
    public String getItemDescription() {
	return "No evil character may have the Holy Lance."
		+ " Add 1 to your Strength during battle."
		+ " Add 3 to your Strength during battle against Dragons.";
    }

    @Override
    public Boolean hasEffect() {
	return true;
    }

    @Override
    public void effect() {
	/**
	 * TODO: if enemy == dragon battle strength+=3
	 */
    }

    @Override
    public int strengthBonus() {
	return 1;
    }

    @Override
    public int craftBonus() {
	return 0;
    }

    @Override
    public void equipEffect(Player player) {
	if (player.character.getAlignmentString() == "Evil") {
	    player.items.remove(this);
	} else {
	    player.character.setStrength(player.character.getStrength() + 1);
	    this.equipped = true;
	    player.magicEquipped = true;
	}

    }

    @Override
    public void unequipEffect(Player player) {
	player.character.setStrength(player.character.getStrength() - 1);
	this.equipped = false;
	player.magicEquipped = false;

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
	if (enemy.getType() == GameRunner.getMessage("dragon")) {
	    player.character.setStrength(player.character.getStrength() + 2);
	}

    }

    @Override
    public void postBattleEffect(Player player, int playerRoll, Enemy enemy,
	    int enemyRoll, int result) {
	if (enemy.getType() == GameRunner.getMessage("dragon")) {
	    player.character.setStrength(player.character.getStrength() - 2);
	}
    }

}
