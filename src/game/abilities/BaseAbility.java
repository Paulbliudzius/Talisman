package game.abilities;

import game.Player;
import game.EncounterSequence.GameState;

public abstract class BaseAbility {
    protected CharacterAbilities[] abilities = new CharacterAbilities[]{};
    protected Player player;
    public void setPlayer(Player p){
	player=p;
    }
    public CharacterAbilities[] getAbilities(){
	return abilities;
    }
    public boolean checkAbilities(GameState state){
	boolean usable = false;
	for (int i = 0; i < abilities.length; i++) {
	    if(abilities[i]==null)continue;
	    if(abilities[i].canUse(state)){
		abilities[i].effect();
		usable = true;
	    }
	}
	return usable;
    }
}
