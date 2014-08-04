package actions;

import items.Item;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author bliudzpp. Created Apr 19, 2014.
 */
public class BuyItem implements Action {
    protected Item item;
    private int price;

    public BuyItem(Item itemParam, int price) {
	this.item = itemParam;
	this.price = price;
    }

    @Override
    public void act(Player player) {
	if (player.getItems().size() < 4
		&& player.getGold() >= this.price) {
	    player.addItem(this.item);
	    player.setGold(player.getGold() - price);
	} else {
	    // Discard Option should be done in the actual interface under
	    // inventory, so have them delete item
	}
    }
}
