package actions;

import game.EncounterSequence.GameState;
import game.Player;

public class FightCharacters implements Action {
    private Player[] enemies;
    private String[] choices;

    public FightCharacters(Player[] enemies) {
	this.enemies = enemies;
    }

    @Override
    public void act(Player player) {
	Action[] actions = generateActions(player);
	new Choice(choices.length, actions, getChoicesString()).act(player);
    }

    public String[] getChoicesString() {
	return this.choices;
    }

    public Action[] generateActions(Player player) {
	int k = 1;
	if (player.checkAbilities(GameState.TAKE_TROPHY)) {
	    k += enemies.length;
	}
	choices = new String[enemies.length + k];
	Action[] actions = new Action[enemies.length + k];
	for (int i = 0; i < enemies.length; i++) {
	    choices[i] = enemies[i].getName();
	    actions[i] = new FightCharacter(enemies[i]);
	}
	if (k > 1) {
	    for (int j = enemies.length; j < choices.length - 1; j++) {
		choices[j] = "Trophy";// enemies[j-enemies.length].getName();
		actions[j] = new TakeTrophy(enemies[j - enemies.length]);
	    }
	}
	if (player.getPosition()[0] != 8 || player.getPosition()[1] != 2) {
	    choices[choices.length - 1] = "Don't Fight";
	    actions[choices.length - 1] = new EncounterSpace();
	}
	return actions;
    }
}
