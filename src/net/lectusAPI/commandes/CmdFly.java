package net.lectusAPI.commandes;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.lectusAPI.MainLectusApi;
import net.lectusAPI.cache.PlayerCache;
import net.lectusAPI.grade.Rank;

public class CmdFly implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if (sender instanceof Player) {
			if(Rank.MODO.hasPermission(p)){
				String gameState = MainLectusApi.getInstance().APIMODE;
				if(gameState.equalsIgnoreCase("game") && !MainLectusApi.getInstance().getSql().hasModMode(p) && PlayerCache.getCacheByPlayer(p).getRank() != Rank.OWNER){
					p.sendMessage(ChatColor.RED + "Erreur: Tu ne peux pas faire cette commande lorsque tu es dans un jeux !");
				} else if (gameState.equalsIgnoreCase("other") || PlayerCache.getCacheByPlayer(p).getRank() == Rank.OWNER){
					if (p.isFlying()) {
						p.setAllowFlight(false);
						p.setFlying(false);
						p.sendMessage(ChatColor.RED + "Fly: Fly mode desactiver");
					} else {
						p.setVelocity(p.getLocation().getDirection().setY(1));	
						p.setAllowFlight(true);
						p.setFlying(true);
						p.sendMessage(ChatColor.GREEN + "Fly: Fly mode activer");
					}
				} else if (gameState.equalsIgnoreCase("game") && MainLectusApi.getInstance().getSql().hasModMode(p) || PlayerCache.getCacheByPlayer(p).getRank() == Rank.OWNER) {
					if (p.isFlying()) {
						p.setAllowFlight(false);
						p.setFlying(false);
						p.sendMessage(ChatColor.RED + "Dly: Fly mode desactiver");
					} else {
						p.setVelocity(p.getLocation().getDirection().setY(1));	
						p.setAllowFlight(true);
						p.setFlying(true);
						p.sendMessage(ChatColor.GREEN + "Dly: Fly mode activer");
					}
				}
			} else {
				p.sendMessage(ChatColor.RED + "Erreur: Tu n'a pas la permission (rank.modo) pour faire cette commande!");
			}

		} else {
			return true;
		}
		return false;
	}

}