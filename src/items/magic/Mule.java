package items.magic;

import game.GameRunner;
import game.Player;
import items.Item;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 15, 2014.
 */
public class Mule extends Item {

    @Override
    public String getItemName() {
	// TODO Auto-generated method stub.
	return GameRunner.getMessage("mule");
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub.
	return "Object";
    }

    @Override
    public String getItemDescription() {
	// TODO Auto-generated method stub.
	return GameRunner.getMessage("muleDesc");
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
	player.inventoryCap = player.inventoryCap * 2;
	player.muleEquipped = true;
	this.equipped = true;
    }

    @Override
    public void unequipEffect(Player player) {
	player.inventoryCap = player.inventoryCap / 2;
	player.muleEquipped = false;
	this.equipped = false;
    }

    @Override
    public int getCost() {
	// TODO Auto-generated method stub.
	return 0;
    }

}
