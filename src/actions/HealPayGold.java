package actions;

import game.Player;

/**
 * Action interaction of paying a given pay amount to use.
 */
public class HealPayGold implements Action {
    private int pay;

    public HealPayGold(int pay) {
	this.pay = pay;
    }

    @Override
    public void act(Player player) {
	if (player.character.getLife() < player.character.getMaxLife()
		&& player.getGold() > 0) {
	    new StatChange("Gold", -1 * this.pay).act(player);
	    new StatChange("Life", this.pay).act(player);
	}

    }

}
