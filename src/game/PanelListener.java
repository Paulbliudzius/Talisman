package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author bliudzpp. Created May 7, 2014.
 */
public class PanelListener implements ActionListener {
    private boolean[] displayBool;

    /**
     * Stat,Spell,Inventory,Description
     * 
     * @param displays
     *            - Stat,Spell,Inventory,Description
     */
    public PanelListener(boolean[] displays) {
	displayBool = displays;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

	if (displayBool[0]) {
	    GameFrame.statsPanel.refresh();
	    GameFrame.statsVisible = !GameFrame.statsVisible;
	} else {
	    GameFrame.statsVisible = false;
	}
	if (displayBool[1]) {
	    GameFrame.spellsPanel.refresh();
	    GameFrame.spellsVisible = !GameFrame.spellsVisible;
	} else {
	    GameFrame.spellsVisible = false;
	}
	if (displayBool[2]) {
	    GameFrame.inventoryPanel.refresh();
	    GameFrame.inventoryVisible = !GameFrame.inventoryVisible;
	} else {
	    GameFrame.inventoryVisible = false;
	}
	if (displayBool[3]) {
	    GameFrame.sequence.buttonHandler("Description");
	    GameFrame.moveDescription.refresh();
	    GameFrame.descriptionVisible = !GameFrame.descriptionVisible;
	} else {
	    GameFrame.descriptionVisible = false;
	}
	GameFrame.setInfoPanels();
    }
}
