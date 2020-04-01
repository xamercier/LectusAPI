package net.lectusAPI.GameManager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import net.lectusAPI.utils.ItemStackUtils;
import net.lectusAPI.utils.LectusItem;
import net.lectusAPI.utils.SendPlayerViaBungee;

public enum GameManager {
	
	hub(false,
			"hub",
			20,
			"hub_",
			"xamercier",
			new LectusItem(ItemStackUtils.create(Material.ENDER_PEARL, (byte) 0, 1, ChatColor.GREEN + "Hub", null)),
			""	),
	HikaBrain1v1(true,
			"HikaBrain1v1",
			2,
			"HikaBrain1v1_",
			"xamercier",
			new LectusItem(ItemStackUtils.create(Material.BED, (byte) 0, 1, ChatColor.RED + "HikaBrain1v1", null)),
			ChatColor.GOLD + "Developpeur: xamercier"	),
	HikaBrain2v2(true,
			"HikaBrain2v2",
			2,
			"HikaBrain2v2_",
			"xamercier",
			new LectusItem(ItemStackUtils.create(Material.BED, (byte) 0, 1, ChatColor.RED + "HikaBrain2v2", null)),
			ChatColor.GOLD + "Developpeur: xamercier"	);
	
	Boolean isAGameOrOther;
	String gamePrefix;
	int maxGamePlayersPerGames;
	String serverPrefixEnum;
	String dev;
	LectusItem gameItem;
	String itemLore;
	
	GameManager(Boolean isAGame,String prefix, int maxPlayersPerGames, String serverPrefix, String developpeur, LectusItem item, String lore) {
		isAGameOrOther = isAGame;
		gamePrefix = prefix;
		maxGamePlayersPerGames = maxPlayersPerGames;
		serverPrefixEnum = serverPrefix;
		dev = developpeur;
		gameItem = item;
		itemLore = lore;
	}
	
	public boolean isAGame() {
		return isAGameOrOther;
	}
	
	public String getGamePrefix() {
		return gamePrefix;
	}
	
	public int getMaxPlayerPerGame() {
		return maxGamePlayersPerGames;
	}
	
	public String getServerPrefix() {
		return serverPrefixEnum;
	}
	
	public LectusItem getGameItem() {
		return gameItem;
	}
	
	public String getItemLore() {
		return itemLore;
	}
	
	public void sendPlayerToGame(Player p) {
		String game = this.gamePrefix;
		SendPlayerViaBungee.sendPlayer(p, game);
	}
	
}