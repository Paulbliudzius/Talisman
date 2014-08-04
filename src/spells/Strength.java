package spells;

import game.EncounterSequence.GameState;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Mar 31, 2014.
 */
public class Strength extends Spell {

    private Player currentPlayer;

    public Strength() {
	this.duration = 1;
    }

    @Override
    public GameState getUseState() {
	return GameState.BEFORE_ROLL;
    }

    @Override
    public String getName() {
	return "Strength";
    }

    @Override
    public String gettext() {
	return "Cast on yourself at the start of your turn. Gain 1 Strength until the end of the turn.";
    }

    @Override
    public void endOfDuration() {
	this.currentPlayer.character.setStrength(this.currentPlayer.character
		.getStrength() - 1);
    }

    @Override
    public void ability(Player target) {
	this.currentPlayer = target;
	target.character.setStrength(target.character.getStrength() + 1);
    }

    @Override
    public String getTarget() {
	return "self";
    }
}
