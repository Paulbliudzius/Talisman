package enemy;

import game.GameRunner;
import game.Player;

/**
 * 
 * Should only be used on the 4,0 space.
 */
public class Sentinel extends Enemy {

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return "Sentinel";
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub.
	return "guardian";
    }

    @Override
    public String getCombatType() {
	// TODO Auto-generated method stub.
	return "strength";
    }

    @Override
    public int power() {
	// TODO Auto-generated method stub.
	return 9;
    }

    @Override
    public int getBounty() {
	// TODO Auto-generated method stub.
	return 0;
    }

    @Override
    public void postDefeatAbility() {
	GameRunner.players.get(GameRunner.playerturn).killedSent();
	GameRunner.enemiesOnBoard.remove(this);
    }

    @Override
    public void ability() {
	// TODO Auto-generated method stub.

    }

    @Override
    public String getDescription() {
	// TODO Auto-generated method stub.
	return "Defeat the sentinel to get to the middle region.";
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
