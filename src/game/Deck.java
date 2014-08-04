package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Outline for all the cards in the deck.
 */
public abstract class Deck {

    protected List<Card> cards = new ArrayList<Card>();

    private void generateDeck() {
    }

    protected void shuffle() {
	Collections.shuffle(this.cards);
    }

    protected void addCard(Card card) {
	this.cards.add(card);
    }

    public abstract Card takeTop();

    public List<Card> viewTop(int n) {
	if (n == 0) {
	    return null;
	}
	List<Card> ret = new ArrayList<Card>();
	for (int i = 0; i < n; i++) {
	    ret.add(this.cards.get(i));
	}
	return ret;
    }

    public List<Card> viewAll() {
	int n = this.cards.size();
	List<Card> ret = new ArrayList<Card>();
	for (int i = 0; i < n; i++) {
	    ret.add(this.cards.get(i));
	}
	return ret;
    }
}
