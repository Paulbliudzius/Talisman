package spells;

import game.EncounterSequence.GameState;
import game.GameRunner;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 15, 2014.
 */
public class Random extends Spell {

    public int seed;

    public Random() {
	seed = 1 + (int) (Math.random() * ((6 - 1) + 1));

    }

    @Override
    public GameState getUseState() {
	// TODO Auto-generated method stub.
	return GameState.ANY;
    }

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return GameRunner.getMessage("random");
    }

    @Override
    public String gettext() {
	// TODO Auto-generated method stub.
	return GameRunner.getMessage("randomDesc");

    }

    @Override
    public void ability(Player target) {
	if (this.seed == 1) {
	    target.addStrength(1);

	} else if (this.seed == 2) {
	    target.addStrength(-1);
	} else if (this.seed == 3) {
	    target.addCraft(1);
	} else if (this.seed == 4) {
	    target.addCraft(-1);
	} else if (this.seed == 5) {
	    target.addLife(-1);
	} else {
	    target.setGold(0);
	}
	// TODO Auto-generated method stub.

    }

    @Override
    public void endOfDuration() {
	// TODO Auto-generated method stub.

    }

    @Override
    public String getTarget() {
	return "player";
    }

}
