package actions;

import game.GameFrame;
import game.Player;

public class AlignmentPanel implements Action {
    boolean display;
    @Override
    public void act(Player player) {
	display=!display;
	if(display)
	GameFrame.stealPanel.displayAlignmentSwitch("Change Alignment:", 4, 1);
	else GameFrame.stealPanel.refresh();
    }

}
