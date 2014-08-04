package game;

import items.Item;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Mar 26, 2014.
 */
public class InventoryPanel extends JPanel {
    private Player currentPlayer;

    public InventoryPanel() {

	this.currentPlayer = GameRunner.players.get(GameRunner.playerturn);
	this.setVisible(false);
	createInventoryLabelsAndButtons();
	setSize(500, 500);
    }

    public void createInventoryLabelsAndButtons() {
	GridBagConstraints c = new GridBagConstraints();

	GridBagLayout gridbag = new GridBagLayout();
	setLayout(gridbag);

	ArrayList<Item> items = this.currentPlayer.getItems();
	JLabel inventoryLabel;
	int i = 0;

	for (Item item : items) {
	    c.weightx = 1.0; // reset to the default
	    c.weighty = 1.0;
	    inventoryLabel = new JLabel("<html>" + item.getItemName() + " : "
		    + item.getItemDescription() + "</html>");
	    gridbag.setConstraints(inventoryLabel, c);
	    // inventoryLabel = new JLabel(item.getItemName() + ": "
	    // + item.getItemDescription());
	    GameFrame.applyLabelFormat(inventoryLabel);
	    JButton equip = makebutton("Equip " + item.getItemName(), gridbag,
		    c, new equipItem(this.currentPlayer, item));
	    if (item.getType() == "weapon") {
		if ((this.currentPlayer.weaponEquipped && !this.currentPlayer.holdsTwoWeapons)
			|| this.currentPlayer.secondWeaponEquipped) {
		    equip.setEnabled(false);
		}
	    } else if (item.getType() == "armour") {
		if (this.currentPlayer.armourEquipped) {
		    equip.setEnabled(false);
		}
	    } else if (item.getType() == "magic") {
		if (this.currentPlayer.magicEquipped) {
		    equip.setEnabled(false);
		}
	    } else if (item.getItemName() == GameRunner.getMessage("mule")) {
		if (this.currentPlayer.muleEquipped) {
		    equip.setEnabled(false);
		}

	    }
	    JButton unequip = makebutton("unequip", gridbag, c,
		    new unequipItem(this.currentPlayer, item));

	    this.add(inventoryLabel);
	    if (item.equipped()) {
		this.add(unequip);
	    } else {
		this.add(equip);
	    }
	    c.gridwidth = GridBagConstraints.REMAINDER; // end row
	    JButton discardItem = makebutton("Discard - " + item.getItemName(),
		    gridbag, c, new discardListen(this.currentPlayer, i));

	    this.add(discardItem);
	    c.gridwidth = 3;

	    i++;
	    // end row

	}
    }

    protected JButton makebutton(String name, GridBagLayout gridbag,
	    GridBagConstraints c, ActionListener action) {
	JButton button = new JButton(name);
	gridbag.setConstraints(button, c);
	button.addActionListener(action);
	GameFrame.applyButtonFormat(button);
	return button;
    }

    public void refresh() {
	this.removeAll();
	this.currentPlayer = GameRunner.players.get(GameRunner.playerturn);
	this.setVisible(false);
	createInventoryLabelsAndButtons();
    }

    public class discardListen implements ActionListener {

	private Player player;
	private int pos;

	discardListen(Player player, int pos) {
	    this.player = player;
	    this.pos = pos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    player.removeItem(this.pos);
	    InventoryPanel.this.refresh();
	    GameFrame.inventoryPanel.setVisible(true);
	}

    }

    public class equipItem implements ActionListener {

	public Player player;
	public Item item;

	public equipItem(Player player, Item item) {
	    this.player = player;
	    this.item = item;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    this.item.equipEffect(this.player);
	    GameFrame.inventoryPanel.refresh();
	    GameFrame.inventoryPanel.setVisible(true);

	}

    }

    public class unequipItem implements ActionListener {

	public Player player;
	public Item item;

	public unequipItem(Player player, Item item) {
	    this.player = player;
	    this.item = item;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    this.item.unequipEffect(this.player);
	    GameFrame.inventoryPanel.refresh();
	    GameFrame.inventoryPanel.setVisible(true);

	}

    }
}
