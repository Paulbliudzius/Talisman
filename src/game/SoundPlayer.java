package game;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.*;

/**
 * Audio Player
 * 
 * @author roccoma. Created May 2, 2013.
 */
public class SoundPlayer {
	private String fileName;
	@SuppressWarnings("restriction")
	private AudioStream as;

	/**
	 * Constructs the music player
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	@SuppressWarnings("restriction")
	public SoundPlayer(String fileName) throws IOException {
		this.fileName = fileName;
		InputStream in = new FileInputStream(this.fileName);
		this.as = new AudioStream(in);
	}

	/**
	 * starts music
	 * 
	 */
	@SuppressWarnings("restriction")
	public void start() {
		AudioPlayer.player.start(this.as);
	}

	/**
	 * stops music
	 * 
	 */
	@SuppressWarnings("restriction")
	public void stop() {
		AudioPlayer.player.stop(this.as);
	}
	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param string
	 */


}
