package game.abilities;

import game.GameRunner;
import game.Player;

public class Elf extends BaseAbility {

    @Override
    public void setPlayer(Player p){
	super.setPlayer(p);
	p.giveSpell();
    }
    public Elf() {
	abilities = new CharacterAbilities[2];
	abilities[0]= new CharacterAbilities(GameRunner.getMessage("oneSpell")) {
	    @Override
	    public void effect() {
	    }
	    
	};
	abilities[1]=new CharacterAbilities(GameRunner.getMessage("forestRoll")) {   
	    @Override
	    public void effect() {
		
	    }
	};
}
}
