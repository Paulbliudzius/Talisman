package enemy;

import game.Dice;
import game.GameRunner;
import game.Player;

/**
 * Used for the Werefold Den board Space
 */
public class Werewolf extends Enemy {

    private int[] pos;
    private int powerStat = -1;

    @Override
    public String getName() {
	return "Werewolf";
    }

    @Override
    public String getType() {
	return "monster";
    }

    @Override
    public String getCombatType() {
	return "strength";
    }

    @Override
    public int power() {
	if (this.powerStat == -1) {
	    this.powerStat = new Dice(2).roll();
	}
	return this.powerStat;
    }

    @Override
    public int getBounty() {
	return 0;
    }

    @Override
    public void postDefeatAbility() {
	GameRunner.players.get(GameRunner.playerturn).stayedTurn(1);
    }

    @Override
    public void ability() {
    }

    @Override
    public String getDescription() {
	return "If you lose you must fight the Werewolf again and you lose a life.";
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
