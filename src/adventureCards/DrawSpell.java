package adventureCards;

import actions.Action;
import game.GameRunner;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 */
public class DrawSpell implements Action {

    @Override
    public void act(Player player) {
	player.spells.add(GameRunner.spellDeck.getSpell());
    }
}
