package game;

import game.EncounterSequence.GameState;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import spells.Spell;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Mar 26, 2014.
 */
public class SpellsPanel extends JPanel {
    public static boolean targeting = false;

    public Player currentPlayer;

    public SpellsPanel() {
	this.currentPlayer = GameRunner.players.get(GameRunner.playerturn);
	this.setLayout(new GridLayout(0, 3));
	this.setVisible(false);
	createSpellLabelsAndButtons();

    }

    public void refresh() {
	this.removeAll();
	this.setLayout(new GridLayout(0, 3));
	this.currentPlayer = GameRunner.players.get(GameRunner.playerturn);
	this.setVisible(false);
	this.createSpellLabelsAndButtons();
    }

    public void createSpellLabelsAndButtons() {
	final ArrayList<Spell> spells = this.currentPlayer.getSpells();
	if (spells.size() == 0) {
	    JLabel noSpells = new JLabel(
		    "You have no spells. Travel with caution.");
	    GameFrame.applyLabelFormat(noSpells);
	    this.add(noSpells);
	} else {
	    for (int i = 0; i < spells.size(); i++) {
		String[] spellText = spells.get(i).generateText();
		JLabel name = new JLabel(spellText[0]);
		JLabel text = new JLabel(spellText[1]);
		JButton spellButton = new JButton("Use");
		spellButton.setName("" + i);
		ActionListener useSpell = null;
		// single target spells action listener.
		if (spells.get(i).getTarget() == "self") {
		    useSpell = new SingleTargetListener(spells.get(i),
			    this.currentPlayer);
		}

		if (spells.get(i).getTarget() == "player"
			|| spells.get(i).getTarget() == "not_self") {
		    useSpell = new targetSelector(this.currentPlayer,
			    spells.get(i));
		}

		spellButton.addActionListener(useSpell);

		if (spells.get(i).getUseState() != GameState.ANY
			&& spells.get(i).getUseState() != GameFrame.sequence
				.getCurrentState()) {
		    spellButton.setEnabled(false);
		}
		GameFrame.applyLabelFormat(name);
		GameFrame.applyLabelFormat(text);
		GameFrame.applyButtonFormat(spellButton);

		this.add(name);
		this.add(text);
		this.add(spellButton);
	    }
	}
    }

    public class SingleTargetListener implements ActionListener {
	private Spell currentSpell;
	private Player currentPlayer;

	/**
	 * TODO Put here a description of what this constructor does.
	 * 
	 * @param i
	 * @param currentPlayer2
	 */
	public SingleTargetListener(Spell spell, Player currentPlayer) {
	    this.currentPlayer = currentPlayer;
	    this.currentSpell = spell;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if (this.currentSpell.getTarget() == null) {
		System.out.println("Check the spells getTarget function");
	    }

	    // self-targeting spells
	    if (this.currentSpell.getTarget() == "self") {
		this.currentSpell.ability(this.currentPlayer);
		if (this.currentSpell.getDuration() >= 1) {
		    this.currentPlayer.spellEffects.add(this.currentSpell);
		}
	    }
	    this.currentPlayer.spells.remove(this.currentSpell);
	    this.currentPlayer.checkAbilities(GameState.CAST_SPELL);
	    GameRunner.playAudio("magic spell.wav");
	    GameFrame.resetPanels();
	    GameFrame.spellsPanel.setVisible(true);
	}
    }

    public class targetSelector implements ActionListener {
	private int number;
	private Player currentPlayer;
	private Spell currentSpell;

	public targetSelector(Player player, Spell currentSpell) {
	    this.currentPlayer = player;
	    this.currentSpell = currentSpell;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    // open a new JFrame with list of players to choose from.
	    JFrame frame = new JFrame();
	    frame.setLayout(new GridLayout(2, 6));
	    frame.setTitle("Target Select");
	    frame.setSize(400, 400);
	    frame.setVisible(true);
	    for (Player players : GameRunner.players) {
		JLabel name = new JLabel(players.getName() + "the "
			+ players.character.getName());
		JButton target = new JButton(" X ");
		target.addActionListener(new targetSelected(players,
			this.currentSpell, frame));
		frame.add(name);
		frame.add(target);
	    }

	}

	public class targetSelected implements ActionListener {

	    private Player target;
	    private Spell currentSpell;
	    private JFrame frame;

	    /**
	     * TODO Put here a description of what this constructor does.
	     * 
	     * @param players
	     */
	    public targetSelected(Player players, Spell currentSpell,
		    JFrame frame) {
		this.target = players;
		this.currentSpell = currentSpell;
		this.frame = frame;
	    }

	    @Override
	    public void actionPerformed(ActionEvent arg0) {

		this.currentSpell.ability(this.target);
		GameRunner.players.get(GameRunner.playerturn).spells
			.remove(this.currentSpell);
		GameRunner.playAudio("magic spell.wav");
		this.frame.dispose();
		SpellsPanel.this.refresh();

	    }

	}

    }
}
