package game;

import java.util.Collections;
import java.util.Stack;

import enemy.Doppleganger;
import enemy.DuskwoodWraith;
import enemy.Enemy;
import enemy.Ghast;
import enemy.Leech;
import enemy.Ogre;
import enemy.Shadow;
import enemy.Spirit;
import enemy.TreasureGoblin;
import enemy.Wraith;
import enemy.generateGenericEnemy;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Apr 9, 2014.
 */
public class EnemyList {

    private Stack<Enemy> enemy = new Stack<Enemy>();

    public EnemyList() {
	buildList();
	shuffle();
    }

    public int size() {
	return this.enemy.size();
    }

    public Enemy getEnemy() {
	Enemy ret = this.enemy.pop();
	if (this.enemy.empty()) {
	    buildList();
	    shuffle();
	}
	return ret;
    }

    /**
     * TODO Put here a description of what this method does.
     * 
     */
    private void buildList() {

	for (int i = 0; i < 2; i++) {
	    Enemy ape = new generateGenericEnemy(
		    GameRunner.messages.getString("ape"),
		    GameRunner.messages.getString("apeDesc"),
		    "animal",
		    GameRunner.messages.getString("strength"), 3, 2);
	    this.enemy.push(ape);
	}
	// add 2 apes
	for (int i = 0; i < 2; i++) {
	    Enemy bear = new generateGenericEnemy(
		    GameRunner.messages.getString("bear"),
		    GameRunner.messages.getString("bearDesc"),
		    "animal",
		    GameRunner.messages.getString("strength"), 3, 2);
	    this.enemy.push(bear);
	}
	// add 2 bears
	Enemy lion = new generateGenericEnemy(
		GameRunner.messages.getString("lion"),
		GameRunner.messages.getString("lionDesc"),
		"animal",
		GameRunner.messages.getString("strength"), 3, 2);
	this.enemy.push(lion);
	// adds 3 bears
	for (int i = 0; i < 3; i++) {
	    Enemy boar = new generateGenericEnemy(
		    GameRunner.messages.getString("boar"),
		    GameRunner.messages.getString("boarDesc"),
		    "animal",
		    GameRunner.messages.getString("strength"), 1, 2);
	    this.enemy.push(boar);
	}

	Enemy wolf = new generateGenericEnemy(
		GameRunner.messages.getString("wolf"),
		GameRunner.messages.getString("wolfDesc"),
		"animal",
		GameRunner.messages.getString("strength"), 2, 2);
	this.enemy.push(wolf);
	Enemy giant = new generateGenericEnemy(
		GameRunner.messages.getString("giant"),
		GameRunner.messages.getString("giantDesc"),
		"monster",
		GameRunner.messages.getString("strength"), 6, 2);
	this.enemy.push(giant);

	Enemy goblin = new generateGenericEnemy(
		GameRunner.messages.getString("goblin"),
		GameRunner.messages.getString("goblinDesc"),
		"monster",
		GameRunner.messages.getString("strength"), 2, 2);
	this.enemy.push(goblin);

	Enemy hobgoblin = new generateGenericEnemy(
		GameRunner.messages.getString("hobgoblin"),
		GameRunner.messages.getString("hobgoblinDesc"),
		"monster",
		GameRunner.messages.getString("strength"), 3, 2);
	this.enemy.push(hobgoblin);

	Enemy ogre = new generateGenericEnemy(
		GameRunner.messages.getString("ogre"),
		GameRunner.messages.getString("ogreDesc"),
		"monster",
		GameRunner.messages.getString("strength"), 5, 2);
	this.enemy.push(ogre);

	Enemy serpent = new generateGenericEnemy(
		GameRunner.messages.getString("serpent"),
		GameRunner.messages.getString("serpentDesc"),
		"monster",
		GameRunner.messages.getString("strength"), 4, 2);
	this.enemy.push(serpent);

	Enemy dragon = new generateGenericEnemy(
		GameRunner.messages.getString("dragon"),
		GameRunner.messages.getString("dragonDesc"),
		"dragonType",
		GameRunner.messages.getString("strength"), 7, 2);
	this.enemy.push(dragon);

	Enemy demon = new generateGenericEnemy(
		GameRunner.messages.getString("demon"),
		GameRunner.messages.getString("demonDesc"),
		"spirit",
		GameRunner.messages.getString("craft"), 10, 3);
	this.enemy.push(demon);

	Enemy ghost = new generateGenericEnemy(
		GameRunner.messages.getString("ghost"),
		GameRunner.messages.getString("ghostDesc"),
		"spirit",
		GameRunner.messages.getString("craft"), 4, 3);
	this.enemy.push(ghost);

	Enemy spectre = new generateGenericEnemy(
		GameRunner.messages.getString("spectre"),
		GameRunner.messages.getString("spectreDesc"),
		"spirit",
		GameRunner.messages.getString("craft"), 3, 3);
	this.enemy.push(spectre);

	Enemy giantLizard = new generateGenericEnemy(
		GameRunner.messages.getString("giantLizard"),
		GameRunner.messages.getString("giantLizard"),
		"animal",
		GameRunner.messages.getString("strength"), 3, 2);
	this.enemy.push(giantLizard);

	Enemy gargoyle = new generateGenericEnemy(
		GameRunner.messages.getString("gargoyle"),
		GameRunner.messages.getString("gargoyleDesc"),
		"monster",
		GameRunner.messages.getString("strength"), 5, 2);
	this.enemy.push(gargoyle);

	Enemy banshee = new generateGenericEnemy(
		GameRunner.messages.getString("banshee"),
		GameRunner.messages.getString("bansheeDesc"),
		"spirit",
		GameRunner.messages.getString("craft"), 3, 3);
	this.enemy.push(banshee);

	Enemy shade = new generateGenericEnemy(
		GameRunner.messages.getString("shade"),
		GameRunner.messages.getString("shadeDesc"),
		"spirit",
		GameRunner.messages.getString("craft"), 2, 3);
	this.enemy.push(shade);

	Enemy griffon = new generateGenericEnemy(
		GameRunner.messages.getString("griffon"),
		GameRunner.messages.getString("griffonDesc"),
		"monster",
		GameRunner.messages.getString("strength"), 5, 2);
	this.enemy.push(griffon);

	Enemy giantRat = new generateGenericEnemy(
		GameRunner.messages.getString("giantRat"),
		GameRunner.messages.getString("giantRatDesc"),
		"animal",
		GameRunner.messages.getString("strength"), 3, 2);
	this.enemy.push(giantRat);

	Enemy giantWorm = new generateGenericEnemy(
		GameRunner.messages.getString("giantWorm"),
		GameRunner.messages.getString("giantWormDesc"),
		"animal",
		GameRunner.messages.getString("strength"), 5, 2);
	this.enemy.push(giantWorm);

	Enemy bronzeDragon = new generateGenericEnemy(
		GameRunner.messages.getString("bronzeDragon"),
		GameRunner.messages.getString("bronzeDragonDesc"),
		"dragonType",
		GameRunner.messages.getString("strength"), 8, 2);
	this.enemy.push(bronzeDragon);

	Enemy cloudDragon = new generateGenericEnemy(
		GameRunner.messages.getString("cloudDragon"),
		GameRunner.messages.getString("cloudDragonDesc"),
		"dragonType",
		GameRunner.messages.getString("strength"), 8, 3);
	this.enemy.push(cloudDragon);

	Enemy zombieDragon = new generateGenericEnemy(
		GameRunner.messages.getString("zombieDragon"),
		GameRunner.messages.getString("zombieDragonDesc"),
		"dragonType",
		GameRunner.messages.getString("strength"), 7, 3);
	this.enemy.push(zombieDragon);

	Enemy redDragon = new generateGenericEnemy(
		GameRunner.messages.getString("redDragon"),
		GameRunner.messages.getString("redDragonDesc"),
		"dragonType",
		GameRunner.messages.getString("strength"), 7, 3);
	this.enemy.push(redDragon);

	this.enemy.push(new Ogre());
	this.enemy.push(new DuskwoodWraith());
	this.enemy.push(new Wraith());
	this.enemy.push(new Leech());
	this.enemy.push(new TreasureGoblin());
	this.enemy.push(new Spirit());
	this.enemy.push(new Ghast());
	this.enemy.push(new Shadow());
	this.enemy.push(new Doppleganger());

    }

    private void shuffle() {
	Collections.shuffle(this.enemy);
    }

}
