package game;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PlayerNamePanel extends JPanel {
    protected JTextField nameField;

    public PlayerNamePanel(int player) {
	setBorder(new EmptyBorder(20, 160, 20,160));
	setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	setBackground(Color.BLACK);
	createdFormattedLabel("Name: ");
	nameField = new JTextField("");
	add(nameField);
    }

    public JLabel createdFormattedLabel(String name) {
	JLabel label = new JLabel(name);
	label.setForeground(Color.yellow.darker());
	label.setFont(new Font("Verdana", Font.BOLD, 12));
	this.add(label);
	return label;
    }
}