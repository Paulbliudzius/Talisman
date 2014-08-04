package actions;

import java.util.ArrayList;

import game.GameFrame;
import game.GameRunner;
import game.Player;
import game.TakeItemPanel;

public class TakeFromPlayer implements Action {
    @Override
    public void act(Player player) {
	ArrayList<Player> players = GameRunner.map.characterContents(player.getPosition());
	GameFrame.stealPanel.displayContents(players);
    }

}
