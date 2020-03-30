package net.lectusAPI.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

import net.lectusAPI.cache.PlayerCache;
import net.lectusAPI.grade.Rank;
import net.md_5.bungee.api.ChatColor;

public class GameUtils {

	public static void sendCoins(String prefix, Boolean validGame, HashMap<Player, String> players) {
		HashMap<Player, String> map = players;
		Iterator<Entry<Player, String>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Entry<Player, String> entry = entries.next();
			Player pl = entry.getKey();
			int extraCoins = Integer.parseInt(entry.getValue());
			
			if (!validGame == true) {
				pl.sendMessage(prefix + ChatColor.GREEN + " + 50 coins : " + ChatColor.RED
						+ " Partie invalide.");
				PlayerCache.getCacheByPlayer(pl).addCoins(50);
			} else {
				if (PlayerCache.getCacheByPlayer(pl).getRank().equals(Rank.JOUEUR)) {
					if (extraCoins != 0) {
						pl.sendMessage(prefix + ChatColor.GREEN + " + " + extraCoins + " : " + ChatColor.RED + "Game");
					}
					pl.sendMessage(prefix + ChatColor.GREEN + " + 100 coins : "
							+ PlayerCache.getCacheByPlayer(pl).getRank().getShortName());
					PlayerCache.getCacheByPlayer(pl).addCoins(100 + extraCoins);
				} else if (PlayerCache.getCacheByPlayer(pl).getRank().equals(Rank.MINIVIP)) {
					if (extraCoins != 0) {
						pl.sendMessage(prefix + ChatColor.GREEN + " + " + extraCoins + " : " + ChatColor.RED + "Game");
					}
					pl.sendMessage(prefix + ChatColor.GREEN + " + 150 coins bonus : "
							+ PlayerCache.getCacheByPlayer(pl).getRank().getShortName());
					PlayerCache.getCacheByPlayer(pl).addCoins(150 + extraCoins);
				} else if (PlayerCache.getCacheByPlayer(pl).getRank().equals(Rank.VIP)) {
					if (extraCoins != 0) {
						pl.sendMessage(prefix + ChatColor.GREEN + " + " + extraCoins + " : " + ChatColor.RED + "Game");
					}
					pl.sendMessage(prefix + ChatColor.GREEN + " + 200 coins bonus : "
							+ PlayerCache.getCacheByPlayer(pl).getRank().getShortName());
					PlayerCache.getCacheByPlayer(pl).addCoins(200 + extraCoins);
				} else if (PlayerCache.getCacheByPlayer(pl).getRank().equals(Rank.VIPPLUS)) {
					if (extraCoins != 0) {
						pl.sendMessage(prefix + ChatColor.GREEN + " + " + extraCoins + " : " + ChatColor.RED + "Game");
					}
					pl.sendMessage(prefix + ChatColor.GREEN + " + 300 coins bonus : "
							+ PlayerCache.getCacheByPlayer(pl).getRank().getShortName());
					PlayerCache.getCacheByPlayer(pl).addCoins(300 + extraCoins);
				} else if (PlayerCache.getCacheByPlayer(pl).getRank().equals(Rank.MEGAVIP)) {
					if (extraCoins != 0) {
						pl.sendMessage(prefix + ChatColor.GREEN + " + " + extraCoins + " : " + ChatColor.RED + "Game");
					}
					pl.sendMessage(prefix + ChatColor.GREEN + " + 400 coins bonus : "
							+ PlayerCache.getCacheByPlayer(pl).getRank().getShortName());
					PlayerCache.getCacheByPlayer(pl).addCoins(400 + extraCoins);
				} else if (PlayerCache.getCacheByPlayer(pl).getRank().equals(Rank.YOUTUBEUR)) {
					if (extraCoins != 0) {
						pl.sendMessage(prefix + ChatColor.GREEN + " + " + extraCoins + " : " + ChatColor.RED + "Game");
					}
					pl.sendMessage(prefix + ChatColor.GREEN + " + 500 coins bonus : "
							+ PlayerCache.getCacheByPlayer(pl).getRank().getShortName());
					PlayerCache.getCacheByPlayer(pl).addCoins(500 + extraCoins);
				} else if (Rank.BUILDEUR.hasPermission(pl)) {
					if (extraCoins != 0) {
						pl.sendMessage(prefix + ChatColor.GREEN + " + " + extraCoins + " : " + ChatColor.RED + "Game");
					}
					pl.sendMessage(prefix + ChatColor.GREEN + " + 600 coins bonus : STAFF");
					PlayerCache.getCacheByPlayer(pl).addCoins(600 + extraCoins);
				} else {
					if (extraCoins != 0) {
						pl.sendMessage(prefix + ChatColor.GREEN + " + " + extraCoins + " : " + ChatColor.RED + "Game");
					}
					pl.sendMessage(prefix + ChatColor.GREEN + " + 100 coins : "
							+ PlayerCache.getCacheByPlayer(pl).getRank().getShortName());
					PlayerCache.getCacheByPlayer(pl).addCoins(100 + extraCoins);
				}
				pl.sendMessage(prefix + ChatColor.GREEN + "Les coins peuvent prendre jusqu'a 10 minutes a s'ajouter.");
			}
		}
	}
}
