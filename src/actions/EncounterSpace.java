package actions;

import game.GameRunner;
import game.Player;

public class EncounterSpace implements Action{

    @Override
    public void act(Player player) {
	GameRunner.map.getSpace(player.getPosition()).interact(player);
	if (player.getDrawnCard() != null) {
	    player.useCard();
	}	
    }

}
