package game;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import actions.Action;
import actions.FightEnemy;
import actions.LoseTurn;
import actions.Safe;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created May 16, 2014.
 */
public class RollAndEffectPanel extends JPanel {

    public JButton roll;
    public Action[] actions;
    public int size;
    public JLabel effectLabel;

    public RollAndEffectPanel() {
	this.setVisible(false);
    }

    public void setRoll(String effect, int size, Action[] actionParam) {
	this.actions = new Action[size + 1];
	for (int count = 0; count < size; count++) {
	    this.actions[count] = actionParam[count];
	}
	// this.actions = actionsParam;
	this.removeAll();
	this.size = size;
	GameFrame.disableGamePane();
	JLabel label = new JLabel(effect);
	GameFrame.applyLabelFormat(label);
	effectLabel = label;
	this.add(effectLabel);
	Player player = GameRunner.players.get(GameRunner.playerturn);
	this.roll = new JButton(GameRunner.messages.getString("roll"));
	if((player.character.getName().equals("Dwarf")&&actionParam[0] instanceof LoseTurn&&(player.getPosition()==new int[]{8,0}||player.getPosition()==new int[]{6,1}))||
		(player.character.getName().equals("Elf")&&actionParam[0] instanceof FightEnemy&&(player.getPosition()==new int[]{22,0}))){
	JButton skip = new JButton("skip roll");
	skip.addActionListener(new closeThis());
	GameFrame.applyButtonFormat(skip);
	}
	GameFrame.applyButtonFormat(this.roll);

	this.roll.addActionListener(new rollAndChangeDescription(size, false));
	this.add(this.roll);
	this.setVisible(true);

    }

    public void displayResults(int result, Boolean fateRoll) {
	this.roll.setVisible(false);
	this.removeAll();
	this.add(effectLabel);
	JLabel label = new JLabel(result + "");
	GameFrame.applyLabelFormat(label);
	this.add(label);
	if (!fateRoll
		&& GameRunner.players.get(GameRunner.playerturn).character
			.getFate() > 0) {
	    JButton fate = new JButton("Fate reroll");
	    fate.addActionListener(new rollAndChangeDescription(this.size, true));
	    this.add(fate);
	}
	JButton okay = new JButton("Okay");
	okay.addActionListener(new okayEffect(this.actions, result));
	this.add(okay);
	if(GameRunner.players.get(GameRunner.playerturn).character.getName().equals("Dwarf")&&result<11&&GameRunner.players.get(GameRunner.playerturn).getPosition()==new int[]{12,2}){
	okay = new JButton("Add 1 to roll");
	okay.addActionListener(new okayEffect(this.actions, result++));
	this.add(okay);
	}
	this.revalidate();
    }

    public void displayQuest() {
	this.setVisible(true);
	this.removeAll();
	System.out.println("quest"
		+ GameRunner.players.get(GameRunner.playerturn).quest);
	System.out.println(GameRunner.getMessage("quest"
		+ GameRunner.players.get(GameRunner.playerturn).quest));
	JLabel label = new JLabel(GameRunner.getMessage("quest"
		+ GameRunner.players.get(GameRunner.playerturn).quest));
	GameFrame.applyLabelFormat(label);
	this.add(label);
	JButton okay = new JButton("Okay");
	okay.addActionListener(new okayEffect(new Action[] { new Safe() }, 1));
	this.add(okay);
	this.revalidate();
    }

    private JButton fateButton() {
	JButton fateButton = new JButton(
		GameRunner.messages.getString("useFate"));
	GameFrame.applyButtonFormat(fateButton);
	ActionListener newGameStarter = new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		GameFrame.sequence.buttonHandler("Fate");
		GameFrame.fateButton.setVisible(false);
	    }

	};
	fateButton.addActionListener(newGameStarter);
	return fateButton;
    }

    public class okayEffect implements ActionListener {
	Action[] actions;
	int result;

	public okayEffect(Action[] actions, int result) {
	    this.actions = actions;
	    this.result = result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    GameFrame.rollAndEffectPanel.setVisible(false);
	    GameFrame.gamePane.setVisible(true);
	    this.actions[this.result - 1].act(GameRunner.players
		    .get(GameRunner.playerturn));
	}
    }

    public class rollAndChangeDescription implements ActionListener {

	private int s;
	private boolean fate;

	public rollAndChangeDescription(int side, Boolean fate) {
	    this.s = side;
	    this.fate = fate;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    Dice dice = new Dice(this.s / 6);
	    int result = dice.roll();
	    if (this.fate) {
		GameRunner.players.get(GameRunner.playerturn).addFate(-1);
	    }
	    GameFrame.rollAndEffectPanel.displayResults(result, fate);

	}

    }

    public class closeThis implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    GameFrame.rollAndEffectPanel.setVisible(false);
	    GameFrame.gamePane.setVisible(true);

	}

    }
    
}
