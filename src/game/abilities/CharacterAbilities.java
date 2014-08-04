package game.abilities;

import game.EncounterSequence;
import game.EncounterSequence.GameState;

import java.util.ArrayList;

public abstract class CharacterAbilities {
    	String message;
	private ArrayList<game.EncounterSequence.GameState>states = new ArrayList<EncounterSequence.GameState>();
	/**
	 * The effect this ability does.
	 * 
	 */
	public abstract void effect();

	public String getDescription(){
	    return message;
	};
	public CharacterAbilities(String message){
	    this.message=message;
	}
	public CharacterAbilities addUsableState(game.EncounterSequence.GameState state){
	    states.add(state);
	    return this;
	}
	public boolean canUse(game.EncounterSequence.GameState state){
	    for (int i = 0; i < states.size(); i++) {
		if(state.equals(states.get(i))||(states.get(i).equals(game.EncounterSequence.GameState.ANY)
			&&!state.equals(GameState.TAKE_TROPHY)&&!state.equals(GameState.PRE_ASSASINATE)
			&&!state.equals(GameState.COMBAT_ROLL_TWO)&&!state.equals(GameState.ITEM_SELECTION))
			&&!state.equals(GameState.COMBAT_PROJECTION)&&!state.equals(GameState.MONK_COMBAT)
			&&!state.equals(GameState.COMBAT_CHOICE)&&!state.equals(GameState.ATTACK_LESSER)&&!state.equals(GameState.AID_COMBAT)&&!state.equals(GameState.ANIMALS_DRAGONS))return true;
	    }
	    return false;
	}
}