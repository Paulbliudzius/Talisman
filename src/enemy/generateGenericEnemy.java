package enemy;

import game.GameRunner;
import game.Player;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 11, 2014.
 */
public class generateGenericEnemy extends Enemy {

    private int bounty;
    private int power;
    private String combatType;
    private String type;
    private String name;
    private String description;

    /**
     * Create a enemy that does not have a special ability or power;
     * 
     * @param name
     * @param description
     * @param type
     * @param combatType
     * @param power
     * @param bounty
     */
    public generateGenericEnemy(String name, String description, String type,
	    String combatType, int power, int bounty) {
	this.name = name;
	this.type = type;
	this.combatType = combatType;
	this.power = power;
	this.bounty = bounty;
	this.description = description;

    }

    @Override
    public String getName() {
	// TODO Auto-generated method stub.
	return this.name;
    }

    @Override
    public String getType() {
	return GameRunner.messages.getString(this.type);
    }
    @Override
    public String getTypeAmerican(){
	return type;
    }
    @Override
    public String getCombatType() {
	// TODO Auto-generated method stub.
	return this.combatType;
    }

    @Override
    public int power() {
	// TODO Auto-generated method stub.
	return this.power;
    }

    @Override
    public int getBounty() {
	// TODO Auto-generated method stub.
	return this.bounty;
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
	// TODO Auto-generated method stub.

    }

    @Override
    public void playerWinsSideEffect(Player player) {
	// TODO Auto-generated method stub.

    }

    @Override
    public String getDescription() {
	// TODO Auto-generated method stub.
	return this.description;
    }

    @Override
    public String getSound() {
	// TODO Auto-generated method stub.
	return null;
    }

}
