package actions;

import game.EncounterSequence.GameState;
import game.GameFrame;
import game.Player;

/**
 * Class for interface of selecting which heal to use
 */
public class HealForGold implements Action {
    private int total = 0;
    private String[] choices;

    @Override
    public void act(Player player) {
	Action[] actions = generateActions(player);
	new Choice(choices.length, actions, getChoicesString()).act(player);
    }

    public String[] getChoicesString() {
	return this.choices;
    }

    public Action[] generateActions(Player player) {
	int life = player.character.getMaxLife() - player.character.getLife();
	int gold = player.getGold();
	if (life < gold) {
	    total = life;
	} else {
	    total = gold;
	}
	Action[] actions = new Action[total + 1];
	choices = new String[total + 1];
	for (int count = 0; count < total + 1; count++) {
	    choices[count] = "Heal " + count;
	    actions[count] = new HealPayGold(count);
	}
	return actions;
    }

}
