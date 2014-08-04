package game;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import enemy.Enemy;

/**
 * Useful for dispaly cards with multiple options and choices.
 * 
 * @author roccoma. Created Apr 10, 2014.
 */
public class DisplayCardPane extends JPanel {
    /**
     * TODO Put here a description of what this constructor does.
     * 
     * @param string
     * @param description
     */
    public DisplayCardPane() {

	this.setVisible(false);
    }

    /**
     * TODO Put here a description of what this method does.
     * 
     * @param name
     * @param description
     * @param buttons
     */
    public void setCard(String Cname, String description, JButton[] buttons) {
	this.removeAll();
	this.setLayout(new GridLayout(2, 5));
	JLabel name = new JLabel(Cname);
	GameFrame.applyLabelFormat(name);
	this.add(name);
	JLabel desc = new JLabel(description);
	GameFrame.applyLabelFormat(desc);
	this.add(desc);

	for (int i = 0; i < buttons.length; i++) {
	    if (buttons[i] != null) {
		GameFrame.applyButtonFormat(buttons[i]);
		this.add(buttons[i]);

	    }
	}
	this.setVisible(true);
	GameFrame.disableGamePane();

    }

    public void addEnemyImage(Enemy enemy) {
	JLabel icon = new JLabel(enemy.getImage());
	icon.setSize(80, 80);
	this.setLayout(new GridLayout(0, 5));
	this.add(icon);
    }

    public void setInvisible() {
	this.setVisible(false);
    }
}