package game;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Apr 16, 2014.
 */
public class BoardPane extends JPanel {

    public BoardPane() {
	this.setVisible(false);
	this.setLayout(new GridLayout(0, 3));
    }

    public void generateComps(BoardSpace space) {
	this.removeAll();
	JLabel name = new JLabel(space.getName());
	GameFrame.applyLabelFormat(name);
	JLabel desc;
	if (space.getDesc() == null) {
	    desc = new JLabel("I STILL NEED A DESC");
	} else {
	    desc = new JLabel(space.getDesc());
	}
	GameFrame.applyLabelFormat(desc);
	this.add(desc);
	this.add(name);
	this.setVisible(true);

    }

    public void removeAndHide() {
	this.setVisible(false);
    }
}
