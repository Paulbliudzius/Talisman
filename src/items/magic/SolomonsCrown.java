package items.magic;

import game.Player;
import items.Item;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 12, 2014.
 */
public class SolomonsCrown extends Item {

    @Override
    public String getItemName() {
	// TODO Auto-generated method stub.
	return "Solomon's Crown";
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub.
	return "magic";
    }

    @Override
    public String getItemDescription() {
	// TODO Auto-generated method stub.
	return "+ 2 craft";
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
	player.magicEquipped = true;
	this.equipped = true;
	player.character.addCraft(2);

    }

    @Override
    public void unequipEffect(Player player) {
	player.magicEquipped = false;
	this.equipped = false;
	player.character.addCraft(-2);

    }

    @Override
    public int getCost() {
	// TODO Auto-generated method stub.
	return 0;
    }

}
