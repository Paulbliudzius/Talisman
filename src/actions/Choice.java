package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import game.EncounterSequence.GameState;
import game.GameFrame;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 */
public class Choice implements Action {
    private Action[] actions;
    private int choicesAmount;
    private String[] names;
    private GameState selectionState = null;
    public Choice(int choicesAmountParam, Action[] actionsParam,
	    String[] namesParam) {
	this.choicesAmount = choicesAmountParam;
	this.actions = new Action[this.choicesAmount];
	int count;
	for (count = 0; count < this.choicesAmount; count++) {
	    this.actions[count] = actionsParam[count];
	}
	this.names = new String[choicesAmountParam];
	for (count = 0; count < this.choicesAmount; count++) {
	    this.names[count] = namesParam[count];
	}

    }

    public String[] getNames() {
	return names;
    }
    
    public Choice setStateCheck(GameState state){
	selectionState=state;
	return this;
    }
    @Override
    public void act(Player player) {
	GameFrame.choices.removeAllItems();
	for (String name : names) {
	    GameFrame.choices.addItem(name);
	}
	GameFrame.sequence.changeState(GameState.SPACE_INTERACTION);
	GameFrame.sequence.actionChoice(this.choicesAmount, this.actions);
	GameFrame.sequence.buttonHandler("PreChoice");
	if(selectionState!=null)
	player.checkAbilities(selectionState);
    }
}
