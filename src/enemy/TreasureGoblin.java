package enemy;

import game.GameRunner;
import game.Player;
import items.weapons.GoblinSword;
import adventureCards.RandomItem;
import adventureCards.bagOfGold;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 8, 2014.
 */
public class TreasureGoblin extends Enemy {

    private int[] pos;

    public TreasureGoblin() {
	this.lives = 0;
    }

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return "Treasure Goblin";
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub.
	return "goblin";
    }

    @Override
    public String getCombatType() {
	// TODO Auto-generated method stub.
	return "strength";
    }

    @Override
    public int power() {
	// TODO Auto-generated method stub.
	return 6;
    }

    @Override
    public int getBounty() {
	// TODO Auto-generated method stub.
	return 3;
    }

    @Override
    public void postDefeatAbility() {
	// TODO Auto-generated method stub.

    }

    @Override
    public void ability() {
	// TODO Auto-generated method stub.

    }

    @Override
    public void enemyWinsSideEffect(Player player) {

    }

    @Override
    public void playerWinsSideEffect(Player player) {
	RandomItem rand = new RandomItem();
	rand.effect(player, new GoblinSword());
	bagOfGold gold = new bagOfGold(2);
	gold.effect(player);

    }

    @Override
    public String getDescription() {
	// TODO Auto-generated method stub.
	return "<html> The rare treasure goblin. Kill him to <br> take his loot"
		+ "He will run away if he defeats you </html>";
    }

    @Override
    public String getSound() {
	// TODO Auto-generated method stub.
	return null;
    }

    @Override
    public void setPosition(int[] position) {
	GameRunner.enemiesOnBoard.remove(this);

    }

    @Override
    public int[] getPosition() {
	GameRunner.enemiesOnBoard.remove(this);
	return this.pos;
    }
}
