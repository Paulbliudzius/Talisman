package adventureCards;

import game.GameRunner;
import game.Player;

import java.util.ArrayList;

import actions.Action;
import actions.DisplayChange;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Apr 18, 2014.
 */
public class Pestilence implements Action {

    @Override
    public void act(Player player) {
	String desc = "<html>A Pestilence has befouled this Region.<br> All palyers in it must loose 1 life. <br> The pestilence then vanishes!";
	int[] pos = player.getPosition();
	effect(GameRunner.players, pos[1]);
	DisplayChange display = new DisplayChange(desc);
	display.act(player);
    }

    public void effect(ArrayList<Player> players, int region) {
	int[] pos;
	for (Player player : players) {
	    pos = player.getPosition();
	    if (region == pos[1]) {
		player.character.setCurrentLife(player.character.getLife() - 1);
	    }
	}
    }

}
