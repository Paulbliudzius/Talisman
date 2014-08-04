package actions;

import game.GameRunner;
import game.Player;

public class DrawSpell implements Action {

    @Override
    public void act(Player player) {
	player.spells.add(GameRunner.spellDeck.getSpell());
    }

}
