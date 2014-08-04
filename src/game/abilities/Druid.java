package game.abilities;

import actions.AddFullComplement;
import actions.AlignmentPanel;
import game.GameFrame;
import game.GameRunner;
import game.Player;
import game.EncounterSequence.GameState;

public class Druid extends BaseAbility {
    
    @Override
    public void setPlayer(Player p){
	super.setPlayer(p);
	p.giveSpell();
    }
    
    public Druid() {
	abilities = new CharacterAbilities[3];
	abilities[0]= new CharacterAbilities(GameRunner.getMessage("oneSpell")) {
	    @Override
	    public void effect() {
	    }
	    
	};
abilities[1]= new CharacterAbilities(GameRunner.getMessage("alignment")) {
	    @Override
	    public void effect() {
		GameFrame.actionButton("Change Alignment", new AlignmentPanel());
	    }
	    
	}.addUsableState(GameState.ANY);
abilities[2]= new CharacterAbilities(GameRunner.getMessage("complement")) {
	    @Override
	    public void effect() {
		if(player.getPosition()[1]==0&&(player.getPosition()[0]==3||player.getPosition()[0]==10||player.getPosition()[0]==16||player.getPosition()[0]==22))
		GameFrame.actionButton("Add Spell Complement", new AddFullComplement());
	    }
	    
	}.addUsableState(GameState.POST_MOVEMENT);
    }

}
