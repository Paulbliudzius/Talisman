package actions;

import game.Player;

/**
 * Stay the next turn
 */
public class StayNextTurn implements Action {
    private int turnsLost;

    public StayNextTurn(int turns) {
	this.turnsLost = turns;
    }

    @Override
    public void act(Player player) {
	if (player.stay() == 0) {
	    player.stayPosition(this.turnsLost);
	}
	if (player.stay() == -1) {
	    player.stayedTurn(-1);
	}
    }
}
