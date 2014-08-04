package actions;

import game.GameRunner;
import game.Player;

/**
 * 
 */
public class DrawCard implements Action {
    private int cardsAmount;

    public DrawCard(int amountParam) {
	this.cardsAmount = amountParam;
    }

    @Override
    public void act(Player player) {
	player.drawCard(GameRunner.adventureDeck.takeTop());
    }

}
