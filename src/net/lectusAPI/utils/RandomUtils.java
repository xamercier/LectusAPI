package net.lectusAPI.utils;

/**
 * @author xamercier and Amenixx
 */
public class RandomUtils {

	private static RandomUtils			instance	= null;
	
	public static RandomUtils getInstance() {
		if (instance == null) {
			instance = new RandomUtils();
		}
		return instance;
	}
	
	public int random(int max, int min) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}
	
}
