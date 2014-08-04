package items.weapons;

import enemy.Enemy;
import game.Player;

public class Axe extends Weapon {
    public boolean equipped = false;

    @Override
    public String getItemName() {
	return "Axe";
    }

    @Override
    public String getItemDescription() {
	return "Add 1 to your Strength during battle.";
    }

    @Override
    public Boolean hasEffect() {
	return true;
    }

    @Override
    public void effect() {
	/*
	 * TODO: if in woods, allow raft building
	 */

    }

    @Override
    public int strengthBonus() {
	return 1;
    }

    @Override
    public int craftBonus() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public void equipEffect(Player player) {
	player.equipWeapon();
	player.character.addStrength(1);
	this.equipped = true;
    }

    @Override
    public void unequipEffect(Player player) {
	player.unequipWeapon();
	player.character.addStrength(-1);
	this.equipped = false;
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
