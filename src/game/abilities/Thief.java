package game.abilities;

import actions.Steal;
import actions.TakeFromPlayer;
import game.GameFrame;
import game.GameRunner;
import game.Player;
import game.EncounterSequence.GameState;

public class Thief extends BaseAbility {

    public Thief() {
	abilities = new CharacterAbilities[2];
	abilities[0] = new CharacterAbilities(GameRunner.getMessage("rob")) {
	    @Override
	    public void effect() {

		if (GameRunner.map.containsCharacter(GameRunner.players.get(
			GameRunner.playerturn).getPosition())) {
		    GameFrame.actionButton("Steal", new TakeFromPlayer());
		}
	    }
	}.addUsableState(GameState.MOVEMENT_CHOICE);
	// TODO:Change usableState
	abilities[1] = new CharacterAbilities(GameRunner.getMessage("villageThief")) {
	    @Override
	    public void effect() {
		GameFrame.actionButton("Steal", new Steal());
	    }
	}.addUsableState(GameState.MOVEMENT_CHOICE);

    }

}
