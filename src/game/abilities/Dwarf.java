package game.abilities;

import game.GameRunner;
import game.Player;

public class Dwarf extends BaseAbility {
    @Override
    public void setPlayer(Player p){
	super.setPlayer(p);
	p.setGold(5);
    }
    public Dwarf() {
	abilities=new CharacterAbilities[4];
	abilities[0]=new CharacterAbilities(GameRunner.getMessage("cragsChasmRoll")) {
	    @Override
	    public void effect() {
		
	    }
	};
abilities[1]=new CharacterAbilities(GameRunner.getMessage("temple")) {
	    @Override
	    public void effect() {
		
	    }
	};
	abilities[2]=new CharacterAbilities(GameRunner.getMessage("powerCraft")) {
	    
	    @Override
	    public void effect() {
		
	    }
	};
	abilities[3]=new CharacterAbilities(GameRunner.getMessage("2mines")) {
	    
	    @Override
	    public void effect() {
		
	    }
	};
    }

}
