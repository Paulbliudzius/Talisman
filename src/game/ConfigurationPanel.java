package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Mar 19, 2014.
 */
public class ConfigurationPanel extends JPanel {

    public static int numberOfPlayers = 2;
    private static JComboBox<String> players;

    public ConfigurationPanel() {
	setBorder(new EmptyBorder(200, 200, 200, 200));
	setLayout(new GridLayout(0, 2, 10, 20));
	String[] numberOfPlayers = { "2", "3", "4", "5", "6" };
	String[] bonusFactors = { "0", "1", "2", "3", "4", "5", "6" };
	setBackground(Color.BLACK);
	createdFormattedLabel(GameRunner.messages.getString("gameName"));

	// add(nameLabel);

	JTextField nameField = new JTextField();
	add(nameField);

	// creat labels and boxs
	createdFormattedLabel(GameRunner.messages.getString("numPlay"));

	this.players = new JComboBox<String>(numberOfPlayers);
	ConfigurationPanel.numberOfPlayers = Integer.parseInt((String) players
		.getSelectedItem());
	add(players);

	createdFormattedLabel(GameRunner.messages.getString("bonStat"));
	JComboBox<String> bonusFactor = new JComboBox<String>(bonusFactors);
	add(bonusFactor);

	JButton exit = createButton(GameRunner.messages.getString("back"));
	add(exit);
	ActionListener exitAction = new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		try {
		    GameRunner.returnToMainMenu();
		} catch (IOException exception) {
		    // TODO Auto-generated catch-block stub.
		    exception.printStackTrace();
		}

	    }

	};
	ActionListener submitAction = new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		ConfigurationPanel.numberOfPlayers = Integer
			.parseInt((String) ConfigurationPanel.players
				.getSelectedItem());
		GameRunner.startCharacterConfig(1);// 1 is for the first
						   // character

	    }

	};

	exit.addActionListener(exitAction);
	JButton submit = createButton(GameRunner.messages
		.getString("startgame"));
	submit.addActionListener(submitAction);
	add(submit);

    }

    public JLabel createdFormattedLabel(String name) {
	JLabel label = new JLabel(name);
	label.setForeground(Color.yellow.darker());
	label.setFont(new Font("Verdana", Font.BOLD, 12));
	this.add(label);
	return label;
    }

    public JButton createButton(String name) {
	JButton button = new JButton(name);
	button.setBackground(new Color(156, 93, 82));
	button.setFont(new Font("Verdana", Font.BOLD, 12));
	return button;
    }
}
