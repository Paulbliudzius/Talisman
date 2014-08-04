package game.abilities;

import game.GameRunner;
import game.Player;
import game.EncounterSequence.GameState;

public class Sorceress extends BaseAbility {
    @Override
    public void setPlayer(Player p){
	super.setPlayer(p);
	p.giveSpell();
    }
    public Sorceress() {
	abilities = new CharacterAbilities[2];
	abilities[0]= new CharacterAbilities(GameRunner.getMessage("oneSpell")) {
	    @Override
	    public void effect() {
	    }
	    
	};
//	abilities[2] = new CharacterAbilities() { 
//	    @Override
//	    public String getDescription() {
//		return "You may take any one follower from a player you land on.";
//	    }
//	    @Override
//	    public void effect() {
//		
//		if(GameRunner.map.containsCharacter(player.getPosition()))
//		    GameFrame.actionButton("Steal",new TakeFromPlayer());
//	    }		
//	}.addUsableState(GameState.MOVEMENT_CHOICE);
	abilities[1] = new CharacterAbilities(GameRunner.getMessage("combatChoice")) {
	    @Override
	    public void effect() {
	    }

	}.addUsableState(GameState.COMBAT_CHOICE);
    }

}
