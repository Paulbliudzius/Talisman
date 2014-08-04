package enemy;

import game.Player;

/**
 * Used for the Crags
 */
public class Spirit extends Enemy {
    public int[] pos;

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return "Spirit";
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
	return 4;
    }

    @Override
    public int getBounty() {
	// TODO Auto-generated method stub.
	return 0;
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
	return "The evil spirit.";
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
