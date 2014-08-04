package game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Mar 26, 2014.
 */
public class StatsPanel extends JPanel {

    private Player currentPlayer;

    public StatsPanel() {

	this.currentPlayer = GameRunner.players.get(GameRunner.playerturn);
	this.setLayout(new GridLayout(8, 2));
	this.setVisible(false);
	createstatsLabelsAndButtons();

    }

    public void createstatsLabelsAndButtons() {
	int strength = this.currentPlayer.character.getStrength();
	int craft = this.currentPlayer.character.getCraft();
	int life = this.currentPlayer.character.getLife();
	int fate = this.currentPlayer.character.getFate();
	int gold = this.currentPlayer.getGold();
	String alignment = this.currentPlayer.character.getAlignmentString();
	int strengthBounty = this.currentPlayer.character.getStrengthBounty();
	int craftBounty = this.currentPlayer.character.getCraftBounty();
	JLabel strengthLabel = new JLabel("Strength: " + strength);
	JButton strengthUpgrade = new JButton("+");

	JLabel strengthBountyLabel = new JLabel("Strength Bounty: ");
	JLabel strengthBountyInfo = new JLabel(strengthBounty + "/7");

	JLabel craftLabel = new JLabel("craft: " + craft);
	JButton craftUpgrade = new JButton("+");

	craftUpgrade
		.addActionListener(new upgrade(this.currentPlayer, "craft"));
	strengthUpgrade.addActionListener(new upgrade(this.currentPlayer,
		"strength"));

	JLabel craftBountyLabel = new JLabel("Craft Bounty: ");
	JLabel craftBountyInfo = new JLabel(craftBounty + "/7");

	JLabel placeHolder = new JLabel(" ");

	JLabel lifeLabel = new JLabel("life: " + life);

	JLabel goldLabel = new JLabel("gold: " + gold);

	JLabel fateLabel = new JLabel("fate: " + fate);

	JLabel alignmentLabel = new JLabel("Alignment: " + alignment);
	GameFrame.applyLabelFormat(alignmentLabel);
	GameFrame.applyButtonFormat(strengthUpgrade);
	GameFrame.applyButtonFormat(craftUpgrade);
	GameFrame.applyLabelFormat(strengthLabel);
	GameFrame.applyLabelFormat(strengthBountyLabel);
	GameFrame.applyLabelFormat(craftLabel);
	GameFrame.applyLabelFormat(craftBountyLabel);
	GameFrame.applyLabelFormat(lifeLabel);
	GameFrame.applyLabelFormat(goldLabel);
	GameFrame.applyLabelFormat(fateLabel);
	GameFrame.applyLabelFormat(strengthBountyInfo);
	GameFrame.applyLabelFormat(craftBountyInfo);

	this.add(strengthLabel);
	this.add(strengthUpgrade);

	this.add(strengthBountyLabel);
	this.add(strengthBountyInfo);
	if (strengthBounty < 7) {
	    strengthUpgrade.setEnabled(false);
	} else {
	    strengthUpgrade.setEnabled(true);
	}
	this.add(craftLabel);
	this.add(craftUpgrade);
	if (craftBounty < 7) {
	    craftUpgrade.setEnabled(false);
	} else {
	    craftUpgrade.setEnabled(true);
	}
	this.add(craftBountyLabel);
	this.add(craftBountyInfo);

	this.add(lifeLabel);
	this.add(placeHolder);
	this.add(goldLabel);
	this.add(placeHolder);
	this.add(fateLabel);
	this.add(placeHolder);
	this.add(alignmentLabel);
	this.add(placeHolder);
	if (this.currentPlayer.talisman) {
	    JLabel talisman = new JLabel("Has Talisman");
	    GameFrame.applyLabelFormat(talisman);
	    this.add(talisman);
	} else {
	    JLabel talisman = new JLabel("Need Talisman");
	    GameFrame.applyLabelFormat(talisman);
	    this.add(talisman);
	}
	if (this.currentPlayer.questComplete) {
	    JLabel questCompleteLabel = new JLabel("Quest Complete");
	    GameFrame.applyLabelFormat(questCompleteLabel);
	    this.add(questCompleteLabel);
	}
	if (this.currentPlayer.quest != -1) {
	    JLabel questLabel = new JLabel("Quest: " + this.currentPlayer.quest
		    + "  "
		    + GameRunner.getMessage("quest" + this.currentPlayer.quest));
	    GameFrame.applyLabelFormat(questLabel);
	    this.add(questLabel);
	}

    }

    public class upgrade implements ActionListener {

	private Player player;
	private String stat;

	upgrade(Player player, String stat) {
	    this.player = player;
	    this.stat = stat;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if (this.stat == "craft") {
		this.player.character
			.setCraft(this.player.character.getCraft() + 1);
		this.player.character.setCraftBounty(0);
	    }
	    if (this.stat == "strength") {
		this.player.character.setStrength(this.player.character
			.getStrength() + 1);
		this.player.character.setStrengthBounty(0);
	    }
	    StatsPanel.this.refresh();
	}

    }

    public void refresh() {
	this.removeAll();
	this.currentPlayer = GameRunner.players.get(GameRunner.playerturn);
	this.setLayout(new GridLayout(8, 2));
	this.setVisible(false);
	createstatsLabelsAndButtons();
    }
}
