package spells;

import game.Dice;
import game.EncounterSequence.GameState;
import game.GameFrame;
import game.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import actions.DisplayChange;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Apr 18, 2014.
 */
public class Enrich extends Spell {

    @Override
    public GameState getUseState() {

	return GameState.ANY;
    }

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return "Enrich";
    }

    @Override
    public String gettext() {
	// TODO Auto-generated method stub.
	return "<html>Cast this spell at anytime. <br> Roll a die and create that much gold </html>";
    }

    @Override
    public void ability(Player target) {
	JButton[] buttons = new JButton[2];
	JButton rollButton = new JButton("Roll");
	GameFrame.applyButtonFormat(rollButton);
	ActionListener roll = new rollAction(target);

	rollButton.addActionListener(roll);
	buttons[0] = rollButton;
	GameFrame.displayCard.setCard("Roll! ", "Roll for you're gold!",
		buttons);

    }

    @Override
    public void endOfDuration() {
	// TODO Auto-generated method stub.

    }

    @Override
    public String getTarget() {
	// TODO Auto-generated method stub.
	return "self";
    }

    class rollAction implements ActionListener {

	private Player player;

	public rollAction(Player player) {
	    this.player = player;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    GameFrame.displayCard.setVisible(false);
	    Dice die = new Dice(1);
	    int res = die.roll();
	    player.setGold(player.getGold() + res);
	    DisplayChange change = new DisplayChange("You have earned" + res
		    + " gold.");
	    change.act(this.player);
	}
    }
}
