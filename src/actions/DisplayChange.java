package actions;

import game.GameFrame;
import game.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Used to display a single stat change in the gui. Cannot handle complex
 * changes.
 * 
 * @author roccoma. Created Apr 16, 2014.
 */
public class DisplayChange implements Action {

    private String stat;
    private int amount;
    private String description;
    private Player player;

    public DisplayChange(String description) {
	this.description = description;
    }

    @Override
    public void act(Player player) {
	JButton buttons[] = new JButton[2];
	this.player = player;
	JButton okay = new JButton("Okay");
	GameFrame.applyButtonFormat(okay);
	okay.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		GameFrame.displayCard.setVisible(false);
		GameFrame.displayCard.removeAll();
		GameFrame.enableGamePane();
	    }
	});
	buttons[0] = okay;
	GameFrame.displayCard.setCard("", this.description, buttons);
	GameFrame.displayCard.setVisible(true);
	GameFrame.disableGamePane();

    }

}
