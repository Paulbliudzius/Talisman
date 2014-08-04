package actions;

import game.Dice;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author bliudzpp. Created May 14, 2014.
 */
public class CryptEncounter implements Action {

    @Override
    public void act(Player player) {
	move(new Dice(3).roll(), player);
    }

    public void move(int roll, Player player) {
	int strength = player.character.getStrength();
	int calc = roll - strength;
	if (calc < 1) {
	    return;
	} else if (calc > 5) {
	    new MovePlayerPos(new int[] { 12, 0 }).act(player);
	} else if (calc == 1) {
	    new MovePlayerPos(new int[] { 0, 2 }).act(player);
	} else if (calc == 3 || calc == 2) {
	    new MovePlayerPos(new int[] { 0, 1 }).act(player);
	} else if (calc == 4 || calc == 5) {
	    new MovePlayerPos(new int[] { 8, 1 }).act(player);
	}
    }

}
