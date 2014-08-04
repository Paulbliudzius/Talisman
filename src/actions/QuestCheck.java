package actions;

import game.Player;

/**
 * TODO Makes a player lose a turn
 */
public class QuestCheck implements Action {
    private int quest;

    public QuestCheck(int quest) {
	this.quest = quest;
    }

    @Override
    public void act(Player player) {
	if (player.quest == this.quest) {
	    player.questComplete = true;
	    player.quest = -1;
	}
    }
}
