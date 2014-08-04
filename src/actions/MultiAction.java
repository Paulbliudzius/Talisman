package actions;

import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author bliudzpp. Created Apr 2, 2014.
 */
public class MultiAction implements Action {
    private int totalAmount;
    private Action[] actions;

    public MultiAction(int totalAmountParam, Action[] actionsParam) {
	this.totalAmount = totalAmountParam;
	this.actions = new Action[this.totalAmount];
	for (int count = 0; count < this.totalAmount; count++) {
	    this.actions[count] = actionsParam[count];
	}
    }

    @Override
    public void act(Player player) {
	int arrayLength = this.actions.length;
	for (int i = 0; i < arrayLength; i++) {
	    this.actions[i].act(player);
	}

    }

}
