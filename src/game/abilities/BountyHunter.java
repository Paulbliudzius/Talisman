package game.abilities;

import game.GameRunner;
import game.Player;
import game.EncounterSequence.GameState;

public class BountyHunter extends BaseAbility {

    public BountyHunter() {
	abilities = new CharacterAbilities[4];
	abilities[0] = new CharacterAbilities(GameRunner.getMessage("soul")) {
	    @Override
	    public void effect() {
		if (player.lastBattle.fightingPlayer) {
		    if (!((Player) player.lastBattle.enemy).character
			    .getAlignment().equals(
				    player.character.getAlignment())) {
			player.addGold(1);
		    }
		}
	    }
	}.addUsableState(GameState.WON_COMBAT);
	abilities[1] = new CharacterAbilities(GameRunner.getMessage("reward")) {
	    @Override
	    public void effect() {
		if (!player.lastBattle.fightingPlayer) {
		    player.addGold(1);
		}
	    }
	}.addUsableState(GameState.WON_COMBAT);
	abilities[2] = new CharacterAbilities(GameRunner.getMessage("overcome")) {
	    @Override
	    public void effect() {
	    }
	}.addUsableState(GameState.COMBAT_PROJECTION);
	// TODO:Make take trophy work
	abilities[3] = new CharacterAbilities(GameRunner.getMessage("poach")) {
	    @Override
	    public void effect() {
	    }
	}.addUsableState(GameState.TAKE_TROPHY);
    }
}
