package adventureCards;

import game.GameRunner;
import game.Player;
import actions.Action;
import actions.DisplayChange;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 8, 2014.
 */
public class BlackHole implements Action {

    @Override
    public void act(Player player) {
	DisplayChange change = new DisplayChange(
		"<html>A black hole consumes all enemies on the board <br>"
			+ "and all your items <br> unless you have more than 7 strength </html>");
	GameRunner.enemiesOnBoard.clear();
	effect(player);
	change.act(player);
    }

    public void effect(Player player) {
	if (player.character.getStrength() >= 7) {
	    // do nothing
	} else {
	    player.removeAndUnequippedItems();
	}
    }
}
