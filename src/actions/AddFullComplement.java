package actions;

import game.Player;

public class AddFullComplement implements Action {

    @Override
    public void act(Player player) {
	int toAdd = (player.character.getCraft()-player.spells.size())/2;
	for (int i = 0; i < toAdd; i++) {
	    new DrawSpell().act(player);
	}
    }

}
