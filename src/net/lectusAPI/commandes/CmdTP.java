package net.lectusAPI.commandes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.lectusAPI.MainLectusApi;
import net.lectusAPI.cache.PlayerCache;
import net.lectusAPI.grade.Rank;

public class CmdTP implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String laber, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Rank.BUILDEUR.hasPermission(player)) {
				String gameState = MainLectusApi.getInstance().APIMODE;
				if (gameState.equalsIgnoreCase("game") && !MainLectusApi.getInstance().getSql().hasModMode(player)
						&& PlayerCache.getCacheByPlayer(player).getRank() != Rank.OWNER) {
					player.sendMessage(
							ChatColor.RED + "Erreur: Tu ne peux pas faire cette commande lorsque tu es dans un jeux!");
				} else if (gameState.equalsIgnoreCase("other")
						|| PlayerCache.getCacheByPlayer(player).getRank() == Rank.OWNER) {

					if (args.length == 0) {
						player.sendMessage(ChatColor.RED + "Erreur: Utilisation incorrecte !");
						player.sendMessage(ChatColor.RED + "/tp");
						player.sendMessage(ChatColor.RED + "/tp <joueur>");
						player.sendMessage(ChatColor.RED + "/tp <joueur> <joueur>");
					} else if (args.length == 1) {
						Player target1 = Bukkit.getPlayer(args[0]);
						if (target1 == null) {
							player.sendMessage(ChatColor.RED + "Erreur: Le joueur " + ChatColor.YELLOW + args[0]
									+ ChatColor.RED + " n'est pas en ligne!");
							return true;
						} else {
							player.teleport(target1.getLocation());
						}
					} else if (args.length == 2) {
						Player target1 = Bukkit.getPlayer(args[0]);
						Player target2 = Bukkit.getPlayer(args[1]);
						if (target1 == null) {
							player.sendMessage(ChatColor.RED + "Erreur: Le joueur " + ChatColor.YELLOW + args[0]
									+ ChatColor.RED + " n'est pas en ligne!");
							return true;
						} else if (target2 == null) {
							player.sendMessage(ChatColor.RED + "Erreur: Le joueur " + ChatColor.YELLOW + args[1]
									+ ChatColor.RED + " n'est pas en ligne!");
							return true;
						} else {
							target1.teleport(target2.getLocation());
						}
					} else {
						player.sendMessage(ChatColor.RED + "Erreur: Utilisation incorrecte !");
						player.sendMessage(ChatColor.RED + "/tp");
						player.sendMessage(ChatColor.RED + "/tp <joueur>");
						player.sendMessage(ChatColor.RED + "/tp <joueur> <joueur>");
					}

				} else if (gameState.equalsIgnoreCase("game") && MainLectusApi.getInstance().getSql().hasModMode(player)
						|| PlayerCache.getCacheByPlayer(player).getRank() == Rank.OWNER) {

					if (args.length == 0) {
						player.sendMessage(ChatColor.RED + "Erreur: Utilisation incorrecte !");
						player.sendMessage(ChatColor.RED + "/tp");
						player.sendMessage(ChatColor.RED + "/tp <joueur>");
						player.sendMessage(ChatColor.RED + "/tp <joueur> <joueur>");
					} else if (args.length == 1) {
						Player target1 = Bukkit.getPlayer(args[0]);
						if (target1 == null) {
							player.sendMessage(ChatColor.RED + "Erreur: Le joueur " + ChatColor.YELLOW + args[0]
									+ ChatColor.RED + " n'est pas en ligne!");
							return true;
						} else {
							player.teleport(target1.getLocation());
						}
					} else if (args.length == 2) {
						Player target1 = Bukkit.getPlayer(args[0]);
						Player target2 = Bukkit.getPlayer(args[1]);
						if (target1 == null) {
							player.sendMessage(ChatColor.RED + "Erreur: Le joueur " + ChatColor.YELLOW + args[0]
									+ ChatColor.RED + " n'est pas en ligne!");
							return true;
						} else if (target2 == null) {
							player.sendMessage(ChatColor.RED + "Erreur: Le joueur " + ChatColor.YELLOW + args[1]
									+ ChatColor.RED + " n'est pas en ligne!");
							return true;
						} else {
							target1.teleport(target2.getLocation());
						}
					} else {
						player.sendMessage(ChatColor.RED + "Erreur: Utilisation incorrecte !");
						player.sendMessage(ChatColor.RED + "/tp");
						player.sendMessage(ChatColor.RED + "/tp <joueur>");
						player.sendMessage(ChatColor.RED + "/tp <joueur> <joueur>");
					}

				} else {
					player.sendMessage(ChatColor.RED
							+ "Erreur: Tu n'a pas la permission (rank.buildeur) pour faire cette commande!");
				}
			}
			return true;
		}
		return true;
	}
}
