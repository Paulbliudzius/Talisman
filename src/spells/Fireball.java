package spells;

import enemy.Enemy;
import enemy.generateGenericEnemy;
import game.EncounterSequence.GameState;
import game.GameFrame;
import game.GameRunner;
import game.Player;
import actions.FightEnemy;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 15, 2014.
 */
public class Fireball extends Spell {

    private int fireBallPower;

    public Fireball(int i) {
	this.fireBallPower = i;
    }

    @Override
    public GameState getUseState() {
	// TODO Auto-generated method stub.
	return GameState.ANY;
    }

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return GameRunner.getMessage("fireball");
    }

    @Override
    public String gettext() {
	// TODO Auto-generated method stub.
	return GameRunner.getMessage("fireballDesc");

    }

    @Override
    public void ability(Player target) {
	GameState state = GameFrame.sequence.state;
	Enemy fireball = new generateGenericEnemy(
		GameRunner.getMessage("fireball"),
		GameRunner.getMessage("fireballDesc"),
		GameRunner.getMessage("spirit"),
		GameRunner.getMessage("strength"), this.fireBallPower, 0);
	fireball.lives = 0;
	FightEnemy fight = new FightEnemy(fireball);
	fight.act(target);
    }

    @Override
    public void endOfDuration() {
	// TODO Auto-generated method stub.

    }

    @Override
    public String getTarget() {
	// TODO Auto-generated method stub.
	return "player";
    }

}
