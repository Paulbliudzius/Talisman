package game.abilities;

import items.followers.RidingHorse;
import game.GameRunner;
import game.Player;
import game.EncounterSequence.GameState;

public class ChivalricKnight extends BaseAbility {

    @Override
    public void setPlayer(Player p){
	super.setPlayer(p);
	player.addItem(new RidingHorse());
    }
    
    public ChivalricKnight() {
	//TODO:Allow free Riding Horse if at castle and lost Horse
	abilities = new CharacterAbilities[3];
	//TODO:2 or more cards encounter in any order
	abilities[0]= new CharacterAbilities(GameRunner.getMessage("ridingHorse")) {    
	    @Override
	    public void effect() {	
	    }
	}.addUsableState(GameState.BEFORE_ROLL);
	abilities[1]= new CharacterAbilities(GameRunner.getMessage("aidCombat")) {
	    	    
	    @Override
	    public void effect() {
	    }		
	}.addUsableState(GameState.AID_COMBAT);
	abilities[2]= new CharacterAbilities(GameRunner.getMessage("bully")) {
	    
	    @Override
	    public void effect() {
		
	    }
	}.addUsableState(GameState.ATTACK_LESSER);
    }

}
