package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import enemy.Enemy;
import game.GameFrame;
import game.GameRunner;
import game.Player;
import game.EncounterSequence.GameState;

import javax.swing.JButton;

public class FightEnemy implements Action {
    private Object setEnemy;

    public FightEnemy(Object enemy) {
	this.setEnemy = enemy;
    }

    @Override
    public void act(Player player) {
	Object enemy;
	if (this.setEnemy == null) {
	    enemy = GameRunner.enemyDeck.getEnemy();
	} else {
	    enemy = this.setEnemy;
	}
	JButton buttons[] = new JButton[5];
	JButton fight = new JButton("Fight");
	JButton fight1 = null;
	GameFrame.applyButtonFormat(fight);
	if (setEnemy instanceof Player) {

	    fight.addActionListener(GameFrame
		    .startFight(player, (Player) enemy));
	} else {
	    fight.addActionListener(GameFrame.startFight(player, (Enemy) enemy));
	   if( player.checkAbilities(GameState.ANIMALS_DRAGONS)&&(((Enemy) enemy).getTypeAmerican()=="animal"||((Enemy) enemy).getTypeAmerican()=="dragonType")){
		fight1 = new JButton("Evade");
		fight1.addActionListener(new dismissFight());
		GameFrame.applyButtonFormat(fight1);
		}
	}
	GameFrame.applyButtonFormat(fight);
	fight.setSize(50, 50);

	buttons[0] = fight;
	if(fight1!=null)
	buttons[1] = fight1;
	if (setEnemy instanceof Player) {
	    GameFrame.displayCard.setCard(new GenerateFightDisplay(
		    (Player) enemy).act(), ((Player) enemy).getName(), buttons);
	} else {
	    GameFrame.displayCard.setCard(new GenerateFightDisplay(
		    (Enemy) enemy).act(), ((Enemy) enemy).getDescription(),
		    buttons);
	    GameFrame.displayCard.addEnemyImage((Enemy) enemy);
	}
    }
    public class dismissFight implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
	  GameFrame.displayCard.setVisible(false);
	  GameFrame.sequence.state=GameState.DRAW_COMBAT;
	}
	
    }
}
