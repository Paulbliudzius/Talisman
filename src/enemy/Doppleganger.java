package enemy;

import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 12, 2014.
 */
public class Doppleganger extends Enemy {

    private int strength = 0;

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return "DoppleGanger";
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub.
	return "monster";
    }

    @Override
    public String getCombatType() {
	// TODO Auto-generated method stub.
	return "strength";
    }

    @Override
    public int power() {
	// TODO Auto-generated method stub.
	return this.strength;
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
    public void enemyPreBattle(Player player, int playerRoll) {
	this.strength = player.character.getStrength();
    }

    @Override
    public void enemyWinsSideEffect(Player player) {
	// TODO Auto-generated method stub.

    }

    @Override
    public void playerWinsSideEffect(Player player) {
	// TODO Auto-generated method stub.

    }

    @Override
    public String getDescription() {
	// TODO Auto-generated method stub.
	return "Your strength, is my strength";
    }

    @Override
    public String getSound() {
	// TODO Auto-generated method stub.
	return null;
    }

}
