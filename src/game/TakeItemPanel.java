package game;

import game.Character.Alignment;
import items.Item;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import actions.AlignmentPanel;
import actions.ChangeAlignment;
import actions.DiscardItem;

public class TakeItemPanel extends JPanel {
    private ArrayList<Player> players;
    int rowSize;

    public TakeItemPanel() {
    }
    public void displayAlignmentSwitch(String title, int rows, int cols){
	this.removeAll();
	this.setLayout(new GridLayout(4, 1));
	JLabel titleLabel = new JLabel(title);
	GameFrame.applyLabelFormat(titleLabel);
	this.add(titleLabel);
	JButton b;
	Player p = GameRunner.players.get(GameRunner.playerturn);
	if(!p.character.getAlignment().equals(Alignment.GOOD)){
	    b=new JButton("Good");
	    b.addActionListener(new switchAlignment(Alignment.GOOD));
	}
	if(!p.character.getAlignment().equals(Alignment.NEUTRAL)){
	    b=new JButton("Neutral");
	    b.addActionListener(new switchAlignment(Alignment.NEUTRAL));
	}
	if(!p.character.getAlignment().equals(Alignment.EVIL)){
	    b=new JButton("Evil");
	    b.addActionListener(new switchAlignment(Alignment.EVIL));
	}
	
	setVisible(true);
    }
    public void createInventoryLabelsAndButtons() {
	ArrayList<JComponent> components[] = new ArrayList[players.size()];
	for (int i = 0; i < components.length; i++) {
	    components[i] = new ArrayList<JComponent>();
	}
	JLabel titleLabel = new JLabel("Select Item to Steal:");
	GameFrame.applyLabelFormat(titleLabel);
	components[0].add(titleLabel);
	for (int i = 0; i < players.size(); i++) {
	    if (i > 0) {
		components[i].add(new JLabel());
	    }
	    Player p = players.get(i);
	    JLabel l = new JLabel(p.getName());
	    GameFrame.applyLabelFormat(l);
	    components[i].add(l);
	    JButton pinchGold = new JButton("Gold");
	    if (p.getGold() == 0) {
		pinchGold.setEnabled(false);
	    }
	    pinchGold.addActionListener(new pinchGold(p));
	    GameFrame.applyButtonFormat(pinchGold);
	    components[i].add(pinchGold);
	    int j;
	    for (j = 0; j < p.items.size(); j++) {
		Item item = p.items.get(j);
		JButton button = new JButton(item.getItemName());
		GameFrame.applyButtonFormat(button);
		button.addActionListener(new pinchItem(p, item));
		if (!GameRunner.players.get(GameRunner.playerturn).canAddItem()) {
		    button.setEnabled(false);
		}
		components[i].add(button);
	    }
	    if (i > 0) {
		j++;
	    }
	    while (j < rowSize - 3) {
		components[i].add(new JLabel());
		j++;
	    }
	}
	addToPanel(components);
    }

    public void addToPanel(ArrayList<JComponent>[] components) {
	for (int i = 0; i < components[0].size(); i++) {
	    for (int j = 0; j < components.length; j++) {
		this.add(components[j].get(i));
	    }
	}
    }

    public void refresh() {
	this.removeAll();
	this.setVisible(false);
    }

    public void displayContents(ArrayList<Player> players) {
	this.removeAll();
	this.players = players;
	rowSize = players.get(0).items.size();
	for (int i = 1; i < players.size(); i++) {
	    rowSize = Math.max(rowSize, players.get(i).items.size());
	}
	rowSize += 3;
	this.setLayout(new GridLayout(rowSize, players.size()));
	createInventoryLabelsAndButtons();
	this.setVisible(true);
    }

    public class pinchItem implements ActionListener {

	public Player player;
	public Item item;

	public pinchItem(Player player, Item item) {
	    this.player = player;
	    this.item = item;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if (item.equipped()) {
		 item.unequipEffect(player);
	    }
	    new DiscardItem(item).act(player);
	    GameRunner.players.get(GameRunner.playerturn).addItem(item);
	    setVisible(false);
	}

    }

    public class pinchGold implements ActionListener {

	public Player player;

	public pinchGold(Player player) {
	    this.player = player;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    player.setGold(player.getGold() - 1);
	    GameRunner.players.get(GameRunner.playerturn).addGold(1);
	    setVisible(false);
	}

    }
    
    public class switchAlignment implements ActionListener {

	public Alignment alignment;

	public switchAlignment(Alignment a) {
	    this.alignment = a;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    new ChangeAlignment(alignment).act(GameRunner.players.get(GameRunner.playerturn));
	    
	    setVisible(false);
	    GameFrame.actionButton("Change Alignment: ", new AlignmentPanel());
	}

    }
}