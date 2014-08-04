package enemy;

import game.GameRunner;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Apr 9, 2014.
 */
public class Pitfiend extends Enemy {

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return "Pitfiend";
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub.
	return "Pitfiend";
    }

    @Override
    public String getCombatType() {
	// TODO Auto-generated method stub.
	return "strength";
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
	GameRunner.players.get(GameRunner.playerturn).stayedTurn(
		GameRunner.players.get(GameRunner.playerturn).stay());

    }

    @Override
    public void ability() {
	// TODO Auto-generated method stub.

    }

    @Override
    public String getDescription() {
	// TODO Auto-generated method stub.
	return "Defeat them all.";
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
