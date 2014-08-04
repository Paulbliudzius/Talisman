package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import actions.Action;
import actions.DisplayChange;
import adventureCards.BlackHole;
import adventureCards.Devil;
import adventureCards.DrawEnemy;
import adventureCards.Instructor;
import adventureCards.Mephisotpheles;
import adventureCards.Pestilence;
import adventureCards.RandomItem;
import adventureCards.bagOfGold;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author bliudzpp. Created Apr 10, 2014.
 */
public class QuestDeck {
    protected List<Integer> quests = new ArrayList<Integer>();

    public QuestDeck() {
	generateDeck();
	shuffle();
    }

    private void generateDeck() {
	for (int count = 0; count < 12; count++) {
	    quests.add(count);
	}
    }

    protected void shuffle() {
	Collections.shuffle(this.quests);
    }

    public int takeTop() {
	shuffle();
	return this.quests.get(0);
    }

    public static String getName(int quest) {
	return GameRunner.messages.getString("quest" + quest);
    }

}
