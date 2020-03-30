package net.lectusAPI.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachmentInfo;

import net.lectusAPI.MainLectusApi;
import net.lectusAPI.cache.PlayerCache;

public class QuitEvent implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		for (PermissionAttachmentInfo attachmentInfo : p.getEffectivePermissions()) {
			try {
				attachmentInfo.getAttachment().unsetPermission(attachmentInfo.getPermission());
			} catch (Exception ex) {
				p.sendMessage("FATAL ERROR: Could not remove permission: " + attachmentInfo.getPermission());
			}
		}
		
		if (!MainLectusApi.getInstance().getConfigurationManager().getPlayerState().equalsIgnoreCase("Build") && !MainLectusApi.getInstance().getConfigurationManager().getPlayerState().equalsIgnoreCase("Connect")) {
		MainLectusApi.getInstance().getSql().setPlayers(MainLectusApi.getInstance().getServer().getPort() + "", MainLectusApi.getInstance().getServer().getOnlinePlayers().size()-1);
		}
		
		PlayerCache.getCacheByPlayer(event.getPlayer()).save(true);
	}
	
}
