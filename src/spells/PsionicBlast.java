package spells;

import game.EncounterSequence.GameState;
import game.GameRunner;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 15, 2014.
 */
public class PsionicBlast extends Spell {
    public Player current;

    public PsionicBlast() {
	this.duration = 1;
    }

    @Override
    public GameState getUseState() {
	// TODO Auto-generated method stub.
	return GameState.ANY;
    }

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return GameRunner.getMessage("psionicBlast");
    }

    @Override
    public String gettext() {
	// TODO Auto-generated method stub.
	return GameRunner.getMessage("psionicBlastDesc");
    }

    @Override
    public void ability(Player target) {
	this.current = target;
	target.addStrength(target.character.getCraft());
	// TODO Auto-generated method stub.

    }

    @Override
    public void endOfDuration() {
	this.current.addStrength(-this.current.character.getCraft());

    }

    @Override
    public String getTarget() {
	// TODO Auto-generated method stub.
	return "self";
    }

}
