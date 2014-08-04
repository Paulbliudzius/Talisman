package adventureCards;

import game.Player;
import actions.Action;
import actions.DisplayChange;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 1, 2014.
 */
public class bagOfGold implements Action {
    public int gold;

    public bagOfGold(int amount) {

	this.gold = amount;
    }

    @Override
    public void act(Player player) {
	effect(player);
	DisplayChange display = new DisplayChange("You found a bag with "
		+ this.gold + " gold. What are the odds?");
	display.act(player);
    }

    /**
     * TODO Put here a description of what this method does.
     * 
     * @param player
     */
    public void effect(Player player) {
	player.setGold(player.getGold() + this.gold);

    }

}
