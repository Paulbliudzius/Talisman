package actions;

import items.Item;
import game.Character.Alignment;
import game.Player;

/**
 * Changes the character alignment
 */
public class ChangeAlignment implements Action {
    private Alignment allignment;

    public ChangeAlignment(Alignment allignmentParam) {
	this.allignment = allignmentParam;
    }

    @Override
    public void act(Player player) {
	player.character.setAlignment(this.allignment);
	for (int i = 0; i < player.items.size(); i++) {
	    Item item = player.items.get(i);
	    if(item.getItemName().equals("RuneSword")&&allignment.equals(Alignment.GOOD)){
		if(item.equipped)
		item.unequipEffect(player);
		player.items.remove(i);
		break;
	    }
	}
    }
}
