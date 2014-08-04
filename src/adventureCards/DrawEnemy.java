package adventureCards;

import actions.Action;
import actions.FightEnemy;
import enemy.Enemy;
import game.Player;

/**
 * We routed to FightEnemy Action
 */

public class DrawEnemy implements Action {
    private Action enemyAction;

    public DrawEnemy() {
	enemyAction = new FightEnemy(null);
    }

    public DrawEnemy(Enemy enemy) {
	this.enemyAction = new FightEnemy(enemy);
    }

    @Override
    public void act(Player player) {
	this.enemyAction.act(player);
    }
}