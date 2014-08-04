package spells;

import game.EncounterSequence.GameState;
import game.Player;

/**
 * The Energize Spell
 * 
 * @author roccoma. Created Mar 31, 2014.
 */
public class Energize extends Spell {

    game.Character playerCharacter;
    private int initialStrength;

    public Energize() {
    }

    @Override
    public int getDuration() {
	return this.duration;
    }

    @Override
    public GameState getUseState() {
	return GameState.ANY;
    }

    @Override
    public String getName() {
	return "Energize";
    }

    @Override
    public String gettext() {
	return "Cast on yourself at any time or any other player. Your Strength value is doubled until the end of the turn.";
    }

    @Override
    public void ability(Player target) {
	this.playerCharacter = target.character;
	this.initialStrength = target.character.getStrength();
	this.playerCharacter.setStrength(this.initialStrength * 2);
    }

    @Override
    public void endOfDuration() {
	this.playerCharacter.setStrength(this.initialStrength);

    }

    @Override
    public String getTarget() {
	// TODO Auto-generated method stub.
	return "self";
    }

}
