package enemy;

import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Apr 9, 2014.
 */
public class DuskwoodWraith extends Enemy {

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return "Duskwood Wraith";
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub.
	return "spirit";
    }

    @Override
    public String getCombatType() {
	// TODO Auto-generated method stub.
	return "craft";
    }

    @Override
    public int power() {
	// TODO Auto-generated method stub.
	return 2;
    }

    @Override
    public int getBounty() {
	// TODO Auto-generated method stub.
	return 3;
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
	return "The DuskWood Wraith will remain here until it is killed";
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
