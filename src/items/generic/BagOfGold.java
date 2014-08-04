package items.generic;

import game.GameRunner;
import game.Player;
import items.Object;

public class BagOfGold extends Object {

    @Override
    public String getType() {
	return "generic";
    }

    @Override
    public String getItemName() {
	return "Bag Of Gold";
    }

    @Override
    public String getItemDescription() {
	return "Exchange immediately for 1 gold and then"
		+ "place this card on the discard pile.";
    }

    @Override
    public Boolean hasEffect() {
	return true;
    }

    @Override
    public void effect() {
	GameRunner.players.get(GameRunner.playerturn).addGold(goldBonus());

    }

    @Override
    public int goldBonus() {
	return 1;
    }

    @Override
    public void equipEffect(Player player) {
	// TODO Auto-generated method stub.

    }

    @Override
    public void unequipEffect(Player player) {
	// TODO Auto-generated method stub.

    }

    @Override
    public boolean equipped() {
	return false;
    }

    @Override
    public int getCost() {
	return 0;
    }

}
