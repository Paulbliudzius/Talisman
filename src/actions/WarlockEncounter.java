package actions;

import game.EncounterSequence.GameState;
import game.GameFrame;
import game.Player;

/**
 * Class for Warlock Cove Space
 */
public class WarlockEncounter implements Action {

    @Override
    public void act(Player player) {
	if (player.talisman) {
	    new Safe().act(player);
	} else if (player.questComplete) {
	    player.hasTalisman(true);
	    new Safe().act(player);
	} else {
	    new Choice(2, new Action[] { new RecieveQuest(), new Safe() },
		    new String[] { "Get a Quest", "Deny Quest" }).act(player);
	}
    }

}
