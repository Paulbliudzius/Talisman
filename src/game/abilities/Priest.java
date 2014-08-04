package game.abilities;

import game.GameRunner;
import game.Player;
import game.EncounterSequence.GameState;

public class Priest extends BaseAbility {
    @Override
    public void setPlayer(Player p){
	super.setPlayer(p);
	p.giveSpell();
    }
    public Priest() {
	abilities = new CharacterAbilities[3];
	abilities[0]= new CharacterAbilities(GameRunner.getMessage("oneSpell")) {
	    @Override
	    public void effect() {
	    }
	    
	};
	abilities[1] = new CharacterAbilities(GameRunner.getMessage("spirits")) {
	    @Override
	    public void effect() {
		if (player.spells.size() == 0) {
		   player.giveSpell();
		}
	    }
	}.addUsableState(GameState.ANY);
	abilities[2] = new CharacterAbilities(GameRunner.getMessage("stripped")) {
	    @Override
	    public void effect() {
		if(player.weaponEquipped||player.armourEquipped)
			for (int i = 0; i < player.items.size(); i++) {
			    if(player.items.get(i).equipped&&
				    (player.items.get(i).getType().equals("weapon")))
				player.items.get(i).unequipEffect(player);
			}
	    }
	}.addUsableState(GameState.ANY);
    }

}
