package actions;

import game.Player;

/**
 * Action to move a player to a position
 */
public class MovePlayerPos implements Action {
    private int[] pos;

    public MovePlayerPos(int[] pos) {
	this.pos = new int[] { pos[0], pos[1] };
    }

    @Override
    public void act(Player player) {
	player.movePos(this.pos);
    }

}
