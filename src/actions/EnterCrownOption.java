package actions;

import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author bliudzpp. Created May 14, 2014.
 */
public class EnterCrownOption implements Action {

    @Override
    public void act(Player player) {
	if (player.talisman) {
	    new Choice(2, new Action[] { new MovePlayerPos(new int[] { 8, 2 }),
		    new Safe() }, new String[] { "Enter Crown", "Do nothing" })
		    .act(player);
	} else {
	    new Choice(1, new Action[] { new Safe() },
		    new String[] { "Need a Talisman" }).act(player);
	}

    }

}
