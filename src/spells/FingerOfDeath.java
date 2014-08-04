package spells;

import game.EncounterSequence.GameState;
import game.GameRunner;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 15, 2014.
 */
public class FingerOfDeath extends Spell {

    @Override
    public GameState getUseState() {
	// TODO Auto-generated method stub.
	return GameState.ANY;
    }

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return GameRunner.messages.getString("fingerOfDeath");
    }

    @Override
    public String gettext() {
	// TODO Auto-generated method stub.
	return GameRunner.getMessage("fingerOfDeathDesc");
    }

    @Override
    public void ability(Player target) {
	target.addLife(-2);

    }

    @Override
    public void endOfDuration() {
	// TODO Auto-generated method stub.

    }

    @Override
    public String getTarget() {
	// TODO Auto-generated method stub.
	return "player";
    }

}
