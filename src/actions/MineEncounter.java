package actions;

import game.Dice;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author bliudzpp. Created May 14, 2014.
 */
public class MineEncounter implements Action {
    int dice = 3;
    @Override
    public void act(Player player) {
	if(!player.character.getName().equals("Dwarf"))
	    dice--;
	move(new Dice(dice).roll(), player);
    }

    public void move(int roll, Player player) {
	int strength = player.character.getCraft();
	int calc = roll - strength;
	if (calc < 1) {
	    return;
	} else if (calc > 5) {
	    new MovePlayerPos(new int[] { 18, 0 }).act(player);
	} else if (calc == 1) {
	    new MovePlayerPos(new int[] { 0, 2 }).act(player);
	} else if (calc == 3 || calc == 2) {
	    new MovePlayerPos(new int[] { 0, 1 }).act(player);
	} else if (calc == 4 || calc == 5) {
	    new MovePlayerPos(new int[] { 8, 1 }).act(player);
	}
    }

}
