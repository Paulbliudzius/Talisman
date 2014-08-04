package enemy;

import game.Player;

/**
 * Used on the Tavern
 */
public class Brigand extends Enemy {

    @Override
    public String getName() {
	return "Brigand";
    }

    @Override
    public String getType() {
	return "human";
    }

    @Override
    public String getCombatType() {
	return "strength";
    }

    @Override
    public int power() {
	return 4;
    }

    @Override
    public int getBounty() {
	return 4;
    }

    @Override
    public void postDefeatAbility() {
	// TODO Auto-generated method stub.

    }

    @Override
    public void ability() {

    }

    @Override
    public String getDescription() {
	return "The Brigand";
    }

    @Override
    public String getSound() {
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
