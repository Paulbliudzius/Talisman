package game.abilities;

import actions.Regenerate;
import game.GameFrame;
import game.GameRunner;
import game.EncounterSequence.GameState;

public class Troll extends BaseAbility {

    public Troll() {
	abilities = new CharacterAbilities[2];
	abilities[0]= new CharacterAbilities(GameRunner.getMessage("recharge")) {	    
	    @Override
	    public void effect() {
		if(player.getLastRoll()==6&&player.character.getLife()<player.character.getMaxLife())
		    GameFrame.actionButton("Heal one life",new Regenerate());
	    }		
	}.addUsableState(GameState.MOVEMENT_CHOICE);
	abilities[1]= new CharacterAbilities(GameRunner.getMessage("cragsRoll")) {	    
	    @Override
	    public void effect() {
	    }		
	}.addUsableState(GameState.MOVEMENT_CHOICE);
    }

}
