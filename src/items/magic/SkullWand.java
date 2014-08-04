package items.magic;

import game.Player;
import items.weapons.Weapon;

public class SkullWand extends Weapon {
    public boolean equipped = false;

    @Override
    public String getItemName() {
	return "Skull Wand";
    }

    @Override
    public String getItemDescription() {
	return "No good character may have the Skull Wand."
		+ " Add 1 to your Strength in battle and 2 to your Craft in psychic combat.";
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
	return 2;
    }

    @Override
    public void equipEffect(Player player) {
	if (player.character.getAlignmentString() == "Good") {
	    player.items.remove(this);
	} else {
	    player.character.setStrength(player.character.getStrength() + 1);
	    player.character.setCraft(player.character.getCraft() + 2);
	    player.magicEquipped = true;
	    this.equipped = true;
	}

    }

    @Override
    public void unequipEffect(Player player) {
	player.character.setStrength(player.character.getStrength() - 1);
	player.character.setCraft(player.character.getCraft() - 2);
	player.magicEquipped = false;
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
