package actions;

import game.GameFrame;
import game.Player;
import game.CombatFrame.battle;
import game.EncounterSequence.GameState;

public class Rematch implements Action {
    public Rematch() {
	GameFrame.actionButton("Rematch", this);
	GameFrame.actionButton.setVisible(true);
    }

    @Override
    public void act(Player player) {
	if (GameFrame.sequence.state == GameState.LOST_COMBAT) {
	    player.character.setCurrentLife(player.character.getLife() + 1);
	}
	battle b = player.lastBattle;
	if (b != null) {
	    b.finalFight = true;
	    b.fightFrame.generateComponents(b.enemy, player);
	    GameFrame.actionButton.setVisible(false);
	}
    }

}
