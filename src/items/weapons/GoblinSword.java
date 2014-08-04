package items.weapons;

import game.Player;
import actions.StatChange;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 8, 2014.
 */
public class GoblinSword extends Weapon {

    public boolean equipped = false;

    @Override
    public String getItemName() {
	// TODO Auto-generated method stub.
	return "Goblin Sword";
    }

    @Override
    public String getItemDescription() {
	// TODO Auto-generated method stub.
	return "<html>The rare goblin sword: <br>+4 Strength, +1 craft<br>"
		+ "If you unequipped or loose the sword, you lose 2 lives,<br> unless it would kill you.";
    }

    @Override
    public Boolean hasEffect() {
	// TODO Auto-generated method stub.
	return false;
    }

    @Override
    public void effect() {
	// TODO Auto-generated method stub.

    }

    @Override
    public void equipEffect(Player player) {
	StatChange change = new StatChange("Strength", 4);
	change.act(player);
	StatChange change2 = new StatChange("Craft", 1);
	change2.act(player);
	this.equipped = true;
	player.equipWeapon();
    }

    @Override
    public void unequipEffect(Player player) {
	player.character.setStrength(player.character.getStrength() - 4);
	player.character.setStrength(player.character.getCraft() - 1);
	if (player.character.getLife() >= 3) {
	    player.character.setCurrentLife(player.character.getLife() - 2);
	} else if (player.character.getLife() == 2) {
	    player.character.setCurrentLife(1);
	}
	player.unequipWeapon();
	this.equipped = false;
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
    public int strengthBonus() {
	// TODO Auto-generated method stub.
	return 4;
    }

    @Override
    public int craftBonus() {
	// TODO Auto-generated method stub.
	return 1;
    }

}
