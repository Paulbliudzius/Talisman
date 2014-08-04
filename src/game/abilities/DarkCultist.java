package game.abilities;

import game.Dice;
import game.GameRunner;
import game.Player;
import game.Character.Alignment;
import game.EncounterSequence.GameState;

public class DarkCultist extends BaseAbility {

    public DarkCultist() {
	abilities = new CharacterAbilities[3];
abilities[0]= new CharacterAbilities(GameRunner.getMessage("combatChoice")) {    
	    @Override
	    public void effect() {
	    }
	}.addUsableState(GameState.COMBAT_CHOICE);
abilities[1]= new CharacterAbilities(GameRunner.getMessage("reward")) {
	    @Override
	    public void effect() {
		int value = new Dice(1).roll();
		if(player.lastBattle.fightingPlayer&&((Player)player.lastBattle.enemy).character.getAlignment()==Alignment.GOOD)
		    value++;
		if(value==1)
		    player.addFate(1);
		else if(value==2)
		    player.addGold(1);
		else if(value==3)
		    player.character.addLife(1);
		else if(value==4)
		    player.character.addStrength(1);
		else if(value==5)
		    player.character.addCraft(1);
		else
		    player.giveSpell();	
	    }
	}.addUsableState(GameState.WON_COMBAT);
abilities[2]= new CharacterAbilities(GameRunner.getMessage("evil")) {
	    
	    @Override
	    public void effect() {
		player.character.setAlignment(Alignment.EVIL);
	    }
	}.addUsableState(GameState.ANY);
    }

}
