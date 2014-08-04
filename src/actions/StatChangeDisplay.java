package actions;

import game.GameFrame;
import game.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Apr 16, 2014.
 */
public class StatChangeDisplay implements Action {

    private String stat;
    private int amount;
    private String description;
    private Player player;

    public StatChangeDisplay(String string, int i, String description) {
	this.stat = string;
	this.amount = i;
	this.description = description;
    }

    @Override
    public void act(Player player) {
	JButton buttons[] = new JButton[2];
	this.player = player;
	JButton okay = new JButton("Okay");
	GameFrame.applyButtonFormat(okay);
	okay.addActionListener(new effect(this.stat, this.amount));
	buttons[0] = okay;
	GameFrame.displayCard.setCard("Stat Change  ", this.description,
		buttons);
	GameFrame.displayCard.setVisible(true);
	GameFrame.disableGamePane();

    }

    class effect implements ActionListener {

	private String stat;
	private int amount;

	public effect(String stat, int amount) {
	    this.stat = stat;
	    this.amount = amount;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    StatChange change = new StatChange(this.stat, this.amount);
	    change.act(player);
	    GameFrame.displayCard.setVisible(false);
	    GameFrame.displayCard.removeAll();
	    GameFrame.enableGamePane();

	}
    }
}
