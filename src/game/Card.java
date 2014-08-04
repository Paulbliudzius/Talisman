package game;

import actions.Action;

/**
 * 
 */
public class Card {
    private Action cardAction;
    private String cardName;
    private String cardDescription;

    public Card(String name, String description, Action action) {
	this.cardAction = action;
	this.cardName = name;
	this.cardDescription = description;
    }

    public void use(Player player) {
	this.cardAction.act(player);
	// discard?
    }
}
