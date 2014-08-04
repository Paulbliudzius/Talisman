package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import game.BoardMap;
import game.BoardSpace;
import game.Card;
import game.Character;
import game.EnemyList;
import game.GameRunner;
import game.Player;

import org.junit.Test;

import actions.Action;

public class BoardTest {

    private BoardSpace space = (new BoardMap()).getSpace(new int[] { 0, 0 });
    Card card = new Card("TestCard", "SomeRandomCard", new Action() {

	@Override
	public void act(Player player) {
	    // TODO Auto-generated method stub

	}
    });

    /*
     * Tests the creation of the map
     */
    @Test
    public void boardCreation() {
	assertNotNull(GameRunner.map);
    }

    /*
     * Tests to ensure that the boardmap created at least one instance of a
     * BoardSpace
     */
    @Test
    public void boardSpaceCreation() {
	assertNotNull(space);
    }

    /*
     * Checks the canAddCard method
     */
    @Test
    public void canAddCard() {
	assertTrue(space.canAddCard());
    }

    /*
     * Makes sure a card can be added
     */
    @Test
    public void addCard() {
	assertTrue(space.addCard(card));
    }

    /*
     * Another card case where all the spaces are filled
     */
    @Test
    public void fillCard() {

	space.addCard(card);
	space.addCard(card);
	space.addCard(card);
	space.addCard(card);
	assertFalse(space.canAddCard());
    }

    /*
     * Ensures the useCard function is removing cards properly
     */
    @Test
    public void useCard() {
	Player player = new Player(new Character("Alchemist",
		Character.Alignment.NEUTRAL, 4, 2, 4, 3, 1, new int[] { 6, 0 }), "Test");
	space.addCard(card);
	space.addCard(card);
	space.addCard(card);
	space.addCard(card);
	space.useCard(player, 1);
	assertTrue(space.canAddCard());
    }

    @Test
    public void testEnemyDeck() {
	EnemyList enemies = new EnemyList();
	int size = enemies.size();
	enemies.getEnemy();
	assertEquals(size - 1, enemies.size());
	while (enemies.size() > 1) {
	    enemies.getEnemy();
	}
	enemies.getEnemy();
	assertEquals(size, enemies.size());
    }

    @Test
    public void GameRunnerTest() {
	GameRunner.players.clear();
	GameRunner.addPlayer(new Player(GameRunner.characterDeck.takeTop(),
		"Player 1"));
	GameRunner.addPlayer(new Player(GameRunner.characterDeck.takeTop(),
		"Player 2"));
	assertEquals(2, GameRunner.players.size());
    }

    @Test
    public void checkPlayersPos() {
	GameRunner.players.clear();
	Player player1 = new Player(GameRunner.characterDeck.takeTop(),
		"Player 1");
	player1.setPosition(new int[] { 1, 0 });
	Player player2 = new Player(GameRunner.characterDeck.takeTop(),
		"Player 2");
	player2.setPosition(new int[] { 1, 0 });
	GameRunner.addPlayer(player1);
	GameRunner.addPlayer(player2);
	assertEquals(1, GameRunner.map.characterContents(new int[] { 1, 0 })
		.size());
    }
    
    @Test
    public void checkContainsCharacter(){
	GameRunner.players.clear();
	GameRunner.playerturn=0;
	Player player1 = new Player(GameRunner.characterDeck.takeTop(),
		"Player 1");
	player1.setPosition(new int[] { 1, 0 });
	GameRunner.addPlayer(player1);
	Player player2 = new Player(GameRunner.characterDeck.takeTop(),
		"Player 2");
	player2.setPosition(new int[] { 2, 0 });
	GameRunner.addPlayer(player2);
	assertFalse(GameRunner.map.containsCharacter(new int[] { 1, 0 }));
	assertTrue(GameRunner.map.containsCharacter(new int[] { 2, 0 }));
    }

}
