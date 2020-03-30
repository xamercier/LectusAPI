package net.lectusAPI.utils;

import org.bukkit.entity.Player;

import net.lectusAPI.MainLectusApi;

public class VanishUtils {

	private static VanishUtils instance = null;

	public static VanishUtils getInstance() {
		if (instance == null) {
			instance = new VanishUtils();
		}
		return instance;
	}
	
	public void enableVanish(Player player) {
		if(!MainLectusApi.getInstance().VanishedPlayers.contains(player)){
		MainLectusApi.getInstance().VanishedPlayers.add(player);
		}
		for (Player other : MainLectusApi.getInstance().getServer().getOnlinePlayers()) {
			other.hidePlayer(player);
		}
	}

	public void disableVanish(Player player) {
		if (MainLectusApi.getInstance().VanishedPlayers.contains(player)) {
			MainLectusApi.getInstance().VanishedPlayers.remove(player);
			for (Player other : MainLectusApi.getInstance().getServer().getOnlinePlayers()) {
				other.showPlayer(player);
			}
		} else {
			return;
		}
	}
	
	public void checkVanish(Player player) {
		for (Player other : MainLectusApi.getInstance().getServer().getOnlinePlayers()) {
			other.showPlayer(player);
		}
	}
}
