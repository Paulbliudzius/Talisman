package game.abilities;

import game.GameRunner;
import game.EncounterSequence.GameState;

public class Assassin extends BaseAbility {

    public Assassin() {

	abilities = new CharacterAbilities[1];
	//TODO:Force take only gold
	abilities[0]= new CharacterAbilities(GameRunner.getMessage("assasinate")) {
	    
	    @Override
	    public void effect() {
	    }
	}.addUsableState(GameState.PRE_ASSASINATE);
    }

}
