package game.abilities;

import actions.RollTwo;
import game.GameRunner;
import game.EncounterSequence.GameState;

public class Monk extends BaseAbility {

    public Monk() {
	abilities = new CharacterAbilities[2];
	abilities[0]= new CharacterAbilities(GameRunner.getMessage("combine")) {	    
	    @Override
	    public void effect() {
		//new RollTwo();		
	    }
	}.addUsableState(GameState.MONK_COMBAT);
	abilities[1] = new CharacterAbilities(GameRunner.getMessage("stripped")) {
	    @Override
	    public void effect() {
		if(player.weaponEquipped||player.armourEquipped)
		for (int i = 0; i < player.items.size(); i++) {
		    if(player.items.get(i).equipped&&
			    (player.items.get(i).getType().equals("weapon")||player.items.get(i).getType().equals("armour")))
			player.items.get(i).unequipEffect(player);
		}
	    }
	}.addUsableState(GameState.ANY);
    }

}
