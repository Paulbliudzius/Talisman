package actions;

import game.Player;

/**
 * So i can implement all the boards, I will be using this for now, temporary.
 * 
 */
public class PLACEHOLDER implements Action {
    String placeholderReminder;

    public PLACEHOLDER(String reminderForwhatThisDoesLaterOn) {
	this.placeholderReminder = reminderForwhatThisDoesLaterOn;
    }

    @Override
    public void act(Player player) {
	System.out.println(this.placeholderReminder);

    }

}
