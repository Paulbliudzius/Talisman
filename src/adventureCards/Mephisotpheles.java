package adventureCards;

import actions.Action;
import actions.DisplayChange;
import game.Character.Alignment;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Apr 18, 2014.
 */
public class Mephisotpheles implements Action {

    @Override
    public void act(Player player) {
	String desc = "<html> You have just been encourted by a <br> Mephistopheles on a mission. If you are Evil <br>he increases your craft. Otherwise, he <br>converts you evil!";
	String effect = effect(player);
	new DisplayChange(desc + effect);
    }

    public String effect(Player player) {
	// TODO Auto-generated method stub.
	if (player.character.getAlignment() == Alignment.EVIL) {
	    player.character.setCraft(player.character.getCraft() + 1);
	    return "<br>You gain 1 craft </hmtl>";
	} else {
	    player.character.setAlignment(Alignment.EVIL);
	    return "<br>You are now evil! </html>";
	}
    }

}
