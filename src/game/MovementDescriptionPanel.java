package game;

import game.StatsPanel.upgrade;
import items.Item;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Mar 26, 2014.
 */
public class MovementDescriptionPanel extends JPanel {

    public MovementDescriptionPanel() {
	this.setLayout(new GridLayout(5, 5));
	this.setVisible(false);
	createDescLabels();

    }

    public void createDescLabels() {
	JLabel titleLabel = new JLabel("Space Description:");
	GameFrame.applyLabelFormat(titleLabel);
	this.add(titleLabel);
	if (GameRunner.moves.size() > 0) {
	    JLabel descLabel;
	    descLabel = new JLabel(GameRunner.map.getSpace(
		    GameRunner.moves.get(GameRunner.description)).getDesc());
	    GameFrame.applyLabelFormat(descLabel);
	    this.add(descLabel);
	}
    }

    public void refresh() {
	this.removeAll();
	this.setLayout(new GridLayout(5, 5));
	this.setVisible(false);
	createDescLabels();
    }

}
