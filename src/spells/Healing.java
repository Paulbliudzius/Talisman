package spells;

import game.EncounterSequence.GameState;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Mar 31, 2014.
 */
public class Healing extends Spell {

    @Override
    public GameState getUseState() {
	// TODO Auto-generated method stub.
	return GameState.ANY;
    }

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return "Healing";
    }

    @Override
    public String gettext() {
	// TODO Auto-generated method stub.
	return "Cast as required. By casting it on yourself or any other character the recipient is healed up to his life value.";
    }

    @Override
    public void ability(Player target) {
	target.character.setCurrentLife(target.character.getMaxLife());
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
