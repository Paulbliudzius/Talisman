package adventureCards;

import game.GameRunner;
import game.Player;
import actions.Action;
import actions.DisplayChange;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 1, 2014.
 */
public class FoolsGold implements Action {

    @Override
    public void act(Player player) {
	String desc = GameRunner.messages.getString("foolsGold");
	effect(player);
	DisplayChange change = new DisplayChange(desc);
	change.act(player);

    }

    /**
     * TODO Put here a description of what this method does.
     * 
     * @param player
     */
    public void effect(Player player) {
	player.setGold(0);
    }

}
