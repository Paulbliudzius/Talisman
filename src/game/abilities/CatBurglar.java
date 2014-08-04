package game.abilities;

import actions.Burgle;
import game.GameFrame;
import game.GameRunner;
import game.EncounterSequence.GameState;

public class CatBurglar extends BaseAbility{

    public CatBurglar() {
	abilities = new CharacterAbilities[1];
	//TODO:2 or more cards encounter in any order
	abilities[0]= new CharacterAbilities(GameRunner.getMessage("take")) {
	    
	    @Override
	    public void effect() {
		GameFrame.actionButton("Burgle",new Burgle());
	    }		
	}.addUsableState(GameState.ITEM_SELECTION);
    }

}
