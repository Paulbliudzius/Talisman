package actions;

import game.Player;

/**
 * TODO Makes a player lose a turn
 */
public class LoseTurn implements Action {
    private int turnsLost;

    public LoseTurn(int turns) {
	this.turnsLost = turns;
    }

    @Override
    public void act(Player player) {
	player.loseTurns(this.turnsLost);
    }
}
