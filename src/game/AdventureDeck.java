package game;

import actions.Action;
import actions.DisplayChange;
import adventureCards.BlackHole;
import adventureCards.Devil;
import adventureCards.DrawEnemy;
import adventureCards.Instructor;
import adventureCards.MagicalVortex;
import adventureCards.Mephisotpheles;
import adventureCards.Pestilence;
import adventureCards.RandomItem;
import adventureCards.bagOfGold;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author bliudzpp. Created Apr 10, 2014.
 */
public class AdventureDeck extends Deck {
    public AdventureDeck() {
	generateDeck();
	shuffle();
    }

    @Override
    public Card takeTop() {
	if (this.cards.isEmpty()) {
	    generateDeck();
	}
	Card ret = this.cards.get(0);
	this.cards.remove(0);
	return ret;
    }

    private void generateDeck() {
	Card bagOf1Gold = new Card(GameRunner.messages.getString("BagofGold"),
		GameRunner.messages.getString("BagofGold"), new bagOfGold(1));
	Card bagOf2Gold = new Card(GameRunner.messages.getString("BagofGold"),
		GameRunner.messages.getString("BagofGold"), new bagOfGold(2));
	Action action = new Action() {

	    @Override
	    public void act(Player player) {
		player.giveSpell();
		DisplayChange display = new DisplayChange(
			GameRunner.messages.getString("stumbleSpellBook"));
		display.act(player);
	    }
	};

	Card magicVertex = new Card("Magic Vertex", "", new MagicalVortex());
	Card devil = new Card("Devil",
		GameRunner.messages.getString("alignBased"), new Devil());
	Card mes = new Card("Meph",
		GameRunner.messages.getString("alignBased"),
		new Mephisotpheles());
	Card pes = new Card("Pest", "everyone", new Pestilence());
	Card instructor = new Card("instructor", "everyone", new Instructor());
	for (int i = 0; i < GameRunner.itemDeck.size(); i++) {
	    addCard(new Card("item", "everyone", new RandomItem()));
	}
	Card blackHole = new Card("blackHole", "enemies", new BlackHole());

	for (int i = 0; i < GameRunner.enemyDeck.size(); i++) {
	    addCard(new Card("Enemy",
		    GameRunner.messages.getString("spawnEnemy"),
		    new DrawEnemy()));
	}
	for (int i = 0; i < 6; i++) {
	    Card spellBook = new Card(
		    GameRunner.messages.getString("SpellBook"),
		    GameRunner.messages.getString("SpellBook"), action);
	    addCard(spellBook);
	}

	addCard(bagOf1Gold);
	addCard(bagOf2Gold);
	addCard(devil);
	addCard(pes);
	addCard(magicVertex);
	addCard(blackHole);
	addCard(instructor);
	addCard(mes);
	// "emptyAdventureCard")));
	// addCard(new Card("PlaceKeeper", "ForPractice", new PLACEHOLDER(
	// "emptyAdventureCard")));
	// addCard(new Card("PlaceKeeper", "ForPractice", new PLACEHOLDER(
	// "emptyAdventureCard")));
    }
}
