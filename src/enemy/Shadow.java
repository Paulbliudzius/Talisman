package enemy;

import game.GameRunner;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 14, 2014.
 */
public class Shadow extends Enemy {

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return GameRunner.messages.getString("shadow");
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub.
	return GameRunner.messages.getString("shadow");
    }

    @Override
    public String getCombatType() {
	// TODO Auto-generated method stub.
	return GameRunner.messages.getString("craft");
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
    public void enemyWinsSideEffect(Player player) {
	player.character.addCraft(-1);
	player.character.addLife(1);

    }

    @Override
    public void playerWinsSideEffect(Player player) {
	// TODO Auto-generated method stub.

    }

    @Override
    public String getDescription() {
	// TODO Auto-generated method stub.
	return GameRunner.messages.getString("shadowDesc");
    }

    @Override
    public String getSound() {
	// TODO Auto-generated method stub.
	return null;
    }

}
