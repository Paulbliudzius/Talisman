package game;

import java.util.ArrayList;
import java.util.List;

import actions.Action;

/**
 * Board Space which is for every space on the board that can have players on
 * top of them, cards on top of them, and have actions for when a player lands
 * on the space.
 */
public class BoardSpace {
    public String name;
    private Action boardAction;
    private List<Card> cards;
    private int x;
    private int y;
    private String desc;

    public BoardSpace(String name, Action action, int x, int y, String desc) {
	this.desc = desc;
	this.name = name;
	this.boardAction = action;
	this.cards = new ArrayList<Card>();
	this.x = x;
	this.y = y;
    }

    public boolean canAddCard() {
	if (this.cards.size() < 4) {
	    return true;
	}
	return false;
    }

    public boolean addCard(Card card) {
	if (canAddCard()) {
	    this.cards.add(card);
	    return true;
	}
	return false;
    }

    public void useCard(Player player, int n) {
	if (n < this.cards.size() && n > 0) {
	    this.cards.get(n).use(player);
	    this.cards.remove(n);
	}
    }

    public int[] getLocation() {
	return new int[] { x, y };
    }

    /**
     * TODO: Return an enum flag like END_TURN or POST_MOVE so that
     * EncounterSequence knows how to proceed
     */
    public void interact(Player player) {
	this.boardAction.act(player);
    }

    public String getDesc() {
	return this.desc;
    }

    public String getName() {
	return this.name;
    }

}
