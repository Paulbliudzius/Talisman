package actions;

import items.Item;
import game.GameFrame;
import game.Player;

public class Steal implements Action {
    Item item;
    @Override
    public void act(Player player) {
	int choice = GameFrame.choices.getSelectedIndex();
	item =((BuyItem) GameFrame.sequence.getMoves()[choice]).item;
	    player.addItem(item);
    }

}
