package actions;

import game.GameFrame;
import game.Player;
import game.EncounterSequence.GameState;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author bliudzpp. Created Apr 21, 2014.
 */
public class PortalOfPower implements Action {
    private String statString;
    int twoDice = 12;

    public PortalOfPower(String type) {
	this.statString = type;
    }

    @Override
    public void act(Player player) {
	    if(player.character.getName().equals("Dwarf")&&!this.statString.equals("Strength"))
		twoDice = 6;
	GameFrame.sequence.changeState(GameState.SPACE_INTERACTION);
	GameFrame.sequence.actionChoice(twoDice, generateActions(player));
	GameFrame.sequence.buttonHandler("PreRoll");
    }

    public Action[] generateActions(Player player) {
	Action[] actions = new Action[twoDice];
	int stat = 0;
	if (this.statString.equals("Strength")) {
	    stat = player.character.getStrength();
	} else {
	    stat = player.character.getCraft();
	}
	for (int i = 0; i < twoDice; i++) {
	    if (i < stat) {
		actions[i] = new MovePlayerPos(new int[] { 0, 2 });
	    } else {
		actions[i] = new StatChange(this.statString, -1);
	    }
	}
	return actions;

    }
}
