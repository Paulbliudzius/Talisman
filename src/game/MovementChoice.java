package game;

import game.EncounterSequence.GameState;
import actions.Action;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author bliudzpp. Created Apr 23, 2014.
 */
public class MovementChoice {
    private Action[] moves;
    private int choicesAmount;
    private int[][] spaces;
    private String[] names;
    private String[] descriptions;

    public MovementChoice(int choicesAmountParam, Action[] movesParam,
	    int[][] spaces, String[] namesParam, String[] descParam) {
	this.choicesAmount = choicesAmountParam;
	int count;
	this.moves = new Action[this.choicesAmount];
	for (count = 0; count < this.choicesAmount; count++) {
	    this.moves[count] = movesParam[count];
	}
	this.names = new String[choicesAmountParam];
	for (count = 0; count < this.choicesAmount; count++) {
	    this.names[count] = namesParam[count];
	}
	this.descriptions = new String[choicesAmountParam];
	for (count = 0; count < this.choicesAmount; count++) {
	    this.descriptions[count] = descParam[count];
	}
	this.spaces = new int[choicesAmountParam][2];
	for (count = 0; count < this.choicesAmount; count++) {
	    this.spaces[count][0] = spaces[count][0];
	    this.spaces[count][1] = spaces[count][1];
	}

    }

    public String[] getNames() {
	return names;
    }

    public String[] getDescriptions() {
	return descriptions;
    }

    public void act(Player player) {
	GameFrame.choices.removeAllItems();
	for (String name : names) {
	    GameFrame.choices.addItem(name);
	}
	GameFrame.sequence.changeState(GameState.MOVEMENT_CHOICE);
	GameFrame.sequence.movementActionChoice(this.choicesAmount, this.moves,
		this.names, this.descriptions, this.spaces);
	GameFrame.sequence.buttonHandler("PreChoice");

    }
}
