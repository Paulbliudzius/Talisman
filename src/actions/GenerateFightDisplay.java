package actions;

import enemy.Enemy;
import game.Player;

/**
 * For displaying information during a fight that is then testable
 */
public class GenerateFightDisplay {
    private String display;

    public GenerateFightDisplay(Enemy enemy) {
	this.display = "<html> " + enemy.getName() + "<br> Type "
		+ enemy.getType() + " " + enemy.getCombatType() + " "
		+ enemy.power() + "</html>";
    }

    public GenerateFightDisplay(Player enemy) {
	this.display = "<html> " + enemy.getName() + "<br> Strength " + " "
		+ enemy.character.getStrength() + "</html>";
    }

    public String act() {
	return this.display;
    }
}
