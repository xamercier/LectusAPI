package net.lectusAPI.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.lectusAPI.cache.PlayerCache;
import net.lectusAPI.grade.Rank;
import net.lectusAPI.utils.StringUtils;

public class ChatEvent implements Listener {
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void join(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		Rank rank = PlayerCache.getCacheByPlayer(p).getRank();
		
		if (Rank.MODO.hasPermission(p)) {
			e.setMessage(StringUtils.formatColor(e.getMessage()));
		}
		
		e.setFormat(rank.getDisplayName() + " " + p.getName() + ": " + e.getMessage());
	}
	
}
