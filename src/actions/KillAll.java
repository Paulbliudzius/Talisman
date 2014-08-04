package actions;

import java.util.ArrayList;

import game.GameRunner;
import game.Player;

/**
 * For making everyone but the character
 */
public class KillAll implements Action {

    @Override
    public void act(Player player) {
	kill(GameRunner.players, player);

    }

    public void kill(ArrayList<Player> list, Player player) {
	int size = list.size();
	for (int count = 0; count < size; count++) {
	    if (!player.equals(list.get(count))) {
		new StatChange("Life", -1).act(list.get(count));
	    }
	}
    }

}