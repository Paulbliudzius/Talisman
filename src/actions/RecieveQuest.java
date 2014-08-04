package actions;

import game.GameFrame;
import game.GameRunner;
import game.Player;

public class RecieveQuest implements Action {

    @Override
    public void act(Player player) {
	player.quest = GameRunner.questDeck.takeTop();
	if (player.quest == 0) {
	    new StatChange("Strength", -1).act(player);
	    GameFrame.rollAndEffectPanel.displayQuest();
	} else if (player.quest == 1) {
	    GameFrame.rollAndEffectPanel.displayQuest();
	    new LoseTurn(2).act(player);
	} else if (player.quest == 2) {
	    GameFrame.rollAndEffectPanel.displayQuest();
	    new StatChange("Craft", -1).act(player);
	} else if (player.quest == 3) {
	    GameFrame.rollAndEffectPanel.displayQuest();
	    new StatChange("Life", -1).act(player);
	} else if (player.quest == 5) {
	    GameFrame.rollAndEffectPanel.displayQuest();
	    new MovePlayerPos(new int[] { 12, 0 }).act(player);
	    new StayNextTurn(2).act(player);
	} else if (player.quest == 6) {
	    GameFrame.rollAndEffectPanel.displayQuest();
	    new MovePlayerPos(new int[] { 4, 1 }).act(player);
	    new StayNextTurn(2).act(player);
	}
	    player.quest = -1;
	    player.questComplete = true;
    }
}
