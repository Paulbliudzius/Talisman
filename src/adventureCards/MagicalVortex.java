package adventureCards;

import game.GameRunner;
import game.Player;
import actions.Action;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 14, 2014.
 */
public class MagicalVortex implements Action {

    @Override
    public void act(Player player) {
	String desc = GameRunner.messages.getString("magicVortex");

    }

    public void effect(Player player) {
	for (Player p : GameRunner.players) {
	    p.spells.clear();
	}
    }

}
