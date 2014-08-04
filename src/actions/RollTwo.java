package actions;

import game.Dice;
import game.GameFrame;
import game.Player;

public class RollTwo implements Action {
    private int i;
    private int j;
    public RollTwo(){
	GameFrame.actionButton("Roll 2", this);
	Dice d = new Dice(1);
	i = d.roll();
	j = d.roll();
    }
    public int[] values(){
	return new int[]{i,j};
    }
    @Override
    public void act(Player player) {
	new Choice(2, new Action[]{new SetLastMovementRoll(i), new SetLastMovementRoll(j)}, new String[]{""+i,""+j}).act(player);
    }

}
