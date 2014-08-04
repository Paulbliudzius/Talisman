package enemy;

import game.Player;
import actions.DisplayChange;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 8, 2014.
 */
public class Leech extends Enemy {

    private int killCount = 0;

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return "Leech";
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub.
	return "monster";

    }

    @Override
    public String getCombatType() {
	// TODO Auto-generated method stub.
	return "Strength";
    }

    @Override
    public int power() {
	// TODO Auto-generated method stub.
	return 3;
    }

    @Override
    public int getBounty() {
	// TODO Auto-generated method stub.
	return 5;
    }

    @Override
    public void postDefeatAbility() {
	// TODO Auto-generated method stub.

    }

    @Override
    public void ability() {
	// TODO Auto-generated method stub.

    }

    @Override
    public String getDescription() {
	// TODO Auto-generated method stub.
	return "<html>If you loose to the leech, you <br> loose 1 strength if you are not wearing armour <br>"
		+ "If you defeat the leech, you <br> can the blood of its victums. </html>";
    }

    @Override
    public String getSound() {
	// TODO Auto-generated method stub.
	return null;
    }

    @Override
    public void enemyWinsSideEffect(Player player) {
	if (player.armourEquipped == false) {
	    player.character.setStrength(player.character.getStrength() - 1);
	}
	this.killCount++;
    }

    @Override
    public void playerWinsSideEffect(Player player) {

	DisplayChange change = new DisplayChange(generateWinEffect(player));
	change.act(player);

    }

    public String generateWinEffect(Player player) {
	String desc = "<html> The blood of the leech grants you 1 strenght <br>";
	player.character.setStrength(player.character.getStrength() + 1);
	if (this.killCount >= 2) {
	    desc = desc + " but the blood made you hallucinate. Miss a turn";
	    player.loseTurns(1);
	}
	desc = desc + "</html>";
	return desc;
    }

}
