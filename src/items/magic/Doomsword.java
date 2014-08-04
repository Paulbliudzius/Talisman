package items.magic;

import enemy.Enemy;
import game.Player;
import items.weapons.Weapon;

public class Doomsword extends Weapon {
    public boolean equipped = false;

    @Override
    public String getType() {
	return "magic";
    }

    @Override
    public String getItemName() {
	return "Doomsword";
    }

    @Override
    public String getItemDescription() {
	return "Add 3 to your Strength during battle."
		+ " If you roll a 1 for your attack roll, "
		+ "you lose a life.";
    }

    @Override
    public Boolean hasEffect() {
	return true;
    }

    @Override
    public void effect() {
	/**
	 * TODO: if roll == 1 defeated and lose life
	 */
    }

    @Override
    public int strengthBonus() {
	return 3;
    }

    @Override
    public int craftBonus() {
	return 0;
    }

    @Override
    public void equipEffect(Player player) {
	this.equipped = true;
	player.magicEquipped = true;
	player.character.setStrength(player.character.getStrength() + 3);

    }

    @Override
    public void unequipEffect(Player player) {
	this.equipped = false;
	player.magicEquipped = false;
	player.character.setStrength(player.character.getStrength() - 3);

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
	if (playerRoll == 1) {
	    player.character.setCurrentLife(player.character.getLife() - 1);
	}

    }

}
