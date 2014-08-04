package actions;

import game.EncounterSequence.GameState;
import game.GameFrame;
import game.Player;

/**
 * Class for interface of selecting which heal to use
 */
public class PrinceSpace implements Action {

    @Override
    public void act(Player player) {
	int size = player.followers.size();
	for (int i = 0; i < size; i++) {
	    String followname = player.followers.get(i).getName();
	    if (followname.equals("Prince") || followname.equals("Princess")) {
		new StatChange("Life", player.character.getMaxLife()
			- player.character.getLife()).act(player);
		return;
	    }
	}
	new HealForGold().act(player);
    }

}
