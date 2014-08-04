package game.abilities;

import game.GameRunner;
import game.EncounterSequence.GameState;

public class Ghoul extends BaseAbility{

    public Ghoul() {
	abilities = new CharacterAbilities[2];
	//TODO: add raised follower
	abilities[0] = new CharacterAbilities(GameRunner.getMessage("combatChoice")) {
	    @Override
	    public void effect() {
	    }
	}.addUsableState(GameState.COMBAT_CHOICE);	
	abilities[1] = new CharacterAbilities(GameRunner.getMessage("pyschicWin")) {
	    @Override
	    public void effect() {
		player.character.addLife(1);
	    }
	}.addUsableState(GameState.WON_COMBAT);
    }

}
