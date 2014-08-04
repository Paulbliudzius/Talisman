package game.abilities;

import game.GameRunner;
import game.EncounterSequence.GameState;

public class Minstrel extends BaseAbility {

    public Minstrel() {
	abilities = new CharacterAbilities[1];
	abilities[0]=new CharacterAbilities(GameRunner.getMessage("animalsDragons")) {	    
	    @Override
	    public void effect() {
	    }
	}.addUsableState(GameState.ANIMALS_DRAGONS);
    }

}
