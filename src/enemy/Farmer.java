package enemy;

import game.Player;

/**
 * Used on the Tavern
 */
public class Farmer extends Enemy {

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return "Farmer";
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub.
	return "human";
    }

    @Override
    public String getCombatType() {
	// TODO Auto-generated method stub.
	return "strength";
    }

    @Override
    public int power() {
	// TODO Auto-generated method stub.
	return 3;
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
	return "The Farmer";
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
