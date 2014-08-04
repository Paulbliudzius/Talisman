package spells;

import game.EncounterSequence.GameState;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Mar 31, 2014.
 */
public class Nullify extends Spell {

    @Override
    public GameState getUseState() {
	return GameState.ANY;
    }

    @Override
    public String getName() {
	return "Nullify";
    }

    @Override
    public String gettext() {
	// TODO Auto-generated method stub.
	return "Cast at any time on any character. That character must discard all of his Spells.";
    }

    @Override
    public void ability(Player target) {
	target.spells.clear();
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
