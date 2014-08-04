package actions;

import game.EncounterSequence.GameState;
import game.GameFrame;
import game.Player;

public class SetLastMovementRoll implements Action {
    int roll;
    public SetLastMovementRoll(int i){
	roll = i;
    }
    @Override
    public void act(Player player) {
	player.setLastRoll(roll);
	GameFrame.sequence.state=GameState.MOVEMENT_CHOICE;
	GameFrame.sequence.rolled(roll, player);
	GameFrame.sequence.buttonHandler("PreChoice");
    }

}
