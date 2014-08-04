package spells;

import game.EncounterSequence.GameState;
import game.GameFrame;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Apr 3, 2014.
 */
public class GustOfWind extends Spell {

    @Override
    public GameState getUseState() {
	// TODO Auto-generated method stub.
	return GameState.MOVEMENT_CHOICE;
    }

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return "Gust Of Wind";
    }

    @Override
    public String gettext() {
	// TODO Auto-generated method stub.
	return "Summon a gust of wind that doubles your last movement roll";
    }

    @Override
    public void ability(Player target) {
	target.setLastRoll(target.getLastRoll() * 2);
	// TODO commented out for testing. need to implement way to change it
	// and be testable
	// GameFrame.lastRoll.setText("Now " + target.getLastRoll() * 2);

    }

    @Override
    public void endOfDuration() {
	// TODO Auto-generated method stub.

    }

    @Override
    public String getTarget() {
	// TODO Auto-generated method stub.
	return "self";
    }

}
