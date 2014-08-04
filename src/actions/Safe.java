package actions;

import game.Player;

/**
 * Safe Action does nothing.
 */
public class Safe implements Action {

    @Override
    public void act(Player player) {
	return;
    }

}
