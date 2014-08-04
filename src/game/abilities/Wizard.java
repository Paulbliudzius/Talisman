package game.abilities;

import game.GameRunner;
import game.Player;
import game.EncounterSequence.GameState;

public class Wizard extends BaseAbility {
    @Override
    public void setPlayer(Player p){
	super.setPlayer(p);
	p.giveSpell();
	p.giveSpell();
    }
    public Wizard() {
	abilities = new CharacterAbilities[3];
	abilities[0]= new CharacterAbilities(GameRunner.getMessage("twoSpells")) {
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
	abilities[2] = new CharacterAbilities(GameRunner.getMessage("combatChoice")) {
	    @Override
	    public void effect() {
	    }
	}.addUsableState(GameState.COMBAT_CHOICE);
    }

}
