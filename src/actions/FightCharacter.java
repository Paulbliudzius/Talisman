package actions;

import javax.swing.JButton;

import enemy.Enemy;

import game.GameFrame;
import game.Player;

public class FightCharacter implements Action {

    private Action enemyAction;

    public FightCharacter(Player enemy) {
	this.enemyAction = new FightEnemy(enemy);
    }

    @Override
    public void act(Player player) {
	this.enemyAction.act(player);
	GameFrame.displayCard.setVisible(true);
	GameFrame.disableGamePane();
    }
}
