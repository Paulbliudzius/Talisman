package actions;

import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author bliudzpp. Created Apr 20, 2014.
 */
public class RandomMove implements Action {
    private int region;

    public RandomMove(int region) {
	this.region = region;
    }

    @Override
    public void act(Player player) {
	player.movePos(new int[] {
		(int) (1 + (Math.random() * 8 * (3 - this.region))),
		this.region });

    }

}
