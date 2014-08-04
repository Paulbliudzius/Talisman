package spells;

import game.EncounterSequence.GameState;
import game.Player;

/**
 * An interface for the spells.
 * 
 * @author roccoma. Created Mar 26, 2014.
 */
public abstract class Spell {

    /**
     * Instant unless otherwise specified.
     */
    public int duration = 0;

    /**
     * Returns the duration this spell's effect last. Returns 0 if spell is
     * instant and duration is inapplicable. Returns 1 if it last until the end
     * of the turn. Returns -1 if it last until some player interaction.
     * 
     */

    public String[] generateText() {
	String[] spells = new String[5];
	spells[0] = this.getName();
	spells[1] = this.gettext();
	return spells;
    }

    public int getDuration() {
	return this.duration;
    }

    /**
     * The different states that a card can be used in.
     */
    /**
     * Returns which state this spell can be used in.
     * 
     * @return GameRunner
     */
    public abstract GameState getUseState();

    /**
     * Grabs the name of the card.
     * 
     */
    public abstract String getName();

    /**
     * Get the text description of the card
     * 
     */
    public abstract String gettext();

    /**
     * Ability that targets another player, and possibly the owner.
     * 
     * @param target
     */
    public abstract void ability(Player target);

    /**
     * What happens when the cards duration ends?
     * 
     */
    public abstract void endOfDuration();

    /**
     * Returns a string containing "self", "enemy", "any_player","not_self"
     * "players" based on who the card effects.
     * 
     * @return
     */
    public abstract String getTarget();

}
