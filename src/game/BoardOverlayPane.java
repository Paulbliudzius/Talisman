package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

import enemy.Enemy;

public class BoardOverlayPane extends JComponent {

    @Override
    protected void paintComponent(Graphics g) {

	Color[] arr = new Color[] { Color.BLUE, Color.GREEN, Color.ORANGE,
		Color.RED, Color.CYAN, Color.YELLOW };
	g.setColor(Color.red);
	int size = GameRunner.players.size();
	for (int i = 0; i < GameRunner.players.size(); i++) {
	    g.setColor(arr[i]);
	    int[] coordinates = GameRunner.map.getLocation(GameRunner.players
		    .get(i).getPosition());
	    // g.fillRect(coordinates[0] + 10 * i, coordinates[1], 80, 80);
	    g.drawImage(GameRunner.players.get(i).character.getImage()
		    .getImage(), coordinates[0] + 10 * i, coordinates[1], 80,
		    100, null);
	    if (GameRunner.playerturn == i) {
		g.setColor(Color.yellow.brighter());
		g.drawRect(coordinates[0] + 10 * i, coordinates[1], 81, 101);
		g.drawRect(coordinates[0] + 10 * i, coordinates[1], 79, 99);
		g.drawRect(coordinates[0] + 10 * i, coordinates[1], 80, 100);
	    }
	}
	size = GameRunner.enemiesOnBoard.size();
	for (int i = 0; i < GameRunner.enemiesOnBoard.size(); i++) {
	    g.setColor(Color.black);
	    int[] coordinates = GameRunner.map
		    .getLocation(GameRunner.enemiesOnBoard.get(i).getPosition());
	    Enemy enemy = GameRunner.enemiesOnBoard.get(i);

	    g.drawImage(enemy.getImage().getImage(), coordinates[0] + 10 * i,
		    coordinates[1] - 40, 200, 200, null);

	}

	size = GameRunner.moves.size();
	for (int i = 0; i < size; i++) {
	    g.setColor(Color.WHITE);
	    int[] coordinates = GameRunner.map.getLocation(GameRunner.moves
		    .get(i));
	    g.fillOval(coordinates[0] + 20, coordinates[1], 60, 60);
	    g.setColor(Color.black);
	    g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
	    g.drawString(Integer.toString(i + 1), coordinates[0] + 40,
		    coordinates[1] + 40);
	}
    }

}
