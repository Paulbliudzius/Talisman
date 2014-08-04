package adventureCards;

import actions.Action;
import actions.DisplayChange;
import game.Character.Alignment;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Apr 16, 2014.
 */
public class Devil implements Action {

    @Override
    public void act(Player player) {

	String effect = ("<html> You are visited by the devil. <br>Alignment Good: -1 life <br>Alignment Neutral: No effect <br> Alignment Evil: +1 life ");
	String desc = effect(player);
	DisplayChange change = new DisplayChange(effect + desc);
	change.act(player);
    }

    /**
     * TODO Put here a description of what this method does.
     * 
     * @param player
     */
    public String effect(Player player) {
	if (player.character.getAlignment() == Alignment.EVIL) {
	    player.character.setCurrentLife(player.character.getLife() + 1);
	    return "You gain a life </html>";
	} else if (player.character.getAlignment() == Alignment.GOOD) {
	    player.character.setCurrentLife(player.character.getLife() - 1);
	    return "You loose a life </html>";
	} else {
	    return "No effect </html";
	}
    }
    // TODO Auto-generated method stub.

}
