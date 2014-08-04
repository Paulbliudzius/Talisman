package items.weapons;

import game.Player;

public class Sword extends Weapon {
    public boolean equipped = false;

    @Override
    public String getItemName() {
	return "Sword";
    }

    @Override
    public String getItemDescription() {
	return "Add 1 to your Strength during battle.";
    }

    @Override
    public Boolean hasEffect() {
	return false;
    }

    @Override
    public void effect() {
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
	player.equipWeapon();
	player.character.setStrength(player.character.getStrength()
		+ this.strengthBonus());
	this.equipped = true;

    }

    @Override
    public void unequipEffect(Player player) {
	player.unequipWeapon();
	player.character.setStrength(player.character.getStrength()
		- this.strengthBonus());
	this.equipped = false;

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

}
