package actions;

import game.Player;

/**
 * Structure for the actions that can happen in multiple instances within the
 * game
 */
public interface Action {

    /**
     * This code will contain an action within the board, the actual running of
     * the action.
     * 
     */
    public void act(Player player);
}
