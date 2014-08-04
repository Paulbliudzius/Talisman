package enemy;

import game.GameRunner;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 15, 2014.
 */
public class Ghast extends Enemy {

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return GameRunner.messages.getString("ghast");
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub.
	return GameRunner.messages.getString("spirit");
    }

    @Override
    public String getCombatType() {
	// TODO Auto-generated method stub.
	return GameRunner.messages.getString("craft");
    }

    @Override
    public int power() {
	// TODO Auto-generated method stub.
	return 5;
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
    public void enemyWinsSideEffect(Player player) {
	player.character.addStrength(-1);

    }

    @Override
    public void playerWinsSideEffect(Player player) {
	// TODO Auto-generated method stub.

    }

    @Override
    public String getDescription() {
	// TODO Auto-generated method stub.
	return GameRunner.messages.getString("ghastDesc");
    }

    @Override
    public String getSound() {
	// TODO Auto-generated method stub.
	return null;
    }

}
