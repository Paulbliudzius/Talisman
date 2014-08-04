package enemy;

import game.GameRunner;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Apr 9, 2014.
 */
public class Ogre extends Enemy {

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return GameRunner.getMessage("orge");
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub.
	return GameRunner.getMessage("monster");
    }

    @Override
    public String getCombatType() {
	// TODO Auto-generated method stub.
	return GameRunner.getMessage("strength");
    }

    @Override
    public int power() {
	// TODO Auto-generated method stub.
	return 5;
    }

    @Override
    public int getBounty() {
	// TODO Auto-generated method stub.
	return 2;
    }

    @Override
    public void postDefeatAbility() {
	// TODO Auto-generated method stub.

    }

    @Override
    public void ability() {
	// TODO Auto-generated method stub.

    }

    @Override
    public String getDescription() {
	// TODO Auto-generated method stub.
	return "RUN! ITS AN OOOGRRRREEE!";
    }

    @Override
    public String getSound() {
	// TODO Auto-generated method stub.
	return null;
    }

    @Override
    public void enemyWinsSideEffect(Player player) {
	// TODO Auto-generated method stub.

    }

    @Override
    public void playerWinsSideEffect(Player player) {
	// TODO Auto-generated method stub.

    }

}
