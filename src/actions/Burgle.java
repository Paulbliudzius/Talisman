package actions;

import items.Item;
import game.Dice;
import game.GameFrame;
import game.Player;

public class Burgle implements Action {
    Item item;
    @Override
    public void act(Player player) {
	int choice = GameFrame.choices.getSelectedIndex();
	item =((BuyItem) GameFrame.sequence.getMoves()[choice]).item;
	int cost = item.getCost()+new Dice(1).roll();
	if(cost<=player.character.getCraft()){
	    player.addItem(item);
	}
	else
	    new LoseTurn(1).act(player);
    }

}
