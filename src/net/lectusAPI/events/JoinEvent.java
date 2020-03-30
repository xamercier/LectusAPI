package net.lectusAPI.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import net.lectusAPI.MainLectusApi;
import net.lectusAPI.cache.PlayerCache;
import net.lectusAPI.grade.Rank;
import net.lectusAPI.utils.BukkitPermissionsUtils;

public class JoinEvent implements Listener {

	@EventHandler
	public void join(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.setExp(0);
		MainLectusApi.getInstance().getSql().updateAccount(p);
		PlayerCache.getCacheByPlayer(p).loadPlayerCache();

		/*
		 * MOVE AU BUNGEE
		 * MainLectusApi.getInstance().getSql().setServer(p,
		 * MainLectusApi.getInstance().getConfigurationManager().getSrvPrefix()
		 * + MainLectusApi.getInstance().getServer().getPort());
		 * MainLectusApi.getInstance().getSql().setGameState(p,
		 * MainLectusApi.getInstance().getConfigurationManager().getPlayerState(
		 * )); 
		 * MOVE AU BUNGEE
		 */

		if (!MainLectusApi.getInstance().getConfigurationManager().getPlayerState().equalsIgnoreCase("Build") && !MainLectusApi.getInstance().getConfigurationManager().getPlayerState().equalsIgnoreCase("Connect")) {
			MainLectusApi.getInstance().getSql().setPlayers(MainLectusApi.getInstance().getServer().getPort() + "",
					MainLectusApi.getInstance().getServer().getOnlinePlayers().size());
		}

		if (Rank.SUPERMODO.hasPermission(p)) {
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.difficulty");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.effect");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.gamerule");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.give.*");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.give");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.setblock");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.fill");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.setworldspawn");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.spawnpoint");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.time");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.weather");
			BukkitPermissionsUtils.addPermission(p.getUniqueId(), "minecraft.command.xp");
		}
		return; // Todo: Why return here ?
	}

	@EventHandler
	public void onAchievementEvent(PlayerAchievementAwardedEvent e) {
		e.setCancelled(true);
	}

}
