package adventureCards;

import actions.Action;
import game.GameRunner;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 */
public class Talisman implements Action {

    @Override
    public void act(Player player) {
	player.hasTalisman(true);
    }
}
