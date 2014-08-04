package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import game.EncounterSequence;
import game.EncounterSequence.GameState;
import game.EnemyList;
import game.GameFrame;
import game.GameRunner;
import game.Player;
import game.SpellsList;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Mockito.*;

public class EncounterSequenceTest {
    GameFrame frame;
    EncounterSequence sequence;
    Field f;
    Player p;

    @Before
    public void initialize() {
	try {
	    new GameRunner();
	} catch (IOException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	GameRunner.enemyDeck = new EnemyList();
	GameRunner.spellDeck = new SpellsList();
	GameRunner.addPlayer(new Player(GameRunner.characterDeck
		.getPositionInDeck(0), "TestName"));
	GameRunner.addPlayer(new Player(GameRunner.characterDeck
		.getPositionInDeck(1), "TestName2"));
	frame = Mockito.mock(GameFrame.class);
	frame.lastRoll = Mockito.mock(JLabel.class);
	frame.NextPlayerButton = Mockito.mock(JButton.class);
	frame.rollButton = Mockito.mock(JButton.class);
	frame.choices = Mockito.mock(JComboBox.class);
	frame.select = Mockito.mock(JButton.class);
	frame.fateButton = Mockito.mock(JButton.class);
	frame.descButton = Mockito.mock(JButton.class);
	sequence = new EncounterSequence(frame);
	GameFrame.sequence = sequence;
	try {
	    f = sequence.getClass().getDeclaredField("state");
	} catch (NoSuchFieldException | SecurityException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	f.setAccessible(true);
	p = GameRunner.players.get(0);
    }

    @Test
    public void sequenceInitialized() {
	assertNotNull(sequence);
    }

    @Test
    public void rolledMethodTest() {
	Player player = new Player(
		GameRunner.characterDeck.getPositionInDeck(0), "TestName");
	player.setPosition(new int[] { 2, 0 });
	sequence.rolled(1, player);
	assertEquals(GameRunner.moves.size(), 2);
	player.setPosition(new int[] { 2, 0 });
	sequence.rolled(4, player);
	assertEquals(GameRunner.moves.size(), 2);
	player.setPosition(new int[] { 2, 0 });
	player.killedSent();
	sequence.rolled(6, player);
	assertEquals(GameRunner.moves.size(), 4);
	player.setPosition(new int[] { 3, 0 });
	sequence.rolled(2, player);
	assertEquals(GameRunner.moves.size(), 3);
	player.setPosition(new int[] { 5, 0 });
	sequence.rolled(2, player);
	assertEquals(GameRunner.moves.size(), 3);
	player.setPosition(new int[] { 5, 0 });
	sequence.rolled(5, player);
	assertEquals(GameRunner.moves.size(), 4);
	player.setPosition(new int[] { 3, 0 });
	sequence.rolled(3, player);
	assertEquals(GameRunner.moves.size(), 4);
	player.setPosition(new int[] { 23, 0 });
	sequence.rolled(6, player);
	assertEquals(GameRunner.moves.size(), 3);
	player.setPosition(new int[] { 2, 1 });
	sequence.rolled(6, player);
	assertEquals(GameRunner.moves.size(), 4);
	player.setPosition(new int[] { 4, 1 });
	sequence.rolled(6, player);
	assertEquals(GameRunner.moves.size(), 4);
	player.setPosition(new int[] { 2, 1 });
	sequence.rolled(2, player);
	assertEquals(GameRunner.moves.size(), 3);
	player.setPosition(new int[] { 15, 1 });
	sequence.rolled(6, player);
	assertEquals(GameRunner.moves.size(), 4);
	player.setPosition(new int[] { 15, 1 });
	sequence.rolled(5, player);
	assertEquals(GameRunner.moves.size(), 3);
	player.setPosition(new int[] { 2, 2 });
	sequence.rolled(2, player);
	assertEquals(GameRunner.moves.size(), 2);
	player.setPosition(new int[] { 0, 2 });
	sequence.rolled(6, player);
	assertEquals(GameRunner.moves.size(), 6);
	player.setPosition(new int[] { 0, 2 });
	sequence.rolled(5, player);
	assertEquals(GameRunner.moves.size(), 5);
	player.setPosition(new int[] { 8, 2 });
	sequence.rolled(5, player);
	assertEquals(GameRunner.moves.size(), 1);
    }

    @Test
    public void checkPlayerRoll() {
	sequence.buttonHandler("Roll");
	assertEquals(GameState.MOVEMENT_CHOICE, pullState());
    }

    @Test
    public void changeStateWorks() {
	assertEquals(GameState.BEFORE_ROLL, pullState());
	sequence.changeState(GameState.MOVEMENT_CHOICE);
	assertEquals(GameState.MOVEMENT_CHOICE, pullState());
    }

    @Test
    public void turnEnds() {
	sequence.endTurn();
	assertEquals(1, GameRunner.playerturn);
    }

    public GameState pullState() {
	GameState state = null;
	try {
	    state = (GameState) f.get(sequence);
	} catch (IllegalArgumentException | IllegalAccessException e) {
	    e.printStackTrace();
	}
	return state;
    }
}
