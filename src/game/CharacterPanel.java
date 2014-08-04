package game;

import game.abilities.CharacterAbilities;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author crowleaj. Created Mar 19, 2014.
 */
public class CharacterPanel extends JPanel {
    private int player;
    private final int CHAR_PER_PLAYER = 3;
    GridBagConstraints constraints;
    public CharacterPanel(int player) {
	this.player = player;
	setBorder(new EmptyBorder(0, 20, 20, 20));
	//setLayout(new GridLayout(8, 3, 10, 10));
	constraints = new GridBagConstraints();
	constraints.gridx=0;
	constraints.gridy=0;
	constraints.fill=GridBagConstraints.NONE;
	constraints.ipadx=100;
	constraints.ipady=20;
	constraints.anchor=GridBagConstraints.CENTER;
	GridBagLayout l = new GridBagLayout();
	setLayout(l);
	//l.setConstraints(this, constraints);
	setBackground(Color.BLACK);
	Character[] characters = new Character[CHAR_PER_PLAYER];
	for (int i = 0; i < CHAR_PER_PLAYER; i++) {
	    characters[i] = GameRunner.characterDeck.takeTop();
	   // createdFormattedLabel(characters[i].getName());
	    constraints.gridx++;
	}
	constraints.gridy++;
	constraints.gridx=0;
	for (int i = 0; i < CHAR_PER_PLAYER; i++) {
	    JLabel picLabel = new JLabel(characters[i].getImage());
	    add(picLabel,constraints);
	    constraints.gridx++;
	}
	constraints.gridy++;
	constraints.gridx=0;


	for (int i = 0; i < CHAR_PER_PLAYER; i++) {
	    createdFormattedLabel(GameRunner.messages.getString("life") + ": "
		    + characters[i].getLife());
	    constraints.gridx++;
	}
	constraints.gridy++;
	constraints.gridx=0;
	for (int i = 0; i < CHAR_PER_PLAYER; i++) {
	    createdFormattedLabel(GameRunner.messages.getString("strength")
		    + ": " + characters[i].getStrength());
	    constraints.gridx++;
	}
	constraints.gridy++;
	constraints.gridx=0;
	for (int i = 0; i < CHAR_PER_PLAYER; i++) {
	    createdFormattedLabel(GameRunner.messages.getString("craft") + ": "
		    + characters[i].getCraft());
	    constraints.gridx++;
	}
	constraints.gridy++;
	constraints.gridx=0;
	for (int i = 0; i < CHAR_PER_PLAYER; i++) {
	    createdFormattedLabel(GameRunner.messages.getString("fate") + ": "
		    + characters[i].getFate());
	    constraints.gridx++;
	}
	constraints.gridy++;
	constraints.gridx=0;
	constraints.ipady=80;
	    constraints.anchor=GridBagConstraints.NORTH;
	for (int i = 0; i < CHAR_PER_PLAYER; i++) {
	    createdFormattedLabel(characters[i].getAbilityString());
	    constraints.gridx++;
	    //CharacterAbilities[] abilities = characters[i].getAbilities();
//	    if (null != abilities) {
//		String labelString = "<html>"; // NOTE HTML is required to break
//					       // lines in a JLabel
//		for (int j = 0; j < abilities.length; j++) {
//		    labelString = labelString + "Abilty " + j + ": "
//			    + abilities[j].getDescription() + "<br>";
//		}
//		labelString = labelString + "</html>";
//		createdFormattedLabel(labelString);
//	    } else {
//		//createdFormattedLabel("not yet implement");
//	    }
	}
	constraints.anchor=GridBagConstraints.CENTER;
	constraints.ipady=20;
	constraints.gridy++;
	constraints.gridx=0;
	for (int i = 0; i < CHAR_PER_PLAYER; i++) {
	    final Character character = characters[i];
	    JButton button = createButton(GameRunner.messages
		    .getString("select"));
	    ActionListener submitAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    GameRunner.addPlayer(character);
		    CharacterAbilities[] abilities = character.getAbilities();
		    if (abilities != null) { // nullcheck for testing purposes
			// for (int i = 0; i < abilities.length; i++) {
			// if (abilities[i].getState() == GameState.ANY
			// || abilities[i].getState() == GameState.GAME_START) {
			// abilities[i].effect();
			// System.out.println("I did an effect!");
			// }
			// }
		    }
		    refresh(character);
		}

	    };
	    button.addActionListener(submitAction);
	    this.add(button,constraints);
	    constraints.gridx++;
	}
    }

    private void refresh(Character character) {
	if (player == ConfigurationPanel.numberOfPlayers) {
	    try {
		GameRunner.startNewGame();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	} else {
	    GameRunner.startCharacterConfig(++player);
	}
    }
    public JLabel createdFormattedLabel(String name) {

	JLabel label = new JLabel(name);
	label.setForeground(Color.yellow.darker());
	label.setFont(new Font("Verdana", Font.BOLD, 12));
	label.setHorizontalAlignment(SwingConstants.CENTER);
	this.add(label,constraints);
	return label;
    }

    public JButton createButton(String name) {
	JButton button = new JButton(name);
	button.setBackground(new Color(156, 93, 82));
	button.setFont(new Font("Verdana", Font.BOLD, 12));
	return button;
    }
    
}
