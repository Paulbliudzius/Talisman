package items.magic;

import game.Player;
import items.Item;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 8, 2014.
 */
public class MagicRing extends Item {

    private boolean equipped = false;

    @Override
    public String getItemName() {
	// TODO Auto-generated method stub.
	return "Magic Ring";
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub.
	return "magic";
    }

    @Override
    public String getItemDescription() {
	// TODO Auto-generated method stub.
	return "+1 strength, +1 craft";
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
	player.character.setStrength(player.character.getStrength() + 1);
	player.character.setCraft(player.character.getCraft() + 1);
	player.magicEquipped = true;
	this.equipped = true;
    }

    @Override
    public void unequipEffect(Player player) {
	player.character.setStrength(player.character.getStrength() - 1);
	player.character.setCraft(player.character.getCraft() - 1);
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
	// TODO Auto-generated method stub.
	return 0;
    }

}
