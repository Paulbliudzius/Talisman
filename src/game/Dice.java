package game;

/**
 * Allows for the rolling of 1 or 2 dice randomly generating for 1-6 or 1-12
 */
public class Dice {
    private int diceTot;

    public Dice(int n) {
	this.diceTot = (n * 6);
    }

    public int roll() {
	return (int) (1 + (Math.random() * (this.diceTot)));
    }
}
