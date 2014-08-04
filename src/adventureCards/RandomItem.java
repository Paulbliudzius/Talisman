package adventureCards;

import game.GameRunner;
import game.Player;
import items.Item;
import actions.Action;
import actions.DisplayChange;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 8, 2014.
 */
public class RandomItem implements Action {

    @Override
    public void act(Player player) {
	Item item = GameRunner.itemDeck.getItem();
	String result = effect(player, item);
	DisplayChange change = new DisplayChange(result);
	change.act(player);

    }

    public String effect(Player player, Item item) {
	if (player.items.size() < player.inventoryCap) {
	    player.items.add(item);
	    return "<html> You found a " + item.getItemName() + "<br>"
		    + item.getItemDescription() + " <br>" + "Type: "
		    + item.getType() + "</html>";
	} else {
	    return "<html> You found a " + item.getItemName() + "<br>"
		    + "but your inventory is already full</hmtl>";
	}
    }

}
