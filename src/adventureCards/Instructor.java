package adventureCards;

import game.GameFrame;
import game.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import actions.Action;
import actions.Choice;
import actions.DisplayChange;
import actions.MultiAction;
import actions.Safe;
import actions.StatChange;

/**
 * Buy stat improvements
 */
public class Instructor implements Action {

    public Player player;

    @Override
    public void act(Player player) {
	if (player.getGold() < 3) {
	    new Choice(1, new Action[] { new Safe() },
		    new String[] { "Need 3G" }).act(player);
	    // DisplayChange change = new DisplayChange(
	    // "<html> The instructor does not interact with peasants like yourself!</html>");
	    // change.act(player);
	} else {
	    new Choice(3, new Action[] {
		    new MultiAction(2, new Action[] {
			    new StatChange("Strength", 1),
			    new StatChange("Gold", -3) }),
		    new MultiAction(2, new Action[] {
			    new StatChange("Craft", 1),
			    new StatChange("Gold", -3) }), new Safe() },
		    new String[] { "3g:Train Strength", "3G:Train Craft",
			    "Nothing" }).act(player);
	    // JButton buttons[] = new JButton[5];
	    //
	    // JButton buyCraft = new JButton("Craft");
	    // buyCraft.addActionListener(new decision(player, "craft"));
	    //
	    // JButton buyStrength = new JButton("Strength");
	    // buyStrength.addActionListener(new decision(player, "strength"));
	    //
	    // JButton cancel = new JButton("Cancel");
	    // cancel.addActionListener(new ActionListener() {
	    //
	    // @Override
	    // public void actionPerformed(ActionEvent e) {
	    // GameFrame.displayCard.setVisible(false);
	    // GameFrame.gamePane.setVisible(true);
	    //
	    // }
	    //
	    // });
	    // buttons[0] = buyCraft;
	    // buttons[1] = buyStrength;
	    // buttons[2] = cancel;
	    // GameFrame.displayCard.setCard("<html> Instructor </html>",
	    // "<html> The instructor will train you for 3 gold </html>",
	    // buttons);

	}
    }

    // public class decision implements ActionListener {
    //
    // private Player player;
    // private String stat;
    //
    // public decision(Player player, String stat) {
    // this.player = player;
    // this.stat = stat;
    // }
    //
    // @Override
    // public void actionPerformed(ActionEvent arg0) {
    // Instructor.this.effect(player, stat);
    // GameFrame.displayCard.setVisible(false);
    // GameFrame.gamePane.setVisible(true);
    // }
    // }
    //
    // public void effect(Player player, String stat) {
    // if (stat == "craft") {
    // player.character.setCraft(player.character.getCraft() + 1);
    // } else if (stat == "strength") {
    // player.character.setStrength(player.character.getStrength() + 1);
    // }
    // player.character.setGold(player.character.getGold() - 3);
    // }

}
