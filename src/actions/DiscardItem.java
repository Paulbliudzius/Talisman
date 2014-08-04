package actions;

import items.Item;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author bliudzpp. Created Apr 19, 2014.
 */
public class DiscardItem implements Action {
    private int discard;
    private Item item;

    public DiscardItem(int discardItem) {
	this.discard = discardItem;
    }

    public DiscardItem(Item item) {
	this.item=item;
    }

    @Override
    public void act(Player player) {
	if(item==null)
	    player.removeItem(this.discard);
	else
	    player.removeItem(item);
    }
}
