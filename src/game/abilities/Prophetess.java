package game.abilities;

import game.GameRunner;
import game.Player;
import game.EncounterSequence.GameState;

public class Prophetess extends BaseAbility {
    @Override
    public void setPlayer(Player p){
	super.setPlayer(p);
	p.giveSpell();
    }
    public Prophetess() {
	abilities = new CharacterAbilities[2];
	abilities[0]= new CharacterAbilities(GameRunner.getMessage("oneSpell")) {
	    @Override
	    public void effect() {
	    }
	    
	};
	abilities[1] = new CharacterAbilities(GameRunner.getMessage("spellStock")) {
	    @Override
	    public void effect() {
		if (player.spells.size() == 0) {
		   player.giveSpell();
		}
	    }
	}.addUsableState(GameState.ANY);
    }

}
