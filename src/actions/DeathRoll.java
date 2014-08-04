package actions;

import game.Dice;
import game.Player;

/**
 * Used for space dice with death
 */
public class DeathRoll implements Action {

    @Override
    public void act(Player player) {
	new RollDice(12, generateActions(new Dice(2).roll())).act(player);
    }

    public Action[] generateActions(int roll) {
	Action[] actions = new Action[12];
	for (int i = 0; i < roll - 1; i++) {
	    actions[i] = new MultiAction(2, new Action[] {
		    new StatChange("Life", -1), new StayNextTurn(1) });
	}
	actions[roll - 1] = new StayNextTurn(1);
	for (int i = roll; i < 12; i++) {
	    actions[i] = new Safe();
	}
	return actions;
    }

}
