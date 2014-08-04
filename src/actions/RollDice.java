package actions;

import game.EncounterSequence.GameState;
import game.GameFrame;
import game.Player;

/**
 * Dice Roll
 */
public class RollDice implements Action {
    Action[] actions;
    int size;
    private String desc;

    public RollDice(int sizeParam, Action[] actionParam) {
	this.size = sizeParam;
	this.actions = new Action[this.size];
	for (int count = 0; count < this.size; count++) {
	    this.actions[count] = actionParam[count];
	}
    }

    /**
     * TODO Put here a description of what this constructor does.
     * 
     * @param i
     * @param actions2
     * @param string
     */
    public RollDice(int sizeParam, Action[] actionParam, String string) {
	this.size = sizeParam;
	this.desc = string;
	this.actions = new Action[this.size + 1];
	for (int count = 0; count < this.size; count++) {
	    this.actions[count] = actionParam[count];
	}
    }

    @Override
    public void act(Player player) {
	GameFrame.sequence.changeState(GameState.SPACE_INTERACTION);
	GameFrame.rollAndEffectPanel.setRoll(this.desc, this.size,
		this.getAction());
    }

    /*
     * @Override public void act(Player player) {
     * GameFrame.sequence.changeState(GameState.SPACE_INTERACTION);
     * GameFrame.sequence.actionChoice(size, getAction());
     * GameFrame.sequence.buttonHandler("PreRoll"); }
     */

    public Action[] getAction() {
	return this.actions;
    }

}
