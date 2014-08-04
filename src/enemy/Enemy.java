package enemy;

import game.Player;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author roccoma. Created Apr 9, 2014.
 */
public abstract class Enemy {

    private int[] pos;
    public int lives = 1;

    /**
     * 
     * 
     * @return the name of the enemy
     */
    public abstract String getName();

    /**
     * 
     * 
     * @return the type of enemy: spirit, monster, animal,
     */
    public abstract String getType();

    /**
     * 
     * 
     * @return strength or craft
     */
    public abstract String getCombatType();

    /**
     * 
     * 
     * @return power of enemy
     */
    public abstract int power();

    /**
     * bounty to be added if they are defeated
     * 
     * @return
     */
    public abstract int getBounty();

    /**
     * ability to use if they are defeated
     * 
     */
    public abstract void postDefeatAbility();

    /**
     * optional ability they may use
     * 
     */
    public abstract void ability();

    /**
     * Override me and use for a pre-battle effect
     * 
     * @return
     */

    public void enemyPreBattle(Player player, int playerRoll) {

	// override me if you need to use it
    }

    public ImageIcon getImage() {
	BufferedImage myPicture;
	try {
	    myPicture = ImageIO.read(new File("enemies/" + this.getName()
		    + ".png"));
	    ImageIcon icon = new ImageIcon(myPicture.getScaledInstance(200,
		    200, Image.SCALE_SMOOTH));
	    return icon;
	} catch (IOException e1) {
	    System.err.println("Error loading image for path: " + "characters/"
		    + this.getName() + ".png");
	    return new ImageIcon();
	}
    }

    public abstract void enemyWinsSideEffect(Player player);

    public abstract void playerWinsSideEffect(Player player);

    public abstract String getDescription();

    public abstract String getSound();

    /**
     * TODO Put here a description of what this method does.
     * 
     * @param position
     */
    public void setPosition(int[] position) {
	this.pos = position;

    }
    public String getTypeAmerican(){
	return getType();
    }
    public int[] getPosition() {
	// TODO Auto-generated method stub.
	return this.pos;
    }

}
