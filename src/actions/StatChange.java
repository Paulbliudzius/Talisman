package actions;

import game.Player;

/**
 * 
 */
public class StatChange implements Action {
    String stat;
    int amount;

    public StatChange(String stringStatParam, int amountParam) {
	this.stat = stringStatParam;
	this.amount = amountParam;
    }

    @Override
    public void act(Player player) {
	int oldStat;
	if (this.stat.equals("Strength")) {
	    oldStat = player.character.getStrength();
	    if ((oldStat + this.amount) >= 0) {
		player.character.setStrength(oldStat + this.amount);
	    }
	} else if (this.stat.equals("Craft")) {
	    oldStat = player.character.getCraft();
	    if ((oldStat + this.amount) >= 0) {
		player.character.setCraft(oldStat + this.amount);
	    }
	} else if (this.stat.equals("Life")) {
	    // TODO: Do stuff incase the person dies
	    oldStat = player.character.getLife();
	    if ((oldStat + this.amount) >= 0) {
		player.character.setCurrentLife(oldStat + this.amount);
	    } else {
		player.character.setCurrentLife(0);
	    }
	} else if (this.stat.equals("AllLife")) {
	    oldStat = player.character.getMaxLife();
	    player.character.setCurrentLife(oldStat);
	} else if (this.stat.equals("Max Life")) {
	    // TODO: Do stuff incase the person dies
	    oldStat = player.character.getMaxLife();
	    if ((oldStat + this.amount) >= 0) {
		player.character.setMaxLife(oldStat + this.amount);
	    }
	} else if (this.stat.equals("Fate")) {
	    oldStat = player.character.getFate();
	    if ((oldStat + this.amount) >= 0) {
		player.character.setFate(oldStat + this.amount);
	    }
	} else if (this.stat.equals("Max Fate")) {
	    // TODO: Refill fate?
	    oldStat = player.character.getMaxFate();
	    if ((oldStat + this.amount) >= 0) {
		player.character.setMaxFate(oldStat + this.amount);
	    }
	} else if (this.stat.equals("Gold")) {
	    oldStat = player.getGold();
	    if ((oldStat + this.amount) >= 0) {
		player.setGold(oldStat + this.amount);
	    }
	}
    }
}
