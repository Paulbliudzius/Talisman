package spells;

import game.EncounterSequence.GameState;
import game.GameRunner;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 15, 2014.
 */
public class Brainwave extends Spell {

    public Player target;

    @Override
    public GameState getUseState() {
	return GameState.ANY;
    }

    @Override
    public String getName() {
	return GameRunner.getMessage("brainWave");
    }

    @Override
    public String gettext() {
	return GameRunner.getMessage("brainWaveDesc");

    }

    @Override
    public void ability(Player target) {
	target.addCraft(3);
	this.target = target;
    }

    @Override
    public void endOfDuration() {
	this.target.addCraft(-3);

    }

    @Override
    public String getTarget() {
	return "self";
    }

}
