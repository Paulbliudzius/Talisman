package actions;

import game.Character.Alignment;
import game.Player;

/**
 * Changes the character alignment
 */
public class AlignmentBased implements Action {
    private Action goodAct;
    private Action neuAct;
    private Action evilAct;

    public AlignmentBased(Action good, Action neutral, Action evil) {
	this.goodAct = good;
	this.neuAct = neutral;
	this.evilAct = evil;
    }

    @Override
    public void act(Player player) {
	Alignment currAlignment = player.character.getAlignment();
	if (currAlignment.equals(Alignment.GOOD)) {
	    this.goodAct.act(player);
	} else if (currAlignment.equals(Alignment.NEUTRAL)) {
	    this.neuAct.act(player);
	} else if (currAlignment.equals(Alignment.EVIL)) {
	    this.evilAct.act(player);
	}

    }
}
