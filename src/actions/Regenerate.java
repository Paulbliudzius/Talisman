package actions;

import game.GameFrame;
import game.Player;

/**
 * Class for interface of selecting which heal to use
 */
public class Regenerate implements Action {

    @Override
    public void act(Player player) {
	if (player.character.getLife() < player.character.getMaxLife()) {
	    player.character.setCurrentLife(player.character.getLife() + 1);
	    GameFrame.sequence.endTurn();
	}
    }

}
