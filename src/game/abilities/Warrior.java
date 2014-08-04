package game.abilities;

import game.GameRunner;
import game.Player;
import game.EncounterSequence.GameState;

public class Warrior extends BaseAbility {
    @Override
    public void setPlayer(Player p){
	super.setPlayer(p);
	p.holdsTwoWeapons = true;
    }
    public Warrior() {
	abilities = new CharacterAbilities[2];
	//TODO: 2 weapons at same time
		abilities[0]=new CharacterAbilities(GameRunner.getMessage("roll2Combat")) {	    
	    @Override
	    public void effect() {		
	    }
	}.addUsableState(GameState.COMBAT_ROLL_TWO);
		abilities[1]=new CharacterAbilities(GameRunner.getMessage("dualWield")) {
	    	    
	    @Override
	    public void effect() {		
	    }
	};
    }

}
