package game.abilities;

import actions.Rematch;
import actions.RollTwo;
import game.GameRunner;
import game.EncounterSequence.GameState;

public class Amazon extends BaseAbility {

    public Amazon() {
	abilities = new CharacterAbilities[2];
	abilities[0]= new CharacterAbilities(GameRunner.getMessage("rollTwo")) {
	    @Override
	    public void effect() {
		new RollTwo();		
	    }
	}.addUsableState(GameState.BEFORE_ROLL);
	//TODO:Make work if not current turn
	abilities[1]= new CharacterAbilities(GameRunner.getMessage("takeTwo")) {	    
	    @Override
	    public void effect() {
		if(!player.lastBattle.finalFight)
		    new Rematch();
	    }		
	}.addUsableState(GameState.LOST_COMBAT).addUsableState(GameState.DRAW_COMBAT);
    }
    

}
