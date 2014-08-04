package game.abilities;

import game.GameRunner;
import game.Player;

public class Alchemist extends BaseAbility {
    @Override
    public void setPlayer(Player p){
	super.setPlayer(p);
	player.setGold(5);
    }
    public Alchemist() {
	abilities = new CharacterAbilities[1];
	
	abilities[0]= new CharacterAbilities(GameRunner.getMessage("oneSpell")) {
	    @Override
	    public void effect() {
	    }
	    
	};
    }
}
